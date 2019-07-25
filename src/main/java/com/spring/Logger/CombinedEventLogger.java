package com.spring.Logger;

import com.spring.Event;

import java.util.Collection;

public class CombinedEventLogger implements EventLogger{
    private Collection<EventLogger> eventLoggers;

    public CombinedEventLogger(Collection<EventLogger> eventLoggers) {
        this.eventLoggers = eventLoggers;
    }

    @Override
    public void logEvent(Event event) {
        eventLoggers.forEach((e) -> e.logEvent(event));
    }
}
