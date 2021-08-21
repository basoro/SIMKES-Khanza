/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgJnsPerawatanRalan.java
 *
 * Created on May 22, 2010, 11:58:21 PM
 */

package bridging;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.config;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import simrskhanza.DlgCariBangsal;

/**
 *
 * @author dosen
 */
public final class SiranapKetersediaanKamarC19 extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private javax.swing.JTextArea TeksArea;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps,ps1;
    private ResultSet rs,rs1;    
    private int i=0, covid19=0,Kapasitas19,Tersedia19,Terpakai19,ID_RSONLINE19,PREPARE19,PREPARE_PLAN19;
    private DlgCariBangsal bangsal=new DlgCariBangsal(null,false);
    private final Properties prop = new Properties();
    private String requestJson,requestJson1,URL="",respon="",respon1="",idrs="",pass="",tot="0",tot1="";
    private ApiKemenkesSirsC19 api=new ApiKemenkesSirsC19();
    private HttpHeaders headers,headers1;
    private HttpEntity requestEntity,requestEntity1;
    private ObjectMapper mapper= new ObjectMapper(), mapper1= new ObjectMapper();
    private JsonNode root,root1;
    private JsonNode nameNode,nameNode1;
    private JsonNode response,response1;
    
    
    
    /** Creates new form DlgJnsPerawatanRalan
     * @param parent
     * @param modal */
    public SiranapKetersediaanKamarC19(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setLocation(8,1);
        setSize(628,674);

        Object[] row={"P","Kode Kamar","Ruangan","Kategori","Kelas","Kapasitas","Tersedia",
                      "Terpakai","Prepare","Prepare plan","Kelas Siranap"};
        tabMode=new DefaultTableModel(null,row){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbJnsPerawatan.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbJnsPerawatan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbJnsPerawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbJnsPerawatan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(155);
            }else if(i==2){
                column.setPreferredWidth(145);
            }else if(i==3){
                column.setPreferredWidth(90);
            }else if(i==4){
                column.setPreferredWidth(170);
            }else if(i==5){
                column.setPreferredWidth(90);
            }else if(i==6){
                column.setPreferredWidth(65);
            }else if(i==7){
                column.setPreferredWidth(65);
            }else if(i==8){
                column.setPreferredWidth(120);
            }else if(i==9){
                column.setPreferredWidth(80);
            }else if(i==10){
                column.setPreferredWidth(90);
            }
        }
        tbJnsPerawatan.setDefaultRenderer(Object.class, new WarnaTable());
        Kapasitas.setDocument(new batasInput((byte)4).getOnlyAngka(Kapasitas)); 
        Tersedia.setDocument(new batasInput((byte)4).getOnlyAngka(Tersedia));
        terpakai.setDocument(new batasInput((byte)4).getOnlyAngka(terpakai)); 
        prepare.setDocument(new batasInput((byte)4).getOnlyAngka(prepare)); 
        prepare_plan.setDocument(new batasInput((byte)4).getOnlyAngka(prepare_plan)); 
        KdKamar.setDocument(new batasInput((byte)5).getKata(KdKamar)); 
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));                  
        
        if(config.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }  
        ChkInput.setSelected(false);
        isForm(); 
        
        bangsal.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(bangsal.getTable().getSelectedRow()!= -1){                   
                    KdKamar.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(),0).toString());
                    NmKamar.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(),1).toString());
                }     
                isCariKetersediaan();
                KdKamar.requestFocus();
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
        
        
        try {
            prop.loadFromXML(new FileInputStream("setting/config.xml"));
            URL = prop.getProperty("URLAPISIRSC19");
            idrs=prop.getProperty("IDSIRSC19");
            pass=prop.getProperty("PASSSIRSC19");
            covid19=((Integer)covid.getSelectedItem()).intValue();
            Kapasitas19=Integer.parseInt(Kapasitas.getText());
            ID_RSONLINE19=Integer.parseInt(ID_RSONLINE.getSelectedItem().toString().replaceAll("1 Super VIP","1").toString().replaceAll("2 VIP","2").toString().replaceAll("3 Kelas 1","3").toString().replaceAll("4 Kelas 2","4").toString().replaceAll("5 Kelas 3","5").toString().replaceAll("6 ICU","6").toString().replaceAll("7 HCU","7").toString().replaceAll("8 ICCU/ICVCU","8").toString().replaceAll("9 RICU","9").toString().replaceAll("10 NICU","10").toString().replaceAll("11 PICU","11").toString().replaceAll("12 Isolasi","12").toString().replaceAll("13 Non Kelas","13").toString().replaceAll("14 Perinatologi","14").toString().replaceAll("24 ICU Tekanan Negatif dengan Ventilator","24").toString().replaceAll("25 ICU Tekanan Negatif tanpa Ventilator","25").toString().replaceAll("26 ICU Tanpa Tekanan Negatif Dengan Ventilator","26").toString().replaceAll("27 ICU Tanpa Tekanan Negatif Tanpa Ventilator","27").toString().replaceAll("28 Isolasi Tekanan Negatif","28").toString().replaceAll("29 Isolasi Tanpa Tekanan Negatif","29").toString().replaceAll("30 NICU Khusus Covid","30").toString().replaceAll("31 PICU Khusus Covid","31").toString().replaceAll("32 IGD Khusus Covid","32").toString().replaceAll("33 VK (Ibu Melahirkan) Khusus Covid","33"));
            PREPARE19=Integer.parseInt(prepare.getText());
            Tersedia19=Integer.parseInt(Tersedia.getText());
            Terpakai19=Integer.parseInt(terpakai.getText());
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

        Tanggal = new widget.Tanggal();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbJnsPerawatan = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel8 = new widget.Label();
        Kapasitas = new widget.TextBox();
        jLabel4 = new widget.Label();
        jLabel19 = new widget.Label();
        KdKamar = new widget.TextBox();
        NmKamar = new widget.TextBox();
        btnKamar = new widget.Button();
        jLabel5 = new widget.Label();
        Kelas = new widget.ComboBox();
        jLabel9 = new widget.Label();
        Tersedia = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        prepare = new widget.TextBox();
        terpakai = new widget.TextBox();
        jLabel12 = new widget.Label();
        prepare_plan = new widget.TextBox();
        ID_RSONLINE = new widget.ComboBox();
        kategori = new widget.ComboBox();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        covid = new widget.ComboBox();
        ChkInput = new widget.CekBox();

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2021-03-16 20:13:49" }));
        Tanggal.setDisplayFormat("yyyy-MM-dd HH:mm:ss");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.setPreferredSize(new java.awt.Dimension(95, 23));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Ketersediaan Kamar RS OnLine ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbJnsPerawatan.setAutoCreateRowSorter(true);
        tbJnsPerawatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbJnsPerawatan.setName("tbJnsPerawatan"); // NOI18N
        tbJnsPerawatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbJnsPerawatanMouseClicked(evt);
            }
        });
        tbJnsPerawatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbJnsPerawatanKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbJnsPerawatan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

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
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setToolTipText("Alt+2");
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
        panelGlass9.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 130));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 197));
        FormInput.setLayout(null);

        jLabel8.setText("Kapasitas :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(700, 10, 60, 23);

        Kapasitas.setText("0");
        Kapasitas.setHighlighter(null);
        Kapasitas.setName("Kapasitas"); // NOI18N
        Kapasitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KapasitasKeyPressed(evt);
            }
        });
        FormInput.add(Kapasitas);
        Kapasitas.setBounds(760, 10, 50, 23);

        jLabel4.setText("ID SIRANAP :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(400, 10, 70, 23);

        jLabel19.setText("Kamar/Ruang :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(0, 10, 80, 23);

        KdKamar.setEditable(false);
        KdKamar.setHighlighter(null);
        KdKamar.setName("KdKamar"); // NOI18N
        KdKamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdKamarKeyPressed(evt);
            }
        });
        FormInput.add(KdKamar);
        KdKamar.setBounds(90, 10, 77, 23);

        NmKamar.setEditable(false);
        NmKamar.setName("NmKamar"); // NOI18N
        FormInput.add(NmKamar);
        NmKamar.setBounds(170, 10, 190, 23);

        btnKamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/190.png"))); // NOI18N
        btnKamar.setMnemonic('3');
        btnKamar.setToolTipText("ALt+3");
        btnKamar.setName("btnKamar"); // NOI18N
        btnKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKamarActionPerformed(evt);
            }
        });
        FormInput.add(btnKamar);
        btnKamar.setBounds(360, 10, 28, 23);

        jLabel5.setText("Kelas :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(20, 40, 60, 23);

        Kelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kelas 1", "Kelas 2", "Kelas 3", "Kelas Utama", "Kelas VIP", "Kelas VVIP" }));
        Kelas.setName("Kelas"); // NOI18N
        Kelas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                KelasItemStateChanged(evt);
            }
        });
        Kelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelasKeyPressed(evt);
            }
        });
        FormInput.add(Kelas);
        Kelas.setBounds(90, 40, 120, 23);

        jLabel9.setText("Tersedia :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(710, 40, 50, 23);

        Tersedia.setText("0");
        Tersedia.setHighlighter(null);
        Tersedia.setName("Tersedia"); // NOI18N
        Tersedia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TersediaKeyPressed(evt);
            }
        });
        FormInput.add(Tersedia);
        Tersedia.setBounds(760, 40, 50, 23);

        jLabel10.setText("Terpakai : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(880, 10, 77, 23);

        jLabel11.setText("Ruang Covid :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(820, 70, 130, 23);

        prepare.setText("0");
        prepare.setHighlighter(null);
        prepare.setName("prepare"); // NOI18N
        prepare.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                prepareKeyPressed(evt);
            }
        });
        FormInput.add(prepare);
        prepare.setBounds(960, 40, 50, 23);

        terpakai.setText("0");
        terpakai.setHighlighter(null);
        terpakai.setName("terpakai"); // NOI18N
        terpakai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                terpakaiKeyPressed(evt);
            }
        });
        FormInput.add(terpakai);
        terpakai.setBounds(960, 10, 50, 23);

        jLabel12.setText("Rencana Persiapan :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(630, 70, 125, 23);

        prepare_plan.setText("0");
        prepare_plan.setHighlighter(null);
        prepare_plan.setName("prepare_plan"); // NOI18N
        prepare_plan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                prepare_planKeyPressed(evt);
            }
        });
        FormInput.add(prepare_plan);
        prepare_plan.setBounds(760, 70, 50, 23);

        ID_RSONLINE.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 Super VIP", "2 VIP", "3 Kelas 1", "4 Kelas 2", "5 Kelas 3", "6 ICU", "7 HCU", "8 ICCU\\/ICVCU", "9 RICU", "10 NICU", "11 PICU", "12 Isolasi", "13 Non Kelas", "14 Perinatologi", "24 ICU Tekanan Negatif dengan Ventilator", "25 ICU Tekanan Negatif tanpa Ventilator", "26 ICU Tanpa Tekanan Negatif Dengan Ventilator", "27 ICU Tanpa Tekanan Negatif Tanpa Ventilator", "28 Isolasi Tekanan Negatif", "29 Isolasi Tanpa Tekanan Negatif", "30 NICU Khusus Covid", "31 PICU Khusus Covid", "32 IGD Khusus Covid", "33 VK (Ibu Melahirkan) Khusus Covid" }));
        ID_RSONLINE.setName("ID_RSONLINE"); // NOI18N
        ID_RSONLINE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID_RSONLINEActionPerformed(evt);
            }
        });
        ID_RSONLINE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ID_RSONLINEKeyPressed(evt);
            }
        });
        FormInput.add(ID_RSONLINE);
        ID_RSONLINE.setBounds(490, 10, 200, 23);

        kategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ruang Kelas III", "Ruang Kelas II", "Ruang Kelas I", "Ruang Kelas UTAMA", "Ruang Kelas VIP", "Ruang Kelas VVIP", "ISOLASI Tekanan Negatif", "ISOLASI Tanpa Tekanan Negatif" }));
        kategori.setName("kategori"); // NOI18N
        kategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kategoriKeyPressed(evt);
            }
        });
        FormInput.add(kategori);
        kategori.setBounds(90, 70, 190, 23);

        jLabel13.setText("Kategori :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(-20, 70, 100, 23);

        jLabel14.setText("Persiapan :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(900, 40, 55, 23);

        covid.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));
        covid.setName("covid"); // NOI18N
        covid.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                covidItemStateChanged(evt);
            }
        });
        covid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                covidKeyPressed(evt);
            }
        });
        FormInput.add(covid);
        covid.setBounds(960, 70, 60, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

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
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);
        internalFrame1.getAccessibleContext().setAccessibleName("::[ Data Ketersediaan Kamar RS OnLine]::");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KapasitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KapasitasKeyPressed
        Valid.pindah(evt,Kelas,Tersedia);
}//GEN-LAST:event_KapasitasKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
       if(KdKamar.getText().trim().equals("")||NmKamar.getText().trim().equals("")){
            Valid.textKosong(KdKamar,"Kode Kamar/Ruang");
        }else if(Kapasitas.getText().trim().equals("")){
            Valid.textKosong(Kapasitas,"Kapasitas");
        }else if(Tersedia.getText().trim().equals("")){
            Valid.textKosong(Tersedia,"Tersedia");
        }else if(terpakai.getText().trim().equals("")){
            Valid.textKosong(terpakai,"Terpakai");
        }else if(prepare.getText().trim().equals("")){
            Valid.textKosong(prepare,"Persiapan");
        }else if(prepare_plan.getText().trim().equals("")){
            Valid.textKosong(prepare_plan,"Rencana Persiapan");
        
        }else{
            try {
                  
                    if(covid.getSelectedIndex()!= '0'){
                        if(Sequel.menyimpantf("siranap_ketersediaan_kamar_c19","?,?,?,?,?,?,?,?,?,?,?","Data",11,new String[]{
                            KdKamar.getText(),NmKamar.getText(),kategori.getSelectedItem().toString(),Kelas.getSelectedItem().toString(),
                            Kapasitas.getText(),Tersedia.getText(),terpakai.getText(),covid.getSelectedItem().toString(),
                            ID_RSONLINE.getSelectedItem().toString().replaceAll("1 Super VIP","1").toString().replaceAll("2 VIP","2").toString().replaceAll("3 Kelas 1","3").toString().replaceAll("4 Kelas 2","4").toString().replaceAll("5 Kelas 3","5").toString().replaceAll("6 ICU","6").toString().replaceAll("7 HCU","7").toString().replaceAll("8 ICCU/ICVCU","8").toString().replaceAll("9 RICU","9").toString().replaceAll("10 NICU","10").toString().replaceAll("11 PICU","11").toString().replaceAll("12 Isolasi","12").toString().replaceAll("13 Non Kelas","13").toString().replaceAll("14 Perinatologi","14").toString().replaceAll("24 ICU Tekanan Negatif dengan Ventilator","24").toString().replaceAll("25 ICU Tekanan Negatif tanpa Ventilator","25").toString().replaceAll("26 ICU Tanpa Tekanan Negatif Dengan Ventilator","26").toString().replaceAll("27 ICU Tanpa Tekanan Negatif Tanpa Ventilator","27").toString().replaceAll("28 Isolasi Tekanan Negatif","28").toString().replaceAll("29 Isolasi Tanpa Tekanan Negatif","29").toString().replaceAll("30 NICU Khusus Covid","30").toString().replaceAll("31 PICU Khusus Covid","31").toString().replaceAll("32 IGD Khusus Covid","32").toString().replaceAll("33 VK (Ibu Melahirkan) Khusus Covid","33"),
                            prepare.getText(),prepare_plan.getText()
                        })==true){
                            
                            tampil();
                    }  
                                    headers = new HttpHeaders();
                                    headers.setContentType(MediaType.APPLICATION_JSON);
                                    headers.add("X-rs-id",idrs);
                                    headers.add("X-Timestamp",String.valueOf(api.GetUTCdatetimeAsString()));            
                                    headers.add("X-pass",pass);
                                    requestJson ="{\"id_tt\":\""+ID_RSONLINE.getSelectedItem().toString().replaceAll("1 Super VIP","1").toString().replaceAll("2 VIP","2").toString().replaceAll("3 Kelas 1","3").toString().replaceAll("4 Kelas 2","4").toString().replaceAll("5 Kelas 3","5").toString().replaceAll("6 ICU","6").toString().replaceAll("7 HCU","7").toString().replaceAll("8 ICCU/ICVCU","8").toString().replaceAll("9 RICU","9").toString().replaceAll("10 NICU","10").toString().replaceAll("11 PICU","11").toString().replaceAll("12 Isolasi","12").toString().replaceAll("13 Non Kelas","13").toString().replaceAll("14 Perinatologi","14").toString().replaceAll("24 ICU Tekanan Negatif dengan Ventilator","24").toString().replaceAll("25 ICU Tekanan Negatif tanpa Ventilator","25").toString().replaceAll("26 ICU Tanpa Tekanan Negatif Dengan Ventilator","26").toString().replaceAll("27 ICU Tanpa Tekanan Negatif Tanpa Ventilator","27").toString().replaceAll("28 Isolasi Tekanan Negatif","28").toString().replaceAll("29 Isolasi Tanpa Tekanan Negatif","29").toString().replaceAll("30 NICU Khusus Covid","30").toString().replaceAll("31 PICU Khusus Covid","31").toString().replaceAll("32 IGD Khusus Covid","32").toString().replaceAll("33 VK (Ibu Melahirkan) Khusus Covid","33")+"\", "+
                                                  "\"ruang\":\""+kategori.getSelectedItem().toString()+"\","+ 
                                                  "\"jumlah_ruang\":\""+Kapasitas.getText()+"\","+ 
                                                  "\"jumlah\":\""+Tersedia.getText()+"\","+
                                                  "\"terpakai\":\""+terpakai.getText()+"\","+
                                                  "\"prepare\":\""+prepare.getText()+"\","+
                                                  "\"prepare_plan\":\""+prepare_plan.getText()+"\","+
                                                  "\"covid\":\""+covid.getSelectedIndex()+"\""+
                                                  "}";
                                    System.out.println(requestJson);
                                    requestEntity = new HttpEntity(requestJson,headers);
                                    requestJson=api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
                                    System.out.println(requestJson);
                                    root = mapper.readTree(requestJson);
                                    respon=root.path("deskripsi").asText();
                                    if(root.path("deskripsi").asText().toLowerCase().contains("berhasil")){
                                //emptTeks(); 
                            }
                    }else if(covid.getSelectedIndex()!= '1'){
                        if(Sequel.menyimpantf("siranap_ketersediaan_kamar_c19","?,?,?,?,?,?,?,?,?,?,?","Data",11,new String[]{
                            KdKamar.getText(),NmKamar.getText(),kategori.getSelectedItem().toString(),Kelas.getSelectedItem().toString(),
                            Kapasitas.getText(),Tersedia.getText(),terpakai.getText(),covid.getSelectedItem().toString(),
                            ID_RSONLINE.getSelectedItem().toString().replaceAll("1 Super VIP","1").toString().replaceAll("2 VIP","2").toString().replaceAll("3 Kelas 1","3").toString().replaceAll("4 Kelas 2","4").toString().replaceAll("5 Kelas 3","5").toString().replaceAll("6 ICU","6").toString().replaceAll("7 HCU","7").toString().replaceAll("8 ICCU/ICVCU","8").toString().replaceAll("9 RICU","9").toString().replaceAll("10 NICU","10").toString().replaceAll("11 PICU","11").toString().replaceAll("12 Isolasi","12").toString().replaceAll("13 Non Kelas","13").toString().replaceAll("14 Perinatologi","14").toString().replaceAll("24 ICU Tekanan Negatif dengan Ventilator","24").toString().replaceAll("25 ICU Tekanan Negatif tanpa Ventilator","25").toString().replaceAll("26 ICU Tanpa Tekanan Negatif Dengan Ventilator","26").toString().replaceAll("27 ICU Tanpa Tekanan Negatif Tanpa Ventilator","27").toString().replaceAll("28 Isolasi Tekanan Negatif","28").toString().replaceAll("29 Isolasi Tanpa Tekanan Negatif","29").toString().replaceAll("30 NICU Khusus Covid","30").toString().replaceAll("31 PICU Khusus Covid","31").toString().replaceAll("32 IGD Khusus Covid","32").toString().replaceAll("33 VK (Ibu Melahirkan) Khusus Covid","33"),
                            prepare.getText(),prepare_plan.getText()
                        })==true){
                            
                            tampil();
                    }  
                                   headers1 = new HttpHeaders();
                                    headers1.setContentType(MediaType.APPLICATION_JSON);
                                    headers1.add("X-rs-id",idrs);
                                   headers1.add("X-Timestamp",String.valueOf(api.GetUTCdatetimeAsString()));            
                                    headers1.add("X-pass",pass);
                                    requestJson1 ="{\"id_tt\":\""+ID_RSONLINE.getSelectedItem().toString().replaceAll("1 Super VIP","1").toString().replaceAll("2 VIP","2").toString().replaceAll("3 Kelas 1","3").toString().replaceAll("4 Kelas 2","4").toString().replaceAll("5 Kelas 3","5").toString().replaceAll("6 ICU","6").toString().replaceAll("7 HCU","7").toString().replaceAll("8 ICCU/ICVCU","8").toString().replaceAll("9 RICU","9").toString().replaceAll("10 NICU","10").toString().replaceAll("11 PICU","11").toString().replaceAll("12 Isolasi","12").toString().replaceAll("13 Non Kelas","13").toString().replaceAll("14 Perinatologi","14").toString().replaceAll("24 ICU Tekanan Negatif dengan Ventilator","24").toString().replaceAll("25 ICU Tekanan Negatif tanpa Ventilator","25").toString().replaceAll("26 ICU Tanpa Tekanan Negatif Dengan Ventilator","26").toString().replaceAll("27 ICU Tanpa Tekanan Negatif Tanpa Ventilator","27").toString().replaceAll("28 Isolasi Tekanan Negatif","28").toString().replaceAll("29 Isolasi Tanpa Tekanan Negatif","29").toString().replaceAll("30 NICU Khusus Covid","30").toString().replaceAll("31 PICU Khusus Covid","31").toString().replaceAll("32 IGD Khusus Covid","32").toString().replaceAll("33 VK (Ibu Melahirkan) Khusus Covid","33")+"\", "+
                                                  "\"ruang\":\""+NmKamar.getText()+"\","+ 
                                                  "\"jumlah_ruang\":\""+Kapasitas.getText()+"\","+ 
                                                  "\"jumlah\":\""+Tersedia.getText()+"\","+
                                                  "\"terpakai\":\""+terpakai.getText()+"\","+
                                                  "\"prepare\":\""+prepare.getText()+"\","+
                                                  "\"prepare_plan\":\""+prepare_plan.getText()+"\","+
                                                  "\"covid\":\""+covid.getSelectedIndex()+"\""+
                                                  "}";
                                    System.out.println(requestJson1);
                                   requestEntity1 = new HttpEntity(requestJson1,headers1);
                                    requestJson1=api.getRest().exchange(URL, HttpMethod.POST, requestEntity1, String.class).getBody();
                                    System.out.println(requestJson1);
                                   root1 = mapper1.readTree(requestJson1);
                                    respon1=root1.path("deskripsi").asText();
                               if(root1.path("deskripsi").asText().toLowerCase().contains("berhasil")){
                                //emptTeks(); 
                            }
                    }   
                                    
                    //emptTeks(); 
            }catch (Exception ex) {
                System.out.println("Notifikasi Bridging : "+ex);
                if(ex.toString().contains("UnknownHostException")){
                    JOptionPane.showMessageDialog(null,"Koneksi ke server BPJS terputus...!");
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{Valid.pindah(evt, prepare_plan, BtnBatal);}
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkInput.setSelected(true);
        isForm(); 
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        for(i=0;i<tbJnsPerawatan.getRowCount();i++){ 
            if(tbJnsPerawatan.getValueAt(i,0).toString().equals("true")){
                try {
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                     headers.add("X-rs-id",idrs);
                    headers.add("X-Timestamp",String.valueOf(api.GetUTCdatetimeAsString()));            
                    headers.add("X-pass",pass);
                    headers.add("Content-Type","application/json");
                    requestJson ="{\"id_tt\":\""+tbJnsPerawatan.getValueAt(i,10).toString()+"\", "+
                                 "\"ruang\":\""+tbJnsPerawatan.getValueAt(i,2).toString()+"\","+ 
                                 "}";
                    requestEntity = new HttpEntity(headers);
                    System.out.println(URL+"/sisrute/hapusdata/"+idrs+"/"+tbJnsPerawatan.getValueAt(i,2).toString().substring(0,4)+"/"+tbJnsPerawatan.getValueAt(i,1).toString().substring(0,4));
                    requestJson=api.getRest().exchange(URL+idrs+"/"+tbJnsPerawatan.getValueAt(i,2).toString().substring(0,4)+"/"+tbJnsPerawatan.getValueAt(i,1).toString().substring(0,4), HttpMethod.POST, requestEntity, String.class).getBody();
                    System.out.println(requestJson);
                    root = mapper.readTree(requestJson);
                    respon=root.path("deskripsi").asText();
                    if(root.path("deskripsi").asText().toLowerCase().contains("berhasil")){
                        Sequel.queryu2("delete from siranap_ketersediaan_kamar_c19 where ID_RSONLINE=? and ID_RUANG=? and kelas=?",3,new String[]{
                            tbJnsPerawatan.getValueAt(i,1).toString(),tbJnsPerawatan.getValueAt(i,2).toString(),tbJnsPerawatan.getValueAt(i,3).toString(),tbJnsPerawatan.getValueAt(i,5).toString()
                        });
                    }else{
                        JOptionPane.showMessageDialog(null,nameNode.path("deskripsi").asText());
                    }
                }catch (Exception ex) {
                    System.out.println("Notifikasi Bridging : "+ex);
                    if(ex.toString().contains("UnknownHostException")){
                        JOptionPane.showMessageDialog(null,"Koneksi ke server SIRANAP terputus...!");
                    }else if(ex.toString().contains("502")){
                        JOptionPane.showMessageDialog(null,"Connection timed out. Hayati lelah bang...!");
                    }else{
                        JOptionPane.showMessageDialog(null,respon);
                    }
                }
            }
        }  
        BtnCariActionPerformed(evt);
        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(KdKamar.getText().trim().equals("")||NmKamar.getText().trim().equals("")){
            Valid.textKosong(KdKamar,"Kode Kamar/Ruang");
        }else if(Kapasitas.getText().trim().equals("")){
            Valid.textKosong(Kapasitas,"Kapasitas");
        }else if(Tersedia.getText().trim().equals("")){
            Valid.textKosong(Tersedia,"Tersedia");
        }else if(terpakai.getText().trim().equals("")){
            Valid.textKosong(terpakai,"Terpakai");
        }else if(prepare.getText().trim().equals("")){
            Valid.textKosong(prepare,"Persiapan");
        }else if(prepare_plan.getText().trim().equals("")){
            Valid.textKosong(prepare_plan,"Rencana Persiapan");
        
        }else{
            try {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("X-rs-id",idrs); 
                headers.add("X-Timestamp",String.valueOf(api.GetUTCdatetimeAsString()));            
                headers.add("X-pass",pass); 
                headers.add("Content-Type","application/json");
                //requestEntity = new HttpEntity(headers);
                //requestJson=api.getRest().exchange(URL+"/sisrute/hapusdata/"+idrs+"/"+tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),2).toString().substring(0,4)+"/"+tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),1).toString().substring(0,4), HttpMethod.POST, requestEntity, String.class).getBody();
                requestJson ="{\"id_tt\":\""+ID_RSONLINE.getSelectedItem().toString().replaceAll("1 Super VIP","1").toString().replaceAll("2 VIP","2").toString().replaceAll("3 Kelas 1","3").toString().replaceAll("4 Kelas 2","4").toString().replaceAll("5 Kelas 3","5").toString().replaceAll("6 ICU","6").toString().replaceAll("7 HCU","7").toString().replaceAll("8 ICCU/ICVCU","8").toString().replaceAll("9 RICU","9").toString().replaceAll("10 NICU","10").toString().replaceAll("11 PICU","11").toString().replaceAll("12 Isolasi","12").toString().replaceAll("13 Non Kelas","13").toString().replaceAll("14 Perinatologi","14").toString().replaceAll("24 ICU Tekanan Negatif dengan Ventilator","24").toString().replaceAll("25 ICU Tekanan Negatif tanpa Ventilator","25").toString().replaceAll("26 ICU Tanpa Tekanan Negatif Dengan Ventilator","26").toString().replaceAll("27 ICU Tanpa Tekanan Negatif Tanpa Ventilator","27").toString().replaceAll("28 Isolasi Tekanan Negatif","28").toString().replaceAll("29 Isolasi Tanpa Tekanan Negatif","29").toString().replaceAll("30 NICU Khusus Covid","30").toString().replaceAll("31 PICU Khusus Covid","31").toString().replaceAll("32 IGD Khusus Covid","32").toString().replaceAll("33 VK (Ibu Melahirkan) Khusus Covid","33")+"\", "+
                             "\"ruang\":\""+kategori.getSelectedItem().toString()+"\","+ 
                              "\"jumlah_ruang\":\""+Kapasitas.getText()+"\","+ 
                              "\"jumlah\":\""+Tersedia.getText()+"\","+
                              "\"terpakai\":\""+terpakai.getText()+"\","+
                              "\"prepare\":\""+prepare.getText()+"\","+
                              "\"prepare_plan\":\""+prepare_plan.getText()+"\","+
                              "\"covid\":\""+'1'+"\""+
                              "}";
                requestEntity = new HttpEntity(requestJson,headers);
                requestJson=api.getRest().exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody();

                System.out.println(requestJson);
                root = mapper.readTree(requestJson);
                respon=root.path("deskripsi").asText();
                if(root.path("response").asText().equals("1")){
                    Sequel.queryu2("delete from siranap_ketersediaan_kamar_c19 where  ID_RSONLINE=? and ID_RUANG=? and kelas=?",4,new String[]{
                        tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),1).toString(),tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),2).toString(),
                        tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),3).toString(),tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),5).toString()
                    });
                    Sequel.menyimpantf("siranap_ketersediaan_kamar_c19","?,?,?,?,?,?,?,?,?,?,?","Data",11,new String[]{
                            KdKamar.getText(),NmKamar.getText(),kategori.getSelectedItem().toString(),Kelas.getSelectedItem().toString(),
                            Kapasitas.getText(),Tersedia.getText(),terpakai.getText(),covid.getSelectedItem().toString(),
                            ID_RSONLINE.getSelectedItem().toString().replaceAll("1 Super VIP","1").toString().replaceAll("2 VIP","2").toString().replaceAll("3 Kelas 1","3").toString().replaceAll("4 Kelas 2","4").toString().replaceAll("5 Kelas 3","5").toString().replaceAll("6 ICU","6").toString().replaceAll("7 HCU","7").toString().replaceAll("8 ICCU/ICVCU","8").toString().replaceAll("9 RICU","9").toString().replaceAll("10 NICU","10").toString().replaceAll("11 PICU","11").toString().replaceAll("12 Isolasi","12").toString().replaceAll("13 Non Kelas","13").toString().replaceAll("14 Perinatologi","14").toString().replaceAll("24 ICU Tekanan Negatif dengan Ventilator","24").toString().replaceAll("25 ICU Tekanan Negatif tanpa Ventilator","25").toString().replaceAll("26 ICU Tanpa Tekanan Negatif Dengan Ventilator","26").toString().replaceAll("27 ICU Tanpa Tekanan Negatif Tanpa Ventilator","27").toString().replaceAll("28 Isolasi Tekanan Negatif","28").toString().replaceAll("29 Isolasi Tanpa Tekanan Negatif","29").toString().replaceAll("30 NICU Khusus Covid","30").toString().replaceAll("31 PICU Khusus Covid","31").toString().replaceAll("32 IGD Khusus Covid","32").toString().replaceAll("33 VK (Ibu Melahirkan) Khusus Covid","33"),
                            prepare.getText(),prepare_plan.getText()
                    });
                }else{
                    JOptionPane.showMessageDialog(null,nameNode.path("deskripsi").asText());
                }
            }catch (Exception ex) {
                System.out.println("Notifikasi Bridging : "+ex);
                if(ex.toString().contains("UnknownHostException")){
                    JOptionPane.showMessageDialog(null,"Koneksi ke server SIRANAP C19 terputus...!");
                }else if(ex.toString().contains("502")){
                    JOptionPane.showMessageDialog(null,"Connection timed out. Hayati lelah bang...!");
                }else{
                    JOptionPane.showMessageDialog(null,respon);
                }
            }
            
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(! TCari.getText().trim().equals("")){
            BtnCariActionPerformed(evt);
        }
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){            
                Map<String, Object> param = new HashMap<>();    
                param.put("namars",var.getnamars());
                param.put("alamatrs",var.getalamatrs());
                param.put("kotars",var.getkabupatenrs());
                param.put("propinsirs",var.getpropinsirs());
                param.put("kontakrs",var.getkontakrs());
                param.put("emailrs",var.getemailrs());   
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
                param.put("parameter","%"+TCari.getText().trim()+"%");   
                Valid.MyReport("rptKamarSiranap.jrxml","report","::[ Data Ketersediaan Kamar Aplicare]::",
                   "select siranap_ketersediaan_kamar.kode_ruang_siranap,siranap_ketersediaan_kamar.kelas_ruang_siranap,siranap_ketersediaan_kamar.kd_bangsal,"+
                   "bangsal.nm_bangsal,siranap_ketersediaan_kamar.kelas,siranap_ketersediaan_kamar.kapasitas,"+
                   "siranap_ketersediaan_kamar.tersedia,siranap_ketersediaan_kamar.tersediapria,"+
                   "siranap_ketersediaan_kamar.tersediawanita,siranap_ketersediaan_kamar.menunggu "+
                   "from siranap_ketersediaan_kamar inner join bangsal on siranap_ketersediaan_kamar.kd_bangsal=bangsal.kd_bangsal where "+
                   "siranap_ketersediaan_kamar.kode_ruang_siranap like '%"+TCari.getText().trim()+"%' or "+
                   "siranap_ketersediaan_kamar.kelas_ruang_siranap like '%"+TCari.getText().trim()+"%' or "+
                   "siranap_ketersediaan_kamar.kd_bangsal like '%"+TCari.getText().trim()+"%' or "+
                   "bangsal.nm_bangsal like '%"+TCari.getText().trim()+"%' or "+
                   "siranap_ketersediaan_kamar.kelas like '%"+TCari.getText().trim()+"%' order by siranap_ketersediaan_kamar.kode_ruang_siranap",param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{

        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbJnsPerawatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbJnsPerawatanMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbJnsPerawatanMouseClicked

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();                
}//GEN-LAST:event_ChkInputActionPerformed

private void KdKamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKamarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnKamarActionPerformed(null);
        }else{
            Valid.pindah(evt,ID_RSONLINE,Kelas);
        }
}//GEN-LAST:event_KdKamarKeyPressed

