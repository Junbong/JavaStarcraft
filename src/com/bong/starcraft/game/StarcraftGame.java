package com.bong.starcraft.game;


import com.bong.starcraft.ObjectTypes;
import com.bong.starcraft.building.Building;
import com.bong.starcraft.building.BuildingTypes;
import com.bong.starcraft.building.produce.AbstractProducableBuilding;
import com.bong.starcraft.building.suppliable.Suppliable;
import com.bong.starcraft.unit.Unit;
import com.bong.starcraft.unit.UnitTypes;
import com.bong.starcraft.unit.ground.buildable.AbstractBuildableUnit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;



/**
 * Created by bong on 15. 6. 11.
 */
public class StarcraftGame {
	private GameObjectManager mGameObjectManagerInstance;
	private UserPropertyManager mUserPropertyManager;


	private ExecutorService mExecutor;

	private Tribe mTribe;
	private boolean mIsStarted;



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

			// Initialize variables
			mGameObjectManagerInstance = null;
		}
	}



	public synchronized GameObjectManager getGameObjectManager() {
		if (mGameObjectManagerInstance == null) {
			mGameObjectManagerInstance = new GameObjectManager();
		}

		return mGameObjectManagerInstance;
	}



	public static class GameObjectManager {
		private final AtomicInteger mObjectIdCounter = new AtomicInteger(1);


		private GameObjectManager() {}


		public int nextObjectId() {
			return mObjectIdCounter.getAndIncrement();
		}
	}



	public synchronized  UserPropertyManager getUserPropertyManager() {
		if (mUserPropertyManager == null) {
			mUserPropertyManager = new UserPropertyManager();
		}

		return mUserPropertyManager;
	}



	public static class UserPropertyManager {
		private final AtomicInteger mMineral = new AtomicInteger(50);
		private final AtomicInteger mGas = new AtomicInteger(0);
		private final AtomicInteger mPopulation = new AtomicInteger(0);
		private final AtomicInteger mPopulationCapacity = new AtomicInteger(0);


		public int incrementAndGetMineral(int delta) {
			return this.mMineral.addAndGet(delta);
		}


		public int decrementAndGetMineral(int delta) {
			return this.mMineral.addAndGet((delta>0)? (-1*delta) : delta);
		}


		public int incrementAndGetGas(int delta) {
			return this.mGas.addAndGet(delta);
		}


		public int decrementAndGetGas(int delta) {
			return this.mGas.addAndGet((delta>0)? (-1*delta) : delta);
		}


		public int incrementAndGetPopulation(int delta) {
			final int pop1 = this.mPopulation.get();
			final int popc1 = this.mPopulationCapacity.get();

			try {
				return this.mPopulation.addAndGet(delta);

			} finally {
				final int pop2 = this.mPopulation.get();
				final int popc2 = this.mPopulationCapacity.get();

				System.out.println(String.format("Population: %d/%d -> %d/%d",
						pop1, popc1, pop2, popc2));
			}
		}


		public int decrementAndGetPopulation(int delta) {
			final int pop1 = this.mPopulation.get();
			final int popc1 = this.mPopulationCapacity.get();

			try {
				return this.mPopulation.addAndGet((delta>0)? (-1*delta) : delta);

			} finally {
				final int pop2 = this.mPopulation.get();
				final int popc2 = this.mPopulationCapacity.get();

				System.out.println(String.format("Population: %d/%d -> %d/%d",
						pop1, popc1, pop2, popc2));
			}
		}


		public int incrementAndGetPopulationCapacity(int delta) {
			final int pop1 = this.mPopulation.get();
			final int popc1 = this.mPopulationCapacity.get();

			try {
				return this.mPopulationCapacity.addAndGet(delta);

			} finally {
				final int pop2 = this.mPopulation.get();
				final int popc2 = this.mPopulationCapacity.get();

				System.out.println(String.format("Population: %d/%d -> %d/%d",
						pop1, popc1, pop2, popc2));
			}
		}


		public int decrementAndGetPopulationCapacity(int delta) {
			final int pop1 = this.mPopulation.get();
			final int popc1 = this.mPopulationCapacity.get();

			try {
				return this.mPopulationCapacity.addAndGet((delta>0)? (-1*delta) : delta);

			} finally {
				final int pop2 = this.mPopulation.get();
				final int popc2 = this.mPopulationCapacity.get();

				System.out.println(String.format("Population: %d/%d -> %d/%d",
						pop1, popc1, pop2, popc2));
			}
		}


//		private int operationSomething(int value) {
//			final int pop1 = this.mPopulation.get();
//			final int popc1 = this.mPopulationCapacity.get();
//
//			try {
//				return value;
//
//			} finally {
//				final int pop2 = this.mPopulation.get();
//				final int popc2 = this.mPopulationCapacity.get();
//
//				System.out.println(String.format("Population: %d/%d -> %d/%d",
//						pop1, popc1, pop2, popc2));
//			}
//		}
	}



	/*
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
	*/



	private void preRequestObjectImpl(ObjectTypes objectTypes) {
		// TODO: my resources enough?

		getUserPropertyManager().decrementAndGetMineral(objectTypes.getRequiredMineral());
		getUserPropertyManager().decrementAndGetGas(objectTypes.getRequiredGas());


		if (objectTypes instanceof UnitTypes) {
			getUserPropertyManager()
					.incrementAndGetPopulation(((UnitTypes) objectTypes).getRequiredPopulation());
		}
	}



//	private void postRequestObjectImpl(ObjectTypes objectTypes) {
//		if (objectTypes instanceof  BuildingTypes) {
//			getUserPropertyManager()
//					.incrementAndGetPopulationCapacity(((BuildingTypes) objectTypes).)
//		}
//	}



	public void requestBuilding(BuildingTypes buildingTypes, AbstractBuildableUnit buildableUnit, Handler<Building> handler) {
		getGameThreadExecutor().execute(new Runnable() {
			@Override public void run() {
				// Pre process
				preRequestObjectImpl(buildingTypes);


				System.out.println(String.format("'%s' started to build '%s'...", buildableUnit, buildingTypes));

				// current time (milliseconds)
				final long time1 = System.currentTimeMillis();

				while ((System.currentTimeMillis() - time1) < buildingTypes.getRequiredProduceTime() * 1000) {
					try { Thread.sleep(1000); } catch (InterruptedException e) {}
				}


				// Building built
				Building building = buildableUnit.build(buildingTypes);

				// Set population
				postRequestBuildingImpl(building);


				// Post process
//				postRequestObjectImpl(buildingTypes);


				// Handle result
				if (handler != null) handler.onHandle(building);
			}
		});
	}



	private void postRequestBuildingImpl(Building building) {
		if (building instanceof Suppliable) {
			Suppliable s = (Suppliable) building;

			/*mUserPopulation += s.getSuppliedPopulation();*/
			getUserPropertyManager().incrementAndGetPopulationCapacity(s.getSuppliedPopulation());
		}
	}



	public <T extends Unit> void requestUnit(UnitTypes unitTypes,
			AbstractProducableBuilding<T> producableBuilding, Handler<Unit> handler) {
		getGameThreadExecutor().execute(new Runnable() {
			@Override public void run() {
				// Pre process
				preRequestObjectImpl(unitTypes);


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


















