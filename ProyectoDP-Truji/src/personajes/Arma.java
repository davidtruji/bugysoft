
package personajes;

/**
 * 
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: EC3
 * @Curso: 2º
 */
public class Arma implements Comparable<Arma> {

	private String nombre;
	private Integer poder;

	/**
	 * Constructor del arma
	 * 
	 * @param nombre
	 *            Entra un nombre.
	 * @param poder
	 *            Entra un poder.
	 */
	public Arma(String nombre, Integer poder) {
		this.nombre = nombre;
		this.poder = poder;
	}

	/**
	 * Get del nombre del arma
	 * 
	 * @return String con el nombre del arma
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Set del nombre del arma
	 * 
	 * @param nombre
	 *            String que sera el nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Get del poder del arma
	 * 
	 * @return Un entero que representa el poder del arma
	 */
	public Integer getPoder() {
		return poder;
	}

	/**
	 * Set del poder del arma
	 * 
	 * @param poder
	 *            Entero que sera el poder del arma
	 */
	public void setPoder(Integer poder) {
		this.poder = poder;
	}

	/**
	 * To String de la clase arma
	 */
	@Override
	public String toString() {
		String s = "";
		if (this != null)
			s = "(" + nombre + "," + poder + ")";
		return s;
	}

	/**
	 * Equals de la clase arma
	 */
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

	/**
	 * ConpareTo de la clase arma
	 */
	@Override
	public int compareTo(Arma a) {
		int ret = nombre.compareToIgnoreCase(a.nombre);
		return ret;
	}

	/**
	 * Metodo que ejecuta las prueas de la clase.
	 */
	private static void pruebasArma() {
		System.out.println("Ejecutando pruebas de la clase Arma...\n");

		Arma Mjolnir = new Arma("Mjolnir", 29);
		Arma Mjolnir2 = new Arma("Mjolnir", 1);
		Arma Antorcha = new Arma("Antorcha", 28);
		Arma Baston = new Arma("Baston", 15);
		System.out.println("Pruebas del compareTo: ");
		System.out.println("comparando : " + Mjolnir + " con " + Mjolnir2);
		if (Mjolnir.compareTo(Mjolnir2) == 0)
			System.out.println("Exito");
		else
			System.out.println("Fracaso");
		System.out.println("comparando : " + Baston + " con " + Antorcha);
		if (Baston.compareTo(Antorcha) == 0)
			System.out.println("Exito");
		else
			System.out.println("Fracaso");

		System.out.println("");

		System.out.println("Prueba del equals:");
		System.out.println("comparando : " + Mjolnir + " con " + Mjolnir2);
		if (Mjolnir.equals(Mjolnir2))
			System.out.println("Exito");
		else
			System.out.println("Fracaso");
		System.out.println("comparando : " + Antorcha + " con " + Baston);
		if (Baston.equals(Antorcha))
			System.out.println("Exito");
		else
			System.out.println("Fracaso");
	}

	/**
	 * Main de la clase arma
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		pruebasArma();

	}

}
