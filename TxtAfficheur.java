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
 *  Copyright 2021 by LORIA, Université de Lorraine
 *  All right reserved 
 */
package fr.ul.miage.arbre;

/**
 * Classe qui permet d'afficher proprement un arbre
 * @author azim
 *
 */
public class TxtAfficheur {

	/**
	 * formatte le Noeud arbre
	 * @param arbre
	 * @return
	 */
	public static String formatter(Noeud arbre) {
		return formatter(arbre, 0);
	}

	/**
	 * affiche le Noeud arbre
	 * @param arbre
	 */
	public static void afficher(Noeud arbre) {
		System.out.println(formatter(arbre));
	}
	
	/**
	 * méthode récursive
	 * @param n
	 * @param niveau
	 * @return
	 */
	private static String formatter(Noeud n, int niveau) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < niveau; i++) {
			if (i == (niveau - 1)) {
				buf.append("└─");				
			}else {
				buf.append("  ");				
			}
		}
		buf.append(n.getLabel());
		buf.append("\n");
		if (!n.estFeuille()) {
			for (Noeud x : n.getFils()) {
				buf.append(formatter(x, niveau + 1));
			}
		}
		return buf.toString();
	}

}
