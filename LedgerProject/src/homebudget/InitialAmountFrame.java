package homebudget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialAmountFrame extends JFrame {
    private JTextField initialAmountField;
    private JButton startButton;

    public InitialAmountFrame() {
        setTitle("초기금액 설정");
        setPreferredSize(new Dimension(300, 150));
        setResizable(false);

        createComponents();

        pack();
        setLocationRelativeTo(null);
    }

    private void createComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("초기금액을 입력하세요 !:");
        panel.add(label, BorderLayout.NORTH);

        initialAmountField = new JTextField();
        panel.add(initialAmountField, BorderLayout.CENTER);

        startButton = new JButton("시작하기");
        panel.add(startButton, BorderLayout.SOUTH);

        add(panel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String initialAmountText = initialAmountField.getText();
                if (!initialAmountText.isEmpty() && initialAmountText.matches("\\d+")) {
                    int initialAmount = Integer.parseInt(initialAmountText);
                    dispose();
                    HomeBudgetFrame homeBudgetFrame = new HomeBudgetFrame(initialAmount);
                    homeBudgetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    homeBudgetFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(InitialAmountFrame.this, "값을 입력해주세요.");
                }
            }
        });
    }
}
