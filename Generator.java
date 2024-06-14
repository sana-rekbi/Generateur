package fr.ul.miage.Generation;

import fr.ul.miage.TDS.Symbole;
import fr.ul.miage.TDS.TDS;
import fr.ul.miage.arbre.Affectation;
import fr.ul.miage.arbre.Appel;
import fr.ul.miage.arbre.Bloc;
import fr.ul.miage.arbre.Const;
import fr.ul.miage.arbre.Different;
import fr.ul.miage.arbre.Division;
import fr.ul.miage.arbre.Ecrire;
import fr.ul.miage.arbre.Egal;
import fr.ul.miage.arbre.Fonction;
import fr.ul.miage.arbre.Idf;
import fr.ul.miage.arbre.Inferieur;
import fr.ul.miage.arbre.InferieurEgal;
import fr.ul.miage.arbre.Lire;
import fr.ul.miage.arbre.Moins;
import fr.ul.miage.arbre.Multiplication;
import fr.ul.miage.arbre.Noeud;
import fr.ul.miage.arbre.Plus;
import fr.ul.miage.arbre.Prog;
import fr.ul.miage.arbre.Retour;
import fr.ul.miage.arbre.Si;
import fr.ul.miage.arbre.Superieur;
import fr.ul.miage.arbre.SuperieurEgal;
import fr.ul.miage.arbre.TantQue;

public class Generator {
	
	 private final StringBuilder asmCode = new StringBuilder();
	 private TDS tableDesSymboles;
	 /**Constructeur qui genere le code assembleur à l'aide de la pachage table des symboles et l'arbre
	  * */
	 public Generator(Prog arbre, TDS tableDesSymboles) {
	        this.tableDesSymboles = tableDesSymboles;
	        GenererProgramme(arbre, tableDesSymboles);
	    }
	 
	/**@param p: programme de l'arbre abstrait
	 * cette methode genere le code assembleur generé
	 * @param tds: table des symboles 
	 * */
	 public void GenererProgramme(Prog tree, TDS tds) {
		asmCode.append("\n.include beta.uasm\n");
		asmCode.append("\n.include intio.uasm\n");
		asmCode.append("\n.options tty\n");
		asmCode.append("\n\tCMOVE(pile,SP)\n");
		asmCode.append("\tBR(begin)\n");
		
		//generer data : 
		for(Symbole symbole:this.tableDesSymboles.getSymbole()) {
			
			if(symbole.getCategory().equals("variable globale")) {
				if(symbole.getValue()==0) {
				
					asmCode.append("\n"+symbole.getName()+":\n");
					asmCode.append("\tLONG(0)\n");
					
				}else {
					asmCode.append("\n"+symbole.getName()+":\n");
					asmCode.append("\tLONG("+symbole.getValue()+")\n");
					
				}
			}
		}
		
		
		for(Noeud n:tree.getFils()) {
			String[]nom=n.getLabel().split("/");
			if(n instanceof Fonction && nom[1].equals("main")) {
				this.GenererFonction((Fonction) n, tds);
				asmCode.append("\nbegin:\n");
				asmCode.append("\tCALL("+nom[1]+")\n");
				asmCode.append("\tHALT()\n");
			}else if(n instanceof Fonction) {
				this.GenererFonction((Fonction)n, tds);
			}else {
				this.GenererInstruction(n);
			}
		}
		
		asmCode.append("\npile:\n");
		asmCode.append("\nfin:\n");
		
		 
	 }



