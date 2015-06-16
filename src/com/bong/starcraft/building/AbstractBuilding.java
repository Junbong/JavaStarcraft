package com.bong.starcraft.building;


import com.bong.starcraft.game.StarcraftGame;
import com.bong.starcraft.game.util.ObjectUtil;



/**
 * Created by bong on 15. 6. 8.
 */
public abstract class AbstractBuilding implements Building {
	private final StarcraftGame mGameInstance;
	private final int mObjectId;
	private final String mName;

	private int mRemainingHitPoint;
	private int mMaxHitPoint;
	private boolean mIsBuiltComplete;



	public AbstractBuilding(StarcraftGame gameInstance, int hitPoint) {
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
	 *
	 * @return
	 */
	@Override public int getMaxHitPoint() {
		return this.mMaxHitPoint;
	}



	/**
	 * Usually minimal hit point is zero.
	 *
	 * @return 0
	 */
	@Override public int getMinHitPoint() {
		return 0;
	}



	@Override public int getRemainingHitPoint() {
		return this.mRemainingHitPoint;
	}



	@Override public boolean hit(int damage) {
		// If not destroyed yet
		if (!isDestroyed()) {
			mRemainingHitPoint -= damage;
			if (getRemainingHitPoint() <= 0)
				destroy();
		}

		return false;
	}



	@Override public boolean recover(int amount) {
		// If not dead yet
		if (!isDestroyed()) {
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



	@Override public void build() {
		// Complete
		mIsBuiltComplete = true;

		System.out.println(String.format("'%s' built!", getClass().getSimpleName()));
	}



	@Override public boolean isBuiltComplete() {
		return this.mIsBuiltComplete;
	}



	@Override public float getBuildProcessFactor() {
		return 0;
	}



	@Override public void destroy() {
		System.out.println(String.format("'%s' destroyed!", getClass().getSimpleName()));
	}



	@Override public boolean isDestroyed() {
		return (getRemainingHitPoint() <= 0);
	}
}













