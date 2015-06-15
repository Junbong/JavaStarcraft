package com.bong.starcraft;


import com.bong.starcraft.building.TerranBuildingTypes;
import com.bong.starcraft.building.produce.Barrack;
import com.bong.starcraft.building.produce.CommandCenter;
import com.bong.starcraft.game.StarcraftGame;
import com.bong.starcraft.game.StarcraftGameHost;
import com.bong.starcraft.game.Tribe;
import com.bong.starcraft.unit.TerranUnitTypes;
import com.bong.starcraft.unit.ground.attackable.Firebat;
import com.bong.starcraft.unit.ground.attackable.Ghost;
import com.bong.starcraft.unit.ground.attackable.Marine;
import com.bong.starcraft.unit.ground.buildable.SCV;
import com.bong.starcraft.unit.ground.healable.Medic;


/**
 * Created by bong on 15. 6. 8.
 */
public class Main {
	public static void main(String[] args) {
		StarcraftGame game = StarcraftGameHost.startNewGame(Tribe.TERRAN);


		SCV testSCV = new SCV(game);
		CommandCenter commandCenter = (CommandCenter) game
				.requestBuilding(TerranBuildingTypes.COMMAND_CENTER, testSCV);

        SCV scv1 = (SCV) commandCenter.produce(TerranUnitTypes.SCV);
        scv1.talk();

		Barrack barrack = (Barrack) game
				.requestBuilding(TerranBuildingTypes.BARRACK, scv1);


		//SCV scv2 = (SCV) commandCenter.produce(TerranUnitTypes.SCV);

		Marine marine1 = (Marine)barrack.produce(TerranUnitTypes.MARINE);
		marine1.talk();

		Firebat firebat1 = (Firebat)barrack.produce(TerranUnitTypes.FIREBAT);
		firebat1.talk();

		Medic medic1 = (Medic)barrack.produce(TerranUnitTypes.MEDIC);

		while(firebat1.isAlive()){
			marine1.attack(firebat1);
			medic1.heal(firebat1);
		}


		Ghost ghost1 = (Ghost)barrack.produce(TerranUnitTypes.GHOST);
		ghost1.talk();
		while(marine1.isAlive()){
			ghost1.attack(marine1);
		}


		StarcraftGameHost.stopGame(game);
	}
}
