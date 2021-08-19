package fungsi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author Owner
 */

/**
 *
 * @author Owner
 */
public final class var {
    private static final Connection koneksi=koneksiDB.condb();
    private static PreparedStatement ps,ps2;
    private static ResultSet rs,rs2;

    private static String kode="",kdbangsal="",namars="",alamatrs="",kabupatenrs="",propinsirs="",kontakrs="",emailrs="",form="",namauser="";
    private static int jml1=0,jml2=0,lebar=0,tinggi=0;
    private static boolean admin=false,user=false,status=false,manajemen=false,medis=false,paramedis=false,apoteker=false,laboratorium=false,radiologi=false,
            rekammedis=false,kasir=false,pengguna=false;

    public static void setData(String user, String pass) {
       try {
                ps=koneksi.prepareStatement("select * from admin where usere=AES_ENCRYPT(?,'nur') and passworde=AES_ENCRYPT(?,'windi')");
                ps2=koneksi.prepareStatement("select * from user where id_user=AES_ENCRYPT(?,'nur') and password=AES_ENCRYPT(?,'windi')");
                try {
                    ps.setString(1,user);
                    ps.setString(2,pass);
                    rs=ps.executeQuery();
                    rs.last();

                    ps2.setString(1,user);
                    ps2.setString(2,pass);
                    rs2=ps2.executeQuery();
                    rs2.last();

                    var.jml1=rs.getRow();
                    var.jml2=rs2.getRow();

                    if(rs.getRow()>=1){
                        var.kode="Admin Utama";
                        var.manajemen=true;
                        var.medis=true;
                        var.paramedis=true;
                        var.apoteker=true;
                        var.laboratorium=true;
                        var.radiologi=true;
                        var.rekammedis=true;
                        var.kasir=true;
                        var.pengguna=true;
                        var.admin=true;
                        var.user=true;
                    }else if(rs2.getRow()>=1){
                        rs2.beforeFirst();
                        rs2.next();
                        var.kode=user;
                        var.manajemen=rs2.getBoolean("manajemen");
                        var.medis=rs2.getBoolean("medis");
                        var.paramedis=rs2.getBoolean("paramedis");
                        var.apoteker=rs2.getBoolean("apoteker");
                        var.laboratorium=rs2.getBoolean("laboratorium");
                        var.radiologi=rs2.getBoolean("radiologi");
                        var.rekammedis=rs2.getBoolean("rekammedis");
                        var.kasir=rs2.getBoolean("kasir");
                        var.pengguna=rs2.getBoolean("pengguna");
                        var.admin=false;
                        var.user=false;
                    }else if((rs.getRow()==0)&&(rs2.getRow()==0)){
                        var.kode="";
                        var.manajemen= false;
                        var.medis= false;
                        var.paramedis= false;
                        var.apoteker=false;
                        var.laboratorium=false;
                        var.radiologi=false;
                        var.rekammedis=false;
                        var.kasir=false;
                        var.pengguna=false;
                        var.admin= false;
                        var.user= false;
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : "+e);
                } finally{
                    if(rs!=null){
                        rs.close();
                    }
                    if(rs2!=null){
                        rs2.close();
                    }
                    if(ps!=null){
                        ps.close();
                    }
                    if(ps2!=null){
                        ps2.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            }

    }

    public static int getjml1() {return var.jml1;}
    public static int getjml2() {return var.jml2;}
    public static boolean getadmin(){return var.admin;}
    public static boolean getuser(){return var.user;}
    public static boolean getmanajemen(){return var.manajemen;}
    public static boolean getmedis(){return var.medis;}
    public static boolean getparamedis(){return var.paramedis;}
    public static boolean getapoteker(){return var.apoteker;}
    public static boolean getlaboratorium(){return var.laboratorium;}
    public static boolean getradiologi(){return var.radiologi;}
    public static boolean getrekammedis(){return var.rekammedis;}
    public static boolean getkasir(){return var.kasir;}
    public static boolean getpengguna(){return var.pengguna;}
    public static String getkode(){return var.kode;}
    public static void setkdbangsal(String kdbangsal){var.kdbangsal=kdbangsal;}
    public static String getkdbangsal(){return var.kdbangsal;}
    public static void setform(String form){var.form=form;}
    public static String getform(){return var.form;}
    public static void setnamauser(String namauser){var.namauser=namauser;}
    public static String getnamauser(){return var.namauser;}
    public static void setstatus(boolean status){var.status=status;}
    public static boolean getstatus(){return var.status;}
    public static void setnamars(String namars){var.namars=namars;}
    public static void setalamatrs(String alamatrs){var.alamatrs=alamatrs;}
    public static void setkabupatenrs(String kabupatenrs){var.kabupatenrs=kabupatenrs;}
    public static void setpropinsirs(String propinsirs){var.propinsirs=propinsirs;}
    public static void setkontakrs(String kontakrs){var.kontakrs=kontakrs;}
    public static void setemailrs(String emailrs){var.emailrs=emailrs;}
    public static String getnamars(){return var.namars;}
    public static String getalamatrs(){return var.alamatrs;}
    public static String getkabupatenrs(){return var.kabupatenrs;}
    public static String getpropinsirs(){return var.propinsirs;}
    public static String getkontakrs(){return var.kontakrs;}
    public static String getemailrs(){return var.emailrs;}
}
