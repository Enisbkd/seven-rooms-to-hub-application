package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.Venue;
import com.sbm.sevenroomstohub.service.dto.VenueDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Venue} and its DTO {@link VenueDTO}.
 */
@Mapper(componentModel = "spring")
public interface VenueMapper extends EntityMapper<VenueDTO, Venue> {}
