package ca.jrvs.apps.twitter.example;

import ca.jrvs.apps.twitter.example.dto.Company;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;

public class JsonParser {
    /**
     * Convert a java object to JSON string
     * @param object input object
     * @return JSON String
     * @throws JsonProcessingException
     *
     */
    public static String toJson(Object object, boolean
            prettyJson, boolean includeNullValues) throws
            JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);
        return json;

    }

//    public static String toJsonn() throws
//            IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        File file = new File("/Users/gursimran/IdeaProjects/java_apps/java_applications/json_files/company.json");
//        Company company = objectMapper.readValue( file, Company.class);
//        System.out.println("company" + company);
//        return company.toString();
//    }


    /**
     * Parse JSON string to a object
     * @param json JSON str
     * @param clazz object class
     * @param <T> Type
     * @return Object
     * @throws IOException
     */
    public static <T> T toObjectFromJson(String json,
                                         Class clazz) throws IOException {


           ObjectMapper objectMapper = new ObjectMapper();
           File file = new File(json);
        //    clazz clz =  objectMapper.readValue(file, clazz.class);



    }




}
