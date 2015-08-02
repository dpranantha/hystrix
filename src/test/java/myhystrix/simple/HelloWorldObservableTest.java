package myhystrix.simple;

import org.junit.Test;

import rx.Observable;

public class HelloWorldObservableTest {

    @Test
    public void testSynchronous() {
        Observable<String> fWorld =  new CommandHelloWorldObservable("World").observe();
        Observable<String> fSong =  new CommandHelloWorldObservable("Song").toObservable();
        fWorld.subscribe((v) -> {
            System.out.println("WOHOO: " + v);
        });
        fSong.subscribe(x -> {
            System.out.println("Wohoo: " + x);
        });
    }
}
