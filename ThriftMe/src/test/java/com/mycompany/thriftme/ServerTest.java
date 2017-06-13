package com.mycompany.thriftme;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author siddharthasingh
 */
public class ServerTest {

    private final int THRIFT_SERVER_PORT = 7911;

    public ServerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    private ServerThread serverThread = null;

    @Before
    public void setUp() {
        String[] args = null;
        serverThread = new ServerThread();
        new Thread(serverThread).start();
        try {
            // wait for the server start up
            System.out.println("Waiting for 100 ms for server to start properly");
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            System.out.println("InterupptedException : " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        if (serverThread != null) {
            serverThread.stop();
        }
    }

    /**
     * Test of main method, of class Server.
     */
    @Test
    public void testMain() {
        TTransport transport;
        try {
            transport = new TSocket("localhost", THRIFT_SERVER_PORT);

            TProtocol protocol = new TBinaryProtocol(transport);

            ArithmeticService.Client client = new ArithmeticService.Client(protocol);
            transport.open();

            long addResult = client.add(100, 200);
            System.out.println("Add result: " + addResult);
            Assert.assertEquals(300L, addResult);
            long multiplyResult = client.multiply(20, 40);
            Assert.assertEquals(800L, multiplyResult);
            System.out.println("Multiply result: " + multiplyResult);
            transport.close();
        }
        catch (TTransportException e) {
            System.out.println("TTransportException : " + e.getMessage());
        }
        catch (TException e) {
            System.out.println("TException : " + e.getMessage());
        }
    }

}
