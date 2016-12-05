package marti.com.example.exampleapp.dataaccess.deserializer;

/**
 * Created by mferrando on 02/06/16.
 */

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.TimeZone;

import marti.com.example.exampleapp.utils.UtilsDate;

    /**
        * GSON data deserializer for ONE date format.
        */
public class DateTypeDeserializer implements JsonDeserializer<Date> {

    // Setup your main date type here yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    @Override
    public Date deserialize(JsonElement jsonElement, Type typeOF, JsonDeserializationContext context)
            throws JsonParseException {

        Date date = UtilsDate.getDateFromString(jsonElement.getAsString(), DATE_FORMAT);
        return UtilsDate.convertTimeZone(date, TimeZone.getTimeZone("UTC"), TimeZone.getDefault());
    }
    }