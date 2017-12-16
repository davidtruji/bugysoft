package mapa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import cargador.Cargador;
import cargador.FicheroCarga;
import estructuras_datos.Grafo;
import personajes.Arma;
import personajes.HombrePuerta;
import personajes.Personaje;
import personajes.SuperHeroe;
import personajes.Villano;
import util.GenAleatorios;

enum Dir {
	S, E, N, W
};

/**
 * 
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: EC2
 * @Curso: 2º
 */
public class Mapa {

	private int dimX;
	private int dimY;
	private Sala tablero[][];
	private int salaDailyPlanet;
	private HombrePuerta hp;
	private int alturaPuerta;
	private int turno;
	private Grafo grafo;
	private List<Pared> paredes = new LinkedList<Pared>();
	private Queue<Personaje> salaTesereacto = new LinkedList<Personaje>();
	private Integer[] frecuenciaPaso;
	private static Mapa mapaSingle = null;

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
	 * Constructor privado de mapa que recibe como parametros el id del Daily Plnet,
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
	 * @return
	 */
	private Mapa(int salaDailyPlanet, int fil, int col, int altura) {
		int id = 0;
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

		grafo = new Grafo();

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
		turno = 0;
		this.salaDailyPlanet = salaDailyPlanet;
		alturaPuerta = altura;
		hp = new HombrePuerta(altura);
		frecuenciaPaso = new Integer[dimX * dimY];
		for (int i = 0; i < frecuenciaPaso.length; i++)
			frecuenciaPaso[i] = 0;

		construirParedes();
		kruscal();
		crearAtajos();
		List<Integer> l = new ArrayList<>();
		distribuirArmasBacktracking(l, 0);
		distribuirArmasFrecuencia(armasSalas);
	}

	/**
	 * Devuelve la instancia del patron sigleton o la crea si no existe
	 * 
	 * @param salaDailyPlanet
	 *            es el ID de la sala salaDailyPlanet
	 * @param fil
	 *            es el numero de filas
	 * @param col
	 *            es el numero de columnas
	 * @param altura
	 *            es la altura de la cerradura del Hombre puerta
	 * @return la instancia Mapa
	 */
	static public Mapa getInstancia(int salaDailyPlanet, int fil, int col, int altura) {

		if (mapaSingle == null)
			mapaSingle = new Mapa(salaDailyPlanet, fil, col, altura);

		return mapaSingle;

	}

	/**
	 * Devuelve la instancia del patron sigleton
	 */
	static public Mapa getInstancia() {

		return mapaSingle;

	}

	public Integer[] getFrecuenciaPaso() {
		return frecuenciaPaso;
	}

	public void setFrecuenciaPaso(Integer[] frecuenciaPaso) {
		this.frecuenciaPaso = frecuenciaPaso;
	}

	public int getDimX() {
		return dimX;
	}

	public void setDimX(int dimX) {
		this.dimX = dimX;
	}

	public int getDimY() {
		return dimY;
	}

