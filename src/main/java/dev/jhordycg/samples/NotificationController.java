package dev.jhordycg.samples;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.sse.Event;
import io.reactivex.rxjava3.core.Flowable;
import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


@Controller("notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Post
    @Consumes(MediaType.TEXT_PLAIN)
    public void createEvent(@Body String data) {
        System.out.println(data);
        notificationService.send(data);
    }

    @Get("/v2")
    @Produces(MediaType.TEXT_EVENT_STREAM)
    public Publisher<Event<String>> subscribe() {
        return notificationService.listen().map(Event::of);
    }

    @Get("/v3")
    @Produces(MediaType.APPLICATION_JSON_STREAM)
    public Publisher<String> subscribe2() {
        return notificationService.listen();
    }
}
