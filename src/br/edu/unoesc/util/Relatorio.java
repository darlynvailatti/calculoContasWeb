package br.edu.unoesc.util;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import br.edu.unoesc.dao.BaseDAO;
import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.modelo.Metodologia;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author DARLYN.VAILATTI
 *
 *
 */
public class Relatorio {

	private final static String pathExport = "/Users/Darlyn/Documents/workspace/calculoContasWeb/WebContent/resources/relatorios/"; // Exporta��o
	private static String pathReports = "/Users/Darlyn/Documents/workspace/calculoContasWeb/WebContent/resources/relatorios/"; // Relat�rios
																																		// .jrxml
	private static Connection connection = null;
	private static JasperReport jasperReport = null;
	private static JasperPrint jasperPrint = null;

	private static Connection getConnection() {
		try{
			GenericDAO<Metodologia> obj = new GenericDAO<>(Metodologia.class);
			System.out.println(obj.getConnection());
			return obj.getConnection();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	private static byte[] gerarPdfDefault(String reportFileName,
			String reportTitle, Map<String, Object> parameters)
			throws Exception {
		try {

			System.out.println(pathReports + reportFileName);
			jasperReport = JasperCompileManager.compileReport(pathReports
					+ reportFileName);
			

			connection = getConnection();

			if (parameters == null) {
				parameters = new HashMap<String, Object>();
			}

			parameters.put("ReportTitle", reportTitle);
			parameters.put("DataFile", pathReports + reportFileName);

			jasperPrint = JasperFillManager.fillReport(jasperReport,
					parameters, connection);

			// Arquivo formato byte[] em pdf
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			MessageJsf.messageInfo(e.getMessage());
		}
		return null;
	}

	public static byte[] gerarPdf(String reportFileName, String reportTitle)
			throws Exception {
		return gerarPdfDefault(reportFileName, reportTitle, null);
	}

	public static byte[] gerarPdf(String reportFileName, String reportTitle,
			Map<String, Object> parameters) throws Exception {
		return gerarPdfDefault(reportFileName, reportTitle, parameters);
	}
}