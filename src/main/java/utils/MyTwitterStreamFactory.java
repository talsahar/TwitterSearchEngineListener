package utils;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import utils.ProjectProperties;

public class MyTwitterStreamFactory {

    private static Configuration buildConfigurationBuilder() {
        String consumer_key = ProjectProperties.getInstance().getConsumer_key();
        String consumer_secret = ProjectProperties.getInstance().getConsumer_secret();
        String access_token = ProjectProperties.getInstance().getAccess_token();
        String access_token_secret = ProjectProperties.getInstance().getAccess_token_secret();

        ConfigurationBuilder cb = new ConfigurationBuilder();
                cb.setOAuthConsumerKey(consumer_key)
                .setOAuthConsumerSecret(consumer_secret)
                .setOAuthAccessToken(access_token)
                .setOAuthAccessTokenSecret(access_token_secret);
        cb.setDebugEnabled(true);
        return cb.build();
    }

    public static TwitterStream newStreamInstance() {
        Configuration conf = buildConfigurationBuilder();
        twitter4j.TwitterStreamFactory tsf = new twitter4j.TwitterStreamFactory(conf);
        return tsf.getInstance();
    }

    public static Twitter newCoreInstance() {
        Configuration conf = buildConfigurationBuilder();
        TwitterFactory tf = new TwitterFactory(conf);
        return tf.getInstance();
    }
}
