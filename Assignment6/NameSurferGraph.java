/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
		entryList = new ArrayList<NameSurferEntry>();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//	 You fill this in //
		entryList.clear();
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		entryList.add(entry);
		update();
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		//	 You fill this in //
		removeAll();
		drawGrid();
		drawEntryLines();
	}
	
	private void drawEntryLines() {
		int count = 0;
		
		for (NameSurferEntry entry: entryList) {
			Color color = chooseColor(count);
			drawOneEntryLine(entry, color);
			count++;
		}
	}
	
	private Color chooseColor(int count) {
		switch(count % 5) {
		case 0:
			return Color.BLACK;
		case 1:
			return Color.RED;
		case 2:
			return Color.BLUE;
		case 3:
			return Color.MAGENTA;
		case 4:
			return Color.GREEN;
		}
		
		return Color.BLACK;
	}
	
	private void drawOneEntryLine(NameSurferEntry entry, Color color) {
		double x0 = 0;
		double y0 = 0;
		double x1 = 0;
		double y1 = 0;
		double canvasWidth = getWidth();
		double canvasHeight = getHeight();
		double spacing = canvasWidth / NDECADES;
		String name = entry.getName();
		GLabel label;
		
		x1 = 0;
		for (int i = 0; i < NDECADES; i++) {
			y1 = (double)entry.getRank(i) / 1000 * (canvasHeight - 2 * GRAPH_MARGIN_SIZE) + GRAPH_MARGIN_SIZE;
			if (entry.getRank(i) == 0) {
				y1 = canvasHeight - GRAPH_MARGIN_SIZE;
				label = new GLabel("  " + name + "*");
				
			} else {
				label = new GLabel("  " + name + entry.getRank(i));
			}
			label.setColor(color);
			add(label, x1, y1 - label.getDescent());
			if (i != 0) {
				GLine line = new GLine(x0, y0, x1, y1);
				line.setColor(color);
				add(line);
			}
			x0 = x1;
			y0 = y1;
			x1 += spacing;
		}
	}
	
	private void drawGrid() {
		drawHorizontalLines();
		drawVerticalLines();
		drawLabels();		
	}
	
	private void drawLabels() {
		int year = START_DECADE;
		double x, y;
		double canvasWidth = getWidth();
		double canvasHeight = getHeight();
		double spacing = canvasWidth / NDECADES;

		x = 0;
		for (int i = 0; i < NDECADES; i++) {
			GLabel label = new GLabel(" " + year); 
			y = canvasHeight - GRAPH_MARGIN_SIZE + (GRAPH_MARGIN_SIZE - label.getHeight()) / 2 + label.getAscent();
			add(label, x, y);
			x += spacing;
			year += 10;
		}
	}
	
	private void drawVerticalLines() {
		double x0, y0, x1, y1;
		double canvasWidth = getWidth();
		double canvasHeight = getHeight();
		double spacing = canvasWidth / NDECADES;
		
		x0 = 0;
		y0 = 0;
		x1 = 0;
		y1 = canvasHeight;
		for (int i = 0; i < NDECADES; i++) {
			add(new GLine(x0, y0, x1, y1));
			x0 += spacing;
			x1 += spacing;
		}
	}
	
	private void drawHorizontalLines() {
		double x0, y0, x1, y1;
		double canvasWidth = getWidth();
		double canvasHeight = getHeight();
		
		x0 = 0;
		y0 = GRAPH_MARGIN_SIZE;
		x1 = canvasWidth;
		y1 = GRAPH_MARGIN_SIZE;
		add(new GLine(x0, y0, x1, y1));
		
		x0 = 0;
		y0 = canvasHeight - GRAPH_MARGIN_SIZE;
		x1 = canvasWidth;
		y1 = canvasHeight - GRAPH_MARGIN_SIZE;
		add(new GLine(x0, y0, x1, y1));
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	/*
	 * ivars
	 */
	private ArrayList<NameSurferEntry> entryList;
}
