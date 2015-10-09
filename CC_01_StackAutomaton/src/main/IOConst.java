package main;

/**
 * @author jose
 *
 *  Collection of constants.
 *  Models the arbitrary positions of the datums/tokens that were chosen
 *  as the input/output standard.
 *  It helps give semantics to the base string data.
 *  
 *  These will only be used by the AutomataIO class.
 */
public class IOConst {
    
    /*
     * Input constants.
     */
    
    // Data positions in processed file.
    final static int IN_STATE_SET = 0;
    final static int IN_INPUT_ALPH = 1;
    final static int IN_STACK_ALPH = 2;
    final static int IN_INIT_STATE = 3;
    final static int IN_INIT_STACK = 4;
    final static int IN_ACCEPT_STATES = 5;
    final static int IN_TRANS_FUNCT = 6; // Where the transition lines BEGIN.
    
    // Data positions in each processed transition line. 
    final static int IN_PREV_STATE = 0;
    final static int IN_NEXT_STATE = 3;
    final static int IN_REQ_INP_CHAR = 1;
    final static int IN_REQ_STACK_SYM = 2;
    final static int IN_STACK_SYM_TO_PUSH = 4;
    
    /*
     * Output constants.
     */
    
    // Data positions in each transition line to output.
    final static int OUT_LN_PREV_STATE = 0;
    final static int OUT_LN_NEXT_STATE = 3;
    final static int OUT_LN_REQ_INP_CHAR = 1;
    final static int OUT_LN_REQ_STACK_SYM = 2;
    final static int OUT_LN_STACK_SYM_TO_PUSH = 4;
    
    // Token positions in each automata status to output.
    final static int OUT_POS_CUR_STATE = 0;
    final static int OUT_POS_REMAIN_STR = 1;
    final static int OUT_POS_STACK_STATUS = 2;
}
