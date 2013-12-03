package com.yourname.flixnet.mapping;

import java.util.Hashtable;

import org.flixel.FlxRect;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.yourname.flixnet.mapping.objects.MapObject;

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
		
		Array<Element> objectGroups = root.getChildrenByName("objectgroup");
		for (Element group : objectGroups) {
			for (Element object : group.getChildrenByName("object")) {
				int x = Integer.parseInt(object.getAttribute("x"));
				int y = Integer.parseInt(object.getAttribute("y"));
				int width = Integer.parseInt(object.getAttribute("width"));
				int height = Integer.parseInt(object.getAttribute("height"));
				FlxRect rect = new FlxRect(x, y, width, height);
				
				Hashtable<String, String> properties = new Hashtable<String, String>();
				Element propertiesElement = object.getChildByName("properties");
				for (Element property : propertiesElement.getChildrenByName("property")) {
					properties.put(property.getAttribute("name"), property.getAttribute("value", ""));
				}
				pMap.mapObjects.add(new MapObject(properties, rect));
			}
		}
		return pMap;
	}
}