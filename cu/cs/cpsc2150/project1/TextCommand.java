/* Author: Blair Durkee */

package cu.cs.cpsc2150.project1;

public class TextCommand {
    private CommandType myCommandType;
    private int myParameter;

    public TextCommand(CommandType type) {
        myCommandType = type;
    }

    public TextCommand(CommandType type, int parameter) {
        myCommandType = type;
        myParameter = parameter;
    }

    public int getParameter() {
        return myParameter;
    }

    public CommandType getCommandType() {
        return myCommandType;
    }
}
