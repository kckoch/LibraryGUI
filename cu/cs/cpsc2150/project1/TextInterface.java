package cu.cs.cpsc2150.project1;

import java.io.*;

public class TextInterface {
    private AccountDatabase myAccountDatabase;
    private Catalog myCatalog;
    private Account myLoggedInAccount;
    private BufferedReader myReader;

    public TextInterface() {
        myAccountDatabase = new AccountDatabase();
        myCatalog = new Catalog();
        myLoggedInAccount = null;
        myReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public boolean login() {
        if (myLoggedInAccount != null) {
            return true;
        }

        String username, password;
        System.out.println("Enter your credentials (type 'q' to quit):");
        while(myLoggedInAccount == null) {
            try {
                System.out.print("Username: ");
                username = myReader.readLine();
                if (username.toUpperCase().equals("Q")) {
                    return false;
                }
                System.out.print("Password: ");
                password = myReader.readLine();
                if (password.toUpperCase().equals("Q")) {
                    return false;
                }

                myLoggedInAccount = myAccountDatabase.login(username, password);

                if (myLoggedInAccount == null) {
                    System.out.println("Invalid login! Please try again.");
                }
            } catch (IOException ex) {
                // try again
            }
        }
        System.out.println("Welcome, " + myLoggedInAccount);
        return true;
    }

    public TextCommand promptCommand() {
        TextCommand cmd = new TextCommand(CommandType.INVALID);
        String input;

        System.out.print("> ");

        while(cmd.getCommandType() == CommandType.INVALID) {
            try {
                input = myReader.readLine();
                cmd = parseCommand(input);
            } catch (Exception ex) {
                System.out.println("Invalid command! Please try again.");
            }
        }
        return cmd;
    }

    private TextCommand parseCommand(String cmd) throws InvalidCommandException {
        String[] tokens = cmd.toLowerCase().split("\\s");
        if (tokens.length > 0) {
            switch(tokens[0]) {
                case "logout":
                    return new TextCommand(CommandType.LOGOUT);
                case "quit":
                    return new TextCommand(CommandType.QUIT);
                case "add":
                    if (tokens.length > 1) {
                        switch (tokens[1]) {
                            case "book":
                                return new TextCommand(CommandType.ADD_BOOK);
                            case "account":
                                return new TextCommand(CommandType.ADD_ACCOUNT);
                        }
                    }
                    break;
                case "remove":
                    if (tokens.length > 2) {
                        try {
                            switch (tokens[1]) {
                                case "book":
                                    return new TextCommand(CommandType.REMOVE_BOOK, Integer.parseInt(tokens[2]));
                                case "account":
                                    return new TextCommand(CommandType.REMOVE_ACCOUNT, Integer.parseInt(tokens[2]));
                            }
                        } catch (NumberFormatException ex) {
                            // do nothing
                        }
                    }
                    break;
                case "list":
                    if (tokens.length > 1) {
                        switch (tokens[1]) {
                            case "books":
                                return new TextCommand(CommandType.LIST_BOOKS);
                            case "accounts":
                                return new TextCommand(CommandType.LIST_ACCOUNTS);
                        }
                    }
                    break;
            }
        }
        throw new InvalidCommandException();
    }

    public void executeCommand(TextCommand cmd) {
        switch (cmd.getCommandType()) {
            case ADD_ACCOUNT:
                doAddAccount();
                break;
            case ADD_BOOK:
                doAddBook();
                break;
            case REMOVE_ACCOUNT:
                myAccountDatabase.removeAccount(cmd.getParameter());
                System.out.println("Account removed!");
                break;
            case REMOVE_BOOK:
                myCatalog.removeBook(cmd.getParameter());
                System.out.println("Book removed!");
                break;
            case LIST_ACCOUNTS:
                System.out.print(myAccountDatabase);
                break;
            case LIST_BOOKS:
                System.out.print(myCatalog);
                break;
            case LOGOUT:
                myLoggedInAccount = null;
        }
    }

    private int readID() throws IOException {
        String textId;
        int id = -1;
        while (id < 0) {
            System.out.print("ID: ");
            textId = myReader.readLine();
            try {
                id = Integer.parseInt(textId);
                if (id < 0) {
                    System.out.println("You must enter a number greater than zero.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("You must enter a number. Please try again.");
            }
        }
        return id;
    }

    private void doAddAccount() {
        String name, username, password;
        int id;
        try {
            id = readID();
            System.out.print("Name: ");
            name = myReader.readLine();
            System.out.print("Username: ");
            username = myReader.readLine();
            System.out.print("Password: ");
            password = myReader.readLine();

            myAccountDatabase.addAccount(new Account(id, name, username, password));
            System.out.println("Account added!");
        } catch (IOException ex) {
            // do nothing
        }


    }

    private void doAddBook() {
        String title, author;
        int id;
        try {
            id = readID();
            System.out.print("Title: ");
            title = myReader.readLine();
            System.out.print("Author: ");
            author = myReader.readLine();

            myCatalog.addBook(new Book(id, title, author));
            System.out.println("Book added!");
        } catch (IOException ex) {
            // do nothing
        }
    }
}
