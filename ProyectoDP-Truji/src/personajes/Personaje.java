package personajes;

/**
 * 
 * ProyectoDP-Truji
 * 
 * @Fichero: Personaje.java
 * @Autor: David Trujillo Torres
 * @Fecha: 6 nov. 2017
 */
public class Personaje {

	String nombre;
	char inicial;

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
