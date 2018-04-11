package StateMachines;

public class FSMRule {
  public char start;
  public char consumes;
  public char end;

  FSMRule(char s, char c, char e){
    start = s; consumes = c; end = e;
  }

  @Override
  public String toString(){
    return "[ " + start + ", " + consumes + ", " + end + " ]";
  }
}
