# Directorios
SRC_DIR=compiler
SCANNER_DIR=$(SRC_DIR)/scanner
PARSER_DIR=$(SRC_DIR)/parser
LIB_DIR=$(SRC_DIR)/lib

# Archivos importantes
FLEX=$(SCANNER_DIR)/Scanner.flex
PARSER=$(PARSER_DIR)/Parser.cup
JAR_CUP=$(LIB_DIR)/java-cup-11b.jar
JAR_JFLEX=$(LIB_DIR)/jflex-full-1.8.2.jar

# Comando de compilación
JC=javac
JFLAGS=-cp $(JAR_CUP):.

# Targets del proyecto
all: parser scanner compile

# Generar el parser con CUP
parser:
	java -jar $(JAR_CUP) -parser Parser -symbols sym -destdir $(PARSER_DIR) $(PARSER)

# Generar el escáner con JFlex
scanner:
	java -jar $(JAR_JFLEX) -d $(SCANNER_DIR) $(FLEX)

# Compilar todos los archivos Java
compile:
	$(JC) $(JFLAGS) $(SCANNER_DIR)/Scanner.java $(PARSER_DIR)/Parser.java $(PARSER_DIR)/sym.java $(SRC_DIR)/Compiler.java

# Limpiar archivos generados
clean:
	rm -f $(SCANNER_DIR)/Scanner.java $(PARSER_DIR)/Parser.java $(PARSER_DIR)/sym.java $(SRC_DIR)/*.class