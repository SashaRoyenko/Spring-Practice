package com.spring;

import com.spring.Logger.CacheFileEventLogger;
import com.spring.Logger.EventLogger;
import com.spring.Logger.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger eventLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }


    private void logEvent(EventType type, String msg) {
//        Event e = null;
//        try {
//            e = (Event) event.clone();
//        } catch (CloneNotSupportedException ex) {
//            ex.printStackTrace();
//        }
//        e.setMsg(event.getMsg().replaceAll(client.getId(), client.getFullName()));
//        eventLogger.logEvent(e);
        ConfigurableApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");
        Event event = (Event) ctx.getBean("event");
        event.setMsg(msg);
        EventLogger logger = loggers.get(type);
        if(logger == null){
            logger = (CacheFileEventLogger)ctx.getBean("cacheFileEventLogger");
        }
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        app.logEvent(EventType.ERROR, "Test");
        ctx.close();
    }
}
