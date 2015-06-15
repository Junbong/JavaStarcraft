package com.bong.starcraft.game;


import com.bong.starcraft.building.Building;
import com.bong.starcraft.building.BuildingTypes;
import com.bong.starcraft.unit.ground.buildable.AbstractBuildableUnit;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/**
 * Created by bong on 15. 6. 11.
 */
public class StarcraftGame {
	private ExecutorService mExecutor;

	private Tribe mTribe;
	private boolean mIsStarted;



	/**
	 * Receive tribe from StarcraftGameHost.
	 */
	public StarcraftGame(Tribe tribe) {
		initialize(tribe);
	}



	/**
	 * Initialize mTribe and make newSingleThreadExecutor.
	 */
	protected void initialize(Tribe tribe) {
		this.mTribe = tribe;
        /***/
        startGame();
        /***/

		// Initialize thread-pool
		mExecutor = Executors.newSingleThreadExecutor();
	}



	protected ExecutorService getGameThreadExecutor() {
		return this.mExecutor;
	}



	public Tribe getTribe() {
		return this.mTribe;
	}



	protected void startGame() {
		this.mIsStarted = true;
	}



	public boolean isGameStarted() {
		return this.mIsStarted;
	}



	protected void stopGame() {
		if (isGameStarted()) {
			this.mIsStarted = false;
			getGameThreadExecutor().shutdownNow();

		}
	}



	/**
	 * Receive buildingTypes and buildableUnit. In Callable<Building>, return buildableUnit and buildingTypes.
	 * and getGameThreadExecutor().submit().get() execute.
	 */
	public Building requestBuilding(BuildingTypes buildingTypes, AbstractBuildableUnit buildableUnit) {
		try {
			return getGameThreadExecutor().submit(new Callable<Building>() {
				@Override public Building call() throws Exception {
					System.out.print(String.format("'%s' started to build '%s'", buildableUnit, buildingTypes));

					// current time (milliseconds)
					final long time1 = System.currentTimeMillis();

					while ((System.currentTimeMillis() - time1) < buildingTypes.getRequiredProduceTime() * 1000) {
						System.out.print(".");
						try { Thread.sleep(1000); } catch (InterruptedException e) {}
					}

					System.out.println();

					return buildableUnit.build(buildingTypes);
				}
			}).get();

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {

			//getGameThreadExecutor().shutdown();
		}

		return null;
	}

}
