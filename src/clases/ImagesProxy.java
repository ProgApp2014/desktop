/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import controlador.middleware.ImageHanlderWS;
import controlador.middleware.ImageHanlderWSService;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodro
 */
public class ImagesProxy {
    private final ImageHanlderWS controlador;

    public ImagesProxy() {
        ImageHanlderWSService servicio = new ImageHanlderWSService();
        controlador = servicio.getImageHanlderWSPort();
    }
    public String saveImage(InputStream is , String fileName){
        try {
            byte b [] = new byte[is.available()];
            is.read(b); 
            return controlador.saveImage(b, fileName);
        } catch (IOException ex) {
            System.out.println(ex.getMessage()+" "+ex.getStackTrace());
            Logger.getLogger(ImagesProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "errror";
    }
    public byte[] getImage(String fileName){
        System.out.println("estamos aca "+fileName);
        return controlador.getImage(fileName);
    }
}
