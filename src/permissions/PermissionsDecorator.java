package permissions;

import java.util.HashMap;
import java.util.Map;

public class PermissionsDecorator {
    private Map<User, AccessType> userPermissions;

    public PermissionsDecorator() {
        this.userPermissions = new HashMap<>();
    }

    public void setPermission(User user, AccessType accessType) {
        userPermissions.put(user, accessType);
    }

    public AccessType getPermission(User user) {
        return userPermissions.getOrDefault(user, AccessType.NONE);
    }

    public enum AccessType {
        READ, WRITE, COMMENT, NONE
    }
}
