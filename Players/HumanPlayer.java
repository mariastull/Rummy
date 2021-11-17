package Players;

import java.util.Scanner;

/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public class HumanPlayer extends Player {
    private Scanner keyboard;

    public HumanPlayer(){
        super();
        keyboard = new Scanner(System.in);
    }

    private boolean askYesNo(String question){
        System.out.println(question + " (y/n)");
        while(true) {
            String input = keyboard.next();
            if(input.equals("y")){
                return true;
            } else if(input.equals("n")){
                return false;
            }
            System.out.println("Expects either y or n");//, but got: '" + input + "'");
        }
    }

    @Override
    protected boolean askCardChoice() {
        return askYesNo("Do you want to take the top of the discard pile?");
    }

    @Override
    protected int askCardDiscard() {
        printHand();
        System.out.println("Which card would you like to discard? (1-7)");
        boolean inputValid = false;
        int input = 0;
        while(!inputValid){
            try {
                input = keyboard.nextInt();
            } catch (Exception e) {
                System.out.println("That's not a number, choose between 1 and 7");
                String trash = keyboard.next();
                continue;
            }
            if(input <= 0){
                System.out.println("Please choose a number that's 1 or greater");
                continue;
            }
            if(input > 7){
                System.out.println("Please choose a number that's 5 or less");
                continue;
            }
            inputValid = true;
        }
        
        // go from 1 indexed to 0 indexed
        return input - 1;
    }

    @Override
    protected boolean askEndGame() {
        return askYesNo("Do you want to end the game now?");
    }

    public void printHand(){
        System.out.println("Your hand:");
        super.printHand();
    }
}