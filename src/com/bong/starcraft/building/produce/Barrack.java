package com.bong.starcraft.building.produce;


import com.bong.starcraft.game.StarcraftGame;
import com.bong.starcraft.unit.TerranUnitTypes;
import com.bong.starcraft.unit.Unit;
import com.bong.starcraft.unit.ground.attackable.Firebat;
import com.bong.starcraft.unit.ground.attackable.Ghost;
import com.bong.starcraft.unit.ground.attackable.Marine;
import com.bong.starcraft.unit.ground.healable.Medic;



/**
 * Created by bong on 15. 6. 8.
 */
public class Barrack extends AbstractProducableBuilding<Unit> {
	public Barrack(StarcraftGame gameInstance) {
		super(gameInstance, 700);
	}



	@Override public void build() {
		super.build();
	}



	@Override protected Unit onProduce(TerranUnitTypes unitTypes) {
		switch (unitTypes) {
			case MARINE:
				TerranUnitTypes.MARINE.getRequiredMineral();
				return new Marine(getGameInstance());

			case FIREBAT:
				return new Firebat(getGameInstance());

			case MEDIC:
				return new Medic(getGameInstance());

			case GHOST:
				return new Ghost(getGameInstance());

			default:
				throw new UnsupportedOperationException();
		}
	}
}
