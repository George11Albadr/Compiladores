JFLEX_JAR = compiler/lib/jflex-full-1.9.1.jar
CUP_JAR = compiler/lib/java-cup-11b.jar
SRC_DIR = compiler
SCANNER_SRC = $(SRC_DIR)/scanner/Scanner.flex
SCANNER_OUT = $(SRC_DIR)/scanner/Lexer.java
COMPILER_SRC = $(SRC_DIR)/Compiler.java
INPUT_FILE = input.txt
OUTPUT_FILE = output.txt

all: compile run-scan

scanner:
	java -jar $(JFLEX_JAR) -d $(SRC_DIR)/scanner $(SCANNER_SRC)

compile: scanner
	javac -cp $(CUP_JAR):. $(COMPILER_SRC) $(SRC_DIR)/scanner/*.java

run-scan: compile
	java -cp $(CUP_JAR):. compiler.Compiler -target scan $(INPUT_FILE) -o $(OUTPUT_FILE)

clean:
	rm -f $(SRC_DIR)/scanner/*.class $(SRC_DIR)/*.class
	rm -f $(SCANNER_OUT) $(OUTPUT_FILE)