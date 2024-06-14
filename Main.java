package fr.ul.miage;


import fr.ul.miage.Generation.Generator;
import fr.ul.miage.TDS.TDS;
import fr.ul.miage.arbre.Affectation;
import fr.ul.miage.arbre.Appel;
import fr.ul.miage.arbre.Bloc;
import fr.ul.miage.arbre.Const;
import fr.ul.miage.arbre.Ecrire;
import fr.ul.miage.arbre.Fonction;
import fr.ul.miage.arbre.Idf;
import fr.ul.miage.arbre.Inferieur;
import fr.ul.miage.arbre.Lire;
import fr.ul.miage.arbre.Multiplication;
import fr.ul.miage.arbre.Plus;
import fr.ul.miage.arbre.Prog;
import fr.ul.miage.arbre.Retour;
import fr.ul.miage.arbre.Si;
import fr.ul.miage.arbre.Superieur;
import fr.ul.miage.arbre.TantQue;
import fr.ul.miage.arbre.TxtAfficheur;

public class Main {

	public static void main(String[] args) {
		exemple1();//marche, correct aussi
		exemple2();//marche, correct aussi
		exemple3();//marche, correct aussi 
		exemple4();//marche, correct aussi
		exemple5();//marche , correct aussi
		exemple6();//marche, correct  aussi
		exemple7();//marche, correct
		exemple8();//marche, correct
		
	}
		// TODO Auto-generated method stub
		
	       public static void exemple1() {
	        //exemple1
	        	//Arbre Abstrait 1
	        	Prog prog1=new Prog();
	    		Fonction fonction1=new Fonction("main");
	    		prog1.ajouterUnFils(fonction1);
	    		TxtAfficheur.afficher(prog1);
	    		//Table des symbole programme1
	    		TDS tds1=new TDS();
	        	tds1.ajouterFonction("main", "void", 0, 0);
	        	tds1.afficher();
	        	//Generation du code
	        	Generator genererCode1=new Generator(prog1, tds1);
	        	genererCode1.afficherCodeAssembleur();
              
	        	
	       }
	        	
