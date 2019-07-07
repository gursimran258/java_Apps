package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TwitterServiceImp implements TwitterService {

    private CrdRepository crdRepository;

    public TwitterServiceImp(CrdRepository crdRepository) {
        this.crdRepository = crdRepository;
    }

    @Override
    public void postTweet(String text, Double latitude, Double longitude) {
        // TwitterRestDao twitterRestDao = new TwitterRestDao();
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(longitude);
        coordinates.add(latitude);

        Tweet tweet = new Tweet();
        tweet.setText(text);
        Coordinates coordinates1= new Coordinates();
        coordinates1.setCoordinates(coordinates);
        tweet.setCoordinates(coordinates1);
        crdRepository.save(tweet);
    }

    @Override
    public void showTweet(String id, String[] fields) {
//        TwitterRestDao twitterRestDao = new TwitterRestDao();
//        Tweet tweet = twitterRestDao.findById(id);
//        System.out.println(tweet.toString());
        Tweet tweet = new Tweet();
        crdRepository.findById(id);
    }

    @Override
    public void deleteTweets(String[] ids) {
        crdRepository.deleteById(ids[0]);
//    TwitterRestDao twitterRestDao = new TwitterRestDao();
//    Tweet tweet = twitterRestDao.deleteById(ids[0]);
    }
}
