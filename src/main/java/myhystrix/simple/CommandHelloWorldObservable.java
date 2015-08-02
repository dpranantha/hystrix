package myhystrix.simple;

import static com.atlassian.fugue.Option.none;
import rx.Observable;
import rx.Subscriber;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

public class CommandHelloWorldObservable extends HystrixObservableCommand<String> {

    private final String name;

    protected CommandHelloWorldObservable(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("HelloWorldObservable"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        //rest-api call?
                        observer.onNext("Hello");
                        observer.onNext(name + "!");
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }

    @Override
    protected Observable<String> resumeWithFallback() {
        return Observable.just("Hello Failure "+ name);
    }
}
