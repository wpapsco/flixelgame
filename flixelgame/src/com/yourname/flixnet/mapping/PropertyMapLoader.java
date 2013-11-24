package com.yourname.flixnet.mapping;

import java.util.Hashtable;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class PropertyMapLoader extends TmxMapLoader {

	public PropertyTiledMap loadProperties(String fileName, Parameters parameters) {
		TiledMap map = this.load(fileName, parameters);
		PropertyTiledMap pMap = new PropertyTiledMap(map);
		pMap.properties = new Hashtable<String, String>();
		if (root.getChildByName("properties") != null) {
			for (int i = 0; i < root.getChildByName("properties").getChildCount(); i++) {
				Element child = root.getChildByName("properties").getChild(i);
				pMap.properties.put(child.getAttribute("name"), child.getAttribute("value"));
			}
		}
		return pMap;
	}
}