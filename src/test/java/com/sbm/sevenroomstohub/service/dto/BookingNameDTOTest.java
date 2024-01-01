package com.sbm.sevenroomstohub.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BookingNameDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookingNameDTO.class);
        BookingNameDTO bookingNameDTO1 = new BookingNameDTO();
        bookingNameDTO1.setId(1L);
        BookingNameDTO bookingNameDTO2 = new BookingNameDTO();
        assertThat(bookingNameDTO1).isNotEqualTo(bookingNameDTO2);
        bookingNameDTO2.setId(bookingNameDTO1.getId());
        assertThat(bookingNameDTO1).isEqualTo(bookingNameDTO2);
        bookingNameDTO2.setId(2L);
        assertThat(bookingNameDTO1).isNotEqualTo(bookingNameDTO2);
        bookingNameDTO1.setId(null);
        assertThat(bookingNameDTO1).isNotEqualTo(bookingNameDTO2);
    }
}
