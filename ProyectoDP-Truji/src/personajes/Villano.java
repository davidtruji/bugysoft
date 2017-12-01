package personajes;

import mapa.Mapa;
import mapa.Sala;

/**
 * 
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: EC2
 * @Curso: 2º
 */
public class Villano extends Personaje {
	private Arma ArmaVillano;

	/**
	 * Constructor del villano que recibe su nombre, inicial y su arma como
	 * parametros
	 * 
	 * @param nom
	 *            nombre del villano
	 * @param ini
	 *            inicial del villano
	 * @param a
	 *            arma del villano
	 */
	public Villano(String nom, char ini, Arma a) {
		super(nom, ini);
		ArmaVillano = a;
	}

	/**
	 * Constructor de super heroe que recibe su nombre y su inicial y su turno de
	 * comienzo
	 * 
	 * @param nom
	 *            es nombre del heroe
	 * @param ini
	 *            es la marca del heroe
	 * @param turno
	 *            es el turno en el comienza
	 */
	public Villano(String nom, char ini, int turno) {
		super(nom, ini, turno);
		Mapa m = Mapa.getInstancia();
		ArmaVillano = null;
		// S S N W S S W S E E N S S
		Dir[] ruta = { Dir.S, Dir.S, Dir.N, Dir.W, Dir.S, Dir.S, Dir.W, Dir.S, Dir.E, Dir.E, Dir.N, Dir.S, Dir.S };
		setRuta(ruta);
		setPosicion(m.getDimX() - 1);
	}

	/**
	 * Get del arma del villano
	 * 
	 * @return arma que posee el villano
	 */
	public Arma getArmaVillano() {
		return ArmaVillano;
	}

	/**
	 * Set del arma del villano
	 * 
	 * @param armaVillano
	 *            arma que poseera el villano
	 */
	public void setArmaVillano(Arma armaVillano) {
		ArmaVillano = armaVillano;
	}

	@Override
	public void recogerArmaPersonaje(Sala s) {

		Arma aux = new Arma("x", 0);
		Arma mejorArmaSala = s.getArmas().mayor();

		if (mejorArmaSala != null)

			if (!s.getArmas().vacio()) {
				aux = getArmaVillano();

				if (aux == null) {
					setArmaVillano(mejorArmaSala);
					s.getArmas().borrar(mejorArmaSala);
				} else if (aux.getPoder() < mejorArmaSala.getPoder()) {

					setArmaVillano(mejorArmaSala);
					s.getArmas().borrar(mejorArmaSala);
					s.getArmas().insertar(aux);
				}

			}

	}

	@Override
	public void interaccionHombrePuerta(HombrePuerta hp) {
		Mapa m = Mapa.getInstancia();

		if (getPosicion() == m.getSalaDailyPlanet()) {

			Arma armaVillano = getArmaVillano();
			Arma mejorArmaHP = hp.getContenedorArmas().mayor();

			if (armaVillano.getPoder() > mejorArmaHP.getPoder()) {
				hp.getContenedorArmas().borrar(mejorArmaHP);
				hp.ActualizarEstadoPortal();

			}
			if (hp.isPortal()) {

				m.getSalaTesereacto().add(this);
				m.getSala(m.getSalaDailyPlanet()).borrarPersonaje(this);
			}

		}

	}

	/**
	 * Metodo toString de un Villano
	 */
	@Override
	public String toString() {
		String s = "";

		if (ArmaVillano != null)
			s = "(villain:" + getInicial() + ":" + getPosicion() + ":" + getTurnoUltimo() + ":" + ArmaVillano + ")";
		else
			s = "(villain:" + getInicial() + ":" + getPosicion() + ":" + getTurnoUltimo() + ":)";

		return s;
	}

	@Override
	public void interaccionEntrePersonajes() {

		Mapa m = Mapa.getInstancia();
		boolean heroe = false;
		Sala s = m.getSala(getPosicion());
		int nPersonajes = s.getPersonajes().size();
		int i = 0;
		Personaje aux = null;

		while (i < nPersonajes && !heroe) {
			aux = s.getPersonajes().get(i);

			if (aux instanceof SuperHeroe && !((SuperHeroe) aux).getContenedorArmas().vacio()) {
				heroe = true;

			}

			i++;
		}

		if (heroe) {

			if (((SuperHeroe) aux).getContenedorArmas().pertenece(ArmaVillano)) {
				Arma ArmaHeroe = ((SuperHeroe) aux).getContenedorArmas().consultar(ArmaVillano);

				if (ArmaHeroe.getPoder() < ArmaVillano.getPoder())
					((SuperHeroe) aux).getContenedorArmas().borrar(ArmaHeroe);

			}

		}

	}

	@Override
	public String mensajeOwneroftheworld() {
		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(owneroftheworld:villain:" + getInicial() + ":1111:" + m.getTurno() + ":" + ArmaVillano + ")";
		return s;

	}

	@Override
	public String mensajeTeseractomember() {

		Mapa m = Mapa.getInstancia();
		String s = "";
		s = "(villain:" + getInicial() + ":1111:" + m.getTurno() + ":" + ArmaVillano + ")";
		return s;

	}

}
