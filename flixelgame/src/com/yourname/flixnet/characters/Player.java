package com.yourname.flixnet.characters;

import org.flixel.FlxBasic;
import org.flixel.FlxG;
import org.flixel.FlxText;
import org.flixel.FlxTimer;
import org.flixel.event.IFlxTimer;

import com.badlogic.gdx.controllers.Controller;
import com.yourname.flixnet.Direction;
import com.yourname.flixnet.GameState;
import com.yourname.flixnet.interfaces.Attacker;

public class Player extends Character implements Attacker {

	private Controller controller;
	private GameState context;
	private FlxText txt;
	public boolean canInteract = true;
	private FlxTimer timer;
	
	public Player(Controller controller, int x, int y, GameState context) {
		super("mage2.png", 8, 13, 10, new int[]{4,5,6,7}, new int[]{0,1,2,3}, new int[]{12,13,14,15}, new int[]{8,9,10,11});
		this.context = context;
		this.x = x;
		this.y = y;
		this.height = 8;
		this.offset.y = 8;
		this.controller = controller;
	}
	
	@Override
	public boolean attack(FlxBasic target) {
		return false;
	}
	
	@Override
	public void update() {
		super.update();
		FlxG.camera.follow(this);
		float speed = 1;
		if (FlxG.keys.LEFT) {
			this.setDirection(Direction.LEFT);
			this.x-=speed;
		}
		if (FlxG.keys.RIGHT) {
			this.setDirection(Direction.RIGHT);
			this.x+=speed;
		}
		if (FlxG.keys.UP) {
			this.setDirection(Direction.UP);
			this.y-=speed;
		}
		if (FlxG.keys.DOWN) {
			this.setDirection(Direction.DOWN);
			this.y+=speed;
		}
		if (FlxG.keys.justPressed("SPACE")) {
			if (canInteract) {
				context.interact(this);
			}
		}
		
		if (!(FlxG.keys.LEFT || FlxG.keys.RIGHT || FlxG.keys.UP || FlxG.keys.DOWN)) {
			idle();
		}
	}
	
	public void onDisconnect() {
		
	}

	public void displayText(String string) {
		canInteract = false;
		timer = new FlxTimer();
		txt = new FlxText(0, 0, FlxG.width, string);
		txt.setAlignment("center");
		txt.scrollFactor.make(0, 0);
		context.add(txt);
		timer.start(1.5f, 0, new IFlxTimer() {
			@Override
			public void callback(FlxTimer Timer) {
				// TODO Auto-generated method stub
				Timer.destroy();
				context.remove(txt);
				canInteract = true;
			}
		});
	}
}
