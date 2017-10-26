package personajes;

public class Arma implements Comparable<Arma> {

	private String nombre;
	private Integer poder;

	public Arma(String nombre, Integer poder) {
		this.nombre = nombre;
		this.poder = poder;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPoder() {
		return poder;
	}

	public void setPoder(Integer poder) {
		this.poder = poder;
	}

	@Override
	public String toString() {
		String s = "";
		s = "{" + nombre + ", " + poder + "}";
		return s;
	}

	@Override
	public int compareTo(Arma a) {
		return nombre.compareToIgnoreCase(a.getNombre());
	}

	public static void main(String[] args) {
		System.out.println("Probando Armas.................");
		Arma a1 = new Arma("X", 999);
		Arma a2 = new Arma("x", 999);

		if (a1.compareTo(a2) < 0)
			System.out.println(a1 + " es menor que " + a2);
		else if (a1.compareTo(a2) > 0)
			System.out.println(a1 + " es mayor que " + a2);
		else
			System.out.println(a1 + " es igual que " + a2);

	}

}
