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
public class Test4 {
    public static void main(String[] args) {
        //Client client=new Client();
        var clientDAOImp=new ClientDAOImp();
        var c1=new Client();
        c1.setId(3L);
        c1.setFullName("Update");
        c1.setAddress("Update Address");
        c1.setEmail("upemail@email.co"); 
        clientDAOImp.updateClient(c1);

                
    }

   

}
