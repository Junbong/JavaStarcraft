package com.bong.starcraft.building.produce;


import com.bong.starcraft.building.suppliable.Suppliable;
import com.bong.starcraft.game.StarcraftGame;
import com.bong.starcraft.game.exception.NotProperTribeException;
import com.bong.starcraft.unit.TerranUnitTypes;
import com.bong.starcraft.unit.Unit;
import com.bong.starcraft.unit.UnitTypes;
import com.bong.starcraft.unit.ground.buildable.SCV;



/**
 * Created by ȫ�� on 2015-06-10.
 */
public class CommandCenter extends AbstractProducableBuilding<Unit> implements Suppliable {
	public CommandCenter(StarcraftGame gameInstance) {
		super(gameInstance, 400);
	}



	@Override public void build() {
		super.build();
	}



	@Override protected Unit onProduce(UnitTypes unitTypes) {
		/*
		switch (unitTypes) {
			case SCV:
				return new SCV(getGameInstance());

			default:
				throw new UnsupportedOperationException();
		}
		*/

		if (unitTypes instanceof TerranUnitTypes) {
			TerranUnitTypes terranUnitTypes = (TerranUnitTypes) unitTypes;

			switch (terranUnitTypes) {
				case SCV:
					return new SCV(getGameInstance());
			}
		}

		throw new NotProperTribeException();
	}



	@Override public int getSuppliedPopulation() {
		return 8;
	}
}
