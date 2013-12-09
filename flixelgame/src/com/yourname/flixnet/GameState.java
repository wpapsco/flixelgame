package com.yourname.flixnet;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import org.flixel.FlxBasic;
import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxObject;
import org.flixel.FlxPoint;
import org.flixel.FlxRect;
import org.flixel.FlxState;
import org.flixel.FlxTilemap;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader.Parameters;
import com.yourname.flixnet.characters.Npc;
import com.yourname.flixnet.characters.Player;
import com.yourname.flixnet.interfaces.Interactive;
import com.yourname.flixnet.mapping.PropertyMapLoader;
import com.yourname.flixnet.mapping.PropertyTiledMap;
import com.yourname.flixnet.mapping.objects.MapObject;

public abstract class GameState extends FlxState implements ControllerListener {

	private String mapImage;
	private String mapFile;
	private int tileWidth;
	private int tileHeight;
	
	protected FlxGroup npcs;
	protected FlxGroup players;
	protected ArrayList<Interactive> interactives;
	protected ArrayList<Controller> controllers;
	protected PropertyTiledMap map;
	protected Hashtable<String, FlxTilemap> layers;
	
	public GameState(String mapFile, String mapImage, int tileWidth, int tileHeight) {
		this.mapFile = mapFile;
		this.mapImage = mapImage;
		this.tileHeight = tileHeight;
		this.tileWidth = tileHeight;
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		npcs = new FlxGroup();
		players = new FlxGroup();
		interactives = new ArrayList<Interactive>();
		controllers = new ArrayList<Controller>();
		layers = new Hashtable<String, FlxTilemap>();
		
		Controllers.addListener(this);
		PropertyMapLoader loader = new PropertyMapLoader();
		Parameters args = new Parameters();
		args.yUp = false;
		map = loader.loadProperties(mapFile, args);
		Enumeration<MapObject> e = map.mapObjects.elements();
		while (e.hasMoreElements()) {
			this.add(e.nextElement());
		}
		for (int i = 0; i < map.map.getLayers().getCount(); i++) {
			FlxTilemap layer = new FlxTilemap();
			if (map.map.getLayers().get(i).getClass() == TiledMapTileLayer.class) {
				layer.loadMap(FlxTilemap.tiledmapToCSV(map.map, i), mapImage, tileWidth, tileHeight, FlxTilemap.OFF, 1);
				if (!map.map.getLayers().get(i).getProperties().get("collide").equals("true")) {
					layer.allowCollisions = FlxObject.NONE;
				}
				if (map.map.getLayers().get(i).getName().equals("Characters")) {
					add(players);
					add(npcs);
				}
				add(layer);
				layers.put(map.map.getLayers().get(i).getName(), layer);
				FlxG.worldBounds.make(0, 0, layer.widthInTiles * this.tileWidth, layer.heightInTiles * this.tileHeight);
			}
		}
	}

	
	public Npc add(Npc npc) {
		npcs.add(npc);
		return npc;
	}
	
	public Interactive add(Interactive interactive) {
		interactives.add(interactive);
		return interactive;
	}
	
	@Override
	public void connected(Controller controller) {
		// TODO Auto-generated method stub
		players.add(new Player(controller, Integer.parseInt(map.properties.get("StartX")), Integer.parseInt(map.properties.get("StartY")), this));
		controllers.add(controller);
	}

	@Override
	public void disconnected(Controller controller) {
		// TODO Auto-generated method stub
		((Player) players.members.get(controllers.indexOf(controller))).onDisconnect();
		players.members.removeIndex(controllers.indexOf(controller));
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		FlxG.collide(npcs, players);
		FlxG.collide(npcs);
		FlxG.collide(players);
		FlxTilemap[] values = new FlxTilemap[layers.values().size()];
		
		values = layers.values().toArray(values);
		
		for (int i = 0; i < values.length; i++) {
			FlxTilemap element = values[i];
			FlxG.collide(element, npcs);
			FlxG.collide(element, players);
		}
	}

	public void interact(Player player) {
		for (int i = 0; i < interactives.size(); i++) {
			Interactive in = interactives.get(i);
			if (in instanceof MapObject) {
				MapObject mo = (MapObject) in;
				if (mo.area.overlaps(new FlxRect(player.x + player.offset.x, player.y + player.offset.y, player.width, player.height))) {
					mo.onInteract(player);
				}
			}
		}
	}
}
