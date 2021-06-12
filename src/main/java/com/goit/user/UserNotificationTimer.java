package com.goit.user;

import com.goit.buttons.SendMenuButton;
import com.goit.buttons.SendText;
import com.goit.messages.Messages;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Class that starts a timer and once a day sends notification phrase at a given time.
 * {@link #run(Long, int, int)} (int, int)} starts a timer and takes hours and minutes as arguments
 * stops a timer, and turns notifications off.
 * {@link #notificationTime(int, int)} service method to form a correct Date to use it in timer.schedule()
 */
public class UserNotificationTimer {
    private static final String[][] buttons = new String[][]{
            {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00"},
            {"14:00", "15:00", "16:00", "17:00", "18:00", "19:00"},
            {"Отключить таймер!"}
    };

    private static final long DAY = 1000 * 60 * 60 * 24; //day format in milliseconds (24h)
    private static final String FILEPATH = "/notification_phrases.txt"; //file with phrases to send
    private static final Timer timer = new Timer(); //main timer
    private static final SendText sendText = new SendText();
    private static final SendMenuButton sendMenuButton = new SendMenuButton();
    private static List<String> phrases;

    static {
        try {
            phrases = Files.readAllLines(Path.of(UserNotificationTimer.class.getResource(FILEPATH).toURI()));
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * TimerTask returns a phrase at a given time once in 24 hours.
     */
    public static void sendMenuButton(Long chatId) {
        sendMenuButton.sendMenuButton(chatId, Messages.sendMenuButton(), buttons);
    }

    public static void checkMenuButtonClick(Long chatId, String messageText) {
        if ("Отключить таймер!".equals(messageText)) {
            notificationOff(chatId);
        } else {
            for (String[] button : buttons) {
                for (String timeOnMenuButton : button) {
                    if (timeOnMenuButton.equals(messageText)) {
                        run(chatId, Integer.parseInt(timeOnMenuButton.substring(0, 2)), 0);
                        sendText.sendText(chatId, Messages.clickMenuButton() + timeOnMenuButton);
                    }
                }
            }
        }
    }

    public static void run(Long chatId, int hours, int minutes) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                printNotificationPhrase(chatId);
            }
        };
        timer.schedule(task, notificationTime(hours, minutes), DAY);
    }

    /**
     * Method to turn notifications off. Stops {@link #timer}
     */
    public static void notificationOff(Long chatId) {
        timer.cancel();
        sendText.sendText(chatId, Messages.notificationOff());
    }

    /**
     * Service method to return Date object for {@link #timer} arguments
     *
     * @return Date object
     * @see Date
     */
    private static Date notificationTime(int hours, int minutes) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Calendar result = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), hours, minutes, 0);
        return result.getTime();
    }

    @SneakyThrows
    private static void printNotificationPhrase(long chatId) {
        Random random = new Random();
        sendText.sendText(chatId, phrases.get(random.nextInt(phrases.size())));

    }


}
