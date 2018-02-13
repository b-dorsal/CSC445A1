/**
 * Created by admin on 1/30/18.
 */
import java.net.*;
import java.io.*;
import java.text.*;

public class Client {
    private static int PORT;
    private static String HOSTNAME;

    public Client(int port, String hostname){
        this.PORT = port;
        this.HOSTNAME = hostname;
    }

    public void runRTT(int byteCount){

        try (
                Socket serverSocket = new Socket(this.HOSTNAME, this.PORT);

                OutputStream outData = serverSocket.getOutputStream();

                InputStream in = serverSocket.getInputStream();
                DataInputStream inData = new DataInputStream(in);
        ) {

            byte outputByte = (byte) 128;

            byte inputByte;
            long start = System.nanoTime();

            for (int x = 0; x < byteCount; x++) {
                System.out.println((x + 1) + " send " + (outputByte & 0xFF));
                outData.write(outputByte);
                inputByte = inData.readByte();
                System.out.println((x + 1) + " receive " + (inputByte & 0xFF));
            }
            long elapsed = System.nanoTime() - start;
            double seconds = (double)elapsed / 1000000000.0 / 1000;
            System.out.println("RTT= " + seconds);

            serverSocket.close();
            outData.close();
            in.close();
            inData.close();

        } catch (UnknownHostException e) {
            System.err.println("Bad hostname " + this.HOSTNAME);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    this.HOSTNAME);
            System.exit(1);
        }
    }

    public void runTransRate(int Kbytes){

        try (
                Socket serverSocket = new Socket(this.HOSTNAME, this.PORT);

                OutputStream outData = serverSocket.getOutputStream();

                InputStream in = serverSocket.getInputStream();
                DataInputStream inData = new DataInputStream(in);
        ) {

            byte outputByte = (byte) 128;

            byte inputByte = 0;
            long startSend = System.nanoTime();


            System.out.println("Sending: " + Kbytes + "KB");
            for (int x = 0; x < Kbytes; x++) {
                outData.write(outputByte);
            }
            System.out.println("Sent. " + (outputByte & 0XFF));

            long elapsedSend = System.nanoTime() - startSend;
            double seconds = (double)elapsedSend / 1000000000.0;
            DecimalFormat df = new DecimalFormat("#.###");

            System.out.println("UP= " + df.format(((Kbytes * 8)/1000000) / seconds));

            System.out.println("Receiving: " + Kbytes + "KB");
            long startReceive = System.nanoTime();
            for (int x = 0; x < Kbytes; x++){
                inputByte = inData.readByte();
            }
            System.out.println("Received. " + (inputByte & 0XFF));

            long elapsedReceive = System.nanoTime() - startReceive;
            seconds = (double)elapsedReceive / 1000000000.0;
            System.out.println("DOWN= " + df.format(((Kbytes * 8)/1000000) / seconds));

            serverSocket.close();
            outData.close();
            in.close();
            inData.close();

        } catch (UnknownHostException e) {
            System.err.println("Bad hostname " + this.HOSTNAME);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    this.HOSTNAME);
            System.exit(1);
        }
    }












}
