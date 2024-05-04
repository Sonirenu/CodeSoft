package com.game;

import java.util.Scanner;
import java.util.Random;

public class Game {
    private int randomNumber;
    private int maxRange;
    private int numberOfAttempts;
    private int noOfGuesses;
    private boolean hasWon;

    public Game(int maxRange, int numberOfAttempts) {
        this.maxRange = maxRange;
        this.numberOfAttempts = numberOfAttempts;
        this.randomNumber = generateRandomNumber(maxRange);
        this.noOfGuesses = 0;
        this.hasWon = false;
    }

    public void play() {
        System.out.println("Welcome to Guess the Number Game!");
        System.out.println("I have chosen a number between 1 and " + maxRange + ". Try to guess it.");

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < numberOfAttempts; i++) {
            System.out.print("Attempt " + (i + 1) + ": ");
            int guess = takeUserInput(scanner);

            if (isCorrectNumber(guess)) {
                hasWon = true;
                break;
            } else if (guess < randomNumber) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }
        }

        if (hasWon) {
            System.out.println("Congratulations! You guessed the number (" + randomNumber + ") correctly!");
        } else {
            System.out.println("Sorry, you've used all your attempts. The number was: " + randomNumber);
        }
    }

    private int generateRandomNumber(int maxRange) {
        Random random = new Random();
        return random.nextInt(maxRange) + 1;
    }

    private int takeUserInput(Scanner scanner) {
        noOfGuesses++;
        System.out.print("Enter your guess: ");
        return scanner.nextInt();
    }

    private boolean isCorrectNumber(int guess) {
        return guess == randomNumber;
    }

    // Getter and setter for noOfGuesses property
    public int getNoOfGuesses() {
        return noOfGuesses;
    }

    public void setNoOfGuesses(int noOfGuesses) {
        this.noOfGuesses = noOfGuesses;
    }

    public static void main(String[] args) {
        Game game = new Game(100, 5); 
        game.play();
    }
}



