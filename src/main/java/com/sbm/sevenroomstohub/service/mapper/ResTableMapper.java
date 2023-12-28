package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.ResTable;
import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.service.dto.ResTableDTO;
import com.sbm.sevenroomstohub.service.dto.ReservationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResTable} and its DTO {@link ResTableDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResTableMapper extends EntityMapper<ResTableDTO, ResTable> {
    @Mapping(target = "reservation", source = "reservation", qualifiedByName = "reservationId")
    ResTableDTO toDto(ResTable s);

    @Named("reservationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ReservationDTO toDtoReservationId(Reservation reservation);
}
