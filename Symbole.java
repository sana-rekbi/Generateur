package fr.ul.miage.TDS;

public class Symbole {
	 public String name;
	    public String type;//Type:int, String
	    public int value;//valeur du symbole
	    public String category;//variable, constante, fonction, parametre
	    public String scope;//globale, locale à une fonction
	    public int nbParams;//Nombre du parametre pour les fonction
	    public int nbLocal;//nombre de variables locales
	    public int rank;//Rang du parametre ou de la variable locale sur la ligne
		//Constructeur pour la fonction:
	    public Symbole(String name, String type, String category, int nbParams, int nbLocal) {
	    	this.name=name;
	    	this.type=type;
	    	this.category=category;
	    	this.nbParams=nbParams;
	    	this.nbLocal=nbLocal;
	    	
	    }
	    //Constructeur pour les parametres, variables locales
	    public Symbole(String name, String type, String scope, String category, int rank) {
	    	this.name=name;
	    	this.type=type;
	    	this.scope=scope;
	    	this.category=category;
	    	this.rank=rank;
	    	
	    }
	    //Constructeur pour les variables globales:
	    public Symbole(String name, String type, String category, int value) {
	    	this.name=name;
	    	this.type=type;
	    	this.category=category;
	    	this.value=value;
	    }
	    
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getScope() {
			return scope;
		}
		public void setScope(String scope) {
			this.scope = scope;
		}
		public int getNbParams() {
			return nbParams;
		}
		public void setNbParams(int numParams) {
			this.nbParams = numParams;
		}
		public int getNbLocal() {
			return nbLocal;
		}
		public void setNbLocal(int nbLocal) {
			this.nbLocal = nbLocal;
		}
		public int getRank() {
			return rank;
		}
		public void setRank(int rank) {
			this.rank = rank;
		}
	    
}
