package pt.ipp.isep.dei.application;

import pt.ipp.isep.dei.domain.auth.Permission;
import pt.ipp.isep.dei.domain.user.User;

public class AuthorizationService {

    private final AuthenticatedUserSession session;

    public AuthorizationService(AuthenticatedUserSession session) {
        if (session == null) {
            throw new IllegalArgumentException("Authenticated user session cannot be null.");
        }

        this.session = session;
    }

    public boolean isAuthorized(String permissionName) {
        if (!session.isLoggedIn()) {
            return false;
        }

        Permission permission = new Permission(permissionName);
        User currentUser = session.currentUser();

        return currentUser.isEnabled() && currentUser.hasPermission(permission);
    }

    public void requirePermission(String permissionName) {
        if (!isAuthorized(permissionName)) {
            throw new SecurityException("User is not authorized to perform this operation.");
        }
    }
}