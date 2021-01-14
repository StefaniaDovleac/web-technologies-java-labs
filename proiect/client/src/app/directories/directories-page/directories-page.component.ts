import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import CustomFileSystemProvider from 'devextreme/file_management/custom_provider';
import FileSystemItem from 'devextreme/file_management/file_system_item';
import { CustomFileSystemItem, DirectoriesService } from '../state/directories.service';
import { DirectoriesQuery } from '../state/directories.query';
import { FileSys } from '../file-system'
import { filter, skip, skipWhile, takeWhile } from 'rxjs/operators';

@Component({
  selector: 'app-directories-page',
  templateUrl: './directories-page.component.html',
  styleUrls: ['./directories-page.component.scss']
})
export class DirectoriesPageComponent implements OnInit, OnDestroy {

  customFileProvider: CustomFileSystemProvider;
  fileSystemItems$ = this.query.select(q => q.fileSystemItems);
  items$ = this.fileSystemItems$.pipe(skipWhile(t => t.length == 0));
  isDestroyed: boolean;
  constructor(private service: DirectoriesService, private query: DirectoriesQuery) {

  }

  async ngOnInit() {
    await this.service.initialize();
    this.query.select(q => q.fileSystemItems).pipe(takeWhile(_ => !this.isDestroyed)).subscribe(i => {
      fileSystem = FileSys(this.service, i);
      this.customFileProvider = new CustomFileSystemProvider({
        getItems: function (parentDirectory) {
          return fileSystem.getItems(parentDirectory.path);
        },
        createDirectory: function (parentDirectory, name) {
          return fileSystem.createDirectory(parentDirectory, name);
        },
        renameItem: function (item: FileSystemItem, name: string) {
          return fileSystem.renameItem(item, name);
        },
        deleteItem: function (item: FileSystemItem) {
          return fileSystem.deleteItem(item);
        },
        moveItem: function (item, destinationDirectory) {
          return fileSystem.moveItem(item, destinationDirectory);
        },
        uploadFileChunk: function (fileData, uploadInfo, destinationDirectory) {
          return fileSystem.uploadFileChunk(fileData, uploadInfo, destinationDirectory);
        },
        downloadItems: function (items) {
          return fileSystem.downloadItem(items[0]);
        }
      });

    })

    // fileSystem = FileSys(this.service, this.query.select(q => q.fileSystemItems));
    // this.customFileProvider = new CustomFileSystemProvider({
    //   getItems: function (parentDirectory) {
    //     return fileSystem.getItems(parentDirectory.path);
    //   },
    //   createDirectory: function (parentDirectory, name) {
    //     return fileSystem.createDirectory(parentDirectory, name);
    //   },
    //   renameItem: function (item: FileSystemItem, name: string) {
    //     return fileSystem.renameItem(item, name);
    //   },
    //   deleteItem: function (item: FileSystemItem) {
    //     return fileSystem.deleteItem(item);
    //   }


    // });
  }

  ngOnDestroy() {
    this.isDestroyed = true;
  }


}

let fileSystem = null;


