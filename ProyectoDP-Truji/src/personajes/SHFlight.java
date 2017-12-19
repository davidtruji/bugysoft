package personajes;

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
public class SHFlight extends SuperHeroe {

	/**
	 * Constructor de la clase SHFlight
	 * 
	 * @param nom
	 *            del heroe
	 * @param ini
	 *            del heroe
	 */
	public SHFlight(String nom, char ini, int turno) {
		super(nom, ini, turno);
		Mapa m = Mapa.getInstancia();
		setPosicion(m.getDimX() * m.getDimY() - m.getDimX());
		Dir[] ruta = crearRuta();
		setRuta(ruta);

	}

	/**
	 * Metodo que encuentra el camino mas corto desde posicion hasta la sala DP
	 * 
	 * @param caminoCorto
	 *            el mejor camino hasta ahora, el mejor al final de la ejecucion
	 * @param camino
	 *            los caminos que va generando el algoritmo
	 * @param posicion
	 *            inicial y de cada sala que se va pasando
	 */
	public void rutaFlightBacktracking(List<Integer> caminoCorto, List<Integer> camino, int posicion) {

		Mapa m = Mapa.getInstancia();
		Set<Integer> salasAdyacentes = new LinkedHashSet<Integer>();

		camino.add(posicion);
		if (posicion == m.getSalaDailyPlanet()) {

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
					rutaFlightBacktracking(caminoCorto, camino, i);
					camino.remove(camino.size() - 1);
				}

			}

		}

	}

	/**
	 * Metodo que teniendo el camino devuelve la ruta
	 * 
	 * @return ruta del personaje
	 */
	public Dir[] crearRuta() {
		List<Integer> caminoCorto = new ArrayList<Integer>();
		List<Integer> camino = new ArrayList<Integer>();
		rutaFlightBacktracking(caminoCorto, camino, getPosicion());
		Dir[] Ruta = caminoARuta(caminoCorto);
		return Ruta;
	}

	/**
	 * Metodo toString de SHFlight
	 */
	@Override
	public String toString() {
		String s = "";
		s = "(shflight:" + getInicial() + ":" + getPosicion() + ":" + getTurnoUltimo() + ":" + getContenedorArmas()
				+ mostrarSaco() + ")";

		return s;
	}

	@Override
	public String mensajeOwneroftheworld() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(owneroftheworld:shflight:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
		return s;
	}

	@Override
	public String mensajeTeseractomember() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(shflight:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
		return s;
	}

}
