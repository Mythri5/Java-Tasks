import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MetaData {

    public static void main(String[] args) {
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:url", "username", "password");

            // Create a statement for executing SQL queries
            Statement statement = connection.createStatement();

            // Define the SQL query to retrieve data
            String sqlQuery = "SELECT * FROM employees";

            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Get metadata of the result set
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            // Get column count
            int colCount = resultSetMetaData.getColumnCount();
            System.out.println("Total column in the table is: " + colCount);
            System.out.println("Details of the columns...");
            System.out.println("==========================");

            // Print details of each column
            for (int i = 1; i <= colCount; i++) {
                System.out.println("Column Name: " + resultSetMetaData.getColumnName(i));
                System.out.println("Data Type: " + resultSetMetaData.getColumnTypeName(i));

                // This might not work for all databases, as not all databases support getting
                // the table name
                // For some databases, it might return an empty string or null
                System.out.println("Column Table Name: " + resultSetMetaData.getTableName(i));
                System.out.println("-------------------------");
            }

            // Close the connection, statement, and result set to release resources
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            // Handle any SQL exceptions
            System.out.println("Exception occurred: " + e.getMessage());
        }

    }
}
