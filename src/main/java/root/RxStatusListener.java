package root;
import rx.Observable;
import rx.subjects.PublishSubject;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;

public class RxStatusListener implements twitter4j.StatusListener {

    private PublishSubject<Status> subscriber;

    public RxStatusListener() {
        subscriber = PublishSubject.create();
    }

    public Observable<Status> asObservable() {
        return subscriber;
    }

    public void onStatus(Status status) {
        subscriber.onNext(status);
    }

    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}

    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}

    public void onScrubGeo(long userId, long upToStatusId) {}

    public void onStallWarning(StallWarning warning) {
        System.out.println("got warning:: "+warning.getMessage());
    }

    public void onException(Exception ex) {
        ex.printStackTrace();
    }
}
