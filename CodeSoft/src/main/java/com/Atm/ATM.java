package com.Atm;

public class ATM {
    private BankAccount[] accounts; // Array to store bank accounts
    private int numOfAccounts; // Number of accounts currently stored

    public ATM(int maxAccounts) {
        accounts = new BankAccount[maxAccounts];
        numOfAccounts = 0;
    }

    // Method to add a new account with initial balance
    public void addAccount(String accountNumber, double initialBalance) {
        if (numOfAccounts < accounts.length) {
            boolean accountExists = false;
            for (int i = 0; i < numOfAccounts; i++) {
                if (accounts[i].getAccountNumber().equals(accountNumber)) {
                    accountExists = true;
                    break;
                }
            }
            if (!accountExists) {
                accounts[numOfAccounts] = new BankAccount(accountNumber, initialBalance);
                numOfAccounts++;
                System.out.println("Account " + accountNumber + " created with initial balance: $" + initialBalance);
            } else {
                System.out.println("Account " + accountNumber + " already exists.");
            }
        } else {
            System.out.println("Cannot add account. ATM storage is full.");
        }
    }

    // Method to deposit money into an account
    public void deposit(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposited $" + amount + " into account " + accountNumber + ". New balance: $" + account.getBalance());
        } else {
            System.out.println("Account " + accountNumber + " does not exist.");
        }
    }

    // Method to withdraw money from an account
    public void withdraw(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.withdraw(amount);
                System.out.println("Withdrawn $" + amount + " from account " + accountNumber + ". New balance: $" + account.getBalance());
            } else {
                System.out.println("Insufficient funds in account " + accountNumber + ".");
            }
        } else {
            System.out.println("Account " + accountNumber + " does not exist.");
        }
    }

    // Method to check account balance
    public void checkBalance(String accountNumber) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Account " + accountNumber + " balance: $" + account.getBalance());
        } else {
            System.out.println("Account " + accountNumber + " does not exist.");
        }
    }

    // Method to find an account by account number
    private BankAccount findAccount(String accountNumber) {
        for (int i = 0; i < numOfAccounts; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null; // Account not found
    }

    // Method to remove an account
    public void removeAccount(String accountNumber) {
        for (int i = 0; i < numOfAccounts; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                // Shift accounts to remove the account at index i
                for (int j = i; j < numOfAccounts - 1; j++) {
                    accounts[j] = accounts[j + 1];
                }
                accounts[numOfAccounts - 1] = null; 
                numOfAccounts--;
                System.out.println("Account " + accountNumber + " removed successfully.");
                return;
            }
        }
        System.out.println("Account " + accountNumber + " does not exist.");
    }
}




