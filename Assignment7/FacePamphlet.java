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

public class FacePamphlet extends Program 
					implements FacePamphletConstants {
	/*
	 * constructor
	 */
	private FacePamphlet() {
		canvas = new FacePamphletCanvas();
		add(canvas);
	}
	
	public static void main(String args[]) {
		new FacePamphlet().start();
	}
	
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
		//println("bob toString: " + bob.toString());
		
	}
	
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	if (e.getSource() == AddButton) {
    		handleAddCommand();
    	} else if (e.getSource() == DeleteButton) {
    		handleDeleteCommand();
    	} else if (e.getSource() == LookupButton) {
    		handleLookupCommand();
    	} else if (e.getSource() == StatusTextField
    			|| e.getSource() == ChangeStatusButton) {
    		handleChangeStatusCommand();	
    	} else if (e.getSource() == PictureTextField
    			|| e.getSource() == ChangePictureButton) {
    		handleChangePictureCommand();
    	} else if (e.getSource() == FriendTextField
    			|| e.getSource() == AddFriendButton) {
    		handleAddFriendCommand();
    	}
	}
    
    private void handleAddFriendCommand() {
    	String friendName = FriendTextField.getText();
    	if (FriendTextField.getText().equals(""))
    		return;
    	if (currentProfile != null) {
    		if (db.containsProfile(friendName)) {
    			addReciprocalRelationship(currentProfile, friendName);
    		} else {
    			canvas.showMessage(friendName + " does not exist.");
    			//println("Profile: " + friendName + "does not exist");
    		}
    		//printCurrentProfile();
    	} else {
    		canvas.showMessage("Please select a profile to add friend");
    		//println("Please select a profile to add a friend");
    		//printCurrentProfile();
    	}
    }
    
    /*
     * add Reciprocal Relationship between the person of profile and friend
     */
    private void addReciprocalRelationship(FacePamphletProfile profile, String friend) {
    	if (!profile.addFriend(friend)) {
    		//println(profile.getName() + " are " + friend + "are friends already");
    		canvas.showMessage(profile.getName() +
    				" already has " + friend + " as a friend.");
    		return;
    	} else {
    		db.getProfile(friend).addFriend(profile.getName());
    		canvas.displayProfile(profile);
    		canvas.showMessage(friend + " added as a friend");
    	}
    }
    
    private void handleChangePictureCommand() {
    	String filename = PictureTextField.getText(); 
    	if (filename.equals(""))
    		return;
		if (currentProfile != null) {
			updateProfileImage(currentProfile, filename);
			//println("Picture updated");
			//printCurrentProfile();
		} else {
			canvas.showMessage("Please select a profile to change picture");
			//printCurrentProfile();
		}
    	
    }
    
    private void printCurrentProfile() {
    	if (currentProfile != null) {
    		//println("--> Current profile: " + currentProfile.toString());
    	} else {
    		//println("--> No current profile");
    	}
    }
    
    private void updateProfileImage(FacePamphletProfile profile, String filename) {
    	GImage image = null;
    	try {
    		image = new GImage(filename);	
    	} catch (ErrorException ex) {
    		//println("cannot open file + " + filename);
    	}
    	if (image != null) {
    		profile.setImage(image);
    		canvas.displayProfile(profile);
    		canvas.showMessage("Picture updated");
    	} else {
    		canvas.showMessage("Unable to open image file: " + filename);
    		//println("failed to change image");
    	}
    	
    }
    
    private void handleChangeStatusCommand() {
    	String status = StatusTextField.getText(); 
    	if (status.equals(""))
			return;
    	if (currentProfile != null) {
    		currentProfile.setStatus(status);
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("Status updated to " + status);
    		//println("Change Status: " + StatusTextField.getText());
    		//printCurrentProfile();
    	} else {
    		canvas.showMessage("Please select a profile to change status");
    		//printCurrentProfile();
    	}
    	
    }
    
    private void handleLookupCommand() {
    	String name = NameTextField.getText();
    	if (name.equals(""))
    		return;
    	if (db.containsProfile(name)) {
    		currentProfile = db.getProfile(name);
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("Displaying " + name);
    		//println("Lookup: " + db.getProfile(name).toString());
    	} else {
    		currentProfile = null;
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("A profile with the name " + name + " does not exist");
    		//println("Lookup: profile with name " + name + " does not exist");
    	}
		
    }
    
    private void handleDeleteCommand() {
    	String name = NameTextField.getText();
    	if (name.equals(""))
    		return;
    	currentProfile = null;
    	if (db.containsProfile(name)) {
    		db.deleteProfile(name);
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("Profile of " + name + " deleted");
    		//println("Delete: profile of " + name + " deleted");
    	} else {
    		//println("Delete: profile with name " + name + " does not exist");
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("A profile with the name " + name + " does not exist");
    	}
		
    }
    
    private void handleAddCommand() {
    	String name = NameTextField.getText();
    	if (name.equals(""))
    		return;
    	if (db.containsProfile(name)) {
    		currentProfile = db.getProfile(name);
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("A profile with the name " + name + " already exists");
    		//println("Add: profile for " + name +
    		//		" already exists: " + db.getProfile(name).toString());
    		//printCurrentProfile();
    	} else {
    		FacePamphletProfile profile = new FacePamphletProfile(name);
    		currentProfile = profile;
    		db.addProfile(profile);
    		canvas.displayProfile(profile);
    		canvas.showMessage("New profile created");
    		//println("Add: new profile: " + profile.toString());
    		//printCurrentProfile();
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
    private FacePamphletProfile currentProfile = null;
    private FacePamphletCanvas canvas;
}
