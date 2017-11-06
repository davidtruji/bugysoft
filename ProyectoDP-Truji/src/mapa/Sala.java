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
	private HombrePuerta hombrePuerta;

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
		hombrePuerta = null;
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

	// TODO QUITAR HOMBRE PUERTA DE LA SALA
	public HombrePuerta getHombrePuerta() {
		return hombrePuerta;
	}

	public void setHombrePuerta(HombrePuerta hombrePuerta) {
		this.hombrePuerta = hombrePuerta;
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

	// TODO Hombre puerta en mapa
	public void insertarHombrePuerta(HombrePuerta h) {
		this.hombrePuerta = h;
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

	// TODO polimorfismos
	/**
	 * Metodo que hace que el personaje de la lista recoja un arma de la sala
	 * 
	 * @param p
	 *            personaje que recojera el arma
	 */
	public void recojerArma(Personaje p) {
		Arma aux = new Arma("x", 0);
		System.out.println(">>Recojiendo Armas de la sala...");
		Arma mejorArmaSala = armas.mayor();

		if (mejorArmaSala != null)
			System.out.println("Mejor arma de la sala:" + mejorArmaSala);
		else
			System.out.println("Mejor arma de la sala: ");

		// Personaje p = personajes.get(i);
		// System.out.print("Personaje:" + p);

		if (!armas.vacio()) {
			if (p instanceof SuperHeroe) {
				// System.out.print(" es heroe\n");
				if (!((SuperHeroe) p).getContenedorArmas().pertenece(mejorArmaSala)) {
					System.out.println("Super Héroe recoje: " + mejorArmaSala);
					((SuperHeroe) p).insertarArmaHeroe(mejorArmaSala);
					// System.out.println("Armas de " + p + ": " + ((SuperHeroe)
					// p).getContenedorArmas().toString());
					System.out.println("Borrando mejor arma de sala: " + mejorArmaSala);
					armas.borrar(mejorArmaSala);
					// System.out.println(armas);
				} else {
					aux = ((SuperHeroe) p).getContenedorArmas().consultar(mejorArmaSala);
					System.out.println("Super Héroe combina Arma " + mejorArmaSala + "con" + aux);
					aux.setPoder(mejorArmaSala.getPoder() + aux.getPoder());
					System.out.println("La Arma resultante sería " + aux);
					// System.out.println("Armas de " + p + ":" + ((SuperHeroe)
					// p).getContenedorArmas().toString());
					System.out.println("Borrando mejor arma de sala: " + mejorArmaSala);
					armas.borrar(mejorArmaSala);
					// System.out.println(armas);
				}

				// System.out.println("Armas de " + p + ": " + ((SuperHeroe)
				// p).getContenedorArmas().toString());

			} else {
				// System.out.print(" es Villano\n");
				System.out.println("Arma de " + p + " :" + ((Villano) p).getArmaVillano());
				aux = ((Villano) p).getArmaVillano();

				if (aux.getPoder() < mejorArmaSala.getPoder()) {
					System.out.println("Villano recoje: " + mejorArmaSala);

					((Villano) p).setArmaVillano(mejorArmaSala);
					System.out.println("Villano deja: " + aux);
					armas.borrar(mejorArmaSala);
					armas.insertar(aux);
				}

				// System.out.println("Armas de la sala: " + armas);
				// System.out.println("Arma del Villano: " + ((Villano) p).getArmaVillano());

			}
		} else {
			System.out.println("Ningún arma en la sala...");
		}
	}

	// TODO polimorfismo
	/**
	 * El personaje pasado por parametro luchara con el hombre puerta
	 * 
	 * @param p
	 *            el personaje que luchara
	 */
	public void interaccionConHombrePuerta(Personaje p) {
		System.out.println(">>Luchando con hombre puerta...");
		// Personaje p = personajes.peek();

		if (p instanceof SuperHeroe) {
			if (!((SuperHeroe) p).getContenedorArmas().vacio()) {
				Arma mejorArmaHeroe = ((SuperHeroe) p).mejorArma();

				System.out.println("Super héroe usa: " + mejorArmaHeroe);

				// Si el hombre puerta encuentra el arma...
				if (hombrePuerta.getContenedorArmas().pertenece(mejorArmaHeroe)) {
					Arma armaHP = hombrePuerta.getContenedorArmas().consultar(mejorArmaHeroe);
					System.out.println("Hombre puerta usa: " + armaHP);

					if (mejorArmaHeroe.getPoder() > armaHP.getPoder()) {
						System.out.println("El arma del Heroe gana, hombre puerta pierde su arma: " + armaHP);
						// System.out.println(hombrePuerta.getContenedorArmas());
						hombrePuerta.getContenedorArmas().borrar(armaHP);
						// System.out.println("Soltada...");
						// System.out.println(hombrePuerta.getContenedorArmas().toString());
					}

				} else {
					System.out.println("El hombre puerta no posee el arma, no habrá lucha");
				}

				System.out.println("El héroe pierde su arma: " + mejorArmaHeroe);
				// System.out.println(((SuperHeroe) p).getContenedorArmas().toString());
				((SuperHeroe) p).getContenedorArmas().borrar(mejorArmaHeroe);
				// System.out.println("Soltada...");
				// System.out.println(((SuperHeroe) p).getContenedorArmas().toString());
			} else {
				System.out.println("El super héroe no tiene ningun arma...");

			}
		} else {
			Arma armaVillano = ((Villano) p).getArmaVillano();
			Arma mejorArmaHP = hombrePuerta.getContenedorArmas().mayor();
			System.out.println("Hombre puerta usa: " + mejorArmaHP);
			System.out.println("Villano usa: " + armaVillano);

			if (armaVillano.getPoder() > mejorArmaHP.getPoder()) {
				System.out.println("Villano gana, el hombre puerta pierde: " + mejorArmaHP);
				// System.out.println("El hombre puerta borrará " + mejorArmaHP);
				hombrePuerta.getContenedorArmas().borrar(mejorArmaHP);
				// System.out.println("Borrada...");
				// System.out.println(hombrePuerta.getContenedorArmas().toString());
			} else {
				System.out.println("Hombre puerta gana, no se hará nada");

			}

		}

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

		s.insertarHombrePuerta(hp);

		for (int i = 0; i < armasSalas.length; i++) {
			s.insertarArma(armasSalas[i]);
		}

		s.insertarPersonaje(v);
		System.out.print(s.toString());

		s.interaccionConHombrePuerta(v);

		// s.recojerArma();

	}

}