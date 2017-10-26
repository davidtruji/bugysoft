package mapa;

import java.util.LinkedList;
import java.util.Queue;

import personajes.Personaje;

@SuppressWarnings("unused")
public class Sala {

	
	private boolean pnorte;
	private boolean psur;
	private boolean peste;
	private boolean poeste;
	private Integer numSala;
	private Queue<Personaje> personajes;
	// private Arbol<Arma> armas;

	public Queue<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(Queue<Personaje> personajes) {
		this.personajes = personajes;
	}

	public Sala(int num, boolean norte, boolean sur, boolean este, boolean oeste) {
		pnorte = norte;
		psur = sur;
		peste = este;
		poeste = oeste;
		numSala = num;
		personajes = new LinkedList<Personaje>();
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