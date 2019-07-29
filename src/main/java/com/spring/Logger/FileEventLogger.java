package com.spring.Logger;

import com.spring.beans.Event;
import org.apache.commons.io.FileUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

    public FileEventLogger(String filename) {
        this.fileName = filename;
    }

    @PostConstruct
    public void init() throws IOException{
        this.file = new File(this.fileName);
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event + "\n",true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
