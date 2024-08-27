import Exceptions.InvalidInputException;
import Exceptions.OperationFailedException;
import Exceptions.UnauthorizedAccessException;
import Exceptions.DatabaseConnectionException;
import Exceptions.ResourceNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        // First way: Using try-catch block
        try {
            app.doSomethingRisky("fail");
        } catch (InvalidInputException e) {
            System.err.println("Caught exception: " + e.getMessage());
        }

        // Second way: Declaring with throws
        try {
            app.performOperation();
        } catch (OperationFailedException e) {
            System.err.println("Caught exception: " + e.getMessage());
        }

        // Simulate Unauthorized Access
        try {
            app.accessResource(false);
        } catch (UnauthorizedAccessException e) {
            System.err.println("Caught exception: " + e.getMessage());
        }

        // Simulate Database Connection
        try {
            app.connectToDatabase(false);
        } catch (DatabaseConnectionException e) {
            System.err.println("Caught exception: " + e.getMessage());
        }

        // Try-catch with resources
        try {
            app.readFile("C:\\Users\\aweso\\laba\\Exceptions\\src\\main\\java\\note.txt");
        } catch (ResourceNotFoundException e) {
            System.err.println("Caught exception: " + e.getMessage());
        }
    }

    public void doSomethingRisky(String input) throws InvalidInputException {
        if ("fail".equals(input)) {
            throw new InvalidInputException("Input caused a failure!");
        }
        System.out.println("Input was fine.");
    }

    public void performOperation() throws OperationFailedException {
        throw new OperationFailedException("The operation failed!");
    }

    public void accessResource(boolean hasAccess) throws UnauthorizedAccessException {
        if (!hasAccess) {
            throw new UnauthorizedAccessException("User does not have the necessary access rights!");
        }
        System.out.println("Resource accessed successfully.");
    }

    public void connectToDatabase(boolean isConnected) throws DatabaseConnectionException {
        if (!isConnected) {
            throw new DatabaseConnectionException("Failed to connect to the database!");
        }
        System.out.println("Connected to the database successfully.");
    }

    public void readFile(String filePath) throws ResourceNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new ResourceNotFoundException("Error reading file: " + e.getMessage());
        }
    }
}
