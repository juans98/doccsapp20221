
package app;

import model.Client;
import persistence.ClientDAOImp;

public class TestApp {
    public static void main(String[] args) {
        var clientDAOImp=new ClientDAOImp();
        String msg;
        var c1=new Client();
        //c1.setId(3L);
        c1.setFullName("AA");
        c1.setAddress("Address");
        c1.setEmail("email@email.co");
        try{
            for (int i = 1; i <= 5; i++) {
                c1.setId(Long.valueOf(i));
                c1.setFullName(i+"AA");
                c1.setAddress(i+"Address");
                c1.setEmail(i+"email@email.co");
                msg = clientDAOImp.addClient(c1);
                System.out.println(msg);
            }
          
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
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
    
}
