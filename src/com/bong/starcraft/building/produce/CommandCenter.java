package com.bong.starcraft.building.produce;


import com.bong.starcraft.game.StarcraftGame;
import com.bong.starcraft.unit.TerranUnitTypes;
import com.bong.starcraft.unit.Unit;
import com.bong.starcraft.unit.ground.buildable.SCV;



/**
 * Created by ȫ�� on 2015-06-10.
 */
public class CommandCenter extends AbstractProducableBuilding<Unit> {
	public CommandCenter(StarcraftGame gameInstance) {
		super(gameInstance, 400);
	}



	@Override public void build() {
		super.build();
	}


	@Override protected Unit onProduce(TerranUnitTypes unitTypes) {
		switch (unitTypes) {
			case SCV:

				return new SCV(getGameInstance());

			default:
				throw new UnsupportedOperationException();
		}
	}
}
