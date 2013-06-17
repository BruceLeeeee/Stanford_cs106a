/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	// You fill in this part
	public void run() {
		if (frontIsBlocked()) {
			putBeeper();
		} else {
			drawNortheastDiagonal();
			turnRight();
			moveToWall();
			moveToSquareCenter();
			moveToWall();
			putBeeper();
			cleanUp();
			moveToMidpoint();
		}
	}
	
	/*
	 * pre-condition: at the original point and facing east
	 * post-condition: at the end of diagonal and facing east
	 */
	private void drawNortheastDiagonal() {
		putBeeper();
		while (frontIsClear()) {
			move();
			turnLeft();
			move();
			turnRight();
			putBeeper();
		}
	}
	
	private void moveToWall() {
		while (frontIsClear()) {
			move();
		}
	}
	
	/*
	 * pre-condition: at the right corner and facing north
	 * post-condition: at the center of the square and facing south
	 */
	private void moveToSquareCenter() {
		turnRight();
		while (noBeepersPresent()) {
			putBeeper();
			move();
			if (noBeepersPresent()) {
				turnRight();
				move();
				turnLeft();
			}
		}
		turnLeft();
	}
	
	/*
	 * pre-condition: at the end of diagonal and facing east
	 * post-condition: at the mid-point and facing east
	 */
	private void moveToMidpoint() {
		turnRight();
		moveToWall();
		turnRight();
		while (noBeepersPresent())
			move();
		turnAround();
	}
	
	/*
	 * pre-condition: at the mid-point and facing south
	 */
	private void cleanUp() {
		turnLeft();
		moveToWall();
		cleanUpSouthEastDiagonal();
		moveToWall();
		turnLeft();
		moveToWall();
		cleanUpNorthEastDiagonal();
	}
	
	/*
	 * pre-condition: at the right corner and facing east
	 * post-condition: at the center of the square and facing east
	 */
	private void cleanUpSouthEastDiagonal() {
		turnAround();
		while (beepersPresent()) {
			pickBeeper();
			move();
			turnRight();
			move();
			turnLeft();
		}
	}
	
	/*
	 * pre-condition: at the left corner and facing south
	 * post-condition: at the end of diagonal and facing east
	 */
	private void cleanUpNorthEastDiagonal() {
		turnLeft();
		pickBeeper();
		while (frontIsClear()) {
			move();
			turnLeft();
			move();
			turnRight();
			if (beepersPresent())
				pickBeeper();
		}
	}
}
