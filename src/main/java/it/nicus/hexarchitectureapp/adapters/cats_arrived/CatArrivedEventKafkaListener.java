package it.nicus.hexarchitectureapp.adapters.cats_arrived;

import it.nicus.hexarchitectureapp.core.CatRegistration;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Adapter listening to a Kafka topic,
 * receiving Cat Registered events and forwarding it to the Cat Registration service
 *
 * (Primary Adapter)
 */
@Service
public class CatArrivedEventKafkaListener {

    // FIXME Configure ConsumerFactory to use JSON deserialiser (see https://github.com/SpringOnePlatform2016/grussell-spring-kafka/blob/master/s1p-kafka/src/main/java/org/s1p/JsonConfiguration.java#L66)

    private final CatRegistration registrationService;

    public CatArrivedEventKafkaListener(CatRegistration registrationService) {
        this.registrationService = registrationService;
    }



    @KafkaListener(topics = {"cat-arrived"})
    public void onMessage(KafkaCatArrivedEvent kafkaRecord) {
        registrationService.newCatArrived( toCatArrivedEvent( kafkaRecord ) );
    }

    /**
     * DTO for the Kafka message
     */
    @Data @NoArgsConstructor
    public static class KafkaCatArrivedEvent {
        private String name;
        private LocalDate dob;
    }

    /**
     * Maps Kafka DTO to the Domain event
     */
    private final CatRegistration.CatArrivedEvent toCatArrivedEvent(KafkaCatArrivedEvent kafkaEvent) {
        return CatRegistration.CatArrivedEvent.builder()
                .catName(kafkaEvent.getName())
                .dateOfBirth(kafkaEvent.getDob())
                .build();
    }
}
