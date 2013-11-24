package com.yourname.flixnet;

import org.flixel.FlxPath;
import org.flixel.FlxPoint;
import org.flixel.FlxSprite;
import org.flixel.FlxTilemap;

import com.badlogic.gdx.Gdx;

public class EnemySprite extends FlxSprite {
	String facing = "sDown";

	public EnemySprite() {
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
	
	@Override
	public void update() {
		
	}
	
	public void hunt(FlxSprite hero){
		float speed = 60 * Gdx.graphics.getDeltaTime();
		if (hero.y+10 < this.y) {
			this.y -= speed;
			this.play("walkUp");
			facing = "sUp";
		}
		else if (hero.y-10 > this.y) {
			this.y += speed;
			this.play("walkDown");
			facing = "sDown";
		}
		if (hero.x+10 < this.x) {
			this.x -= speed;
			this.play("walkLeft");
			facing = "sLeft";
		}
		else if (hero.x-10 > this.x) {
			this.x += speed;
			this.play("walkRight");
			facing = "sRight";
		}else{
			this.play(facing);
		}
	}
}
