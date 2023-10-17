JAVAC 	= javac
JAVA 	= java
JFLAGS 	= -cp out
JCFLAGS	= -d out

SRC 	= src/Main.java										\
	src/Interpreter/Console.java							\
	src/Expression/Operation/Addition.java					\
	src/Expression/Operation/DivisionExpression.java		\
	src/Expression/Operation/MultiplicationExpression.java	\
	src/Expression/Operation/SubstractionExpression.java	\
	src/Expression/Operation/PowerExpression.java			\
	src/Expression/Operation/ParanthesesExpression.java		\
	src/Expression/OperationExpression.java					\
	src/Expression/ArithmeticExpression.java				\
	src/Expression/Minimal/NumberExpression.java			\
	src/Expression/Minimal/VariableExpression.java			\
	src/Expression/Minimal/MinimalExpression.java			\
	src/Expression/Minimal/FunctionExpression.java			\
	src/Expression/ArithmeticExpressionFactory.java			\
	src/Expression/DeclarationExpression.java				\
	src/Expression/MinimalExpressionFactory.java			\
	src/Expression/ExpressionFactory.java					\
	src/Expression/AExpressionFactory.java					\
	src/Context/StatusCode.java								\
	src/Context/Environnement.java							\
	src/Parser/Parsing.java									\
	src/Exception/SyntaxError.java							\
	src/Exception/VariableNotExistError.java				\

OBJ 	= $(SRC:.java=.class)

all:
	$(JAVAC) $(JCFLAGS) $(SRC)

run:
	$(JAVA) $(JFLAGS) Main

%.class: %.java
	$(JAVAC) $(JCFLAGS) $<
