package br.unisinos.unitunes.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MediaSummaryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long mediaId;
	private String mediaName;
	private Long totalSales;
	private Double totalValue;

	public MediaSummaryDTO() {
	}

	public MediaSummaryDTO(Long mediaId, String mediaName, Long totalSales, Double totalValue) {
		this.mediaId = mediaId;
		this.mediaName = mediaName;
		this.totalSales = totalSales;
		this.totalValue = totalValue;
	}

	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public Long getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(Long totalSales) {
		this.totalSales = totalSales;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

}
