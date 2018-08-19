package it.nicus.hexarchitectureapp.core.logic;

import it.nicus.hexarchitectureapp.core.ports.CatDirectory;
import it.nicus.hexarchitectureapp.core.ports.CatRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Implementation of Cat Registration,
 * reacts to a Cat Arrived event creating a new Cat record in the Directory.
 *
 * (Core Logic)
 */
@Service
public class CatRegistrationImpl implements CatRegistration {

    private final CatDirectory directoryService;

    @Autowired
    public CatRegistrationImpl(CatDirectory directoryService) {
        this.directoryService = directoryService;
    }

    @Override
    public void newCatArrived(CatArrivedEvent catArrivedEvent) {
        final String catName = catArrivedEvent.getCatName();

        // Check if the Cat is already registered
        if (!directoryService.findCatByName(catName).isPresent()) {
            // If not registered, create an entry in the Directory
            final Instant registeredAt = Instant.now();
            directoryService.registerNewCat( toRegistrationRequest( catArrivedEvent, registeredAt ));
        }
    }

    // Maps domain object (an event) to another domain object
    private static CatDirectory.CatRegistrationRequest toRegistrationRequest(CatArrivedEvent catArrivedEvent, Instant registeredAt) {
        return CatDirectory.CatRegistrationRequest.builder()
                .name( catArrivedEvent.getCatName() )
                .dateOfBirth( catArrivedEvent.getDateOfBirth())
                .registeredAt( registeredAt )
                .build();
    }

    private boolean isAlreadyRegistered(String catName) {
        return directoryService.findCatByName(catName).isPresent();
    }
}
