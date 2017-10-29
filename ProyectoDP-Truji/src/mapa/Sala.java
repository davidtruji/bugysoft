package mapa;

import java.util.LinkedList;
import java.util.Queue;

import estructuras_datos.Arbol;
import personajes.Arma;
import personajes.HombrePuerta;
import personajes.Personaje;
import personajes.SuperHeroe;
import personajes.Villano;

@SuppressWarnings("unused")
public class Sala {

	private boolean pnorte;
	private boolean psur;
	private boolean peste;
	private boolean poeste;
	private Integer numSala;
	private Queue<Personaje> personajes;
	private Arbol<Arma> armas;
	private HombrePuerta hombrePuerta;

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

	public Arbol<Arma> getArmas() {
		return armas;
	}

	public void setArmas(Arbol<Arma> armas) {
		this.armas = armas;
	}

	public boolean isPnorte() {
		return pnorte;
	}

	public void setPnorte(boolean pnorte) {
		this.pnorte = pnorte;
	}

	public boolean isPsur() {
		return psur;
	}

	public void setPsur(boolean psur) {
		this.psur = psur;
	}

	public boolean isPeste() {
		return peste;
	}

	public void setPeste(boolean peste) {
		this.peste = peste;
	}

	public boolean isPoeste() {
		return poeste;
	}

	public void setPoeste(boolean poeste) {
		this.poeste = poeste;
	}

	public void setNumSala(Integer numSala) {
		this.numSala = numSala;
	}

	public Queue<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(Queue<Personaje> personajes) {
		this.personajes = personajes;
	}

	public int getNumSala() {
		return numSala;
	}

	public void setNumSala(int numSala) {
		this.numSala = numSala;
	}

	public void insertarPersonaje(Personaje p) {
		personajes.add(p);
	}

	public void insertarHombrePuerta(HombrePuerta h) {
		this.hombrePuerta = h;
	}

	public void insertarArma(Arma a) {
		armas.insertar(a);
	}

	public void recojerArma() {
		Arma aux = new Arma("x", 0);
		System.out.println("Recojiendo Armas de la sala...");
		Arma mejorArmaSala = armas.mayor();
		System.out.println("Mejor arma de la sala:" + mejorArmaSala);
		Personaje p = personajes.peek();
		System.out.print("Personaje:" + p);

		if (p instanceof SuperHeroe) {
			System.out.print(" es heroe\n");
			if (!((SuperHeroe) p).getContenedorArmas().pertenece(mejorArmaSala)) {
				System.out.println("Insertando Arma " + mejorArmaSala + "en heroe " + p);
				((SuperHeroe) p).insertarArmaHeroe(mejorArmaSala);
				System.out.println("Armas de " + p + ": " + ((SuperHeroe) p).getContenedorArmas().toString());
				System.out.println("Borrando mejor arma de sala... no deberia estar: " + mejorArmaSala);
				armas.borrar(mejorArmaSala);
				System.out.println(armas);
			} else {
				aux = ((SuperHeroe) p).getContenedorArmas().consultar(mejorArmaSala);
				System.out.println("Combinando Arma " + mejorArmaSala + "con la de " + p + " " + aux);
				aux.setPoder(mejorArmaSala.getPoder() + aux.getPoder());
				System.out.println("La Arma resultante sería " + aux);
				System.out.println("Armas de " + p + ":" + ((SuperHeroe) p).getContenedorArmas().toString());
				System.out.println("Borrando mejor arma de sala... no deberia estar: " + mejorArmaSala);
				armas.borrar(mejorArmaSala);
				System.out.println(armas);
			}

			System.out.println("Armas de " + p + ": " + ((SuperHeroe) p).getContenedorArmas().toString());

		} else {
			System.out.print(" es Villano\n");
			System.out.println("Arma de " + p + " :" + ((Villano) p).getArmaVillano());
			aux = ((Villano) p).getArmaVillano();

			if (aux.getPoder() < mejorArmaSala.getPoder()) {
				((Villano) p).setArmaVillano(mejorArmaSala);
				armas.borrar(mejorArmaSala);
				armas.insertar(aux);
			}

			System.out.println("Armas de la sala: " + armas);
			System.out.println("Arma del Villano: " + ((Villano) p).getArmaVillano());

		}

	}

	// Los personajes recojen el arma correspondiente y la usan en el señor puerta
	// public void interaccionPersonaje() {
	// Arma mejorArmaSala = armas.mayor();
	// System.out.println("Mejor arma de la sala:" + mejorArmaSala);
	// Personaje p = personajes.poll();
	// System.out.print("Personaje:" + p);
	//
	// if (p instanceof SuperHeroe) {
	// System.out.print(" es heroe\n");
	// if (!((SuperHeroe) p).getContenedorArmas().pertenece(mejorArmaSala))
	// ((SuperHeroe) p).insertarArmaHeroe(mejorArmaSala);
	// else {
	// ((SuperHeroe) p).getContenedorArmas().consultar(mejorArmaSala);
	//
	// }
	//
	// System.out.println(((SuperHeroe) p).getContenedorArmas());
	//
	// } else {
	// System.out.print(" es Villano\n");
	//
	// }
	//
	// }

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

	public static void main(String args[]) {
		System.out.println("PRUEBAS SALA............................");

		Arma a = new Arma("Arma V", 28);
		Villano v = new Villano("Malo", 'M', a);
		Arma a1 = new Arma("Mjolnir", 21);
		SuperHeroe h = new SuperHeroe("Bueno", 'B');
		h.insertarArmaHeroe(a1);
		Sala s = new Sala(0, false, false, false, false);
		Arma[] armasSalas = { new Arma("Mjolnir", 29), new Arma("Anillo", 1), new Arma("Garra", 27),
				new Arma("Armadura", 3), new Arma("Red", 25), new Arma("Escudo", 5), new Arma("Lucille", 23),
				new Arma("Lawgiver", 7), new Arma("GuanteInfinito", 21), new Arma("LazoVerdad", 9),
				new Arma("CadenaFuego", 19) };

		for (int i = 0; i < armasSalas.length; i++) {
			s.insertarArma(armasSalas[i]);
		}

		s.insertarPersonaje(h);
		System.out.print(s.toString());

		s.recojerArma();

	}

}