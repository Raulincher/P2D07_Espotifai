package Persistance.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static DatabaseConnector instance = null;


    public static Connection getInstance(){
        Connection connection = null;
        if (instance == null ){
            instance = new DatabaseConnector("root", "", "localhost", 3306, "espotifai");
            connection = instance.connect();
        }
        return connection;
    }

    // Attributes to connect to the database.
    private final String username;
    private final String password;
    private final String url;
    private Connection remoteConnection;

    // Parametrized constructor
    private DatabaseConnector(String username, String password, String ip, int port, String database) {
        this.username = username;
        this.password = password;
        this.url = "jdbc:mysql://" + ip + ":" + port + "/" + database;
    }

    public Connection connect() {
        try {
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            System.out.println("Driver loaded!");
            try {
                remoteConnection = DriverManager.getConnection(url, username, password);
                System.out.println("Successful connection");
            } catch (SQLException e) {
                System.err.println("Failed to create the database connection.");
            }
        }catch(ClassNotFoundException e){
            System.out.println("Driver not found.");
        }
        return remoteConnection;
    }
}
