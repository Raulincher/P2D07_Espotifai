package Persistance.dao.sql;

import Persistance.dao.UserDao;
import Business.Entities.User;
import Persistance.dao.UserNotFoundException;

import java.sql.*;


public class SQLUserDao implements UserDao {

    private final Connection remoteConnection;
    public SQLUserDao(Connection remoteConnection){
        this.remoteConnection = remoteConnection;
    }

    public void register(User user) {
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
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());

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
                System.out.println(rs);
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

        System.out.println(error);
        return error;
    }

}

