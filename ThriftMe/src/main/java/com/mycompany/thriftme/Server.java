package com.mycompany.thriftme;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 *
 * @author siddharthasingh
 */
public class Server {

    private final int THRIFT_SERVER_PORT = 7911;

    private TServer server = null;

    public void start() {
        try {
            TServerSocket serverTransport = new TServerSocket(THRIFT_SERVER_PORT);

            ArithmeticService.Processor processor = new ArithmeticService.Processor(new ArithmeticServiceImpl());

            server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).
                processor(processor));
            System.out.println("Starting server on port 7911 ...");
            server.serve();
        }
        catch (TTransportException e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public void stop() {
        if (server != null) {
            System.out.println("Stopping server...");
            server.stop();
            System.out.println("Server stopped...");
        }
    }

    public Server() {
    }

    public static void main(String[] args) {
        Server srv = new Server();
        srv.start();
    }
}
