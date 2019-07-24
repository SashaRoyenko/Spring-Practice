package com.spring;

import com.spring.Logger.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }


    private void logEvent(Event event) {
        Event e = null;
        try {
            e = (Event) event.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        e.setMsg(event.getMsg().replaceAll(client.getId(), client.getFullName()));
        eventLogger.logEvent(e);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        Event event = (Event) ctx.getBean("event");
        event.setMsg("Event for user 1");
        app.logEvent(event);
        event.setMsg("Event for user 2");
        app.logEvent(event);
        event.setMsg("Event for user 3");
        app.logEvent(event);
        event.setMsg("Event for user 4");
        app.logEvent(event);
        ctx.close();
    }
}
