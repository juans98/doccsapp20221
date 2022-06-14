/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import model.Client;
import persistence.ClientDAOImp;

/**
 *
 * @author Usuario
 */
public class Test2 {
    public static void main(String[] args) {
        Client client=new Client();
        client.setId(2L);
        var clientDAOImp=new ClientDAOImp();
        clientDAOImp.deleteClient(client);
        
                
    }

       
    private static void leeFichero(String fichero)
   {
      try
      {
         
      }
      catch (Exception e2)
      {
         e2.printStackTrace();
      }
   }

}
