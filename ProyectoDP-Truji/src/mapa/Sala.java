package mapa;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import estructuras_datos.Arbol;
import personajes.Arma;
import personajes.HombrePuerta;
import personajes.Personaje;
import personajes.SuperHeroe;
import personajes.Villano;

/**
 * 
 * ProyectoDP
 * 
 * @Fichero: Sala.java
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Fecha: 6 nov. 2017
 */
@SuppressWarnings("unused")
public class Sala {

	private Integer idSala;
	private List<Personaje> personajes;
	private Arbol<Arma> armas;
	private Integer marca;

	/**
	 * Costructor de las salas.
	 * 
	 * @param idSala
	 *            Id de la sala
	 */
	public Sala(int idSala) {
		marca = idSala;
		this.idSala = idSala;
		personajes = new LinkedList<Personaje>();
		armas = new Arbol<Arma>();
	}

	public Integer getMarca() {
		return marca;
	}

	public void setMarca(Integer marca) {
		this.marca = marca;
	}

	/**
	 * Get de la lista de personajes que estan en la sala
	 * 
	 * @return List de tipos Personaje
	 */
	public List<Personaje> getPersonajes() {
		return personajes;
	}

	/**
	 * Set de la lista de personajes que estan en la sala
	 * 
	 * @param personajes
	 *            Tipo list que sera la lista de personajes de la sala
	 */
	public void setPersonajes(List<Personaje> personajes) {
		this.personajes = personajes;
	}

	/**
	 * Get del arbol de armas de la sala
	 * 
	 * @return Arbol de armas
	 */
	public Arbol<Arma> getArmas() {
		return armas;
	}

	/**
	 * Set del arbol de armas de la sala
	 * 
	 * @param armas
	 *            arbol de armas
	 */
	public void setArmas(Arbol<Arma> armas) {
		this.armas = armas;
	}

	/**
	 * Set del id de la sala
	 * 
	 * @param numSala
	 *            entero que representara el id de la sala
	 */
	public void setIdSala(Integer idSala) {
		this.idSala = idSala;
	}

	/**
	 * Get id de la sala
	 * 
	 * @return Entero que representa el id de la sala
	 */
	public int getIdSala() {
		return idSala;
	}

	/**
	 * Inserta un personaje en la lisya de la sala
	 * 
	 * @param p
	 *            personaje a insertar
	 */
	public void insertarPersonaje(Personaje p) {
		personajes.add(p);
	}

	/**
	 * Borra el primer personaje de la lista de personajes
	 */
	public void borrarPersonaje(Personaje p) {
		if (!personajes.isEmpty())
			personajes.remove(p);
	}

	/**
	 * Inserta un arma en el arbol de armas de la sala
	 * 
	 * @param a
	 *            arma a insertar
	 */
	public void insertarArma(Arma a) {
		armas.insertar(a);
	}

	/**
	 * To String de la clase sala
	 */
	@Override
	public String toString() {
		String s = "";

		s = "(square:" + idSala + ":" + armas + ")";

		return s;
	}

	/**
	 * Crea un String que contiene a todos los personajes de la sala
	 * 
	 * @return el string
	 */
	public String personajesToString() {
		String s = "";

		for (int i = 0; i < personajes.size(); i++) {
			s = s + personajes.get(i) + "\n";
		}

		return s;
	}

}