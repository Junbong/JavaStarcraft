package com.bong.starcraft.building.produce;


import com.bong.starcraft.unit.UnitTypes;



/**
 * Created by bong on 15. 6. 15.
 */
public interface Procuable<T> {
	public T produce(UnitTypes unitTypes);
}
