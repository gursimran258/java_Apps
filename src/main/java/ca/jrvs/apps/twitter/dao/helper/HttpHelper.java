package ca.jrvs.apps.twitter.dao.helper;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.entity.StringEntity;

import java.net.URI;

public interface HttpHelper {
    /**
     *
     * POST the tweet to twitter
     * @param uri to be used to post the tweet
     * @response the response recieved on posting the tweet
     */
    HttpResponse httpPost(URI uri);

    HttpResponse httpPost(URI uri, StringEntity stringEntity);


    /**
     * Show tweets
     * @param uri
     * @return the tweet recieved on http GET
     */
    HttpResponse httpGet(URI uri);

    /**
     * Delete the tweet based on uri
     * @param uri
     * @param id
     * @return the response recieved upon deleting the Tweet
     */
    HttpResponse httpDelete(URI uri, String id);
}
