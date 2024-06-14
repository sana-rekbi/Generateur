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
 * Classe non publique des noeuds n'ayant pas d'attributs supplémentaires
 * Exemples : CONST, SI, TQ
 * @author Azim Roussanaly
 * Created at 28 févr. 2019
 */
class NoeudInt extends Noeud {
	//attribut
	private int valeur;
	//constructeur
	protected NoeudInt() {
		setValeur(0);
	}
	/* (non-Javadoc)
	 * @see fr.ul.miashs.api.arbre.Noeud#getLabel()
	 */
	@Override
	public String getLabel() {
		StringBuffer buf = new StringBuffer();
		buf.append(getCat());
		buf.append("/");
		buf.append(valeur);
		return buf.toString();
	}
	//setters & getters
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

}
