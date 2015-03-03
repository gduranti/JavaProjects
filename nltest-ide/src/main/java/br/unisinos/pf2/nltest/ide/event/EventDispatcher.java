package br.unisinos.pf2.nltest.ide.event;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unisinos.pf2.nltest.ide.event.events.Event;

public class EventDispatcher {

	private static final Logger logger = LoggerFactory.getLogger(EventDispatcher.class);

	private static EventDispatcher instance;

	private List<EventListener> listeners;

	public static EventDispatcher getInstance() {
		if (instance == null) {
			logger.debug("Creating dispatcher instance ");
			instance = new EventDispatcher();
			instance.listeners = new ArrayList<>();
		}
		return instance;
	}

	public void registerListener(EventListener listener) {
		logger.debug("Registering listener " + listener.getClass().getName());
		listeners.add(listener);
	}

	public void dispatch(Event event) {

		logger.debug("Dispatching event " + event);

		for (EventListener eventListener : listeners) {
			eventListener.handleEvent(event);
		}
	}

}
