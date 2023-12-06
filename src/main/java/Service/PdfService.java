package Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entities.Pasaje;

public class PdfService {
	public void crearPdf(Pasaje pas) throws Exception {
		try {
			Document document = new Document();
			String destino = "C:\\Users\\Usuario\\Downloads\\Pasajes\\comprobante_" + pas.getIdPasaje() + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(destino));
			document.open();
			agregarContenido(document, pas);
			document.close();

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

	private static void agregarContenido(Document document, Pasaje pas) throws DocumentException {
		// Agregar un encabezado con estilo
		Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.ITALIC, BaseColor.BLACK);
		Paragraph titulo = new Paragraph("Pasaje Aéreo", fontTitulo);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(titulo);

		// Espacio en blanco
		document.add(new Paragraph("\n"));

		// Información del pasaje en una tabla estilizada
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(70);
		table.setHorizontalAlignment(Element.ALIGN_CENTER);

		agregarCelda(table, "Nombre del Pasajero:", pas.getUsuario().getNombre() + " " + pas.getUsuario().getApellido(),
				BaseColor.DARK_GRAY);
		agregarCelda(table, "Fecha de Salida:", pas.getVuelo().getFechaHoraSalida().toString(), BaseColor.DARK_GRAY);
		agregarCelda(table, "Fecha de Llegada:", pas.getVuelo().getFechaHoraLlegada().toString(), BaseColor.DARK_GRAY);
		agregarCelda(table, "Asiento:", pas.getAsiento().getFila() + pas.getAsiento().getNumero(), BaseColor.DARK_GRAY);

		document.add(table);

		// Espacio en blanco
		document.add(new Paragraph("\n"));

		// Detalles adicionales con estilo
		Font fontAgradecimiento = new Font(Font.FontFamily.HELVETICA, 14, Font.ITALIC, BaseColor.DARK_GRAY);
		Paragraph agradecimiento = new Paragraph("¡Gracias por elegir nuestra aerolínea!", fontAgradecimiento);
		agradecimiento.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(agradecimiento);
	}

	private static void agregarCelda(PdfPTable table, String etiqueta, String valor, BaseColor colorFondo) {
		Font fontEtiqueta = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
		Font fontValor = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

		PdfPCell etiquetaCell = new PdfPCell(new Phrase(etiqueta, fontEtiqueta));
		PdfPCell valorCell = new PdfPCell(new Phrase(valor, fontValor));

		etiquetaCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		valorCell.setHorizontalAlignment(Element.ALIGN_LEFT);

		etiquetaCell.setBackgroundColor(colorFondo);

		// Agregar bordes a las celdas
		etiquetaCell.setBorderColor(BaseColor.GRAY);
		etiquetaCell.setBorderWidth(1f);
		valorCell.setBorderColor(BaseColor.GRAY);
		valorCell.setBorderWidth(1f);

		table.addCell(etiquetaCell);
		table.addCell(valorCell);
	}

}
