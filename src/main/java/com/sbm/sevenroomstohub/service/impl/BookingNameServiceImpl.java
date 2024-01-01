package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.BookingName;
import com.sbm.sevenroomstohub.repository.BookingNameRepository;
import com.sbm.sevenroomstohub.service.BookingNameService;
import com.sbm.sevenroomstohub.service.dto.BookingNameDTO;
import com.sbm.sevenroomstohub.service.mapper.BookingNameMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.BookingName}.
 */
@Service
@Transactional
public class BookingNameServiceImpl implements BookingNameService {

    private final Logger log = LoggerFactory.getLogger(BookingNameServiceImpl.class);

    private final BookingNameRepository bookingNameRepository;

    private final BookingNameMapper bookingNameMapper;

    public BookingNameServiceImpl(BookingNameRepository bookingNameRepository, BookingNameMapper bookingNameMapper) {
        this.bookingNameRepository = bookingNameRepository;
        this.bookingNameMapper = bookingNameMapper;
    }

    @Override
    public BookingNameDTO save(BookingNameDTO bookingNameDTO) {
        log.debug("Request to save BookingName : {}", bookingNameDTO);
        BookingName bookingName = bookingNameMapper.toEntity(bookingNameDTO);
        bookingName = bookingNameRepository.save(bookingName);
        return bookingNameMapper.toDto(bookingName);
    }

    @Override
    public BookingNameDTO update(BookingNameDTO bookingNameDTO) {
        log.debug("Request to update BookingName : {}", bookingNameDTO);
        BookingName bookingName = bookingNameMapper.toEntity(bookingNameDTO);
        bookingName = bookingNameRepository.save(bookingName);
        return bookingNameMapper.toDto(bookingName);
    }

    @Override
    public Optional<BookingNameDTO> partialUpdate(BookingNameDTO bookingNameDTO) {
        log.debug("Request to partially update BookingName : {}", bookingNameDTO);

        return bookingNameRepository
            .findById(bookingNameDTO.getId())
            .map(existingBookingName -> {
                bookingNameMapper.partialUpdate(existingBookingName, bookingNameDTO);

                return existingBookingName;
            })
            .map(bookingNameRepository::save)
            .map(bookingNameMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingNameDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BookingNames");
        return bookingNameRepository.findAll(pageable).map(bookingNameMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookingNameDTO> findOne(Long id) {
        log.debug("Request to get BookingName : {}", id);
        return bookingNameRepository.findById(id).map(bookingNameMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BookingName : {}", id);
        bookingNameRepository.deleteById(id);
    }
}
