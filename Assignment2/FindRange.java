/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int SENTINEL = 0;
	
	public void run() {
		/* You fill this in */
		int max = 0;
		int min = 0;
		int count = 0;
		
		println("This program finds the largest and smallest numbers.");
		while (true) {
			int num = readInt("? ");
			if (num == SENTINEL)
				break;
			count++;
			if (count == 1) {
				max = min = num;
			} else {
				if (max < num)
					max = num;
				if (min > num)
					min = num;
			}
		}
		
		if (count == 0) {
			println("User enter sentinel on the very first input line.");
		} else {
			println("smallest: " + min);
			println("largest: " + max);
		}
			
	}
}

