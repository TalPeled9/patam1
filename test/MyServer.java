package test;


public class MyServer {
    private int port;
    private ClientHandler ch;
    private volatile boolean stop;

    public MyServer(int port, ClientHandler ch) {
        this.port=port;
        this.ch=ch;
        stop=false;
    }
        
    public void start(){}

    public void close(){}
	
}
