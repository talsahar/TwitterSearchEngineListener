package utils;

public class ProjectProperties {

    private static ProjectProperties instance = new ProjectProperties();
    private String consumer_secret;
    private String access_token;
    private String access_token_secret;
    private String filter_keyword;
    private String consumer_key;
    private String sqs_url;

    private ProjectProperties() {
        if((consumer_key = System.getProperty("consumer_key")) == null) {
            consumer_key = System.getenv("consumer_key");
        }

        if((consumer_secret = System.getProperty("consumer_secret")) == null) {
            consumer_secret = System.getenv("consumer_secret");
        }

        if((access_token = System.getProperty("access_token")) == null) {
            access_token = System.getenv("access_token");
        }

        if((access_token_secret = System.getProperty("access_token_secret")) == null) {
            access_token_secret = System.getenv("access_token_secret");
        }

        if((filter_keyword = System.getProperty("filter_keyword")) == null) {
            filter_keyword = System.getenv("filter_keyword");
        }

        if((sqs_url = System.getProperty("sqs_url")) == null) {
            sqs_url = System.getenv("sqs_url");
        }
    }

    public static ProjectProperties getInstance() {
        return instance;
    }

    public String getConsumer_secret() {
        return consumer_secret;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getAccess_token_secret() {
        return access_token_secret;
    }

    public String getFilter_keyword() {
        return filter_keyword;
    }

    public String getConsumer_key() {
        return consumer_key;
    }

    public String getSqs_url() {
        return sqs_url;
    }
}
