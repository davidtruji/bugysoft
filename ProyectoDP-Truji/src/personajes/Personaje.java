package personajes;

public class Personaje {

	String nombre;
	char inicial;

	public Personaje(String nom, char inicial) {
		super();
		this.nombre = nom;
		this.inicial = inicial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getInicial() {
		return inicial;
	}

	public void setInicial(char inicial) {
		this.inicial = inicial;
	}

	@Override
	public String toString() {
		String s = "";
		s = s + "{" + nombre + "} ";
		return s;
	}

}
