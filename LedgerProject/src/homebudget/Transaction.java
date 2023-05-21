package homebudget;

public class Transaction {
    private String date;
    private String category;
    private int amount;

    public Transaction(String date, String category, int amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%s: %s, %d", date, category, amount);
    }
}
