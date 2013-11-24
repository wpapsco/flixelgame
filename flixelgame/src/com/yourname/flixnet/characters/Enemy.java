package com.yourname.flixnet.characters;
import org.flixel.FlxBasic;
import org.flixel.FlxPath;
import org.flixel.FlxPoint;

import com.badlogic.gdx.utils.Array;
import com.yourname.flixnet.interfaces.Attacker;


public abstract class Enemy extends Npc implements Attacker {

	public Enemy(String image, int width, int height, 
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

	public Enemy(String image, int width, int height, int frameRate,
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

	public Enemy(String image, int width, int height, int frameRate,
			int[] upFrames, int[] downFrames, int[] leftFrames, int[] rightFrames) {
		super(image, width, height, frameRate, 
				upFrames, downFrames, leftFrames, rightFrames);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean attack(FlxBasic target) {
		return false;
	}

	public void goTo(FlxPoint location, float speed) {
		this.followPath(new FlxPath(new Array<FlxPoint>(new FlxPoint[]{new FlxPoint(this.x, this.y), location})), speed);
	}
	
	public void goTo(FlxPoint location) {
		goTo(location, 1);
	}
	
	public abstract void onSeePlayer();
	public abstract void onLostPlayer();
}
