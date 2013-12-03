package com.yourname.flixnet.mapping.objects;

import java.util.Hashtable;

import org.flixel.FlxRect;

import com.yourname.flixnet.characters.Player;
import com.yourname.flixnet.interfaces.Interactive;

public class MapObject implements Interactive {

	private Hashtable<String, String> properties;
	public FlxRect area;
	
	public MapObject(Hashtable<String, String> properties, FlxRect area) {
		this.properties = properties;
		this.area = area;
	}

	@Override
	public void onInteract(Player player) {
		//do things with the properties here
		if (properties.get("display_text") != null) {
			player.displayText(properties.get("display_text"));
		}
	}
}
