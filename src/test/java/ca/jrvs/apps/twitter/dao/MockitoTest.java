package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
//    @InjectMocks
//    private TwitterServiceImp serviceImp;
//    @Mock
//    private CrdRepository mockDao;

    @Test
    public void postTweet()  {
        // @Mock @InjectMocks;
        CrdRepository mockDao = Mockito.mock(CrdRepository.class);
        TwitterService serviceImp = new TwitterServiceImp(mockDao);
        Tweet mockTweet = new Tweet();
        mockTweet.setText("this is a fake tweet");
        Coordinates coordinates = new Coordinates();
        List<Double> coordinates1 = new ArrayList<>();
        coordinates1.add(10.0);
        coordinates1.add(10.0);
        coordinates.setCoordinates(coordinates1);
        mockTweet.setCoordinates(coordinates);
        when(mockDao.save(any())).thenReturn(mockTweet);
        serviceImp.postTweet("some tweet", 10.0, 10.0);
        try {
            serviceImp.postTweet("", 10.0, 10.0);
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }
}
