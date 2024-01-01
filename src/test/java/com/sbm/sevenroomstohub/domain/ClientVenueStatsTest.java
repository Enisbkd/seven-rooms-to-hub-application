package com.sbm.sevenroomstohub.domain;

import static com.sbm.sevenroomstohub.domain.BookingNameTestSamples.*;
import static com.sbm.sevenroomstohub.domain.ClientTestSamples.*;
import static com.sbm.sevenroomstohub.domain.ClientVenueStatsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ClientVenueStatsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientVenueStats.class);
        ClientVenueStats clientVenueStats1 = getClientVenueStatsSample1();
        ClientVenueStats clientVenueStats2 = new ClientVenueStats();
        assertThat(clientVenueStats1).isNotEqualTo(clientVenueStats2);

        clientVenueStats2.setId(clientVenueStats1.getId());
        assertThat(clientVenueStats1).isEqualTo(clientVenueStats2);

        clientVenueStats2 = getClientVenueStatsSample2();
        assertThat(clientVenueStats1).isNotEqualTo(clientVenueStats2);
    }

    @Test
    void bookingNameTest() throws Exception {
        ClientVenueStats clientVenueStats = getClientVenueStatsRandomSampleGenerator();
        BookingName bookingNameBack = getBookingNameRandomSampleGenerator();

        clientVenueStats.addBookingName(bookingNameBack);
        assertThat(clientVenueStats.getBookingNames()).containsOnly(bookingNameBack);
        assertThat(bookingNameBack.getClientVenueStats()).isEqualTo(clientVenueStats);

        clientVenueStats.removeBookingName(bookingNameBack);
        assertThat(clientVenueStats.getBookingNames()).doesNotContain(bookingNameBack);
        assertThat(bookingNameBack.getClientVenueStats()).isNull();

        clientVenueStats.bookingNames(new HashSet<>(Set.of(bookingNameBack)));
        assertThat(clientVenueStats.getBookingNames()).containsOnly(bookingNameBack);
        assertThat(bookingNameBack.getClientVenueStats()).isEqualTo(clientVenueStats);

        clientVenueStats.setBookingNames(new HashSet<>());
        assertThat(clientVenueStats.getBookingNames()).doesNotContain(bookingNameBack);
        assertThat(bookingNameBack.getClientVenueStats()).isNull();
    }

    @Test
    void clientTest() throws Exception {
        ClientVenueStats clientVenueStats = getClientVenueStatsRandomSampleGenerator();
        Client clientBack = getClientRandomSampleGenerator();

        clientVenueStats.setClient(clientBack);
        assertThat(clientVenueStats.getClient()).isEqualTo(clientBack);
        assertThat(clientBack.getClientVenueStats()).isEqualTo(clientVenueStats);

        clientVenueStats.client(null);
        assertThat(clientVenueStats.getClient()).isNull();
        assertThat(clientBack.getClientVenueStats()).isNull();
    }
}
