package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class socketWrapper implements Serializable {
    private transient Socket socket;
    private transient ObjectOutputStream oos;
    private transient ObjectInputStream ois;

    public socketWrapper(String s, int port) throws IOException { // used by the client
        this.socket = new Socket(s, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }
    public  Socket getSocket() throws IOException {
        return socket;
    }
    public socketWrapper(Socket s) throws IOException { // used by the server
        this.socket = s;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public Object read() throws IOException, ClassNotFoundException {
        return ois.readUnshared();
    }

    public void write(Object o) throws IOException {
        oos.writeUnshared(o);
    }

    public void closeConnection() throws IOException {
        ois.close();
        oos.close();
    }
}
