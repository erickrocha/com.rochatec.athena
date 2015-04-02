package com.rochatec.pos.athena.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

/**
 * Created by epr on 31/10/14.
 */

@Entity
@Table(name="PRODUCT")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="SEQ_PRODUCT", sequenceName="SEQ_PRODUCT",allocationSize=1,initialValue=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PRODUCT")
    @Column(unique=true, nullable=false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private Status status = Status.ACTIVE;

    @Temporal( TemporalType.DATE)
    @Column(name="DATE_REGISTER")
    private Calendar dateRegister;

    @OneToMany(mappedBy="product",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<BarCode> barCodes;

    @ManyToOne
    @JoinColumn(name="ICMS", nullable=false)
    protected Icms icms;



}
