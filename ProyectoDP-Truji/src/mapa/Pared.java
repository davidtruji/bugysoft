package mapa;

/**
 * 
 * ProyectoDP
 * 
 * @Fichero: Pared.java
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Fecha: 27 nov. 2017
 */
public class Pared {
	private int origen;
	private int destino;

	/**
	 * Constructor por defecto de la clase
	 */
	public Pared() {
		origen = 0;
		destino = 0;
	}

	/**
	 * Contruye una pared del mapa
	 * 
	 * @param origen
	 *            ID de una de las dos salas que separa
	 * @param destino
	 *            ID de una de las dos salas que separa
	 */
	public Pared(int origen, int destino) {
		this.origen = origen;
		this.destino = destino;
	}

	public int getOrigen() {
		return origen;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}

	public int getDestino() {
		return destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}

	/**
	 * Metodo toString de una pared
	 */
	@Override
	public String toString() {
		String s = "";
		s = "[" + origen + " " + destino + "]";
		return s;

	}

}
