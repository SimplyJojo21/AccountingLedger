package com.pluralsight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AccountLedgerApp {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Accounting App Home =====");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    addTransaction("DEPOSIT");
                    break;
                case "P":
                    addTransaction("PAYMENT");
                    break;
                case "L":
                    displayLedger();
                    break;
                case "X":
                    running = false;
                    System.out.println("Goodbye 👋");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    public static void addTransaction(String type) {
        System.out.println("\n=== " + (type.equals("DEPOSIT") ? "Add Deposit" : "Make Payment") + " ===");

        String vendor = "";
        while (vendor.isEmpty()) {
            System.out.print("Enter vendor name: ");
            vendor = scanner.nextLine().trim();
            if (vendor.isEmpty()) {
                System.out.println("Vendor name cannot be empty.");
            }
        }

        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            System.out.print("Enter amount: ");
            String input = scanner.nextLine().trim();
            try {
                amount = Double.parseDouble(input);
                if (amount <= 0) {
                    System.out.println("Amount must be greater than 0.");
                } else {
                    validAmount = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        LocalDateTime now = LocalDateTime.now();
        Transaction t = new Transaction(now, vendor, type, amount);
        TransactionCsv.saveTransaction(t);

        System.out.println(type + " saved ✅");
    }

    public static void displayLedger() {
        List<Transaction> transactions = TransactionCsv.readTransactions();

        if (transactions.isEmpty()) {
            System.out.println("\nNo transactions found.");
            return;
        }

        boolean inLedger = true;

        while (inLedger) {
            System.out.println("\n===== Ledger Menu =====");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    System.out.println("\n--- All Transactions ---");
                    for (Transaction t : transactions) {
                        System.out.println(t);
                    }
                    break;
                case "D":
                    System.out.println("\n--- Deposits Only ---");
                    for (Transaction t : transactions) {
                        if (t.type.equals("DEPOSIT")) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "P":
                    System.out.println("\n--- Payments Only ---");
                    for (Transaction t : transactions) {
                        if (t.type.equals("PAYMENT")) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "H":
                    inLedger = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