	/**@param f: fonction de l'arbre abstrait
	 * @param tds: table des symboles
	 * cette methode genere le code assembleur d'une fonction
	 * */
	 public void GenererFonction(Fonction f, TDS tds) {
		 String[]nomFonction=f.getLabel().split("/");
		 asmCode.append("\n"+nomFonction[1]+":\n");
		 asmCode.append("\t PUSH(LP)\n");
		 asmCode.append("\t PUSH(BP)\n");
		 asmCode.append("\t MOVE(SP,BP)\n");
		Symbole fonction=this.tableDesSymboles.getSymbole(nomFonction[1]);
		if(fonction.getName()!="main") {
			asmCode.append("\tALLOCATE("+fonction.getNbLocal()+")\n");//à revoir 
		}
		
		for(Noeud n:f.getFils()) {
			GenererInstruction(n);
		
		}
		 
		 
		 if(fonction.getName()!="main") {
			  asmCode.append("\t DEALLOCATE("+fonction.getNbLocal()+")\n");
		 }
		 asmCode.append("\nreturn_"+fonction.getName()+":\n");
		 asmCode. append("\t POP(BP)\n");
		 asmCode.append("\t POP(LP)\n");
		 asmCode.append("\t  RTN()\n");
		 
		 
		 
		
		 
	 }
	 /**@param n : noeud de l'arbre abstrait
	  * cette methode genere le code assembleur d'une instruction en fonction de type de noeud
	  * */
	 private void GenererInstruction(Noeud n) {//donne affectation|Ecrire|Appel|Retour|Bloc|Si|TantQue|expression boolean
			// TODO Auto-generated method stub
			if(n instanceof Affectation) {
				this.GenererAffectation((Affectation)n);
			}else if(n instanceof Ecrire) {
				this.GenererEcrire((Ecrire)n);
			}else if(n instanceof Appel) {
				this.GenererAppel((Appel)n);
			}else if(n instanceof Retour) {
				this.GenererRetour((Retour)n);
			}else if(n instanceof Bloc) {
				this.GenererBloc((Bloc)n);
			}else if(n instanceof Si ) {
				this.GenererSi((Si)n);
			}else if (n instanceof TantQue) {
				this.GenererTantQue((TantQue)n);
			}else{
				this.genererCondition((n));
			}
			{
				
			}
			
		}
	 	/**@param a:affectation de l'arbre abstrit
	 	 * cette methode genere le code assembleur d'une affectation
	 	 * */
		private void GenererAffectation(Affectation a) {
		// TODO Auto-generated method stub
		genererExpression(a.getFilsDroit());
		
		String []nom=a.getFilsGauche().getLabel().split("/");
		 Symbole symbole = this.tableDesSymboles.getSymbole(nom[1]);
		asmCode.append("\tPOP(R0)\n");
		boolean symboleGlobal = symbole.getCategory().equals("variable globale");
	    boolean symboleLocal = symbole.getCategory().equals("variable locale");
	    boolean symboleParam = symbole.getCategory().equals("parametre");
		if(symboleGlobal==true) {//si la variable est globales
			asmCode.append("\t|affectation d'une variable globale\n");
			asmCode.append("\tST(R0,"+nom[1]+")\n");
			
			
			
		}else if(symboleLocal==true && this.tableDesSymboles.getSymbole1(nom[1], "f")!=null) {
			int offsetLocal=4*(1+symbole.getRank());
			asmCode.append("\t|affectation d'une variable locale\n");
			asmCode.append("\tPUTFRAME(R0,"+offsetLocal+")\n");
			asmCode.append("\tPUSH(R0)\n");//est ce qu'on doit mettre push(R0)? 
		
			
			
		}else if(symboleParam==true && this.tableDesSymboles.getSymbole1(nom[1], "f")!=null ) {//parametre
			int offsetParam=4*(-1 -symbole.getNbParams()+symbole.getRank());
			asmCode.append("\t|affectation d'un parametre\n");
			asmCode.append("\tPUTFRAME(R0,"+offsetParam+")\n");
			asmCode.append("\tPUSH(R0)\n");
			
		}
	}

		



		/**@param n: noeud de l'arbre abstrit
		 * cette methode genere un expression en fonction du type de noeud
		 * */
		private void genererExpression(Noeud n) {//GenererExpression generer Const|Idf|Plus|Moins|Division|Multiplication|Lire|Appel
			// TODO Auto-generated method stub
			if(n instanceof Const) {
				this.GenererConst((Const)n);
			}else if(n instanceof Idf) {
				this.GenererIdf((Idf)n);
			}else if(n instanceof Plus ) {
				this.GenererAddition((Plus)n);
			}else if(n instanceof Moins) {
				this.GenererSoustraction((Moins)n);
			}else if(n instanceof Multiplication) {
				this.GenererMultiplication((Multiplication)n);
			}else if(n instanceof Division) {
				this.GenererDivision((Division)n);
			}else if(n instanceof Lire) {
				this.GenererLire((Lire)n);	
			}else if(n instanceof Appel) {
				this.GenererAppel((Appel)n);//à revoir si appel est un instruction ou un expression 
			}
		}

