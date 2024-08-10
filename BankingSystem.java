import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(BankAccount otherAccount, double amount) {
        if (withdraw(amount)) {
            otherAccount.deposit(amount);
            return true;
        }
        return false;
    }
}

public class BankingSystem extends Frame implements ActionListener {
    private Map<String, BankAccount> accounts = new HashMap<>();
    private Label messageLabel;

    public BankingSystem() {
        setLayout(new FlowLayout());

        Button createButton = new Button("Create Account");
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button checkBalanceButton = new Button("Check Balance");
        Button transferButton = new Button("Transfer");
        Button exitButton = new Button("Exit");

        messageLabel = new Label("Welcome to the Banking System");

        add(createButton);
        add(depositButton);
        add(withdrawButton);
        add(checkBalanceButton);
        add(transferButton);
        add(exitButton);
        add(messageLabel);

        createButton.addActionListener(this);
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        checkBalanceButton.addActionListener(this);
        transferButton.addActionListener(this);
        exitButton.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setTitle("Banking System");
        setSize(600, 400);
        setVisible(true);
    }

    private void showCreateAccountFrame() {
        Frame createFrame = new Frame("Create Account");
        createFrame.setSize(300, 200);
        createFrame.setLayout(new FlowLayout());

        Label accountNumberLabel = new Label("Account Number:");
        TextField accountNumberField = new TextField(20);
        Label accountHolderLabel = new Label("Account Holder:");
        TextField accountHolderField = new TextField(20);
        Button createAccountButton = new Button("Create");

        createFrame.add(accountNumberLabel);
        createFrame.add(accountNumberField);
        createFrame.add(accountHolderLabel);
        createFrame.add(accountHolderField);
        createFrame.add(createAccountButton);

        createAccountButton.addActionListener(e -> {
            String accountNumber = accountNumberField.getText();
            String accountHolder = accountHolderField.getText();
            accounts.put(accountNumber, new BankAccount(accountNumber, accountHolder));
            messageLabel.setText("Account created successfully.");
            createFrame.dispose();
        });

        createFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                createFrame.dispose();
            }
        });

        createFrame.setVisible(true);
    }

    private void showDepositFrame() {
        Frame depositFrame = new Frame("Deposit");
        depositFrame.setSize(300, 200);
        depositFrame.setLayout(new FlowLayout());

        Label accountNumberLabel = new Label("Account Number:");
        TextField accountNumberField = new TextField(20);
        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField(20);
        Button depositButton = new Button("Deposit");

        depositFrame.add(accountNumberLabel);
        depositFrame.add(accountNumberField);
        depositFrame.add(amountLabel);
        depositFrame.add(amountField);
        depositFrame.add(depositButton);

        depositButton.addActionListener(e -> {
            String accountNumber = accountNumberField.getText();
            double amount = Double.parseDouble(amountField.getText());
            BankAccount account = accounts.get(accountNumber);
            if (account != null) {
                account.deposit(amount);
                messageLabel.setText("Amount deposited successfully.");
            } else {
                messageLabel.setText("Account not found.");
            }
            depositFrame.dispose();
        });

        depositFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                depositFrame.dispose();
            }
        });

        depositFrame.setVisible(true);
    }

    private void showWithdrawFrame() {
        Frame withdrawFrame = new Frame("Withdraw");
        withdrawFrame.setSize(300, 200);
        withdrawFrame.setLayout(new FlowLayout());

        Label accountNumberLabel = new Label("Account Number:");
        TextField accountNumberField = new TextField(20);
        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField(20);
        Button withdrawButton = new Button("Withdraw");

        withdrawFrame.add(accountNumberLabel);
        withdrawFrame.add(accountNumberField);
        withdrawFrame.add(amountLabel);
        withdrawFrame.add(amountField);
        withdrawFrame.add(withdrawButton);

        withdrawButton.addActionListener(e -> {
            String accountNumber = accountNumberField.getText();
            double amount = Double.parseDouble(amountField.getText());
            BankAccount account = accounts.get(accountNumber);
            if (account != null && account.withdraw(amount)) {
                messageLabel.setText("Amount withdrawn successfully.");
            } else {
                messageLabel.setText("Insufficient balance or account not found.");
            }
            withdrawFrame.dispose();
        });

        withdrawFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                withdrawFrame.dispose();
            }
        });

        withdrawFrame.setVisible(true);
    }

    private void showCheckBalanceFrame() {
        Frame balanceFrame = new Frame("Check Balance");
        balanceFrame.setSize(300, 200);
        balanceFrame.setLayout(new FlowLayout());

        Label accountNumberLabel = new Label("Account Number:");
        TextField accountNumberField = new TextField(20);
        Button checkBalanceButton = new Button("Check Balance");

        balanceFrame.add(accountNumberLabel);
        balanceFrame.add(accountNumberField);
        balanceFrame.add(checkBalanceButton);

        checkBalanceButton.addActionListener(e -> {
            String accountNumber = accountNumberField.getText();
            BankAccount account = accounts.get(accountNumber);
            if (account != null) {
                messageLabel.setText("Current Balance: " + account.getBalance());
            } else {
                messageLabel.setText("Account not found.");
            }
            balanceFrame.dispose();
        });

        balanceFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                balanceFrame.dispose();
            }
        });

        balanceFrame.setVisible(true);
    }

    private void showTransferFrame() {
        Frame transferFrame = new Frame("Transfer");
        transferFrame.setSize(300, 200);
        transferFrame.setLayout(new FlowLayout());

        Label fromAccountLabel = new Label("From Account:");
        TextField fromAccountField = new TextField(20);
        Label toAccountLabel = new Label("To Account:");
        TextField toAccountField = new TextField(20);
        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField(20);
        Button transferButton = new Button("Transfer");

        transferFrame.add(fromAccountLabel);
        transferFrame.add(fromAccountField);
        transferFrame.add(toAccountLabel);
        transferFrame.add(toAccountField);
        transferFrame.add(amountLabel);
        transferFrame.add(amountField);
        transferFrame.add(transferButton);

        transferButton.addActionListener(e -> {
            String fromAccountNumber = fromAccountField.getText();
            String toAccountNumber = toAccountField.getText();
            double amount = Double.parseDouble(amountField.getText());
            BankAccount fromAccount = accounts.get(fromAccountNumber);
            BankAccount toAccount = accounts.get(toAccountNumber);
            if (fromAccount != null && toAccount != null && fromAccount.transfer(toAccount, amount)) {
                messageLabel.setText("Transfer successful.");
            } else {
                messageLabel.setText("Transfer failed. Check account details and balance.");
            }
            transferFrame.dispose();
        });

        transferFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                transferFrame.dispose();
            }
        });

        transferFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Create Account":
                showCreateAccountFrame();
                break;
            case "Deposit":
                showDepositFrame();
                break;
            case "Withdraw":
                showWithdrawFrame();
                break;
            case "Check Balance":
                showCheckBalanceFrame();
                break;
            case "Transfer":
                showTransferFrame();
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        new BankingSystem();
    }
}
