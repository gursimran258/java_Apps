package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TwitterRestDaoTest {


    private Tweet expectedTweet = new Tweet();
    private Tweet findTweet = new Tweet();
    private CrdRepository twitterRestDao;
    private Coordinates coordinates = new Coordinates();
    private String id = null;

    @Before
    public void beforeTest() {
        //set up expected tweet
        expectedTweet.setText("This is testing tweet2" + System.currentTimeMillis());
        coordinates.setCoordinates(Arrays.asList(10.0,10.0));
        coordinates.setType("Point");
        expectedTweet.setCoordinates(coordinates);

        //Find the expected tweet


        //set up DAO
        HttpHelper httpHelper = new ApacheHttpHelper();
        twitterRestDao = new TwitterRestDao(httpHelper);
    }

    @After
    public void afterTest() {
        //remove Tweet
        twitterRestDao.deleteById(this.id);
    }

    @Test
    public void testMethod() {
        //call create method
        Tweet actualTweet = (Tweet) twitterRestDao.save(expectedTweet);
        id = actualTweet.getIdStr();
        twitterRestDao.findById(id);
        System.out.println("actual tweet is" + actualTweet.toString());
        assertTweets(expectedTweet,actualTweet);
    }

    public void assertTweets(Tweet expected, Tweet actual) {
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getCoordinates(),actual.getCoordinates());
    }
}