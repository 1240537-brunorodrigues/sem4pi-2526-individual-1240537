package pt.ipp.isep.dei.domain.user;

public enum UserStatus {
    ENABLED,
    DISABLED;

    public boolean isEnabled() {
        return this == ENABLED;
    }

    public boolean isDisabled() {
        return this == DISABLED;
    }
}