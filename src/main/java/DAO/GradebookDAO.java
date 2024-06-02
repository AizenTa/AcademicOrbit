package DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GradebookDAO {
    MaConnexion connexion;
    Statement stmt;

    public GradebookDAO(MaConnexion connexion) throws SQLException {
        this.connexion = connexion;
        stmt = connexion.getStmt();
    }

    public boolean validateAdmin(String username, String password) throws SQLException {
        String hashedPassword = hashString(password);
        ResultSet rs = stmt.executeQuery("SELECT * FROM admin WHERE username = '" + username + "' AND password = '" + hashedPassword + "'");
        return rs.next();
    }

    public boolean validateProfessor(String username, String password) throws SQLException {
        String hashedPassword = hashString(password);
        ResultSet rs = stmt.executeQuery("SELECT * FROM prof WHERE username = '" + username + "' AND password = '" + hashedPassword + "'");
        return rs.next();
    }

    public boolean validateStudent(String username, String password) throws SQLException {
        String hashedPassword = hashString(password);
        ResultSet rs = stmt.executeQuery("SELECT * FROM student WHERE username = '" + username + "' AND password = '" + hashedPassword + "'");
        return rs.next();
    }

    private static String hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(input.getBytes());
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}