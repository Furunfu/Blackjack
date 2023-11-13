package blackjack;

import java.util.ArrayList;

public class ManoDealer implements Mano {

	private ArrayList<Carta> mano;

	public ManoDealer() {

		mano = new ArrayList<Carta>();
	}

	public void aggiungiCarta(Carta carta) {

		mano.add(carta);

	}

	public boolean checkBj() {

		int somma;
		boolean bj = false;

		if (mano.get(0).getNome().equals("Asso") == true) {
			somma = mano.get(0).getValore() + mano.get(1).getValore();
			if (somma == 21) {
				bj = true;
			}
		}
		return bj;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Carta carta : mano) {
			str.append(carta.toString()).append("\n");
		}
		return str.toString();
	}

	public Carta getPrimaCartaArList() {

		return mano.get(0);
	}

	public Carta getSecondaCartaArList() {

		return mano.get(1);
	}

	public int totale() {

		int totale = 0, conta = 0;

		for (Carta carta : mano) {

			if (carta.getNome().equals("Asso") == true) {
				conta++;
				totale += carta.getValore();
			} else {

				totale += carta.getValore();
			}
			if (conta > 0 && totale > 21) {
				conta--;
				totale -= 10;
			}
		}
		return totale;
	}
	
	public void ripristina() {
		
		mano.clear();
	}

}
