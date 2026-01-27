package bankManagement;

import java.util.Random;
import java.util.Scanner;

public class DebitCardService {

    private AccountDAO dao;
    private Scanner sc;

    private String[] cardTypes = {"VISA", "RUPAY", "MASTERCARD", "MAESTRO"};
    private String[] cardFeatures = {"CLASSIC", "GOLD", "PLATINUM"};

    public DebitCardService(AccountDAO dao, Scanner sc) {
        this.dao = dao;
        this.sc=sc;
    }

    // ---------------- APPLY FOR DEBIT CARD ----------------
    public void issueInitialCard(Account acc) {

        // no status check needed during opening
        int choice;

        while (true) {
            System.out.println("Select Debit Card Type:");
            for (int i = 0; i < cardTypes.length; i++) {
                System.out.println((i + 1) + ". " + cardTypes[i]);
            }

            choice = sc.nextInt();
            if (choice >= 1 && choice <= cardTypes.length) {
                acc.setCardType(cardTypes[choice - 1]);
                break;
            }
            System.out.println("Invalid choice");
        }

        while (true) {
            System.out.println("Select Card Feature:");
            for (int i = 0; i < cardFeatures.length; i++) {
                System.out.println((i + 1) + ". " + cardFeatures[i]);
            }

            choice = sc.nextInt();
            if (choice >= 1 && choice <= cardFeatures.length) {
                acc.setCardFeature(cardFeatures[choice - 1]);
                break;
            }
            System.out.println("Invalid choice");
        }

        sc.nextLine(); // buffer clear

        acc.setDebitCardNumber(generateDebitCardNumber());
        acc.setDebitCardStatus(DebitCardStatus.ACTIVE);        
        
    }

    
    public void applyDebitCard(Account acc) {

        if (acc.getDebitCardStatus() != DebitCardStatus.NONE) {
            if (acc.getDebitCardStatus() == DebitCardStatus.ACTIVE) {
                System.out.println("Debit card already exists");
            } else {
                System.out.println("Debit card is blocked. Please unblock it");
            }
            return;
        }

        issueInitialCard(acc);

        try {
            dao.updateAccount(acc);
            System.out.println("Debit card applied successfully");
        } catch (Exception e) {
            acc.setDebitCardNumber(null);
            acc.setDebitCardStatus(DebitCardStatus.NONE);
            acc.setCardType(null);
            acc.setCardFeature(null);
            System.out.println("Failed to apply debit card. Please try again later");
        }
    }


    // ---------------- BLOCK DEBIT CARD ----------------
    public void blockDebitCard(Account acc) {
    	
    	if (acc.getDebitCardStatus() != DebitCardStatus.ACTIVE) {
    	    System.out.println("Debit card is not active");
    	    return;
    	}

        System.out.print("Enter Debit Card Number: ");
        String cardNo = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine(); // hash comparison later

        if (!acc.getDebitCardNumber().equals(cardNo) || !PasswordUtil.checkPassword(password, acc.getPassword())) {
            System.out.println("Invalid card number or password");
            return;
        }

        System.out.println(
            "WARNING: Blocking your debit card will disable all transactions\n" +
            "Do you want to continue? (y/n)"
        );

        if (!sc.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Debit card blocking cancelled");
            return;
        }

        acc.setDebitCardStatus(DebitCardStatus.BLOCKED);

        try {
            dao.updateAccount(acc);
            System.out.println("Debit card blocked successfully");
        } catch (Exception e) {
            acc.setDebitCardStatus(DebitCardStatus.ACTIVE);
            System.out.println("Failed to block debit card. Please try again later");
        }
    }

    // ---------------- UNBLOCK DEBIT CARD ----------------
    public void unblockDebitCard(Account acc) {
    	
    	if (acc.getDebitCardStatus() != DebitCardStatus.BLOCKED) {
    	    System.out.println("Debit card is not blocked");
    	    return;
    	}


        System.out.print("Enter Debit Card Number: ");
        String cardNo = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine(); // hash comparison later

        if (!acc.getDebitCardNumber().equals(cardNo) || !PasswordUtil.checkPassword(password, acc.getPassword())) {
            System.out.println("Invalid card number or password");
            return;
        }

        acc.setDebitCardStatus(DebitCardStatus.ACTIVE);

        try {
            dao.updateAccount(acc);
            System.out.println("Debit card unblocked successfully");
        } catch (Exception e) {
            acc.setDebitCardStatus(DebitCardStatus.BLOCKED);
            System.out.println("Failed to unblock debit card. Please try again later");
        }
    }

    // ---------------- DEBIT CARD NUMBER GENERATOR ----------------
    private String generateDebitCardNumber() {

        Random rand = new Random();
        StringBuilder cardNo = new StringBuilder();

        // First digit should not be 0
        cardNo.append(rand.nextInt(9) + 1);

        for (int i = 0; i < 15; i++) {
            cardNo.append(rand.nextInt(10));
        }

        return cardNo.toString();
    }
    
    

}
