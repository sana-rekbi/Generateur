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

import java.util.ArrayList;

/**
 * Description :
 * @author Azim Roussanaly
 * Created at 28 févr. 2019
 */
public class Division extends Noeud0 {
	//constructeurs
	public Division() {
		setCat(Categories.DIV);
		setFils(new ArrayList<>());
		this.getFils().add(0, null);
		this.getFils().add(1, null);
	}
	//methodes
	/**
	 * @return le fils gauche (par convention à l'indice 0)
	 */
	public Noeud getFilsGauche() {
		return getFils().get(0);
	}
	/**
	 * @return le fils droit (par convention à l'indice 1)
	 */
	public Noeud getFilsDroit() {
		return getFils().get(1);
	}
	/**
	 * Ajoute un fils gauche
	 */
	public void setFilsGauche(Noeud n) {
		getFils().set(0, n);
	}
	/**
	 * Ajoute un fils droit
	 */
	public void setFilsDroit(Noeud n) {
		getFils().set(1, n);
	}
}
