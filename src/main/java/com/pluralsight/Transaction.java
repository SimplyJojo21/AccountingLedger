package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final LocalDateTime dateTime;
    private final String description;
    private final String vendor;
    private final double amount;

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Transaction(String description, String vendor, double amount) {
        this.dateTime = LocalDateTime.now();
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public Transaction(LocalDateTime dateTime, String description, String vendor, double amount) {
        this.dateTime = dateTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String toCSVLine() {
        return dateTime.format(dateFormatter) + "|" +
                dateTime.format(timeFormatter) + "|" +
                description + "|" + vendor + "|" + amount;
    }

    @Override
    public String toString() {
        return dateTime.format(dateFormatter) + " " +
                dateTime.format(timeFormatter) + " | " +
                description + " | " + vendor + " | $" +
                String.format("%.2f", amount);
    }

    public static Transaction fromCSVLine(String line) {
        String[] parts = line.split("\\|");
        LocalDateTime dt = LocalDateTime.parse(parts[0] + "T" + parts[1]);
        String description = parts[2];
        String vendor = parts[3];
        double amount = Double.parseDouble(parts[4]);
        return new Transaction(dt, description, vendor, amount);
    }
}
