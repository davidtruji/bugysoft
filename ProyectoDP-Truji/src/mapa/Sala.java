package mapa;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import estructuras_datos.Arbol;
import personajes.Arma;
import personajes.HombrePuerta;
import personajes.Personaje;
import personajes.SHExtraSensorial;
import personajes.SHFlight;
import personajes.SHPhysical;
import personajes.SuperHeroe;
import personajes.Villano;

/**
 * 
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: EC3
 * @Curso: 2º
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

	/**
	 * Devuelve la marca de la sala
	 * 
	 * @return la marca de la sala
	 */
	public Integer getMarca() {
		return marca;
	}

	/**
	 * Establece la marca de la sala
	 * 
	 * @param marca
	 *            la marca de la sala a establecer
	 */
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

	/**
	 * Metodo que ejecuta las pruebas de la clase
	 */
	private static void pruebasSala() {
		System.out.println("Ejecutando las pruebas de Sala...\n");

		Sala s = new Sala(666);

		Personaje p1 = new Villano("KINDER-MALO", 'K', new Arma("RUIDO", -1));
		Personaje p2 = new Villano("CACHORRO", 'C', new Arma("Vara Olivo", 999999));

		System.out.println("Probando insertarPersonaje:");

		s.insertarPersonaje(p1);
		s.insertarPersonaje(p2);

		System.out.println("Salida Esperada:");
		System.out.print("(villain:K:0:0:(RUIDO,-1))\n(villain:C:0:0:(Vara Olivo,999999))\n");

		System.out.println("Salida:");
		System.out.println(s.personajesToString());

		System.out.println("Probando insertarArma:");

		Arma a1 = new Arma("Vara Olivo", 1);
		Arma a2 = new Arma("Escopeta", 2);
		Arma a3 = new Arma("Guante", 3);

		s.insertarArma(a1);
		s.insertarArma(a2);
		s.insertarArma(a3);

		System.out.println("Salida Esperada:");
		System.out.print("(square:666:(Escopeta,2)(Guante,3)(Vara Olivo,1))\n");

		System.out.println("Salida:");
		System.out.println(s);

		System.out.println();

		System.out.println("Probando borrarPersonaje:");

		s.borrarPersonaje(p1);

		System.out.println("Salida Esperada:");
		System.out.print("(villain:C:0:0:(Vara Olivo,999999))\n");

		System.out.println("Salida:");
		System.out.println(s.personajesToString());

	}

	public static void main(String args[]) {
		Sala.pruebasSala();
	}

}