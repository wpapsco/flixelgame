package com.yourname.flixnet;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxTilemap;
import org.flixel.event.IFlxButton;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TmxMapLoader.Parameters;

public class LevelState extends FlxState {
	static final int TOP_Y = 480;
	static final int TOP_X = 800;
	
	private static String FloorTiles;
	private static String WallTiles;
	private static String MAP;
	
	public static LinkSprite hero;
	
	TiledMap map;
	FlxTilemap _level;
	FlxTilemap _levelwall;
	float unitScale = 16 / 2f;

	public LevelState(String _FloorTiles, String _WallTiles, String _MAP) {
		FloorTiles = _FloorTiles;
		WallTiles = _WallTiles;
		MAP = _MAP;
	}

	@Override
	public void create() {
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
		
		FlxButton menubutton = new FlxButton(FlxG.camera.scroll.x, FlxG.camera.scroll.y+10, "Main", new IFlxButton() {
			@Override
			public void callback() {
				FlxG.switchState(new TitleState());
			}
		});
		
		hero = new LinkSprite();
		hero.x = 64;
		hero.y = 64;
		
		_level = new FlxTilemap();
		_levelwall = new FlxTilemap();
		_level.loadMap(FlxTilemap.tiledmapToCSV(map, "Ground"), FloorTiles, 16, 16,FlxTilemap.OFF, 1);
		_levelwall.loadMap(FlxTilemap.tiledmapToCSV(map, "Walls"), WallTiles, 16, 16,FlxTilemap.OFF, 3);
		
		add(_level);
		add(_levelwall);
		add(hero);
		add(menubutton);
	}
	
	public void destroy() {
		super.destroy();
		_level = null;
		_levelwall = null;
		map.dispose();
	}
	
	@Override
	public void update() {
		super.update();
		FlxG.collide(hero, _levelwall);

		FlxG.collide(hero.emitter, _levelwall);
		FlxG.camera.scroll.x = hero.x-(TOP_X/4);
		FlxG.camera.scroll.y = hero.y-(TOP_Y/4);
		FlxG.camera.setBounds( FlxG.camera.scroll.x, FlxG.camera.scroll.y,FlxG.width,FlxG.height, true );
	}

}
