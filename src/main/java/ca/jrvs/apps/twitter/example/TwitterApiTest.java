package ca.jrvs.apps.twitter.example;

import com.google.gdata.util.common.base.PercentEscaper;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;

public class TwitterApiTest {
    private static String CONSUMER_KEY = System.getenv("CONSUMER_KEY");
    private static String CONSUMER_SECRET = System.getenv("CONSUMER_SECRET");
    private static String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
    private static String TOKEN_SECRET = System.getenv("TOKEN_SECRET");

    public static void main(String[] args) throws Exception {
//
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        String status = "210462857140252672";
        String uri1 = "https://api.twitter.com/1.1/statuses/show.json?id="+status;
        //create an HTTP GET request
         HttpGet request = new HttpGet(uri1);
       consumer.sign(request);


        //HTTP post request
        PercentEscaper percentEscaper = new PercentEscaper("", false);
      //  String status = "hi I am there";
        String uri = "https://api.twitter.com/1.1/statuses/update.json?status=";
        HttpPost request1 = new HttpPost("https://api.twitter.com/1.1/statuses/update.json?status="+percentEscaper.escape(status));
        consumer.sign(request1);

        Arrays.stream(request1.getAllHeaders()).forEach(System.out::println);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));

    }
}


