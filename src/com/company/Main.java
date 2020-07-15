package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    static int counter = -1;
    static String fileName;

    public static void main(String[] args) {

        fileName = "cards.db";
        Scanner scanner = new Scanner(System.in);

        // Variable counting card numbers -> have to improve that

        int choice = -1;

        // Creating an SQL database using functions

        createDatabase(fileName);
        createTable(fileName);

        while (choice != 0) {

            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");

            choice = scanner.nextInt();

            switch (choice) {

                case 0:

                    System.out.println();
                    System.out.println("Bye!");
                    break;

                case 1:

                    createAccount();
                    break;

                case 2:

                    logIn();

            }
        }
    }

    static void printData(Card card) {

        System.out.println();
        System.out.println("Your card has been created");
        System.out.println("Your card number:");
        System.out.println(card.getCardNumber());
        System.out.println("Your card PIN:");
        System.out.println(card.getPin());

    }

    static void createAccount() {

        counter++;
        InsertApp app = new InsertApp(fileName);
        Card account = new Card();

        // Inserting data into an SQL database

        app.insert(counter, Long.parseLong(account.getCardNumber()), Integer.parseInt(account.getPin()),
                account.getBalance());

        printData(account);

    }

    static void logIn() {

        System.out.println();
        Scanner scanner = new Scanner(System.in);
        SelectApp app = new SelectApp(fileName);
        boolean loggedIn = false;

        System.out.println("Enter your card number:");
        String numberInput = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pinInput = scanner.nextLine();

        // Checking correctness of card number & PIN

        if (app.checkNumber(Long.parseLong(numberInput)) && app.checkPin(Integer.parseInt(pinInput))) {

            loggedIn = true;
            System.out.println("You have successfully logged in!");
            menu(Long.parseLong(numberInput));

        }

        if (!loggedIn) {
            System.out.println("Wrong card number or PIN!");
        }
    }

    static void menu(long cardNumber) {

        System.out.println();
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");
        Scanner scanner = new Scanner(System.in);
        SelectApp app = new SelectApp(fileName);
        int secondChoice = scanner.nextInt();

        switch (secondChoice) {

            case 2:

                System.out.println();
                System.out.println("You have sucessfully logged out!");
                return;

            case 1:

                System.out.println();
                System.out.println("Balance: " + app.returnBalance(cardNumber));
                break;

            case 0:

                System.out.println();
                System.out.println("Bye!");
                System.exit(0);

        }

        menu(cardNumber);

    }

    public static void createDatabase(String fileName) {

        // Connection string

        String url = "jdbc:sqlite:" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void createTable(String fileName) {

        // Connection string

        String url = "jdbc:sqlite:" + fileName;

        // Creating table

        String sql = "CREATE TABLE IF NOT EXISTS card (" +
                "  `id` INTEGER," +
                "  `number` TEXT," +
                "  `pin` TEXT," +
                "  `balance` INTEGER DEFAULT 0"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Create new table

            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
