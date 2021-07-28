
package simrskhanza;

import bridging.DlgSKDPBPJS;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import inventory.DlgCariObat2;
import inventory.DlgCariObat3;
import inventory.DlgCopyResep;
import inventory.DlgPeresepanDokter;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.Jurnal;
import laporan.DlgBerkasRawat;
import laporan.DlgDiagnosaPenyakit;
import permintaan.DlgPermintaanLaboratorium;
import permintaan.DlgPermintaanRadiologi;
//import rekammedis.RMDataAsuhanGizi;
//import rekammedis.RMDataMonitoringAsuhanGizi;

/**
 *
 * @author perpustakaan
 */
public final class DlgRawatInap extends javax.swing.JDialog {
    private final DefaultTableModel tabModeDr,tabModePr,tabModeDrPr,
            tabModePemeriksaan;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Jurnal jur=new Jurnal();
    private Connection koneksi=koneksiDB.condb();
    public  DlgCariPerawatanRanap perawatan=new DlgCariPerawatanRanap(null,false);
    public  DlgCariPerawatanRanap2 perawatan2=new DlgCariPerawatanRanap2(null,false);
    private DlgPasien pasien=new DlgPasien(null,false);
    private PreparedStatement ps,ps2,ps3,ps4,ps5,psrekening,ps6;
    private ResultSet rs,rsrekening;
    private final Properties prop = new Properties();
    private int i=0,tinggitombol=0;
    private boolean sukses=false;
    private double ttljmdokter=0,ttljmperawat=0,ttlkso=0,ttlpendapatan=0,ttljasasarana=0,ttlbhp=0,ttlmenejemen=0;
    private String Suspen_Piutang_Tindakan_Ranap="",Tindakan_Ranap="",Beban_Jasa_Medik_Dokter_Tindakan_Ranap="",Utang_Jasa_Medik_Dokter_Tindakan_Ranap="",
            Beban_Jasa_Medik_Paramedis_Tindakan_Ranap="",Utang_Jasa_Medik_Paramedis_Tindakan_Ranap="",Beban_KSO_Tindakan_Ranap="",Utang_KSO_Tindakan_Ranap="",
            Beban_Jasa_Sarana_Tindakan_Ranap="",Utang_Jasa_Sarana_Tindakan_Ranap="",Beban_Jasa_Menejemen_Tindakan_Ranap="",Utang_Jasa_Menejemen_Tindakan_Ranap="",
            HPP_BHP_Tindakan_Ranap="",Persediaan_BHP_Tindakan_Ranap="",kode_poli="";

    /** Creates new form DlgRawatInap
     * @param parent
     * @param modal */
    public DlgRawatInap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(885,674);
        
        tabModeDr=new DefaultTableModel(null,new Object[]{
            "P","No.Rawat","No.R.M.","Nama Pasien","Perawatan/Tindakan","Kode Dokter","Dokter Yg Menangani","Tgl.Rawat","Jam Rawat","Biaya","Kode","Tarif Dokter","KSO","Jasa Sarana","BHP","Menejemen"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbRawatDr.setModel(tabModeDr);
        //tampilDr();

        tbRawatDr.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRawatDr.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbRawatDr.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(180);
            }else if(i==4){
                column.setPreferredWidth(180);
            }else if(i==5){
                column.setPreferredWidth(90);
            }else if(i==6){
                column.setPreferredWidth(180);
            }else if(i==7){
                column.setPreferredWidth(80);
            }else if(i==8){
                column.setPreferredWidth(75);
            }else if(i==9){
                column.setPreferredWidth(90);
            }else if(i==10){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==11){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==12){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==13){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==14){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==15){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRawatDr.setDefaultRenderer(Object.class, new WarnaTable());

        tabModePr=new DefaultTableModel(null,new Object[]{
            "P","No.Rawat","No.R.M.","Nama Pasien","Perawatan/Tindakan","NIP","Petugas Yg Menangani","Tgl.Rawat","Jam Rawat","Biaya","Kode","Tarif Perawat","KSO","Jasa Sarana","BHP","Menejemen"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbRawatPr.setModel(tabModePr);
        tbRawatPr.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRawatPr.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbRawatPr.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(180);
            }else if(i==4){
                column.setPreferredWidth(180);
            }else if(i==5){
                column.setPreferredWidth(90);
            }else if(i==6){
                column.setPreferredWidth(180);
            }else if(i==7){
                column.setPreferredWidth(80);
            }else if(i==8){
                column.setPreferredWidth(75);
            }else if(i==9){
                column.setPreferredWidth(90);
            }else if(i==10){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==11){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==12){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==13){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==14){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==15){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRawatPr.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeDrPr=new DefaultTableModel(null,new Object[]{
            "P","No.Rawat","No.R.M.","Nama Pasien","Perawatan/Tindakan","Kode Dokter",
            "Dokter Yg Menangani","NIP","Petugas Yg Menangani","Tgl.Rawat","Jam Rawat",
            "Biaya","Kode","Tarif Dokter","Tarif Petugas","KSO","Jasa Sarana","BHP","Menejemen"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbRawatDrPr.setModel(tabModeDrPr);
        tbRawatDrPr.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRawatDrPr.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbRawatDrPr.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(180);
            }else if(i==4){
                column.setPreferredWidth(180);
            }else if(i==5){
                column.setPreferredWidth(90);
            }else if(i==6){
                column.setPreferredWidth(180);
            }else if(i==7){
                column.setPreferredWidth(90);
            }else if(i==8){
                column.setPreferredWidth(180);
            }else if(i==9){
                column.setPreferredWidth(80);
            }else if(i==10){
                column.setPreferredWidth(75);
            }else if(i==11){
                column.setPreferredWidth(90);
            }else if(i==12){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==13){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==14){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==15){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==16){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==17){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==18){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRawatDrPr.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModePemeriksaan=new DefaultTableModel(null,new Object[]{
            "P","No.Rawat","No.R.M.","Nama Pasien","Tgl.Rawat","Jam Rawat","Suhu(C)","Tensi","Nadi(/menit)",
            "Respirasi(/menit)","Tinggi(Cm)","Berat(Kg)","GCS(E,V,M)","Kesadaran","Subjek","Objek","Alergi","Asesmen","Plan"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbPemeriksaan.setModel(tabModePemeriksaan);
        tbPemeriksaan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPemeriksaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbPemeriksaan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(180);
            }else if(i==4){
                column.setPreferredWidth(80);
            }else if(i==5){
                column.setPreferredWidth(75);
            }else if(i==6){
                column.setPreferredWidth(75);
            }else if(i==7){
                column.setPreferredWidth(75);
            }else if(i==8){
                column.setPreferredWidth(75);
            }else if(i==9){
                column.setPreferredWidth(90);
            }else if(i==10){
                column.setPreferredWidth(75);
            }else if(i==11){
                column.setPreferredWidth(75);
            }else if(i==12){
                column.setPreferredWidth(75);
            }else if(i==13){
                column.setPreferredWidth(90);
            }else if(i==14){
                column.setPreferredWidth(180);
            }else if(i==15){
                column.setPreferredWidth(180);
            }else if(i==16){
                column.setPreferredWidth(130);
            }else if(i==17){
                column.setPreferredWidth(180);
            }else if(i==18){
                column.setPreferredWidth(180);
            }
        }
        tbPemeriksaan.setDefaultRenderer(Object.class, new WarnaTable());
        
        kdptg.setDocument(new batasInput((byte)20).getKata(kdptg));
        kdptg2.setDocument(new batasInput((byte)20).getKata(kdptg2));
        KdDok.setDocument(new batasInput((byte)20).getKata(KdDok));
        KdDok2.setDocument(new batasInput((byte)20).getKata(KdDok2));
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));        
        TKdPrw.setDocument(new batasInput((byte)15).getKata(TKdPrw));
        TKdPrw1.setDocument(new batasInput((byte)15).getKata(TKdPrw1));
        TKdPrw2.setDocument(new batasInput((byte)15).getKata(TKdPrw2));
        TSuhu.setDocument(new batasInput((byte)5).getKata(TSuhu));
        TTensi.setDocument(new batasInput((byte)8).getKata(TTensi));
        TCariPasien.setDocument(new batasInput((byte)20).getKata(TCariPasien));
        TKeluhan.setDocument(new batasInput((int)400).getKata(TKeluhan));      
        TPenilaian.setDocument(new batasInput((int)400).getKata(TPenilaian));  
        TindakLanjut.setDocument(new batasInput((int)400).getKata(TindakLanjut));        
        TTinggi.setDocument(new batasInput((byte)5).getKata(TTinggi));
        TBerat.setDocument(new batasInput((byte)5).getKata(TBerat));
        TNadi.setDocument(new batasInput((byte)3).getOnlyAngka(TNadi));
        TRespirasi.setDocument(new batasInput((byte)3).getOnlyAngka(TRespirasi));      
        TGCS.setDocument(new batasInput((byte)10).getKata(TGCS)); 
        TAlergi.setDocument(new batasInput((int)50).getKata(TAlergi));        
        TPemeriksaan.setDocument(new batasInput((int)400).getKata(TPemeriksaan));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));       
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        if(TabRawat.getSelectedIndex()==0){
                            tampilDr();
                        }else if(TabRawat.getSelectedIndex()==1){
                            tampilPr();
                        }else if(TabRawat.getSelectedIndex()==2){
                            tampilDrPr();
                        }else if(TabRawat.getSelectedIndex()==3){
                            tampilPemeriksaan();
                        }
                    }                        
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        if(TabRawat.getSelectedIndex()==0){
                            tampilDr();
                        }else if(TabRawat.getSelectedIndex()==1){
                            tampilPr();
                        }else if(TabRawat.getSelectedIndex()==2){
                            tampilDrPr();
                        }else if(TabRawat.getSelectedIndex()==3){
                            tampilPemeriksaan();
                        }
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        if(TabRawat.getSelectedIndex()==0){
                            tampilDr();
                        }else if(TabRawat.getSelectedIndex()==1){
                            tampilPr();
                        }else if(TabRawat.getSelectedIndex()==2){
                            tampilDrPr();
                        }else if(TabRawat.getSelectedIndex()==3){
                            tampilPemeriksaan();
                        }
                    }
                }
            });
        } 
        
        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgRawatInap")){
                    if(pasien.getTable().getSelectedRow()!= -1){                   
                        TCariPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),1).toString());
                    }    
/*                    if(pasien.getTable2().getSelectedRow()!= -1){                   
                        TCariPasien.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(),1).toString());
                    }
                    if(pasien.getTable3().getSelectedRow()!= -1){                   
                        TCariPasien.setText(pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(),1).toString());
                    }*/
                    TCariPasien.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(var.getform().equals("DlgRawatInap")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        pasien.dispose();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });  
        
