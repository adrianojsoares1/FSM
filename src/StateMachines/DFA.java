package StateMachines;

import static StateMachines.AuxFunctions.contains;

public class DFA extends FiniteStateMachine {

  DFA(char[] states, char[] alphabet, char start, char[] finals, FSMRule[] rules){
    super(states, alphabet, start, finals, rules);

    try{
      for(char c: this.getAlphabet()) {
        if (c == CONSTANTS.EMP)
          throw new IllegalArgumentException("Constant EMPTY is not allowed in Deterministic Machines!");
        if (c == CONSTANTS.ARROW)
          throw new IllegalArgumentException("Constant ARROW is not allowed in Deterministic Machines!");
      }
    }catch(IllegalArgumentException arg){
      arg.printStackTrace();
    }
  }

  @Override
  public String type(){
    return "I am a Deterministic Finite Automaton.";
  }

  @Override
  public String test(String input){
    return runTest(input, false);
  }

  @Override
  public String testTransitions(String input){
    return runTest(input, true);
  }

  private String runTest(String input, boolean verbose){
    char head = getStart(), oldHead = head;
    StringBuilder sb = new StringBuilder().append(input).append("\n").append("[ ").append(head).append(" ]");

    for(char c : input.toCharArray()) {
      if(head != CONSTANTS.DEAD){ //don't bother iterating through the list if in a dead state
        oldHead = head;
        head = existsRule(head, c);

        if(oldHead == head) //Reason to believe we're in a dead state
          if(checkDeadState(head)) head = CONSTANTS.DEAD;
      }

      if(head != CONSTANTS.DEAD) //Head may have changed, add summary to the iterator
        sb.append(" --").append(c).append("--> ").append(head);
      else sb.append(" --").append(c).append("--> ").append("DEAD");
    }

    if(verbose) System.out.println(sb.toString());
    return contains(getFinals(), head) ? CONSTANTS.ACCEPT : CONSTANTS.REJECT;
  }

}

