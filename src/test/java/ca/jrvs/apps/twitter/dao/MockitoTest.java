package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
    @InjectMocks
    private TwitterServiceImp serviceImp;
    @Mock
    private CrdRepository mockDao;

    @Test
    public void postTweet() {
        // @Mock @InjectMocks;
        Tweet mockTweet = new Tweet();
        mockTweet.setText("this is a fake tweet");
        when(mockDao.save(any())).thenReturn(mockTweet);

        serviceImp.postTweet("some tweet", 10.0, 10.0);


        try {
            serviceImp.postTweet("", 10.0, 10.0);
         //   fail();
        } catch (IllegalArgumentException e) {
           // e.printStackTrace();
        }

    }
}
