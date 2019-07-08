package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TwitterCLIRunner {

    private TwitterService twitterService;
    @Autowired
    public TwitterCLIRunner(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @Bean
    public void run(String[] args) {

        if (args.length < 2) {
            throw new RuntimeException("usage with TwitterCLI");
        }

        switch (args[0].toLowerCase()) {
            case "post":
                postTweets(args);
                break;
            case "get":
                showTweets(args);
                break;
            case "delete":
                deleteTweets(args);
                break;
            default:
                System.out.println("Not proper usage");
                break;

        }
    }

    @Bean
    protected void postTweets(String[] args) {
        Double latitude = 0.0;
        Double longitude = 0.0;
        String status = args[1];
        if (args[2] == null && args[3] == null) {
            latitude = 0.0;
            longitude = 0.0;

        } else {
            String arguments[] = args[2].split(":");
            latitude = Double.parseDouble(arguments[0]);
            longitude = Double.parseDouble(arguments[1]);
        }
        twitterService.postTweet(status, latitude, longitude);

    }
    @Bean
    protected void showTweets(String[] args) {
        String id = args[1];
        String[] fields = null;
        twitterService.showTweet(id, fields);

    }
    @Bean
    protected void deleteTweets(String[] args) {
        String tweetIds = args[1];
        String[] ids = null;
        ids[0] = tweetIds;
        twitterService.deleteTweets(ids);
    }
}
