package com.pluralsight;

import java.util.Scanner;
import java.util.List;

public class AccountLedgerApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n====== Home Screen ======");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    addTransaction(true);  // Deposit
                    break;
                case "P":
                    addTransaction(false); // Payment
                    break;
                case "L":
                    showLedger();
                    break;
                case "X":
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }

    private static void addTransaction(boolean isDeposit) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine().trim();

        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            System.out.print("Enter amount: ");
            String amtStr = scanner.nextLine().trim();
            try {
                amount = Double.parseDouble(amtStr);
                if (!isDeposit) {
                    amount = -amount;
                }
                validAmount = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }

        Transaction t = new Transaction(description, vendor, amount);
        TransactionCsv.saveTransaction(t);
        System.out.println("Transaction saved!");
    }

    private static void showLedger() {
        boolean inLedger = true;

        while (inLedger) {
            System.out.println("\n====== Ledger ======");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            List<Transaction> transactions = TransactionCsv.loadTransactions();

            switch (choice) {
                case "A":
                    System.out.println("\n--- All Transactions ---");
                    for (Transaction t : transactions) {
                        System.out.println(t);
                    }
                    break;
                case "D":
                    System.out.println("\n--- Deposits ---");
                    for (Transaction t : transactions) {
                        if (t.getAmount() > 0) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "P":
                    System.out.println("\n--- Payments ---");
                    for (Transaction t : transactions) {
                        if (t.getAmount() < 0) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "H":
                    inLedger = false;
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }
}
