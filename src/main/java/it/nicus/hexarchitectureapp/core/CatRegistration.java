package it.nicus.hexarchitectureapp.core;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;

public interface CatRegistration {

    void newCatArrived(CatArrivedEvent catArrivedEvent);


    @Getter @Builder(toBuilder = true)
    class CatArrivedEvent {
        @NonNull private final String catName;
        private final LocalDate dateOfBirth;
    }
}
