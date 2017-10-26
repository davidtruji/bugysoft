
package estructuras_datos;

import personajes.Arma;

/**
 * Implementacion de arbol binario de busqueda.
 * 
 * @version 1.0
 * @author Asignatura Desarrollo de Programas<br/>
 *         <b> Profesores DP </b><br>
 *         Curso 14/15
 */
public class Arbol<T extends Comparable<T>> {

	/** Dato almacenado en cada nodo del árbol. */
	private T datoRaiz;

	/** Atributo que indica si el árbol está vacío. */
	boolean esVacio;

	/** Hijo izquierdo del nodo actual */
	private Arbol<T> hIzq;

	/** Hijo derecho del nodo actual */
	private Arbol<T> hDer;

	/**
	 * Constructor por defecto de la clase. Inicializa un árbol vacío.
	 */
	public Arbol() {
		this.esVacio = true;
		this.hIzq = null;
		this.hDer = null;
	}

	/**
	 * Crea un nuevo árbol a partir de los datos pasados por parámetro.
	 *
	 * @param hIzq
	 *            El hijo izquierdo del árbol que se está creando
	 * @param datoRaiz
	 *            Raíz del árbol que se está creando
	 * @param hDer
	 *            El hijo derecho del árbol que se está creando
	 */
	public Arbol(Arbol<T> hIzq, T datoRaiz, Arbol<T> hDer) {
		this.esVacio = false;
		this.datoRaiz = datoRaiz;
		this.hIzq = hIzq;
		this.hDer = hDer;
	}

	/**
	 * Devuelve el hijo izquierdo del árbol
	 *
	 * @return El hijo izquierdo
	 */
	public Arbol<T> getHijoIzq() {
		return hIzq;
	}

	/**
	 * Devuelve el hijo derecho del árbol
	 *
	 * @return Hijo derecho del árbol
	 */
	public Arbol<T> getHijoDer() {
		return hDer;
	}

	/**
	 * Devuelve la raíz del árbol
	 *
	 * @return La raíz del árbol
	 */
	public T getRaiz() {
		return datoRaiz;
	}

	/**
	 * Comprueba si el árbol está vacío.
	 * 
	 * @return verdadero si el árbol está vacío, falso en caso contrario
	 */
	public boolean vacio() {
		return esVacio;
	}

	/**
	 * Inserta un nuevo dato en el árbol.
	 *
	 * @param dato
	 *            El dato a insertar
	 * @return verdadero si el dato se ha insertado correctamente, falso en caso
	 *         contrario
	 */
	public boolean insertar(T dato) {
		boolean resultado = true;
		if (vacio()) {
			datoRaiz = dato;
			esVacio = false;
		} else {
			if (!(this.datoRaiz.equals(dato))) {
				Arbol<T> aux;
				if (dato.compareTo(this.datoRaiz) < 0) { // dato < datoRaiz
					if ((aux = getHijoIzq()) == null)
						hIzq = aux = new Arbol<T>();
				} else { // dato > datoRaiz
					if ((aux = getHijoDer()) == null)
						hDer = aux = new Arbol<T>();
				}
				resultado = aux.insertar(dato);
			} else
				resultado = false;
		}
		return resultado;
	}

	/**
	 * Comprueba si un dato se encuentra almacenado en el árbol
	 *
	 * @param dato
	 *            El dato a buscar
	 * @return verdadero si el dato se encuentra en el árbol, falso en caso
	 *         contrario
	 */
	public boolean pertenece(T dato) {
		Arbol<T> aux = null;
		boolean encontrado = false;
		if (!vacio()) {
			if (this.datoRaiz.equals(dato))
				encontrado = true;
			else {
				if (dato.compareTo(this.datoRaiz) < 0) // dato < datoRaiz
					aux = getHijoIzq();
				else // dato > datoRaiz
					aux = getHijoDer();
				if (aux != null)
					encontrado = aux.pertenece(dato);
			}
		}
		return encontrado;
	}

	/**
	 * Borrar un dato del árbol.
	 *
	 * @param dato
	 *            El dato que se quiere borrar
	 */
	public void borrar(T dato) {
		if (!vacio()) {
			if (dato.compareTo(this.datoRaiz) < 0) { // dato<datoRaiz
				hIzq = hIzq.borrarOrden(dato);
			} else if (dato.compareTo(this.datoRaiz) > 0) { // dato>datoRaiz
				hDer = hDer.borrarOrden(dato);
			} else // En este caso el dato es datoRaiz
			{
				if (hIzq == null && hDer == null) {
					esVacio = true;
				} else
					borrarOrden(dato);
			}
		}
	}

