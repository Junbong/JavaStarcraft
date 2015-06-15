package com.bong.starcraft.game;


/**
 * Created by bong on 15. 6. 11.
 */
public class StarcraftGameHost {
	public static StarcraftGame startNewGame(Tribe tribe) {
		StarcraftGame newGame = new StarcraftGame(tribe);
		newGame.startGame();
		return newGame;
	}



	public static void stopGame(StarcraftGame game) {
		game.stopGame();
	}
}
