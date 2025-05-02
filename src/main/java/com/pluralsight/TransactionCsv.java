package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionCsv {
    private static final String FILE_NAME = "transactions.csv";

    public static void saveTransaction(Transaction t) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(t.toCSVLine());
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                transactions.add(Transaction.fromCSVLine(line));
            }
        } catch (IOException e) {
            // If file doesn't exist yet, that's fine
        }

        Collections.reverse(transactions); // Show newest first
        return transactions;
    }
}