	/**
	 * Borrar un dato. Este método es utilizado por el método borrar anterior.
	 *
	 * @param dato
	 *            El dato a borrar
	 * @return Devuelve el árbol resultante después de haber realizado el borrado
	 */
	private Arbol<T> borrarOrden(T dato) {
		T datoaux;
		Arbol<T> retorno = this;
		Arbol<T> aborrar, candidato, antecesor;

		if (!vacio()) {
			if (dato.compareTo(this.datoRaiz) < 0) { // dato<datoRaiz
				if (hIzq != null)
					hIzq = hIzq.borrarOrden(dato);
			} else if (dato.compareTo(this.datoRaiz) > 0) { // dato>datoRaiz
				if (hDer != null)
					hDer = hDer.borrarOrden(dato);
			} else {
				aborrar = this;
				if ((hDer == null) && (hIzq == null)) { /* si es hoja */
					retorno = null;
				} else {
					if (hDer == null) { /* Solo hijo izquierdo */
						aborrar = hIzq;
						datoaux = this.datoRaiz;
						datoRaiz = hIzq.getRaiz();
						hIzq.datoRaiz = datoaux;
						hIzq = hIzq.getHijoIzq();
						hDer = aborrar.getHijoDer();

						retorno = this;
					} else if (hIzq == null) { /* Solo hijo derecho */
						aborrar = hDer;
						datoaux = datoRaiz;
						datoRaiz = hDer.getRaiz();
						hDer.datoRaiz = datoaux;
						hDer = hDer.getHijoDer();
						hIzq = aborrar.getHijoIzq();

						retorno = this;
					} else { /* Tiene dos hijos */
						candidato = this.getHijoIzq();
						antecesor = this;
						while (candidato.getHijoDer() != null) {
							antecesor = candidato;
							candidato = candidato.getHijoDer();
						}

						/* Intercambio de datos de candidato */
						datoaux = datoRaiz;
						datoRaiz = candidato.getRaiz();
						candidato.datoRaiz = datoaux;
						aborrar = candidato;
						if (antecesor == this)
							hIzq = candidato.getHijoIzq();
						else
							antecesor.hDer = candidato.getHijoIzq();
					} // Eliminar solo ese nodo, no todo el subarbol
					aborrar.hIzq = null;
					aborrar.hDer = null;
				}
			}
		}
		return retorno;
	}

	/**
	 * Recorrido inOrden del árbol.
	 */
	public void inOrden() {
		Arbol<T> aux = null;
		if (!vacio()) {
			if ((aux = getHijoIzq()) != null) {
				aux.inOrden();
			}

			System.out.println(this.datoRaiz);

			if ((aux = getHijoDer()) != null) {
				aux.inOrden();
			}
		}
	}

	/**
	 * Método que main que realiza las pruebas con el árbol.
	 * 
	 * @param args
	 *            Argumentos del main
	 */
	public static void main(String[] args) {
		Arbol<Arma> arbol = new Arbol<Arma>();
		System.out.println("Ejemplos sesion árbol binario de búsqueda");

		Arma[] datos = { new Arma("Mjolnir", 29), new Arma("Anillo", 1), new Arma("Garra", 27), new Arma("Armadura", 3),
				new Arma("Red", 25), new Arma("Escudo", 5), new Arma("Lucille", 23), new Arma("Lawgiver", 7),
				new Arma("GuanteInfinito", 21), new Arma("LazoVerdad", 9), new Arma("CadenaFuego", 19),
				new Arma("Capa", 11), new Arma("Flecha", 17), new Arma("Tridente", 13), new Arma("Antorcha", 15),
				new Arma("Baston", 28), new Arma("Latigo", 2), new Arma("MazaOro", 26), new Arma("CampoMagnetico", 4),
				new Arma("Tentaculo", 24), new Arma("CampoEnergia", 6), new Arma("Cetro", 22),
				new Arma("RayoEnergia", 8), new Arma("Laser", 20), new Arma("Bola", 10), new Arma("Espada", 18),
				new Arma("Sable", 12), new Arma("Acido", 16), new Arma("Gema", 14), new Arma("Nullifier", 23),
				new Arma("Mjolnir", 1), new Arma("Anillo", 29), new Arma("Garra", 3), new Arma("Armadura", 27),
				new Arma("Red", 5), new Arma("Escudo", 25), new Arma("Lucille", 7), new Arma("Lawgiver", 23),
				new Arma("GuanteInfinito", 9), new Arma("LazoVerdad", 21), new Arma("CadenaFuego", 11),
				new Arma("Capa", 19), new Arma("Flecha", 13), new Arma("Tridente", 17), new Arma("Antorcha", 28),
				new Arma("Baston", 15), new Arma("Latigo", 26), new Arma("MazaOro", 2), new Arma("CampoMagnetico", 24),
				new Arma("Tentaculo", 4), new Arma("CampoEnergia", 22), new Arma("Cetro", 6),
				new Arma("RayoEnergia", 20), new Arma("Laser", 8), new Arma("Bola", 18), new Arma("Espada", 10),
				new Arma("Sable", 16), new Arma("Acido", 12), new Arma("Gema", 1), new Arma("Nullifier", 3) };

		for (int i = 0; i < datos.length; i++) {
			arbol.insertar(datos[i]);
		}

		// Insertando datos repetidos
		if (arbol.insertar(new Arma("Latigo", 26)) == false)
			System.out.println("El ABB no admite elementos duplicados");
		else
			System.out.println("El ABB añade elementos duplicados");

		// Pertenencia de un dato
		if (arbol.pertenece(new Arma("Red", 5)))
			System.out.println("Pertenece");
		else
			System.out.println("Pertenece Incorrecto");

		// Recorrido en inOrden
		System.out.println("InOrden");
		arbol.inOrden();

		// Probando el borrado de diferentes datos -- Descomentar estas líneas para ver
		// qué ocurre
		System.out.println("Intento de borrar RayoEnrgia");
		arbol.borrar(new Arma("RayoEnergia", 20));
		System.out.println("Borrado RayoEnrgia");
		arbol.inOrden();

		System.out.println("Intento de borrar Tridente");
		arbol.borrar(new Arma("Tridente", 13));
		System.out.println("Borrado el Tridente");
		arbol.inOrden();

		// Borrando datos del árbol
		for (int i = 0; i < datos.length; i++) {
			arbol.borrar(datos[i]);
			System.out.println("Borrado " + datos[i]);

		}
		arbol.inOrden();
	}
}
