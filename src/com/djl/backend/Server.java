
package com.djl.backend;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    
   
    // Array list para guardar informacion sobre los archivos que se reciben
    static ArrayList<Archivo> archivo = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {

        // Used to track the file (jpanel that has the file name in it on a label).
        int fileId = 0;

        
        // Create a server socket that the server will be listening with.
        ServerSocket serverSocket = new ServerSocket(2069);

        // This while loop will run forever so the server will never stop unless the application is closed.
        while (true) {

            try {
                // Esperar a que el cliente se conecte y cuando lo haga crear un socket para comunicarse con el.
                Socket socket = serverSocket.accept();

                // Stream para recibir data del cliente a traves del socket.
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                // Leer el tamaño del nombre del archivo para saber cuando parar de leer.
                int fileNameLength = dataInputStream.readInt();
                
                // If the file exists
                if (fileNameLength > 0) {
                    byte[] fileNameBytes = new byte[fileNameLength];                    // Byte array para guardar el nombre del archivo
                    dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);  // Lee desde el input stream hacia el byte array.
                    String fileName = new String(fileNameBytes);                        // Create the file name from the byte array.
                    int fileContentLength = dataInputStream.readInt();                  // Read how much data to expect for the actual content of the file.
                    
                    // If the file exists.
                    if (fileContentLength > 0) {
                        // Array to hold the file data.
                        byte[] fileContentBytes = new byte[fileContentLength];
                        // Read from the input stream into the fileContentBytes array.
                        dataInputStream.readFully(fileContentBytes, 0, fileContentBytes.length);

                        // Add the new file to the array list which holds all our data.
                        archivo.add(new Archivo(fileId, fileName, fileContentLength, getFileExtension(fileName), "drewAn", fileContentBytes));
                        // Increment the fileId for the next file to be received.
                        fileId++;
                    }
                }
            } catch (IOException e) {}
        }
    }

    /**
     * @param fileName
     * @return The extension type of the file.
     */
    public static String getFileExtension(String fileName) {
        // Get the file type by using the last occurence of . (for example aboutMe.txt returns txt).
        // Will have issues with files like myFile.tar.gz.
        int i = fileName.lastIndexOf('.');
        // If there is an extension.
        if (i > 0) {
            // Set the extension to the extension of the filename.
            return fileName.substring(i + 1);
        } else {
            return "No extension found.";
        }
    }
}