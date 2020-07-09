package Game;

import Card_Paquet.Carte;
import Card_Paquet.Paquet;

public class War {
	Paquet joueur1 = new Paquet(0);
	Paquet joueur2 = new Paquet(0);
	Paquet Carte_en_jeu = new Paquet(0);
	
	public War(Paquet paquet_depart) {
		paquet_depart.melanger();
		paquet_depart.distribuer(joueur1, joueur2);
	}

	public int jouer(int tour_max) {
		int tour = 0;
		while(!joueur1.isEmpty() && !joueur2.isEmpty() && tour < tour_max) {
			System.out.print("Nouveau Tour");
			
			Carte carte_joueur1 = joueur1.top_of_deck();
			Carte carte_joueur2 = joueur2.top_of_deck();
			
			Carte_en_jeu.add_to_end(carte_joueur1);
			Carte_en_jeu.add_to_end(carte_joueur2);
			
			while(carte_joueur1.is_equals(carte_joueur2) && !joueur1.isEmpty() && !joueur2.isEmpty()) {
				tour++;
				carte_joueur1 = joueur1.top_of_deck();
				carte_joueur2 = joueur2.top_of_deck();
				
				Carte_en_jeu.add_to_end(carte_joueur1);
				Carte_en_jeu.add_to_end(carte_joueur2);
			}
			
			if (joueur1.isEmpty()) {
				return 2;
			}
			
			if (joueur2.isEmpty()) {
				return 1;
			}
			
			if (carte_joueur1.is_greater(carte_joueur2)) {
				System.out.print("Joueur 1 a gagne avec la carte : " + carte_joueur1.afficher());
				while(!Carte_en_jeu.isEmpty()) {
					joueur1.add_to_end(Carte_en_jeu.top_of_deck());
				}
			}else {
				System.out.print("Joueur 2 a gagne avec la carte : " + carte_joueur2.afficher());
				while(!Carte_en_jeu.isEmpty()) {
					joueur2.add_to_end(Carte_en_jeu.top_of_deck());
				}
			}
			tour++;
		}
		
		if (joueur1.isEmpty()) {
			return 2;
		}else if (joueur2.isEmpty()) {
			return 1;
		}
		
		return 0;
	}
	
	public int jouer_partie(int tour_max) {
		int tour = 0;
		while(!joueur1.isEmpty() && !joueur2.isEmpty() && tour < tour_max) {
			//System.out.println("Taille paquet 1 :" + joueur1.len());
			//System.out.println("Taille paquet 2 :" + joueur2.len());
			
			Carte carte_joueur1 = joueur1.top_of_deck();
			Carte carte_joueur2 = joueur2.top_of_deck();
			
			this.Carte_en_jeu.add_to_end(carte_joueur1);
			this.Carte_en_jeu.add_to_end(carte_joueur2);
			
			while(carte_joueur1.is_equals(carte_joueur2) && !joueur1.isEmpty() && !joueur2.isEmpty()) {
				tour++;
				carte_joueur1 = joueur1.top_of_deck();
				carte_joueur2 = joueur2.top_of_deck();
				
				Carte_en_jeu.add_to_end(carte_joueur1);
				Carte_en_jeu.add_to_end(carte_joueur2);
			}
			
			if (joueur1.isEmpty()) {
				return 2;
			}else if (joueur2.isEmpty()) {
				return 1;
			}
			
			if (carte_joueur1.is_greater(carte_joueur2)) {
				//System.out.println("Joueur 1 a gagne avec la carte : " + carte_joueur1.afficher() + " contre " + carte_joueur2.afficher());
				while(!Carte_en_jeu.isEmpty()) {
					joueur1.add_to_end(Carte_en_jeu.top_of_deck());
				}
			}else {
				//System.out.println("Joueur 2 a gagne avec la carte : " + carte_joueur2.afficher() + " contre " + carte_joueur1.afficher());
				while(!Carte_en_jeu.isEmpty()) {
					joueur2.add_to_end(Carte_en_jeu.top_of_deck());
				}
			}
			tour++;
			
		}
		if (joueur1.isEmpty()) {
			return 2;
		}else if (joueur2.isEmpty()) {
			return 1;
		}
		
		return 0;
	}
}
