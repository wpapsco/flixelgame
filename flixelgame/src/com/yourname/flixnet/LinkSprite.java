package com.yourname.flixnet;
import org.flixel.FlxG;
import org.flixel.FlxSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.mappings.Ouya;


public class LinkSprite extends FlxSprite {
	String facing;
	Controller controller;

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

	public LinkSprite(float X) {
		super(X);
		// TODO Auto-generated constructor stub
	}

	public LinkSprite(float X, float Y) {
		super(X, Y);
		// TODO Auto-generated constructor stub
	}

	public LinkSprite(float X, float Y, String SimpleGraphic) {
		super(X, Y, SimpleGraphic);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update() {
		if(controller == null){
			if(FlxG.keys.UP){
				this.y -= 80 * Gdx.graphics.getDeltaTime();
				this.play("walkUp");
				facing = "sUp";
			}
			else if(FlxG.keys.DOWN){
				this.y += 80 * Gdx.graphics.getDeltaTime();
				this.play("walkDown");
				facing = "sDown";
			}
			else if(FlxG.keys.LEFT){
				this.x -= 80 * Gdx.graphics.getDeltaTime();
				this.play("walkLeft");
				facing = "sLeft";
			}
			else if(FlxG.keys.RIGHT){
				this.x += 80 * Gdx.graphics.getDeltaTime();
				this.play("walkRight");
				facing = "sRight";
			}else{
				this.play(facing);
			}
		}else{
			if(controller.getAxis(Ouya.AXIS_LEFT_Y) == 1){
				this.y -= 80 * Gdx.graphics.getDeltaTime();
				this.play("walkUp");
				facing = "sUp";
			}
			else if(controller.getAxis(Ouya.AXIS_LEFT_Y) == -1){
				this.y += 80 * Gdx.graphics.getDeltaTime();
				this.play("walkDown");
				facing = "sDown";
			}
			else if(controller.getAxis(Ouya.AXIS_LEFT_X) == 1){
				this.x -= 80 * Gdx.graphics.getDeltaTime();
				this.play("walkLeft");
				facing = "sLeft";
			}
			else if(controller.getAxis(Ouya.AXIS_LEFT_X) == -1){
				this.x += 80 * Gdx.graphics.getDeltaTime();
				this.play("walkRight");
				facing = "sRight";
			}else{
				this.play(facing);
			}
		}
		super.update();
	}

}
