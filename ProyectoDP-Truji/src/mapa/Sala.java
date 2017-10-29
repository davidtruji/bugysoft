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

	public List<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<Personaje> personajes) {
		this.personajes = personajes;
	}

	public HombrePuerta getHombrePuerta() {
		return hombrePuerta;
	}

	public void setHombrePuerta(HombrePuerta hombrePuerta) {
		this.hombrePuerta = hombrePuerta;
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

	public int getNumSala() {
		return numSala;
	}

	public void setNumSala(int numSala) {
		this.numSala = numSala;
	}

	public void insertarPersonaje(Personaje p) {
		personajes.add(p);
	}

	public void borrarPersonaje() {
		if (!personajes.isEmpty())
			personajes.remove(0);
	}

	public void insertarHombrePuerta(HombrePuerta h) {
		this.hombrePuerta = h;
	}

	public void insertarArma(Arma a) {
		armas.insertar(a);
	}

	// Los personajes recojen el arma correspondiente
	public void recojerArma(Personaje p) {
		Arma aux = new Arma("x", 0);
		System.out.println(">>Recojiendo Armas de la sala...");
		Arma mejorArmaSala = armas.mayor();
		
		if(mejorArmaSala!=null)
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