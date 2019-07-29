package com.spring.beans;

import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

public class Event implements Cloneable{
//    private DateFormat dateFormat;
    private int id;
    private String msg;
    private Date date;


    public Event(Date date) {
        Random random = new Random();
        this.id = random.nextInt(100);
        this.date = date;
    }



//    public Event(Date date, DateFormat dateFormat){
//        this.date = date;
//        this.dateFormat = dateFormat;
//    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static boolean isDay(){
        return LocalTime.now().getHour() >= 8 && LocalTime.now().getHour() <= 17;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}
