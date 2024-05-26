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

public class FilesCreator {
    private File file;
    
    public void createTextFile() {
        file = new File("C:\\Users\\arroy\\OneDrive\\Documentos\\NetBeansProjects\\FinalProjectPOO-main\\build\\classes\\lastsoldier\\textfiles\\file.txt");
        
        try {
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(FilesCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeToFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
