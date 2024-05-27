/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.textmanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* Clase creadora de archivos txt
* Contiene las acciones para manipularlos
* @author Jacobo Arroyave
* @version 0.1, 2024/05/26
*/
public class FilesCreator {
    private File file;
    
    /**
    * Crea un archivo de texto con la ruta indicada
    * @see java.io.File#createNewFile() 
    */
    public void createTextFile() {
        file = new File("C:\\Users\\arroy\\OneDrive\\Documentos\\NetBeansProjects\\FinalProjectPOO-main\\build\\classes\\lastsoldier\\textfiles\\file.txt");
        System.out.println("Creating text file at: " + file.getAbsolutePath());
        
        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException ex) {
            Logger.getLogger(FilesCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
    * Escribe el contenido para el archivo txt
    * @param content
    * @see java.io.BufferedWriter#write(java.lang.String) 
    * @see java.io.BufferedWriter#newLine() 
    */
    public void writeToFile(String content) {
        System.out.println("Writing to file: " + content);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(content);
            writer.newLine();
            System.out.println("Content written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
