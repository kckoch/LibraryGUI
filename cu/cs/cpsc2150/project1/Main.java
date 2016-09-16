/* Author: Blair Durkee */

package cu.cs.cpsc2150.project1;

public class Main {

    public static void main(String[] args) {
        TextInterface textInterface = new TextInterface();
        TextCommand cmd = new TextCommand(CommandType.INVALID);
        while (cmd.getCommandType() != CommandType.QUIT) {
            textInterface.login();
            cmd = textInterface.promptCommand();
            textInterface.executeCommand(cmd);
        }
    }
}
