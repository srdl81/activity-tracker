package ams.labs.util;


import java.time.Instant;
import java.util.Date;

public class GeneralUtil {

    public static Date getCurrentDate() {
        return Date.from(Instant.now());
    }
}
