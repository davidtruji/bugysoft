package mapa;

import java.util.List;

public class Pared {
	private int origen;
	private int destino;

	public Pared() {
		origen = 0;
		destino = 0;
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s = "";
		s = "[" + origen + " " + destino + "]";
		return s;

	}

}
