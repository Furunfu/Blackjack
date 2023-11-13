package blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Starter {

	public static void main(String[] args) throws IOException {

		String s1, s2;
		boolean split = false;
		int stai2 = 0, conv, conta = 0, scelta, scelta2, turno = 0, assicurazione = 0;
		double bet = 0.0, budget;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ManoDealer manoD = new ManoDealer();
		ManoGiocatore manoG = new ManoGiocatore();
		ManoGiocatore manoG2 = new ManoGiocatore();
		Deck deck = new Deck();
		deck.mischia();
		System.out.println("Inserisci il tuo nome: ");
		s1 = in.readLine();

		do {

			System.out.println("\nInserisci il tuo budget: ");
			s2 = in.readLine();
			budget = Double.parseDouble(s2);

			if (budget <= 0.0) {

				System.out.println("\nBudget troppo basso");

			}

		} while (budget <= 0.0);

		Giocatore giocatore = new Giocatore(s1, budget);

		do {

			do {

				System.out.println("\n" + giocatore.toString() + "\n" + giocatore.getNome()
						+ " digita 1 se vuoi uscire, in caso contrario digita un altro numero\n");
				s1 = in.readLine();
				conv = Integer.parseInt(s1);

				if (conv != 1) {

					manoD.ripristina();

					if (giocatore.contaMani() == 2) {

						manoG2.ripristina();
					}

					manoG.ripristina();
					giocatore.ripristinaMano();

					split = false;
					stai2 = 0;
					bet = 0.0;
					scelta = 0;
					scelta2 = 0;
					turno = 0;
					assicurazione = 0;

					if (conta == 3) {
						deck.svuotaDeck();
						deck.rifaiDeck();
						deck.mischia();
						conta = 0;
					}
					conta++;

					do {

						System.out.println("\n" + giocatore.toString() + "\n" + giocatore.getNome()
								+ " Inserisci la tua scommessa: ");
						s1 = in.readLine();
						bet = Double.parseDouble(s1);

						if (bet > giocatore.getBudget() || bet <= 0.0) {

							System.out.println("\nScommessa non possibile");

						}
					} while (bet > giocatore.getBudget() || bet <= 0.0);

					giocatore.scommessa(bet);
					giocatore.addMano(manoG);

					System.out.println("\nDistribuzione carte\n");
					giocatore.getManoUno().aggiungiCarta(deck.getUltimaCarta());
					deck.decrementaDeck();
					manoD.aggiungiCarta(deck.getUltimaCarta());
					deck.decrementaDeck();
					giocatore.getManoUno().aggiungiCarta(deck.getUltimaCarta());
					deck.decrementaDeck();
					manoD.aggiungiCarta(deck.getUltimaCarta());
					deck.decrementaDeck();

					if (manoD.getPrimaCartaArList().getValore() != 11 && giocatore.getManoUno().checkBj() == true) {

						System.out
								.println("\nHai vinto!\nHai fatto blackjack\nHai vinto " + (bet * 2 + bet / 2) + " €");
						giocatore.guadagnoBj(bet);

					}

					if (manoD.getPrimaCartaArList().getValore() == 11) {

						if ((bet / 2) <= giocatore.getBudget()) {

							System.out.println(
									"\nATTENTO\nIl dealer ha un asso\nDigita 1 se vuoi fare l'assicurazione, in caso contrario digita un altro numero\nLe tue carte sono: "
											+ giocatore.getManoUno().toString());
							s1 = in.readLine();
							assicurazione = Integer.parseInt(s1);

						}

						if (assicurazione == 1) {
							giocatore.assicurazione(bet);
						}

						if (manoD.checkBj() == true && giocatore.getManoUno().checkBj() == true) {

							System.out.println(
									"\nPareggio!\nAvete fatto entrambi blackjack\nIl dealer ha: " + manoD.toString());
							giocatore.guadagnoAssicurazioneParita(bet);

							if (assicurazione == 1) {
								giocatore.guadagnoAssicurazioneParita(bet);
								System.out.println("\nHai vinto " + bet + " grazie all'assicurazione");
							}
						}

						if (manoD.checkBj() == true && giocatore.getManoUno().checkBj() == false) {

							System.out.println("\nHai perso!\nBlackjack del dealer\nIl dealer ha: " + manoD.toString());

							if (assicurazione == 1) {
								giocatore.guadagnoAssicurazioneParita(bet);
								System.out.println("\nHai vinto " + bet + " grazie all'assicurazione");
							}
						}

					}
				}
			} while (manoD.checkBj() == true && giocatore.getManoUno().checkBj() == true
					|| manoD.checkBj() == false && giocatore.getManoUno().checkBj() == true
					|| manoD.checkBj() == true && giocatore.getManoUno().checkBj() == false);

			if (conv != 1) {

				do {

					do {
						System.out.println("\nLe tue carte sono: " + giocatore.getManoUno().toString()
								+ "Per un totale di: " + giocatore.getManoUno().totale()
								+ "\n\nLe due carte del dealer sono: " + manoD.getPrimaCartaArList()
								+ " e la seconda una carta coperta\n\nScegli cosa fare:\n1)Stai\n2)Chiedi carta");

						if (turno == 0 && bet <= giocatore.getBudget()) {

							System.out.println("3)Raddoppia");

						}

						if (giocatore.getManoUno().getPrimaCartaArList().getNome()
								.equals(giocatore.getManoUno().getSecondaCartaArList().getNome()) == true && turno == 0
								&& bet <= giocatore.getBudget()) {

							System.out.println("4)Dividi");
						}
						s1 = in.readLine();
						scelta = Integer.parseInt(s1);

						if (scelta < 1 || scelta > 4 || scelta == 3 && turno != 0
								|| scelta == 3 && turno == 0 && bet > giocatore.getBudget() || scelta == 4 && turno != 0
								|| scelta == 4 && turno == 0 && bet > giocatore.getBudget()
								|| scelta == 4 && turno == 0
										&& giocatore.getManoUno().getPrimaCartaArList().getNome().equals(
												giocatore.getManoUno().getSecondaCartaArList().getNome()) == false
								|| scelta == 4 && turno == 0
										&& giocatore.getManoUno().getPrimaCartaArList().getNome().equals(
												giocatore.getManoUno().getSecondaCartaArList().getNome()) == true
										&& bet > giocatore.getBudget()) {

							System.out.println("\nScelta non disponibile\n");
						}

					} while (scelta < 1 || scelta > 4 || scelta == 3 && turno != 0
							|| scelta == 3 && turno == 0 && bet > giocatore.getBudget() || scelta == 4 && turno != 0
							|| scelta == 4 && turno == 0 && bet > giocatore.getBudget()
							|| scelta == 4 && turno == 0
									&& giocatore.getManoUno().getPrimaCartaArList().getNome()
											.equals(giocatore.getManoUno().getSecondaCartaArList().getNome()) == false
							|| scelta == 4 && turno == 0
									&& giocatore.getManoUno().getPrimaCartaArList().getNome()
											.equals(giocatore.getManoUno().getSecondaCartaArList().getNome()) == true
									&& bet > giocatore.getBudget());

					if (scelta != 1) {

						if (scelta == 2) {

							giocatore.getManoUno().aggiungiCarta(deck.getUltimaCarta());
							deck.decrementaDeck();

						}
						if (scelta == 3) {
							giocatore.scommessa(bet);
							giocatore.getManoUno().aggiungiCarta(deck.getUltimaCarta());
							deck.decrementaDeck();

						}
						if (scelta == 4) {

							split = true;
							giocatore.scommessa(bet);
							giocatore.addMano(manoG2);
							giocatore.split();

						}

						turno++;

					}
				} while (scelta == 2 && giocatore.getManoUno().totale() < 21);

				if (split == true) {

					do {

						do {

							if (stai2 == 0) {

								System.out.println("\nLa tua prima mano ha le seguenti carte: "
										+ giocatore.getManoUno().toString() + "Per un totale di: "
										+ giocatore.getManoUno().totale() + "\n\nLe due carte del dealer sono: "
										+ manoD.getPrimaCartaArList()
										+ " e la seconda una carta coperta\n\nScegli cosa fare:\n1)Stai\n2)Chiedi carta\n");

							}
							if (stai2 == 1) {

								System.out.println("\nLa tua seconda mano ha le seguenti carte: "
										+ giocatore.getManoDue().toString() + "Per un totale di: "
										+ giocatore.getManoDue().totale() + "\n\nLe due carte del dealer sono: "
										+ manoD.getPrimaCartaArList()
										+ " e la seconda una carta coperta\n\nScegli cosa fare:\n1)Stai\n2)Chiedi carta\n");

							}
							s1 = in.readLine();
							scelta2 = Integer.parseInt(s1);

							if (scelta2 < 1 || scelta2 > 2) {

								System.out.println("\nScelta non disponibile\n");

							}

						} while (scelta2 < 1 || scelta2 > 2);

						if (scelta2 == 1 && stai2 == 1) {
							stai2++;
						}

						if (scelta2 == 1 && stai2 == 0) {
							stai2++;
						}

						if (scelta2 == 2 && stai2 == 1) {
							giocatore.getManoDue().aggiungiCarta(deck.getUltimaCarta());
							deck.decrementaDeck();
							if (giocatore.getManoDue().totale() > 21) {
								System.out.println("\nHai superato 21,  hai perso questa mano\n");
								stai2++;
							}
							if (giocatore.getManoDue().totale() == 21) {
								System.out.println("\nBravo hai fatto 21 in questa mano!\n");
								stai2++;
							}
						}

						if (scelta2 == 2 && stai2 == 0) {
							giocatore.getManoUno().aggiungiCarta(deck.getUltimaCarta());
							deck.decrementaDeck();
							if (giocatore.getManoUno().totale() > 21) {
								System.out.println("\nHai superato 21,  hai perso questa mano\n");
								stai2++;
							}
							if (giocatore.getManoUno().totale() == 21) {
								System.out.println("\nHai fatto 21, ora passa alla seconda mano\n");
								stai2++;
							}
						}

					} while (scelta2 == 1 && stai2 != 2 || scelta2 != 1 && giocatore.getManoDue().totale() < 21);
				}

				if (giocatore.contaMani() == 2 && giocatore.getManoUno().totale() <= 21
						|| giocatore.contaMani() == 2 && giocatore.getManoDue().totale() <= 21
						|| giocatore.contaMani() == 1 && giocatore.getManoUno().totale() <= 21) {
					while (manoD.totale() < 17) {
						manoD.aggiungiCarta(deck.getUltimaCarta());
						deck.decrementaDeck();
					}
				}

				if (giocatore.contaMani() == 2) {

					System.out.println("\nIl dealer ha:\n" + manoD.toString() + "Per un totale di: " + manoD.totale()
							+ "\n\nNella tua prima mano hai:\n" + giocatore.getManoUno().toString()
							+ "Per un totale di: " + giocatore.getManoUno().totale()
							+ "\n\nMentre nella tua seconda mano hai:\n" + giocatore.getManoDue().toString()
							+ "Per un totale di: " + giocatore.getManoDue().totale());
				} else {

					System.out.println("\nIl dealer ha:\n" + manoD.toString() + "Per un totale di: " + manoD.totale()
							+ "\n\nMentre tu hai:\n" + giocatore.getManoUno().toString() + "Per un totale di: "
							+ giocatore.getManoUno().totale());
				}

				if (scelta == 4) {

					if (manoD.totale() > 21) {

						if (giocatore.getManoUno().totale() <= 21 && giocatore.getManoDue().totale() <= 21) {
							System.out.println("\nHai vinto entrambi le mani, hai vinto: " + bet * 4 + " €");
							giocatore.guadagno(bet);
							giocatore.guadagno(bet);

						}
						if (giocatore.getManoUno().totale() <= 21 && giocatore.getManoDue().totale() > 21
								|| giocatore.getManoUno().totale() > 21 && giocatore.getManoDue().totale() <= 21) {
							System.out.println("\nHai vinto una sola mano, hai vinto: " + bet * 2 + " €");
							giocatore.guadagno(bet);

						}
						if (giocatore.getManoUno().totale() > 21 && giocatore.getManoDue().totale() > 21) {
							System.out.println("\nHai perso entrambi le mani");
						}
					}

					if (manoD.totale() <= 21) {

						if (giocatore.getManoUno().totale() < manoD.totale()
								&& giocatore.getManoDue().totale() < manoD.totale()
								|| giocatore.getManoUno().totale() > 21 && giocatore.getManoDue().totale() > 21) {

							System.out.println("\nHai perso entrambi le mani");

						}

						if (giocatore.getManoUno().totale() == manoD.totale()
								&& giocatore.getManoDue().totale() == manoD.totale()) {

							System.out.println("\nHai pareggiato entrambi le mani, hai vinto: " + bet * 2 + " €");
							giocatore.guadagno(bet);

						}

						if (giocatore.getManoUno().totale() > manoD.totale()
								&& giocatore.getManoDue().totale() > manoD.totale()
								&& giocatore.getManoUno().totale() <= 21 && giocatore.getManoDue().totale() <= 21) {

							System.out.println("\nHai vinto entrambi le mani, hai vinto: " + bet * 4 + " €");
							giocatore.guadagno(bet);
							giocatore.guadagno(bet);

						}

						if (giocatore.getManoUno().totale() <= 21 && giocatore.getManoDue().totale() <= 21
								&& giocatore.getManoUno().totale() > manoD.totale()
								&& giocatore.getManoDue().totale() < manoD.totale()
								|| giocatore.getManoUno().totale() <= 21 && giocatore.getManoDue().totale() <= 21
										&& giocatore.getManoUno().totale() < manoD.totale()
										&& giocatore.getManoDue().totale() > manoD.totale()
								|| giocatore.getManoUno().totale() > 21 && giocatore.getManoDue().totale() <= 21
										&& giocatore.getManoDue().totale() > manoD.totale()
								|| giocatore.getManoUno().totale() <= 21 && giocatore.getManoDue().totale() > 21
										&& giocatore.getManoUno().totale() > manoD.totale()) {
							System.out.println("\nHai vinto una sola mano, hai vinto: " + bet * 2 + " €");
							giocatore.guadagno(bet);

						}

						if (giocatore.getManoUno().totale() <= 21 && giocatore.getManoDue().totale() <= 21
								&& giocatore.getManoUno().totale() > manoD.totale()
								&& giocatore.getManoDue().totale() == manoD.totale()
								|| giocatore.getManoUno().totale() <= 21 && giocatore.getManoDue().totale() <= 21
										&& giocatore.getManoUno().totale() == manoD.totale()
										&& giocatore.getManoDue().totale() > manoD.totale()) {
							System.out.println(
									"\nHai vinto una mano ed hai pareggiato l altra, hai vinto: " + bet * 3 + " €");
							giocatore.guadagno(bet);
							giocatore.guadagnoAssicurazioneParita(bet);

						}

						if (giocatore.getManoUno().totale() <= 21 && giocatore.getManoDue().totale() <= 21
								&& giocatore.getManoUno().totale() < manoD.totale()
								&& giocatore.getManoDue().totale() == manoD.totale()
								|| giocatore.getManoUno().totale() <= 21 && giocatore.getManoDue().totale() <= 21
										&& giocatore.getManoUno().totale() == manoD.totale()
										&& giocatore.getManoDue().totale() < manoD.totale()
								|| giocatore.getManoUno().totale() > 21 && giocatore.getManoDue().totale() <= 21
										&& giocatore.getManoDue().totale() < manoD.totale()
								|| giocatore.getManoUno().totale() <= 21 && giocatore.getManoDue().totale() > 21
										&& giocatore.getManoUno().totale() < manoD.totale()) {

							System.out.println(
									"\nHai perso una mano ed hai pareggiato l altra, hai vinto: " + bet + " €");
							giocatore.guadagnoAssicurazioneParita(bet);

						}
					}
				}

				if (scelta != 4) {

					if (manoD.totale() > 21) {

						if (giocatore.getManoUno().totale() <= 21) {

							System.out.println("\nHai vinto, hai vinto: " + bet * 2 + " €");
							giocatore.guadagno(bet);

						}
						if (giocatore.getManoUno().totale() > 21) {

							System.out.println("\nHai perso");

						}

					}

					if (manoD.totale() <= 21) {

						if (giocatore.getManoUno().totale() <= 21 && giocatore.getManoUno().totale() > manoD.totale()) {

							System.out.println("\nHai vinto, hai vinto: " + bet * 2 + " €");
							giocatore.guadagno(bet);

						}

						if (giocatore.getManoUno().totale() > 21 || giocatore.getManoUno().totale() < manoD.totale()) {

							System.out.println("\nHai perso");

						}

						if (giocatore.getManoUno().totale() == manoD.totale()) {

							System.out.println("\nHai pareggiato, hai vinto: " + bet + " €");
							giocatore.guadagnoAssicurazioneParita(bet);

						}

					}
				}

			}
		} while (giocatore.getBudget() > 0.0 && conv != 1);

		System.out.println("\nArrivederci torna presto!");

	}
}
