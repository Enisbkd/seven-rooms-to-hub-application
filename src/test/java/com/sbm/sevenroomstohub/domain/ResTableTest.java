package com.sbm.sevenroomstohub.domain;

import static com.sbm.sevenroomstohub.domain.ResTableTestSamples.*;
import static com.sbm.sevenroomstohub.domain.ReservationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResTableTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResTable.class);
        ResTable resTable1 = getResTableSample1();
        ResTable resTable2 = new ResTable();
        assertThat(resTable1).isNotEqualTo(resTable2);

        resTable2.setId(resTable1.getId());
        assertThat(resTable1).isEqualTo(resTable2);

        resTable2 = getResTableSample2();
        assertThat(resTable1).isNotEqualTo(resTable2);
    }

    @Test
    void reservationTest() throws Exception {
        ResTable resTable = getResTableRandomSampleGenerator();
        Reservation reservationBack = getReservationRandomSampleGenerator();

        resTable.setReservation(reservationBack);
        assertThat(resTable.getReservation()).isEqualTo(reservationBack);

        resTable.reservation(null);
        assertThat(resTable.getReservation()).isNull();
    }
}
