package simrskhanza;

import bridging.BPJSCekReferensiFaskes;
import bridging.BPJSCekReferensiPenyakit;
import bridging.BPJSCekReferensiPoli;
import bridging.BPJSCekRiwayatRujukanPCare;
import bridging.BPJSCekNoRujukanPCare;
import bridging.BPJSDataSEP;
import bridging.BPJSMonitoringKlaim;
import informasi.InformasiAnalisaKamin;
import inventory.DlgSuplier;
import presensi.DlgBarcode;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import inventory.DlgCariPenjualan;
import inventory.DlgDaruratStok;
import inventory.DlgIndustriFarmasi;
import inventory.DlgInputStok;
import inventory.DlgKonversi;
import inventory.DlgMutasiBarang;
import inventory.DlgPembelian;
import inventory.DlgPemesanan;
import inventory.DlgPenjualan;
import inventory.DlgPiutang;
import inventory.DlgProyeksiBeriObat;
import inventory.DlgProyeksiJual;
import inventory.DlgReturBeli;
import inventory.DlgReturJual;
import inventory.DlgReturObatPasien;
import inventory.DlgReturPiutang;
import inventory.DlgSirkulasiBarang;
import inventory.DlgStokPasien;
import inventory.DlgDaftarPermintaanResep;
import ipsrs.DlgBarangIPSRS;
import ipsrs.DlgJenisIPSRS;
import ipsrs.DlgPembelianIPSRS;
import ipsrs.DlgPengeluaranIPSRS;
import ipsrs.DlgRBiayaHarianIPSRS;
import ipsrs.DlgRHPembelianIPSRS;
import ipsrs.DlgRHPengeluaranIPSRS;
import ipsrs.DlgSuplierIPSRS;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import inventory.DlgRiwayatBarangMedis;
import keuangan.DlgHutangObatBelumLunas;
import presensi.DlgBulanan;
import presensi.DlgHarian;
import presensi.DlgJadwalTambahan;
import presensi.DlgJamMasuk;
import presensi.DlgSidikJari;
import presensi.DlgTemporaryPresensi;
import presensi.DlgKehadiran;
import informasi.InformasiKamar;
import informasi.InformasiTarifOperasi;
import setting.DlgUser;
import setting.DlgSetAplikasi;
import keuangan.DlgRBPaketBHP;
import keuangan.DlgLhtPiutang;
import keuangan.DlgAkunPiutang;
import keuangan.DlgBayarPemesanan;
import keuangan.DlgBayarPiutang;
import keuangan.DlgBubes;
import keuangan.DlgCashflow;
import keuangan.DlgDetailJMDokter;
import keuangan.DlgDetailPotongan;
import keuangan.DlgDetailTambahan;
import keuangan.DlgFeeBacaanEKG;
import keuangan.DlgFeePeriksaRalan;
import keuangan.DlgFeeRujukanRontgen;
import keuangan.DlgFeeVisitDokter;
import keuangan.DlgJnsPerawatanLab;
import keuangan.DlgJnsPerawatanRadiologi;
import keuangan.DlgJnsPerawatanRalan;
import keuangan.DlgJurnal;
import keuangan.DlgJurnalHarian;
import keuangan.DlgLabaRugi;
import keuangan.DlgLhtBiaya;
import keuangan.DlgRBTindakanPoli;
import keuangan.DlgRBKSO;
import keuangan.DlgPaymentPoint;
import keuangan.DlgPemasukanLain;
//import keuangan.DlgPembayaranPerAKunBayar;
import keuangan.DlgPembayaranRalan;
import keuangan.DlgPembayaranRalanPerHari;
import keuangan.DlgPembayaranRanap;
import keuangan.DlgPembyaranRanapPerhari;
import keuangan.DlgPengaturanRekening;
import keuangan.DlgPengeluaranHarian;
import keuangan.DlgPiutangBelumLunas;
import keuangan.DlgRBJS;
import keuangan.DlgRBJmDokter;
import keuangan.DlgRBJmParamedis;
import keuangan.DlgRBKSO;
import keuangan.DlgRBMenejemen;
import keuangan.DlgRBObatBangsal;
import keuangan.DlgRBObatDokterPeresep;
import keuangan.DlgRBObatDokterRalan;
import keuangan.DlgRBObatDokterRanap;
import keuangan.DlgRBObatPercaraBayar;
import keuangan.DlgRBObatPoli;
import keuangan.DlgRBTindakanDokter;
import keuangan.DlgRBTindakanKamar;
import keuangan.DlgRBTindakanPoli;
import keuangan.DlgRHJS;
import keuangan.DlgRHJmDokter;
import keuangan.DlgRHJmParamedis;
import keuangan.DlgRHKSO;
import keuangan.DlgRHMenejemen;
import keuangan.DlgRHPaketBHP;
import keuangan.DlgRekapPerShift;
import keuangan.DlgRekening;
import keuangan.DlgRekeningTahun;
import keuangan.DlgRincianPiutangPasien;
import laporan.DlgICD9;
import laporan.DlgKunjunganRalan;
import laporan.DlgKunjunganRanap;
import laporan.DlgSensusHarianPoli;
import permintaan.DlgCariPermintaanLab;
import permintaan.DlgCariPermintaanRadiologi;
import setting.DlgBiayaHarian;
import setting.DlgBiayaSekaliMasuk;
import setting.DlgClosingKasir;
import setting.DlgSetEmbalase;
import setting.DlgSetHarga;
import setting.DlgSetHargaKamar;
import setting.DlgSetHargaObatRalan;
import setting.DlgSetHargaObatRanap;
import setting.DlgSetKamarInap;
import setting.DlgSetKeterlambatan;
import setting.DlgSetNota;
import setting.DlgSetOtoLokasi;
import setting.DlgSetOtoRalan;
import setting.DlgSetPenjabLab;
import setting.DlgSetRM;
import setting.DlgSetTarif;


/**
 *
 * @author perpustakaan
 */
public class frmUtama extends javax.swing.JFrame {
    private final Connection koneksi=koneksiDB.condb();
    private final sekuel Sequel=new sekuel();
    private final validasi Valid=new validasi();
    private final DlgKasirRalan kasirralan=new DlgKasirRalan(this,false);
    private final DlgAbout About=new DlgAbout(this,false);
    private final DlgPenggajian penggajian=new DlgPenggajian(this,false);
    private final DlgRetensi retensi=new DlgRetensi(this,false);
    private static frmUtama myInstance;
    private PreparedStatement ps;
    private ResultSet rs;
    private final Properties prop = new Properties();
    private int jmlmenu=0;
    private String coder_nik="",pilihpage="",judulform="";
    /** Creates new form frmUtama */
    private frmUtama() {
        super();
        initComponents();
        setIconImage(new ImageIcon(super.getClass().getResource("/icons/addressbook-edit24.png")).getImage());

        this.setSize(screen.width,screen.height);
        edAdmin.setDocument(new batasInput((byte)100).getKata(edAdmin));
        edPwd.setDocument(new batasInput((byte)100).getKata(edPwd));
        PassLama.setDocument(new batasInput((byte)100).getKata(PassLama));
        Passbaru1.setDocument(new batasInput((byte)100).getKata(Passbaru1));
        PassBaru2.setDocument(new batasInput((byte)100).getKata(PassBaru2));

        DlgLogin.setSize(344,201);
        DlgLogin.setVisible(false);
        DlgLogin.setLocationRelativeTo(null);

        WindowInput.setSize(349,180);
        WindowInput.setVisible(false);
        WindowInput.setLocationRelativeTo(null);

        lblTgl.setText(tanggal.getSelectedItem().toString());
        try {
            prop.loadFromXML(new FileInputStream("setting/config.xml"));
        } catch (Exception e) {
            System.out.println("Notif Setting : "+e);
        }

        FlayMenu.setVisible(false);

    }

    public static frmUtama getInstance() {
        if (myInstance == null)
            myInstance = new frmUtama();

        return myInstance;
    }


    private final Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DlgLogin = new javax.swing.JDialog();
        internalFrame2 = new widget.InternalFrame();
        internalFrame3 = new widget.InternalFrame();
        panelGlass1 = new usu.widget.glass.PanelGlass();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        edAdmin = new widget.PasswordBox();
        edPwd = new widget.PasswordBox();
        jLabel6 = new javax.swing.JLabel();
        BtnLogin = new widget.Button();
        BtnCancel = new widget.Button();
        WindowInput = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        PassLama = new widget.TextBox();
        jLabel9 = new widget.Label();
        BtnClosePass = new widget.Button();
        BtnSimpanPass = new widget.Button();
        jLabel10 = new widget.Label();
        Passbaru1 = new widget.TextBox();
        jLabel12 = new widget.Label();
        PassBaru2 = new widget.TextBox();
        tanggal = new widget.Tanggal();
        btnDataPenjualan = new widget.ButtonBig();
        btnInputPenjualan = new widget.ButtonBig();
        btnResepObatDepan = new widget.ButtonBig();
        btnPermintaanLab = new widget.ButtonBig();
        btnPermintaanRadiologi = new widget.ButtonBig();
        btnPeriksaRadiologi = new widget.ButtonBig();
        btnLaboratorium = new widget.ButtonBig();
        btnDaftarPermintaanResep = new widget.ButtonBig();
        internalFrame1 = new widget.InternalFrame();
        BtnToolReg = new widget.ButtonBig();
        btnToolIGD = new widget.ButtonBig();
        jSeparator5 = new javax.swing.JSeparator();
        btnToolLab = new widget.ButtonBig();
        btnToolRad = new widget.ButtonBig();
        BtnToolJualObat = new widget.ButtonBig();
        jSeparator9 = new javax.swing.JSeparator();
        BtnToolKamnap = new widget.ButtonBig();
        BtnToolKasir = new widget.ButtonBig();
        jSeparator7 = new javax.swing.JSeparator();
        BtnLog = new widget.ButtonBig();
        BtnClose = new widget.ButtonBig();
        internalFrame4 = new widget.InternalFrame();
        lblStts = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblUser = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblTgl = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        PanelUtama = new javax.swing.JPanel();
        scrollPane1 = new widget.ScrollPane();
        PanelWall = new usu.widget.glass.PanelGlass();
        panelJudul = new usu.widget.glass.PanelGlass();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        FlayMenu = new usu.widget.glass.PanelGlass();
        MenuBar = new widget.MenuBar();
        jMenuSIMRSKhanza = new javax.swing.JMenu();
        MnAbout = new javax.swing.JMenuItem();
        MenuPengaturan = new javax.swing.JMenu();
        MnSetAplikasi = new javax.swing.JMenuItem();
        MnPenujang = new javax.swing.JMenuItem();
        MnSetOtoLok = new javax.swing.JMenuItem();
        MnSetKmrInp = new javax.swing.JMenuItem();
        MnSetHargaKamar = new javax.swing.JMenuItem();
        MnSetEmbTus = new javax.swing.JMenuItem();
        MnSetUser = new javax.swing.JMenuItem();
        MnSetTrackerLog = new javax.swing.JMenuItem();
        MnDisplayAntrian = new javax.swing.JMenuItem();
        MnSetHargaObt = new javax.swing.JMenuItem();
        MnSetObtRnp = new javax.swing.JMenuItem();
        MnSetPenggTrf = new javax.swing.JMenuItem();
        MnSetOtoRalan = new javax.swing.JMenuItem();
        MnBiayaHarian = new javax.swing.JMenuItem();
        MnBiayaMskSkl = new javax.swing.JMenuItem();
        MnSetRM = new javax.swing.JMenuItem();
        MnSetBilling = new javax.swing.JMenuItem();
        MnClosingKsr = new javax.swing.JMenuItem();
        MnSetLambtPres = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        MnLogin = new javax.swing.JMenuItem();
        MnGantiPassword = new javax.swing.JMenuItem();
        MenuKeluar = new javax.swing.JMenuItem();
        jMenuPasien = new javax.swing.JMenu();
        MnPasien = new javax.swing.JMenuItem();
        MnKelahiranBayi = new javax.swing.JMenuItem();
        MnPasienMeninggal = new javax.swing.JMenuItem();
        MnDiagnosaPasien = new javax.swing.JMenuItem();
        MnRiwayatPerawatan = new javax.swing.JMenuItem();
        MnRetBrksRm = new javax.swing.JMenuItem();
        jMenuLayanan = new javax.swing.JMenu();
        MenuRawatJalan = new javax.swing.JMenu();
        MnRegistrasi = new javax.swing.JMenuItem();
        MnTindakanRalan = new javax.swing.JMenuItem();
        MenuRawatInap = new javax.swing.JMenu();
        MnKamarInap = new javax.swing.JMenuItem();
        MnDpjpRanap = new javax.swing.JMenuItem();
        MnTindakanRanap = new javax.swing.JMenuItem();
        MenuRujukan = new javax.swing.JMenu();
        MnRujukMasuk = new javax.swing.JMenuItem();
        MnRujukKeluar = new javax.swing.JMenuItem();
        MnInfoKamar = new javax.swing.JMenuItem();
        MnIgd = new javax.swing.JMenuItem();
        MnJadwalDokter = new javax.swing.JMenuItem();
        MnOperasi = new javax.swing.JMenuItem();
        MenuObat = new javax.swing.JMenu();
        MnPemberianObat = new javax.swing.JMenuItem();
        MnResepObat = new javax.swing.JMenuItem();
        MnResepPulang = new javax.swing.JMenuItem();
        MnDietPasien = new javax.swing.JMenuItem();
        MnPeriksaLab = new javax.swing.JMenuItem();
        MnPeriksaRad = new javax.swing.JMenuItem();
        MenuKasir = new javax.swing.JMenu();
        MnKasirRalan = new javax.swing.JMenuItem();
        MnDepositPasien = new javax.swing.JMenuItem();
        MnPiutangPasien = new javax.swing.JMenuItem();
        jMenuManajemen = new javax.swing.JMenu();
        MnDataDokter = new javax.swing.JMenuItem();
        MnDataPetugas = new javax.swing.JMenuItem();
        MnDataPegawai = new javax.swing.JMenuItem();
        MenuPresensi = new javax.swing.JMenu();
        MnBarcodePresensi = new javax.swing.JMenuItem();
        MnJamPresensi = new javax.swing.JMenuItem();
        MnPresensiHarian = new javax.swing.JMenuItem();
        MnPresensiBulanan = new javax.swing.JMenuItem();
        MnTempPresensi = new javax.swing.JMenuItem();
        MnRekapKehadiran = new javax.swing.JMenuItem();
        MnSidikJari = new javax.swing.JMenuItem();
        MenuJadwal = new javax.swing.JMenu();
        MnJadwalPegawai = new javax.swing.JMenuItem();
        MnJdwlTambahan = new javax.swing.JMenuItem();
        MnPresensiHarian1 = new javax.swing.JMenuItem();
        jMenuFarmasi = new javax.swing.JMenu();
        MenuSuplier = new javax.swing.JMenu();
        MnIndustriFar = new javax.swing.JMenuItem();
        MnSuplier = new javax.swing.JMenuItem();
        MnSatuanBrg = new javax.swing.JMenuItem();
        MnKonvSat = new javax.swing.JMenuItem();
        MenuInvObat = new javax.swing.JMenu();
        MnJnsObt = new javax.swing.JMenuItem();
        MnDataObat = new javax.swing.JMenuItem();
        MnStokOpname = new javax.swing.JMenuItem();
        MnMutasiObat = new javax.swing.JMenuItem();
        MnStokObtPx = new javax.swing.JMenuItem();
        MnPengadaan = new javax.swing.JMenuItem();
        MnPemesanan = new javax.swing.JMenuItem();
        MnPenjualanObt = new javax.swing.JMenuItem();
        MnStokOpname1 = new javax.swing.JMenuItem();
        MenuRetur = new javax.swing.JMenu();
        MnReturKeSup = new javax.swing.JMenuItem();
        MnReturPemb = new javax.swing.JMenuItem();
        MnReturObtRanap = new javax.swing.JMenuItem();
        MnReturPiutangPemb = new javax.swing.JMenuItem();
        MenuKeuntungan = new javax.swing.JMenu();
        MnKeuntunganPenj = new javax.swing.JMenuItem();
        MnKeuntBeriObt = new javax.swing.JMenuItem();
        MnSirkulasiObt = new javax.swing.JMenuItem();
        MnRiwayat = new javax.swing.JMenuItem();
        MnDaruratStok = new javax.swing.JMenuItem();
        jMenuInventory = new javax.swing.JMenu();
        MnSatuanBrgNon = new javax.swing.JMenuItem();
        MnJnsBrgNon = new javax.swing.JMenuItem();
        MnDataBrgNon = new javax.swing.JMenuItem();
        MnSupNon = new javax.swing.JMenuItem();
        MnPengadaanbrg = new javax.swing.JMenuItem();
        MnStokKeluar = new javax.swing.JMenuItem();
        MnBiayaPengadaan = new javax.swing.JMenuItem();
        MenuRekap = new javax.swing.JMenu();
        MnRekapPengadaan = new javax.swing.JMenuItem();
        MnRekapStok = new javax.swing.JMenuItem();
        jMenuBridging = new javax.swing.JMenu();
        MnCekSKDPBPJS = new javax.swing.JMenuItem();
        MnRiwPesBpjs = new javax.swing.JMenuItem();
        MnRiwPesBpjs1 = new javax.swing.JMenuItem();
        MnCekTglRujukan = new javax.swing.JMenuItem();
        MnCekNoRujPCare = new javax.swing.JMenuItem();
        MnCekNoRujRS = new javax.swing.JMenuItem();
        MnCekRujKartuPCare = new javax.swing.JMenuItem();
        MnCekRujKartuRS = new javax.swing.JMenuItem();
        MnRefDiagBpjs = new javax.swing.JMenuItem();
        MnRefPlBpjs = new javax.swing.JMenuItem();
        MnRefFaskes = new javax.swing.JMenuItem();
        MnBridging = new javax.swing.JMenuItem();
        MnMonitoringKlaim = new javax.swing.JMenuItem();
        jMenuLaporan = new javax.swing.JMenu();
        MenuLapObat = new javax.swing.JMenu();
        MnLapObtPoli = new javax.swing.JMenuItem();
        MnObtKmr = new javax.swing.JMenuItem();
        MnObtDokRln = new javax.swing.JMenuItem();
        MnObtDokRnp = new javax.swing.JMenuItem();
        MnObtDokRsp = new javax.swing.JMenuItem();
        MnObtCrByr = new javax.swing.JMenuItem();
        MnDetJMDok = new javax.swing.JMenuItem();
        MenuLapHarian = new javax.swing.JMenu();
        MnHrDokAll = new javax.swing.JMenuItem();
        MnHrDokRalan = new javax.swing.JMenuItem();
        MnHrDok = new javax.swing.JMenuItem();
        MnHrKamar = new javax.swing.JMenuItem();
        MnHrBhp = new javax.swing.JMenuItem();
        MnHrParamedis = new javax.swing.JMenuItem();
        MnHrMnj = new javax.swing.JMenuItem();
        MnHrKso = new javax.swing.JMenuItem();
        MnHrSrn = new javax.swing.JMenuItem();
        MenuLapBulanan = new javax.swing.JMenu();
        MnBulananDok = new javax.swing.JMenuItem();
        MnBlnParamedis = new javax.swing.JMenuItem();
        MnBlnSrn = new javax.swing.JMenuItem();
        MnBlnKso = new javax.swing.JMenuItem();
        MnBlnMnj = new javax.swing.JMenuItem();
        MnBlnBhp = new javax.swing.JMenuItem();
        MenuLapBulanan1 = new javax.swing.JMenu();
        MnFreeVstDok = new javax.swing.JMenuItem();
        MnFreeBcEkg = new javax.swing.JMenuItem();
        MnFreeRujRotg = new javax.swing.JMenuItem();
        MnFreeRujRnp = new javax.swing.JMenuItem();
        MnFreePrkRln = new javax.swing.JMenuItem();
        MenuLapPemb = new javax.swing.JMenu();
        MnLapPembRalan = new javax.swing.JMenuItem();
        MnLapPembRnp = new javax.swing.JMenuItem();
        MnRkpPmbRln = new javax.swing.JMenuItem();
        MnRkpPmbRnp = new javax.swing.JMenuItem();
        MnLapTagMsk = new javax.swing.JMenuItem();
        MnLapTmbBiayaPx = new javax.swing.JMenuItem();
        MnLapPotBiayaPx = new javax.swing.JMenuItem();
        MnLapDepositPx = new javax.swing.JMenuItem();
        MnLapUangShift = new javax.swing.JMenuItem();
        MnLapPaymentPoint = new javax.swing.JMenuItem();
        MenuLapPykt = new javax.swing.JMenu();
        MnLapIcd9 = new javax.swing.JMenuItem();
        MnLapIcd10 = new javax.swing.JMenuItem();
        MnLapObtPenyakit = new javax.swing.JMenuItem();
        MnLapKjgRln = new javax.swing.JMenuItem();
        MnLapKjgRanap = new javax.swing.JMenuItem();
        MnSensusHrPoli = new javax.swing.JMenuItem();
        jMenuKeu = new javax.swing.JMenu();
        MenuTarif = new javax.swing.JMenu();
        MnTarifKamar = new javax.swing.JMenuItem();
        MnTarifRalan = new javax.swing.JMenuItem();
        MnTarifRanap = new javax.swing.JMenuItem();
        MnTarifLab = new javax.swing.JMenuItem();
        MnTarifRadiologi = new javax.swing.JMenuItem();
        MnTarifOperasi = new javax.swing.JMenuItem();
        MenuRekening = new javax.swing.JMenu();
        MnAkunRek = new javax.swing.JMenuItem();
        MnRekThn = new javax.swing.JMenuItem();
        MnPengaturanRek = new javax.swing.JMenuItem();
        MnAkunPiutang = new javax.swing.JMenuItem();
        MnAkunBayar = new javax.swing.JMenuItem();
        MnPengeluaranHr = new javax.swing.JMenuItem();
        MnPemasukanlain = new javax.swing.JMenuItem();
        MenuPiutang = new javax.swing.JMenu();
        MnPiutangPx = new javax.swing.JMenuItem();
        MnRincPiutangPx = new javax.swing.JMenuItem();
        MnPiutangBlmLns = new javax.swing.JMenuItem();
        MnByrPiutang = new javax.swing.JMenuItem();
        MnHtgObt = new javax.swing.JMenuItem();
        MnByrPsnObt = new javax.swing.JMenuItem();
        MnPostingJurnal = new javax.swing.JMenuItem();
        MnJurnalHr = new javax.swing.JMenuItem();
        MnBukuBesar = new javax.swing.JMenuItem();
        MnCashFlow = new javax.swing.JMenuItem();
        MnKeu = new javax.swing.JMenuItem();
        jMenuBantuan = new javax.swing.JMenu();
        MnBantuan = new javax.swing.JMenuItem();
        MnDokumentasi = new javax.swing.JMenuItem();
        MnUcapan = new javax.swing.JMenuItem();

        DlgLogin.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgLogin.setName("DlgLogin"); // NOI18N
        DlgLogin.setUndecorated(true);
        DlgLogin.setResizable(false);

