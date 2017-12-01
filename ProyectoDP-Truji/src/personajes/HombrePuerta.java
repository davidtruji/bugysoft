package personajes;

import estructuras_datos.Arbol;

/**
 * 
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: EC2
 * @Curso: 2º
 */
public class HombrePuerta {

	private boolean Portal;// TRUE ABIERTO / FALSE CERRADO
	private Arbol<Arma> ContenedorArmas;
	private int profundidad;

	/**
	 * Constructor por defecto del hombre puerta
	 */
	public HombrePuerta() {
		Portal = false;
		ContenedorArmas = new Arbol<Arma>();
	}

	/**
	 * Constructor que inicializa la altura de la cerradura
	 * 
	 * @param profundidad
	 *            de la cerradura
	 */
	public HombrePuerta(int profundidad) {
		this.profundidad = profundidad;
		Portal = false;
		ContenedorArmas = new Arbol<Arma>();
		Arma[] armasPuerta = { new Arma("CampoEnergia", 5), new Arma("Armadura", 13), new Arma("Anillo", 11),
				new Arma("Acido", 1), new Arma("Antorcha", 5), new Arma("Bola", 3), new Arma("Baston", 22),
				new Arma("CadenaFuego", 11), new Arma("Espada", 11), new Arma("Cetro", 20), new Arma("Capa", 10),
				new Arma("CampoMagnetico", 5), new Arma("Escudo", 3), new Arma("Garra", 22), new Arma("Flecha", 12),
				new Arma("Gema", 4) };
		configurar(armasPuerta);
	}

	/**
	 * Get del contenedor de armas del hombre puerta
	 * 
	 * @return Arbol de armas
	 */
	public Arbol<Arma> getContenedorArmas() {
		return ContenedorArmas;
	}

	/**
	 * Set del contenedor de armas del hombre puerta
	 * 
	 * @param contenedorArmas
	 *            Arbol de armas
	 */
	public void setContenedorArmas(Arbol<Arma> contenedorArmas) {
		ContenedorArmas = contenedorArmas;
	}

	/**
	 * Metodo que devulve el estado del portal
	 * 
	 * @return True (Abierto) , False (Cerrado)
	 */
	public boolean isPortal() {
		return Portal;
	}

	/**
	 * Metodo que comprueba si se cumple la condicion de apertura
	 * 
	 * @param altura
	 *            altura del arbol para abrir el portal
	 * @return True si se cumple la condicion false en caso contrario
	 */
	public boolean condicionApetura() {
		boolean portal = false;
		if (profundidad > ContenedorArmas.alturaArbol()) {
			portal = true;
		}

		return portal;

	}

	/**
	 * Actualiza el estado del portal comprobando si se cumple la condicion de
	 * apertura
	 * 
	 * @param altura
	 *            altura del portal para abrirse
	 */
	public void ActualizarEstadoPortal() {

		if (condicionApetura())
			Portal = true;
		else
			Portal = false;

	}

	/**
	 * Set del portal del hombre puerta
	 * 
	 * @param portal
	 *            estado del portal
	 */
	public void setPortal(boolean portal) {
		Portal = portal;
	}

	/**
	 * Metodo que cierra el portal del hombre puerta
	 */
	public void cerrar() {

		Portal = false;
	}

	/**
	 * Metodo que configura un array de armas en el hombre puerta
	 * 
	 * @param armas
	 *            array de armas
	 */
	public void configurar(Arma[] armas) {
		for (int i = 0; i < armas.length; i++) {
			ContenedorArmas.insertar(armas[i]);
		}
	}

	/**
	 * Metodo toString de el Hombre puerta
	 */
	@Override
	public String toString() {
		String s = "";

		if (isPortal())
			s = "(doorman:open:" + profundidad + ":)";
		else
			s = "(doorman:closed:" + profundidad + ":" + ContenedorArmas + ")";

		return s;
	}

}
