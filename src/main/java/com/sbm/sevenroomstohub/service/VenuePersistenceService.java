package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.Venue;

public interface VenuePersistenceService {
    Venue createVenue(Venue venuePayload);
}
