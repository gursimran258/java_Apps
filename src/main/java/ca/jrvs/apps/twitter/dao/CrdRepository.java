package ca.jrvs.apps.twitter.dao;

public interface CrdRepository <T, ID> {
    /**
     * Creating a new tweet to be posted on twitter
     * @param entity of type twitter, the object to be posted on Twitter
     * @return the response that tweet is created on Twitter
     */
    T save(T entity);


    /**
     * Finding the tweets o the timeline based on their ID
     * @param id that needs to be found on twitter
     * @return the tweet found based on id
     */
    T findById(ID id);


    /**
     * Delete tweets based on id
     * @param id of tweet to be deleted on twitter
     * @return the response
     */
    T deleteById(ID id);

}
