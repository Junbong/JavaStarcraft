package com.bong.starcraft.building;


import com.bong.starcraft.ObjectTypes;



/**
 * Created by bong on 15. 6. 9.
 */
public interface BuildingTypes extends ObjectTypes {
	@Override public int getRequiredMineral();

	@Override public int getRequiredGas();

	@Override public int getRequiredProduceTime();
}
