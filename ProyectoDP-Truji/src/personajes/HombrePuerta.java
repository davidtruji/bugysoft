package personajes;

import estructuras_datos.Arbol;

public class HombrePuerta {

	boolean Portal;// TRUE ABIERTO / FALSE CERRADO
	Arbol<Arma> ContenedorArmas;

	public HombrePuerta() {
		Portal = false;
		ContenedorArmas = new Arbol<Arma>();
	}

	public boolean isPortal() {
		return Portal;
	}

	public void setPortal(boolean portal) {
		Portal = portal;
	}

	public static void main(String[] args) {

	}

}
