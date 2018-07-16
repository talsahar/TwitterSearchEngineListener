package root;
import java.util.Scanner;

public class Main {
/**
 * Listen on twitter traffic, filters tweets by defined track and enqueue the data to Amazon SQS
 *
 * Enter the following params as System property/Environment variable
 * @par "consumer_key" Twitter application consumer key
 * @par "consumer_secret" Twitter application consumer secret
 * @par "access_token" Twitter application access token
 * @par "access_token_secret" Twitter application access token secret
 * @par "filter_keyword" Listener track word
 * @par "sqs_url" Amazon SQS url
 * @par "AWS_ACCESS_KEY_ID"
 * @par "AWS_SECRET_ACCESS_KEY"
 */
    public static void main(String[] args) {
       MainServer server = new MainServer();
       server.startServer();
       System.out.println("Twitter listener is running\nenter 'q' to stop");
        Scanner scanner = new Scanner(System.in);
        while(!scanner.nextLine().equals("q"));
        server.stopServer();
        scanner.close();
    }
}
