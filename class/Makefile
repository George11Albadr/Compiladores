# Variables para simplificar rutas y opciones
JC = javac
JFLAGS = -cp compiler/lib/java-cup-11b.jar:.
JAVA = java
JAVACUP = java -jar compiler/lib/java-cup-11b.jar
JFLEX = java -jar compiler/lib/jflex-full-1.9.1.jar
INPUT = input.txt

# Archivos generados
SCANNER = compiler/scanner/Scanner.java
PARSER = compiler/parser/Parser.java
SYM = compiler/parser/sym.java
AST_CLASSES = compiler/ast/*.java
SEMANTIC_CLASSES = compiler/semantic/*.java
COMPILER = compiler/Compiler.java

# Archivos de salida
SCAN_OUTPUT = output_scan.txt
PARSE_OUTPUT = output_parse.txt
SEMANTIC_OUTPUT = output_semantic.txt

# Regla por defecto
all: clean scanner parser compile run-all
	@echo "Compilación y ejecución completadas."

# Limpieza de archivos generados
clean:
	@echo "Limpiando archivos generados..."
	rm -f $(SCANNER) $(PARSER) $(SYM)
	rm -f compiler/parser/*.class compiler/scanner/*.class compiler/semantic/*.class compiler/ast/*.class compiler/*.class
	rm -f output*.txt output*.dot output*.pdf
	@echo "Limpieza completada."

# Generación del scanner
scanner:
	@echo "Generando Scanner.java..."
	$(JFLEX) -d compiler/scanner/ compiler/scanner/Scanner.flex
	@echo "Generación de Scanner.java completada."

# Generación del parser y sym
parser:
	@echo "Generando Parser.java y sym.java..."
	$(JAVACUP) -parser Parser -symbols sym -destdir compiler/parser compiler/parser/Parser.cup
	@echo "Generación de Parser.java y sym.java completada."

# Compilación de todos los archivos Java
compile:
	@echo "Compilando todos los archivos Java..."
	$(JC) $(JFLAGS) $(SCANNER) $(AST_CLASSES) $(PARSER) $(SYM) $(SEMANTIC_CLASSES) $(COMPILER)
	@echo "Compilación completada."

# Ejecución de todas las fases
run-all: run-scan run-parse run-semantic

# Ejecuta el análisis de escaneo
run-scan:
	@echo "Ejecutando análisis de escaneo..."
	$(JAVA) $(JFLAGS) compiler.Compiler -target scan $(INPUT) -o $(SCAN_OUTPUT)
	@echo "Análisis de escaneo completado. Resultado guardado en $(SCAN_OUTPUT)."

# Ejecuta el análisis de parsing
run-parse:
	@echo "Ejecutando análisis de parsing..."
	$(JAVA) $(JFLAGS) compiler.Compiler -target parse $(INPUT) -o $(PARSE_OUTPUT)
	@echo "Análisis de parsing completado. Resultado guardado en $(PARSE_OUTPUT)."

# Ejecuta el análisis semántico
run-semantic:
	@echo "Ejecutando análisis semántico..."
	$(JAVA) $(JFLAGS) compiler.Compiler -target semantic $(INPUT) -o $(SEMANTIC_OUTPUT)
	@echo "Análisis semántico completado. Resultado guardado en $(SEMANTIC_OUTPUT)."

# Genera el archivo DOT y PDF del AST
dot:
	@echo "Generando archivo DOT del AST..."
	$(JAVA) $(JFLAGS) compiler.Compiler -target dot $(INPUT) -o output_ast.dot
	@echo "Archivo DOT generado como output_ast.dot."
	@echo "Generando PDF desde output_ast.dot..."
	dot -Tpdf output_ast.dot -o output_ast.pdf
	@echo "PDF generado exitosamente."

# Limpia y recompila todo
rebuild: clean all

# Ayuda
help:
	@echo "Comandos disponibles:"
	@echo "  make all          - Limpia, compila y ejecuta todas las fases."
	@echo "  make clean        - Limpia archivos generados."
	@echo "  make scanner      - Genera el scanner."
	@echo "  make parser       - Genera el parser y sym."
	@echo "  make compile      - Compila todos los archivos Java."
	@echo "  make run-scan     - Ejecuta el análisis de escaneo."
	@echo "  make run-parse    - Ejecuta el análisis de parsing."
	@echo "  make run-semantic - Ejecuta el análisis semántico."
	@echo "  make generate-dot - Genera el archivo DOT y PDF del AST."
	@echo "  make rebuild      - Limpia y recompila todo."
	@echo "  make help         - Muestra esta ayuda."