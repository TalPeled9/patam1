package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
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
        
    private void runServer() throws Exception {
        server = new ServerSocket(port);
        server.setSoTimeout(1000); // Set a timeout to allow the loop to check the stop condition
        try {
            while (!stop) {
                try {
                    aClient = server.accept(); // Wait for a client connection
                    try {
                        ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (aClient != null && !aClient.isClosed()) {
                            aClient.close();
                        }
                    }
                } catch (SocketTimeoutException e) {
                    // Timeout is expected, continue to check the stop condition
                } catch (SocketException e) {
                    // If the server socket is closed while waiting for a client, this exception will be thrown
                    if (stop) {
                        // Expected behavior when stopping the server, exit the loop
                        break;
                    } else {
                        throw e; // Unexpected SocketException, rethrow it
                    }
                }
            }
        } finally {
            if (server != null && !server.isClosed()) {
                server.close();
            }
        }
    }

    public void start() {
        this.stop = false;
        new Thread(() -> {
            try {
                runServer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void close() {
        this.stop = true;
        if (aClient != null && !aClient.isClosed()) {
            try {
                aClient.close();
            } catch (IOException e) {
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
