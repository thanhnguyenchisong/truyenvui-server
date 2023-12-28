package com.sky.tv.comics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Data
@MappedSuperclass
public class NameEntity extends BaseEntity {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getName()).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        boolean result;
        result = (obj != null) && (obj.getClass() == getClass()) && (obj == this);

        return result && new EqualsBuilder().append(getName(), ((Category) obj).getName()).isEquals();
    }
}
