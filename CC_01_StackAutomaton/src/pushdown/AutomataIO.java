package pushdown;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import pushdown.structs.AutomatonData;
import pushdown.structs.AutomatonStatus;
import pushdown.structs.State;
import pushdown.structs.Symbol;
import pushdown.structs.TransitionRule;
import util.StringProcessing;
import util.TokenizedLines;

/**
 * @author jose
 * 
 * Extracts and gives semantic meaning
 * to Automaton data stored as raw text.
 * 
 * Also used to represent data in raw strings.
 * 
 * Only class that knows the input/output
 * convention (data format) used.
 */
public abstract class AutomataIO extends IOConst{
    
    /*
     * Input functions.
     */
    
	/**
	 * Reads automaton data from a given file.
	 * @param fileName Path to automaton file.
	 * @return Data structure that semantically represents the automaton definition.
	 * @throws IOException
	 */
	public static AutomatonData readAutomatonData(String fileName) throws IOException {
	    
	    TokenizedLines tokLines = prepareAutomatonData(fileName);

        HashSet<State> stateSet = new HashSet<State>();
        for (String token : tokLines.get(IN_FILE_STATE_SET)) {
            stateSet.add(new State(token));
        }

        HashSet<Character> inputAlphabet = new HashSet<Character>();
        for (String token : tokLines.get(IN_FILE_INPUT_ALPH)) {
            inputAlphabet.add(token.charAt(0));
        }

        HashSet<Symbol> stackAlphabet = new HashSet<Symbol>();
        for (String token : tokLines.get(IN_FILE_STACK_ALPH)) {
            stackAlphabet.add(new Symbol(token));
        }

        State initialState = new State(tokLines.get(IN_FILE_INIT_STATE).get(0));

        Symbol initialStackSymbol = new Symbol(tokLines.get(IN_FILE_INIT_STACK).get(0));

        HashSet<State> acceptStates = new HashSet<State>();
        for (String str : tokLines.get(IN_FILE_ACCEPT_STATES)) {
            stateSet.add(new State(str));
        }
        
        TokenizedLines transitionLines = 
                new TokenizedLines(tokLines.subList(IN_FILE_TRANS_FUNCT, tokLines.size()));
        
        HashSet<TransitionRule> transitionRules = readTransitionRules(transitionLines);
        
        return new AutomatonData(stateSet, inputAlphabet, stackAlphabet, initialState,
                initialStackSymbol, transitionRules, acceptStates);
	}
    
	/**
	 * Reads transition rules from an array of string arrays.
	 * @param transitionLines  Array of string arrays containing
	 * @return Data structure that semantically represents a set of transition rules.
	 */
	private static HashSet<TransitionRule> readTransitionRules(TokenizedLines transitionLines){
	    
	    HashSet<TransitionRule> transitionRules = new HashSet<TransitionRule>();
        
        for (ArrayList<String> tl : transitionLines){
            
            State prevState = new State(tl.get(IN_TRAN_PREV_STATE));
            
            State nextState = new State(tl.get(IN_TRAN_NEXT_STATE));
            
            Character requiredInputCharacter = tl.get(IN_TRAN_REQ_INP_CHAR).charAt(0);
            
            Symbol requiredStackSymbol = new Symbol(tl.get(IN_TRAN_REQ_STACK_SYM));
            
            // Read each of the symbols to push.
            ArrayList<Symbol> stackSymbolsToPush = new ArrayList<Symbol>();
            String[] stackCharsToPush = tl.get(IN_TRAN_STACK_SYM_TO_PUSH).split(",");
            for (String str : stackCharsToPush){
                stackSymbolsToPush.add(new Symbol(str));
            }
            
            transitionRules.add(new TransitionRule(prevState, nextState, 
                    requiredInputCharacter, requiredStackSymbol, stackSymbolsToPush));
        }
        
        return transitionRules;
	}
	
	/**
     * Standardizes input file, preparing it for automaton data extraction.
     * @param fileName Path to an automaton file (arbitrary file structure)
     * @return Standardized (expected) lines for reading automaton data.
     * @throws IOException
     */
    private static TokenizedLines prepareAutomatonData(String fileName) throws IOException {
        
        ArrayList<String> lines = StringProcessing.readFileLines(fileName);
        
        lines = StringProcessing.stripComments(lines, "#");
        
        lines = StringProcessing.removeAllEmptyLines(lines);

        TokenizedLines tokenizedLines = StringProcessing.tokenizeLines(lines, "\\s+");
        
        return tokenizedLines;
    }
	
    /*
     * Output functions.
     */
    
    private static final String DUMMY_STRING = "dummy";
    
    /**
     * Produce the representation of a transition, according to the internal IO convention.
     * @param tr Transition rule to be represented.
     * @return Array of strings representing the given transition rule.
     */
    public static ArrayList<String> getTransitionAsTokenizedLine(TransitionRule tr){

//      ArrayList<String> transitionRuleLine = new ArrayList<String>(5);

//      TODO: Try to one-line this?
      ArrayList<String> transitionRuleLine = new ArrayList<String>();
      for (int i = 0; i < 5; i++){
          transitionRuleLine.add(DUMMY_STRING);
      }
      
      transitionRuleLine.set(OUT_TRAN_PREV_STATE, tr.getPrevState().toString());
      transitionRuleLine.set(OUT_TRAN_NEXT_STATE, tr.getNextState().toString());
      transitionRuleLine.set(OUT_TRAN_REQ_INP_CHAR, tr.getRequiredInputCharacter().toString());
      transitionRuleLine.set(OUT_TRAN_REQ_STACK_SYM, tr.getRequiredStackSymbol().toString());
      transitionRuleLine.set(OUT_TRAN_STACK_SYM_TO_PUSH, tr.getStackSymbolsToPush().toString());
      
      return transitionRuleLine;
    }
    
    /**
     * Produce the representation of a transition, according to the internal IO convention.
     * @param ad AutomatonData to be represented.
     * @return Array of string arrays representing the given transition rule.
     */
    public static TokenizedLines getTransitionsAsTokenizedLines(AutomatonData ad){
        
        TokenizedLines tl = new TokenizedLines();
        
        for (TransitionRule tr : ad.getTransitionRules()){            
            tl.add(getTransitionAsTokenizedLine(tr));
        }
        
        return tl;
    }
    
    /**
     * Produce the representation of an automaton status, according to the internal IO convention.
     * @param as Automaton status to be represented.
     * @return Array of strings representing the given automaton status.
     */
    public static ArrayList<String> getStatusAsTokenizedLine(AutomatonStatus as){
        
        ArrayList<String> tokenizedLine = new ArrayList<String>();
        
//      TODO: Try to one-line this?
        for (int i = 0; i < 3; i++){
            tokenizedLine.add(DUMMY_STRING);
        }
        
        tokenizedLine.set(OUT_STAT_CUR_STATE, as.getCurrentState().toString());
        tokenizedLine.set(OUT_STAT_REMAIN_STR, as.getRemainingInputString().toString());
        tokenizedLine.set(OUT_STAT_STACK_STATUS, as.getCurrentStack().toString());
        
        return tokenizedLine;
    }
    
    /**
     * Produce the representation of a trace trail, according to the internal IO convention.
     * @param tt Trace trail to be represented.
     * @return  Array of string arrays representing the given trace trail.
     */
    public static TokenizedLines traceTrailAsTokenizedLines(TraceTrail tt){
        
        TokenizedLines tokenizedLines = new TokenizedLines();
        
        for (AutomatonStatus as : tt){
            tokenizedLines.add(getStatusAsTokenizedLine(as));
        }
        
        return tokenizedLines;
    }
   
}
