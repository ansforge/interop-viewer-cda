1. Introduzione:

Poiché per motivi di sicurezza è sempre meno possibile visualizzare i documenti CDA direttamente tramite browser Internet, ANS ha sviluppato un CDA Viewer.

Il visualizzatore ANS CDA consente:

- Per visualizzare un documento CDA con un foglio di stile (è necessario selezionare il documento CDA da un lato e il foglio di stile dall'altro).
- Visualizzare un documento CDA in un browser Internet
- Per generare un documento PDF dal documento CDA: se il documento CDA contiene una copia PDF nella sezione specifica FR-Documento-PDF-copia, questa non verrà presa in considerazione.
- Aprire un documento CDA in un editor (che consente di visualizzare l'albero CDA)
- Aprire un documento CDA nell'editor di file XML predefinito sulla workstation (ad esempio Oxygen)

2. Utilizzo di ANS CDA Viewer:

Per aprire il visualizzatore CDA, fare doppio clic su ANS_Viewer-CDA_2023.01.jar.

È necessario depositare i documenti CDA da visualizzare nella directory ExemplesCDA di testContenuCDA (testContenuCDA può essere scaricato dal collegamento https://github.com/ansforge/TestContenuCDA-3-0).

Devi quindi:

- Seleziona il documento CDA (nella directory Esempi CDA del test CDA Content)
- Seleziona il foglio di stile (nella directory TestCDAContentStyleSheet)
- È possibile utilizzare il foglio di stile ANS CDA-FO.xsl
- Puoi utilizzare un foglio di stile personalizzato (in questo caso, devi rilasciarlo nella directory TestCDAContentStyleSheet).
- Se il documento CDA selezionato è un CR-Bio autopresentabile, potrai visualizzarlo con il foglio di stile cda_CRBIO.xsl presente nella directory FeuilleDeStyle

3. Prerequisiti

- Java deve essere installato sul computer dell'utente.

- Devi avere testContentCDA sul tuo computer utente.

- Possibile problema di avvio relativo a Java

Se hai l'"eccezione JVM Launcher", devi aggiornare la tua versione Java.

Per fare ciò, installa Java versione 20 (o successiva) facendo clic sul collegamento https://download.oracle.com/java/20/archive/jdk-20.0.2_windows-x64_bin.exe

Dopo aver installato Java, apri il prompt dei comandi (CMD) e digita "java -version" per assicurarti che sia installata la nuova versione di Java.

È quindi possibile riavviare il visualizzatore CDA.