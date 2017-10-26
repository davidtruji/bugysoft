package mapa;

import personajes.Personaje;

public class Mapa {

	private Sala tablero[][];

	public Mapa(int fil, int col) {
		tablero = new Sala[fil][col];
	}

	public Mapa(Sala t[][]) {
		tablero = new Sala[t.length][t[0].length];
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[0].length; j++) {
				tablero[i][j] = t[i][j];
			}
		}
	}
		
	
	
	
	
	
	
	
	
	
	

	// public void construirMapa() {
	// int num = 0;
	//
	// for (int i = 0; i < fila; i++) {
	// for (int j = 0; j < columna; j++) {
	// if (i == 0)
	// mapa[i][j].asignarPnorte(true);
	//
	// if (j == 0)
	// mapa[i][j].asignarPoeste(true);
	//
	// if (i == (fila - 1))
	// mapa[i][j].asignarPeste(true);
	//
	// if (j == (columna - 1))
	// mapa[i][j].asignarPsur(true);
	//
	// mapa[i][j].asignarNumSala(num);
	// num++;
	// }
	// }
	//
	// }
	//
	// public void obtenerNumero() {
	// for (int i = 0; i < this.fila; i++) {
	// for (int j = 0; i < this.columna; j++) {
	// System.out.println(mapa[i][j].obtenerNumSala());
	//
	// }
	//
	// }
	// }
	//
	// public void asignarFila(int fila) {
	// this.fila = fila;
	// }
	//
	// public void asignarColumna(int columna) {
	// this.columna = columna;
	// }
	//
	// public int obtenerFila() {
	// return fila;
	// }
	//
	// public int obtenerColumna() {
	// return columna;
	// }

	@Override
	public String toString() {
		String t = "";
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				t = t + tablero[i][j].getNumSala() + " ";
			}
			t = t + "\n";
		}

		return t;

	}

	public static void main(String[] args) {
		int num_sala = 0;
		Sala[][] s = new Sala[6][6];
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[0].length; j++) {
				s[i][j] = new Sala(num_sala, false, false, false, false);
				Personaje p = new Personaje();
				s[i][j].getPersonajes().add(p);
				num_sala++;
			}
		}

		Mapa mapa1 = new Mapa(s);
		System.out.print(mapa1);
	}

}
