package com.spring;

public class App {
    private Client client;
    private ConsoleEventLogger eventLogger = new ConsoleEventLogger();

    private void logEvent(String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(message);
    }

    public static void main(String[] args) {
        App app = new App();
        app.client = new Client("1", "Oleksandr Roienko");
        app.logEvent("Event for user 1");
    }
}
