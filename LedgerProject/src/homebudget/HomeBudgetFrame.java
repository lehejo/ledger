package homebudget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeBudgetFrame extends JFrame {
    private JLabel mostSpentCategoryLabel;
    private JButton addTransactionButton;
    private JButton addDepositButton;
    private JButton viewTransactionsButton;
    private JLabel totalAmountLabel;

    private List<Transaction> transactions = new ArrayList<>();
    private int totalAmount;

    public HomeBudgetFrame(int initialAmount) {
        setTitle("가 계 부 !!!!!!!!!");
        setPreferredSize(new Dimension(400, 200));
        setResizable(false);

        totalAmount = initialAmount;

        createComponents();

        pack();
        setLocationRelativeTo(null);
    }

    private void createComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        mostSpentCategoryLabel = new JLabel();
        panel.add(mostSpentCategoryLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        addTransactionButton = new JButton("지출");
        buttonPanel.add(addTransactionButton);

        addDepositButton = new JButton("입금");
        buttonPanel.add(addDepositButton);

        viewTransactionsButton = new JButton("내역보기");
        buttonPanel.add(viewTransactionsButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        totalAmountLabel = new JLabel("Total Amount: " + totalAmount);
        panel.add(totalAmountLabel, BorderLayout.SOUTH);

        add(panel);

        addTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTransaction();
            }
        });

        addDepositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDeposit();
            }
        });

        viewTransactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTransactions();
            }
        });
    }

    private void addTransaction() {
        JFrame transactionFrame = new JFrame("Add Transaction");
        transactionFrame.setPreferredSize(new Dimension(300, 200));
        transactionFrame.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        JLabel dateLabel = new JLabel("날짜:");
        JTextField dateField = new JTextField();
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);

        JLabel categoryLabel = new JLabel("카테고리:");
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"식비", "교통비", "통신비", "주거비", "교육비", "여가비", "건강비", "기타"});
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryComboBox);

        JLabel amountLabel = new JLabel("금액:");
        JTextField amountField = new JTextField();
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);

        panel.add(inputPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("저장");
        panel.add(saveButton, BorderLayout.SOUTH);

        transactionFrame.add(panel);

        transactionFrame.pack();
        transactionFrame.setLocationRelativeTo(this);
        transactionFrame.setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String category = (String) categoryComboBox.getSelectedItem();
                String amountText = amountField.getText();

                if (!date.isEmpty() && !amountText.isEmpty() && amountText.matches("\\d+")) {
                    int amount = Integer.parseInt(amountText);
                    Transaction transaction = new Transaction(date, category, amount);
                    transactions.add(transaction);
                    totalAmount -= amount;
                    totalAmountLabel.setText("총금액: " + totalAmount);
                    transactionFrame.dispose();
                    updateMostSpentCategory();
                } else {
                    JOptionPane.showMessageDialog(transactionFrame, "Invalid input. Please enter valid data.");
                }
            }
        });
    }

    private void addDeposit() {
        JFrame transactionFrame = new JFrame("Add Transaction");
        transactionFrame.setPreferredSize(new Dimension(300, 200));
        transactionFrame.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        JLabel dateLabel = new JLabel("날짜:");
        JTextField dateField = new JTextField();
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);

        JLabel categoryLabel = new JLabel("카테고리:");
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"월급", "용돈", "경조사비"});
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryComboBox);

        JLabel amountLabel = new JLabel("금액:");
        JTextField amountField = new JTextField();
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);

        panel.add(inputPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("저장");
        panel.add(saveButton, BorderLayout.SOUTH);

        transactionFrame.add(panel);

        transactionFrame.pack();
        transactionFrame.setLocationRelativeTo(this);
        transactionFrame.setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String category = (String) categoryComboBox.getSelectedItem();
                String amountText = amountField.getText();

                if (!date.isEmpty() && !amountText.isEmpty() && amountText.matches("\\d+")) {
                    int amount = Integer.parseInt(amountText);
                    Transaction transaction = new Transaction(date, category, amount);
                    transactions.add(transaction);
                    totalAmount += amount;
                    totalAmountLabel.setText("총금액: " + totalAmount);
                    transactionFrame.dispose();
                    updateMostSpentCategory();
                } else {
                    JOptionPane.showMessageDialog(transactionFrame, "Invalid input. Please enter valid data.");
                }
            }
        });
    }


    private void viewTransactions() {
        JFrame transactionsFrame = new JFrame("지출내역");
        transactionsFrame.setPreferredSize(new Dimension(400, 300));
        transactionsFrame.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());

        DefaultListModel<Transaction> transactionListModel = new DefaultListModel<>();
        for (Transaction transaction : transactions) {
            transactionListModel.addElement(transaction);
        }
        JList<Transaction> transactionList = new JList<>(transactionListModel);
        JScrollPane scrollPane = new JScrollPane(transactionList);
        panel.add(scrollPane, BorderLayout.CENTER);

        transactionsFrame.add(panel);

        transactionsFrame.pack();
        transactionsFrame.setLocationRelativeTo(this);
        transactionsFrame.setVisible(true);
    }

    private void updateMostSpentCategory() {
        Map<String, Integer> categoryMap = new HashMap<>();
        for (Transaction transaction : transactions) {
            String category = transaction.getCategory();
            int amount = transaction.getAmount();
            categoryMap.put(category, categoryMap.getOrDefault(category, 0) + amount);
        }

        String mostSpentCategory = "";
        int maxAmount = 0;

        for (Map.Entry<String, Integer> entry : categoryMap.entrySet()) {
            String category = entry.getKey();
            int amount = entry.getValue();
            if (amount > maxAmount) {
                maxAmount = amount;
                mostSpentCategory = category;
            }
        }

        mostSpentCategoryLabel.setText("가장 많이 사용된 카테고리: " + mostSpentCategory);
    }
}
