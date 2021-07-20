
package com.djl.frontend;

import com.djl.backend.Archivo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    File ultimaCarpeta;
    public Controlador() {
        this.ultimaCarpeta = new File("");
    }
    // Accion del boton SUBIR.
    // Abre un JFileChooser para seleccionar el archivo a subir.
    public void escogerArchivo(JLabel jlFileName) 
    {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Abrir");
        jFileChooser.setCurrentDirectory(ultimaCarpeta);
            if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado.
            
            this.fileToSend[0] = jFileChooser.getSelectedFile();
            ultimaCarpeta=jFileChooser.getCurrentDirectory();
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
                dataOutputStream.writeInt(0); //Envia la instruccion de recibir el archivo
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
        DefaultTableModel tabla2=new DefaultTableModel();
        DecimalFormat formato1= new DecimalFormat("#.00");
        tabla2=(DefaultTableModel) tabla1.getModel();
        try{
            Socket socket = new Socket(host, port);
            
            try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
                dataOutputStream.writeInt(1);
                dataOutputStream.flush();
                dataOutputStream.close();
            }
            
            ServerSocket serverSocket1 = new ServerSocket(2070);
            try{
                Socket socket1 = serverSocket1.accept();
                InputStream in = socket1.getInputStream();
                ObjectInputStream input = new ObjectInputStream(in);
                lista=(ArrayList<Archivo>)input.readObject();
                in.close();
                serverSocket1.close();   
            }
       
            catch(IOException e) {}
            tabla2.setRowCount(0);
            for(Archivo x:lista){
                tabla2.addRow(new Object[]{
                    x.getName(),
                    formato1.format((float)x.getSize()/1024)+"KB",
                    x.getType(),
                });
            }
            socket.close();
        }catch(IOException e) {}

        return tabla2;
    }
    public void eliminar(String fileName){
        comando(2,fileName);
    }
    
    public boolean enviarUsuario(String userName,String password,int instruccion) throws IOException{
        String unido=userName+"`"+password;
        
        comando(instruccion,unido); //instruccion manda lo que deseemos realizar(registrar usuario o iniciar sesion), como vamos a enviar los mismos datos, solo cambiaremos lo que el server haga con ellos
        //si instruccion==4 inicia sesion, si instruccion ==5 el servidor registra al usuario
        
        String respuesta;
        ServerSocket serverSocket = new ServerSocket(2070);
        Socket socket = serverSocket.accept();
        DataInputStream input = new DataInputStream(socket.getInputStream());
        respuesta=input.readUTF();
        serverSocket.close();
        input.close();
        switch(respuesta){
            case "usuario no existe":
                
                break;
                
            case "invalid password":
                
                break;
                
            case "Usuario ya existe":
                
                break;
                
            default:
                System.out.println("Todo fino, adelante");
                return true;
        }
        return false;
    }
    public void comando(int instruccion,String fileName){
        
        try{
            Socket socket = new Socket(host, port);
            
            try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
                dataOutputStream.writeInt(instruccion);
                dataOutputStream.writeUTF(fileName);
                dataOutputStream.flush();
                dataOutputStream.close();
            }
        }catch (IOException ex) {}
    }
    
    public void descargarArchivo(String nombre) throws IOException{
        comando(3,nombre);
        ServerSocket serverSocket = new ServerSocket(2070);
        
        Socket socket = serverSocket.accept();
        DataInputStream input = new DataInputStream(socket.getInputStream());
        JFileChooser carpeta = new JFileChooser();
        carpeta.setDialogTitle("Seleccione la ubicación de descarga");
        carpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        carpeta.setCurrentDirectory(ultimaCarpeta);
        carpeta.setAcceptAllFileFilterUsed(false);
        
        if(carpeta.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            
            File selectedPath=carpeta.getSelectedFile();
            
            byte[] fileBytes=new byte[input.readInt()];
            input.readFully(fileBytes);
            ultimaCarpeta=selectedPath;
            File file = new File(ultimaCarpeta+"\\"+nombre);
            FileOutputStream file1 = new FileOutputStream(file);
            file1.write(fileBytes);
            file1.close();
            input.close();
        }
        socket.close();
        serverSocket.close();
    }
    

}
