/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author rodro
 */
public class Utils {

    public static String getFechaNacFormateada(Calendar fechaNacimiento) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        String formatted = format1.format(fechaNacimiento.getTime());
        return formatted;
    }

    public static String getFechaNacFormateada(XMLGregorianCalendar fechaNacimiento) {
        return getFechaNacFormateada(fechaNacimiento.toGregorianCalendar());

    }
}
