package personajes;

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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub

		
		
	}

}
