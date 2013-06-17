/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	// You fill in this part
	public void run() {
		repairOneColumn();
		while (frontIsClear()) {
			for (int i = 0; i < 3; i++) {
				if (frontIsClear())
					move();
			}
			if (frontIsClear()) {
				move();
				repairOneColumn();
			}
		}
	}
	
	/*
	 * pre-condition: at the bottom of a colomn and facing east
	 * post-condition: at the bottom of a colomn and facing east
	 */
	private void repairOneColumn() {
		turnLeft();
		repairColumn();
		moveBackToBottom();
		turnLeft();
	}
	
	/*
	 * pre-condition: at the bottom of a column and facing north
	 * post-condition: at the top of a column and facing south
	 */
	private void repairColumn() {
		if (noBeepersPresent())
			putBeeper();
		while (frontIsClear()) {
			move();
			if (noBeepersPresent())
				putBeeper();		
		}
		turnAround();
	}
	
	/*
	 * pre-condition: at the top of a column and facing south
	 * post-condition: at the bottom of a column and facing south
	 */
	private void moveBackToBottom() {
		while (frontIsClear())
			move();
	}
}
