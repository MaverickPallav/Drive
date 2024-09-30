package search;

import components.*;

public class FileSystemSearch {
    private Folder root;

    public FileSystemSearch(Folder root) {
        this.root = root;
    }

    public FileSystemComponent search(String name) {
        return searchRecursive(root, name);
    }

    private FileSystemComponent searchRecursive(FileSystemComponent component, String name) {
        if (component.getName().equals(name)) {
            return component;
        }
        if (component instanceof Folder) {
            for (FileSystemComponent child : ((Folder) component).getChildren().values()) {
                FileSystemComponent result = searchRecursive(child, name);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}
