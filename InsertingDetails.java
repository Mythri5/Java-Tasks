import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertingDetails {

    public static void main(String[] args) {
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_name",
                    "username",
                    "password");

            // Create a statement object for executing SQL queries
            Statement statement = connection.createStatement();

            // Create a Scanner object to get user input
            Scanner sc = new Scanner(System.in);

            // Prompt the user to enter the employee ID
            System.out.print("Enter id: ");
            int id = sc.nextInt();

            sc.nextLine();
            // Prompt the user to enter the employee name
            System.out.print("Enter name: ");
            String name = sc.nextLine();

            // Prompt the user to enter the employee salary
            System.out.print("Enter salary: ");
            int salary = sc.nextInt();
            sc.nextLine();
            // Prompt the user to enter the employee ID
            System.out.print("Enter department: ");
            String department = sc.nextLine();
            

            // SQL query to select employee details by ID
            String query = "INSERT INTO employees VALUES(?,?,?,?)";

            // Create a prepared statement to execute the parameterized query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id); // Set the parameter for the employee ID
            preparedStatement.setString(2, name); // Set the parameter for the employee ID
            preparedStatement.setInt(3, salary); // Set the parameter for the employee ID
            preparedStatement.setString(4, department); // Set the parameter for the employee ID

           int i = preparedStatement.executeUpdate();
           if (i>0) {
            System.out.println("Data Inserted successfully!");
           }else{System.out.println("Failed to insert data!");}
            // Close all resources
            if (connection != null)
                connection.close();
            if (statement != null)
                statement.close();
            if (preparedStatement != null)
                preparedStatement.close();
            sc.close(); // Close scanner
        }catch(InputMismatchException e){
            System.out.println("Error: Please type valid input!");
        }
         catch (Exception e) {
            // Handle any exceptions
            System.out.println("Error: Please check your credentials!");
        }
    }
}
