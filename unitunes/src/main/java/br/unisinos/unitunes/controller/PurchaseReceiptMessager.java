package br.unisinos.unitunes.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;

import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.User;

public class PurchaseReceiptMessager {

	public static void showReceipt(Media media, User user) {

		StringBuilder builder = new StringBuilder();
		builder.append("Dados da Compra:").append("<br>");
		builder.append("").append("<br>");
		builder.append("Mídia: ").append(media.getName()).append("<br>");
		builder.append("Valor: ").append(new DecimalFormat("R$ #0.00").format(media.getValue())).append("<br>");
		builder.append("Data: ").append(new SimpleDateFormat("dd/MM/yyyy").format(new Date())).append("<br>");
		builder.append("Comprador: ").append(user.getName()).append("<br>");
		builder.append("Vendedor: ").append(media.getAuthor().getName()).append("<br>");

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comprovante de Compra", builder.toString());
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

}
