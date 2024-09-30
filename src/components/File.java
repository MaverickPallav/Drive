package components;

import versions.FileVersion;
import permissions.PermissionsDecorator;

// File.java
import java.util.ArrayList;
import java.util.List;

public class File implements FileSystemComponent {
    private String name;
    private String content;
    private List<FileVersion> versions;
    private PermissionsDecorator permissions;

    public File(String name) {
        this.name = name;
        this.content = "";
        this.versions = new ArrayList<>();
        this.permissions = new PermissionsDecorator();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void add(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot add to a file.");
    }

    @Override
    public void remove(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot remove from a file.");
    }

    @Override
    public FileSystemComponent getChild(String name) {
        throw new UnsupportedOperationException("Files do not contain children.");
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "File: " + name);
    }

    public synchronized void writeContent(String content) {
        this.content = content;
        versions.add(new FileVersion(content));
    }

    public synchronized String readContent() {
        return content;
    }

    public List<FileVersion> getVersions() {
        return versions;
    }

    @Override // Implementing the new method
    public PermissionsDecorator getPermissions() {
        return permissions;
    }
}
