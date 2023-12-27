package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.domain.ClientVenueStats;
import com.sbm.sevenroomstohub.service.dto.ClientDTO;
import com.sbm.sevenroomstohub.service.dto.ClientVenueStatsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClientVenueStats} and its DTO {@link ClientVenueStatsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClientVenueStatsMapper extends EntityMapper<ClientVenueStatsDTO, ClientVenueStats> {
    @Mapping(target = "client", source = "client", qualifiedByName = "clientId")
    ClientVenueStatsDTO toDto(ClientVenueStats s);

    @Named("clientId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ClientDTO toDtoClientId(Client client);
}
