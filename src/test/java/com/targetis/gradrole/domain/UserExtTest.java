package com.targetis.gradrole.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.targetis.gradrole.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserExtTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserExt.class);
        UserExt userExt1 = new UserExt();
        userExt1.setId(1L);
        UserExt userExt2 = new UserExt();
        userExt2.setId(userExt1.getId());
        assertThat(userExt1).isEqualTo(userExt2);
        userExt2.setId(2L);
        assertThat(userExt1).isNotEqualTo(userExt2);
        userExt1.setId(null);
        assertThat(userExt1).isNotEqualTo(userExt2);
    }
}
