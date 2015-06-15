package com.bong.starcraft.building;


/**
 * Created by ȫ�� on 2015-06-09.
 */
public enum TerranBuildingTypes implements BuildingTypes {
	COMMAND_CENTER(400, 0, 5),
	BARRACK(150, 0, 5),
	ENGINEERING_BAY(100, 0, 15),
	ACADEMY(150, 30, 25);


	private final int mRequiredMineral;
	private final int mRequiredGas;
	private final int mRequiredTime;



	TerranBuildingTypes(int requiredMineral, int requiredGas, int requiredTime) {

		this.mRequiredMineral = requiredMineral;
		this.mRequiredGas = requiredGas;
		this.mRequiredTime = requiredTime;
	}



	@Override public int getRequiredMineral() {
		return this.mRequiredMineral;
	}



	@Override public int getRequiredGas() {
		return this.mRequiredGas;
	}



	@Override public int getRequiredProduceTime() {
		return this.mRequiredTime;
	}
}
