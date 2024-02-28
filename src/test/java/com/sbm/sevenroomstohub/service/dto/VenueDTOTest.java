package com.sbm.sevenroomstohub.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VenueDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VenueDTO.class);
        VenueDTO venueDTO1 = new VenueDTO();
        venueDTO1.setId("id1");
        VenueDTO venueDTO2 = new VenueDTO();
        assertThat(venueDTO1).isNotEqualTo(venueDTO2);
        venueDTO2.setId(venueDTO1.getId());
        assertThat(venueDTO1).isEqualTo(venueDTO2);
        venueDTO2.setId("id2");
        assertThat(venueDTO1).isNotEqualTo(venueDTO2);
        venueDTO1.setId(null);
        assertThat(venueDTO1).isNotEqualTo(venueDTO2);
    }
}
