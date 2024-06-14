/*
- *  License and Copyright:
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

import java.util.List;
import java.util.logging.Logger;

/**
 * Description : Classe qui représente à la fois un noeud et un arbre
 * Un attribut commun à toutes les noeuds est la catégorie (cat) 
 * Noeud est une classe abstraite (pas de constructeur)
 * @author Azim Roussanaly
 * Created at 26 févr. 2019
 */
public abstract class Noeud {
	private static final Logger LOG = Logger.getLogger(Noeud.class.getName());
	//les catégories de noeuds possibles
	public static enum Categories {
			PROG, FONCTION, BLOC,
			AFF, SI,  TQ, ECR, RET,
			PLUS, MOINS, DIV, MUL,
			SUP, INF, SUPE, INFE, EG, DIF,
			IDF, CONST, LIRE, APPEL
	}
	//attributs
	private Categories cat;
	private List<Noeud> fils;
	
	//abstract
	abstract public String  getLabel();
	
	//methodes
	/**
	 * ajouter un fils à un noeud
	 * @param f
	 * @return booléen
	 */
	public boolean ajouterUnFils(Noeud f) {
		return getFils().add(f);
	}
	/**
	 * Ajouter une liste de fils
	 * @param l
	 * @return nombre de fils ajoutés
	 */
	public int ajouterDesFils(List<Noeud> l) {
		int res = 0;
		for (Noeud f : l) {
			if (ajouterUnFils(f)) {
				res++;
			}
		}
		return res;
	}
	/**
	 * tester si c'est une feuille
	 * @return
	 */
	public boolean estFeuille() {
		if ((fils == null) || (fils.size() == 0)){
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return getLabel();
	}
	
	
	//setters & getters
	public Categories getCat() {
		return cat;
	}
	public void setCat(Categories cat) {
		this.cat = cat;
	}
	public List<Noeud> getFils() {
		return fils;
	}
	public void setFils(List<Noeud> fils) {
		this.fils = fils;
	}

	

	

}
