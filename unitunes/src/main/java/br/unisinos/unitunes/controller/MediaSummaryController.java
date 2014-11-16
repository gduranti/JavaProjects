package br.unisinos.unitunes.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import br.unisinos.unitunes.business.media.MediaFacade;
import br.unisinos.unitunes.model.MediaSummaryDTO;

@Named
@ViewAccessScoped
public class MediaSummaryController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MediaFacade facade;

	private Calendar initialDate;
	private Calendar finalDate;

	private List<MediaSummaryDTO> list;

	@PostConstruct
	public void initModel() {
		initialDate = Calendar.getInstance();
		finalDate = Calendar.getInstance();
	}

	public Calendar getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Calendar initialDate) {
		this.initialDate = initialDate;
	}

	public Calendar getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Calendar finalDate) {
		this.finalDate = finalDate;
	}

	public List<MediaSummaryDTO> list() {
		if (list == null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println(dateFormat.format(initialDate.getTime()) + " ~ " + dateFormat.format(finalDate.getTime()));
			list = facade.listSummary(initialDate, finalDate);
		}
		return list;
	}

	public void clearList() {
		list = null;
	}

	public String formatMediaValue(Double value) {
		return "R$ " + new DecimalFormat("#0.00").format(value);
	}

	public Long getTotalSales() {
		Long total = 0L;
		for (MediaSummaryDTO summary : list()) {
			total += summary.getTotalSales();
		}
		return total;
	}

	public Double getTotalValue() {
		Double total = 0.0;
		for (MediaSummaryDTO summary : list()) {
			total += summary.getTotalValue();
		}
		return total;
	}

}
