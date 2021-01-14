import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { confirm, alert } from 'devextreme/ui/dialog';
import { arrayAdd, arrayRemove, arrayToggle, arrayUpdate, arrayUpsert } from '@datorama/akita';
import { toByteArray } from 'base64-js';
import { DirectoriesStore } from './directories.store';
import { DirectoriesHttpService, DirectoryDTO } from '../directories-http.service';
import FileSystemItem from 'devextreme/file_management/file_system_item';
import { title } from 'process';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { FileDTO, FilesHttpService } from '../files-http.service';
declare let Camera: any;

@Injectable({ providedIn: 'root' })
export class DirectoriesService {
    data: [{
        name: "MyFile.jpg",
        size: 1024,
        dateModified: "2019/05/08",
        isDirectory: true,
        items: [
            // ...
            // Nested data objects with the same structure
            // ...
        ]
    },
        {
            name: "dir1",
            size: 1024,
            dateModified: "2019/05/08",
            isDirectory: true,
            items: [
                {
                    name: "File2",
                    size: 1024,
                    dateModified: "2019/05/08",
                    isDirectory: true,
                    items: [
                        // ...
                        // Nested data objects with the same structure
                        // ...
                    ]
                }
            ]
        }
    ]

    constructor(private store: DirectoriesStore,
        private toastrService: ToastrService,
        private directoriesHttpService: DirectoriesHttpService,
        private filesHttpService: FilesHttpService) {
    }

    private get directories() {
        return this.store.getValue().directories;
    }

    private get files() {
        return this.store.getValue().files;
    }

    async initialize() {
        try {
            this.store.setLoading(true);
            const directories = await this.directoriesHttpService.getAll().toPromise();
            const files = await this.filesHttpService.getAll().toPromise();
            this.store.update({ directories, files });
            this.updateFileItems();
        } finally {
            this.store.getValue();
        }
    }

    updateFileItems() {
        const state = this.store.getValue();
        const files = state.files;
        const directories = state.directories;
        let fileSystemItems = [];
        fileSystemItems.push(...this.mapDirectories(directories), ...this.mapFiles(files))
        this.store.update({ fileSystemItems })
    }

    getItems(path) {
        console.log("get");
        return this.store.getValue().fileSystemItems.filter(i => i.path === path);
    }

    private mapFiles(files: FileDTO[]): CustomFileSystemItem[] {
        return files.map(file => {
            let filePath = "";
            if (file?.parentId) {
                const parent = this.directories.find(dir => dir.id === file.parentId)
                const parentTitle = parent?.title;
                filePath = this.findPath(parent).slice(0, -1).split("/").reverse().join("/") + "/" + parentTitle;
            }
            console.log(filePath);
            return {
                path: filePath,
                isDirectory: false,
                name: file.title,
                key: file.id + "_" + EntityType.File,
                size: file.size,
                createdBy: file.createdBy

            } as CustomFileSystemItem
        })
    }

    private mapDirectories(directories: DirectoryDTO[]): CustomFileSystemItem[] {
        return directories.map((d: DirectoryDTO) => {
            const path = this.findPath(d).slice(0, -1).split("/").reverse().join("/"); // refactor???
            return {
                path: path,
                isDirectory: true,
                name: d.title,
                key: d.id + "_" + EntityType.Directory, // ex :25_1
                createdBy: d.createdBy,
                hasSubDirectories: this.directories.some(dir => dir.parentId === d.id)
            } as CustomFileSystemItem
        })
    }

    private findPath(directory: DirectoryDTO) {
        if (!directory?.parentId) {
            return "";
        }
        const parent = this.directories.find(d => d.id === directory.parentId);
        return parent.title + "/" + this.findPath(parent);
    }

    public async addDirectory(parent: CustomFileSystemItem, name: string) {
        try {
            console.log("create", parent);
            this.store.setLoading(true);
            const directory = {
                title: name,
                createdBy: 1,
                lastModifiedBy: 1,
                createdOn: new Date(),
                lastModifiedOn: new Date(),
                parentId: parent.key.length > 0 ? +parent.key.split("_")[0] : null,
            } as DirectoryDTO;
            const result = await this.directoriesHttpService.create(directory).toPromise();
            if (result) {
                const newItem = this.mapDirectories([result]);
                const state = this.store.getValue();
                this.store.update({
                    fileSystemItems:
                        arrayAdd(state.fileSystemItems, newItem),
                    // ...arrayUpdate(state.fileSystemItems, (i:FileSystemItem)=>i.)

                    directories: arrayAdd(state.directories, result) //?
                });
            }
        }
        finally {
            this.store.setLoading(false);
        }
    }

