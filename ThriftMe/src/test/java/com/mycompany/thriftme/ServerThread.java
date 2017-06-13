package com.mycompany.thriftme;

/**
 *
 * @author siddharthasingh
 */
public class ServerThread implements Runnable {

    private Server server = null;

    @Override
    public void run() {
        server = new Server();
        server.start();
    }

    public void stop() {
        if (server != null) {
            server.stop();
        }
    }
}
