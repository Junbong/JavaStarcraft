package com.bong.starcraft.unit;


import com.bong.starcraft.game.StarcraftGame;
import com.bong.starcraft.game.util.ObjectUtil;



/**
 * Created by bong on 15. 6. 8.
 */
public abstract class AbstractUnit implements Unit {
	private final StarcraftGame mGameInstance;
	private final int mObjectId;
	private final String mName;

	private int mRemainingHitPoint;
	private int mMaxHitPoint;



	public AbstractUnit(StarcraftGame gameInstance, int hitPoint) {
		if (gameInstance == null) throw new IllegalArgumentException();

		this.mGameInstance = gameInstance;
		this.mObjectId = gameInstance.getGameObjectManager().nextObjectId();
		this.mName = ObjectUtil.newName(this);

		this.mRemainingHitPoint = hitPoint;
		this.mMaxHitPoint = mRemainingHitPoint;
	}



	@Override public String toString() {
		return mName;
	}



	@Override public StarcraftGame getGameInstance() {
		return mGameInstance;
	}



	@Override public int getObjectId() {
		return mObjectId;
	}



	/**
	 * Max hit point
	 * @return
	 */
	@Override public int getMaxHitPoint() {
		return this.mMaxHitPoint;
	}



	/**
	 * Usually minimal hit point is zero.
	 * @return 0
	 */
	@Override public int getMinHitPoint() {
		return 0;
	}



	@Override public int getRemainingHitPoint() {
		return this.mRemainingHitPoint;
	}



	@Override public boolean hit(int damage) {
		// If not died yet
		if (isAlive()) {
			mRemainingHitPoint -= damage;

			if (getRemainingHitPoint() <= 0) {
				this.die();
				return false;
			}

			return true;
		}

		return false;
	}



	@Override public boolean recover(int amount) {
		// If not dead yet
		if (isAlive()) {
			if (getRemainingHitPoint() < getMaxHitPoint()) {
				mRemainingHitPoint += amount;
				mRemainingHitPoint = Math.min(getMaxHitPoint(), mRemainingHitPoint);
				return true;
			}
		}

		return false;
	}



	@Override public boolean isHealable() {
		return true;
	}



	@Override public void move(int x, int y) {
		System.out.println(String.format("'%s' moved to (%d, %d)",
				this.toString(), x, y));
	}



	@Override public final void talk() {
		System.out.println(String.format("'%s' says: \"%s\"",
				this.toString(), onTalk()));
	}



	protected String onTalk() {
		return "Who am I?";
	}



	@Override public void die() {
		System.out.println(String.format("'%s' died...", this.toString()));
	}



	@Override public boolean isDied() {
		return (getRemainingHitPoint() <= 0);
	}



	@Override public boolean isAlive() {
		return (!isDied());
	}
}
