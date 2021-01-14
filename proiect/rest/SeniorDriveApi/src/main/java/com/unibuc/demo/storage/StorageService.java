package com.unibuc.demo.storage;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@Service
public class StorageService {

    public static final String storageConnectionString =
            "DefaultEndpointsProtocol=https;" +
                    "AccountName=seniordrive;" +
                    "AccountKey=jcNkyQ28t1n8FrYD6syO5FqfnUkU6jyCEdCF2GAZv3o8s77g5mIVZda19IJlo90Z9s2BjbSQLvDobVCoJ2cykg==;" +
                    "EndpointSuffix=core.windows.net";

    public static final String containerName = "seniordrivefiles";
    private CloudStorageAccount storageAccount;
    private CloudBlobClient blobClient;
    private CloudBlobContainer container;

    File sourceFile = null;

    public StorageService() throws URISyntaxException, StorageException, InvalidKeyException { // throw ???
        storageAccount = CloudStorageAccount.parse(storageConnectionString);
        blobClient = storageAccount.createCloudBlobClient();
        container = blobClient.getContainerReference(containerName);
    }

    public String uploadBlob(String fileName, byte[] byteArray) {
        try {
            CloudBlockBlob cloudBlockBlob = container.getBlockBlobReference(fileName);
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            cloudBlockBlob.upload(inputStream, byteArray.length);
            String fileUri = cloudBlockBlob.getUri().toString();
            return fileUri;

        } catch (StorageException ex) {

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteBlob(String name) {
        try {

            CloudBlockBlob cloudBlockBlob = container.getBlockBlobReference(name);

            return cloudBlockBlob.deleteIfExists();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
}
