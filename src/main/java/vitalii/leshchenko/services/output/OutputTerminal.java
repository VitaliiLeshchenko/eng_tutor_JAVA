package vitalii.leshchenko.services.output;

public class OutputTerminal implements Output{
  @Override
  public void output(String message) {
    System.out.println(message);
  }
}
