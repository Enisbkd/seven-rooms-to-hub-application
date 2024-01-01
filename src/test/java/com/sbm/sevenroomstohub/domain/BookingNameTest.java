package com.sbm.sevenroomstohub.domain;

import static com.sbm.sevenroomstohub.domain.BookingNameTestSamples.*;
import static com.sbm.sevenroomstohub.domain.ClientVenueStatsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BookingNameTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookingName.class);
        BookingName bookingName1 = getBookingNameSample1();
        BookingName bookingName2 = new BookingName();
        assertThat(bookingName1).isNotEqualTo(bookingName2);

        bookingName2.setId(bookingName1.getId());
        assertThat(bookingName1).isEqualTo(bookingName2);

        bookingName2 = getBookingNameSample2();
        assertThat(bookingName1).isNotEqualTo(bookingName2);
    }

    @Test
    void clientVenueStatsTest() throws Exception {
        BookingName bookingName = getBookingNameRandomSampleGenerator();
        ClientVenueStats clientVenueStatsBack = getClientVenueStatsRandomSampleGenerator();

        bookingName.setClientVenueStats(clientVenueStatsBack);
        assertThat(bookingName.getClientVenueStats()).isEqualTo(clientVenueStatsBack);

        bookingName.clientVenueStats(null);
        assertThat(bookingName.getClientVenueStats()).isNull();
    }
}
