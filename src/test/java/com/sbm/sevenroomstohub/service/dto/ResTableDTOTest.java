package com.sbm.sevenroomstohub.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.sevenroomstohub.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResTableDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResTableDTO.class);
        ResTableDTO resTableDTO1 = new ResTableDTO();
        resTableDTO1.setId(1L);
        ResTableDTO resTableDTO2 = new ResTableDTO();
        assertThat(resTableDTO1).isNotEqualTo(resTableDTO2);
        resTableDTO2.setId(resTableDTO1.getId());
        assertThat(resTableDTO1).isEqualTo(resTableDTO2);
        resTableDTO2.setId(2L);
        assertThat(resTableDTO1).isNotEqualTo(resTableDTO2);
        resTableDTO1.setId(null);
        assertThat(resTableDTO1).isNotEqualTo(resTableDTO2);
    }
}
