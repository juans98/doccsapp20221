
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
import model.*;


public class OrganizationDAOImp implements OrganizationDAOInt {
    
    File file=new File("organization.dat");
    
    @Override
    public List<Organization> listOrganization() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Organization> organizations = new ArrayList<Organization>();
        FileInputStream fileInput = new FileInputStream(file);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);

        try {
            while (fileInput.available()!=0){
                organizations.add((Organization) objectInput.readObject());
            }
        } catch (EOFException e) {
            System.out.println("End of file");
        }
        objectInput.close();
        fileInput.close();
        return organizations;
    }

    @Override
    public String addOrganization(Organization organization) throws FileNotFoundException, IOException {
        String msg="";
        //Se valida que el objeto que se desea guardar no sea null o que ya
        //exista en el archvo de datos.
        if (organization != null && !validateIdOrganization(organization)) {
            try {
                FileOutputStream fileOutput = null;
                //Se crea el objeto FileOutputStream con el atributo true para 
                //habilitar que se agreguen datos al archivo
                fileOutput = new FileOutputStream(file, true);
                //Si el archivo no existe, inicializa el archivo
                //la opcion de true permite agregar registros al archivo
                if (file.length() == 0) {
                    ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
                    objectOutput.writeObject(organization);
                    objectOutput.close();
                } 
                //Si el archivo ya existe, agrega el objeto organization usando un objeto
                //de una clase que extiende ObjectOutputStrema para evitar un error
                //en la inicialización del objeto para gestionar el almacenamiento de
                //objeto al archivo de salida
                else {
                    MyObjectOutputStream myobjectOutput = null;
                    myobjectOutput = new MyObjectOutputStream(fileOutput);
                    myobjectOutput.writeObject(organization);
                    myobjectOutput.close();
                }
                msg="Organization added!";
                fileOutput.close();
            } catch (IOException e) {
                System.out.println("Error Occurred" + e);
            }
        }
        else {
            msg="Organization exists!";
        }
        return msg;
    }
//El método deleteOrganization recibe un objeto organization que se desea borrar
    @Override
    public void deleteOrganization(Organization organization) {
        //Si el objeto esta en el archivo lo va a borrar
        if (validateIdOrganization(organization)) {
            //Se usa dos archivos, el de datos y uno tempoaral
            File fileIn = new File("organization.dat");
            File fileTmp = new File("organizationtmp.dat");
            try ( FileInputStream flowIn = new FileInputStream(fileIn);  FileOutputStream flowOut = new FileOutputStream(fileTmp)) {
                //el flow in : lee los datos del archivo original y 
                //el flow out: guardar archivos en el archivo temporal 
                ObjectInputStream reader= new ObjectInputStream (flowIn);
                ObjectOutputStream writer = new ObjectOutputStream(flowOut);
                Organization c;
                while (flowIn.available()!=0) {
                    c = (Organization) reader.readObject();
                    //Compara el id de cada registros con el que se desea borrar
                    //Si son diferentes, escribe el registro en el archivo temporal
                    if (c.getId().equals(organization.getId())==false) {
                        writer.writeObject(c);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(OrganizationDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {    
                Logger.getLogger(OrganizationDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e){
            }
            //Elimina el archivo de datos y despues renombra el archivo temporal como el nuevo archivo de datos
            fileIn.delete();
            fileTmp.renameTo(fileIn);
        }        
    }

    @Override
    public void updateOrganization(Organization organization) {
         //Si el objeto esta en el archivo lo va a actualizar
        if (validateIdOrganization(organization)) {
            //Se usa dos archivos, el de datos y uno tempoaral
            File fileIn = new File("organization.dat");
            File fileTmp = new File("organizationtmp.dat");
            try ( FileInputStream flowIn = new FileInputStream(fileIn);  FileOutputStream flowOut = new FileOutputStream(fileTmp)) {
                ObjectInputStream reader= new ObjectInputStream (flowIn);
                ObjectOutputStream writer = new ObjectOutputStream(flowOut);
                Organization c;
                while (flowIn.available()!=0) {
                    c = (Organization) reader.readObject();
                    //Compara el id de cada registros con el que se desea borrar
                    //Si son diferentes, escribe el regitrro en el archivo temporal
                    if (c.getId().equals(organization.getId())==false) {
                        writer.writeObject(c);
                    }
                    else {
                        writer.writeObject(organization);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(OrganizationDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {    
                Logger.getLogger(OrganizationDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e){
            }
            //Elimina el archivo de datos y despues renombra el archivo temporal como el nuevo archivo de datos
            fileIn.delete();
            fileTmp.renameTo(fileIn);
        }        
    }
    
    //Recorre los registros guardados para buscar una organization que se quiere 
    //guardar. Retorna true si el ciente existe en elarchivo, de lo contrario
    //retorna false
    public boolean validateIdOrganization(Organization organization){
        boolean status = false;
        try {
            var organizations = listOrganization();
            for (Organization c : organizations) {
                status = c.getId().equals(organization.getId());
                if (status)
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    
}
