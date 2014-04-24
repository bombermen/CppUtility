# CppUtility

## Fonctionnalités
Cet utilitaire permet la création rapide de classes en C++.
Il génère les fichier .cpp et .hpp des classes en créant le constructeur et les attributs privés. Les attributs sont encapsulés avec les accesseurs et mutateurs adéquats.

## Utilisation
La configuration des classes à générer est effectuée dans le fichier CppUtility.java.

### Configuration du chemin des fichiers générés
Indiquer le répertoire de base du projet en modifiant la variable projectBase. Le répertoire en question doit exister pour que tout se passe bien à l'exécution.

### Configuration des classes à générer

#### Classes
Créer autant de ClassBeans que vous désirez ajouter de classes dans votre projet. La syntaxe de création est la suivante :
`ClassBeans <classInstance> = new ClassBeans("<className>");`

#### Inclusions

* Inclusions entre guillemets : `<classInstance>.addInclude("<headerName.extension>");`
* Inclusions entre chevrons : `<classInstance>.addStdInclude("<headerName.extension>");`

#### Attributs
Pour ajouter un attribut, utiliser : `<classInstance>.addAttribute("<attributeName>", "<attributeType>");`
Les types primaires reconnus sont indiqués dans la liste `AttributeBeans.primaryTypes`. Il est à noter que les accesseurs et mutateurs des attributs dont le type n'est pas primaire sont générés avec le mot clé `const`. Par exemple, la déclaration du prototype du mutateur de l'attribut vector de type Vec4f d'une classe sera défini de la manière suivante:
`void setVector(const Vec4f& vector);`

## Compilation

### NetBeans
Le projet peut être ouvert avec NetBeans et compilé par ses soins.
