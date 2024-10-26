# Compila el archivo Parser y Scanner
all: clean parser scanner compile
	@echo "Regla 'all' completada."

every: clean scanner parser compile-scan 
	@echo "Regla 'every' completada."

every2: clean scanner parser compile-parse
	@echo "Regla 'every2' completada."

allscan: clean scanner parser compile-scan compile-parse run-scan
	@echo "Regla 'allscan' completada."

allparse: clean scanner parser compile-scan compile-parse run-parse
	@echo "Regla 'allparse' completada."

# Limpia archivos generados previamente
clean:
	@echo "Ejecutando limpieza completa..."
	rm -f compiler/parser/Parser.java compiler/parser/sym.java compiler/scanner/Scanner.java \
	      compiler/parser/sym.class \
	      compiler/parser/Parser\$$CUP\$$Parser\$$actions.class \
	      compiler/parser/Parser.class compiler/scanner/Scanner.class \
	      compiler/Compiler.class output_scan.txt output_parse.txt
	@echo "Limpieza completada."

clean-sus:
	@echo "Ejecutando limpieza de archivos AST..."
	rm compiler/ast/*.class
	@echo "Limpieza de archivos AST completada."

clean2:
	@echo "Ejecutando limpieza completa con archivos DOT y PDF..."
	rm -f compiler/parser/Parser.java compiler/parser/sym.java compiler/scanner/Scanner.java \
	      compiler/parser/sym.class \
	      compiler/parser/Parser\$$CUP\$$Parser\$$actions.class \
	      compiler/parser/Parser.class compiler/scanner/Scanner.class \
	      compiler/Compiler.class compiler/ast/*.class output_scan.txt output_parse.txt output_parse.pdf output_parse.dot 
	@echo "Limpieza completa con archivos DOT y PDF."

# Genera Parser.java y sym.java
parser:
	@echo "Generando Parser.java y sym.java..."
	java -jar compiler/lib/java-cup-11b.jar -parser Parser -symbols sym -destdir compiler/parser compiler/parser/Parser.cup
	@echo "Generación de Parser.java y sym.java completada."

# Genera Scanner.java
scanner:
	@echo "Generando Scanner.java..."
	java -jar compiler/lib/jflex-full-1.9.1.jar -d compiler/scanner/ compiler/scanner/Scanner.flex
	@echo "Generación de Scanner.java completada."

# Compila los archivos del paquete ast
compile-ast:
	@echo "Compilando archivos AST..."
	javac -cp compiler/lib/java-cup-11b.jar:. compiler/ast/*.java
	@echo "Compilación de archivos AST completada."

compile-scan:
	@echo "Compilando Scanner.java y Compiler.java..."
	javac -cp compiler/lib/java-cup-11b.jar:. compiler/scanner/Scanner.java compiler/Compiler.java
	@echo "Compilación de Scanner.java y Compiler.java completada."

compile-parse:
	@echo "Compilando Scanner.java, Parser.java, sym.java y Compiler.java..."
	javac -cp compiler/lib/java-cup-11b.jar:. compiler/scanner/Scanner.java compiler/parser/Parser.java compiler/parser/sym.java compiler/Compiler.java
	@echo "Compilación completada."

# Compila todos los archivos Java
compile:
	@echo "Compilando todos los archivos Java..."
	javac -cp compiler/lib/java-cup-11b.jar:. compiler/scanner/Scanner.java compiler/ast/*.java compiler/parser/Parser.java compiler/parser/sym.java compiler/Compiler.java
	@echo "Compilación de todos los archivos Java completada."

# Ejecuta el análisis de escaneo del archivo input.txt y guarda el resultado en output_scan.txt
run-scan:
	@echo "Ejecutando análisis de escaneo..."
	java -cp compiler/lib/java-cup-11b.jar:. compiler.Compiler -target scan input.txt -o output_scan.txt
	@echo "Análisis de escaneo completado. Resultado guardado en output_scan.txt."

# Ejecuta el análisis de escaneo del archivo input.txt y guarda el resultado en output_scan.txt (con AST)
run-scan-ast:
	@echo "Ejecutando análisis de escaneo con AST..."
	java -cp compiler/lib/java-cup-11b.jar:. compiler.Compiler -target scan input.txt -o output_scan.txt
	@echo "Análisis de escaneo con AST completado."

run-scanb:
	@echo "Ejecutando análisis de escaneo para input_copy.txt..."
	java -cp compiler/lib/java-cup-11b.jar:. compiler.Compiler -target scan input_copy.txt -o output_scan.txt
	@echo "Análisis de escaneo para input_copy.txt completado."

# Ejecuta el análisis de parsing del archivo input.txt y guarda el resultado en output_parse.txt
run-parse:
	@echo "Ejecutando análisis de parsing..."
	java -cp compiler/lib/java-cup-11b.jar:. compiler.Compiler -target parse input.txt -o output_parse.txt
	@echo "Análisis de parsing completado. Resultado guardado en output_parse.txt."

run-parseb:
	@echo "Ejecutando análisis de parsing para input_copy.txt..."
	java -cp compiler/lib/java-cup-11b.jar:. compiler.Compiler -target parse input_copy.txt -o output_parse.txt
	@echo "Análisis de parsing para input_copy.txt completado."

# Ejecuta todo el proceso (limpieza, generación y análisis)
exe-scan: all run-scan
	@echo "Ejecutando regla 'exe-scan' completada."

exe-parse: all run-parse
	@echo "Ejecutando regla 'exe-parse' completada."

exe-M1: all run-scan run-parse
	@echo "Ejecutando regla 'exe-M1' completada."

exe-M2: all run-scanb run-parseb
	@echo "Ejecutando regla 'exe-M2' completada."

# Regla corregida para generar el PDF desde el archivo DOT
generate-dot:
	@echo "Generando PDF desde output_parse.dot..."
	dot -Tpdf output_parse.dot -o output_parse.pdf
	@echo "PDF generado exitosamente como output_parse.pdf"

# Ejecuta todo el proceso con AST, incluyendo la fase de escaneo y generación de PDF
all-ast: clean2 scanner parser compile run-scan run-parse generate-dot
	@echo "Ejecutando regla 'all-ast' completada."

# Agregar mensajes de depuración adicionales al final
debug:
	@echo "===== DEPURACIÓN ====="
	@echo "Contenido de output_parse.dot:"
	@cat output_parse.dot
	@echo "===== FIN DE DEPURACIÓN ====="