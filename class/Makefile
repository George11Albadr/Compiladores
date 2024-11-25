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
IRT_CLASSES = compiler/irt/*.java
COMPILER = compiler/Compiler.java

# Archivos de salida
SCAN_OUTPUT = output_scan.txt
PARSE_OUTPUT = output_parse.txt
SEMANTIC_OUTPUT = output_semantic.txt
IRT_OUTPUT = output.irt.txt
DOT_OUTPUT = output_ast.dot
PDF_OUTPUT = output_ast.pdf
IRT_DOT_OUTPUT = output_ir.dot
IRT_PDF_OUTPUT = output_ir.pdf

# Regla por defecto: compila y ejecuta hasta la fase semántica, excluyendo IRT
all: clean scanner parser compile run-all

	@echo "Compilación y ejecución completadas hasta la fase semántica (IRT excluido)."

# Limpieza de archivos generados
clean:
	@echo "Limpiando archivos generados..."
	rm -f $(SCANNER) $(PARSER) $(SYM)
	rm -f compiler/parser/*.class compiler/scanner/*.class compiler/semantic/*.class compiler/ast/*.class compiler/irt/*.class compiler/*.class
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

# Compilación de todos los archivos Java, excluyendo IRT
compile: scanner parser
	@echo "Compilando archivos Java sin incluir IRT..."
	$(JC) $(JFLAGS) $(SCANNER) $(AST_CLASSES) $(PARSER) $(SYM) $(SEMANTIC_CLASSES) $(COMPILER)
	@echo "Compilación sin IRT completada."

# Ejecución de todas las fases de compilación hasta semántica
run-all: run-scan run-parse run-semantic

# Ejecuta el análisis de escaneo
run-scan:
	@echo "Ejecutando análisis de escaneo..."
	$(JAVA) $(JFLAGS) compiler.Compiler -target scan $(INPUT) -o $(SCAN_OUTPUT)
	@echo "Análisis de escaneo completado. Resultado guardado en $(SCAN_OUTPUT)."

# Ejecuta el análisis de parsing y genera el archivo DOT del AST
run-parse:
	@echo "Ejecutando análisis de parsing..."
	$(JAVA) $(JFLAGS) compiler.Compiler -target parse $(INPUT) -o $(PARSE_OUTPUT)
	@echo "Generando archivo DOT del AST..."
	$(JAVA) $(JFLAGS) compiler.Compiler -target dot $(INPUT) -o $(DOT_OUTPUT)
	@echo "Análisis de parsing completado. Resultados guardados en $(PARSE_OUTPUT) y $(DOT_OUTPUT)."

# Ejecuta el análisis semántico
run-semantic:
	@echo "Ejecutando análisis semántico..."
	$(JAVA) $(JFLAGS) compiler.Compiler -target semantic $(INPUT) -o $(SEMANTIC_OUTPUT)
	@echo "Análisis semántico completado. Resultado guardado en $(SEMANTIC_OUTPUT)."

# Compilación de clases del IRT
compile-IRT:
	@echo "Compilando clases IRT..."
	$(JC) $(JFLAGS) $(IRT_CLASSES)
	@echo "Clases IRT compiladas exitosamente."

# Generación del IRT
run-IRT: compile-IRT
	@echo "Generando el IRT..."
	$(JAVA) $(JFLAGS) compiler.Compiler -target irt $(INPUT) -o $(IRT_OUTPUT)
	@echo "IRT generado exitosamente en $(IRT_OUTPUT)."

# Generación de archivo DOT y PDF para IRT
generate-dot-IRT:
	@echo "Generando archivo DOT y PDF para el IRT..."
	dot -Tpdf $(IRT_DOT_OUTPUT) -o $(IRT_PDF_OUTPUT)
	@echo "Archivos $(IRT_DOT_OUTPUT) y $(IRT_PDF_OUTPUT) generados exitosamente."

# Opciones de depuración para IRT
debug-IRT:
	@echo "Generando el IRT con modo debug..."
	$(JAVA) $(JFLAGS) compiler.Compiler -target irt $(INPUT) -o $(IRT_OUTPUT) -debug
	@echo "IRT generado exitosamente en $(IRT_OUTPUT) con modo debug."

# Prueba rápida de generación del IRT
test-IRT:
	@echo "Generando el IRT (prueba rápida)..."
	$(JAVA) $(JFLAGS) compiler.Compiler -target irt $(INPUT) -o $(IRT_OUTPUT)
	@echo "IRT generado exitosamente en $(IRT_OUTPUT)."

# Genera el archivo DOT y PDF del AST
dot: run-parse
	@echo "Generando PDF desde $(DOT_OUTPUT)..."
	dot -Tpdf $(DOT_OUTPUT) -o $(PDF_OUTPUT)
	@echo "PDF generado exitosamente como $(PDF_OUTPUT)."

# Limpia y recompila todo
rebuild: clean all

# Ayuda
help:
	@echo "Comandos disponibles:"
	@echo "  make all              - Limpia, compila y ejecuta hasta la fase semántica (IRT excluido)."
	@echo "  make clean            - Limpia archivos generados."
	@echo "  make scanner          - Genera el scanner."
	@echo "  make parser           - Genera el parser y sym."
	@echo "  make compile          - Compila todos los archivos Java sin incluir IRT."
	@echo "  make run-scan         - Ejecuta el análisis de escaneo."
	@echo "  make run-parse        - Ejecuta el análisis de parsing y genera el DOT del AST."
	@echo "  make run-semantic     - Ejecuta el análisis semántico."
	@echo "  make compile-IRT      - Compila las clases IRT."
	@echo "  make run-IRT          - Genera el IRT."
	@echo "  make debug-IRT        - Genera el IRT en modo debug."
	@echo "  make test-IRT         - Prueba rápida de generación del IRT."
	@echo "  make generate-dot-IRT - Genera archivo DOT y PDF para el IRT."
	@echo "  make dot              - Genera el archivo PDF del AST."
	@echo "  make rebuild          - Limpia y recompila todo."
	@echo "  make help             - Muestra esta ayuda."