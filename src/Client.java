import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Client {

   public static void main(String[] args) {
      
      final Socket clientSocket;
      final BufferedReader in;
      final PrintWriter out;
      final Scanner sc = new Scanner(System.in);//pour lire à partir du clavier
  
      CouplePublic cp = Chiffrement.publicKey(11);
      CouplePrive cpp = Chiffrement.privateKey(cp);
      final CouplePublic cpServeur = new CouplePublic(new BigInteger("1"), new BigInteger("1"), new BigInteger("1"));
      
      try {
         clientSocket = new Socket("127.0.0.1",5000);
   
         out = new PrintWriter(clientSocket.getOutputStream());
         
         in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
   
         Thread envoyer = new Thread(new Runnable() {
             @Override
             public void run() {
          	   
               String msg;
               
               out.println(new Gson().toJson(cp));
               out.flush();
               
               System.out.println("Client : envoi de la clé publique");
               
               while(true){
                 msg = sc.nextLine();
                 
                 ArrayList<BigInteger> b = Chiffrement.chiffrement(msg, cpServeur);
                 
                 System.out.println("Client : chiffrement d'un message");
                 
                 out.println(new Gson().toJson(b));
                 out.flush();
                 
                 System.out.println("Client : envoi d'un message");
               }
            }
        });
        envoyer.start();
     
        Thread recevoir = new Thread(new Runnable() {
            @Override
            public void run() {

               String msg;
               
               try {
                 msg = in.readLine();
                 
                 System.out.println("Client : reception de la clé publique");
                 
                 CouplePublic cptmp = new Gson().fromJson(msg, CouplePublic.class);
                 
                 cpServeur.setE(cptmp.getE());
                 cpServeur.setN(cptmp.getN());
                 cpServeur.setM(cptmp.getM());
                 
                 while(msg!=null){
              	   
                    msg = in.readLine();
                    
                    System.out.println("Client : reception d'un message");
                    
                    ArrayList<BigInteger> b = new Gson().fromJson(msg, new TypeToken<ArrayList<BigInteger>>(){}.getType());
                    
                    String msgDechiffre = Chiffrement.dechiffrement(b, cpp);
                    
                    System.out.println("Client : déchiffrement du message");
                    
                    System.out.println("Client : message = " + msgDechiffre);
                 }
                 
                 System.out.println("Client déconecté");
                 
                 out.close();
                 clientSocket.close();
                 
               } catch (IOException e) {
                   e.printStackTrace();
               }
            }
        });
        recevoir.start();
   
      } catch (IOException e) {
           e.printStackTrace();
      }
  }
}