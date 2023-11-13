package blackjack;

public class Carta {

	private final String seme;
	private int valore;
	private String nome;

	public Carta(String seme, int valore) {

		this.seme = seme;
		this.valore = valore;

		if (this.valore >= 2 && this.valore <= 10) {
			nome = Integer.toString(this.valore);
		}
		if (this.valore == 11) {
			nome = "Asso";
		}
		if (this.valore == 12) {
			this.valore = 10;
			nome = "Jack";
		}
		if (this.valore == 13) {
			this.valore = 10;
			nome = "Queen";
		}
		if (this.valore == 14) {
			this.valore = 10;
			nome = "King";
		}

	}

	public String getNome() {
		return nome;
	}

	public int getValore() {
		return valore;
	}

	public String getSeme() {
		return seme;
	}

	public String toString() {

		return nome + " di " + seme;

	}
}