package org.healthnlp.deepphe.i2b2.orm.i2b2data;

// Generated Apr 15, 2015 4:30:25 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

/**
 * ConceptDimension generated by hbm2java
 */
@Entity
@Table(name = "CONCEPT_DIMENSION", schema = "I2B2DEMODATA")
public class ConceptDimension implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String conceptCd;
	private String conceptPath;
	private String nameChar;
	private String conceptBlob;
	private Date updateDate;
	private Date downloadDate;
	private Date importDate;
	private String sourcesystemCd;
	private BigDecimal uploadId;

	public ConceptDimension() {
	}

	public ConceptDimension(String conceptCd, String conceptPath) {
		this.conceptCd = conceptCd;
		this.conceptPath = conceptPath;
	}

	public ConceptDimension(String conceptCd, String conceptPath,
			String nameChar, String conceptBlob, Date updateDate,
			Date downloadDate, Date importDate, String sourcesystemCd,
			BigDecimal uploadId) {
		this.conceptCd = conceptCd;
		this.conceptPath = conceptPath;
		this.nameChar = nameChar;
		this.conceptBlob = conceptBlob;
		this.updateDate = updateDate;
		this.downloadDate = downloadDate;
		this.importDate = importDate;
		this.sourcesystemCd = sourcesystemCd;
		this.uploadId = uploadId;
	}

	@Id
	@Column(name = "CONCEPT_CD", unique = true, nullable = false, length = 150)
	public String getConceptCd() {
		return this.conceptCd;
	}

	public void setConceptCd(String conceptCd) {
		this.conceptCd = conceptCd;
	}

	@Column(name = "CONCEPT_PATH", nullable = false, length = 2100)
	public String getConceptPath() {
		return this.conceptPath;
	}

	public void setConceptPath(String conceptPath) {
		this.conceptPath = conceptPath;
	}

	@Column(name = "NAME_CHAR", length = 4000)
	public String getNameChar() {
		return this.nameChar;
	}

	public void setNameChar(String nameChar) {
		this.nameChar = nameChar;
	}

	@Column(name = "CONCEPT_BLOB")
	@Type(type = "org.hibernate.type.MaterializedClobType")
	public String getConceptBlob() {
		return this.conceptBlob;
	}

	public void setConceptBlob(String conceptBlob) {
		this.conceptBlob = conceptBlob;
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

	@Column(name = "SOURCESYSTEM_CD", length = 150)
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