	public void setDimY(int dimY) {
		this.dimY = dimY;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
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

	public Queue<Personaje> getSalaTesereacto() {
		return salaTesereacto;
	}

	public void setSalaTesereacto(Queue<Personaje> salaTesereacto) {
		this.salaTesereacto = salaTesereacto;
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
			// System.out.println("Numero random: " + randomN);

			paredAux = paredes.get(randomN);
			// System.out.println("Pared a tirar: " + paredAux);

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
		}

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
	 * Muestra la sala de los ganadores y sus mensajes
	 */
	public void mostrarTeseracto() {

		if (!salaTesereacto.isEmpty()) {
			System.out.println("(teseractomembers)");
			System.out.println(salaTesereacto.poll().mensajeOwneroftheworld());
			while (!salaTesereacto.isEmpty()) {
				System.out.println(salaTesereacto.poll().mensajeTeseractomember());

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
	public Sala getSala(int idSala) {
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

	/**
	 * Devuelve si hay la pared indicada en la sala con el ID del parametro
	 * 
	 * @param idSala
	 *            sala a considerar
	 * @return si hay pared
	 */
	public boolean paredNorte(int idSala) {

		if (idSala > dimX - 1 && !grafo.adyacente(idSala, idSala - dimX))
			return true;
		else
			return false;

	}

	/**
	 * Devuelve si hay la pared indicada en la sala con el ID del parametro
	 * 
	 * @param idSala
	 *            sala a considerar
	 * @return si hay pared
	 */
	public boolean paredEste(int idSala) {
		if ((idSala + 1) % dimX != 0 && !grafo.adyacente(idSala, idSala + 1))
			return true;
		else
			return false;

	}

	/**
	 * Devuelve si hay la pared indicada en la sala con el ID del parametro
	 * 
	 * @param idSala
	 *            sala a considerar
	 * @return si hay pared
	 */
	public boolean paredOeste(int idSala) {

		if (idSala % dimX != 0 && !grafo.adyacente(idSala, idSala - 1))
			return true;
		else
			return false;

	}

	/**
	 * Devuelve si hay la pared indicada en la sala con el ID del parametro
	 * 
	 * @param idSala
	 *            sala a considerar
	 * @return si hay pared
	 */
	public boolean paredSur(int idSala) {
		if (idSala < (dimX * dimY) - dimX && !grafo.adyacente(idSala, idSala + dimX))
			return true;
		else
			return false;

	}

	/**
	 * Inserta un personaje en la sala indicada
	 * 
	 * @param p
	 *            el personaje
	 * @param id
	 *            de la sala indicada
	 */
	public void insertarPersonaje(Personaje p, int id) {
		int i, j;
		int col = tablero[0].length;
		i = id / col;
		j = id % col;

		tablero[i][j].insertarPersonaje(p);

	}

	/**
	 * Borra un personaje de la sala indicada
	 * 
	 * @param p
	 *            el personaje
	 * @param id
	 *            de la sala indicada
	 */
	public void borrarPersonaje(Personaje p, int id) {
		int i, j;
		int col = tablero[0].length;
		i = id / col;
		j = id % col;

		tablero[i][j].borrarPersonaje(p);

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

	public void distribuirArmasFrecuencia(Arma[] armasSalas) {
		int x = 0;// Indices de los vectores de los parametros

		int i, j, pos = 0;
		int col = tablero[0].length;
		int mayorFrecuencia;

		for (int k = 0; k < armasSalas.length; k = k + 5) {
			mayorFrecuencia = 0;
			for (int id = 0; id < frecuenciaPaso.length; id++) {
				if (frecuenciaPaso[mayorFrecuencia] < frecuenciaPaso[id])
					mayorFrecuencia = id;
			}
			frecuenciaPaso[mayorFrecuencia] = -1;
			i = mayorFrecuencia / col;
			j = mayorFrecuencia % col;

			for (x = 0; x < 5; x++) {
				tablero[i][j].insertarArma(armasSalas[pos]);
				pos++;
			}
		}
	}

	/**
	 * Dibuja el laberinto antes de crear atajos y las rutas de los personajes
	 * 
	 * @return el dibujo del laberinto y rutas
	 */
	private String mostrarLaberintoRutas() {

		String t = "";
		Sala sala = null;
		int idIzq, idDer;
		Personaje p = null;

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

				if (paredSur(sala.getIdSala()) || i == dimY - 1)
					t = t + "_";
				else
					t = t + " ";

				if (j == dimX - 1)
					t = t + "|";

			}

			t = t + "\n";
		}

		for (int fil = 0; fil < tablero.length; fil++) {
			for (int col = 0; col < tablero[0].length; col++) {

				for (int i = 0; i < tablero[fil][col].getPersonajes().size(); i++) {
					p = tablero[fil][col].getPersonajes().get(i);

					t = t + p.rutaToString() + "\n";
				}

			}
		}

		return t;

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
					if (paredSur(sala.getIdSala()) || i == dimY - 1)
						t = t + "_";
					else
						t = t + " ";
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

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				if (!tablero[i][j].getArmas().vacio())
					t = t + tablero[i][j] + "\n";

			}
		}

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				if (!tablero[i][j].getPersonajes().isEmpty())
					t = t + tablero[i][j].personajesToString();

			}
		}

		return t;
	}

