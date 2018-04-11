package StateMachines;

import StateMachines.FiniteStateMachine;

public abstract class NDFA extends FiniteStateMachine {

  NDFA(char[] states, char[] alphabet, char start, char[] finals, FSMRule[] rules){
    super(states, alphabet, start, finals, rules);

  }

  public String test(String s){
    return "";
  }


}
