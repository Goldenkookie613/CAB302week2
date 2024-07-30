import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Static list of users, acting as a database
    private static ArrayList<User> users = new ArrayList<>();

    // Mock authentication service that always returns the first user when logging in and does nothing when signing up
    private static IAuthenticationService authService = new AuthenticationService(users) {
        @Override
        public User signUp(String username, String password) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return null; // Username already taken
                }
            }
            User newUser = new User(username, password);
            users.add(newUser);
            return newUser;
        }

        @Override
        public User logIn(String username, String password) {
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return user; // Successful login
                }
            }
            return null; // Login failed
        }
    };

    private static boolean isRunning = true;

    /**
     * The entry point of the application.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // Pre-populate the users list with a test user
        users.add(new User("test", "test"));

        // Main loop to show menu and handle choices
        while (isRunning) {
            showMenu();
        }
    }

    /**
     * Displays the main menu to the user.
     */
    public static void showMenu() {
        System.out.println("Welcome to the To-Do List Application!");
        System.out.println("1. Log in");
        System.out.println("2. Sign up");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        handleMenu(choice);
    }

    /**
     * Handles the user's choice, mapping the menu options to the corresponding methods.
     * @param choice The user's choice.
     */
    public static void handleMenu(int choice) {
        switch (choice) {
            case 1:
                onLogIn();
                break;
            case 2:
                onSignUp();
                break;
            case 3:
                onExit();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    /**
     * Handles the log-in process and initiates the to-do list functionality for the logged-in user.
     */
    public static void onLogIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = authService.logIn(username, password);
        if (user != null) {
            System.out.println("Welcome, " + user.getUsername() + "!");
            // Create an instance of ToDoList and call the run method
            ToDoList toDoList = new ToDoList(user);
            toDoList.run();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    /**
     * Handles the sign-up process and provides feedback to the user.
     */
    public static void onSignUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = authService.signUp(username, password);
        if (user != null) {
            System.out.println("User " + username + " has been created successfully!");
        } else {
            System.out.println("The username is already taken!");
        }
    }

    /**
     * Exits the application by setting the `isRunning` flag to false.
     */
    public static void onExit() {
        isRunning = false;
        System.out.println("Exiting the application. Goodbye!");
    }
}