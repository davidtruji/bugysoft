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
	public boolean equals(Object a) {
		if (!(a instanceof Arma))
			return false;
		Arma aAux = (Arma) a;
		if (nombre.equalsIgnoreCase(aAux.getNombre()))
			return true;
		else
			return false;
	}

	/*
	 * THIS ES -1 MENOR / 0 IGUAL / 1 MAYOR QUE EL PARAMETRO
	 */
	@Override
	public int compareTo(Arma a) {
		int ret=999;

		if (nombre.compareToIgnoreCase(a.getNombre()) != 0) {
			if (poder > a.getPoder())
				ret = 1;
			else {

				if (poder < a.getPoder())
					ret = -1;
				else {

					if (poder == a.getPoder())
						ret = nombre.compareToIgnoreCase(a.getNombre());

				}

			}

		} else {
			ret = 0;
		}
	

		return ret;

	}

	public static void main(String[] args) {
		System.out.println("Probando Armas.................");
		Arma a1 = new Arma("a", 10);
		Arma a2 = new Arma("b", 999);

		if (a1.compareTo(a2) < 0)
			System.out.println(a1 + " es menor que " + a2);
		else if (a1.compareTo(a2) > 0)
			System.out.println(a1 + " es mayor que " + a2);
		else
			System.out.println(a1 + " es igual que " + a2);

	}

}