	/**
	 * Metodo de pruebas del toString de la clase mapa
	 */
	public void pruebasToString() {

		Mapa m = Mapa.getInstancia(35, 6, 6, 4);

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
	static private void MostrarCabeceraAscii() {
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
		System.out.println("                                   POR: David Trujillo Torres & Alberto Diaz Martin");
		System.out.print("\n\n");

	}

	/**
	 * Metodo que simula la ejecucion del juego en la sala DP durante 5 turnos
	 */
	public void simulacionEC1() {
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

				p.interaccionHombrePuerta(hp);

				p.recogerArmaPersonaje(s);

				hp.ActualizarEstadoPortal();

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
	 * Simulacion del juego de la EC2
	 * 
	 * @param turnosMax
	 *            son los turnos maximos que se permiten
	 */
	private void simulacionEC2(int turnosMax) {
		int nper, j = 0;
		boolean finJuego = false;
		boolean leToca = false;

		System.out.print(mostrarLaberintoRutas());
		crearAtajos();

		// Turnos
		while (turno < turnosMax && !finJuego) {

			// Recorrido del tablero completo
			for (int fil = 0; fil < tablero.length; fil++) {
				for (int col = 0; col < tablero[0].length; col++) {

					Sala s = tablero[fil][col];

					// Acciones de personajes en cada sala
					if (!s.getPersonajes().isEmpty()) {

						nper = s.getPersonajes().size();

						for (int i = 0; i < nper; i++) {

							Personaje p = null;
							while (j < s.getPersonajes().size() && !leToca) {
								p = s.getPersonajes().get(j);

								if (p.esSuTurno())
									leToca = true;

								j++;
							}
							j = 0;
							leToca = false;

							if (p.getTurno() <= turno && p.esSuTurno()) {

								p.realizarAcciones();
								p.setTurnoUltimo(turno);

								if (hp.isPortal()) {
									finJuego = true;
								}

							} else {
								p.setTurnoUltimo(turno);

							}

						}

					}

				}

			}
			System.out.println("(turn:" + turno + ")");
			System.out.println("(map:" + salaDailyPlanet + ")");
			System.out.println(hp);
			System.out.print(this);
			turno++;
		}
		turno--;
		mostrarTeseracto();
	}

	public void distribuirArmasBacktracking(List<Integer> camino, int posicion) {

		// Mapa m = Mapa.getInstancia();
		Set<Integer> salasAdyacentes = new LinkedHashSet<Integer>();

		camino.add(posicion);
		if (posicion == getSalaDailyPlanet()) {
			incrementarFrecuenciaPaso(camino);
			System.out.println("CAMINO DEVUELTO: \n" + camino);
		} else {
			getGrafo().adyacentes(posicion, salasAdyacentes);
			for (Integer i : salasAdyacentes) {

				if (!camino.contains(i)) {
					distribuirArmasBacktracking(camino, i);
					camino.remove(camino.size() - 1);
				}

			}

		}

	}

	public void incrementarFrecuenciaPaso(List<Integer> camino) {

		for (int i = 0; i < camino.size(); i++) {
			frecuenciaPaso[camino.get(i)]++;
		}

	}

	/**
	 * Main de la clase mapa, desde donde se ejecuta el juego
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Mapa.MostrarCabeceraAscii();
		/**
		 * instancia asociada al fichero de entrada inicio.txt
		 */
		Cargador cargador = new Cargador();
		try {
			/**
			 * Método que procesa línea a línea el fichero de entrada inicio.txt
			 */
			FicheroCarga.procesarFichero("ficherosEntrada/init_10x10_2.txt", cargador);
		} catch (FileNotFoundException valor) {
			System.err.println("Excepción capturada al procesar fichero: " + valor.getMessage());
		} catch (IOException valor) {
			System.err.println("Excepción capturada al procesar fichero: " + valor.getMessage());
		}

		Mapa m = Mapa.getInstancia();
		// m.simulacionEC2(50);
		// m.crearAtajos();
		System.out.println(m.mostrarLaberintoRutas());
		System.out.println(m);
	}

}