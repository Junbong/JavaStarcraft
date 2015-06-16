package com.bong.starcraft.unit;


import com.bong.starcraft.ObjectTypes;



/**
 * Created by bong on 15. 6. 9.
 */
public interface UnitTypes extends ObjectTypes {
	@Override public int getRequiredMineral();

	@Override public int getRequiredGas();

	@Override public int getRequiredProduceTime();

	public int getRequiredPopulation();
}
