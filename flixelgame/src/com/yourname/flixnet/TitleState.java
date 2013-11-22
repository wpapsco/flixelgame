package com.yourname.flixnet;
import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.event.IFlxButton;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.mappings.Ouya;


public class TitleState extends FlxState {
	Controller controller;

	public TitleState() {
		
	}

	@Override
	public void create() {
		FlxButton button = new FlxButton(100, 100, "Start", new IFlxButton() {
			@Override
			public void callback() {
				FlxG.switchState(new PlayState());
			}
		});
		
		add(button);
		if (controller != null) {
			if(controller.getButton(Ouya.BUTTON_O)){
				FlxG.switchState(new PlayState());
			}
		}
	}

}
