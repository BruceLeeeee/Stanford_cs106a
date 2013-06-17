/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	// You fill in this part
	public void run() {
		paintOneRow();
		turnLeft();
		while (frontIsClear()) {
			move();
			if (rightIsBlocked()) {
				paintForRowToWest();
			} else {
				paintForRowToEast();
			}
		}
	}
	
	/*
	 * pre-condition: facing east or west
	 * post-condition: null
	 */
	private void paintOneRow() {
		putBeeper();
		while (frontIsClear()) {
			if (frontIsClear())
				move();
			if (frontIsClear()) {
				move();
				putBeeper();
			}
		}
	}
	
	/*
	 * pre-condition: facing north and right is blocked
	 * post-condition: facing north and left is blocked
	 */
	private void paintForRowToWest() {		
		turnLeft();
		paintOneRow();
		turnRight();
	}
	
	/*
	 * pre-condition: facing north and left is blocked
	 * post-condition: facing north and right is blocked
	 */
	private void paintForRowToEast() {
		turnRight();
		paintOneRow();
		turnLeft();
	}
}
