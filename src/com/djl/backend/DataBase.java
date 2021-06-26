
package com.djl.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataBase {
    
    //Atributos de la clase
    private final String url = "jdbc:postgresql://ec2-52-72-65-76.compute-1.amazonaws.com:5432/d4qqqsbi8k716u"; 
    private final String user = "dgnhipvcxvjkym";
    private final String pwd = "b23f432f4a8c5d9283596679a015ad2acf5402a92830ed43c92f467b85e15819";
    private static final String INSERT_USERS_SQL = "INSERT INTO djl_users" +
        "  (id, name, timestamp) VALUES " +
        " (?, ?, ?);";
    private static final String UPDATE_USERS_SQL = "update users set name = ? where id = ?;";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String QUERY = "select id,name,email,country,password from Users where id =?";
    private static final String SELECT_ALL_QUERY = "select * from users";
    
    
    
    
    public void insertRecord() throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, pwd);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Tony");
            preparedStatement.setString(3, "26-jun-21");

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }
    
    public void updateRecord() throws SQLException {
        System.out.println(UPDATE_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, pwd);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setString(1, "Ram");
            preparedStatement.setInt(2, 1);

            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }
    
    public void deleteRecord() throws SQLException {
        System.out.println(DELETE_USERS_SQL);

        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, pwd);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);) {
            preparedStatement.setInt(1, 1);

            // Step 3: Execute the query or update query
            int result = preparedStatement.executeUpdate();
            System.out.println("Number of records affected :: " + result);
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }
    
    public void getUserById() {
        // using try-with-resources to avoid closing resources (boiler plate
        // code)

        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, pwd);
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, 1);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String password = rs.getString("password");
                System.out.println(id + "," + name + "," + email + "," + country + "," + password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}