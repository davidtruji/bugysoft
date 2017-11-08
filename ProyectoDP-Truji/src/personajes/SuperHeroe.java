package personajes;

import estructuras_datos.Arbol;
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
	 * Get del contenedor de armas del heroe
	 * 
	 * @return Arbol de armas
	 */
	public Arbol<Arma> getContenedorArmas() {
		return ContenedorArmas;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see personajes.Personaje#recogerArmaPersonaje()
	 */
	@Override
	public void recogerArmaPersonaje(Sala s) {
		Arma aux = new Arma("x", 0);
		System.out.println(">>Recojiendo Armas de la sala...");
		Arma mejorArmaSala = s.getArmas().mayor();

		if (mejorArmaSala != null)
			System.out.println("Mejor arma de la sala:" + mejorArmaSala);
		else
			System.out.println("Mejor arma de la sala: ");

		if (!s.getArmas().vacio()) {

			if (!getContenedorArmas().pertenece(mejorArmaSala)) {

				System.out.println("Super Héroe recoje: " + mejorArmaSala);
				insertarArmaHeroe(mejorArmaSala);
				System.out.println("Borrando mejor arma de sala: " + mejorArmaSala);
				s.getArmas().borrar(mejorArmaSala);

			} else {

				aux = getContenedorArmas().consultar(mejorArmaSala);
				System.out.println("Super Héroe combina Arma " + mejorArmaSala + "con" + aux);
				aux.setPoder(mejorArmaSala.getPoder() + aux.getPoder());
				System.out.println("La Arma resultante sería " + aux);
				System.out.println("Borrando mejor arma de sala: " + mejorArmaSala);
				s.getArmas().borrar(mejorArmaSala);

			}

		} else {
			System.out.println("Ningún arma en la sala...");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see personajes.Personaje#interaccionHombrePuerta()
	 */
	@Override
	public void interaccionHombrePuerta(HombrePuerta hp) {

	}

}
