package com.rochatec.pos.athena.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by epr on 23/07/15.
 */
@Entity
@Table(name = "PROVINCE")
public class Province implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ACRONYM",length = 10)
    private String acronym;

    @Column(name = "NAME",length = 40)
    private String name;

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Province province = (Province) o;

        return acronym.equals(province.acronym);

    }

    @Override
    public int hashCode() {
        return acronym.hashCode();
    }
}
