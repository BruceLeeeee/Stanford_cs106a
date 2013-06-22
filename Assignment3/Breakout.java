/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/** Animation cycle delay */ 
	private static final int DELAY = 10;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		setup();
		play();
	}
	
	private void play() {
		createBall();
		while (!gameOver()) {
			moveBall();
			dealWithCollision();
			pause(DELAY);
		}
	}
	
	private boolean gameOver() {
		if (hitLowerWall()) {
			turnsLeft--;
			if (turnsLeft > 0) {
				createBall();
				return false;
			} else {
				printMsg("You lost!");
				return true;
			}
		}
		
		if (bricksLeft == 0) {
			printMsg("You won!");
			return true;
		}
		
		return false;
	}
	
	private void printMsg(String msg) {
		double x, y;
		GLabel label = new GLabel(msg);
		x = (WIDTH - label.getWidth()) / 2;
		y = (HEIGHT - label.getAscent()) / 2;
		add(label, x, y);
	}
	private boolean hitLowerWall() {
		if (ball.getY() + ball.getHeight() >= HEIGHT)
			return true;
		return false;
	}
	private void dealWithCollision() {
		GObject collider = getCollidingObject();
		if (collider == null) {
			return;
		} else if (collider == paddle) {
			vy = -vy;
		} else {
			remove(collider);
			vy = -vy;
			bricksLeft--;
		}
	}
	
	private GObject getCollidingObject() {
		GObject collider;
		collider = getElementAt(ball.getX(), ball.getY());
		if (collider != null)
			return collider;
		collider = getElementAt(ball.getX() + ball.getWidth(), ball.getY());
		if (collider != null)
			return collider;
		collider = getElementAt(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight());
		if (collider != null)
			return collider;
		collider = getElementAt(ball.getX(), ball.getY() + ball.getHeight());
		if (collider != null)
			return collider;
		return null;
	}
	
	private void moveBall() {
		ball.move(vx, vy);
		if (ball.getX() + ball.getWidth() > WIDTH || ball.getX() < 0)
			vx = -vx;
		if (ball.getY() + ball.getHeight() > HEIGHT || ball.getY() < 0)
			vy = -vy;
	}
	
	/*
	 * set up the board and initial state of the program
	 */
	private void setup() {
		createBricks();
		createPaddle();
	}
	
	private void createBricks() {
		double x = 0;
		double y = 0;
		
		for (int i = 0; i < NBRICK_ROWS; i++) {
			x = (WIDTH - NBRICKS_PER_ROW * (BRICK_WIDTH + BRICK_SEP) + BRICK_SEP) / 2;
			y = BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP);
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				paintBrick(brick, i);
				add(brick);
				x += BRICK_WIDTH + BRICK_SEP;
			}
		}
	}
	
	private void paintBrick(GRect brick, int row) {
		brick.setFilled(true);
		switch (row) {
			case 0: case 1:
				brick.setColor(Color.RED);
				break;
			case 2: case 3:
				brick.setColor(Color.ORANGE);
				break;
			case 4: case 5:
				brick.setColor(Color.YELLOW);
				break;
			case 6: case 7:
				brick.setColor(Color.GREEN);
				break;
			case 8: case 9:
				brick.setColor(Color.CYAN);
				break;
		}
	}
	
	private void createPaddle() {
		double x, y;
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		x = (WIDTH - PADDLE_WIDTH) / 2;
		y = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		add(paddle, x, y);
		last = new GPoint(x + PADDLE_WIDTH / 2, y);
		addMouseListeners();
	}
	
	private void createBall() {
		double x, y;
		x = WIDTH / 2 - BALL_RADIUS;
		y = HEIGHT / 2 - BALL_RADIUS;
		ball = new GOval(BALL_RADIUS * 2, BALL_RADIUS * 2);
		ball.setFilled(true);
		ball.setColor(Color.BLUE);
		add(ball, x, y);
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5))
			vx = -vx;
		vy = 3.0;
	}
	
	public void mouseMoved(MouseEvent e) {
		paddle.move(e.getX() - last.getX(), 0);
		last.setLocation(e.getX(), last.getY());
	}
	// instance variables
	private GRect paddle;
	private GPoint last;     		/* last mouse position */
	private GOval ball;
	private double vx, vy;    		/* v, y velocity of the ball */
	private int turnsLeft = NTURNS;
	private int bricksLeft = NBRICKS_PER_ROW * NBRICK_ROWS;
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
