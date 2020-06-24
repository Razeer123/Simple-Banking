package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final ArrayList<Card> cards = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice = -1;

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

        Card account = new Card();
        cards.add(account);
        printData(account);

    }

    static void logIn() {

        System.out.println();
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

        System.out.println("Enter your card number:");
        String numberInput = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pinInput = scanner.nextLine();

        // Checking correctness of card number & PIN

        for (Card card : cards) {

            if (card.getCardNumber().equals(numberInput) && card.getPin().equals(pinInput)) {

                loggedIn = true;
                System.out.println("You have successfully logged in!");
                menu(card);

            }
        }

        if (!loggedIn) {
            System.out.println("Wrong card number or PIN!");
        }
    }

    static void menu(Card card) {

        System.out.println();
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");
        Scanner scanner = new Scanner(System.in);
        int secondChoice = scanner.nextInt();

        switch (secondChoice) {

            case 2:

                System.out.println();
                System.out.println("You have sucessfully logged out!");
                return;

            case 1:

                System.out.println();
                System.out.println("Balance: " + card.getBalance());
                break;

            case 0:

                System.out.println();
                System.out.println("Bye!");
                System.exit(0);

        }

        menu(card);

    }
}
