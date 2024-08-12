package test;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler {
    private Scanner in;
    private PrintWriter out;

    public void handleClient(InputStream inFromclient, OutputStream outToClient)
    {  
        in = new Scanner(inFromclient);
        out = new PrintWriter(outToClient);
        boolean check = false;
        String line = in.nextLine();
        String[] args = line.split("/");
        DictionaryManager dm = DictionaryManager.get();
        if (args[0] == "Q")
            check = dm.query(Arrays.copyOfRange(args, 1, args.length));
        else if (args[0] == "C")
            check = dm.challenge(Arrays.copyOfRange(args, 1, args.length));
        out.print(Boolean.toString(check) + "\n");
		out.flush();

    }
	
    public void close()
    {
        this.in.close();
        this.out.close();
    }
}
