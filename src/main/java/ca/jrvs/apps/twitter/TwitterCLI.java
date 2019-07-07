package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;

public class TwitterCLI {

    public static void main(String[] args) {

        // Initialize components
        ApacheHttpHelper apacheHttpHelper = new ApacheHttpHelper();
        CrdRepository dao = new TwitterRestDao(apacheHttpHelper);
        TwitterService twitterService = new TwitterServiceImp(dao);

        //Create runner
        TwitterCLIRunner twitterCLIRunner = new TwitterCLIRunner(twitterService);

        //Run the application
        twitterCLIRunner.run(args);
    }
}

