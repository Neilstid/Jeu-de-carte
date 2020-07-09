package Card_Paquet;
import java.util.*;

import Game.President;
import Game.War; 

public class Main {
	
	public static void main(String[] args) 
    { 
		/*
		 //Bataille
		 for (int i = 0; i < 1000; i++) {
			Paquet p = new Paquet(32);
			p.melanger();
			War w = new War(p);
			int result = w.jouer_partie(100000);
			
			System.out.println(result);
		}*/
		
		//President
		int nb_joueur = 5;
		
		List<String> family_name_president = new ArrayList<String>(Arrays.asList("Coeur", "Carreau", "Trefle", "Pique"));
		List<String> card_name_president = new ArrayList<String>(Arrays.asList("Trois", "Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf", "Dix", "Vallet", "Renne", "Roi", "As", "Deux"));
		List<Integer> value_president = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
		
		ArrayList<List<Integer>> total = new ArrayList<>();
		for (int i = 0; i < nb_joueur; i++) {
			List<Integer> classement = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0));
			total.add(classement);
		}
			
		List<Integer> temp = new ArrayList<Integer>();
		President partie = new President(nb_joueur);
		
		for (int i = 0; i < 100000; i++) {
			Paquet p = new Paquet(family_name_president, card_name_president, value_president);
			partie.init_manche(p);
			temp = partie.jouer_partie();
			
			for (int j = 0; j < nb_joueur; j++) {
				total.get(temp.get(j)).set(j, total.get(temp.get(j)).get(j)+1);
			}
		}
		
		for (int j = 0; j < nb_joueur; j++) {
			System.out.println(total.get(j));
		}
		
    }
}
