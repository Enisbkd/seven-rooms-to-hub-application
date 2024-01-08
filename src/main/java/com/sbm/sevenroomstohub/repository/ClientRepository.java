package com.sbm.sevenroomstohub.repository;

import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.service.dto.ClientDTO;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Client entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByClientId(String ClientId);
}