/*        pasien.getTable2().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(var.getform().equals("DlgRawatInap")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        pasien.dispose();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        pasien.getTable3().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(var.getform().equals("DlgRawatInap")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        pasien.dispose();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });*/
        
        perawatan.dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgRawatInap")){
                    if(perawatan.dokter.getTable().getSelectedRow()!= -1){
                        if(TabRawat.getSelectedIndex()==0){
                            KdDok.setText(perawatan.dokter.getTable().getValueAt(perawatan.dokter.getTable().getSelectedRow(),0).toString());
                            TDokter.setText(perawatan.dokter.getTable().getValueAt(perawatan.dokter.getTable().getSelectedRow(),1).toString());
                            KdDok.requestFocus();
                        }else if(TabRawat.getSelectedIndex()==2){
                            KdDok2.setText(perawatan.dokter.getTable().getValueAt(perawatan.dokter.getTable().getSelectedRow(),0).toString());
                            TDokter2.setText(perawatan.dokter.getTable().getValueAt(perawatan.dokter.getTable().getSelectedRow(),1).toString());
                            KdDok2.requestFocus();
                        }  
                    }   
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        perawatan.petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgRawatInap")){
                    if(perawatan.petugas.getTable().getSelectedRow()!= -1){                   
                        if(TabRawat.getSelectedIndex()==1){
                            kdptg.setText(perawatan.petugas.getTable().getValueAt(perawatan.petugas.getTable().getSelectedRow(),0).toString());
                            TPerawat.setText(perawatan.petugas.getTable().getValueAt(perawatan.petugas.getTable().getSelectedRow(),1).toString());
                            kdptg.requestFocus();
                        }else if(TabRawat.getSelectedIndex()==2){
                            kdptg2.setText(perawatan.petugas.getTable().getValueAt(perawatan.petugas.getTable().getSelectedRow(),0).toString());
                            TPerawat2.setText(perawatan.petugas.getTable().getValueAt(perawatan.petugas.getTable().getSelectedRow(),1).toString());
                            kdptg2.requestFocus();
                        }
                    }      
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
                
        perawatan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgRawatInap")){
                    if(perawatan.getTable().getSelectedRow()!= -1){   
                        if(TabRawat.getSelectedIndex()==0){
                            TKdPrw.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),1).toString());   
                            TNmPrw.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),2).toString());
                            BagianRS.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),5).toString());
                            Bhp.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),6).toString());
                            JmDokter.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),7).toString());
                            JmPerawat.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),8).toString());
                            KSO.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),9).toString());
                            Menejemen.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),10).toString());
                            TTnd.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),4).toString());
                            TKdPrw.requestFocus();  
                        }else if(TabRawat.getSelectedIndex()==1){
                            TKdPrw1.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),1).toString());   
                            TNmPrw1.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),2).toString());
                            BagianRS.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),5).toString());
                            Bhp.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),6).toString());
                            JmDokter.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),7).toString());
                            JmPerawat.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),8).toString());
                            KSO.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),9).toString());
                            Menejemen.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),10).toString());
                            TTnd.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),4).toString());
                            TKdPrw1.requestFocus(); 
                        }else if(TabRawat.getSelectedIndex()==2){
                            TKdPrw2.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),1).toString());   
                            TNmPrw2.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),2).toString());
                            BagianRS.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),5).toString());
                            Bhp.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),6).toString());
                            JmDokter.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),7).toString());
                            JmPerawat.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),8).toString());
                            KSO.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),9).toString());
                            Menejemen.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),10).toString());
                            TTnd.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(),4).toString());
                            TKdPrw2.requestFocus(); 
                        }
                    }  
                    BtnCariActionPerformed(null);
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
             
        perawatan2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgRawatInap")){
                    if(perawatan2.getTable().getSelectedRow()!= -1){ 
                        if(TabRawat.getSelectedIndex()==0){
                            TKdPrw.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),4).toString());   
                            TNmPrw.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),5).toString());
                            BagianRS.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),8).toString());
                            Bhp.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),9).toString());
                            JmDokter.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),10).toString());
                            JmPerawat.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),11).toString());
                            KSO.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),12).toString());
                            Menejemen.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),13).toString());
                            TTnd.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),7).toString());
                            TKdPrw.requestFocus();
                        }else if(TabRawat.getSelectedIndex()==1){
                            TKdPrw1.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),4).toString());   
                            TNmPrw1.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),5).toString());
                            BagianRS.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),8).toString());
                            Bhp.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),9).toString());
                            JmDokter.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),10).toString());
                            JmPerawat.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),11).toString());
                            KSO.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),12).toString());
                            Menejemen.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),13).toString());
                            TTnd.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),7).toString());
                            TKdPrw1.requestFocus();
                        }else if(TabRawat.getSelectedIndex()==2){
                            TKdPrw2.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),4).toString());   
                            TNmPrw2.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),5).toString());
                            BagianRS.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),8).toString());
                            Bhp.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),9).toString());
                            JmDokter.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),10).toString());
                            JmPerawat.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),11).toString());
                            KSO.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),12).toString());
                            Menejemen.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),13).toString());
                            TTnd.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(),7).toString());
                            TKdPrw2.requestFocus();
                        }
                    } 
                    BtnCariActionPerformed(null);
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        ChkInput.setSelected(false);
        isForm(); 
        ChkAccor.setSelected(false);
        isMenu();
        jam();
        
        try {
            psrekening=koneksi.prepareStatement("select * from set_akun_ranap");
            try {
                rsrekening=psrekening.executeQuery();
                while(rsrekening.next()){
                    Suspen_Piutang_Tindakan_Ranap=rsrekening.getString("Suspen_Piutang_Tindakan_Ranap");
                    Tindakan_Ranap=rsrekening.getString("Tindakan_Ranap");
                    Beban_Jasa_Medik_Dokter_Tindakan_Ranap=rsrekening.getString("Beban_Jasa_Medik_Dokter_Tindakan_Ranap");
                    Utang_Jasa_Medik_Dokter_Tindakan_Ranap=rsrekening.getString("Utang_Jasa_Medik_Dokter_Tindakan_Ranap");
                    Beban_Jasa_Medik_Paramedis_Tindakan_Ranap=rsrekening.getString("Beban_Jasa_Medik_Paramedis_Tindakan_Ranap");
                    Utang_Jasa_Medik_Paramedis_Tindakan_Ranap=rsrekening.getString("Utang_Jasa_Medik_Paramedis_Tindakan_Ranap");
                    Beban_KSO_Tindakan_Ranap=rsrekening.getString("Beban_KSO_Tindakan_Ranap");
                    Utang_KSO_Tindakan_Ranap=rsrekening.getString("Utang_KSO_Tindakan_Ranap");
                    Beban_Jasa_Sarana_Tindakan_Ranap=rsrekening.getString("Beban_Jasa_Sarana_Tindakan_Ranap");
                    Utang_Jasa_Sarana_Tindakan_Ranap=rsrekening.getString("Utang_Jasa_Sarana_Tindakan_Ranap");
                    Beban_Jasa_Menejemen_Tindakan_Ranap=rsrekening.getString("Beban_Jasa_Menejemen_Tindakan_Ranap");
                    Utang_Jasa_Menejemen_Tindakan_Ranap=rsrekening.getString("Utang_Jasa_Menejemen_Tindakan_Ranap");
                    HPP_BHP_Tindakan_Ranap=rsrekening.getString("HPP_BHP_Tindakan_Ranap");
                    Persediaan_BHP_Tindakan_Ranap=rsrekening.getString("Persediaan_BHP_Tindakan_Ranap");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : "+e);
            } finally{
                if(rsrekening!=null){
                    rsrekening.close();
                }
                if(psrekening!=null){
                    psrekening.close();
                }
            }            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            prop.loadFromXML(new FileInputStream("setting/config.xml"));
        } catch (Exception ex) {
        }
    }
    
    


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BagianRS = new javax.swing.JTextField();
        Bhp = new javax.swing.JTextField();
        JmDokter = new javax.swing.JTextField();
        JmPerawat = new javax.swing.JTextField();
        TTnd = new javax.swing.JTextField();
        KSO = new javax.swing.JTextField();
        Menejemen = new javax.swing.JTextField();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel20 = new widget.Label();
        TCariPasien = new widget.TextBox();
        btnPasien = new widget.Button();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbRawatDr = new widget.Table();
        panelGlass7 = new widget.panelisi();
        jLabel5 = new widget.Label();
        KdDok = new widget.TextBox();
        BtnSeekDokter = new widget.Button();
        TDokter = new widget.TextBox();
        jLabel11 = new widget.Label();
        TKdPrw = new widget.TextBox();
        TNmPrw = new widget.TextBox();
        btnTindakan = new widget.Button();
        btnTindakan3 = new widget.Button();
        internalFrame3 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbRawatPr = new widget.Table();
        panelGlass13 = new widget.panelisi();
        jLabel13 = new widget.Label();
        kdptg = new widget.TextBox();
        BtnSeekPetugas = new widget.Button();
        TPerawat = new widget.TextBox();
        jLabel28 = new widget.Label();
        TKdPrw1 = new widget.TextBox();
        TNmPrw1 = new widget.TextBox();
        btnTindakan4 = new widget.Button();
        btnTindakan5 = new widget.Button();
        internalFrame4 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbRawatDrPr = new widget.Table();
        panelGlass11 = new widget.panelisi();
        jLabel14 = new widget.Label();
        kdptg2 = new widget.TextBox();
        BtnSeekPetugas2 = new widget.Button();
        TPerawat2 = new widget.TextBox();
        jLabel12 = new widget.Label();
        KdDok2 = new widget.TextBox();
        TDokter2 = new widget.TextBox();
        BtnSeekDokter2 = new widget.Button();
        jLabel29 = new widget.Label();
        TKdPrw2 = new widget.TextBox();
        TNmPrw2 = new widget.TextBox();
        btnTindakan6 = new widget.Button();
        btnTindakan7 = new widget.Button();
        internalFrame5 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbPemeriksaan = new widget.Table();
        PanelInput1 = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        panelGlass12 = new widget.panelisi();
        jLabel7 = new widget.Label();
        TSuhu = new widget.TextBox();
        jLabel4 = new widget.Label();
        TTensi = new widget.TextBox();
        jLabel25 = new widget.Label();
        TBerat = new widget.TextBox();
        TNadi = new widget.TextBox();
        jLabel24 = new widget.Label();
        TTinggi = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel15 = new widget.Label();
        TAlergi = new widget.TextBox();
        jLabel23 = new widget.Label();
        TRespirasi = new widget.TextBox();
        jLabel22 = new widget.Label();
        TGCS = new widget.TextBox();
        scrollPane1 = new widget.ScrollPane();
        TKeluhan = new widget.TextArea();
        scrollPane2 = new widget.ScrollPane();
        TPemeriksaan = new widget.TextArea();
        jLabel8 = new widget.Label();
        jLabel9 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        TPenilaian = new widget.TextArea();
        jLabel37 = new widget.Label();
        jLabel26 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        TindakLanjut = new widget.TextArea();
        jLabel41 = new widget.Label();
        cmbKesadaran = new widget.ComboBox();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        DTPTgl = new widget.Tanggal();
        jLabel18 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        ScrollMenu = new widget.ScrollPane();
        FormMenu = new widget.PanelBiasa();
        BtnResepObat = new widget.Button();
        BtnCopyResep = new widget.Button();
        BtnInputObat = new widget.Button();
        BtnObatBhp = new widget.Button();
        BtnBerkasDigital = new widget.Button();
        BtnPermintaanLab = new widget.Button();
        BtnPermintaanRad = new widget.Button();
        BtnSKDP = new widget.Button();
        BtnRujukKeluar = new widget.Button();
        BtnCatatan = new widget.Button();
        BtnDiagnosa = new widget.Button();
        BtnResume = new widget.Button();
        BtnRiwayat = new widget.Button();

        BagianRS.setEditable(false);
        BagianRS.setText("0");
        BagianRS.setName("BagianRS"); // NOI18N

        Bhp.setEditable(false);
        Bhp.setText("0");
        Bhp.setName("Bhp"); // NOI18N

        JmDokter.setEditable(false);
        JmDokter.setText("0");
        JmDokter.setName("JmDokter"); // NOI18N

        JmPerawat.setEditable(false);
        JmPerawat.setText("0");
        JmPerawat.setName("JmPerawat"); // NOI18N

        TTnd.setEditable(false);
        TTnd.setText("0");
        TTnd.setName("TTnd"); // NOI18N

        KSO.setEditable(false);
        KSO.setText("0");
        KSO.setName("KSO"); // NOI18N

        Menejemen.setEditable(false);
        Menejemen.setText("0");
        Menejemen.setName("Menejemen"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Perawatan/Tindakan Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setBackground(new java.awt.Color(215, 225, 215));
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setIconTextGap(3);
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setIconTextGap(3);
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setIconTextGap(3);
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setIconTextGap(3);
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setIconTextGap(3);
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setIconTextGap(3);
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnAll);

        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(95, 30));
        panelGlass8.add(jLabel10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(87, 30));
        panelGlass8.add(LCount);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setIconTextGap(3);
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel19.setText("Tgl.Rawat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(64, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-05-2021" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-05-2021" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(DTPCari2);

        jLabel20.setText("No.RM :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass10.add(jLabel20);

        TCariPasien.setName("TCariPasien"); // NOI18N
        TCariPasien.setPreferredSize(new java.awt.Dimension(140, 23));
        panelGlass10.add(TCariPasien);

        btnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/190.png"))); // NOI18N
        btnPasien.setMnemonic('6');
        btnPasien.setToolTipText("Alt+6");
        btnPasien.setName("btnPasien"); // NOI18N
        btnPasien.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienActionPerformed(evt);
            }
        });
        btnPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPasienKeyPressed(evt);
            }
        });
        panelGlass10.add(btnPasien);

        jSeparator5.setBackground(new java.awt.Color(220, 225, 215));
        jSeparator5.setForeground(new java.awt.Color(220, 225, 215));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setName("jSeparator5"); // NOI18N
        jSeparator5.setOpaque(true);
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass10.add(jSeparator5);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(273, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        BtnCari.setMnemonic('7');
        BtnCari.setToolTipText("Alt+7");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass10.add(BtnCari);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(255, 255, 253));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbRawatDr.setAutoCreateRowSorter(true);
        tbRawatDr.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRawatDr.setName("tbRawatDr"); // NOI18N
        tbRawatDr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRawatDrMouseClicked(evt);
            }
        });
        tbRawatDr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbRawatDrKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbRawatDr);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 74));
        panelGlass7.setLayout(null);

        jLabel5.setText("Dokter :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(0, 10, 105, 23);

        KdDok.setHighlighter(null);
        KdDok.setName("KdDok"); // NOI18N
        KdDok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDokKeyPressed(evt);
            }
        });
        panelGlass7.add(KdDok);
        KdDok.setBounds(108, 10, 130, 23);

        BtnSeekDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/190.png"))); // NOI18N
        BtnSeekDokter.setMnemonic('4');
        BtnSeekDokter.setToolTipText("ALt+4");
        BtnSeekDokter.setName("BtnSeekDokter"); // NOI18N
        BtnSeekDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekDokterActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnSeekDokter);
        BtnSeekDokter.setBounds(849, 10, 28, 23);

        TDokter.setEditable(false);
        TDokter.setHighlighter(null);
        TDokter.setName("TDokter"); // NOI18N
        panelGlass7.add(TDokter);
        TDokter.setBounds(240, 10, 606, 23);

        jLabel11.setText("Tindakan/Tagihan :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(0, 40, 105, 23);

        TKdPrw.setName("TKdPrw"); // NOI18N
        TKdPrw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKdPrwKeyPressed(evt);
            }
        });
        panelGlass7.add(TKdPrw);
        TKdPrw.setBounds(108, 40, 100, 23);

        TNmPrw.setEditable(false);
        TNmPrw.setName("TNmPrw"); // NOI18N
        panelGlass7.add(TNmPrw);
        TNmPrw.setBounds(210, 40, 605, 23);

        btnTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/190.png"))); // NOI18N
        btnTindakan.setMnemonic('3');
        btnTindakan.setToolTipText("Alt+3");
        btnTindakan.setName("btnTindakan"); // NOI18N
        btnTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTindakanActionPerformed(evt);
            }
        });
        btnTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnTindakanKeyPressed(evt);
            }
        });
        panelGlass7.add(btnTindakan);
        btnTindakan.setBounds(818, 40, 28, 23);

        btnTindakan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/190.png"))); // NOI18N
        btnTindakan3.setMnemonic('3');
        btnTindakan3.setToolTipText("Alt+3");
        btnTindakan3.setName("btnTindakan3"); // NOI18N
        btnTindakan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTindakan3ActionPerformed(evt);
            }
        });
        btnTindakan3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnTindakan3KeyPressed(evt);
            }
        });
        panelGlass7.add(btnTindakan3);
        btnTindakan3.setBounds(849, 40, 28, 23);

        internalFrame2.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Penanganan Dokter", internalFrame2);

        internalFrame3.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbRawatPr.setAutoCreateRowSorter(true);
        tbRawatPr.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRawatPr.setName("tbRawatPr"); // NOI18N
        tbRawatPr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRawatPrMouseClicked(evt);
            }
        });
        tbRawatPr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbRawatPrKeyReleased(evt);
            }
        });
        Scroll1.setViewportView(tbRawatPr);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 74));
        panelGlass13.setLayout(null);

        jLabel13.setText("Petugas :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass13.add(jLabel13);
        jLabel13.setBounds(0, 10, 105, 23);

        kdptg.setHighlighter(null);
        kdptg.setName("kdptg"); // NOI18N
        kdptg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdptgKeyPressed(evt);
            }
        });
        panelGlass13.add(kdptg);
        kdptg.setBounds(108, 10, 130, 23);

        BtnSeekPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/190.png"))); // NOI18N
        BtnSeekPetugas.setMnemonic('5');
        BtnSeekPetugas.setToolTipText("ALt+5");
        BtnSeekPetugas.setName("BtnSeekPetugas"); // NOI18N
        BtnSeekPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekPetugasActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnSeekPetugas);
        BtnSeekPetugas.setBounds(849, 10, 28, 23);

        TPerawat.setEditable(false);
        TPerawat.setBackground(new java.awt.Color(202, 202, 202));
        TPerawat.setHighlighter(null);
        TPerawat.setName("TPerawat"); // NOI18N
        panelGlass13.add(TPerawat);
        TPerawat.setBounds(240, 10, 606, 23);

        jLabel28.setText("Tindakan/Tagihan :");
        jLabel28.setName("jLabel28"); // NOI18N
        panelGlass13.add(jLabel28);
        jLabel28.setBounds(0, 40, 105, 23);

        TKdPrw1.setName("TKdPrw1"); // NOI18N
        TKdPrw1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKdPrw1KeyPressed(evt);
            }
        });
        panelGlass13.add(TKdPrw1);
        TKdPrw1.setBounds(108, 40, 100, 23);

        TNmPrw1.setEditable(false);
        TNmPrw1.setName("TNmPrw1"); // NOI18N
        panelGlass13.add(TNmPrw1);
        TNmPrw1.setBounds(210, 40, 605, 23);

        btnTindakan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/190.png"))); // NOI18N
        btnTindakan4.setMnemonic('3');
        btnTindakan4.setToolTipText("Alt+3");
        btnTindakan4.setName("btnTindakan4"); // NOI18N
        btnTindakan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTindakan4ActionPerformed(evt);
            }
        });
        btnTindakan4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnTindakan4KeyPressed(evt);
            }
        });
        panelGlass13.add(btnTindakan4);
        btnTindakan4.setBounds(818, 40, 28, 23);

        btnTindakan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/190.png"))); // NOI18N
        btnTindakan5.setMnemonic('3');
        btnTindakan5.setToolTipText("Alt+3");
        btnTindakan5.setName("btnTindakan5"); // NOI18N
        btnTindakan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTindakan5ActionPerformed(evt);
            }
        });
        btnTindakan5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnTindakan5KeyPressed(evt);
            }
        });
        panelGlass13.add(btnTindakan5);
        btnTindakan5.setBounds(849, 40, 28, 23);

        internalFrame3.add(panelGlass13, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Penanganan Petugas", internalFrame3);

        internalFrame4.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbRawatDrPr.setAutoCreateRowSorter(true);
        tbRawatDrPr.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRawatDrPr.setName("tbRawatDrPr"); // NOI18N
        tbRawatDrPr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRawatDrPrMouseClicked(evt);
            }
        });
        tbRawatDrPr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbRawatDrPrKeyReleased(evt);
            }
        });
        Scroll2.setViewportView(tbRawatDrPr);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 104));
        panelGlass11.setLayout(null);

        jLabel14.setText("Petugas :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass11.add(jLabel14);
        jLabel14.setBounds(0, 40, 105, 23);

        kdptg2.setHighlighter(null);
        kdptg2.setName("kdptg2"); // NOI18N
        kdptg2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdptg2KeyPressed(evt);
            }
        });
        panelGlass11.add(kdptg2);
        kdptg2.setBounds(108, 40, 130, 23);

        BtnSeekPetugas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/190.png"))); // NOI18N
        BtnSeekPetugas2.setMnemonic('5');
        BtnSeekPetugas2.setToolTipText("ALt+5");
        BtnSeekPetugas2.setName("BtnSeekPetugas2"); // NOI18N
        BtnSeekPetugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekPetugas2ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnSeekPetugas2);
        BtnSeekPetugas2.setBounds(849, 40, 28, 23);

        TPerawat2.setEditable(false);
        TPerawat2.setBackground(new java.awt.Color(202, 202, 202));
        TPerawat2.setHighlighter(null);
        TPerawat2.setName("TPerawat2"); // NOI18N
        panelGlass11.add(TPerawat2);
        TPerawat2.setBounds(240, 40, 606, 23);

        jLabel12.setText("Dokter :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass11.add(jLabel12);
        jLabel12.setBounds(0, 10, 105, 23);

        KdDok2.setHighlighter(null);
        KdDok2.setName("KdDok2"); // NOI18N
        KdDok2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDok2KeyPressed(evt);
            }
        });
        panelGlass11.add(KdDok2);
        KdDok2.setBounds(108, 10, 130, 23);

        TDokter2.setEditable(false);
        TDokter2.setHighlighter(null);
        TDokter2.setName("TDokter2"); // NOI18N
        panelGlass11.add(TDokter2);
        TDokter2.setBounds(240, 10, 606, 23);

        BtnSeekDokter2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/190.png"))); // NOI18N
        BtnSeekDokter2.setMnemonic('4');
        BtnSeekDokter2.setToolTipText("ALt+4");
        BtnSeekDokter2.setName("BtnSeekDokter2"); // NOI18N
        BtnSeekDokter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekDokter2ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnSeekDokter2);
        BtnSeekDokter2.setBounds(849, 10, 28, 23);

        jLabel29.setText("Tindakan/Tagihan :");
        jLabel29.setName("jLabel29"); // NOI18N
        panelGlass11.add(jLabel29);
        jLabel29.setBounds(0, 70, 105, 23);

        TKdPrw2.setName("TKdPrw2"); // NOI18N
        TKdPrw2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKdPrw2KeyPressed(evt);
            }
        });
        panelGlass11.add(TKdPrw2);
        TKdPrw2.setBounds(108, 70, 100, 23);

        TNmPrw2.setEditable(false);
        TNmPrw2.setName("TNmPrw2"); // NOI18N
        panelGlass11.add(TNmPrw2);
        TNmPrw2.setBounds(210, 70, 605, 23);

        btnTindakan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/190.png"))); // NOI18N
        btnTindakan6.setMnemonic('3');
        btnTindakan6.setToolTipText("Alt+3");
        btnTindakan6.setName("btnTindakan6"); // NOI18N
        btnTindakan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTindakan6ActionPerformed(evt);
            }
        });
        btnTindakan6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnTindakan6KeyPressed(evt);
            }
        });
        panelGlass11.add(btnTindakan6);
        btnTindakan6.setBounds(818, 70, 28, 23);

        btnTindakan7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/190.png"))); // NOI18N
        btnTindakan7.setMnemonic('3');
        btnTindakan7.setToolTipText("Alt+3");
        btnTindakan7.setName("btnTindakan7"); // NOI18N
        btnTindakan7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTindakan7ActionPerformed(evt);
            }
        });
        btnTindakan7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnTindakan7KeyPressed(evt);
            }
        });
        panelGlass11.add(btnTindakan7);
        btnTindakan7.setBounds(849, 70, 28, 23);

        internalFrame4.add(panelGlass11, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Penanganan Dokter & Petugas", internalFrame4);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbPemeriksaan.setAutoCreateRowSorter(true);
        tbPemeriksaan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemeriksaan.setName("tbPemeriksaan"); // NOI18N
        tbPemeriksaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemeriksaanMouseClicked(evt);
            }
        });
        tbPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPemeriksaanKeyReleased(evt);
            }
        });
        Scroll3.setViewportView(tbPemeriksaan);

        internalFrame5.add(Scroll3, java.awt.BorderLayout.CENTER);

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(192, 185));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/145.png"))); // NOI18N
        ChkInput.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkInputItemStateChanged(evt);
            }
        });
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput1.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass12.setLayout(null);

        jLabel7.setText("Suhu (°C) :");
        jLabel7.setName("jLabel7"); // NOI18N
        panelGlass12.add(jLabel7);
        jLabel7.setBounds(0, 100, 70, 23);

        TSuhu.setFocusTraversalPolicyProvider(true);
        TSuhu.setName("TSuhu"); // NOI18N
        TSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSuhuKeyPressed(evt);
            }
        });
        panelGlass12.add(TSuhu);
        TSuhu.setBounds(73, 100, 55, 23);

        jLabel4.setText("Tensi :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass12.add(jLabel4);
        jLabel4.setBounds(0, 130, 70, 23);

        TTensi.setHighlighter(null);
        TTensi.setName("TTensi"); // NOI18N
        TTensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTensiKeyPressed(evt);
            }
        });
        panelGlass12.add(TTensi);
        TTensi.setBounds(73, 130, 55, 23);

        jLabel25.setText("Berat(Kg) :");
        jLabel25.setName("jLabel25"); // NOI18N
        panelGlass12.add(jLabel25);
        jLabel25.setBounds(294, 100, 79, 23);

        TBerat.setHighlighter(null);
        TBerat.setName("TBerat"); // NOI18N
        TBerat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBeratKeyPressed(evt);
            }
        });
        panelGlass12.add(TBerat);
        TBerat.setBounds(378, 100, 55, 23);

        TNadi.setFocusTraversalPolicyProvider(true);
        TNadi.setName("TNadi"); // NOI18N
        TNadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNadiActionPerformed(evt);
            }
        });
        TNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNadiKeyPressed(evt);
            }
        });
        panelGlass12.add(TNadi);
        TNadi.setBounds(378, 130, 55, 23);

        jLabel24.setText("Nadi(/menit) :");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass12.add(jLabel24);
        jLabel24.setBounds(294, 130, 79, 23);

        TTinggi.setFocusTraversalPolicyProvider(true);
        TTinggi.setName("TTinggi"); // NOI18N
        TTinggi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTinggiKeyPressed(evt);
            }
        });
        panelGlass12.add(TTinggi);
        TTinggi.setBounds(237, 100, 55, 23);

        jLabel16.setText("Tinggi Badan(Cm) :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass12.add(jLabel16);
        jLabel16.setBounds(134, 100, 100, 23);

        jLabel15.setText("Alergi :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass12.add(jLabel15);
        jLabel15.setBounds(450, 40, 90, 23);

        TAlergi.setHighlighter(null);
        TAlergi.setName("TAlergi"); // NOI18N
        TAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlergiKeyPressed(evt);
            }
        });
        panelGlass12.add(TAlergi);
        TAlergi.setBounds(543, 40, 360, 23);

        jLabel23.setText("Respirasi(/menit) :");
        jLabel23.setName("jLabel23"); // NOI18N
        panelGlass12.add(jLabel23);
        jLabel23.setBounds(134, 130, 100, 23);

        TRespirasi.setHighlighter(null);
        TRespirasi.setName("TRespirasi"); // NOI18N
        TRespirasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRespirasiKeyPressed(evt);
            }
        });
        panelGlass12.add(TRespirasi);
        TRespirasi.setBounds(237, 130, 55, 23);

        jLabel22.setText("GCS(E,V,M) :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass12.add(jLabel22);
        jLabel22.setBounds(450, 10, 90, 23);

        TGCS.setFocusTraversalPolicyProvider(true);
        TGCS.setName("TGCS"); // NOI18N
        TGCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TGCSKeyPressed(evt);
            }
        });
        panelGlass12.add(TGCS);
        TGCS.setBounds(543, 10, 55, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        TKeluhan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TKeluhan.setColumns(20);
        TKeluhan.setRows(5);
        TKeluhan.setName("TKeluhan"); // NOI18N
        TKeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKeluhanKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(TKeluhan);

        panelGlass12.add(scrollPane1);
        scrollPane1.setBounds(73, 10, 360, 38);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        TPemeriksaan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TPemeriksaan.setColumns(20);
        TPemeriksaan.setRows(5);
        TPemeriksaan.setName("TPemeriksaan"); // NOI18N
        TPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(TPemeriksaan);

        panelGlass12.add(scrollPane2);
        scrollPane2.setBounds(73, 55, 360, 38);

        jLabel8.setText("Subjek :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass12.add(jLabel8);
        jLabel8.setBounds(0, 10, 70, 23);

        jLabel9.setText("Objek :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass12.add(jLabel9);
        jLabel9.setBounds(0, 55, 70, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        TPenilaian.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TPenilaian.setColumns(20);
        TPenilaian.setRows(5);
        TPenilaian.setName("TPenilaian"); // NOI18N
        TPenilaian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPenilaianKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(TPenilaian);

        panelGlass12.add(scrollPane3);
        scrollPane3.setBounds(543, 70, 360, 38);

        jLabel37.setText("Asesmen :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelGlass12.add(jLabel37);
        jLabel37.setBounds(450, 70, 90, 23);

        jLabel26.setText("Plan :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelGlass12.add(jLabel26);
        jLabel26.setBounds(450, 115, 90, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        TindakLanjut.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TindakLanjut.setColumns(20);
        TindakLanjut.setRows(5);
        TindakLanjut.setName("TindakLanjut"); // NOI18N
        TindakLanjut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindakLanjutKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(TindakLanjut);

        panelGlass12.add(scrollPane4);
        scrollPane4.setBounds(543, 115, 360, 38);

        jLabel41.setText("Kesadaran :");
        jLabel41.setName("jLabel41"); // NOI18N
        panelGlass12.add(jLabel41);
        jLabel41.setBounds(624, 10, 80, 23);

        cmbKesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos Mentis", "Somnolence", "Sopor", "Coma" }));
        cmbKesadaran.setName("cmbKesadaran"); // NOI18N
        cmbKesadaran.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbKesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbKesadaranKeyPressed(evt);
            }
        });
        panelGlass12.add(cmbKesadaran);
        cmbKesadaran.setBounds(707, 10, 196, 23);

        PanelInput1.add(panelGlass12, java.awt.BorderLayout.CENTER);

        internalFrame5.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Pemeriksaan", internalFrame5);

        TabRawat.setSelectedIndex(3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        FormInput.setBackground(new java.awt.Color(215, 225, 215));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(260, 43));
        FormInput.setLayout(null);

        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(null);
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 10, 70, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 125, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(201, 10, 80, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(283, 10, 260, 23);

        DTPTgl.setForeground(new java.awt.Color(50, 70, 50));
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-05-2021" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(617, 10, 90, 23);

        jLabel18.setText("Tanggal :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(554, 10, 60, 23);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(711, 10, 62, 23);

        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(776, 10, 62, 23);

        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(841, 10, 62, 23);

        ChkJln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkJln.setSelected(true);
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJlnActionPerformed(evt);
            }
        });
        FormInput.add(ChkJln);
        ChkJln.setBounds(906, 10, 23, 23);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(135, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setBackground(new java.awt.Color(255, 250, 248));
        ChkAccor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 255, 248)));
        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/kanan.png"))); // NOI18N
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(15, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/kanan.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/kiri.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/kiri.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.EAST);

        ScrollMenu.setBorder(null);
        ScrollMenu.setName("ScrollMenu"); // NOI18N
        ScrollMenu.setOpaque(true);

        FormMenu.setBackground(new java.awt.Color(255, 255, 255));
        FormMenu.setBorder(null);
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(115, 43));
        FormMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 1));

        BtnResepObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/item.png"))); // NOI18N
        BtnResepObat.setText("Input Resep");
        BtnResepObat.setFocusPainted(false);
        BtnResepObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnResepObat.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnResepObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnResepObat.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnResepObat.setName("BtnResepObat"); // NOI18N
        BtnResepObat.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnResepObat.setRoundRect(false);
        BtnResepObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepObatActionPerformed(evt);
            }
        });
        FormMenu.add(BtnResepObat);

        BtnCopyResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/item.png"))); // NOI18N
        BtnCopyResep.setText("Copy Resep");
        BtnCopyResep.setFocusPainted(false);
        BtnCopyResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnCopyResep.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnCopyResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnCopyResep.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnCopyResep.setName("BtnCopyResep"); // NOI18N
        BtnCopyResep.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnCopyResep.setRoundRect(false);
        BtnCopyResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopyResepActionPerformed(evt);
            }
        });
        FormMenu.add(BtnCopyResep);

        BtnInputObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/item.png"))); // NOI18N
        BtnInputObat.setText("Input Obat & BHP");
        BtnInputObat.setFocusPainted(false);
        BtnInputObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnInputObat.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnInputObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnInputObat.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnInputObat.setName("BtnInputObat"); // NOI18N
        BtnInputObat.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnInputObat.setRoundRect(false);
        BtnInputObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInputObatActionPerformed(evt);
            }
        });
        FormMenu.add(BtnInputObat);

        BtnObatBhp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/item.png"))); // NOI18N
        BtnObatBhp.setText("Data Obat & BHP");
        BtnObatBhp.setFocusPainted(false);
        BtnObatBhp.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnObatBhp.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnObatBhp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnObatBhp.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnObatBhp.setName("BtnObatBhp"); // NOI18N
        BtnObatBhp.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnObatBhp.setRoundRect(false);
        BtnObatBhp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObatBhpActionPerformed(evt);
            }
        });
        FormMenu.add(BtnObatBhp);

        BtnBerkasDigital.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/item.png"))); // NOI18N
        BtnBerkasDigital.setText("Berkas Digital");
        BtnBerkasDigital.setFocusPainted(false);
        BtnBerkasDigital.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnBerkasDigital.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnBerkasDigital.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnBerkasDigital.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnBerkasDigital.setName("BtnBerkasDigital"); // NOI18N
        BtnBerkasDigital.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnBerkasDigital.setRoundRect(false);
        BtnBerkasDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBerkasDigitalActionPerformed(evt);
            }
        });
        FormMenu.add(BtnBerkasDigital);

        BtnPermintaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/item.png"))); // NOI18N
        BtnPermintaanLab.setText("Permintaan Lab");
        BtnPermintaanLab.setFocusPainted(false);
        BtnPermintaanLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnPermintaanLab.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnPermintaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPermintaanLab.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnPermintaanLab.setName("BtnPermintaanLab"); // NOI18N
        BtnPermintaanLab.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnPermintaanLab.setRoundRect(false);
        BtnPermintaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPermintaanLabActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPermintaanLab);

        BtnPermintaanRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/item.png"))); // NOI18N
        BtnPermintaanRad.setText("Permintaan Rad");
        BtnPermintaanRad.setFocusPainted(false);
        BtnPermintaanRad.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnPermintaanRad.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnPermintaanRad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPermintaanRad.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnPermintaanRad.setName("BtnPermintaanRad"); // NOI18N
        BtnPermintaanRad.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnPermintaanRad.setRoundRect(false);
        BtnPermintaanRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPermintaanRadActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPermintaanRad);

        BtnSKDP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/item.png"))); // NOI18N
        BtnSKDP.setText("Data SKDP");
        BtnSKDP.setFocusPainted(false);
        BtnSKDP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnSKDP.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnSKDP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnSKDP.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnSKDP.setName("BtnSKDP"); // NOI18N
        BtnSKDP.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnSKDP.setRoundRect(false);
        BtnSKDP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSKDPActionPerformed(evt);
            }
        });
        FormMenu.add(BtnSKDP);

        BtnRujukKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/item.png"))); // NOI18N
        BtnRujukKeluar.setText("Rujuk Keluar");
        BtnRujukKeluar.setFocusPainted(false);
        BtnRujukKeluar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnRujukKeluar.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnRujukKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnRujukKeluar.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnRujukKeluar.setName("BtnRujukKeluar"); // NOI18N
        BtnRujukKeluar.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnRujukKeluar.setRoundRect(false);
        BtnRujukKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRujukKeluarActionPerformed(evt);
            }
        });
        FormMenu.add(BtnRujukKeluar);

        BtnCatatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/item.png"))); // NOI18N
        BtnCatatan.setText("Catatan Pasien");
        BtnCatatan.setFocusPainted(false);
        BtnCatatan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnCatatan.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnCatatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnCatatan.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnCatatan.setName("BtnCatatan"); // NOI18N
        BtnCatatan.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnCatatan.setRoundRect(false);
        BtnCatatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCatatanActionPerformed(evt);
            }
        });
        FormMenu.add(BtnCatatan);

        BtnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/item.png"))); // NOI18N
        BtnDiagnosa.setText("Diagnosa");
        BtnDiagnosa.setFocusPainted(false);
        BtnDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnDiagnosa.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnDiagnosa.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnDiagnosa.setName("BtnDiagnosa"); // NOI18N
        BtnDiagnosa.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnDiagnosa.setRoundRect(false);
        BtnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagnosaActionPerformed(evt);
            }
        });
        FormMenu.add(BtnDiagnosa);

        BtnResume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/item.png"))); // NOI18N
        BtnResume.setText("Resume Pasien");
        BtnResume.setFocusPainted(false);
        BtnResume.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnResume.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnResume.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnResume.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnResume.setName("BtnResume"); // NOI18N
        BtnResume.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnResume.setRoundRect(false);
        BtnResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResumeActionPerformed(evt);
            }
        });
        FormMenu.add(BtnResume);

        BtnRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/item.png"))); // NOI18N
        BtnRiwayat.setText("Riwayat Pasien");
        BtnRiwayat.setFocusPainted(false);
        BtnRiwayat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnRiwayat.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnRiwayat.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnRiwayat.setName("BtnRiwayat"); // NOI18N
        BtnRiwayat.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnRiwayat.setRoundRect(false);
        BtnRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRiwayatActionPerformed(evt);
            }
        });
        FormMenu.add(BtnRiwayat);

        ScrollMenu.setViewportView(FormMenu);

        PanelAccor.add(ScrollMenu, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelAccor, java.awt.BorderLayout.WEST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
            isPsien();
        }else{         
            if(TabRawat.getSelectedIndex()==0){
                Valid.pindah(evt,DTPTgl,KdDok);
            }else if(TabRawat.getSelectedIndex()==1){
                Valid.pindah(evt,DTPTgl,kdptg);
            }else if(TabRawat.getSelectedIndex()==2){
                Valid.pindah(evt,DTPTgl,KdDok2);
            }else if(TabRawat.getSelectedIndex()==3){
                Valid.pindah(evt,DTPTgl,TKeluhan);
            }
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TSuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSuhuKeyPressed
        Valid.pindah(evt,TPemeriksaan,TTinggi);
}//GEN-LAST:event_TSuhuKeyPressed

    private void TTensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTensiKeyPressed
        Valid.pindah(evt,TBerat,TRespirasi);
}//GEN-LAST:event_TTensiKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else{            
            switch (TabRawat.getSelectedIndex()) {
                case 0:
                    if(KdDok.getText().trim().equals("")||TDokter.getText().trim().equals("")){
                        Valid.textKosong(KdDok,"Dokter");
                    }else if(TKdPrw.getText().trim().equals("")||TNmPrw.getText().trim().equals("")){
                        Valid.textKosong(TKdPrw,"perawatan");
                    }else{
                        if(Sequel.cariRegistrasi(TNoRw.getText().trim())>0){
                            JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                            TCari.requestFocus();
                        }else{
                            Sequel.AutoComitFalse();
                            sukses=true;
                            if(Sequel.menyimpantf("rawat_inap_dr","?,?,?,?,?,?,?,?,?,?,?","Data",11,new String[]{
                                TNoRw.getText(),TKdPrw.getText(),KdDok.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),
                                cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),
                                BagianRS.getText(),Bhp.getText(),JmDokter.getText(),KSO.getText(),Menejemen.getText(),TTnd.getText()
                            })==true){
                                Sequel.queryu("delete from tampjurnal");
                                if(Valid.SetAngka(TTnd.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Tindakan_Ranap+"','Suspen Piutang Tindakan Ranap','"+TTnd.getText()+"','0'","debet=debet+'"+(TTnd.getText())+"'","kd_rek='"+Suspen_Piutang_Tindakan_Ranap+"'");                              
                                    Sequel.menyimpan("tampjurnal","'"+Tindakan_Ranap+"','Pendapatan Tindakan Rawat Inap','0','"+TTnd.getText()+"'","kredit=kredit+'"+(TTnd.getText())+"'","kd_rek='"+Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(JmDokter.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"','Beban Jasa Medik Dokter Tindakan Ranap','"+JmDokter.getText()+"','0'","debet=debet+'"+(JmDokter.getText())+"'","kd_rek='"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"','Utang Jasa Medik Dokter Tindakan Ranap','0','"+JmDokter.getText()+"'","kredit=kredit+'"+(JmDokter.getText())+"'","kd_rek='"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(KSO.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Beban_KSO_Tindakan_Ranap+"','Beban KSO Tindakan Ranap','"+KSO.getText()+"','0'","debet=debet+'"+(KSO.getText())+"'","kd_rek='"+Beban_KSO_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Utang_KSO_Tindakan_Ranap+"','Utang KSO Tindakan Ranap','0','"+KSO.getText()+"'","kredit=kredit+'"+(KSO.getText())+"'","kd_rek='"+Utang_KSO_Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(BagianRS.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Sarana_Tindakan_Ranap+"','Beban Jasa Sarana Tindakan Ranap','"+BagianRS.getText()+"','0'","debet=debet+'"+(BagianRS.getText())+"'","kd_rek='"+Beban_Jasa_Sarana_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Sarana_Tindakan_Ranap+"','Utang Jasa Sarana Tindakan Ranap','0','"+BagianRS.getText()+"'","kredit=kredit+'"+(BagianRS.getText())+"'","kd_rek='"+Utang_Jasa_Sarana_Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(Bhp.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+HPP_BHP_Tindakan_Ranap+"','HPP BHP Tindakan Ranap','"+Bhp.getText()+"','0'","debet=debet+'"+(Bhp.getText())+"'","kd_rek='"+HPP_BHP_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Persediaan_BHP_Tindakan_Ranap+"','Persediaan BHP Tindakan Ranap','0','"+Bhp.getText()+"'","kredit=kredit+'"+(Bhp.getText())+"'","kd_rek='"+Persediaan_BHP_Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(Menejemen.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Menejemen_Tindakan_Ranap+"','Beban Jasa Menejemen Tindakan Ranap','"+Menejemen.getText()+"','0'","debet=debet+'"+(Menejemen.getText())+"'","kd_rek='"+Beban_Jasa_Menejemen_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Menejemen_Tindakan_Ranap+"','Utang Jasa Menejemen Tindakan Ranap','0','"+Menejemen.getText()+"'","kredit=kredit+'"+(Menejemen.getText())+"'","kd_rek='"+Utang_Jasa_Menejemen_Tindakan_Ranap+"'");   
                                }
                                /*if(jur.simpanJurnal(TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),"U","TINDAKAN RAWAT INAP PASIEN "+TPasien.getText()+" DIPOSTING OLEH "+var.getkode())==false){
                                    sukses=false;
                                }*/
                            }else{
                                sukses=false;
                            }
                            
                            if(sukses==true){
                                //Sequel.Commit();
                                tampilDr();
                                BtnBatalActionPerformed(evt);
                            }else{
                                //JOptionPane.showMessageDialog(null,"Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
                                //Sequel.RollBack();
                            }
                            Sequel.AutoComitTrue();
                        }
                    }   break;
                case 1:
                    if(kdptg.getText().trim().equals("")||TPerawat.getText().trim().equals("")){                              
                        Valid.textKosong(kdptg,"Petugas");
                    }else if(TKdPrw1.getText().trim().equals("")||TNmPrw1.getText().trim().equals("")){
                        Valid.textKosong(TKdPrw1,"perawatan");
                    }else{
                        if(Sequel.cariRegistrasi(TNoRw.getText().trim())>0){
                            JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                            TCari.requestFocus();
                        }else{
                            Sequel.AutoComitFalse();
                            sukses=true;
                            if(Sequel.menyimpantf("rawat_inap_pr","?,?,?,?,?,?,?,?,?,?,?","Data",11,new String[]{
                                TNoRw.getText(),TKdPrw1.getText(),kdptg.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),
                                cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),
                                BagianRS.getText(),Bhp.getText(),JmPerawat.getText(),KSO.getText(),Menejemen.getText(),TTnd.getText()
                            })==true){
                                Sequel.queryu("delete from tampjurnal");
                                if(Valid.SetAngka(TTnd.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Tindakan_Ranap+"','Suspen Piutang Tindakan Ranap','"+TTnd.getText()+"','0'","debet=debet+'"+(TTnd.getText())+"'","kd_rek='"+Suspen_Piutang_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Tindakan_Ranap+"','Pendapatan Tindakan Rawat Inap','0','"+TTnd.getText()+"'","kredit=kredit+'"+(TTnd.getText())+"'","kd_rek='"+Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(JmPerawat.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Beban Jasa Medik Paramedis Tindakan Ranap','"+JmPerawat.getText()+"','0'","debet=debet+'"+(JmPerawat.getText())+"'","kd_rek='"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Utang Jasa Medik Paramedis Tindakan Ranap','0','"+JmPerawat.getText()+"'","kredit=kredit+'"+(JmPerawat.getText())+"'","kd_rek='"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(KSO.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Beban_KSO_Tindakan_Ranap+"','Beban KSO Tindakan Ranap','"+KSO.getText()+"','0'","debet=debet+'"+(KSO.getText())+"'","kd_rek='"+Beban_KSO_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Utang_KSO_Tindakan_Ranap+"','Utang KSO Tindakan Ranap','0','"+KSO.getText()+"'","kredit=kredit+'"+(KSO.getText())+"'","kd_rek='"+Utang_KSO_Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(BagianRS.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Sarana_Tindakan_Ranap+"','Beban Jasa Sarana Tindakan Ranap','"+BagianRS.getText()+"','0'","debet=debet+'"+(BagianRS.getText())+"'","kd_rek='"+Beban_Jasa_Sarana_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Sarana_Tindakan_Ranap+"','Utang Jasa Sarana Tindakan Ranap','0','"+BagianRS.getText()+"'","kredit=kredit+'"+(BagianRS.getText())+"'","kd_rek='"+Utang_Jasa_Sarana_Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(Bhp.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+HPP_BHP_Tindakan_Ranap+"','HPP BHP Tindakan Ranap','"+Bhp.getText()+"','0'","debet=debet+'"+(Bhp.getText())+"'","kd_rek='"+HPP_BHP_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Persediaan_BHP_Tindakan_Ranap+"','Persediaan BHP Tindakan Ranap','0','"+Bhp.getText()+"'","kredit=kredit+'"+(Bhp.getText())+"'","kd_rek='"+Persediaan_BHP_Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(Menejemen.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Menejemen_Tindakan_Ranap+"','Beban Jasa Menejemen Tindakan Ranap','"+Menejemen.getText()+"','0'","debet=debet+'"+(Menejemen.getText())+"'","kd_rek='"+Beban_Jasa_Menejemen_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Menejemen_Tindakan_Ranap+"','Utang Jasa Menejemen Tindakan Ranap','0','"+Menejemen.getText()+"'","kredit=kredit+'"+(Menejemen.getText())+"'","kd_rek='"+Utang_Jasa_Menejemen_Tindakan_Ranap+"'");   
                                }
                                //if(jur.simpanJurnal(TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),"U","TINDAKAN RAWAT INAP PASIEN "+TPasien.getText()+" DIPOSTING OLEH "+var.getkode())==false){
                                //    sukses=false;
                                //}
                            }else{
                                sukses=false;
                            }
                            if(sukses==true){
                                //Sequel.Commit();
                                tampilPr();
                                BtnBatalActionPerformed(evt);
                            }else{
                                //JOptionPane.showMessageDialog(null,"Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
                                //Sequel.RollBack();
                            }
                            Sequel.AutoComitTrue();
                        }                            
                    }   break;
                case 2:
                    if(KdDok2.getText().trim().equals("")||TDokter2.getText().trim().equals("")){                              
                        Valid.textKosong(KdDok2,"Dokter");
                    }else if(kdptg2.getText().trim().equals("")||TPerawat2.getText().trim().equals("")){
                        Valid.textKosong(kdptg2,"Petugas");
                    }else if(TKdPrw2.getText().trim().equals("")||TNmPrw2.getText().trim().equals("")){
                        Valid.textKosong(TKdPrw2,"perawatan");
                    }else{
                        if(Sequel.cariRegistrasi(TNoRw.getText().trim())>0){
                            JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                            TCari.requestFocus();
                        }else{
                            Sequel.AutoComitFalse();
                            sukses=true;
                            if(Sequel.menyimpantf("rawat_inap_drpr","?,?,?,?,?,?,?,?,?,?,?,?,?","Data",13,new String[]{
                                TNoRw.getText(),TKdPrw2.getText(),KdDok2.getText(),kdptg2.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),
                                cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),
                                BagianRS.getText(),Bhp.getText(),JmDokter.getText(),JmPerawat.getText(),KSO.getText(),Menejemen.getText(),TTnd.getText()
                            })==true){
                                Sequel.queryu("delete from tampjurnal");
                                if(Valid.SetAngka(TTnd.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Tindakan_Ranap+"','Suspen Piutang Tindakan Ranap','"+TTnd.getText()+"','0'","debet=debet+'"+(TTnd.getText())+"'","kd_rek='"+Suspen_Piutang_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Tindakan_Ranap+"','Pendapatan Tindakan Rawat Inap','0','"+TTnd.getText()+"'","kredit=kredit+'"+(TTnd.getText())+"'","kd_rek='"+Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(JmDokter.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"','Beban Jasa Medik Dokter Tindakan Ranap','"+JmDokter.getText()+"','0'","debet=debet+'"+(JmDokter.getText())+"'","kd_rek='"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"','Utang Jasa Medik Dokter Tindakan Ranap','0','"+JmDokter.getText()+"'","kredit=kredit+'"+(JmDokter.getText())+"'","kd_rek='"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(JmPerawat.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Beban Jasa Medik Paramedis Tindakan Ranap','"+JmPerawat.getText()+"','0'","debet=debet+'"+(JmPerawat.getText())+"'","kd_rek='"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Utang Jasa Medik Paramedis Tindakan Ranap','0','"+JmPerawat.getText()+"'","kredit=kredit+'"+(JmPerawat.getText())+"'","kd_rek='"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(KSO.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Beban_KSO_Tindakan_Ranap+"','Beban KSO Tindakan Ranap','"+KSO.getText()+"','0'","debet=debet+'"+(KSO.getText())+"'","kd_rek='"+Beban_KSO_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Utang_KSO_Tindakan_Ranap+"','Utang KSO Tindakan Ranap','0','"+KSO.getText()+"'","kredit=kredit+'"+(KSO.getText())+"'","kd_rek='"+Utang_KSO_Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(BagianRS.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Sarana_Tindakan_Ranap+"','Beban Jasa Sarana Tindakan Ranap','"+BagianRS.getText()+"','0'","debet=debet+'"+(BagianRS.getText())+"'","kd_rek='"+Beban_Jasa_Sarana_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Sarana_Tindakan_Ranap+"','Utang Jasa Sarana Tindakan Ranap','0','"+BagianRS.getText()+"'","kredit=kredit+'"+(BagianRS.getText())+"'","kd_rek='"+Utang_Jasa_Sarana_Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(Bhp.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+HPP_BHP_Tindakan_Ranap+"','HPP BHP Tindakan Ranap','"+Bhp.getText()+"','0'","debet=debet+'"+(Bhp.getText())+"'","kd_rek='"+HPP_BHP_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Persediaan_BHP_Tindakan_Ranap+"','Persediaan BHP Tindakan Ranap','0','"+Bhp.getText()+"'","kredit=kredit+'"+(Bhp.getText())+"'","kd_rek='"+Persediaan_BHP_Tindakan_Ranap+"'");   
                                }
                                if(Valid.SetAngka(Menejemen.getText())>0){
                                    Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Menejemen_Tindakan_Ranap+"','Beban Jasa Menejemen Tindakan Ranap','"+Menejemen.getText()+"','0'","debet=debet+'"+(Menejemen.getText())+"'","kd_rek='"+Beban_Jasa_Menejemen_Tindakan_Ranap+"'");  
                                    Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Menejemen_Tindakan_Ranap+"','Utang Jasa Menejemen Tindakan Ranap','0','"+Menejemen.getText()+"'","kredit=kredit+'"+(Menejemen.getText())+"'","kd_rek='"+Utang_Jasa_Menejemen_Tindakan_Ranap+"'");   
                                }
                                //if(jur.simpanJurnal(TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),"U","TINDAKAN RAWAT INAP PASIEN "+TPasien.getText()+" DIPOSTING OLEH "+var.getkode())==false){
                                //    sukses=false;
                                //}
                            }else{
                                sukses=false;
                            }
                            
                            if(sukses==true){
                                //Sequel.Commit();
                                tampilDrPr();
                                BtnBatalActionPerformed(evt);
                            }else{
                                //JOptionPane.showMessageDialog(null,"Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
                                //Sequel.RollBack();
                            }
                            Sequel.AutoComitTrue();
                        }                            
                    }   break;
                case 3:
                    if((!TKeluhan.getText().trim().equals(""))||(!TPemeriksaan.getText().trim().equals(""))||
                            (!TSuhu.getText().trim().equals(""))||(!TTensi.getText().trim().equals(""))||
                            (!TAlergi.getText().trim().equals(""))||(!TTinggi.getText().trim().equals(""))||
                            (!TBerat.getText().trim().equals(""))||(!TRespirasi.getText().trim().equals(""))||
                            (!TNadi.getText().trim().equals(""))||(!TGCS.getText().trim().equals(""))||
                            (!TPenilaian.getText().trim().equals(""))||(!TindakLanjut.getText().trim().equals(""))){
                        Sequel.menyimpan("pemeriksaan_ranap","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",16,new String[]{
                            TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),                      
                            TSuhu.getText(),TTensi.getText(),TNadi.getText(),TRespirasi.getText(),TTinggi.getText(),TBerat.getText(),TGCS.getText(),
                            cmbKesadaran.getSelectedItem().toString(),TKeluhan.getText(),TPemeriksaan.getText(),TAlergi.getText(),
                            TPenilaian.getText(),TindakLanjut.getText()
                        });
                        tampilPemeriksaan();
                        BtnBatalActionPerformed(evt);
                    }   break;
                default:
                    break;
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            if(TabRawat.getSelectedIndex()==0){
                Valid.pindah(evt,TKdPrw,BtnBatal);
            }else if(TabRawat.getSelectedIndex()==1){
                Valid.pindah(evt,TKdPrw1,BtnBatal);
            }else if(TabRawat.getSelectedIndex()==2){
                Valid.pindah(evt,TKdPrw2,BtnBatal);
            }else if(TabRawat.getSelectedIndex()==3){
                Valid.pindah(evt,TindakLanjut,BtnBatal);
            }
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkInput.setSelected(true);
        isForm(); 
        TSuhu.setText("");
        TKdPrw.setText("");
        TNmPrw.setText("");
        TKdPrw1.setText("");
        TNmPrw1.setText("");        
        TKdPrw2.setText("");
        TNmPrw2.setText("");
        TTensi.setText("");
        TKeluhan.setText("");
        TPemeriksaan.setText("");
        TPenilaian.setText("");
        TindakLanjut.setText("");
        TBerat.setText("");
        TTinggi.setText("");
        TNadi.setText("");
        TRespirasi.setText("");
        TGCS.setText("");
        TAlergi.setText("");
        TTnd.setText("0");
        BagianRS.setText("0");
        Bhp.setText("0");
        JmDokter.setText("0");
        JmPerawat.setText("0");
        DTPTgl.setDate(new Date());
        cmbKesadaran.setSelectedIndex(0);
        TNoRw.requestFocus();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnBatalActionPerformed(null);
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                if(tabModeDr.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                }else{
                    Sequel.AutoComitFalse();
                    sukses=true;
                    ttljmdokter=0;ttljmperawat=0;ttlkso=0;ttlpendapatan=0;ttljasasarana=0;ttlbhp=0;ttlmenejemen=0;
                    for(i=0;i<tbRawatDr.getRowCount();i++){
                        if(tbRawatDr.getValueAt(i,0).toString().equals("true")){
                            if(Sequel.cariRegistrasi(tbRawatDr.getValueAt(i,1).toString())>0){
                                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                                TCari.requestFocus();
                            }else{
                                if(Sequel.queryutf("delete from rawat_inap_dr where no_rawat='"+tbRawatDr.getValueAt(i,1).toString()+
                                        "' and kd_jenis_prw='"+tbRawatDr.getValueAt(i,10)+
                                        "' and kd_dokter='"+tbRawatDr.getValueAt(i,5).toString()+
                                        "' and tgl_perawatan='"+tbRawatDr.getValueAt(i,7).toString()+
                                        "' and jam_rawat='"+tbRawatDr.getValueAt(i,8).toString()+"'")==true){
                                    ttljmdokter=ttljmdokter+Double.parseDouble(tbRawatDr.getValueAt(i,11).toString());
                                    ttlkso=ttlkso+Double.parseDouble(tbRawatDr.getValueAt(i,12).toString());
                                    ttlpendapatan=ttlpendapatan+Double.parseDouble(tbRawatDr.getValueAt(i,9).toString());
                                    ttljasasarana=ttljasasarana+Double.parseDouble(tbRawatDr.getValueAt(i,13).toString());
                                    ttlbhp=ttlbhp+Double.parseDouble(tbRawatDr.getValueAt(i,14).toString());
                                    ttlmenejemen=ttlmenejemen+Double.parseDouble(tbRawatDr.getValueAt(i,15).toString());
                                }else{
                                    sukses=false;
                                }
                            }
                        }
                    }
                    if(sukses==true){
                        Sequel.queryu("delete from tampjurnal");
                        if(ttlpendapatan>0){
                            Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Tindakan_Ranap+"','Suspen Piutang Tindakan Ranap','0','"+ttlpendapatan+"'","kredit=kredit+'"+(ttlpendapatan)+"'","kd_rek='"+Suspen_Piutang_Tindakan_Ranap+"'");   
                            Sequel.menyimpan("tampjurnal","'"+Tindakan_Ranap+"','Pendapatan Tindakan Rawat Inap','"+ttlpendapatan+"','0'","debet=debet+'"+(ttlpendapatan)+"'","kd_rek='"+Tindakan_Ranap+"'");                             
                        }
                        if(ttljmdokter>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"','Beban Jasa Medik Dokter Tindakan Ranap','0','"+ttljmdokter+"'","kredit=kredit+'"+(ttljmdokter)+"'","kd_rek='"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"','Utang Jasa Medik Dokter Tindakan Ranap','"+ttljmdokter+"','0'","debet=debet+'"+(ttljmdokter)+"'","kd_rek='"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"'");                              
                        }
                        if(ttlkso>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_KSO_Tindakan_Ranap+"','Beban KSO Tindakan Ranap','0','"+ttlkso+"'","kredit=kredit+'"+(ttlkso)+"'","kd_rek='"+Beban_KSO_Tindakan_Ranap+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Utang_KSO_Tindakan_Ranap+"','Utang KSO Tindakan Ranap','"+ttlkso+"','0'","debet=debet+'"+(ttlkso)+"'","kd_rek='"+Utang_KSO_Tindakan_Ranap+"'");                               
                        }
                        if(ttlmenejemen>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Menejemen_Tindakan_Ranap+"','Beban Jasa Menejemen Tindakan Ranap','0','"+ttlmenejemen+"'","kredit=kredit+'"+(ttlmenejemen)+"'","kd_rek='"+Beban_Jasa_Menejemen_Tindakan_Ranap+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Menejemen_Tindakan_Ranap+"','Utang Jasa Menejemen Tindakan Ranap','"+ttlmenejemen+"','0'","debet=debet+'"+(ttlmenejemen)+"'","kd_rek='"+Utang_Jasa_Menejemen_Tindakan_Ranap+"'");                                
                        }
                        if(ttljasasarana>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Sarana_Tindakan_Ranap+"','Beban Jasa Sarana Tindakan Ranap','0','"+ttljasasarana+"'","kredit=kredit+'"+(ttljasasarana)+"'","kd_rek='"+Beban_Jasa_Sarana_Tindakan_Ranap+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Sarana_Tindakan_Ranap+"','Utang Jasa Sarana Tindakan Ranap','"+ttljasasarana+"','0'","debet=debet+'"+(ttljasasarana)+"'","kd_rek='"+Utang_Jasa_Sarana_Tindakan_Ranap+"'");                              
                        }
                        if(ttlbhp>0){
                            Sequel.menyimpan("tampjurnal","'"+HPP_BHP_Tindakan_Ranap+"','HPP BHP Tindakan Ranap','0','"+ttlbhp+"'","kredit=kredit+'"+(ttlbhp)+"'","kd_rek='"+HPP_BHP_Tindakan_Ranap+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Persediaan_BHP_Tindakan_Ranap+"','Persediaan BHP Tindakan Ranap','"+ttlbhp+"','0'","debet=debet+'"+(ttlbhp)+"'","kd_rek='"+Persediaan_BHP_Tindakan_Ranap+"'");                            
                        }
                        //sukses=jur.simpanJurnal(TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),"U","PEMBATALAN TINDAKAN RAWAT INAP PASIEN OLEH "+var.getkode());
                    }
                      
                    if(sukses==true){
                        //Sequel.Commit();
                        tampilDr();
                    }else{
                        sukses=false;
                        //JOptionPane.showMessageDialog(null,"Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
                        //Sequel.RollBack();
                    }
                    Sequel.AutoComitTrue();
                }   break;
            case 1:
                if(tabModePr.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                }else{
                    Sequel.AutoComitFalse();
                    sukses=true;
                    ttljmdokter=0;ttljmperawat=0;ttlkso=0;ttlpendapatan=0;ttljasasarana=0;ttlbhp=0;ttlmenejemen=0;
                    for(i=0;i<tbRawatPr.getRowCount();i++){
                        if(tbRawatPr.getValueAt(i,0).toString().equals("true")){
                            if(Sequel.cariRegistrasi(tbRawatPr.getValueAt(i,1).toString())>0){
                                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                                TCari.requestFocus();
                            }else{
                                if(Sequel.queryutf("delete from rawat_inap_pr where no_rawat='"+tbRawatPr.getValueAt(i,1).toString()+
                                        "' and kd_jenis_prw='"+tbRawatPr.getValueAt(i,10)+
                                        "' and nip='"+tbRawatPr.getValueAt(i,5).toString()+
                                        "' and tgl_perawatan='"+tbRawatPr.getValueAt(i,7).toString()+
                                        "' and jam_rawat='"+tbRawatPr.getValueAt(i,8).toString()+"' ")==true){
                                    ttljmperawat=ttljmperawat+Double.parseDouble(tbRawatPr.getValueAt(i,11).toString());
                                    ttlkso=ttlkso+Double.parseDouble(tbRawatPr.getValueAt(i,12).toString());
                                    ttlpendapatan=ttlpendapatan+Double.parseDouble(tbRawatPr.getValueAt(i,9).toString());
                                    ttljasasarana=ttljasasarana+Double.parseDouble(tbRawatPr.getValueAt(i,13).toString());
                                    ttlbhp=ttlbhp+Double.parseDouble(tbRawatPr.getValueAt(i,14).toString());
                                    ttlmenejemen=ttlmenejemen+Double.parseDouble(tbRawatPr.getValueAt(i,15).toString());
                                }else{
                                    sukses=false;
                                }
                            }
                        }                            
                    }
                    if(sukses==true){
                        Sequel.queryu("delete from tampjurnal");
                        if(ttlpendapatan>0){
                            Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Tindakan_Ranap+"','Suspen Piutang Tindakan Ranap','0','"+ttlpendapatan+"'","kredit=kredit+'"+(ttlpendapatan)+"'","kd_rek='"+Suspen_Piutang_Tindakan_Ranap+"'");   
                            Sequel.menyimpan("tampjurnal","'"+Tindakan_Ranap+"','Pendapatan Tindakan Rawat Inap','"+ttlpendapatan+"','0'","debet=debet+'"+(ttlpendapatan)+"'","kd_rek='"+Tindakan_Ranap+"'");                             
                        }
                        if(ttljmperawat>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Beban Jasa Medik Paramedis Tindakan Ranap','0','"+ttljmperawat+"'","kredit=kredit+'"+(ttljmperawat)+"'","kd_rek='"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"'");     
                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Utang Jasa Medik Paramedis Tindakan Ranap','"+ttljmperawat+"','0'","debet=debet+'"+(ttljmperawat)+"'","kd_rek='"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"'");                              
                        }
                        if(ttlkso>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_KSO_Tindakan_Ranap+"','Beban KSO Tindakan Ranap','0','"+ttlkso+"'","kredit=kredit+'"+(ttlkso)+"'","kd_rek='"+Beban_KSO_Tindakan_Ranap+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Utang_KSO_Tindakan_Ranap+"','Utang KSO Tindakan Ranap','"+ttlkso+"','0'","debet=debet+'"+(ttlkso)+"'","kd_rek='"+Utang_KSO_Tindakan_Ranap+"'");                               
                        }
                        if(ttlmenejemen>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Menejemen_Tindakan_Ranap+"','Beban Jasa Menejemen Tindakan Ranap','0','"+ttlmenejemen+"'","kredit=kredit+'"+(ttlmenejemen)+"'","kd_rek='"+Beban_Jasa_Menejemen_Tindakan_Ranap+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Menejemen_Tindakan_Ranap+"','Utang Jasa Menejemen Tindakan Ranap','"+ttlmenejemen+"','0'","debet=debet+'"+(ttlmenejemen)+"'","kd_rek='"+Utang_Jasa_Menejemen_Tindakan_Ranap+"'");                                
                        }
                        if(ttljasasarana>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Sarana_Tindakan_Ranap+"','Beban Jasa Sarana Tindakan Ranap','0','"+ttljasasarana+"'","kredit=kredit+'"+(ttljasasarana)+"'","kd_rek='"+Beban_Jasa_Sarana_Tindakan_Ranap+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Sarana_Tindakan_Ranap+"','Utang Jasa Sarana Tindakan Ranap','"+ttljasasarana+"','0'","debet=debet+'"+(ttljasasarana)+"'","kd_rek='"+Utang_Jasa_Sarana_Tindakan_Ranap+"'");                              
                        }
                        if(ttlbhp>0){
                            Sequel.menyimpan("tampjurnal","'"+HPP_BHP_Tindakan_Ranap+"','HPP BHP Tindakan Ranap','0','"+ttlbhp+"'","kredit=kredit+'"+(ttlbhp)+"'","kd_rek='"+HPP_BHP_Tindakan_Ranap+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Persediaan_BHP_Tindakan_Ranap+"','Persediaan BHP Tindakan Ranap','"+ttlbhp+"','0'","debet=debet+'"+(ttlbhp)+"'","kd_rek='"+Persediaan_BHP_Tindakan_Ranap+"'");                            
                        }
                        //sukses=jur.simpanJurnal(TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),"U","PEMBATALAN TINDAKAN RAWAT INAP PASIEN OLEH "+var.getkode());
                    }
                        
                    if(sukses==true){
                        //Sequel.Commit();
                        tampilPr();
                    }else{
                        sukses=false;
                        //JOptionPane.showMessageDialog(null,"Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
                        //Sequel.RollBack();
                    }
                    Sequel.AutoComitTrue();
                }   break;
            case 2:
                if(tabModeDrPr.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                }else{
                    Sequel.AutoComitFalse();
                    sukses=true;
                    ttljmdokter=0;ttljmperawat=0;ttlkso=0;ttlpendapatan=0;ttljasasarana=0;ttlbhp=0;ttlmenejemen=0;
                    for(i=0;i<tbRawatDrPr.getRowCount();i++){
                        if(tbRawatDrPr.getValueAt(i,0).toString().equals("true")){
                            if(Sequel.cariRegistrasi(tbRawatDrPr.getValueAt(i,1).toString())>0){
                                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                                TCari.requestFocus();
                            }else{
                                if(Sequel.queryutf("delete from rawat_inap_drpr where no_rawat='"+tbRawatDrPr.getValueAt(i,1).toString()+
                                        "' and kd_jenis_prw='"+tbRawatDrPr.getValueAt(i,12)+
                                        "' and kd_dokter='"+tbRawatDrPr.getValueAt(i,5).toString()+
                                        "' and nip='"+tbRawatDrPr.getValueAt(i,7).toString()+
                                        "' and tgl_perawatan='"+tbRawatDrPr.getValueAt(i,9).toString()+
                                        "' and jam_rawat='"+tbRawatDrPr.getValueAt(i,10).toString()+"' ")==true){
                                    ttljmdokter=ttljmdokter+Double.parseDouble(tbRawatDrPr.getValueAt(i,13).toString());
                                    ttljmperawat=ttljmperawat+Double.parseDouble(tbRawatDrPr.getValueAt(i,14).toString());
                                    ttlkso=ttlkso+Double.parseDouble(tbRawatDrPr.getValueAt(i,15).toString());
                                    ttlpendapatan=ttlpendapatan+Double.parseDouble(tbRawatDrPr.getValueAt(i,11).toString());
                                    ttljasasarana=ttljasasarana+Double.parseDouble(tbRawatDrPr.getValueAt(i,16).toString());
                                    ttlbhp=ttlbhp+Double.parseDouble(tbRawatDrPr.getValueAt(i,17).toString());
                                    ttlmenejemen=ttlmenejemen+Double.parseDouble(tbRawatDrPr.getValueAt(i,18).toString());
                                }else{
                                    sukses=false;
                                }
                            }
                        }                            
                    }
                    if(sukses==true){
                        Sequel.queryu("delete from tampjurnal");
                        if(ttlpendapatan>0){
                            Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Tindakan_Ranap+"','Suspen Piutang Tindakan Ranap','0','"+ttlpendapatan+"'","kredit=kredit+'"+(ttlpendapatan)+"'","kd_rek='"+Suspen_Piutang_Tindakan_Ranap+"'");   
                            Sequel.menyimpan("tampjurnal","'"+Tindakan_Ranap+"','Pendapatan Tindakan Rawat Inap','"+ttlpendapatan+"','0'","debet=debet+'"+(ttlpendapatan)+"'","kd_rek='"+Tindakan_Ranap+"'");                             
                        }
                        if(ttljmdokter>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"','Beban Jasa Medik Dokter Tindakan Ranap','0','"+ttljmdokter+"'","kredit=kredit+'"+(ttljmdokter)+"'","kd_rek='"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"','Utang Jasa Medik Dokter Tindakan Ranap','"+ttljmdokter+"','0'","debet=debet+'"+(ttljmdokter)+"'","kd_rek='"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"'");                              
                        }
                        if(ttljmperawat>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Beban Jasa Medik Paramedis Tindakan Ranap','0','"+ttljmperawat+"'","kredit=kredit+'"+(ttljmperawat)+"'","kd_rek='"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"'");     
                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Utang Jasa Medik Paramedis Tindakan Ranap','"+ttljmperawat+"','0'","debet=debet+'"+(ttljmperawat)+"'","kd_rek='"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"'");                              
                        }
                        if(ttlkso>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_KSO_Tindakan_Ranap+"','Beban KSO Tindakan Ranap','0','"+ttlkso+"'","kredit=kredit+'"+(ttlkso)+"'","kd_rek='"+Beban_KSO_Tindakan_Ranap+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Utang_KSO_Tindakan_Ranap+"','Utang KSO Tindakan Ranap','"+ttlkso+"','0'","debet=debet+'"+(ttlkso)+"'","kd_rek='"+Utang_KSO_Tindakan_Ranap+"'");                               
                        }
                        if(ttlmenejemen>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Menejemen_Tindakan_Ranap+"','Beban Jasa Menejemen Tindakan Ranap','0','"+ttlmenejemen+"'","kredit=kredit+'"+(ttlmenejemen)+"'","kd_rek='"+Beban_Jasa_Menejemen_Tindakan_Ranap+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Menejemen_Tindakan_Ranap+"','Utang Jasa Menejemen Tindakan Ranap','"+ttlmenejemen+"','0'","debet=debet+'"+(ttlmenejemen)+"'","kd_rek='"+Utang_Jasa_Menejemen_Tindakan_Ranap+"'");                                
                        }
                        if(ttljasasarana>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Sarana_Tindakan_Ranap+"','Beban Jasa Sarana Tindakan Ranap','0','"+ttljasasarana+"'","kredit=kredit+'"+(ttljasasarana)+"'","kd_rek='"+Beban_Jasa_Sarana_Tindakan_Ranap+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Sarana_Tindakan_Ranap+"','Utang Jasa Sarana Tindakan Ranap','"+ttljasasarana+"','0'","debet=debet+'"+(ttljasasarana)+"'","kd_rek='"+Utang_Jasa_Sarana_Tindakan_Ranap+"'");                              
                        }
                        if(ttlbhp>0){
                            Sequel.menyimpan("tampjurnal","'"+HPP_BHP_Tindakan_Ranap+"','HPP BHP Tindakan Ranap','0','"+ttlbhp+"'","kredit=kredit+'"+(ttlbhp)+"'","kd_rek='"+HPP_BHP_Tindakan_Ranap+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Persediaan_BHP_Tindakan_Ranap+"','Persediaan BHP Tindakan Ranap','"+ttlbhp+"','0'","debet=debet+'"+(ttlbhp)+"'","kd_rek='"+Persediaan_BHP_Tindakan_Ranap+"'");                            
                        }
                        //sukses=jur.simpanJurnal(TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),"U","PEMBATALAN TINDAKAN RAWAT INAP PASIEN OLEH "+var.getkode());
                    }
                        
                    if(sukses==true){
                        //Sequel.Commit();
                        tampilDrPr();
                    }else{
                        sukses=false;
                        //JOptionPane.showMessageDialog(null,"Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
                        //Sequel.RollBack();
                    }
                    Sequel.AutoComitTrue();
                }   break;
            case 3:
                if(tabModePemeriksaan.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                }else{
                    for(i=0;i<tbPemeriksaan.getRowCount();i++){
                        if(tbPemeriksaan.getValueAt(i,0).toString().equals("true")){
                            Sequel.queryu("delete from pemeriksaan_ranap where no_rawat='"+tbPemeriksaan.getValueAt(i,1).toString()+
                                    "' and tgl_perawatan='"+tbPemeriksaan.getValueAt(i,4).toString()+
                                    "' and jam_rawat='"+tbPemeriksaan.getValueAt(i,5).toString()+"' ");
                        }
                    }
                    tampilPemeriksaan();
                }   break;
            default:
                break;
        }

        BtnBatalActionPerformed(evt);
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(! TCari.getText().trim().equals("")){
            BtnCariActionPerformed(evt);
        }
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                if(tabModeDr.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                }else if(tabModeDr.getRowCount()!=0){
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars",var.getnamars());
                    param.put("alamatrs",var.getalamatrs());
                    param.put("kotars",var.getkabupatenrs());
                    param.put("propinsirs",var.getpropinsirs());
                    param.put("kontakrs",var.getkontakrs());
                    param.put("emailrs",var.getemailrs());
                    param.put("logo",Sequel.cariGambar("select logo from setting"));
                    String pas=" and reg_periksa.no_rkm_medis like '%"+TCariPasien.getText()+"%' ";
                    
                    String tgl=" rawat_inap_dr.tgl_perawatan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' "+pas;
                    Valid.MyReportqry("rptInapDr.jasper","report","::[ Data Rawat Inap Yang Ditangani Dokter ]::",
                            "select rawat_inap_dr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                                    "jns_perawatan_inap.nm_perawatan,rawat_inap_dr.kd_dokter,dokter.nm_dokter,"+
                                    "rawat_inap_dr.tgl_perawatan,rawat_inap_dr.jam_rawat,rawat_inap_dr.biaya_rawat " +
                                    "from pasien inner join reg_periksa inner join jns_perawatan_inap inner join "+
                                    "dokter inner join rawat_inap_dr "+
                                    "on rawat_inap_dr.no_rawat=reg_periksa.no_rawat "+
                                    "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                                    "and rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
                                    "and rawat_inap_dr.kd_dokter=dokter.kd_dokter "+
                                    "where "+tgl+" and rawat_inap_dr.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and jns_perawatan_inap.nm_perawatan like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and rawat_inap_dr.kd_dokter like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and dokter.nm_dokter like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and tgl_perawatan like '%"+TCari.getText().trim()+"%' "+
                                            " order by rawat_inap_dr.no_rawat desc",param);
                    
                }   break;
            case 1:
                if(tabModePr.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                }else if(tabModePr.getRowCount()!=0){
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars",var.getnamars());
                    param.put("alamatrs",var.getalamatrs());
                    param.put("kotars",var.getkabupatenrs());
                    param.put("propinsirs",var.getpropinsirs());
                    param.put("kontakrs",var.getkontakrs());
                    param.put("emailrs",var.getemailrs());
                    param.put("logo",Sequel.cariGambar("select logo from setting"));
                    String pas=" and reg_periksa.no_rkm_medis like '%"+TCariPasien.getText()+"%' ";
                    
                    String tgl=" rawat_inap_pr.tgl_perawatan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' "+pas;
                    Valid.MyReportqry("rptInapPr.jasper","report","::[ Data Rawat Inap Yang Ditangani Perawat ]::",
                            "select rawat_inap_pr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                                    "jns_perawatan_inap.nm_perawatan,rawat_inap_pr.nip,petugas.nama,"+
                                    "rawat_inap_pr.tgl_perawatan,rawat_inap_pr.jam_rawat,rawat_inap_pr.biaya_rawat " +
                                    "from pasien inner join reg_periksa inner join jns_perawatan_inap inner join "+
                                    "petugas inner join rawat_inap_pr "+
                                    "on rawat_inap_pr.no_rawat=reg_periksa.no_rawat "+
                                    "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                                    "and rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
                                    "and rawat_inap_pr.nip=petugas.nip where  "+
                                    tgl+"and rawat_inap_pr.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and jns_perawatan_inap.nm_perawatan like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and rawat_inap_pr.nip like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and petugas.nama like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and rawat_inap_pr.tgl_perawatan like '%"+TCari.getText().trim()+"%'  "+
                                            "order by rawat_inap_pr.no_rawat desc",param);
                }   break;
            case 2:
                if(tabModeDrPr.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                }else if(tabModeDrPr.getRowCount()!=0){
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars",var.getnamars());
                    param.put("alamatrs",var.getalamatrs());
                    param.put("kotars",var.getkabupatenrs());
                    param.put("propinsirs",var.getpropinsirs());
                    param.put("kontakrs",var.getkontakrs());
                    param.put("emailrs",var.getemailrs());
                    param.put("logo",Sequel.cariGambar("select logo from setting"));
                    String pas=" and reg_periksa.no_rkm_medis like '%"+TCariPasien.getText()+"%' ";
                    
                    String tgl=" rawat_inap_drpr.tgl_perawatan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' "+pas;
                    Valid.MyReportqry("rptInapDrPr.jasper","report","::[ Data Rawat Inap Yang Ditangani Dokter ]::",
                            "select rawat_inap_drpr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                                    "jns_perawatan_inap.nm_perawatan,rawat_inap_drpr.kd_dokter,dokter.nm_dokter,rawat_inap_drpr.nip,petugas.nama,"+
                                    "rawat_inap_drpr.tgl_perawatan,rawat_inap_drpr.jam_rawat,rawat_inap_drpr.biaya_rawat " +
                                    "from pasien inner join reg_periksa inner join jns_perawatan_inap inner join "+
                                    "dokter inner join rawat_inap_drpr inner join "+
                                    "petugas on rawat_inap_drpr.no_rawat=reg_periksa.no_rawat "+
                                    "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                                    "and rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
                                    "and rawat_inap_drpr.kd_dokter=dokter.kd_dokter "+
                                    "and rawat_inap_drpr.nip=petugas.nip "+
                                    "where "+tgl+" and rawat_inap_drpr.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and jns_perawatan_inap.nm_perawatan like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and rawat_inap_drpr.kd_dokter like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and dokter.nm_dokter like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and rawat_inap_drpr.nip like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and petugas.nama like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and tgl_perawatan like '%"+TCari.getText().trim()+"%' "+
                                            " order by rawat_inap_drpr.no_rawat desc",param);
                }   break;
            case 3:
                if(tabModePemeriksaan.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                }else if(tabModePemeriksaan.getRowCount()!=0){
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars",var.getnamars());
                    param.put("alamatrs",var.getalamatrs());
                    param.put("kotars",var.getkabupatenrs());
                    param.put("propinsirs",var.getpropinsirs());
                    param.put("kontakrs",var.getkontakrs());
                    param.put("emailrs",var.getemailrs());
                    param.put("logo",Sequel.cariGambar("select logo from setting"));
                    String pas=" and reg_periksa.no_rkm_medis like '%"+TCariPasien.getText()+"%' ";
                    
                    String tgl=" pemeriksaan_ranap.tgl_perawatan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' "+pas;
                    Valid.MyReportqry("rptInapPemeriksaan.jasper","report","::[ Data Pemeriksaan Rawat Inap ]::",
                            "select pemeriksaan_ranap.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                                    "pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat,pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi, " +
                                    "pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi,pemeriksaan_ranap.tinggi, " +
                                    "pemeriksaan_ranap.berat,pemeriksaan_ranap.gcs,pemeriksaan_ranap.kesadaran,pemeriksaan_ranap.keluhan, " +
                                    "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi,pemeriksaan_ranap.penilaian,pemeriksaan_ranap.rtl from pasien inner join reg_periksa inner join pemeriksaan_ranap "+
                                    "on pemeriksaan_ranap.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where  "+
                                    tgl+"and pemeriksaan_ranap.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and pemeriksaan_ranap.alergi like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and pemeriksaan_ranap.keluhan like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and pemeriksaan_ranap.penilaian like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and pemeriksaan_ranap.rtl like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and pemeriksaan_ranap.pemeriksaan like '%"+TCari.getText().trim()+"%' "+
                                            "order by pemeriksaan_ranap.no_rawat desc",param);
                }   break;
            default:
                break;
        }

        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        TCariPasien.setText("");
        BtnCariActionPerformed(null);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                tampilDr();
                break;
            case 1:
                tampilPr();
                break;
            case 2:
                tampilDrPr();
                break;
            case 3:
                tampilPemeriksaan();
                break;
            default:
                break;
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        isJns();
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                tampilDr();
                break;
            case 1:
                tampilPr();
                break;
            case 2:
                tampilDrPr();
                break;
            case 3:
                tampilPemeriksaan();
                break;
            default:
                break;
        }
}//GEN-LAST:event_TabRawatMouseClicked

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
       Valid.pindah(evt,TKdPrw,cmbJam);
}//GEN-LAST:event_DTPTglKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt,DTPTgl,cmbMnt);
}//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt,cmbJam,cmbDtk);
}//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt,cmbMnt,TKeluhan);
}//GEN-LAST:event_cmbDtkKeyPressed

    private void btnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienActionPerformed
        var.setform("DlgRawatInap");
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(rootPaneCheckingEnabled);
}//GEN-LAST:event_btnPasienActionPerformed

    private void btnPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPasienKeyPressed
        Valid.pindah(evt,TCariPasien,DTPCari1);
}//GEN-LAST:event_btnPasienKeyPressed

    private void tbRawatDrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRawatDrMouseClicked
        if(tabModeDr.getRowCount()!=0){
            try {
                getDataDr();
            } catch (java.lang.NullPointerException e) {
            }           
            
        }
}//GEN-LAST:event_tbRawatDrMouseClicked

    private void tbRawatPrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRawatPrMouseClicked
        if(tabModePr.getRowCount()!=0){
            try {
                getDataPr();
            } catch (java.lang.NullPointerException e) {
            }
            
        }
}//GEN-LAST:event_tbRawatPrMouseClicked

