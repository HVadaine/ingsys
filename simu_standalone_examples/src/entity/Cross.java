package entity;

import java.util.HashMap;

import engine.ISimEntity;

public class Cross extends Node implements ISimEntity {

	Car isOccuped[] = new Car[4];
	boolean rule;
	public final boolean STOP = true, FEU = false;
	
	
}
