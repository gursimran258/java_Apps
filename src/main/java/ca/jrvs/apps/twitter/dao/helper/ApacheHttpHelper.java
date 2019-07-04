package ca.jrvs.apps.twitter.dao.helper;

import com.google.gdata.util.common.base.PercentEscaper;
import com.sun.deploy.net.HttpRequest;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

public class ApacheHttpHelper implements HttpHelper {

    private static String CONSUMER_KEY = System.getenv("CONSUMER_KEY");
    private static String CONSUMER_SECRET = System.getenv("CONSUMER_SECRET");
    private static String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
    private static String TOKEN_SECRET = System.getenv("TOKEN_SECRET");
    OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);

    public ApacheHttpHelper() {
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);
    }


    public HttpResponse httpDelete(URI uri, String id) {
      //  return null;
        HttpPost requestDel  = new HttpPost(uri.toString()+id);
        Arrays.stream(requestDel.getAllHeaders()).forEach(System.out::println);
        HttpClient httpClient = new DefaultHttpClient();
        try {
            consumer.sign(requestDel);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse response = httpClient.execute(requestDel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }


    @Override
    public HttpResponse httpPost(URI uri) {
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        String status = "hi I am there";
        HttpPost request1 = new HttpPost(uri.toString() + percentEscaper.escape(status));
        Arrays.stream(request1.getAllHeaders()).forEach(System.out::println);
        HttpClient httpClient = new DefaultHttpClient();

        try {
            consumer.sign(request1);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }

        try {
            HttpResponse response = httpClient.execute(request1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public HttpResponse httpPost(URI uri, StringEntity stringEntity) {
        return null;
    }

    @Override
    public HttpResponse httpGet(URI uri) {
            HttpGet request = new HttpGet(uri);
        try {
            consumer.sign(request);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpResponse httpResponse = httpClient.execute(request);
            return httpResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void customerSign(HttpRequest request) {
        try {
            consumer.sign(request);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }
    }
}
