/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author betzler
 * The RockPaperScissors class implements an application which plays up to 10
 * rounds of Rock, Paper, Scissors with the user.  Round winners and overall
 * results are printed to the console.
 */
public class RockPaperScissors {

    /*
    public static void main(String[] args)
    Entry point for the program; execution begins here.
    */
    public static void main(String[] args) {
        char responsePlayAgain;
        int numberRounds;
        int numberTies;
        int numberUserWins;
        int numberComputerWins;
        int computerChoice;
        int userChoice;

        do {
            numberRounds = getRounds();

            numberTies = 0;
            numberUserWins = 0;
            numberComputerWins = 0;

            for (int i = 1; i <= numberRounds; i++) {
                userChoice = getUserChoice();
                computerChoice = getComputerChoice();

                System.out.println("\nI chose " + getPlayDescription(computerChoice) + ".");
                System.out.println("You chose " + getPlayDescription(userChoice) + ".");

                //Fastest way to determine round outcome.
                if ((userChoice - computerChoice == 1) || (computerChoice - userChoice == 2)) {
                    numberUserWins += 1;
                    System.out.println("\nYou win this round!");
                } else if (userChoice == computerChoice) {
                    numberTies += 1;
                    System.out.println("\nThis round is a tie!");
                } else {
                    numberComputerWins += 1;
                    System.out.println("\nI win this round!");
                }
            }

            declareWinner(numberUserWins, numberComputerWins, numberTies);
            responsePlayAgain = getPlayAgain();

        } while (responsePlayAgain == 'y');

    }

    /*
    Prompts user for number of rounds within range of 1-10. User response is error
    checked; on invalid input user is repeatedly prompted until valid input.
    Once user input is validated string input is returned as integer.
    */
    public static int getRounds() {
        String response;
        boolean responseValid = false;

        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n*****          ROCK PAPER SCISSORS          *****");
        System.out.println("\nWe can play up to ten rounds of Rock, Paper, Scissors.");

        do {
            System.out.println("How many rounds of Rock, Paper, Scissors would you like to play?");
            response = sc.nextLine();

            if (validateRangedResponse(response, 10)) {
                responseValid = true;
            } else {
                System.out.println("\nThat isn't a valid response.\nPlease enter a value from 1 to 10.");
            }

        } while (!responseValid);

        return Integer.parseInt(response);
    }

    /*
    Prompts user for choice in range of 1-3: Rock/1, Paper/1 or Scissors/3.
    User response is error checked; on invalid input user is repeatedly prompted
    until valid input.  Once user input is validated string input is returned
    as integer.
    */
    public static int getUserChoice() {
        String response;
        boolean responseValid = false;

        Scanner sc = new Scanner(System.in);

        System.out.println("\nChoose Rock, Paper or Scissors!!!");

        do {
            System.out.println("What's your choice: 1 = Rock, 2 = Paper, 3 = Scissors?");
            response = sc.nextLine();

            if (validateRangedResponse(response, 3)) {
                responseValid = true;
            } else {
                System.out.println("\nThat isn't a valid response.\nWe can only choose Rock, Paper or Scissors.");
            }

        } while (!responseValid);
        
        return Integer.parseInt(response);
    }

    public static int getComputerChoice() {
        Random randomChoice = new Random();

        return (randomChoice.nextInt(3) + 1);
    }

    //Play choice translated to string description for round summary to console.
    public static String getPlayDescription(int play) {
        String playDescription = "";

        switch (play) {
            case 1:
                playDescription = "Rock";
                break;
            case 2:
                playDescription = "Paper";
                break;
            case 3:
                playDescription = "Scissors";
                break;
            default:
                break;
        }

        return playDescription;
    }

    /*
    Validates a string input is within an integer range up to max.  Quick way
    to check against most keyboard input.
    */
    public static boolean validateRangedResponse(String response, int max) {
        boolean responseValid = false;

        for (int i = 1; i <= max; i++) {
            if (response.equals(Integer.toString(i))) {
                responseValid = true;
                break;
            }
        }

        return responseValid;
    }

    public static void declareWinner(int user, int computer, int ties) {
        System.out.println("\nWe tied " + ties + " times.");
        System.out.println("You won " + user + " times.");
        System.out.println("I won " + computer + " times.");

        if (user > computer) {
            System.out.println("\nYou're the winner!!!\n");
        } else if (computer > user) {
            System.out.println("\nI'm the winner!!!\n");
        } else {
            System.out.println("\nWe're both winners - it's a tie!!!\n");
        }
    }

    /*
    Prompts user for y/n choice to play again. User response is error checked; 
    on invalid input user is repeatedly prompted until valid input.
    */
    public static char getPlayAgain() {
        String response;
        char yesNo = 'y';
        boolean responseValid = false;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Would you like to play again (y/n)?");
            response = sc.nextLine();

            if (response.equalsIgnoreCase("n")){
                responseValid = true;
                yesNo = 'n';
            } else if (response.equalsIgnoreCase("y")){
                responseValid = true;
                yesNo = 'y';
            } else {
                System.out.println("\nI didn't understand your answer.");
            }

        } while (!responseValid);

        return yesNo;
    }
}
