package com.sky.tv.comics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@MappedSuperclass
@Getter
@Setter
public class NameEntity extends BaseEntity {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getName()).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return new EqualsBuilder().append(getName(), ((NameEntity) obj).getName()).isEquals();
    }
}
