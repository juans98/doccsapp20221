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
public class Test3 {
    public static void main(String[] args) {
        var clientDAOImp=new ClientDAOImp();
        System.out.println("Listado....");
        try{
            var clients = clientDAOImp.listClients();
            for(Client c : clients){
                System.out.println(c.toString());
        }
        }
        catch (Exception e){
            e.printStackTrace();
        }
                
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
