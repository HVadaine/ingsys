package entity;

import java.util.LinkedList;

import engine.ISimEntity;

public class Line  implements ISimEntity {

	private LinkedList<Car> cars;
	private int longueur;
	public final int largeur = 3;
	private Node begin, end;
	
}
