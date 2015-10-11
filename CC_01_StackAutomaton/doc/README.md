# Pushdown Automata Simulator

Pushdown automata simulator coded in Java by Jose Hernandez.

## Packages overview

* __gui__ contains the class(es) related to the Graphical User Interface.
* __pushdown__ contains the problem-specific algorithms.
* __pushdown.structs__ contains the problem-specific data structures.
* __util__ contains the non-problem-specific algorithms and data structures.

## Source explanation

* _MainWindow_ fires up the GUI.
* When 'Load' is chosen, _AutomataIO_ converts a given file into an _AutomatonData_.
	* Said _AutomatonData_ itself is used to create an _Automaton_.
* When 'Run' is chosen, the already created _Automaton_ executes a trace of the given string.
