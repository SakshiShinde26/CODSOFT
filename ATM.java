import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

class ATM extends JFrame {
    private BankAccount bankAccount;

    public ATM(BankAccount account) {
        setTitle("ATM Machine");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        bankAccount = account;

        JLabel infoLabel = new JLabel("Welcome to the ATM");
        infoLabel.setBounds(90, 10, 150, 20);
        add(infoLabel);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(20, 40, 100, 30);
        add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(140, 40, 100, 30);
        add(depositButton);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(80, 90, 120, 30);
        add(checkBalanceButton);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = JOptionPane.showInputDialog("Enter amount to withdraw:");
                if (amount != null && !amount.isEmpty()) {
                    try {
                        double withdrawAmount = Double.parseDouble(amount);
                        if (bankAccount.withdraw(withdrawAmount)) {
                            JOptionPane.showMessageDialog(null, "Successfully withdrawn: $" + withdrawAmount);
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient funds!");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
                    }
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = JOptionPane.showInputDialog("Enter amount to deposit:");
                if (amount != null && !amount.isEmpty()) {
                    try {
                        double depositAmount = Double.parseDouble(amount);
                        bankAccount.deposit(depositAmount);
                        JOptionPane.showMessageDialog(null, "Successfully deposited: $" + depositAmount);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
                    }
                }
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double currentBalance = bankAccount.getBalance();
                JOptionPane.showMessageDialog(null, "Current Balance: $" + currentBalance);
            }
        });
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Initialize account with a balance of $1000
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ATM atm = new ATM(account);
                atm.setVisible(true);
            }
        });
    }
}
