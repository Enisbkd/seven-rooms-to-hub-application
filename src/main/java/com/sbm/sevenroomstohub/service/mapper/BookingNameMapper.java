package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.BookingName;
import com.sbm.sevenroomstohub.domain.ClientVenueStats;
import com.sbm.sevenroomstohub.service.dto.BookingNameDTO;
import com.sbm.sevenroomstohub.service.dto.ClientVenueStatsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BookingName} and its DTO {@link BookingNameDTO}.
 */
@Mapper(componentModel = "spring")
public interface BookingNameMapper extends EntityMapper<BookingNameDTO, BookingName> {
    @Mapping(target = "clientVenueStats", source = "clientVenueStats", qualifiedByName = "clientVenueStatsId")
    BookingNameDTO toDto(BookingName s);

    @Named("clientVenueStatsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ClientVenueStatsDTO toDtoClientVenueStatsId(ClientVenueStats clientVenueStats);
}
