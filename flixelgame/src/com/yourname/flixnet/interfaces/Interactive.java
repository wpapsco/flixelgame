package com.yourname.flixnet.interfaces;

import com.yourname.flixnet.characters.Player;

public interface Interactive {
	/**
	 * @param player the player interacting with this
	 */
	public void onInteract(Player player);
}
