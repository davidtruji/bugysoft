package personajes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import mapa.Mapa;
import util.Dir;

/**
 * 
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: EC3
 * @Curso: 2º
 */
public class SHPhysical extends SuperHeroe {

	/**
	 * Constructor de la clase SHPhysical
	 * 
	 * @param nom
	 *            del heroe
	 * @param ini
	 *            del heroe
	 */
	public SHPhysical(String nom, char ini, int turno) {
		super(nom, ini, turno);
		setPosicion(0);
		Dir[] ruta = crearRuta();
		setRuta(ruta);

	}

	/**
	 * Metodo que busca un solo camino desde la sala posicion hasta la sala DP
	 * 
	 * @param camino
	 *            el camino que va generando el algoritmo
	 * @param posicion
	 *            inicial y de cada sala que se va pasando
	 * @return Verdadero al encontrar un camino
	 */
	public boolean rutaPhysicalBacktracking(List<Integer> camino, int posicion) {

		Mapa m = Mapa.getInstancia();
		Set<Integer> salasAdyacentes = new LinkedHashSet<Integer>();
		boolean caminoEncontrado = false;

		camino.add(posicion);
		if (posicion == m.getSalaDailyPlanet()) {
			caminoEncontrado = true;
		} else {

			m.getGrafo().adyacentes(posicion, salasAdyacentes);
			for (Integer i : salasAdyacentes) {

				if (!camino.contains(i) && !caminoEncontrado) {
					caminoEncontrado = rutaPhysicalBacktracking(camino, i);
					if (!caminoEncontrado)
						camino.remove(camino.size() - 1);
				}

			}

		}

		return caminoEncontrado;

	}

	/**
	 * Metodo que teniendo el camino devuelve la ruta
	 * 
	 * @return ruta del personaje
	 */
	public Dir[] crearRuta() {
		List<Integer> l = new ArrayList<Integer>();
		rutaPhysicalBacktracking(l, 0);
		Dir[] Ruta = caminoARuta(l);
		return Ruta;
	}

	/**
	 * Metodo toString de SHPhysical
	 */
	@Override
	public String toString() {
		String s = "";
		s = "(shphysical:" + getInicial() + ":" + getPosicion() + ":" + getTurnoUltimo() + ":" + getContenedorArmas()
				+ mostrarSaco() + ")";

		return s;
	}

	@Override
	public String mensajeOwneroftheworld() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(owneroftheworld:shphysical:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
		return s;
	}

	@Override
	public String mensajeTeseractomember() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(shphysical:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
		return s;
	}

	/**
	 * Metodo que ejecuta las prueas de la clase.
	 * 
	 * @throws IOException
	 */
	private static void pruebasSHPhysical() throws IOException {
		System.out.println("Ejecutando pruebas de la clase SHPhysical...\n");

		Mapa m = Mapa.getInstancia(35, 6, 6, 2);

		SHPhysical sh = new SHPhysical("Super", 'S', 0);
		m.insertarPersonaje(sh, sh.getPosicion());
		List<Integer> camino = new ArrayList<Integer>();

		sh.rutaPhysicalBacktracking(camino, sh.getPosicion());
		System.out.println("Salida esperada rutaPhysicalBacktracking:\n[0, 1, 2, 8, 14, 15, 21, 27, 28, 29, 35]\n");

		System.out.println("Salida del metodo rutaPhysicalBacktracking: \n" + camino);

	}

	/**
	 * Main de la clase SHPhysical
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		pruebasSHPhysical();

	}

}
