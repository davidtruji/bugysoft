package util;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: EC2
 * @Curso: 2º
 */
public class Log {

	private static String nombreRegistroLog = "ficherosSalida/registro.log";
	private static FileWriter writer;

	public static void writeInLog(String s) throws IOException {
		if (writer == null)
			writer = new FileWriter(nombreRegistroLog);

		writer.write(s);
	}

	public static void close() throws IOException {
		writer.close();
	}

}