		/**@param r: noeud de l'arbre abstrait
		 * cette methode genere le code assembleur pour le retour 
		 * */
		private void GenererRetour(Retour r) {
		// TODO Auto-generated method stub
			String []nom=r.getLabel().split("/");
			Symbole symbole = this.tableDesSymboles.getSymbole(nom[1]);
			if(r.getFils()!=null && symbole.getScope()!="" ) {
				genererExpression(r.getLeFils());
				
				int n=symbole.getNbParams();
				int offset=4*(-n-2);
				asmCode.append("\t|retour\n");
				asmCode.append("\tPOP(R0)\n");
				asmCode.append("\tPUTFRAME(R0,"+offset+")\n");
				
				
				
			}
			
			asmCode.append("\tBR(return_"+nom[1]+")\n");
			
		
			
			
		
	}


		/**@param a: noeud appel de l'arbre abstrait
		 * cette methode genere le code assembleur pour un appel
		 * */
		private void GenererAppel(Appel a) {
		// TODO Auto-generated method stub
			int p=0;
			String[]nom=a.getLabel().split("/");
			if(this.tableDesSymboles.getSymbole(nom[1])!=null) {//à revoir
				p++;
				asmCode.append("\t"+"ALLOCATE("+p+")\n");
				
				
			}for(Noeud n:a.getFils()) {
				genererExpression(n);
				
				
			}
			asmCode.append("\tCALL("+a.getValeur().toString()+")\n");
			asmCode.append("\tDEALLOCATE("+p+")\n");
			
			
			
	}
		/**@param e: noeud de l'arbre abstrait
		 * cette methode genere le code assembleur pour l'ecriture
		 * à revoir si je mettrai un condition pour que si j'ai une variable globale dans le fils, je mettrai ST()
		 * */
		private void GenererEcrire(Ecrire e) {
		// TODO Auto-generated method stub
		genererExpression(e.getLeFils());
		asmCode.append("\tPOP(R0)\n");
		asmCode.append("\tWRINT()\n\n");
		}
		
		/**@param l
		 * cette methode genere le code assembleur pour le lecture
		 * */
		private void GenererLire(Lire l) {
			// TODO Auto-generated method stub
			asmCode.append("\tRDINT()\n");
			asmCode.append("\tPUSH(R0)\n");
		}
		
			/**@param d: noeud different de l'arbre abstrait
			 * cette methode genere le code assembleur pour different
			 * */
			private void GenererDifferent(Different d) { //a#b en code assembleur
			// TODO Auto-generated method stub
				genererExpression(d.getFilsGauche());
				genererExpression(d.getFilsDroit());
				asmCode.append("\tPOP(R2)\n");
				asmCode.append("\tPOP(R1)\n");
				asmCode.append("\tCMPEQ(R1,R2,R3)\n");
				asmCode.append("\tPUSH(R3\n");
				
		}
			/**@param ie: noeud inférieur ou egal de l'abre abstrait
			 * cette methode genere le code assembleur pour l'instruction <=
			 * */
			private void GenererInferieurEgal(InferieurEgal ie) {
			// TODO Auto-generated method stub
				genererExpression(ie.getFilsGauche());
				genererExpression(ie.getFilsDroit());
				asmCode.append("\tPOP(R1)\n");
				asmCode.append("\tPOP(R0)\n");
				asmCode.append("\tCMPLT(R0,R1,R2)\n");
				asmCode.append("\tPUSH(R2)\n");
				
		}
			/**@param se: noeud superieur ou egal de l'abre abstrait
			 * cette methode genere le code assembleur pour l'instruction >=
			 * */
			private void GenererSuperieurEgal(SuperieurEgal se) {//à revoir
			// TODO Auto-generated method stub
				genererExpression(se.getFilsGauche());
				genererExpression(se.getFilsDroit());
				asmCode.append("\tPOP(R1)\n");
				asmCode.append("\tPOP(R0)\n");
				asmCode.append("\tCMPLE(R1,R0,R2)\n");
				asmCode.append("\tPUSH(R2)\n");
				
		}
			/**@param e: noued egal de l'arbre abstrait
			 * cette methode genere le code assembleur pour l'egalité
			 * */
			private void GenererEgal(Egal e) {//a=b, ca sera la meme chose que affectation mais pour des constantes on verra 
			// TODO Auto-generated method stub
				
				genererExpression(e.getFilsGauche());
				genererExpression(e.getFilsDroit());
				asmCode.append("\tPOP(R2)\n");
				asmCode.append("\tPOP(R1)\n");
				asmCode.append("\tCMPEQ(R1,R2,R3)\n");
				asmCode.append("\tPUSH(R3)\n");
				
		}
			/**@param s: noeud supérieur de l'arbre abstrait
			 * cette methode genere le code assembleur pour "supérieur"
			 * */
			private void GenererSuperieur(Superieur s) {//lorsque a>b donc c'est b<a
			// TODO Auto-generated method stub
			genererExpression(s.getFilsGauche());
			genererExpression(s.getFilsDroit());
			asmCode.append("\tPOP(R1)\n");
			asmCode.append("\tPOP(R0)\n");
			asmCode.append("\tCMPLT(R1,R0,R2)\n");
			asmCode.append("\tPUSH(R2)\n");
			
		}
			
