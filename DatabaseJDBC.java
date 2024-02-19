import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DatabaseJDBC {

    public static void main(String[] args) {
        // JDBC connection parameters
        String url = "jdbc:mysql://localhost:3306/database_name";
        String username = "username";
        String password = "password";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Scanner sc = new Scanner(System.in);

            // Menu for CRUD operations
            while (true) {
                System.out.println("----JDBC: Data Validation----");
                System.out.println("1. Insert new record");
                System.out.println("2. Update existing record");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        insertRecord(con, sc);
                        break;
                    case 2:
                        updateRecord(con, sc);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        con.close();
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (InputMismatchException e1){
            System.out.println("Error: Input mismatch!");
        }
    }

    // Method to insert a new record
    private static void insertRecord(Connection con, Scanner sc) {
        try {
            System.out.println("Inserting new record...");
            System.out.print("Enter ID: ");
            String id = sc.nextLine();
            System.out.print("Enter name: ");
            String name = sc.nextLine();

            // Data validation
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                return;
            }

            // Prepare SQL statement
            String sql = "INSERT INTO mytable (id, name) VALUES (?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, name);

            // Execute the statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New record inserted successfully.");
            } else {
                System.out.println("Failed to insert new record.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (InputMismatchException e1){
            System.out.println("Error: Input mismatch!");
        }
    }

    // Method to update an existing record
    private static void updateRecord(Connection con, Scanner sc) {
        try {
            System.out.println("Updating existing record...");
            System.out.print("Enter ID of the record to update: ");
            int id = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.print("Enter new name: ");
            String name = sc.nextLine();

            // Data validation
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                return;
            }

            // Prepare SQL statement
            String sql = "UPDATE mytable SET name=? WHERE id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, id);

            // Execute the statement
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Record updated successfully.");
            } else {
                System.out.println("Failed to update record. Record with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (InputMismatchException e1){
            System.out.println("Error: Input mismatch!");
        }
    }
}
