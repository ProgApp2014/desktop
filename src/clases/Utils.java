/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
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

    public static String formatDate(XMLGregorianCalendar fechaNacimiento) {
        return getFechaNacFormateada(fechaNacimiento.toGregorianCalendar());

    }

    public static XMLGregorianCalendar getXMLGregorianCalendarFromDate(Date date) {
        try {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(date);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static XMLGregorianCalendar getXMLGregorianCalendar(GregorianCalendar date) {
        try { 
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static String md5(String input) {

        String md5 = null;

        if (null == input) {
            return null;
        }

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }
    public static String formatString(String s) {

        if (s != null) {

            return s.trim();
        }
        return "";
    }
    public static Date getDateFromString(String value) {
        try {
            return new SimpleDateFormat(Constantes.DATE_FORMAT).parse(value);
        } catch (ParseException ex) {
            return null;
        }
    }
}
