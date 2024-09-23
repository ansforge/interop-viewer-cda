1. Introdução:

Como a visualização de documentos CDA diretamente através de navegadores de internet é cada vez menos possível por questões de segurança, a ANS desenvolveu um CDA Viewer.

O Visualizador CDA da ANS permite:

- Para visualizar um documento CDA com uma folha de estilo (deve selecionar o documento CDA por um lado e a folha de estilo por outro).
- Visualize um documento CDA em um navegador de internet
- Para gerar um documento PDF a partir do documento CDA: se o documento CDA contiver uma cópia em PDF na seção específica FR-Document-PDF-copy, isso não será levado em consideração.
- Abra um documento CDA em um editor (que permite visualizar a árvore CDA)
- Abra um documento CDA no editor de arquivo XML padrão na estação de trabalho (Oxygen, por exemplo)

2. Utilização do Visualizador CDA da ANS:

Para abrir o CDA Viewer, clique duas vezes em ANS_Viewer-CDA_2023.01.jar.

Você deve depositar os documentos CDA para serem visualizados no diretório ExemplesCDA do testContenuCDA (testContenuCDA pode ser baixado no link https://github.com/ansforge/TestContenuCDA-3-0).

Você deve então:

- Selecione o documento CDA (no diretório Exemplos de CDA do teste de conteúdo CDA)
- Selecione a folha de estilo (no diretório TestCDAContentStyleSheet)
- Você pode usar a folha de estilo ANS CDA-FO.xsl
- Você pode usar uma folha de estilo personalizada (neste caso, você deve colocá-la no diretório TestCDAContentStyleSheet).
- Se o documento CDA selecionado for um CR-Bio autoapresentável, você poderá exibi-lo com a folha de estilo cda_CRBIO.xsl presente no diretório FeuilleDeStyle

3. Pré-requisitos

- O Java deve estar instalado no computador do usuário.

- Você deve ter testContentCDA no computador do usuário.

- Possível problema de inicialização relacionado ao Java

Se você tiver a "exceção do JVM Launcher", será necessário atualizar sua versão do Java.

Para isso, instale o Java versão 20 (ou posterior) clicando no link https://download.oracle.com/java/20/archive/jdk-20.0.2_windows-x64_bin.exe

Após instalar o Java, abra o Prompt de Comando (CMD) e digite “java -version” para garantir que a nova versão do Java esteja instalada.

Você pode então reiniciar o CDA Viewer.