**Introduction :**

La visualisation des documents CDA directement via les navigateurs internet étant de moins en moins possible pour des raisons de sécurité, l'ANS a développé un Viewer CDA en accès libre.

Le Viewer CDA de l'ANS permet :
-	de visualiser un document CDA avec une feuille de style (il faut sélectionner le document CDA d'une part et la feuille de style d'autre part).
-	de visualiser un document CDA dans un navigateur internet
-	de générer un document PDF à partir du document CDA : si le document CDA contient une copie PDF dans la section spécifique FR-Document-PDF-copie, celle-ci ne sera pas prise en compte.
-	d'ouvrir un document CDA dans un éditeur (qui permet notamment de visualiser l'arborescence du CDA)
-	d'ouvrir un document CDA dans l’éditeur par défaut des fichiers XML sur le poste (Oxygen par exemple)

**Prérequis :**

***Java***

Java doit être installé sur votre poste utilisateur.

Vous devez disposer de testContenuCDA sur votre poste utilisateur.

Problème possible de lancement lié à Java :

  Si vous avez l'exception "JVM Launcher exception", vous devez mettre à jour votre version de Java.

  Pour cela, installez la version 19 de Java (ou ultérieure) en cliquant sur le lien https://download.oracle.com/java/19/archive/jdk-19.0.2_windows-x64_bin.exe

  Après l’installation de Java, ouvrez l’invite de commande (CMD) et tapez "java -version" pour vous assurer que la nouvelle version de Java est installée.

  Vous pouvez alors relancer le Viewer CDA.

Pour le bon fonctionnement du Viewer CDA de l'ANS, vous devez respecter l’arborescence du dossier "testContenuCDA".

***Télécharger testContenuCDA*** 

Vous devez télécharger l'outil testContenuCDA publié sur https://github.com/ansforge/TestContenuCDA-3-0 et extraire le contenu de l'archive (.zip) sur votre poste de travail. 

***Télécharger le viewer CDA***

Vous devez télécharger le viewer CDA à partir du projet "interop-viewer-cda" sur GitHub (https://github.com/ansforge/interop-viewer-cda) puis sélectionnez la realese « ANS_Viewer-CDA_2023.01.jar » pour le télécharger.

**Utilisation du Viewer CDA de l'ANS**

Vous devrez déposer vos documents CDA à visualiser dans le répertoire ExemplesCDA de testContenuCDA. 

Pour ouvrir le Viewer CDA, double cliquez sur ANS_Viewer-CDA_2023.01.jar. 

Vous devez ensuite : 

- Choisir le document CDA (dans le répertoire ExemplesCDA de testContenuCDA) 

- Choisir la feuille de style (dans le répertoire FeuilleDeStyle de testContenuCDA) 

- Vous pouvez utiliser la feuille de style CDA-FO.xsl de l'ANS 

- Vous pouvez utiliser une feuille de style personnalisée (dans ce cas, vous devez la déposer dans le répertoire FeuilleDeStyle de testContenuCDA). 

 - Si le document CDA sélectionné est un CR-BIO, il vous sera possible de l’afficher avec la feuille de style cda_CRBIO.xsl présente dans le répertoire FeuilleDeStyle (voir paragraphe 4) qui permet de visualiser la partie narrative et la partie structuré du CR-BIO.

**Visualisation et contrôle d'un CR-BIO au format CDA R2 N3**

***Contexte***

Conformément à l'Article L 1111-15 du code de santé publique et à l'Arrêté du 26 avril 2022 fixant la liste des documents soumis à l'obligation prévue à l'article L. 1111-15 du code de la santé publique, les biologistes doivent obligatoirement et systématiquement produire un CR d'examens de biologie médicale (CR-BIO) dans un format conforme au volet définit dans le CI-SIS et : 

- le déposer dans le Dossier médical partagé du patient, 

- l'envoyer par messagerie sécurisée au médecin traitant, au médecin prescripteur s'il y a lieu, ainsi qu'à tout professionnel dont l'intervention dans la prise en charge du patient est pertinente, selon des modalités conformes aux référentiels d'interopérabilité et de sécurité mentionnés, 

- l'envoyer par messagerie sécurisée au patient. 

Le Ségur du numérique en santé pour la biologie médicale vise à favoriser le déploiement de solutions permettant de répondre à ces obligations. 

Cependant, les biologistes souhaitent pouvoir contrôler le contenu des CR-BIO au format CDA R2 N3 pour s’assurer que les données structurées produites par leur système de gestion de laboratoire (SGL) soient fidèles aux attendus de transmission de données cliniquement pertinentes et conformes aux critères qualité requis pour l’accréditation COFRAC. 

L’Agence du Numérique en Santé met à disposition des biologistes ce viewer CDA avec une feuille de style spécifique CR-BIO pour leur permettre de visualiser et comparer : 

- la partie narrative du CDA (celle qui est affichée aux utilisateurs) 

- les données structurées correspondantes (qui sont exploitables par les SI consommateurs).
 
***Contrôle d'un CR-BIO au format CDA R2 N3 non autoprésentable***

Enregistrez votre document CDA dans le répertoire ExemplesCDA de testContenuCDA. 

Pour afficher le CR-BIO dans le Viewer CDA : 

- Sélectionnez le document CDA à contrôler 

- Sélectionnez la feuille de style "cda_CRBIO.xsl".

- Cliquez sur "Afficher le document".

 Le viewer affiche la partie narrative puis les éléments de la partie structurée correspondante.

 ***Contrôle d'un CR-BIO au format CDA R2 N3 autoprésentable***

Pour un CR-BIO au format CDA R2 N3 autoprésentable, vous pouvez : 

- Afficher le document avec la feuille de style du CDA autoprésentable 

- Afficher le document avec la feuille de style "cda_CRBIO.xsl" (pour comparer partie narrative et partie structurée).

 ****Afficher le document avec la feuille de style du CDA autoprésentable****

Enregistrez votre document CDA dans le répertoire ExemplesCDA de testContenuCDA. 

Pour afficher le CR-BIO dans le Viewer CDA : 

- Sélectionnez le document CDA à contrôler 

- Sélectionnez "Autoprésentable"

- Cliquez sur "Afficher le document"  

Le viewer affiche le CDA autoprésentable.

****Afficher le document avec la feuille de style "cda_CRBIO.xsl"****

Enregistrez votre document CDA dans le répertoire ExemplesCDA de testContenuCDA. 

Pour afficher le CR-BIO dans le Viewer CDA : 

- Sélectionnez le document CDA à contrôler 

- Sélectionnez "Feuille de style : CR-BIO" 

- Cliquez sur "Afficher le document"  

 Le viewer affiche la partie narrative puis les éléments de la partie structurée correspondante.

 **Vérification de la conformité d'un document CDA**

Vous avez la possibilité de vérifier la conformité d'un document CDA, à partir du Viewer CDA. 

Pour cela, vous devez :  

- Sélectionner le document CDA 

- Cliquer sur Validateur CDA, tel qu'indiqué dans la capture d'écran 

- Cliquer sur le bouton Création des rapports 

 **Espace de test Gazelle**

L’espace de tests Gazelle, disponible 24/7 à partir du lien https://interop.esante.gouv.fr/evs/cda/validator.seam?standard=34  

permet aux éditeurs de faire vérifier des documents CDA sans données de vie réelle. 

 
 

 
