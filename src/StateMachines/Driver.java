package StateMachines;

public class Driver {

  public static void main(String[] args){

    char[] states = {'S', 'A', 'B', 'C', 'D'};
    char[] alpha = {'a', 'b', 'c'};
    char[] finals = {'C'};
    FSMRule[] rules = {
      new FSMRule('S', 'a', 'A'),
      new FSMRule('A', 'b', 'B'),
      new FSMRule('B', 'c', 'C'),
      new FSMRule('A', 'a', 'A'),
      new FSMRule('B', 'b', 'B'),
      new FSMRule('C', 'c', 'C'),
      new FSMRule('S', 'b', 'D'),
      new FSMRule('S', 'c', 'D'),
      new FSMRule('A', 'c', 'D'),
      new FSMRule('B', 'a', 'D'),
      new FSMRule('C','a','D'),
      new FSMRule('C','b','D')
    };
    FSMRule[] rules1= {
      new FSMRule('S', 'a', 'A'),
      new FSMRule('A', 'b', 'B'),
      new FSMRule('B', 'c', 'C'),
      new FSMRule('A', 'a', 'A'),
      new FSMRule('B', 'b', 'B'),
      new FSMRule('C', 'c', 'C')
    };

    DFA d = new DFA(states, alpha, 'S', finals, rules);
    DFA d1 = new DFA(states, alpha, 'S', finals, rules);
    System.out.println(d.equivalent(d1));

  }
}
