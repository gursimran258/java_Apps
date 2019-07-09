//package ca.jrvs.apps.twitter.spring;
//
//import ca.jrvs.apps.twitter.TwitterCLIRunner;
//import ca.jrvs.apps.twitter.dao.CrdRepository;
//import ca.jrvs.apps.twitter.dao.TwitterRestDao;
//import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
//import ca.jrvs.apps.twitter.service.TwitterService;
//import ca.jrvs.apps.twitter.service.TwitterServiceImp;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//
//public class TwitterCLIBean implements ApplicationContextAware {
//
//    private ApplicationContextAware applicationContextAware;
//
//    public static void main(String[] args) {
//
//        ApplicationContext ac =  new AnnotationConfigApplicationContext(TwitterCLIRunner.class);
//        TwitterCLIRunner twitterCLIRunner = ac.getBean(TwitterCLIRunner.class);
//        twitterCLIRunner.run(args);
////        // Initialize components
////        ApacheHttpHelper apacheHttpHelper = new ApacheHttpHelper();
////        CrdRepository dao = new TwitterRestDao(apacheHttpHelper);
////        TwitterService twitterService = new TwitterServiceImp(dao);
////
////        //Create runner
////        TwitterCLIRunner twitterCLIRunner = new TwitterCLIRunner(twitterService);
////
////        //Run the application
////        twitterCLIRunner.run(args);
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContextAware = applicationContextAware;
//    }
//}
