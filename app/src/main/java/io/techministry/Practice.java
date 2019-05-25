package io.techministry;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class Practice {

    public void main(String[] args) throws InterruptedException {

        Disposable d = Observable.just("Hello world!")
                .delay(1, TimeUnit.SECONDS)
                .subscribeWith(new DisposableObserver<String>() {
                    @Override public void onStart() {
                        System.out.println("Start!");
                    }
                    @Override public void onNext(String t) {
                        System.out.println(t);
                    }
                    @Override public void onError(Throwable t) {
                        t.printStackTrace();
                    }
                    @Override public void onComplete() {
                        System.out.println("Done!");
                    }
                });

        Thread.sleep(500);
        // the sequence can now be disposed via dispose()
        d.dispose();
    }


}
