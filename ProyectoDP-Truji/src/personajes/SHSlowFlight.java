package personajes;

import java.util.ArrayList;
import java.util.List;
import mapa.Mapa;
import util.Dir;

/**
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres
 * @Entrega: DEFENSA
 * @Curso: 2º
 */
public class SHSlowFlight extends SHFlight {

	/**
	 * @param nom
	 * @param ini
	 * @param turno
	 */
	public SHSlowFlight(String nom, char ini, int turno) {
		super(nom, ini, turno);
		Mapa m = Mapa.getInstancia();
		setPosicion(m.getDimX() * m.getDimY() - m.getDimX());
		Dir[] ruta = crearRuta();
		setRuta(ruta);

	}

	/**
	 * Metodo que encuentra el camino mas corto desde posicion hasta la sala Mas
	 * lejana
	 * 
	 * @param caminoCorto
	 *            el mejor camino hasta ahora, el mejor al final de la ejecucion
	 * @param camino
	 *            los caminos que va generando el algoritmo
	 * @param posicion
	 *            inicial y de cada sala que se va pasando
	 */
	public int SalaMasLejana(List<Integer> caminoLargo) {
		Mapa m = Mapa.getInstancia();
		List<Integer> camino = new ArrayList<Integer>();
		List<Integer> caminoCorto = new ArrayList<Integer>();
		int id_lejano = 0;

		for (int id = 0; id <= m.getSalaDailyPlanet(); id++) {

			if (id != getPosicion()) {
				caminoCorto.clear();
				camino.clear();
				m.caminoMasCorto(caminoCorto, camino, getPosicion(), id);

				if (caminoLargo.size() < caminoCorto.size()
						|| (caminoLargo.size() == caminoCorto.size() && id < getPosicion())) {

					id_lejano = id;

					caminoLargo.clear();
					for (int i = 0; i < caminoCorto.size(); i++)
						caminoLargo.add(caminoCorto.get(i));

				}

			}
		}

		return id_lejano;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see personajes.SHFlight#crearRuta()
	 */
	@Override
	public Dir[] crearRuta() {
		Mapa m = Mapa.getInstancia();
		List<Integer> camino1 = new ArrayList<Integer>();
		List<Integer> camino2 = new ArrayList<Integer>();
		List<Integer> caminoAux = new ArrayList<Integer>();

		int id_lejano = SalaMasLejana(camino1);
		m.caminoMasCorto(camino2, caminoAux, id_lejano, m.getSalaDailyPlanet());

		camino2.remove(0);

		caminoAux.clear();

		for (int i = 0; i < camino1.size(); i++)
			caminoAux.add(camino1.get(i));

		for (int i = 0; i < camino2.size(); i++)
			caminoAux.add(camino2.get(i));

		Dir[] Ruta = caminoARuta(caminoAux);
		return Ruta;

	}

	/**
	 * Metodo toString de SHFlight
	 */
	@Override
	public String toString() {
		String s = "";
		s = "(shslowflight:" + getInicial() + ":" + getPosicion() + ":" + getTurnoUltimo() + ":" + getContenedorArmas()
				+ mostrarSaco() + ")";

		return s;
	}

	@Override
	public String mensajeOwneroftheworld() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(owneroftheworld:shslowflight:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas()
				+ ")";
		return s;
	}

	@Override
	public String mensajeTeseractomember() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(shslowflight:" + getInicial() + ":1111:" + m.getTurno() + ":" + getContenedorArmas() + ")";
		return s;
	}

}
