package fr.ensta.lerouxlu.DemoSimple;

import java.util.HashMap;
import java.util.LinkedList;

import coiffure.Coiffeur;
import coiffure.Environnement;
import coiffure.Salon;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.base.utility.Logger;
import enstabretagne.base.utility.LoggerParamsNames;
import enstabretagne.base.utility.loggerimpl.SXLSXExcelDataloggerImpl;
import enstabretagne.base.utility.loggerimpl.SysOutLogger;
import enstabretagne.simulation.core.ISimulationDateProvider;
import fr.ensta.lerouxlu.simu.SimEngine;

public class SimMain {
		
	
	
	public static void main(String [] args) {
		
		LinkedList<Double> moy = new LinkedList<Double>();
		
		//Premier d'entre eux: le logger qui écrit dans la sortie standard
		HashMap<String,HashMap<String,Object>> loggersNames = new HashMap<String,HashMap<String,Object>>();
		loggersNames.put(SysOutLogger.class.getCanonicalName(), new HashMap<String,Object>());
		
		//Premier d'entre eux: le logger qui écrit dans un fichier excel
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put(LoggerParamsNames.DirectoryName.toString(), System.getProperty("user.dir"));
		params.put(LoggerParamsNames.FileName.toString(), "LoggerAndProba.xlsx");
		loggersNames.put(SXLSXExcelDataloggerImpl.class.getCanonicalName(),params);
		
		//Simulation en cascade
		for(int i = 0;i<10;i++){
			
		
		
		LogicalDateTime begin = LogicalDateTime.Zero;

		SimEngine engine = new SimEngine(1,begin,LogicalDuration.ofHours(30));
		//Initialisation de l'ensemble des loggers
		Logger.Init((ISimulationDateProvider) engine, loggersNames, true);

		//Toujours créer le salon en preimier, env en dernier		
		Salon salon = new Salon(engine);
		Coiffeur c = new Coiffeur(engine, salon, "Bob");
		Coiffeur c2 = new Coiffeur(engine, salon, "Alice");
		Coiffeur c3 = new Coiffeur(engine, salon, "Eve");
		Environnement env = new Environnement(engine,salon);
		
		///Client c = new Client(engine,  null);
		
		engine.initialize();
		engine.resume();
		
		while (engine.triggerNextEvent()) {}
			
		moy.add(salon.moy);
		}
		
		System.out.println(moy);
	}
	
}
