package com.yourname.flixnet.characters;

import org.flixel.FlxSprite;

import com.yourname.flixnet.Direction;

public abstract class Character extends FlxSprite {
	private Direction direction;
	private int[] idleFrames;
	
	public Character(String image, int width, int height, 
			int[] upFrames,    int idleFrameUp,    int upRate, 
			int[] downFrames,  int idleFrameDown,  int downRate, 
			int[] leftFrames,  int idleFrameLeft,  int leftRate, 
			int[] rightFrames, int idleFrameRight, int rightRate) {
		super();
		
		this.loadGraphic(image, true, false, width, height);
		this.addAnimation("UP", upFrames, upRate, true);
		this.addAnimation("DOWN", downFrames, downRate, true);
		this.addAnimation("LEFT", leftFrames, leftRate, true);
		this.addAnimation("RIGHT", rightFrames, rightRate, true);
		setDirection(Direction.DOWN);
		idleFrames = new int[]{idleFrameUp, idleFrameDown, idleFrameLeft, idleFrameRight};
	}
	
	public Character(String image, int width, int height, int frameRate, 
			int[] upFrames,    int idleFrameUp, 
			int[] downFrames,  int idleFrameDown, 
			int[] leftFrames,  int idleFrameLeft,
			int[] rightFrames, int idleFrameRight) {
		this(image, width, height, 
				upFrames, idleFrameUp, frameRate, 
				downFrames, idleFrameDown, frameRate, 
				leftFrames, idleFrameLeft, frameRate, 
				rightFrames, idleFrameRight, frameRate);
		
	}
	
	public Character(String image, int width, int height, int frameRate, 
			int[] upFrames, int[] downFrames, int[] leftFrames, int[] rightFrames) {
		this(image, width, height, frameRate,
				upFrames,    -1, 
				downFrames,  -1, 
				leftFrames,  -1, 
				rightFrames, -1);
		
	}

	public void idle() {
		int frame = 0;
		switch(direction) {
		case UP:
			frame = idleFrames[0];
			break;
		case DOWN:
			frame = idleFrames[1];
			break;
		case LEFT:
			frame = idleFrames[2];
			break;
		case RIGHT:
			frame = idleFrames[3];
			break;
		}
		if (frame == -1) {
			this.setFrame(this.getFrame());
		} else {
			this.setFrame(frame);
		}
	}
	
	protected void setDirection(Direction direction) {
		this.direction = direction;
		this.play(direction.toString());
	}
	
	public Direction getDirection() {
		return direction;
	}
}
