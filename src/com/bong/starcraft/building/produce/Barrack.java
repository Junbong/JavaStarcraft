package com.bong.starcraft.building.produce;


import com.bong.starcraft.game.StarcraftGame;
import com.bong.starcraft.game.exception.NotProperTribeException;
import com.bong.starcraft.unit.TerranUnitTypes;
import com.bong.starcraft.unit.Unit;
import com.bong.starcraft.unit.UnitTypes;
import com.bong.starcraft.unit.ground.attackable.Firebat;
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



	@Override protected Unit onProduce(UnitTypes unitTypes) {
		if (unitTypes instanceof TerranUnitTypes) {
			TerranUnitTypes terranUnitTypes = (TerranUnitTypes) unitTypes;

			switch (terranUnitTypes) {
				case MARINE:
					TerranUnitTypes.MARINE.getRequiredMineral();
					return new Marine(getGameInstance());

				case FIREBAT:
					return new Firebat(getGameInstance());

				case MEDIC:
					return new Medic(getGameInstance());
			}
		}

		throw new NotProperTribeException();
	}
}
