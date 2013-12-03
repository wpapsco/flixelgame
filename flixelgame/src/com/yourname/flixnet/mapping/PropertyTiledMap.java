package com.yourname.flixnet.mapping;
import java.util.ArrayList;
import java.util.Hashtable;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.yourname.flixnet.interfaces.Interactive;
import com.yourname.flixnet.mapping.objects.MapObject;


public class PropertyTiledMap {
	public Hashtable<String, String> properties;
	public ArrayList<MapObject> mapObjects;
	public TiledMap map;
	
	public PropertyTiledMap(TiledMap map) {
		this.map = map;
		mapObjects = new ArrayList<MapObject>();
	}
}
