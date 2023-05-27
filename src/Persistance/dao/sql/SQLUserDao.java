package Persistance.dao.sql;

import Persistance.dao.UserDao;
import Business.Entities.User;
import Persistance.dao.UserNotFoundException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


public class SQLUserDao implements UserDao {

    private final Connection remoteConnection;
    public SQLUserDao(Connection remoteConnection){
        this.remoteConnection = remoteConnection;
    }

    public void register(User user) {
        try {
            String generatedPassword = null;
            try{
                String password = user.getPassword();
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] bytes = md.digest();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                generatedPassword = sb.toString();
            }catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            Statement statement = remoteConnection.createStatement();
            statement.executeQuery("USE espotifai");
            try {
                String username = user.getUsername();
                String email = user.getEmail();
                String register = "INSERT INTO user (username, email, password) VALUES (?, ?, ?)";
                PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                preparedStmt.setString (1, username);
                preparedStmt.setString (2, email);
                preparedStmt.setString (3, generatedPassword);
                preparedStmt.execute();
            }catch (SQLException e){
                System.err.println("User already exists");
            }
        } catch (SQLException e) {
            System.err.println("Espotifai not found in register");
        }
    }

    public boolean userExists(User user){
        boolean error = false;
        try{
            Statement statement = remoteConnection.createStatement();
            statement.executeQuery("USE espotifai");
            try{
                String username = user.getUsername();
                String email = user.getEmail();
                String queryExists = "SELECT count(*) FROM user where email = ? OR username = ?";
                PreparedStatement prepared = remoteConnection.prepareStatement(queryExists);
                prepared.setString (1, email);
                prepared.setString (2, username);
                ResultSet rs = prepared.executeQuery();
                while (rs.next()) {
                    error = rs.getInt(1) != 0;
                }
            }catch(SQLException e){
                System.err.println("Error at UserExists");
            }
        }catch (SQLException e){
            //error = 1;
            System.err.println("Espotifai not found in UserExists");
        }
        return error;
    }

    public void delete(User user) throws UserNotFoundException {
        int affected = 0;
        System.out.println(user.getEmail());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());

        try{
            String generatedPassword = null;
            try{
                String password = user.getPassword();
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] bytes = md.digest();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                generatedPassword = sb.toString();
                System.out.println(generatedPassword);
            }catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            Statement statement = remoteConnection.createStatement();
            statement.executeQuery("USE espotifai");
            try{
                String username = user.getUsername();
                String email = user.getEmail();
                String queryExists = "DELETE FROM user where ((email = ? OR username = ?) AND password = ?)";
                PreparedStatement prepared = remoteConnection.prepareStatement(queryExists);
                prepared.setString (1, email);
                prepared.setString (2, username);
                prepared.setString (3, generatedPassword);
                affected = prepared.executeUpdate();

            }catch(SQLException e){
                throw new UserNotFoundException("The user you are trying to delete doesn't exist");
            }
        }catch (SQLException e){
            //error = 1;
            System.err.println("Espotifai not found in Delete");
        }

        if(affected > 0){
            System.out.println("deleted Successfully");
        }else{
            System.err.println("The user you try to delete doesn't exist");
        }
    }

    public boolean login(User user) throws UserNotFoundException{
        boolean error = false;

        try{
            String generatedPassword = null;
            try{
                String password = user.getPassword();
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] bytes = md.digest();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                generatedPassword = sb.toString();
            }catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            Statement statement = remoteConnection.createStatement();
            statement.executeQuery("USE espotifai");
            try{
                String username = user.getUsername();
                String email = user.getEmail();
                String queryExists = "SELECT count(*) FROM user where ((email = ? OR username = ?) AND password = ?)";
                PreparedStatement prepared = remoteConnection.prepareStatement(queryExists);
                prepared.setString (1, email);
                prepared.setString (2, username);
                prepared.setString (3, generatedPassword);
                ResultSet rs = prepared.executeQuery();
                while (rs.next()) {
                    if (rs.getInt(1) == 0) {
                        error = false;
                        //no login
                    }else{
                        error = true;
                        //login
                    }
                }
            }catch(SQLException e){
                throw new UserNotFoundException("User not found");
            }
        }catch (SQLException e){
            //error = 1;
            System.err.println("Espotifai not found in login");
        }

        return error;
    }

}

