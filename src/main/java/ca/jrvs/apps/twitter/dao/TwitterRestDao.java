package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.apache.http.HttpResponse;

import java.net.URI;

public class TwitterRestDao implements CrdRepository <Tweet, String > {

    ApacheHttpHelper helper = new ApacheHttpHelper();
    @Override
    /**
     * This method is basically to create tweets
     */
    public Tweet save(Tweet entity) {
        return null;
    }

    @Override
    public Tweet findById(String s) {
        //construct uri
        //excecute http
        //validate response and deser response to tweet object
        URI uri = URI.create("https://api.twitter.com/1.1/statuses/show.json?id=" + s);
        HttpResponse response =  helper.httpGet(uri);
        return null;
    }

    @Override
    public Tweet deleteById(String s) {
        return null;
    }




}
