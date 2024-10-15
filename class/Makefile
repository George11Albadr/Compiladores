# Compila el archivo Parser y Scanner
all: clean parser scanner compile
every: clean scanner parser compile-scan 
every2: clean scanner parser compile-parse
allscan: clean scanner parser compile-scan compile-parse run-scan
allparse: clean scanner parser compile-scan compile-parse run-parse

# Limpia archivos generados previamente
clean:
	rm -f compiler/parser/Parser.java compiler/parser/sym.java compiler/scanner/Scanner.java \
	      compiler/parser/sym.class \
	      compiler/parser/Parser\$$CUP\$$Parser\$$actions.class \
	      compiler/parser/Parser.class compiler/scanner/Scanner.class \
	      compiler/Compiler.class output_scan.txt output_parse.txt

# Genera Parser.java y sym.java
parser:
	java -jar compiler/lib/java-cup-11b.jar -parser Parser -symbols sym -destdir compiler/parser compiler/parser/Parser.cup

# Genera Scanner.java
scanner:
	java -jar compiler/lib/jflex-full-1.9.1.jar -d compiler/scanner/ compiler/scanner/Scanner.flex


compile-scan:
	javac -cp compiler/lib/java-cup-11b.jar:. compiler/scanner/Scanner.java compiler/Compiler.java

compile-parse:
	javac -cp compiler/lib/java-cup-11b.jar:. compiler/scanner/Scanner.java compiler/parser/Parser.java compiler/parser/sym.java compiler/Compiler.java


# Compila todos los archivos Java
compile:
	javac -cp compiler/lib/java-cup-11b.jar:. compiler/scanner/Scanner.java compiler/parser/Parser.java compiler/parser/sym.java compiler/Compiler.java

# Ejecuta el análisis de escaneo del archivo input.txt y guarda el resultado en output.txt
run-scan:
	java -cp compiler/lib/java-cup-11b.jar:. compiler.Compiler -target scan input.txt -o output_scan.txt

run-scanb:
	java -cp compiler/lib/java-cup-11b.jar:. compiler.Compiler -target scan input_copy.txt -o output_scan.txt

# Ejecuta el análisis de parsing del archivo input.txt y guarda el resultado en output.txt
run-parse:
	java -cp compiler/lib/java-cup-11b.jar:. compiler.Compiler -target parse input.txt -o output_parse.txt

run-parseb:
	java -cp compiler/lib/java-cup-11b.jar:. compiler.Compiler -target parse input_copy.txt -o output_parse.txt

# Ejecuta todo el proceso (limpieza, generación y análisis)
execute-scan: all run-scan

execute-parse: all run-parse

execute-M: all run-scanb run-parseb