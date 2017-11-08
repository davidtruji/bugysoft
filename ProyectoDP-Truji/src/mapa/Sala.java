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

	private boolean pnorte;
	private boolean psur;
	private boolean peste;
	private boolean poeste;
	private Integer numSala;
	private List<Personaje> personajes;
	private Arbol<Arma> armas;

	/**
	 * Costructor de las salas.
	 * 
	 * @param num
	 *            Id de las salas.
	 * @param norte
	 *            Pared norte.
	 * @param sur
	 *            Pared sur.
	 * @param este
	 *            Pared este.
	 * @param oeste
	 *            Pared oeste.
	 */
	public Sala(int num, boolean norte, boolean sur, boolean este, boolean oeste) {
		pnorte = norte;
		psur = sur;
		peste = este;
		poeste = oeste;
		numSala = num;
		personajes = new LinkedList<Personaje>();
		armas = new Arbol<Arma>();
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
	 * Metodo que devulve si hay pared en la direccion indicada
	 * 
	 * @return true si hay pared false en caso contrario
	 */
	public boolean isPnorte() {
		return pnorte;
	}

	/**
	 * Metodo que pone pared o la quita en la direccion indicada
	 * 
	 * @param psur
	 *            True si hay pared false si no
	 */
	public void setPnorte(boolean pnorte) {
		this.pnorte = pnorte;
	}

	/**
	 * Metodo que devulve si hay pared en la direccion indicada
	 * 
	 * @return true si hay pared false en caso contrario
	 */
	public boolean isPsur() {
		return psur;
	}

	/**
	 * Metodo que pone pared o la quita en la direccion indicada
	 * 
	 * @param psur
	 *            True si hay pared false si no
	 */
	public void setPsur(boolean psur) {
		this.psur = psur;
	}

	/**
	 * Metodo que devulve si hay pared en la direccion indicada
	 * 
	 * @return true si hay pared false en caso contrario
	 */
	public boolean isPeste() {
		return peste;
	}

	/**
	 * Metodo que pone pared o la quita en la direccion indicada
	 * 
	 * @param peste
	 *            True si hay pared false si no
	 */
	public void setPeste(boolean peste) {
		this.peste = peste;
	}

	/**
	 * Metodo que devulve si hay pared en la direccion indicada
	 * 
	 * @return true si hay pared false en caso contrario
	 */
	public boolean isPoeste() {
		return poeste;
	}

	/**
	 * Metodo que pone pared o la quita en la direccion indicada
	 * 
	 * @param poeste
	 *            True si hay pared false si no
	 */
	public void setPoeste(boolean poeste) {
		this.poeste = poeste;
	}

	/**
	 * Set del id de la sala
	 * 
	 * @param numSala
	 *            entero que representara el id de la sala
	 */
	public void setNumSala(Integer numSala) {
		this.numSala = numSala;
	}

	/**
	 * Get id de la sala
	 * 
	 * @return Entero que representa el id de la sala
	 */
	public int getNumSala() {
		return numSala;
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
	public void borrarPersonaje() {
		if (!personajes.isEmpty())
			personajes.remove(0);
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
		s = "[Sala ID " + numSala + "]" + "\n";
		s = s + "Personajes:\n";
		s = s + personajes.toString() + "\n";
		s = s + "Armas:\n";
		s = s + armas.toString() + "\n";
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

		Sala s = new Sala(0, false, false, false, false);
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