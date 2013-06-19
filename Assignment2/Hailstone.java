/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		int n = readInt("Enter a number: ");
		int count = 0;
		
		while (n != 1) {
			count++;
			if (n % 2 == 1) {
				int tmp = n;
				n = 3 * n + 1;
				println(tmp + " is odd, so I make 3n + 1: " + n);
			} else {
				int tmp = n;
				n /= 2;
				println(tmp + " is even, so I take half: " + n);
			}
		}
		println("The process took " + count + " to reach 1");
	}
}

