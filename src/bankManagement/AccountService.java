package bankManagement;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;

public class AccountService {
	
	private HashMap<String, Account> map;
	private DebitCardService debitCardService;
	private AccountDAO dao;
	private Scanner sc;

    public AccountService(HashMap<String, Account> map, AccountDAO dao, DebitCardService debitCardService, Scanner sc) {
        this.map = map;
        this.dao = dao;
        this.debitCardService=debitCardService;
        this.sc=sc;
    }	

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
 
	{
	    df.setLenient(false);
	}

    private String[] accountTypes = {"Savings", "Current", "Fixed", "Salary"};
    
    
    // Account Opening
    public void openAccount(Account acc) {

        System.out.print("Enter Name: ");
        acc.setName(sc.nextLine());
        
        // DOB validation loop
        while (true) {
            try {
                System.out.println("DOB (dd-MM-yyyy): ");
                acc.setDob(df.parse(sc.nextLine()));
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Try again");
            }
        }
        
        int age;
        while (true) {
            System.out.print("Enter Age: ");
            age = sc.nextInt();
            if (age > 0 && age <= 120) break;
            System.out.println("Invalid age. Try again");
        }
        acc.setAge(age);
        
        sc.nextLine(); // buffer clear

        // Age validation        

        if (acc.isMinor()) {

            System.out.println("Account holder is a minor. Guardian details are mandatory");

            System.out.print("Enter Guardian Name: ");
            acc.setGuardianName(sc.nextLine());
            
            while (true) {
                System.out.print("Enter Guardian Mobile Number: ");
                String gMobile = sc.nextLine();
                if (gMobile.matches("\\d{10}")) {
                    acc.setGuardianMobileNumber(gMobile);
                    break;
                }
                System.out.println("Invalid mobile number. Try again");
            }

            while (true) {
                System.out.print("Enter Guardian Email ID: ");
                String gEmail = sc.nextLine();
                if (gEmail.matches("^[A-Za-z0-9+_.-]+@(gmail|yahoo|outlook)\\.com$")) {
                    acc.setGuardianEmail(gEmail);
                    break;
                }
                System.out.println("Invalid email format. Try again");
            }

            acc.setMobileNumber(null);
            acc.setEmail(null);
            acc.setPanNumber(null);

        } else {
            // Clear guardian details for non-minor
            acc.setGuardianName(null);
            acc.setGuardianMobileNumber(null);
            acc.setGuardianEmail(null);
            acc.setGuardianPanNumber(null);
        }


        // Mobile number and Email validation
        if (!acc.isMinor()) {

            while (true) {
                System.out.print("Enter Mobile Number: ");
                String mobile = sc.nextLine();
                if (mobile.matches("\\d{10}")) {
                    acc.setMobileNumber(mobile);
                    break;
                }
                System.out.println("Invalid mobile number. Try again");
            }

            while (true) {
                System.out.print("Enter Email ID: ");
                String email = sc.nextLine();

                if (email.matches("^[A-Za-z0-9+_.-]+@(gmail|yahoo|outlook)\\.com$")) {
                    acc.setEmail(email);
                    break;
                }
                System.out.println("Invalid email. Try again");
            }
        }


        // Aadhaar validation
        while (true) {
            System.out.print("Enter Aadhaar Number: ");
            String aadhaar = sc.nextLine();
            if (aadhaar.matches("\\d{12}")) {
                acc.setAadhaarNumber(aadhaar);
                break;
            }
            System.out.println("Invalid Aadhaar number. Try again");
        }

        
        // PAN validation
        if (!acc.isMinor()) {

            System.out.print("Do you have a PAN card? (y/n): ");
            String hasPan = sc.nextLine();

            if (hasPan.equalsIgnoreCase("y")) {
                while (true) {
                    System.out.print("Enter PAN Number: ");
                    String pan = sc.nextLine();
                    if (pan.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {
                        acc.setPanNumber(pan);
                        acc.setGuardianPanNumber(null);
                        break;
                    }
                    System.out.println("Invalid PAN format");
                }
            } else {
                acc.setPanNumber(null);
            }

        } else {
            // Minor → Guardian PAN
            while (true) {
                System.out.print("Enter Guardian PAN Number: ");
                String gPan = sc.nextLine();
                if (gPan.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {
                    acc.setGuardianPanNumber(gPan);
                    break;
                }
                System.out.println("Invalid PAN format");
            }
        }


        System.out.print("Enter Address: ");
        acc.setAddress(sc.nextLine());

        // Mandatory deposit confirmation
        System.out.println(
            "To open a bank account, a mandatory minimum deposit of Rs.1000 is required\n" +
            "This amount will be credited as your opening balance\n" +
            "Do you wish to proceed? (y/n)"
        );

        if (!sc.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Account creation cancelled");
            return;
        }

        acc.setBalanceAmount(1000);

        // Account type selection
        System.out.println("Select Account Type: ");
        for (int i = 0; i < accountTypes.length; i++) {
            System.out.println((i + 1) + ". " + accountTypes[i]);
        }

        int choice = sc.nextInt();
        sc.nextLine(); // buffer clear
        
        if (choice >= 1 && choice <= accountTypes.length) {
            acc.setAccountType(accountTypes[choice - 1]);
        } else {
            System.out.println("Invalid account type");
            return;
        }

        
        // Debit card option
        System.out.println("Do you want to apply for a Debit Card? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            debitCardService.issueInitialCard(acc);
            System.out.println("Debit Card applied successfully");
        } else {
            acc.setDebitCardStatus(DebitCardStatus.NONE);    
        	acc.setDebitCardNumber(null);
        	acc.setCardType(null);
        	acc.setCardFeature(null);
        }

        
        // Nominee information
        System.out.println("Do you want to register a Nominee? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
        	
        	System.out.println("Enter Nominee Name: ");
        	acc.setNomineeName(sc.nextLine());
        	
        	System.out.println("Enter Nominee Age: ");
        	acc.setNomineeAge(sc.nextInt());
        	
        	sc.nextLine(); // buffer clear
        	
        	System.out.println("Enter Nominee Relationship: ");
        	acc.setNomineeRelationship(sc.nextLine());
        	
        	while (true) {
                System.out.print("Enter Nominee Mobile Number: ");
                String mobile = sc.nextLine();
                if (mobile.matches("\\d{10}")) {
                    acc.setNomineeMobileNumber(mobile);
                    break;
                }
                System.out.println("Invalid mobile number. Try again");
            }
        	
        	while (true) {
                System.out.print("Enter Nominee PAN Number: ");
                String pan = sc.nextLine();
                if (pan.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {
                    acc.setNomineePanNumber(pan);
                    break;
                }
                System.out.println("Invalid PAN format");
            }
        }
        else {
        	acc.setNomineeName(null);
        	acc.setNomineeAge(null);
        	acc.setNomineeRelationship(null);
        	acc.setNomineeMobileNumber(null);
        	acc.setNomineePanNumber(null);
        }
        

        // Username & Password
        System.out.print("Create Username: ");
        acc.setUsername(sc.nextLine());

        System.out.print("Create Password: ");
        acc.setPassword(sc.nextLine());

        try {
			dao.saveAccount(acc);
			map.put(acc.getAccountNumber(), acc);
			System.out.println("Account created successfully");
		} catch (Exception e) {
			System.out.println("Account opening failed due to ytem error. Please try again later");
			e.printStackTrace();
		}

    }
    
    
    // Account closing
    
    public void accountClosing() {

        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        // Check whether account exists
        if (!map.containsKey(accNo)) {
            System.out.println("Account not found");
            return;
        }

        Account storedAccount = map.get(accNo);

        // Password validation
        if (!storedAccount.getPassword().equals(password)) {
            System.out.println("Invalid password");
            return;
        }

        // Warning + confirmation
        System.out.println(
            "WARNING: Closing your account is permanent\n" +
            "All account details and balance will be deleted\n" +
            "This action cannot be undone\n" +
            "Do you still want to close your account? (y/n)"
        );

        String confirmation = sc.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            try {
            	map.remove(accNo);
				dao.deleteAccount(accNo);
				System.out.println("Account closed successfully");
			} catch (Exception e) {
				System.out.println("Account closing failed due to system error. Please try again later");
				e.printStackTrace();
			}
            
        } else {
            System.out.println("Account closing cancelled");
        }
    }

    
}
