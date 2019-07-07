package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.service.TwitterService;

public class TwitterCLIRunner {

    private TwitterService twitterService;

    public TwitterCLIRunner(TwitterService twitterService) {
        this.twitterService = twitterService;
    }


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

    protected void postTweets(String[] args) {
        Double latitude = 0.0;
        Double longitude = 0.0;
        String status = args[1];
        if (args[2] == null && args[3] == null) {
            latitude = 0.0;
            longitude = 0.0;

        } else {
            latitude = Double.parseDouble(args[2]);
            longitude = Double.parseDouble(args[3]);
        }
        twitterService.postTweet(status, latitude, longitude);

    }

    protected void showTweets(String[] args) {
        String id = args[1];
        String[] fields = null;
        twitterService.showTweet(id, fields);

    }

    protected void deleteTweets(String[] args) {
        String tweetIds = args[1];
        String[] ids = null;
        ids[0] = tweetIds;
        twitterService.deleteTweets(ids);
    }
}
