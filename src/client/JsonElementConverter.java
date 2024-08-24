package client;

import com.beust.jcommander.IStringConverter;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonElementConverter implements IStringConverter<JsonElement> {
    @Override
    public JsonElement convert(String value) {
        return JsonParser.parseString(value);
    }

}
