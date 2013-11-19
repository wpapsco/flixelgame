package com.yourname.flixnet;

import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxTilemap;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TmxMapLoader.Parameters;

public class PlayState extends FlxState {
	static final int TOP_Y = 480;
	static final int TOP_X = 800;

	private static String MAP = "map1.tmx";
	private static String ImgTiles = "layer1floortiles.png";
	private static String WallTiles = "layer2walls.png";

	public LinkSprite hero;
	// public EnemySprite jimbo;
	FlxTilemap floor;
	FlxTilemap walls;
	//LOLOLOLOL

	TiledMap map;
	FlxTilemap _level1;
	FlxTilemap _level1wall;
	float unitScale = 16 / 2f;

	@Override
	public void create() {
		// FlxG.worldBounds = new FlxRect(0,0,700,700);
		System.out.println("creates");
		map = new TiledMap();
		TmxMapLoader loader = new TmxMapLoader();
		Parameters args = new Parameters();
		args.yUp = false;
		
		map = loader.load(MAP, args);

		FlxG.setBgColor(0x00000000);
		FlxG.width = FlxG.camera.viewportWidth;

		
		FlxG.width = TOP_X;
		FlxG.height = TOP_Y;
		FlxG.camera.width = FlxG.width;
		FlxG.camera.height = FlxG.height;
		


		hero = new LinkSprite();

		hero.x = 16;
		hero.y = 16;
		// MapLayers layers = map.getLayers();
		_level1 = new FlxTilemap();
		_level1wall = new FlxTilemap();
		_level1.loadMap(FlxTilemap.tiledmapToCSV(map, "Ground"), ImgTiles, 16, 16,FlxTilemap.OFF, 1);
		_level1wall.loadMap(FlxTilemap.tiledmapToCSV(map, "Walls"), WallTiles, 16, 16,FlxTilemap.OFF, 8);
		
		add(_level1);
		add(_level1wall);
		add(hero);
		
	}

	public void destroy() {
		super.destroy();
		_level1 = null;
		map.dispose();

	}

	@Override
	public void update() {

		super.update();
		FlxG.collide(hero, _level1wall);
		FlxG.camera.scroll.x = hero.x-(TOP_X/4);
		FlxG.camera.scroll.y = hero.y-(TOP_Y/4);
		FlxG.camera.setBounds( FlxG.camera.scroll.x, FlxG.camera.scroll.y,FlxG.width,FlxG.height, true );
	}

}