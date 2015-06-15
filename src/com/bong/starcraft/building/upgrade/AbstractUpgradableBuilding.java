package com.bong.starcraft.building.upgrade;

import com.bong.starcraft.building.AbstractBuilding;
import com.bong.starcraft.game.StarcraftGame;

/**
 * Created by È«¼® on 2015-06-14.
 */
public class AbstractUpgradableBuilding extends AbstractBuilding{
    public AbstractUpgradableBuilding(StarcraftGame gameInstance, int hitPoint) {
        super(gameInstance, hitPoint);
    }


    @Override public void build() {
        super.build();
    }

}
