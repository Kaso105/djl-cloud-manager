
package com.djl.backend;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {
    
    // Array list para guardar informacion sobre los archivos que se reciben
    static ArrayList<Archivo> archivo = new ArrayList<>();
    static String downloadPath = System.getProperty("user.dir")+"\\Download";
    static File downloadFolder;
    static ArrayList<Usuario> usuarios= new ArrayList<>();
    static Usuario juan;
    
    public static void main(String[] args) throws IOException {
        leerRegistro();
        int fileId=0;
        // Crea un server socket donde el servidor esperara requests.
        ServerSocket serverSocket = new ServerSocket(2069);
        //Obtiene los datos del server para actualizarlos al cliente
        
        // Este while loop correra para siempre en el servidor a menos que la aplicación sea cerrada.
        while (true) {

            try {
                
                // Esperar a que el cliente se conecte y cuando lo haga crear un socket para comunicarse con el.
                Socket socket = serverSocket.accept();

                // Stream para recibir data del cliente a traves del socket.
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                int instruccion=dataInputStream.readInt();
                switch(instruccion){
                    case 1:
                        enviarLista();
                        break;
                    case 2:
                        eliminar(dataInputStream.readUTF());
                        break;
                    case 3:
                        compartirArchivo(dataInputStream.readUTF());
                        break;
                    case 4:
                        verificarUsuario(dataInputStream.readUTF());
                        downloadFolder=new File(downloadPath);
                        findUserFolder(downloadFolder);
                        findAllFilesInFolder(downloadFolder);
                        fileId = archivo.get(archivo.size()-1).getId();
                        break;
                    case 5:
                        registrarUsuario(dataInputStream.readUTF());
                        downloadFolder=new File(downloadPath);
                        findUserFolder(downloadFolder);
                        findAllFilesInFolder(downloadFolder);
                        fileId = archivo.get(archivo.size()-1).getId();
                        break;
                    default:
                        // Leer el tamaño del nombre del archivo para saber cuando parar de leer.
                    int fileNameLength = dataInputStream.readInt();
                    // Si el archivo existe.
                    if (fileNameLength > 0) {
                    // Byte array para guardar el nombre del archivo.
                    byte[] fileNameBytes = new byte[fileNameLength];     
                    // Lee desde el input stream hacia el byte array.
                    dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);
                    // Crea el nombre del archivo desde el array de bytes.
                    String fileName = new String(fileNameBytes);
                    
                    boolean exist=false; //verifica si ya existe ese archivo
                    for(Archivo x:archivo){
                        if(x.getName().equals(fileName))
                            exist=true;
                    }
                    if(!exist){
                        
                        // Lee cuanda data esperar del contenido en si del archivo.
                        int fileContentLength = dataInputStream.readInt();

                        // Si el archivo existe.
                        if (fileContentLength > 0) {
                            // Array para contener toda la data.
                            byte[] fileContentBytes = new byte[fileContentLength];
                            // Lee del input stream hacia el array de fileContentBytes.
                            dataInputStream.readFully(fileContentBytes, 0, fileContentBytes.length);
                            // Añade el nuevo file al ArrayList que contiene todo nuestra data.
                            archivo.add(new Archivo(fileId, fileName, fileContentLength, getFileExtension(fileName)));
                            // Incrementa el fileId para el siguiente Archivo a ser recibido.
                            fileId++;

                            guardarArchivo(fileName,fileContentBytes);
                            enviarLista();
                            }
                        }
                    }
                } 
            }catch (IOException e) {}
                
        }
    }

    /**
     * @param fileName
     * @return The extension type of the file.
     */
    public static String getFileExtension(String fileName) {
        // Toma el tipo de archivo usando la ultima ocurrencia de . (por ejemplo readme.txt returns txt).
        // Tendrá problemas con los archivos tipo myFile.tar.gaz.
        int i = fileName.lastIndexOf('.');
        // Si hay una extensión.
        if (i > 0) {
            // Set la extensión a la extension del nombre del archivo.
            return fileName.substring(i + 1);
        } else {
            return "Extension no encontrada.";
        }
    }
    
    public static void findUserFolder(File folder){
        int i=1;
		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
                                if(juan.getUserName().equals(file.getName())){
                                    downloadFolder=file;
                                    return;
                                }
                                i++;
			}
		}
    }
    
    public static void findAllFilesInFolder(File folder) {
        int i=1;
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
                                Archivo newArchivo= new Archivo(i,file.getName(),(float)file.length(),getFileExtension(file.getName()));
                                archivo.add(newArchivo);
                                i++;
			} else {
				findAllFilesInFolder(file);
			}
		}
	}
    public static void guardarArchivo(String fileName,byte[] data){
        File fileToDownload = new File(downloadPath+"\\"+fileName);
                try {
                    // Create a stream to write data to the file.
                    FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
                    // Write the actual file data to the file.
                    fileOutputStream.write(data);
                    // Close the stream.
                    fileOutputStream.close();
                } catch (IOException ex) {
                    
                }}
    
    public static void enviarLista() throws IOException{
 
        Socket socket1 = new Socket("localhost", 2070);
        ObjectOutputStream dataOut=new ObjectOutputStream(socket1.getOutputStream());
        dataOut.writeObject(archivo);
        dataOut.flush();
        dataOut.close();
        
    }
    
    public static void verificarUsuario(String nombreYpassword){
        String[] user=nombreYpassword.split("`");
        String respuesta="";
        for(Usuario x:usuarios){
            if(x.getUserName().equals(user[0])){
                if(x.getPassword().equals(user[1])){
                    respuesta="todo fino, adelante";
                    downloadFolder=new File(downloadPath+"\\"+user[0]);
                    juan=x;
                }
                else
                    respuesta="invalid password";
            }else
                respuesta="usuario no existe";
        }
    }
    
    public static void registrarUsuario(String nombreYpassword){
        String[] user=nombreYpassword.split("`");
        String respuesta=null;
        for(Usuario x:usuarios){
            if(x.getUserName().equals(user[0])){
                respuesta="Usuario ya existe";
            }
        }
        if(respuesta==null){
            File carpeta =new File(System.getProperty("user.dir")+"\\Download"+"\\"+user[0]);
            carpeta.mkdir();
            juan=new Usuario(user[0],user[1]);
            usuarios.add(juan);
            downloadFolder=carpeta;
        }
    }
    
    public static void eliminar(String nombre){
        for(Archivo x:archivo){
            if(x.getName().equals(nombre)){
                archivo.remove(x);
                break;
            }
        }
        File del=new File(downloadPath+"\\"+nombre);
        del.delete();
    }
    
    public static void compartirArchivo(String nombre) throws FileNotFoundException, IOException{
        File file=new File(downloadPath+"\\"+nombre);
        DataOutputStream output;
        System.out.println(nombre);
        try (FileInputStream input = new FileInputStream(file)) {
            Socket socket1 = new Socket("localhost", 2070);
            output = new DataOutputStream(socket1.getOutputStream());
            byte[] fileBytes = new byte[(int)file.length()];
            input.read(fileBytes);
            output.writeInt(fileBytes.length);
            output.write(fileBytes);
        }
        output.flush();
        output.close();
    }
    public static void leerRegistro() throws IOException{
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("registro.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null){
                String nombre=linea;
                linea=br.readLine();
                String password=linea;
                Usuario x = new Usuario(nombre, password);
                usuarios.add(x);
         }
      }
      catch(IOException | NumberFormatException e){}
    }
    
    public void escribirRegistro(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("registro.txt");
            pw = new PrintWriter(fichero);
            
            for(Usuario x:usuarios) 
                    pw.println(x.getUserName()+"\n"+x.getPassword()+"\n");
                    
        }catch(IOException e){}
    }
}