package bankManagement;

import java.util.Scanner;

public class TransactionService {

    private AccountDAO dao;
    private Scanner sc;
    
    public TransactionService(AccountDAO dao, Scanner sc) {
    	this.dao=dao;
    	this.sc=sc;
    }

    // ---------------- BALANCE ENQUIRY ----------------
    public void balanceEnquiry(Account acc) {
        System.out.println("Available Balance: ₹" + acc.getBalanceAmount());
    }

    // ---------------- DEPOSIT ----------------
    public void deposit(Account acc) {

        System.out.print("Enter Deposit Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // buffer clear

        if (amount < 1) {
            System.out.println("Deposit amount must be ₹1 or greater");
            return;
        }


        double oldBalance = acc.getBalanceAmount();
        acc.setBalanceAmount(oldBalance + amount);

        try {
            dao.updateAccount(acc);
            System.out.println("Deposit successful");
        } catch (Exception e) {
            acc.setBalanceAmount(oldBalance); // rollback
            System.out.println("Transaction failed due to system error. Please try again later");
        }
    }

    // ---------------- WITHDRAWAL ----------------
    public void withdrawal(Account acc) {

        System.out.print("Enter Withdrawal Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // buffer clear

        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount");
            return;
        }

        if (amount > acc.getBalanceAmount()) {
            System.out.println("Insufficient balance");
            return;
        }

        double oldBalance = acc.getBalanceAmount();
        acc.setBalanceAmount(oldBalance - amount);

        try {
            dao.updateAccount(acc);
            System.out.println("Withdrawal successful");
        } catch (Exception e) {
            acc.setBalanceAmount(oldBalance); // rollback
            System.out.println("Transaction failed due to system error. Please try again later");
        }
    }
}