private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_ChkJlnActionPerformed

private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else{
            switch (TabRawat.getSelectedIndex()) {
                case 0:
                    if(KdDok.getText().trim().equals("")||TDokter.getText().trim().equals("")){
                        Valid.textKosong(KdDok,"Dokter");
                    }else if(TKdPrw.getText().trim().equals("")||TNmPrw.getText().trim().equals("")){
                        Valid.textKosong(TKdPrw,"perawatan");                            
                    }else{
                        if(tbRawatDr.getSelectedRow()>-1){
                            if(Sequel.cariRegistrasi(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),1).toString())>0){
                                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh diubah.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                                TCari.requestFocus();
                            }else{
                                Sequel.AutoComitFalse();
                                sukses=true;
                                if(Sequel.mengedittf("rawat_inap_dr","no_rawat='"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),1)+
                                        "' and kd_jenis_prw='"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),10)+  
                                        "' and kd_dokter='"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),5)+
                                        "' and tgl_perawatan='"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),7)+
                                        "' and jam_rawat='"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),8)+"'",
                                        "no_rawat='"+TNoRw.getText()+"',kd_jenis_prw='"+TKdPrw.getText()+
                                                "',kd_dokter='"+KdDok.getText()+"',material='"+BagianRS.getText()+
                                                "',bhp='"+Bhp.getText()+"',tarif_tindakandr='"+JmDokter.getText()+"',biaya_rawat='"+TTnd.getText()+
                                                "',tgl_perawatan='"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"',jam_rawat='"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"',"+
                                                        "kso='"+KSO.getText()+"',menejemen='"+Menejemen.getText()+"'")==true){
                                    
                                    Sequel.queryu("delete from tampjurnal");
                                    if(Valid.SetAngka(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),9).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Tindakan_Ranap+"','Suspen Piutang Tindakan Ranap','0','"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),9).toString()+"'","kredit=kredit+'"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),9).toString()+"'","kd_rek='"+Suspen_Piutang_Tindakan_Ranap+"'"); 
                                        Sequel.menyimpan("tampjurnal","'"+Tindakan_Ranap+"','Pendapatan Tindakan Rawat Inap','"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),9).toString()+"','0'","debet=debet+'"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),9).toString()+"'","kd_rek='"+Tindakan_Ranap+"'");
                                    }
                                    if(Valid.SetAngka(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),11).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"','Beban Jasa Medik Dokter Tindakan Ranap','0','"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),11).toString()+"'","kredit=kredit+'"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),11).toString()+"'","kd_rek='"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"'"); 
                                        Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"','Utang Jasa Medik Dokter Tindakan Ranap','"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),11).toString()+"','0'","debet=debet+'"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),11).toString()+"'","kd_rek='"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"'"); 
                                    }
                                    if(Valid.SetAngka(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),12).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Beban_KSO_Tindakan_Ranap+"','Beban KSO Tindakan Ranap','0','"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),12).toString()+"'","kredit=kredit+'"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),12).toString()+"'","kd_rek='"+Beban_KSO_Tindakan_Ranap+"'");  
                                        Sequel.menyimpan("tampjurnal","'"+Utang_KSO_Tindakan_Ranap+"','Utang KSO Tindakan Ranap','"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),12).toString()+"','0'","debet=debet+'"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),12).toString()+"'","kd_rek='"+Utang_KSO_Tindakan_Ranap+"'");  
                                    }
                                    if(Valid.SetAngka(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),13).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Sarana_Tindakan_Ranap+"','Beban Jasa Sarana Tindakan Ranap','0','"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),13).toString()+"'","kredit=kredit+'"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),13).toString()+"'","kd_rek='"+Beban_Jasa_Sarana_Tindakan_Ranap+"'");  
                                        Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Sarana_Tindakan_Ranap+"','Utang Jasa Sarana Tindakan Ranap','"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),13).toString()+"','0'","debet=debet+'"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),13).toString()+"'","kd_rek='"+Utang_Jasa_Sarana_Tindakan_Ranap+"'"); 
                                    }
                                    if(Valid.SetAngka(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),14).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+HPP_BHP_Tindakan_Ranap+"','HPP BHP Tindakan Ranap','0','"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),14).toString()+"'","kredit=kredit+'"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),14).toString()+"'","kd_rek='"+HPP_BHP_Tindakan_Ranap+"'"); 
                                        Sequel.menyimpan("tampjurnal","'"+Persediaan_BHP_Tindakan_Ranap+"','Persediaan BHP Tindakan Ranap','"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),14).toString()+"','0'","debet=debet+'"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),14).toString()+"'","kd_rek='"+Persediaan_BHP_Tindakan_Ranap+"'"); 
                                    }
                                    if(Valid.SetAngka(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),15).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Menejemen_Tindakan_Ranap+"','Beban Jasa Menejemen Tindakan Ranap','0','"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),15).toString()+"'","kredit=kredit+'"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),15).toString()+"'","kd_rek='"+Beban_Jasa_Menejemen_Tindakan_Ranap+"'"); 
                                        Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Menejemen_Tindakan_Ranap+"','Utang Jasa Menejemen Tindakan Ranap','"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),15).toString()+"','0'","debet=debet+'"+tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),15).toString()+"'","kd_rek='"+Utang_Jasa_Menejemen_Tindakan_Ranap+"'");
                                    }
                                    //sukses=jur.simpanJurnal(TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),"U","PERUBAHAN TINDAKAN RAWAT INAP PASIEN OLEH "+var.getkode());

                                    if(sukses==true){
                                        Sequel.queryu("delete from tampjurnal");
                                            if(Valid.SetAngka(TTnd.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Tindakan_Ranap+"','Suspen Piutang Tindakan Ranap','"+TTnd.getText()+"','0'","debet=debet+'"+(TTnd.getText())+"'","kd_rek='"+Suspen_Piutang_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Tindakan_Ranap+"','Pendapatan Tindakan Rawat Inap','0','"+TTnd.getText()+"'","kredit=kredit+'"+(TTnd.getText())+"'","kd_rek='"+Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(JmDokter.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"','Beban Jasa Medik Dokter Tindakan Ranap','"+JmDokter.getText()+"','0'","debet=debet+'"+(JmDokter.getText())+"'","kd_rek='"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"','Utang Jasa Medik Dokter Tindakan Ranap','0','"+JmDokter.getText()+"'","kredit=kredit+'"+(JmDokter.getText())+"'","kd_rek='"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(KSO.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Beban_KSO_Tindakan_Ranap+"','Beban KSO Tindakan Ranap','"+KSO.getText()+"','0'","debet=debet+'"+(KSO.getText())+"'","kd_rek='"+Beban_KSO_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Utang_KSO_Tindakan_Ranap+"','Utang KSO Tindakan Ranap','0','"+KSO.getText()+"'","kredit=kredit+'"+(KSO.getText())+"'","kd_rek='"+Utang_KSO_Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(BagianRS.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Sarana_Tindakan_Ranap+"','Beban Jasa Sarana Tindakan Ranap','"+BagianRS.getText()+"','0'","debet=debet+'"+(BagianRS.getText())+"'","kd_rek='"+Beban_Jasa_Sarana_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Sarana_Tindakan_Ranap+"','Utang Jasa Sarana Tindakan Ranap','0','"+BagianRS.getText()+"'","kredit=kredit+'"+(BagianRS.getText())+"'","kd_rek='"+Utang_Jasa_Sarana_Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(Bhp.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+HPP_BHP_Tindakan_Ranap+"','HPP BHP Tindakan Ranap','"+Bhp.getText()+"','0'","debet=debet+'"+(Bhp.getText())+"'","kd_rek='"+HPP_BHP_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Persediaan_BHP_Tindakan_Ranap+"','Persediaan BHP Tindakan Ranap','0','"+Bhp.getText()+"'","kredit=kredit+'"+(Bhp.getText())+"'","kd_rek='"+Persediaan_BHP_Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(Menejemen.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Menejemen_Tindakan_Ranap+"','Beban Jasa Menejemen Tindakan Ranap','"+Menejemen.getText()+"','0'","debet=debet+'"+(Menejemen.getText())+"'","kd_rek='"+Beban_Jasa_Menejemen_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Menejemen_Tindakan_Ranap+"','Utang Jasa Menejemen Tindakan Ranap','0','"+Menejemen.getText()+"'","kredit=kredit+'"+(Menejemen.getText())+"'","kd_rek='"+Utang_Jasa_Menejemen_Tindakan_Ranap+"'");   
                                        }
                                        //sukses=jur.simpanJurnal(TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),"U","PERUBAHAN TINDAKAN RAWAT INAP PASIEN "+TPasien.getText());
                                    }
                                }else{
                                    sukses=false;
                                }  
                                
                                if(sukses==true){
                                    //Sequel.Commit();
                                    tampilDr();
                                    BtnBatalActionPerformed(evt);
                                }else{
                                    //JOptionPane.showMessageDialog(null,"Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
                                    //Sequel.RollBack();
                                }
                                Sequel.AutoComitTrue();
                            }
                        }else{
                            JOptionPane.showMessageDialog(rootPane,"Silahkan pilih data yang mau diganti..!!");
                            TCari.requestFocus();
                        }
                        
                    }   break;
                case 1:
                    if(kdptg.getText().trim().equals("")||TPerawat.getText().trim().equals("")){
                        Valid.textKosong(kdptg,"Petugas");
                    }else if(TKdPrw1.getText().trim().equals("")||TNmPrw1.getText().trim().equals("")){
                        Valid.textKosong(TKdPrw1,"perawatan");
                    }else{
                        if(tbRawatPr.getSelectedRow()>-1){
                            if(Sequel.cariRegistrasi(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),1).toString())>0){
                                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh diubah.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                                TCari.requestFocus();
                            }else{
                                Sequel.AutoComitFalse();
                                sukses=true;
                                if(Sequel.mengedittf("rawat_inap_pr","no_rawat='"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),1)+
                                        "' and kd_jenis_prw='"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),10)+
                                        "' and nip='"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),5)+
                                        "' and tgl_perawatan='"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),7)+
                                        "' and jam_rawat='"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),8)+"'",
                                        "no_rawat='"+TNoRw.getText()+"',kd_jenis_prw='"+TKdPrw1.getText()+
                                        "',nip='"+kdptg.getText()+"',material='"+BagianRS.getText()+
                                        "',bhp='"+Bhp.getText()+"',tarif_tindakanpr='"+JmPerawat.getText()+"',biaya_rawat='"+TTnd.getText()+
                                        "',tgl_perawatan='"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"',jam_rawat='"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"',"+
                                        "kso='"+KSO.getText()+"',menejemen='"+Menejemen.getText()+"'")==true){
                                    
                                    Sequel.queryu("delete from tampjurnal");
                                    if(Valid.SetAngka(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),9).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Tindakan_Ranap+"','Suspen Piutang Tindakan Ranap','0','"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),9).toString()+"'","kredit=kredit+'"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),9).toString()+"'","kd_rek='"+Suspen_Piutang_Tindakan_Ranap+"'"); 
                                        Sequel.menyimpan("tampjurnal","'"+Tindakan_Ranap+"','Pendapatan Tindakan Rawat Inap','"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),9).toString()+"','0'","debet=debet+'"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),9).toString()+"'","kd_rek='"+Tindakan_Ranap+"'");
                                    }
                                    if(Valid.SetAngka(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),11).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Beban Jasa Medik Dokter Tindakan Ranap','0','"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),11).toString()+"'","kredit=kredit+'"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),11).toString()+"'","kd_rek='"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"'"); 
                                        Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Utang Jasa Medik Dokter Tindakan Ranap','"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),11).toString()+"','0'","debet=debet+'"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),11).toString()+"'","kd_rek='"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"'"); 
                                    }
                                    if(Valid.SetAngka(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),12).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Beban_KSO_Tindakan_Ranap+"','Beban KSO Tindakan Ranap','0','"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),12).toString()+"'","kredit=kredit+'"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),12).toString()+"'","kd_rek='"+Beban_KSO_Tindakan_Ranap+"'");  
                                        Sequel.menyimpan("tampjurnal","'"+Utang_KSO_Tindakan_Ranap+"','Utang KSO Tindakan Ranap','"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),12).toString()+"','0'","debet=debet+'"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),12).toString()+"'","kd_rek='"+Utang_KSO_Tindakan_Ranap+"'");  
                                    }
                                    if(Valid.SetAngka(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),13).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Sarana_Tindakan_Ranap+"','Beban Jasa Sarana Tindakan Ranap','0','"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),13).toString()+"'","kredit=kredit+'"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),13).toString()+"'","kd_rek='"+Beban_Jasa_Sarana_Tindakan_Ranap+"'");  
                                        Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Sarana_Tindakan_Ranap+"','Utang Jasa Sarana Tindakan Ranap','"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),13).toString()+"','0'","debet=debet+'"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),13).toString()+"'","kd_rek='"+Utang_Jasa_Sarana_Tindakan_Ranap+"'"); 
                                    }
                                    if(Valid.SetAngka(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),14).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+HPP_BHP_Tindakan_Ranap+"','HPP BHP Tindakan Ranap','0','"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),14).toString()+"'","kredit=kredit+'"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),14).toString()+"'","kd_rek='"+HPP_BHP_Tindakan_Ranap+"'"); 
                                        Sequel.menyimpan("tampjurnal","'"+Persediaan_BHP_Tindakan_Ranap+"','Persediaan BHP Tindakan Ranap','"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),14).toString()+"','0'","debet=debet+'"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),14).toString()+"'","kd_rek='"+Persediaan_BHP_Tindakan_Ranap+"'"); 
                                    }
                                    if(Valid.SetAngka(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),15).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Menejemen_Tindakan_Ranap+"','Beban Jasa Menejemen Tindakan Ranap','0','"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),15).toString()+"'","kredit=kredit+'"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),15).toString()+"'","kd_rek='"+Beban_Jasa_Menejemen_Tindakan_Ranap+"'"); 
                                        Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Menejemen_Tindakan_Ranap+"','Utang Jasa Menejemen Tindakan Ranap','"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),15).toString()+"','0'","debet=debet+'"+tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),15).toString()+"'","kd_rek='"+Utang_Jasa_Menejemen_Tindakan_Ranap+"'");
                                    }
                                    //sukses=jur.simpanJurnal(TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),"U","PEMBATALAN TINDAKAN RAWAT INAP PASIEN OLEH "+var.getkode());

                                    if(sukses==true){
                                        Sequel.queryu("delete from tampjurnal");
                                        if(Valid.SetAngka(TTnd.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Tindakan_Ranap+"','Suspen Piutang Tindakan Ranap','"+TTnd.getText()+"','0'","debet=debet+'"+(TTnd.getText())+"'","kd_rek='"+Suspen_Piutang_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Tindakan_Ranap+"','Pendapatan Tindakan Rawat Inap','0','"+TTnd.getText()+"'","kredit=kredit+'"+(TTnd.getText())+"'","kd_rek='"+Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(JmPerawat.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Beban Jasa Medik Paramedis Tindakan Ranap','"+JmPerawat.getText()+"','0'","debet=debet+'"+(JmPerawat.getText())+"'","kd_rek='"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Utang Jasa Medik Paramedis Tindakan Ranap','0','"+JmPerawat.getText()+"'","kredit=kredit+'"+(JmPerawat.getText())+"'","kd_rek='"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(KSO.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Beban_KSO_Tindakan_Ranap+"','Beban KSO Tindakan Ranap','"+KSO.getText()+"','0'","debet=debet+'"+(KSO.getText())+"'","kd_rek='"+Beban_KSO_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Utang_KSO_Tindakan_Ranap+"','Utang KSO Tindakan Ranap','0','"+KSO.getText()+"'","kredit=kredit+'"+(KSO.getText())+"'","kd_rek='"+Utang_KSO_Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(BagianRS.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Sarana_Tindakan_Ranap+"','Beban Jasa Sarana Tindakan Ranap','"+BagianRS.getText()+"','0'","debet=debet+'"+(BagianRS.getText())+"'","kd_rek='"+Beban_Jasa_Sarana_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Sarana_Tindakan_Ranap+"','Utang Jasa Sarana Tindakan Ranap','0','"+BagianRS.getText()+"'","kredit=kredit+'"+(BagianRS.getText())+"'","kd_rek='"+Utang_Jasa_Sarana_Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(Bhp.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+HPP_BHP_Tindakan_Ranap+"','HPP BHP Tindakan Ranap','"+Bhp.getText()+"','0'","debet=debet+'"+(Bhp.getText())+"'","kd_rek='"+HPP_BHP_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Persediaan_BHP_Tindakan_Ranap+"','Persediaan BHP Tindakan Ranap','0','"+Bhp.getText()+"'","kredit=kredit+'"+(Bhp.getText())+"'","kd_rek='"+Persediaan_BHP_Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(Menejemen.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Menejemen_Tindakan_Ranap+"','Beban Jasa Menejemen Tindakan Ranap','"+Menejemen.getText()+"','0'","debet=debet+'"+(Menejemen.getText())+"'","kd_rek='"+Beban_Jasa_Menejemen_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Menejemen_Tindakan_Ranap+"','Utang Jasa Menejemen Tindakan Ranap','0','"+Menejemen.getText()+"'","kredit=kredit+'"+(Menejemen.getText())+"'","kd_rek='"+Utang_Jasa_Menejemen_Tindakan_Ranap+"'");   
                                        }
                                        //sukses=jur.simpanJurnal(TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),"U","TINDAKAN RAWAT INAP PASIEN "+TPasien.getText());
                                    }
                                }else{
                                    sukses=false;
                                } 
                                if(sukses==true){
                                    //Sequel.Commit();
                                    tampilPr();
                                    BtnBatalActionPerformed(evt);
                                }else{
                                    //JOptionPane.showMessageDialog(null,"Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
                                    //Sequel.RollBack();
                                }
                                Sequel.AutoComitTrue();
                            }
                        }else{
                            JOptionPane.showMessageDialog(rootPane,"Silahkan pilih data yang mau diganti..!!");
                            TCari.requestFocus();
                        }
                    }   break;
                case 2:
                    if(KdDok2.getText().trim().equals("")||TDokter2.getText().trim().equals("")){
                        Valid.textKosong(KdDok2,"Dokter");
                    }else if(kdptg2.getText().trim().equals("")||TPerawat2.getText().trim().equals("")){
                        Valid.textKosong(kdptg2,"Petugas");
                    }else if(TKdPrw2.getText().trim().equals("")||TNmPrw2.getText().trim().equals("")){
                        Valid.textKosong(TKdPrw2,"perawatan");                            
                    }else{
                        if(tbRawatDrPr.getSelectedRow()>-1){
                            if(Sequel.cariRegistrasi(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),1).toString())>0){
                                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh diubah.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                                TCari.requestFocus();
                            }else{
                                Sequel.AutoComitFalse();
                                sukses=true;
                                if(Sequel.mengedittf("rawat_inap_drpr","no_rawat='"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),1)+
                                        "' and kd_jenis_prw='"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),12)+
                                        "' and kd_dokter='"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),5)+
                                        "' and nip='"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),7)+
                                        "' and tgl_perawatan='"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),9)+
                                        "' and jam_rawat='"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),10)+"'",
                                        "no_rawat='"+TNoRw.getText()+"',kd_jenis_prw='"+TKdPrw2.getText()+
                                        "',nip='"+kdptg2.getText()+"',kd_dokter='"+KdDok2.getText()+"',material='"+BagianRS.getText()+
                                        "',bhp='"+Bhp.getText()+"',tarif_tindakanpr='"+JmPerawat.getText()+"',tarif_tindakandr='"+JmDokter.getText()+"',biaya_rawat='"+TTnd.getText()+
                                        "',tgl_perawatan='"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"',jam_rawat='"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"',"+
                                        "kso='"+KSO.getText()+"',menejemen='"+Menejemen.getText()+"'")==true){
                                    
                                    Sequel.queryu("delete from tampjurnal");
                                    if(Valid.SetAngka(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),11).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Tindakan_Ranap+"','Suspen Piutang Tindakan Ranap','0','"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),11).toString()+"'","kredit=kredit+'"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),11).toString()+"'","kd_rek='"+Suspen_Piutang_Tindakan_Ranap+"'"); 
                                        Sequel.menyimpan("tampjurnal","'"+Tindakan_Ranap+"','Pendapatan Tindakan Rawat Inap','"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),11).toString()+"','0'","debet=debet+'"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),11).toString()+"'","kd_rek='"+Tindakan_Ranap+"'");   
                                    }
                                    if(Valid.SetAngka(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),13).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"','Beban Jasa Medik Dokter Tindakan Ranap','0','"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),13).toString()+"'","kredit=kredit+'"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),13).toString()+"'","kd_rek='"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"'"); 
                                        Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"','Utang Jasa Medik Dokter Tindakan Ranap','"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),13).toString()+"','0'","debet=debet+'"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),13).toString()+"'","kd_rek='"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"'"); 
                                    }
                                    if(Valid.SetAngka(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),14).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Beban Jasa Medik Paramedis Tindakan Ranap','0','"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),14).toString()+"'","kredit=kredit+'"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),14).toString()+"'","kd_rek='"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"'");     
                                        Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Utang Jasa Medik Paramedis Tindakan Ranap','"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),14).toString()+"','0'","debet=debet+'"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),14).toString()+"'","kd_rek='"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"'");         
                                    }
                                    if(Valid.SetAngka(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),15).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Beban_KSO_Tindakan_Ranap+"','Beban KSO Tindakan Ranap','0','"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),15).toString()+"'","kredit=kredit+'"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),15).toString()+"'","kd_rek='"+Beban_KSO_Tindakan_Ranap+"'");  
                                        Sequel.menyimpan("tampjurnal","'"+Utang_KSO_Tindakan_Ranap+"','Utang KSO Tindakan Ranap','"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),15).toString()+"','0'","debet=debet+'"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),15).toString()+"'","kd_rek='"+Utang_KSO_Tindakan_Ranap+"'");                               
                                    }
                                    if(Valid.SetAngka(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),16).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Sarana_Tindakan_Ranap+"','Beban Jasa Sarana Tindakan Ranap','0','"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),16).toString()+"'","kredit=kredit+'"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),16).toString()+"'","kd_rek='"+Beban_Jasa_Sarana_Tindakan_Ranap+"'"); 
                                        Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Sarana_Tindakan_Ranap+"','Utang Jasa Sarana Tindakan Ranap','"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),16).toString()+"','0'","debet=debet+'"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),16).toString()+"'","kd_rek='"+Utang_Jasa_Sarana_Tindakan_Ranap+"'"); 
                                    }
                                    if(Valid.SetAngka(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),17).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+HPP_BHP_Tindakan_Ranap+"','HPP BHP Tindakan Ranap','0','"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),17).toString()+"'","kredit=kredit+'"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),17).toString()+"'","kd_rek='"+HPP_BHP_Tindakan_Ranap+"'"); 
                                        Sequel.menyimpan("tampjurnal","'"+Persediaan_BHP_Tindakan_Ranap+"','Persediaan BHP Tindakan Ranap','"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),17).toString()+"','0'","debet=debet+'"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),17).toString()+"'","kd_rek='"+Persediaan_BHP_Tindakan_Ranap+"'"); 
                                    }
                                    if(Valid.SetAngka(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),18).toString())>0){
                                        Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Menejemen_Tindakan_Ranap+"','Beban Jasa Menejemen Tindakan Ranap','0','"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),18).toString()+"'","kredit=kredit+'"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),18).toString()+"'","kd_rek='"+Beban_Jasa_Menejemen_Tindakan_Ranap+"'");    
                                        Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Menejemen_Tindakan_Ranap+"','Utang Jasa Menejemen Tindakan Ranap','"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),18).toString()+"','0'","debet=debet+'"+tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),18).toString()+"'","kd_rek='"+Utang_Jasa_Menejemen_Tindakan_Ranap+"'");  
                                    }
                                    //sukses=jur.simpanJurnal(TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),"U","PEMBATALAN TINDAKAN RAWAT INAP PASIEN OLEH "+var.getkode());

                                    if(sukses==true){
                                        Sequel.queryu("delete from tampjurnal");
                                        if(Valid.SetAngka(TTnd.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Tindakan_Ranap+"','Suspen Piutang Tindakan Ranap','"+TTnd.getText()+"','0'","debet=debet+'"+(TTnd.getText())+"'","kd_rek='"+Suspen_Piutang_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Tindakan_Ranap+"','Pendapatan Tindakan Rawat Inap','0','"+TTnd.getText()+"'","kredit=kredit+'"+(TTnd.getText())+"'","kd_rek='"+Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(JmDokter.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"','Beban Jasa Medik Dokter Tindakan Ranap','"+JmDokter.getText()+"','0'","debet=debet+'"+(JmDokter.getText())+"'","kd_rek='"+Beban_Jasa_Medik_Dokter_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"','Utang Jasa Medik Dokter Tindakan Ranap','0','"+JmDokter.getText()+"'","kredit=kredit+'"+(JmDokter.getText())+"'","kd_rek='"+Utang_Jasa_Medik_Dokter_Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(JmPerawat.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Beban Jasa Medik Paramedis Tindakan Ranap','"+JmPerawat.getText()+"','0'","debet=debet+'"+(JmPerawat.getText())+"'","kd_rek='"+Beban_Jasa_Medik_Paramedis_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"','Utang Jasa Medik Paramedis Tindakan Ranap','0','"+JmPerawat.getText()+"'","kredit=kredit+'"+(JmPerawat.getText())+"'","kd_rek='"+Utang_Jasa_Medik_Paramedis_Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(KSO.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Beban_KSO_Tindakan_Ranap+"','Beban KSO Tindakan Ranap','"+KSO.getText()+"','0'","debet=debet+'"+(KSO.getText())+"'","kd_rek='"+Beban_KSO_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Utang_KSO_Tindakan_Ranap+"','Utang KSO Tindakan Ranap','0','"+KSO.getText()+"'","kredit=kredit+'"+(KSO.getText())+"'","kd_rek='"+Utang_KSO_Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(BagianRS.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Sarana_Tindakan_Ranap+"','Beban Jasa Sarana Tindakan Ranap','"+BagianRS.getText()+"','0'","debet=debet+'"+(BagianRS.getText())+"'","kd_rek='"+Beban_Jasa_Sarana_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Sarana_Tindakan_Ranap+"','Utang Jasa Sarana Tindakan Ranap','0','"+BagianRS.getText()+"'","kredit=kredit+'"+(BagianRS.getText())+"'","kd_rek='"+Utang_Jasa_Sarana_Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(Bhp.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+HPP_BHP_Tindakan_Ranap+"','HPP BHP Tindakan Ranap','"+Bhp.getText()+"','0'","debet=debet+'"+(Bhp.getText())+"'","kd_rek='"+HPP_BHP_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Persediaan_BHP_Tindakan_Ranap+"','Persediaan BHP Tindakan Ranap','0','"+Bhp.getText()+"'","kredit=kredit+'"+(Bhp.getText())+"'","kd_rek='"+Persediaan_BHP_Tindakan_Ranap+"'");   
                                        }
                                        if(Valid.SetAngka(Menejemen.getText())>0){
                                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Menejemen_Tindakan_Ranap+"','Beban Jasa Menejemen Tindakan Ranap','"+Menejemen.getText()+"','0'","debet=debet+'"+(Menejemen.getText())+"'","kd_rek='"+Beban_Jasa_Menejemen_Tindakan_Ranap+"'");  
                                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Menejemen_Tindakan_Ranap+"','Utang Jasa Menejemen Tindakan Ranap','0','"+Menejemen.getText()+"'","kredit=kredit+'"+(Menejemen.getText())+"'","kd_rek='"+Utang_Jasa_Menejemen_Tindakan_Ranap+"'");   
                                        }
                                        //sukses=jur.simpanJurnal(TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),"U","TINDAKAN RAWAT INAP PASIEN "+TPasien.getText());
                                    }   
                                }else{
                                    sukses=false;
                                } 
                                
                                if(sukses==true){
                                    //Sequel.Commit();
                                    tampilDrPr();
                                    BtnBatalActionPerformed(evt);
                                }else{
                                    //JOptionPane.showMessageDialog(null,"Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
                                    //Sequel.RollBack();
                                }
                                Sequel.AutoComitTrue();                                    
                            }
                        }else{
                            JOptionPane.showMessageDialog(rootPane,"Silahkan pilih data yang mau diganti..!!");
                            TCari.requestFocus();
                        }
                    }   break;
                case 3:
                    if(TKeluhan.getText().trim().equals("")&&TPemeriksaan.getText().trim().equals("")&&TPenilaian.getText().trim().equals("")&&TSuhu.getText().trim().equals("")&&TTensi.getText().trim().equals("")){
                        Valid.textKosong(TKeluhan,"Hasil Periksa/Perkambangan/Suhu Badan/Tensi");                            
                    }else{
                        if(tbPemeriksaan.getSelectedRow()>-1){
                            Sequel.mengedit("pemeriksaan_ranap","no_rawat='"+tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),1)+
                                "' and tgl_perawatan='"+tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),4)+
                                "' and jam_rawat='"+tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),5)+"'",
                                "no_rawat='"+TNoRw.getText()+"',suhu_tubuh='"+TSuhu.getText()+"',tensi='"+TTensi.getText()+"',"+
                                "keluhan='"+TKeluhan.getText()+"',pemeriksaan='"+TPemeriksaan.getText()+"',"+
                                "nadi='"+TNadi.getText()+"',respirasi='"+TRespirasi.getText()+"',tinggi='"+TTinggi.getText()+"',berat='"+TBerat.getText()+"',"+
                                "gcs='"+TGCS.getText()+"',kesadaran='"+cmbKesadaran.getSelectedItem()+"',alergi='"+TAlergi.getText()+"',tgl_perawatan='"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"',"+
                                "jam_rawat='"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"',"+
                                "rtl='"+TindakLanjut.getText()+"',penilaian='"+TPenilaian.getText()+"'");
                            tampilPemeriksaan();
                            BtnBatalActionPerformed(evt);
                        }else{
                            JOptionPane.showMessageDialog(rootPane,"Silahkan pilih data yang mau diganti..!!");
                            TCari.requestFocus();
                        }
                    }   break;
                default:
                    break;                
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void tbRawatDrPrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRawatDrPrMouseClicked
        if(tabModeDrPr.getRowCount()!=0){
            try {
                getDataDrPr();
            } catch (java.lang.NullPointerException e) {
            }
            
        }
    }//GEN-LAST:event_tbRawatDrPrMouseClicked

    private void tbPemeriksaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemeriksaanMouseClicked
        if(tabModePemeriksaan.getRowCount()!=0){
            try {
                getDataPemeriksaan();
            } catch (java.lang.NullPointerException e) {
            }           
            
        }
    }//GEN-LAST:event_tbPemeriksaanMouseClicked

    private void TAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlergiKeyPressed
        Valid.pindah(evt,cmbKesadaran,TPenilaian);
    }//GEN-LAST:event_TAlergiKeyPressed

    private void TGCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TGCSKeyPressed
        Valid.pindah(evt,TNadi,cmbKesadaran);
    }//GEN-LAST:event_TGCSKeyPressed

    private void TRespirasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRespirasiKeyPressed
        Valid.pindah(evt,TTensi,TNadi);
    }//GEN-LAST:event_TRespirasiKeyPressed

    private void TNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNadiKeyPressed
        Valid.pindah(evt,TRespirasi,TGCS);
    }//GEN-LAST:event_TNadiKeyPressed

    private void TBeratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBeratKeyPressed
        Valid.pindah(evt,TTinggi,TTensi);
    }//GEN-LAST:event_TBeratKeyPressed

    private void TNadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNadiActionPerformed
        
    }//GEN-LAST:event_TNadiActionPerformed

    private void TTinggiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTinggiKeyPressed
        Valid.pindah(evt,TSuhu,TBerat);
    }//GEN-LAST:event_TTinggiKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void KdDokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDokKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?",TDokter,KdDok.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnSeekDokterActionPerformed(null);
        }else{
            Valid.pindah(evt,TNoRw,TKdPrw);
        }
    }//GEN-LAST:event_KdDokKeyPressed

    private void BtnSeekDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekDokterActionPerformed
        var.setform("DlgRawatInap");
        perawatan.dokter.emptTeks();
        perawatan.dokter.isCek();
        perawatan.dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        perawatan.dokter.setLocationRelativeTo(internalFrame1);
        perawatan.dokter.setVisible(true);
    }//GEN-LAST:event_BtnSeekDokterActionPerformed

    private void TKdPrwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdPrwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isJns();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnTindakanActionPerformed(null);
        }else{            
            Valid.pindah(evt,KdDok,BtnSimpan);
        }
    }//GEN-LAST:event_TKdPrwKeyPressed

    private void kdptgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdptgKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select nama from petugas where nip=?",TPerawat,kdptg.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnSeekPetugasActionPerformed(null);
        }else{
            Valid.pindah(evt,TNoRw,TKdPrw1);
        }
    }//GEN-LAST:event_kdptgKeyPressed

    private void BtnSeekPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekPetugasActionPerformed
        var.setform("DlgRawatInap");
        perawatan.petugas.emptTeks();
        perawatan.petugas.isCek();
        perawatan.petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        perawatan.petugas.setLocationRelativeTo(internalFrame1);
        perawatan.petugas.setVisible(true);
    }//GEN-LAST:event_BtnSeekPetugasActionPerformed

    private void TKdPrw1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdPrw1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isJns();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnTindakan4ActionPerformed(null);
        }else{
            Valid.pindah(evt,kdptg,BtnSimpan);
        }
    }//GEN-LAST:event_TKdPrw1KeyPressed

    private void kdptg2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdptg2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select nama from petugas where nip=?",TPerawat2,kdptg2.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnSeekPetugas2ActionPerformed(null);
        }else{
            Valid.pindah(evt,KdDok2,TKdPrw2);
        }
    }//GEN-LAST:event_kdptg2KeyPressed

    private void BtnSeekPetugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekPetugas2ActionPerformed
        var.setform("DlgRawatInap");
        perawatan.petugas.emptTeks();
        perawatan.petugas.isCek();
        perawatan.petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        perawatan.petugas.setLocationRelativeTo(internalFrame1);
        perawatan.petugas.setVisible(true);
    }//GEN-LAST:event_BtnSeekPetugas2ActionPerformed

    private void KdDok2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDok2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?",TDokter2,KdDok2.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnSeekDokter2ActionPerformed(null);
        }else{
            Valid.pindah(evt,TNoRw,kdptg2);
        }
    }//GEN-LAST:event_KdDok2KeyPressed

    private void BtnSeekDokter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekDokter2ActionPerformed
        var.setform("DlgRawatInap");
        perawatan.dokter.emptTeks();
        perawatan.dokter.isCek();
        perawatan.dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        perawatan.dokter.setLocationRelativeTo(internalFrame1);
        perawatan.dokter.setVisible(true);
    }//GEN-LAST:event_BtnSeekDokter2ActionPerformed

    private void TKdPrw2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdPrw2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isJns();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnTindakan6ActionPerformed(null);
        }else{
            Valid.pindah(evt,kdptg2,BtnSimpan);
        }
    }//GEN-LAST:event_TKdPrw2KeyPressed

    private void btnTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTindakanActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else if(TDokter.getText().trim().equals("")){
            Valid.textKosong(KdDok,"Dokter");
        }else{  
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                TCari.requestFocus();
            }else{
                perawatan.setNoRm(TNoRw.getText(),"rawat_inap_dr",DTPTgl.getDate(),cmbJam.getSelectedItem().toString(),cmbMnt.getSelectedItem().toString(),cmbDtk.getSelectedItem().toString(),false,TPasien.getText());
                perawatan.setPetugas(KdDok.getText(),TDokter.getText(),TSuhu.getText(),TTensi.getText(),TKeluhan.getText(),TPemeriksaan.getText(),"","",
                    TBerat.getText(),TTinggi.getText(),TNadi.getText(),TRespirasi.getText(),TGCS.getText(),TAlergi.getText());
                var.setform("DlgRawatInap");
                perawatan.emptTeks();
                perawatan.isCek();
                perawatan.tampil();
                perawatan.setSize(this.getWidth()-20,this.getHeight()-20);
                perawatan.setLocationRelativeTo(internalFrame1);
                perawatan.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnTindakanActionPerformed

    private void btnTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnTindakanKeyPressed
        Valid.pindah(evt,TKdPrw,TKeluhan);
    }//GEN-LAST:event_btnTindakanKeyPressed

    private void btnTindakan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTindakan3ActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else if(TDokter.getText().trim().equals("")){
            Valid.textKosong(KdDok,"Dokter");
        }else{  
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                TCari.requestFocus();
            }else{
                perawatan2.setNoRm(TNoRw.getText(),"rawat_inap_dr",DTPTgl.getDate(),cmbJam.getSelectedItem().toString(),cmbMnt.getSelectedItem().toString(),cmbDtk.getSelectedItem().toString(),false,TPasien.getText());
                perawatan2.setPetugas(KdDok.getText(),TDokter.getText(),TSuhu.getText(),TTensi.getText(),TKeluhan.getText(),TPemeriksaan.getText(),"","",
                    TBerat.getText(),TTinggi.getText(),TNadi.getText(),TRespirasi.getText(),TGCS.getText(),TAlergi.getText());
                var.setform("DlgRawatInap");
                perawatan2.isCek();
                perawatan2.tampil2();
                perawatan2.setSize(this.getWidth()-20,this.getHeight()-20);
                perawatan2.setLocationRelativeTo(internalFrame1);
                perawatan2.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnTindakan3ActionPerformed

    private void btnTindakan3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnTindakan3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTindakan3KeyPressed

    private void btnTindakan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTindakan4ActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else if(TPerawat.getText().trim().equals("")){
            Valid.textKosong(kdptg,"perawat");
        }else{
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                TCari.requestFocus();
            }else{
                perawatan.setNoRm(TNoRw.getText(),"rawat_inap_pr",DTPTgl.getDate(),cmbJam.getSelectedItem().toString(),cmbMnt.getSelectedItem().toString(),cmbDtk.getSelectedItem().toString(),false,TPasien.getText());
                perawatan.setPetugas(kdptg.getText(),TPerawat.getText(),TSuhu.getText(),TTensi.getText(),TKeluhan.getText(),TPemeriksaan.getText(),"","",
                    TBerat.getText(),TTinggi.getText(),TNadi.getText(),TRespirasi.getText(),TGCS.getText(),TAlergi.getText());
                var.setform("DlgRawatInap");
                perawatan.emptTeks();
                perawatan.isCek();
                perawatan.tampil();
                perawatan.setSize(this.getWidth()-20,this.getHeight()-20);
                perawatan.setLocationRelativeTo(internalFrame1);
                perawatan.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnTindakan4ActionPerformed

    private void btnTindakan4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnTindakan4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTindakan4KeyPressed

    private void btnTindakan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTindakan5ActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else if(TPerawat.getText().trim().equals("")){
            Valid.textKosong(kdptg,"perawat");
        }else{
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                TCari.requestFocus();
            }else{
                perawatan2.setNoRm(TNoRw.getText(),"rawat_inap_pr",DTPTgl.getDate(),cmbJam.getSelectedItem().toString(),cmbMnt.getSelectedItem().toString(),cmbDtk.getSelectedItem().toString(),false,TPasien.getText());
                perawatan2.setPetugas(kdptg.getText(),TPerawat.getText(),TSuhu.getText(),TTensi.getText(),TKeluhan.getText(),TPemeriksaan.getText(),"","",
                    TBerat.getText(),TTinggi.getText(),TNadi.getText(),TRespirasi.getText(),TGCS.getText(),TAlergi.getText());
                var.setform("DlgRawatInap");
                perawatan2.isCek();
                perawatan2.tampil2();
                perawatan2.setSize(this.getWidth()-20,this.getHeight()-20);
                perawatan2.setLocationRelativeTo(internalFrame1);
                perawatan2.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnTindakan5ActionPerformed

    private void btnTindakan5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnTindakan5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTindakan5KeyPressed

    private void btnTindakan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTindakan6ActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else if(TDokter2.getText().trim().equals("")){
            Valid.textKosong(KdDok2,"Dokter");
        }else if(TPerawat2.getText().trim().equals("")){
            Valid.textKosong(kdptg2,"perawat");
        }else{ 
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                TCari.requestFocus();
            }else{
                perawatan.setNoRm(TNoRw.getText(),"rawat_inap_drpr",DTPTgl.getDate(),cmbJam.getSelectedItem().toString(),cmbMnt.getSelectedItem().toString(),cmbDtk.getSelectedItem().toString(),false,TPasien.getText());
                perawatan.setPetugas(KdDok2.getText(),TDokter2.getText(),TSuhu.getText(),TTensi.getText(),TKeluhan.getText(),TPemeriksaan.getText(),kdptg2.getText(),TPerawat2.getText(),
                    TBerat.getText(),TTinggi.getText(),TNadi.getText(),TRespirasi.getText(),TGCS.getText(),TAlergi.getText());
                var.setform("DlgRawatInap");
                perawatan.emptTeks();
                perawatan.isCek();
                perawatan.tampil();
                perawatan.setSize(this.getWidth()-20,this.getHeight()-20);
                perawatan.setLocationRelativeTo(internalFrame1);
                perawatan.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnTindakan6ActionPerformed

    private void btnTindakan6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnTindakan6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTindakan6KeyPressed

    private void btnTindakan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTindakan7ActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else if(TDokter2.getText().trim().equals("")){
            Valid.textKosong(KdDok2,"Dokter");
        }else if(TPerawat2.getText().trim().equals("")){
            Valid.textKosong(kdptg2,"perawat");
        }else{ 
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                TCari.requestFocus();
            }else{
                perawatan2.setNoRm(TNoRw.getText(),"rawat_inap_drpr",DTPTgl.getDate(),cmbJam.getSelectedItem().toString(),cmbMnt.getSelectedItem().toString(),cmbDtk.getSelectedItem().toString(),false,TPasien.getText());
                perawatan2.setPetugas(KdDok2.getText(),TDokter2.getText(),TSuhu.getText(),TTensi.getText(),TKeluhan.getText(),TPemeriksaan.getText(),kdptg2.getText(),TPerawat2.getText(),
                    TBerat.getText(),TTinggi.getText(),TNadi.getText(),TRespirasi.getText(),TGCS.getText(),TAlergi.getText());
                var.setform("DlgRawatInap");
                perawatan2.isCek();
                perawatan2.tampil2();
                perawatan2.setSize(this.getWidth()-20,this.getHeight()-20);
                perawatan2.setLocationRelativeTo(internalFrame1);
                perawatan2.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnTindakan7ActionPerformed

    private void btnTindakan7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnTindakan7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTindakan7KeyPressed

    private void ChkInputItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkInputItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInputItemStateChanged

    private void TKeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKeluhanKeyPressed
        Valid.pindah(evt,TKdPrw,TPemeriksaan);
    }//GEN-LAST:event_TKeluhanKeyPressed

    private void TPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaanKeyPressed
        Valid.pindah(evt,TKeluhan,TSuhu);
    }//GEN-LAST:event_TPemeriksaanKeyPressed

    private void tbRawatDrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRawatDrKeyReleased
        if(tabModeDr.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataDr();
                } catch (java.lang.NullPointerException e) {
                }
            }
                       
        }
    }//GEN-LAST:event_tbRawatDrKeyReleased

    private void tbRawatPrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRawatPrKeyReleased
        if(tabModePr.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataPr();
                } catch (java.lang.NullPointerException e) {
                }
            }
            
        }
    }//GEN-LAST:event_tbRawatPrKeyReleased

    private void tbRawatDrPrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRawatDrPrKeyReleased
        if(tabModeDrPr.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataDrPr();
                } catch (java.lang.NullPointerException e) {
                }
            }
            
        }
    }//GEN-LAST:event_tbRawatDrPrKeyReleased

    private void tbPemeriksaanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemeriksaanKeyReleased
        if(tabModePemeriksaan.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataPemeriksaan();
                } catch (java.lang.NullPointerException e) {
                }
            }
                       
        }
    }//GEN-LAST:event_tbPemeriksaanKeyReleased

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void BtnResepObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepObatActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{            
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi ..!!");
            }else{
                DlgPeresepanDokter resep=new DlgPeresepanDokter(null,false);
                resep.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
                resep.setLocationRelativeTo(internalFrame1);
                resep.setNoRm(TNoRw.getText(),DTPTgl.getDate(),cmbJam.getSelectedItem().toString(),cmbMnt.getSelectedItem().toString(),
                        cmbDtk.getSelectedItem().toString(),KdDok.getText(),TDokter.getText(),"ranap");
                resep.isCek();
                resep.tampilobat();
                resep.setVisible(true);
            }
        }
    }//GEN-LAST:event_BtnResepObatActionPerformed

    private void BtnCopyResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopyResepActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCopyResep daftar=new DlgCopyResep(null,false);
            daftar.isCek();
            daftar.setRM(TNoRw.getText(),TNoRM.getText(),KdDok.getText(),Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?",TNoRw.getText()),"ranap");
            daftar.tampil();
            daftar.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            daftar.setLocationRelativeTo(internalFrame1);
            daftar.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCopyResepActionPerformed

    private void BtnInputObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInputObatActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{            
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi ..!!");
            }else{
                if(Sequel.cariInteger("select count(no_rawat) from stok_obat_pasien where no_rawat=? ",TNoRw.getText())>0){
                    DlgCariObat3 dlgobt=new DlgCariObat3(null,false);
                    dlgobt.setNoRm(TNoRw.getText(),DTPTgl.getDate());
                    dlgobt.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
                    dlgobt.setLocationRelativeTo(internalFrame1);
                    dlgobt.setVisible(true);
                }else{
                    DlgCariObat2 dlgobt=new DlgCariObat2(null,false);
                    dlgobt.setNoRm(TNoRw.getText(),DTPTgl.getDate());
                    dlgobt.isCek();
                    dlgobt.tampil();
                    dlgobt.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
                    dlgobt.setLocationRelativeTo(internalFrame1);
                    dlgobt.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_BtnInputObatActionPerformed

    private void BtnObatBhpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObatBhpActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgPemberianObat dlgrwinap=new DlgPemberianObat(null,false);
            dlgrwinap.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            dlgrwinap.setLocationRelativeTo(internalFrame1);
            dlgrwinap.isCek();
            dlgrwinap.setNoRm2(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate(),"ranap");
            dlgrwinap.tampilPO();
            dlgrwinap.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnObatBhpActionPerformed

    private void BtnBerkasDigitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBerkasDigitalActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgBerkasRawat berkas=new DlgBerkasRawat(null,true);
            berkas.setJudul("::[ Berkas Digital Perawatan ]::","berkasrawat/pages");
            try {
                berkas.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"berkasrawat/login2.php?act=login&usere=admin&passwordte=akusayangsamakamu&no_rawat="+TNoRw.getText());
            } catch (Exception ex) {
                System.out.println("Notifikasi : "+ex);
            }

            berkas.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            berkas.setLocationRelativeTo(internalFrame1);
            berkas.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnBerkasDigitalActionPerformed

    private void BtnPermintaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPermintaanLabActionPerformed
        if(TPasien.getText().trim().equals("")||TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgPermintaanLaboratorium dlgro=new DlgPermintaanLaboratorium(null,false);
            dlgro.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            dlgro.setLocationRelativeTo(internalFrame1);
            dlgro.emptTeks();
            dlgro.isCek();
            dlgro.setNoRm(TNoRw.getText(),"Ranap");
            dlgro.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPermintaanLabActionPerformed

    private void BtnPermintaanRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPermintaanRadActionPerformed
        if(TPasien.getText().trim().equals("")||TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgPermintaanRadiologi dlgro=new DlgPermintaanRadiologi(null,false);
            dlgro.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            dlgro.setLocationRelativeTo(internalFrame1);
            dlgro.emptTeks();
            dlgro.isCek();
            dlgro.setNoRm(TNoRw.getText(),"Ranap");
            dlgro.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPermintaanRadActionPerformed

    private void BtnSKDPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSKDPActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{            
            DlgSKDPBPJS form=new DlgSKDPBPJS(null,false);
            form.isCek();
            form.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.emptTeks();
            kode_poli=Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat=?",TNoRw.getText());
            form.setNoRm(TNoRM.getText(),TPasien.getText(), kode_poli,Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?",kode_poli),KdDok.getText(),TDokter.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_BtnSKDPActionPerformed

    private void BtnRujukKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRujukKeluarActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{            
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi ..!!");
            }else{
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgRujuk dlgrjk=new DlgRujuk(null,false);
                dlgrjk.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
                dlgrjk.setLocationRelativeTo(internalFrame1);
                dlgrjk.emptTeks();
                dlgrjk.isCek();
                dlgrjk.setNoRm(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate());
                dlgrjk.tampil();
                dlgrjk.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }            
        }
    }//GEN-LAST:event_BtnRujukKeluarActionPerformed

    private void BtnCatatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCatatanActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{       
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCatatan catatan=new DlgCatatan(null,true);
            catatan.setNoRm(TNoRM.getText());
            catatan.setSize(720,330);
            catatan.setLocationRelativeTo(internalFrame1);
            catatan.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCatatanActionPerformed

    private void BtnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagnosaActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        }else{            
            DlgDiagnosaPenyakit resep=new DlgDiagnosaPenyakit(null,false);
            resep.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            resep.setLocationRelativeTo(internalFrame1);
            resep.isCek();
            resep.setNoRm(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate(),"Ranap");
            resep.panelDiagnosa1.tampil();
            resep.setVisible(true);
        }
    }//GEN-LAST:event_BtnDiagnosaActionPerformed

    private void BtnRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRiwayatActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMRiwayatPerawatan resume=new RMRiwayatPerawatan(null,true);
            resume.setNoRm(TNoRM.getText(),TPasien.getText());
            resume.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnRiwayatActionPerformed

    private void TPenilaianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPenilaianKeyPressed
        Valid.pindah(evt,TAlergi,TindakLanjut);
    }//GEN-LAST:event_TPenilaianKeyPressed

    private void TindakLanjutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindakLanjutKeyPressed
        Valid.pindah(evt,TPenilaian,BtnSimpan);
    }//GEN-LAST:event_TindakLanjutKeyPressed

    private void BtnResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResumeActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{ 
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMDataResumePasien resume=new RMDataResumePasien(null,false);
            resume.isCek();
            resume.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            resume.setLocationRelativeTo(internalFrame1);
            resume.setNoRm(TNoRw.getText(),DTPCari2.getDate());
            resume.tampil();
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnResumeActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        tinggitombol=0;
        if(BtnResepObat.isVisible()==true){
            tinggitombol=tinggitombol+23;
        }
        if(BtnCopyResep.isVisible()==true){
            tinggitombol=tinggitombol+23;
        }
        if(BtnInputObat.isVisible()==true){
            tinggitombol=tinggitombol+23;
        }
        if(BtnObatBhp.isVisible()==true){
            tinggitombol=tinggitombol+23;
        }
        if(BtnBerkasDigital.isVisible()==true){
            tinggitombol=tinggitombol+23;
        }
        if(BtnPermintaanLab.isVisible()==true){
            tinggitombol=tinggitombol+23;
        }
        if(BtnPermintaanRad.isVisible()==true){
            tinggitombol=tinggitombol+23;
        }
        if(BtnSKDP.isVisible()==true){
            tinggitombol=tinggitombol+23;
        }
        if(BtnRujukKeluar.isVisible()==true){
            tinggitombol=tinggitombol+23;
        }
        if(BtnCatatan.isVisible()==true){
            tinggitombol=tinggitombol+23;
        }
        if(BtnDiagnosa.isVisible()==true){
            tinggitombol=tinggitombol+23;
        }
        if(BtnResume.isVisible()==true){
            tinggitombol=tinggitombol+23;
        }
        if(BtnRiwayat.isVisible()==true){
            tinggitombol=tinggitombol+23;
        }
        
        if(PanelAccor.getHeight()<(tinggitombol+20)){   
            ScrollMenu.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            ScrollMenu.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            FormMenu.setPreferredSize(new Dimension(120,(tinggitombol+20)));
        }else{
            ScrollMenu.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            ScrollMenu.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER); 
        }
    }//GEN-LAST:event_formWindowActivated

    private void cmbKesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbKesadaranKeyPressed
        Valid.pindah(evt,TGCS,TAlergi);
    }//GEN-LAST:event_cmbKesadaranKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRawatInap dialog = new DlgRawatInap(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BagianRS;
    private javax.swing.JTextField Bhp;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnBerkasDigital;
    private widget.Button BtnCari;
    private widget.Button BtnCatatan;
    private widget.Button BtnCopyResep;
    private widget.Button BtnDiagnosa;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnInputObat;
    private widget.Button BtnKeluar;
    private widget.Button BtnObatBhp;
    private widget.Button BtnPermintaanLab;
    private widget.Button BtnPermintaanRad;
    private widget.Button BtnPrint;
    private widget.Button BtnResepObat;
    private widget.Button BtnResume;
    private widget.Button BtnRiwayat;
    private widget.Button BtnRujukKeluar;
    private widget.Button BtnSKDP;
    private widget.Button BtnSeekDokter;
    private widget.Button BtnSeekDokter2;
    private widget.Button BtnSeekPetugas;
    private widget.Button BtnSeekPetugas2;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkAccor;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMenu;
    private javax.swing.JTextField JmDokter;
    private javax.swing.JTextField JmPerawat;
    private javax.swing.JTextField KSO;
    private widget.TextBox KdDok;
    private widget.TextBox KdDok2;
    private widget.Label LCount;
    private javax.swing.JTextField Menejemen;
    private widget.PanelBiasa PanelAccor;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane ScrollMenu;
    private widget.TextBox TAlergi;
    private widget.TextBox TBerat;
    private widget.TextBox TCari;
    private widget.TextBox TCariPasien;
    private widget.TextBox TDokter;
    private widget.TextBox TDokter2;
    private widget.TextBox TGCS;
    private widget.TextBox TKdPrw;
    private widget.TextBox TKdPrw1;
    private widget.TextBox TKdPrw2;
    private widget.TextArea TKeluhan;
    private widget.TextBox TNadi;
    private widget.TextBox TNmPrw;
    private widget.TextBox TNmPrw1;
    private widget.TextBox TNmPrw2;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea TPemeriksaan;
    private widget.TextArea TPenilaian;
    private widget.TextBox TPerawat;
    private widget.TextBox TPerawat2;
    private widget.TextBox TRespirasi;
    private widget.TextBox TSuhu;
    private widget.TextBox TTensi;
    private widget.TextBox TTinggi;
    private javax.swing.JTextField TTnd;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextArea TindakLanjut;
    private widget.Button btnPasien;
    private widget.Button btnTindakan;
    private widget.Button btnTindakan3;
    private widget.Button btnTindakan4;
    private widget.Button btnTindakan5;
    private widget.Button btnTindakan6;
    private widget.Button btnTindakan7;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKesadaran;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel37;
    private widget.Label jLabel4;
    private widget.Label jLabel41;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator5;
    private widget.TextBox kdptg;
    private widget.TextBox kdptg2;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.Table tbPemeriksaan;
    private widget.Table tbRawatDr;
    private widget.Table tbRawatDrPr;
    private widget.Table tbRawatPr;
    // End of variables declaration//GEN-END:variables

    public void tampilDr() {
        Valid.tabelKosong(tabModeDr);
        try{
            if(TCari.getText().equals("")&&TCariPasien.getText().equals("")){
                ps=koneksi.prepareStatement("select rawat_inap_dr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                   "concat(rawat_inap_dr.kd_jenis_prw,' ',jns_perawatan_inap.nm_perawatan),rawat_inap_dr.kd_dokter,dokter.nm_dokter,"+
                   "rawat_inap_dr.tgl_perawatan,rawat_inap_dr.jam_rawat,rawat_inap_dr.biaya_rawat,rawat_inap_dr.kd_jenis_prw, " +
                   "rawat_inap_dr.tarif_tindakandr,rawat_inap_dr.kso,rawat_inap_dr.material,rawat_inap_dr.bhp,rawat_inap_dr.menejemen "+
                   "from pasien inner join reg_periksa inner join jns_perawatan_inap inner join "+
                   "dokter inner join rawat_inap_dr "+
                   "on rawat_inap_dr.no_rawat=reg_periksa.no_rawat "+
                   "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "and rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
                   "and rawat_inap_dr.kd_dokter=dokter.kd_dokter "+
                   "where rawat_inap_dr.tgl_perawatan between ? and ? order by rawat_inap_dr.no_rawat desc");
            }else{
                ps=koneksi.prepareStatement("select rawat_inap_dr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                   "concat(rawat_inap_dr.kd_jenis_prw,' ',jns_perawatan_inap.nm_perawatan),rawat_inap_dr.kd_dokter,dokter.nm_dokter,"+
                   "rawat_inap_dr.tgl_perawatan,rawat_inap_dr.jam_rawat,rawat_inap_dr.biaya_rawat,rawat_inap_dr.kd_jenis_prw, " +
                   "rawat_inap_dr.tarif_tindakandr,rawat_inap_dr.kso,rawat_inap_dr.material,rawat_inap_dr.bhp,rawat_inap_dr.menejemen "+
                   "from pasien inner join reg_periksa inner join jns_perawatan_inap inner join "+
                   "dokter inner join rawat_inap_dr "+
                   "on rawat_inap_dr.no_rawat=reg_periksa.no_rawat "+
                   "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "and rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
                   "and rawat_inap_dr.kd_dokter=dokter.kd_dokter "+
                   "where rawat_inap_dr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and rawat_inap_dr.no_rawat like ? or "+
                    "rawat_inap_dr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "+
                    "rawat_inap_dr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "+
                    "rawat_inap_dr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and jns_perawatan_inap.nm_perawatan like ? or "+
                    "rawat_inap_dr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and rawat_inap_dr.kd_dokter like ? or "+
                    "rawat_inap_dr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and dokter.nm_dokter like ? or "+
                    "rawat_inap_dr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and tgl_perawatan like ? "+
                   " order by rawat_inap_dr.no_rawat desc");
            }
            
            try {
                if(TCari.getText().equals("")&&TCariPasien.getText().equals("")){
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                }else{
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps.setString(3,"%"+TCariPasien.getText()+"%");
                    ps.setString(4,"%"+TCari.getText().trim()+"%");
                    ps.setString(5,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(6,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps.setString(7,"%"+TCariPasien.getText()+"%");
                    ps.setString(8,"%"+TCari.getText().trim()+"%");
                    ps.setString(9,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(10,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps.setString(11,"%"+TCariPasien.getText()+"%");
                    ps.setString(12,"%"+TCari.getText().trim()+"%");
                    ps.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps.setString(15,"%"+TCariPasien.getText()+"%");
                    ps.setString(16,"%"+TCari.getText().trim()+"%");
                    ps.setString(17,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(18,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps.setString(19,"%"+TCariPasien.getText()+"%");
                    ps.setString(20,"%"+TCari.getText().trim()+"%");
                    ps.setString(21,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(22,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps.setString(23,"%"+TCariPasien.getText()+"%");
                    ps.setString(24,"%"+TCari.getText().trim()+"%");
                    ps.setString(25,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(26,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps.setString(27,"%"+TCariPasien.getText()+"%");
                    ps.setString(28,"%"+TCari.getText().trim()+"%");
                }
                    
                rs=ps.executeQuery();
                while(rs.next()){
                    tabModeDr.addRow(new Object[]{
                        false,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
                        rs.getString(8),rs.getDouble(9),rs.getString("kd_jenis_prw"),rs.getString("tarif_tindakandr"),rs.getString("kso"),
                        rs.getString("material"),rs.getString("bhp"),rs.getString("menejemen")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }           
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabModeDr.getRowCount());
    }
    
    private void getDataDr() {
        if(tbRawatDr.getSelectedRow()!= -1){
            TNoRw.setText(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),1).toString());
            TNoRM.setText(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),2).toString());
            TPasien.setText(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),3).toString());
            KdDok.setText(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),5).toString());
            TDokter.setText(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),6).toString());
            cmbJam.setSelectedItem(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),8).toString().substring(0,2));
            cmbMnt.setSelectedItem(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),8).toString().substring(3,5));
            cmbDtk.setSelectedItem(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),8).toString().substring(6,8));
            TNmPrw.setText(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),4).toString());
            TKdPrw.setText(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),10).toString());
            Valid.SetTgl(DTPTgl,tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(),7).toString());
            isJns();
        }
    }

    private void tampilPr() {
        Valid.tabelKosong(tabModePr);
        try{  
            if(TCari.getText().equals("")&&TCariPasien.getText().equals("")){
                ps2=koneksi.prepareStatement("select rawat_inap_pr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                   "concat(rawat_inap_pr.kd_jenis_prw,' ',jns_perawatan_inap.nm_perawatan),rawat_inap_pr.nip,petugas.nama,"+
                   "rawat_inap_pr.tgl_perawatan,rawat_inap_pr.jam_rawat,rawat_inap_pr.biaya_rawat,rawat_inap_pr.kd_jenis_prw, " +
                   "rawat_inap_pr.tarif_tindakanpr,rawat_inap_pr.kso,rawat_inap_pr.material,rawat_inap_pr.bhp,rawat_inap_pr.menejemen "+
                   "from pasien inner join reg_periksa inner join jns_perawatan_inap inner join "+
                   "petugas inner join rawat_inap_pr "+
                   "on rawat_inap_pr.no_rawat=reg_periksa.no_rawat "+
                   "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "and rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
                   "and rawat_inap_pr.nip=petugas.nip where  "+
                    "rawat_inap_pr.tgl_perawatan between ? and ? order by rawat_inap_pr.no_rawat desc"); 
            }else{
                ps2=koneksi.prepareStatement("select rawat_inap_pr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                   "concat(rawat_inap_pr.kd_jenis_prw,' ',jns_perawatan_inap.nm_perawatan),rawat_inap_pr.nip,petugas.nama,"+
                   "rawat_inap_pr.tgl_perawatan,rawat_inap_pr.jam_rawat,rawat_inap_pr.biaya_rawat,rawat_inap_pr.kd_jenis_prw, " +
                   "rawat_inap_pr.tarif_tindakanpr,rawat_inap_pr.kso,rawat_inap_pr.material,rawat_inap_pr.bhp,rawat_inap_pr.menejemen "+
                   "from pasien inner join reg_periksa inner join jns_perawatan_inap inner join "+
                   "petugas inner join rawat_inap_pr "+
                   "on rawat_inap_pr.no_rawat=reg_periksa.no_rawat "+
                   "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "and rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
                   "and rawat_inap_pr.nip=petugas.nip where  "+
                    "rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and rawat_inap_pr.no_rawat like ? or "+
                    "rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "+
                    "rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "+
                    "rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and jns_perawatan_inap.nm_perawatan like ? or "+
                    "rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and rawat_inap_pr.nip like ? or "+
                    "rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and petugas.nama like ? or "+
                    "rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and rawat_inap_pr.tgl_perawatan like ? "+
                   "order by rawat_inap_pr.no_rawat desc"); 
            }
                
            try{
                if(TCari.getText().equals("")&&TCariPasien.getText().equals("")){
                    ps2.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps2.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                }else{
                    ps2.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps2.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps2.setString(3,"%"+TCariPasien.getText()+"%");
                    ps2.setString(4,"%"+TCari.getText().trim()+"%");
                    ps2.setString(5,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps2.setString(6,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps2.setString(7,"%"+TCariPasien.getText()+"%");
                    ps2.setString(8,"%"+TCari.getText().trim()+"%");
                    ps2.setString(9,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps2.setString(10,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps2.setString(11,"%"+TCariPasien.getText()+"%");
                    ps2.setString(12,"%"+TCari.getText().trim()+"%");
                    ps2.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps2.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps2.setString(15,"%"+TCariPasien.getText()+"%");
                    ps2.setString(16,"%"+TCari.getText().trim()+"%");
                    ps2.setString(17,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps2.setString(18,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps2.setString(19,"%"+TCariPasien.getText()+"%");
                    ps2.setString(20,"%"+TCari.getText().trim()+"%");
                    ps2.setString(21,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps2.setString(22,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps2.setString(23,"%"+TCariPasien.getText()+"%");
                    ps2.setString(24,"%"+TCari.getText().trim()+"%");
                    ps2.setString(25,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps2.setString(26,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps2.setString(27,"%"+TCariPasien.getText()+"%");
                    ps2.setString(28,"%"+TCari.getText().trim()+"%");
                }
                    
                rs=ps2.executeQuery();
                while(rs.next()){
                    tabModePr.addRow(new Object[]{
                        false,rs.getString(1),rs.getString(2),rs.getString(3),
                         rs.getString(4),rs.getString(5),rs.getString(6),
                         rs.getString(7),rs.getString(8),rs.getDouble(9),
                         rs.getString("kd_jenis_prw"),rs.getString("tarif_tindakanpr"),
                         rs.getString("kso"),rs.getString("material"),
                         rs.getString("bhp"),rs.getString("menejemen")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps2!=null){
                    ps2.close();
                }
            }                  
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabModePr.getRowCount());
    }

    private void getDataPr() {
        if(tbRawatPr.getSelectedRow()!= -1){
            TNoRw.setText(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),1).toString());
            TNoRM.setText(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),2).toString());
            TPasien.setText(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),3).toString());   
            kdptg.setText(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),5).toString());
            TPerawat.setText(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),6).toString());
            cmbJam.setSelectedItem(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),8).toString().substring(0,2));
            cmbMnt.setSelectedItem(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),8).toString().substring(3,5));
            cmbDtk.setSelectedItem(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),8).toString().substring(6,8));
            TNmPrw1.setText(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),4).toString());
            TKdPrw1.setText(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),10).toString());
            Valid.SetTgl(DTPTgl,tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(),7).toString());
            isJns();
        }
    }
    
    public void tampilDrPr() {
        Valid.tabelKosong(tabModeDrPr);
        try{
            if(TCari.getText().equals("")&&TCariPasien.getText().equals("")){
                ps3=koneksi.prepareStatement("select rawat_inap_drpr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                   "concat(rawat_inap_drpr.kd_jenis_prw,' ',jns_perawatan_inap.nm_perawatan),rawat_inap_drpr.kd_dokter,dokter.nm_dokter,"+
                   "rawat_inap_drpr.nip,petugas.nama,rawat_inap_drpr.tgl_perawatan,rawat_inap_drpr.jam_rawat,rawat_inap_drpr.biaya_rawat,rawat_inap_drpr.kd_jenis_prw, " +
                   "rawat_inap_drpr.tarif_tindakandr,rawat_inap_drpr.tarif_tindakanpr,rawat_inap_drpr.kso,rawat_inap_drpr.material,rawat_inap_drpr.bhp,rawat_inap_drpr.menejemen "+
                   "from pasien inner join reg_periksa inner join jns_perawatan_inap inner join "+
                   "dokter inner join rawat_inap_drpr inner join petugas on rawat_inap_drpr.no_rawat=reg_periksa.no_rawat "+
                   "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
                   "and rawat_inap_drpr.kd_dokter=dokter.kd_dokter and rawat_inap_drpr.nip=petugas.nip "+
                   "where rawat_inap_drpr.tgl_perawatan between ? and ? order by rawat_inap_drpr.no_rawat desc");
            }else{
                ps3=koneksi.prepareStatement("select rawat_inap_drpr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                   "concat(rawat_inap_drpr.kd_jenis_prw,' ',jns_perawatan_inap.nm_perawatan),rawat_inap_drpr.kd_dokter,dokter.nm_dokter,"+
                   "rawat_inap_drpr.nip,petugas.nama,rawat_inap_drpr.tgl_perawatan,rawat_inap_drpr.jam_rawat,rawat_inap_drpr.biaya_rawat,rawat_inap_drpr.kd_jenis_prw, " +
                   "rawat_inap_drpr.tarif_tindakandr,rawat_inap_drpr.tarif_tindakanpr,rawat_inap_drpr.kso,rawat_inap_drpr.material,rawat_inap_drpr.bhp,rawat_inap_drpr.menejemen "+
                   "from pasien inner join reg_periksa inner join jns_perawatan_inap inner join "+
                   "dokter inner join rawat_inap_drpr inner join petugas on rawat_inap_drpr.no_rawat=reg_periksa.no_rawat "+
                   "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
                   "and rawat_inap_drpr.kd_dokter=dokter.kd_dokter and rawat_inap_drpr.nip=petugas.nip "+
                   "where rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and rawat_inap_drpr.no_rawat like ? or "+
                    "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "+
                    "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "+
                    "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and jns_perawatan_inap.nm_perawatan like ? or "+
                    "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and rawat_inap_drpr.kd_dokter like ? or "+
                    "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and dokter.nm_dokter like ? or "+
                    "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and rawat_inap_drpr.nip like ? or "+
                    "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and petugas.nama like ? or "+
                    "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and tgl_perawatan like ?  "+
                   " order by rawat_inap_drpr.no_rawat desc");
            }
                
            try{
                if(TCari.getText().equals("")&&TCariPasien.getText().equals("")){
                    ps3.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps3.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                }else{
                    ps3.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps3.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps3.setString(3,"%"+TCariPasien.getText()+"%");
                    ps3.setString(4,"%"+TCari.getText().trim()+"%");
                    ps3.setString(5,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps3.setString(6,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps3.setString(7,"%"+TCariPasien.getText()+"%");
                    ps3.setString(8,"%"+TCari.getText().trim()+"%");
                    ps3.setString(9,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps3.setString(10,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps3.setString(11,"%"+TCariPasien.getText()+"%");
                    ps3.setString(12,"%"+TCari.getText().trim()+"%");
                    ps3.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps3.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps3.setString(15,"%"+TCariPasien.getText()+"%");
                    ps3.setString(16,"%"+TCari.getText().trim()+"%");
                    ps3.setString(17,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps3.setString(18,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps3.setString(19,"%"+TCariPasien.getText()+"%");
                    ps3.setString(20,"%"+TCari.getText().trim()+"%");
                    ps3.setString(21,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps3.setString(22,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps3.setString(23,"%"+TCariPasien.getText()+"%");
                    ps3.setString(24,"%"+TCari.getText().trim()+"%");
                    ps3.setString(25,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps3.setString(26,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps3.setString(27,"%"+TCariPasien.getText()+"%");
                    ps3.setString(28,"%"+TCari.getText().trim()+"%");
                    ps3.setString(29,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps3.setString(30,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps3.setString(31,"%"+TCariPasien.getText()+"%");
                    ps3.setString(32,"%"+TCari.getText().trim()+"%");
                    ps3.setString(33,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps3.setString(34,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps3.setString(35,"%"+TCariPasien.getText()+"%");
                    ps3.setString(36,"%"+TCari.getText().trim()+"%");
                }
                    
                rs=ps3.executeQuery();
                while(rs.next()){
                    tabModeDrPr.addRow(new Object[]{
                        false,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
                        rs.getString(8),rs.getString(9),rs.getString(10),rs.getDouble(11),rs.getString("kd_jenis_prw"),
                        rs.getString("tarif_tindakandr"),rs.getString("tarif_tindakanpr"),rs.getString("kso"),
                        rs.getString("material"),rs.getString("bhp"),rs.getString("menejemen")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps3!=null){
                    ps3.close();
                }
            }              
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabModeDrPr.getRowCount());
    }
    
    private void getDataDrPr() {
        if(tbRawatDrPr.getSelectedRow()!= -1){
            TNoRw.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),1).toString());
            TNoRM.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),2).toString());
            TPasien.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),3).toString());
            KdDok2.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),5).toString());
            TDokter2.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),6).toString());
            kdptg2.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),7).toString());
            TPerawat2.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),8).toString());
            cmbJam.setSelectedItem(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),10).toString().substring(0,2));
            cmbMnt.setSelectedItem(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),10).toString().substring(3,5));
            cmbDtk.setSelectedItem(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),10).toString().substring(6,8));
            TNmPrw2.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),4).toString());
            TKdPrw2.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),12).toString());
            Valid.SetTgl(DTPTgl,tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(),9).toString());
            isJns();
        }
    }

    private void isRawat(){
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ",TNoRM,TNoRw.getText());
    }

    private void isPsien(){
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ",TPasien,TNoRM.getText());
    }

    private void isJns(){
        if(TabRawat.getSelectedIndex()==0){
            if(!TKdPrw.getText().equals("")){
                Sequel.cariIsi("select nm_perawatan from jns_perawatan_inap where kd_jenis_prw=? ",TNmPrw,TKdPrw.getText());
                Sequel.cariIsi("select bhp from jns_perawatan_inap where kd_jenis_prw=? ",Bhp,TKdPrw.getText());
                Sequel.cariIsi("select material from jns_perawatan_inap where kd_jenis_prw=? ",BagianRS,TKdPrw.getText());
                Sequel.cariIsi("select tarif_tindakandr from jns_perawatan_inap where kd_jenis_prw=? ",JmDokter,TKdPrw.getText());
                Sequel.cariIsi("select tarif_tindakanpr from jns_perawatan_inap where kd_jenis_prw=? ",JmPerawat,TKdPrw.getText());
                Sequel.cariIsi("select kso from jns_perawatan_inap where kd_jenis_prw=? ",KSO,TKdPrw.getText());
                Sequel.cariIsi("select menejemen from jns_perawatan_inap where kd_jenis_prw=? ",Menejemen,TKdPrw.getText());
                Sequel.cariIsi("select total_byrdr from jns_perawatan_inap where kd_jenis_prw=? ",TTnd,TKdPrw.getText());
            }
        }else if(TabRawat.getSelectedIndex()==1){
            if(!TKdPrw1.getText().equals("")){
                Sequel.cariIsi("select nm_perawatan from jns_perawatan_inap where kd_jenis_prw=? ",TNmPrw1,TKdPrw1.getText());
                Sequel.cariIsi("select bhp from jns_perawatan_inap where kd_jenis_prw=? ",Bhp,TKdPrw1.getText());
                Sequel.cariIsi("select material from jns_perawatan_inap where kd_jenis_prw=? ",BagianRS,TKdPrw1.getText());
                Sequel.cariIsi("select tarif_tindakandr from jns_perawatan_inap where kd_jenis_prw=? ",JmDokter,TKdPrw1.getText());
                Sequel.cariIsi("select tarif_tindakanpr from jns_perawatan_inap where kd_jenis_prw=? ",JmPerawat,TKdPrw1.getText());
                Sequel.cariIsi("select kso from jns_perawatan_inap where kd_jenis_prw=? ",KSO,TKdPrw1.getText());
                Sequel.cariIsi("select menejemen from jns_perawatan_inap where kd_jenis_prw=? ",Menejemen,TKdPrw1.getText());
                Sequel.cariIsi("select total_byrpr from jns_perawatan_inap where kd_jenis_prw=? ",TTnd,TKdPrw1.getText());
            }
        }else if(TabRawat.getSelectedIndex()==2){
            if(!TKdPrw2.getText().equals("")){
                Sequel.cariIsi("select nm_perawatan from jns_perawatan_inap where kd_jenis_prw=? ",TNmPrw2,TKdPrw2.getText());
                Sequel.cariIsi("select bhp from jns_perawatan_inap where kd_jenis_prw=? ",Bhp,TKdPrw2.getText());
                Sequel.cariIsi("select material from jns_perawatan_inap where kd_jenis_prw=? ",BagianRS,TKdPrw2.getText());
                Sequel.cariIsi("select tarif_tindakandr from jns_perawatan_inap where kd_jenis_prw=? ",JmDokter,TKdPrw2.getText());
                Sequel.cariIsi("select tarif_tindakanpr from jns_perawatan_inap where kd_jenis_prw=? ",JmPerawat,TKdPrw2.getText());
                Sequel.cariIsi("select kso from jns_perawatan_inap where kd_jenis_prw=? ",KSO,TKdPrw2.getText());
                Sequel.cariIsi("select menejemen from jns_perawatan_inap where kd_jenis_prw=? ",Menejemen,TKdPrw2.getText());
                Sequel.cariIsi("select total_byrdrpr from jns_perawatan_inap where kd_jenis_prw=? ",TTnd,TKdPrw2.getText());
            }
        }
    }    
    
    public void setNoRm(String norwt,Date awal,Date akhir) {
        TNoRw.setText(norwt);
        KdDok.setText(Sequel.cariIsi("select kd_dokter from dpjp_ranap where no_rawat=?",norwt));
        if(KdDok.getText().equals("")){
            KdDok.setText(Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?",norwt));
        }
        Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?",TDokter,KdDok.getText());
        
        isRawat();
        isPsien();
        DTPCari1.setDate(awal);
        DTPCari2.setDate(akhir);
        TCari.setText(norwt);
        ChkInput.setSelected(true);
        isForm();
        TabRawatMouseClicked(null);
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH,186));
            panelGlass12.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput1.setPreferredSize(new Dimension(WIDTH,20));
            panelGlass12.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(var.getmanajemen());
        BtnHapus.setEnabled(var.getmanajemen());
        BtnEdit.setEnabled(var.getmanajemen());
        BtnDiagnosa.setVisible(var.getmanajemen());         
        BtnRiwayat.setVisible(var.getmanajemen());    
        BtnResepObat.setVisible(var.getmanajemen());
        BtnCopyResep.setVisible(var.getmanajemen());
        //BtnPermintaanStok.setVisible(var.getmanajemen());
        BtnObatBhp.setVisible(var.getmanajemen());  
        BtnInputObat.setVisible(var.getmanajemen());   
        BtnPermintaanLab.setVisible(var.getmanajemen());     
        BtnBerkasDigital.setVisible(var.getmanajemen());    
        BtnPermintaanRad.setVisible(var.getmanajemen());  
        BtnRujukKeluar.setVisible(var.getmanajemen());
        BtnSKDP.setVisible(var.getmanajemen());     
        BtnCatatan.setVisible(var.getmanajemen());  
        //BtnAsuhanGizi.setVisible(var.getmanajemen());
        BtnResume.setVisible(var.getmanajemen());
        //BtnMonitoringAsuhanGizi.setVisible(var.getmanajemen());
    }
    
    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkJln.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkJln.isSelected()==false){
                    nilai_jam =cmbJam.getSelectedIndex();
                    nilai_menit =cmbMnt.getSelectedIndex();
                    nilai_detik =cmbDtk.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                cmbJam.setSelectedItem(jam);
                cmbMnt.setSelectedItem(menit);
                cmbDtk.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void tampilPemeriksaan() {
        Valid.tabelKosong(tabModePemeriksaan);
        try{  
            if(TCari.getText().equals("")&&TCariPasien.getText().equals("")){
                ps4=koneksi.prepareStatement("select pemeriksaan_ranap.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                    "pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat,pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi, " +
                    "pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi,pemeriksaan_ranap.tinggi, " +
                    "pemeriksaan_ranap.berat,pemeriksaan_ranap.gcs,pemeriksaan_ranap.kesadaran,pemeriksaan_ranap.keluhan, " +
                    "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi,pemeriksaan_ranap.penilaian,pemeriksaan_ranap.rtl from pasien inner join reg_periksa inner join pemeriksaan_ranap "+
                    "on pemeriksaan_ranap.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where  "+
                    "pemeriksaan_ranap.tgl_perawatan between ? and ? order by pemeriksaan_ranap.no_rawat desc"); 
            }else{
                ps4=koneksi.prepareStatement("select pemeriksaan_ranap.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                    "pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat,pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi, " +
                    "pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi,pemeriksaan_ranap.tinggi, " +
                    "pemeriksaan_ranap.berat,pemeriksaan_ranap.gcs,pemeriksaan_ranap.kesadaran,pemeriksaan_ranap.keluhan, " +
                    "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi,pemeriksaan_ranap.penilaian,pemeriksaan_ranap.rtl from pasien inner join reg_periksa inner join pemeriksaan_ranap "+
                    "on pemeriksaan_ranap.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where  "+
                    "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ranap.no_rawat like ? or "+
                    "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "+
                    "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or  "+
                    "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ranap.alergi like ? or "+
                    "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ranap.keluhan like ? or "+
                    "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ranap.penilaian like ? or "+
                    "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ranap.rtl like ? or "+
                    "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ranap.pemeriksaan like ? "+
                   "order by pemeriksaan_ranap.no_rawat desc"); 
            }
                
            try{
                if(TCari.getText().equals("")&&TCariPasien.getText().equals("")){
                    ps4.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps4.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                }else{
                    ps4.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps4.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps4.setString(3,"%"+TCariPasien.getText()+"%");
                    ps4.setString(4,"%"+TCari.getText().trim()+"%");
                    ps4.setString(5,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps4.setString(6,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps4.setString(7,"%"+TCariPasien.getText()+"%");
                    ps4.setString(8,"%"+TCari.getText().trim()+"%");
                    ps4.setString(9,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps4.setString(10,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps4.setString(11,"%"+TCariPasien.getText()+"%");
                    ps4.setString(12,"%"+TCari.getText().trim()+"%");
                    ps4.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps4.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps4.setString(15,"%"+TCariPasien.getText()+"%");
                    ps4.setString(16,"%"+TCari.getText().trim()+"%");
                    ps4.setString(17,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps4.setString(18,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps4.setString(19,"%"+TCariPasien.getText()+"%");
                    ps4.setString(20,"%"+TCari.getText().trim()+"%");
                    ps4.setString(21,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps4.setString(22,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps4.setString(23,"%"+TCariPasien.getText()+"%");
                    ps4.setString(24,"%"+TCari.getText().trim()+"%");
                    ps4.setString(25,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps4.setString(26,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps4.setString(27,"%"+TCariPasien.getText()+"%");
                    ps4.setString(28,"%"+TCari.getText().trim()+"%");
                    ps4.setString(29,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps4.setString(30,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps4.setString(31,"%"+TCariPasien.getText()+"%");
                    ps4.setString(32,"%"+TCari.getText().trim()+"%");
                }
                    
                rs=ps4.executeQuery();
                while(rs.next()){
                    tabModePemeriksaan.addRow(new Object[]{
                        false,rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
                        rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),
                        rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),
                        rs.getString(16),rs.getString(17),rs.getString(18)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps4!=null){
                    ps4.close();
                }
            }                  
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabModePemeriksaan.getRowCount());
    }

    private void getDataPemeriksaan() {
        if(tbPemeriksaan.getSelectedRow()!= -1){
            TNoRw.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),1).toString());
            TNoRM.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),2).toString());
            TPasien.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),3).toString());             
            TSuhu.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),6).toString()); 
            TTensi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),7).toString()); 
            TNadi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),8).toString()); 
            TRespirasi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),9).toString()); 
            TTinggi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),10).toString()); 
            TBerat.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),11).toString());  
            TGCS.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),12).toString()); 
            cmbKesadaran.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),13).toString()); 
            TKeluhan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),14).toString()); 
            TPemeriksaan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),15).toString()); 
            TAlergi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),16).toString()); 
            TPenilaian.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),17).toString()); 
            TindakLanjut.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),18).toString()); 
            cmbJam.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),5).toString().substring(0,2));
            cmbMnt.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),5).toString().substring(3,5));
            cmbDtk.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),5).toString().substring(6,8));
            Valid.SetTgl(DTPTgl,tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),4).toString());
        }
    }
    
    private void isMenu(){
        if(ChkAccor.isSelected()==true){
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(135,HEIGHT));
            FormMenu.setVisible(true); 
            ChkAccor.setVisible(true);
        }else if(ChkAccor.isSelected()==false){  
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15,HEIGHT));
            FormMenu.setVisible(false);    
            ChkAccor.setVisible(true);
        }
    }
    
    
}
