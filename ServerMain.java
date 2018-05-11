import java.io.*;
import java.net.*;
import java.util.ArrayList;

class ServerMain 
{
    static ArrayList<Sockets> allConnections = new ArrayList<Sockets>();
    private static int d;
    //NON DIMENTICARE MAI PIU LO /N ALLA FINE DI UN INVIO GRAZIE PREGO
    public static void main(String argv[]) throws Exception
    {

        int c = 0;
        String sentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("Msn Server 0.1 alpha is running");
        Socket connection;
        //Prima Connessione
        connection = welcomeSocket.accept();
        allConnections.add(new Sockets(connection));

        while(true)
        {
            System.out.println("\nConnected Clients: " + allConnections.size());
            connection = welcomeSocket.accept();
            allConnections.add(new Sockets(connection));
            for(c = 0; c < allConnections.size(); c++)
                allConnections.get(c).updateList(allConnections.get(allConnections.size() - 1).getNomeUtente(), true);
        }
    }

    public static int searchUser(String user)
    {
        for (d = 0; d < allConnections.size(); d++)
        {
            if (allConnections.get(d).getNomeUtente().equals(user))
            {
                return d;
            }
        }

        return d;
    }
}
