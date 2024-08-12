package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer {
    private int port;
    private ClientHandler ch;
    private ServerSocket server;
    private Socket aClient;
    private volatile boolean stop;

    public MyServer(int port, ClientHandler ch) {
        this.port=port;
        this.ch=ch;
        this.stop=false;
    }
        
    private void runServer() throws Exception{
        this.stop = false;
        server = new ServerSocket(port);
        server.setSoTimeout(1000);
        while(!stop){
            try{
                aClient = server.accept();
                try {
                    ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }catch(SocketTimeoutException e) {}
        }
    }

    public void start(){
        new Thread(()->{
            try {
                runServer();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();
    }

    public void close(){
        this.stop = true;
        if (this.ch != null) {
            ch.close();
        }
        if(!aClient.isClosed()){
        try{
            aClient.getInputStream().close();
            aClient.getOutputStream().close();
            aClient.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
        if (server != null && !server.isClosed()) {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
   
}
