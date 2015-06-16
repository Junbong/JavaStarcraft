package com.bong.starcraft.unit.ground.buildable;


import com.bong.starcraft.game.StarcraftGame;
import com.bong.starcraft.building.Building;
import com.bong.starcraft.unit.AbstractUnit;



/**
 * Created by ȫ�� on 2015-06-09.
 */
public abstract class AbstractBuildableUnit<T> extends AbstractUnit {
	public AbstractBuildableUnit(StarcraftGame gameInstance, int hitPoint) {
		super(gameInstance, hitPoint);
	}



	public final Building build(final T buildingTypes) {
		System.out.println(String.format("'%s' builds '%s' complete!",
				this.toString(), buildingTypes));

		return onBuild(buildingTypes);


		/*
		final ExecutorService executor = Executors.newSingleThreadExecutor();

		try {
			return executor.submit(new Callable<Building>() {
				@Override public Building call() throws Exception {
					// current time (milliseconds)
					final long time1 = System.currentTimeMillis();

					while ((System.currentTimeMillis() - time1) <
							TerranBuildingTypes.COMMAND_CENTER.getRequiredProduceTime() * 1000) {
						System.out.print(".");
						try { Thread.sleep(1000); } catch (InterruptedException e) {}
					}


					System.out.println(String.format("'%s' build '%s'!",
							getClass().getSimpleName(), buildingTypes));


					return onBuild(buildingTypes);
				}
			}).get();

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}

		return null;
		*/
	}



	protected abstract Building onBuild(T buildingTypes);
}
