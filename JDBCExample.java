import java.sql.*;
import java.util.Scanner;

public class JDBCExample {

    public static void main(String[] args) {
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Data_base_name",
                    "username",
                    "password");

            // Create a statement object for executing SQL queries
            Statement statement = connection.createStatement();

            // Create a Scanner object to get user input
            Scanner sc = new Scanner(System.in);

            // Prompt the user to enter the employee ID
            System.out.print("Enter employee id whose record you want to fetch: ");
            int id = sc.nextInt();

            // SQL query to select employee details by ID
            String query = "SELECT * FROM employees WHERE id = ?";

            // Create a prepared statement to execute the parameterized query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id); // Set the parameter for the employee ID

            // Execute the query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the result set has data
            if (resultSet.next()) {
                System.out.println("Employee record...");

                // Display column headers
                System.out.println("ID | name | salary | department");

                // Retrieve data from the result set
                id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int salary = resultSet.getInt("salary");
                String department = resultSet.getString("department");

                // Display employee details
                System.out.println(id + " | " + name + " | " + salary + " | " + department);
            } else {
                System.out.println("Employee's id " + id + " doesn't exist!");
            }

            // Close all resources
            if (connection != null)
                connection.close();
            if (statement != null)
                statement.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (resultSet != null)
                resultSet.close();
            sc.close(); // Close scanner
        } catch (Exception e) {
            // Handle any exceptions
            System.out.println("Error: Please check your credentials!");
        }
    }
}
