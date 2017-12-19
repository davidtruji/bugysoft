package personajes;

import java.util.List;
import mapa.Mapa;
import mapa.Sala;
import util.Dir;


/**
 * 
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: EC2
 * @Curso: 2º
 */
public abstract class Personaje {

	private String nombre;
	private char inicial;
	private int turnoComienzo;
	private int turnoUltimo;
	private int posicion;
	private Dir[] ruta;

	/**
	 * Constructor de personaje que recibe como parametros su nombre y su inicial y
	 * turno de comienzo
	 * 
	 * @param nombre
	 *            del personaje
	 * @param inicial
	 *            que es la marca del personaje
	 * @param turno
	 *            en que comienza la partida
	 */
	public Personaje(String nombre, char inicial, int turno) {
		this.nombre = nombre;
		this.inicial = inicial;
		this.turnoComienzo = turno;
		turnoUltimo = -1;

	}

	/**
	 * Constructor de personaje que recibe como parametros su nombre y su inicial
	 * 
	 * @param nom
	 *            nombre del personaje
	 * @param inicial
	 *            inicial del personaje
	 */
	public Personaje(String nom, char inicial) {
		this.nombre = nom;
		this.inicial = inicial;
	}

	/**
	 * Get del nombre del personaje
	 * 
	 * @return String con el nombre del personaje
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Set del nombre del personaje
	 * 
	 * @param nombre
	 *            String con el nombre del personaje
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Get inicial del personaje
	 * 
	 * @return Char de la inicial del personaje
	 */
	public char getInicial() {
		return inicial;
	}

	/**
	 * Set de la inicial del personaje
	 * 
	 * @param inicial
	 *            Char de la inicial del personaje
	 */
	public void setInicial(char inicial) {
		this.inicial = inicial;

	}

	public int getTurnoComienzo() {
		return turnoComienzo;
	}

	public void setTurno(int turno) {
		this.turnoComienzo = turno;
	}

	public Dir[] getRuta() {
		return ruta;
	}