private void btnKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKamarActionPerformed
        bangsal.emptTeks();
        bangsal.isCek();
        bangsal.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        bangsal.setLocationRelativeTo(internalFrame1);
        bangsal.setVisible(true);
}//GEN-LAST:event_btnKamarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        emptTeks();
    }//GEN-LAST:event_formWindowOpened

    private void KelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelasKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            isCariKetersediaan();
            Kapasitas.requestFocus();            
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            KdKamar.requestFocus();
        }
    }//GEN-LAST:event_KelasKeyPressed

    private void TersediaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TersediaKeyPressed
        Valid.pindah(evt,Kapasitas,prepare);
    }//GEN-LAST:event_TersediaKeyPressed

    private void prepareKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prepareKeyPressed
        Valid.pindah(evt,Tersedia,terpakai);
    }//GEN-LAST:event_prepareKeyPressed

    private void terpakaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_terpakaiKeyPressed
        Valid.pindah(evt,prepare,prepare_plan);
    }//GEN-LAST:event_terpakaiKeyPressed

    private void prepare_planKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prepare_planKeyPressed
        Valid.pindah(evt,terpakai,BtnSimpan);
    }//GEN-LAST:event_prepare_planKeyPressed

    private void KelasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_KelasItemStateChanged
        isCariKetersediaan();
    }//GEN-LAST:event_KelasItemStateChanged

    private void tbJnsPerawatanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbJnsPerawatanKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbJnsPerawatanKeyReleased

    private void ID_RSONLINEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ID_RSONLINEKeyPressed
         Valid.pindah(evt,KdKamar,Kapasitas);
    }//GEN-LAST:event_ID_RSONLINEKeyPressed

    private void ID_RSONLINEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID_RSONLINEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ID_RSONLINEActionPerformed

    private void kategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kategoriKeyPressed
        Valid.pindah(evt,TCari,ID_RSONLINE);
    }//GEN-LAST:event_kategoriKeyPressed

    private void covidItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_covidItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_covidItemStateChanged

    private void covidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_covidKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_covidKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            SiranapKetersediaanKamar dialog = new SiranapKetersediaanKamar(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.PanelBiasa FormInput;
    private widget.ComboBox ID_RSONLINE;
    private widget.TextBox Kapasitas;
    private widget.TextBox KdKamar;
    private widget.ComboBox Kelas;
    private widget.Label LCount;
    private widget.TextBox NmKamar;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.Tanggal Tanggal;
    private widget.TextBox Tersedia;
    private widget.Button btnKamar;
    private widget.ComboBox covid;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel19;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.ComboBox kategori;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.TextBox prepare;
    private widget.TextBox prepare_plan;
    private widget.Table tbJnsPerawatan;
    private widget.TextBox terpakai;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try{
           ps=koneksi.prepareStatement(
                   "select siranap_ketersediaan_kamar_c19.ID_ruang,siranap_ketersediaan_kamar_c19.kategori,siranap_ketersediaan_kamar_c19.ID_RSONLINE,"+
                           "bangsal.nm_bangsal,siranap_ketersediaan_kamar_c19.kelas,siranap_ketersediaan_kamar_c19.kapasitas,"+
                           "siranap_ketersediaan_kamar_c19.tersedia,siranap_ketersediaan_kamar_c19.terpakai,siranap_ketersediaan_kamar_c19.prepare,"+
                           "siranap_ketersediaan_kamar_c19.prepare_plan,siranap_ketersediaan_kamar_c19.covid from siranap_ketersediaan_kamar_c19 inner join "+
                           "bangsal on siranap_ketersediaan_kamar_c19.id_ruang=bangsal.kd_bangsal where  bangsal.status = '1' order by "+
                           "siranap_ketersediaan_kamar_c19.ID_ruang");
            try {
              rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        false,rs.getString("ID_RUANG"),rs.getString("nm_bangsal"),
                        rs.getString("KATEGORI"),rs.getString("kelas"),rs.getString("kapasitas"),rs.getString("tersedia"),rs.getString("terpakai"),
                        rs.getString("PREPARE"),rs.getString("PREPARE_PLAN"),rs.getString("ID_RSONLINE"),
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif Ketersediaan : "+e);
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
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {
        KdKamar.setText("");
        NmKamar.setText("");
        Kapasitas.setText("0");
        Tersedia.setText("0");
        prepare.setText("0");
        terpakai.setText("0");
        prepare_plan.setText("0");
        prepare_plan.setText("0");
        //RuangSiranap.requestFocus();
    }

    private void getData() {
       if(tbJnsPerawatan.getSelectedRow()!= -1){
           KdKamar.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),1).toString());
           NmKamar.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),2).toString());
           kategori.setSelectedItem(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),3).toString());
           Kelas.setSelectedItem(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),4).toString());
           Kapasitas.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),5).toString());
           Tersedia.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),6).toString());
           terpakai.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),7).toString());
           //ID_RSONLINE.setSelectedItem(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),10).toString());
           //prepare.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),9).toString());
           //prepare_plan.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),11).toString());
           // covid.setSelectedItem(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),8).toString().replaceAll("0","Tidak").replaceAll("1","Ya"));
           
           
           
       }
    }

    
   
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,130));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        //BtnSimpan.setEnabled(var.getsiranap_ketersediaan_kamar_c19());
        //BtnHapus.setEnabled(var.getsiranap_ketersediaan_kamar_c19());
        //BtnEdit.setEnabled(var.getsiranap_ketersediaan_kamar_c19());
        //BtnPrint.setEnabled(var.getsiranap_ketersediaan_kamar_c19());
    }
    
    public JTable getTable(){
        return tbJnsPerawatan;
    }    

    private void isCariKetersediaan() {
        if(!KdKamar.getText().equals("")){
            Kapasitas.setText(Sequel.cariIsi("select count(kd_bangsal) as jumlah_ruang from kamar where kd_bangsal LIKE ? ",KdKamar.getText()));
            Tersedia.setText(Sequel.cariIsi("select count(kd_kamar) from kamar where statusdata='1' and kelas='"+Kelas.getSelectedItem()+"' and status='KOSONG' and kd_bangsal=?",KdKamar.getText()));
            terpakai.setText(Sequel.cariIsi("select count(kd_kamar) from kamar where statusdata='1' and kelas='"+Kelas.getSelectedItem()+"' and status='ISI' and kd_bangsal=?",KdKamar.getText()));
            prepare.setText(Sequel.cariIsi("select count(kd_kamar) from kamar where statusdata='1' and kelas='"+Kelas.getSelectedItem()+"' and status='DIBERSIHKAN' and kd_bangsal=?",KdKamar.getText()));
            tot1=Tersedia.getText()+terpakai.getText();
        }
    }
    
    
    

    
}
