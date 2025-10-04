# Variaveis para a sua estrutura de diretorios
SRC_DIR = src
CONTROLLER_DIR = controller
VIEW_DIR = interfaceView
RES_DIR = resources
OUT_DIR = out

# Variaveis para os arquivos e classes
JAVAC = javac
JAVA = java
MAIN_CLASS = Principal
JAR_NAME = termo.jar

# Define as classes a serem compiladas por diretorio
SRC_CLASSES = $(wildcard $(SRC_DIR)/*.java)
CONTROLLER_CLASSES = $(wildcard $(CONTROLLER_DIR)/*.java)
VIEW_CLASSES = $(wildcard $(VIEW_DIR)/*.java)

# Alvo default: compilar
all: compile

# Alvo para compilar todo o projeto
compile:
	@echo "Compilando os arquivos..."
	@mkdir -p $(OUT_DIR)
	# O "-d ." compila tudo no diretorio atual. Isso e necessario pois nao ha pacotes.
	$(JAVAC) -d $(OUT_DIR) $(SRC_CLASSES) $(CONTROLLER_CLASSES) $(VIEW_CLASSES)
	@echo "Compilacao concluida. Arquivos .class criados na pasta $(OUT_DIR)"

# Alvo para executar o programa
run: compile
	@echo "Executando o programa..."
	# O "-cp" deve conter todos os diretorios que contem classes (.class)
	$(JAVA) -cp $(OUT_DIR):$(OUT_DIR)/controller:$(OUT_DIR)/interfaceView $(MAIN_CLASS) $(RES_DIR)/palavras.txt

# Alvo para criar um arquivo executavel .jar
jar: compile
	@echo "Criando o arquivo JAR..."
	cd $(OUT_DIR) && jar cfe ../$(JAR_NAME) $(MAIN_CLASS) .
	@echo "Arquivo $(JAR_NAME) criado com sucesso."

# Alvo para limpar os arquivos compilados e o JAR
clean:
	@echo "Removendo arquivos compilados e JAR..."
	rm -rf $(OUT_DIR)
	rm -f $(JAR_NAME)
	@echo "Limpeza concluida."

.PHONY: all compile run jar clean