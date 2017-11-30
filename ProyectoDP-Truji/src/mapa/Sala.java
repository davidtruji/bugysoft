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
 * ProyectoDP-Truji
 * 
 * @Fichero: Sala.java
 * @Autor: David Trujillo Torres
 * @Fecha: 6 nov. 2017
 */
@SuppressWarnings("unused")
public class Sala {

	// private boolean pnorte;
	// private boolean psur;
	// private boolean peste;
	// private boolean poeste;
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

	// /**
	// * @param idSala
	// * @param marca
	// */
	// public Sala(int idSala, int marca) {
	// this.marca = marca;
	// this.idSala = idSala;
	// personajes = new LinkedList<Personaje>();
	// armas = new Arbol<Arma>();
	// }

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

	public String personajesToString() {
		String s = "";

		for (int i = 0; i < personajes.size(); i++) {
			s = s + personajes.get(i) + "\n";
		}
		
		return s;
	}

	/**
	 * Main de la clase sala se usa para pruebas internas
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		System.out.println("PRUEBAS SALA............................");

		Arma a = new Arma("RED", 1);
		Villano v = new Villano("Malo", 'M', a);
		Arma a1 = new Arma("Reda", 26);
		Arma a2 = new Arma("Mjolniras", 50);
		Arma a3 = new Arma("Mjolniraw", 10);

		SuperHeroe h = new SuperHeroe("Bueno", 'B');
		h.insertarArmaHeroe(a1);
		h.insertarArmaHeroe(a2);
		h.insertarArmaHeroe(a3);

		Sala s = new Sala(0);
		Arma[] armasSalas = { new Arma("Mjolnir", 29), new Arma("Anillo", 1), new Arma("Garra", 27),
				new Arma("Armadura", 3), new Arma("Red", 25), new Arma("Escudo", 5), new Arma("Lucille", 23),
				new Arma("Lawgiver", 7), new Arma("GuanteInfinito", 21), new Arma("LazoVerdad", 9),
				new Arma("CadenaFuego", 19) };

		HombrePuerta hp = new HombrePuerta();
		hp.getContenedorArmas().insertar(new Arma("Red", 25));
		hp.getContenedorArmas().insertar(new Arma("Palo", 12));
		hp.getContenedorArmas().insertar(new Arma("Tenedor", 1));

		for (int i = 0; i < armasSalas.length; i++) {
			s.insertarArma(armasSalas[i]);
		}

		s.insertarPersonaje(v);
		System.out.print(s.toString());

	}

}