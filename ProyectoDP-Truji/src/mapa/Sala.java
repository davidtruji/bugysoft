package mapa;

import java.util.LinkedList;
import java.util.Queue;

import estructuras_datos.Arbol;
import personajes.Arma;
import personajes.HombrePuerta;
import personajes.Personaje;
import personajes.SuperHeroe;

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

	// Los personajes recojen el arma correspondiente y la usan en el señor puerta
	public void interaccionPersonaje() {
		Arma mejorArmaSala = armas.mayor();
		System.out.println("Mejor arma de la sala:" + mejorArmaSala);
		Personaje p = personajes.peek();
		System.out.print("Personaje:" + p);

		if (p instanceof SuperHeroe) {
			System.out.print(" es heroe\n");
			

		} else {
			System.out.print(" es Villano\n");

		}

	}

	@Override
	public String toString() {
		String s = "";
		s = "[Sala ID " + numSala + "]" + "\n";
		s = s + "Personajes:\n";
		s = s + personajes.toString() + "\n";
		s = s + "Armas:\n";
		s = s + armas.toString();
		return s;
	}

	public static void main(String args[]) {
		System.out.println("PRUEBAS SALA............................");

		Sala s = new Sala(0, false, false, false, false);
		Arma[] armasSalas = { new Arma("Mjolnir", 29), new Arma("Anillo", 1), new Arma("Garra", 27),
				new Arma("Armadura", 3), new Arma("Red", 25), new Arma("Escudo", 5), new Arma("Lucille", 23),
				new Arma("Lawgiver", 7), new Arma("GuanteInfinito", 21), new Arma("LazoVerdad", 9),
				new Arma("CadenaFuego", 19), new Arma("Capa", 11), new Arma("Flecha", 17), new Arma("Tridente", 13),
				new Arma("Antorcha", 15), new Arma("Baston", 28), new Arma("Latigo", 2), new Arma("MazaOro", 26),
				new Arma("CampoMagnetico", 4), new Arma("Tentaculo", 24), new Arma("CampoEnergia", 6),
				new Arma("Cetro", 22), new Arma("RayoEnergia", 8), new Arma("Laser", 20), new Arma("Bola", 10),
				new Arma("Espada", 18), new Arma("Sable", 12), new Arma("Acido", 16), new Arma("Gema", 14),
				new Arma("Nullifier", 23), new Arma("Mjolnir", 1), new Arma("Anillo", 29), new Arma("Garra", 3),
				new Arma("Armadura", 27), new Arma("Red", 5), new Arma("Escudo", 25), new Arma("Lucille", 7),
				new Arma("Lawgiver", 23), new Arma("GuanteInfinito", 9), new Arma("LazoVerdad", 21),
				new Arma("CadenaFuego", 11), new Arma("Capa", 19), new Arma("Flecha", 13), new Arma("Tridente", 17),
				new Arma("Antorcha", 28), new Arma("Baston", 15), new Arma("Latigo", 26), new Arma("MazaOro", 2),
				new Arma("CampoMagnetico", 24), new Arma("Tentaculo", 4), new Arma("CampoEnergia", 22),
				new Arma("Cetro", 6), new Arma("RayoEnergia", 20), new Arma("Laser", 8), new Arma("Bola", 18),
				new Arma("Espada", 10), new Arma("Sable", 16), new Arma("Acido", 12), new Arma("Gema", 1),
				new Arma("Nullifier", 3) };

		for (int i = 0; i < armasSalas.length; i++) {
			s.insertarArma(armasSalas[i]);
		}

		System.out.print(s.toString());

	}

}