			/**@param i: noeud inférieur de l'arbre abstrait 
			 * cette methode genere le code assembleur pour l'inferieur
			 * 
			 * */
			private void GenererInferieur(Inferieur i) {//lorsque a<b , à revoir 
			// TODO Auto-generated method stub
			genererExpression(i.getFilsGauche());
			genererExpression(i.getFilsDroit());
			asmCode.append("\tPOP(R1)\n");
			asmCode.append("\tPOP(R0)\n");
			asmCode.append("\tCMPLT(R0,R1,R2)\n");
			asmCode.append("\tPUSH(R2)\n");
			
		}
			/**@param tq: noeud tant que de l'arbre abstrait 
			 * cette methode genere le code assembleur pour le tant que
			 * */
			private void GenererTantQue(TantQue tq) {
			// TODO Auto-generated method stub
			//on l'a pas encore fait
				String []nom=tq.getLabel().split("/");
				asmCode.append("\ntantque_"+nom[1]+":\n");
				genererCondition(tq.getFils().get(0));
				asmCode.append("\tPOP(R0)\n");
				asmCode.append("\tBF(R2,fin_tantque"+nom[1]+")\n");
				GenererBloc((Bloc)tq.getFils().get(1));
				asmCode.append("\tBR(tantque_"+nom[1]+")\n");
				asmCode.append("\nfin_tantque"+nom[1]+":\n");
				
				
				
		}
			/**@param s: noeud si de l'arbre abstrait
			 * cette methode genere le code assembleur pour "si"
			 * */
			private void GenererSi(Si s) {
			// TODO Auto-generated method stub
				String []nom=s.getLabel().split("/");
				asmCode.append("\nsi_"+nom[1]+":\n");
				genererCondition(s.getCondition());
				asmCode.append("\tPOP(R0)\n");
				asmCode.append("\tBF(R2,sinon_"+nom[1]+")\n");
				asmCode.append("\nalors_"+nom[1]+":\n");
				GenererBloc(s.getBlocAlors());
				asmCode.append("\tBR(fsi_"+nom[1]+")\n");
				asmCode.append("\nsinon_"+nom[1]+":\n");
				GenererBloc(s.getBlocSinon());
				asmCode.append("\nfsi_"+nom[1]+":\n");
				
		}
			/**@param n: noeud de l'arbre abstrait
			 * cette methode genere une condition en fonction de type de noeud
			 * */
			private void genererCondition(Noeud n) {
				// TODO Auto-generated method stub
				if(n instanceof Inferieur) {
					GenererInferieur((Inferieur)n);
				}else if(n instanceof Superieur) {
					GenererSuperieur((Superieur)n);
				}else if(n instanceof Egal) {
					GenererEgal((Egal)n);
				}else if(n instanceof InferieurEgal) {
					GenererInferieurEgal((InferieurEgal)n);
				}else if(n instanceof SuperieurEgal) {
					GenererSuperieurEgal((SuperieurEgal)n);
				}else if(n instanceof Different) {
					GenererDifferent((Different)n);
				}
			}
			/**@param b: bloc de l'arbre abstrait
			 * cette methode genere le code assembleur de l'expression du bloc
			 * */
			private void GenererBloc(Bloc b) {
			// TODO Auto-generated method stub
				for(Noeud n:b.getFils()) {
					GenererInstruction(n);
				}
				 
		}


