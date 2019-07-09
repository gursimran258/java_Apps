package ca.jrvs.apps.twitter.dao.helper;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.entity.StringEntity;

import java.net.URI;

public interface HttpHelper {
    /**
     *
     * POST the tweet to twitter
     * @param uri to be used
     */
    HttpResponse httpPost(URI uri);

    HttpResponse httpPost(URI uri, StringEntity stringEntity);

    HttpResponse httpGet(URI uri);

    HttpResponse httpDelete(URI uri, String id);
}
