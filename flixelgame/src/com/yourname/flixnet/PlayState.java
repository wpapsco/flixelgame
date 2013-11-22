package com.yourname.flixnet;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxTilemap;
import org.flixel.FlxU;
import org.flixel.event.IFlxButton;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TmxMapLoader.Parameters;

public class PlayState extends FlxState {
	//this is a comment from will
	static final int TOP_Y = 480;
	static final int TOP_X = 800;
	
	
	private static String ImgTiles = "layer1floortiles.png";
	private static String WallTiles = "layer2walls.png";
	private static String MAP = "map1.tmx";

	public LinkSprite hero;
	public EnemySprite jimbo;
	FlxTilemap floor;
	FlxTilemap walls;

	PropertyTiledMap map;
	FlxTilemap _level1;
	FlxTilemap _level1wall;
	float unitScale = 16 / 2f;

	@Override
	public void create() {
		PropertyMapLoader loader = new PropertyMapLoader();
		Parameters args = new Parameters();
		args.yUp = false;
		
		map = loader.loadProperties(MAP, args);
		System.out.println(map.properties.get("StartX"));

		FlxG.setBgColor(0x00000000);
		FlxG.width = FlxG.camera.viewportWidth;
		
		FlxG.width = TOP_X;
		FlxG.height = TOP_Y;
		FlxG.camera.width = FlxG.width;
		FlxG.camera.height = FlxG.height;
		


		hero = new LinkSprite();

		hero.x = 64;
		hero.y = 64;
		// MapLayers layers = map.getLayers();
		_level1 = new FlxTilemap();
		_level1wall = new FlxTilemap();
		_level1.loadMap(FlxTilemap.tiledmapToCSV(map.map, "Ground"), ImgTiles, 16, 16,FlxTilemap.OFF, 1);
		_level1wall.loadMap(FlxTilemap.tiledmapToCSV(map.map, "Walls"), WallTiles, 16, 16,FlxTilemap.OFF, 8);
		jimbo = new EnemySprite();
		jimbo.x = 64*2;
		jimbo.y = 64*2;
		
		add(_level1);
		add(_level1wall);
		add(hero);
		add(jimbo);
	}

	public void destroy() {
		super.destroy();
		_level1 = null;
		map.map.dispose();
	}

	@Override
	public void update() {
		super.update();
		jimbo.hunt(hero);
		FlxG.collide(hero, _level1wall);
		FlxG.collide(jimbo, _level1wall);
		FlxG.collide(hero.emitter, _level1wall);
		FlxG.camera.scroll.x = hero.x-(TOP_X/4);
		FlxG.camera.scroll.y = hero.y-(TOP_Y/4);
		FlxG.camera.setBounds( FlxG.camera.scroll.x, FlxG.camera.scroll.y,FlxG.width,FlxG.height, true );
	}

}