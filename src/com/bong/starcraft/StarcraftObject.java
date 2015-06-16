package com.bong.starcraft;


import com.bong.starcraft.game.StarcraftGame;



/**
 * Created by bong on 15. 6. 8.
 */
public interface StarcraftObject {
	public StarcraftGame getGameInstance();
	public int getObjectId();


	public int getMaxHitPoint();
	public int getMinHitPoint();
	public boolean isHealable();

	/**
	 * Retrieve remaining HP(hit point) of this unit
	 * @return remaining hit point
	 */
	public int getRemainingHitPoint();
	public boolean hit(int demage);
	public boolean recover(int amount);
}
