package com.yourname.flixelgame;

import org.flixel.FlxEmitter;
import org.flixel.FlxG;
import org.flixel.FlxParticle;
import org.flixel.FlxSprite;
import org.flixel.FlxU;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.mappings.Ouya;

public class MageSprite extends FlxSprite {
	
	private boolean attackStarted = false;
	private Controller controller;
	public FlxEmitter emitter;
	
	public MageSprite(Controller controller) {
		super();
		this.controller = controller;
		createEmitter();
		int speed = 5;
		this.loadGraphic("mage2.png", true, false, 8, 13);
		int[] frames = {4, 5, 6, 7};
		this.addAnimation("up", frames, speed, true);
		int[] frames2 = {0, 1, 2, 3};
		this.addAnimation("down", frames2, speed, true);
		int[] frames3 = {8, 9, 10, 11};
		this.addAnimation("right", frames3, speed, true);
		int[] frames4 = {12, 13, 14, 15};
		this.addAnimation("left", frames4, speed, true);
		int[] frames5 = {0};
		this.addAnimation("idle", frames5, speed, true);
		this.x = 0;
		this.y = 0;
	}
	
	public MageSprite() {
		this(null);
	}
	
	private void createEmitter() {
		emitter = new FlxEmitter(this.x, this.y, 400);
		for (int i = 0; i < 400; i++) {
			FlxParticle particle = (FlxParticle) new FlxParticle();
			particle.loadGraphic("face.png");
			particle.origin.make(2.5f, 2.5f);
			particle.exists = true;
			emitter.add(particle);
			emitter.setXSpeed(-500, 500);
			emitter.setYSpeed(-500, 500);
			emitter.bounce = .75f;
		}
	}
	
	private void doMovement() {
		float speed = 1.f;
		boolean particlesKey;
		if (controller != null) {
			particlesKey = controller.getButton(Ouya.BUTTON_O);
		} else {
			particlesKey = FlxG.keys.SPACE;
		}
		if (controller != null) {
			doControllerMovement(speed);
		} else {
			doKeyboardMovement(speed);
		}
		
		
		if (particlesKey) {
			if (!attackStarted) {
				attack();
				attackStarted = true;
			}
		} else {
			attackStarted = false;
		}
	}
	
	private void doKeyboardMovement(float speed) {
		boolean isMoving = false;
		if (FlxG.keys.DOWN) {
			this.play("down");
			this.y+=speed;
			isMoving = true;
		}
		if (FlxG.keys.UP) {
			this.play("up");
			this.y-=speed;
			isMoving = true;
		}
		if (FlxG.keys.LEFT) {
			if (!isMoving) {
				this.play("left");
			}
			this.x-=speed;
			isMoving = true;
		}
		if (FlxG.keys.RIGHT) {
			if (!isMoving) {
				this.play("right");
			}
			this.x+=speed;
			isMoving = true;
		} 
		if (!isMoving) {
			this.setFrame(this.getFrame());
		}
	}

	private void doControllerMovement(float speed) {
		boolean isMoving = false;
		if (controller.getAxis(Ouya.AXIS_LEFT_Y) <= -.5) {
			this.play("up");
			this.y-=speed;
			isMoving = true;
		}
		if (controller.getAxis(Ouya.AXIS_LEFT_Y) > .5) {
			this.play("down");
			this.y+=speed;
			isMoving = true;
		}
		if (controller.getAxis(Ouya.AXIS_LEFT_X) > .5) {
			if (!isMoving) {
				this.play("right");
			}
			this.x+=speed;
			isMoving = true;
		}
		if (controller.getAxis(Ouya.AXIS_LEFT_X) <= -.5) {
			if (!isMoving) {
				this.play("left");
			}
			this.x-=speed;
			isMoving = true;
		}
		if (!isMoving) {
			this.setFrame(this.getFrame());
		}
	}

	private void attack() {
		emitter.start(true, 2, 0, 40);
	}

	private void updateCamera() {
		FlxG.camera.follow(this);
		FlxG.cameras.get(1).follow(this);
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
		emitter.draw();
	}
	
	@Override
	public void update() {
		super.update();
		doMovement();
		updateCamera();
		emitter.update();
		emitter.x = this.getMidpoint().x;
		emitter.y = this.getMidpoint().y;
	}
}
