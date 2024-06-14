/*
+ *  License and Copyright:
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

import java.util.ArrayList;

/**
 * Description :
 * @author Azim Roussanaly
 * Created at 28 févr. 2019
 */
public class TantQue extends NoeudInt {
	static int compteur;
	
	public TantQue() {
		this(compteur);
		compteur++;
	}
	//constructeur
	public TantQue(int valeur) {
		setValeur(valeur);
		setCat(Categories.TQ);
		setFils(new ArrayList<Noeud>(2));
		this.getFils().add(0, null);// 0 = condition
		this.getFils().add(1, new Bloc());// 1 = bloc (vide par défaut)
	}
	//methodes
	/**
	 * @return la condition
	 */
	public Noeud getCondition() {
		return getFils().get(0);
	}
	/**
	 * @return le bloc 
	 */
	public Bloc getBloc() {
		return (Bloc) getFils().get(1);
	}
	/**
	 * Ajoute une condition
	 */
	public void setCondition(Noeud n) {
		getFils().set(0, n);
	}
	/**
	 * Ajoute un bloc
	 */
	public void setBloc(Bloc n) {
		getFils().set(1, n);
	}
}
