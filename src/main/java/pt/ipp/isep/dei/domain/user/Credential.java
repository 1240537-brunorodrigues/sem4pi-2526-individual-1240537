package pt.ipp.isep.dei.domain.user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Credential {

    private static final int MINIMUM_PASSWORD_LENGTH = 8;

    private final String passwordHash;

    public Credential(String rawPassword) {
        validatePassword(rawPassword);
        this.passwordHash = hash(rawPassword);
    }

    private Credential(String passwordHash, boolean alreadyHashed) {
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("Password hash cannot be empty.");
        }

        this.passwordHash = passwordHash;
    }

    public static Credential fromHash(String passwordHash) {
        return new Credential(passwordHash, true);
    }

    public boolean matches(String rawPassword) {
        validatePassword(rawPassword);
        return passwordHash.equals(hash(rawPassword));
    }

    public String passwordHash() {
        return passwordHash;
    }

    private static void validatePassword(String rawPassword) {
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        if (rawPassword.length() < MINIMUM_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("Password must have at least 8 characters.");
        }
    }

    private static String hash(String rawPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexadecimalString = new StringBuilder();

            for (byte b : encodedHash) {
                hexadecimalString.append(String.format("%02x", b));
            }

            return hexadecimalString.toString();

        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("SHA-256 algorithm is not available.", exception);
        }
    }

    @Override
    public String toString() {
        return "Credential{passwordHash='[PROTECTED]'}";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Credential credential)) {
            return false;
        }

        return passwordHash.equals(credential.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passwordHash);
    }
}