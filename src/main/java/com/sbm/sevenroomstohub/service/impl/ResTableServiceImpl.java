package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.ResTable;
import com.sbm.sevenroomstohub.repository.ResTableRepository;
import com.sbm.sevenroomstohub.service.ResTableService;
import com.sbm.sevenroomstohub.service.dto.ResTableDTO;
import com.sbm.sevenroomstohub.service.mapper.ResTableMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.ResTable}.
 */
@Service
@Transactional
public class ResTableServiceImpl implements ResTableService {

    private final Logger log = LoggerFactory.getLogger(ResTableServiceImpl.class);

    private final ResTableRepository resTableRepository;

    private final ResTableMapper resTableMapper;

    public ResTableServiceImpl(ResTableRepository resTableRepository, ResTableMapper resTableMapper) {
        this.resTableRepository = resTableRepository;
        this.resTableMapper = resTableMapper;
    }

    @Override
    public ResTableDTO save(ResTableDTO resTableDTO) {
        log.debug("Request to save ResTable : {}", resTableDTO);
        ResTable resTable = resTableMapper.toEntity(resTableDTO);
        resTable = resTableRepository.save(resTable);
        return resTableMapper.toDto(resTable);
    }

    @Override
    public ResTableDTO update(ResTableDTO resTableDTO) {
        log.debug("Request to update ResTable : {}", resTableDTO);
        ResTable resTable = resTableMapper.toEntity(resTableDTO);
        resTable = resTableRepository.save(resTable);
        return resTableMapper.toDto(resTable);
    }

    @Override
    public Optional<ResTableDTO> partialUpdate(ResTableDTO resTableDTO) {
        log.debug("Request to partially update ResTable : {}", resTableDTO);

        return resTableRepository
            .findById(resTableDTO.getId())
            .map(existingResTable -> {
                resTableMapper.partialUpdate(existingResTable, resTableDTO);

                return existingResTable;
            })
            .map(resTableRepository::save)
            .map(resTableMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResTableDTO> findAll() {
        log.debug("Request to get all ResTables");
        return resTableRepository.findAll().stream().map(resTableMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ResTableDTO> findOne(Long id) {
        log.debug("Request to get ResTable : {}", id);
        return resTableRepository.findById(id).map(resTableMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ResTable : {}", id);
        resTableRepository.deleteById(id);
    }
}
