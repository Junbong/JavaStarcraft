package com.bong.starcraft.unit.ground.attackable;

import com.bong.starcraft.game.StarcraftGame;

/**
 * Created by È«¼® on 2015-06-14.
 */
public class Ghost extends AbstractAttackableUnit {
    public Ghost(StarcraftGame gameInstance) {
        super(gameInstance, 200);
    }



    @Override public int getDamage() {
        return 50;
    }



    @Override protected String onTalk() {
        return "Somebody call for on exterminater?";
    }
}
