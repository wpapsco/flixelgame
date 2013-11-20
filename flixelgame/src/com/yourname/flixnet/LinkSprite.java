package com.yourname.flixnet;
import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxU;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.mappings.Ouya;

enum Direction {
	UP, DOWN, LEFT, RIGHT, IDLE
}

public class LinkSprite extends FlxSprite {
	Direction direction = Direction.IDLE;
	String facing;
	Controller controller;
	boolean[] pressedKeys = {false, false, false, false}; 

	public LinkSprite() {
		facing = "sDown";
		this.loadGraphic("mage2.png",true,true,8,13);
		
		this.addAnimation("sDown", new int[]{0}, 0);
		this.addAnimation("sUp", new int[]{4}, 0);
		this.addAnimation("sLeft", new int[]{12}, 0);
		this.addAnimation("sRight", new int[]{8}, 0);
		
		this.addAnimation("walkDown", new int[]{0,1,2,3}, 10);
		this.addAnimation("walkUp", new int[]{4,5,6,7}, 10);
		this.addAnimation("walkRight", new int[]{8,9,10,11}, 10);
		this.addAnimation("walkLeft", new int[]{12,13,14,15}, 10);
	}
	
	private void checkMovement() {
		if (controller == null) {
			if (FlxG.keys.UP) {
				direction = Direction.UP;
			} else if (FlxG.keys.DOWN) {
				direction = Direction.DOWN;
			} else if (FlxG.keys.LEFT) {
				direction = Direction.LEFT;
			} else if (FlxG.keys.RIGHT) {
				direction = Direction.RIGHT;
			} else {
				direction = Direction.IDLE;
			}
		}else{
			if (controller.getAxis(Ouya.AXIS_LEFT_Y) >= .5) {
				direction = Direction.UP;
			} else if (controller.getAxis(Ouya.AXIS_LEFT_Y) <= -.5) {
				direction = Direction.DOWN;
			} else if (controller.getAxis(Ouya.AXIS_LEFT_X) >= .5) {
				direction = Direction.LEFT;
			} else if (controller.getAxis(Ouya.AXIS_LEFT_X) <= -.5) {
				direction = Direction.RIGHT;
			} else {
				direction = Direction.IDLE;
			}
		}
	}
	
	private void doMovement() {
		super.update();
		float movementAmount = 80 * Gdx.graphics.getDeltaTime();
		switch(direction) {
		case UP:
			this.y -= movementAmount;
			this.play("walkUp");
			facing = "sUp";
			break;
		case DOWN:
			this.y += movementAmount;
			this.play("walkDown");
			facing = "sDown";
			break;
		case LEFT:
			this.x -= movementAmount;
			this.play("walkLeft");
			facing = "sLeft";
			break;
		case RIGHT:
			this.x += movementAmount;
			this.play("walkRight");
			facing = "sRight";
			break;
		case IDLE:
			this.play(facing);
			break;
		}
	}
	
	@Override
	public void update() {
		checkMovement();
		doMovement();
	}
}
