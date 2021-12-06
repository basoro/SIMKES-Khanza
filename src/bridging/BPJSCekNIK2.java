/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgKamar.java
 *
 * Created on May 23, 2010, 12:07:21 AM
 */

package bridging;

import fungsi.WarnaTable;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import keuangan.DlgKamar;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgPasien;
import simrskhanza.DlgPilihanCetakDokumen;

/**
 *
 * @author dosen
 */
public final class BPJSCekNIK2 extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private DlgPasien pasien=new DlgPasien(null,false);
    private BPJSCekReferensiFaskes faskes=new BPJSCekReferensiFaskes(null,false);
    private BPJSCekReferensiPenyakit penyakit=new BPJSCekReferensiPenyakit(null,false);
    private BPJSCekMappingPoli poli=new BPJSCekMappingPoli(null,false);
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private DlgKamar kamar=new DlgKamar(null,false);
    private DlgPilihanCetakDokumen pilihan=new DlgPilihanCetakDokumen(null,false);
    private BPJSCekReferensiDokterDPJP dpjp=new BPJSCekReferensiDokterDPJP(null,false);
    private BPJSSuratKontrol skdp=new BPJSSuratKontrol(null,false);
    private BPJSSPRI skdp2=new BPJSSPRI(null,false);
    private BPJSCekReferensiPropinsi propinsikll=new BPJSCekReferensiPropinsi(null,false);
    private BPJSCekReferensiKabupaten kabupatenkll=new BPJSCekReferensiKabupaten(null,false);
    private BPJSCekReferensiKecamatan kecamatankll=new BPJSCekReferensiKecamatan(null,false);
    private BPJSCekHistoriPelayanan historiPelayanan=new BPJSCekHistoriPelayanan(null,false);
    private ApiBPJS api=new ApiBPJS();
    private int pilih=0,p_no_ktp=0,p_tmp_lahir=0,p_nm_ibu=0,p_alamat=0,
            p_pekerjaan=0,p_no_tlp=0,p_umur=0,p_namakeluarga=0,p_no_peserta=0,
            p_kelurahan=0,p_kecamatan=0,p_kabupaten=0,p_pekerjaanpj=0,
            p_alamatpj=0,p_kelurahanpj=0,p_kecamatanpj=0,p_kabupatenpj=0,jmlhari=0,
            p_propinsi=0,p_propinsipj=0;
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    private String kdkel="",kdkec="",kdkab="",kdprop="",nosisrute="",BASENOREG="",URUTNOREG="",link="",klg="SAUDARA",statuspasien="",pengurutan="",tahun="",bulan="",posisitahun="",awalantahun="",awalanbulan="",
            no_ktp="",tmp_lahir="",nm_ibu="",alamat="",pekerjaan="",no_tlp="",tglkkl="0000-00-00",
            umur="",umurdaftar="0",namakeluarga="",no_peserta="",kelurahan="",kecamatan="",sttsumur="",
            kabupaten="",pekerjaanpj="",alamatpj="",kelurahanpj="",kecamatanpj="",
            kabupatenpj="",hariawal="",requestJson,URL="",nosep="",user="",prb="",peserta="",
            status="Baru",propinsi="",propinsipj="",utc="";
    private PreparedStatement ps,pskelengkapan,pscariumur,pssetalamat;
    private ResultSet rs,rs2;
    private double biaya=0;
    private BPJSCekNIK cekViaBPJS=new BPJSCekNIK();
    private boolean empt=false;
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    

    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public BPJSCekNIK2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10,2);
        setSize(628,674);

        Object[] row={"",""};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbKamar.setModel(tabMode);

        //tbKamar.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbKamar.getBackground()));
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(170);
            }else if(i==1){
                column.setPreferredWidth(470);
            }
        }
        
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());
        
        
        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(pasien.getTable().getSelectedRow()!= -1){                   
                    if(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),3).toString().equals("")){
                        JOptionPane.showMessageDialog(rootPane,"Maaf pasien tidak punya NIK...!");
                    }else{
                        TNik.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),3).toString());
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
        
        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    pasien.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });       
    
    }
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKamar = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        panelCari = new widget.panelisi();
        scrollPane2 = new widget.ScrollPane();
        panelGlass6 = new widget.panelisi();
        jLabel16 = new widget.Label();
        TNik = new widget.TextBox();
        btnPasien = new widget.Button();
        BtnCari = new widget.Button();
        jLabel17 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pencarian Peserta BPJS Berdasarkan NIK ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKamar.setAutoCreateRowSorter(true);
        tbKamar.setToolTipText("");
        tbKamar.setName("tbKamar"); // NOI18N
        Scroll.setViewportView(tbKamar);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(200, 907));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        panelCari.setName("panelCari"); // NOI18N
        panelCari.setPreferredSize(new java.awt.Dimension(44, 44));
        panelCari.setLayout(new java.awt.BorderLayout());

        scrollPane2.setBorder(null);
        scrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setPreferredSize(new java.awt.Dimension(1093, 138));
        panelCari.add(scrollPane2, java.awt.BorderLayout.CENTER);

        PanelInput.add(panelCari, java.awt.BorderLayout.CENTER);

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel16.setText("NIK Pasien :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass6.add(jLabel16);

        TNik.setName("TNik"); // NOI18N
        TNik.setPreferredSize(new java.awt.Dimension(250, 23));
        TNik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNikKeyPressed(evt);
            }
        });
        panelGlass6.add(TNik);

        btnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/190.png"))); // NOI18N
        btnPasien.setMnemonic('5');
        btnPasien.setToolTipText("Alt+5");
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
        panelGlass6.add(btnPasien);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setToolTipText("Alt+6");
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
        panelGlass6.add(BtnCari);

        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(jLabel17);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        panelGlass6.add(BtnPrint);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
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
        panelGlass6.add(BtnKeluar);

        PanelInput.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,BtnKeluar);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            Sequel.queryu("truncate table temporary");
            int row=tabMode.getRowCount();
            for(int r=0;r<row;r++){  
                Sequel.menyimpan("temporary","'0','"+
                                tabMode.getValueAt(r,0).toString()+"','"+
                                tabMode.getValueAt(r,1).toString()+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''","Rekap Harian Pengadaan Ipsrs"); 
            }
            
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());   
            param.put("logo",Sequel.cariGambar("select logo from setting")); 