        internalFrame2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 120, 40)));
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setWarnaAtas(new java.awt.Color(0, 120, 80));
        internalFrame2.setWarnaBawah(new java.awt.Color(0, 120, 80));
        internalFrame2.setLayout(null);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(100, 125, 90), 1, true), ":: Silahkan Anda Login ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 12), new java.awt.Color(50, 70, 40))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaAtas(new java.awt.Color(195, 215, 170));
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 255, 220));
        internalFrame3.setLayout(null);

        panelGlass1.setBackground(java.awt.Color.lightGray);
        panelGlass1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(145, 185, 145)));
        panelGlass1.setOpaqueImage(false);
        panelGlass1.setRound(false);
        panelGlass1.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(125, 81, 81));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ID Admin :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass1.add(jLabel4);
        jLabel4.setBounds(2, 12, 80, 23);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(125, 81, 81));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Password :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass1.add(jLabel5);
        jLabel5.setBounds(2, 40, 80, 23);

        edAdmin.setForeground(new java.awt.Color(125, 81, 81));
        edAdmin.setToolTipText("Silahkan masukkan ID Admin");
        edAdmin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edAdmin.setName("edAdmin"); // NOI18N
        edAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edAdminKeyPressed(evt);
            }
        });
        panelGlass1.add(edAdmin);
        edAdmin.setBounds(85, 12, 220, 23);

        edPwd.setForeground(new java.awt.Color(125, 81, 81));
        edPwd.setToolTipText("Silahkan masukkan password");
        edPwd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edPwd.setName("edPwd"); // NOI18N
        edPwd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edPwdKeyPressed(evt);
            }
        });
        panelGlass1.add(edPwd);
        edPwd.setBounds(85, 40, 220, 23);

        internalFrame3.add(panelGlass1);
        panelGlass1.setBounds(-1, 30, 342, 76);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/LaST (Cobalt) Lock n Gear.png"))); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        internalFrame3.add(jLabel6);
        jLabel6.setBounds(120, 5, 135, 145);

        BtnLogin.setForeground(new java.awt.Color(185, 86, 86));
        BtnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lock.png"))); // NOI18N
        BtnLogin.setMnemonic('Z');
        BtnLogin.setText("Log-in");
        BtnLogin.setToolTipText("Alt+Z");
        BtnLogin.setName("BtnLogin"); // NOI18N
        BtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoginActionPerformed(evt);
            }
        });
        internalFrame3.add(BtnLogin);
        BtnLogin.setBounds(12, 125, 105, 32);

        BtnCancel.setForeground(new java.awt.Color(185, 86, 86));
        BtnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        BtnCancel.setMnemonic('Y');
        BtnCancel.setText("Batal");
        BtnCancel.setToolTipText("Alt+Y");
        BtnCancel.setName("BtnCancel"); // NOI18N
        BtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelActionPerformed(evt);
            }
        });
        internalFrame3.add(BtnCancel);
        BtnCancel.setBounds(222, 125, 105, 32);

        internalFrame2.add(internalFrame3);
        internalFrame3.setBounds(2, 15, 340, 170);

        DlgLogin.getContentPane().add(internalFrame2, java.awt.BorderLayout.CENTER);

        WindowInput.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowInput.setModal(true);
        WindowInput.setName("WindowInput"); // NOI18N
        WindowInput.setUndecorated(true);
        WindowInput.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Ubah Password ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 70, 40))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(235, 245, 225));
        internalFrame6.setLayout(null);

        PassLama.setHighlighter(null);
        PassLama.setName("PassLama"); // NOI18N
        PassLama.setSelectionColor(new java.awt.Color(255, 255, 255));
        internalFrame6.add(PassLama);
        PassLama.setBounds(128, 30, 190, 23);

        jLabel9.setText("Password Lama :");
        jLabel9.setName("jLabel9"); // NOI18N
        internalFrame6.add(jLabel9);
        jLabel9.setBounds(0, 30, 125, 23);

        BtnClosePass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        BtnClosePass.setMnemonic('2');
        BtnClosePass.setText("Tutup");
        BtnClosePass.setToolTipText("Alt+2");
        BtnClosePass.setName("BtnClosePass"); // NOI18N
        BtnClosePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnClosePassActionPerformed(evt);
            }
        });
        BtnClosePass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnClosePassKeyPressed(evt);
            }
        });
        internalFrame6.add(BtnClosePass);
        BtnClosePass.setBounds(230, 130, 100, 30);

        BtnSimpanPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save-16x16.png"))); // NOI18N
        BtnSimpanPass.setMnemonic('1');
        BtnSimpanPass.setText("Simpan");
        BtnSimpanPass.setToolTipText("Alt+1");
        BtnSimpanPass.setName("BtnSimpanPass"); // NOI18N
        BtnSimpanPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanPassActionPerformed(evt);
            }
        });
        BtnSimpanPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanPassKeyPressed(evt);
            }
        });
        internalFrame6.add(BtnSimpanPass);
        BtnSimpanPass.setBounds(20, 130, 100, 30);

        jLabel10.setText("Password Baru :");
        jLabel10.setName("jLabel10"); // NOI18N
        internalFrame6.add(jLabel10);
        jLabel10.setBounds(0, 60, 125, 23);

        Passbaru1.setHighlighter(null);
        Passbaru1.setName("Passbaru1"); // NOI18N
        Passbaru1.setSelectionColor(new java.awt.Color(255, 255, 255));
        internalFrame6.add(Passbaru1);
        Passbaru1.setBounds(128, 60, 190, 23);

        jLabel12.setText("Password Baru :");
        jLabel12.setName("jLabel12"); // NOI18N
        internalFrame6.add(jLabel12);
        jLabel12.setBounds(0, 90, 125, 23);

        PassBaru2.setHighlighter(null);
        PassBaru2.setName("PassBaru2"); // NOI18N
        PassBaru2.setSelectionColor(new java.awt.Color(255, 255, 255));
        internalFrame6.add(PassBaru2);
        PassBaru2.setBounds(128, 90, 190, 23);

        WindowInput.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        tanggal.setEditable(false);
        tanggal.setForeground(new java.awt.Color(50, 70, 50));
        tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27/08/2021" }));
        tanggal.setDisplayFormat("dd/MM/yyyy");
        tanggal.setName("tanggal"); // NOI18N
        tanggal.setOpaque(false);

        btnDataPenjualan.setForeground(new java.awt.Color(40, 70, 50));
        btnDataPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/1485357971_desktop_computer.png"))); // NOI18N
        btnDataPenjualan.setText("Data Penjualan Obat & BHP");
        btnDataPenjualan.setIconTextGap(0);
        btnDataPenjualan.setName("btnDataPenjualan"); // NOI18N
        btnDataPenjualan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDataPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataPenjualanActionPerformed(evt);
            }
        });

        btnInputPenjualan.setForeground(new java.awt.Color(40, 70, 50));
        btnInputPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cashbox.png"))); // NOI18N
        btnInputPenjualan.setText("Input Penjualan Obat & BHP");
        btnInputPenjualan.setIconTextGap(0);
        btnInputPenjualan.setName("btnInputPenjualan"); // NOI18N
        btnInputPenjualan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnInputPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputPenjualanActionPerformed(evt);
            }
        });

        btnResepObatDepan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_task.png"))); // NOI18N
        btnResepObatDepan.setText("No. Resep");
        btnResepObatDepan.setIconTextGap(0);
        btnResepObatDepan.setName("btnResepObatDepan"); // NOI18N
        btnResepObatDepan.setPreferredSize(new java.awt.Dimension(200, 90));
        btnResepObatDepan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResepObatDepanActionPerformed(evt);
            }
        });

        btnPermintaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/1481002063_laboratory.png"))); // NOI18N
        btnPermintaanLab.setText("Permintaan Lab");
        btnPermintaanLab.setIconTextGap(0);
        btnPermintaanLab.setName("btnPermintaanLab"); // NOI18N
        btnPermintaanLab.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPermintaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPermintaanLabActionPerformed(evt);
            }
        });

        btnPermintaanRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/1410153940_radiology.png"))); // NOI18N
        btnPermintaanRadiologi.setText("Permintaan Radiologi");
        btnPermintaanRadiologi.setIconTextGap(0);
        btnPermintaanRadiologi.setName("btnPermintaanRadiologi"); // NOI18N
        btnPermintaanRadiologi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPermintaanRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPermintaanRadiologiActionPerformed(evt);
            }
        });

        btnPeriksaRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Print.png"))); // NOI18N
        btnPeriksaRadiologi.setText("Periksa Radiologi");
        btnPeriksaRadiologi.setIconTextGap(0);
        btnPeriksaRadiologi.setName("btnPeriksaRadiologi"); // NOI18N
        btnPeriksaRadiologi.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPeriksaRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeriksaRadiologiActionPerformed(evt);
            }
        });

        btnLaboratorium.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/1481002039_laboratory.png"))); // NOI18N
        btnLaboratorium.setText("Periksa Lab");
        btnLaboratorium.setIconTextGap(0);
        btnLaboratorium.setName("btnLaboratorium"); // NOI18N
        btnLaboratorium.setPreferredSize(new java.awt.Dimension(200, 90));
        btnLaboratorium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaboratoriumActionPerformed(evt);
            }
        });

        btnDaftarPermintaanResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/1360485894_add-notes.png"))); // NOI18N
        btnDaftarPermintaanResep.setText("Daftar Resep Dokter");
        btnDaftarPermintaanResep.setIconTextGap(0);
        btnDaftarPermintaanResep.setName("btnDaftarPermintaanResep"); // NOI18N
        btnDaftarPermintaanResep.setPreferredSize(new java.awt.Dimension(200, 90));
        btnDaftarPermintaanResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaftarPermintaanResepActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("::[ Khanza Hospital Management System 2017 ]::");
        setIconImages(null);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 225, 195)));
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(40, 44));
        internalFrame1.setVerifyInputWhenFocusTarget(false);
        internalFrame1.setWarnaAtas(new java.awt.Color(235, 245, 215));
        internalFrame1.setWarnaBawah(new java.awt.Color(215, 225, 195));
        internalFrame1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 1));

        BtnToolReg.setForeground(new java.awt.Color(80, 100, 80));
        BtnToolReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/PatientFile.png"))); // NOI18N
        BtnToolReg.setMnemonic('R');
        BtnToolReg.setText("Registrasi");
        BtnToolReg.setToolTipText("Alt+R");
        BtnToolReg.setEnabled(false);
        BtnToolReg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnToolReg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnToolReg.setIconTextGap(3);
        BtnToolReg.setMargin(new java.awt.Insets(1, 2, 1, 0));
        BtnToolReg.setName("BtnToolReg"); // NOI18N
        BtnToolReg.setPreferredSize(new java.awt.Dimension(90, 40));
        BtnToolReg.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        BtnToolReg.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnToolReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnToolRegActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnToolReg);

        btnToolIGD.setForeground(new java.awt.Color(80, 100, 80));
        btnToolIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Doctor.png"))); // NOI18N
        btnToolIGD.setMnemonic('D');
        btnToolIGD.setText("IGD/UGD");
        btnToolIGD.setToolTipText("Alt+D");
        btnToolIGD.setEnabled(false);
        btnToolIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnToolIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnToolIGD.setIconTextGap(3);
        btnToolIGD.setMargin(new java.awt.Insets(1, 2, 1, 0));
        btnToolIGD.setName("btnToolIGD"); // NOI18N
        btnToolIGD.setPreferredSize(new java.awt.Dimension(84, 40));
        btnToolIGD.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnToolIGD.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnToolIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToolIGDActionPerformed(evt);
            }
        });
        internalFrame1.add(btnToolIGD);

        jSeparator5.setBackground(new java.awt.Color(175, 195, 150));
        jSeparator5.setForeground(new java.awt.Color(175, 195, 150));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 120, 80)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        jSeparator5.setOpaque(true);
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 38));
        internalFrame1.add(jSeparator5);

        btnToolLab.setForeground(new java.awt.Color(80, 100, 80));
        btnToolLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addressbook-search24.png"))); // NOI18N
        btnToolLab.setMnemonic('O');
        btnToolLab.setText("Laboratorium");
        btnToolLab.setToolTipText("Alt+O");
        btnToolLab.setEnabled(false);
        btnToolLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnToolLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnToolLab.setIconTextGap(3);
        btnToolLab.setMargin(new java.awt.Insets(1, 2, 1, 0));
        btnToolLab.setName("btnToolLab"); // NOI18N
        btnToolLab.setPreferredSize(new java.awt.Dimension(110, 40));
        btnToolLab.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnToolLab.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnToolLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToolLabActionPerformed(evt);
            }
        });
        internalFrame1.add(btnToolLab);

        btnToolRad.setForeground(new java.awt.Color(80, 100, 80));
        btnToolRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Surgeon.png"))); // NOI18N
        btnToolRad.setMnemonic('A');
        btnToolRad.setText("Radiologi");
        btnToolRad.setToolTipText("Alt+A");
        btnToolRad.setEnabled(false);
        btnToolRad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnToolRad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnToolRad.setIconTextGap(3);
        btnToolRad.setMargin(new java.awt.Insets(1, 2, 1, 0));
        btnToolRad.setName("btnToolRad"); // NOI18N
        btnToolRad.setPreferredSize(new java.awt.Dimension(90, 40));
        btnToolRad.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnToolRad.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnToolRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToolRadActionPerformed(evt);
            }
        });
        internalFrame1.add(btnToolRad);

        BtnToolJualObat.setForeground(new java.awt.Color(80, 100, 80));
        BtnToolJualObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/shopping-cart-insert24.png"))); // NOI18N
        BtnToolJualObat.setMnemonic('j');
        BtnToolJualObat.setText("Apotek");
        BtnToolJualObat.setToolTipText("Alt+J");
        BtnToolJualObat.setEnabled(false);
        BtnToolJualObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnToolJualObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnToolJualObat.setIconTextGap(3);
        BtnToolJualObat.setMargin(new java.awt.Insets(1, 2, 1, 0));
        BtnToolJualObat.setName("BtnToolJualObat"); // NOI18N
        BtnToolJualObat.setPreferredSize(new java.awt.Dimension(90, 40));
        BtnToolJualObat.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        BtnToolJualObat.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnToolJualObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnToolJualObatActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnToolJualObat);

        jSeparator9.setBackground(new java.awt.Color(175, 195, 150));
        jSeparator9.setForeground(new java.awt.Color(175, 195, 150));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 120, 80)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        jSeparator9.setOpaque(true);
        jSeparator9.setPreferredSize(new java.awt.Dimension(1, 38));
        internalFrame1.add(jSeparator9);

        BtnToolKamnap.setForeground(new java.awt.Color(80, 100, 80));
        BtnToolKamnap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home24.png"))); // NOI18N
        BtnToolKamnap.setMnemonic('K');
        BtnToolKamnap.setText("Rawat Inap");
        BtnToolKamnap.setToolTipText("Alt+K");
        BtnToolKamnap.setEnabled(false);
        BtnToolKamnap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnToolKamnap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnToolKamnap.setIconTextGap(3);
        BtnToolKamnap.setMargin(new java.awt.Insets(1, 2, 1, 0));
        BtnToolKamnap.setName("BtnToolKamnap"); // NOI18N
        BtnToolKamnap.setPreferredSize(new java.awt.Dimension(100, 40));
        BtnToolKamnap.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        BtnToolKamnap.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnToolKamnap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnToolKamnapActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnToolKamnap);

        BtnToolKasir.setForeground(new java.awt.Color(80, 100, 80));
        BtnToolKasir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addressbook-edit24.png"))); // NOI18N
        BtnToolKasir.setMnemonic('S');
        BtnToolKasir.setText("Rawat Jalan");
        BtnToolKasir.setToolTipText("Alt+S");
        BtnToolKasir.setEnabled(false);
        BtnToolKasir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnToolKasir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnToolKasir.setIconTextGap(3);
        BtnToolKasir.setMargin(new java.awt.Insets(1, 2, 1, 0));
        BtnToolKasir.setName("BtnToolKasir"); // NOI18N
        BtnToolKasir.setPreferredSize(new java.awt.Dimension(105, 40));
        BtnToolKasir.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        BtnToolKasir.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnToolKasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnToolKasirActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnToolKasir);

        jSeparator7.setBackground(new java.awt.Color(175, 195, 150));
        jSeparator7.setForeground(new java.awt.Color(175, 195, 150));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 120, 80)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        jSeparator7.setOpaque(true);
        jSeparator7.setPreferredSize(new java.awt.Dimension(1, 38));
        internalFrame1.add(jSeparator7);

        BtnLog.setForeground(new java.awt.Color(80, 100, 80));
        BtnLog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/login2.png"))); // NOI18N
        BtnLog.setMnemonic('L');
        BtnLog.setText("Log In");
        BtnLog.setToolTipText("Alt+L");
        BtnLog.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnLog.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnLog.setIconTextGap(3);
        BtnLog.setMargin(new java.awt.Insets(1, 2, 1, 0));
        BtnLog.setName("BtnLog"); // NOI18N
        BtnLog.setPreferredSize(new java.awt.Dimension(80, 40));
        BtnLog.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        BtnLog.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLogActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnLog);

        BtnClose.setForeground(new java.awt.Color(80, 100, 80));
        BtnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Delete.png"))); // NOI18N
        BtnClose.setMnemonic('U');
        BtnClose.setText("Keluar");
        BtnClose.setToolTipText("Alt+U");
        BtnClose.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnClose.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnClose.setIconTextGap(3);
        BtnClose.setMargin(new java.awt.Insets(1, 2, 1, 0));
        BtnClose.setName("BtnClose"); // NOI18N
        BtnClose.setPreferredSize(new java.awt.Dimension(80, 40));
        BtnClose.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        BtnClose.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseActionPerformed(evt);
            }
        });
        internalFrame1.add(BtnClose);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.PAGE_START);

        internalFrame4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 225, 195)));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setPreferredSize(new java.awt.Dimension(330, 25));
        internalFrame4.setWarnaAtas(new java.awt.Color(215, 225, 195));
        internalFrame4.setWarnaBawah(new java.awt.Color(235, 245, 215));
        internalFrame4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        lblStts.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lblStts.setForeground(new java.awt.Color(80, 100, 80));
        lblStts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStts.setText("Status Admin :");
        lblStts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblStts.setName("lblStts"); // NOI18N
        lblStts.setPreferredSize(new java.awt.Dimension(100, 23));
        internalFrame4.add(lblStts);

        jSeparator1.setBackground(new java.awt.Color(175, 195, 150));
        jSeparator1.setForeground(new java.awt.Color(175, 195, 150));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setName("jSeparator1"); // NOI18N
        jSeparator1.setOpaque(true);
        jSeparator1.setPreferredSize(new java.awt.Dimension(1, 20));
        internalFrame4.add(jSeparator1);

        lblUser.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lblUser.setForeground(new java.awt.Color(80, 100, 80));
        lblUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUser.setText("Log Out");
        lblUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblUser.setName("lblUser"); // NOI18N
        lblUser.setPreferredSize(new java.awt.Dimension(170, 23));
        internalFrame4.add(lblUser);

        jSeparator2.setBackground(new java.awt.Color(175, 195, 150));
        jSeparator2.setForeground(new java.awt.Color(175, 195, 150));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setName("jSeparator2"); // NOI18N
        jSeparator2.setOpaque(true);
        jSeparator2.setPreferredSize(new java.awt.Dimension(1, 20));
        internalFrame4.add(jSeparator2);

        lblTgl.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lblTgl.setForeground(new java.awt.Color(80, 100, 80));
        lblTgl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTgl.setText("Tanggal");
        lblTgl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTgl.setName("lblTgl"); // NOI18N
        lblTgl.setPreferredSize(new java.awt.Dimension(150, 23));
        internalFrame4.add(lblTgl);

        jSeparator3.setBackground(new java.awt.Color(175, 195, 150));
        jSeparator3.setForeground(new java.awt.Color(175, 195, 150));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setName("jSeparator3"); // NOI18N
        jSeparator3.setOpaque(true);
        jSeparator3.setPreferredSize(new java.awt.Dimension(1, 20));
        internalFrame4.add(jSeparator3);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(80, 100, 80));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/file-edit-16x16.png"))); // NOI18N
        jLabel7.setText("Didesain & dibuat oleh Khanza.Soft Media.");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel7.setIconTextGap(3);
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(347, 23));
        internalFrame4.add(jLabel7);

        getContentPane().add(internalFrame4, java.awt.BorderLayout.PAGE_END);

        PanelUtama.setName("PanelUtama"); // NOI18N
        PanelUtama.setOpaque(false);
        PanelUtama.setLayout(new java.awt.BorderLayout());

        scrollPane1.setBorder(null);
        scrollPane1.setName("scrollPane1"); // NOI18N

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/icons/wallpaper.jpg"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                PanelWallMouseMoved(evt);
            }
        });
        PanelWall.setLayout(new java.awt.BorderLayout());

        panelJudul.setBackground(new java.awt.Color(255, 255, 255));
        panelJudul.setOpaqueImage(false);
        panelJudul.setPreferredSize(new java.awt.Dimension(200, 170));
        panelJudul.setRound(false);
        panelJudul.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Rumas Sakit Kok Gitu");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(430, 30));
        panelJudul.add(jLabel11);
        jLabel11.setBounds(145, 90, 680, 30);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/yaski_icon.png"))); // NOI18N
        jLabel8.setText("RS Masa Kini");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel8.setName("jLabel8"); // NOI18N
        panelJudul.add(jLabel8);
        jLabel8.setBounds(40, 0, 820, 150);

        PanelWall.add(panelJudul, java.awt.BorderLayout.PAGE_END);

        FlayMenu.setBackground(new java.awt.Color(255, 255, 255));
        FlayMenu.setOpaqueImage(false);
        FlayMenu.setPreferredSize(new java.awt.Dimension(200, 110));
        FlayMenu.setRound(false);
        FlayMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        PanelWall.add(FlayMenu, java.awt.BorderLayout.PAGE_START);

        scrollPane1.setViewportView(PanelWall);

        PanelUtama.add(scrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(PanelUtama, java.awt.BorderLayout.CENTER);

        MenuBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        MenuBar.setForeground(new java.awt.Color(255, 255, 255));
        MenuBar.setName("MenuBar"); // NOI18N
        MenuBar.setPreferredSize(new java.awt.Dimension(227, 32));

        jMenuSIMRSKhanza.setBorder(null);
        jMenuSIMRSKhanza.setForeground(new java.awt.Color(255, 255, 255));
        jMenuSIMRSKhanza.setMnemonic('A');
        jMenuSIMRSKhanza.setText("  SIMRSKhanza");
        jMenuSIMRSKhanza.setToolTipText("Alt+A");
        jMenuSIMRSKhanza.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuSIMRSKhanza.setIconTextGap(8);
        jMenuSIMRSKhanza.setName("jMenuSIMRSKhanza"); // NOI18N
        jMenuSIMRSKhanza.setOpaque(false);
        jMenuSIMRSKhanza.setPreferredSize(new java.awt.Dimension(103, 24));

        MnAbout.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnAbout.setText("Tentang SIMRS Khanza");
        MnAbout.setName("MnAbout"); // NOI18N
        MnAbout.setPreferredSize(new java.awt.Dimension(200, 22));
        MnAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAboutActionPerformed(evt);
            }
        });
        jMenuSIMRSKhanza.add(MnAbout);

        MenuPengaturan.setBorder(null);
        MenuPengaturan.setForeground(new java.awt.Color(51, 51, 51));
        MenuPengaturan.setMnemonic('P');
        MenuPengaturan.setText("Pengaturan");
        MenuPengaturan.setToolTipText("Alt+P");
        MenuPengaturan.setAutoscrolls(true);
        MenuPengaturan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MenuPengaturan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuPengaturan.setIconTextGap(7);
        MenuPengaturan.setName("MenuPengaturan"); // NOI18N
        MenuPengaturan.setPreferredSize(new java.awt.Dimension(200, 22));

        MnSetAplikasi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSetAplikasi.setForeground(new java.awt.Color(51, 51, 51));
        MnSetAplikasi.setText("Set Aplikasi");
        MnSetAplikasi.setEnabled(false);
        MnSetAplikasi.setName("MnSetAplikasi"); // NOI18N
        MnSetAplikasi.setPreferredSize(new java.awt.Dimension(250, 24));
        MnSetAplikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetAplikasiActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnSetAplikasi);

        MnPenujang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPenujang.setForeground(new java.awt.Color(51, 51, 51));
        MnPenujang.setText("Set PJ Penunjang");
        MnPenujang.setEnabled(false);
        MnPenujang.setName("MnPenujang"); // NOI18N
        MnPenujang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenujangActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnPenujang);

        MnSetOtoLok.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSetOtoLok.setForeground(new java.awt.Color(51, 51, 51));
        MnSetOtoLok.setText("Set Oto Lokasi");
        MnSetOtoLok.setEnabled(false);
        MnSetOtoLok.setName("MnSetOtoLok"); // NOI18N
        MnSetOtoLok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetOtoLokActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnSetOtoLok);

        MnSetKmrInp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSetKmrInp.setForeground(new java.awt.Color(51, 51, 51));
        MnSetKmrInp.setText("Set Kamar Inap");
        MnSetKmrInp.setEnabled(false);
        MnSetKmrInp.setName("MnSetKmrInp"); // NOI18N
        MnSetKmrInp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetKmrInpActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnSetKmrInp);

        MnSetHargaKamar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSetHargaKamar.setForeground(new java.awt.Color(51, 51, 51));
        MnSetHargaKamar.setText("Set Harga Kamar");
        MnSetHargaKamar.setEnabled(false);
        MnSetHargaKamar.setName("MnSetHargaKamar"); // NOI18N
        MnSetHargaKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetHargaKamarActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnSetHargaKamar);

        MnSetEmbTus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSetEmbTus.setForeground(new java.awt.Color(51, 51, 51));
        MnSetEmbTus.setText("Set Embalase & Tuslah");
        MnSetEmbTus.setEnabled(false);
        MnSetEmbTus.setName("MnSetEmbTus"); // NOI18N
        MnSetEmbTus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetEmbTusActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnSetEmbTus);

        MnSetUser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSetUser.setForeground(new java.awt.Color(51, 51, 51));
        MnSetUser.setText("Set User");
        MnSetUser.setEnabled(false);
        MnSetUser.setName("MnSetUser"); // NOI18N
        MnSetUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetUserActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnSetUser);

        MnSetTrackerLog.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSetTrackerLog.setForeground(new java.awt.Color(51, 51, 51));
        MnSetTrackerLog.setText("Tracker Login");
        MnSetTrackerLog.setEnabled(false);
        MnSetTrackerLog.setName("MnSetTrackerLog"); // NOI18N
        MnSetTrackerLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetTrackerLogActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnSetTrackerLog);

        MnDisplayAntrian.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDisplayAntrian.setForeground(new java.awt.Color(51, 51, 51));
        MnDisplayAntrian.setText("Display Antrian");
        MnDisplayAntrian.setEnabled(false);
        MnDisplayAntrian.setName("MnDisplayAntrian"); // NOI18N
        MnDisplayAntrian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDisplayAntrianActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnDisplayAntrian);

        MnSetHargaObt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSetHargaObt.setForeground(new java.awt.Color(51, 51, 51));
        MnSetHargaObt.setText("Set Harga Obat");
        MnSetHargaObt.setEnabled(false);
        MnSetHargaObt.setName("MnSetHargaObt"); // NOI18N
        MnSetHargaObt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetHargaObtActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnSetHargaObt);

        MnSetObtRnp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSetObtRnp.setForeground(new java.awt.Color(51, 51, 51));
        MnSetObtRnp.setText("Set Obat Ranap");
        MnSetObtRnp.setEnabled(false);
        MnSetObtRnp.setName("MnSetObtRnp"); // NOI18N
        MnSetObtRnp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetObtRnpActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnSetObtRnp);

        MnSetPenggTrf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSetPenggTrf.setForeground(new java.awt.Color(51, 51, 51));
        MnSetPenggTrf.setText("Set Penggunaan Tarif");
        MnSetPenggTrf.setEnabled(false);
        MnSetPenggTrf.setName("MnSetPenggTrf"); // NOI18N
        MnSetPenggTrf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetPenggTrfActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnSetPenggTrf);

        MnSetOtoRalan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSetOtoRalan.setForeground(new java.awt.Color(51, 51, 51));
        MnSetOtoRalan.setText("Set Oto Ralan");
        MnSetOtoRalan.setEnabled(false);
        MnSetOtoRalan.setName("MnSetOtoRalan"); // NOI18N
        MnSetOtoRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetOtoRalanActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnSetOtoRalan);

        MnBiayaHarian.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnBiayaHarian.setForeground(new java.awt.Color(51, 51, 51));
        MnBiayaHarian.setText("Biaya Harian");
        MnBiayaHarian.setEnabled(false);
        MnBiayaHarian.setName("MnBiayaHarian"); // NOI18N
        MnBiayaHarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBiayaHarianActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnBiayaHarian);

        MnBiayaMskSkl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnBiayaMskSkl.setForeground(new java.awt.Color(51, 51, 51));
        MnBiayaMskSkl.setText("Biaya Masuk Sekali");
        MnBiayaMskSkl.setEnabled(false);
        MnBiayaMskSkl.setName("MnBiayaMskSkl"); // NOI18N
        MnBiayaMskSkl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBiayaMskSklActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnBiayaMskSkl);

        MnSetRM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSetRM.setForeground(new java.awt.Color(51, 51, 51));
        MnSetRM.setText("Set RM");
        MnSetRM.setEnabled(false);
        MnSetRM.setName("MnSetRM"); // NOI18N
        MnSetRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetRMActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnSetRM);

        MnSetBilling.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSetBilling.setForeground(new java.awt.Color(51, 51, 51));
        MnSetBilling.setText("Set Billing");
        MnSetBilling.setEnabled(false);
        MnSetBilling.setName("MnSetBilling"); // NOI18N
        MnSetBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetBillingActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnSetBilling);

        MnClosingKsr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnClosingKsr.setForeground(new java.awt.Color(51, 51, 51));
        MnClosingKsr.setText("Closing Kasir");
        MnClosingKsr.setEnabled(false);
        MnClosingKsr.setName("MnClosingKsr"); // NOI18N
        MnClosingKsr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnClosingKsrActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnClosingKsr);

        MnSetLambtPres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSetLambtPres.setForeground(new java.awt.Color(51, 51, 51));
        MnSetLambtPres.setText("Set Keterlambatan Presensi");
        MnSetLambtPres.setEnabled(false);
        MnSetLambtPres.setName("MnSetLambtPres"); // NOI18N
        MnSetLambtPres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetLambtPresActionPerformed(evt);
            }
        });
        MenuPengaturan.add(MnSetLambtPres);

        jMenuSIMRSKhanza.add(MenuPengaturan);

        jSeparator8.setBackground(new java.awt.Color(155, 175, 130));
        jSeparator8.setForeground(new java.awt.Color(155, 175, 130));
        jSeparator8.setName("jSeparator8"); // NOI18N
        jSeparator8.setPreferredSize(new java.awt.Dimension(0, 6));
        jMenuSIMRSKhanza.add(jSeparator8);

        MnLogin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLogin.setText("Log In");
        MnLogin.setMargin(new java.awt.Insets(10, 0, 0, 0));
        MnLogin.setName("MnLogin"); // NOI18N
        MnLogin.setPreferredSize(new java.awt.Dimension(200, 22));
        MnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLogActionPerformed(evt);
            }
        });
        jMenuSIMRSKhanza.add(MnLogin);

        MnGantiPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnGantiPassword.setText("Ubah Password");
        MnGantiPassword.setEnabled(false);
        MnGantiPassword.setName("MnGantiPassword"); // NOI18N
        MnGantiPassword.setPreferredSize(new java.awt.Dimension(200, 22));
        MnGantiPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiPasswordBtnLogActionPerformed(evt);
            }
        });
        jMenuSIMRSKhanza.add(MnGantiPassword);

        MenuKeluar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuKeluar.setText("Keluar");
        MenuKeluar.setName("MenuKeluar"); // NOI18N
        MenuKeluar.setPreferredSize(new java.awt.Dimension(200, 22));
        MenuKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuKeluarBtnKeluarActionPerformed(evt);
            }
        });
        jMenuSIMRSKhanza.add(MenuKeluar);

        MenuBar.add(jMenuSIMRSKhanza);

        jMenuPasien.setBorder(null);
        jMenuPasien.setForeground(new java.awt.Color(255, 255, 255));
        jMenuPasien.setMnemonic('N');
        jMenuPasien.setText("  Pasien");
        jMenuPasien.setToolTipText("Alt+N");
        jMenuPasien.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuPasien.setName("jMenuPasien"); // NOI18N
        jMenuPasien.setOpaque(false);
        jMenuPasien.setPreferredSize(new java.awt.Dimension(62, 30));

        MnPasien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPasien.setForeground(new java.awt.Color(51, 51, 51));
        MnPasien.setText("Pasien");
        MnPasien.setEnabled(false);
        MnPasien.setName("MnPasien"); // NOI18N
        MnPasien.setPreferredSize(new java.awt.Dimension(200, 22));
        MnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPasienActionPerformed(evt);
            }
        });
        jMenuPasien.add(MnPasien);

        MnKelahiranBayi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnKelahiranBayi.setForeground(new java.awt.Color(51, 51, 51));
        MnKelahiranBayi.setText("Kelahiran Bayi");
        MnKelahiranBayi.setEnabled(false);
        MnKelahiranBayi.setName("MnKelahiranBayi"); // NOI18N
        MnKelahiranBayi.setPreferredSize(new java.awt.Dimension(200, 22));
        MnKelahiranBayi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKelahiranBayiActionPerformed(evt);
            }
        });
        jMenuPasien.add(MnKelahiranBayi);

        MnPasienMeninggal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPasienMeninggal.setForeground(new java.awt.Color(51, 51, 51));
        MnPasienMeninggal.setText("Pasien Meninggal");
        MnPasienMeninggal.setEnabled(false);
        MnPasienMeninggal.setName("MnPasienMeninggal"); // NOI18N
        MnPasienMeninggal.setPreferredSize(new java.awt.Dimension(200, 22));
        MnPasienMeninggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPasienMeninggalActionPerformed(evt);
            }
        });
        jMenuPasien.add(MnPasienMeninggal);

        MnDiagnosaPasien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDiagnosaPasien.setForeground(new java.awt.Color(51, 51, 51));
        MnDiagnosaPasien.setText("Diagnosa Pasien");
        MnDiagnosaPasien.setEnabled(false);
        MnDiagnosaPasien.setName("MnDiagnosaPasien"); // NOI18N
        MnDiagnosaPasien.setPreferredSize(new java.awt.Dimension(200, 22));
        MnDiagnosaPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiagnosaPasienActionPerformed(evt);
            }
        });
        jMenuPasien.add(MnDiagnosaPasien);

        MnRiwayatPerawatan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRiwayatPerawatan.setForeground(new java.awt.Color(51, 51, 51));
        MnRiwayatPerawatan.setText("Riwayat Perawatan");
        MnRiwayatPerawatan.setEnabled(false);
        MnRiwayatPerawatan.setName("MnRiwayatPerawatan"); // NOI18N
        MnRiwayatPerawatan.setPreferredSize(new java.awt.Dimension(200, 22));
        MnRiwayatPerawatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatPerawatanActionPerformed(evt);
            }
        });
        jMenuPasien.add(MnRiwayatPerawatan);

        MnRetBrksRm.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRetBrksRm.setForeground(new java.awt.Color(51, 51, 51));
        MnRetBrksRm.setText("Retensi Berkas Rekam Medik");
        MnRetBrksRm.setEnabled(false);
        MnRetBrksRm.setName("MnRetBrksRm"); // NOI18N
        MnRetBrksRm.setPreferredSize(new java.awt.Dimension(200, 22));
        MnRetBrksRm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRetBrksRmActionPerformed(evt);
            }
        });
        jMenuPasien.add(MnRetBrksRm);

        MenuBar.add(jMenuPasien);

        jMenuLayanan.setBorder(null);
        jMenuLayanan.setForeground(new java.awt.Color(255, 255, 255));
        jMenuLayanan.setMnemonic('I');
        jMenuLayanan.setText("  Layanan");
        jMenuLayanan.setToolTipText("Alt+I");
        jMenuLayanan.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuLayanan.setName("jMenuLayanan"); // NOI18N
        jMenuLayanan.setOpaque(false);
        jMenuLayanan.setPreferredSize(new java.awt.Dimension(68, 21));

        MenuRawatJalan.setBorder(null);
        MenuRawatJalan.setForeground(new java.awt.Color(51, 51, 51));
        MenuRawatJalan.setMnemonic('P');
        MenuRawatJalan.setText("Rawat Jalan");
        MenuRawatJalan.setToolTipText("Alt+P");
        MenuRawatJalan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuRawatJalan.setIconTextGap(8);
        MenuRawatJalan.setName("MenuRawatJalan"); // NOI18N
        MenuRawatJalan.setPreferredSize(new java.awt.Dimension(200, 22));

        MnRegistrasi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRegistrasi.setForeground(new java.awt.Color(51, 51, 51));
        MnRegistrasi.setText("Registrasi");
        MnRegistrasi.setName("MnRegistrasi"); // NOI18N
        MnRegistrasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRegistrasiActionPerformed(evt);
            }
        });
        MenuRawatJalan.add(MnRegistrasi);

        MnTindakanRalan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnTindakanRalan.setForeground(new java.awt.Color(51, 51, 51));
        MnTindakanRalan.setText("Tindakan Rawat Jalan");
        MnTindakanRalan.setEnabled(false);
        MnTindakanRalan.setName("MnTindakanRalan"); // NOI18N
        MnTindakanRalan.setPreferredSize(null);
        MnTindakanRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTindakanRalanActionPerformed(evt);
            }
        });
        MenuRawatJalan.add(MnTindakanRalan);

        jMenuLayanan.add(MenuRawatJalan);

        MenuRawatInap.setBorder(null);
        MenuRawatInap.setForeground(new java.awt.Color(51, 51, 51));
        MenuRawatInap.setMnemonic('P');
        MenuRawatInap.setText("Rawat Inap");
        MenuRawatInap.setToolTipText("Alt+P");
        MenuRawatInap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuRawatInap.setIconTextGap(8);
        MenuRawatInap.setName("MenuRawatInap"); // NOI18N
        MenuRawatInap.setPreferredSize(new java.awt.Dimension(200, 22));

        MnKamarInap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnKamarInap.setForeground(new java.awt.Color(51, 51, 51));
        MnKamarInap.setText("Kamar Inap");
        MnKamarInap.setEnabled(false);
        MnKamarInap.setName("MnKamarInap"); // NOI18N
        MnKamarInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKamarInapActionPerformed(evt);
            }
        });
        MenuRawatInap.add(MnKamarInap);

        MnDpjpRanap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDpjpRanap.setForeground(new java.awt.Color(51, 51, 51));
        MnDpjpRanap.setText("DPJP Ranap");
        MnDpjpRanap.setEnabled(false);
        MnDpjpRanap.setName("MnDpjpRanap"); // NOI18N
        MnDpjpRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDpjpRanapActionPerformed(evt);
            }
        });
        MenuRawatInap.add(MnDpjpRanap);

        MnTindakanRanap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnTindakanRanap.setForeground(new java.awt.Color(51, 51, 51));
        MnTindakanRanap.setText("Tindakan Ranap");
        MnTindakanRanap.setEnabled(false);
        MnTindakanRanap.setName("MnTindakanRanap"); // NOI18N
        MnTindakanRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTindakanRanapActionPerformed(evt);
            }
        });
        MenuRawatInap.add(MnTindakanRanap);

        jMenuLayanan.add(MenuRawatInap);

        MenuRujukan.setBorder(null);
        MenuRujukan.setForeground(new java.awt.Color(51, 51, 51));
        MenuRujukan.setMnemonic('P');
        MenuRujukan.setText("Rujukan");
        MenuRujukan.setToolTipText("Alt+P");
        MenuRujukan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuRujukan.setIconTextGap(8);
        MenuRujukan.setName("MenuRujukan"); // NOI18N
        MenuRujukan.setPreferredSize(new java.awt.Dimension(200, 22));

        MnRujukMasuk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRujukMasuk.setForeground(new java.awt.Color(51, 51, 51));
        MnRujukMasuk.setText("Rujukan Masuk");
        MnRujukMasuk.setEnabled(false);
        MnRujukMasuk.setName("MnRujukMasuk"); // NOI18N
        MnRujukMasuk.setPreferredSize(null);
        MnRujukMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukMasukActionPerformed(evt);
            }
        });
        MenuRujukan.add(MnRujukMasuk);

        MnRujukKeluar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRujukKeluar.setForeground(new java.awt.Color(51, 51, 51));
        MnRujukKeluar.setText("Rujukan Keluar");
        MnRujukKeluar.setEnabled(false);
        MnRujukKeluar.setName("MnRujukKeluar"); // NOI18N
        MnRujukKeluar.setPreferredSize(null);
        MnRujukKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukKeluarActionPerformed(evt);
            }
        });
        MenuRujukan.add(MnRujukKeluar);

        jMenuLayanan.add(MenuRujukan);

        MnInfoKamar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnInfoKamar.setForeground(new java.awt.Color(51, 51, 51));
        MnInfoKamar.setText("Informasi Kamar");
        MnInfoKamar.setEnabled(false);
        MnInfoKamar.setName("MnInfoKamar"); // NOI18N
        MnInfoKamar.setPreferredSize(new java.awt.Dimension(200, 22));
        MnInfoKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInfoKamarActionPerformed(evt);
            }
        });
        jMenuLayanan.add(MnInfoKamar);

        MnIgd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnIgd.setForeground(new java.awt.Color(51, 51, 51));
        MnIgd.setText("IGD / UGD");
        MnIgd.setEnabled(false);
        MnIgd.setName("MnIgd"); // NOI18N
        MnIgd.setPreferredSize(new java.awt.Dimension(200, 22));
        MnIgd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnIgdActionPerformed(evt);
            }
        });
        jMenuLayanan.add(MnIgd);

        MnJadwalDokter.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnJadwalDokter.setForeground(new java.awt.Color(51, 51, 51));
        MnJadwalDokter.setText("Jadwal Praktek Dokter");
        MnJadwalDokter.setEnabled(false);
        MnJadwalDokter.setName("MnJadwalDokter"); // NOI18N
        MnJadwalDokter.setPreferredSize(new java.awt.Dimension(200, 22));
        MnJadwalDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJadwalDokterActionPerformed(evt);
            }
        });
        jMenuLayanan.add(MnJadwalDokter);

        MnOperasi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnOperasi.setForeground(new java.awt.Color(51, 51, 51));
        MnOperasi.setText("Operasi / VK");
        MnOperasi.setEnabled(false);
        MnOperasi.setName("MnOperasi"); // NOI18N
        MnOperasi.setPreferredSize(new java.awt.Dimension(200, 22));
        MnOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnOperasiActionPerformed(evt);
            }
        });
        jMenuLayanan.add(MnOperasi);

        MenuObat.setBorder(null);
        MenuObat.setForeground(new java.awt.Color(51, 51, 51));
        MenuObat.setMnemonic('P');
        MenuObat.setText("Obat");
        MenuObat.setToolTipText("Alt+P");
        MenuObat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuObat.setIconTextGap(8);
        MenuObat.setName("MenuObat"); // NOI18N
        MenuObat.setPreferredSize(new java.awt.Dimension(200, 22));

        MnPemberianObat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPemberianObat.setForeground(new java.awt.Color(51, 51, 51));
        MnPemberianObat.setText("Pemberian Obat");
        MnPemberianObat.setEnabled(false);
        MnPemberianObat.setName("MnPemberianObat"); // NOI18N
        MnPemberianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemberianObatActionPerformed(evt);
            }
        });
        MenuObat.add(MnPemberianObat);

        MnResepObat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnResepObat.setForeground(new java.awt.Color(51, 51, 51));
        MnResepObat.setText("Resep Obat");
        MnResepObat.setEnabled(false);
        MnResepObat.setName("MnResepObat"); // NOI18N
        MnResepObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnResepObatActionPerformed(evt);
            }
        });
        MenuObat.add(MnResepObat);

        MnResepPulang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnResepPulang.setForeground(new java.awt.Color(51, 51, 51));
        MnResepPulang.setText("Resep Pulang");
        MnResepPulang.setEnabled(false);
        MnResepPulang.setName("MnResepPulang"); // NOI18N
        MnResepPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnResepPulangActionPerformed(evt);
            }
        });
        MenuObat.add(MnResepPulang);

        jMenuLayanan.add(MenuObat);

        MnDietPasien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDietPasien.setForeground(new java.awt.Color(51, 51, 51));
        MnDietPasien.setText("Diet Pasien");
        MnDietPasien.setEnabled(false);
        MnDietPasien.setName("MnDietPasien"); // NOI18N
        MnDietPasien.setPreferredSize(new java.awt.Dimension(200, 22));
        MnDietPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDietPasienActionPerformed(evt);
            }
        });
        jMenuLayanan.add(MnDietPasien);

        MnPeriksaLab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPeriksaLab.setForeground(new java.awt.Color(51, 51, 51));
        MnPeriksaLab.setText("Periksa Laboratorium");
        MnPeriksaLab.setEnabled(false);
        MnPeriksaLab.setName("MnPeriksaLab"); // NOI18N
        MnPeriksaLab.setPreferredSize(new java.awt.Dimension(200, 22));
        MnPeriksaLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaLabActionPerformed(evt);
            }
        });
        jMenuLayanan.add(MnPeriksaLab);

        MnPeriksaRad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPeriksaRad.setForeground(new java.awt.Color(51, 51, 51));
        MnPeriksaRad.setText("Periksa Radiologi");
        MnPeriksaRad.setEnabled(false);
        MnPeriksaRad.setName("MnPeriksaRad"); // NOI18N
        MnPeriksaRad.setPreferredSize(new java.awt.Dimension(200, 22));
        MnPeriksaRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaRadActionPerformed(evt);
            }
        });
        jMenuLayanan.add(MnPeriksaRad);

        MenuKasir.setBorder(null);
        MenuKasir.setForeground(new java.awt.Color(51, 51, 51));
        MenuKasir.setMnemonic('P');
        MenuKasir.setText("Kasir");
        MenuKasir.setToolTipText("Alt+P");
        MenuKasir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuKasir.setIconTextGap(8);
        MenuKasir.setName("MenuKasir"); // NOI18N
        MenuKasir.setPreferredSize(new java.awt.Dimension(200, 22));

        MnKasirRalan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnKasirRalan.setForeground(new java.awt.Color(51, 51, 51));
        MnKasirRalan.setText("Kasir Rawat Jalan");
        MnKasirRalan.setEnabled(false);
        MnKasirRalan.setName("MnKasirRalan"); // NOI18N
        MnKasirRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKasirRalanActionPerformed(evt);
            }
        });
        MenuKasir.add(MnKasirRalan);

        MnDepositPasien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDepositPasien.setForeground(new java.awt.Color(51, 51, 51));
        MnDepositPasien.setText("Deposit Pasien");
        MnDepositPasien.setEnabled(false);
        MnDepositPasien.setName("MnDepositPasien"); // NOI18N
        MnDepositPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDepositPasienActionPerformed(evt);
            }
        });
        MenuKasir.add(MnDepositPasien);

        MnPiutangPasien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPiutangPasien.setForeground(new java.awt.Color(51, 51, 51));
        MnPiutangPasien.setText("Piutang Pasien");
        MnPiutangPasien.setEnabled(false);
        MnPiutangPasien.setName("MnPiutangPasien"); // NOI18N
        MnPiutangPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPiutangPasienActionPerformed(evt);
            }
        });
        MenuKasir.add(MnPiutangPasien);

        jMenuLayanan.add(MenuKasir);

        MenuBar.add(jMenuLayanan);

        jMenuManajemen.setBorder(null);
        jMenuManajemen.setForeground(new java.awt.Color(255, 255, 255));
        jMenuManajemen.setMnemonic('N');
        jMenuManajemen.setText("  Manajemen");
        jMenuManajemen.setToolTipText("Alt+N");
        jMenuManajemen.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuManajemen.setName("jMenuManajemen"); // NOI18N
        jMenuManajemen.setOpaque(false);
        jMenuManajemen.setPreferredSize(new java.awt.Dimension(88, 30));

        MnDataDokter.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDataDokter.setForeground(new java.awt.Color(51, 51, 51));
        MnDataDokter.setText("Data Dokter");
        MnDataDokter.setEnabled(false);
        MnDataDokter.setName("MnDataDokter"); // NOI18N
        MnDataDokter.setPreferredSize(new java.awt.Dimension(200, 22));
        MnDataDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataDokterActionPerformed(evt);
            }
        });
        jMenuManajemen.add(MnDataDokter);

        MnDataPetugas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDataPetugas.setForeground(new java.awt.Color(51, 51, 51));
        MnDataPetugas.setText("Data Petugas");
        MnDataPetugas.setEnabled(false);
        MnDataPetugas.setName("MnDataPetugas"); // NOI18N
        MnDataPetugas.setPreferredSize(new java.awt.Dimension(200, 22));
        MnDataPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataPetugasActionPerformed(evt);
            }
        });
        jMenuManajemen.add(MnDataPetugas);

        MnDataPegawai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDataPegawai.setForeground(new java.awt.Color(51, 51, 51));
        MnDataPegawai.setText("Data Pegawai");
        MnDataPegawai.setEnabled(false);
        MnDataPegawai.setName("MnDataPegawai"); // NOI18N
        MnDataPegawai.setPreferredSize(new java.awt.Dimension(200, 22));
        MnDataPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataPegawaiActionPerformed(evt);
            }
        });
        jMenuManajemen.add(MnDataPegawai);

        MenuPresensi.setBorder(null);
        MenuPresensi.setForeground(new java.awt.Color(51, 51, 51));
        MenuPresensi.setMnemonic('P');
        MenuPresensi.setText("Presensi");
        MenuPresensi.setToolTipText("Alt+P");
        MenuPresensi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuPresensi.setIconTextGap(8);
        MenuPresensi.setName("MenuPresensi"); // NOI18N
        MenuPresensi.setPreferredSize(new java.awt.Dimension(200, 22));

        MnBarcodePresensi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnBarcodePresensi.setForeground(new java.awt.Color(51, 51, 51));
        MnBarcodePresensi.setText("Barcode Presensi");
        MnBarcodePresensi.setEnabled(false);
        MnBarcodePresensi.setName("MnBarcodePresensi"); // NOI18N
        MnBarcodePresensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodePresensiActionPerformed(evt);
            }
        });
        MenuPresensi.add(MnBarcodePresensi);

        MnJamPresensi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnJamPresensi.setForeground(new java.awt.Color(51, 51, 51));
        MnJamPresensi.setText("Jam Presensi");
        MnJamPresensi.setEnabled(false);
        MnJamPresensi.setName("MnJamPresensi"); // NOI18N
        MnJamPresensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJamPresensiActionPerformed(evt);
            }
        });
        MenuPresensi.add(MnJamPresensi);

        MnPresensiHarian.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPresensiHarian.setForeground(new java.awt.Color(51, 51, 51));
        MnPresensiHarian.setText("Presensi Harian");
        MnPresensiHarian.setEnabled(false);
        MnPresensiHarian.setName("MnPresensiHarian"); // NOI18N
        MnPresensiHarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPresensiHarianActionPerformed(evt);
            }
        });
        MenuPresensi.add(MnPresensiHarian);

        MnPresensiBulanan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPresensiBulanan.setForeground(new java.awt.Color(51, 51, 51));
        MnPresensiBulanan.setText("Presensi Bulanan");
        MnPresensiBulanan.setEnabled(false);
        MnPresensiBulanan.setName("MnPresensiBulanan"); // NOI18N
        MnPresensiBulanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPresensiBulananActionPerformed(evt);
            }
        });
        MenuPresensi.add(MnPresensiBulanan);

        MnTempPresensi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnTempPresensi.setForeground(new java.awt.Color(51, 51, 51));
        MnTempPresensi.setText("Temporary Presensi");
        MnTempPresensi.setEnabled(false);
        MnTempPresensi.setName("MnTempPresensi"); // NOI18N
        MnTempPresensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTempPresensiActionPerformed(evt);
            }
        });
        MenuPresensi.add(MnTempPresensi);

        MnRekapKehadiran.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRekapKehadiran.setForeground(new java.awt.Color(51, 51, 51));
        MnRekapKehadiran.setText("Rekap Kehadiran");
        MnRekapKehadiran.setEnabled(false);
        MnRekapKehadiran.setName("MnRekapKehadiran"); // NOI18N
        MnRekapKehadiran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapKehadiranActionPerformed(evt);
            }
        });
        MenuPresensi.add(MnRekapKehadiran);

        jMenuManajemen.add(MenuPresensi);

        MnSidikJari.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSidikJari.setForeground(new java.awt.Color(51, 51, 51));
        MnSidikJari.setText("Sidik Jari");
        MnSidikJari.setEnabled(false);
        MnSidikJari.setName("MnSidikJari"); // NOI18N
        MnSidikJari.setPreferredSize(new java.awt.Dimension(200, 22));
        MnSidikJari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSidikJariActionPerformed(evt);
            }
        });
        jMenuManajemen.add(MnSidikJari);

        MenuJadwal.setBorder(null);
        MenuJadwal.setForeground(new java.awt.Color(51, 51, 51));
        MenuJadwal.setMnemonic('P');
        MenuJadwal.setText("Jadwal");
        MenuJadwal.setToolTipText("Alt+P");
        MenuJadwal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuJadwal.setIconTextGap(8);
        MenuJadwal.setName("MenuJadwal"); // NOI18N
        MenuJadwal.setPreferredSize(new java.awt.Dimension(200, 22));

        MnJadwalPegawai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnJadwalPegawai.setForeground(new java.awt.Color(51, 51, 51));
        MnJadwalPegawai.setText("Jadwal Pegawai");
        MnJadwalPegawai.setEnabled(false);
        MnJadwalPegawai.setName("MnJadwalPegawai"); // NOI18N
        MnJadwalPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJadwalPegawaiActionPerformed(evt);
            }
        });
        MenuJadwal.add(MnJadwalPegawai);

        MnJdwlTambahan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnJdwlTambahan.setForeground(new java.awt.Color(51, 51, 51));
        MnJdwlTambahan.setText("Jadwal Tambahan");
        MnJdwlTambahan.setEnabled(false);
        MnJdwlTambahan.setName("MnJdwlTambahan"); // NOI18N
        MnJdwlTambahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJdwlTambahanActionPerformed(evt);
            }
        });
        MenuJadwal.add(MnJdwlTambahan);

        jMenuManajemen.add(MenuJadwal);

        MnPresensiHarian1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPresensiHarian1.setForeground(new java.awt.Color(51, 51, 51));
        MnPresensiHarian1.setText("Kepegawaian & Gaji");
        MnPresensiHarian1.setEnabled(false);
        MnPresensiHarian1.setName("MnPresensiHarian1"); // NOI18N
        MnPresensiHarian1.setPreferredSize(new java.awt.Dimension(200, 22));
        MnPresensiHarian1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPresensiHarian1ActionPerformed(evt);
            }
        });
        jMenuManajemen.add(MnPresensiHarian1);

        MenuBar.add(jMenuManajemen);

        jMenuFarmasi.setBorder(null);
        jMenuFarmasi.setForeground(new java.awt.Color(255, 255, 255));
        jMenuFarmasi.setMnemonic('N');
        jMenuFarmasi.setText("  Farmasi");
        jMenuFarmasi.setToolTipText("Alt+N");
        jMenuFarmasi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuFarmasi.setName("jMenuFarmasi"); // NOI18N
        jMenuFarmasi.setOpaque(false);
        jMenuFarmasi.setPreferredSize(new java.awt.Dimension(72, 30));

        MenuSuplier.setBorder(null);
        MenuSuplier.setForeground(new java.awt.Color(51, 51, 51));
        MenuSuplier.setMnemonic('P');
        MenuSuplier.setText("Suplier");
        MenuSuplier.setToolTipText("Alt+P");
        MenuSuplier.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuSuplier.setIconTextGap(8);
        MenuSuplier.setName("MenuSuplier"); // NOI18N
        MenuSuplier.setPreferredSize(new java.awt.Dimension(200, 22));

        MnIndustriFar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnIndustriFar.setForeground(new java.awt.Color(51, 51, 51));
        MnIndustriFar.setText("Industri Farmasi");
        MnIndustriFar.setEnabled(false);
        MnIndustriFar.setName("MnIndustriFar"); // NOI18N
        MnIndustriFar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnIndustriFarActionPerformed(evt);
            }
        });
        MenuSuplier.add(MnIndustriFar);

        MnSuplier.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSuplier.setForeground(new java.awt.Color(51, 51, 51));
        MnSuplier.setText("Suplier Obat/Alkes/BHP");
        MnSuplier.setEnabled(false);
        MnSuplier.setName("MnSuplier"); // NOI18N
        MnSuplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuplierActionPerformed(evt);
            }
        });
        MenuSuplier.add(MnSuplier);

        jMenuFarmasi.add(MenuSuplier);

        MnSatuanBrg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSatuanBrg.setForeground(new java.awt.Color(51, 51, 51));
        MnSatuanBrg.setText("Satuan Barang");
        MnSatuanBrg.setEnabled(false);
        MnSatuanBrg.setName("MnSatuanBrg"); // NOI18N
        MnSatuanBrg.setPreferredSize(new java.awt.Dimension(200, 22));
        MnSatuanBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSatuanBrgActionPerformed(evt);
            }
        });
        jMenuFarmasi.add(MnSatuanBrg);

        MnKonvSat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnKonvSat.setForeground(new java.awt.Color(51, 51, 51));
        MnKonvSat.setText("Konversi Satuan");
        MnKonvSat.setEnabled(false);
        MnKonvSat.setName("MnKonvSat"); // NOI18N
        MnKonvSat.setPreferredSize(new java.awt.Dimension(200, 22));
        MnKonvSat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKonvSatActionPerformed(evt);
            }
        });
        jMenuFarmasi.add(MnKonvSat);

        MenuInvObat.setBorder(null);
        MenuInvObat.setForeground(new java.awt.Color(51, 51, 51));
        MenuInvObat.setMnemonic('P');
        MenuInvObat.setText("Inventory Obat");
        MenuInvObat.setToolTipText("Alt+P");
        MenuInvObat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuInvObat.setIconTextGap(8);
        MenuInvObat.setName("MenuInvObat"); // NOI18N
        MenuInvObat.setPreferredSize(new java.awt.Dimension(200, 22));

        MnJnsObt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnJnsObt.setForeground(new java.awt.Color(51, 51, 51));
        MnJnsObt.setText("Jenis Obat/Alkes/BHP");
        MnJnsObt.setEnabled(false);
        MnJnsObt.setName("MnJnsObt"); // NOI18N
        MnJnsObt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJnsObtActionPerformed(evt);
            }
        });
        MenuInvObat.add(MnJnsObt);

        MnDataObat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDataObat.setForeground(new java.awt.Color(51, 51, 51));
        MnDataObat.setText("Data Obat/Alkes/BHP");
        MnDataObat.setEnabled(false);
        MnDataObat.setName("MnDataObat"); // NOI18N
        MnDataObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataObatActionPerformed(evt);
            }
        });
        MenuInvObat.add(MnDataObat);

        MnStokOpname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnStokOpname.setForeground(new java.awt.Color(51, 51, 51));
        MnStokOpname.setText("Stok Opname Obat & BHP");
        MnStokOpname.setEnabled(false);
        MnStokOpname.setName("MnStokOpname"); // NOI18N
        MnStokOpname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStokOpnameActionPerformed(evt);
            }
        });
        MenuInvObat.add(MnStokOpname);

        MnMutasiObat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnMutasiObat.setForeground(new java.awt.Color(51, 51, 51));
        MnMutasiObat.setText("Mutasi Obat & BHP");
        MnMutasiObat.setEnabled(false);
        MnMutasiObat.setName("MnMutasiObat"); // NOI18N
        MnMutasiObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnMutasiObatActionPerformed(evt);
            }
        });
        MenuInvObat.add(MnMutasiObat);

        MnStokObtPx.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnStokObtPx.setForeground(new java.awt.Color(51, 51, 51));
        MnStokObtPx.setText("Stok Obat Pasien");
        MnStokObtPx.setEnabled(false);
        MnStokObtPx.setName("MnStokObtPx"); // NOI18N
        MnStokObtPx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStokObtPxActionPerformed(evt);
            }
        });
        MenuInvObat.add(MnStokObtPx);

        MnPengadaan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPengadaan.setForeground(new java.awt.Color(51, 51, 51));
        MnPengadaan.setText("Pengadaan Obat & BHP");
        MnPengadaan.setEnabled(false);
        MnPengadaan.setName("MnPengadaan"); // NOI18N
        MnPengadaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPengadaanActionPerformed(evt);
            }
        });
        MenuInvObat.add(MnPengadaan);

        MnPemesanan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPemesanan.setForeground(new java.awt.Color(51, 51, 51));
        MnPemesanan.setText("Pemesanan Obat & BHP");
        MnPemesanan.setEnabled(false);
        MnPemesanan.setName("MnPemesanan"); // NOI18N
        MnPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemesananActionPerformed(evt);
            }
        });
        MenuInvObat.add(MnPemesanan);

        MnPenjualanObt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPenjualanObt.setForeground(new java.awt.Color(51, 51, 51));
        MnPenjualanObt.setText("Penjualan Obat & BHP");
        MnPenjualanObt.setEnabled(false);
        MnPenjualanObt.setName("MnPenjualanObt"); // NOI18N
        MnPenjualanObt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenjualanObtActionPerformed(evt);
            }
        });
        MenuInvObat.add(MnPenjualanObt);

        MnStokOpname1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnStokOpname1.setForeground(new java.awt.Color(51, 51, 51));
        MnStokOpname1.setText("Piutang Obat & BHP");
        MnStokOpname1.setEnabled(false);
        MnStokOpname1.setName("MnStokOpname1"); // NOI18N
        MnStokOpname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStokOpname1ActionPerformed(evt);
            }
        });
        MenuInvObat.add(MnStokOpname1);

        jMenuFarmasi.add(MenuInvObat);

        MenuRetur.setBorder(null);
        MenuRetur.setForeground(new java.awt.Color(51, 51, 51));
        MenuRetur.setMnemonic('P');
        MenuRetur.setText("Retur");
        MenuRetur.setToolTipText("Alt+P");
        MenuRetur.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuRetur.setIconTextGap(8);
        MenuRetur.setName("MenuRetur"); // NOI18N
        MenuRetur.setPreferredSize(new java.awt.Dimension(200, 22));

        MnReturKeSup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnReturKeSup.setForeground(new java.awt.Color(51, 51, 51));
        MnReturKeSup.setText("Retur Ke Suplier");
        MnReturKeSup.setEnabled(false);
        MnReturKeSup.setName("MnReturKeSup"); // NOI18N
        MnReturKeSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnReturKeSupActionPerformed(evt);
            }
        });
        MenuRetur.add(MnReturKeSup);

        MnReturPemb.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnReturPemb.setForeground(new java.awt.Color(51, 51, 51));
        MnReturPemb.setText("Retur Dari Pembeli");
        MnReturPemb.setEnabled(false);
        MnReturPemb.setName("MnReturPemb"); // NOI18N
        MnReturPemb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnReturPembActionPerformed(evt);
            }
        });
        MenuRetur.add(MnReturPemb);

        MnReturObtRanap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnReturObtRanap.setForeground(new java.awt.Color(51, 51, 51));
        MnReturObtRanap.setText("Retur Obat Ranap");
        MnReturObtRanap.setEnabled(false);
        MnReturObtRanap.setName("MnReturObtRanap"); // NOI18N
        MnReturObtRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnReturObtRanapActionPerformed(evt);
            }
        });
        MenuRetur.add(MnReturObtRanap);

        MnReturPiutangPemb.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnReturPiutangPemb.setForeground(new java.awt.Color(51, 51, 51));
        MnReturPiutangPemb.setText("Retur Piutang Pembeli");
        MnReturPiutangPemb.setEnabled(false);
        MnReturPiutangPemb.setName("MnReturPiutangPemb"); // NOI18N
        MnReturPiutangPemb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnReturPiutangPembActionPerformed(evt);
            }
        });
        MenuRetur.add(MnReturPiutangPemb);

        jMenuFarmasi.add(MenuRetur);

        MenuKeuntungan.setBorder(null);
        MenuKeuntungan.setForeground(new java.awt.Color(51, 51, 51));
        MenuKeuntungan.setMnemonic('P');
        MenuKeuntungan.setText("Keuntungan");
        MenuKeuntungan.setToolTipText("Alt+P");
        MenuKeuntungan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuKeuntungan.setIconTextGap(8);
        MenuKeuntungan.setName("MenuKeuntungan"); // NOI18N
        MenuKeuntungan.setPreferredSize(new java.awt.Dimension(200, 22));

        MnKeuntunganPenj.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnKeuntunganPenj.setForeground(new java.awt.Color(51, 51, 51));
        MnKeuntunganPenj.setText("Keuntungan Penjualan");
        MnKeuntunganPenj.setEnabled(false);
        MnKeuntunganPenj.setName("MnKeuntunganPenj"); // NOI18N
        MnKeuntunganPenj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKeuntunganPenjActionPerformed(evt);
            }
        });
        MenuKeuntungan.add(MnKeuntunganPenj);

        MnKeuntBeriObt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnKeuntBeriObt.setForeground(new java.awt.Color(51, 51, 51));
        MnKeuntBeriObt.setText("Keuntungan Beri Obat");
        MnKeuntBeriObt.setEnabled(false);
        MnKeuntBeriObt.setName("MnKeuntBeriObt"); // NOI18N
        MnKeuntBeriObt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKeuntBeriObtActionPerformed(evt);
            }
        });
        MenuKeuntungan.add(MnKeuntBeriObt);

        jMenuFarmasi.add(MenuKeuntungan);

        MnSirkulasiObt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSirkulasiObt.setForeground(new java.awt.Color(51, 51, 51));
        MnSirkulasiObt.setText("Sirkulasi Obat, Alkes & BHP");
        MnSirkulasiObt.setEnabled(false);
        MnSirkulasiObt.setName("MnSirkulasiObt"); // NOI18N
        MnSirkulasiObt.setPreferredSize(new java.awt.Dimension(200, 22));
        MnSirkulasiObt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSirkulasiObtActionPerformed(evt);
            }
        });
        jMenuFarmasi.add(MnSirkulasiObt);

        MnRiwayat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRiwayat.setForeground(new java.awt.Color(51, 51, 51));
        MnRiwayat.setText("Riwayat Obat, Alkes & BHP");
        MnRiwayat.setEnabled(false);
        MnRiwayat.setName("MnRiwayat"); // NOI18N
        MnRiwayat.setPreferredSize(new java.awt.Dimension(200, 22));
        MnRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatActionPerformed(evt);
            }
        });
        jMenuFarmasi.add(MnRiwayat);

        MnDaruratStok.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDaruratStok.setForeground(new java.awt.Color(51, 51, 51));
        MnDaruratStok.setText("Darurat Stok");
        MnDaruratStok.setEnabled(false);
        MnDaruratStok.setName("MnDaruratStok"); // NOI18N
        MnDaruratStok.setPreferredSize(new java.awt.Dimension(200, 22));
        MnDaruratStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDaruratStokActionPerformed(evt);
            }
        });
        jMenuFarmasi.add(MnDaruratStok);

        MenuBar.add(jMenuFarmasi);

        jMenuInventory.setBorder(null);
        jMenuInventory.setForeground(new java.awt.Color(255, 255, 255));
        jMenuInventory.setMnemonic('N');
        jMenuInventory.setText("  Inventory");
        jMenuInventory.setToolTipText("Alt+N");
        jMenuInventory.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuInventory.setName("jMenuInventory"); // NOI18N
        jMenuInventory.setOpaque(false);
        jMenuInventory.setPreferredSize(new java.awt.Dimension(74, 30));

        MnSatuanBrgNon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSatuanBrgNon.setForeground(new java.awt.Color(51, 51, 51));
        MnSatuanBrgNon.setText("Satuan Barang");
        MnSatuanBrgNon.setEnabled(false);
        MnSatuanBrgNon.setName("MnSatuanBrgNon"); // NOI18N
        MnSatuanBrgNon.setPreferredSize(new java.awt.Dimension(200, 22));
        MnSatuanBrgNon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSatuanBrgNonActionPerformed(evt);
            }
        });
        jMenuInventory.add(MnSatuanBrgNon);

        MnJnsBrgNon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnJnsBrgNon.setForeground(new java.awt.Color(51, 51, 51));
        MnJnsBrgNon.setText("Jenis Barang");
        MnJnsBrgNon.setEnabled(false);
        MnJnsBrgNon.setName("MnJnsBrgNon"); // NOI18N
        MnJnsBrgNon.setPreferredSize(new java.awt.Dimension(200, 22));
        MnJnsBrgNon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJnsBrgNonActionPerformed(evt);
            }
        });
        jMenuInventory.add(MnJnsBrgNon);

        MnDataBrgNon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDataBrgNon.setForeground(new java.awt.Color(51, 51, 51));
        MnDataBrgNon.setText("Data Barang");
        MnDataBrgNon.setEnabled(false);
        MnDataBrgNon.setName("MnDataBrgNon"); // NOI18N
        MnDataBrgNon.setPreferredSize(new java.awt.Dimension(200, 22));
        MnDataBrgNon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataBrgNonActionPerformed(evt);
            }
        });
        jMenuInventory.add(MnDataBrgNon);

        MnSupNon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSupNon.setForeground(new java.awt.Color(51, 51, 51));
        MnSupNon.setText("Suplier Non Medis");
        MnSupNon.setEnabled(false);
        MnSupNon.setName("MnSupNon"); // NOI18N
        MnSupNon.setPreferredSize(new java.awt.Dimension(200, 22));
        MnSupNon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSupNonActionPerformed(evt);
            }
        });
        jMenuInventory.add(MnSupNon);

        MnPengadaanbrg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPengadaanbrg.setForeground(new java.awt.Color(51, 51, 51));
        MnPengadaanbrg.setText("Pengadaan Barang");
        MnPengadaanbrg.setEnabled(false);
        MnPengadaanbrg.setName("MnPengadaanbrg"); // NOI18N
        MnPengadaanbrg.setPreferredSize(new java.awt.Dimension(200, 22));
        MnPengadaanbrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPengadaanbrgActionPerformed(evt);
            }
        });
        jMenuInventory.add(MnPengadaanbrg);

        MnStokKeluar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnStokKeluar.setForeground(new java.awt.Color(51, 51, 51));
        MnStokKeluar.setText("Stok Keluar");
        MnStokKeluar.setEnabled(false);
        MnStokKeluar.setName("MnStokKeluar"); // NOI18N
        MnStokKeluar.setPreferredSize(new java.awt.Dimension(200, 22));
        MnStokKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStokKeluarActionPerformed(evt);
            }
        });
        jMenuInventory.add(MnStokKeluar);

        MnBiayaPengadaan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnBiayaPengadaan.setForeground(new java.awt.Color(51, 51, 51));
        MnBiayaPengadaan.setText("Biaya Pengadaan");
        MnBiayaPengadaan.setEnabled(false);
        MnBiayaPengadaan.setName("MnBiayaPengadaan"); // NOI18N
        MnBiayaPengadaan.setPreferredSize(new java.awt.Dimension(200, 22));
        MnBiayaPengadaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBiayaPengadaanActionPerformed(evt);
            }
        });
        jMenuInventory.add(MnBiayaPengadaan);

        MenuRekap.setBorder(null);
        MenuRekap.setForeground(new java.awt.Color(51, 51, 51));
        MenuRekap.setMnemonic('P');
        MenuRekap.setText("Rekap");
        MenuRekap.setToolTipText("Alt+P");
        MenuRekap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuRekap.setIconTextGap(8);
        MenuRekap.setName("MenuRekap"); // NOI18N
        MenuRekap.setPreferredSize(new java.awt.Dimension(200, 22));

        MnRekapPengadaan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRekapPengadaan.setForeground(new java.awt.Color(51, 51, 51));
        MnRekapPengadaan.setText("Rekap Pengadaan");
        MnRekapPengadaan.setEnabled(false);
        MnRekapPengadaan.setName("MnRekapPengadaan"); // NOI18N
        MnRekapPengadaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapPengadaanActionPerformed(evt);
            }
        });
        MenuRekap.add(MnRekapPengadaan);

        MnRekapStok.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRekapStok.setForeground(new java.awt.Color(51, 51, 51));
        MnRekapStok.setText("Rekap Stok Keluar");
        MnRekapStok.setEnabled(false);
        MnRekapStok.setName("MnRekapStok"); // NOI18N
        MnRekapStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapStokActionPerformed(evt);
            }
        });
        MenuRekap.add(MnRekapStok);

        jMenuInventory.add(MenuRekap);

        MenuBar.add(jMenuInventory);

        jMenuBridging.setBorder(null);
        jMenuBridging.setForeground(new java.awt.Color(255, 255, 255));
        jMenuBridging.setMnemonic('N');
        jMenuBridging.setText("  Bridging");
        jMenuBridging.setToolTipText("Alt+N");
        jMenuBridging.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuBridging.setName("jMenuBridging"); // NOI18N
        jMenuBridging.setOpaque(false);
        jMenuBridging.setPreferredSize(new java.awt.Dimension(68, 30));

        MnCekSKDPBPJS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnCekSKDPBPJS.setForeground(new java.awt.Color(51, 51, 51));
        MnCekSKDPBPJS.setText("Cek SKDP BPJS");
        MnCekSKDPBPJS.setEnabled(false);
        MnCekSKDPBPJS.setName("MnCekSKDPBPJS"); // NOI18N
        MnCekSKDPBPJS.setPreferredSize(new java.awt.Dimension(200, 22));
        MnCekSKDPBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekSKDPBPJSActionPerformed(evt);
            }
        });
        jMenuBridging.add(MnCekSKDPBPJS);

        MnRiwPesBpjs.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRiwPesBpjs.setForeground(new java.awt.Color(51, 51, 51));
        MnRiwPesBpjs.setText("Riwayat Rujukan PCare");
        MnRiwPesBpjs.setEnabled(false);
        MnRiwPesBpjs.setName("MnRiwPesBpjs"); // NOI18N
        MnRiwPesBpjs.setPreferredSize(new java.awt.Dimension(200, 22));
        MnRiwPesBpjs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwPesBpjsActionPerformed(evt);
            }
        });
        jMenuBridging.add(MnRiwPesBpjs);

        MnRiwPesBpjs1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRiwPesBpjs1.setForeground(new java.awt.Color(51, 51, 51));
        MnRiwPesBpjs1.setText("Riwayat Rujukan RS");
        MnRiwPesBpjs1.setEnabled(false);
        MnRiwPesBpjs1.setName("MnRiwPesBpjs1"); // NOI18N
        MnRiwPesBpjs1.setPreferredSize(new java.awt.Dimension(200, 22));
        MnRiwPesBpjs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwPesBpjs1ActionPerformed(evt);
            }
        });
        jMenuBridging.add(MnRiwPesBpjs1);

        MnCekTglRujukan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnCekTglRujukan.setForeground(new java.awt.Color(51, 51, 51));
        MnCekTglRujukan.setText("Cek Tanggal Rujukan");
        MnCekTglRujukan.setEnabled(false);
        MnCekTglRujukan.setName("MnCekTglRujukan"); // NOI18N
        MnCekTglRujukan.setPreferredSize(new java.awt.Dimension(200, 22));
        MnCekTglRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekTglRujukanActionPerformed(evt);
            }
        });
        jMenuBridging.add(MnCekTglRujukan);

        MnCekNoRujPCare.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnCekNoRujPCare.setForeground(new java.awt.Color(51, 51, 51));
        MnCekNoRujPCare.setText("Cek No. Rujukan PCare");
        MnCekNoRujPCare.setEnabled(false);
        MnCekNoRujPCare.setName("MnCekNoRujPCare"); // NOI18N
        MnCekNoRujPCare.setPreferredSize(new java.awt.Dimension(200, 22));
        MnCekNoRujPCare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekNoRujPCareActionPerformed(evt);
            }
        });
        jMenuBridging.add(MnCekNoRujPCare);

        MnCekNoRujRS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnCekNoRujRS.setForeground(new java.awt.Color(51, 51, 51));
        MnCekNoRujRS.setText("Cek No. Rujukan RS");
        MnCekNoRujRS.setEnabled(false);
        MnCekNoRujRS.setName("MnCekNoRujRS"); // NOI18N
        MnCekNoRujRS.setPreferredSize(new java.awt.Dimension(200, 22));
        MnCekNoRujRS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekNoRujRSActionPerformed(evt);
            }
        });
        jMenuBridging.add(MnCekNoRujRS);

        MnCekRujKartuPCare.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnCekRujKartuPCare.setForeground(new java.awt.Color(51, 51, 51));
        MnCekRujKartuPCare.setText("Cek Rujukan Kartu PCare");
        MnCekRujKartuPCare.setEnabled(false);
        MnCekRujKartuPCare.setName("MnCekRujKartuPCare"); // NOI18N
        MnCekRujKartuPCare.setPreferredSize(new java.awt.Dimension(200, 22));
        MnCekRujKartuPCare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekRujKartuPCareActionPerformed(evt);
            }
        });
        jMenuBridging.add(MnCekRujKartuPCare);

        MnCekRujKartuRS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnCekRujKartuRS.setForeground(new java.awt.Color(51, 51, 51));
        MnCekRujKartuRS.setText("Cek Rujukan Kartu RS");
        MnCekRujKartuRS.setEnabled(false);
        MnCekRujKartuRS.setName("MnCekRujKartuRS"); // NOI18N
        MnCekRujKartuRS.setPreferredSize(new java.awt.Dimension(200, 22));
        MnCekRujKartuRS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekRujKartuRSActionPerformed(evt);
            }
        });
        jMenuBridging.add(MnCekRujKartuRS);

        MnRefDiagBpjs.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRefDiagBpjs.setForeground(new java.awt.Color(51, 51, 51));
        MnRefDiagBpjs.setText("Referensi Diagnosa BPJS");
        MnRefDiagBpjs.setEnabled(false);
        MnRefDiagBpjs.setName("MnRefDiagBpjs"); // NOI18N
        MnRefDiagBpjs.setPreferredSize(new java.awt.Dimension(200, 22));
        MnRefDiagBpjs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRefDiagBpjsActionPerformed(evt);
            }
        });
        jMenuBridging.add(MnRefDiagBpjs);

        MnRefPlBpjs.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRefPlBpjs.setForeground(new java.awt.Color(51, 51, 51));
        MnRefPlBpjs.setText("Referensi Poli Bpjs");
        MnRefPlBpjs.setEnabled(false);
        MnRefPlBpjs.setName("MnRefPlBpjs"); // NOI18N
        MnRefPlBpjs.setPreferredSize(new java.awt.Dimension(200, 22));
        MnRefPlBpjs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRefPlBpjsActionPerformed(evt);
            }
        });
        jMenuBridging.add(MnRefPlBpjs);

        MnRefFaskes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRefFaskes.setForeground(new java.awt.Color(51, 51, 51));
        MnRefFaskes.setText("Referensi Faskes BPJS");
        MnRefFaskes.setEnabled(false);
        MnRefFaskes.setName("MnRefFaskes"); // NOI18N
        MnRefFaskes.setPreferredSize(new java.awt.Dimension(200, 22));
        MnRefFaskes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRefFaskesActionPerformed(evt);
            }
        });
        jMenuBridging.add(MnRefFaskes);

        MnBridging.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnBridging.setForeground(new java.awt.Color(51, 51, 51));
        MnBridging.setText("Data Bridging BPJS");
        MnBridging.setEnabled(false);
        MnBridging.setName("MnBridging"); // NOI18N
        MnBridging.setPreferredSize(new java.awt.Dimension(200, 22));
        MnBridging.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBridgingActionPerformed(evt);
            }
        });
        jMenuBridging.add(MnBridging);

        MnMonitoringKlaim.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnMonitoringKlaim.setForeground(new java.awt.Color(51, 51, 51));
        MnMonitoringKlaim.setText("Monitoring Verifikasi Klaim BPJS");
        MnMonitoringKlaim.setEnabled(false);
        MnMonitoringKlaim.setName("MnMonitoringKlaim"); // NOI18N
        MnMonitoringKlaim.setPreferredSize(new java.awt.Dimension(200, 22));
        MnMonitoringKlaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnMonitoringKlaimActionPerformed(evt);
            }
        });
        jMenuBridging.add(MnMonitoringKlaim);

        MenuBar.add(jMenuBridging);

        jMenuLaporan.setBorder(null);
        jMenuLaporan.setForeground(new java.awt.Color(255, 255, 255));
        jMenuLaporan.setMnemonic('N');
        jMenuLaporan.setText("  Laporan");
        jMenuLaporan.setToolTipText("Alt+N");
        jMenuLaporan.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuLaporan.setName("jMenuLaporan"); // NOI18N
        jMenuLaporan.setOpaque(false);
        jMenuLaporan.setPreferredSize(new java.awt.Dimension(67, 30));

        MenuLapObat.setBorder(null);
        MenuLapObat.setForeground(new java.awt.Color(51, 51, 51));
        MenuLapObat.setMnemonic('P');
        MenuLapObat.setText("Obat");
        MenuLapObat.setToolTipText("Alt+P");
        MenuLapObat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuLapObat.setIconTextGap(8);
        MenuLapObat.setName("MenuLapObat"); // NOI18N
        MenuLapObat.setPreferredSize(new java.awt.Dimension(200, 22));

        MnLapObtPoli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLapObtPoli.setForeground(new java.awt.Color(51, 51, 51));
        MnLapObtPoli.setText("Obat Per Poli");
        MnLapObtPoli.setEnabled(false);
        MnLapObtPoli.setName("MnLapObtPoli"); // NOI18N
        MnLapObtPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapObtPoliActionPerformed(evt);
            }
        });
        MenuLapObat.add(MnLapObtPoli);

        MnObtKmr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnObtKmr.setForeground(new java.awt.Color(51, 51, 51));
        MnObtKmr.setText("Obat Per Kamar");
        MnObtKmr.setEnabled(false);
        MnObtKmr.setName("MnObtKmr"); // NOI18N
        MnObtKmr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnObtKmrActionPerformed(evt);
            }
        });
        MenuLapObat.add(MnObtKmr);

        MnObtDokRln.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnObtDokRln.setForeground(new java.awt.Color(51, 51, 51));
        MnObtDokRln.setText("Obar Per Dokter Ralan");
        MnObtDokRln.setEnabled(false);
        MnObtDokRln.setName("MnObtDokRln"); // NOI18N
        MnObtDokRln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnObtDokRlnActionPerformed(evt);
            }
        });
        MenuLapObat.add(MnObtDokRln);

        MnObtDokRnp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnObtDokRnp.setForeground(new java.awt.Color(51, 51, 51));
        MnObtDokRnp.setText("Obat Per Dokter Ranap");
        MnObtDokRnp.setEnabled(false);
        MnObtDokRnp.setName("MnObtDokRnp"); // NOI18N
        MnObtDokRnp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnObtDokRnpActionPerformed(evt);
            }
        });
        MenuLapObat.add(MnObtDokRnp);

        MnObtDokRsp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnObtDokRsp.setForeground(new java.awt.Color(51, 51, 51));
        MnObtDokRsp.setText("Obat Per Dokter Peresep");
        MnObtDokRsp.setEnabled(false);
        MnObtDokRsp.setName("MnObtDokRsp"); // NOI18N
        MnObtDokRsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnObtDokRspActionPerformed(evt);
            }
        });
        MenuLapObat.add(MnObtDokRsp);

        MnObtCrByr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnObtCrByr.setForeground(new java.awt.Color(51, 51, 51));
        MnObtCrByr.setText("Obar Per Cara Bayar");
        MnObtCrByr.setEnabled(false);
        MnObtCrByr.setName("MnObtCrByr"); // NOI18N
        MnObtCrByr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnObtCrByrActionPerformed(evt);
            }
        });
        MenuLapObat.add(MnObtCrByr);

        jMenuLaporan.add(MenuLapObat);

        MnDetJMDok.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDetJMDok.setForeground(new java.awt.Color(51, 51, 51));
        MnDetJMDok.setText("Detail JM Dokter");
        MnDetJMDok.setEnabled(false);
        MnDetJMDok.setName("MnDetJMDok"); // NOI18N
        MnDetJMDok.setPreferredSize(new java.awt.Dimension(200, 22));
        MnDetJMDok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDetJMDokActionPerformed(evt);
            }
        });
        jMenuLaporan.add(MnDetJMDok);

        MenuLapHarian.setBorder(null);
        MenuLapHarian.setForeground(new java.awt.Color(51, 51, 51));
        MenuLapHarian.setMnemonic('P');
        MenuLapHarian.setText("Harian");
        MenuLapHarian.setToolTipText("Alt+P");
        MenuLapHarian.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuLapHarian.setIconTextGap(8);
        MenuLapHarian.setName("MenuLapHarian"); // NOI18N
        MenuLapHarian.setPreferredSize(new java.awt.Dimension(200, 22));

        MnHrDokAll.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnHrDokAll.setForeground(new java.awt.Color(51, 51, 51));
        MnHrDokAll.setText("Harian Dokter");
        MnHrDokAll.setEnabled(false);
        MnHrDokAll.setName("MnHrDokAll"); // NOI18N
        MnHrDokAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHrDokAllActionPerformed(evt);
            }
        });
        MenuLapHarian.add(MnHrDokAll);

        MnHrDokRalan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnHrDokRalan.setForeground(new java.awt.Color(51, 51, 51));
        MnHrDokRalan.setText("Harian Dokter Ralan");
        MnHrDokRalan.setEnabled(false);
        MnHrDokRalan.setName("MnHrDokRalan"); // NOI18N
        MnHrDokRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHrDokRalanActionPerformed(evt);
            }
        });
        MenuLapHarian.add(MnHrDokRalan);

        MnHrDok.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnHrDok.setForeground(new java.awt.Color(51, 51, 51));
        MnHrDok.setText("Harian Dokter/Poli");
        MnHrDok.setEnabled(false);
        MnHrDok.setName("MnHrDok"); // NOI18N
        MnHrDok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHrDokActionPerformed(evt);
            }
        });
        MenuLapHarian.add(MnHrDok);

        MnHrKamar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnHrKamar.setForeground(new java.awt.Color(51, 51, 51));
        MnHrKamar.setText("Harian Kamar");
        MnHrKamar.setEnabled(false);
        MnHrKamar.setName("MnHrKamar"); // NOI18N
        MnHrKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHrKamarActionPerformed(evt);
            }
        });
        MenuLapHarian.add(MnHrKamar);

        MnHrBhp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnHrBhp.setForeground(new java.awt.Color(51, 51, 51));
        MnHrBhp.setText("Harian BHP Medis/Paket Obat");
        MnHrBhp.setEnabled(false);
        MnHrBhp.setName("MnHrBhp"); // NOI18N
        MnHrBhp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHrBhpActionPerformed(evt);
            }
        });
        MenuLapHarian.add(MnHrBhp);

        MnHrParamedis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnHrParamedis.setForeground(new java.awt.Color(51, 51, 51));
        MnHrParamedis.setText("Harian Paramedis");
        MnHrParamedis.setEnabled(false);
        MnHrParamedis.setName("MnHrParamedis"); // NOI18N
        MnHrParamedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHrParamedisActionPerformed(evt);
            }
        });
        MenuLapHarian.add(MnHrParamedis);

        MnHrMnj.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnHrMnj.setForeground(new java.awt.Color(51, 51, 51));
        MnHrMnj.setText("Harian Manajemen");
        MnHrMnj.setEnabled(false);
        MnHrMnj.setName("MnHrMnj"); // NOI18N
        MnHrMnj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHrMnjActionPerformed(evt);
            }
        });
        MenuLapHarian.add(MnHrMnj);

        MnHrKso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnHrKso.setForeground(new java.awt.Color(51, 51, 51));
        MnHrKso.setText("Harian KSO");
        MnHrKso.setEnabled(false);
        MnHrKso.setName("MnHrKso"); // NOI18N
        MnHrKso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHrKsoActionPerformed(evt);
            }
        });
        MenuLapHarian.add(MnHrKso);

        MnHrSrn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnHrSrn.setForeground(new java.awt.Color(51, 51, 51));
        MnHrSrn.setText("Harian Jasa Sarana");
        MnHrSrn.setEnabled(false);
        MnHrSrn.setName("MnHrSrn"); // NOI18N
        MnHrSrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHrSrnActionPerformed(evt);
            }
        });
        MenuLapHarian.add(MnHrSrn);

        jMenuLaporan.add(MenuLapHarian);

        MenuLapBulanan.setBorder(null);
        MenuLapBulanan.setForeground(new java.awt.Color(51, 51, 51));
        MenuLapBulanan.setMnemonic('P');
        MenuLapBulanan.setText("Bulanan");
        MenuLapBulanan.setToolTipText("Alt+P");
        MenuLapBulanan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuLapBulanan.setIconTextGap(8);
        MenuLapBulanan.setName("MenuLapBulanan"); // NOI18N
        MenuLapBulanan.setPreferredSize(new java.awt.Dimension(200, 22));

        MnBulananDok.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnBulananDok.setForeground(new java.awt.Color(51, 51, 51));
        MnBulananDok.setText("Bulanan Dokter");
        MnBulananDok.setEnabled(false);
        MnBulananDok.setName("MnBulananDok"); // NOI18N
        MnBulananDok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBulananDokActionPerformed(evt);
            }
        });
        MenuLapBulanan.add(MnBulananDok);

        MnBlnParamedis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnBlnParamedis.setForeground(new java.awt.Color(51, 51, 51));
        MnBlnParamedis.setText("Bulanan Paramedis");
        MnBlnParamedis.setEnabled(false);
        MnBlnParamedis.setName("MnBlnParamedis"); // NOI18N
        MnBlnParamedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBlnParamedisActionPerformed(evt);
            }
        });
        MenuLapBulanan.add(MnBlnParamedis);

        MnBlnSrn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnBlnSrn.setForeground(new java.awt.Color(51, 51, 51));
        MnBlnSrn.setText("Bulanan Jasa Sarana");
        MnBlnSrn.setEnabled(false);
        MnBlnSrn.setName("MnBlnSrn"); // NOI18N
        MnBlnSrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBlnSrnActionPerformed(evt);
            }
        });
        MenuLapBulanan.add(MnBlnSrn);

        MnBlnKso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnBlnKso.setForeground(new java.awt.Color(51, 51, 51));
        MnBlnKso.setText("Bulanan KSO");
        MnBlnKso.setEnabled(false);
        MnBlnKso.setName("MnBlnKso"); // NOI18N
        MnBlnKso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBlnKsoActionPerformed(evt);
            }
        });
        MenuLapBulanan.add(MnBlnKso);

        MnBlnMnj.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnBlnMnj.setForeground(new java.awt.Color(51, 51, 51));
        MnBlnMnj.setText("Bulanan Manajemen");
        MnBlnMnj.setEnabled(false);
        MnBlnMnj.setName("MnBlnMnj"); // NOI18N
        MnBlnMnj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBlnMnjActionPerformed(evt);
            }
        });
        MenuLapBulanan.add(MnBlnMnj);

        MnBlnBhp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnBlnBhp.setForeground(new java.awt.Color(51, 51, 51));
        MnBlnBhp.setText("Bulanan BHP Medis/Paket Obat");
        MnBlnBhp.setEnabled(false);
        MnBlnBhp.setName("MnBlnBhp"); // NOI18N
        MnBlnBhp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBlnBhpActionPerformed(evt);
            }
        });
        MenuLapBulanan.add(MnBlnBhp);

        jMenuLaporan.add(MenuLapBulanan);

        MenuLapBulanan1.setBorder(null);
        MenuLapBulanan1.setForeground(new java.awt.Color(51, 51, 51));
        MenuLapBulanan1.setMnemonic('P');
        MenuLapBulanan1.setText("Free");
        MenuLapBulanan1.setToolTipText("Alt+P");
        MenuLapBulanan1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuLapBulanan1.setIconTextGap(8);
        MenuLapBulanan1.setName("MenuLapBulanan1"); // NOI18N
        MenuLapBulanan1.setPreferredSize(new java.awt.Dimension(200, 22));

        MnFreeVstDok.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnFreeVstDok.setForeground(new java.awt.Color(51, 51, 51));
        MnFreeVstDok.setText("Free Visit Dokter");
        MnFreeVstDok.setEnabled(false);
        MnFreeVstDok.setName("MnFreeVstDok"); // NOI18N
        MnFreeVstDok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFreeVstDokActionPerformed(evt);
            }
        });
        MenuLapBulanan1.add(MnFreeVstDok);

        MnFreeBcEkg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnFreeBcEkg.setForeground(new java.awt.Color(51, 51, 51));
        MnFreeBcEkg.setText("Free Bacaan EKG");
        MnFreeBcEkg.setEnabled(false);
        MnFreeBcEkg.setName("MnFreeBcEkg"); // NOI18N
        MnFreeBcEkg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFreeBcEkgActionPerformed(evt);
            }
        });
        MenuLapBulanan1.add(MnFreeBcEkg);

        MnFreeRujRotg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnFreeRujRotg.setForeground(new java.awt.Color(51, 51, 51));
        MnFreeRujRotg.setText("Free Rujukan Rontgen");
        MnFreeRujRotg.setEnabled(false);
        MnFreeRujRotg.setName("MnFreeRujRotg"); // NOI18N
        MnFreeRujRotg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFreeRujRotgActionPerformed(evt);
            }
        });
        MenuLapBulanan1.add(MnFreeRujRotg);

        MnFreeRujRnp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnFreeRujRnp.setForeground(new java.awt.Color(51, 51, 51));
        MnFreeRujRnp.setText("Free Rujukan Ranap");
        MnFreeRujRnp.setEnabled(false);
        MnFreeRujRnp.setName("MnFreeRujRnp"); // NOI18N
        MnFreeRujRnp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFreeRujRnpActionPerformed(evt);
            }
        });
        MenuLapBulanan1.add(MnFreeRujRnp);

        MnFreePrkRln.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnFreePrkRln.setForeground(new java.awt.Color(51, 51, 51));
        MnFreePrkRln.setText("Free Periksa Ralan");
        MnFreePrkRln.setEnabled(false);
        MnFreePrkRln.setName("MnFreePrkRln"); // NOI18N
        MnFreePrkRln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFreePrkRlnActionPerformed(evt);
            }
        });
        MenuLapBulanan1.add(MnFreePrkRln);

        jMenuLaporan.add(MenuLapBulanan1);

        MenuLapPemb.setBorder(null);
        MenuLapPemb.setForeground(new java.awt.Color(51, 51, 51));
        MenuLapPemb.setMnemonic('P');
        MenuLapPemb.setText("Pembayaran");
        MenuLapPemb.setToolTipText("Alt+P");
        MenuLapPemb.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuLapPemb.setIconTextGap(8);
        MenuLapPemb.setName("MenuLapPemb"); // NOI18N
        MenuLapPemb.setPreferredSize(new java.awt.Dimension(200, 22));

        MnLapPembRalan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLapPembRalan.setForeground(new java.awt.Color(51, 51, 51));
        MnLapPembRalan.setText("Pembayaran Ralan");
        MnLapPembRalan.setEnabled(false);
        MnLapPembRalan.setName("MnLapPembRalan"); // NOI18N
        MnLapPembRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapPembRalanActionPerformed(evt);
            }
        });
        MenuLapPemb.add(MnLapPembRalan);

        MnLapPembRnp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLapPembRnp.setForeground(new java.awt.Color(51, 51, 51));
        MnLapPembRnp.setText("Pembayaran Ranap");
        MnLapPembRnp.setEnabled(false);
        MnLapPembRnp.setName("MnLapPembRnp"); // NOI18N
        MnLapPembRnp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapPembRnpActionPerformed(evt);
            }
        });
        MenuLapPemb.add(MnLapPembRnp);

        MnRkpPmbRln.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRkpPmbRln.setForeground(new java.awt.Color(51, 51, 51));
        MnRkpPmbRln.setText("Rekap Pembayaran Ralan");
        MnRkpPmbRln.setEnabled(false);
        MnRkpPmbRln.setName("MnRkpPmbRln"); // NOI18N
        MnRkpPmbRln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRkpPmbRlnActionPerformed(evt);
            }
        });
        MenuLapPemb.add(MnRkpPmbRln);

        MnRkpPmbRnp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRkpPmbRnp.setForeground(new java.awt.Color(51, 51, 51));
        MnRkpPmbRnp.setText("Rekap Pembayaran Ranap");
        MnRkpPmbRnp.setEnabled(false);
        MnRkpPmbRnp.setName("MnRkpPmbRnp"); // NOI18N
        MnRkpPmbRnp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRkpPmbRnpActionPerformed(evt);
            }
        });
        MenuLapPemb.add(MnRkpPmbRnp);

        jMenuLaporan.add(MenuLapPemb);

        MnLapTagMsk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLapTagMsk.setForeground(new java.awt.Color(51, 51, 51));
        MnLapTagMsk.setText("Tagihan Masuk");
        MnLapTagMsk.setEnabled(false);
        MnLapTagMsk.setName("MnLapTagMsk"); // NOI18N
        MnLapTagMsk.setPreferredSize(new java.awt.Dimension(200, 22));
        MnLapTagMsk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapTagMskActionPerformed(evt);
            }
        });
        jMenuLaporan.add(MnLapTagMsk);

        MnLapTmbBiayaPx.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLapTmbBiayaPx.setForeground(new java.awt.Color(51, 51, 51));
        MnLapTmbBiayaPx.setText("Tambahan Biaya Pasien");
        MnLapTmbBiayaPx.setEnabled(false);
        MnLapTmbBiayaPx.setName("MnLapTmbBiayaPx"); // NOI18N
        MnLapTmbBiayaPx.setPreferredSize(new java.awt.Dimension(200, 22));
        MnLapTmbBiayaPx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapTmbBiayaPxActionPerformed(evt);
            }
        });
        jMenuLaporan.add(MnLapTmbBiayaPx);

        MnLapPotBiayaPx.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLapPotBiayaPx.setForeground(new java.awt.Color(51, 51, 51));
        MnLapPotBiayaPx.setText("Potongan Biaya Pasien");
        MnLapPotBiayaPx.setEnabled(false);
        MnLapPotBiayaPx.setName("MnLapPotBiayaPx"); // NOI18N
        MnLapPotBiayaPx.setPreferredSize(new java.awt.Dimension(200, 22));
        MnLapPotBiayaPx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapPotBiayaPxActionPerformed(evt);
            }
        });
        jMenuLaporan.add(MnLapPotBiayaPx);

        MnLapDepositPx.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLapDepositPx.setForeground(new java.awt.Color(51, 51, 51));
        MnLapDepositPx.setText("Deposit Pasien");
        MnLapDepositPx.setEnabled(false);
        MnLapDepositPx.setName("MnLapDepositPx"); // NOI18N
        MnLapDepositPx.setPreferredSize(new java.awt.Dimension(200, 22));
        MnLapDepositPx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapDepositPxActionPerformed(evt);
            }
        });
        jMenuLaporan.add(MnLapDepositPx);

        MnLapUangShift.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLapUangShift.setForeground(new java.awt.Color(51, 51, 51));
        MnLapUangShift.setText("Rekap Uang Pershift");
        MnLapUangShift.setEnabled(false);
        MnLapUangShift.setName("MnLapUangShift"); // NOI18N
        MnLapUangShift.setPreferredSize(new java.awt.Dimension(200, 22));
        MnLapUangShift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapUangShiftActionPerformed(evt);
            }
        });
        jMenuLaporan.add(MnLapUangShift);

        MnLapPaymentPoint.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLapPaymentPoint.setForeground(new java.awt.Color(51, 51, 51));
        MnLapPaymentPoint.setText("Payment Point");
        MnLapPaymentPoint.setEnabled(false);
        MnLapPaymentPoint.setName("MnLapPaymentPoint"); // NOI18N
        MnLapPaymentPoint.setPreferredSize(new java.awt.Dimension(200, 22));
        MnLapPaymentPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapPaymentPointActionPerformed(evt);
            }
        });
        jMenuLaporan.add(MnLapPaymentPoint);

        MenuLapPykt.setBorder(null);
        MenuLapPykt.setForeground(new java.awt.Color(51, 51, 51));
        MenuLapPykt.setMnemonic('P');
        MenuLapPykt.setText("Penyakit");
        MenuLapPykt.setToolTipText("Alt+P");
        MenuLapPykt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuLapPykt.setIconTextGap(8);
        MenuLapPykt.setName("MenuLapPykt"); // NOI18N
        MenuLapPykt.setPreferredSize(new java.awt.Dimension(200, 22));

        MnLapIcd9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLapIcd9.setForeground(new java.awt.Color(51, 51, 51));
        MnLapIcd9.setText("ICD 9");
        MnLapIcd9.setEnabled(false);
        MnLapIcd9.setName("MnLapIcd9"); // NOI18N
        MnLapIcd9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapIcd9ActionPerformed(evt);
            }
        });
        MenuLapPykt.add(MnLapIcd9);

        MnLapIcd10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLapIcd10.setForeground(new java.awt.Color(51, 51, 51));
        MnLapIcd10.setText("ICD 10");
        MnLapIcd10.setEnabled(false);
        MnLapIcd10.setName("MnLapIcd10"); // NOI18N
        MnLapIcd10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapIcd10ActionPerformed(evt);
            }
        });
        MenuLapPykt.add(MnLapIcd10);

        MnLapObtPenyakit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLapObtPenyakit.setForeground(new java.awt.Color(51, 51, 51));
        MnLapObtPenyakit.setText("Obat Penyakit");
        MnLapObtPenyakit.setEnabled(false);
        MnLapObtPenyakit.setName("MnLapObtPenyakit"); // NOI18N
        MnLapObtPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapObtPenyakitActionPerformed(evt);
            }
        });
        MenuLapPykt.add(MnLapObtPenyakit);

        jMenuLaporan.add(MenuLapPykt);

        MnLapKjgRln.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLapKjgRln.setForeground(new java.awt.Color(51, 51, 51));
        MnLapKjgRln.setText("Kunjungan Ralan");
        MnLapKjgRln.setEnabled(false);
        MnLapKjgRln.setName("MnLapKjgRln"); // NOI18N
        MnLapKjgRln.setPreferredSize(new java.awt.Dimension(200, 22));
        MnLapKjgRln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapKjgRlnActionPerformed(evt);
            }
        });
        jMenuLaporan.add(MnLapKjgRln);

        MnLapKjgRanap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnLapKjgRanap.setForeground(new java.awt.Color(51, 51, 51));
        MnLapKjgRanap.setText("Kunjungan Ranap");
        MnLapKjgRanap.setEnabled(false);
        MnLapKjgRanap.setName("MnLapKjgRanap"); // NOI18N
        MnLapKjgRanap.setPreferredSize(new java.awt.Dimension(200, 22));
        MnLapKjgRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapKjgRanapActionPerformed(evt);
            }
        });
        jMenuLaporan.add(MnLapKjgRanap);

        MnSensusHrPoli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnSensusHrPoli.setForeground(new java.awt.Color(51, 51, 51));
        MnSensusHrPoli.setText("Sensus Harian Poli");
        MnSensusHrPoli.setEnabled(false);
        MnSensusHrPoli.setName("MnSensusHrPoli"); // NOI18N
        MnSensusHrPoli.setPreferredSize(new java.awt.Dimension(200, 22));
        MnSensusHrPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSensusHrPoliActionPerformed(evt);
            }
        });
        jMenuLaporan.add(MnSensusHrPoli);

        MenuBar.add(jMenuLaporan);

        jMenuKeu.setBorder(null);
        jMenuKeu.setForeground(new java.awt.Color(255, 255, 255));
        jMenuKeu.setMnemonic('N');
        jMenuKeu.setText("  Keuangan");
        jMenuKeu.setToolTipText("Alt+N");
        jMenuKeu.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuKeu.setName("jMenuKeu"); // NOI18N
        jMenuKeu.setOpaque(false);
        jMenuKeu.setPreferredSize(new java.awt.Dimension(80, 30));

        MenuTarif.setBorder(null);
        MenuTarif.setForeground(new java.awt.Color(51, 51, 51));
        MenuTarif.setMnemonic('P');
        MenuTarif.setText("Tarif Pelayanan");
        MenuTarif.setToolTipText("Alt+P");
        MenuTarif.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuTarif.setIconTextGap(8);
        MenuTarif.setName("MenuTarif"); // NOI18N
        MenuTarif.setPreferredSize(new java.awt.Dimension(200, 22));

        MnTarifKamar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnTarifKamar.setForeground(new java.awt.Color(51, 51, 51));
        MnTarifKamar.setText("Kamar");
        MnTarifKamar.setEnabled(false);
        MnTarifKamar.setName("MnTarifKamar"); // NOI18N
        MnTarifKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTarifKamarActionPerformed(evt);
            }
        });
        MenuTarif.add(MnTarifKamar);

        MnTarifRalan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnTarifRalan.setForeground(new java.awt.Color(51, 51, 51));
        MnTarifRalan.setText("Tarif Ralan");
        MnTarifRalan.setEnabled(false);
        MnTarifRalan.setName("MnTarifRalan"); // NOI18N
        MnTarifRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTarifRalanActionPerformed(evt);
            }
        });
        MenuTarif.add(MnTarifRalan);

        MnTarifRanap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnTarifRanap.setForeground(new java.awt.Color(51, 51, 51));
        MnTarifRanap.setText("Tarif Ranap");
        MnTarifRanap.setEnabled(false);
        MnTarifRanap.setName("MnTarifRanap"); // NOI18N
        MnTarifRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTarifRanapActionPerformed(evt);
            }
        });
        MenuTarif.add(MnTarifRanap);

        MnTarifLab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnTarifLab.setForeground(new java.awt.Color(51, 51, 51));
        MnTarifLab.setText("Tarif Lab");
        MnTarifLab.setEnabled(false);
        MnTarifLab.setName("MnTarifLab"); // NOI18N
        MnTarifLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTarifLabActionPerformed(evt);
            }
        });
        MenuTarif.add(MnTarifLab);

        MnTarifRadiologi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnTarifRadiologi.setForeground(new java.awt.Color(51, 51, 51));
        MnTarifRadiologi.setText("Tarif Radiologi");
        MnTarifRadiologi.setEnabled(false);
        MnTarifRadiologi.setName("MnTarifRadiologi"); // NOI18N
        MnTarifRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTarifRadiologiActionPerformed(evt);
            }
        });
        MenuTarif.add(MnTarifRadiologi);

        MnTarifOperasi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnTarifOperasi.setForeground(new java.awt.Color(51, 51, 51));
        MnTarifOperasi.setText("Tarif Operasi/VK");
        MnTarifOperasi.setEnabled(false);
        MnTarifOperasi.setName("MnTarifOperasi"); // NOI18N
        MnTarifOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTarifOperasiActionPerformed(evt);
            }
        });
        MenuTarif.add(MnTarifOperasi);

        jMenuKeu.add(MenuTarif);

        MenuRekening.setBorder(null);
        MenuRekening.setForeground(new java.awt.Color(51, 51, 51));
        MenuRekening.setMnemonic('P');
        MenuRekening.setText("Rekening");
        MenuRekening.setToolTipText("Alt+P");
        MenuRekening.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuRekening.setIconTextGap(8);
        MenuRekening.setName("MenuRekening"); // NOI18N
        MenuRekening.setPreferredSize(new java.awt.Dimension(200, 22));

        MnAkunRek.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnAkunRek.setForeground(new java.awt.Color(51, 51, 51));
        MnAkunRek.setText("Akun Rekening");
        MnAkunRek.setEnabled(false);
        MnAkunRek.setName("MnAkunRek"); // NOI18N
        MnAkunRek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAkunRekActionPerformed(evt);
            }
        });
        MenuRekening.add(MnAkunRek);

        MnRekThn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRekThn.setForeground(new java.awt.Color(51, 51, 51));
        MnRekThn.setText("Rekening Tahun");
        MnRekThn.setEnabled(false);
        MnRekThn.setName("MnRekThn"); // NOI18N
        MnRekThn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekThnActionPerformed(evt);
            }
        });
        MenuRekening.add(MnRekThn);

        MnPengaturanRek.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPengaturanRek.setForeground(new java.awt.Color(51, 51, 51));
        MnPengaturanRek.setText("Pengaturan Rekening");
        MnPengaturanRek.setEnabled(false);
        MnPengaturanRek.setName("MnPengaturanRek"); // NOI18N
        MnPengaturanRek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPengaturanRekActionPerformed(evt);
            }
        });
        MenuRekening.add(MnPengaturanRek);

        jMenuKeu.add(MenuRekening);

        MnAkunPiutang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnAkunPiutang.setForeground(new java.awt.Color(51, 51, 51));
        MnAkunPiutang.setText("Akun Piutang");
        MnAkunPiutang.setEnabled(false);
        MnAkunPiutang.setName("MnAkunPiutang"); // NOI18N
        MnAkunPiutang.setPreferredSize(new java.awt.Dimension(200, 22));
        MnAkunPiutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAkunPiutangActionPerformed(evt);
            }
        });
        jMenuKeu.add(MnAkunPiutang);

        MnAkunBayar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnAkunBayar.setForeground(new java.awt.Color(51, 51, 51));
        MnAkunBayar.setText("Akun Bayar");
        MnAkunBayar.setEnabled(false);
        MnAkunBayar.setName("MnAkunBayar"); // NOI18N
        MnAkunBayar.setPreferredSize(new java.awt.Dimension(200, 22));
        MnAkunBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAkunBayarActionPerformed(evt);
            }
        });
        jMenuKeu.add(MnAkunBayar);

        MnPengeluaranHr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPengeluaranHr.setForeground(new java.awt.Color(51, 51, 51));
        MnPengeluaranHr.setText("Pengeluaran Harian");
        MnPengeluaranHr.setEnabled(false);
        MnPengeluaranHr.setName("MnPengeluaranHr"); // NOI18N
        MnPengeluaranHr.setPreferredSize(new java.awt.Dimension(200, 22));
        MnPengeluaranHr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPengeluaranHrActionPerformed(evt);
            }
        });
        jMenuKeu.add(MnPengeluaranHr);

        MnPemasukanlain.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPemasukanlain.setForeground(new java.awt.Color(51, 51, 51));
        MnPemasukanlain.setText("Pemasukan lain - lain");
        MnPemasukanlain.setEnabled(false);
        MnPemasukanlain.setName("MnPemasukanlain"); // NOI18N
        MnPemasukanlain.setPreferredSize(new java.awt.Dimension(200, 22));
        MnPemasukanlain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemasukanlainActionPerformed(evt);
            }
        });
        jMenuKeu.add(MnPemasukanlain);

        MenuPiutang.setBorder(null);
        MenuPiutang.setForeground(new java.awt.Color(51, 51, 51));
        MenuPiutang.setMnemonic('P');
        MenuPiutang.setText("Piutang");
        MenuPiutang.setToolTipText("Alt+P");
        MenuPiutang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MenuPiutang.setIconTextGap(8);
        MenuPiutang.setName("MenuPiutang"); // NOI18N
        MenuPiutang.setPreferredSize(new java.awt.Dimension(200, 22));

        MnPiutangPx.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPiutangPx.setForeground(new java.awt.Color(51, 51, 51));
        MnPiutangPx.setText("Piutang Pasien");
        MnPiutangPx.setEnabled(false);
        MnPiutangPx.setName("MnPiutangPx"); // NOI18N
        MnPiutangPx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPiutangPxActionPerformed(evt);
            }
        });
        MenuPiutang.add(MnPiutangPx);

        MnRincPiutangPx.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnRincPiutangPx.setForeground(new java.awt.Color(51, 51, 51));
        MnRincPiutangPx.setText("Rincian Piutang Pasien");
        MnRincPiutangPx.setEnabled(false);
        MnRincPiutangPx.setName("MnRincPiutangPx"); // NOI18N
        MnRincPiutangPx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRincPiutangPxActionPerformed(evt);
            }
        });
        MenuPiutang.add(MnRincPiutangPx);

        MnPiutangBlmLns.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPiutangBlmLns.setForeground(new java.awt.Color(51, 51, 51));
        MnPiutangBlmLns.setText("Piutang Belum Lunas");
        MnPiutangBlmLns.setEnabled(false);
        MnPiutangBlmLns.setName("MnPiutangBlmLns"); // NOI18N
        MnPiutangBlmLns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPiutangBlmLnsActionPerformed(evt);
            }
        });
        MenuPiutang.add(MnPiutangBlmLns);

        MnByrPiutang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnByrPiutang.setForeground(new java.awt.Color(51, 51, 51));
        MnByrPiutang.setText("Bayar Piutang");
        MnByrPiutang.setEnabled(false);
        MnByrPiutang.setName("MnByrPiutang"); // NOI18N
        MnByrPiutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnByrPiutangActionPerformed(evt);
            }
        });
        MenuPiutang.add(MnByrPiutang);

        jMenuKeu.add(MenuPiutang);

        MnHtgObt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnHtgObt.setForeground(new java.awt.Color(51, 51, 51));
        MnHtgObt.setText("Hutang Obat & BHP");
        MnHtgObt.setEnabled(false);
        MnHtgObt.setName("MnHtgObt"); // NOI18N
        MnHtgObt.setPreferredSize(new java.awt.Dimension(200, 22));
        MnHtgObt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHtgObtActionPerformed(evt);
            }
        });
        jMenuKeu.add(MnHtgObt);

        MnByrPsnObt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnByrPsnObt.setForeground(new java.awt.Color(51, 51, 51));
        MnByrPsnObt.setText("Bayar Pesan Obat & BHP");
        MnByrPsnObt.setEnabled(false);
        MnByrPsnObt.setName("MnByrPsnObt"); // NOI18N
        MnByrPsnObt.setPreferredSize(new java.awt.Dimension(200, 22));
        MnByrPsnObt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnByrPsnObtActionPerformed(evt);
            }
        });
        jMenuKeu.add(MnByrPsnObt);

        MnPostingJurnal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnPostingJurnal.setForeground(new java.awt.Color(51, 51, 51));
        MnPostingJurnal.setText("Posting Jurnal");
        MnPostingJurnal.setEnabled(false);
        MnPostingJurnal.setName("MnPostingJurnal"); // NOI18N
        MnPostingJurnal.setPreferredSize(new java.awt.Dimension(200, 22));
        MnPostingJurnal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPostingJurnalActionPerformed(evt);
            }
        });
        jMenuKeu.add(MnPostingJurnal);

        MnJurnalHr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnJurnalHr.setForeground(new java.awt.Color(51, 51, 51));
        MnJurnalHr.setText("Jurnal Harian");
        MnJurnalHr.setEnabled(false);
        MnJurnalHr.setName("MnJurnalHr"); // NOI18N
        MnJurnalHr.setPreferredSize(new java.awt.Dimension(200, 22));
        MnJurnalHr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJurnalHrActionPerformed(evt);
            }
        });
        jMenuKeu.add(MnJurnalHr);

        MnBukuBesar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnBukuBesar.setForeground(new java.awt.Color(51, 51, 51));
        MnBukuBesar.setText("Buku Besar");
        MnBukuBesar.setEnabled(false);
        MnBukuBesar.setName("MnBukuBesar"); // NOI18N
        MnBukuBesar.setPreferredSize(new java.awt.Dimension(200, 22));
        MnBukuBesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBukuBesarActionPerformed(evt);
            }
        });
        jMenuKeu.add(MnBukuBesar);

        MnCashFlow.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnCashFlow.setForeground(new java.awt.Color(51, 51, 51));
        MnCashFlow.setText("Cash Flow");
        MnCashFlow.setEnabled(false);
        MnCashFlow.setName("MnCashFlow"); // NOI18N
        MnCashFlow.setPreferredSize(new java.awt.Dimension(200, 22));
        MnCashFlow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCashFlowActionPerformed(evt);
            }
        });
        jMenuKeu.add(MnCashFlow);

        MnKeu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnKeu.setForeground(new java.awt.Color(51, 51, 51));
        MnKeu.setText("Neraca Rugi Laba");
        MnKeu.setEnabled(false);
        MnKeu.setName("MnKeu"); // NOI18N
        MnKeu.setPreferredSize(new java.awt.Dimension(200, 22));
        MnKeu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKeuActionPerformed(evt);
            }
        });
        jMenuKeu.add(MnKeu);

        MenuBar.add(jMenuKeu);

        jMenuBantuan.setBorder(null);
        jMenuBantuan.setForeground(new java.awt.Color(255, 255, 255));
        jMenuBantuan.setMnemonic('N');
        jMenuBantuan.setText("  Bantuan");
        jMenuBantuan.setToolTipText("Alt+N");
        jMenuBantuan.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenuBantuan.setName("jMenuBantuan"); // NOI18N
        jMenuBantuan.setOpaque(false);
        jMenuBantuan.setPreferredSize(new java.awt.Dimension(200, 22));

        MnBantuan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnBantuan.setText("Bantua Penggunaan");
        MnBantuan.setName("MnBantuan"); // NOI18N
        MnBantuan.setPreferredSize(new java.awt.Dimension(200, 22));
        MnBantuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBantuanActionPerformed(evt);
            }
        });
        jMenuBantuan.add(MnBantuan);

        MnDokumentasi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDokumentasi.setText("Dokumentasi Online");
        MnDokumentasi.setName("MnDokumentasi"); // NOI18N
        MnDokumentasi.setPreferredSize(new java.awt.Dimension(200, 22));
        MnDokumentasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokumentasiActionPerformed(evt);
            }
        });
        jMenuBantuan.add(MnDokumentasi);

        MnUcapan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnUcapan.setText("Ucapan Terima Kasih");
        MnUcapan.setName("MnUcapan"); // NOI18N
        MnUcapan.setPreferredSize(new java.awt.Dimension(200, 22));
        MnUcapan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUcapanActionPerformed(evt);
            }
        });
        jMenuBantuan.add(MnUcapan);

        MenuBar.add(jMenuBantuan);

        setJMenuBar(MenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    private void BtnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseActionPerformed
        isTutup();
        int jawab=JOptionPane.showConfirmDialog(null, "Yakin anda mau keluar dari program ini ????","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if(jawab==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_BtnCloseActionPerformed

    private void BtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelActionPerformed
        edAdmin.setText("");
        edPwd.setText("");
        DlgLogin.dispose();
    }//GEN-LAST:event_BtnCancelActionPerformed

    private void BtnLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLogActionPerformed
        try{
            //com.sun.awt.AWTUtilities.setWindowOpacity(DlgLogin,0.7f);
        }catch(Exception e){
        }
        FlayMenu.setVisible(false);

        switch (BtnLog.getText().trim()) {
            case "Log Out":
                BtnToolReg.setEnabled(false);
                BtnToolKamnap.setEnabled(false);
                BtnToolKasir.setEnabled(false);
                btnToolLab.setEnabled(false);
                btnToolRad.setEnabled(false);
                BtnToolJualObat.setEnabled(false);
                btnToolIGD.setEnabled(false);
                MnGantiPassword.setEnabled(false);

                //Jong Source First
                MnPasien.setEnabled(false);
                MnKelahiranBayi.setEnabled(false);
                MnPasienMeninggal.setEnabled(false);
                MnDiagnosaPasien.setEnabled(false);
                MnRiwayatPerawatan.setEnabled(false);
                MnRetBrksRm.setEnabled(false);
                MnRegistrasi.setEnabled(false);
                MnTindakanRalan.setEnabled(false);
                MnKamarInap.setEnabled(false);
                MnDpjpRanap.setEnabled(false);
                MnTindakanRanap.setEnabled(false);
                MnRujukMasuk.setEnabled(false);
                MnRujukKeluar.setEnabled(false);
                MnInfoKamar.setEnabled(false);
                MnIgd.setEnabled(false);
                MnJadwalDokter.setEnabled(false);
                MnOperasi.setEnabled(false);
                MnPemberianObat.setEnabled(false);
                MnResepObat.setEnabled(false);
                MnResepPulang.setEnabled(false);
                MnDietPasien.setEnabled(false);
                MnPeriksaLab.setEnabled(false);
                MnPeriksaRad.setEnabled(false);
                MnKasirRalan.setEnabled(false);
                MnDepositPasien.setEnabled(false);
                MnPiutangPasien.setEnabled(false);
                MnDataDokter.setEnabled(false);
                MnDataPetugas.setEnabled(false);
                MnDataPegawai.setEnabled(false);
                MnBarcodePresensi.setEnabled(false);
                MnJamPresensi.setEnabled(false);
                MnPresensiHarian.setEnabled(false);
                MnPresensiBulanan.setEnabled(false);
                MnTempPresensi.setEnabled(false);
                MnRekapKehadiran.setEnabled(false);
                MnSidikJari.setEnabled(false);
                MnJadwalPegawai.setEnabled(false);
                MnJdwlTambahan.setEnabled(false);
                MnPresensiHarian1.setEnabled(false);
                MnIndustriFar.setEnabled(false);
                MnSuplier.setEnabled(false);
                MnSatuanBrg.setEnabled(false);
                MnKonvSat.setEnabled(false);
                MnJnsObt.setEnabled(false);
                MnDataObat.setEnabled(false);
                MnStokOpname.setEnabled(false);
                MnMutasiObat.setEnabled(false);
                MnStokObtPx.setEnabled(false);
                MnPengadaan.setEnabled(false);
                MnPemesanan.setEnabled(false);
                MnPenjualanObt.setEnabled(false);
                MnStokOpname1.setEnabled(false);
                MnReturKeSup.setEnabled(false);
                MnReturPemb.setEnabled(false);
                MnReturObtRanap.setEnabled(false);
                MnReturPiutangPemb.setEnabled(false);
                MnKeuntunganPenj.setEnabled(false);
                MnKeuntBeriObt.setEnabled(false);
                MnSirkulasiObt.setEnabled(false);
                MnRiwayat.setEnabled(false);
                MnDaruratStok.setEnabled(false);
                MnSatuanBrgNon.setEnabled(false);
                MnJnsBrgNon.setEnabled(false);
                MnDataBrgNon.setEnabled(false);
                MnSupNon.setEnabled(false);
                MnPengadaanbrg.setEnabled(false);
                MnStokKeluar.setEnabled(false);
                MnBiayaPengadaan.setEnabled(false);
                MnRekapPengadaan.setEnabled(false);
                MnRekapStok.setEnabled(false);
                MnRiwPesBpjs.setEnabled(false);
                MnCekNoRujPCare.setEnabled(false);
                MnRefDiagBpjs.setEnabled(false);
                MnRefPlBpjs.setEnabled(false);
                MnRefFaskes.setEnabled(false);
                MnBridging.setEnabled(false);
                MnMonitoringKlaim.setEnabled(false);
                MnLapObtPoli.setEnabled(false);
                MnObtKmr.setEnabled(false);
                MnObtDokRln.setEnabled(false);
                MnObtDokRnp.setEnabled(false);
                MnObtDokRsp.setEnabled(false);
                MnObtCrByr.setEnabled(false);
                MnDetJMDok.setEnabled(false);
                MnHrDokAll.setEnabled(false);
                MnHrDokRalan.setEnabled(false);
                MnHrDok.setEnabled(false);
                MnHrKamar.setEnabled(false);
                MnHrBhp.setEnabled(false);
                MnHrParamedis.setEnabled(false);
                MnHrMnj.setEnabled(false);
                MnHrKso.setEnabled(false);
                MnHrSrn.setEnabled(false);
                MnBulananDok.setEnabled(false);
                MnBlnParamedis.setEnabled(false);
                MnBlnSrn.setEnabled(false);
                MnBlnKso.setEnabled(false);
                MnBlnMnj.setEnabled(false);
                MnBlnBhp.setEnabled(false);
                MnFreeVstDok.setEnabled(false);
                MnFreeBcEkg.setEnabled(false);
                MnFreeRujRotg.setEnabled(false);
                MnFreeRujRnp.setEnabled(false);
                MnFreePrkRln.setEnabled(false);
                MnLapPembRalan.setEnabled(false);
                MnLapPembRnp.setEnabled(false);
                MnRkpPmbRln.setEnabled(false);
                MnRkpPmbRnp.setEnabled(false);
                MnLapTagMsk.setEnabled(false);
                MnLapTmbBiayaPx.setEnabled(false);
                MnLapPotBiayaPx.setEnabled(false);
                MnLapDepositPx.setEnabled(false);
                MnLapUangShift.setEnabled(false);
                MnLapPaymentPoint.setEnabled(false);
                MnLapIcd9.setEnabled(false);
                MnLapIcd10.setEnabled(false);
                MnLapObtPenyakit.setEnabled(false);
                MnLapKjgRln.setEnabled(false);
                MnLapKjgRanap.setEnabled(false);
                MnSensusHrPoli.setEnabled(false);
                MnTarifKamar.setEnabled(false);
                MnTarifRalan.setEnabled(false);
                MnTarifRanap.setEnabled(false);
                MnTarifLab.setEnabled(false);
                MnTarifRadiologi.setEnabled(false);
                MnTarifOperasi.setEnabled(false);
                MnAkunRek.setEnabled(false);
                MnRekThn.setEnabled(false);
                MnPengaturanRek.setEnabled(false);
                MnAkunPiutang.setEnabled(false);
                MnAkunBayar.setEnabled(false);
                MnPengeluaranHr.setEnabled(false);
                MnPemasukanlain.setEnabled(false);
                MnPiutangPx.setEnabled(false);
                MnRincPiutangPx.setEnabled(false);
                MnPiutangBlmLns.setEnabled(false);
                MnByrPiutang.setEnabled(false);
                MnHtgObt.setEnabled(false);
                MnByrPsnObt.setEnabled(false);
                MnPostingJurnal.setEnabled(false);
                MnJurnalHr.setEnabled(false);
                MnBukuBesar.setEnabled(false);
                MnCashFlow.setEnabled(false);
                MnKeu.setEnabled(false);
                MnSetAplikasi.setEnabled(false);
                MnPenujang.setEnabled(false);
                MnSetOtoLok.setEnabled(false);
                MnSetKmrInp.setEnabled(false);
                MnSetHargaKamar.setEnabled(false);
                MnSetEmbTus.setEnabled(false);
                MnSetUser.setEnabled(false);
                MnSetTrackerLog.setEnabled(false);
                MnDisplayAntrian.setEnabled(false);
                MnSetHargaObt.setEnabled(false);
                MnSetObtRnp.setEnabled(false);
                MnSetPenggTrf.setEnabled(false);
                MnSetOtoRalan.setEnabled(false);
                MnBiayaHarian.setEnabled(false);
                MnBiayaMskSkl.setEnabled(false);
                MnSetRM.setEnabled(false);
                MnSetBilling.setEnabled(false);
                MnClosingKsr.setEnabled(false);
                MnSetLambtPres.setEnabled(false);
                //Jong Source end

                edAdmin.setText("");
                edPwd.setText("");
                BtnLog.setText("Log In");
                MnLogin.setText("Log In");
                lblStts.setText("Status Admin : ");
                lblUser.setText("Log Out");
                isTutup();
                break;
            case "Log In":
                DlgLogin.setVisible(true);
                edAdmin.requestFocus();
                break;
        }
    }//GEN-LAST:event_BtnLogActionPerformed

    private void BtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoginActionPerformed
        if(edAdmin.getText().trim().equals("")){
            Valid.textKosong(edAdmin,"ID User");
        }else if(edPwd.getText().trim().equals("")){
            Valid.textKosong(edPwd,"Password");
        }else {
            try {
                var.setData(edAdmin.getText(),edPwd.getText());
                if(var.getjml1()>=1){
                    BtnToolReg.setEnabled(true);
                    BtnToolKamnap.setEnabled(true);
                    BtnToolKasir.setEnabled(true);
                    btnToolLab.setEnabled(true);
                    btnToolIGD.setEnabled(true);
                    btnToolRad.setEnabled(true);
                    BtnToolJualObat.setEnabled(true);
                    MnGantiPassword.setEnabled(false);

                    //Jong Source first
                    MnPasien.setEnabled(true);
                    MnKelahiranBayi.setEnabled(true);
                    MnPasienMeninggal.setEnabled(true);
                    MnDiagnosaPasien.setEnabled(true);
                    MnRiwayatPerawatan.setEnabled(true);
                    MnRetBrksRm.setEnabled(true);
                    MnRegistrasi.setEnabled(true);
                    MnTindakanRalan.setEnabled(true);
                    MnKamarInap.setEnabled(true);
                    MnDpjpRanap.setEnabled(true);
                    MnTindakanRanap.setEnabled(true);
                    MnRujukMasuk.setEnabled(true);
                    MnRujukKeluar.setEnabled(true);
                    MnInfoKamar.setEnabled(true);
                    MnIgd.setEnabled(true);
                    MnJadwalDokter.setEnabled(true);
                    MnOperasi.setEnabled(true);
                    MnPemberianObat.setEnabled(true);
                    MnResepObat.setEnabled(true);
                    MnResepPulang.setEnabled(true);
                    MnDietPasien.setEnabled(true);
                    MnPeriksaLab.setEnabled(true);
                    MnPeriksaRad.setEnabled(true);
                    MnKasirRalan.setEnabled(true);
                    MnDepositPasien.setEnabled(true);
                    MnPiutangPasien.setEnabled(true);
                    MnDataDokter.setEnabled(true);
                    MnDataPetugas.setEnabled(true);
                    MnDataPegawai.setEnabled(true);
                    MnBarcodePresensi.setEnabled(true);
                    MnJamPresensi.setEnabled(true);
                    MnPresensiHarian.setEnabled(true);
                    MnPresensiBulanan.setEnabled(true);
                    MnTempPresensi.setEnabled(true);
                    MnRekapKehadiran.setEnabled(true);
                    MnSidikJari.setEnabled(true);
                    MnJadwalPegawai.setEnabled(true);
                    MnJdwlTambahan.setEnabled(true);
                    MnPresensiHarian1.setEnabled(true);
                    MnIndustriFar.setEnabled(true);
                    MnSuplier.setEnabled(true);
                    MnSatuanBrg.setEnabled(true);
                    MnKonvSat.setEnabled(true);
                    MnJnsObt.setEnabled(true);
                    MnDataObat.setEnabled(true);
                    MnStokOpname.setEnabled(true);
                    MnMutasiObat.setEnabled(true);
                    MnStokObtPx.setEnabled(true);
                    MnPengadaan.setEnabled(true);
                    MnPemesanan.setEnabled(true);
                    MnPenjualanObt.setEnabled(true);
                    MnStokOpname1.setEnabled(true);
                    MnReturKeSup.setEnabled(true);
                    MnReturPemb.setEnabled(true);
                    MnReturObtRanap.setEnabled(true);
                    MnReturPiutangPemb.setEnabled(true);
                    MnKeuntunganPenj.setEnabled(true);
                    MnKeuntBeriObt.setEnabled(true);
                    MnSirkulasiObt.setEnabled(true);
                    MnRiwayat.setEnabled(true);
                    MnDaruratStok.setEnabled(true);
                    MnSatuanBrgNon.setEnabled(true);
                    MnJnsBrgNon.setEnabled(true);
                    MnDataBrgNon.setEnabled(true);
                    MnSupNon.setEnabled(true);
                    MnPengadaanbrg.setEnabled(true);
                    MnStokKeluar.setEnabled(true);
                    MnBiayaPengadaan.setEnabled(true);
                    MnRekapPengadaan.setEnabled(true);
                    MnRekapStok.setEnabled(true);
                    MnRiwPesBpjs.setEnabled(true);
                    MnCekNoRujPCare.setEnabled(true);
                    MnRefDiagBpjs.setEnabled(true);
                    MnRefPlBpjs.setEnabled(true);
                    MnRefFaskes.setEnabled(true);
                    MnBridging.setEnabled(true);
                    MnMonitoringKlaim.setEnabled(true);
                    MnLapObtPoli.setEnabled(true);
                    MnObtKmr.setEnabled(true);
                    MnObtDokRln.setEnabled(true);
                    MnObtDokRnp.setEnabled(true);
                    MnObtDokRsp.setEnabled(true);
                    MnObtCrByr.setEnabled(true);
                    MnDetJMDok.setEnabled(true);
                    MnHrDokAll.setEnabled(true);
                    MnHrDokRalan.setEnabled(true);
                    MnHrDok.setEnabled(true);
                    MnHrKamar.setEnabled(true);
                    MnHrBhp.setEnabled(true);
                    MnHrParamedis.setEnabled(true);
                    MnHrMnj.setEnabled(true);
                    MnHrKso.setEnabled(true);
                    MnHrSrn.setEnabled(true);
                    MnBulananDok.setEnabled(true);
                    MnBlnParamedis.setEnabled(true);
                    MnBlnSrn.setEnabled(true);
                    MnBlnKso.setEnabled(true);
                    MnBlnMnj.setEnabled(true);
                    MnBlnBhp.setEnabled(true);
                    MnFreeVstDok.setEnabled(true);
                    MnFreeBcEkg.setEnabled(true);
                    MnFreeRujRotg.setEnabled(true);
                    MnFreeRujRnp.setEnabled(true);
                    MnFreePrkRln.setEnabled(true);
                    MnLapPembRalan.setEnabled(true);
                    MnLapPembRnp.setEnabled(true);
                    MnRkpPmbRln.setEnabled(true);
                    MnRkpPmbRnp.setEnabled(true);
                    MnLapTagMsk.setEnabled(true);
                    MnLapTmbBiayaPx.setEnabled(true);
                    MnLapPotBiayaPx.setEnabled(true);
                    MnLapDepositPx.setEnabled(true);
                    MnLapUangShift.setEnabled(true);
                    MnLapPaymentPoint.setEnabled(true);
                    MnLapIcd9.setEnabled(true);
                    MnLapIcd10.setEnabled(true);
                    MnLapObtPenyakit.setEnabled(true);
                    MnLapKjgRln.setEnabled(true);
                    MnLapKjgRanap.setEnabled(true);
                    MnSensusHrPoli.setEnabled(true);
                    MnTarifKamar.setEnabled(true);
                    MnTarifRalan.setEnabled(true);
                    MnTarifRanap.setEnabled(true);
                    MnTarifLab.setEnabled(true);
                    MnTarifRadiologi.setEnabled(true);
                    MnTarifOperasi.setEnabled(true);
                    MnAkunRek.setEnabled(true);
                    MnRekThn.setEnabled(true);
                    MnPengaturanRek.setEnabled(true);
                    MnAkunPiutang.setEnabled(true);
                    MnAkunBayar.setEnabled(true);
                    MnPengeluaranHr.setEnabled(true);
                    MnPemasukanlain.setEnabled(true);
                    MnPiutangPx.setEnabled(true);
                    MnRincPiutangPx.setEnabled(true);
                    MnPiutangBlmLns.setEnabled(true);
                    MnByrPiutang.setEnabled(true);
                    MnHtgObt.setEnabled(true);
                    MnByrPsnObt.setEnabled(true);
                    MnPostingJurnal.setEnabled(true);
                    MnJurnalHr.setEnabled(true);
                    MnBukuBesar.setEnabled(true);
                    MnCashFlow.setEnabled(true);
                    MnKeu.setEnabled(true);
                    MnSetAplikasi.setEnabled(true);
                    MnPenujang.setEnabled(true);
                    MnSetOtoLok.setEnabled(true);
                    MnSetKmrInp.setEnabled(true);
                    MnSetHargaKamar.setEnabled(true);
                    MnSetEmbTus.setEnabled(true);
                    MnSetUser.setEnabled(true);
                    MnSetTrackerLog.setEnabled(true);
                    MnDisplayAntrian.setEnabled(true);
                    MnSetHargaObt.setEnabled(true);
                    MnSetObtRnp.setEnabled(true);
                    MnSetPenggTrf.setEnabled(true);
                    MnSetOtoRalan.setEnabled(true);
                    MnBiayaHarian.setEnabled(true);
                    MnBiayaMskSkl.setEnabled(true);
                    MnSetRM.setEnabled(true);
                    MnSetBilling.setEnabled(true);
                    MnClosingKsr.setEnabled(true);
                    MnSetLambtPres.setEnabled(true);
                    //Jong Source end

                    DlgLogin.dispose();
                    BtnLog.setText("Log Out");
                    MnLogin.setText("Log Out");
                    lblStts.setText("Admin : ");
                    lblUser.setText("Admin Utama");
                }else if(var.getjml2()>=1){


                    jMenuLayanan.setEnabled(true);
                    jMenuManajemen.setEnabled(true);
                    jMenuFarmasi.setEnabled(true);
                    jMenuInventory.setEnabled(true);
                    jMenuLaporan.setEnabled(true);

                    DlgLogin.dispose();
                    BtnLog.setText("Log Out");
                    MnLogin.setText("Log Out");
                    lblStts.setText("Admin : ");
                    lblUser.setText(var.getkode());
                    MnGantiPassword.setEnabled(true);
                    if(var.getrekammedis() == true){
                        BtnToolReg.setEnabled(true);
                        btnToolIGD.setEnabled(true);
                        BtnToolKamnap.setEnabled(true);
                        MnPasien.setEnabled(true);
                        MnKelahiranBayi.setEnabled(true);
                        MnPasienMeninggal.setEnabled(true);
                        MnDiagnosaPasien.setEnabled(true);
                        MnRiwayatPerawatan.setEnabled(true);
                        MnRetBrksRm.setEnabled(true);
                        MnRegistrasi.setEnabled(true);
                        MnIgd.setEnabled(true);
                        MnRiwPesBpjs.setEnabled(true);
                        MnCekNoRujPCare.setEnabled(true);
                        MnRefDiagBpjs.setEnabled(true);
                        MnRefPlBpjs.setEnabled(true);
                        MnRefFaskes.setEnabled(true);
                        MnBridging.setEnabled(true);
                        MnMonitoringKlaim.setEnabled(true);
                        //MnRefKmrAplicare.setEnabled(true); // Belum
                        //MnKmrAplicare.setEnabled(true);
                        //MnRefMblJkn.setEnabled(true);
                        //MnBtlMblJkn.setEnabled(true);
                        MnLapIcd9.setEnabled(true);
                        MnLapIcd10.setEnabled(true);
                        MnLapObtPenyakit.setEnabled(true);
                        MnLapKjgRln.setEnabled(true);
                        MnLapKjgRanap.setEnabled(true);
                        MnSensusHrPoli.setEnabled(true);
                    }

                    // Menu Paramedis ================================================
                    if(var.getparamedis() == true){
                        BtnToolReg.setEnabled(true);
                        btnToolIGD.setEnabled(true);
                        MnTindakanRalan.setEnabled(true);
                        MnKamarInap.setEnabled(true);
                        MnDpjpRanap.setEnabled(true);
                        MnTindakanRanap.setEnabled(true);
                        MnRujukMasuk.setEnabled(true);
                        MnRujukKeluar.setEnabled(true);
                        MnInfoKamar.setEnabled(true);
                        MnIgd.setEnabled(true);
                        MnOperasi.setEnabled(true);
                        BtnToolKamnap.setEnabled(true);
                    }

                    // MENU PENGGUNA ============================================
                    if(var.getpengguna() == true){
                        MnJadwalDokter.setEnabled(true);
                        MnDietPasien.setEnabled(true);
                        MnDataDokter.setEnabled(true);
                        MnDataPetugas.setEnabled(true);
                        MnDataPegawai.setEnabled(true);
                        MnBarcodePresensi.setEnabled(true);
                        MnJamPresensi.setEnabled(true);
                        MnPresensiHarian.setEnabled(true);
                        MnPresensiBulanan.setEnabled(true);
                        MnTempPresensi.setEnabled(true);
                        MnRekapKehadiran.setEnabled(true);
                        MnSidikJari.setEnabled(true);
                        MnJadwalPegawai.setEnabled(true);
                        MnJdwlTambahan.setEnabled(true);
                        MnPresensiHarian1.setEnabled(true);
                        MnSatuanBrgNon.setEnabled(true);
                        MnJnsBrgNon.setEnabled(true);
                        MnDataBrgNon.setEnabled(true);
                        MnSupNon.setEnabled(true);
                        MnPengadaanbrg.setEnabled(true);
                        MnStokKeluar.setEnabled(true);
                        MnBiayaPengadaan.setEnabled(true);//belum
                        MnRekapPengadaan.setEnabled(true);
                        MnRekapStok.setEnabled(true);
                    }

                    if(var.getapoteker() == true){
                        MnPemberianObat.setEnabled(true);
                        MnResepObat.setEnabled(true);
                        MnResepPulang.setEnabled(true);
                        MnIndustriFar.setEnabled(true);
                        MnSuplier.setEnabled(true);
                        MnSatuanBrg.setEnabled(true);
                        MnKonvSat.setEnabled(true);
                        MnJnsObt.setEnabled(true); //belum
                        MnDataObat.setEnabled(true); // ada sama bingung yang mana
                        MnStokOpname.setEnabled(true);
                        MnMutasiObat.setEnabled(true);
                        MnStokObtPx.setEnabled(true);
                        MnPengadaan.setEnabled(true);
                        MnPemesanan.setEnabled(true);
                        MnPenjualanObt.setEnabled(true);
                        MnReturKeSup.setEnabled(true);
                        MnReturPemb.setEnabled(true);
                        MnReturObtRanap.setEnabled(true);
                        MnReturPiutangPemb.setEnabled(true);
                        MnKeuntunganPenj.setEnabled(true);
                        MnKeuntBeriObt.setEnabled(true);
                        MnSirkulasiObt.setEnabled(true);
                        MnRiwayat.setEnabled(true);
                        MnDaruratStok.setEnabled(true);
                        BtnToolJualObat.setEnabled(true);
                    }

                    // Menu Laboratorium ============================================
                    if(var.getlaboratorium() == true){
                        MnPeriksaLab.setEnabled(true);
                        btnToolLab.setEnabled(true);
                    }

                    // Menu Radiologi ============================================
                    if(var.getradiologi() == true){
                        MnPeriksaRad.setEnabled(true);
                        btnToolRad.setEnabled(true);
                    }

                    // Menu Kasir ===============================================
                    if(var.getkasir() == true){
                        MnKasirRalan.setEnabled(true);
                        MnDepositPasien.setEnabled(true);
                        MnPiutangPasien.setEnabled(true);
                        MnLapObtPoli.setEnabled(true);
                        MnObtKmr.setEnabled(true);
                        MnObtDokRln.setEnabled(true);
                        MnObtDokRnp.setEnabled(true);
                        MnObtDokRsp.setEnabled(true);
                        MnObtCrByr.setEnabled(true);
                        MnDetJMDok.setEnabled(true);
                        MnHrDokAll.setEnabled(true);
                        MnHrDokRalan.setEnabled(true);
                        MnHrDok.setEnabled(true);
                        MnHrKamar.setEnabled(true);
                        MnHrBhp.setEnabled(true);
                        MnHrParamedis.setEnabled(true);
                        MnHrMnj.setEnabled(true);
                        MnHrKso.setEnabled(true);
                        MnHrSrn.setEnabled(true);
                        MnBulananDok.setEnabled(true);
                        MnBlnParamedis.setEnabled(true);
                        MnBlnSrn.setEnabled(true);
                        MnBlnKso.setEnabled(true);
                        MnBlnMnj.setEnabled(true);
                        MnBlnBhp.setEnabled(true);
                        MnFreeVstDok.setEnabled(true);
                        MnFreeBcEkg.setEnabled(true);
                        MnFreeRujRotg.setEnabled(true);
                        MnFreeRujRnp.setEnabled(true);
                        MnFreePrkRln.setEnabled(true);
                        MnLapPembRalan.setEnabled(true);
                        MnLapPembRnp.setEnabled(true);
                        MnRkpPmbRln.setEnabled(true);
                        MnRkpPmbRnp.setEnabled(true);
                        MnLapTagMsk.setEnabled(true);
                        MnLapTmbBiayaPx.setEnabled(true);
                        MnLapPotBiayaPx.setEnabled(true);
                        MnLapDepositPx.setEnabled(true);
                        MnLapUangShift.setEnabled(true);
                        MnLapPaymentPoint.setEnabled(true);
                        MnTarifKamar.setEnabled(true);
                        MnTarifRalan.setEnabled(true);
                        MnTarifRanap.setEnabled(true);
                        MnTarifLab.setEnabled(true);
                        MnTarifRadiologi.setEnabled(true);
                        MnTarifOperasi.setEnabled(true);
                        MnAkunRek.setEnabled(true);
                        MnRekThn.setEnabled(true);
                        MnPengaturanRek.setEnabled(true);
                        MnAkunPiutang.setEnabled(true);
                        MnAkunBayar.setEnabled(true);
                        MnPengeluaranHr.setEnabled(true);
                        MnPemasukanlain.setEnabled(true);
                        MnPiutangPx.setEnabled(true);
                        MnRincPiutangPx.setEnabled(true);
                        MnPiutangBlmLns.setEnabled(true);
                        MnByrPiutang.setEnabled(true);
                        MnHtgObt.setEnabled(true);
                        MnByrPsnObt.setEnabled(true);
                        MnPostingJurnal.setEnabled(true);
                        MnJurnalHr.setEnabled(true);
                        MnBukuBesar.setEnabled(true);
                        MnCashFlow.setEnabled(true);
                        MnKeu.setEnabled(true);
                        BtnToolKasir.setEnabled(true);
                    }

                    // INI MENU ADMIN ONLY =============================================================
//                    if(var.getadmin() == true){
//                        MnSetAplikasi.setEnabled(true);
//                        MnSetAdmin.setEnabled(true);
//                        MnPenujang.setEnabled(true);
//                        MnSetOtoLok.setEnabled(true);
//                        MnSetKmrInp.setEnabled(true);
//                        MnSetHargaKamar.setEnabled(true);
//                        MnSetEmbTus.setEnabled(true);
//                        MnSetUser.setEnabled(true);
//                        MnSetTrackerLog.setEnabled(true);
//                        MnDisplayAntrian.setEnabled(true);
//                        MnSetHargaObt.setEnabled(true);
//                        MnSetObtRalan.setEnabled(true);
//                        MnSetObtRnp.setEnabled(true);
//                        MnSetPenggTrf.setEnabled(true);
//                        MnSetOtoRalan.setEnabled(true);
//                        MnBiayaHarian.setEnabled(true);
//                        MnBiayaMskSkl.setEnabled(true);
//                        MnSetRM.setEnabled(true);
//                        MnSetBilling.setEnabled(true);
//                        MnClosingKsr.setEnabled(true);
//                        MnSetLambtPres.setEnabled(true);
//                    }
                    //Jong Source end

                    btnToolIGD.setEnabled(var.getmanajemen());
                    Sequel.menyimpan("tracker","'"+edAdmin.getText()+"',current_date(),current_time()","Login");
                }else if((var.getjml1()==0)&&(var.getjml2()==0)){
                    JOptionPane.showMessageDialog(null,"Maaf, Gagal login. ID User atau password ada yang salah ...!");
                    BtnToolReg.setEnabled(false);
                    BtnToolKamnap.setEnabled(false);
                    BtnToolKasir.setEnabled(false);
                    MnGantiPassword.setEnabled(false);
                    btnToolLab.setEnabled(false);
                    btnToolIGD.setEnabled(false);
                    btnToolRad.setEnabled(false);
                    BtnToolJualObat.setEnabled(false);
                    edAdmin.setText("");
                    edPwd.setText("");


                    edAdmin.requestFocus();
                    BtnLog.setText("Log In");
                    MnLogin.setText("Log In");
                    lblStts.setText("Status Admin : ");
                    lblUser.setText("Log Out");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            }
        }
    }//GEN-LAST:event_BtnLoginActionPerformed

    private void BtnToolKamnapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnToolKamnapActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        kasirralan.kamarinap.isCek();
        kasirralan.kamarinap.emptTeks();
        //kasirralan.kamarinap.tampil();
        kasirralan.kamarinap.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.kamarinap.setLocationRelativeTo(PanelUtama);
        kasirralan.kamarinap.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnToolKamnapActionPerformed

private void edAdminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edAdminKeyPressed
        Valid.pindah(evt,BtnCancel, edPwd);
}//GEN-LAST:event_edAdminKeyPressed

private void edPwdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edPwdKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnLoginActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            edAdmin.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnLogin.requestFocus();
        }
}//GEN-LAST:event_edPwdKeyPressed

private void BtnToolKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnToolKasirActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        kasirralan.isCek();
        //kasirralan.tampilkasir();
        kasirralan.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.setLocationRelativeTo(PanelUtama);
        kasirralan.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnToolKasirActionPerformed

private void BtnToolRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnToolRegActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        //kasirralan.kamarinap.reg.tampil();
        kasirralan.kamarinap.reg.emptTeks();
        kasirralan.kamarinap.reg.isCek();
        kasirralan.kamarinap.reg.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.kamarinap.reg.setLocationRelativeTo(PanelUtama);
        kasirralan.kamarinap.reg.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnToolRegActionPerformed

private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
    if(this.getState()==1){
        isTutup();
    }
}//GEN-LAST:event_formWindowStateChanged

private void BtnClosePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnClosePassActionPerformed
        WindowInput.dispose();
}//GEN-LAST:event_BtnClosePassActionPerformed

private void BtnClosePassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnClosePassKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            WindowInput.dispose();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            PassBaru2.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            PassLama.requestFocus();
        }
}//GEN-LAST:event_BtnClosePassKeyPressed

