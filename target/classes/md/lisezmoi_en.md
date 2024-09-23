1. Introduction:

Since viewing CDA documents directly via internet browsers is less and less possible for security reasons, the ANS has developed a CDA Viewer.

The ANS CDA Viewer allows:

- To view a CDA document with a style sheet (you must select the CDA document on the one hand and the style sheet on the other hand).
- View a CDA document in an internet browser
- To generate a PDF document from the CDA document: if the CDA document contains a PDF copy in the specific FR-Document-PDF-copy section, this will not be taken into account.
- Open a CDA document in an editor (which allows you to view the CDA tree)
- Open a CDA document in the default XML file editor on the workstation (Oxygen for example)

2. Use of the ANS CDA Viewer:

To open the CDA Viewer, double click on ANS_Viewer-CDA_2023.01.jar.

You must deposit the CDA documents to be viewed in the ExemplesCDA directory of testContenuCDA (testContenuCDA can be downloaded from the link https://github.com/ansforge/TestContenuCDA-3-0).

You must then:

- Select the CDA document (in the CDA Examples directory of CDA Content test)
- Select the style sheet (in the TestCDAContentStyleSheet directory)
- You can use the ANS CDA-FO.xsl style sheet
- You can use a custom style sheet (in this case, you must drop it in the TestCDAContentStyleSheet directory).
- If the selected CDA document is a self-presentable CR-Bio, you will be able to display it with the cda_CRBIO.xsl style sheet present in the FeuilleDeStyle directory

3. Prerequisites

- Java must be installed on your user computer.

- You must have testContentCDA on your user computer.

- Possible launch problem related to Java

If you have the "JVM Launcher exception", you need to update your Java version.

To do this, install Java version 20 (or later) by clicking on the link https://download.oracle.com/java/20/archive/jdk-20.0.2_windows-x64_bin.exe

After installing Java, open Command Prompt (CMD) and type "java -version" to ensure that the new version of Java is installed.

You can then restart the CDA Viewer.