//            Valid.MyReport("rptCariBPJSNoPeserta.jasper","report","[ Pencarian Peserta BPJS Berdasarkan Nomor Kepesertaan ]",param);
            this.setCursor(Cursor.getDefaultCursor());
        }        
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void TNikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNikKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TNikKeyPressed

    private void btnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienActionPerformed
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
    }//GEN-LAST:event_btnPasienActionPerformed

    private void btnPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPasienKeyPressed
        Valid.pindah(evt,TNik,BtnPrint);
    }//GEN-LAST:event_btnPasienKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt,TNik,BtnPrint);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    }//GEN-LAST:event_formWindowOpened

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSCekNIK2 dialog = new BPJSCekNIK2(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox TNik;
    private widget.Button btnPasien;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass6;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbKamar;
    // End of variables declaration//GEN-END:variables

    public void tampil(String nomorpeserta) {
        try {
            cekViaBPJS.tampil(nomorpeserta);
            if(cekViaBPJS.informasi.equals("OK")){
                Valid.tabelKosong(tabMode);
                Valid.tabelKosong(tabMode);             
                tabMode.addRow(new Object[]{
                    "Nama",": "+cekViaBPJS.nama
                });
                tabMode.addRow(new Object[]{
                    "NIK",": "+TNik.getText()
                });
                tabMode.addRow(new Object[]{
                    "Nomor Kartu",": "+cekViaBPJS.noKartu
                });
                tabMode.addRow(new Object[]{
                    "Nomor MR",": "+cekViaBPJS.mrnoMR
                });
                tabMode.addRow(new Object[]{
                    "Nomor Telp",": "+cekViaBPJS.mrnoTelepon
                });
                tabMode.addRow(new Object[]{
                    "Pisa",": "+cekViaBPJS.pisa
                });
                tabMode.addRow(new Object[]{
                    "Jenis Kelamin",": "+cekViaBPJS.sex.replaceAll("L","Laki-Laki").replaceAll("P","Perempuan")
                });
                tabMode.addRow(new Object[]{
                    "Status Peserta",":"
                });
                tabMode.addRow(new Object[]{
                    "       Keterangan",": "+cekViaBPJS.statusPesertaketerangan
                });
                tabMode.addRow(new Object[]{
                    "       Kode",": "+cekViaBPJS.statusPesertakode
                });
                tabMode.addRow(new Object[]{
                    "Jenis Peserta",":"
                });
                tabMode.addRow(new Object[]{
                    "       Kode Jenis Peserta",": "+cekViaBPJS.jenisPesertakode
                });
                tabMode.addRow(new Object[]{
                    "       Nama Jenis Peserta",": "+cekViaBPJS.jenisPesertaketerangan
                });            
                peserta=cekViaBPJS.jenisPesertaketerangan;
                tabMode.addRow(new Object[]{
                    "Kelas Tanggungan",":"
                });
                tabMode.addRow(new Object[]{
                    "       Kode Kelas",": "+cekViaBPJS.hakKelaskode
                });
                tabMode.addRow(new Object[]{
                    "       Nama Kelas",": "+cekViaBPJS.hakKelasketerangan
                });
                tabMode.addRow(new Object[]{
                    "Provider Umum",":"
                });
                tabMode.addRow(new Object[]{
                    "       Kode Provider",": "+cekViaBPJS.provUmumkdProvider
                });                
                tabMode.addRow(new Object[]{
                    "       Nama Provider",": "+cekViaBPJS.provUmumnmProvider
                });
                tabMode.addRow(new Object[]{
                    "Tanggal Cetak Kartu",": "+cekViaBPJS.tglCetakKartu
                });
                tabMode.addRow(new Object[]{
                    "Tanggal Lahir",": "+cekViaBPJS.tglLahir
                });
                tabMode.addRow(new Object[]{
                    "Tanggal TAT",": "+cekViaBPJS.tglTAT
                });
                tabMode.addRow(new Object[]{
                    "Tanggal TMT",": "+cekViaBPJS.tglTMT
                });
                tabMode.addRow(new Object[]{
                    "Umur",":"
                });
                tabMode.addRow(new Object[]{
                    "       Umur Saat Pelayanan",": "+cekViaBPJS.umurumurSaatPelayanan
                });
                tabMode.addRow(new Object[]{
                    "       Umur Sekarang",": "+cekViaBPJS.umurumurSekarang.replaceAll("tahun","Th ").replaceAll("bulan","Bl ").replaceAll("hari","Hr")
                });
                tabMode.addRow(new Object[]{
                    "Informasi",":"
                });
                tabMode.addRow(new Object[]{
                    "       Dinsos",": "+cekViaBPJS.informasidinsos
                });
                tabMode.addRow(new Object[]{
                    "       No.SKTM",": "+cekViaBPJS.informasinoSKTM
                });
                tabMode.addRow(new Object[]{
                    "       Prolanis PRB",": Potensi PRB"/*cekViaBPJS.informasiprolanisPRB*;*/
                });
                tabMode.addRow(new Object[]{
                    "COB",":"
                });
                tabMode.addRow(new Object[]{
                    "       Nama Asuransi",": "+cekViaBPJS.cobnmAsuransi
                });
                tabMode.addRow(new Object[]{
                    "       No Asuransi",": "+cekViaBPJS.cobnoAsuransi
                });
                tabMode.addRow(new Object[]{
                    "       Tanggal TAT",": "+cekViaBPJS.cobtglTAT
                });
                tabMode.addRow(new Object[]{
                    "       Tanggal TMT",": "+cekViaBPJS.cobtglTMT
                });
                prb=cekViaBPJS.informasiprolanisPRB.replaceAll("null","");

            }else {
                emptTeks();
                isForm();               
            }   
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : "+ex);            
        }
    }  
    
    private void isForm(){
    }
    
    public void emptTeks() {

    }
    
    public void isCek(){
    }
    
    public void SetNoKTP(String NoKTP){
        emptTeks();
        TNik.setText(NoKTP);
        tampil(NoKTP);
        empt=true;
    }
    
    public void SetNoRujuk(String norujuk){
        this.nosisrute=norujuk;
    }
}
