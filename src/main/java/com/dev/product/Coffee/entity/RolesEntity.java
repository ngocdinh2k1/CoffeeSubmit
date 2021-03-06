package com.dev.product.Coffee.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

/**
 * @author DinhMN
 */

@Entity
@Table(name = "tbl_roles")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class RolesEntity extends BaseEntity {
    private String name;
    private String desc;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_users_roles", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UsersEntity> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RolesEntity that = (RolesEntity) o;

        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 622155445;
    }
}
