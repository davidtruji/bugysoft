package cargador;

import java.io.IOException;
import java.util.List;

import mapa.Mapa;
import personajes.SHExtraSensorial;
import personajes.SHFlight;
import personajes.SHPhysical;
import personajes.SHSlowFlight;
import personajes.Villano;

/**
 * Clase creada para ser usada en la utilidad cargador contiene el main del
 * cargador. Se crea una instancia de la clase Estacion, una instancia de la
 * clase Cargador y se procesa el fichero de inicio, es decir, se leen todas las
 * líneas y se van creando todas las instancias de la simulación
 * 
 * @version 6.0 - 17/11/2017
 * @author Profesores DP
 */
public class Cargador {
	/**
	 * número de elementos distintos que tendrá la simulación: Mapa, SHPhysical,
	 * SHExtraSensorial, SHFlight, Villain
	 */
	static final int NUMELTOSCONF = 6;
	/**
	 * atributo para almacenar el mapeo de los distintos elementos
	 */
	static private DatoMapeo[] mapeo;

	/**
	 * constructor parametrizado
	 * 
	 * @param e
	 *            referencia a la instancia del patrón Singleton
	 */
	public Cargador() {
		mapeo = new DatoMapeo[NUMELTOSCONF];
		mapeo[0] = new DatoMapeo("MAP", 5);
		mapeo[1] = new DatoMapeo("SHPHYSICAL", 4);
		mapeo[2] = new DatoMapeo("SHEXTRASENSORIAL", 4);
		mapeo[3] = new DatoMapeo("SHFLIGHT", 4);
		mapeo[4] = new DatoMapeo("VILLAIN", 4);
		mapeo[5] = new DatoMapeo("SHSLOWFLIGHT", 4);

	}

	/**
	 * busca en mapeo el elemento leído del fichero inicio.txt y devuelve la
	 * posición en la que está
	 * 
	 * @param elto
	 *            elemento a buscar en el array
	 * @return res posición en mapeo de dicho elemento
	 */
	private int queElemento(String elto) {
		int res = -1;
		boolean enc = false;

		for (int i = 0; (i < NUMELTOSCONF && !enc); i++) {
			if (mapeo[i].getNombre().equals(elto)) {
				res = i;
				enc = true;
			}
		}
		return res;
	}

	/**
	 * método que crea las distintas instancias de la simulación
	 * 
	 * @param elto
	 *            nombre de la instancia que se pretende crear
	 * @param numCampos
	 *            número de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo de la instancia
	 * @throws IOException
	 */
	public void crear(String elto, int numCampos, List<String> vCampos) throws IOException {
		// Si existe elemento y el número de campos es correcto, procesarlo... si no,
		// error
		int numElto = queElemento(elto);

		// Comprobación de datos básicos correctos
		if ((numElto != -1) && (mapeo[numElto].getCampos() == numCampos)) {
			// procesar
			switch (numElto) {
			case 0:
				crearMap(numCampos, vCampos);
				break;
			case 1:
				crearSHPhysical(numCampos, vCampos);
				break;
			case 2:
				crearSHExtraSensorial(numCampos, vCampos);
				break;
			case 3:
				crearSHFlight(numCampos, vCampos);
				break;
			case 4:
				crearVillain(numCampos, vCampos);
				break;
			case 5:
				crearSHSlowFlight(numCampos, vCampos);
				break;
			}
		} else
			System.out.println(
					"ERROR Cargador::crear: Datos de configuración incorrectos... " + elto + "," + numCampos + "\n");
	}

	/**
	 * método que crea una instancia de la clase Planta
	 * 
	 * @param numCampos
	 *            número de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 * @throws IOException
	 */
	private void crearMap(int numCampos, List<String> vCampos) throws IOException {
		// System.out.println("Creado Map: " + vCampos.get(1) + "\n");

		int dimX, dimY, salaDailyPlanet, altura;

		dimX = Integer.valueOf(vCampos.get(1));
		dimY = Integer.valueOf(vCampos.get(2));
		salaDailyPlanet = Integer.valueOf(vCampos.get(3));
		altura = Integer.valueOf(vCampos.get(4));

		// inicializar mapa
		if (dimY > 0 && dimX > 0 && altura > 0)
			Mapa.getInstancia(salaDailyPlanet, dimY, dimX, altura);
		else
			System.err.println("Existen errores en los parametros de creacion del mapa");
	}

