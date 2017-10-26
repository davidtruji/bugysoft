package mapa;

import java.util.LinkedList;
import java.util.Queue;

import estructuras_datos.Arbol;
import personajes.Arma;
import personajes.Personaje;

@SuppressWarnings("unused")
public class Sala {

	private boolean pnorte;
	private boolean psur;
	private boolean peste;
	private boolean poeste;
	private Integer numSala;
	private Queue<Personaje> personajes;
	private Arbol<Arma> armas;

	public Sala(int num, boolean norte, boolean sur, boolean este, boolean oeste) {
		pnorte = norte;
		psur = sur;
		peste = este;
		poeste = oeste;
		numSala = num;
		personajes = new LinkedList<Personaje>();
		armas = new Arbol<Arma>();
	}

	public Arbol<Arma> getArmas() {
		return armas;
	}

	public void setArmas(Arbol<Arma> armas) {
		this.armas = armas;
	}

	public boolean isPnorte() {
		return pnorte;
	}

	public void setPnorte(boolean pnorte) {
		this.pnorte = pnorte;
	}

	public boolean isPsur() {
		return psur;
	}

	public void setPsur(boolean psur) {
		this.psur = psur;
	}

	public boolean isPeste() {
		return peste;
	}

	public void setPeste(boolean peste) {
		this.peste = peste;
	}

	public boolean isPoeste() {
		return poeste;
	}

	public void setPoeste(boolean poeste) {
		this.poeste = poeste;
	}

	public void setNumSala(Integer numSala) {
		this.numSala = numSala;
	}

	public Queue<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(Queue<Personaje> personajes) {
		this.personajes = personajes;
	}

	public int getNumSala() {
		return numSala;
	}

	void setNumSala(int numSala) {
		this.numSala = numSala;
	}

	@Override
	public String toString() {
		return numSala.toString();
	}

}