package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import sun.security.provider.PolicySpiFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TwitterRestDao implements CrdRepository<Tweet, String> {

    //URI contants
    public static final String API_URI = "https://api.twitter.com";
    public static final String POST_PATH = "/1.1/statuses/update.json";
    public static final String SHOW_PATH = "/1.1/statuses/show.json";
    public static final String DELETE_PATH = "/1.1/statuses/destroy";

    //URI symbols
    public static final String QUERY_SUM = "?";
    public static final String AMPERSAND = "&";
    public static final String EQUAL = "=";

    //Response code
    public static final int HTTP_OK  = 200;

    HttpHelper httpHelper;

    public TwitterRestDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    /**
     * This method is basically to create tweets
     */
    public Tweet save(Tweet entity) {
//        String str = entity.getText();
//        PercentEscaper percentEscaper = new PercentEscaper("", false);
//        percentEscaper.escape(str);
//        URI uri = URI.create("https://api.twitter.com/1.1/statuses/update.json?status=" + str);
//        HttpResponse response = httpHelper.httpPost(uri);
//        return toTweetObject(response);
        //Construct URI
        URI uri;
        try{
        uri = getPostURI(entity);
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Invalid Tweet Input", e);
        }
        //Execute HTTP Request
        HttpResponse response = httpHelper.httpPost(uri);
        //Validate response and deser resoponse
        return parseResponseBody(response, HTTP_OK);
    }

    @Override
    public Tweet findById(String id) {
        //construct uri
        URI uri;
        try {
            uri = getShowURI(id);
        } catch (URISyntaxException e) {
            throw  new IllegalArgumentException("unable to construct URI", e);
        }


        //excecute http
        HttpResponse response;
        response = httpHelper.httpGet(uri);
        return parseResponseBody(response, HTTP_OK);
        //validate response and deser response to tweet object
//        URI uri = URI.create("https://api.twitter.com/1.1/statuses/show.json?id=" + s);
//        HttpResponse response = httpHelper.httpGet(uri);
//        return toTweetObject(response);
    }

    @Override
    public Tweet deleteById(String id) {
//        URI uri = URI.create("https://api.twitter.com/1.1/statuses/destroy/" + s + ".json");
//        HttpResponse response = httpHelper.httpDelete(uri, s);
//        return toTweetObject(response);

        //Construct URI
        URI uri;
       try{
           uri = getDeleteURI(id);
       } catch (URISyntaxException e) {
           throw new IllegalArgumentException("Unable to construct uri", e);
       }

       //Execute HTTP request
        HttpResponse response;
       response = httpHelper.httpPost(uri);

       //Validate response and deser response
        return parseResponseBody(response, HTTP_OK);
    }

    /**
     *
     * Delete URI builder
     * @return
     */
    protected URI getDeleteURI(String id) throws  URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append(API_URI)
                .append(DELETE_PATH)
                .append("/")
                .append(id)
                .append(".json");

        return new URI(sb.toString());
    }

    public Tweet toTweetObject(HttpResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Tweet tweet = objectMapper.readValue(response.getEntity().getContent(), Tweet.class);
            System.out.println(tweet.toString());
            return tweet;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected  URI getShowURI(String id) throws
            URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append(API_URI)
                .append(SHOW_PATH)
                .append(QUERY_SUM);

        appendQueryParam(sb, "id", id, true);
        return new URI(sb.toString());
    }


    /**
     *
     * Create POST URI
     * @param tweet
     * @return
     * @throws URISyntaxException
     * @throws UnsupportedEncodingException
     */

    protected URI getPostURI(Tweet tweet) throws URISyntaxException, UnsupportedEncodingException {
        String text = tweet.getText();
        Double longitude = tweet.getCoordinates().getCoordinates().get(0);
        Double latitude = tweet.getCoordinates().getCoordinates().get(1);

        StringBuilder sb = new StringBuilder();
        sb.append(API_URI)
                .append(POST_PATH)
                .append(QUERY_SUM);
        appendQueryParam(sb, "status", URLEncoder.encode(text, StandardCharsets.UTF_8.name()),true);
        appendQueryParam(sb, "long", longitude.toString(), false);
        appendQueryParam(sb, "lat", latitude.toString(), false);

        return new URI(sb.toString());
    }


    /**
     * helper function to append one query parameter
     * @param sb
     * @param key
     * @param value
     * @param firstParam
     */
    private void appendQueryParam(StringBuilder sb, String key, String value, Boolean firstParam)
    {
        if(!firstParam) {
            sb.append(AMPERSAND);
        }
        sb.append(key)
                .append(EQUAL)
                .append(value);
    }

    /**
     * ES
     * Check response status code and convert the response into tweet.
     */

    protected Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
        Tweet tweet = null;

        //Check Repsonse status
        int status = response.getStatusLine().getStatusCode();
        if(status != expectedStatusCode) {
            throw  new RuntimeException("Unexpected HTTP status:" + status);
        }

        if(response.getEntity() == null) {
            throw  new RuntimeException("Empty response body");
        }

        //Convert Response Entity to str
        String jsonStr;
        try{
            jsonStr = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert entity");
        }

        //Deser JSON string to Tweet object
        try{
            tweet = (Tweet) toObjectFromJson(jsonStr, Tweet.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert entity");
        }
    return tweet;
    }



    /**
     * Parse JSON string to a object
     * @param json JSON str
     * @param clazz object class
     * @param <T> Type
     * @return Object
     * @throws IOException
     */
    public static <T> T toObjectFromJson(String json, Class clazz) throws IOException {
        ObjectMapper m = new ObjectMapper();
        //m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T) m.readValue(json, clazz);
    }

}

