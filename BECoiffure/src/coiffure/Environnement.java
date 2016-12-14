package coiffure;

import java.time.Duration;
import java.util.LinkedList;
import java.util.Random;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.base.utility.Logger;
import enstabretagne.base.utility.loggerimpl.SysOutLogger;
import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.SimEntity;
import fr.ensta.lerouxlu.simu.SimEvent;

public  class Environnement extends SimEntity {

		private Duration tempsOpe;
		private final int freqClient = 6;
	
		private Salon salon;

		public Environnement(SimEngine engine,Salon salon) {
			super(engine,"Client");
			this.salon = salon;
		}
		
		@Override
		public void initialize() {
			super.initialize();
			Logger.Information(this, "initialize",  "initialise env");
			LogicalDateTime t = new LogicalDateTime("14/12/2016 00:00:00.0000");
			
			for(int i =0;i<50;i++){
				
				salon.addEvent(new SimEvent(t) {
					
					@Override
					public void process() {
						// TODO Auto-generated method stub
						
						LinkedList<Coiffeur> l = salon.coiffeurs;
						if(!l.contains(null)){
							l.add(null);
						}
						
						int size = l.size();
						Random r = new Random();
						int n = r.nextInt(size);
						salon.clients.add(new Client(engine,  l.get(n), this.scheduleDate()));
						if(l.get(n) == null)
							Logger.Information(this, "addClient", "Adding client with fav : none");
						else
							Logger.Information(this, "addClient", "Adding client with fav : "+l.get(n));
					}
				});
				t = t.add(LogicalDuration.ofMinutes(freqClient));
			}
		}
/*		
		public void sayHelloTo(Sim other) {

			Logger.Information(this, "sayhello", "Hello "+ other);
			other.answerHelloTo(this);
			
		}

		public void answerHelloTo(Sim other) {
			//Calcul du prochain instant où on se dit bonjour
			long randomduration = getEngine().getRandomDuration();
			addEvent(new SayHello(getEngine().SimulationDate().add(LogicalDuration.ofHours(randomduration)),other));
			
		}
*/
		/*@Override
		public String toString() {
			return id+" "+fav;
		}*/
		
		@Override
		public void activate() {
			super.activate();
			Logger.Information(this, "activate", "Env se reveille");
			
/*			Client sim = (Sim)getEngine().trouver("Sim");
			if(sim!=null)
				if(sim.getName().compareTo(this.getName())!=0){
					this.addEvent(new SayHello(getEngine().SimulationDate().add(LogicalDuration.ofHours(2)),sim));
				}
*/		
		}

		
		@Override
		public void deactivate() {
			super.deactivate();
			Logger.Information(this, "deactivate", "je suis desactivé");
		}
		@Override
		public void terminate() {
			super.terminate();
			Logger.Information(this, "terminate","je suis terminé");

		}
/*
		public class SayHello extends SimEvent {

			public Sim collegue;
			public SayHello(LogicalDateTime scheduledDate,Sim collegue){
				super(scheduledDate);
				this.collegue = collegue;
			}
			@Override
			public void process() {
				sayHelloTo(collegue);
				
			}
			
		}
*/
	}