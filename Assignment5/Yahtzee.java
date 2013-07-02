/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;

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
		
		scorecard = new int[nPlayers][N_CATEGORIES + 1];
	}
	
	private void playGame() {
		/* You fill this in */
		initPara();
		int roundCount = 1;
		while (roundCount <= N_ROUNDS) {
			rollDicesInTurn();
			roundCount++;
		}
		calculateFinalScore();
	}
	
	private void calculateFinalScore() {
		calculateUpperScore();
		calculateUpperBonus();
		calculateLowerScore();
		calculateTotal();
		updateFinalScorecard();
	}
	
	private void calculateUpperScore() {
		int count = 0;
		for (int i = 0; i < nPlayers; i++) {
			count = 0;
			for (int j = ONES; j <= SIXES; j++)
				count += scorecard[i][j];
			scorecard[i][UPPER_SCORE] = count;
		}
	}
	
	private void calculateUpperBonus() {
		for (int i = 0; i < nPlayers; i++)
			if (scorecard[i][UPPER_SCORE] >= 63)
				scorecard[i][UPPER_BONUS] = 35;
	}
	
	private void calculateLowerScore() {
		int count = 0;
		for (int i = 0; i < nPlayers; i++) {
			count = 0;
			for (int j = THREE_OF_A_KIND; j <= CHANCE; j++)
				count += scorecard[i][j];
			scorecard[i][LOWER_SCORE] = count;
		}
	}
	
	private void calculateTotal() {
		for (int i = 0; i < nPlayers; i++) {
			scorecard[i][TOTAL] = 0;
			for (int j = 1; j <= 6; j++)
				scorecard[i][TOTAL] += scorecard[i][j];
			scorecard[i][TOTAL] += scorecard[i][8];
			for (int j = 9; j <= 15; j++)
				scorecard[i][TOTAL] += scorecard[i][j];
		}
	}
	
	private void updateFinalScorecard() {
		for (int i = 0; i < nPlayers; i++)
			for (int j = 1; j <= N_CATEGORIES; j++)
				display.updateScorecard(j, i + 1, scorecard[i][j]);
	}
	
	private void rollDicesInTurn() {
		int[] dice;
		int score;
		int category;
		for (int i = 0; i < nPlayers; i++) {
			dice = rollThrice(i);
			category = chooseValidCategory(i);
			score = calculateScore(dice, category);
			scorecard[i][category] = score;
			display.updateScorecard(category, i + 1, score);
			calculateTotal();
			display.updateScorecard(TOTAL, i + 1, scorecard[i][TOTAL]);
		}
	}
	
	/*
	 * prototype version using YahtzeeMagicStub.checkCategory method
	 */
	private int calculateScore(int[] dice, int category) {
		int score = 0;
		
		if (YahtzeeMagicStub.checkCategory(dice, category) == false)
			return 0;
		//Arrays.sort(dice);
		switch (category) {
		case ONES:
			score = 1 * countOccurrences(dice, category);
			break;
		case TWOS:
			score = 2 * countOccurrences(dice, category);
			break;
		case THREES:
			score = 3 * countOccurrences(dice, category);
			break;
		case FOURS:
			score = 4 * countOccurrences(dice, category);
			break;
		case FIVES:
			score = 5 * countOccurrences(dice, category);
			break;
		case SIXES:
			score = 6 * countOccurrences(dice, category);
			break;
		case THREE_OF_A_KIND:
			score = 3 * findOccurMost(dice);
			break;
		case FOUR_OF_A_KIND:
			score = 4 * findOccurMost(dice);
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
	
	/*
	 * find the number that occurs most frequently inside an int array
	 */
	private int findOccurMost(int[] arr) {
		int[] count = new int[10];
		int max = 0;
		int index = 0;
		
		for (int i = 0; i < 10; i++)
			count[i] = 0;
		for (int i = 0; i < N_DICE; i++)
			count[arr[i]]++;
		for (int i = 1; i <= 6; i++ )
			if (count[i] > max) {
				max = count[i];
				index = i;
			}
		
		return index;
	}
	
	private int sumUp(int[] dice) {
		int sum = 0;
		
		for (int i = 0; i < N_DICE; i++)
			sum += dice[i];
		return sum;
	}
	
	/*
	 * count occurences of value in dice array
	 */
	private int countOccurrences(int[] dice, int value) {
		int count = 0;
		
		for (int i = 0; i < N_DICE; i++)
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
	 * roll dices thrice for player, player is indexed from 0
	 */
	private int[] rollThrice(int player) {
		int[] dice = new int[N_DICE];
		for (int i = 1; i <= N_ROLL_CHANCE; i++) {
			if (i == 1) {
				display.printMessage(playerNames[player] + "'s turn! Click \"Roll Dice\"" +
				" button to roll the dice");
				display.waitForPlayerToClickRoll(player + 1);
			} else {
				display.printMessage("Select the dice you wish to re-roll and click" +
				"\"Roll Again\".");
				display.waitForPlayerToSelectDice();
			}
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
	private int[][] scorecard;
	
}
