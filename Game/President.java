package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Math;

import Card_Paquet.Carte;
import Card_Paquet.Paquet;

public class President {
	private List<Paquet> joueur = new ArrayList<Paquet>();
	private List<Integer> role = new ArrayList<Integer>(); //-2 Trou, -1 vice, 0 neutre, 1 vice president, 2 ppresident
	private Paquet carte_en_jeu = new Paquet(0);
	private int nb_joueur = 0;
	
	public President(int _nb_joueur) {
		this.nb_joueur = _nb_joueur;
		
		if (nb_joueur > 5 || nb_joueur < 2) {
			return;
		}
		
		for (int j = 0; j < _nb_joueur; j++) {
			Paquet p = new Paquet(0);
			joueur.add(p);
			role.add(0);
		}
	}

	public void init_manche(Paquet paquet_depart) {
		paquet_depart.melanger();
		paquet_depart.distribuer(joueur);
		
		HashMap<Integer, Paquet> transition = new HashMap<Integer, Paquet>();
		for (int position = 0; position < this.nb_joueur; position++) {
			joueur.get(position).sort_paquet();
			Paquet p = new Paquet(0);
			
			for(int nb_carte = 0; nb_carte< Math.abs(role.get(position)); nb_carte++) {
				if(role.get(position) < 0) {
					p.add_to_end(joueur.get(position).end_of_deck());
				}else {
					p.add_to_end(joueur.get(position).top_of_deck());
				}
			}
			transition.put(role.get(position), p);
		}
		
		for (int position = 0; position < this.nb_joueur; position++) {
			joueur.get(position).add_deck(transition.get(this.role.get(position) * (-1)));
			joueur.get(position).sort_paquet();
		}	
	}
	
	public List<Integer> jouer_partie() {
		List<Integer> Classement = new ArrayList<Integer>();
		int attribution_role = 0;
		if(this.nb_joueur >= 4) {
			attribution_role = 2;
		}else {
			attribution_role = 1;
		}
		
		int debut_tour = 0;
		int suivant = 0;
		int min_viewed = 3;
		for (int position = 0; position < this.nb_joueur; position++) {
			if (role.get(position) <= min_viewed) {
				min_viewed = role.get(position);
				debut_tour = position;
				suivant = (position + 1) % (this.nb_joueur - 1);
			}
		}
		
		int joueur_en_jeu = this.nb_joueur;
		while(joueur_en_jeu > 1) {
			this.carte_en_jeu.clear();
			
			while(this.joueur.get(debut_tour).isEmpty()){
				debut_tour = (debut_tour + 1) % (this.nb_joueur);
			}
			
			this.carte_en_jeu.add_to_top(joueur.get(debut_tour).top_of_deck());
			if(this.joueur.get(debut_tour).isEmpty()) {
				joueur_en_jeu--;
				this.role.set(debut_tour, attribution_role);
				attribution_role--;
				if (attribution_role == 0 && this.nb_joueur % 2 == 0) {
					attribution_role--;
				}
				Classement.add(debut_tour);
				continue;
			}
			
			int tour_passe = 0;
			while(tour_passe < joueur_en_jeu) {
				suivant = (suivant + 1) % (this.nb_joueur);
				while(this.joueur.get(suivant).isEmpty()){
					suivant = (suivant + 1) % (this.nb_joueur);
				}
				
				int nb_carte_vue = 0;
				while(nb_carte_vue < this.joueur.get(suivant).len() && this.carte_en_jeu.view_top_of_deck().getValue() >= this.joueur.get(suivant).view_index_of_deck(nb_carte_vue).getValue()) {
					nb_carte_vue++;
				}
				if(nb_carte_vue < this.joueur.get(suivant).len()) {
					this.carte_en_jeu.add_to_top(joueur.get(suivant).index_of_deck(nb_carte_vue));
					debut_tour = suivant;
				}else {
					tour_passe++;
				}
				if(this.joueur.get(suivant).isEmpty()) {
					joueur_en_jeu--;
					this.role.set(suivant, attribution_role);
					attribution_role--;
					if (attribution_role == 0 && this.nb_joueur % 2 == 0) {
						attribution_role--;
					}
					Classement.add(suivant);
					break;
				}
			}	
		}
		
		for (int j = 0; j < this.nb_joueur; j++) {
			if(!this.joueur.get(j).isEmpty()) {
				this.role.set(j, attribution_role);
				Classement.add(j);
			}
		}
		
		return Classement;
	}
}
