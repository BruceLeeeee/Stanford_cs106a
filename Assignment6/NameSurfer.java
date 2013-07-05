/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends ConsoleProgram implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		installInteractors();
		db = new NameSurferDataBase(NAMES_DATA_FILE);
	
		//testNameSurferEntry();		
	}
	
	private void testNameSurferEntry() {
		NameSurferEntry entry = new NameSurferEntry("Sam 58 69 99 131 168 236 278 380 467 408 466");
		println(entry.getName());
		for (int i = 0; i < NDECADES; i++)
			print(entry.getRank(i) + " ");
		println("");
	}
	
	private void installInteractors() {
		JLabel label = new JLabel("Name");
		nameField = new JTextField(20);
		GraphButton = new JButton("Graph");
		ClearButton = new JButton("Clear");
		
		add(label, SOUTH);
		add(nameField, SOUTH);
		add(GraphButton, SOUTH);
		add(ClearButton, SOUTH);
		addActionListeners();
		nameField.addActionListener(this);
	}
	
/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		if (e.getSource() == nameField || e.getSource() == GraphButton) {
			String objectInfo = db.findEntry(nameField.getText()).toString();
			println("Graph: " + objectInfo);
		} else if (e.getSource() == ClearButton) {
			println("Clear");
		}
	}
	
	/*
	 * instance variables
	 */
	private JTextField nameField;
	private JButton GraphButton;
	private JButton ClearButton;
	private NameSurferDataBase db;
}
