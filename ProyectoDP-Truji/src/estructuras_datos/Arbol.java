
package estructuras_datos;

import personajes.Arma;

/**
 * 
 * @Grupo: Bugysoft
 * @Autor: David Trujillo Torres y Alberto Diaz Martin
 * @Entrega: ENERO
 * @Curso: 2º
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
	 * Devuleve un dato contenido en el arbol
	 * 
	 * @param dato
	 *            el dato que se desea retornar
	 * @return el dato retornado
	 */
	public T consultar(T dato) {
		Arbol<T> aux = null;
		T auxD = null;
		if (!vacio()) {
			if (dato.equals(this.getRaiz())) {
				auxD = this.getRaiz();
			} else {

				if ((aux = getHijoIzq()) != null && auxD == null)
					auxD = aux.consultar(dato);

				if ((aux = getHijoDer()) != null && auxD == null)
					auxD = aux.consultar(dato);

			}

		}
		return auxD;
	}

	/**
	 * Borrar un dato del árbol.
	 *
	 * @param dato
	 *            El dato que se quiere borrar
	 */
	public void borrar(T dato) {
		if (!vacio() && pertenece(dato)) {
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
	 * Metodo que devuelve el mayor Arma del arbol llamando al metodo recursivo
	 * 
	 * @return el mayor Arma del arbol
	 */
	public Arma mayor() {

		return mayorR((Arma) datoRaiz);

	}

	/**
	 * Metodo que devuelve el menor Arma del arbol llamando al metodo recursivo
	 * 
	 * @return el mayor Arma del arbol
	 */
	public Arma menor() {

		return menorR((Arma) datoRaiz);

	}

	/**
	 * Metodo recursivo que devulve el menor elemento del arbol
	 * 
	 * @param mayor
	 *            el menor arma hasta ahora
	 * @return el menor arma de todo el arbol
	 */
	private Arma menorR(Arma menor) {
		Arbol<T> aux = null;
		Arma raiz = (Arma) datoRaiz;
		Arma ret = menor;
		if (!vacio()) {
			if (raiz.getPoder() < ret.getPoder()) {
				ret = raiz;
			} else if (raiz.getPoder() == ret.getPoder() && raiz.getNombre().compareToIgnoreCase(ret.getNombre()) > 0) {
				ret = raiz;

			}

			if ((aux = getHijoIzq()) != null)
				ret = aux.menorR(ret);

			if ((aux = getHijoDer()) != null)
				ret = aux.menorR(ret);

		}
		return ret;
	}

	/**
	 * Metodo recursivo que devulve el mayor elemento del arbol
	 * 
	 * @param mayor
	 *            el mayor arma hasta ahora
	 * @return el mayor arma de todo el arbol
	 */
	public Arma mayorR(Arma mayor) {
		Arbol<T> aux = null;
		Arma raiz = (Arma) datoRaiz;
		Arma ret = mayor;
		if (!vacio()) {
			if (raiz.getPoder() > ret.getPoder()) {
				ret = raiz;
			} else if (raiz.getPoder() == ret.getPoder() && raiz.getNombre().compareToIgnoreCase(ret.getNombre()) < 0) {
				ret = raiz;

			}

			if ((aux = getHijoIzq()) != null)
				ret = aux.mayorR(ret);

			if ((aux = getHijoDer()) != null)
				ret = aux.mayorR(ret);

		}
		return ret;
	}

	/**
	 * Metodo que devulve la altura del arbol
	 * 
	 * @return la altura del arbol
	 */
	public int alturaArbol() {
		int alturaIzq = 0;
		int alturaDer = 0;
		int altura = 0;
		if (hIzq != null) {
			alturaIzq = hIzq.alturaArbol();
		}
		if (hDer != null) {
			alturaDer = hDer.alturaArbol();
		}
		if (alturaIzq > alturaDer) {
			altura = alturaIzq + 1;
		} else {
			altura = alturaDer + 1;
		}
		return altura;
	}

	/**
	 * To String inOrden
	 */
	@Override
	public String toString() {
		String s = "";
		Arbol<T> aux = null;
		if (!vacio()) {
			if ((aux = getHijoIzq()) != null) {
				s = s + aux.toString();
			}

			s = s + datoRaiz.toString();

			if ((aux = getHijoDer()) != null) {
				s = s + aux.toString();
			}
		}
		return s;
	}

	/**
	 * Metodo que ejecuta las pruebas de la clase
	 */
	private static void pruebasArbol() {
		System.out.println("Ejecutando pruebas de la clase Arbol...\n");

		Arbol<Integer> arbol = new Arbol<Integer>();

		Arbol<Arma> arbolA = new Arbol<Arma>();

		Integer[] datos = { new Integer(20), new Integer(7), new Integer(18), new Integer(6), new Integer(5),
				new Integer(1), new Integer(22) };

		Arma[] armas = { new Arma("arma1", 21), new Arma("arma21", 0), new Arma("arma41", 213), new Arma("arma153", 6),
				new Arma("arma132", 213) };

		for (int i = 0; i < datos.length; i++) {
			arbol.insertar(datos[i]);
		}

		for (int i = 0; i < armas.length; i++) {
			arbolA.insertar(armas[i]);
		}

		// Probando Menor
		System.out.println("Menor debe dar el Arma21 poder 0 : ");
		System.out.println(arbolA.menor());

		// Insertando datos repetidos
		if (arbol.insertar(new Integer(22)) == false)
			System.out.println("El ABB no admite elementos duplicados");

		// Pertenencia de un dato
		if (arbol.pertenece(new Integer(22)))
			System.out.println("Pertenece");
		else
			System.out.println("NO Pertenece");

		// Recorrido en inOrden
		System.out.println("InOrden");
		arbol.inOrden();

		// Probando el borrado de diferentes datos -- Descomentar estas líneas para ver
		// qué ocurre
		arbol.borrar(new Integer(20));
		System.out.println("Borrado " + 20);
		arbol.borrar(new Integer(15));
		System.out.println("Borrado " + 15);

		// Borrando datos del árbol
		for (int i = 0; i < datos.length; i++) {
			arbol.borrar(datos[i]);
			System.out.println("Borrado " + datos[i]);
			arbol.inOrden();
		}

	}

	public static void main(String args[]) {
		Arbol.pruebasArbol();
	}

}
