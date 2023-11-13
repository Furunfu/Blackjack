package blackjack;

public interface Mano {

	public void aggiungiCarta(Carta carta);

	public int totale();

	public void ripristina();

	public boolean checkBj();

}
