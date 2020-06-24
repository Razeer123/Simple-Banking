package com.company;

import java.util.Random;

public class Card {

    private final String cardNumber;
    private final String pin;
    private final long balance;

    protected Card() {

        Random random = new Random();
        String tempNumber = "400000" + (random.nextInt(999) + 100) +
                (random.nextInt(999) + 100) + (random.nextInt(999) + 100);

        // Applying Luhn Algorithm to generate the checksum

        long numberCheck = Long.parseLong(tempNumber);
        int sum = 0, checksum = 0;

        while (numberCheck > 0) {
            sum += numberCheck % 10;
            numberCheck /= 10;
        }

        while (sum % 10 != 0) {
            sum++;
            checksum++;
        }

        cardNumber = tempNumber + checksum;
        int tempPin = random.nextInt(9999);

        if (tempPin <= 999) {
            pin = "0" + tempPin;
        } else {
            pin = "" + tempPin;
        }

        balance = 0;

    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public long getBalance() {
        return balance;
    }

}
