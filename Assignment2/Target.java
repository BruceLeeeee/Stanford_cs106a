/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	private static final int INCH = 72;
	
	public void run() {
		/* You fill this in. */
		int winCenterX = getWidth() / 2;
		int winCenterY = getHeight() / 2;
		
		GOval oval1 = new GOval(winCenterX - INCH, winCenterY - INCH, INCH * 2, INCH  * 2);
		oval1.setFilled(true);
		oval1.setColor(Color.RED);
		add(oval1);
		
		GOval oval2 = new GOval(winCenterX - INCH * 0.65, winCenterY - INCH * 0.65, 
				INCH * 0.65 * 2, INCH * 0.65 * 2);
		oval2.setFilled(true);
		oval2.setColor(Color.WHITE);
		add(oval2);
		
		GOval oval3 = new GOval(winCenterX - INCH * 0.3, winCenterY - INCH * 0.3,
				INCH * 0.3 * 2, INCH * 0.3 * 2);
		oval3.setFilled(true);
		oval3.setColor(Color.RED);
		add(oval3);
	}
}
