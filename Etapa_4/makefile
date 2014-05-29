.PHONY:run
JFLAGS = -g
JC = javac
JVM= java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Ball.java \
	BallView.java\
	FixedHookView.java\
	LabMenuListener.java\
	MouseListener.java\
        MyWorld.java \
	MyWorldView.java\
        PhysicsElement.java \
	Simulateable.java\
	Spring.java \
	SpringView.java\
	FixedHook.java \
	SpringAttachable.java \
        PhysicsLab.java 

MAIN = PhysicsLab

default: classes

SOURCES =Ball.java BallView.java Myworld.java

doc: $(SOURCES)
	javadoc \
	-d \ documentation \
	$(SOURCES)

classes: $(CLASSES:.java=.class)

run:	 
	javac PhysicsLab.java
	java PhysicsLab

clean:
	$(RM) *.class