	        public static void exemple2() {
	        	//Arbre abstrait 2
	        	Prog prog2=new Prog();
	    		Fonction fonction2=new Fonction("main");
	    		Idf i21=new Idf("i");
	    		Idf j21=new Idf("j");
	    		Idf k21=new Idf("k");
	    		Idf l21=new Idf("l");
	    		prog2.ajouterUnFils(fonction2);
	    		prog2.ajouterUnFils(l21);
	    		prog2.ajouterUnFils(k21);
	    		prog2.ajouterUnFils(j21);
	    		prog2.ajouterUnFils(i21);
	    		TxtAfficheur.afficher(prog2);
	    		
	    		//Table des symboles programme2
	    		TDS tds2=new TDS();
	    		tds2.ajouterFonction("main", "void", 0, 0);
	    		tds2.ajouterVariableGlobale("i", "int", 10);
	    		tds2.ajouterVariableGlobale("j","int", 20);
	    		tds2.ajouterVariableGlobale("k", "int", 0);
	    		tds2.ajouterVariableGlobale("k", "int", 0);
	    		tds2.ajouterVariableGlobale("l", "int", 0);
	    		tds2.afficher();
	    		//Generation du code
	    		Generator genererCode2=new Generator(prog2, tds2);
	        	genererCode2.afficherCodeAssembleur();
	    		
	        }
	        public static void exemple3() {
	        	//Arbre abstrait 3:
	    		Prog prog3=new Prog();
	    		Fonction fonction3=new Fonction("main");
	    		//la fonction donne deux affectations
	    		Affectation affectation31=new Affectation();
	    		Affectation affectation32=new Affectation();
	    		prog3.ajouterUnFils(fonction3);
	    		fonction3.ajouterUnFils(affectation31);
	    		fonction3.ajouterUnFils(affectation32);
	    		//l'affectation1 donne k et 2
	    		Idf k31= new Idf("k");
	    		Const const31=new Const(2);
	    		affectation31.setFilsGauche(k31);
	    		affectation31.setFilsDroit(const31);
	    		//l'affectation2 donne l et plus
	    		Idf l31=new Idf("l");
	    		Plus plus31=new Plus();
	    		affectation32.setFilsGauche(l31);
	    		affectation32.setFilsDroit(plus31);
	    		//le plus donne i et *
	    		Idf i31=new Idf("i");
	    		Multiplication mul31=new Multiplication();
	    		plus31.setFilsGauche(i31);
	    		plus31.setFilsDroit(mul31);
	    		//la multiplication donne const et j
	    		Idf j31=new Idf("j");
	    		Const cons32=new Const(3);
	    		mul31.setFilsGauche(cons32);
	    		mul31.setFilsDroit(j31);
	    		TxtAfficheur.afficher(prog3);
	    		//Table des symboles 3
	    		TDS tds3=new TDS();
	    		tds3.ajouterFonction("main", "void", 0, 0);
	    		tds3.ajouterVariableGlobale("i", "int", 10);
	    		tds3.ajouterVariableGlobale("j", "int", 20);
	    		tds3.ajouterVariableGlobale("k", "int", 0);
	    		tds3.ajouterVariableGlobale("l", "int", 0);
	    		tds3.afficher();
	    		//Generation du code:
	    		Generator genererCode3=new Generator(prog3, tds3);
	        	genererCode3.afficherCodeAssembleur();
	        }
	        public static void exemple4() {
	        	Prog prog4=new Prog();
	    		Fonction fonction4=new Fonction("main");
	    		//la fonction main donne une affectation et "ecrire"
	    		Affectation affectation41=new Affectation();
	    		Ecrire ecrire41=new Ecrire();
	    		fonction4.ajouterUnFils(affectation41);
	    		fonction4.ajouterUnFils(ecrire41);
	    		//l'affectation donne i et lire
	    		Lire lire41=new Lire();
	    		Idf i41=new Idf("i");
	    		affectation41.setFilsGauche(i41);
	    		affectation41.setFilsDroit(lire41);
	    		//ecrire donne +
	    		Plus plus41=new Plus();
	    		ecrire41.ajouterUnFils(plus41);
	    		//plus donne i et j
	    		Idf j41=new Idf("j");
	    		plus41.setFilsGauche(i41);
	    		plus41.setFilsDroit(j41);
	    		prog4.ajouterUnFils(fonction4);
	    		TxtAfficheur.afficher(prog4);
	    		
	    		//Table des symboles
	    		TDS tds4=new TDS();
	    		tds4.ajouterFonction("main", "void", 0, 0);
	    		tds4.ajouterVariableGlobale("i", "int", 0);
	    		tds4.ajouterVariableGlobale("j", "int", 20);
	    		tds4.afficher();
	    		//Generation du code
	    		Generator genererCode4=new Generator(prog4, tds4);
	        	genererCode4.afficherCodeAssembleur();
	        }
	        public static void exemple5() {
	        	//Arbre abstraite 5
	        	Prog prog5=new Prog();
	    		Fonction fonction5=new Fonction("main");
	    		//la fonction main donne l'affectation et si
	    		Affectation affectation51=new Affectation();
	    		//l'affectation donne i et lire
	    		Idf i51=new Idf("i");
	    		Lire lire51=new Lire();
	    		affectation51.setFilsGauche(i51);
	    		affectation51.setFilsDroit(lire51);
	    		Si si51=new Si();
	    		Bloc bloc151=new Bloc();
	    		Bloc bloc152=new Bloc();
	    		Superieur sup=new Superieur();
	    		//superieur donne i et const 10
	    		sup.setFilsGauche(i51);
	    		Const const50=new Const(10);
	    		sup.setFilsDroit(const50);
	    		si51.setCondition(sup);
	    		si51.setBlocAlors(bloc151);
	    		si51.setBlocSinon(bloc152);
	    		//dans le bloc Alors
	    		Ecrire ecrire51=new Ecrire();
	    		bloc151.ajouterUnFils(ecrire51);
	    		//dans le bloc sinon
	    		Ecrire ecrire52=new Ecrire();
	    		bloc152.ajouterUnFils(ecrire52);
	    		//ecrire1 a un fils const=1
	    		Const const51=new Const(1);
	    		ecrire51.ajouterUnFils(const51);
	    		//ecrire2 a un fils const=2
	    		Const const52=new Const(2);
	    		ecrire52.ajouterUnFils(const52);
	    		fonction5.ajouterUnFils(affectation51);
	    		fonction5.ajouterUnFils(si51);
	    		prog5.ajouterUnFils(fonction5);
	    		TxtAfficheur.afficher(prog5);
	    		//Table des symboles 5
	    		TDS tds5=new TDS();
	    		tds5.ajouterFonction("main", "void", 0, 0);
	    		tds5.ajouterVariableGlobale("i", "int", 0);
	    		tds5.afficher();
	    		//Generation du code
	    		Generator genererCode5=new Generator(prog5, tds5);
	        	genererCode5.afficherCodeAssembleur();
	        }
	        public static void exemple6() {
	        	Prog prog6=new Prog();
	    		Fonction fonction6=new Fonction("main");
	    		//la fonction main donne une affectation et une boucle tant que
	    		Affectation affectation61=new Affectation();
	    		TantQue tq=new TantQue();
	    		//l'affectation donne i et const=0
	    		Idf i61=new Idf("i");
	    		Const const61=new Const(0);
	    		affectation61.setFilsGauche(i61);
	    		affectation61.setFilsDroit(const61);
	    		//la condition de tantque: <
	    		Inferieur inf=new Inferieur();
	    		//i<n
	    		Idf n61=new Idf("n");
	    		inf.setFilsGauche(i61);
	    		inf.setFilsDroit(n61);
	    		tq.setCondition(inf);
	    		Bloc bloc61=new Bloc();
	    		tq.setBloc(bloc61);
	    		//bloc donne ecrire et une affectation
	    		Ecrire ecrire61=new Ecrire();
	    		ecrire61.ajouterUnFils(i61);
	    		Affectation affectation62=new Affectation();
	    		bloc61.ajouterUnFils(ecrire61);
	    		bloc61.ajouterUnFils(affectation62);
	    		//l'affectation2 donne i et +
	    		Plus plus61=new Plus();
	    		affectation62.setFilsGauche(i61);
	    		affectation62.setFilsDroit(plus61);
	    		//plus donne i et cont=1
	    		Const const62=new Const(1);
	    		plus61.setFilsGauche(i61);
	    		plus61.setFilsDroit(const62);
	    		fonction6.ajouterUnFils(affectation61);
	    		fonction6.ajouterUnFils(tq);
	    		prog6.ajouterUnFils(fonction6);
	    		TxtAfficheur.afficher(prog6);
	    		//Table des symboles
	    		TDS tds6=new TDS();
	    		tds6.ajouterFonction("main", "void", 0, 0);
	    		tds6.ajouterVariableGlobale("i", "int", 0);
	    		tds6.ajouterVariableGlobale("n", "int", 5);
	    		tds6.afficher();
	    		
	    		//Generation du code
	    		
	    		Generator genererCode6=new Generator(prog6, tds6);
	        	genererCode6.afficherCodeAssembleur();
	        }
	        public static void exemple7() {
	        	Prog prog7=new Prog();
	    		Fonction fonction71=new Fonction("main");
	    		Fonction fonction72=new Fonction("f");
	    		prog7.ajouterUnFils(fonction71);
	    		prog7.ajouterUnFils(fonction72);
	    		//la fonction f donne 3 affectations
	    		Affectation affectation71=new Affectation();
	    		Affectation affectation72=new Affectation();
	    		Affectation affectation73=new Affectation();
	    		
	    		//la premier affectation donne x et const=1
	    		Idf x71=new Idf("x");
	    		Const const71=new Const(1);
	    		affectation71.setFilsGauche(x71);
	    		affectation71.setFilsDroit(const71);
	    		//la deuxieme affectation donne y et const=1
	    		Idf y71=new Idf("y");
	    		Const const72=new Const(1);
	    		affectation72.setFilsGauche(y71);
	    		affectation72.setFilsDroit(const72);
	    		//la 3eme affectation donne a et plus
	    		Idf a71=new Idf("a");
	    		Plus plus71 =new Plus();
	    		affectation73.setFilsGauche(a71);
	    		affectation73.setFilsDroit(plus71);
	    		//plus donne i et plus
	    		Plus plus72=new Plus();
	    		Idf i71=new Idf("i");
	    		plus71.setFilsGauche(i71);
	    		plus71.setFilsDroit(plus72);
	    		
	    		//la plus donne x et y
	    		plus72.setFilsGauche(x71);
	    		plus72.setFilsDroit(y71);
	    		//la fonction main donne appel f et ecrire
	    		Appel appel71=new Appel("f");
	    		Ecrire ecrire71=new Ecrire();
	    		fonction71.ajouterUnFils(appel71);
	    		fonction71.ajouterUnFils(ecrire71);
	    		fonction72.ajouterUnFils(affectation71);
	    		fonction72.ajouterUnFils(affectation72);
	    		fonction72.ajouterUnFils(affectation73);
	    		//l'appel donne const=3
	    		Const const73=new Const(3);
	    		appel71.ajouterUnFils(const73);
	    		//ecrire donne a 
	    		ecrire71.ajouterUnFils(a71);
	    		TxtAfficheur.afficher(prog7);
	    		//Table des symboles
	    		TDS tds7=new TDS();
	    		tds7.ajouterFonction("main", "void", 0, 0);
	    		tds7.ajouterVariableGlobale("a", "int", 10);
	    		tds7.ajouterFonction("f", "void", 1, 2);
	    		tds7.ajouterParam("i", "int", "f", 0);
	    		tds7.ajouterVariableLocale("x", "int", "f", 0);
	    		tds7.ajouterVariableLocale("y", "int", "f", 1);
	    		tds7.afficher();
	    		
	    		//Generation de code
	    		
	    		Generator genererCode7=new Generator(prog7, tds7);
	        	genererCode7.afficherCodeAssembleur();
	        }
	        public static void exemple8() {
	        	 Idf idf81 = new Idf("a");
	             Ecrire ecrire81 = new Ecrire();
	             ecrire81.setLeFils(idf81);

	             Const c81= new Const(2);
	             Const c82 = new Const(1);
	             Appel appel81 = new Appel("f");
	             appel81.ajouterUnFils(c81);
	             appel81.ajouterUnFils(c82);

	             Idf idf82 = new Idf("a");
	             Affectation affectation81 = new Affectation();
	             affectation81.setFilsGauche(idf82);
	             affectation81.setFilsDroit(appel81);

	             Fonction fonction8 = new Fonction("main");
	             fonction8.ajouterUnFils(ecrire81);
	             fonction8.ajouterUnFils(affectation81);
	          
	             Const c83 = new Const(10);
	             Idf idf83 = new Idf("x");
	             Plus plus81 = new Plus();
	             plus81.setFilsGauche(idf83);
	             plus81.setFilsDroit(c83);
	             Retour retour81 = new Retour("f");
	             retour81.setLeFils(plus81);

	             Idf idf84 = new Idf("j");
	             Idf idf85 = new Idf("i");
	             Plus plus82 = new Plus();
	             plus82.setFilsGauche(idf84);
	             plus82.setFilsDroit(idf85);
	             Idf idf86 = new Idf("x");
	             Affectation affectation82 = new Affectation();
	             affectation82.setFilsGauche(idf86);
	             affectation82.setFilsDroit(plus82);
	             Fonction fonction82 = new Fonction("f");
	             fonction82.ajouterUnFils(affectation82);
	             fonction82.ajouterUnFils(retour81);

	             Prog prog8 = new Prog();
	             prog8.ajouterUnFils(fonction8);
	             prog8.ajouterUnFils(fonction82);
	             TxtAfficheur.afficher(prog8);
	             //Table des symboles:
	             TDS tds8=new TDS();
	             tds8.ajouterFonction("main", "void", 0, 0);
	             tds8.ajouterVariableGlobale("a", "int", 10);
	             tds8.ajouterFonction("f", "void", 2, 1);
	             tds8.ajouterParam("i", "int", "f", 0);
	             tds8.ajouterVariableLocale("x", "int", "f", 0);
	             tds8.ajouterParam("j", "int", "f", 1);
	             tds8.afficher();
	             //Generation de code
	             	Generator genererCode8=new Generator(prog8, tds8);
		        	genererCode8.afficherCodeAssembleur();
	    	

	        	
	        
	        }
	}


