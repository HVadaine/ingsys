package coiffure;

import java.util.Iterator;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.base.utility.Logger;
import enstabretagne.base.utility.loggerimpl.SysOutLogger;
import fr.ensta.lerouxlu.simu.ISimEvent;
import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.SimEntity;
import fr.ensta.lerouxlu.simu.SimEvent;

public  class Coiffeur extends SimEntity {

		private String name;
		private Salon salon;
		public Coiffeur self;
		
		public String getName() {
			return name;
		}

		public Coiffeur(SimEngine engine, Salon salon, String name) {
			super(engine, "coiffeur");
			this.name = name;
			this.salon = salon;
			this.self = this;
		}
		
		@Override
		public void initialize() {
			super.initialize();
			Logger.Information(this, "initialize", name + " s initialise");
		}
		
		@Override
		public String toString() {
			return "Coiffeur "+this.name;
		}
		
		@Override
		public void activate() {
			super.activate();
			Logger.Information(this, "activate", name  +" se reveille");

			this.addEvent(new debutCoiff(getEngine().SimulationDate(),this));
		
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

		public class debutCoiff extends SimEvent {
			private Coiffeur self;

			public debutCoiff(LogicalDateTime scheduledDate, Coiffeur self){
				super(scheduledDate);
				this.self = self;
				}
			@Override
			public void process() {
				//System.out.println(salon.clients);
				boolean b = false;
				if(!salon.clients.isEmpty()){
					for(int i = 0;i<salon.clients.size();i++){
						//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+salon.clients.get(i).fav+"     "+this.toString());
						if(salon.clients.get(i).fav==null || salon.clients.get(i).fav.equals(self)){
							
							addEvent(new finCoiff(this.scheduledDate.add(LogicalDuration.ofMinutes(19)),self));
							Logger.Information(this, "remove","debut coiffure par : "+name+"/"+salon.clients.get(i).fav);
							
							b = true;
							
							LogicalDateTime d = salon.clients.get(i).temps;
							LogicalDateTime d2 = getEngine().SimulationDate();
							LogicalDuration dur = d2.soustract(d);
							salon.temps.add(dur.getMinutes());
							salon.clients.remove(i);
							
							break;
						}
					}
					if(!b){
						SimEvent e = this;
						e.add(LogicalDuration.ofMinutes(1));
						addEvent(e);
					}
					
				}
				else{
					SimEvent e = this;
					e.add(LogicalDuration.ofMinutes(1));
					addEvent(e);
				}
				
			}
			
		}
		
		public class finCoiff extends SimEvent {
			private Coiffeur self;

			public finCoiff(LogicalDateTime scheduledDate,Coiffeur self){
				super(scheduledDate);
				this.self = self;
				}
			@Override
			public void process() {
					addEvent(new debutCoiff(this.scheduledDate.add(LogicalDuration.ofMinutes(1)),self));
					Logger.Information(this, "fin","fin coiffure");
				
			}
			
		}

	}