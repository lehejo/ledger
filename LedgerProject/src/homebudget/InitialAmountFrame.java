package homebudget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InitialAmountFrame extends JFrame {
    private JTextField initialAmountField;
    private JButton startButton;

    public InitialAmountFrame() {
        setTitle("초기금액 설정");
        setPreferredSize(new Dimension(400, 250));
        setResizable(false);

        createComponents();

        pack();
        setLocationRelativeTo(null);
    }

    private void createComponents() {
        JPanel panel = new JPanel(new BorderLayout());

<<<<<<< Updated upstream
        JLabel label = new JLabel("현재 보유 중인 자산 입력");
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
=======
        JLabel label = new JLabel("현재 보유 중인 금액을 입력해주세요 !");
>>>>>>> Stashed changes
        panel.add(label, BorderLayout.NORTH);
        label.setHorizontalAlignment(JLabel.CENTER);

        initialAmountField = new JTextField();
        panel.add(initialAmountField, BorderLayout.CENTER);

        startButton = new JButton("시작하기");
<<<<<<< Updated upstream
        startButton.setFont(new Font("SansSerif", Font.BOLD, 14));
=======
>>>>>>> Stashed changes
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