import FileSystemItem from "devextreme/file_management/file_system_item";
import { Observable } from "rxjs";
import { filter, map, tap } from "rxjs/operators";
import { DirectoryDTO } from "./directories-http.service";
import { DirectoriesQuery } from "./state/directories.query";
import { DirectoriesService } from "./state/directories.service";

// var items = [{
//     path: "",
//     name: "Directory1",
//     pathKeys: ["Files"],
//     isDirectory: true,
//     hasSubDirectories: true,
// } as FileSystemItem,

// {
//     path: "Directory1",
//     name: "File1",
//     key: "Directory1/ File1",
//     isDirectory: false,

// } as FileSystemItem,
// {
//     path: "Directory1",
//     name: "Directory3",
//     isDirectory: true,
//     hasSubDirectories: false

// } as FileSystemItem,
// {
//     path: "",
//     name: "Directory2",
//     isDirectory: true,
//     hasSubDirectories: false,

// } as FileSystemItem
// ];

var items;
// export var FileSys = function (service: DirectoriesService, fileSystemItems$: Observable<FileSystemItem[]>) {
export var FileSys = function (service: DirectoriesService, fileSystemItems: FileSystemItem[]) {

    var getItems = function (path) {
        // return service.getItems(path);
        return fileSystemItems.filter(i => i.path === path);
    }

    var createDirectory = function (parent, name: string) {
        console.log("parent", parent);

        service.addDirectory(parent, name);
    }

    var renameItem = function (item, name) {

        this.service.renameItem(item, name);
    }

    var deleteItem = function (item: FileSystemItem) {
        service.deleteItem(item);
    }

    var renameItem = function (item, destinationDirectory) {
        service.renameItem(item, destinationDirectory);
    }

    var uploadFileChunk = function (fileData, uploadInfo, destinationDirectory) {
        service.uploadFileChunk(fileData, uploadInfo, destinationDirectory)
    }

    var downloadItem = function (item) {
        service.downloadItem(item);
    }
    return {
        getItems: getItems,
        createDirectory: createDirectory,
        renameItem: renameItem,
        deleteItem: deleteItem,
        uploadFileChunk: uploadFileChunk,
        downloadItem: downloadItem,
    }
}

var test;