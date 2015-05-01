package edu.pitt.dbmi.deepphe.summarization.orm.i2b2data;

// Generated Apr 15, 2015 4:30:25 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ProviderDimension generated by hbm2java
 */
@Entity
@Table(name = "PROVIDER_DIMENSION", schema = "I2B2DEMODATA")
public class ProviderDimension implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private ProviderDimensionId id;
	private String nameChar;
	private Clob providerBlob;
	private Date updateDate;
	private Date downloadDate;
	private Date importDate;
	private String sourcesystemCd;
	private BigDecimal uploadId;

	public ProviderDimension() {
	}

	public ProviderDimension(ProviderDimensionId id) {
		this.id = id;
	}

	public ProviderDimension(ProviderDimensionId id, String nameChar,
			Clob providerBlob, Date updateDate, Date downloadDate,
			Date importDate, String sourcesystemCd, BigDecimal uploadId) {
		this.id = id;
		this.nameChar = nameChar;
		this.providerBlob = providerBlob;
		this.updateDate = updateDate;
		this.downloadDate = downloadDate;
		this.importDate = importDate;
		this.sourcesystemCd = sourcesystemCd;
		this.uploadId = uploadId;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "providerPath", column = @Column(name = "PROVIDER_PATH", nullable = false, length = 700)),
			@AttributeOverride(name = "providerId", column = @Column(name = "PROVIDER_ID", nullable = false, length = 50)) })
	public ProviderDimensionId getId() {
		return this.id;
	}

	public void setId(ProviderDimensionId id) {
		this.id = id;
	}

	@Column(name = "NAME_CHAR", length = 850)
	public String getNameChar() {
		return this.nameChar;
	}

	public void setNameChar(String nameChar) {
		this.nameChar = nameChar;
	}

	@Column(name = "PROVIDER_BLOB")
	public Clob getProviderBlob() {
		return this.providerBlob;
	}

	public void setProviderBlob(Clob providerBlob) {
		this.providerBlob = providerBlob;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DATE", length = 7)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DOWNLOAD_DATE", length = 7)
	public Date getDownloadDate() {
		return this.downloadDate;
	}

	public void setDownloadDate(Date downloadDate) {
		this.downloadDate = downloadDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "IMPORT_DATE", length = 7)
	public Date getImportDate() {
		return this.importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	@Column(name = "SOURCESYSTEM_CD", length = 50)
	public String getSourcesystemCd() {
		return this.sourcesystemCd;
	}

	public void setSourcesystemCd(String sourcesystemCd) {
		this.sourcesystemCd = sourcesystemCd;
	}

	@Column(name = "UPLOAD_ID", precision = 38, scale = 0)
	public BigDecimal getUploadId() {
		return this.uploadId;
	}

	public void setUploadId(BigDecimal uploadId) {
		this.uploadId = uploadId;
	}

}
