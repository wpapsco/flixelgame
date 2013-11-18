package com.yourname.flixelgame;

import java.util.ArrayList;

import org.flixel.FlxPoint;
import org.flixel.FlxSprite;
import org.flixel.FlxU;

public abstract class EnemySprite extends FlxSprite {

	private ArrayList<FlxSprite> spritesToNotice;
	private float viewDistance;
	private FlxSprite target;
	
	public EnemySprite() {
	}

	public EnemySprite(float X) {
		super(X);
	}

	public EnemySprite(float X, float Y) {
		super(X, Y);
	}

	public EnemySprite(float X, float Y, String SimpleGraphic) {
		super(X, Y, SimpleGraphic);
		spritesToNotice = new ArrayList<FlxSprite>();
	}

	@Override
	public void update() {
		super.update();
		if (target == null) {
			for (int i = 0; i < spritesToNotice.size(); i++) {
				if (FlxU.getDistance(new FlxPoint(spritesToNotice.get(i).x, spritesToNotice.get(i).y), new FlxPoint(this.x, this.y)) <= viewDistance) {
					onNoticeTarget(spritesToNotice.get(i));
					target = spritesToNotice.get(i);
				}
			}
		} else if (FlxU.getDistance(new FlxPoint(target.x,  target.y), new FlxPoint(this.x, this.y)) > viewDistance) {
			onLostTarget(target);
			target = null;
		}
	}
	
	public abstract void idle();
	public abstract void onNoticeTarget(FlxSprite player);
	public abstract void onFollow(FlxSprite player);
	public abstract void onLostTarget(FlxSprite player);
}
