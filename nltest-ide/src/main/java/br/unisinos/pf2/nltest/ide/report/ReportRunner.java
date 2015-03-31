package br.unisinos.pf2.nltest.ide.report;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.io.IOUtils;

public class ReportRunner {

	public static void runReport(String reportName, List<?> reportList, Map<String, Object> parameters) {
		InputStream jasperInputStream = null;
		ByteArrayOutputStream reportOutputStream = null;
		ByteArrayInputStream pdfInputStream = null;
		OutputStream pdfOutputStream = null;

		try {
			jasperInputStream = ReportRunner.class.getResourceAsStream("/reports/" + reportName);
			reportOutputStream = new ByteArrayOutputStream();

			JRDataSource dataSource = new JRBeanCollectionDataSource(reportList);

			JasperFillManager.fillReportToStream(jasperInputStream, reportOutputStream, parameters, dataSource);

			pdfInputStream = new ByteArrayInputStream(reportOutputStream.toByteArray());
			File tempFile = File.createTempFile("nltest-" + reportName + "-", ".pdf");
			pdfOutputStream = new FileOutputStream(tempFile);
			JasperExportManager.exportReportToPdfStream(pdfInputStream, pdfOutputStream);

			System.out.println("tempFile: " + tempFile);

			Desktop.getDesktop().open(tempFile);

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(jasperInputStream);
			IOUtils.closeQuietly(reportOutputStream);
			IOUtils.closeQuietly(pdfInputStream);
			IOUtils.closeQuietly(pdfOutputStream);
		}
	}

}
