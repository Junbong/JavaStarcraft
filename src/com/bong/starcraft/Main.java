package com.bong.starcraft;


import com.bong.starcraft.building.Building;
import com.bong.starcraft.building.BuildingTypes;
import com.bong.starcraft.building.TerranBuildingTypes;
import com.bong.starcraft.building.produce.Barrack;
import com.bong.starcraft.building.produce.CommandCenter;
import com.bong.starcraft.game.Handler;
import com.bong.starcraft.game.StarcraftGame;
import com.bong.starcraft.game.StarcraftGameHost;
import com.bong.starcraft.game.Tribe;
import com.bong.starcraft.unit.TerranUnitTypes;
import com.bong.starcraft.unit.Unit;
import com.bong.starcraft.unit.ground.attackable.Firebat;
import com.bong.starcraft.unit.ground.attackable.Marine;
import com.bong.starcraft.unit.ground.buildable.SCV;



/**
 * Created by bong on 15. 6. 8.
 */
public class Main {
	public static void main(String[] args) {
		StarcraftGame game = StarcraftGameHost.startNewGame(Tribe.TERRAN);


		SCV testSCV1 = new SCV(game);
		/*
		CommandCenter commandCenter = (CommandCenter) game
				.requestBuilding(TerranBuildingTypes.COMMAND_CENTER, testSCV);

		SCV scv1 = (SCV) commandCenter.produce(TerranUnitTypes.SCV);
		scv1.talk();
		*/

		game.requestBuilding(TerranBuildingTypes.COMMAND_CENTER, testSCV1, new Handler<Building>() {
			@Override public void onHandle(Building result) {
				CommandCenter commandCenter = (CommandCenter) result;

				game.requestUnit(TerranUnitTypes.SCV, commandCenter, unit -> {
					SCV scv1 = (SCV) unit;
					scv1.talk();

					game.requestBuilding(TerranBuildingTypes.BARRACK, scv1, new Handler<Building>() {
						@Override public void onHandle(Building result) {
							Barrack barrack = (Barrack) result;

							game.requestUnit(TerranUnitTypes.MARINE, barrack, result1 -> {
								Marine marine1 = (Marine) result1;
								marine1.talk();
							});
						}
					});
				});
			}
		});


//		SCV testScv2 = new SCV(game);
//
//		game.requestBuilding(TerranBuildingTypes.BARRACK, testScv2, result -> {
//			/*
//			Barrack barrack = (Barrack) result;
//			Firebat firebat = (Firebat) barrack.produce(TerranUnitTypes.FIREBAT);
//			firebat.talk();
//			*/
//
//			Barrack barrack = (Barrack) result;
//			game.requestUnit(TerranUnitTypes.FIREBAT, barrack, new Handler<Unit>() {
//				@Override public void onHandle(Unit result) {
//					Firebat firebat = (Firebat) result;
//					firebat.talk();
//				}
//			});
//		});


//		StarcraftGameHost.stopGame(game);
	}
}
