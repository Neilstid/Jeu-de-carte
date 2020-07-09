package Card_Paquet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Paquet {
	private List<Carte> paquet = new ArrayList<Carte>();
	
	//Constructeur
	public Paquet(int nb_carte) {
		List<String> family_name = new ArrayList<String>(Arrays.asList("Coeur", "Carreau", "Trefle", "Pique"));
		List<String> card_name = new ArrayList<String>(Arrays.asList("Deux", "Trois", "Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf", "Dix", "Vallet", "Renne", "Roi", "As"));
		List<Integer> value = new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 1));
		
		if (nb_carte == 52) {
			for (int fam = 0; fam < 4; fam++) {
				for (int card = 0; card < 13; card++) {
					Carte c = new Carte(family_name.get(fam), card_name.get(card), value.get(card));
					paquet.add(c);
				}	
			}
			
		}else if(nb_carte == 32) {
			for (int fam = 0; fam < 4; fam++) {
				for (int card = 5; card < 13; card++) {
					Carte c = new Carte(family_name.get(fam), card_name.get(card), value.get(card));
					paquet.add(c);
				}	
			}
			
		}else if(nb_carte != 0) {
			System.out.print(nb_carte + " n'est pas un nombre de carte valide ! Le paquet sera donc vide.");
		}
	}
	
	public Paquet(List<String> family_name, List<String> card_name, List<Integer> value) {
		for (String family : family_name) {
			for (int carte = 0; carte < card_name.size(); carte++) {
				Carte c = new Carte(family, card_name.get(carte), value.get(carte));
				paquet.add(c);
			}
		}
	}
	
	public void melanger() {
		Collections.shuffle(paquet);
	}
	
	public Carte top_of_deck() {
		Carte top = paquet.get(0);
		paquet.remove(0);
		return top;
	}
	
	public Carte end_of_deck() {
		Carte top = paquet.get(paquet.size() - 1);
		paquet.remove(paquet.size() - 1);
		return top;
	}
	
	public Carte index_of_deck(int index) {
		Carte c = paquet.get(index);
		paquet.remove(index);
		return c;
	}
	
	public Carte view_top_of_deck() {
		Carte top = paquet.get(0);
		return top;
	}
	
	public Carte view_end_of_deck() {
		Carte top = paquet.get(paquet.size() - 1);
		return top;
	}
	
	public Carte view_index_of_deck(int index) {
		Carte top = paquet.get(index);
		return top;
	}
	
	public void add_to_end(Carte c) {
		paquet.add(c);
	}
	
	public void add_to_top(Carte c) {
		paquet.add(0, c);
	}
	
	public void add_deck(Paquet p) {
		while(!p.isEmpty()) {
			this.add_to_end(p.end_of_deck());
		}
	}
	
	public boolean isEmpty() {
		return paquet.isEmpty();
	}
	
	public void clear() {
		paquet.clear();
	}
	
	public int len() {
		return paquet.size();
	}
	
	public void distribuer(Paquet ...paquets) {
		while(!this.paquet.isEmpty()) {
			for (Paquet paquet : paquets) {
				if (!this.isEmpty()) {
					paquet.add_to_end(this.top_of_deck());
				}
			}
		}
	}
	
	public void distribuer(List<Paquet> paquets) {
		while(!this.paquet.isEmpty()) {
			for (Paquet paquet : paquets) {
				if (!this.isEmpty()) {
					paquet.add_to_end(this.top_of_deck());
				}
			}
		}
	}
	
	public void sort_paquet() {
		Collections.sort(paquet, new SortByValue());
	}
}


