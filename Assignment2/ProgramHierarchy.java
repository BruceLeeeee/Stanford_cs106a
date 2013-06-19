/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	private static final int BOX_WIDTH = 100;
	private static final int BOX_HEIGHT = 50;
	
	public void run() {
		/* You fill this in. */
		int winWidth = getWidth();
		int winHeight = getHeight();
		double x;
		double y;
		
		// box0
		x = (winWidth - BOX_WIDTH) / 2;
		y = (winHeight - BOX_HEIGHT * 3) / 2;
		GRect rect0 = new GRect(x, y, BOX_WIDTH, BOX_HEIGHT);
		add(rect0);	
		GLabel label0 = new GLabel("Program");
		x = rect0.getX() + (rect0.getWidth() - label0.getWidth()) / 2;
		y = rect0.getY() + (rect0.getHeight() - label0.getHeight()) / 2 + label0.getAscent();
		label0.setLocation(x, y);
		add(label0);
		
		//box1
		x = rect0.getX() - BOX_WIDTH - BOX_HEIGHT / 2;
		y = rect0.getY() + BOX_HEIGHT * 2;
		GRect rect1 = new GRect(x, y, BOX_WIDTH, BOX_HEIGHT);
		add(rect1);	
		GLabel label1 = new GLabel("GraphicsProgram");
		x = rect1.getX() + (rect1.getWidth() - label1.getWidth()) / 2;
		y = rect1.getY() + (rect1.getHeight() - label1.getHeight()) / 2 + label1.getAscent();
		label1.setLocation(x, y);
		add(label1);
		
		//box2
		x = rect0.getX();
		y = rect0.getY() + BOX_HEIGHT * 2;
		GRect rect2 = new GRect(x, y, BOX_WIDTH, BOX_HEIGHT);
		add(rect2);	
		GLabel label2 = new GLabel("ConsoleProgram");
		x = rect2.getX() + (rect2.getWidth() - label2.getWidth()) / 2;
		y = rect2.getY() + (rect2.getHeight() - label2.getHeight()) / 2 + label2.getAscent();
		label2.setLocation(x, y);
		add(label2);
		
		//box3
		x = rect0.getX() + BOX_WIDTH + BOX_HEIGHT / 2;
		y = rect0.getY() + BOX_HEIGHT * 2;
		GRect rect3 = new GRect(x, y, BOX_WIDTH, BOX_HEIGHT);
		add(rect3);	
		GLabel label3 = new GLabel("DialogProgram");
		x = rect3.getX() + (rect3.getWidth() - label3.getWidth()) / 2;
		y = rect3.getY() + (rect3.getHeight() - label3.getHeight()) / 2 + label3.getAscent();
		label3.setLocation(x, y);
		add(label3);
		
		double x0, y0;
		double x1, y1;
		
		//lines
		x0 = rect0.getX() + rect0.getWidth() / 2;
		y0 = rect0.getY() + rect0.getHeight();
		
		x1 = rect1.getX() + rect1.getWidth() / 2;
		y1 = rect1.getY();
		GLine line0 = new GLine(x0, y0, x1, y1);
		add(line0);
		
		x1 = rect2.getX() + rect2.getWidth() / 2;
		y1 = rect2.getY();
		GLine line1 = new GLine(x0, y0, x1, y1);
		add(line1);
		
		x1 = rect3.getX() + rect3.getWidth() / 2;
		y1 = rect3.getY();
		GLine line2 = new GLine(x0, y0, x1, y1);
		add(line2);
		
	}
}

