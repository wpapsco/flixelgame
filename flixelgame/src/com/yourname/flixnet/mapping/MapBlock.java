package com.yourname.flixnet.mapping;

import org.flixel.FlxPoint;
import org.flixel.FlxRect;
import org.flixel.FlxSprite;

public class MapBlock extends FlxSprite {
	
	public MapBlock(FlxPoint location, int tileIndex, String graphicPath, int tileWidth, int tileHeight) {
		super(location.x, location.y);
		this.loadGraphic(graphicPath);
		int wit = Math.round(this.width / tileWidth);
		int hit = Math.round(this.height / tileHeight);
		tileIndex = tileIndex - 1;
		this.framePixels.setRegion((tileIndex % wit) * tileWidth, (float) Math.floor(tileIndex / wit) * tileHeight, tileWidth, tileHeight);
		this.framePixels.setBounds(0, 0, tileWidth, tileHeight);
		this.width = tileWidth;
		this.height = tileHeight;
	}
}
