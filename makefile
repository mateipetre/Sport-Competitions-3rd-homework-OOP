build: 
	javac *.java
run: build
	java Test $(COMANDA) $(IN1) $(IN2) $(OUT)
clean: run
	rm -rf *.class