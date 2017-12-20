package personajes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mapa.Mapa;
import util.Dir;

/**
 * 
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: EC3
 * @Curso: 2º
 */
public class SHExtraSensorial extends SuperHeroe {

	/**
	 * Constructor de la clase SHExtraSensorial
	 * 
	 * @param nom
	 *            del heroe
	 * @param ini
	 *            del heroe
	 */
	public SHExtraSensorial(String nom, char ini, int turno) {
		super(nom, ini, turno);
		setPosicion(0);
		Dir[] ruta = crearRuta();
		setRuta(ruta);

	}

	/**
	 * Metodo que genera un camino siguiendo el algoritmo de la mano derecha
	 * 
	 * @return el camino
	 */
	public List<Integer> rutaManoDerecha() {
		Mapa m = Mapa.getInstancia();
		Dir dir = Dir.W;
		int pos = getPosicion();
		List<Integer> camino = new ArrayList<Integer>();
		boolean fin = false;

		while (!fin) {

			switch (dir) {
			case N:

				if (!m.paredEste(pos) && !m.bordeEste(pos)) {
					dir = Dir.E;
				} else if (!m.paredNorte(pos) && !m.bordeNorte(pos)) {
					dir = Dir.N;
				} else if (!m.paredOeste(pos) && !m.bordeOeste(pos)) {
					dir = Dir.W;
				} else {
					dir = Dir.S;
				}

				break;
			case E:

				if (!m.paredSur(pos) && !m.bordeSur(pos)) {
					dir = Dir.S;
				} else if (!m.paredEste(pos) && !m.bordeEste(pos)) {
					dir = Dir.E;
				} else if (!m.paredNorte(pos) && !m.bordeNorte(pos)) {
					dir = Dir.N;
				} else {
					dir = Dir.W;
				}

				break;
			case S:

				if (!m.paredOeste(pos) && !m.bordeOeste(pos)) {
					dir = Dir.W;
				} else if (!m.paredSur(pos) && !m.bordeSur(pos)) {
					dir = Dir.S;
				} else if (!m.paredEste(pos) && !m.bordeEste(pos)) {
					dir = Dir.E;
				} else {
					dir = Dir.N;
				}

				break;

			case W:

				if (!m.paredNorte(pos) && !m.bordeNorte(pos)) {
					dir = Dir.N;
				} else if (!m.paredOeste(pos) && !m.bordeOeste(pos)) {
					dir = Dir.W;
				} else if (!m.paredSur(pos) && !m.bordeSur(pos)) {
					dir = Dir.S;
				} else {
					dir = Dir.E;
				}

				break;

			default:
				System.out.println("Error en la direccion introducida");
				break;
			}

			camino.add(pos);

			if (pos == m.getSalaDailyPlanet())
				fin = true;

			pos = m.getSalaDireccion(pos, dir);

		}

		return camino;

	}

	/**
	 * Metodo que teniendo el camino devuelve la ruta
	 * 
	 * @return ruta del personaje
	 */
	public Dir[] crearRuta() {
		List<Integer> l = rutaManoDerecha();
		Dir[] Ruta = caminoARuta(l);
		return Ruta;
	}

	/**
	 * Metodo toString de un SHExtraSensorial
	 */
	@Override
	public String toString() {
		String s = "";
		s = "(shextrasensorial:" + getInicial() + ":" + getPosicion() + ":" + getTurnoUltimo() + ":"
				+ getContenedorArmas() + mostrarSaco() + ")";

		return s;
	}

	@Override
	public String mensajeOwneroftheworld() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(owneroftheworld:shextrasensorial:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas()
				+ ")";
		return s;
	}

	@Override
	public String mensajeTeseractomember() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(shextrasensorial:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
		return s;
	}

	/**
	 * Metodo que ejecuta las prueas de la clase.
	 * 
	 * @throws IOException
	 */
	private static void pruebasSHExtraSensorial() throws IOException {
		System.out.println("Ejecutando pruebas de la clase SHExtraSensorial...\n");

		Mapa m = Mapa.getInstancia(35, 6, 6, 2);

		SHExtraSensorial sh = new SHExtraSensorial("Super", 'S', 0);
		m.insertarPersonaje(sh, sh.getPosicion());

		System.out.println("Salida esperada de rutaManoDerecha: \n"
				+ "[0, 1, 2, 8, 7, 6, 7, 13, 12, 13, 7, 8, 14, 15, 21, 20, 19, 18, 24, 25, 24, 18, 19, 20, 26, 20, 21, 27, 33, 32, 31, 30, 31, 32, 33, 34, 35] \n");
		System.out.println("Salida del metodo rutaManoDerecha: \n" + sh.rutaManoDerecha());
	}

	/**
	 * Main de la clase SHExtraSensorial
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		pruebasSHExtraSensorial();

	}

}
