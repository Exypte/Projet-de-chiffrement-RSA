import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Serveur {
 
   public static void main(String[] test) {
  
     final ServerSocket serveurSocket  ;
     final Socket clientSocket ;
     final BufferedReader in;
     final PrintWriter out;
     final Scanner sc = new Scanner(System.in);
  
	CouplePublic cp = Chiffrement.publicKey(11);
	CouplePrive cpp = Chiffrement.privateKey(cp);
    final CouplePublic cpClient = new CouplePublic(new BigInteger("1"), new BigInteger("1"), new BigInteger("1"));
     
     try {
       serveurSocket = new ServerSocket(5000);
       
       clientSocket = serveurSocket.accept();
       
       out = new PrintWriter(clientSocket.getOutputStream());
       
       in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));
       
       Thread envoyer = new Thread(new Runnable() {
           @Override
           public void run() {
        	   
             String msg;
             
             out.println(new Gson().toJson(cp));
             out.flush();
             
             System.out.println("Serveur : envoi de la clé publique");
             
             while(true){
               msg = sc.nextLine();
               
               ArrayList<BigInteger> b = Chiffrement.chiffrement(msg, cpClient);
               
               System.out.println("Serveur : chiffrement d'un message");
               
               out.println(new Gson().toJson(b));
               out.flush();
               
               System.out.println("Serveur : envoi d'un message");
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
               
               System.out.println("Serveur : reception de la clé publique");
               
               CouplePublic cptmp = new Gson().fromJson(msg, CouplePublic.class);
               
               cpClient.setE(cptmp.getE());
               cpClient.setN(cptmp.getN());
               cpClient.setM(cptmp.getM());
               
               while(msg!=null){
            	   
                  msg = in.readLine();
                  
                  System.out.println("Serveur : reception d'un message");
                  
                  ArrayList<BigInteger> b = new Gson().fromJson(msg, new TypeToken<ArrayList<BigInteger>>(){}.getType());
                  
                  String msgDechiffre = Chiffrement.dechiffrement(b, cpp);
                  
                  System.out.println("Serveur : déchiffrement du message");
                  
                  System.out.println("Serveur : message = " + msgDechiffre);
                  
                  b = Chiffrement.chiffrement(msgDechiffre, cpClient);
                  
                  System.out.println("Serveur : chiffrement du message reçu");
                  
                  out.println(new Gson().toJson(b));
                  out.flush();
                  System.out.println("Serveur : envoi du message reçu");
               }
               
               System.out.println("Serveur déconecté");
               
               out.close();
               clientSocket.close();
               
             } catch (IOException e) {
                 e.printStackTrace();
             }
          }
      });
      recevoir.start();
      
      }catch (IOException e) {
         e.printStackTrace();
      }
   }
}