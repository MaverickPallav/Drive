package proxy;

import components.FileSystemComponent;
import permissions.*;

public class FileSystemProxy implements FileSystemComponent {
    private FileSystemComponent realComponent;
    private User currentUser;

    public FileSystemProxy(FileSystemComponent component, User user) {
        this.realComponent = component;
        this.currentUser = user;
    }

    @Override
    public String getName() {
        return realComponent.getName();
    }

    @Override
    public void setName(String name) {
        if (hasWritePermission()) {
            realComponent.setName(name);
        } else {
            throw new SecurityException("No write permission for user: " + currentUser.getUsername());
        }
    }

    @Override
    public void add(FileSystemComponent component) {
        if (hasWritePermission()) {
            realComponent.add(component);
        } else {
            throw new SecurityException("No write permission for user: " + currentUser.getUsername());
        }
    }

    @Override
    public void remove(FileSystemComponent component) {
        if (hasWritePermission()) {
            realComponent.remove(component);
        } else {
            throw new SecurityException("No write permission for user: " + currentUser.getUsername());
        }
    }

    @Override
    public FileSystemComponent getChild(String name) {
        if (hasReadPermission()) {
            return realComponent.getChild(name);
        } else {
            throw new SecurityException("No read permission for user: " + currentUser.getUsername());
        }
    }

    @Override
    public void display(String indent) {
        if (hasReadPermission()) {
            realComponent.display(indent);
        } else {
            throw new SecurityException("No read permission for user: " + currentUser.getUsername());
        }
    }

    @Override
    public PermissionsDecorator getPermissions() {
        return realComponent.getPermissions(); // Implementing getPermissions
    }

    private boolean hasReadPermission() {
        PermissionsDecorator.AccessType accessType = getPermissions().getPermission(currentUser);
        return accessType == PermissionsDecorator.AccessType.READ ||
               accessType == PermissionsDecorator.AccessType.WRITE ||
               accessType == PermissionsDecorator.AccessType.COMMENT;
    }

    private boolean hasWritePermission() {
        PermissionsDecorator.AccessType accessType = getPermissions().getPermission(currentUser);
        return accessType == PermissionsDecorator.AccessType.WRITE;
    }
}
