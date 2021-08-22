/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author keuangan
 */
public final class config {
    
    private static String caricepat = "", var = "", Key ="", Consid="", PEMBULATANHARGAOBAT="", namakamar="", link ="",version="";
    private static final Properties prop = new Properties();
    
    public static String cariCepat() {
        try {
            prop.loadFromXML(new FileInputStream("setting/config.xml"));
            caricepat = prop.getProperty("CARICEPAT");
        } catch (Exception e) {
            caricepat = "tidak aktif";
        }
        return caricepat;
    }

    public static String HOST() {
        try {
            prop.loadFromXML(new FileInputStream("setting/config.xml"));
            var = prop.getProperty("HOSTHYBRIDWEB");
        } catch (Exception e) {
            var = "localhost";
        }
        return var;
    }

    public static String HOSTHYBRIDWEB() {
        try {
            prop.loadFromXML(new FileInputStream("setting/config.xml"));
            var = prop.getProperty("HOSTHYBRIDWEB");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String HYBRIDWEB() {
        try {
            prop.loadFromXML(new FileInputStream("setting/config.xml"));
            var = prop.getProperty("HYBRIDWEB");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String PORTWEB() {
        try {
            prop.loadFromXML(new FileInputStream("setting/config.xml"));
            var = prop.getProperty("PORTWEB");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
    
    public static String ApiKeyBPJS(){
        try {
            prop.loadFromXML(new FileInputStream("setting/config.xml"));
            Key = EnkripsiAES.decrypt(prop.getProperty("SECRETKEYAPIBPJS"));
        } catch (Exception ex) {
            Key = "";
        }
        return Key;
    }
    
    public static String ApiConsBPJS(){
        try {
            prop.loadFromXML(new FileInputStream("setting/config.xml"));
            Consid = EnkripsiAES.decrypt(prop.getProperty("CONSIDAPIBPJS"));
        } catch (Exception ex) {
            Consid = "";
        }
        return Consid;
    }
    
    public static String linkBpjs(){
        try {
            prop.loadFromXML(new FileInputStream("setting/config.xml"));
            link = prop.getProperty("URLAPIBPJS");   
        } catch (Exception e) {
            System.out.println("E : "+e);
            link = "";
        }
        return link;
    }
    
    public static String versionBpjs(){
        try {
            prop.loadFromXML(new FileInputStream("setting/config.xml"));
            version = prop.getProperty("VERSIONAPIBPJS");   
        } catch (Exception e) {
            System.out.println("E : "+e);
            version = "";
        }
        return version;
    }
    
    public static String bulatHarga(){
        try{
            prop.loadFromXML(new FileInputStream("setting/config.xml"));
            PEMBULATANHARGAOBAT=prop.getProperty("PEMBULATANHARGAOBAT");
        }catch(Exception e){
            PEMBULATANHARGAOBAT="no";
        }
        return PEMBULATANHARGAOBAT;
    };
    
    public static String namaKamar(){
        try {
            prop.loadFromXML(new FileInputStream("setting/config.xml"));
            namakamar=prop.getProperty("KAMARAKTIFRANAP");
        } catch (Exception ex) {
            namakamar="";
        }
        return namakamar;
    }
}
