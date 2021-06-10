package com.goit.telegrambot;

import lombok.SneakyThrows;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class UpdateDataBaseTimer {
    public static void updateDataBase(){
        new RunUpdate().start();
    }
    static class RunUpdate extends Thread{
        int hours = 4; // Set hours to upgrade.
        int minutes = 3; // Set minutes to upgrade..
        @SneakyThrows
        @Override
        public void run() {
            int wl = 0;
            while (wl == 0) {
                Calendar calendar = new GregorianCalendar();
                if (calendar.get(Calendar.HOUR) == hours && calendar.get(Calendar.MINUTE) == minutes){
                    UserList.getUsers().forEach((k,v) -> v.getLearningBlock().fillQuestions());
                }
                Thread.sleep(60000);
            }
        }
    }
}
