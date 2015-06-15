package com.bong.starcraft.building.produce;


import com.bong.starcraft.building.AbstractBuilding;
import com.bong.starcraft.game.StarcraftGame;
import com.bong.starcraft.unit.UnitTypes;



/**
 * Created by bong on 15. 6. 8.
 */
public abstract class AbstractProducableBuilding<T> extends AbstractBuilding {
	public AbstractProducableBuilding(StarcraftGame gameInstance, int hitPoint) {
		super(gameInstance, hitPoint);
	}



	public final T produce(UnitTypes unitTypes) {
		// have enough resources?
		unitTypes.getRequiredMineral();
		unitTypes.getRequiredGas();
		unitTypes.getRequiredProduceTime();

		// TODO: reduce player`s resource


		/*return onProduce(unitTypes);*/

		T unit = onProduce(unitTypes);

		if (unit != null) {
			System.out.println(String.format("'%s' produce '%s'!",
					getClass().getSimpleName(), unit));
		}

		return unit;
	}



	protected abstract T onProduce(UnitTypes unitTypes);
}





