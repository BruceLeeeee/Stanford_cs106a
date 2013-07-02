/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	public static final int N_ROUNDS = 13;
	public static final int N_ROLL_CHANCE = 3;
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void initPara() {
		categoryLogger = new boolean[nPlayers][N_CATEGORIES];
		for (int i = 0; i < nPlayers; i++)
			for (int j = 0; j < N_CATEGORIES; j++)
				categoryLogger[i][j] = false;
	}
	
	private void playGame() {
		/* You fill this in */
		initPara();
		int roundCount = 1;
		while (roundCount <= N_ROUNDS) {
			rollDicesInTurn();
			roundCount++;
		}
		//calculateFinalScore();
	}
	
	private void rollDicesInTurn() {
		int[] dice;
		int score;
		int category;
		for (int i = 0; i < nPlayers; i++) {
			dice = rollThrice(i + 1);
			category = chooseValidCategory(i);
			score = calculateScore(dice, category);
			display.updateScorecard(category, i + 1, score);
		}
	}
	
	/*
	 * prototype version using YahtzeeMagicStub.checkCategory method
	 */
	private int calculateScore(int[] dice, int category) {
		int score = 0;
		
		if (YahtzeeMagicStub.checkCategory(dice, category) == false)
			return 0;
		
		switch (category) {
		case ONES: case TWOS: case THREES:
		case FOURS: case FIVES: case SIXES:
			score = checkForOccurrences(dice, category);
			break;
		case THREE_OF_A_KIND:
			break;
		case FOUR_OF_A_KIND:
			break;
		case FULL_HOUSE:
			score = 25;
			break;
		case SMALL_STRAIGHT:
			score = 30;
			break;
		case LARGE_STRAIGHT:
			score = 40;
			break;
		case YAHTZEE:
			score = 50;
			break;
		case CHANCE:
			score = sumUp(dice);
			break;
		}
		
		return score;
	}
	
	private int sumUp(int[] dice) {
		int sum = 0;
		
		for (int i = 0; i < dice[i]; i++)
			sum += dice[i];
		return sum;
	}
	private int checkForOccurrences(int[] dice, int value) {
		int count = 0;
		
		for (int i = 0; i < 6; i++)
			if (dice[i] == value)
				count++;
		
		return count;
	}
	
	private int chooseValidCategory(int player) {
		int category;
		
		while (true) {
			category = display.waitForPlayerToSelectCategory();
			if (categoryLogger[player][category] == false) {
				categoryLogger[player][category] = true;
				break;
			}
		}
		
		return category;
	}
	
	/*
	 * roll dices thrice for player, player is indexed from 1
	 */
	private int[] rollThrice(int player) {
		int[] dice = new int[N_DICE];
		for (int i = 1; i <= N_ROLL_CHANCE; i++) {
			display.waitForPlayerToClickRoll(player);
			for (int j = 0; j < N_DICE; j++)
				if (i == 1 || display.isDieSelected(j))
					dice[j] = rollOneDice();
			display.displayDice(dice);
		}
		
		return dice;
	}
	
	/*
	 * roll a dice, and return its value
	 */
	private int rollOneDice() {
		return rgen.nextInt(1, 6);
	}
	
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private boolean[][] categoryLogger;

}
