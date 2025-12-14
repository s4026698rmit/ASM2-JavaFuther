package com.groupX.ems.repositories;

/**
 * @author 13
 */

import com.groupX.ems.config.DatabaseConnection;
import com.groupX.ems.models.Person;
import com.groupX.ems.models.enums.Role;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;

public class AuthRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthRepository.class);

    private Connection getConnection() {
        Connection conn = DatabaseConnection.getInstance();
        if (conn == null) {
            throw new IllegalStateException("Database connection is not available");
        }
        return conn;
    }

    
    public Optional<Person> login(String username, String rawPassword) {
        String sql = """
                SELECT p.id, p.full_name, p.dob, p.email, p.phone, p.username, p.password_hash, p.role, COALESCE(a.id, pr.id, e.id, s.id) as role_id
                FROM person p 
                left join attendee a on p.id = a.person_id 
                left join presenter pr on p.id = pr.person_id 
                left join event_admin e on p.id = e.person_id 
                left join system_admin s on p.id = s.person_id 
                WHERE p.username = ?
                """;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                String storedHash = rs.getString("password_hash");
                if (!BCrypt.checkpw(rawPassword, storedHash)) {
                    return Optional.empty();
                }
                return Optional.of(mapPerson(rs, storedHash));
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to execute login for user {}", username, e);
            return Optional.empty();
        }
    }

   
    public Person registerAccount(String fullName,
                                  LocalDate dob,
                                  String email,
                                  String phone,
                                  String username,
                                  String rawPassword,
                                  String role) {
        String insertPerson = "INSERT INTO person (full_name, dob, email, phone, username, password_hash, role) VALUES (?,?,?,?,?,?,?)";
        String hashed = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);
            long personId;
            try (PreparedStatement ps = conn.prepareStatement(insertPerson, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, fullName);
                ps.setObject(2, dob);
                ps.setString(3, email);
                ps.setString(4, phone);
                ps.setString(5, username);
                ps.setString(6, hashed);
                ps.setString(7, role.toUpperCase());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (!keys.next()) {
                        throw new SQLException("Failed to retrieve generated person id");
                    }
                    personId = keys.getLong(1);
                }
            }

            insertRoleRow(conn, personId, role);
            conn.commit();
            return new Person(personId, fullName, dob, email, phone, username, hashed, Role.valueOf(role.toUpperCase()));
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                LOGGER.error("Failed to rollback after register failure", ex);
            }
            LOGGER.error("Failed to register account for username {}", username, e);
            throw new RuntimeException("Could not register account", e);
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.warn("Failed to reset autocommit", e);
            }
        }
    }

   

    private void insertRoleRow(Connection conn, long personId, String role) throws SQLException {
        String normalized = role.toUpperCase();
        String tableName = switch (normalized) {
            case "ATTENDEE" -> "attendee";
            case "PRESENTER" -> "presenter";
            case "EVENT_ADMIN" -> "event_admin";
            case "SYSTEM_ADMIN" -> "system_admin";
            default -> throw new IllegalArgumentException("Unsupported role: " + role);
        };
        String sql = "INSERT INTO " + tableName + " (person_id) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, personId);
            ps.executeUpdate();
        }
    }

    private Person mapPerson(ResultSet rs, String passwordHash) throws SQLException {
        long id = rs.getLong("id");
        String fullName = rs.getString("full_name");
        LocalDate dob = rs.getObject("dob", LocalDate.class);
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String username = rs.getString("username");
        Role role = Role.valueOf(rs.getString("role").toUpperCase());
        return new Person(id, fullName, dob, email, phone, username, passwordHash, role);
    }
    
}

