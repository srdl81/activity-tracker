package ams.labs.util;


import ams.labs.exception.InvalidFormatException;
import ams.labs.model.IdNamn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Converter {

    final static Logger logger = LoggerFactory.getLogger(Converter.class);

    public static Long convertToLong(IdNamn profession) {

        try {
            return new Long(profession.getId());
        } catch (Exception e) {
            logger.error("Could not convert string='%s' to Long");
            throw new InvalidFormatException(String.format("Could not convert string='%s' to Long", profession.getId()));
        }

    }

}
