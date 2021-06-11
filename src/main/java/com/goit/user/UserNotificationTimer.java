package com.goit.user;

import lombok.SneakyThrows;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Class that starts a timer and once a day sends notification phrase at a given time.
 * {@link #run(int, int)} starts a timer and takes hours and minutes as arguments
 * {@link #notificationOff()} stops a timer, and turns notifications off.
 * {@link #notificationTime(int, int)} service method to form a correct Date to use it in timer.schedule()
 */
public class UserNotificationTimer {

    private static final long DAY = 1000 * 60 * 60 * 24; //day format in milliseconds (24h)
    private static final String FILEPATH = "src/main/notification_phrases.txt"; //file with phrases to send
    private static final Timer timer = new Timer(); //main timer

    /**
     * TimerTask returns a phrase at a given time once in 24 hours.
     */
    public static void run(int hours, int minutes) {
        TimerTask task = new TimerTask() {
            @Override
            public void run(){
                printNotificationPhrase();
            }
        };
        timer.schedule(task, notificationTime(hours, minutes), DAY);
    }

    /**
     * Method to turn notifications off. Stops {@link #timer}
     */
    public static void notificationOff(){
        timer.cancel();
    }

    /**
     * Service method to return Date object for {@link #timer} arguments
     * @return Date object
     * @see Date
     */
    private static Date notificationTime(int hours, int minutes){
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 1);
        Calendar result = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE), hours, minutes);
        return result.getTime();
    }


    @SneakyThrows
    private static void printNotificationPhrase(){
        List<String> phrases = Files.readAllLines(Path.of(FILEPATH));
        Random random = new Random();
        System.out.println(phrases.get(random.nextInt(phrases.size())));

    }


}
