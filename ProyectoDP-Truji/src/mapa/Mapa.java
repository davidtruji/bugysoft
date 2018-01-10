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
import personajes.Villano;
import util.Dir;
import util.GenAleatorios;
import util.Log;

/**
 * 
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: ENERO
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
	 * @throws IOException
	 */
	private Mapa(int salaDailyPlanet, int fil, int col, int altura) throws IOException {
		int id = 0;

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
		grafo.warshall();
		grafo.floyd();
		System.out.print(stringLaberinto());
		Log.writeInLog(stringLaberinto());
		crearAtajos();
		List<Integer> l = new ArrayList<>();
		distribuirArmasBacktracking(l, 0);
		distribuirArmasFrecuencia();
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
	 * @throws IOException
	 */
	static public Mapa getInstancia(int salaDailyPlanet, int fil, int col, int altura) throws IOException {

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

	/**
	 * Devuelve el array que contiene la frecuentcia de paso por las salas
	 * 
	 * @return el array que contiene la frecuentcia de paso
	 */
	public Integer[] getFrecuenciaPaso() {
		return frecuenciaPaso;
	}

	/**
	 * Set del array que contiene la frecuencia de paso por las salas
	 * 
	 * @param frecuenciaPaso
	 *            array que contiene la frecuentcia de paso por las salas
	 */
	public void setFrecuenciaPaso(Integer[] frecuenciaPaso) {
		this.frecuenciaPaso = frecuenciaPaso;
	}

	/**
	 * Devuelve la dimension X del tablero
	 * 
	 * @return dimension X del tablero
	 */
	public int getDimX() {
		return dimX;
	}

	/**
	 * Establece la dimension X del tablero
	 * 
	 * @param dimX
	 *            la dimension X a poner
	 */
	public void setDimX(int dimX) {
		this.dimX = dimX;
	}

	/**
	 * Devuelve la dimension Y del tablero
	 * 
	 * @return dimension Y del tablero
	 */
	public int getDimY() {
		return dimY;
	}

	/**
	 * Establece la dimension Y del tablero
	 * 
	 * @param dimY
	 *            la dimension Y a poner
	 */
	public void setDimY(int dimY) {
		this.dimY = dimY;
	}

	/**
	 * Devuelve el turno por el que va la partida
	 * 
	 * @return turno actual
	 */
	public int getTurno() {
		return turno;
	}

	/**
	 * Ajusta el turno actual
	 * 
	 * @param turno
	 *            que se desde poner
	 */
	public void setTurno(int turno) {
		this.turno = turno;
	}

	/**
	 * Devuelve el Hombre Puerta
	 * 
	 * @return el hombre puerta
	 */
	public HombrePuerta getHp() {
		return hp;
	}

	/**
	 * Establece el hombre puerta
	 * 
	 * @param hp
	 *            el hombre puerta que se desea establecer
	 */
	public void setHp(HombrePuerta hp) {
		this.hp = hp;
	}

	/**
	 * Devuelve el grafo del laberinto
	 * 
	 * @return el grafo laberinto
	 */
	public Grafo getGrafo() {
		return grafo;
	}

	/**
	 * Estblece el grafo del laberinto
	 * 
	 * @param grafo
	 *            que se establecera
	 */
	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}

	/**
	 * Devuelve la lista que contiene las paredes del laberinto
	 * 
	 * @return lista que contiene las paredes del laberinto
	 */
	public List<Pared> getParedes() {
		return paredes;
	}

	/**
	 * Establece la lista que contiene las paredes del laberinto
	 * 
	 * @param lista
	 *            paredes del laberinto
	 */
	public void setParedes(List<Pared> paredes) {
		this.paredes = paredes;
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
	 * Devuelve la cola que contiene a los personajes que acceden al Teseracto
	 * 
	 * @return Cola de personajes de Teseracto
	 */
	public Queue<Personaje> getSalaTesereacto() {
		return salaTesereacto;
	}

	/**
	 * Establece la cola que contiene a los personajes que acceden al Teseracto
	 * 
	 * @param salaTesereacto
	 *            Cola de personajes de Teseracto a establecer
	 */
	public void setSalaTesereacto(Queue<Personaje> salaTesereacto) {
		this.salaTesereacto = salaTesereacto;
	}

	/**
	 * Devuelve el ID de la sala que esta en la direccion indicada de la sala pasada
	 * por parametro
	 * 
	 * @param id
	 *            De la sala actual
	 * @param dir
	 *            Direccion en formato DIR de la sala que queremos conocer el ID,
	 *            respecto a la sala actual
	 * @return ID de la sala deseada
	 */
	public int getSalaDireccion(int id, Dir dir) {
		int id_vecina = 0;

		switch (dir) {
		case N:
			id_vecina = getSalaNorte(id);

			break;
		case E:
			id_vecina = getSalaEste(id);

			break;
		case S:
			id_vecina = getSalaSur(id);

			break;
		case W:
			id_vecina = getSalaOeste(id);

			break;

		default:
			System.out.println("Error en la direccion introducida");
			break;
		}

		return id_vecina;

	}

	/**
	 * Devuelve el ID de la sala al Norte de la indicada por parametro
	 * 
	 * @param ID
	 *            de la sala actual
	 * @return ID de la sala al norte de la actual
	 */
	public int getSalaNorte(int id) {
		return id - dimX;
	}

	/**
	 * Devuelve el ID de la sala al este de la indicada por parametro
	 * 
	 * @param ID
	 *            de la sala actual
	 * @return ID de la sala al este de la actual
	 */
	public int getSalaEste(int id) {
		return id + 1;
	}

	/**
	 * Devuelve el ID de la sala al sur de la indicada por parametro
	 * 
	 * @param ID
	 *            de la sala actual
	 * @return ID de la sala al sur de la actual
	 */
	public int getSalaSur(int id) {
		return id + dimX;
	}

	/**
	 * Devuelve el ID de la sala al oeste de la indicada por parametro
	 * 
	 * @param ID
	 *            de la sala actual
	 * @return ID de la sala al oeste de la actual
	 */
	public int getSalaOeste(int id) {
		return id - 1;
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
	 * 
	 * @throws IOException
	 */
	public void mostrarTeseracto() throws IOException {

		if (!salaTesereacto.isEmpty()) {
			System.out.println("(teseractomembers)");
			Log.writeInLog("(teseractomembers)" + "\n");

			String msgOwner = salaTesereacto.poll().mensajeOwneroftheworld();

			System.out.println(msgOwner);
			Log.writeInLog(msgOwner + "\n");

			while (!salaTesereacto.isEmpty()) {
				String msgTeseracto = salaTesereacto.poll().mensajeTeseractomember();

				System.out.println(msgTeseracto);
				Log.writeInLog(msgTeseracto + "\n");

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
	 * Devuelve si la sala ID pasado por parametro tiene borde al norte
	 * 
	 * @param idSala
	 *            que se desea conocer si tiene borde
	 * @return Verdadero si hay borde falso en caso contrario
	 */
	public boolean bordeNorte(int idSala) {

		if (idSala < dimX)
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
	 * Devuelve si la sala ID pasado por parametro tiene borde al este
	 * 
	 * @param idSala
	 *            que se desea conocer si tiene borde
	 * @return Verdadero si hay borde falso en caso contrario
	 */
	public boolean bordeEste(int idSala) {
		if ((idSala + 1) % dimX == 0)
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
	 * Devuelve si la sala ID pasado por parametro tiene borde al oeste
	 * 
	 * @param idSala
	 *            que se desea conocer si tiene borde
	 * @return Verdadero si hay borde falso en caso contrario
	 */
	public boolean bordeOeste(int idSala) {

		if (idSala % dimX == 0)
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
	 * Devuelve si la sala ID pasado por parametro tiene borde al sur
	 * 
	 * @param idSala
	 *            que se desea conocer si tiene borde
	 * @return Verdadero si hay borde falso en caso contrario
	 */
	public boolean bordeSur(int idSala) {
		if (idSala >= (dimX * dimY) - dimX)
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

	/**
	 * Distribuye las armas segun la frecuencia del array de frecuencia de paso,
	 * inicializado con el backtracking de armas
	 */
	public void distribuirArmasFrecuencia() {

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
	private String stringLaberinto() {

		String t = "";
		Sala sala = null;
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

				if (paredSur(sala.getIdSala()) || i == dimY - 1)
					t = t + "_";
				else
					t = t + " ";

				if (j == dimX - 1)
					t = t + "|";

			}

			t = t + "\n";
		}

		return t;

	}

	/**
	 * Devuelve el String con las rutas de los personajes creados
	 * 
	 * @return el String con las rutas de los personajes creados
	 */
	public String stringRutas() {
		String t = "";
		Personaje p = null;

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
	@SuppressWarnings("unused")
	private void simulacionEC1() {
		int i, j;
		int id = salaDailyPlanet;
		int col = tablero[0].length;
		i = id / col;
		j = id % col;
		Personaje p;
		Sala s = tablero[i][j];
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
	 * Simulacion del juego Final
	 * 
	 * @param turnosMax
	 *            son los turnos maximos que se permiten
	 * @throws IOException
	 */
	private void simulacionFinal(int turnosMax) throws IOException {
		int nper, j = 0;
		boolean finJuego = false;
		boolean leToca = false;

		System.out.print(stringRutas());
		Log.writeInLog(stringRutas());

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

							// Se selecciona al personaje correspondiente de la sala
							while (j < s.getPersonajes().size() && !leToca) {
								p = s.getPersonajes().get(j);

								if (p.esSuTurno())
									leToca = true;

								j++;
							}
							j = 0;
							leToca = false;

							// Si es el turno del personaje elegido, se realizan sus acciones
							if (p.getTurnoComienzo() <= turno && p.esSuTurno()) {

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
			// Escritura en Consola y Log

			System.out.println("(turn:" + turno + ")");
			Log.writeInLog("(turn:" + turno + ")" + "\n");

			System.out.println("(map:" + salaDailyPlanet + ")");
			Log.writeInLog("(map:" + salaDailyPlanet + ")" + "\n");

			System.out.println(hp);
			Log.writeInLog(hp.toString() + "\n");

			System.out.print(this);
			Log.writeInLog(this.toString());

			turno++;
		}
		turno--;
		mostrarTeseracto();
	}

	/**
	 * Backtraking que incrementa la frecuencia de paso, para saber en que salas
	 * debemos repartir las armas
	 * 
	 * @param camino
	 *            Cada uno de los caminos posibles que se van generando de la sala 0
	 *            a la Daily Planet
	 * @param posicion
	 *            Posicion inicial y actual en cada solucion desde la que se
	 *            calculan los caminos
	 */
	public void distribuirArmasBacktracking(List<Integer> camino, int posicion) {

		Set<Integer> salasAdyacentes = new LinkedHashSet<Integer>();

		camino.add(posicion);
		if (posicion == getSalaDailyPlanet()) {
			incrementarFrecuenciaPaso(camino);
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

	/**
	 * Metodo que incrementa la frecuencia de paso de salas del camino introducido
	 * 
	 * @param camino
	 *            lista que contiene el camino introducido
	 */
	public void incrementarFrecuenciaPaso(List<Integer> camino) {

		for (int i = 0; i < camino.size(); i++) {
			frecuenciaPaso[camino.get(i)]++;
		}

	}

	/**
	 * Metodo que encuentra el camino mas corto desde posicion hasta posicion2
	 * 
	 * @param caminoCorto
	 *            el mejor camino hasta ahora, el mejor al final de la ejecucion
	 * @param camino
	 *            los caminos que va generando el algoritmo
	 * @param posicion
	 *            inicial y de cada sala que se va pasando
	 * @param posicion2
	 *            posicion final
	 */
	public void caminoMasCorto(List<Integer> caminoCorto, List<Integer> camino, int posicion, int posicion2) {

		Mapa m = Mapa.getInstancia();
		Set<Integer> salasAdyacentes = new LinkedHashSet<Integer>();

		camino.add(posicion);
		if (posicion == posicion2) {

			if (caminoCorto.size() == 0) {

				for (int i = 0; i < camino.size(); i++)
					caminoCorto.add(camino.get(i));

			}

			if (camino.size() < caminoCorto.size()) {
				caminoCorto.clear();
				for (int i = 0; i < camino.size(); i++)
					caminoCorto.add(camino.get(i));

			}

		} else {

			m.getGrafo().adyacentes(posicion, salasAdyacentes);
			for (Integer i : salasAdyacentes) {

				if (!camino.contains(i)) {
					caminoMasCorto(caminoCorto, camino, i, posicion2);
					camino.remove(camino.size() - 1);
				}

			}

		}

	}

	/**
	 * Metodo que ejecuta las pruebas de la clase
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private static void pruebasMapa() throws IOException {
		System.out.println("Ejecutando pruebas de la clase Mapa...\n");

		System.out.println();

		System.out.println("Construyendo mapa de 6x6 con kruskal sin atajo...");

		Mapa m = Mapa.getInstancia(35, 6, 6, 1);

		System.out.println();

		System.out.println("Construyendo atajo y mostrar armas insertadas segun Backtracking...\n");

		System.out.println("Salida Esperada:");
		System.out.println("(square:0:(Anillo,1)(Armadura,3)(Garra,27)(Mjolnir,29)(Red,25))");
		System.out.println("(square:1:(Escudo,5)(GuanteInfinito,21)(Lawgiver,7)(LazoVerdad,9)(Lucille,23))");
		System.out.println("(square:2:(Antorcha,15)(CadenaFuego,19)(Capa,11)(Flecha,17)(Tridente,13))");
		System.out.println("(square:8:(Baston,28)(CampoMagnetico,4)(Latigo,2)(MazaOro,26)(Tentaculo,24))");
		System.out.println("(square:14:(Bola,10)(CampoEnergia,6)(Cetro,22)(Laser,20)(RayoEnergia,8))");
		System.out.println("(square:15:(Acido,16)(Espada,18)(Gema,14)(Nullifier,23)(Sable,12))");
		System.out.println("(square:21:(Anillo,29)(Armadura,27)(Garra,3)(Mjolnir,1)(Red,5))");
		System.out.println("(square:27:(Escudo,25)(GuanteInfinito,9)(Lawgiver,23)(LazoVerdad,21)(Lucille,7))");
		System.out.println("(square:28:(Baston,15)(CampoMagnetico,24)(Latigo,26)(MazaOro,2)(Tentaculo,4))");
		System.out.println("(square:29:(Bola,18)(CampoEnergia,22)(Cetro,6)(Laser,8)(RayoEnergia,20))");
		System.out.println("(square:33:(Acido,12)(Espada,10)(Gema,1)(Nullifier,3)(Sable,16))");
		System.out.println("(square:35:(Antorcha,28)(CadenaFuego,11)(Capa,19)(Flecha,13)(Tridente,17))");

		m.crearAtajos();

		System.out.println(m);

		System.out.println("Probando esSalaDailyPlanet, debe devolver true:");
		if (m.esSalaDailyPlanet(5, 5))
			System.out.println("True");
		System.out.println("Probando esSalaDailyPlanet, debe devolver false:");
		if (!m.esSalaDailyPlanet(5, 4))
			System.out.println("False");

		System.out.println();

		System.out.println("Probando paredNorte, debe devolver true:");
		if (m.paredNorte(6))
			System.out.println("True");
		System.out.println("Probando paredNorte, debe devolver false:");
		if (!m.paredNorte(35))
			System.out.println("False");

		System.out.println("Probando paredSur, debe devolver true:");
		if (m.paredSur(6))
			System.out.println("True");
		System.out.println("Probando paredSur, debe devolver false:");
		if (!m.paredSur(35))
			System.out.println("False");

		System.out.println("Probando paredEste, debe devolver true:");
		if (m.paredEste(2))
			System.out.println("True");
		System.out.println("Probando paredEste, debe devolver false:");
		if (!m.paredEste(35))
			System.out.println("False");

		System.out.println("Probando paredOeste, debe devolver true:");
		if (m.paredOeste(3))
			System.out.println("True");
		System.out.println("Probando paredOeste, debe devolver false:");
		if (!m.paredOeste(35))
			System.out.println("False");

		System.out.println();

		System.out.println("Probando bordeNorte, debe devolver true:");
		if (m.bordeNorte(3))
			System.out.println("True");
		System.out.println("Probando bordeNorte, debe devolver false:");
		if (!m.bordeNorte(35))
			System.out.println("False");

		System.out.println("Probando bordeSur, debe devolver true:");
		if (m.bordeSur(33))
			System.out.println("True");
		System.out.println("Probando bordeSur, debe devolver false:");
		if (!m.bordeSur(20))
			System.out.println("False");

		System.out.println("Probando bordeEste, debe devolver true:");
		if (m.bordeEste(5))
			System.out.println("True");
		System.out.println("Probando bordeEste, debe devolver false:");
		if (!m.bordeEste(21))
			System.out.println("False");

		System.out.println("Probando bordeOeste, debe devolver true:");
		if (m.bordeOeste(6))
			System.out.println("True");
		System.out.println("Probando bordeOeste, debe devolver false:");
		if (!m.bordeOeste(35))
			System.out.println("False");

		System.out.println();

		System.out.println("Probando insertarPersonaje, debe estar V en la casilla 0:");

		Villano v = new Villano("Villano", 'V', new Arma("Mata-Patos", 0));

		m.insertarPersonaje(v, 0);

		System.out.println(m);

		System.out.println("Probando borrarPersonaje, V ya no deberia aparecer en la casilla 0:");

		m.borrarPersonaje(v, 0);

		System.out.println(m);

	}

	/**
	 * Main de la clase mapa, desde donde se ejecuta el juego
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		Mapa.MostrarCabeceraAscii();
		/**
		 * instancia asociada al fichero de entrada inicio.txt
		 */
		Cargador cargador = new Cargador();
		try {
			/**
			 * Método que procesa línea a línea el fichero de entrada inicio.txt
			 */
			FicheroCarga.procesarFichero(args[0], cargador);
		} catch (FileNotFoundException valor) {
			System.err.println("Excepción capturada al procesar fichero: " + valor.getMessage());
		} catch (IOException valor) {
			System.err.println("Excepción capturada al procesar fichero: " + valor.getMessage());
		}

		Mapa m = Mapa.getInstancia();
		m.simulacionFinal(50);
		Log.close();

		// Descomentar metodo y comentar resto del main para probar la clase
		// Mapa.pruebasMapa();
	}

}