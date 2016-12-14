package fr.ensta.simu.examples.ping;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

import fr.ensta.simu.logger.impl.SysOutLogger;
import fr.enstabretagne.simu.engine.impl.BasicSimEngine;

public class PingMonitor {

	
	
	public static void main(String [] args) {
		final Random rand = new Random(LocalDateTime.now().getNano());
		
		PingEntity ping = new PingEntity("Alice");
		ping.setLatencySupplier(() -> Duration.ofMinutes(15));
		PingEntity pong = new PingEntity("Bob");
		pong.setLatencySupplier(() -> Duration.ofMinutes(5 + rand.nextInt(25)));
		ping.setTarget(pong);
		pong.setTarget(ping);
		
		final LocalDateTime startTime = LocalDateTime.of(0, 1, 1, 0, 0);
		final Duration duration = Duration.ofHours(6);
		
		BasicSimEngine engine = new BasicSimEngine();
		engine.getLoggerHub().addLogger(new SysOutLogger());
		engine.initialize(startTime);
		ping.sendPing(engine);
		
		engine.processEventsUntil(startTime.plus(duration));
		engine.getLoggerHub().terminate();
	}
	
}
