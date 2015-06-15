package com.bong.starcraft.unit;


/**
 * Created by bong on 15. 6. 9.
 */
public enum TerranUnitTypes implements UnitTypes {
	SCV(50, 0, 20, 1),
	MARINE(100, 0, 30, 1),
	FIREBAT(150, 50, 45, 1),
	MEDIC(120, 50, 50, 1),
	GHOST(130, 25, 45, 1);


	private final int mRequiredMineral;
	private final int mRequiredGas;
	private final int mRequiredTime;
	private final int mRequiredPopulation;


	TerranUnitTypes(int requiredMineral, int requiredGas, int requiredTime, int requiredPopulation) {
		this.mRequiredMineral = requiredMineral;
		this.mRequiredGas = requiredGas;
		this.mRequiredTime = requiredTime;
		this.mRequiredPopulation = requiredPopulation;
	}



	@Override
	public int getRequiredMineral() {
		return this.mRequiredMineral;
	}



	@Override
	public int getRequiredGas() {
		return this.mRequiredGas;
	}



	@Override
	public int getRequiredProduceTime() {
		return this.mRequiredTime;
	}



	@Override
	public int getRequiredPopulation() {
		return this.mRequiredPopulation;
	}
}
