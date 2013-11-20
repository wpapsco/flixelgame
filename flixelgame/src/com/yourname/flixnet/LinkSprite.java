package com.yourname.flixnet;
import org.flixel.FlxEmitter;
import org.flixel.FlxG;
import org.flixel.FlxParticle;
import org.flixel.FlxSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.mappings.Ouya;

enum Direction {
	UP, DOWN, LEFT, RIGHT
}

public class LinkSprite extends FlxSprite {
	private boolean isIdle = true;
	private Direction direction = Direction.DOWN;
	private String facing;
	private Controller controller;
	public FlxEmitter emitter;

	public LinkSprite() {
		this.loadGraphic("mage2.png",true,true,8,13);
		setupAnimations();
		setupEmitter();
	}
	
	private void setupEmitter() {
		emitter = new FlxEmitter(0, 0, 10);
		emitter.bounce = 1;
		for (int i = 0; i < 10; i++) {
			FlxParticle p = new FlxParticle();
			p.makeGraphic(2, 2, 0xffff0000); //aarrggbb format
			emitter.add(p);
		}
	}

	private void setupAnimations() {
		facing = "sDown";
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
		isIdle = false;
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
				isIdle = true;
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
				isIdle = true;
			}
		}
	}
	
	private void doMovement() {
		super.update();
		float speed = 80 * Gdx.graphics.getDeltaTime();
		if (!isIdle) {
			switch(direction) {
			case UP:
				this.y -= speed;
				this.play("walkUp");
				facing = "sUp";
				break;
			case DOWN:
				this.y += speed;
				this.play("walkDown");
				facing = "sDown";
				break;
			case LEFT:
				this.x -= speed;
				this.play("walkLeft");
				facing = "sLeft";
				break;
			case RIGHT:
				this.x += speed;
				this.play("walkRight");
				facing = "sRight";
				break;
			}
		} else {
			this.play(facing);
		}
	}
	
	private void doAttack() {
		if (FlxG.keys.justPressed("SPACE") ) {
			updateEmitter();
			emitter.emitParticle();
		}
	}
	
	@Override
	public void update() {
		checkMovement();
		doMovement();
		doAttack();
		emitter.update();
	}
	
	private void updateEmitter() {
		emitter.x = this.x + (this.width / 2);
		emitter.y = this.y + (this.height / 2);
		float speed = 100f;
		switch (direction) {
		case UP:
			emitter.setXSpeed(0);
			emitter.setYSpeed(-speed, -speed);
			break;
		case DOWN:
			emitter.setXSpeed(0);
			emitter.setYSpeed(speed, speed);
			break;
		case LEFT:
			emitter.setXSpeed(-speed, -speed);
			emitter.setYSpeed(0);
			break;
		case RIGHT:
			emitter.setXSpeed(speed, speed);
			emitter.setYSpeed(0);
			break;
		}
	}

	@Override
	public void draw() {
		emitter.draw();
		super.draw();
	}
}
