/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
		initializeBlankImage();
		appMsg = new GLabel("");
		add(appMsg);
	}

	private void initializeBlankImage() {
		double x, y;
		
		blankImage = new GCompound();
		GLabel label = new GLabel("No Image");
		label.setFont(PROFILE_IMAGE_FONT);
		blankImage.add(new GRect(IMAGE_WIDTH, IMAGE_HEIGHT), 0, 0);
		x = (IMAGE_WIDTH - label.getWidth()) / 2;
		y = (IMAGE_HEIGHT - label.getAscent()) / 2 + label.getAscent();
		blankImage.add(label, x, y);
	}
	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in
		double x, y;
		
		appMsg.setLabel(msg);
		x = (getWidth() - appMsg.getWidth()) / 2;
		y = getHeight() - BOTTOM_MESSAGE_MARGIN;
		appMsg.setFont(MESSAGE_FONT);
		add(appMsg, x, y);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		// You fill this in
		removeAll();
		if (profile != null) {
			displayName(profile);
			displayImage(profile);
			displayStatus(profile);
			displayFriends(profile);
		}
	}
	
	private void displayFriends(FacePamphletProfile profile) {
		double x, y;
		double spacing;
		GLabel headerText = new GLabel("Friends:");
		
		x = getWidth() / 2;
		y = blankImage.getY();
		headerText.setFont(PROFILE_FRIEND_LABEL_FONT);
		spacing = headerText.getAscent();
		add(headerText, x, y);
		y += spacing;
		Iterator it = profile.getFriends();
		if (it != null) {
			while (it.hasNext()) {
				String friendName = (String)it.next();
				GLabel friendLabel = new GLabel(friendName);
				friendLabel.setFont(PROFILE_FRIEND_FONT);
				add(friendLabel, x, y);
				y += spacing;
			}
		}
	}
	
	private void displayStatus(FacePamphletProfile profile) {
		String status = profile.getStatus();
		String statusMessage = "";
		
		if (status.equals("")) {
			statusMessage += "No current status";
		} else {
			statusMessage += profile.getName() + " is " + status;
		}
		this.status = new GLabel(statusMessage);
		this.status.setFont(PROFILE_STATUS_FONT);
		add(this.status, LEFT_MARGIN, blankImage.getY() + blankImage.getHeight() + STATUS_MARGIN);
	}
	
	private void displayImage(FacePamphletProfile profile) {
		image = profile.getImage();
		if (image == null) {
			add(blankImage, LEFT_MARGIN, name.getY() + IMAGE_MARGIN);
		} else {
			image.scale(IMAGE_WIDTH / image.getWidth(), IMAGE_HEIGHT / image.getHeight());
			add(image, LEFT_MARGIN, name.getX() + name.getAscent() + IMAGE_MARGIN);
		}
	}
	
	private void displayName(FacePamphletProfile profile) {
		double x, y;
		
		name = new GLabel(profile.getName());
		name.setColor(Color.BLUE);
		name.setFont(PROFILE_NAME_FONT);
		x = LEFT_MARGIN;
		y = TOP_MARGIN + name.getAscent();
		add(name, x, y);
	}
	
	/*
	 * ivars
	 */
	private GLabel name;
	private GImage image;
	private GCompound blankImage;
	private GLabel status;
	private GLabel appMsg;
}
