package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.Venue;
import com.sbm.sevenroomstohub.repository.VenueRepository;
import com.sbm.sevenroomstohub.service.VenueService;
import com.sbm.sevenroomstohub.service.dto.VenueDTO;
import com.sbm.sevenroomstohub.service.mapper.VenueMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.Venue}.
 */
@Service
@Transactional
public class VenueServiceImpl implements VenueService {

    private final Logger log = LoggerFactory.getLogger(VenueServiceImpl.class);

    private final VenueRepository venueRepository;

    private final VenueMapper venueMapper;

    public VenueServiceImpl(VenueRepository venueRepository, VenueMapper venueMapper) {
        this.venueRepository = venueRepository;
        this.venueMapper = venueMapper;
    }

    @Override
    public VenueDTO save(VenueDTO venueDTO) {
        log.debug("Request to save Venue : {}", venueDTO);
        Venue venue = venueMapper.toEntity(venueDTO);
        venue = venueRepository.save(venue);
        return venueMapper.toDto(venue);
    }

    @Override
    public VenueDTO update(VenueDTO venueDTO) {
        log.debug("Request to update Venue : {}", venueDTO);
        Venue venue = venueMapper.toEntity(venueDTO);
        venue = venueRepository.save(venue);
        return venueMapper.toDto(venue);
    }

    @Override
    public Optional<VenueDTO> partialUpdate(VenueDTO venueDTO) {
        log.debug("Request to partially update Venue : {}", venueDTO);

        return venueRepository
            .findById(venueDTO.getId())
            .map(existingVenue -> {
                venueMapper.partialUpdate(existingVenue, venueDTO);

                return existingVenue;
            })
            .map(venueRepository::save)
            .map(venueMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VenueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Venues");
        return venueRepository.findAll(pageable).map(venueMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VenueDTO> findOne(Long id) {
        log.debug("Request to get Venue : {}", id);
        return venueRepository.findById(id).map(venueMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Venue : {}", id);
        venueRepository.deleteById(id);
    }
}