			/**@param d: noeud division de l'arbre abstrait
			 * cette methode genere le code assembleur pour "division"
			 * */
		private void GenererDivision(Division d) {
			// TODO Auto-generated method stub
			genererExpression(d.getFilsGauche());
			genererExpression(d.getFilsDroit());
			asmCode.append("\tPOP(R2)\n");
			asmCode.append("\tPOP(R1)\n");
			asmCode.append("\tDiv(R1,R2,R0)\n");
			asmCode.append("\tPUSH(R0)\n");
			
		}
		/**@param mu: noeud multiplication de l'arbre abstrait
		 * cette methode genere le code assembleur pour la multiplication 
		 * */
		private void GenererMultiplication(Multiplication mu) {
			// TODO Auto-generated method stub
			genererExpression(mu.getFilsGauche());
			genererExpression(mu.getFilsDroit());
			asmCode.append("\tPOP(R2)\n");
			asmCode.append("\tPOP(R1)\n");
			asmCode.append("\tMUL(R1,R2,R0)\n");
			asmCode.append("\tPUSH(R0)\n");
			
			
		}
		/**@param m: noeud soustraction  de l'arbre abstrait
		 * cette methode genere le code assembleur pour la soustraction 
		 * */
		private void GenererSoustraction(Moins m) {
			// TODO Auto-generated method stub
			genererExpression(m.getFilsGauche());
			genererExpression(m.getFilsDroit());
			asmCode.append("\tPOP(R2)\n");
			asmCode.append("\tPOP(R1)\n");
			asmCode.append("\tSUB(R1,R2,R0)");
			asmCode.append("\tPUSH(R0)\n\n");
			
		}
		/**@param p: noeud addition de l'arbre abstrait
		 * cette methode genere le code assembleur pour l'addition 
		 * */
		private void GenererAddition(Plus p) {
			// TODO Auto-generated method stub
			
			
			genererExpression(p.getFilsGauche());
			genererExpression(p.getFilsDroit());
			asmCode.append("\tPOP(R2)\n");
			asmCode.append("\tPOP(R1)\n");
			asmCode.append("\tADD(R1,R2,R0)\n");
			asmCode.append("\tPUSH(R0)\n\n");
			
			}
			
			
			
		



		/**@param idf: idf de l'arbre abstrait
		 * cette methode genere le code assembleur de l'idf
		 * */
		private void GenererIdf(Idf idf) {
			// TODO Auto-generated method stub
			
			
			String[]nom=idf.getLabel().split("/");	
			Symbole item=this.tableDesSymboles.getSymbole(nom[1]);
			
				if(item.getCategory().equals("variable locale") && item.getScope().equals("f")) {
			        // Pour les variables locales, calcul et génération du code pour l'offset.
			      int  offset1 = 4*(1+item.getRank());
			      //rajouter une condition pour que si j'ai pas PUTFRAME j'utilise GETFRAME
			      	asmCode.append("\t|variable locale\n");
			        asmCode.append("\tGETFRAME( ").append(offset1).append(",R0)\n");//je suis pas sur si PUTFRAME
			        asmCode.append("\tPUSH (R0)\n\n");
			      
			    } else if (item.getCategory().equals("parametre") && item.getScope().equals("f")){
			     
			    	
			       int offset2 = 4*(-1 -item.getNbParams()+item.getRank());
			       	asmCode.append("\t|parametre\n");
			        asmCode.append("\tGETFRAME( ").append(offset2).append(", R0)\n\n");//pareil, on uilise PUTFRAME? 
			        asmCode.append("\tPUSH(R0)\n");
			       
			    }else if(item.getCategory().equals("variable globale")) {
			    	 // Cas par défaut pour les identifiants qui ne sont ni locaux ni des paramètres.
			    	//je suis pas sur si ca sera  vrm globale
			    		asmCode.append("\t|declaration d'une variable globale\n");
			            asmCode.append("\tLD("+nom[1]+", R0)\n");
			            asmCode.append("\tPUSH( R0)\n\n");
			    }
			    	
			    }
			    	
			    
			       
		
		


		/**@param c: constante de l'arbre abstraite
		 * cette methdoe genere le code assembleur de "constante"
		 * */
		private void GenererConst(Const c) {
			// TODO Auto-generated method stub
			asmCode.append("\tCMOVE("+c.getValeur()+",R0)\n");
			asmCode.append("\tPUSH(R0)\n");
			
		}


		

		{
			
		}
		//affiche le code assembleur 
		public void afficherCodeAssembleur() {
		    System.out.println(asmCode.toString());
		}

	 
	 
	 
	 
}
