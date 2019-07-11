package ca.jrvs.apps.twitter.example;

import ca.jrvs.apps.twitter.example.dto.Company;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class JsonParser {
    /**
     * Convert a java object to JSON string
     *
     * @param object input object
     * @return JSON String
     * @throws JsonProcessingException
     */
    public static String toJson(Object object, boolean
            prettyJson, boolean includeNullValues) throws
            JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if (!includeNullValues)
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        if (prettyJson)
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

        return mapper.writeValueAsString(mapper);
    }

    /**
     * Parse JSON string to a object
     *
     * @param json  JSON str
     * @param clazz object class
     * @param <T>   Type
     * @return Object
     * @throws IOException //
     */
    public static <T> T toObjectFromJson(String json,
                                         Class clazz) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(json);
        //  objectMapper.readValue(file, clazz.class);
        return (T) objectMapper.readValue(json, clazz);
    }
}




