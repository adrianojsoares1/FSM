package StateMachines;

import java.util.Random;

final class AuxFunctions {

  private static Random random = new Random();

  static boolean contains(char[] symbols, char ch){
    for(char c : symbols) if(ch == c) return true; return false;
  }

  static String generateAlphaNumeric(char[] alphabet, int maxLength){
    StringBuilder sb = new StringBuilder(maxLength);
    int strLength = random.nextInt(maxLength) + 1, newChar;

    for(int i = 0; i < strLength; ++i){
      newChar = random.nextInt(alphabet.length);
      sb.append(alphabet[newChar]);
    }

    return sb.toString();
  }
}