    public async renameItem(item: FileSystemItem, name: string) {
        try {
            this.store.setLoading(true);
            const id = +item.key.split("_")[0];
            console.log(id)
            if (item.isDirectory) {
                const directoryToUpdate = this.directories.find(d => d.id === id);

                const newDirectory = {
                    ...directoryToUpdate,
                    title: name,
                    categoryId: 1,
                    parentId: directoryToUpdate.parentId == 0 ? null : directoryToUpdate.parentId,
                    lastModifiedOn: new Date(),
                } as DirectoryDTO

                console.log("dir", newDirectory)
                const directoryUpdated = await this.directoriesHttpService.update(newDirectory).toPromise();
                this.store.update({
                    directories: arrayUpdate(this.store.getValue().directories, (d) => d.id === id, newDirectory)
                })
            } else {
                const fileToUpdate = this.files.find(d => d.id === id);
                const newFile = {
                    ...fileToUpdate,
                    title: name,
                    parentId: fileToUpdate.parentId == 0 ? null : fileToUpdate.parentId,
                    lastModifiedOn: new Date()
                }
                const fileUpdated = await this.filesHttpService.update(newFile).toPromise();
                this.store.update({
                    files: arrayUpdate(this.store.getValue().files, (d) => d.id === id, newFile)
                })
            }
            this.updateFileItems();
        }
        finally {
            this.store.setLoading(false);
        }

    }

    async deleteItem(item: FileSystemItem) {
        try {
            this.store.setLoading(true);
            let result;
            const id = +item.key.split("_")[0];
            if (item.isDirectory) {
                result = await this.directoriesHttpService.delete(id).toPromise();
            } else {
                result = await this.filesHttpService.delete(id).toPromise()
            }
            if (result) {
                this.store.update({
                    fileSystemItems: arrayRemove(this.store.getValue().fileSystemItems, (i: FileSystemItem) => i.key === item.key)
                })
            }
        }
        finally {
            this.store.setLoading(false);
        }
    }

    moveItem(item, destinationDirectory) {
        // const destinationPath = destinationDirectory.
    }

    public async uploadFileChunk(fileData, uploadInfo, destinationDirectory) {
        console.log(fileData);
        console.log(uploadInfo);
        console.log(destinationDirectory);

        let parentId;

        if (destinationDirectory.key.length > 0) {
            parentId = +destinationDirectory.key.split("_")[0];
        }
        else {
            parentId = null;
        }
        try {
            this.store.setLoading(true);

            const newFile = {
                content: "new file",
                createdOn: new Date(),
                createdBy: 1,
                parentId: parentId,
                size: fileData.size,
                title: fileData.name,
                lastModifiedBy: 1,
                lastModifiedOn: new Date(),
                isPublic: false

            } as FileDTO;
            const result = await this.filesHttpService.create(newFile).toPromise();
            if (result) {
                const fileSystemItems = this.mapFiles([result]);
                console.log(fileSystemItems);
                this.store.update({
                    fileSystemItems: arrayAdd(this.store.getValue().fileSystemItems, fileSystemItems[0])
                })
            }
        } finally {
            this.store.setLoading(false);
        }
    }
    reset() {
        this.store.reset();
    }

    public downloadItem(item: FileSystemItem) {
        try {
            const id = +item.key.split("_")[0];
            const url = this.files.find(f => f.id === id)?.fileUri;
            this.store.setLoading(true);
            // if (window.navigator && window.navigator.msSaveOrOpenBlob) {
            //     window.navigator.msSaveOrOpenBlob(blob);
            //     return;
            // }
            const link = document.createElement('a');
            // link.href = window.URL.createObjectURL(blob);
            link.href = url;
            link.download = item.name;
            link.click();

            setTimeout(() => {
                window.URL.revokeObjectURL(link.href);
            }, 100);
        }
        finally {
            this.store.setLoading(false);
        }
        console.log(item);
    }
}

export class CustomFileSystemItem extends FileSystemItem {
    id: number;
    type: EntityType
    createdBy: number;
}

export enum EntityType {
    Directory = 1,
    File = 2
}