	/**
	 * método que crea una instancia de la clase SHPhysical
	 * 
	 * @param numCampos
	 *            número de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearSHPhysical(int numCampos, List<String> vCampos) {
		// System.out.println("Creado SHPhysical: " + vCampos.get(1) + "\n");

		// Registrar SHPhysical en el mapa
		String nom = vCampos.get(1);
		char marca = vCampos.get(2).charAt(0);
		int turno = Integer.valueOf(vCampos.get(3));

		if (turno >= 0) {
			SHPhysical sh = new SHPhysical(nom, marca, turno);

			Mapa.getInstancia().insertarPersonaje(sh, 0);
		} else {
			System.err.println("El turno de comienzo del personaje no puede ser negativo");
		}
	}

	/**
	 * método que crea una instancia de la clase SHExtraSensorial
	 * 
	 * @param numCampos
	 *            número de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearSHExtraSensorial(int numCampos, List<String> vCampos) {
		// System.out.println("Creado SHExtraSensorial: " + vCampos.get(1) + "\n");
		// Registrar SHExtraSensorial en el mapa

		String nom = vCampos.get(1);
		char marca = vCampos.get(2).charAt(0);
		int turno = Integer.valueOf(vCampos.get(3));

		if (turno >= 0) {
			SHExtraSensorial sh = new SHExtraSensorial(nom, marca, turno);

			Mapa.getInstancia().insertarPersonaje(sh, 0);
		} else {
			System.err.println("El turno de comienzo del personaje no puede ser negativo");
		}
	}

	/**
	 * método que crea una instancia de la clase SHFlight
	 * 
	 * @param numCampos
	 *            número de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearSHFlight(int numCampos, List<String> vCampos) {
		// System.out.println("Creado SHFlight: " + vCampos.get(1) + "\n");
		// Registrar SHFlight en el mapa

		String nom = vCampos.get(1);
		char marca = vCampos.get(2).charAt(0);
		int turno = Integer.valueOf(vCampos.get(3));

		Mapa m = Mapa.getInstancia();
		int dimX = m.getDimX();
		int dimY = m.getDimY();

		if (turno >= 0) {
			SHFlight sh = new SHFlight(nom, marca, turno);

			Mapa.getInstancia().insertarPersonaje(sh, dimX * dimY - dimX);
		} else {
			System.err.println("El turno de comienzo del personaje no puede ser negativo");
		}

	}

	/**
	 * método que crea una instancia de la clase Villain
	 * 
	 * @param numCampos
	 *            número de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearVillain(int numCampos, List<String> vCampos) {
		// System.out.println("Creado Villain: " + vCampos.get(1) + "\n");
		// Registrar Villain en el mapa

		String nom = vCampos.get(1);
		char marca = vCampos.get(2).charAt(0);
		int turno = Integer.valueOf(vCampos.get(3));

		Mapa m = Mapa.getInstancia();
		int dimX = m.getDimX();

		if (turno >= 0) {
			Villano v = new Villano(nom, marca, turno);

			Mapa.getInstancia().insertarPersonaje(v, dimX - 1);
		} else {
			System.err.println("El turno de comienzo del personaje no puede ser negativo");
		}

	}

	/**
	 * método que crea una instancia de la clase SHSlowFlight
	 * 
	 * @param numCampos
	 *            número de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearSHSlowFlight(int numCampos, List<String> vCampos) {

		String nom = vCampos.get(1);
		char marca = vCampos.get(2).charAt(0);
		int turno = Integer.valueOf(vCampos.get(3));

		Mapa m = Mapa.getInstancia();
		int dimX = m.getDimX();
		int dimY = m.getDimY();

		if (turno >= 0) {
			SHSlowFlight sh = new SHSlowFlight(nom, marca, turno);

			Mapa.getInstancia().insertarPersonaje(sh, dimX * dimY - dimX);
		} else {
			System.err.println("El turno de comienzo del personaje no puede ser negativo");
		}

	}

}
