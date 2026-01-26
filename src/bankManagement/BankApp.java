package bankManagement;

import java.util.HashMap;
import java.util.Scanner;

public class BankApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HashMap<String, Account> accountMap = new HashMap<>();

        AccountDAO accountDAO = new AccountDAO();
        DebitCardService debitCardService = new DebitCardService(accountDAO, sc);
        AccountService accountService = new AccountService(accountMap, accountDAO, debitCardService, sc);
        TransactionService transactionService = new TransactionService(accountDAO, sc);
        ProfileService profileService = new ProfileService(accountDAO, sc);
        

        // -------- WELCOME SCREEN --------
        System.out.println("==================================");
        System.out.println("  WELCOME TO NOVA BANKING SYSTEM");
        System.out.println("==================================");
        System.out.println("Your trusted digital banking partner");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // ignore
        }

        while (true) {
        	
        	try {
                accountMap = accountDAO.loadAccounts();
            } catch (Exception e) {
                System.out.println("Failed to load data. Please try again.");
                e.printStackTrace();
                return;
            }

            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Login");
            System.out.println("2. Open Account");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // buffer clear

            switch (choice) {

                case 1: { // LOGIN
                    System.out.print("Enter Username: ");
                    String username = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    Account loggedInAccount = null;

                    for (Account acc : accountMap.values()) {
                        if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                            loggedInAccount = acc;
                            break;
                        }
                    }

                    if (loggedInAccount == null) {
                        System.out.println("Invalid username or password.");
                        break;
                    }

                    System.out.println("Login successful!");
                    System.out.println("Welcome, " + loggedInAccount.getName());

                    // -------- USER DASHBOARD --------
                    boolean loggedIn = true;

                    while (loggedIn) {

                        System.out.println("\n--- USER DASHBOARD ---");
                        System.out.println("1. Balance Enquiry");
                        System.out.println("2. Deposit");
                        System.out.println("3. Withdrawal");
                        System.out.println("4. Profile Settings");
                        System.out.println("5. Debit Card Services");
                        System.out.println("6. Logout");
                        System.out.print("Enter choice: ");

                        int userChoice = sc.nextInt();
                        sc.nextLine(); // buffer clear

                        switch (userChoice) {

                            case 1 -> transactionService.balanceEnquiry(loggedInAccount);
                            case 2 -> transactionService.deposit(loggedInAccount);
                            case 3 -> transactionService.withdrawal(loggedInAccount);

                            case 4 -> profileMenu(profileService, loggedInAccount, sc);

                            case 5 -> debitCardMenu(debitCardService, loggedInAccount, sc);

                            case 6 -> {
                                loggedIn = false;
                                System.out.println("Logged out successfully.");
                            }

                            default -> System.out.println("Invalid option. Try again.");
                        }
                    }

                    break;
                }

                case 2: // OPEN ACCOUNT
                    accountService.openAccount(new Account());
                    break;

                case 3: // EXIT
                    System.out.println("Thank you for banking with us!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    private static void profileMenu(ProfileService profileService, Account acc, Scanner sc) {

		boolean back = false;
		
		while (!back) {
			System.out.println("\n--- PROFILE SETTINGS ---");
			System.out.println("1. Name Correction");
			System.out.println("2. Address Correction");
			System.out.println("3. Email Update");
			System.out.println("4. Mobile Number Update");
			System.out.println("5. Change Password");
			System.out.println("6. Nominee Update");
			System.out.println("7. Back");
			System.out.print("Choose: ");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch (choice) {
				case 1 -> profileService.nameCorrection(acc);
				case 2 -> profileService.addressCorrection(acc);
				case 3 -> profileService.emailUpdate(acc);
				case 4 -> profileService.mobileNumberUpdate(acc);
				case 5 -> profileService.changePassword(acc);
				case 6 -> profileService.nomineeAdding(acc);
				case 7 -> back = true;
				default -> System.out.println("Invalid option.");
			}
		}
    }
    
    private static void debitCardMenu(DebitCardService debitCardService, Account acc,Scanner sc) {

		boolean back = false;
		
		while (!back) {
			System.out.println("\n--- DEBIT CARD SERVICES ---");
			System.out.println("1. Apply Debit Card");
			System.out.println("2. Block Debit Card");
			System.out.println("3. Unblock Debit Card");
			System.out.println("4. Back");
			System.out.print("Choose: ");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch (choice) {
				case 1 -> debitCardService.issueInitialCard(acc);
				case 2 -> debitCardService.blockDebitCard(acc);
				case 3 -> debitCardService.unblockDebitCard(acc);
				case 4 -> back = true;
				default -> System.out.println("Invalid option.");
			}
		}
	}

}
