package entity;

import java.util.HashMap;

import engine.ISimEntity;

public class Cross extends Node implements ISimEntity {

	Car isOccupied[] = new Car[4];
	boolean rule;
	public final boolean STOP = true, FEU = false;
	
	public Cross (Car car1, Car car2, Car car3, Car car4, boolean r) {
		isOccupied[0] = car1;
		isOccupied[1] = car2;
		isOccupied[2] = car3;
		isOccupied[3] = car4;
		rule = r;
	}
}
