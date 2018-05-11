import java.io.*;
import java.net.*;

public class Sockets
{    
    private Socket connectionSocket;
    private BufferedReader inFromClient; //Riceve
    private PrintWriter outToClient;//Manda
    private ReceiveFromClient comunicazioni;
    private SendOnline lista;
    private String sentence;
    private String nomeUtente = null;

    public Sockets(Socket connectionSocket) throws Exception
    {
        this.connectionSocket = connectionSocket;
        inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        outToClient = new PrintWriter(new OutputStreamWriter(connectionSocket.getOutputStream(),"UTF-8"), true);
        comunicazioni = new ReceiveFromClient(this);
        lista = new SendOnline(this);
        nomeUtente = new String(inFromClient.readLine().getBytes(), "UTF-8");
        lista.sendList(this);
        comunicazioni.start();
    }
    public void updateList(String name, boolean type)
    {
        lista.updateList(name, type);
    }
    public void addUser(String name)
    {
        lista.addUser(name);
    }
    public void removeUser(String name)
    {
        lista.removeUser(name);
    }

    public Socket getConnectionSocket() {
        return connectionSocket;
    }

    public void setConnectionSocket(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    public BufferedReader getInFromClient() {
        return inFromClient;
    }

    public void setInFromClient(BufferedReader inFromClient) {
        this.inFromClient = inFromClient;
    }

    public PrintWriter getOutToClient() {
        return outToClient;
    }

    public void setOutToClient(PrintWriter outToClient) {
        this.outToClient = outToClient;
    }

    public ReceiveFromClient getComunicazioni() {
        return comunicazioni;
    }

    public void setComunicazioni(ReceiveFromClient comunicazioni) {
        this.comunicazioni = comunicazioni;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }
}
