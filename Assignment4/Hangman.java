/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	private static final int GUESS_CHANCE = 8;
	
    public void run() {
		/* You fill this in */
    	setup();
    	play();
	}

    /*
     * set up the secret word and currentWord
     */
    private void setup() {
    	lexicon = new HangmanLexicon();
    	int index = rgen.nextInt(0, lexicon.getWordCount() - 1);	
    	secretWord = lexicon.getWord(index);
    	initCurrentWorld();
    	guessLeft = GUESS_CHANCE;
    }
    
    private void initCurrentWorld() {
    	int len = secretWord.length();
    	currentWord = "";
    	for (int i = 0; i < len; i++)
    		currentWord += '-';
    }
    
    private void play() {
    	println("Welcome to Hangman!");
    	println("The word now looks like this: " + currentWord);
    	println("You have 8 guesses left.");
    	while (!gameOver()) {
    		if (guess() == false)
    			guessLeft--;
    	}
    }
    
    /*
     * gameOver has two circonstances: 
     * 	1. the user guess the right word; 
     * 	2. the user uses up all guess chances
     */ 
    private boolean gameOver() {
    	if (secretWord.equals(currentWord)) {
    		println("You guessed the word: " + secretWord);
    		println("You win.");
    		return true;
    	}
    	
    	if (guessLeft == 0) {
    		println("You're completely hung.");
    		println("The word was: " + secretWord);
    		println("You lose.");
    		return true;
    	}
    	
    	return false;
    }
    
    /*
     * return true is a guess is correct, otherwise false
     */
    private boolean guess() {
    	char guessedCh = inputLegalGuess();
    	if (guessIsCorrect(guessedCh)) {
    		println("That guess is correct.");
    		updateCurrentWord(guessedCh);
    		if (secretWord.equals(currentWord))
    			return true;
    		println("The word now looks like this:" + currentWord);
        	println("You have " + guessLeft + " guesses left");
        	return true;
    		
    	} else {
    		println("There are no " + guessedCh + "'s in the word.");
    		if (guessLeft - 1 == 0)
    			return false;
    		println("The word now looks like this:" + currentWord);
        	println("You have " + (guessLeft - 1) + " guesses left");
        	return false;
    	}
    	
    }
    
    private void updateCurrentWord(char guessedCh) {
    	int start = 0;
    	int index;
    	while (true) {
    		index = secretWord.indexOf(guessedCh, start);
    		if (index == -1) 
    			break;
    		currentWord = currentWord.substring(0, index) + guessedCh 
    					+ currentWord.substring(index + 1);
    		start = index + 1;
    	}
    }
    
    private boolean guessIsCorrect(char guessedCh) {
    	if (secretWord.indexOf(guessedCh) != -1)
    		return true;
    	return false;
    }
    
    private char inputLegalGuess() {
    	String str = "";
    	while (true) {
    		str = readLine("You guess: ");
    		if (isLegal(str))
    			break;
    	}
    	
    	return Character.toUpperCase(str.charAt(0));
    }
    
    private boolean isLegal(String str) {
    	if (str.length() != 1)
    		return false;
    	char ch = str.charAt(0);
    	ch = Character.toUpperCase(ch);
    	if (ch < 'A' || ch > 'Z')
    		return false;
    	
    	return true;
    }
    
    //instance variables
    private HangmanLexicon lexicon;
    private String secretWord;
    private String currentWord;
    private int guessLeft;
    private RandomGenerator rgen = RandomGenerator.getInstance();
}
