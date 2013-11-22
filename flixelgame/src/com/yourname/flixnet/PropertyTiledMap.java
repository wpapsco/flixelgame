package com.yourname.flixnet;
import java.util.Hashtable;

import com.badlogic.gdx.maps.tiled.TiledMap;


public class PropertyTiledMap {
	public Hashtable<String, String> properties;
	public TiledMap map;
	
	public PropertyTiledMap(TiledMap map) {
		this.map = map;
	}
}
