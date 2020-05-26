package tech.ippon.generated.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UpperCamelClazz.
 */
@Entity
@Table(name = "upper_camel_clazz")
public class UpperCamelClazz implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UpperCamelClazz)) {
            return false;
        }
        return id != null && id.equals(((UpperCamelClazz) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UpperCamelClazz{" +
            "id=" + getId() +
            "}";
    }
}
