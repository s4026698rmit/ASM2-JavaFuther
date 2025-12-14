package com.groupX.ems.utils;

import com.groupX.ems.models.enums.Role;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public final class LocalStorageUtil {

    private static final Path STORAGE_PATH = Paths.get("localstorage", "current_user_details.txt");

    private LocalStorageUtil() {
    }

    public static void saveCurrentUser(long personId, String username, Role role) {
        try {
            Files.createDirectories(STORAGE_PATH.getParent());
            List<String> lines = List.of(
                    "Username: " + username,
                    "PersonId: " + personId,
                    "Role: " + role.name()
            );
            Files.write(STORAGE_PATH, lines);
        } catch (IOException e) {
            // In skeleton mode, ignore or log if desired.
        }
    }

    public static void clearCurrentUser() {
        try {
            if (Files.exists(STORAGE_PATH)) {
                Files.delete(STORAGE_PATH);
            }
        } catch (IOException e) {
            // In skeleton mode, ignore or log if desired.
        }
    }

    public static Optional<CurrentUserContext> loadCurrentUser() {
        if (!Files.exists(STORAGE_PATH)) {
            return Optional.empty();
        }
        try {
            List<String> lines = Files.readAllLines(STORAGE_PATH);
            String username = null;
            Long personId = null;
            Role role = Role.ANONYMOUS;
            for (String line : lines) {
                if (line.startsWith("Username:")) {
                    username = line.replace("Username:", "").trim();
                } else if (line.startsWith("PersonId:")) {
                    personId = Long.parseLong(line.replace("PersonId:", "").trim());
                } else if (line.startsWith("Role:")) {
                    role = Role.valueOf(line.replace("Role:", "").trim());
                }
            }
            if (username != null && personId != null) {
                return Optional.of(new CurrentUserContext(personId, username, role));
            }
        } catch (Exception e) {
            // In skeleton mode, ignore or log if desired.
        }
        return Optional.empty();
    }

    public static Role getCurrentRoleOrAnonymous() {
        return loadCurrentUser()
                .map(CurrentUserContext::role)
                .orElse(Role.ANONYMOUS);
    }

    public record CurrentUserContext(long personId, String username, Role role) {
    }
}

