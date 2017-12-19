package mapa;

/**
 * 
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: EC3
 * @Curso: 2º
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

	/**
	 * Devuelve el ID de la sala origen de una pared
	 * 
	 * @return el ID de la sala origen de una pared
	 */
	public int getOrigen() {
		return origen;
	}

	/**
	 * Establece el ID de la sala origen de una pared
	 * 
	 * @param origen
	 *            el ID de la sala origen de una pared
	 */
	public void setOrigen(int origen) {
		this.origen = origen;
	}

	/**
	 * Devuelve el ID de la sala destino de una pared
	 * 
	 * @return el ID de la sala destino de una pared
	 */
	public int getDestino() {
		return destino;
	}

	/**
	 * Establece el ID de la sala destino de una pared
	 * 
	 * @param origen
	 *            el ID de la sala destino de una pared
	 */
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
