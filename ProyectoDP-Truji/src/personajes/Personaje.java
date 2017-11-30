package personajes;

import mapa.Sala;

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

	/**
	 * Metodo que ejecuta un personaje para recojer arma de la sala pasada por
	 * parametro
	 */
	public abstract void recogerArmaPersonaje(Sala s);

	/**
	 * Metodo que ejecuta cada personaje para interaccionar con el hombre puerta
	 */
	public abstract void interaccionHombrePuerta(HombrePuerta hp);

	/**
	 * Metodo To String de la clase personaje
	 */
	@Override
	public String toString() {
		String s = "";
		s = s + "{" + nombre + "} ";
		return s;
	}

}
