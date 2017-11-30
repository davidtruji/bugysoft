package personajes;

import estructuras_datos.Arbol;
import mapa.Sala;

/**
 * 
 * ProyectoDP-Truji
 * 
 * @Fichero: Villano.java
 * @Autor: David Trujillo Torres
 * @Fecha: 6 nov. 2017
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
		ArmaVillano = null;
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
			System.out.println("Arma de " + this.toString() + " :" + getArmaVillano());
			aux = getArmaVillano();

			if (aux.getPoder() < mejorArmaSala.getPoder()) {
				System.out.println("Villano recoje: " + mejorArmaSala);

				setArmaVillano(mejorArmaSala);
				System.out.println("Villano deja: " + aux);
				s.getArmas().borrar(mejorArmaSala);
				s.getArmas().insertar(aux);
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
		System.out.println(">>Luchando con hombre puerta...");

		Arma armaVillano = getArmaVillano();
		Arma mejorArmaHP = hp.getContenedorArmas().mayor();
		System.out.println("Hombre puerta usa: " + mejorArmaHP);
		System.out.println("Villano usa: " + armaVillano);

		if (armaVillano.getPoder() > mejorArmaHP.getPoder()) {
			System.out.println("Villano gana, el hombre puerta pierde: " + mejorArmaHP);
			hp.getContenedorArmas().borrar(mejorArmaHP);
		} else {
			System.out.println("Hombre puerta gana, no se hará nada");

		}

	}

}
