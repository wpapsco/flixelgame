package com.yourname.flixnet.items;

import org.flixel.FlxBasic;
import org.flixel.FlxObject;
import org.flixel.FlxSprite;

import com.yourname.flixnet.characters.Player;

public abstract class Item extends FlxBasic {

	private boolean enabled;
	private FlxSprite equipImage;
	
	public Item() {
		
	}

	public void onUse(Player player) {
		
	}
	
	
}
