package dev.jhordycg.samples;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import jakarta.inject.Singleton;

@Singleton
public class NotificationService {
    private final Subject<String> subject = PublishSubject.create();

    public void send(String message) {
        subject.onNext(message);
    }

    public Flowable<String> listen() {
        return subject.toFlowable(BackpressureStrategy.BUFFER);
    }


}
