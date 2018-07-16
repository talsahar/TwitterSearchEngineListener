package root;
import twitter4j.TwitterStream;
import utils.MyTwitterStreamFactory;
import utils.ProjectProperties;
import java.util.ArrayList;
import java.util.Arrays;

public class MainServer {

    TwitterStream stream;
    String filter_keyword;
    SQSSender sqs_sender;
    String sqs_url;
    RxStatusListener listener;

    public MainServer() {
        filter_keyword = ProjectProperties.getInstance().getFilter_keyword();
        sqs_url = ProjectProperties.getInstance().getSqs_url();
        sqs_sender = new SQSSender(sqs_url);
        listener = new RxStatusListener();
    }

    public void startServer() {
        listener.asObservable()
                    .map(status -> status.getURLEntities())
                    .map(urls -> new ArrayList<>(Arrays.asList(urls)))
                    .flatMapIterable(ids -> ids)
                    .map(urlEntity -> new SQSSender.TwitterSqsObject(urlEntity.getExpandedURL(), filter_keyword))
                    .subscribe(tweet_str -> sqs_sender.sendMessageWithMetrics(tweet_str),
                        error-> error.printStackTrace());

        stream = MyTwitterStreamFactory.newStreamInstance();
        stream.addListener(listener);
        stream.filter(filter_keyword);
        stream.sample();
        }

    public void stopServer() {
        stream.shutdown();
    }
}
