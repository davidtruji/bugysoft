package personajes;

public class Villano extends Personaje {
	private Arma ArmaVillano;

	public Villano(String nom, char ini) {
		super(nom, ini);
		ArmaVillano = null;
	}

	public Arma getArmaVillano() {
		return ArmaVillano;
	}

	public void setArmaVillano(Arma armaVillano) {
		ArmaVillano = armaVillano;
	}

	public static void main(String[] args) {

	}

}
