package com.goit.services;

import com.goit.repository.UserList;
import lombok.SneakyThrows;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UpdateDataBaseTimer {

    public static void updateDataBase(){
        new RunUpdate().start();
    }
    static class RunUpdate extends Thread{
        int hours = 0; // Set hours to upgrade.
        int minutes = 0; // Set minutes to upgrade..

        /**
         * update base of questions
         */
        @SneakyThrows
        @Override
        public void run() {
//            int wl = 0;
//            while (wl == 0) {
//                Calendar calendar = new GregorianCalendar();
//                if (calendar.get(Calendar.HOUR) == hours && calendar.get(Calendar.MINUTE) == minutes){
//                    UserList
//                            .getUsers()
//                            .forEach((k, v) -> v.getLearningBlock().fillQuestions());
//                }
//                Thread.sleep(1000*60*60*1);
//            }
        }
    }
}
