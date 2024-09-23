1. Introducción:

Dado que ver documentos CDA directamente a través de navegadores de Internet es cada vez menos posible por razones de seguridad, ANS ha desarrollado un visor CDA.

El visor CDA de ANS permite:

- Para ver un documento CDA con hoja de estilo (debes seleccionar el documento CDA por un lado y la hoja de estilo por otro).
- Ver un documento CDA en un navegador de Internet
- Para generar un documento PDF a partir del documento CDA: si el documento CDA contiene una copia PDF en la sección específica FR-Documento-PDF-copia, esto no se tendrá en cuenta.
- Abrir un documento CDA en un editor (que le permite ver el árbol CDA)
- Abra un documento CDA en el editor de archivos XML predeterminado en la estación de trabajo (Oxygen, por ejemplo)

2. Uso del Visor CDA de ANS:

Para abrir el Visor CDA, haga doble clic en ANS_Viewer-CDA_2023.01.jar.

Debe depositar los documentos CDA para ser vistos en el directorio ExemplesCDA de testContenuCDA (testContenuCDA se puede descargar desde el enlace https://github.com/ansforge/TestContenuCDA-3-0).

Entonces debes:

- Seleccione el documento CDA (en el directorio de ejemplos CDA de la prueba de contenido CDA)
- Seleccione la hoja de estilo (en el directorio TestCDAContentStyleSheet)
- Puede utilizar la hoja de estilo ANS CDA-FO.xsl
- Puede utilizar una hoja de estilo personalizada (en este caso, debe colocarla en el directorio TestCDAContentStyleSheet).
- Si el documento CDA seleccionado es un CR-Bio autopresentable, podrá mostrarlo con la hoja de estilo cda_CRBIO.xsl presente en el directorio FeuilleDeStyle

3. Requisitos previos

- Java debe estar instalado en su computadora de usuario.

- Debe tener testContentCDA en su computadora de usuario.

- Posible problema de lanzamiento relacionado con Java.

Si tiene la "excepción de JVM Launcher", debe actualizar su versión de Java.

Para hacer esto, instale Java versión 20 (o posterior) haciendo clic en el enlace https://download.oracle.com/java/20/archive/jdk-20.0.2_windows-x64_bin.exe

Después de instalar Java, abra el símbolo del sistema (CMD) y escriba "java -version" para asegurarse de que esté instalada la nueva versión de Java.

Luego puede reiniciar el Visor CDA.