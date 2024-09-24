**Introduction :**

La visualisation des documents CDA directement via les navigateurs internet étant de moins en moins possible pour des raisons de sécurité, l'ANS a développé un Viewer CDA.

Le Viewer CDA de l'ANS permet :

- De visualiser un document CDA avec une feuille de style (il faut sélectionner le document CDA d'une part et la feuille de style d'autre part).
- De visualiser un document CDA dans un navigateur internet
- De générer un document PDF à partir du document CDA : si le document CDA contient une copie PDF dans la section spécifique FR-Document-PDF-copie, celle-ci ne sera pas prise en compte.
- D'ouvrir un document CDA dans un éditeur (qui permet notamment de visualiser l'arborescence du CDA)
- D'ouvrir un document CDA dans l’éditeur par défaut des fichiers XML sur le poste (Oxygen par exemple)

**Utilisation du Viewer CDA de l'ANS :**

Pour ouvrir le Viewer CDA, double cliquez sur ANS_Viewer-CDA_2023.01.jar.

Vous devez déposer les documents CDA à visualiser dans le répertoire ExemplesCDA de testContenuCDA (testContenuCDA est téléchargeable sur le lien https://github.com/ansforge/TestContenuCDA-3-0).

Vous devez ensuite :

- Sélectionner le document CDA (dans le répertoire ExemplesCDA de testContenuCDA)
- Sélectionner la feuille de style (dans le répertoire FeuilleDeStyle de testContenuCDA)
- Vous pouvez utiliser la feuille de style CDA-FO.xsl de l'ANS
- Vous pouvez utiliser une feuille de style personnalisée (dans ce cas, vous devez la déposer dans le répertoire FeuilleDeStyle de testContenuCDA).
- Si le document CDA sélectionné est un CR-Bio auto présentable, il vous sera possible de l’afficher avec la feuille de style cda_CRBIO.xsl présente   dans le répertoire FeuilleDeStyle

**Prérequis :**

Java doit être installé sur votre poste utilisateur. Version 19 ou ultérieure.

Vous devez disposer de testContenuCDA sur votre poste utilisateur.

Problème possible de lancement lié à Java

Si vous avez l'exception "JVM Launcher exception", vous devez mettre à jour votre version de Java.

Pour cela, installez la version 19 de Java (ou ultérieure) en cliquant sur le lien https://download.oracle.com/java/19/archive/jdk-19.0.2_windows-x64_bin.exe

Après l’installation de Java, ouvrez l’invite de commande (CMD) et tapez "java -version" pour vous assurer que la nouvelle version de Java est installée.

Vous pouvez alors relancer le Viewer CDA. 
