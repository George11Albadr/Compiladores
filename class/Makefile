# Compila el archivo Parser y Scanner
all: clean parser scanner compile

# Limpia archivos generados previamente
clean:
	rm -f compiler/parser/Parser.java compiler/parser/sym.java compiler/scanner/Scanner.java output.txt

# Genera Parser.java y sym.java
parser:
	java -jar compiler/lib/java-cup-11b.jar -parser Parser -symbols sym -destdir compiler/parser compiler/parser/Parser.cup

# Genera Scanner.java
scanner:
	java -jar /Users/georgealbadr/Documents/GitHub/Compiladores/class/compiler/lib/jflex-1.9.1/lib/jflex-full-1.9.1.jar -d compiler/scanner compiler/scanner/Scanner.flex

# Compila todos los archivos Java
compile:
	javac -cp compiler/lib/java-cup-11b.jar:. compiler/scanner/Scanner.java compiler/parser/Parser.java compiler/parser/sym.java compiler/Compiler.java

# Ejecuta el análisis de escaneo del archivo input.txt y guarda el resultado en output.txt
run-scan:
	java -cp compiler/lib/java-cup-11b.jar:. compiler.Compiler -target scan input.txt -o output.txt

# Ejecuta el análisis de parsing del archivo input.txt y guarda el resultado en output.txt
run-parse:
	java -cp compiler/lib/java-cup-11b.jar:. compiler.Compiler -target parse input.txt -o output.txt

# Ejecuta todo el proceso (limpieza, generación y análisis)
execute-scan: all run-scan

execute-parse: all run-parse