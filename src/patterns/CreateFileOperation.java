package patterns;

import components.Folder;
import components.File;

public class CreateFileOperation extends FileOperation {
    private Folder folder;
    private String fileName;

    public CreateFileOperation(Folder folder, String fileName) {
        this.folder = folder;
        this.fileName = fileName;
    }

    @Override
    protected boolean validate() {
        return folder.getChild(fileName) == null;
    }

    @Override
    protected void performOperation() {
        File newFile = new File(fileName);
        folder.add(newFile);
        System.out.println("Created file: " + fileName);
    }
}
