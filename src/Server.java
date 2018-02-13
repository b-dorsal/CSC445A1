/**
 * Created by admin on 1/30/18.
 */
import java.net.*;
import java.io.*;

public class Server {
    private final int PORT;

    public Server(int port){
        this.PORT = port;
    }

    public void runRTT(int byteCount) {
        try (
                ServerSocket serverSocket = new ServerSocket(this.PORT);
                Socket clientSocket = serverSocket.accept();

                OutputStream outData = clientSocket.getOutputStream();

                InputStream in = clientSocket.getInputStream();
                DataInputStream inData = new DataInputStream(in);
        ) {

            byte inputByte, outputByte;

            for(int x = 0; x < byteCount; x++) {
                inputByte = inData.readByte();
                outputByte = inputByte;
                System.out.println(x + ": " + (inputByte & 0xFF));
                outData.write(outputByte);
            }

            outData.close();
            inData.close();
            in.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + this.PORT + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

    public void runTransRate(int Kbytes) {
        try (
                ServerSocket serverSocket = new ServerSocket(this.PORT);
                Socket clientSocket = serverSocket.accept();

                OutputStream outData = clientSocket.getOutputStream();

                InputStream in = clientSocket.getInputStream();
                DataInputStream inData = new DataInputStream(in);
        ) {

            byte inputByte = 0;
            byte outputByte = 0;

            System.out.println("Receiving: " + Kbytes + "KB");

            for (int x = 0; x < Kbytes; x++){
                inputByte = inData.readByte();
                outputByte = inputByte;
            }
            System.out.println("Received. " + (inputByte & 0xFF));

            System.out.println("Sending: " + Kbytes + "KB");
            for(int x = 0; x < Kbytes; x++){
                outData.write(outputByte);
            }
            System.out.println("Sent. " + (outputByte & 0XFF));


            outData.close();
            inData.close();
            in.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + this.PORT + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }



}
