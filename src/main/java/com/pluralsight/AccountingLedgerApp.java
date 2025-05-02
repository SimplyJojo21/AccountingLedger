package com.pluralsight;

import java.util.Scanner;

public class AccountingLedgerApp {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean inProcess = true;


        while (inProcess) {

            System.out.println("Welcome to Jojo's Ledge! Nice to have you hanging around");
            System.out.println("Please choose one of the following:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Don't leave me hanging! :(");

            String homeChoice = scanner.nextLine().toUpperCase().trim();

            switch (homeChoice) {
                case "D": addDeposit();
                    break;
                case "P": makePayment();
                    break;
                case "L": Ledger();
                    break;
                case "X":
                    inProcess = false;
                    System.out.println("Goodbye");
                    break;
                default: System.out.println("Invalid, try again please");






            }



        }

scanner.close();
    }


}