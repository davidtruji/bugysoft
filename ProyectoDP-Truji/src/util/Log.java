package util;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: EC3
 * @Curso: 2º
 */
public class Log {

	private static String nombreRegistroLog = "ficherosSalida/registro.log";
	private static FileWriter writer;

	/**
	 * Metodo que escribe en el fichero log
	 * 
	 * @param s
	 *            el String que se desea escribir
	 * @throws IOException
	 */
	public static void writeInLog(String s) throws IOException {
		if (writer == null)
			writer = new FileWriter(nombreRegistroLog);

		writer.write(s);
	}

	/**
	 * Metodo que cierra el flujo de escritura al finalizar esta
	 * 
	 * @throws IOException
	 */
	public static void close() throws IOException {
		writer.close();
	}

}
