package syndexmx.github.com.tgsiren.services.shutdown;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import syndexmx.github.com.tgsiren.entities.FeedMessage;
import syndexmx.github.com.tgsiren.services.backgroundwebmonitor.NotificationService;
import syndexmx.github.com.tgsiren.utils.Crayon;

import java.time.LocalDateTime;

@Component
@Slf4j
public class GracefulShutdownHandler {

    private final NotificationService notificationService;

    public GracefulShutdownHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @EventListener(ContextClosedEvent.class)
    public void onShutdown() {

        log.info("Performing graceful shutdown action...");
        sendSimpleNotification();
        log.info("Custom shutdown action completed");
    }

    private void sendSimpleNotification() {
        FeedMessage feedMessage = FeedMessage.builder()
                .text("Сервис сейчас останавливается. \n В случае возобновления работы вам нужно будет снова подписаться на уведомления в боте. \n До встречи!")
                .footer(LocalDateTime.now().toString().replace("T", " "))
                .owner("Пока!")
                .build();
        notificationService.notifyAllInterested("*", feedMessage );
        log.info(Crayon.magenta("Subscribers are informed about service termination"));
    }
}