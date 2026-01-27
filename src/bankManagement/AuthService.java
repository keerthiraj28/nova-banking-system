package bankManagement;

import java.util.HashMap;
import java.util.Scanner;

public class AuthService {

    private HashMap<String, Account> accountMap;
    private Scanner sc;
    private static final int MAX_ATTEMPTS = 3;

    public AuthService(HashMap<String, Account> accountMap, Scanner sc) {
        this.accountMap = accountMap;
        this.sc = sc;
    }

    public Account login() {

        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {

            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            for (Account acc : accountMap.values()) {
                if (acc.getUsername().equals(username) && PasswordUtil.checkPassword(password, acc.getPassword())) {

                    System.out.println("Login successful!");
                    return acc;
                }
            }

            attempts++;
            System.out.println("Invalid username or password. Attempts left: " + (MAX_ATTEMPTS - attempts));
        }

        System.out.println("Too many failed attempts. Access denied");
        return null;
    }
}
