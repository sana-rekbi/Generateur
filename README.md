# Bibliothèque Java de Gestion des arbres abstraits

Ce projet est une librairie qui permet de créer et d'afficher un arbre abstrait.
Un arbre abstrait est un arbre n-aire qui represente de manière abstraite un code source.
Cette librairie est utilisée par les étudiant de Licence MIAGE à l'Université de Lorraine.

## Capture d'écran
```
% ./tester 4
SI/1
└─INF
  └─IDF/j
  └─IDF/k
└─BLOC
  └─AFF
    └─IDF/i
    └─CONST/10
└─BLOC
```

## Utilisation

Voici un exemple de code en Java qui crée un arbre abstrait et qui l'affiche à la suite

```java
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
Si si4 = new Si(1);
si4.setCondition(inf4);
si4.setBlocAlors(bloc4);
TxtAfficheur.afficher(si4);
```

##Diagramme des classes

![](arbre.png)

## Prérequis

Utiliser un IDE qui intègre Maven (par exemple Eclipse)

## Installation

1. Télécharger la distribution .zip depuis le [gitlab de l'Université de Lorraine](https://gitlab.univ-lorraine.fr/roussana5/arbre)
2. Décompresser dans un dossier
3. Importer le projet dans votre IDE (pour Eclipse : `File>Import...>Existing Maven Project...`
4. Installer dans le repository local (pour Eclipse: `Run As...>Maven Install`

## Utilisation

Dans un projet Maven, ajouter la dépendance suivante dans le fichier `pom.xml` :

```
		<dependency>
			<groupId>fr.ul.miage</groupId>
			<artifactId>arbre</artifactId>
			<version>0.0.5</version>
		</dependency>

```
##Auteur

Azim Roussanaly (IDMC/Université de Lorraine)

##Licence

Licence MIT