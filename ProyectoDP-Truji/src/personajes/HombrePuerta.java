package personajes;

import estructuras_datos.Arbol;

public class HombrePuerta {

	boolean Portal;// TRUE ABIERTO / FALSE CERRADO
	Arbol<Arma> ContenedorArmas;

	public HombrePuerta() {
		Portal = false;
		ContenedorArmas = new Arbol<Arma>();
	}

	public Arbol<Arma> getContenedorArmas() {
		return ContenedorArmas;
	}

	public void setContenedorArmas(Arbol<Arma> contenedorArmas) {
		ContenedorArmas = contenedorArmas;
	}

	public boolean isPortal() {
		return Portal;
	}

	public void setPortal(boolean portal) {
		Portal = portal;
	}

	public void cerrar() {

		Portal = false;
	}

	public void configurar(Arma[] armas) {
		for (int i = 0; i < armas.length; i++) {
			ContenedorArmas.insertar(armas[i]);
		}
	}

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
