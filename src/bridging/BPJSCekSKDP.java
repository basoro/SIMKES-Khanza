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
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import keuangan.DlgKamar;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgCariBahasa;
import simrskhanza.DlgCariCacatFisik;
import simrskhanza.DlgCariCaraBayar;
import simrskhanza.DlgKabupaten;
import simrskhanza.DlgKecamatan;
import simrskhanza.DlgKelurahan;
import simrskhanza.DlgCariPerusahaan;
import simrskhanza.DlgPilihanCetakDokumen;
import simrskhanza.DlgPropinsi;
import simrskhanza.DlgCariSuku;

/**
 *
 * @author dosen
 */
public final class BPJSCekSKDP extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private final Properties prop = new Properties();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private BPJSCekReferensiFaskes faskes=new BPJSCekReferensiFaskes(null,false);
    private BPJSCekReferensiPenyakit penyakit=new BPJSCekReferensiPenyakit(null,false);
    private BPJSCekMappingPoli poli=new BPJSCekMappingPoli(null,false);
    private DlgKabupaten kab=new DlgKabupaten(null,false);
    private DlgKecamatan kec=new DlgKecamatan(null,false);
    private DlgKelurahan kel=new DlgKelurahan(null,false);
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private DlgKamar kamar=new DlgKamar(null,false);
    private DlgCariCaraBayar penjab=new DlgCariCaraBayar(null,false);
    public  DlgPropinsi propin=new DlgPropinsi(null,false);
    public  DlgCariPerusahaan perusahaan=new DlgCariPerusahaan(null,false);
    public  DlgCariBahasa bahasa=new DlgCariBahasa(null,false);
    public  DlgCariCacatFisik cacat=new DlgCariCacatFisik(null,false);
    public  DlgCariSuku suku=new DlgCariSuku(null,false);
    private BPJSCekNoKartu cekViaBPJSKartu=new BPJSCekNoKartu();
    private BPJSCekReferensiDokterDPJP dpjp=new BPJSCekReferensiDokterDPJP(null,false);
    private BPJSSuratKontrol skdp=new BPJSSuratKontrol(null,false);
    private BPJSCekReferensiPropinsi propinsikll=new BPJSCekReferensiPropinsi(null,false);
    private BPJSCekReferensiKabupaten kabupatenkll=new BPJSCekReferensiKabupaten(null,false);
    private BPJSCekReferensiKecamatan kecamatankll=new BPJSCekReferensiKecamatan(null,false);
    private DlgPilihanCetakDokumen pilihan=new DlgPilihanCetakDokumen(null,false);
    private ApiBPJS api=new ApiBPJS();
    private int pilih=0,p_no_ktp=0,p_tmp_lahir=0,p_nm_ibu=0,p_alamat=0,
            p_pekerjaan=0,p_no_tlp=0,p_umur=0,p_namakeluarga=0,p_no_peserta=0,
            p_kelurahan=0,p_kecamatan=0,p_kabupaten=0,p_pekerjaanpj=0,
            p_alamatpj=0,p_kelurahanpj=0,p_kecamatanpj=0,p_kabupatenpj=0,jmlhari=0,
            p_propinsi=0,p_propinsipj=0;
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    private String kdkel="",kdkec="",kdkab="",kdprop="",nosisrute="",BASENOREG="",URUTNOREG="",klg="SAUDARA",pengurutan="",tahun="",bulan="",posisitahun="",awalantahun="",awalanbulan="",
            no_ktp="",tmp_lahir="",nm_ibu="",alamat="",pekerjaan="",no_tlp="",tglkkl="0000-00-00",
            umur="",umurdaftar="0",namakeluarga="",no_peserta="",kelurahan="",kecamatan="",sttsumur="",norawat="",
            kabupaten="",pekerjaanpj="",alamatpj="",kelurahanpj="",kecamatanpj="",prb="",peserta="",
            kabupatenpj="",hariawal="",requestJson,URL="",nosep="",user="",link="",
            status="Baru",propinsi="",propinsipj="",hari="",utc="";
    private PreparedStatement ps,pskelengkapan,pscariumur,pssetalamat;
    private ResultSet rs,rs2;
    private double biaya=0;
    private HttpHeaders headers;
    private HttpHeaders headers2;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private Calendar cal = Calendar.getInstance();
    private int day = cal.get(Calendar.DAY_OF_WEEK);
    

    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public BPJSCekSKDP(java.awt.Frame parent, boolean modal) {
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
        NoSKDP = new widget.TextBox();
        btnSKDP = new widget.Button();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pencarian Peserta BPJS Berdasarkan Nomor SKDP ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        PanelInput.setPreferredSize(new java.awt.Dimension(200, 917));
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

        jLabel16.setText("No.SKDP :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass6.add(jLabel16);

        NoSKDP.setHighlighter(null);
        NoSKDP.setName("NoSKDP"); // NOI18N
        NoSKDP.setPreferredSize(new java.awt.Dimension(210, 23));
        NoSKDP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSKDPKeyPressed(evt);
            }
        });
        panelGlass6.add(NoSKDP);

        btnSKDP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/190.png"))); // NOI18N
        btnSKDP.setMnemonic('X');
        btnSKDP.setToolTipText("Alt+X");
        btnSKDP.setName("btnSKDP"); // NOI18N
        btnSKDP.setPreferredSize(new java.awt.Dimension(28, 23));
        btnSKDP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSKDPActionPerformed(evt);
            }
        });
        btnSKDP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSKDPKeyPressed(evt);
            }
        });
        panelGlass6.add(btnSKDP);

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

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
    }//GEN-LAST:event_BtnCariKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    }//GEN-LAST:event_formWindowOpened

    private void NoSKDPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSKDPKeyPressed
    }//GEN-LAST:event_NoSKDPKeyPressed

    private void btnSKDPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSKDPActionPerformed
        skdp.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        skdp.setLocationRelativeTo(internalFrame1);
        skdp.setVisible(true);
    }//GEN-LAST:event_btnSKDPActionPerformed

    private void btnSKDPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSKDPKeyPressed
    }//GEN-LAST:event_btnSKDPKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSCekSKDP dialog = new BPJSCekSKDP(new javax.swing.JFrame(), true);
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
    private widget.TextBox NoSKDP;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.Button btnSKDP;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass6;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbKamar;
    // End of variables declaration//GEN-END:variables

    public void tampil(String noskdp) {
        try {
            peserta="";
            if(cekViaBPJSKartu.informasi.equals("OK")){
                Valid.tabelKosong(tabMode);             
                tabMode.addRow(new Object[]{
                    "Nama",": "+cekViaBPJSKartu.nama
                });
                tabMode.addRow(new Object[]{
                    "NIK",": "+cekViaBPJSKartu.nik
                });
                tabMode.addRow(new Object[]{
                    "Nomor Kartu",": "+cekViaBPJSKartu.noKartu
                });
                tabMode.addRow(new Object[]{
                    "Nomor MR",": "+cekViaBPJSKartu.mrnoMR
                });
                tabMode.addRow(new Object[]{
                    "Nomor Telp",": "+cekViaBPJSKartu.mrnoTelepon
                });
                tabMode.addRow(new Object[]{
                    "Pisa",": "+cekViaBPJSKartu.pisa
                });
                tabMode.addRow(new Object[]{
                    "Jenis Kelamin",": "+cekViaBPJSKartu.sex.replaceAll("L","Laki-Laki").replaceAll("P","Perempuan")
                });
                tabMode.addRow(new Object[]{
                    "Status Peserta",":"
                });
                tabMode.addRow(new Object[]{
                    "       Keterangan",": "+cekViaBPJSKartu.statusPesertaketerangan
                });
                tabMode.addRow(new Object[]{
                    "       Kode",": "+cekViaBPJSKartu.statusPesertakode
                });
                tabMode.addRow(new Object[]{
                    "Jenis Peserta",":"
                });
                tabMode.addRow(new Object[]{
                    "       Kode Jenis Peserta",": "+cekViaBPJSKartu.jenisPesertakode
                });
                tabMode.addRow(new Object[]{
                    "       Nama Jenis Peserta",": "+cekViaBPJSKartu.jenisPesertaketerangan
                });            
                peserta=cekViaBPJSKartu.jenisPesertaketerangan;
                tabMode.addRow(new Object[]{
                    "Kelas Tanggungan",":"
                });
                tabMode.addRow(new Object[]{
                    "       Kode Kelas",": "+cekViaBPJSKartu.hakKelaskode
                });
                tabMode.addRow(new Object[]{
                    "       Nama Kelas",": "+cekViaBPJSKartu.hakKelasketerangan
                });
                tabMode.addRow(new Object[]{
                    "Provider Umum",":"
                });
                tabMode.addRow(new Object[]{
                    "       Kode Provider",": "+cekViaBPJSKartu.provUmumkdProvider
                });                
                tabMode.addRow(new Object[]{
                    "       Nama Provider",": "+cekViaBPJSKartu.provUmumnmProvider
                });
                tabMode.addRow(new Object[]{
                    "Tanggal Cetak Kartu",": "+cekViaBPJSKartu.tglCetakKartu
                });
                tabMode.addRow(new Object[]{
                    "Tanggal Lahir",": "+cekViaBPJSKartu.tglLahir
                });
                tabMode.addRow(new Object[]{
                    "Tanggal TAT",": "+cekViaBPJSKartu.tglTAT
                });
                tabMode.addRow(new Object[]{
                    "Tanggal TMT",": "+cekViaBPJSKartu.tglTMT
                });
                tabMode.addRow(new Object[]{
                    "Umur",":"
                });
                tabMode.addRow(new Object[]{
                    "       Umur Saat Pelayanan",": "+cekViaBPJSKartu.umurumurSaatPelayanan
                });
                tabMode.addRow(new Object[]{
                    "       Umur Sekarang",": "+cekViaBPJSKartu.umurumurSekarang.replaceAll("tahun","Th ").replaceAll("bulan","Bl ").replaceAll("hari","Hr")
                });
                tabMode.addRow(new Object[]{
                    "Informasi",":"
                });
                tabMode.addRow(new Object[]{
                    "       Dinsos",": "+cekViaBPJSKartu.informasidinsos
                });
                tabMode.addRow(new Object[]{
                    "       No.SKTM",": "+cekViaBPJSKartu.informasinoSKTM
                });
                tabMode.addRow(new Object[]{
                    "       Prolanis PRB",": "+cekViaBPJSKartu.informasiprolanisPRB
                });
                tabMode.addRow(new Object[]{
                    "COB",":"
                });
                tabMode.addRow(new Object[]{
                    "       Nama Asuransi",": "+cekViaBPJSKartu.cobnmAsuransi
                });
                tabMode.addRow(new Object[]{
                    "       No Asuransi",": "+cekViaBPJSKartu.cobnoAsuransi
                });
                tabMode.addRow(new Object[]{
                    "       Tanggal TAT",": "+cekViaBPJSKartu.cobtglTAT
                });
                tabMode.addRow(new Object[]{
                    "       Tanggal TMT",": "+cekViaBPJSKartu.cobtglTMT
                });
                prb=cekViaBPJSKartu.informasiprolanisPRB.replaceAll("null","");

            }else {
                emptTeks();
                isForm();
                JOptionPane.showMessageDialog(null,cekViaBPJSKartu.informasi);                
            }   
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : "+ex);
        }
    }  
    
    private void isForm(){
    }
    
    public void emptTeks() {
    }
    
    private void isNumber(){    
    }
    
    private void isPoli(){
    }
    
    private void insertSEP(){
    }
    
    private void inputRegistrasi(){
    }

    private void insertPasien() {
    }
    
    private void UpdateUmur(){
    }
    
    public void isCek(){
    }
    
    
}
