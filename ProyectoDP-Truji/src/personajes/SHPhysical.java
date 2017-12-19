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
 * @Entrega: EC2
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

}
