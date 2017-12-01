package personajes;

import java.util.LinkedList;
import java.util.List;

import estructuras_datos.Arbol;
import mapa.Mapa;
import mapa.Sala;

/**
 * 
 * ProyectoDP-Truji
 * 
 * @Fichero: SuperHeroe.java
 * @Autor: David Trujillo Torres
 * @Fecha: 6 nov. 2017
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

	public String mostrarSaco() {
		String s = "";

		for (int i = 0; i < sacoDeMalos.size(); i++) {
			Villano v = sacoDeMalos.get(i);
			s = s + v.getInicial() + " ";
		}

		return s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see personajes.Personaje#recogerArmaPersonaje()
	 */
	@Override
	public void recogerArmaPersonaje(Sala s) {
		Arma aux = new Arma("x", 0);
		// System.out.println(">>Recojiendo Armas de la sala...");
		Arma mejorArmaSala = s.getArmas().mayor();

		// if (mejorArmaSala != null)
		// System.out.println("Mejor arma de la sala:" + mejorArmaSala);
		// else
		// System.out.println("Mejor arma de la sala: ");

		if (!s.getArmas().vacio()) {

			if (!getContenedorArmas().pertenece(mejorArmaSala)) {

				// System.out.println("Super Héroe recoje: " + mejorArmaSala);
				insertarArmaHeroe(mejorArmaSala);
				// System.out.println("Borrando mejor arma de sala: " + mejorArmaSala);
				s.getArmas().borrar(mejorArmaSala);

			} else {

				aux = getContenedorArmas().consultar(mejorArmaSala);
				// System.out.println("Super Héroe combina Arma " + mejorArmaSala + "con" +
				// aux);
				aux.setPoder(mejorArmaSala.getPoder() + aux.getPoder());
				// System.out.println("La Arma resultante sería " + aux);
				// System.out.println("Borrando mejor arma de sala: " + mejorArmaSala);
				s.getArmas().borrar(mejorArmaSala);

			}

		} else {
			// System.out.println("Ningún arma en la sala...");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see personajes.Personaje#interaccionHombrePuerta()
	 */
	@Override
	public void interaccionHombrePuerta(HombrePuerta hp) {
		// System.out.println(">>Luchando con hombre puerta...");
		Mapa m = Mapa.getInstancia();

		if (m.getSalaDailyPlanet() == getPosicion()) {

			if (!getContenedorArmas().vacio()) {
				Arma mejorArmaHeroe = mejorArma();

				// System.out.println("Super héroe usa: " + mejorArmaHeroe);

				// Si el hombre puerta encuentra el arma...
				if (hp.getContenedorArmas().pertenece(mejorArmaHeroe)) {
					Arma armaHP = hp.getContenedorArmas().consultar(mejorArmaHeroe);
					// System.out.println("Hombre puerta usa: " + armaHP);

					if (mejorArmaHeroe.getPoder() > armaHP.getPoder()) {
						// System.out.println("El arma del Heroe gana, hombre puerta pierde su arma: " +
						// armaHP);
						hp.getContenedorArmas().borrar(armaHP);
						hp.ActualizarEstadoPortal();

						if (hp.isPortal()) {
							// Si se habre ahora
							// System.out.println("(teseractomembers)");
							// System.out.println(mensajeOwneroftheworld());
							m.getSalaTesereacto().add(this);
							m.getSala(m.getSalaDailyPlanet()).borrarPersonaje(this);

						}

					}

				}

				// System.out.println("El héroe pierde su arma: " + mejorArmaHeroe);
				getContenedorArmas().borrar(mejorArmaHeroe);

			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see personajes.Personaje#interaccionEntrePersonajes()
	 */
	@Override
	public void interaccionEntrePersonajes() {
		// TODO Auto-generated method stub

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see personajes.Personaje#mensajeOwneroftheworld()
	 */
	@Override
	public String mensajeOwneroftheworld() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see personajes.Personaje#mensajeTeseractomember()
	 */
	@Override
	public String mensajeTeseractomember() {
		return null;
	}

}
