import java.io.IOException;
import java.util.ArrayList;

public class SendOnline {
    private Sockets connection;

    public SendOnline(Sockets connection) {
        this.connection = connection;
    }

    public void sendList(Sockets client) //invia la lista aggiornata a tutti i client
    {
        for (int i = 0; i < ServerMain.allConnections.size(); i++) {
            client.getOutToClient().println("0@#-@" + ServerMain.allConnections.get(i).getNomeUtente());
        }
    }

    //Aggiorna le liste di tutti gli utenti connessi quando si connette o disconnette qualcuno
    //Type 0 aggiunge, Type 1 toglie
    public void updateList(String name, boolean type)
    {
        if (type)
            addUser(name);
        else
            removeUser(name);
    }


        public void addUser (String name)
        {
            try {
                connection.getOutToClient().println("0@#-@" + name);
            } catch (Exception e) {
            }
        }

        public void removeUser (String name)
        {
            try {
                connection.getOutToClient().println("2@#-@" + name);
            } catch (Exception e) {
            }
        }


}