import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    // JDBC URL, username and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/departments";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    // JDBC variables for opening, closing and managing connection
    private Connection connection;
    private Statement statement;

    // Constructor to initialize database connection
    public Main() {
        try {
            // Establishing connection to the database
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            // Creating a statement
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert a Department object into the database
    public void insertDepartment(Department department) {
        try {
            // Creating SQL query to insert data into department table
            String query = String.format("INSERT INTO department (id, name) VALUES (%d, '%s')",
                                         department.getId(), department.getName());
            // Executing the query
            statement.executeUpdate(query);
            System.out.println("Department inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to close the database connection
    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();

        // Creating sample department objects
        Department department1 = new Department(1, "IT");
        Department department2 = new Department(2, "HR");

        // Inserting department objects into the database
        main.insertDepartment(department1);
        main.insertDepartment(department2);

        // Closing the database connection
        main.close();
    }
}

class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}