/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int LABEL_Y_OFFSET = 30;
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		removeAll();
		addScaffold();
		incorrectCount = 0;
		resetLabel();
		
	}

	private void resetLabel() {
		double x, y;
		x = (getWidth() - BEAM_LENGTH) / 2;
		y = getHeight() - LABEL_Y_OFFSET;
		wrongGuess = new GLabel("", x, y);
		y -= LABEL_Y_OFFSET;
		currentWord = new GLabel("", x, y);
		add(currentWord);
		add(wrongGuess);
	}
	
	private void addScaffold() {
		double x, y;
		x = (getWidth() / 2 - BEAM_LENGTH) / 2;
		y = (getHeight() - SCAFFOLD_HEIGHT) / 2 - LABEL_Y_OFFSET;
		GLine line = new GLine(x, y, x, y + SCAFFOLD_HEIGHT);
		add(line);
		line = new GLine(x, y, x + BEAM_LENGTH, y);
		add(line);
		x += BEAM_LENGTH;
		line = new GLine(x, y, x, y + ROPE_LENGTH);
		add(line);
	}
	
/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
		currentWord.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */
		incorrectStr += letter;
		incorrectCount++;
		wrongGuess.setLabel(incorrectStr);
		switch (incorrectCount) {
		case 1:
			addHead();
			break;
		case 2:
			addBody();
			break;
		case 3:
			addLeftarm();
			break;
		case 4:
			addRightarm();
			break;
		case 5: 
			addLeftleg();
			break;
		case 6:
			addRightleg();
			break;
		case 7:
			addLeftfoot();
			break;
		case 8:
			addRightfoot();
			break;
		}
	}
	
	private void addHead() {
		double x, y;
		x = (getWidth() / 2 - BEAM_LENGTH) / 2 + BEAM_LENGTH - HEAD_RADIUS;
		y = (getHeight() - SCAFFOLD_HEIGHT) / 2 - LABEL_Y_OFFSET + ROPE_LENGTH;
		GOval head = new GOval(x, y, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
		add(head);
	}
	
	private void addBody() {
		double x, y;
		x = (getWidth() / 2 - BEAM_LENGTH) / 2 + BEAM_LENGTH;
		y = (getHeight() - SCAFFOLD_HEIGHT) / 2 - LABEL_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2;
		GLine body = new GLine(x, y, x, y + BODY_LENGTH);
		add(body);
	}
	
	private void addLeftarm() {
		double x, y;
		x = (getWidth() / 2 - BEAM_LENGTH) / 2 + BEAM_LENGTH;
		y = (getHeight() - SCAFFOLD_HEIGHT) / 2 - LABEL_Y_OFFSET + ROPE_LENGTH
			+ HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
		GLine line = new GLine(x, y, x - UPPER_ARM_LENGTH, y);
		add(line);
		line = new GLine(x - UPPER_ARM_LENGTH, y, x - UPPER_ARM_LENGTH, y + LOWER_ARM_LENGTH);
		add(line);
	}
	
	private void addRightarm() {
		double x, y;
		x = (getWidth() / 2 - BEAM_LENGTH) / 2 + BEAM_LENGTH;
		y = (getHeight() - SCAFFOLD_HEIGHT) / 2 - LABEL_Y_OFFSET + ROPE_LENGTH
			+ HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
		GLine line = new GLine(x, y, x + UPPER_ARM_LENGTH, y);
		add(line);
		line = new GLine(x + UPPER_ARM_LENGTH, y, x + UPPER_ARM_LENGTH, y + LOWER_ARM_LENGTH);
		add(line);
	}
	
	private void addLeftleg() {
		double x, y;
		x = (getWidth() / 2 - BEAM_LENGTH) / 2 + BEAM_LENGTH;
		y = (getHeight() - SCAFFOLD_HEIGHT) / 2 - LABEL_Y_OFFSET + ROPE_LENGTH
			+ HEAD_RADIUS * 2 + BODY_LENGTH;
		GLine line = new GLine(x, y, x - HIP_WIDTH, y);
		add(line);
		x -= HIP_WIDTH;
		line = new GLine(x, y, x, y + LEG_LENGTH);
		add(line);
	}
	
	private void addRightleg() {
		double x, y;
		x = (getWidth() / 2 - BEAM_LENGTH) / 2 + BEAM_LENGTH;
		y = (getHeight() - SCAFFOLD_HEIGHT) / 2 - LABEL_Y_OFFSET + ROPE_LENGTH
			+ HEAD_RADIUS * 2 + BODY_LENGTH;
		GLine line = new GLine(x, y, x + HIP_WIDTH, y);
		add(line);
		x += HIP_WIDTH;
		line = new GLine(x, y, x, y + LEG_LENGTH);
		add(line);
	}
	
	private void addLeftfoot() {
		double x, y;
		x = (getWidth() / 2 - BEAM_LENGTH) / 2 + BEAM_LENGTH - HIP_WIDTH;
		y = (getHeight() - SCAFFOLD_HEIGHT) / 2 - LABEL_Y_OFFSET + ROPE_LENGTH
			+ HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
		GLine line = new GLine(x, y, x - FOOT_LENGTH, y);
		add(line);
	}
	
	private void addRightfoot() {
		double x, y;
		x = (getWidth() / 2 - BEAM_LENGTH) / 2 + BEAM_LENGTH + HIP_WIDTH;
		y = (getHeight() - SCAFFOLD_HEIGHT) / 2 - LABEL_Y_OFFSET + ROPE_LENGTH
			+ HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
		GLine line = new GLine(x, y, x + FOOT_LENGTH, y);
		add(line);
	}
	
	private int incorrectCount;
	private GLabel currentWord;
	private GLabel wrongGuess;
	private String incorrectStr = "";
}
