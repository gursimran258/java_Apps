package ca.jrvs.apps.twitter.example;

import ca.jrvs.apps.twitter.example.dto.Company;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gdata.util.common.base.PercentEscaper;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.http.HttpParameters;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.DefaultedHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import sun.jvmstat.perfdata.monitor.PerfStringVariableMonitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwitterApiTest {
    private static String CONSUMER_KEY = System.getenv("CONSUMER_KEY");
    private static String CONSUMER_SECRET = System.getenv("CONSUMER_SECRET");
    private static String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
    private static String TOKEN_SECRET = System.getenv("TOKEN_SECRET");

    public static void main(String[] args) throws Exception {
//
        JsonParser jsonParser = new JsonParser();
        Company company = new Company();
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        //create an HTTP GET request
        HttpGet request = new HttpGet("https://api.twitter.com/1.1/users/search.json?q=realDonaldTrump");
       // consumer.sign(request);

        PercentEscaper percentEscaper = new PercentEscaper("", false);
        percentEscaper.escape("hello there");
        HttpPost request1 = new HttpPost("https://api.twitter.com/1.1/statuses/update.json" + percentEscaper);
        consumer.sign(request1);

        Arrays.stream(request1.getAllHeaders()).forEach(System.out::println);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request1);
        System.out.println(EntityUtils.toString(response.getEntity()));

    }
}


