package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TwitterServiceImp implements TwitterService {

    private CrdRepository crdRepository;

    @Autowired
    public TwitterServiceImp(CrdRepository crdRepository) {
        this.crdRepository = crdRepository;
    }

    @Override
    public void postTweet(String text, Double latitude, Double longitude) {
        // TwitterRestDao twitterRestDao = new TwitterRestDao();
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(longitude);
        coordinates.add(latitude);
        if (validatePostTweet(text, latitude, longitude)) {
            Tweet tweet = new Tweet();
            tweet.setText(text);
            Coordinates coordinates1 = new Coordinates();
            coordinates1.setCoordinates(coordinates);
            tweet.setCoordinates(coordinates1);

                crdRepository.save(tweet);

        } else {
            System.out.println("this tweet is not valid");
        }
    }

    @Override
    public void showTweet(String id, String[] fields) {
        Tweet tweet = (Tweet) crdRepository.findById(id);
        System.out.println(tweet.toString());
    }

    @Override
    public void deleteTweets(String[] ids) {
      Tweet tweet = (Tweet) crdRepository.deleteById(ids[0]);
        System.out.println(tweet.toString());
    }


    public static Boolean validatePostTweet(String text, Double longitude, Double latitude) {
        int length = text.length();
        if(length == 0 ){
            throw new IllegalArgumentException("Not to be empty");
        }
        if (length <= 150 && -180 < longitude && longitude < 180 && -180 < latitude && latitude < 180) {
            return true;
        }
        return false;
    }
}
