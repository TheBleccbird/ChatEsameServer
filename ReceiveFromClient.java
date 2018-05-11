import java.util.ArrayList;
import java.io.*;
import java.net.*;

public class ReceiveFromClient extends Thread
{
    private String sentence;
    private Sockets connection;
    private String[] splittedSentence;
    private int connectionIndex;

    public ReceiveFromClient (Sockets connection)
    {
        this.connection = connection;
    }

    public void run()
    {
        try
        {
            while(connection.getConnectionSocket().isConnected())
            {
                sentence = connection.getInFromClient().readLine();
                splittedSentence = sentence.split("@#-@");

                if(splittedSentence[0].equals("CLOSE"))
                {
                    for(int c = 0; c < ServerMain.allConnections.size(); c++)
                        ServerMain.allConnections.get(c).updateList(connection.getNomeUtente(), false);

                    connection.getConnectionSocket().close();
                    ServerMain.allConnections.remove(connection);
                }

                else if (splittedSentence[0].equals("0"))
                {
                    connectionIndex = ServerMain.searchUser(splittedSentence[1]);
                    ServerMain.allConnections.get(connectionIndex).getOutToClient().println("1@#-@" + splittedSentence[2] + "@#-@" + connection.getNomeUtente());
                }
                else
                    System.out.println("Ricevuto " + sentence + " da " + connection.getNomeUtente());
            }
        }
        catch(Exception e){ } //Inserire eccezione giusta
    }
}
