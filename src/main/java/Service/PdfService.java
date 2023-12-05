package Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import entities.Pasaje;

public class PdfService {
	public void crearPdf(Pasaje pas) throws Exception {
		try {
			Document document = new Document();
			String destino = "C:\\Users\\Usuario\\Downloads\\Pasajes\\comprobante_" + pas.getIdPasaje() + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(destino));
			document.open();

			Phrase p = new Phrase("Hola mundo");
			document.add(p);
			Paragraph paragraph = new Paragraph(
					"¡Hola!" + pas.getUsuario().getNombre() + " " + pas.getUsuario().getApellido());

			document.add(paragraph);

			document.close();

			System.out.println("Creado el PDF");

		} catch (FileNotFoundException ex) {
			System.err.println("Error: Archivo no encontrado - " + ex.getMessage());
			ex.printStackTrace();
			throw ex;
		} catch (DocumentException ex) {
			System.err.println("Error en el documento PDF - " + ex.getMessage());
			ex.printStackTrace();
			throw ex;
		}
	}

}
