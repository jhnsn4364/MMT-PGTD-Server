/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mmtserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author tarek.haykal and george.barrow
 */
public class MMTServer extends TimerTask{

    /**
     * @param args the command line arguments
     */
    private Set<PrintWriter> clientPrintWriters = new HashSet<PrintWriter>();;
    private HashMap<PrintWriter,Player>playerData = new HashMap<PrintWriter,Player>();
    private String dataForPlayers;
    private int myId=0;
    private int myX,myY,isIt;
    
    public static void main(String[] args) {
        MMTServer theApp = new MMTServer();
        theApp.go();
    }
    
    public MMTServer()
    {
        super();
        Timer t = new Timer();
        t.scheduleAtFixedRate(this,0,16); // parameters: 0) which TimerTask 
                                            // object's "run" method should I  
                                            // call? 1) how many milliseconds  
                                            // until I call it the first time?
                                            // 2) how many milliseconds
                                            // between subsequent calls?
        
    }
    
    public void go()
    {
        System.out.println("Starting Program.");
        
        try
        {
             ServerSocket myServerSocket = new ServerSocket(5001);
             while(true)
             {
                 Socket clientSocket = myServerSocket.accept();
                 // Someone connected.
                 PrintWriter pw = new PrintWriter(clientSocket.getOutputStream());
                 clientPrintWriters.add(pw);
                 Thread t = new Thread(new ClientListener(clientSocket,pw));
                 t.start();
             }
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            System.exit(1);
        }
    }
    
    public void run()
    {
        // this is what the timer calls.
        if (!(playerData.keySet().equals(null)))
        {
            String message = "";
            message += playerData.keySet().size();

            for (PrintWriter pw: playerData.keySet())
            {
                message += "\t" + playerData.get(pw).toString();
            }

            broadcast(message);
        }
    }
    
    public void broadcast(String message)
    {
        //TODO: write this method: loop through all my clients and send the
        //  the message.
       
        
            for (PrintWriter pw : playerData.keySet())
            {
                pw.println(message);
               // System.out.println(message);
                pw.flush();
            }
        
    }
    
    public void personalBroadcast(PrintWriter pw, String message)
    {
        pw.println(message);
        //System.out.println(message);
        pw.flush();
    }


public class ClientListener implements Runnable
{
        
        private Socket mySocket;
        private PrintWriter myPrintWriter;
        private String myName;
        private Scanner myScanner;
 
        private final int screenWidth=800,screenHeight=800;
        
        
    public ClientListener(Socket s, PrintWriter pw)
    {
        // when the clientlistener is initiated this happens once
        mySocket = s;
        myPrintWriter = pw;
        try
            {
                myScanner = new Scanner(mySocket.getInputStream());
                System.out.println("New scanner received at socket: "+mySocket);
                myName = myScanner.nextLine();
                
                myId++;
                myX=(int)(screenWidth*Math.random());
                myY=(int)(screenHeight*Math.random());
                isIt = 0;
                Player p = new Player(myId, myX, myY, isIt);
                
                playerData.put(pw, p);
                
                String initMessage = p.toString();
                System.out.println(initMessage);
                personalBroadcast(pw, initMessage);
               // System.out.println(p.x+","+p.y);
                System.out.println("Linked to :"+p.id);
            }
       catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        
    }
    
    
    
    public void run()
    {
        // the listening part
        String message;
        try
        {
            while ((message = myScanner.nextLine())!=null)
            {
               Player p = playerData.get(myPrintWriter);
               String[] part = message.split("\t");
               System.out.println(part);
               int id = Integer.parseInt(part[0]);
               int w  = Integer.parseInt(part[1]);
               int a  = Integer.parseInt(part[2]);
               int s  = Integer.parseInt(part[3]);
               int d  = Integer.parseInt(part[4]);
               
               p.y -= w;
               p.x -= a;
               p.y += s;
               p.x += d;
            }
        }

        catch (NoSuchElementException nsee)
        {
            // the client disconnected.
            clientPrintWriters.remove(myPrintWriter);
            playerData.remove(myPrintWriter);
            broadcast(playerData.get(myPrintWriter).id+" has left the conversation.");

        }
        
    }
    
//    dataForPlayers+=playerData.keySet().size();
//                for(PrintWriter printer:playerData.keySet())
//                {
//                    dataForPlayers+=playerData.get(printer);
//                    
//                }

}
    
}

