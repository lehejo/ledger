package homebudget;

public class Transaction {
    private String date;
    private String category;
    private String TransactionType;
    private int amount;

    public Transaction(String date, String category, int amount, String TransactionType) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.TransactionType = TransactionType;
    }
    public String getType(){
        return TransactionType;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%s||%s: %s, %d", TransactionType, date, category, amount);
    }
}
