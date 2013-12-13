package com.yourname.flixnet.mapping;
import java.util.ArrayList;
import java.util.Hashtable;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.yourname.flixnet.interfaces.Interactive;


public class CustomTiledMap {
	public Hashtable<String, String> properties;
	public Hashtable<String, MapObject> mapObjects;
	public TiledMap map;
	
	public CustomTiledMap(TiledMap map) {
		this.map = map;
		mapObjects = new Hashtable<String, MapObject>();
	}
}
