package personajes;

import mapa.Mapa;
import mapa.Sala;

enum Dir {
	S, E, N, W
};

/**
 * 
 * ProyectoDP-Truji
 * 
 * @Fichero: Personaje.java
 * @Autor: David Trujillo Torres
 * @Fecha: 6 nov. 2017
 */
public abstract class Personaje {

	private String nombre;
	private char inicial;
	private int turno;
	private int posicion;
	private Dir[] ruta;

	/**
	 * Constructor de personaje que recibe como parametros su nombre y su inicial y
	 * turno de comienzo
	 * 
	 * @param nombre
	 *            del personaje
	 * @param inicial
	 *            que es la marca del personaje
	 * @param turno
	 *            en que comienza la partida
	 */
	public Personaje(String nombre, char inicial, int turno) {
		this.nombre = nombre;
		this.inicial = inicial;
		this.turno = turno;
	}

	/**
	 * Constructor de personaje que recibe como parametros su nombre y su inicial
	 * 
	 * @param nom
	 *            nombre del personaje
	 * @param inicial
	 *            inicial del personaje
	 */
	public Personaje(String nom, char inicial) {
		this.nombre = nom;
		this.inicial = inicial;
	}

	/**
	 * Get del nombre del personaje
	 * 
	 * @return String con el nombre del personaje
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Set del nombre del personaje
	 * 
	 * @param nombre
	 *            String con el nombre del personaje
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Get inicial del personaje
	 * 
	 * @return Char de la inicial del personaje
	 */
	public char getInicial() {
		return inicial;
	}

	/**
	 * Set de la inicial del personaje
	 * 
	 * @param inicial
	 *            Char de la inicial del personaje
	 */
	public void setInicial(char inicial) {
		this.inicial = inicial;

	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public Dir[] getRuta() {
		return ruta;
	}

	public void setRuta(Dir[] ruta) {
		this.ruta = ruta;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	/**
	 * Metodo que ejecuta un personaje para recojer arma de la sala pasada por
	 * parametro
	 */
	public abstract void recogerArmaPersonaje(Sala s);

	/**
	 * Metodo que ejecuta cada personaje para interaccionar con el hombre puerta
	 */
	public abstract void interaccionHombrePuerta(HombrePuerta hp);

	private boolean movimientoPosible(Dir dir) {
		Mapa m = Mapa.getInstancia(0, 0, 0, 0);
		int dimX, dimY;
		dimX = m.getDimX();
		dimY = m.getDimY();
		boolean posible = false;

		switch (dir) {
		case N:
			if (!m.paredNorte(posicion)) {
				if (posicion > dimX - 1) {
					posible = true;
				}

			}

			break;
		case E:

			if (!m.paredEste(posicion)) {
				if ((posicion + 1) % dimX != 0) {
					posible = true;
				}

			}

			break;
		case S:
			if (!m.paredSur(posicion)) {
				if (posicion < (dimX * dimY) - dimX) {
					posible = true;
				}

			}

			break;
		case W:

			if (!m.paredOeste(posicion)) {
				if (posicion % dimX != 0) {
					posible = true;
				}

			}

			break;

		default:
			System.out.println("Error en la direccion introducida");
			break;
		}

		return posible;
	}

	public void mover() {
		Mapa m = Mapa.getInstancia(0, 0, 0, 0);
		int dimX, dimY;
		dimX = m.getDimX();
		dimY = m.getDimY();
		int dest;

		if (ruta.length != 0) {

			Dir dir = ruta[0];

			switch (dir) {
			case N:
				if (movimientoPosible(dir)) {

					// borrar de sala origen
					m.borrarPersonaje(this, posicion);

					dest = posicion - dimX;

					// insertar en sala destino
					m.insertarPersonaje(this, dest);
					posicion = dest;
				}

				break;
			case E:

				if (movimientoPosible(dir)) {
					// borrar de sala origen
					m.borrarPersonaje(this, posicion);

					dest = posicion + 1;

					// insertar en sala destino
					m.insertarPersonaje(this, dest);
					posicion = dest;

				}

				break;
			case S:
				if (movimientoPosible(dir)) {
					// borrar de sala origen
					m.borrarPersonaje(this, posicion);

					dest = posicion + dimX;

					// insertar en sala destino
					m.insertarPersonaje(this, dest);
					posicion = dest;

				}
				break;
			case W:

				if (movimientoPosible(dir)) {
					// borrar de sala origen
					m.borrarPersonaje(this, posicion);

					dest = posicion - 1;

					// insertar en sala destino
					m.insertarPersonaje(this, dest);
					posicion = dest;

				}

				break;

			default:
				System.out.println("Error en la direccion introducida");
				break;
			}

			// TODO:CHAPUSA ENOLME
			ruta[0] = ruta[1];
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personaje other = (Personaje) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}
