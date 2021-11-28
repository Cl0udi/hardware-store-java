/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hardwarestore;

import java.io.IOException;
import java.io.File;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;


/**
 * This class handles the creation of a Logger object so it can
 * be saved correctly to an output file.
 * @author Alfonso
 */
public class Log {
    
    public Logger logger;
    FileHandler fh;
    
    public Log(String file_name) throws SecurityException, IOException{
        File f = new File(file_name);
        
        if(!f.exists()){
            f.createNewFile();
        }
        
        fh = new FileHandler(file_name, true);
        logger = Logger.getLogger("Test");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }
    
}
