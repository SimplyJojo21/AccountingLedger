import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    public LocalDateTime date;
    public String vendor;
    public String type; // "DEPOSIT" or "PAYMENT"
    public double amount;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Transaction(LocalDateTime date, String vendor, String type, double amount) {
        this.date = date;
        this.vendor = vendor;
        this.type = type;
        this.amount = amount;
    }

    public String toCSV() {
        return date.format(FORMATTER) + "," + vendor + "," + type + "," + amount;
    }

    public static Transaction fromCSV(String line) {
        String[] parts = line.split(",");
        LocalDateTime date = LocalDateTime.parse(parts[0], FORMATTER);
        String vendor = parts[1];
        String type = parts[2];
        double amount = Double.parseDouble(parts[3]);
        return new Transaction(date, vendor, type, amount);
    }

    public String toString() {
        return date.format(FORMATTER) + " | " + vendor + " | " + type + " | $" + String.format("%.2f", amount);
    }
}
