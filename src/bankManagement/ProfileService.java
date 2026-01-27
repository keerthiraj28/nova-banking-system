package bankManagement;

import java.util.Scanner;

public class ProfileService {

    private AccountDAO dao;
    private Scanner sc;
    
    public ProfileService(AccountDAO dao, Scanner sc) {
        this.dao = dao;
        this.sc=sc;
    }

    // NAME CORRECTION
    public void nameCorrection(Account acc) {
    	
    	String oldName = acc.getName();
    	
        System.out.print("Enter New Name: ");
        String name = sc.nextLine();        
        acc.setName(name);
        
        try {
			dao.updateAccount(acc);
			System.out.println("Name updated successfully");
		} catch (Exception e) {
			acc.setName(oldName); // rollback
			e.printStackTrace();
			System.out.println("System error occurred. Please try again later");

		}

    }

    // ADDRESS CORRECTION
    public void addressCorrection(Account acc) {

    	String oldAddress = acc.getAddress();
    	
        System.out.print("Enter New Address: ");
        String address = sc.nextLine();        
        acc.setAddress(address);

        try {
			dao.updateAccount(acc);
			System.out.println("Address updated successfully");
		} catch (Exception e) {
			acc.setAddress(oldAddress); // rollback
			e.printStackTrace();
			System.out.println("System error occurred. Please try again later");

		}
        
        
    }

    // EMAIL UPDATE
    public void emailUpdate(Account acc) {
    	
    	String oldEmail = acc.getEmail();

        while (true) {
            System.out.print("Enter New Email: ");
            String email = sc.nextLine();

            if (email.matches("^[A-Za-z0-9+_.-]+@(gmail|yahoo|outlook)\\.com$")) {
                acc.setEmail(email);
                try {
					dao.updateAccount(acc);
					System.out.println("Email updated successfully");
				} catch (Exception e) {
					acc.setEmail(oldEmail); // rollback
					e.printStackTrace();
					System.out.println("System error occurred. Please try again later");

				}
                break;
            } else {
                System.out.println("Invalid email format. Try again");
            }
        }
    }

    // MOBILE NUMBER UPDATE
    public void mobileNumberUpdate(Account acc) {
    	
    	String oldMobile = acc.getMobileNumber();

        while (true) {
            System.out.print("Enter New Mobile Number: ");
            String mobile = sc.nextLine();

            if (mobile.matches("\\d{10}")) {
                acc.setMobileNumber(mobile);
                try {
					dao.updateAccount(acc);
					System.out.println("Mobile number updated successfully");
				} catch (Exception e) {
					acc.setMobileNumber(oldMobile); // rollback
					e.printStackTrace();
					System.out.println("System error occurred. Please try again later");

				}
                
                break;
            } else {
                System.out.println("Invalid mobile number. Try again");
            }
        }
    }

    // PASSWORD CHANGE
    public void changePassword(Account acc) {
    	
    	String newPass = "";
    	String oldPass = "";
	
   		System.out.print("Enter Old Password: ");
        oldPass = sc.nextLine();
        
        if (!PasswordUtil.checkPassword(oldPass, acc.getPassword())) {
            System.out.println("Incorrect password.");
            return;
        }
        
        
    	while (true) {
    		
    		System.out.print("Enter New Password: ");
            newPass = sc.nextLine();
            
            if (!PasswordUtil.isValidPassword(newPass)) {
                System.out.println(
                    "Password must be at least 8 characters long and include:\n" +
                    "- Uppercase letter\n" +
                    "- Lowercase letter\n" +
                    "- Number\n" +
                    "- Special character"
                );
                return;
            }

            System.out.print("Confirm New Password: ");
            String confirmPass = sc.nextLine();

            if (!newPass.equals(confirmPass)) {
                System.out.println("Passwords do not match");
                return;
            }
            break;
    		
    	}
        
    	acc.setPassword(PasswordUtil.hashPassword(newPass));
        
        try {
			dao.updateAccount(acc);
			System.out.println("Password changed successfully");
		} catch (Exception e) {
			acc.setPassword(oldPass); // rollback
			e.printStackTrace();
			System.out.println("System error occurred. Please try again later");

		}
        
    }
    
    // NOMINEE ADDING
    public void nomineeAdding(Account acc) {
    	
    	String oldNomineeName = acc.getNomineeName();
    	Integer oldNomineeAge = acc.getNomineeAge();
    	String oldNomineeRelation = acc.getNomineeRelationship();
    	String oldNomineeMobile = acc.getNomineeMobileNumber();
    	String oldNomineePan = acc.getNomineePanNumber();
    	
    	while (true) {
    		System.out.println("Enter Nominee Name: ");
        	acc.setNomineeName(sc.nextLine());
        	
        	System.out.println("Enter Nominee Age: ");
        	int age=sc.nextInt();
        	
        	if(age>=18) {
        		acc.setNomineeAge(age);
        		break;
        	}
        	System.out.println("Nominee Age must be 18 or older. Try again");
    	}
    	
    	sc.nextLine();
    	
    	System.out.println("Nominee Relationship: ");
    	acc.setNomineeRelationship(sc.nextLine());
    	
    	while (true) {
            System.out.print("Enter Nominee Mobile Number: ");
            String mobile = sc.nextLine();

            if (mobile.matches("\\d{10}")) {
                acc.setNomineeMobileNumber(mobile);
                break;
            } else {
                System.out.println("Invalid mobile number. Try again");
            }
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
    	
    	try {
			dao.updateAccount(acc);
			System.out.println("Nominee Details has updated");
		} catch (Exception e) {
			acc.setNomineeName(oldNomineeName);
			acc.setNomineeAge(oldNomineeAge);
			acc.setNomineeRelationship(oldNomineeRelation); // rollback
			acc.setNomineeMobileNumber(oldNomineeMobile);
			acc.setNomineePanNumber(oldNomineePan);
			e.printStackTrace();
			System.out.println("System error occurred. Please try again later");

		}
    	
    	
    	
    }
}
