import components.*;
import patterns.*;
import storage.*;

public class Main {
    public static void main(String[] args) {
        FileSystemManager fsManager = FileSystemManager.getInstance();
        Folder root = fsManager.getRoot();

        // Create some files and folders
        Folder docs = new Folder("Documents");
        root.add(docs);

        // Create threads for file operations
        Thread createFileThread = new Thread(() -> {
            CreateFileOperation createFile = new CreateFileOperation(docs, "file1.txt");
            createFile.execute();
        });

        Thread writeFileThread = new Thread(() -> {
            File file = (File) docs.getChild("file1.txt");
            if (file != null) {
                file.writeContent("Hello, World!");
                System.out.println("Content of " + file.getName() + ": " + file.readContent());
            }
        });

        createFileThread.start();
        writeFileThread.start();

        try {
            createFileThread.join();
            writeFileThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        docs.display("  ");
    }
}
