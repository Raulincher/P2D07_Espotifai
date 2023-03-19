package Persistance.dao.sql;

import Persistance.dao.UserDao;
import Business.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class SQLUserDao implements UserDao {

    private final Connection remoteConnection;
    public SQLUserDao(Connection remoteConnection){
        this.remoteConnection = remoteConnection;
    }

    public void register(User user) {

        try {
            Statement statement = remoteConnection.createStatement();
            statement.executeQuery("USE espotifai");
            String username = user.getUsername();
            String email = user.getEmail();
            String password = user.getPassword();
            try {
                String register = "INSERT INTO user (username, email, password) VALUES (?, ?, ?)";
                PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                preparedStmt.setString (1, username );
                preparedStmt.setString (2, email);
                preparedStmt.setString (3, password);
                preparedStmt.execute();
            }catch (SQLException e){
                System.err.println("User already exists");
            }
        } catch (SQLException e) {
            System.err.println("Espotifai not found in register");
        }
    }

    public int userExists(User user){
        int error = 0;
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
                    if (rs.getInt(1) == 0) {
                        error = 0;
                    }else{
                        error = 1;
                    }
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

    public void Delete(User user){
        int error = 0;
        try{
            Statement statement = remoteConnection.createStatement();
            statement.executeQuery("USE espotifai");
            try{
                String username = user.getUsername();
                String email = user.getEmail();
                String password = user.getPassword();
                System.out.println(email);
                System.out.println(password);
                System.out.println(username);
                String queryExists = "DELETE FROM user where ((email = ? OR username = ?) AND password = ?)";
                PreparedStatement prepared = remoteConnection.prepareStatement(queryExists);
                prepared.setString (1, email);
                prepared.setString (2, username);
                prepared.setString (3, password);
                prepared.execute();
                System.out.println("deleted Successfully");

            }catch(SQLException e){
                System.err.println("The user you try to delete doesn't exist");
            }
        }catch (SQLException e){
            //error = 1;
            System.err.println("Espotifai not found in Delete");
        }
    }

    public int Login(User user){
        int error = 0;
        try{
            Statement statement = remoteConnection.createStatement();
            statement.executeQuery("USE espotifai");
            try{
                String username = user.getUsername();
                String email = user.getEmail();
                String password = user.getPassword();
                String queryExists = "SELECT count(*) FROM user where ((email = ? OR username = ?) AND password = ?)";
                PreparedStatement prepared = remoteConnection.prepareStatement(queryExists);
                prepared.setString (1, email);
                prepared.setString (2, username);
                prepared.setString (3, password);
                ResultSet rs = prepared.executeQuery();

                while (rs.next()) {
                    if (rs.getInt(1) == 0) {
                        error = 1;
                        //no login
                    }else{
                        error = 0;
                        //login
                    }
                }
            }catch(SQLException e){
                System.err.println("Error at login");
            }
        }catch (SQLException e){
            //error = 1;
            System.err.println("Espotifai not found in login");
        }
        return error;
    }

}

