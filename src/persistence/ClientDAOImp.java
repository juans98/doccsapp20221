
package persistence;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;

public class ClientDAOImp implements ClientDAOInt {
    
    File file = new File("client.dat");

    @Override
    public List<Client> listClients() throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<Client> clients = new ArrayList<Client>();
        FileInputStream fileInput = new FileInputStream(file);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        try {
            while (fileInput.available()!=0){
                clients.add((Client) objectInput.readObject());
            }
        } catch (EOFException e) {
            System.out.println("End of file");
        }
        objectInput.close();
        fileInput.close();
        return clients;
    }

    @Override
    public String addClient(Client client) throws FileNotFoundException, IOException {
        String msg="";
        //Se valida que el objeto que se desea guardar no sea null o que ya
        //exista en el archvo de datos.
        if (client != null && !validateIdClient(client)) {
            try {
                FileOutputStream fileOutput = null;
                //Se crea el objeto FileOutputStream con el atributo true para 
                //habilitar que se agreguen datos al archivo
                fileOutput = new FileOutputStream(file, true);
                //Si el archivo no existe, inicializa el archivo
                if (file.length() == 0) {
                    ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
                    objectOutput.writeObject(client);
                    objectOutput.close();
                } 
                //Si el archivo ya existe, agrega el objeto client usando un objeto
                //de una clase que extiende ObjectOutputStrema para evitar un error
                //en la inicialización del objeto para gestionar el almacenamiento de
                //objeto al archivo de salida
                else {
                    MyObjectOutputStream myobjectOutput = null;
                    myobjectOutput = new MyObjectOutputStream(fileOutput);
                    myobjectOutput.writeObject(client);
                    myobjectOutput.close();
                }
                msg="Client added!";
                fileOutput.close();
            } catch (IOException e) {
                System.out.println("Error Occurred" + e);
            }
        }
        else {
            msg="Client exists!";
        }
        return msg;
    }
//El método deleteCliente recibe un objeto client que se desea borrar
    @Override
    public void deleteClient(Client client) {
        //Si el objeto esta en el archivo lo va a borrar
        if (validateIdClient(client)) {
            //Se usa dos archivos, el de datos y uno tempoaral
            File fileIn = new File("client.dat");
            File fileTmp = new File("clienttmp.dat");
            try ( FileInputStream flowIn = new FileInputStream(fileIn);  FileOutputStream flowOut = new FileOutputStream(fileTmp)) {
                ObjectInputStream reader= new ObjectInputStream (flowIn);
                ObjectOutputStream writer = new ObjectOutputStream(flowOut);
                Client c;
                while (flowIn.available()!=0) {
                    c = (Client) reader.readObject();
                    //Compara el id de cada registros con el que se desea borrar
                    //Si son diferentes, escribe el registro en el archivo temporal
                    if (c.getId().equals(client.getId())==false) {
                        writer.writeObject(c);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ClientDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {    
                Logger.getLogger(ClientDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e){
            }
            //Elimina el archivo de datos y despues renombra el archivo temporal como el nuevo archivo de datos
            fileIn.delete();
            fileTmp.renameTo(fileIn);
        }        

    }

    @Override
    public void updateClient(Client client) {
         //Si el objeto esta en el archivo lo va a actualizar
        if (validateIdClient(client)) {
            //Se usa dos archivos, el de datos y uno tempoaral
            File fileIn = new File("client.dat");
            File fileTmp = new File("clienttmp.dat");
            try ( FileInputStream flowIn = new FileInputStream(fileIn);  FileOutputStream flowOut = new FileOutputStream(fileTmp)) {
                ObjectInputStream reader= new ObjectInputStream (flowIn);
                ObjectOutputStream writer = new ObjectOutputStream(flowOut);
                Client c;
                while (flowIn.available()!=0) {
                    c = (Client) reader.readObject();
                    //Compara el id de cada registros con el que se desea borrar
                    //Si son diferentes, escribe el regitrro en el archivo temporal
                    if (c.getId().equals(client.getId())==false) {
                        writer.writeObject(c);
                    }
                    else {
                        writer.writeObject(client);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ClientDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {    
                Logger.getLogger(ClientDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e){
            }
            //Elimina el archivo de datos y despues renombra el archivo temporal como el nuevo archivo de datos
            fileIn.delete();
            fileTmp.renameTo(fileIn);
        }        
    }
    
    //Recorre los registros guardados para buscar un cliente que se quiere 
    //guardar. Retorna true si el ciente existe en elarchivo, de lo contrario
    //retorna false
    public boolean validateIdClient(Client client){
        boolean status = false;
        try {
            var clients = listClients();
            for (Client c : clients) {
                status = c.getId().equals(client.getId());
                if (status)
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}

