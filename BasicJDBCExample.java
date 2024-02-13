import java.sql.*;
import java.util.Scanner;

public class BasicJDBCExample {
    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter JDBC URL, username, password, and query
        System.out.print("Enter JDBC URL(e.g: jdbc:mysql://host_name:port_number/database_name): ");
        String jdbcUrl = scanner.nextLine();
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter SQL query: ");
        String query = scanner.nextLine();

        try {
            // Establishing a connection to the database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Creating a statement to execute SQL queries
            statement = connection.createStatement();

            // Executing the user-entered SQL query and storing the result in a ResultSet
            resultSet = statement.executeQuery(query);

            // Displaying the results
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            System.out.println("Query result:");
            // Print column names
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + " | ");
            }
            System.out.println();
            
            // Print rows
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + " | ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            // Handle database-related exceptions
            if (e.getSQLState().equals("42000")) {
                System.out.println("Error: Database not found. Please check if the database exists.");
            } else if (e.getSQLState().equals("28000")) {
                System.out.println("Error: Access denied. Please check your username or password.");
            } else if (e.getSQLState().equals("42S02")) {
                System.out.println("Error: Table not found. Please check if the table exists.");
            } else {
                System.out.println("Error executing SQL query: " + e.getMessage());
            }
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        } finally {
            // Closing all resources to prevent memory leaks
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                scanner.close(); // close the scanner
            } catch (SQLException e) {
                System.out.println("Error: Something went wrong while closing connections.");
            }
        }
    }
}
