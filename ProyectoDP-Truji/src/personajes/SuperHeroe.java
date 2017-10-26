package personajes;

import estructuras_datos.Arbol;

public class SuperHeroe extends Personaje {

	private Arbol<Arma> ContenedorArmas;

	public SuperHeroe(String nom) {
		super(nom);
		ContenedorArmas = new Arbol<Arma>();
	}

	public Arbol<Arma> getContenedorArmas() {
		return ContenedorArmas;
	}

	public void setContenedorArmas(Arbol<Arma> contenedorArmas) {
		ContenedorArmas = contenedorArmas;
	}

	public static void main(String[] args) {
	}

}
