/**
 * Created by admin on 1/30/18.
 */
public class main {

    static final int PORT = 2691;
    public static void main(String args[]){
        int SCarg = Integer.parseInt(args[0]);
        if(SCarg == 0){
            Server testServer = new Server(PORT);
            System.out.println("Server started. Listening on " + PORT);
            testServer.runRTT(1);
            testServer.runTransRate(1000000);
        }else {
            String serverIP = args[1];
            Client testClient = new Client(PORT, serverIP);
            System.out.println("Client started on " + PORT);
            testClient.runRTT(1);
            testClient.runTransRate(1000000);
        }

        System.out.println("done.");
    }
}
