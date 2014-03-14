.PHONY: dist clean

TestFenetre: $(wildcard *.java)
	javac $^

dist:
	jar -cvf Apnee2.jar `find . -name "*.java"`

clean:
	rm *.class
