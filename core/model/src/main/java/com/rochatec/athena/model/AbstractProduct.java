package com.rochatec.athena.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the PRODUCT database table.
 * 
 */
@Entity
@Table(name="PRODUCT")
@DiscriminatorColumn(name = "PRODUCT_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_PRODUCT", sequenceName="SEQ_PRODUCT",allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PRODUCT")
	@Column(unique=true, nullable=false)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private Status status;

	@Column(precision=10, scale=2)
	private BigDecimal costprice = BigDecimal.ZERO;

    @Temporal( TemporalType.DATE)
    @Column(name="DATE_LAST_COSTPRICE")
	private Calendar dateLastCostprice;

    @Temporal( TemporalType.DATE)
	@Column(name="DATE_LAST_SELLPRICE")
	private Calendar dateLastSellprice;

    @Temporal( TemporalType.DATE)
	@Column(name="DATE_REGISTER")
	private Calendar dateRegister;

	@Column(precision=5, scale=2)
	private Double ipi = 0D;

	@Column(name="LAST_COSTPRICE", precision=10, scale=2)
	private BigDecimal lastCostprice = BigDecimal.ZERO;

	@Column(name="LAST_SELLPRICE", precision=10, scale=2)
	private BigDecimal lastSellprice = BigDecimal.ZERO;

	@Column(precision=10, scale=2)
	private Double markup = 0D;

	@Column(nullable=false, length=80)
	private String name;

	@Column(precision=10, scale=2)
	private BigDecimal sellprice = BigDecimal.ZERO;

	@Column(name="SHORT_NAME", length=40)
	private String shortName;

	@Column(precision=10, scale=3)
	private BigDecimal stock = BigDecimal.ZERO;

	@Column(precision=10, scale=3)
	private BigDecimal stockdown = BigDecimal.ZERO;

	@Column(precision=10, scale=3)
	private BigDecimal stockup = BigDecimal.ZERO;

	@Column(name="UNIT_MEASURE", length=5)
	@Enumerated(EnumType.STRING)
	private UnitMeasure unitMeasure;

	//bi-directional many-to-one association to Category
    @ManyToOne
	@JoinColumn(name="CATEGORY", nullable=false)
	protected Category category;

	//bi-directional many-to-one association to Icms
    @ManyToOne
	@JoinColumn(name="ICMS", nullable=false)
    protected Icms icms;

	//bi-directional many-to-one association to Ncm
    @ManyToOne
	@JoinColumn(name="NCM", nullable=false)
    protected Ncm ncm;
    
    @Column(name="WEIGHT",precision=10,scale=3)
    private BigDecimal weight = BigDecimal.ZERO;
    
    @Column(name="HEIGHT",precision=10,scale=3)
    private BigDecimal height = BigDecimal.ZERO;
    
    @Column(name="WIDTH",precision=10,scale=3)
    private BigDecimal width = BigDecimal.ZERO;
    
    @Column(name="PRODUCTION_TIME",precision=10,scale=3)
    private BigDecimal productionTime = BigDecimal.ZERO;
    
    @Column(name="LAST_PRODUCTION_TIME",precision=10,scale=3)
    private BigDecimal lastProductionTime = BigDecimal.ZERO;
    
    @Column(name="AVERAGE_PRODUCTION_TIME",precision=10,scale=3)
    private BigDecimal averageProductionTime = BigDecimal.ZERO;
    
    @Column(name="MANUFACTURE",precision=10,scale=0)
    private int manufacture;
    
    public AbstractProduct() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return this.status.equals(Status.ACTIVE) ? true : false;
	}

	public void setActive(boolean active) {
		this.status = active ? Status.ACTIVE : Status.INACTIVE;
	}

	public void setActive(Status status) {
		this.status = status;
	}

	public BigDecimal getCostprice() {
		return this.costprice;
	}

	public void setCostprice(BigDecimal costprice) {
		this.costprice = costprice;
	}

	public Calendar getDateLastCostprice() {
		return this.dateLastCostprice;
	}

	public void setDateLastCostprice(Calendar dateLastCostprice) {
		this.dateLastCostprice = dateLastCostprice;
	}

	public Calendar getDateLastSellprice() {
		return this.dateLastSellprice;
	}

	public void setDateLastSellprice(Calendar dateLastSellprice) {
		this.dateLastSellprice = dateLastSellprice;
	}

	public Calendar getDateRegister() {
		return this.dateRegister;
	}

	public void setDateRegister(Calendar dateRegister) {
		this.dateRegister = dateRegister;
	}

	public Double getIpi() {
		return this.ipi;
	}

	public void setIpi(Double ipi) {
		this.ipi = ipi;
	}

	public BigDecimal getLastCostPrice() {
		return this.lastCostprice;
	}

	public void setLastCostPrice(BigDecimal lastCostprice) {
		this.lastCostprice = lastCostprice;
	}

	public BigDecimal getLastSellPrice() {
		return this.lastSellprice;
	}

	public void setLastSellPrice(BigDecimal lastSellprice) {
		this.lastSellprice = lastSellprice;
	}

	public Double getMarkup() {
		return this.markup;
	}

	public void setMarkup(Double markup) {
		this.markup = markup;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSellprice() {
		return this.sellprice;
	}

	public void setSellprice(BigDecimal sellprice) {
		this.sellprice = sellprice;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public BigDecimal getStock() {
		return this.stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}

	public BigDecimal getStockdown() {
		return this.stockdown;
	}

	public void setStockdown(BigDecimal stockdown) {
		this.stockdown = stockdown;
	}

	public BigDecimal getStockup() {
		return this.stockup;
	}

	public void setStockup(BigDecimal stockup) {
		this.stockup = stockup;
	}

	public UnitMeasure getUnitMeasure() {
		return this.unitMeasure;
	}

	public void setUnitMeasure(UnitMeasure unitMeasure) {
		this.unitMeasure = unitMeasure;
	}
		
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Icms getIcms() {
		return this.icms;
	}

	public void setIcms(Icms icms) {
		this.icms = icms;
	}
	
	public Ncm getNcm() {
		return this.ncm;
	}

	public void setNcm(Ncm ncm) {
		this.ncm = ncm;
	}

	public BigDecimal getLastCostprice() {
		return lastCostprice;
	}

	public void setLastCostprice(BigDecimal lastCostprice) {
		this.lastCostprice = lastCostprice;
	}

	public BigDecimal getLastSellprice() {
		return lastSellprice;
	}

	public void setLastSellprice(BigDecimal lastSellprice) {
		this.lastSellprice = lastSellprice;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getProductionTime() {
		return productionTime;
	}

	public void setProductionTime(BigDecimal productionTime) {
		this.productionTime = productionTime;
	}

	public BigDecimal getLastProductionTime() {
		return lastProductionTime;
	}

	public void setLastProductionTime(BigDecimal lastProductionTime) {
		this.lastProductionTime = lastProductionTime;
	}

	public BigDecimal getAverageProductionTime() {
		return averageProductionTime;
	}

	public void setAverageProductionTime(BigDecimal averageProductionTime) {
		this.averageProductionTime = averageProductionTime;
	}

	public int getManufacture() {
		return manufacture;
	}

	public void setManufacture(int manufacture) {
		this.manufacture = manufacture;
	}
	
	public boolean isManufacture(){
		return this.manufacture == 1 ? true : false;
	}
	
	public void setManufacture(boolean value){
		this.manufacture = value ? 1 : 0;
	}
	
	public Status getStatus() {
		return status;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractProduct other = (AbstractProduct) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return shortName != null ? shortName : name;
	}
	
}