package storage;

import components.File;

public interface StorageStrategy {
    void saveFile(File file);
    File loadFile(String fileName);
}
