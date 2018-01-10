package personajes;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import estructuras_datos.Arbol;
import mapa.Mapa;
import mapa.Sala;

/**
 * 
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: ENERO
 * @Curso: 2º
 */
public class SuperHeroe extends Personaje {

	private List<Villano> sacoDeMalos;
	private Arbol<Arma> ContenedorArmas;

	/**
	 * Constructor de super heroe que recibe su nombre y su inicial
	 * 
	 * @param nom
	 *            nombre del heroe
	 * @param ini
	 *            inicial del heroe
	 */
	public SuperHeroe(String nom, char ini) {
		super(nom, ini);
		ContenedorArmas = new Arbol<Arma>();
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
	public SuperHeroe(String nom, char ini, int turno) {
		super(nom, ini, turno);
		ContenedorArmas = new Arbol<Arma>();
		sacoDeMalos = new LinkedList<Villano>();

	}

	/**
	 * Get del contenedor de armas del heroe
	 * 
	 * @return Arbol de armas
	 */
	public Arbol<Arma> getContenedorArmas() {
		return ContenedorArmas;
	}

	public List<Villano> getSacoDeMalos() {
		return sacoDeMalos;
	}

	/**
	 * Inseta en el saco de malos a un malo cuando lo captura
	 * 
	 * @param malo
	 *            capturado
	 */
	public void insertarMaloEnSaco(Villano malo) {

		sacoDeMalos.add(malo);

	}

	public void setSacoDeMalos(List<Villano> sacoDeMalos) {
		this.sacoDeMalos = sacoDeMalos;
	}

	/**
	 * Inserta un arma en el contenedor del heroe
	 * 
	 * @param a
	 *            arma a insertar
	 */
	public void insertarArmaHeroe(Arma a) {
		ContenedorArmas.insertar(a);
	}

	/**
	 * Devuelve el arma mas poderosa del contenedor
	 * 
	 * @return el arma con mayor poder
	 */
	public Arma mejorArma() {

		return ContenedorArmas.mayor();

	}

	/**
	 * Devuelve el arma menos poderosa del contenedor
	 * 
	 * @return el arma con menor poder
	 */
	public Arma peorArma() {

		return ContenedorArmas.menor();

	}

	/**
	 * Borra el arma del parametro del contenedor
	 * 
	 * @param a
	 *            arma a borrar
	 */
	public void borrarArma(Arma a) {

		ContenedorArmas.borrar(a);

	}

	/**
	 * Set del contenedor de armas del heroe
	 * 
	 * @param contenedorArmas
	 *            arbol de armas
	 */
	public void setContenedorArmas(Arbol<Arma> contenedorArmas) {
		ContenedorArmas = contenedorArmas;
	}

	/**
	 * Retorna un String que contiene el saco de malos
	 * 
	 * @return el string
	 */
	public String mostrarSaco() {
		String s = "";

		for (int i = 0; i < sacoDeMalos.size(); i++) {
			Villano v = sacoDeMalos.get(i);
			s = s + v.getInicial() + " ";
		}

		return s;
	}

	@Override
	public void recogerArmaPersonaje(Sala s) {
		Arma aux = new Arma("x", 0);
		Arma mejorArmaSala = s.getArmas().mayor();

		if (!s.getArmas().vacio()) {

			if (!getContenedorArmas().pertenece(mejorArmaSala)) {

				insertarArmaHeroe(mejorArmaSala);
				s.getArmas().borrar(mejorArmaSala);

			} else {

				aux = getContenedorArmas().consultar(mejorArmaSala);
				aux.setPoder(mejorArmaSala.getPoder() + aux.getPoder());
				s.getArmas().borrar(mejorArmaSala);

			}

		}

	}

	@Override
	public void interaccionHombrePuerta(HombrePuerta hp) {

		Mapa m = Mapa.getInstancia();

		if (m.getSalaDailyPlanet() == getPosicion()) {

			if (!getContenedorArmas().vacio()) {
				Arma mejorArmaHeroe = mejorArma();

				if (hp.getContenedorArmas().pertenece(mejorArmaHeroe)) {
					Arma armaHP = hp.getContenedorArmas().consultar(mejorArmaHeroe);

					if (mejorArmaHeroe.getPoder() > armaHP.getPoder()) {

						hp.getContenedorArmas().borrar(armaHP);
						hp.ActualizarEstadoPortal();

					}
					if (hp.isPortal()) {

						m.getSalaTesereacto().add(this);
						m.getSala(m.getSalaDailyPlanet()).borrarPersonaje(this);
					}

				} else {

					if (hp.isPortal()) {

						m.getSalaTesereacto().add(this);
						m.getSala(m.getSalaDailyPlanet()).borrarPersonaje(this);
					}

				}

				getContenedorArmas().borrar(mejorArmaHeroe);

			}

		}
	}

	@Override
	public void interaccionEntrePersonajes() {

		Mapa m = Mapa.getInstancia();
		boolean villano = false;
		Sala s = m.getSala(getPosicion());
		int nPersonajes = s.getPersonajes().size();
		int i = 0;
		Personaje aux = null;

		while (i < nPersonajes && !villano) {
			aux = s.getPersonajes().get(i);

			if (aux instanceof Villano && ((Villano) aux).getArmaVillano() != null) {
				villano = true;
			}

			i++;
		}

		if (villano) {

			Arma armaVillano = ((Villano) aux).getArmaVillano();

			if (getContenedorArmas().pertenece(armaVillano)) {

				Arma armaHeroe = getContenedorArmas().consultar(armaVillano);

				if (armaHeroe.getPoder() > armaVillano.getPoder()) {
					this.insertarMaloEnSaco((Villano) aux);
					s.borrarPersonaje(aux);
				}

			}

		}

	}

	@Override
	public String mensajeOwneroftheworld() {
		return null;
	}

	@Override
	public String mensajeTeseractomember() {
		return null;
	}

	private static void pruebasSuperHeroe() throws IOException {
		System.out.println("Ejecutando pruebas de la clase SuperHeroe...\n");

		Mapa m = Mapa.getInstancia(35, 6, 6, 5);

		SuperHeroe Alberto = new SuperHeroe("Alberto", 'A', 0);
		SuperHeroe David = new SuperHeroe("Truji", 'T', 0);
		Alberto.setPosicion(m.getSalaDailyPlanet());
		David.setPosicion(m.getSalaDailyPlanet());

		HombrePuerta hp = new HombrePuerta();
		Arbol<Arma> armas = new Arbol<Arma>();

		Arma Mjolnir = new Arma("Mjolnir", 1);
		Arma Antorcha = new Arma("Antorcha", 28);
		Arma Baston = new Arma("Baston", 15);
		Arma Baston2 = new Arma("Baston", 16);
		Villano dp = new Villano("Java", 'D', Baston);
		m.insertarPersonaje(dp, m.getSalaDailyPlanet());

		armas.insertar(Mjolnir);
		armas.insertar(Antorcha);
		armas.insertar(Baston);
		hp.setContenedorArmas(armas);

		Sala s = m.getSala(0);
		s.insertarArma(Mjolnir);
		s.insertarArma(Antorcha);

		m.insertarPersonaje(Alberto, s.getIdSala());
		m.insertarPersonaje(David, s.getIdSala());

		System.out.println("Armas del heroe  Alberto: " + Alberto.getContenedorArmas());
		System.out.println("Armas del heroe  David: " + David.getContenedorArmas());
		System.out.println("");

		System.out.println("RECOGIDA DE ARMAS: ");
		System.out.println("Armas de la sala:" + s);
		System.out.println("Alberto debe recoger: " + s.getArmas().mayor());
		Alberto.recogerArmaPersonaje(s);
		System.out.println("Armas actuales del heroe Alberto: " + Alberto.ContenedorArmas);
		System.out.println("David debe recoger: " + s.getArmas().mayor());
		David.recogerArmaPersonaje(s);
		System.out.println("Armas actuales del heroe David: " + David.ContenedorArmas);
		System.out.println("");

		System.out.println("PELEA HOMBRE PUERTA: ");
		System.out.println("Armas del hombre puerta: " + hp.getContenedorArmas());
		System.out.println("Alberto debe perder el arma: " + Alberto.ContenedorArmas.mayor());
		System.out.println("El hombre puerta debe perder el arma: "
				+ hp.getContenedorArmas().consultar(Alberto.ContenedorArmas.mayor()));
		Alberto.interaccionHombrePuerta(hp);
		System.out.println("Armas actuales del heroe  Alberto: " + Alberto.getContenedorArmas());
		System.out.println("Armas actuales del hombre puerta: " + hp.getContenedorArmas());
		System.out.println("David debe perder el arma: " + David.ContenedorArmas.mayor());
		System.out.println("No debe perder arma");
		System.out.println("Armas actuales del heroe  David: " + David.getContenedorArmas());
		System.out.println("Armas actuales del hombre puerta: " + hp.getContenedorArmas());
		System.out.println("");

		Alberto.insertarArmaHeroe(Mjolnir);
		David.insertarArmaHeroe(Baston2);
		m.insertarPersonaje(Alberto, m.getSalaDailyPlanet());
		m.insertarPersonaje(David, m.getSalaDailyPlanet());
		System.out.println("Armas actuales del heroe  Alberto: " + Alberto.getContenedorArmas());
		System.out.println("Armas actuales del heroe  David: " + David.getContenedorArmas());

		System.out.println("PELEA CONTRA VILLANO: ");
		System.out.println("Armas del villano dp: " + dp.getArmaVillano());

		Alberto.interaccionEntrePersonajes();
		System.out.println("El heroe Alberto no pelea contra el villano");
		System.out.println("El heroe David pelea con su arma"
				+ David.getContenedorArmas().consultar(dp.getArmaVillano()) + " y gana, encerrando al villano. ");
		David.interaccionEntrePersonajes();
		System.out.println("Armas actuales del heroe  David: " + David.getContenedorArmas());
		if (!Alberto.sacoDeMalos.isEmpty())
			System.out.println("Villano capturado por el superheroe Alberto: " + Alberto.getSacoDeMalos());
		if (!David.sacoDeMalos.isEmpty())
			System.out.println("Villano capturado por el superheroe David: " + David.getSacoDeMalos());
	}

	/**
	 * Main de la clase SuperHeroe
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		pruebasSuperHeroe();

	}

}
