package fr.ensta.lerouxlu.simu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import coiffure.Coiffeur;
import coiffure.Salon;

public abstract class SimEntity {

	private EntityState state;
	public Set<ISimEvent> events = new HashSet<>();
	protected SimEngine engine;

	String type;

	public String getType() {
		return type;
	}

	public SimEngine getEngine() {
		return engine;
	}

	public SimEntity(SimEngine engine,String type) {
		this.engine = engine;
		engine.addEntity(this);
		setState(EntityState.BORN);
		this.type=type;
		if(type.equals("coiffeur")){
			Iterator<SimEntity> i = engine.entities.iterator();
			Salon s = null;
			boolean b = true;
			while(b){
				try{
					s = (Salon) i.next();
					b = false;
				}
				catch(ClassCastException e){
					
				}
				
			}
			s.coiffeurs.add((Coiffeur)this);
		}
	}

	public void terminate() {
		setState(EntityState.DEAD);
	}

	public void initialize() {
		setState(EntityState.IDLE);
	}

	public void activate() {
		setState(EntityState.ACTIVE);
	}

	public void deactivate() {
		setState(EntityState.BORN);
	}

	public void pause() {
		setState(EntityState.IDLE);
	}

	public void lock() {
		setState(EntityState.HELD);
	}

	public void release() {
		setState(EntityState.ACTIVE);
	}

	public EntityState getState() {
		return state;
	}

	public void setState(EntityState state) {
		this.state = state;
	}

	public void addEvent(ISimEvent event) {
		events.add(event);
		engine.onEventPosted(event);
	}

	public boolean isAffectedBy(ISimEvent event) {
		return events.contains(event);
	}

}
