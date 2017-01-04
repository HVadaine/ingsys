package fr.ensta.simu.logger.impl;

import fr.ensta.simu.logger.AbstractLogger;

public class SysOutLogger extends AbstractLogger {

	@Override
	public void log() {
		System.out.println(this);
	}

	@Override
	public void terminate() {
		// Nothing to do
	}

}
