package StateMachines;
import java.util.ArrayList;
import java.util.Arrays;
import static StateMachines.AuxFunctions.contains;

public abstract class FiniteStateMachine {
  private char[] states;
  private char[] alphabet;
  private char start;
  private char[] finals;
  private FSMRule[] rules;

  FiniteStateMachine(char[] states, char[] alphabet, char start, char[] finals, FSMRule[] rules){
    //Validate the Input
    try {
      //Check the States for Constants. Can't have any of that.
      for (char c : states)
        if (c == CONSTANTS.EMP || c == CONSTANTS.ARROW)
          throw new IllegalArgumentException("Predefined constant '" + c + "' may not be used as a state!");

      //Check the Starting State
      if (!contains(states, start))
        throw new IllegalArgumentException("Provided starting state '" + start + "' was not defined.");

      //Check the Final States
      for (char c : finals) {
        if (!contains(states, c))
          throw new IllegalArgumentException("Provided final state '" + c + "' was not defined.");
      }
      //Check the Rules
      for (FSMRule rule : rules) {
        if(!(contains(states, start) &&
          contains(states, rule.end) &&
          contains(alphabet, rule.consumes)))
          throw new IllegalArgumentException("Rule: " + rule.toString() + " contains an undefined element!");
      }
    }catch(IllegalArgumentException arg){
      arg.printStackTrace();
    }

    //Generate shallow copies to preserve given data, just in case.
    this.states = Arrays.copyOf(states, states.length);
    this.alphabet = Arrays.copyOf(alphabet, alphabet.length);
    this.start = start;
    this.finals = Arrays.copyOf(finals, finals.length);
    this.rules = Arrays.copyOf(rules, rules.length);

  }

  public char[] getStates() { return states; }

  char[] getAlphabet() { return alphabet; }

  char getStart() { return start; }

  char[] getFinals() { return finals; }

  public FSMRule[] getRules() { return rules; }

  public String type(){ return "I am a Finite State Machine."; }

  public abstract String test(String input);

  public abstract String testTransitions(String input);

  public boolean compareResult(FiniteStateMachine fsm, String toCompare){
    return this.test(toCompare).equals(fsm.test(toCompare));
  }

  public ArrayList<String> compareNRandom(FiniteStateMachine fsm, int n, int wordLength){
    ArrayList<String> wrong = new ArrayList<>(n);

    for(int i = 0; i < n; ++i){
      String testString = AuxFunctions.generateAlphaNumeric(this.alphabet, wordLength);
      if(!this.test(testString).equals(fsm.test(testString)))
        wrong.add(testString);
    }
    return wrong;
  }

  public ArrayList<String> compareNRandom(FiniteStateMachine fsm, int n){
    return compareNRandom(fsm, n, 15);
  }

  public ArrayList<String> compareNRandom(FiniteStateMachine fsm){
    return compareNRandom(fsm, 100);
  }

  public boolean equivalent(FiniteStateMachine fsm){
    return this.compareNRandom(fsm).isEmpty();
  }

  char existsRule(char head, char input){
    for(FSMRule rule : getRules())
      if(head == rule.start && input == rule.consumes)
        return rule.end;
    return CONSTANTS.DEAD;
  }

  boolean checkDeadState(char state){
    for(FSMRule rule : getRules())
      if(rule.start == state && rule.end != state)
        return false;
    return true;
  }


}