private void BtnSimpanPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanPassActionPerformed
        if(PassLama.getText().trim().equals("")){
            Valid.textKosong(PassLama,"Password Lama");
        }else if(Passbaru1.getText().trim().equals("")){
            Valid.textKosong(Passbaru1,"Password Baru");
        }else if(PassBaru2.getText().trim().equals("")){
            Valid.textKosong(PassBaru2,"Password Baru");
        }else if(!edPwd.getText().trim().equals(PassLama.getText())){
            JOptionPane.showMessageDialog(null,"Maaf, Password lama salah...!!!");
            PassLama.requestFocus();
        }else if(!Passbaru1.getText().trim().equals(PassBaru2.getText())){
            JOptionPane.showMessageDialog(null,"Maaf, Password Baru 1 dan Password Baru 2 tidak sesuai...!!!");
            PassBaru2.requestFocus();
        }else{
            Sequel.queryu("update user set password=AES_ENCRYPT('"+PassBaru2.getText()+"','windi')  where id_user=AES_ENCRYPT('"+lblUser.getText()+"','nur')");
            WindowInput.setVisible(false);
        }
}//GEN-LAST:event_BtnSimpanPassActionPerformed

private void BtnSimpanPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanPassKeyPressed
        Valid.pindah(evt,PassLama,PassBaru2);
}//GEN-LAST:event_BtnSimpanPassKeyPressed

    private void btnToolLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToolLabActionPerformed
        isTutup();
        FlayMenu.removeAll();
        FlayMenu.add(btnPermintaanLab);
        FlayMenu.add(btnLaboratorium);
        //if((var.getpermintaan_lab()==true)||(var.getperiksa_lab()==true)){
        //    btnPermintaanLab.setEnabled(true);
        //}else{
        //    btnPermintaanLab.setEnabled(var.getpermintaan_lab());
        //}
        //btnLaboratorium.setEnabled(var.getperiksa_lab());
        FlayMenu.setVisible(true);
    }//GEN-LAST:event_btnToolLabActionPerformed

    private void btnToolIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToolIGDActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgIGD igd=new DlgIGD(this,false);
        //igd.tampil();
        igd.emptTeks();
        igd.isCek();
        igd.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        igd.setLocationRelativeTo(PanelUtama);
        igd.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnToolIGDActionPerformed

    private void btnToolRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToolRadActionPerformed
        //btnPeriksaRadiologiActionPerformed(evt);
        isTutup();
        FlayMenu.removeAll();
        FlayMenu.add(btnPermintaanRadiologi);
        FlayMenu.add(btnPeriksaRadiologi);
        //if((var.getpermintaan_radiologi()==true)||(var.getperiksa_radiologi()==true)){
        //    btnPermintaanRadiologi.setEnabled(true);
        //}else{
        //    btnPermintaanRadiologi.setEnabled(var.getpermintaan_radiologi());
        //}
        //btnPeriksaRadiologi.setEnabled(var.getperiksa_radiologi());
        FlayMenu.setVisible(true);

    }//GEN-LAST:event_btnToolRadActionPerformed

    private void MnInfoKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInfoKamarActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        InformasiAnalisaKamin analisakamin=new InformasiAnalisaKamin(this,false);
        analisakamin.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        analisakamin.setLocationRelativeTo(PanelUtama);
        analisakamin.setVisible(true);
        analisakamin.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        analisakamin.setLocationRelativeTo(PanelUtama);
        analisakamin.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInfoKamarActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        Window[] wins = Window.getWindows();
        for (Window win : wins) {
            if (win instanceof JDialog) {
                //Panelmenu.repaint();
            }
        }
    }//GEN-LAST:event_formComponentResized

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
        Window[] wins = Window.getWindows();
        for (Window win : wins) {
            if (win instanceof JDialog) {
                win.setLocationRelativeTo(PanelUtama);
                win.toFront();
            }
        }

        setToolbar();
    }//GEN-LAST:event_formComponentMoved

    private void BtnToolJualObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnToolJualObatActionPerformed
        isTutup();
        FlayMenu.removeAll();
        FlayMenu.add(btnInputPenjualan);
        FlayMenu.add(btnDataPenjualan);
        FlayMenu.add(btnDaftarPermintaanResep);
        FlayMenu.add(btnResepObatDepan);
        //btnInputPenjualan.setEnabled(var.getpenjualan_obat());
        //btnDataPenjualan.setEnabled(var.getpenjualan_obat());
        FlayMenu.setVisible(true);
    }//GEN-LAST:event_BtnToolJualObatActionPerformed

    private void PanelWallMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelWallMouseMoved
        setToolbar();
        Window[] wins = Window.getWindows();
        for (Window win : wins) {
            if (win instanceof JDialog) {
                win.setLocationRelativeTo(PanelUtama);
                win.toFront();
            }
        }

    }//GEN-LAST:event_PanelWallMouseMoved

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setToolbar();
    }//GEN-LAST:event_formWindowOpened

    private void btnInputPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputPenjualanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenjualan penjualan=new DlgPenjualan(this,false);
        penjualan.isCek();
        penjualan.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        penjualan.setLocationRelativeTo(PanelUtama);
        penjualan.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnInputPenjualanActionPerformed

    private void btnDataPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPenjualanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgCariPenjualan penjualan=new DlgCariPenjualan(this,false);
        penjualan.emptTeks();
        penjualan.isCek();
        penjualan.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        penjualan.setLocationRelativeTo(PanelUtama);
        penjualan.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDataPenjualanActionPerformed

    private void MenuKeluarBtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuKeluarBtnKeluarActionPerformed
        int jawab=JOptionPane.showConfirmDialog(null, "Yakin anda mau keluar dari aplikasi ini ????","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if(jawab==JOptionPane.YES_OPTION){
            this.dispose();
            System.exit(0);
        }
    }//GEN-LAST:event_MenuKeluarBtnKeluarActionPerformed

    private void MnGantiPasswordBtnLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiPasswordBtnLogActionPerformed
        PassLama.setText("");
        Passbaru1.setText("");
        PassBaru2.setText("");
        WindowInput.setVisible(true);
    }//GEN-LAST:event_MnGantiPasswordBtnLogActionPerformed

    private void MnRegistrasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRegistrasiActionPerformed
         isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //kasirralan.kamarinap.reg.tampil();
        kasirralan.kamarinap.reg.emptTeks();
        kasirralan.kamarinap.reg.isCek();
        kasirralan.kamarinap.reg.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.kamarinap.reg.setLocationRelativeTo(PanelUtama);
        kasirralan.kamarinap.reg.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRegistrasiActionPerformed

    private void MnIgdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnIgdActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgIGD igd=new DlgIGD(this,false);
        //igd.tampil();
        igd.emptTeks();
        igd.isCek();
        igd.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        igd.setLocationRelativeTo(PanelUtama);
        igd.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnIgdActionPerformed

    private void MnJadwalDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJadwalDokterActionPerformed
        isTutup();
        DlgJadwal jadwal=new DlgJadwal(this,false);
        jadwal.emptTeks();
        jadwal.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        jadwal.setLocationRelativeTo(PanelUtama);
        jadwal.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJadwalDokterActionPerformed

    private void MnRujukMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukMasukActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRujukMasuk rujukmasuk=new DlgRujukMasuk(null,false);
        rujukmasuk.tampil();
        rujukmasuk.emptTeks();
        rujukmasuk.isCek();
        rujukmasuk.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rujukmasuk.setLocationRelativeTo(PanelUtama);
        rujukmasuk.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRujukMasukActionPerformed

    private void MnRujukKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukKeluarActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRujuk rujuk=new DlgRujuk(this,false);
        rujuk.tampil();
        rujuk.emptTeks();
        rujuk.isCek();
        rujuk.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rujuk.setLocationRelativeTo(PanelUtama);
        rujuk.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRujukKeluarActionPerformed

    private void MnTindakanRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTindakanRalanActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRawatJalan dlgrwjl=new DlgRawatJalan(null,false);
        dlgrwjl.isCek();
        dlgrwjl.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dlgrwjl.setLocationRelativeTo(internalFrame1);
        dlgrwjl.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTindakanRalanActionPerformed

    private void MnKamarInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKamarInapActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        kasirralan.kamarinap.isCek();
        kasirralan.kamarinap.emptTeks();
        //kasirralan.kamarinap.tampil();
        kasirralan.kamarinap.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.kamarinap.setLocationRelativeTo(PanelUtama);
        kasirralan.kamarinap.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnKamarInapActionPerformed

    private void MnDpjpRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDpjpRanapActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDpjp aplikasi=new DlgDpjp(this,false);
        aplikasi.isCek();
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDpjpRanapActionPerformed

    private void MnTindakanRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTindakanRanapActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        kasirralan.kamarinap.billing.rawatinap.tampilDr();
        kasirralan.kamarinap.billing.rawatinap.isCek();
        kasirralan.kamarinap.billing.rawatinap.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.kamarinap.billing.rawatinap.setLocationRelativeTo(PanelUtama);
        kasirralan.kamarinap.billing.rawatinap.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTindakanRanapActionPerformed

    private void MnOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnOperasiActionPerformed
        isTutup();
        DlgCariTagihanOperasi produsen=new DlgCariTagihanOperasi(this,false);
        //produsen.emptTeks();
        produsen.isCek();
        produsen.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        produsen.setLocationRelativeTo(PanelUtama);
        produsen.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnOperasiActionPerformed

    private void MnPemberianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemberianObatActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        kasirralan.kamarinap.billing.beriobat.tampilPO();
        kasirralan.kamarinap.billing.beriobat.isCek();
        kasirralan.kamarinap.billing.beriobat.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.kamarinap.billing.beriobat.setLocationRelativeTo(PanelUtama);
        kasirralan.kamarinap.billing.beriobat.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPemberianObatActionPerformed

    private void MnResepPulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnResepPulangActionPerformed
       isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgResepPulang reseppulang=new DlgResepPulang(this,false);
        reseppulang.tampil();
        reseppulang.isCek();
        reseppulang.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        reseppulang.setLocationRelativeTo(PanelUtama);
        reseppulang.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnResepPulangActionPerformed

    private void MnResepObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnResepObatActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgResepObat resep=new DlgResepObat(this,false);
        resep.tampil();
        resep.emptTeks();
        resep.isCek();
        resep.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        resep.setLocationRelativeTo(PanelUtama);
        resep.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnResepObatActionPerformed

    private void MnDietPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDietPasienActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPemberianDiet diet=new DlgPemberianDiet(this,false);
        diet.tampil();
        diet.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        diet.setLocationRelativeTo(PanelUtama);
        diet.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDietPasienActionPerformed

    private void MnPeriksaLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaLabActionPerformed
       isTutup();
        DlgCariPeriksaLab produsen=new DlgCariPeriksaLab(this,false);
        //produsen.emptTeks();
        produsen.isCek();
        produsen.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        produsen.setLocationRelativeTo(PanelUtama);
        produsen.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPeriksaLabActionPerformed

    private void MnPeriksaRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaRadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgCariPeriksaRadiologi produsen=new DlgCariPeriksaRadiologi(this,false);
        //produsen.emptTeks();
        produsen.isCek();
        produsen.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        produsen.setLocationRelativeTo(PanelUtama);
        produsen.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPeriksaRadActionPerformed

    private void MnKasirRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKasirRalanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        kasirralan.isCek();
        //kasirralan.tampilkasir();
        kasirralan.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.setLocationRelativeTo(PanelUtama);
        kasirralan.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnKasirRalanActionPerformed

    private void MnDepositPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDepositPasienActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDeposit deposit=new DlgDeposit(this,false);
        deposit.tampil();
        deposit.isCek();
        deposit.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        deposit.setLocationRelativeTo(PanelUtama);
        deposit.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDepositPasienActionPerformed

    private void MnPiutangPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPiutangPasienActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgLhtPiutang billing=new DlgLhtPiutang(this,false);
        billing.tampil();
        billing.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        billing.setLocationRelativeTo(PanelUtama);
        billing.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPiutangPasienActionPerformed

    private void MnDataDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataDokterActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        kasirralan.kamarinap.reg.dokter.dokter.emptTeks();
        kasirralan.kamarinap.reg.dokter.dokter.isCek();
        kasirralan.kamarinap.reg.dokter.dokter.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.kamarinap.reg.dokter.dokter.setLocationRelativeTo(PanelUtama);
        kasirralan.kamarinap.reg.dokter.dokter.setAlwaysOnTop(false);
        kasirralan.kamarinap.reg.dokter.dokter.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDataDokterActionPerformed

    private void MnDataPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataPetugasActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        kasirralan.billing.dlgrwjl.petugas.petugas.emptTeks();
        kasirralan.billing.dlgrwjl.petugas.petugas.isCek();
        kasirralan.billing.dlgrwjl.petugas.petugas.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.billing.dlgrwjl.petugas.petugas.setLocationRelativeTo(PanelUtama);
        kasirralan.billing.dlgrwjl.petugas.petugas.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDataPetugasActionPerformed

    private void MnDataPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataPegawaiActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if(var.getadmin()==true){
                penggajian.loadURL("http://"+prop.getProperty("HOSTHYBRIDWEB")+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"penggajian/login.php?act=login&usere=admin&passwordte=akusayangsamakamu");
            }else if(var.getmanajemen()==true){
                penggajian.loadURL("http://" +prop.getProperty("HOHOSTHYBRIDWEBST")+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"penggajian/login.php?act=login&usere=paijo&passwordte=mumet");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        penggajian.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        penggajian.setLocationRelativeTo(PanelUtama);
        penggajian.setVisible(true);

        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDataPegawaiActionPerformed

    private void MnBarcodePresensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodePresensiActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgBarcode barcode=new DlgBarcode(this,false);
        barcode.tampil();
        barcode.isCek();
        barcode.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        barcode.setLocationRelativeTo(PanelUtama);
        barcode.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBarcodePresensiActionPerformed

    private void MnJamPresensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJamPresensiActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgJamMasuk jammasuk=new DlgJamMasuk(this,false);
        jammasuk.isCek();
        jammasuk.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        jammasuk.setLocationRelativeTo(PanelUtama);
        jammasuk.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJamPresensiActionPerformed

    private void MnPresensiHarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPresensiHarianActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgHarian harian=new DlgHarian(this,false);
        harian.tampil();
        harian.isCek();
        harian.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        harian.setLocationRelativeTo(PanelUtama);
        harian.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPresensiHarianActionPerformed

    private void MnSidikJariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSidikJariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgSidikJari sidikjari=new DlgSidikJari(this,false);
        sidikjari.tampil();
        sidikjari.isCek();
        sidikjari.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        sidikjari.setLocationRelativeTo(PanelUtama);
        sidikjari.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSidikJariActionPerformed

    private void MnJadwalPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJadwalPegawaiActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgJadwalTambahan jadwal=new DlgJadwalTambahan(this,false);
        jadwal.isCek();
        jadwal.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        jadwal.setLocationRelativeTo(PanelUtama);
        jadwal.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJadwalPegawaiActionPerformed

    private void MnJdwlTambahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJdwlTambahanActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgJadwalTambahan jadwal=new DlgJadwalTambahan(this,false);
        jadwal.isCek();
        jadwal.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        jadwal.setLocationRelativeTo(PanelUtama);
        jadwal.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJdwlTambahanActionPerformed

    private void MnPresensiBulananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPresensiBulananActionPerformed
       this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgBulanan bulanan=new DlgBulanan(this,false);
        bulanan.isCek();
        bulanan.tampil();
        bulanan.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        bulanan.setLocationRelativeTo(PanelUtama);
        bulanan.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPresensiBulananActionPerformed

    private void MnTempPresensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTempPresensiActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgTemporaryPresensi temporary=new DlgTemporaryPresensi(this,false);
        temporary.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        temporary.setLocationRelativeTo(PanelUtama);
        temporary.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTempPresensiActionPerformed

    private void MnRekapKehadiranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapKehadiranActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgKehadiran hadir=new DlgKehadiran(this,false);
        hadir.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        hadir.setLocationRelativeTo(PanelUtama);
        hadir.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapKehadiranActionPerformed

    private void MnPresensiHarian1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPresensiHarian1ActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if(var.getadmin()==true){
                penggajian.loadURL("http://"+prop.getProperty("HOSTHYBRIDWEB")+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"penggajian/login.php?act=login&usere=admin&passwordte=akusayangsamakamu");
            }else if(var.getmanajemen()==true){
                penggajian.loadURL("http://"+prop.getProperty("HOSTHYBRIDWEB")+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"penggajian/login.php?act=login&usere=paijo&passwordte=mumet");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        penggajian.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        penggajian.setLocationRelativeTo(PanelUtama);
        penggajian.setVisible(true);

        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPresensiHarian1ActionPerformed

    private void MnIndustriFarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnIndustriFarActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgIndustriFarmasi suplier=new DlgIndustriFarmasi(this,false);
        suplier.isCek();
        suplier.emptTeks();
        suplier.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        suplier.setLocationRelativeTo(PanelUtama);
        suplier.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnIndustriFarActionPerformed

    private void MnSuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuplierActionPerformed
       isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSuplier suplier=new DlgSuplier(this,false);
        suplier.isCek();
        suplier.emptTeks();
        suplier.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        suplier.setLocationRelativeTo(PanelUtama);
        suplier.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSuplierActionPerformed

    private void MnSatuanBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSatuanBrgActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        kasirralan.billing.dlgobt.barang.satuan.satuan.isCek();
        kasirralan.billing.dlgobt.barang.satuan.satuan.emptTeks();
        kasirralan.billing.dlgobt.barang.satuan.satuan.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.billing.dlgobt.barang.satuan.satuan.setLocationRelativeTo(PanelUtama);
        kasirralan.billing.dlgobt.barang.satuan.satuan.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSatuanBrgActionPerformed

    private void MnKonvSatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKonvSatActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgKonversi konversi=new DlgKonversi(this,false);
        konversi.isCek();
        konversi.emptTeks();
        konversi.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        konversi.setLocationRelativeTo(PanelUtama);
        konversi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnKonvSatActionPerformed

    private void MnJnsObtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJnsObtActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        kasirralan.billing.dlgobt.barang.jenis.jenis.isCek();
        kasirralan.billing.dlgobt.barang.jenis.jenis.emptTeks();
        kasirralan.billing.dlgobt.barang.jenis.jenis.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.billing.dlgobt.barang.jenis.jenis.setLocationRelativeTo(PanelUtama);
        kasirralan.billing.dlgobt.barang.jenis.jenis.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJnsObtActionPerformed

    private void MnDataObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataObatActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        kasirralan.billing.dlgobt.barang.emptTeks();
        kasirralan.billing.dlgobt.barang.isCek();
        kasirralan.billing.dlgobt.barang.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.billing.dlgobt.barang.setLocationRelativeTo(PanelUtama);
        kasirralan.billing.dlgobt.barang.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDataObatActionPerformed

    private void MnStokOpnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStokOpnameActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgInputStok opname=new DlgInputStok(this,false);
        opname.tampil();
        opname.isCek();
        opname.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        opname.setLocationRelativeTo(PanelUtama);
        opname.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnStokOpnameActionPerformed

    private void MnMutasiObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMutasiObatActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgMutasiBarang aplikasi=new DlgMutasiBarang(this,false);
        aplikasi.isCek();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnMutasiObatActionPerformed

    private void MnStokObtPxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStokObtPxActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgStokPasien opname=new DlgStokPasien(this,false);
        opname.isCek();
        opname.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        opname.setLocationRelativeTo(PanelUtama);
        opname.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnStokObtPxActionPerformed

    private void MnPengadaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPengadaanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPembelian pembelian=new DlgPembelian(this,false);
        pembelian.isCek();
        pembelian.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        pembelian.setLocationRelativeTo(PanelUtama);
        pembelian.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPengadaanActionPerformed

    private void MnPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemesananActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPemesanan pembelian=new DlgPemesanan(this,false);
        pembelian.isCek();
        pembelian.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        pembelian.setLocationRelativeTo(PanelUtama);
        pembelian.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPemesananActionPerformed

    private void MnPenjualanObtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenjualanObtActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenjualan penjualan=new DlgPenjualan(this,false);
        penjualan.isCek();
        penjualan.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        penjualan.setLocationRelativeTo(PanelUtama);
        penjualan.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPenjualanObtActionPerformed

    private void MnStokOpname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStokOpname1ActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPiutang piutang=new DlgPiutang(this,false);
        piutang.emptTeks();
        piutang.isCek();
        piutang.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        piutang.setLocationRelativeTo(PanelUtama);
        piutang.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnStokOpname1ActionPerformed

    private void MnReturKeSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnReturKeSupActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgReturBeli returbeli=new DlgReturBeli(this,false);
        returbeli.isCek();
        returbeli.emptTeks();
        returbeli.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        returbeli.setLocationRelativeTo(PanelUtama);
        returbeli.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnReturKeSupActionPerformed

    private void MnReturPembActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnReturPembActionPerformed
        var.setform("DlgReturJual");
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgReturJual returjual=new DlgReturJual(this,false);
        returjual.emptTeks();
        returjual.isCek();
        returjual.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        returjual.setLocationRelativeTo(PanelUtama);
        returjual.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnReturPembActionPerformed

    private void MnReturObtRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnReturObtRanapActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgReturObatPasien returpasien=new DlgReturObatPasien(this,false);
        returpasien.isCek();
        returpasien.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        returpasien.setLocationRelativeTo(PanelUtama);
        returpasien.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnReturObtRanapActionPerformed

    private void MnReturPiutangPembActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnReturPiutangPembActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgReturPiutang returpiutang=new DlgReturPiutang(this,false);
        returpiutang.isCek();
        returpiutang.emptTeks();
        returpiutang.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        returpiutang.setLocationRelativeTo(PanelUtama);
        returpiutang.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnReturPiutangPembActionPerformed

    private void MnKeuntunganPenjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKeuntunganPenjActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgProyeksiJual projul=new DlgProyeksiJual(this,false);
        projul.isCek();
        projul.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        projul.setLocationRelativeTo(PanelUtama);
        projul.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnKeuntunganPenjActionPerformed

    private void MnKeuntBeriObtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKeuntBeriObtActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgProyeksiBeriObat projul=new DlgProyeksiBeriObat(this,false);
        projul.isCek();
        projul.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        projul.setLocationRelativeTo(PanelUtama);
        projul.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnKeuntBeriObtActionPerformed

    private void MnSirkulasiObtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSirkulasiObtActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSirkulasiBarang sirkulasi=new DlgSirkulasiBarang(this,false);
        sirkulasi.isCek();
        sirkulasi.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        sirkulasi.setLocationRelativeTo(PanelUtama);
        sirkulasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSirkulasiObtActionPerformed

    private void MnRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRiwayatBarangMedis sirkulasi=new DlgRiwayatBarangMedis(this,false);
        sirkulasi.isCek();
        sirkulasi.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        sirkulasi.setLocationRelativeTo(PanelUtama);
        sirkulasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRiwayatActionPerformed

    private void MnDaruratStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDaruratStokActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDaruratStok aplikasi=new DlgDaruratStok(this,false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDaruratStokActionPerformed

    private void MnSatuanBrgNonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSatuanBrgNonActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        kasirralan.billing.dlgobt.barang.satuan.satuan.isCek();
        kasirralan.billing.dlgobt.barang.satuan.satuan.emptTeks();
        kasirralan.billing.dlgobt.barang.satuan.satuan.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.billing.dlgobt.barang.satuan.satuan.setLocationRelativeTo(PanelUtama);
        kasirralan.billing.dlgobt.barang.satuan.satuan.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSatuanBrgNonActionPerformed

    private void MnJnsBrgNonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJnsBrgNonActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJenisIPSRS aplikasi=new DlgJenisIPSRS(this,false);
        aplikasi.isCek();
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJnsBrgNonActionPerformed

    private void MnDataBrgNonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataBrgNonActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBarangIPSRS ipsrs=new DlgBarangIPSRS(this,false);
        ipsrs.emptTeks();
        ipsrs.onCari();
        ipsrs.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        ipsrs.setLocationRelativeTo(PanelUtama);
        ipsrs.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDataBrgNonActionPerformed

    private void MnSupNonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSupNonActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSuplierIPSRS suplier=new DlgSuplierIPSRS(this,false);
        suplier.isCek();
        suplier.emptTeks();
        suplier.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        suplier.setLocationRelativeTo(PanelUtama);
        suplier.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSupNonActionPerformed

    private void MnPengadaanbrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPengadaanbrgActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPembelianIPSRS pembelian=new DlgPembelianIPSRS(this,false);
        pembelian.isCek();
        pembelian.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        pembelian.setLocationRelativeTo(PanelUtama);
        pembelian.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPengadaanbrgActionPerformed

    private void MnStokKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStokKeluarActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPengeluaranIPSRS pengeluaran=new DlgPengeluaranIPSRS(this,false);
        pengeluaran.isCek();
        pengeluaran.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        pengeluaran.setLocationRelativeTo(PanelUtama);
        pengeluaran.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnStokKeluarActionPerformed

    private void MnBiayaPengadaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBiayaPengadaanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBiayaHarianIPSRS rhkeluaripsrs=new DlgRBiayaHarianIPSRS(this,false);
        rhkeluaripsrs.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rhkeluaripsrs.setLocationRelativeTo(PanelUtama);
        rhkeluaripsrs.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBiayaPengadaanActionPerformed

    private void MnRekapPengadaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapPengadaanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRHPembelianIPSRS rhipsrs=new DlgRHPembelianIPSRS(this,false);
        rhipsrs.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rhipsrs.setLocationRelativeTo(PanelUtama);
        rhipsrs.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapPengadaanActionPerformed

    private void MnRekapStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapStokActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRHPengeluaranIPSRS rhkeluaripsrs=new DlgRHPengeluaranIPSRS(this,false);
        rhkeluaripsrs.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rhkeluaripsrs.setLocationRelativeTo(PanelUtama);
        rhkeluaripsrs.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapStokActionPerformed

    private void MnHrDokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHrDokActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBTindakanPoli rbpoli=new DlgRBTindakanPoli(this,false);
        rbpoli.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbpoli.setLocationRelativeTo(PanelUtama);
        rbpoli.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnHrDokActionPerformed

    private void MnHrKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHrKamarActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBTindakanKamar rbpoli=new DlgRBTindakanKamar(this,false);
        rbpoli.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbpoli.setLocationRelativeTo(PanelUtama);
        rbpoli.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnHrKamarActionPerformed

    private void MnHrDokRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHrDokRalanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBTindakanDokter rbpoli=new DlgRBTindakanDokter(this,false);
        rbpoli.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbpoli.setLocationRelativeTo(PanelUtama);
        rbpoli.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnHrDokRalanActionPerformed

    private void MnLapObtPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapObtPoliActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBObatPoli rbpoli=new DlgRBObatPoli(this,false);
        rbpoli.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbpoli.setLocationRelativeTo(PanelUtama);
        rbpoli.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapObtPoliActionPerformed

    private void MnObtKmrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnObtKmrActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBObatBangsal rbobatbangsal=new DlgRBObatBangsal(this,false);
        rbobatbangsal.isCek();
        rbobatbangsal.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbobatbangsal.setLocationRelativeTo(PanelUtama);
        rbobatbangsal.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnObtKmrActionPerformed

    private void MnObtDokRlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnObtDokRlnActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBObatDokterRalan rbpoli=new DlgRBObatDokterRalan(this,false);
        rbpoli.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbpoli.setLocationRelativeTo(PanelUtama);
        rbpoli.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnObtDokRlnActionPerformed

    private void MnObtDokRnpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnObtDokRnpActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBObatDokterRanap rbpoli=new DlgRBObatDokterRanap(this,false);
        rbpoli.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbpoli.setLocationRelativeTo(PanelUtama);
        rbpoli.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnObtDokRnpActionPerformed

    private void MnObtDokRspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnObtDokRspActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBObatDokterPeresep rbpoli=new DlgRBObatDokterPeresep(this,false);
        rbpoli.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbpoli.setLocationRelativeTo(PanelUtama);
        rbpoli.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnObtDokRspActionPerformed

    private void MnObtCrByrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnObtCrByrActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBObatPercaraBayar rbpoli=new DlgRBObatPercaraBayar(this,false);
        rbpoli.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbpoli.setLocationRelativeTo(PanelUtama);
        rbpoli.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnObtCrByrActionPerformed

    private void MnDetJMDokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDetJMDokActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDetailJMDokter rhtindakandokter=new DlgDetailJMDokter(this,false);
        rhtindakandokter.isCek();
        rhtindakandokter.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rhtindakandokter.setLocationRelativeTo(PanelUtama);
        rhtindakandokter.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDetJMDokActionPerformed

    private void MnHrDokAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHrDokAllActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRHJmDokter rhtindakandokter=new DlgRHJmDokter(this,false);
        rhtindakandokter.isCek();
        rhtindakandokter.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rhtindakandokter.setLocationRelativeTo(PanelUtama);
        rhtindakandokter.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnHrDokAllActionPerformed

    private void MnBulananDokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBulananDokActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBJmDokter rbtindakandokter=new DlgRBJmDokter(this,false);
        rbtindakandokter.isCek();
        rbtindakandokter.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbtindakandokter.setLocationRelativeTo(PanelUtama);
        rbtindakandokter.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBulananDokActionPerformed

    private void MnHrParamedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHrParamedisActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRHJmParamedis rhtindakanparamedis=new DlgRHJmParamedis(this,false);
        rhtindakanparamedis.isCek();
        rhtindakanparamedis.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rhtindakanparamedis.setLocationRelativeTo(PanelUtama);
        rhtindakanparamedis.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnHrParamedisActionPerformed

    private void MnBlnParamedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBlnParamedisActionPerformed
       isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBJmParamedis rbtindakanparamedis=new DlgRBJmParamedis(this,false);
        rbtindakanparamedis.isCek();
        rbtindakanparamedis.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbtindakanparamedis.setLocationRelativeTo(PanelUtama);
        rbtindakanparamedis.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBlnParamedisActionPerformed

    private void MnHrSrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHrSrnActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRHJS rhjs=new DlgRHJS(this,false);
        rhjs.isCek();
        rhjs.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rhjs.setLocationRelativeTo(PanelUtama);
        rhjs.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnHrSrnActionPerformed

    private void MnBlnSrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBlnSrnActionPerformed
       isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBJS rbjs=new DlgRBJS(this,false);
        rbjs.isCek();
        rbjs.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbjs.setLocationRelativeTo(PanelUtama);
        rbjs.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBlnSrnActionPerformed

    private void MnHrKsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHrKsoActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRHKSO rhkso=new DlgRHKSO(this,false);
        rhkso.isCek();
        rhkso.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rhkso.setLocationRelativeTo(PanelUtama);
        rhkso.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnHrKsoActionPerformed

    private void MnBlnKsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBlnKsoActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBKSO rbkso=new DlgRBKSO(this,false);
        rbkso.isCek();
        rbkso.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbkso.setLocationRelativeTo(PanelUtama);
        rbkso.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBlnKsoActionPerformed

    private void MnHrMnjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHrMnjActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRHMenejemen rhmenejemen=new DlgRHMenejemen(this,false);
        rhmenejemen.isCek();
        rhmenejemen.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rhmenejemen.setLocationRelativeTo(PanelUtama);
        rhmenejemen.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnHrMnjActionPerformed

    private void MnBlnMnjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBlnMnjActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBMenejemen rbmenejemen=new DlgRBMenejemen(this,false);
        rbmenejemen.isCek();
        rbmenejemen.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbmenejemen.setLocationRelativeTo(PanelUtama);
        rbmenejemen.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBlnMnjActionPerformed

    private void MnHrBhpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHrBhpActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRHPaketBHP rhpaketbhp=new DlgRHPaketBHP(this,false);
        rhpaketbhp.isCek();
        rhpaketbhp.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rhpaketbhp.setLocationRelativeTo(PanelUtama);
        rhpaketbhp.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnHrBhpActionPerformed

    private void MnBlnBhpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBlnBhpActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBPaketBHP rbpaketbhp=new DlgRBPaketBHP(this,false);
        rbpaketbhp.isCek();
        rbpaketbhp.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbpaketbhp.setLocationRelativeTo(PanelUtama);
        rbpaketbhp.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBlnBhpActionPerformed

    private void MnFreeVstDokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFreeVstDokActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgFeeVisitDokter feevisitdokter=new DlgFeeVisitDokter(this,false);
        feevisitdokter.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        feevisitdokter.setLocationRelativeTo(PanelUtama);
        feevisitdokter.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnFreeVstDokActionPerformed

    private void MnFreeBcEkgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFreeBcEkgActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgFeeBacaanEKG feebacaanekg=new DlgFeeBacaanEKG(this,false);
        feebacaanekg.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        feebacaanekg.setLocationRelativeTo(PanelUtama);
        feebacaanekg.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnFreeBcEkgActionPerformed

    private void MnFreeRujRotgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFreeRujRotgActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgFeeRujukanRontgen feerujukanrontgen=new DlgFeeRujukanRontgen(this,false);
        feerujukanrontgen.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        feerujukanrontgen.setLocationRelativeTo(PanelUtama);
        feerujukanrontgen.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnFreeRujRotgActionPerformed

    private void MnFreeRujRnpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFreeRujRnpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnFreeRujRnpActionPerformed

    private void MnFreePrkRlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFreePrkRlnActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgFeePeriksaRalan feeperiksaralan=new DlgFeePeriksaRalan(this,false);
        feeperiksaralan.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        feeperiksaralan.setLocationRelativeTo(PanelUtama);
        feeperiksaralan.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnFreePrkRlnActionPerformed

    private void MnLapPembRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapPembRalanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPembayaranRalan billing=new DlgPembayaranRalan(this,false);
        billing.tampil();
        billing.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        billing.setLocationRelativeTo(PanelUtama);
        billing.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapPembRalanActionPerformed

    private void MnLapPembRnpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapPembRnpActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPembayaranRanap billing=new DlgPembayaranRanap(this,false);
        billing.tampil();
        billing.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        billing.setLocationRelativeTo(PanelUtama);
        billing.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapPembRnpActionPerformed

    private void MnRkpPmbRlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRkpPmbRlnActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPembayaranRalanPerHari rhkeluaripsrs=new DlgPembayaranRalanPerHari(this,false);
        rhkeluaripsrs.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rhkeluaripsrs.setLocationRelativeTo(PanelUtama);
        rhkeluaripsrs.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRkpPmbRlnActionPerformed

    private void MnRkpPmbRnpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRkpPmbRnpActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPembyaranRanapPerhari rhkeluaripsrs=new DlgPembyaranRanapPerhari(this,false);
        rhkeluaripsrs.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rhkeluaripsrs.setLocationRelativeTo(PanelUtama);
        rhkeluaripsrs.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRkpPmbRnpActionPerformed

    private void MnLapTagMskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapTagMskActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgLhtBiaya billing=new DlgLhtBiaya(this,false);
        billing.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        billing.setLocationRelativeTo(PanelUtama);
        billing.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapTagMskActionPerformed

    private void MnLapTmbBiayaPxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapTmbBiayaPxActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDetailTambahan pembelian=new DlgDetailTambahan(this,false);
        pembelian.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        pembelian.setLocationRelativeTo(PanelUtama);
        pembelian.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapTmbBiayaPxActionPerformed

    private void MnLapPotBiayaPxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapPotBiayaPxActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDetailPotongan pembelian=new DlgDetailPotongan(this,false);
        pembelian.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        pembelian.setLocationRelativeTo(PanelUtama);
        pembelian.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapPotBiayaPxActionPerformed

    private void MnLapDepositPxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapDepositPxActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        keuangan.DlgDeposit deposit=new keuangan.DlgDeposit(this,false);
        deposit.tampil();
        deposit.isCek();
        deposit.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        deposit.setLocationRelativeTo(PanelUtama);
        deposit.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapDepositPxActionPerformed

    private void MnLapUangShiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapUangShiftActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRekapPerShift aplikasi=new DlgRekapPerShift(this,false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapUangShiftActionPerformed

    private void MnLapPaymentPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapPaymentPointActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPaymentPoint aplikasi=new DlgPaymentPoint(this,false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapPaymentPointActionPerformed

    private void MnLapIcd9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapIcd9ActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgICD9 aplikasi=new DlgICD9(this,false);
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapIcd9ActionPerformed

    private void MnLapIcd10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapIcd10ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        kasirralan.kamarinap.billing.beriobat.dlgobtpny.penyakit.penyakit.isCek();
        kasirralan.kamarinap.billing.beriobat.dlgobtpny.penyakit.penyakit.emptTeks();
        kasirralan.kamarinap.billing.beriobat.dlgobtpny.penyakit.penyakit.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.kamarinap.billing.beriobat.dlgobtpny.penyakit.penyakit.setLocationRelativeTo(PanelUtama);
        kasirralan.kamarinap.billing.beriobat.dlgobtpny.penyakit.penyakit.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapIcd10ActionPerformed

    private void MnLapObtPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapObtPenyakitActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgObatPenyakit obatpenyakit=new DlgObatPenyakit(this,false);
        obatpenyakit.isCek();
        obatpenyakit.emptTeks();
        obatpenyakit.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        obatpenyakit.setLocationRelativeTo(PanelUtama);
        obatpenyakit.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapObtPenyakitActionPerformed

    private void MnLapKjgRlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapKjgRlnActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgKunjunganRalan aplikasi=new DlgKunjunganRalan(this,false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapKjgRlnActionPerformed

    private void MnLapKjgRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapKjgRanapActionPerformed
         isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgKunjunganRanap aplikasi=new DlgKunjunganRanap(this,false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLapKjgRanapActionPerformed

    private void MnSensusHrPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSensusHrPoliActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSensusHarianPoli aplikasi=new DlgSensusHarianPoli(this,false);
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSensusHrPoliActionPerformed

    private void MnBantuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBantuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnBantuanActionPerformed

    private void MnUcapanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUcapanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnUcapanActionPerformed

    private void MnDokumentasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumentasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnDokumentasiActionPerformed

    private void MnTarifKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTarifKamarActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        InformasiKamar belum=new InformasiKamar(this,true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTarifKamarActionPerformed

    private void MnTarifRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTarifRalanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJnsPerawatanRalan form=new DlgJnsPerawatanRalan(null,false);
        form.emptTeks();
        form.isCek();
        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTarifRalanActionPerformed

    private void MnTarifRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTarifRanapActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        kasirralan.kamarinap.billing.rawatinap.perawatan.perawatan.emptTeks();
        kasirralan.kamarinap.billing.rawatinap.perawatan.perawatan.isCek();
        kasirralan.kamarinap.billing.rawatinap.perawatan.perawatan.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.kamarinap.billing.rawatinap.perawatan.perawatan.setLocationRelativeTo(PanelUtama);
        kasirralan.kamarinap.billing.rawatinap.perawatan.perawatan.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTarifRanapActionPerformed

    private void MnTarifLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTarifLabActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJnsPerawatanLab tariflab=new DlgJnsPerawatanLab(this,false);
        tariflab.emptTeks();
        tariflab.isCek();
        tariflab.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        tariflab.setLocationRelativeTo(PanelUtama);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTarifLabActionPerformed

    private void MnTarifRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTarifRadiologiActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJnsPerawatanRadiologi tarifrad=new DlgJnsPerawatanRadiologi(this,false);
        tarifrad.emptTeks();
        tarifrad.isCek();
        tarifrad.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        tarifrad.setLocationRelativeTo(PanelUtama);
        tarifrad.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTarifRadiologiActionPerformed

    private void MnTarifOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTarifOperasiActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        InformasiTarifOperasi belum=new InformasiTarifOperasi(this,true);
        belum.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        belum.setLocationRelativeTo(PanelUtama);
        belum.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTarifOperasiActionPerformed

    private void MnAkunRekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAkunRekActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRekening rekening=new DlgRekening(this,false);
        rekening.tampil();
        rekening.isCek();
        rekening.emptTeks();
        rekening.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rekening.setLocationRelativeTo(PanelUtama);
        rekening.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnAkunRekActionPerformed

    private void MnRekThnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekThnActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRekeningTahun rekeningtahun=new DlgRekeningTahun(this,false);
        rekeningtahun.tampil();
        rekeningtahun.isCek();
        rekeningtahun.emptTeks();
        rekeningtahun.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rekeningtahun.setLocationRelativeTo(PanelUtama);
        rekeningtahun.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekThnActionPerformed

    private void MnAkunPiutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAkunPiutangActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgAkunPiutang form=new DlgAkunPiutang(this,false);
        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnAkunPiutangActionPerformed

    private void MnAkunBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAkunBayarActionPerformed
    }//GEN-LAST:event_MnAkunBayarActionPerformed

    private void MnPengaturanRekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPengaturanRekActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPengaturanRekening aplikasi=new DlgPengaturanRekening(this,false);
        aplikasi.isCek();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPengaturanRekActionPerformed

    private void MnPengeluaranHrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPengeluaranHrActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPengeluaranHarian pembelian=new DlgPengeluaranHarian(this,false);
        pembelian.emptTeks();
        pembelian.isCek();
        pembelian.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        pembelian.setLocationRelativeTo(PanelUtama);
        pembelian.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPengeluaranHrActionPerformed

    private void MnPemasukanlainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemasukanlainActionPerformed
       isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPemasukanLain aplikasi=new DlgPemasukanLain(this,false);
        aplikasi.isCek();
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPemasukanlainActionPerformed

    private void MnPiutangPxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPiutangPxActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgLhtPiutang billing=new DlgLhtPiutang(this,false);
        billing.tampil();
        billing.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        billing.setLocationRelativeTo(PanelUtama);
        billing.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPiutangPxActionPerformed

    private void MnRincPiutangPxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRincPiutangPxActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRincianPiutangPasien billing=new DlgRincianPiutangPasien(this,false);
        billing.tampil();
        billing.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        billing.setLocationRelativeTo(PanelUtama);
        billing.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRincPiutangPxActionPerformed

    private void MnPiutangBlmLnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPiutangBlmLnsActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPiutangBelumLunas rbpaketbhp=new DlgPiutangBelumLunas(this,false);
        rbpaketbhp.tampil();
        rbpaketbhp.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbpaketbhp.setLocationRelativeTo(PanelUtama);
        rbpaketbhp.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPiutangBlmLnsActionPerformed

    private void MnByrPiutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnByrPiutangActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBayarPiutang bayarpiutang=new DlgBayarPiutang(this,false);
        bayarpiutang.tampil();
        bayarpiutang.emptTeks();
        bayarpiutang.isCek();
        bayarpiutang.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        bayarpiutang.setLocationRelativeTo(PanelUtama);
        bayarpiutang.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnByrPiutangActionPerformed

    private void MnHtgObtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHtgObtActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgHutangObatBelumLunas form=new DlgHutangObatBelumLunas(this,false);
        form.tampil();
        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnHtgObtActionPerformed

    private void MnByrPsnObtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnByrPsnObtActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBayarPemesanan bayarpesan=new DlgBayarPemesanan(this,false);
        bayarpesan.tampil();
        bayarpesan.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        bayarpesan.setLocationRelativeTo(PanelUtama);
        bayarpesan.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnByrPsnObtActionPerformed

    private void MnPostingJurnalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPostingJurnalActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJurnal jurnal=new DlgJurnal(this,false);
        jurnal.tampil();
        jurnal.isCek();
        jurnal.emptTeks();
        jurnal.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        jurnal.setLocationRelativeTo(PanelUtama);
        jurnal.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPostingJurnalActionPerformed

    private void MnJurnalHrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJurnalHrActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJurnalHarian jh=new DlgJurnalHarian(this,false);
        jh.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        jh.setLocationRelativeTo(PanelUtama);
        jh.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJurnalHrActionPerformed

    private void MnBukuBesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBukuBesarActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBubes bubes=new DlgBubes(this,false);
        bubes.isCek();
        bubes.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        bubes.setLocationRelativeTo(PanelUtama);
        bubes.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBukuBesarActionPerformed

    private void MnCashFlowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCashFlowActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgCashflow bubes=new DlgCashflow(this,false);
        bubes.isCek();
        bubes.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        bubes.setLocationRelativeTo(PanelUtama);
        bubes.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCashFlowActionPerformed

    private void MnKeuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKeuActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgLabaRugi labrug=new DlgLabaRugi(this,false);
        labrug.isCek();
        labrug.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        labrug.setLocationRelativeTo(PanelUtama);
        labrug.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnKeuActionPerformed

    private void MnRiwPesBpjsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwPesBpjsActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekRiwayatRujukanPCare form=new BPJSCekRiwayatRujukanPCare(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRiwPesBpjsActionPerformed

    private void MnCekNoRujPCareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekNoRujPCareActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekNoRujukanPCare form=new BPJSCekNoRujukanPCare(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCekNoRujPCareActionPerformed

    private void MnRefDiagBpjsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRefDiagBpjsActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiPenyakit form=new BPJSCekReferensiPenyakit(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRefDiagBpjsActionPerformed

    private void MnRefPlBpjsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRefPlBpjsActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiPoli form=new BPJSCekReferensiPoli(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRefPlBpjsActionPerformed

    private void MnRefFaskesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRefFaskesActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekReferensiFaskes form=new BPJSCekReferensiFaskes(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRefFaskesActionPerformed

    private void MnBridgingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBridgingActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSDataSEP form=new BPJSDataSEP(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBridgingActionPerformed

    private void MnMonitoringKlaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMonitoringKlaimActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSMonitoringKlaim form=new BPJSMonitoringKlaim(this,false);
        form.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnMonitoringKlaimActionPerformed

    private void MnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPasienActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        kasirralan.kamarinap.reg.pasien.emptTeks();
        kasirralan.kamarinap.reg.pasien.isCek();
        kasirralan.kamarinap.reg.pasien.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        kasirralan.kamarinap.reg.pasien.setLocationRelativeTo(PanelUtama);
        kasirralan.kamarinap.reg.pasien.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPasienActionPerformed

    private void MnKelahiranBayiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKelahiranBayiActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgIKBBayi lahir=new DlgIKBBayi(this,false);
        //lahir.tampil();
        lahir.isCek();
        lahir.emptTeks();
        lahir.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        lahir.setLocationRelativeTo(PanelUtama);
        lahir.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnKelahiranBayiActionPerformed

    private void MnPasienMeninggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPasienMeninggalActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPasienMati pasienmati=new DlgPasienMati(this,false);
        pasienmati.emptTeks();
        pasienmati.isCek();
        pasienmati.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        pasienmati.setLocationRelativeTo(PanelUtama);
        pasienmati.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPasienMeninggalActionPerformed

    private void MnDiagnosaPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiagnosaPasienActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgHutangObatBelumLunas form=new DlgHutangObatBelumLunas(this,false);
        form.tampil();
        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDiagnosaPasienActionPerformed

    private void MnRiwayatPerawatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatPerawatanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgResumePerawatan resume=new DlgResumePerawatan(this,false);
        resume.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        resume.setLocationRelativeTo(PanelUtama);
        resume.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRiwayatPerawatanActionPerformed

    private void MnRetBrksRmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRetBrksRmActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            retensi.loadURL("http://"+prop.getProperty("HOSTHYBRIDWEB")+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"medrec/login.php?act=login&usere=admin&passwordte=akusayangsamakamu");
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }

        retensi.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        retensi.setLocationRelativeTo(PanelUtama);
        retensi.setVisible(true);

        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRetBrksRmActionPerformed

    private void MnSetLambtPresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetLambtPresActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetKeterlambatan keterlambatan=new DlgSetKeterlambatan(this,false);
        keterlambatan.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        keterlambatan.setLocationRelativeTo(PanelUtama);
        keterlambatan.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSetLambtPresActionPerformed

    private void MnClosingKsrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnClosingKsrActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgClosingKasir ckas=new DlgClosingKasir(this,false);
        ckas.isCek();
        ckas.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        ckas.setLocationRelativeTo(PanelUtama);
        ckas.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnClosingKsrActionPerformed

    private void MnSetBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetBillingActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetNota aplikasi=new DlgSetNota(this,false);
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSetBillingActionPerformed

    private void MnSetRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetRMActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetRM aplikasi=new DlgSetRM(this,false);
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSetRMActionPerformed

    private void MnBiayaMskSklActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBiayaMskSklActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBiayaSekaliMasuk biayaharian=new DlgBiayaSekaliMasuk(this,false);
        biayaharian.emptTeks();
        biayaharian.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        biayaharian.setLocationRelativeTo(PanelUtama);
        biayaharian.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBiayaMskSklActionPerformed

    private void MnBiayaHarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBiayaHarianActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgBiayaHarian biayaharian=new DlgBiayaHarian(this,false);
        biayaharian.emptTeks();
        biayaharian.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        biayaharian.setLocationRelativeTo(PanelUtama);
        biayaharian.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBiayaHarianActionPerformed

    private void MnSetOtoRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetOtoRalanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetOtoRalan aplikasi=new DlgSetOtoRalan(this,false);
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSetOtoRalanActionPerformed

    private void MnSetPenggTrfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetPenggTrfActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetTarif aplikasi=new DlgSetTarif(this,false);
        aplikasi.emptTeks();
        aplikasi.tampil();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSetPenggTrfActionPerformed

    private void MnSetObtRnpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetObtRnpActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetHargaObatRanap aplikasi=new DlgSetHargaObatRanap(this,false);
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSetObtRnpActionPerformed

    private void MnSetHargaObtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetHargaObtActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetHarga setharga=new DlgSetHarga(this,false);
        setharga.emptTeks();
        setharga.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        setharga.setLocationRelativeTo(PanelUtama);
        setharga.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSetHargaObtActionPerformed

    private void MnDisplayAntrianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDisplayAntrianActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRunTeks runteks=new DlgRunTeks(this,false);
        runteks.emptTeks();
        runteks.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        runteks.setLocationRelativeTo(PanelUtama);
        runteks.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDisplayAntrianActionPerformed

    private void MnSetTrackerLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetTrackerLogActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenelusuranLogin rbpoli=new DlgPenelusuranLogin(this,false);
        rbpoli.isCek();
        rbpoli.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbpoli.setLocationRelativeTo(PanelUtama);
        rbpoli.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSetTrackerLogActionPerformed

    private void MnSetUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetUserActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgUser user=new DlgUser(this,false);
        //user.tampil();
        user.emptTeks();
        user.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        user.setLocationRelativeTo(PanelUtama);
        user.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSetUserActionPerformed

    private void MnSetEmbTusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetEmbTusActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetEmbalase ktginventaris=new DlgSetEmbalase(this,false);
        ktginventaris.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        ktginventaris.setLocationRelativeTo(PanelUtama);
        ktginventaris.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSetEmbTusActionPerformed

    private void MnSetHargaKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetHargaKamarActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetHargaKamar hargakamar=new DlgSetHargaKamar(this,false);
        hargakamar.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        hargakamar.setLocationRelativeTo(PanelUtama);
        hargakamar.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSetHargaKamarActionPerformed

    private void MnSetKmrInpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetKmrInpActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetKamarInap form=new DlgSetKamarInap(this,false);
        form.tampil();
        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSetKmrInpActionPerformed

    private void MnSetOtoLokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetOtoLokActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetOtoLokasi ktginventaris=new DlgSetOtoLokasi(this,false);
        ktginventaris.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        ktginventaris.setLocationRelativeTo(PanelUtama);
        ktginventaris.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSetOtoLokActionPerformed

    private void MnPenujangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenujangActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetPenjabLab aplikasi=new DlgSetPenjabLab(this,false);
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPenujangActionPerformed

    private void MnSetAplikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetAplikasiActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgSetAplikasi aplikasi=new DlgSetAplikasi(this,false);
        aplikasi.emptTeks();
        aplikasi.setSize(PanelUtama.getWidth(), PanelUtama.getHeight());
        aplikasi.setLocationRelativeTo(PanelUtama);
        aplikasi.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSetAplikasiActionPerformed

    private void btnReferensiPendaftaranMobileJKNActionPerformed(java.awt.event.ActionEvent evt) {
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBPaketBHP rbpaketbhp=new DlgRBPaketBHP(this,false);
        rbpaketbhp.isCek();
        rbpaketbhp.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        rbpaketbhp.setLocationRelativeTo(PanelUtama);
        rbpaketbhp.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void MnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAboutActionPerformed
        isTutup();
        DlgAbout About=new DlgAbout(this,true);
        About.setSize(PanelWall.getWidth(), PanelWall.getHeight());
        About.setLocationRelativeTo(PanelWall);
        About.setVisible(true);
    }//GEN-LAST:event_MnAboutActionPerformed

    private void btnPermintaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPermintaanLabActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgCariPermintaanLab form=new DlgCariPermintaanLab(this,false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPermintaanLabActionPerformed

    private void btnPermintaanRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPermintaanRadiologiActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgCariPermintaanRadiologi form=new DlgCariPermintaanRadiologi(this,false);
        form.isCek();
        form.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        form.setLocationRelativeTo(PanelUtama);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPermintaanRadiologiActionPerformed

    private void btnPeriksaRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeriksaRadiologiActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isTutup();
        DlgCariPeriksaRadiologi produsen=new DlgCariPeriksaRadiologi(this,false);
        produsen.isCek();
        produsen.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        produsen.setLocationRelativeTo(PanelUtama);
        produsen.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPeriksaRadiologiActionPerformed

    private void btnLaboratoriumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaboratoriumActionPerformed

        isTutup();
        DlgCariPeriksaLab produsen=new DlgCariPeriksaLab(this,false);
        produsen.isCek();
        produsen.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        produsen.setLocationRelativeTo(PanelUtama);
        produsen.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnLaboratoriumActionPerformed

    private void btnDaftarPermintaanResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaftarPermintaanResepActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgDaftarPermintaanResep daftar=new DlgDaftarPermintaanResep(null,false);
        daftar.emptTeks();
        daftar.isCek();
        daftar.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        daftar.setLocationRelativeTo(PanelUtama);
        daftar.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnDaftarPermintaanResepActionPerformed

    private void btnResepObatDepanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResepObatDepanActionPerformed
        isTutup();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgResepObat resep=new DlgResepObat(this,false);
        resep.tampil();
        resep.emptTeks();
        resep.isCek();
        resep.setSize(PanelUtama.getWidth(),PanelUtama.getHeight());
        resep.setLocationRelativeTo(PanelUtama);
        resep.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnResepObatDepanActionPerformed

    private void MnCekSKDPBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekSKDPBPJSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnCekSKDPBPJSActionPerformed

    private void MnRiwPesBpjs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwPesBpjs1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnRiwPesBpjs1ActionPerformed

    private void MnCekTglRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekTglRujukanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnCekTglRujukanActionPerformed

    private void MnCekNoRujRSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekNoRujRSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnCekNoRujRSActionPerformed

    private void MnCekRujKartuPCareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekRujKartuPCareActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnCekRujKartuPCareActionPerformed

    private void MnCekRujKartuRSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekRujKartuRSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnCekRujKartuRSActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new frmUtama().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnCancel;
    private widget.ButtonBig BtnClose;
    private widget.Button BtnClosePass;
    private widget.ButtonBig BtnLog;
    private widget.Button BtnLogin;
    private widget.Button BtnSimpanPass;
    private widget.ButtonBig BtnToolJualObat;
    private widget.ButtonBig BtnToolKamnap;
    private widget.ButtonBig BtnToolKasir;
    private widget.ButtonBig BtnToolReg;
    private javax.swing.JDialog DlgLogin;
    private usu.widget.glass.PanelGlass FlayMenu;
    private widget.MenuBar MenuBar;
    private javax.swing.JMenu MenuInvObat;
    private javax.swing.JMenu MenuJadwal;
    private javax.swing.JMenu MenuKasir;
    private javax.swing.JMenuItem MenuKeluar;
    private javax.swing.JMenu MenuKeuntungan;
    private javax.swing.JMenu MenuLapBulanan;
    private javax.swing.JMenu MenuLapBulanan1;
    private javax.swing.JMenu MenuLapHarian;
    private javax.swing.JMenu MenuLapObat;
    private javax.swing.JMenu MenuLapPemb;
    private javax.swing.JMenu MenuLapPykt;
    private javax.swing.JMenu MenuObat;
    private javax.swing.JMenu MenuPengaturan;
    private javax.swing.JMenu MenuPiutang;
    private javax.swing.JMenu MenuPresensi;
    private javax.swing.JMenu MenuRawatInap;
    private javax.swing.JMenu MenuRawatJalan;
    private javax.swing.JMenu MenuRekap;
    private javax.swing.JMenu MenuRekening;
    private javax.swing.JMenu MenuRetur;
    private javax.swing.JMenu MenuRujukan;
    private javax.swing.JMenu MenuSuplier;
    private javax.swing.JMenu MenuTarif;
    private javax.swing.JMenuItem MnAbout;
    private javax.swing.JMenuItem MnAkunBayar;
    private javax.swing.JMenuItem MnAkunPiutang;
    private javax.swing.JMenuItem MnAkunRek;
    private javax.swing.JMenuItem MnBantuan;
    private javax.swing.JMenuItem MnBarcodePresensi;
    private javax.swing.JMenuItem MnBiayaHarian;
    private javax.swing.JMenuItem MnBiayaMskSkl;
    private javax.swing.JMenuItem MnBiayaPengadaan;
    private javax.swing.JMenuItem MnBlnBhp;
    private javax.swing.JMenuItem MnBlnKso;
    private javax.swing.JMenuItem MnBlnMnj;
    private javax.swing.JMenuItem MnBlnParamedis;
    private javax.swing.JMenuItem MnBlnSrn;
    private javax.swing.JMenuItem MnBridging;
    private javax.swing.JMenuItem MnBukuBesar;
    private javax.swing.JMenuItem MnBulananDok;
    private javax.swing.JMenuItem MnByrPiutang;
    private javax.swing.JMenuItem MnByrPsnObt;
    private javax.swing.JMenuItem MnCashFlow;
    private javax.swing.JMenuItem MnCekNoRujPCare;
    private javax.swing.JMenuItem MnCekNoRujRS;
    private javax.swing.JMenuItem MnCekRujKartuPCare;
    private javax.swing.JMenuItem MnCekRujKartuRS;
    private javax.swing.JMenuItem MnCekSKDPBPJS;
    private javax.swing.JMenuItem MnCekTglRujukan;
    private javax.swing.JMenuItem MnClosingKsr;
    private javax.swing.JMenuItem MnDaruratStok;
    private javax.swing.JMenuItem MnDataBrgNon;
    private javax.swing.JMenuItem MnDataDokter;
    private javax.swing.JMenuItem MnDataObat;
    private javax.swing.JMenuItem MnDataPegawai;
    private javax.swing.JMenuItem MnDataPetugas;
    private javax.swing.JMenuItem MnDepositPasien;
    private javax.swing.JMenuItem MnDetJMDok;
    private javax.swing.JMenuItem MnDiagnosaPasien;
    private javax.swing.JMenuItem MnDietPasien;
    private javax.swing.JMenuItem MnDisplayAntrian;
    private javax.swing.JMenuItem MnDokumentasi;
    private javax.swing.JMenuItem MnDpjpRanap;
    private javax.swing.JMenuItem MnFreeBcEkg;
    private javax.swing.JMenuItem MnFreePrkRln;
    private javax.swing.JMenuItem MnFreeRujRnp;
    private javax.swing.JMenuItem MnFreeRujRotg;
    private javax.swing.JMenuItem MnFreeVstDok;
    private javax.swing.JMenuItem MnGantiPassword;
    private javax.swing.JMenuItem MnHrBhp;
    private javax.swing.JMenuItem MnHrDok;
    private javax.swing.JMenuItem MnHrDokAll;
    private javax.swing.JMenuItem MnHrDokRalan;
    private javax.swing.JMenuItem MnHrKamar;
    private javax.swing.JMenuItem MnHrKso;
    private javax.swing.JMenuItem MnHrMnj;
    private javax.swing.JMenuItem MnHrParamedis;
    private javax.swing.JMenuItem MnHrSrn;
    private javax.swing.JMenuItem MnHtgObt;
    private javax.swing.JMenuItem MnIgd;
    private javax.swing.JMenuItem MnIndustriFar;
    private javax.swing.JMenuItem MnInfoKamar;
    private javax.swing.JMenuItem MnJadwalDokter;
    private javax.swing.JMenuItem MnJadwalPegawai;
    private javax.swing.JMenuItem MnJamPresensi;
    private javax.swing.JMenuItem MnJdwlTambahan;
    private javax.swing.JMenuItem MnJnsBrgNon;
    private javax.swing.JMenuItem MnJnsObt;
    private javax.swing.JMenuItem MnJurnalHr;
    private javax.swing.JMenuItem MnKamarInap;
    private javax.swing.JMenuItem MnKasirRalan;
    private javax.swing.JMenuItem MnKelahiranBayi;
    private javax.swing.JMenuItem MnKeu;
    private javax.swing.JMenuItem MnKeuntBeriObt;
    private javax.swing.JMenuItem MnKeuntunganPenj;
    private javax.swing.JMenuItem MnKonvSat;
    private javax.swing.JMenuItem MnLapDepositPx;
    private javax.swing.JMenuItem MnLapIcd10;
    private javax.swing.JMenuItem MnLapIcd9;
    private javax.swing.JMenuItem MnLapKjgRanap;
    private javax.swing.JMenuItem MnLapKjgRln;
    private javax.swing.JMenuItem MnLapObtPenyakit;
    private javax.swing.JMenuItem MnLapObtPoli;
    private javax.swing.JMenuItem MnLapPaymentPoint;
    private javax.swing.JMenuItem MnLapPembRalan;
    private javax.swing.JMenuItem MnLapPembRnp;
    private javax.swing.JMenuItem MnLapPotBiayaPx;
    private javax.swing.JMenuItem MnLapTagMsk;
    private javax.swing.JMenuItem MnLapTmbBiayaPx;
    private javax.swing.JMenuItem MnLapUangShift;
    private javax.swing.JMenuItem MnLogin;
    private javax.swing.JMenuItem MnMonitoringKlaim;
    private javax.swing.JMenuItem MnMutasiObat;
    private javax.swing.JMenuItem MnObtCrByr;
    private javax.swing.JMenuItem MnObtDokRln;
    private javax.swing.JMenuItem MnObtDokRnp;
    private javax.swing.JMenuItem MnObtDokRsp;
    private javax.swing.JMenuItem MnObtKmr;
    private javax.swing.JMenuItem MnOperasi;
    private javax.swing.JMenuItem MnPasien;
    private javax.swing.JMenuItem MnPasienMeninggal;
    private javax.swing.JMenuItem MnPemasukanlain;
    private javax.swing.JMenuItem MnPemberianObat;
    private javax.swing.JMenuItem MnPemesanan;
    private javax.swing.JMenuItem MnPengadaan;
    private javax.swing.JMenuItem MnPengadaanbrg;
    private javax.swing.JMenuItem MnPengaturanRek;
    private javax.swing.JMenuItem MnPengeluaranHr;
    private javax.swing.JMenuItem MnPenjualanObt;
    private javax.swing.JMenuItem MnPenujang;
    private javax.swing.JMenuItem MnPeriksaLab;
    private javax.swing.JMenuItem MnPeriksaRad;
    private javax.swing.JMenuItem MnPiutangBlmLns;
    private javax.swing.JMenuItem MnPiutangPasien;
    private javax.swing.JMenuItem MnPiutangPx;
    private javax.swing.JMenuItem MnPostingJurnal;
    private javax.swing.JMenuItem MnPresensiBulanan;
    private javax.swing.JMenuItem MnPresensiHarian;
    private javax.swing.JMenuItem MnPresensiHarian1;
    private javax.swing.JMenuItem MnRefDiagBpjs;
    private javax.swing.JMenuItem MnRefFaskes;
    private javax.swing.JMenuItem MnRefPlBpjs;
    private javax.swing.JMenuItem MnRegistrasi;
    private javax.swing.JMenuItem MnRekThn;
    private javax.swing.JMenuItem MnRekapKehadiran;
    private javax.swing.JMenuItem MnRekapPengadaan;
    private javax.swing.JMenuItem MnRekapStok;
    private javax.swing.JMenuItem MnResepObat;
    private javax.swing.JMenuItem MnResepPulang;
    private javax.swing.JMenuItem MnRetBrksRm;
    private javax.swing.JMenuItem MnReturKeSup;
    private javax.swing.JMenuItem MnReturObtRanap;
    private javax.swing.JMenuItem MnReturPemb;
    private javax.swing.JMenuItem MnReturPiutangPemb;
    private javax.swing.JMenuItem MnRincPiutangPx;
    private javax.swing.JMenuItem MnRiwPesBpjs;
    private javax.swing.JMenuItem MnRiwPesBpjs1;
    private javax.swing.JMenuItem MnRiwayat;
    private javax.swing.JMenuItem MnRiwayatPerawatan;
    private javax.swing.JMenuItem MnRkpPmbRln;
    private javax.swing.JMenuItem MnRkpPmbRnp;
    private javax.swing.JMenuItem MnRujukKeluar;
    private javax.swing.JMenuItem MnRujukMasuk;
    private javax.swing.JMenuItem MnSatuanBrg;
    private javax.swing.JMenuItem MnSatuanBrgNon;
    private javax.swing.JMenuItem MnSensusHrPoli;
    private javax.swing.JMenuItem MnSetAplikasi;
    private javax.swing.JMenuItem MnSetBilling;
    private javax.swing.JMenuItem MnSetEmbTus;
    private javax.swing.JMenuItem MnSetHargaKamar;
    private javax.swing.JMenuItem MnSetHargaObt;
    private javax.swing.JMenuItem MnSetKmrInp;
    private javax.swing.JMenuItem MnSetLambtPres;
    private javax.swing.JMenuItem MnSetObtRnp;
    private javax.swing.JMenuItem MnSetOtoLok;
    private javax.swing.JMenuItem MnSetOtoRalan;
    private javax.swing.JMenuItem MnSetPenggTrf;
    private javax.swing.JMenuItem MnSetRM;
    private javax.swing.JMenuItem MnSetTrackerLog;
    private javax.swing.JMenuItem MnSetUser;
    private javax.swing.JMenuItem MnSidikJari;
    private javax.swing.JMenuItem MnSirkulasiObt;
    private javax.swing.JMenuItem MnStokKeluar;
    private javax.swing.JMenuItem MnStokObtPx;
    private javax.swing.JMenuItem MnStokOpname;
    private javax.swing.JMenuItem MnStokOpname1;
    private javax.swing.JMenuItem MnSupNon;
    private javax.swing.JMenuItem MnSuplier;
    private javax.swing.JMenuItem MnTarifKamar;
    private javax.swing.JMenuItem MnTarifLab;
    private javax.swing.JMenuItem MnTarifOperasi;
    private javax.swing.JMenuItem MnTarifRadiologi;
    private javax.swing.JMenuItem MnTarifRalan;
    private javax.swing.JMenuItem MnTarifRanap;
    private javax.swing.JMenuItem MnTempPresensi;
    private javax.swing.JMenuItem MnTindakanRalan;
    private javax.swing.JMenuItem MnTindakanRanap;
    private javax.swing.JMenuItem MnUcapan;
    private javax.swing.JPanel PanelUtama;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.TextBox PassBaru2;
    private widget.TextBox PassLama;
    private widget.TextBox Passbaru1;
    private javax.swing.JDialog WindowInput;
    private widget.ButtonBig btnDaftarPermintaanResep;
    private widget.ButtonBig btnDataPenjualan;
    private widget.ButtonBig btnInputPenjualan;
    private widget.ButtonBig btnLaboratorium;
    private widget.ButtonBig btnPeriksaRadiologi;
    private widget.ButtonBig btnPermintaanLab;
    private widget.ButtonBig btnPermintaanRadiologi;
    private widget.ButtonBig btnResepObatDepan;
    private widget.ButtonBig btnToolIGD;
    private widget.ButtonBig btnToolLab;
    private widget.ButtonBig btnToolRad;
    private widget.PasswordBox edAdmin;
    private widget.PasswordBox edPwd;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame6;
    private widget.Label jLabel10;
    private javax.swing.JLabel jLabel11;
    private widget.Label jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JMenu jMenuBantuan;
    private javax.swing.JMenu jMenuBridging;
    private javax.swing.JMenu jMenuFarmasi;
    private javax.swing.JMenu jMenuInventory;
    private javax.swing.JMenu jMenuKeu;
    private javax.swing.JMenu jMenuLaporan;
    private javax.swing.JMenu jMenuLayanan;
    private javax.swing.JMenu jMenuManajemen;
    private javax.swing.JMenu jMenuPasien;
    private javax.swing.JMenu jMenuSIMRSKhanza;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lblStts;
    private javax.swing.JLabel lblTgl;
    private javax.swing.JLabel lblUser;
    private usu.widget.glass.PanelGlass panelGlass1;
    private usu.widget.glass.PanelGlass panelJudul;
    private widget.ScrollPane scrollPane1;
    private widget.Tanggal tanggal;
    // End of variables declaration//GEN-END:variables

    public void isWall(){
        try{
            ps=koneksi.prepareStatement("select nama_instansi, alamat_instansi, kabupaten, propinsi, aktifkan, wallpaper,kontak,email,logo from setting");
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    jLabel8.setText(rs.getString(1));
                    this.setTitle("SIM "+rs.getString("nama_instansi"));
                    jLabel11.setText(rs.getString(2) +", "+rs.getString(3) +", "+rs.getString(4) +" ");
                    var.setnamars(rs.getString("nama_instansi"));
                    var.setalamatrs(rs.getString("alamat_instansi"));
                    var.setkabupatenrs(rs.getString("kabupaten"));
                    var.setpropinsirs(rs.getString("propinsi"));
                    var.setkontakrs(rs.getString("kontak"));
                    var.setemailrs(rs.getString("email"));

                    if(rs.getString(5).equals("Yes")){
                        Blob blob = rs.getBlob(6);
                        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(blob.getBytes(1, (int) (blob.length()))));
                        repaint();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : Silahkan Set Aplikasi "+e);
        }
    }

    private void isTutup() {
        FlayMenu.setVisible(false);
        var.setform("frmUtama");
        Window[] wins = Window.getWindows();
        for (Window win : wins) {
            if (win instanceof JDialog) {
                win.dispose();
            }
        }
    }

    private void setToolbar(){
        if(internalFrame1.getWidth()<(BtnToolReg.getWidth()+btnToolIGD.getWidth()+
                btnToolRad.getWidth()+BtnToolJualObat.getWidth()+BtnToolKamnap.getWidth()+
                BtnToolKasir.getWidth()+BtnLog.getWidth()+BtnClose.getWidth()+8)){
            internalFrame1.setSize(new Dimension(PanelUtama.getWidth(),90));
        }else{
            internalFrame1.setSize(new Dimension(PanelUtama.getWidth(),44));
        }
    }


}
