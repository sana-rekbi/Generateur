package fr.ul.miage.TDS;

import java.util.Collection;
import java.util.HashMap;

public class TDS {
	   private HashMap<String, Symbole> TableDesSymboles;
	   
	   //constructeur de Table des symboles
	public TDS() {
	
		this.TableDesSymboles = new HashMap<String, Symbole>();;
	}
	/**@param name: nom de symbole
	 * @param type: type de symbole 
	 * @param value: valeur de symbole 
	 * pour ajouter une variable globale:
	 * return void 
	 * */
	public void ajouterVariableGlobale(String name, String type, int value) {
	TableDesSymboles.put(name, new Symbole(name, type, "variable globale", value));
	}
	/**@param name: nom de symbole
	 * @param type: type de symbole 
	 *@param scope: scope de la symbole
	 *@param rank: rank dy symbole
	 *eturn void 
	 * pour ajouter une variable locale
	 * */
	public void ajouterVariableLocale(String name, String type, String scope, int rank) {
		TableDesSymboles.put(name, new Symbole(name, type,scope, "variable locale" , rank));
	}
	/**@param name: nom de symbole
	 * @param type: type de symbole 
	 *@param scope: scope de la symbole
	 *@param rank: rank dy symbole
	 *return void 
	 *pour ajouter un parametre
	 */
	
	public void ajouterParam(String name, String type, String scope, int rank) {
		TableDesSymboles.put(name, new Symbole(name, type,scope, "parametre" , rank));
	}
	/**@param name: nom de symbole
	 * @param type: type de symbole 
	 *@param nbParam: nombre de parametres
	 *@param nbVarLoc : nombre de variables locales
	 *return void 
	 *pour ajouter une fonction
	 */
	
	
	public void ajouterFonction(String name, String type, int nbParam, int nbVarLoc) {
		TableDesSymboles.put(name, new Symbole(name, type, "fonction" , nbParam,nbVarLoc));
	}
	
	/**@param name: nom de symbole
	 * @param scope: scope de symbole
	 * return symbole
	 * return null si le symbole n'existe pas 
	 * */
	
	public Symbole getSymbole1(String name, String scope) {
		for(String clef: this.TableDesSymboles.keySet()) {
			Symbole symbole=this.TableDesSymboles.get(clef);
			if(symbole.getScope()!=null) {
				if(symbole.getName().equals(name)&& symbole.getScope().equals(scope)) {
					return symbole;
				}
				
			}
		}
		return null;
	}
	
	
	/**@param name: nom de symbole
	 * @param category: category de symbole
	 * @param scope: scope de symbole
	 * return symbole
	 * return null si le symbole n'existe pas 
	 * */
	public Symbole getSymbole2(String name, String category, String scope) {
		for(String clef: this.TableDesSymboles.keySet()) {
			Symbole symbole=this.TableDesSymboles.get(clef);
			if(symbole.getName().equals(name)&&symbole.getCategory().equals(category)&& symbole.getScope().equals(scope)) {
				return symbole;
			}
			
		}
		return null;
	}

	/**@param name: nom de symbole
	 * @param category : category de symbole
	 * return symbole
	 * return null si le symbole n'existe pas 
	 * */
	public Symbole getSymbole3(String name,  String category ) {
		for(String clef: this.TableDesSymboles.keySet()) {
			Symbole symbole=this.TableDesSymboles.get(clef);
			if(symbole.getName().equals(name)&&symbole.getCategory().equals(category)) {
				return symbole;
			}
		}
		return null;
	}
	  
	   


	/**
	 * return une list(collection des symboles)
	 * */
public Collection<Symbole>getSymbole(){
	return this.TableDesSymboles.values();
	
}
/**
 * return void 
 * Affichage de la table des symboles
 * */

public void afficher() {
	for(String name: this.TableDesSymboles.keySet()) {
		Symbole symbole=this.TableDesSymboles.get(name);
		if(symbole.getCategory().equals("fonction")) {
			System.out.println(afficherfonction(symbole));
			
		}
		else {
			System.out.println(afficherVariable(symbole));
		}
	}
}
/**@param symbole: symbole
 * return string
 * afficher la table de symboles pour les variables et parametres
 * */
public String afficherVariable(Symbole symbole) {
	// TODO Auto-generated method stub
if(symbole.getValue()==0&& symbole.getScope()=="f") {
	return "nom:"+symbole.getName()+";type:"+symbole.getType()+"; categorie:"+symbole.getCategory()+";rang: "+symbole.getRank()+";scope:"+symbole.getScope()+";";
	
	
}else if(symbole.getValue() !=0&& symbole.getScope()=="globale") {
	return "nom:"+symbole.getName()+"; type:"+symbole.getType()+";categorie:"+symbole.getCategory()+";valeur:"+symbole.getValue()+";";
	
}else {
	//variable globale
	return "nom:"+symbole.name+"; type: "+symbole.getType()+";categorie:"+symbole.getCategory()+";valeur:"+symbole.getValue()+";";
}
}
/**@param symbole: symbole
 * return string
 * affichage de table des symboles pour une fonction
 * */

public String afficherfonction(Symbole symbole) {
	// TODO Auto-generated method stub
	if(symbole.getName()=="main") {
		return "nom: "+symbole.getName()+"; type:"+symbole.getType()+"; categorie:"+symbole.getCategory()+";";
		
	}
	return "nom:"+symbole.getName()+"; type:"+symbole.getType()+";categorie: "+symbole.getCategory()+"; nbParametres: "+symbole.getNbParams()+"; nb_variables_locales: "+symbole.getNbLocal()+";";
}
/**@param symbole: symbole
 * return void 
 * supprimer un symbole
 * */
//suprrimer des symboles
public void removeSymbole(String symboleName) {
	//on obtient d'abord le nom de symbole pour obtenir son categorie
	Symbole symbole=TableDesSymboles.get(symboleName);
	if("fonction".equals(symbole.getCategory())) {
		TableDesSymboles.entrySet().removeIf(e->"locale".equals(e.getValue().getScope())&&symboleName.equals(e.getValue().getName())||"parametre".equals(e.getValue().getCategory())&& symboleName.equals(e.getValue().getName()));
	}
	//supprimer le symbole 
	TableDesSymboles.remove(symboleName);
	
}
/**@param name: nom de symbole 
 * return symbole
 * return null si le symbole n'existe pas 
 * recuperer un symbole à l'aide de son nom 
 * */
public Symbole getSymbole(String nom) {
	// TODO Auto-generated method stub
	for (String clef : this.TableDesSymboles.keySet()) {
        Symbole symbole = this.TableDesSymboles.get(clef);
        if(symbole.getName().equals(nom)){
            return symbole;
        }
    }
    return null;


}
}
