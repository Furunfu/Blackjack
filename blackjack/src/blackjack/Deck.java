package blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private ArrayList<Carta> deck;
	private final int nrMazzi;

	public Deck() {

		nrMazzi = 2;
		deck = new ArrayList<Carta>();

		for (int k = 1; k <= 4; k++) {
			for (int j = 1; j <= this.nrMazzi; j++) {
				for (int i = 2; i <= 14; i++) {
					if (k == 1) {
						deck.add(new Carta("Cuori", i));
					}
					if (k == 2) {
						deck.add(new Carta("Quadri", i));
					}
					if (k == 3) {
						deck.add(new Carta("Picche", i));
					}
					if (k == 4) {
						deck.add(new Carta("Fiori", i));
					}
				}
			}
		}
	}

	private void swapCarte(int carta1, int carta2) {
		Carta temp, temp2;

		temp = deck.get(carta1);
		temp2 = deck.get(carta2);
		deck.set(carta2, temp);
		deck.set(carta1, temp2);
	}

	public void mischia() {
		Random rn = new Random();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < deck.size(); j++) {
				swapCarte(i, rn.nextInt(52 * nrMazzi));
			}
		}
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Carta carta : deck) {
			str.append(carta.toString()).append("   ");
		}
		return str.toString();
	}

	public void decrementaDeck() {
		deck.remove(deck.size() - 1);
	}

	public Carta getUltimaCarta() {

		return deck.get(deck.size() - 1);
	}
	
	public void svuotaDeck() {
		
		deck.clear();
	}

	public void rifaiDeck() {

		for (int k = 1; k <= 4; k++) {
			for (int j = 1; j <= this.nrMazzi; j++) {
				for (int i = 2; i <= 14; i++) {
					if (k == 1) {
						deck.add(new Carta("Cuori", i));
					}
					if (k == 2) {
						deck.add(new Carta("Quadri", i));
					}
					if (k == 3) {
						deck.add(new Carta("Picche", i));
					}
					if (k == 4) {
						deck.add(new Carta("Fiori", i));
					}
				}
			}
		}
	}
}