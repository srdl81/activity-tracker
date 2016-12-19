package ams.labs.util;


import ams.labs.exception.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Converter {

    final static Logger logger = LoggerFactory.getLogger(Converter.class);

    public static Long convertToLong(String value) {
        try {
            return Long.valueOf(value);
        } catch (Exception e) {
            logger.error("Could not convert string='%s' to Long");
            throw new InvalidFormatException(String.format("Could not convert string='%s' to Long", value));
        }
    }

}
