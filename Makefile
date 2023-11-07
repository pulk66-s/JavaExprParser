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
	src/Expression/FunctionExpression.java					\
	src/Expression/Operation/ParanthesesExpression.java		\
	src/Expression/OperationExpression.java					\
	src/Expression/ArithmeticExpression.java				\
	src/Expression/Minimal/NumberExpression.java			\
	src/Expression/Minimal/VariableExpression.java			\
	src/Expression/Minimal/MinimalExpression.java			\
	src/Expression/ArithmeticExpressionFactory.java			\
	src/Expression/DeclarationExpression.java				\
	src/Expression/MinimalExpressionFactory.java			\
	src/Expression/ExpressionFactory.java					\
	src/Expression/AExpressionFactory.java					\
	src/Context/Environnement.java							\
	src/Exception/SyntaxError.java							\
	src/Exception/VariableNotExistError.java				\

TESTS_SRC	=	$(SRC)					\
	Tests/Addition/Simple.java			\
	Tests/Addition/Simplify.java		\
	Tests/AdditionSuite.java			\
	Tests/TestResult.java				\
	Tests/TestSuite.java				\
	Tests/AdditionSuite.java			\
	Tests/Main.java						\
	Tests/DivisionSuite.java			\
	Tests/PowerSuite.java				\
	Tests/MultiplicationSuite.java		\
	Tests/Multiplication/Simplify.java	\
	Tests/Division/Simple.java			\
	Tests/Power/Simple.java				\

OBJ 		= $(SRC:.java=.class)
TESTS_OBJ	= $(TESTS_SRC:.java=.class)

all:
	$(JAVAC) $(JCFLAGS) $(SRC)

run:
	$(JAVA) $(JFLAGS) Main

run_tests:
	$(JAVAC) $(JCFLAGS) $(TESTS_SRC)
	$(JAVA) $(JFLAGS) Tests.Main
