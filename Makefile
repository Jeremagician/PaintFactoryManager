.PHONY: dist clean

all: $(wildcard *.java) $(wildcard com/jeretim/PaintFactoryManager/*.java) $(wildcard com/jeretim/PaintFactoryManager/Tool/*.java)
	@javac $^

dist:
	jar -cvf Apnee2.jar `find . -name "*.java"`

clean:
	rm -f *.class com/jeretim/PaintFactoryManager/*.class com/jeretim/Paintfactorymanager/Tool/*.class
