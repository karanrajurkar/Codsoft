import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false; // Insufficient funds
        }
        balance -= amount;
        return true; // Withdrawal successful
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public double checkBalance() {
        return bankAccount.getBalance();
    }

    public boolean deposit(double amount) {
        if (amount < 0) {
            return false; // Invalid deposit amount
        }
        bankAccount.deposit(amount);
        return true; // Deposit successful
    }

    public boolean withdraw(double amount) {
        return bankAccount.withdraw(amount);
    }
}

public class ATMGUI extends JFrame {
    private ATM atm;
    private JTextField amountField;
    private JTextArea resultTextArea;

    public ATMGUI(ATM atm) {
        this.atm = atm;
        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridLayout(4, 2));

        amountField = new JTextField();
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton checkBalanceButton = new JButton("Check Balance");
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWithdraw();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckBalance();
            }
        });

        mainPanel.add(new JLabel("Enter Amount: "));
        mainPanel.add(amountField);
        mainPanel.add(depositButton);
        mainPanel.add(withdrawButton);
        mainPanel.add(checkBalanceButton);
        mainPanel.add(new JLabel()); // Empty label for spacing
        mainPanel.add(new JLabel()); // Empty label for spacing
        mainPanel.add(resultTextArea);

        add(mainPanel);
    }

    private void handleDeposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (atm.deposit(amount)) {
                resultTextArea.setText("Deposit successful. New balance: " + atm.checkBalance());
            } else {
                resultTextArea.setText("Invalid deposit amount.");
            }
        } catch (NumberFormatException ex) {
            resultTextArea.setText("Invalid input. Please enter a valid amount.");
        }
    }

    private void handleWithdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (atm.withdraw(amount)) {
                resultTextArea.setText("Withdrawal successful. New balance: " + atm.checkBalance());
            } else {
                resultTextArea.setText("Insufficient funds or invalid withdrawal amount.");
            }
        } catch (NumberFormatException ex) {
            resultTextArea.setText("Invalid input. Please enter a valid amount.");
        }
    }

    private void handleCheckBalance() {
        resultTextArea.setText("Current Balance: " + atm.checkBalance());
    }

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1000.0); // Initial balance for the bank account
        ATM atm = new ATM(bankAccount);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMGUI(atm).setVisible(true);
            }
        });
    }
}
