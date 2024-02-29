package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.Venue;
import com.sbm.sevenroomstohub.service.VenuePersistenceService;
import com.sbm.sevenroomstohub.service.VenueService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class VenuePersistenceServiceImpl implements VenuePersistenceService {

    private final Logger logger = LoggerFactory.getLogger(VenuePersistenceServiceImpl.class);

    @Autowired
    VenueService venueService;

    public Venue createVenue(Venue venuePayload) {
        String venueId = venuePayload.getVenueId();
        Optional<Venue> clientFromDB = venueService.findByVenueId(venueId);
        if (!clientFromDB.isPresent()) {
            logger.debug("Venue with venueId " + venueId + " does not exist in DB , Inserting ...");
            venueService.save(venuePayload);
            return venuePayload;
        } else {
            logger.debug("Venue with venueId " + venueId + " already exists in DB , Aborting ...");
            return null;
        }
    }
}
