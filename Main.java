/*
 *  License and Copyright:
 *  This file is part of arbre project.
 *
 *   It is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   any later version.
 *
 *   It is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Copyright 2019 by LORIA, Université de Lorraine
 *  All right reserved 
 */
package fr.ul.miage.arbre;

/**
 * Description :
 * 
 * @author Azim Roussanaly Created at 28 févr. 2019
 */
public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String n = "0";
		if (args.length == 1) {
			n = args[0];
		}
		switch (n) {
		case "1":
			// test #1
			Noeud prog2 = new Prog();
			Noeud max2 = new Fonction("max");
			Noeud main2 = new Fonction("main");
			prog2.ajouterUnFils(max2);
			prog2.ajouterUnFils(main2);
			TxtAfficheur.afficher(prog2);
			break;
		case "2":
			// test #2
			Prog prog3 = new Prog();
			Fonction max3 = new Fonction("max");
			Fonction main3 = new Fonction("main");
			Affectation aff3 = new Affectation();
			Idf x3 = new Idf("x");
			Const c3 = new Const(10);
			aff3.setFilsGauche(x3);
			aff3.setFilsDroit(c3);
			Retour r3 = new Retour("max");
			Idf y3 = new Idf("x");
			r3.setLeFils(y3);
			Bloc b3 = new Bloc();
			b3.ajouterUnFils(aff3);
			b3.ajouterUnFils(r3);
			max3.ajouterUnFils(b3);
			prog3.ajouterUnFils(max3);
			prog3.ajouterUnFils(main3);
			TxtAfficheur.afficher(prog3);
			break;
		case "3":
			// test Tant que
			Idf idf3 = new Idf("i");
			Const const3 = new Const(10);
			Affectation afff3 = new Affectation();
			afff3.setFilsGauche(idf3);
			afff3.setFilsDroit(const3);
			Bloc bloc3 = new Bloc();
			bloc3.ajouterUnFils(afff3);
			Idf idff3 = new Idf("j");
			Idf idfff3 = new Idf("k");
			Inferieur inf3 = new Inferieur();
			inf3.setFilsGauche(idff3);
			inf3.setFilsDroit(idfff3);
			TantQue tq3 = new TantQue();
			tq3.setCondition(inf3);
			tq3.setBloc(bloc3);
			TxtAfficheur.afficher(tq3);
			break;
		case "4":
			// test Si
			Idf idf41 = new Idf("j");
			Idf idf42 = new Idf("k");
			Inferieur inf4 = new Inferieur();
			inf4.setFilsGauche(idf41);
			inf4.setFilsDroit(idf42);
			Idf idf43 = new Idf("i");
			Const const41 = new Const(10);
			Affectation aff41 = new Affectation();
			aff41.setFilsGauche(idf43);
			aff41.setFilsDroit(const41);
			Bloc bloc4 = new Bloc();
			bloc4.ajouterUnFils(aff41);
			Si si4 = new Si();
			si4.setCondition(inf4);
			si4.setBlocAlors(bloc4);
			TxtAfficheur.afficher(si4);
			break;

		default:
			// test #0
			Noeud prog = new Prog();
			Noeud main = new Fonction("main");
			prog.ajouterUnFils(main);
			TxtAfficheur.afficher(prog);
			break;
		}
	}

}
