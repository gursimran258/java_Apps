package ca.jrvs.apps.twitter.service;

public interface TwitterService {
    /**
     * Use the arguments to create a tweet object to construct URI to be posted on Twitter
     * @param text
     * @param latitude
     * @param longitude
     */
    void postTweet(String text, Double latitude,
                   Double longitude);

    /**
     * Use the arguments to construct a tweet object to construct URI to get tweets based on ID
     * @param id
     * @param fields optional -did not use it in the implementation of this interface
     */
    void showTweet(String id, String[] fields);


    /**
     * Use the id in arguments to delete the tweet based on id
     * @param ids
     */
    void deleteTweets(String[] ids);
}
