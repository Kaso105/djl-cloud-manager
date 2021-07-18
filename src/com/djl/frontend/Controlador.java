
package com.djl.frontend;

import com.djl.backend.Archivo;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Controlador 
{    
    final File[] fileToSend = new File[1];
    final String host="localhost";
    final int port=2069;
    // Accion del boton SUBIR.
    // Abre un JFileChooser para seleccionar el archivo a subir.
    public void escogerArchivo(JLabel jlFileName) 
    {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Abrir");
            if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado.
            this.fileToSend[0] = jFileChooser.getSelectedFile();
            // Cambiar el nombre del JLabel para confirmar
            jlFileName.setText(fileToSend[0].getName());
        }
    }

    public void subirArchivo(JLabel jlFileName) throws ClassNotFoundException 
    {
        // Si un archivo no ha sido seleccionado mostrar este mensaje.
        if (fileToSend[0] == null) 
        {
            jlFileName.setText("Seleccione un archivo a subir primero");
        } 
        else 
        {
            try 
            {
                // Crea un input stream en el archivo que se mandará.
                FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());
                // Crea la conexión socket con el servidor.
                Socket socket = new Socket(host, port);
                // Crea un output stream para escribir al servidor a traves del socket 
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                // Toma el nombre del archivo a mandar y lo guardalo en fileName
                String fileName = fileToSend[0].getName();
                // Convierte el nombre del archivo en un array de bytes para mandarlo al servidor.
                byte[] fileNameBytes = fileName.getBytes();
                // Crea un array de bytes del tamaño del archivo para no mandar ni mucha ni poca data al servidor.
                byte[] fileBytes = new byte[(int) fileToSend[0].length()];
                // Coloca el contenido del archivo en un array de bytes para mandado al servidor
                fileInputStream.read(fileBytes);
                // Manda la longitud del nombre del archivo para que el servidor sepa cuando parar de leer.
                dataOutputStream.writeInt(fileNameBytes.length);
                // Manda el nombre del archivo.
                dataOutputStream.write(fileNameBytes);
                // Manda la longitud del array de bytes para que el servidor sepa cuando para de leer.
                dataOutputStream.writeInt(fileBytes.length);
                // Manda el archivo como tal.
                dataOutputStream.write(fileBytes);
            } catch (IOException ex) {}
        }
    }
    
    public DefaultTableModel update(JTable tabla1) throws IOException, ClassNotFoundException{
        ArrayList<Archivo> lista=new ArrayList();
        String comando="solicitarLista";
        DefaultTableModel tabla2=new DefaultTableModel();
        DecimalFormat formato1= new DecimalFormat("#.00");
        tabla2=(DefaultTableModel) tabla1.getModel();
        byte[] size=comando.getBytes();
        try{
            Socket socket = new Socket(host, port);
            
            try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
                dataOutputStream.writeInt(size.length);
                dataOutputStream.write(size);
                dataOutputStream.flush();
                dataOutputStream.close();
            }
            lista=read();
            tabla2.setRowCount(0);
            System.out.println("borrado");
            System.out.println(lista.size());
            for(Archivo x:lista){
                tabla2.addRow(new Object[]{
                    x.getName(),
                    formato1.format((float)x.getSize()/1024)+"KB",
                    x.getType(),
                    x.getUser(),
                });
            }
            socket.close();
        }catch(IOException e) {}

        return tabla2;
    }
    public void eliminar(String fileName){
        String comando="eliminarArchivo";
        byte[] comandoBytes=comando.getBytes();
        try{
            Socket socket = new Socket(host, port);
            
            try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
                dataOutputStream.writeInt(comandoBytes.length);
                dataOutputStream.write(comandoBytes);
                dataOutputStream.writeUTF(fileName);
                dataOutputStream.flush();
                dataOutputStream.close();
            }
        }catch (IOException ex) {}
        
    }
    
    public ArrayList<Archivo> read() throws IOException, ClassNotFoundException{
        ArrayList<Archivo> lista=new ArrayList<>();
        ServerSocket serverSocket1 = new ServerSocket(2070);
            try{
                Socket socket1 = serverSocket1.accept();
                InputStream in = socket1.getInputStream();
                ObjectInputStream input = new ObjectInputStream(in);
                lista=(ArrayList<Archivo>)input.readObject();
                System.out.println("Si");
                in.close();
                serverSocket1.close();   
            }
       
            catch(IOException e) {}
        
        return lista;
    }

}
