1. Einleitung:

Da die direkte Betrachtung von CDA-Dokumenten über Internetbrowser aus Sicherheitsgründen immer weniger möglich ist, hat die ANS einen CDA-Viewer entwickelt.

Der ANS CDA Viewer ermöglicht:

- Um ein CDA-Dokument mit einem Stylesheet anzuzeigen (Sie müssen einerseits das CDA-Dokument und andererseits das Stylesheet auswählen).
- Zeigen Sie ein CDA-Dokument in einem Internetbrowser an
- Um ein PDF-Dokument aus dem CDA-Dokument zu generieren: Wenn das CDA-Dokument eine PDF-Kopie im spezifischen Abschnitt FR-Dokument-PDF-Kopie enthält, wird diese nicht berücksichtigt.
- Öffnen Sie ein CDA-Dokument in einem Editor (der Ihnen die Anzeige des CDA-Baums ermöglicht).
- Öffnen Sie ein CDA-Dokument im Standard-XML-Dateieditor auf der Workstation (z. B. Oxygen).

2. Verwendung des ANS CDA Viewers:

Um den CDA Viewer zu öffnen, doppelklicken Sie auf ANS_Viewer-CDA_2023.01.jar.

Sie müssen die anzuzeigenden CDA-Dokumente im ExemplesCDA von testContenuCDA hinterlegen (testContenuCDA kann über den Link https://github.com/ansforge/TestContenuCDA-3-0 heruntergeladen werden).

Sie müssen dann:

- Wählen Sie das CDA-Dokument aus (im CDA-Beispielverzeichnis des CDA-Inhaltstests).
- Wählen Sie das Stylesheet aus (im Verzeichnis TestCDAContentStyleSheet)
- Sie können das Stylesheet ANS CDA-FO.xsl verwenden
- Sie können ein benutzerdefiniertes Stylesheet verwenden (in diesem Fall müssen Sie es im Verzeichnis TestCDAContentStyleSheet ablegen).
- Wenn es sich bei dem ausgewählten CDA-Dokument um eine selbstpräsentierbare CR-Bio handelt, können Sie es mit dem Stylesheet cda_CRBIO.xsl im FeuilleDeStyle-Verzeichnis anzeigen

3. Voraussetzungen

- Java muss auf Ihrem Benutzercomputer installiert sein.

- Sie müssen testContentCDA auf Ihrem Benutzercomputer haben.

- Mögliches Startproblem im Zusammenhang mit Java

Wenn bei Ihnen die „JVM Launcher-Ausnahme“ auftritt, müssen Sie Ihre Java-Version aktualisieren.

Installieren Sie dazu Java Version 20 (oder höher), indem Sie auf den Link https://download.oracle.com/java/20/archive/jdk-20.0.2_windows-x64_bin.exe klicken

Öffnen Sie nach der Installation von Java die Eingabeaufforderung (CMD) und geben Sie „java -version“ ein, um sicherzustellen, dass die neue Version von Java installiert ist.

Anschließend können Sie den CDA Viewer neu starten.