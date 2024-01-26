package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.service.dto.ReservationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reservation} and its DTO {@link ReservationDTO}.
 */
@Mapper(componentModel = "spring")
public interface ReservationMapper extends EntityMapper<ReservationDTO, Reservation> {}
