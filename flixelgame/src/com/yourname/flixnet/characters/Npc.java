package com.yourname.flixnet.characters;

import com.yourname.flixnet.interfaces.Interactive;

public class Npc extends Character implements Interactive {

	public Npc(String image, int width, int height, 
			int[] upFrames,    int idleFrameUp,    int upRate, 
			int[] downFrames,  int idleFrameDown,  int downRate, 
			int[] leftFrames,  int idleFrameLeft,  int leftRate,
			int[] rightFrames, int idleFrameRight, int rightRate) {
		super(image, width, height,
				upFrames,    idleFrameUp,    upRate, 
				downFrames,  idleFrameDown,  downRate, 
				leftFrames,  idleFrameLeft,  leftRate,
				rightFrames, idleFrameRight, rightRate);
		// TODO Auto-generated constructor stub
	}

	public Npc(String image, int width, int height, int frameRate,
			int[] upFrames, int idleFrameUp, 
			int[] downFrames, int idleFrameDown, 
			int[] leftFrames, int idleFrameLeft,
			int[] rightFrames, int idleFrameRight) {
		super(image, width, height, frameRate,
				upFrames, idleFrameUp,
				downFrames, idleFrameDown,
				leftFrames, idleFrameLeft,
				rightFrames, idleFrameRight);
		// TODO Auto-generated constructor stub
	}

	public Npc(String image, int width, int height, int frameRate,
			int[] upFrames, int[] downFrames, int[] leftFrames, int[] rightFrames) {
		super(image, width, height, frameRate, 
				upFrames, downFrames, leftFrames, rightFrames);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onInteract(Player player) {
		// TODO Auto-generated method stub
	}

}
