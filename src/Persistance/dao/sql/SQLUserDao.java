package Persistance.dao.sql;

import Persistance.dao.UserDao;
import Business.Entities.User;

import java.sql.*;


public class SQLUserDao implements UserDao {

    private final Connection remoteConnection;
    public SQLUserDao(Connection remoteConnection){
        this.remoteConnection = remoteConnection;
    }

    public void Register(User user) {

        try {
            Statement statement = remoteConnection.createStatement();
            statement.executeQuery("USE espotifai");
            try {
                String username = user.getUsername();
                String email = user.getEmail();
                String password = user.getPassword();
                String register = "INSERT INTO user (username, email, password) VALUES (?, ?, ?)";
                PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                preparedStmt.setString (1, username);
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
                    if (rs.getInt(1) == 0) {
                        error = false;
                    }else{
                        error = true;
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

    public boolean Login(User user){
        boolean error = false;
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
                    //no login
                    //login
                    error = rs.getInt(1) == 0;
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

