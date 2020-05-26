package tech.ippon.generated.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import tech.ippon.generated.web.rest.TestUtil;

public class UpperCamelClazzTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UpperCamelClazz.class);
        UpperCamelClazz upperCamelClazz1 = new UpperCamelClazz();
        upperCamelClazz1.setId(1L);
        UpperCamelClazz upperCamelClazz2 = new UpperCamelClazz();
        upperCamelClazz2.setId(upperCamelClazz1.getId());
        assertThat(upperCamelClazz1).isEqualTo(upperCamelClazz2);
        upperCamelClazz2.setId(2L);
        assertThat(upperCamelClazz1).isNotEqualTo(upperCamelClazz2);
        upperCamelClazz1.setId(null);
        assertThat(upperCamelClazz1).isNotEqualTo(upperCamelClazz2);
    }
}
