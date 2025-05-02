package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionCsv {

    private static final String FILE_NAME = "transactions.csv";

    public static void saveTransaction(Transaction t) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            out.println(t.toCSV());
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    public static List<Transaction> readTransactions() {
        List<Transaction> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Transaction t = Transaction.fromCSV(line);
                list.add(t);
            }
        } catch (IOException e) {
            // It's okay if the file doesn't exist yet
        }

        Collections.reverse(list); // Show newest first
        return list;
    }
}
