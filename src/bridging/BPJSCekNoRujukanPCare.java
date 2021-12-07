/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */

package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
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
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
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
public final class BPJSCekNoRujukanPCare extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private validasi Valid=new validasi();
    private sekuel Sequel=new sekuel();
    private int i=0;
    private Connection koneksi=koneksiDB.condb();
    private BPJSCekReferensiFaskes faskes=new BPJSCekReferensiFaskes(null,false);
    private BPJSCekReferensiPenyakit penyakit=new BPJSCekReferensiPenyakit(null,false);
    private BPJSCekMappingPoli poli=new BPJSCekMappingPoli(null,false);
    private DlgKabupaten kab=new DlgKabupaten(null,false);
    private DlgKecamatan kec=new DlgKecamatan(null,false);
    private DlgKelurahan kel=new DlgKelurahan(null,false);
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private DlgPilihanCetakDokumen pilihan=new DlgPilihanCetakDokumen(null,false);
    private DlgKamar kamar=new DlgKamar(null,false);
    private DlgCariCaraBayar penjab=new DlgCariCaraBayar(null,false);
    public  DlgPropinsi propin=new DlgPropinsi(null,false);
    public  DlgCariPerusahaan perusahaan=new DlgCariPerusahaan(null,false);
    public  DlgCariBahasa bahasa=new DlgCariBahasa(null,false);
    public  DlgCariCacatFisik cacat=new DlgCariCacatFisik(null,false);
    public  DlgCariSuku suku=new DlgCariSuku(null,false);
    private BPJSCekReferensiDokterDPJP dpjp=new BPJSCekReferensiDokterDPJP(null,false);
    private BPJSSuratKontrol skdp=new BPJSSuratKontrol(null,false);
    private BPJSSPRI skdp2=new BPJSSPRI(null,false);
    private BPJSCekReferensiPropinsi propinsikll=new BPJSCekReferensiPropinsi(null,false);
    private BPJSCekReferensiKabupaten kabupatenkll=new BPJSCekReferensiKabupaten(null,false);
    private BPJSCekReferensiKecamatan kecamatankll=new BPJSCekReferensiKecamatan(null,false);
    private ApiBPJS api=new ApiBPJS();
    private Calendar cal = Calendar.getInstance();
    private int day = cal.get(Calendar.DAY_OF_WEEK);
    private int pilih=0,p_no_ktp=0,p_tmp_lahir=0,p_nm_ibu=0,p_alamat=0,
            p_pekerjaan=0,p_no_tlp=0,p_umur=0,p_namakeluarga=0,p_no_peserta=0,
            p_kelurahan=0,p_kecamatan=0,p_kabupaten=0,p_pekerjaanpj=0,
            p_alamatpj=0,p_kelurahanpj=0,p_kecamatanpj=0,p_kabupatenpj=0,jmlhari=0,
            p_propinsi=0,p_propinsipj=0;
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    private String kdkel="",kdkec="",kdkab="",kdprop="",BASENOREG="",URUTNOREG="",link="",klg="SAUDARA",statuspasien="",pengurutan="",tahun="",bulan="",posisitahun="",awalantahun="",awalanbulan="",
            no_ktp="",tmp_lahir="",nm_ibu="",alamat="",pekerjaan="",no_tlp="",tglkkl="0000-00-00",
            umur="",umurdaftar="",namakeluarga="",no_peserta="",kelurahan="",kecamatan="",sttsumur="",
            kabupaten="",pekerjaanpj="",alamatpj="",kelurahanpj="",kecamatanpj="",
            kabupatenpj="",hariawal="",requestJson,URL="",nosep="",user="",prb="",peserta="",
            status="Baru",propinsi="",propinsipj="",utc="";
    private PreparedStatement ps,pskelengkapan,pscariumur,pssetalamat;
    private ResultSet rs,rs2;
    private double biaya=0;
    private Date lahir;
    private LocalDate today=LocalDate.now();
    private LocalDate birthday;
    private Period p;
    private long p2;
    private boolean empt=false;
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private String hari="";

    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public BPJSCekNoRujukanPCare(java.awt.Frame parent, boolean modal) {
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
                column.setPreferredWidth(450);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());
    
        try {
            link=koneksiDB.URLAPIBPJS();  
        } catch (Exception e) {
            System.out.println("E : "+e);
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

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKamar = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        panelGlass6 = new widget.panelisi();
        jLabel16 = new widget.Label();
        NoRujukan = new widget.TextBox();
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
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pencarian Data Rujukan PCare Berdasarkan Nomor Rujukan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 100));

        tbKamar.setAutoCreateRowSorter(true);
        tbKamar.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbKamar.setName("tbKamar"); // NOI18N
        Scroll.setViewportView(tbKamar);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(200, 55));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel16.setText("No. Rujukan :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass6.add(jLabel16);

        NoRujukan.setName("NoRujukan"); // NOI18N
        NoRujukan.setPreferredSize(new java.awt.Dimension(250, 23));
        NoRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRujukanKeyPressed(evt);
            }
        });
        panelGlass6.add(NoRujukan);

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

    private void NoRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRujukanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){            
            BtnCariActionPerformed(null);
            BtnPrint.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_NoRujukanKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(NoRujukan.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"No.Rujukan masih kosong...!!");
        }else{
            tampil(NoRujukan.getText());
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt,NoRujukan,BtnPrint);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

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

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        kab.dispose();
        kec.dispose();
        kel.dispose();
        dokter.dispose();
        kamar.dispose();
        penjab.dispose();
        propin.dispose();
        perusahaan.dispose();
        bahasa.dispose();
        cacat.dispose();
        suku.dispose();
        pilihan.dispose();
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,BtnKeluar);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    }//GEN-LAST:event_formWindowOpened

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSCekNoRujukanPCare dialog = new BPJSCekNoRujukanPCare(new javax.swing.JFrame(), true);
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
    private widget.TextBox NoRujukan;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.panelisi panelGlass6;
    private widget.Table tbKamar;
    // End of variables declaration//GEN-END:variables

    public void tampil(String nomorrujukan) {
        try {
            URL = link+"/Rujukan/"+nomorrujukan;
            headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("X-Cons-ID",koneksiDB.CONSIDAPIBPJS());
	    utc=String.valueOf(api.GetUTCdatetimeAsString());
	    headers.add("X-Timestamp",utc);
	    headers.add("X-Signature",api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
	    requestEntity = new HttpEntity(headers);
	    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            peserta="";
            if(nameNode.path("code").asText().equals("200")){
                Valid.tabelKosong(tabMode);
                response = mapper.readTree(api.Decrypt(root.path("response").asText(),utc)).path("rujukan");
                //response = root.path("response").path("rujukan");
                tabMode.addRow(new Object[]{
                    "Diagnosa",": "+response.path("diagnosa").path("kode").asText()+" "+response.path("diagnosa").path("nama").asText()
                });                   
                tabMode.addRow(new Object[]{
                    "Keluhan",": "+response.path("keluhan").asText()
                }); 
                tabMode.addRow(new Object[]{
                    "No.Kunjungan",": "+response.path("noKunjungan").asText()
                }); 
                tabMode.addRow(new Object[]{
                    "Pelayanan",": "+response.path("pelayanan").path("kode").asText()+" "+response.path("pelayanan").path("nama").asText()
                }); 
                tabMode.addRow(new Object[]{
                    "Peserta",": "
                }); 
                tabMode.addRow(new Object[]{
                    "       COB",": "
                });
                tabMode.addRow(new Object[]{
                    "              Nama Asuransi",": "+response.path("peserta").path("cob").path("nmAsuransi").asText()
                }); 
                tabMode.addRow(new Object[]{
                    "              No. Asuransi",": "+response.path("peserta").path("cob").path("noAsuransi").asText()
                }); 
                tabMode.addRow(new Object[]{
                    "              Tanggal TAT",": "+response.path("peserta").path("cob").path("tglTAT").asText()
                }); 
                tabMode.addRow(new Object[]{
                    "              Tanggal TMT",": "+response.path("peserta").path("cob").path("tglTMT").asText()
                }); 
                tabMode.addRow(new Object[]{
                    "       Hak Kelas",": "+response.path("peserta").path("hakKelas").path("kode").asText()+". "+response.path("peserta").path("hakKelas").path("keterangan").asText()
                });
                tabMode.addRow(new Object[]{
                    "       Informasi",": "
                });
                tabMode.addRow(new Object[]{
                    "              Dinsos",": "+response.path("peserta").path("informasi").path("dinsos").asText()
                }); 
                tabMode.addRow(new Object[]{
                    "              No.SKTM",": "+response.path("peserta").path("informasi").path("noSKTM").asText()
                }); 
                tabMode.addRow(new Object[]{
                    "              Prolanis PRB",": "+response.path("peserta").path("informasi").path("prolanisPRB").asText()
                }); 
                prb=response.path("peserta").path("informasi").path("prolanisPRB").asText().replaceAll("null","");
                tabMode.addRow(new Object[]{
                    "       Jenis Peserta",": "+response.path("peserta").path("jenisPeserta").path("kode").asText()+". "+response.path("peserta").path("jenisPeserta").path("keterangan").asText()
                });
                peserta=response.path("peserta").path("jenisPeserta").path("keterangan").asText();
                tabMode.addRow(new Object[]{
                    "       Medical Record",": "
                });
                tabMode.addRow(new Object[]{
                    "              Nomor RM",": "+response.path("peserta").path("mr").path("noMR").asText()
                }); 
                tabMode.addRow(new Object[]{
                    "              Nomor Telp",": "+response.path("peserta").path("mr").path("noTelepon").asText()
                });
                tabMode.addRow(new Object[]{
                    "       Nama Pasien",": "+response.path("peserta").path("nama").asText()
                });
                tabMode.addRow(new Object[]{
                    "       NIK",": "+response.path("peserta").path("nik").asText()
                });
                tabMode.addRow(new Object[]{
                    "       No.Kartu",": "+response.path("peserta").path("noKartu").asText()
                });
                tabMode.addRow(new Object[]{
                    "       Pisa",": "+response.path("peserta").path("pisa").asText()
                });
                tabMode.addRow(new Object[]{
                    "       Provider",": "+response.path("peserta").path("provUmum").path("kdProvider").asText()+" "+response.path("peserta").path("provUmum").path("nmProvider").asText()
                });
                tabMode.addRow(new Object[]{
                    "       Jenis Kelamin",": "+response.path("peserta").path("sex").asText().replaceAll("L","Laki-Laki").replaceAll("P","Perempuan")
                });
                tabMode.addRow(new Object[]{
                    "       Status Peserta",": "+response.path("peserta").path("statusPeserta").path("kode").asText()+" "+response.path("peserta").path("statusPeserta").path("keterangan").asText()
                });
                tabMode.addRow(new Object[]{
                    "       Tgl. Cetak Kartu",": "+response.path("peserta").path("tglCetakKartu").asText()
                });
                tabMode.addRow(new Object[]{
                    "       Tgl. Lahir",": "+response.path("peserta").path("tglLahir").asText()
                });
                tabMode.addRow(new Object[]{
                    "       Tgl. TAT",": "+response.path("peserta").path("tglTAT").asText()
                });
                tabMode.addRow(new Object[]{
                    "       Tgl. TMT",": "+response.path("peserta").path("tglTMT").asText()
                });
                tabMode.addRow(new Object[]{
                    "       Umur Saat Pelayanan",": "+response.path("peserta").path("umur").path("umurSaatPelayanan").asText()
                });
                tabMode.addRow(new Object[]{
                    "       Umur Sekarang",": "+response.path("peserta").path("umur").path("umurSekarang").asText()
                });
                tabMode.addRow(new Object[]{
                    "Poli Rujukan",": "+response.path("poliRujukan").path("kode").asText()+" "+response.path("poliRujukan").path("nama").asText()
                }); 
                tabMode.addRow(new Object[]{
                    "Provider Perujuk",": "+response.path("provPerujuk").path("kode").asText()+" "+response.path("provPerujuk").path("nama").asText()
                });
                tabMode.addRow(new Object[]{
                    "Tanggal Kunjungan",": "+response.path("tglKunjungan").asText()
                }); 
            }else {
                emptTeks();
                isForm();
                JOptionPane.showMessageDialog(null,nameNode.path("message").asText());                
            }   
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
            }
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
 
    private void autoNomor() {        
    }
    
    private void insertPasien() {
    }
    
    private void inputRegistrasi(){
    }
    
    private void insertSEP(){
    }
    
    private void UpdateUmur(){
    }
    
    public void SetRujukan(String NoRujuk){
        NoRujukan.setText(NoRujuk);
        tampil(NoRujuk);
        empt=true;
    }
    
    public void isCek(){
    }
    
    
    
}
