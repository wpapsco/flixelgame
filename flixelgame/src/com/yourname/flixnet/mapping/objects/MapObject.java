package com.yourname.flixnet.mapping.objects;

import java.util.Hashtable;

import org.flixel.FlxRect;

import com.yourname.flixnet.GameState;
import com.yourname.flixnet.characters.Player;
import com.yourname.flixnet.interfaces.Interactive;
import com.yourname.flixnet.mapping.PropertyTiledMap;

public class MapObject implements Interactive {

	private Hashtable<String, String> properties;
	public FlxRect area;
	public boolean enabled;
	private PropertyTiledMap map;
	
	public MapObject(Hashtable<String, String> properties, FlxRect area, PropertyTiledMap map) {
		this.properties = properties;
		this.area = area;
		this.map = map;
		if (properties.get("start_enabled") != null) {
			enabled = Boolean.parseBoolean(properties.get("start_enabled"));
		} else {
			enabled = true;
		}
	}

	@Override
	public void onInteract(Player player) {
		//do things with the properties here
		if (enabled) {
			if (properties.get("display_text") != null) {
				player.displayText(properties.get("display_text"));
			}
			if (properties.get("add_coin") != null) {
				player.addCoin(Integer.parseInt(properties.get("add_coin")));
			}
			if (properties.get("enable_object") != null) {
				map.mapObjects.get(properties.get("enable_object")).enabled = true;
			}
			if (properties.get("disable_object") != null) {
				map.mapObjects.get(properties.get("disable_object")).enabled = false;
			}
		}
	}
}
