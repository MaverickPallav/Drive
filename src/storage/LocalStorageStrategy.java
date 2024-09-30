package storage;

import components.File;

public class LocalStorageStrategy implements StorageStrategy {
    @Override
    public void saveFile(File file) {
        System.out.println("Saving file locally: " + file.getName());
    }

    @Override
    public File loadFile(String fileName) {
        System.out.println("Loading file locally: " + fileName);
        return new File(fileName);
    }
}
