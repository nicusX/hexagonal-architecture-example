package it.nicus.hexarchitectureapp.core.ports;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;

/**
 * Interface to Cat Registration,
 * reacting to "Cat Arrived" events.
 *
 * (Primary Port)
 */
public interface CatRegistration {

    void newCatArrived(CatArrivedEvent catArrivedEvent);


    /**
     * Domain object representing the "Cat Arrived" event
     */
    @Getter @Builder(toBuilder = true)
    class CatArrivedEvent {
        @NonNull private final String catName;
        private final LocalDate dateOfBirth;
    }
}
