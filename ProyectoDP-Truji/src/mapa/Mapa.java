package mapa;

import java.util.LinkedList;
import java.util.List;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import estructuras_datos.Grafo;
import personajes.Arma;
import personajes.HombrePuerta;
import personajes.Personaje;
import personajes.SHExtraSensorial;
import personajes.SHFlight;
import personajes.SHPhysical;
import personajes.SuperHeroe;
import personajes.Villano;
import util.GenAleatorios;

enum Dir {
	S, E, N, W
};

/**
 * 
 * ProyectoDP-Truji
 * 
 * @Fichero: Mapaa.java
 * @Autor: David Trujillo Torres
 * @Fecha: 6 nov. 2017
 */
public class Mapa {

	private int dimX;
	private int dimY;
	private Sala tablero[][];
	private int salaDailyPlanet;
	private HombrePuerta hp;
	private int alturaPuerta;
	private Grafo grafo = new Grafo();
	private List<Pared> paredes = new LinkedList<Pared>();

	/**
	 * Constructor de mapa que recibe como parametros las dimensiones de este
	 * 
	 * @param fil
	 *            numero de filas
	 * @param col
	 *            numero de columnas
	 */
	public Mapa(int fil, int col) {
		int num_sala = 0;

		Sala[][] s = new Sala[fil][col];
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[0].length; j++) {
				s[i][j] = new Sala(num_sala);
				num_sala++;
			}
		}
		tablero = s;
		salaDailyPlanet = (fil * col) - 1;
	}

	/**
	 * Contructor de mapa que recibe como parametro el array de salas
	 * 
	 * @param t
	 *            Array bidimensional de salas
	 */
	public Mapa(Sala t[][]) {
		tablero = new Sala[t.length][t[0].length];
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[0].length; j++) {
				tablero[i][j] = t[i][j];
			}
		}
	}

	/**
	 * Constructor de mapa que recibe como parametros el id del Daily Plnet,
	 * dimensiones y la altura del hombre puerta
	 * 
	 * @param salaDailyPlanet
	 *            Id de la sala DP
	 * @param fil
	 *            numero de filas
	 * @param col
	 *            numero de columnas
	 * @param altura
	 *            altura del hombre puerta
	 */
	public Mapa(int salaDailyPlanet, int fil, int col, int altura) {
		int id = 0;

		Sala[][] s = new Sala[fil][col];
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[0].length; j++) {
				s[i][j] = new Sala(id);
				grafo.nuevoNodo(id);
				id++;
			}
		}
		dimX = col;
		dimY = fil;
		tablero = s;
		this.salaDailyPlanet = (fil * col) - 1;
		alturaPuerta = altura;
		construirParedes();
	}

	public HombrePuerta getHp() {
		return hp;
	}

	public void setHp(HombrePuerta hp) {
		this.hp = hp;
	}

	public Grafo getGrafo() {
		return grafo;
	}

	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}

	/**
	 * Tira la pared pasada por parametro
	 * 
	 * @param p
	 *            es la pared a derrivar
	 */
	public void tirarPared(Pared p) {

		grafo.nuevoArco(p.getOrigen(), p.getDestino(), 1);
		grafo.nuevoArco(p.getDestino(), p.getOrigen(), 1);

	}

	/**
	 * Tira la pared de la sala indicada y direccion indicada
	 * 
	 * @param idSala
	 *            de la sala
	 * @param idSalaDest
	 *            de la sala destino
	 */
	public void tirarPared(int idSala, int idSalaDest) {

		grafo.nuevoArco(idSala, idSalaDest, 1);
		grafo.nuevoArco(idSalaDest, idSala, 1);

	}

	/**
	 * Devuleve si al tirar la pared de una sala quedan espacios vacios
	 * 
	 * @param idSala
	 *            de la sala a comprobar
	 * @param dir
	 *            de la pared que se quiere derrivar
	 * @return si quedan espacios al derrivar la pared
	 */
	public boolean espacioVacio(int idSala, Dir dir) {
		boolean espacioVacio = false;

		switch (dir) {
		case N:
			if (!paredOeste(idSala - dimX)) {
				if (!paredOeste(idSala)) {
					if (!paredNorte(idSala - 1)) {
						espacioVacio = true;
					}
				}
			}

			if (!paredEste(idSala - dimX)) {
				if (!paredEste(idSala)) {
					if (!paredNorte(idSala + 1)) {
						espacioVacio = true;
					}
				}
			}

			break;
		case E:

			if (!paredEste(idSala - dimX)) {
				if (!paredNorte(idSala + 1)) {
					if (!paredNorte(idSala)) {
						espacioVacio = true;
					}
				}
			}

			if (!paredEste(idSala + dimX)) {
				if (!paredSur(idSala + 1)) {
					if (!paredSur(idSala)) {
						espacioVacio = true;
					}
				}
			}

			break;
		case S:
			if (!paredOeste(idSala)) {
				if (!paredOeste(idSala + dimX)) {
					if (!paredSur(idSala - 1)) {
						espacioVacio = true;
					}
				}
			}

			if (!paredEste(idSala)) {
				if (!paredEste(idSala + dimX)) {
					if (!paredSur(idSala + 1)) {
						espacioVacio = true;
					}
				}
			}

			break;
		case W:

			if (!paredOeste(idSala - dimX)) {
				if (!paredNorte(idSala)) {
					if (!paredNorte(idSala - 1)) {
						espacioVacio = true;
					}
				}
			}

			if (!paredSur(idSala)) {
				if (!paredOeste(idSala + dimX)) {
					if (!paredSur(idSala - 1)) {
						espacioVacio = true;
					}
				}
			}

			break;

		default:
			System.out.println("Mapa.comprobarEspacioVacio() error en la direccion introducida");
			break;
		}

		return espacioVacio;
	}

	public List<Pared> getParedes() {
		return paredes;
	}

	public void setParedes(List<Pared> paredes) {
		this.paredes = paredes;
	}

	public HombrePuerta getHombrePuerta() {
		return hp;
	}

	public void setHombrePuerta(HombrePuerta hp) {
		this.hp = hp;
	}

	/**
	 * Metodo que devuleve el tablero
	 * 
	 * @return Matriz de salas
	 */
	public Sala[][] getTablero() {
		return tablero;
	}

	/**
	 * Set del tablero del mapa
	 * 
	 * @param tablero
	 *            Matriz de salas
	 */
	public void setTablero(Sala[][] tablero) {
		this.tablero = tablero;
	}

	/**
	 * Metodo que devuelve el id de la sala DP
	 * 
	 * @return Entero con el id de DP
	 */
	public int getSalaDailyPlanet() {
		return salaDailyPlanet;
	}

	/**
	 * Metodo Set de la sala DP
	 * 
	 * @param salaDailyPlanet
	 *            Entero que sera el id de la sala DP
	 */
	public void setSalaDailyPlanet(int salaDailyPlanet) {
		this.salaDailyPlanet = salaDailyPlanet;
	}

	/**
	 * Devuelve la altura del hombre puerta
	 * 
	 * @return Entero con la altura del hombre puerta
	 */
	public int getAlturaPuerta() {
		return alturaPuerta;
	}

	/**
	 * Set de la altura del hombre puerta
	 * 
	 * @param alturaPuerta
	 */
	public void setAlturaPuerta(int alturaPuerta) {
		this.alturaPuerta = alturaPuerta;
	}

	/**
	 * Metodo que devulve si la sala pasada por parametros es la sala DP
	 * 
	 * @param i
	 *            cordenada de filas
	 * @param j
	 *            cordenada de columna
	 * @return true si es la sala DP y false en caso contrario
	 */
	public boolean esSalaDailyPlanet(int i, int j) {
		if (tablero[i][j].getIdSala() == salaDailyPlanet)
			return true;
		else
			return false;
	}

	/**
	 * Algoritmo de derrivo de paredes kruscal
	 */
	public void kruscal() {
		int randomN;
		Pared paredAux = new Pared(), paredAuxInversa = new Pared();
		Sala sOri, sDest;

		while (!paredes.isEmpty()) {
			// System.out.println(paredes);

			randomN = GenAleatorios.generarNumero(paredes.size());
			System.out.println("Numero random: " + randomN);

			paredAux = paredes.get(randomN);
			System.out.println("Pared a tirar: " + paredAux);

			paredAuxInversa.setDestino(paredAux.getOrigen());
			paredAuxInversa.setOrigen(paredAux.getDestino());

			paredes.remove(paredAux);
			paredes.remove(paredAuxInversa);

			sOri = getSala(paredAux.getOrigen());
			sDest = getSala(paredAux.getDestino());

			if (sOri.getMarca() != sDest.getMarca()) {
				tirarPared(paredAux);
				marcarSalasId(sOri.getMarca(), sDest.getMarca());

			}
			System.out.println(this);
		}
		System.out.println("Laberinto Objetivo:");
		System.out.println(" _ _ _ _ _ _ ");
		System.out.println("|_ _  |_ _  |");
		System.out.println("|_    |_    |");
		System.out.println("|_ _|_  | |_|");
		System.out.println("|  _     _| |");
		System.out.println("|_ _|_|  _  |");
		System.out.println("|_ _ _ _ _|_|");

		// System.out.println("Mapa.kruscal(), atajos; " + crearAtajos());
	}

	/**
	 * Derriba N paredes, N es el 5% de las salas que tiene el tablero
	 */
	public void crearAtajos() {
		int atajosCreados = 0;
		int atajos = (int) ((dimX * dimY) * 0.05);
		int randomN;

		// Orden: Norte, Sur, Oeste y Este.
		while (atajosCreados != atajos) {

			randomN = GenAleatorios.generarNumero(dimX * dimY);
			// System.out.println("Numero random: " + randomN + " atajos: " + atajos);

			if (paredNorte(randomN) && !espacioVacio(randomN, Dir.N)) {

				tirarPared(randomN, randomN - dimX);
				atajosCreados++;

			} else if (paredSur(randomN) && !espacioVacio(randomN, Dir.S)) {

				tirarPared(randomN, randomN + dimX);
				atajosCreados++;

			} else if (paredOeste(randomN) && !espacioVacio(randomN, Dir.W)) {

				tirarPared(randomN, randomN - 1);
				atajosCreados++;

			} else if (paredEste(randomN) && !espacioVacio(randomN, Dir.E)) {

				tirarPared(randomN, randomN + 1);
				atajosCreados++;

			}

		}

	}

	/**
	 * Cambia la marca de las salas con una marca determinada por una nueva marca
	 * 
	 * @param marca
	 *            es la marca a cambiar
	 * @param nuevaMarca
	 *            es la marca por la que se va a cambiar
	 */
	public void marcarSalasId(int marca, int nuevaMarca) {
		Sala s = null;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				s = tablero[i][j];
				if (s.getMarca() == marca)
					s.setMarca(nuevaMarca);

			}
		}
	}

	/**
	 * Retorna la sala con el ID indicado
	 * 
	 * @param idSala
	 *            es el ID de la sala a devolver
	 * @return la sala con el ID indicado
	 */
	Sala getSala(int idSala) {
		Sala s = new Sala(0);
		int i, j;
		int col = tablero[0].length;
		i = idSala / col;
		j = idSala % col;

		s = tablero[i][j];
		return s;
	}

	/**
	 * Mete todas las paredes del mapa en la lista de paredes
	 */
	public void construirParedes() {
		Pared p = null;

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {

				// Pared N
				if (i > 0) {
					p = new Pared();
					p.setOrigen(tablero[i][j].getIdSala());
					p.setDestino(tablero[i][j].getIdSala() - dimX);
					paredes.add(p);
				}

				// Pared E
				if (j < dimX - 1) {
					p = new Pared();
					p.setOrigen(tablero[i][j].getIdSala());
					p.setDestino(tablero[i][j].getIdSala() + 1);
					paredes.add(p);
				}

				// Pared S
				if (i < dimY - 1) {
					p = new Pared();
					p.setOrigen(tablero[i][j].getIdSala());
					p.setDestino(tablero[i][j].getIdSala() + dimX);
					paredes.add(p);
				}

				// Pared O
				if (j > 0) {
					p = new Pared();
					p.setOrigen(tablero[i][j].getIdSala());
					p.setDestino(tablero[i][j].getIdSala() - 1);
					paredes.add(p);
				}

			}
		}

	}

	private boolean paredNorte(int idSala) {

		if (idSala > dimX - 1 && !grafo.adyacente(idSala, idSala - dimX))
			return true;
		else
			return false;

	}

	private boolean paredEste(int idSala) {
		if ((idSala + 1) % dimX != 0 && !grafo.adyacente(idSala, idSala + 1))
			return true;
		else
			return false;

	}

	private boolean paredOeste(int idSala) {

		if (idSala % dimX != 0 && !grafo.adyacente(idSala, idSala - 1))
			return true;
		else
			return false;

	}

	private boolean paredSur(int idSala) {
		if (idSala < (dimX * dimY) - dimX && !grafo.adyacente(idSala, idSala + dimX))
			return true;
		else
			return false;

	}

	public void insertarPersonaje(Personaje p, int id) {
		int i, j;
		int col = tablero[0].length;
		i = id / col;
		j = id % col;

		tablero[i][j].insertarPersonaje(p);

	}

	/**
	 * Metodo que distribuye un array con armas segun los requisitos de la primera
	 * entrega
	 * 
	 * @param idSalasConArmas
	 *            Array que contiene Orden en que las armas deben ser dejadas
	 * @param armasSalas
	 *            Array con todas las armas que se deben dejar
	 */
	public void distribuirArmas(int[] idSalasConArmas, Arma[] armasSalas) {
		int x = 0;// Indices de los vectores de los parametros

		int i, j, pos = 0;
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

	/**
	 * To String de la clase Mapa
	 */
	@Override
	public String toString() {
		String t = "";
		Sala sala = null;
		char c = ' ';
		int idIzq, idDer;

		// Dibuja Pared Norte
		for (int j = 0; j < (tablero[0].length - 1) + 1; j++) {

			t = t + " _";

		}
		t = t + "\n";

		for (int i = 0; i < tablero.length; i++) {

			// Dibuja Pared Oeste, ID Sala, Pared Este, Pared Sur
			for (int j = 0; j < tablero[0].length; j++) {
				sala = tablero[i][j];

				if (j == 0)
					t = t + "|";
				else {

					idIzq = sala.getIdSala() - 1;
					idDer = sala.getIdSala();

					if (!grafo.adyacente(idIzq, idDer))
						t = t + "|";
					else
						t = t + " ";

				}

				if (sala.getPersonajes().isEmpty()) {
					// t = t + sala.getMarca();
					if (paredSur(sala.getIdSala()) || i == dimY - 1)
						t = t + "_";
					else
						t = t + " ";
					// TODO:Cambiar a como estaba antes al acabar pruebas kruscal
				} else if (sala.getPersonajes().size() == 1) {
					c = sala.getPersonajes().get(0).getInicial();

					t = t + c;

				} else {
					t = t + sala.getPersonajes().size();
				}

				if (j == dimX - 1)
					t = t + "|";

			}
			t = t + "\n";

		}

		return t;
	}

	/**
	 * Metodo de pruebas del toString de la clase mapa
	 */
	public void pruebasToString() {

		Mapa m = new Mapa(35, 6, 6, 4);

		System.out.println("Dibujo del mapa predeterminado(VACIO)");
		System.out.println("_____________");
		System.out.println("|_|_|_|_|_|_|");
		System.out.println("|_|_|_|_|_|_|");
		System.out.println("|_|_|_|_|_|_|");
		System.out.println("|_|_|_|_|_|_|");
		System.out.println("|_|_|_|_|_|_|");
		System.out.println("|_|_|_|_|_|_|");
		System.out.println(m);
		System.out.print("\n\n");

		System.out.println("Dibujo del mapa:");
		System.out.println("_____________");
		System.out.println("|A _|_|_|_|_|");
		System.out.println("|_|_|_|_|_|_|");
		System.out.println("|_|_|_|_|_|_|");
		System.out.println("|_|_|_|_|_|_|");
		System.out.println("|_|_|_|_|_|_|");
		System.out.println("|_|_|_|_|_|_|");
		m.insertarPersonaje(new SuperHeroe("Acuaman", 'A'), 0);
		m.getGrafo().nuevoArco(0, 1, -1);
		m.getGrafo().nuevoArco(0, 6, -1);
		System.out.println(m);
		System.out.print("\n\n");

		System.out.println("Dibujo del mapa:");
		System.out.println("_____________");
		System.out.println("|A _ _ _ _ _|");
		System.out.println("| |_|_|_|_|_|");
		System.out.println("| |_|_|_|_|_|");
		System.out.println("| |_|_|_|_|_|");
		System.out.println("| |_|_|_|_|_|");
		System.out.println("|_|_|_|_|_|_|");
		m.getGrafo().nuevoArco(1, 2, -1);
		m.getGrafo().nuevoArco(2, 3, -1);
		m.getGrafo().nuevoArco(3, 4, -1);
		m.getGrafo().nuevoArco(4, 5, -1);
		m.getGrafo().nuevoArco(6, 12, -1);
		m.getGrafo().nuevoArco(12, 18, -1);
		m.getGrafo().nuevoArco(18, 24, -1);
		m.getGrafo().nuevoArco(24, 30, -1);
		System.out.println(m);
		System.out.print("\n\n");

		System.out.println("Dibujo del mapa:");
		System.out.println("_____________");
		System.out.println("|2 _ _ _ _ _|");
		System.out.println("| |_|_|_|_|_|");
		System.out.println("| |_|_|_|_|_|");
		System.out.println("| |_|_|_|_|_|");
		System.out.println("| |_|_|_|_|_|");
		System.out.println("|_|_|_|_|_|_|");
		m.insertarPersonaje(new Villano("Señor X", 'X', new Arma("Espada", 10)), 0);
		System.out.println(m);
		System.out.print("\n\n");

	}

	/**
	 * Muestra el titulo del programa con caracteres ascii
	 */
	private void MostrarCabeceraAscii() {
		System.out.print("\n\n");
		System.out.println(
				" ████████╗██╗  ██╗███████╗    ███╗   ███╗ █████╗ ██████╗ ██╗   ██╗███████╗██╗     ██╗      ██████╗ ██╗   ██╗███████╗");
		System.out.println(
				" ╚══██╔══╝██║  ██║██╔════╝    ████╗ ████║██╔══██╗██╔══██╗██║   ██║██╔════╝██║     ██║     ██╔═══██╗██║   ██║██╔════╝");
		System.out.println(
				"    ██║   ███████║█████╗      ██╔████╔██║███████║██████╔╝██║   ██║█████╗  ██║     ██║     ██║   ██║██║   ██║███████╗");
		System.out.println(
				"    ██║   ██╔══██║██╔══╝      ██║╚██╔╝██║██╔══██║██╔══██╗╚██╗ ██╔╝██╔══╝  ██║     ██║     ██║   ██║██║   ██║╚════██║");
		System.out.println(
				"    ██║   ██║  ██║███████╗    ██║ ╚═╝ ██║██║  ██║██║  ██║ ╚████╔╝ ███████╗███████╗███████╗╚██████╔╝╚██████╔╝███████║");
		System.out.println(
				"    ╚═╝   ╚═╝  ╚═╝╚══════╝    ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝  ╚═══╝  ╚══════╝╚══════╝╚══════╝ ╚═════╝  ╚═════╝ ╚══════╝");
		System.out.println("                 ██████╗ ██████╗      ██████╗ ██████╗ ███╗   ███╗██╗ ██████╗███████╗");
		System.out.println("                 ██╔══██╗██╔══██╗    ██╔════╝██╔═══██╗████╗ ████║██║██╔════╝██╔════╝");
		System.out.println("                 ██║  ██║██████╔╝    ██║     ██║   ██║██╔████╔██║██║██║     ███████╗");
		System.out.println("                 ██║  ██║██╔═══╝     ██║     ██║   ██║██║╚██╔╝██║██║██║     ╚════██║");
		System.out.println("                 ██████╔╝██║         ╚██████╗╚██████╔╝██║ ╚═╝ ██║██║╚██████╗███████║");
		System.out.println("                 ╚═════╝ ╚═╝          ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚═╝ ╚═════╝╚══════╝");
		System.out.println("                                   POR: David Trujillo Torres & Alberto GAY");
		System.out.print("\n\n");

	}

	/**
	 * Metodo que simula la ejecucion del juego en la sala DP durante 5 turnos
	 */
	public void simulacion() {
		int i, j;
		int id = salaDailyPlanet;
		int col = tablero[0].length;
		i = id / col;
		j = id % col;
		// HombrePuerta hp = null;
		Personaje p;
		Sala s = tablero[i][j];
		// hp = s.getHombrePuerta();
		int t = 0;
		System.out.println("Hombre puerta: " + hp.getContenedorArmas().toString());

		while (t < 5 && !hp.isPortal()) {
			System.out.println("\n\n");
			System.out.println("..............TURNO " + (t + 1) + "..............");
			int pers = 0;

			while (pers < s.getPersonajes().size() && !hp.isPortal()) {
				System.out.print("\n");

				p = s.getPersonajes().get(pers);

				System.out.println("[  Acciones de " + p + "  ]");

				// TODO cambiado con el polimorfismo
				p.interaccionHombrePuerta(hp);

				// TODO cambiado con el polimorfismo
				p.recogerArmaPersonaje(s);

				hp.ActualizarEstadoPortal(this.getAlturaPuerta());

				if (hp.isPortal())
					System.out.println("##################### Portal abierto!!! JUEGO TERMINADO GANADOR: "
							+ s.getPersonajes().get(pers));
				else
					System.out.println("El portal permance cerrado...");

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
	 * Main de la clase mapa, desde donde se ejecuta el juego
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		int dimX = 6;
		int dimY = 6;
		int dailyPlanetSquare = (dimX * dimY) - 1;
		int initialDepth = 4;
		int MAXTURNS = 50;
		// Creating the map
		// Parameters: dailyPlanet square, columns number, rows number,
		// Initial depth for the lock
		// The constructor must create the different squares for the map
		Mapa map = new Mapa(dailyPlanetSquare, dimY, dimX, initialDepth);
		map.MostrarCabeceraAscii();
		// map.construirParedes();
		// map.pruebasToString();
		map.kruscal();
		map.crearAtajos();
		System.out.println(map);
		// System.out.println(map.getParedes().toString());
		// Generate the weapons and distribute them. In this stage, we pass to the map
		// an array
		// with the identifiers of the squares where the weapons are going to be
		// distributed
		// it was specified in the previous stage
		int[] idSquaresWithWeapons = { 1, 2, 8, 14, 15, 21, 27, 35, 28, 29, 33, 34 };
		// map.distribuirArmas(idSquaresWithWeapons);
		// Creating and configuring the DoorMan character. It is not specified here
		// since
		// it was specified in the previous stage
		HombrePuerta doorMan = new HombrePuerta(initialDepth);
		map.setHombrePuerta(doorMan);
		// Creating the characters
		// Creating a SHPHYSICAL
		// Parameters: name, mark, turn in which it will start the simulation and
		// initial square
		// SHPhysical shPhDare = new SHPhysical("Daredevil", 'D', 1, 0);
		// Creating the route for the SHPHYSICAL:// (route:D: E E S S E S S E E S)
		// LinearDS<Dir> directionsDare = { Dir.E, Dir.E, Dir.S, Dir.S, Dir.E, Dir.S,
		// Dir.S, Dir.E, Dir.E, Dir.S };
		// shPhDare.asignRoute(directionsDare);
		// Adding the character into the map
		// map.addCharacter(shPhDare);
		// Creating a SHEXTRASENSORIAL
		// Parameters: name, mark, turn in which it will start the simulation and
		// initial square
		// SHExtraSensorial shExProf = new SHExtrasensorial("ProfessorX", 'P', 1, 0);
		// (route:P: E E S W W E S W E N E S E S W W W S E W N E E S N E S S W W W E E E
		// E E)
		// LinearDS<Dir> directionsProf = {Dir.E, Dir.E, Dir.S, Dir.W, Dir.W, Dir.E,
		// Dir.S, Dir.W, Dir.E,
		// Dir.N, Dir.E, Dir.S, Dir.E, Dir.S, Dir.W, Dir.W, Dir.W, Dir.S, Dir.E,
		// Dir.W, Dir.N, Dir.E, Dir.E, Dir.S, Dir.N, Dir.E, Dir.S, Dir.S, Dir.W,
		// Dir.W, Dir.W, Dir.E, Dir.E, Dir.E, Dir.E, Dir.E};
		// shExProf.asignRoute(directionsProf);
		// Adding the character into the map
		// map.addCharacter(shExProf);
		// Creating a SHFLIGHT
		// Parameters: name, mark, turn in which it will start the simulation and
		// initial square
		// SHFlight shFli = new SHFlight("Eternity", 'F', 1, map.getSouthWestCorner());
		// (route:F: E E E N E E S)
		// LinearDS<Dir> directionsFli = {Dir.E, Dir.E, Dir.E, Dir.N, Dir.E, Dir.E,
		// Dir.S};
		// shFli.asignRoute(directionsFli);
		// Adding the character into the map
		// map.addCharacter(shFli);
		// Creating a Villain
		// Parameters: name, mark, turn in which it will start the simulation and
		// initial square
		// Villano villainAb = new Villano("Abomination", 'A', 1,
		// map.getNorthEastCorner());
		// (ruta:A: S S N W S S W S E E N S S)
		// LinearDS<Dir> directionsA = {Dir.S, Dir.S, Dir.N, Dir.W, Dir.S, Dir.S, Dir.W,
		// Dir.S, Dir.E, Dir.E,
		// Dir.N, Dir.S, Dir.S};
		// villainAb.asignRoute(directionsA);
		// Adding the character into the map
		// map.addCharacter(villainAb);
		// map.paint();
		// Executing the simulation
		// The process method must be executed turn after turn, traversing the map from
		// square 0
		// to the last square and the characters stored in each square must execute
		// their actions
		// in a chronologically order (the characters that arrived first are the first
		// in leaving the square)
		// for (int i=0; i<MAXTURNS;i++) {
		// map.process(i);
		// }
		// map.paint();
		// }

	}

}