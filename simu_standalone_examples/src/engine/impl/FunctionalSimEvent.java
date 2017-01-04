package fr.enstabretagne.simu.engine.impl;

import java.time.LocalDateTime;
import java.util.function.Consumer;

import fr.enstabretagne.simu.engine.AbstractSimEvent;
import fr.enstabretagne.simu.engine.ISimEngine;
import fr.enstabretagne.simu.engine.ISimEntity;

public class FunctionalSimEvent<E extends ISimEntity> extends AbstractSimEvent<E> {
	
	private final Consumer<ISimEngine> behavior;

	public FunctionalSimEvent(E owner, LocalDateTime time, Consumer<ISimEngine> behavior) {
		super(owner, time);
		this.behavior = behavior;
	}

	@Override
	public void process(ISimEngine engine) {
		behavior.accept(engine);
	}

}
