package br.edu.unoesc.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DARLYN.VAILATTI
 * Gerar, configurar e encaminhar um arquivo (no formato byte[]) para o browser.
 *
 * Processo padr�o:
 * 1. Localizar contexto alvo para onde ser� enviado o arquivo: init().
 * 2. Gerar nome din�mico para o arquivo a ser gerado: nameGenerator(String reportName).
 * 3. Inicializar o objeto de resposta que "armazenar�" o arquivo a ser gerado: initializeResponse().
 * 4. Escrever o conteudo do arquivo com as suas configuracoes no objeto de resposta: writeFileToResponse().
 * 5. Informar ao JSF que o objeto resposta j� foi tratado e por tanto ele nao deve process�-lo novamente.
 */
public class Impressora {
	private static FacesContext facesContext;
	private static ExternalContext externalContext;
	private static HttpServletResponse response;
	private static OutputStream output;

	public static void pdfView(byte[] fileContent, String reportName) throws IOException{
		init();

		reportName = nameGenerator(reportName,".pdf");

	    String contentType = "application/pdf";
	    String header = "filename=\"" + reportName + "";

	    initializeResponse(contentType, header);

	    writeFileToResponse(fileContent);

	    // Inform JSF to not take the response in hands.
	    facesContext.responseComplete(); // Important! Else JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
	}

	public static void pdfDownload(byte[] fileContent, String reportName) throws IOException{
		init();

		reportName = nameGenerator(reportName,".pdf");

		String contentType = "application/pdf";
		String header = "attachment;filename=\""+ reportName +"";

	    initializeResponse(contentType, header);

	    writeFileToResponse(fileContent);

	    // Inform JSF to not take the response in hands.
	    facesContext.responseComplete(); // Important! Else JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.

	}

	public static void sheetDownload(byte[] fileContent, String reportName) throws IOException{
		init();

		reportName = nameGenerator(reportName,".xls");

		String header = "attachment;filename=\""+ reportName+"";
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

		initializeResponse(contentType, header);

	    writeFileToResponse(fileContent);

	    // Inform JSF to not take the response in hands.
	    facesContext.responseComplete(); // Important! Else JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.

	}

	//Inicializacao dos objetos de contexto do webApp: pra quem responder?
	private static void init(){
		facesContext = FacesContext.getCurrentInstance();
	    externalContext = facesContext.getExternalContext();
	    response = (HttpServletResponse) externalContext.getResponse();
	}

	//Escreve o arquivo no objeto de resposta
	private static void writeFileToResponse(byte[] fileContent) throws IOException{
		// Write file to response.
	    output = response.getOutputStream();
	    output.write(fileContent);
	    output.close();
	}

	//Inicializa o objeto de resposta
	private static void initializeResponse(String contentType, String header){
		 // Initialize response.
	    response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
	    response.setContentType(contentType); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ServletContext#getMimeType() for auto-detection based on filename.
	    response.setHeader("Content-disposition", header); // The Save As popup magic is done here. You can give it any filename you want, this only won't work in MSIE, it will use current request URL as filename instead.

	}

	private static String nameGenerator(String fileName,String fileFormat){
		Long dateNow = new Date().getTime();
		String dateNowString = dateNow.toString();

		fileName = fileName.toLowerCase();
		fileName = fileName.replace(fileFormat, "");
		fileName = fileName + "_" + (dateNowString) + fileFormat;

		return fileName;
	}
}
