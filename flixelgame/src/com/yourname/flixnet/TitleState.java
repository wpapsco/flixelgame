package com.yourname.flixnet;
import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.event.IFlxButton;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.mappings.Ouya;


public class TitleState extends FlxState {
	Controller controller;
	LevelState game;

	public TitleState() {
		
	}

	@Override
	public void create() {
		game = new LevelState("groundTiles.png", "wallTiles.png", "innawoods.tmx");
		FlxButton button = new FlxButton(100, 100, "Start Level 1", new IFlxButton() {
			@Override
			public void callback() {
				FlxG.switchState(new PlayState());
			}
		});
		
		FlxButton button2 = new FlxButton(100, 130, "Start Level 2", new IFlxButton() {
			@Override
			public void callback() {
				FlxG.switchState(game);
			}
		});
		
		add(button);
		add(button2);
		if (controller != null) {
			if(controller.getButton(Ouya.BUTTON_O)){
				FlxG.switchState(new PlayState());
			}
		}
	}

}
