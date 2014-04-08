package com.rochatec.athena.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InvoiceValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7239118436228315580L;

	@Column(name="BASE_ICMS",precision=10,scale=2,nullable=false)
	private BigDecimal baseIcms = BigDecimal.ZERO;

	@Column(name="TOTAL_ICMS",precision=10,scale=2,nullable=false)
	private BigDecimal totalIcms = BigDecimal.ZERO;

	@Column(name="BASE_ICMS_SUBSTITUICAO",precision=10,scale=2,nullable=false)
	private BigDecimal baseIcmsSub = BigDecimal.ZERO;

	@Column(name="TOTAL_ICMS_SUBSTITUICAO",precision=10,scale=2,nullable=false)
	private BigDecimal totalIcmsSub = BigDecimal.ZERO;

	@Column(name="TOTAL_PRODUCTS",precision=10,scale=2,nullable=false)
	private BigDecimal totalItems = BigDecimal.ZERO;

	@Column(name="TOTAL_FRETE",precision=10,scale=2)
	private BigDecimal totalFrete = BigDecimal.ZERO;

	@Column(name="TOTAL_INVOICE",precision=10,scale=2,nullable=false)
	private BigDecimal totalInvoice = BigDecimal.ZERO;

	@Column(name="TOTAL_SEGURO",precision=10,scale=2)
	private BigDecimal totalSeguro = BigDecimal.ZERO;

	@Column(name="OUTRAS_DESPESAS",precision=10,scale=2)
	private BigDecimal outrasdespesas = BigDecimal.ZERO;

	@Column(name="TOTAL_IPI",precision=10,scale=2,nullable=false)
	private BigDecimal totalIpi = BigDecimal.ZERO;

	@Column(name="DESCONTO",precision=10,scale=2)
	private BigDecimal desconto = BigDecimal.ZERO;

	public BigDecimal getBaseIcms() {
		return baseIcms;
	}

	public void setBaseIcms(BigDecimal baseIcms) {
		this.baseIcms = baseIcms;
	}

	public BigDecimal getTotalIcms() {
		return totalIcms;
	}

	public void setTotalIcms(BigDecimal totalIcms) {
		this.totalIcms = totalIcms;
	}

	public BigDecimal getBaseIcmsSub() {
		return baseIcmsSub;
	}

	public void setBaseIcmsSub(BigDecimal baseIcmsSub) {
		this.baseIcmsSub = baseIcmsSub;
	}

	public BigDecimal getTotalIcmsSub() {
		return totalIcmsSub;
	}

	public void setTotalIcmsSub(BigDecimal totalIcmsSub) {
		this.totalIcmsSub = totalIcmsSub;
	}

	public BigDecimal getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(BigDecimal totalItems) {
		this.totalItems = totalItems;
	}

	public BigDecimal getTotalFrete() {
		return totalFrete;
	}

	public void setTotalFrete(BigDecimal totalFrete) {
		this.totalFrete = totalFrete;
	}

	public BigDecimal getTotalInvoice() {
		return totalInvoice;
	}

	public void setTotalInvoice(BigDecimal totalInvoice) {
		this.totalInvoice = totalInvoice;
	}

	public BigDecimal getTotalSeguro() {
		return totalSeguro;
	}

	public void setTotalSeguro(BigDecimal totalSeguro) {
		this.totalSeguro = totalSeguro;
	}

	public BigDecimal getOutrasdespesas() {
		return outrasdespesas;
	}

	public void setOutrasdespesas(BigDecimal outrasdespesas) {
		this.outrasdespesas = outrasdespesas;
	}

	public BigDecimal getTotalIpi() {
		return totalIpi;
	}

	public void setTotalIpi(BigDecimal totalIpi) {
		this.totalIpi = totalIpi;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

}
