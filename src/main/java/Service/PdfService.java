package Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeQRCode;
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

		Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.ITALIC, BaseColor.BLACK);
		Paragraph titulo = new Paragraph("Pasaje Aéreo", fontTitulo);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(titulo);

		document.add(new Paragraph("\n"));

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(70);
		table.setHorizontalAlignment(Element.ALIGN_CENTER);

		agregarCelda(table, "Nombre del Pasajero:", pas.getUsuario().getNombre() + " " + pas.getUsuario().getApellido(),
				BaseColor.DARK_GRAY);
		agregarCelda(table, "Origen: ",
				pas.getVuelo().getAeropuertoOrigen().getNombre() + ", "
						+ pas.getVuelo().getAeropuertoOrigen().getCiudad().getNombre() + ", "
						+ pas.getVuelo().getAeropuertoOrigen().getCiudad().getPais().getNombre(),
				BaseColor.DARK_GRAY);
		agregarCelda(table, "Fecha de Salida:", pas.getVuelo().getFechaHoraSalida()
				.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")).toString(), BaseColor.DARK_GRAY);
		agregarCelda(table, "Destino: ",
				pas.getVuelo().getAeropuertoDestino().getNombre() + ", "
						+ pas.getVuelo().getAeropuertoDestino().getCiudad().getNombre() + ", "
						+ pas.getVuelo().getAeropuertoDestino().getCiudad().getPais().getNombre(),
				BaseColor.DARK_GRAY);
		agregarCelda(table, "Fecha de Llegada:", pas.getVuelo().getFechaHoraLlegada()
				.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")).toString(), BaseColor.DARK_GRAY);
		agregarCelda(table, "Asiento:", pas.getAsiento().getFila() + " " + pas.getAsiento().getNumero(),
				BaseColor.DARK_GRAY);

		document.add(table);

		document.add(new Paragraph("\n"));

		Font fontAgradecimiento = new Font(Font.FontFamily.HELVETICA, 14, Font.ITALIC, BaseColor.DARK_GRAY);
		Paragraph agradecimiento = new Paragraph("¡Gracias por elegir nuestra aerolínea!", fontAgradecimiento);
		agradecimiento.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(agradecimiento);

		document.add(new Paragraph("\n"));

		String pasajeInfoUrlParams = String.format("id=%s", pas.getIdPasaje());

		String textCodigoQR = "https://poorly-verified-hornet.ngrok-free.app/jaTp/FinalizarPasaje?accion=mostrar&"
				+ pasajeInfoUrlParams;
		BarcodeQRCode codigoBarrasQR = new BarcodeQRCode(textCodigoQR, 300, 300, null);
		Image image = codigoBarrasQR.getImage();
		image.setAlignment(Element.ALIGN_CENTER);
		document.add(image);

	}

	private static void agregarCelda(PdfPTable table, String etiqueta, String valor, BaseColor colorFondo) {
		Font fontEtiqueta = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
		Font fontValor = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

		PdfPCell etiquetaCell = new PdfPCell(new Phrase(etiqueta, fontEtiqueta));
		PdfPCell valorCell = new PdfPCell(new Phrase(valor, fontValor));

		etiquetaCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		valorCell.setHorizontalAlignment(Element.ALIGN_LEFT);

		etiquetaCell.setBackgroundColor(colorFondo);

		etiquetaCell.setBorderColor(BaseColor.GRAY);
		etiquetaCell.setBorderWidth(1f);
		valorCell.setBorderColor(BaseColor.GRAY);
		valorCell.setBorderWidth(1f);

		table.addCell(etiquetaCell);
		table.addCell(valorCell);
	}

}
