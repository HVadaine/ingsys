package coiffure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.base.utility.Logger;
import enstabretagne.base.utility.loggerimpl.SysOutLogger;
import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.SimEntity;
import fr.ensta.lerouxlu.simu.SimEvent;

public  class Salon extends SimEntity {

		public LinkedList<Client> clients;
		public LinkedList<Coiffeur> coiffeurs;
		public LinkedList<Integer> temps;
		public double moy = 0.0;

		public Salon(SimEngine engine) {
			super(engine,"Salon");
			clients = new LinkedList<Client>();
			coiffeurs = new LinkedList<Coiffeur>();
			temps = new LinkedList<Integer>();
		}
		
		@Override
		public void initialize() {
			super.initialize();
			Logger.Information(this, "initialize", "salon initialise");
		}
		
		@Override
		public String toString() {
			return "Salon : waiting people: "+clients;
		}
		
		@Override
		public void activate() {
			super.activate();
			Logger.Information(this, "activate", "salon se reveille");
		}
		
		@Override
		public void deactivate() {
			super.deactivate();
			Logger.Information(this, "deactivate", "je suis desactivé");
			
			Iterator<Integer> ii = temps.iterator();
			int somme = 0;
			while(ii.hasNext()){
				somme += ii.next();
			}
			double d = temps.size();
			double s = somme;
			moy = s/d;
			Logger.Information(this, "moy", Double.toString(moy));
		}
		@Override
		public void terminate() {
			super.terminate();
			Logger.Information(this, "terminate","je suis terminé");
			
			
		}

		public class NewClient extends SimEvent {

			public NewClient(LogicalDateTime scheduledDate, Client client){
				super(scheduledDate);
				clients.add(client);
			}
			@Override
			public void process() {
				return;
			}
			
		}
		
	}