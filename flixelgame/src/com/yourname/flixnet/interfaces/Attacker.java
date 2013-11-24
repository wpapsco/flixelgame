package com.yourname.flixnet.interfaces;

import org.flixel.FlxBasic;

public interface Attacker {
	/**
	 * @param target the target of the attack
	 * @return if the attack was successful
	 */
	public boolean attack(FlxBasic target);
}
