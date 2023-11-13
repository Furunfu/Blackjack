package blackjack;

import java.util.ArrayList;

public class Giocatore {

	private final String nome;
	private double budget;
	private ArrayList<ManoGiocatore> manoG;

	public Giocatore(String nome, double budget) {

		this.nome = nome;
		this.budget = budget;
		manoG = new ArrayList<ManoGiocatore>();
	}

	public double getBudget() {

		return budget;
	}

	public String getNome() {

		return nome;
	}

	public void scommessa(double scommessa) {

		budget -= scommessa;

	}

	public void assicurazione(double scommessa) {

		budget -= scommessa / 2;
	}

	public void guadagnoAssicurazioneParita(double scommessa) {

		budget += scommessa;
	}

	public void guadagno(double scommessa) {

		budget += scommessa * 2;
	}

	public void guadagnoBj(double scommessa) {

		guadagno(scommessa);
		budget += scommessa / 2;
	}

	public void addMano(ManoGiocatore manoGiocatore) {

		manoG.add(manoGiocatore);
	}
	
	public void ripristinaMano() {

		manoG.clear();
	}

	public ManoGiocatore getManoUno() {

		return manoG.get(0);
	}

	public ManoGiocatore getManoDue() {

		return manoG.get(1);
	}

	public String toString() {

		return nome + " ecco il tuo budget: " + budget;
	}
	
	public void split() {
		
		Carta carta;
		
		carta = manoG.get(0).getSecondaCartaArList();
		manoG.get(0).rimuoviSecondaCarta();
		manoG.get(1).aggiungiCarta(carta);
	}
	public int contaMani() {
	
		return manoG.size();
	}
}
