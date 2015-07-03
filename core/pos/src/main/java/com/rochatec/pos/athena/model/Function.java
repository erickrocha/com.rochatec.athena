package com.rochatec.pos.athena.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by epr on 02/07/15.
 */
@Entity
@Table(name = "FUNCTION")
public class Function implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "HIERARCHY", length = 50)
    private Hierarchy hierarchy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Function function = (Function) o;

        return id.equals(function.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
