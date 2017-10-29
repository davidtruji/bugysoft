package mapa;

import java.util.LinkedList;
import java.util.Queue;

import personajes.Arma;
import personajes.HombrePuerta;
import personajes.Personaje;
import personajes.SuperHeroe;
import personajes.Villano;

public class Mapa {

	private Sala tablero[][];
	private int salaDailyPlanet;
	private int alturaPuerta;

	public Mapa(int fil, int col) {
		int num_sala = 0;

		Sala[][] s = new Sala[fil][col];
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[0].length; j++) {
				s[i][j] = new Sala(num_sala, false, false, false, false);
				num_sala++;
			}
		}
		tablero = s;
		salaDailyPlanet = (fil * col) - 1;
	}

	public Mapa(Sala t[][]) {
		tablero = new Sala[t.length][t[0].length];
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[0].length; j++) {
				tablero[i][j] = t[i][j];
			}
		}
	}

	public Mapa(int salaDailyPlanet, int fil, int col, int altura) {
		int num_sala = 0;

		Sala[][] s = new Sala[fil][col];
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[0].length; j++) {
				s[i][j] = new Sala(num_sala, false, false, false, false);
				num_sala++;
			}
		}
		tablero = s;
		this.salaDailyPlanet = (fil * col) - 1;
		alturaPuerta = altura;
	}

	public Sala[][] getTablero() {
		return tablero;
	}

	public void setTablero(Sala[][] tablero) {
		this.tablero = tablero;
	}

	public int getSalaDailyPlanet() {
		return salaDailyPlanet;
	}

	public void setSalaDailyPlanet(int salaDailyPlanet) {
		this.salaDailyPlanet = salaDailyPlanet;
	}

	public int getAlturaPuerta() {
		return alturaPuerta;
	}

	public void setAlturaPuerta(int alturaPuerta) {
		this.alturaPuerta = alturaPuerta;
	}

	public boolean esSalaDailyPlanet(int i, int j) {
		if (tablero[i][j].getNumSala() == salaDailyPlanet)
			return true;
		else
			return false;
	}

	public void construirMapa() {

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				if (i == 0)
					tablero[i][j].setPnorte(true);

				if (j == 0)
					tablero[i][j].setPoeste(true);

				if (i == (tablero.length - 1))
					tablero[i][j].setPsur(true);

				if (j == (tablero[0].length - 1))
					tablero[i][j].setPeste(true);

			}
		}
	}

	public void insertarHombrePuerta(HombrePuerta doorMan) {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				if (esSalaDailyPlanet(i, j))
					tablero[i][j].insertarHombrePuerta(doorMan);
			}
		}

	}

	public void insertarPersonaje(Personaje p, int id) {
		int i, j;
		// int fil = tablero.length;
		int col = tablero[0].length;
		i = id / col;
		j = id % col;

		tablero[i][j].insertarPersonaje(p);

	}

	public void distribuirArmas(int[] idSalasConArmas, Arma[] armasSalas) {
		int x = 0;// Indices de los vectores de los parametros

		int i, j, pos = 0;
		// int fil = tablero.length;
		int col = tablero[0].length;
		for (int y = 0; y < idSalasConArmas.length; y++) {
			int id = idSalasConArmas[y];
			i = id / col;
			j = id % col;
			for (x = 0; x < 5; x++) {
				tablero[i][j].insertarArma(armasSalas[pos]);
				pos++;
			}

		}

	}

	@Override
	public String toString() {
		String t = "";
		Sala sala = null;

		// t = t + "SALA DAILY PLANET ID: " + salaDailyPlanet + "\n";
		for (int i = 0; i < tablero.length; i++) {

			// Dibuja Pared Norte
			for (int j = 0; j < tablero[0].length; j++) {
				sala = tablero[i][j];

				if (sala.isPnorte())
					t = t + "----";
				else
					t = t + "    ";

			}
			t = t + "\n";
			// Dibuja Pared Oeste, ID Sala, Pared Este
			for (int j = 0; j < tablero[0].length; j++) {
				sala = tablero[i][j];

				if (sala.isPoeste())
					t = t + "|";
				else
					t = t + " ";

				t = t + sala.getNumSala();

				if (sala.getNumSala() < 10)
					t = t + " ";

				if (sala.isPeste())
					t = t + "|";
				else
					t = t + " ";

			}
			t = t + "\n";
			// Dibujar Pared Sur
			for (int j = 0; j < tablero[0].length; j++) {
				sala = tablero[i][j];

				if (sala.isPsur())
					t = t + "----";
				else
					t = t + "    ";
			}
			t = t + "\n";
		}

		for (int i = 0; i < tablero.length; i++) {

			for (int j = 0; j < tablero[0].length; j++) {
				sala = tablero[i][j];

				if (esSalaDailyPlanet(i, j)) {
					t = t + "DAILY PLANET " + sala.toString() + "\n\n\n";
					t = t + "Hola soy el hombre puerta y duermo en la sala Daily Planet por las noches \n";
				} else {
					t = t + sala.toString() + "\n\n\n";
				}
			}

		}

		return t;
	}

	public void simulacion() {
		int i, j;
		int id = salaDailyPlanet;
		// boolean portal = false;
		int col = tablero[0].length;
		i = id / col;
		j = id % col;
		HombrePuerta hp = null;
		Personaje p;
		Sala s = tablero[i][j];
		// Queue<Personaje> perAux = new LinkedList<Personaje>();
		// perAux = s.getPersonajes();
		hp = s.getHombrePuerta();
		int t = 0;
		System.out.println("Hombre puerta: " + hp.getContenedorArmas().toString());

		while (t < 5 && !hp.isPortal() && !hp.isPortal()) {
			System.out.println("\n\n");
			System.out.println("..............TURNO " + (t + 1) + "..............");
			int pers = 0;
			while (pers < s.getPersonajes().size() && !hp.isPortal()) {
				System.out.print("\n");

				p = s.getPersonajes().get(pers);

				System.out.println("[  Acciones de " + p + "  ]");

				s.interaccionConHombrePuerta(p);
				s.recojerArma(p);

				hp.ActualizarEstadoPortal(this.getAlturaPuerta());

				if (hp.isPortal())
					System.out.println("##################### Portal abierto!!! JUEGO TERMINADO GANADOR: "
							+ s.getPersonajes().get(pers));
				else
					System.out.println("El portal permance cerrado...");

				// s.borrarPersonaje();
				pers++;
			}
			System.out.print("\n");
			System.out.println("ESTADO DE LA SALA:\n" + s);
			System.out.println("Hombre puerta: " + hp.getContenedorArmas().toString());

			t++;
		}

		System.out.println("SIMULACION ACABADA..............");
		System.out.println("Estado Sala:\n" + s);
		System.out.println("Estado del hombre puerta:\n" + hp.getContenedorArmas());
		System.out.println("Altura: " + hp.getContenedorArmas().alturaArbol());

	}

	/**
	 * Programa principal - EC1.
	 * 
	 * @param args
	 *            Argumentos que recibe el programa principal
	 * @return Retorna la salida del programa
	 */
	public static void main(String args[]) {
		// Creación del mapa
		// Parámetros: sala del Daily Planet, no columnas, no filas y profundidad de
		// apertura del portal
		int dimX = 6;
		int dimY = 6;
		int salaDailyPlanet = (dimX * dimY) - 1;
		int alturaApertura = 4;
		Mapa mapa = new Mapa(salaDailyPlanet, dimX, dimY, alturaApertura);

		// Creación de las armas para el hombre puerta
		int numArmasPuerta = 15;
		Arma[] armasPuerta = { new Arma("CampoEnergia", 5), new Arma("Armadura", 13), new Arma("Anillo", 11),
				new Arma("Acido", 1), new Arma("Antorcha", 5), new Arma("Bola", 3), new Arma("Baston", 22),
				new Arma("CadenaFuego", 11), new Arma("Espada", 11), new Arma("Cetro", 20), new Arma("Capa", 10),
				new Arma("CampoMagnetico", 5), new Arma("Escudo", 3), new Arma("Garra", 22), new Arma("Flecha", 12),
				new Arma("Gema", 4) };

		// Creación del hombre puerta y configuración
		HombrePuerta doorMan = new HombrePuerta();

		// Configurar el hombre puerta introduciendo la combinación de armas
		doorMan.configurar(armasPuerta);

		// Cerrar el portal, por si inicialmente está abierto
		doorMan.cerrar();

		// Añadir el hombre puerta al mapa
		mapa.insertarHombrePuerta(doorMan);

		// Creación de las armas para repartir en salas
		int numArmasSalas = 60;
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

		int[] idSalasConArmas = { 1, 2, 8, 14, 15, 21, 27, 35, 28, 29, 33, 34 };
		mapa.distribuirArmas(idSalasConArmas, armasSalas);

		// La distribución de armas quedará de la siguiente forma:

		// (sala:1: {Mjolnir,29}, {Anillo,1}, {Garra,27}, {Armadura,3}, {Red,25},)
		// (sala:2: {Escudo,5}, {Lucille,23}, {Lawgiver,7},
		// {GuanteInfinito,21},{LazoVerdad,9},)
		// (sala:8: {CadenaFuego,19}, {Capa,11}, {Flecha,17}, {Tridente,13},
		// {Antorcha,15},)
		// (sala:14: {Baston,28}, {Latigo,2}, {MazaOro,26}, {CampoMagnetico,4},
		// {Tentaculo,24},)
		// (sala:15: {CampoEnergia,6}, {Cetro,22}, {RayoEnergia,8}, {Laser,20},
		// {Bola,10},)
		// (sala:21: {Espada,18}, {Sable,12}, {Acido,16}, {Gema,14}, {Nullifier,23},)
		// (sala:27: {Mjolnir,1}, {Anillo,29}, {Garra,3}, {Armadura,27}, {Red,5},)
		// (sala:35: {Escudo,25}, {Lucille,7}, {Lawgiver,23}, {GuanteInfinito,9},
		// {LazoVerdad,21},)
		// (sala:28: {CadenaFuego,11}, {Capa,19}, {Flecha,13}, {Tridente,17},
		// {Antorcha,28},)
		// (sala:29: {Baston,15}, {Latigo,26}, {MazaOro,2}, {CampoMagnetico,24},
		// {Tentaculo,4},)
		// (sala:33: {CampoEnergia,22}, {Cetro,6}, {RayoEnergia,20}, {Laser,8},
		// {Bola,18},)
		// (sala:34: {Espada,10}, {Sable,16}, {Acido,12}, {Gema,1}, {Nullifier,3})

		// Creación de varios personajes
		SuperHeroe thor = new SuperHeroe("Thor", 'T');
		// New: Thor's weapons
		thor.insertarArmaHeroe(new Arma("Baston", 25));
		thor.insertarArmaHeroe(new Arma("Armadura", 15));
		thor.insertarArmaHeroe(new Arma("Mjolnir", 50));
		thor.insertarArmaHeroe(new Arma("Lawgiver", 12));
		mapa.insertarPersonaje(thor, salaDailyPlanet);

		SuperHeroe ironMan = new SuperHeroe("IronMan", 'I');
		// New: IronMan's weapons
		ironMan.insertarArmaHeroe(new Arma("Escudo", 20));
		ironMan.insertarArmaHeroe(new Arma("Garra", 10));
		ironMan.insertarArmaHeroe(new Arma("Gema", 15));
		mapa.insertarPersonaje(ironMan, salaDailyPlanet);

		SuperHeroe storm = new SuperHeroe("Storm", 'S');
		// New: Storm's weapons
		storm.insertarArmaHeroe(new Arma("Baston", 25));
		storm.insertarArmaHeroe(new Arma("Anillo", 10));
		storm.insertarArmaHeroe(new Arma("Capa", 15));
		mapa.insertarPersonaje(storm, salaDailyPlanet);

		SuperHeroe captainAmerica = new SuperHeroe("Capitan América", 'C');
		// New: Captain America's weapons
		captainAmerica.insertarArmaHeroe(new Arma("Cetro", 22));
		captainAmerica.insertarArmaHeroe(new Arma("Bola", 15));
		captainAmerica.insertarArmaHeroe(new Arma("Garra", 24));
		mapa.insertarPersonaje(captainAmerica, salaDailyPlanet);

		// SuperHeroe thor = new SuperHeroe("Thor", 'T');
		// mapa.insertarPersonaje(thor, salaDailyPlanet);
		// SuperHeroe ironMan = new SuperHeroe("IronMan", 'I');
		// mapa.insertarPersonaje(ironMan, salaDailyPlanet);
		// SuperHeroe storm = new SuperHeroe("Storm", 'S');
		// mapa.insertarPersonaje(storm, salaDailyPlanet);
		// SuperHeroe captainAmerica = new SuperHeroe("Capitan América", 'C');

		Villano deadPool = new Villano("Dead Pool", 'D', new Arma("Sable", 17));
		mapa.insertarPersonaje(deadPool, salaDailyPlanet);
		Villano kurtConnnors = new Villano("Kurt Connors", 'K', new Arma("CampoEnergia", 15));
		mapa.insertarPersonaje(kurtConnnors, salaDailyPlanet);
		Villano nebula = new Villano("Nebula", 'N', new Arma("RayoEnergia", 15));
		mapa.insertarPersonaje(nebula, salaDailyPlanet);

		// mapa.pintar(); // se mostrará en este caso únicamente la información del mapa
		mapa.construirMapa();
		System.out.println(mapa.toString());
		mapa.simulacion();

	}

}
