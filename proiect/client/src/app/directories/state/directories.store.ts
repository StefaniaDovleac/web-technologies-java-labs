import { Injectable } from '@angular/core';
import { Store, StoreConfig } from '@datorama/akita';
import FileSystemItem from 'devextreme/file_management/file_system_item';
import { FileDTO } from '../files-http.service';
import { CustomFileSystemItem } from './directories.service';

export interface DirectoriesState {
    directories: any[],
    fileSystemItems: CustomFileSystemItem[],
    files: FileDTO[],
}

export function createInitialState(): DirectoriesState {
    return {
        directories: [],
        fileSystemItems: [],
        files: [],
    };
}


@Injectable({ providedIn: 'root' })
@StoreConfig({
    name: 'directories',
    resettable: true,
    // deepFreezeFn
})
export class DirectoriesStore extends Store<DirectoriesState> {

    constructor() {
        super(createInitialState());
    }

}
