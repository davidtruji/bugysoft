package personajes;

import estructuras_datos.Arbol;

/**
 * 
 * ProyectoDP-Truji
 * 
 * @Fichero: HombrePuerta.java
 * @Autor: David Trujillo Torres
 * @Fecha: 6 nov. 2017
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

	public HombrePuerta(int profundidad) {
		this.profundidad = profundidad;
		Portal = false;
		ContenedorArmas = new Arbol<Arma>();
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
	public boolean condicionApetura(int altura) {
		boolean portal = false;
		if (altura > ContenedorArmas.alturaArbol()) {
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
	public void ActualizarEstadoPortal(int altura) {

		if (condicionApetura(altura))
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
	 * Main de la clase Hombre puerta, se usa para pruebas internas de la clase
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Arma[] armasPuerta = { new Arma("CampoEnergia", 5), new Arma("Armadura", 13), new Arma("Anillo", 11),
				new Arma("Acido", 1), new Arma("Antorcha", 5), new Arma("Bola", 3), new Arma("Baston", 22),
				new Arma("CadenaFuego", 11), new Arma("Espada", 11), new Arma("Cetro", 20), new Arma("Capa", 10),
				new Arma("CampoMagnetico", 5), new Arma("Escudo", 3), new Arma("Garra", 22), new Arma("Flecha", 12),
				new Arma("Gema", 4) };
		// Creación del hombre puerta y configuración
		HombrePuerta doorMan = new HombrePuerta();
		// Configurar el hombre puerta introduciendo la combinación de armas
		doorMan.configurar(armasPuerta);
		doorMan.getContenedorArmas().inOrden();
	}

}