	public void setRuta(Dir[] ruta) {
		this.ruta = ruta;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public int getTurnoUltimo() {
		return turnoUltimo;
	}

	public void setTurnoUltimo(int turnoUltimo) {
		this.turnoUltimo = turnoUltimo;
	}

	/**
	 * Devuelve true si es el turno del personaje y false en caso contrario
	 * 
	 * @return si es su turno
	 */
	public boolean esSuTurno() {
		boolean suTurno = false;
		Mapa m = Mapa.getInstancia();

		if (m.getTurno() > turnoUltimo)
			suTurno = true;

		return suTurno;
	}

	/**
	 * Devuelve un String con la ruta del personaje
	 * 
	 * @return el string con la ruta
	 */
	public String rutaToString() {
		String s = "";

		s = "(path:" + inicial + ":";

		for (int i = 0; i < ruta.length; i++)
			s = s + " " + ruta[i];

		s = s + ")";

		return s;

	}

	/**
	 * Metodo que ejecuta un personaje para recojer arma de la sala pasada por
	 * parametro
	 */
	public abstract void recogerArmaPersonaje(Sala s);

	/**
	 * Metodo que ejecuta cada personaje para interaccionar con el hombre puerta
	 */
	public abstract void interaccionHombrePuerta(HombrePuerta hp);

	/**
	 * Metodo que llama a las acciones de todos los personajes
	 */
	public void realizarAcciones() {

		Mapa m = Mapa.getInstancia();

		// 1. Interaccion con el hombre puerta
		interaccionHombrePuerta(m.getHp());
		// 2. Movimiento
		mover();
		// 3. Recoger Armas
		recogerArmaPersonaje(m.getSala(posicion));
		// 4. Interaccion con otros personajes
		interaccionEntrePersonajes();

	}

	/**
	 * Accion entre villanos y heroes
	 */
	public abstract void interaccionEntrePersonajes();

	/**
	 * Muestra el mensaje del ganador del juego
	 * 
	 * @return el mensaje
	 */
	public abstract String mensajeOwneroftheworld();

	/**
	 * Muestra el mensaje del que atraviesa el portal despues de ganar
	 * 
	 * @return el mensaje
	 */
	public abstract String mensajeTeseractomember();

	/**
	 * Devuelve si es posible realizar un movimiento en la direccion indicada
	 * 
	 * @param dir
	 *            contiene la direccion del movimiento
	 * @return si es posible hacer el movimiento sin chocarse o salirse del mapa
	 */
	private boolean movimientoPosible(Dir dir) {
		Mapa m = Mapa.getInstancia();
		int dimX, dimY;
		dimX = m.getDimX();
		dimY = m.getDimY();
		boolean posible = false;

		switch (dir) {
		case N:
			if (!m.paredNorte(posicion)) {
				if (posicion > dimX - 1) {
					posible = true;
				}

			}

			break;
		case E:

			if (!m.paredEste(posicion)) {
				if ((posicion + 1) % dimX != 0) {
					posible = true;
				}

			}

			break;
		case S:
			if (!m.paredSur(posicion)) {
				if (posicion < (dimX * dimY) - dimX) {
					posible = true;
				}

			}

			break;
		case W:

			if (!m.paredOeste(posicion)) {
				if (posicion % dimX != 0) {
					posible = true;
				}

			}

			break;

		default:
			System.out.println("Error en la direccion introducida");
			break;
		}

		return posible;
	}

	/**
	 * Accion de movimiento del personaje en su ruta determinada
	 * 
	 * @return true si se ha movido y false en caso contrario
	 */
	public Boolean mover() {
		Mapa m = Mapa.getInstancia();
		Boolean movido = false;
		int dimX;
		dimX = m.getDimX();
		int dest;

		if (ruta.length != 0 && posicion != m.getSalaDailyPlanet()) {

			Dir dir = ruta[0];

			switch (dir) {
			case N:
				if (movimientoPosible(dir)) {
					movido = true;
					// borrar de sala origen
					m.borrarPersonaje(this, posicion);

					dest = posicion - dimX;

					// insertar en sala destino
					m.insertarPersonaje(this, dest);
					posicion = dest;
				}

				break;
			case E:

				if (movimientoPosible(dir)) {
					movido = true;
					// borrar de sala origen
					m.borrarPersonaje(this, posicion);

					dest = posicion + 1;

					// insertar en sala destino
					m.insertarPersonaje(this, dest);
					posicion = dest;

				}

				break;
			case S:
				if (movimientoPosible(dir)) {
					movido = true;

					// borrar de sala origen
					m.borrarPersonaje(this, posicion);

					dest = posicion + dimX;

					// insertar en sala destino
					m.insertarPersonaje(this, dest);
					posicion = dest;

				}
				break;
			case W:

				if (movimientoPosible(dir)) {
					movido = true;

					// borrar de sala origen
					m.borrarPersonaje(this, posicion);

					dest = posicion - 1;

					// insertar en sala destino
					m.insertarPersonaje(this, dest);
					posicion = dest;

				}

				break;

			default:
				System.out.println("Error en la direccion introducida");
				break;
			}

			for (int n = 0; n < ruta.length - 1; n++)
				ruta[n] = ruta[n + 1];
		}
		return movido;
	}

	public Dir[] caminoARuta(List<Integer> camino) {

		int ori = 0;
		Dir aux;
		Dir[] Ruta = new Dir[camino.size() - 1];
		for (int dest = 1; dest < camino.size(); dest++) {

			aux = direccion(camino.get(ori), camino.get(dest));
			Ruta[ori] = aux;
			ori = dest;
		}

		return Ruta;
	}

	public Dir direccion(int id_ori, int id_dest) {
		Dir ret = null;
		Mapa m = Mapa.getInstancia();
		int dimX = m.getDimX();

		// Si la direccion es norte
		if (id_dest == id_ori - dimX)
			ret = Dir.N;

		// Si la direccion es este
		if (id_dest == id_ori + 1)
			ret = Dir.E;

		// Si la direccion es sur
		if (id_dest == id_ori + dimX)
			ret = Dir.S;

		// Si la direccion es oeste
		if (id_dest == id_ori - 1)
			ret = Dir.W;

		return ret;

	}

	/**
	 * Equals de la clase personaje
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personaje other = (Personaje) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}
