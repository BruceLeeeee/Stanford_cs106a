/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends ConsoleProgram 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// You fill this in
		//northern region
		NameTextField = new JTextField(TEXT_FIELD_SIZE);
		AddButton = new JButton("Add");
		DeleteButton = new JButton("Delete");
		LookupButton = new JButton("Lookup");
		add(new JLabel("Name"), NORTH);
		add(NameTextField, NORTH);
		add(AddButton, NORTH);
		add(DeleteButton, NORTH);
		add(LookupButton, NORTH);
		
		//western region
		StatusTextField = new JTextField(TEXT_FIELD_SIZE);
		ChangeStatusButton = new JButton("Change Status");
		PictureTextField = new JTextField(TEXT_FIELD_SIZE);
		ChangePictureButton = new JButton("Change Picture");
		FriendTextField = new JTextField(TEXT_FIELD_SIZE);
		AddFriendButton = new JButton("Add Friend");
		add(StatusTextField, WEST);
		add(ChangeStatusButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(PictureTextField, WEST);
		add(ChangePictureButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(FriendTextField, WEST);
		add(AddFriendButton, WEST);
		
		addActionListeners();
		StatusTextField.addActionListener(this);
		PictureTextField.addActionListener(this);
		FriendTextField.addActionListener(this);
		
		db = new FacePamphletDatabase();
		//testFacePamphletProfile();
    }
    
	private void testFacePamphletProfile() {
		FacePamphletProfile bob = new FacePamphletProfile("bob");
		bob.setStatus("coding like a fiend!");
		bob.addFriend("Mary");
		bob.addFriend("Lucy");
		bob.addFriend("Agnes");
		println("bob toString: " + bob.toString());
		
	}
	
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	if (e.getSource() == AddButton) {
    		dealWithAddCommand();
    	} else if (e.getSource() == DeleteButton) {
    		dealWithDeleteCommand();
    	} else if (e.getSource() == LookupButton) {
    		dealWithLookupCommand();
    	} else if (e.getSource() == StatusTextField
    			|| e.getSource() == ChangeStatusButton) {
    		if (!StatusTextField.getText().equals(""))
    			println("Change Status: " + StatusTextField.getText());
    		
    	} else if (e.getSource() == PictureTextField
    			|| e.getSource() == ChangePictureButton) {
    		if (!PictureTextField.getText().equals(""))
    			println("Change Picture: " + PictureTextField.getText());
    		
    	} else if (e.getSource() == FriendTextField
    			|| e.getSource() == AddFriendButton) {
    		if (!FriendTextField.getText().equals(""))
    			println("Add Friend: " + FriendTextField.getText());
    	}
    	
	}
    
    private void dealWithLookupCommand() {
    	String name = NameTextField.getText();
    	if (name.equals(""))
    		return;
    	if (db.containsProfile(name)) {
    		println("Lookup: " + db.getProfile(name).toString());
    	} else {
    		println("Lookup: profile with name " + name + " does not exist");
    	}
		
    }
    
    private void dealWithDeleteCommand() {
    	String name = NameTextField.getText();
    	if (name.equals(""))
    		return;
    	if (db.containsProfile(name)) {
    		db.deleteProfile(name);
    		println("Delete: profile of " + name + " deleted");
    	} else {
    		println("Delete: profile with name " + name + " does not exist");
    	}
		
    }
    
    private void dealWithAddCommand() {
    	String name = NameTextField.getText();
    	if (name.equals(""))
    		return;
    	if (db.containsProfile(name)) {
    		println("Add: profile for " + name +
    				" already exists: " + db.getProfile(name).toString());
    	} else {
    		FacePamphletProfile profile = new FacePamphletProfile(name);
    		db.addProfile(profile);
    		println("Add: new profile: " + profile.toString());
    	}
    }
    
    /*
     * ivars
     */
    private JTextField NameTextField;
    private JTextField StatusTextField;
    private JTextField PictureTextField;
    private JTextField FriendTextField;
    private JButton AddButton;
    private JButton DeleteButton;
    private JButton LookupButton;
    private JButton ChangeStatusButton;
    private JButton ChangePictureButton;
    private JButton AddFriendButton;
    private FacePamphletDatabase db;
}
