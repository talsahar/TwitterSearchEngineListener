package root;
import com.alibaba.fastjson.JSON;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import metrics.GenericMetrics;
import utils.ProjectProperties;

//set access key & secret key as environment variables
public class SQSSender {

    public static class TwitterSqsObject {
        String url;
        String track;

        public TwitterSqsObject(String url, String track) {
            this.url = url;
            this.track = track;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setTrack(String track) {
            this.track = track;
        }

        public String getTrack() {
            return track;
        }
    }

    private AmazonSQS sqs;
    private String queue_url;
    private GenericMetrics metrics;

    public SQSSender(String queue_url) {
        this.queue_url = queue_url;
        sqs = AmazonSQSClientBuilder.defaultClient();
        String filter_keyword = ProjectProperties.getInstance().getFilter_keyword();
        metrics = new GenericMetrics("TWEETS/MANUFACTOR",
                "FetchCounter", "Track", filter_keyword);
    }

    public void sendMessageWithMetrics(TwitterSqsObject link) {
        String msg = JSON.toJSONString(link);
        this.sendMessage(msg);
        metrics.sendMetricData((double)1);
    }

    public void sendMessage(String msg) {
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queue_url)
                .withMessageBody(msg);
        sqs.sendMessage(send_msg_request);
    }
}