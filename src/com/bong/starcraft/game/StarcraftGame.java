package com.bong.starcraft.game;


import com.bong.starcraft.building.Building;
import com.bong.starcraft.building.BuildingTypes;
import com.bong.starcraft.building.produce.AbstractProducableBuilding;
import com.bong.starcraft.building.suppliable.Suppliable;
import com.bong.starcraft.unit.Unit;
import com.bong.starcraft.unit.UnitTypes;
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


	private int mUserPopulation = 0;



	public StarcraftGame(Tribe tribe) {
		initialize(tribe);
	}



	protected void initialize(Tribe tribe) {
		this.mTribe = tribe;
	}



	protected ExecutorService getGameThreadExecutor() {
		return this.mExecutor;
	}



	public Tribe getTribe() {
		return this.mTribe;
	}



	protected void startGame() {
		if (!isGameStarted()) {
			this.mIsStarted = true;

			// Initialize thread-pool
			/*mExecutor = Executors.newSingleThreadExecutor();*/
			mExecutor = Executors.newCachedThreadPool();
		}
	}



	public boolean isGameStarted() {
		return this.mIsStarted;
	}



	protected void stopGame() {
		if (isGameStarted()) {
			this.mIsStarted = false;

			// Shutdown thread-pool
			getGameThreadExecutor().shutdownNow();
		}
	}



	public Building requestBuilding(BuildingTypes buildingTypes, AbstractBuildableUnit buildableUnit) {
		try {
			Building building = getGameThreadExecutor().submit(new Callable<Building>() {
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

			// Set population
			requestBuildingImpl(building);

			// Return build result
			return building;

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			//
		}

		return null;
	}



	public void requestBuilding(BuildingTypes buildingTypes, AbstractBuildableUnit buildableUnit, Handler<Building> handler) {
		getGameThreadExecutor().execute(new Runnable() {
			@Override public void run() {
				System.out.println(String.format("'%s' started to build '%s'...", buildableUnit, buildingTypes));

				// current time (milliseconds)
				final long time1 = System.currentTimeMillis();

				while ((System.currentTimeMillis() - time1) < buildingTypes.getRequiredProduceTime() * 1000) {
					try { Thread.sleep(1000); } catch (InterruptedException e) {}
				}


				// Building built
				Building building = buildableUnit.build(buildingTypes);

				// Set population
				requestBuildingImpl(building);


				// Handle result
				if (handler != null) handler.onHandle(building);
			}
		});
	}



	private void requestBuildingImpl(Building building) {
		if (building instanceof Suppliable) {
			Suppliable s = (Suppliable) building;
			mUserPopulation += s.getSuppliedPopulation();
		}
	}



	public <T extends Unit> void requestUnit(UnitTypes unitTypes,
			AbstractProducableBuilding<T> producableBuilding, Handler<Unit> handler) {
		getGameThreadExecutor().execute(new Runnable() {
			@Override public void run() {
				System.out.println(String.format("'%s' started to produce '%s'...", producableBuilding, unitTypes));

				// current time (milliseconds)
				final long time1 = System.currentTimeMillis();

				while ((System.currentTimeMillis() - time1) < unitTypes.getRequiredProduceTime() * 1000) {
					try { Thread.sleep(1000); } catch (InterruptedException e) {}
				}

				// Handle result
				//return buildableUnit.build(buildingTypes);
				if (handler != null) {
					handler.onHandle(producableBuilding.produce(unitTypes));
				}
			}
		});
	}
}


















