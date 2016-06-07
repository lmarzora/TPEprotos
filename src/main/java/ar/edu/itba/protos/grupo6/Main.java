package ar.edu.itba.protos.grupo6;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    private final static int BACK_SERVER_PORT = 9090;

    public static void main(String[] args) {
        InetSocketAddress serverAddr = new InetSocketAddress("localhost", 8080);
        InetSocketAddress pop3 = new InetSocketAddress("pop.gmx.com", 110);
        //set initial capacity
        BlockingQueue<SelectionKey> outbox = new LinkedBlockingQueue<>();

        Server server = new Server(serverAddr, pop3, outbox);
        HandlerPool pool = new HandlerPool(outbox, server);

        new Thread(server).start();
        new Thread(new BackServer(BACK_SERVER_PORT)).start();
    }

}
