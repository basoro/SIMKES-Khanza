package simrskhanza;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import java.io.FileOutputStream;
import java.io.File;
import javax.swing.GroupLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileInputStream;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import fungsi.EnkripsiAES;
import java.util.Properties;
import java.sql.Connection;
import javax.swing.JFrame;

public class DlgSetKoneksi extends JFrame
{
    private static Connection connection;
    private String hostEn;
    private String databaseEn;
    private String portEn;
    private String userEn;
    private String pasEn;
    private String hostDe;
    private String databaseDe;
    private String portDe;
    private String userDe;
    private String pasDe;
    private final Properties prop;
    private static final MysqlDataSource dataSource;
    private JButton BtnTesKoneksi;
    private JTextField TDatabase;
    private JTextField THost;
    private JTextField TPassword;
    private JTextField TPort;
    private JTextField TUser;
    private JButton jButton1;
    private JButton jButton3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;

    public DlgSetKoneksi() {
        this.hostEn = "";
        this.databaseEn = "";
        this.portEn = "";
        this.userEn = "";
        this.pasEn = "";
        this.hostDe = "";
        this.databaseDe = "";
        this.portDe = "";
        this.userDe = "";
        this.pasDe = "";
        this.prop = new Properties();
        this.initComponents();
        try {
            this.prop.loadFromXML(new FileInputStream("setting/database.xml"));
        }
        catch (Exception e) {
            System.out.println("Notif Setting : " + e);
        }
        try {
            this.hostDe = this.prop.getProperty("HOST");
        }
        catch (Exception ex) {
            Logger.getLogger(DlgSetKoneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.databaseDe = this.prop.getProperty("DATABASE");
        }
        catch (Exception ex) {
            Logger.getLogger(DlgSetKoneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.portDe = this.prop.getProperty("PORT");
        }
        catch (Exception ex) {
            Logger.getLogger(DlgSetKoneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.userDe = this.prop.getProperty("USER");
        }
        catch (Exception ex) {
            Logger.getLogger(DlgSetKoneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.pasDe = this.prop.getProperty("PAS");
        }
        catch (Exception ex) {
            Logger.getLogger(DlgSetKoneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.THost.setText(this.hostDe);
        this.TDatabase.setText(this.databaseDe);
        this.TPort.setText(this.portDe);
        this.TUser.setText(this.userDe);
        this.TPassword.setText(this.pasDe);
    }

    private void initComponents() {
        this.THost = new JTextField();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.TDatabase = new JTextField();
        this.jLabel3 = new JLabel();
        this.TPort = new JTextField();
        this.jLabel4 = new JLabel();
        this.TUser = new JTextField();
        this.jLabel5 = new JLabel();
        this.jButton1 = new JButton();
        this.jButton3 = new JButton();
        this.TPassword = new JTextField();
        this.BtnTesKoneksi = new JButton();
        this.setDefaultCloseOperation(3);
        this.setForeground(Color.darkGray);
        this.THost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DlgSetKoneksi.this.THostActionPerformed(evt);
            }
        });
        this.jLabel1.setText("Host");
        this.jLabel2.setText("Database");
        this.TDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DlgSetKoneksi.this.TDatabaseActionPerformed(evt);
            }
        });
        this.jLabel3.setText("Port");
        this.TPort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DlgSetKoneksi.this.TPortActionPerformed(evt);
            }
        });
        this.jLabel4.setText("User");
        this.TUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DlgSetKoneksi.this.TUserActionPerformed(evt);
            }
        });
        this.jLabel5.setText("Password");
        this.jButton1.setText("Simpan");
        this.jButton1.setPreferredSize(new Dimension(91, 33));
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DlgSetKoneksi.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton3.setText("Run Aplikasi");
        this.jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DlgSetKoneksi.this.jButton3ActionPerformed(evt);
            }
        });
        this.TPassword.setText("JTextField");
        this.BtnTesKoneksi.setText("Tes Koneksi");
        this.BtnTesKoneksi.setPreferredSize(new Dimension(109, 33));
        this.BtnTesKoneksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DlgSetKoneksi.this.BtnTesKoneksiActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TUser, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TPort, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(THost, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(BtnTesKoneksi)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(THost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(BtnTesKoneksi)
                    .addComponent(jButton3))
                .addContainerGap())
        );
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void THostActionPerformed(final ActionEvent evt) {
    }

    private void TDatabaseActionPerformed(final ActionEvent evt) {
    }

    private void TPortActionPerformed(final ActionEvent evt) {
    }

    private void TUserActionPerformed(final ActionEvent evt) {
    }

    private void jButton1ActionPerformed(final ActionEvent evt) {
        try {
            this.hostEn = EnkripsiAES.encrypt(this.THost.getText());
            this.databaseEn = EnkripsiAES.encrypt(this.TDatabase.getText());
            this.portEn = this.TPort.getText();
            this.userEn = EnkripsiAES.encrypt(this.TUser.getText());
            this.pasEn = EnkripsiAES.encrypt(this.TPassword.getText());
            final Properties properties = new Properties();
            properties.setProperty("HOST", this.hostEn);
            properties.setProperty("DATABASE", this.databaseEn);
            properties.setProperty("PORT", this.portEn);
            properties.setProperty("USER", this.userEn);
            properties.setProperty("PAS", this.pasEn);
            final File file = new File("setting/database.xml");
            final FileOutputStream fileOut = new FileOutputStream(file);
            properties.storeToXML(fileOut, "SIMRSKhanza");
            fileOut.close();
            JOptionPane.showMessageDialog(null, "Seting Telah Disimpan");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        catch (Exception ex) {
            Logger.getLogger(DlgSetKoneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButton3ActionPerformed(final ActionEvent evt) {
        this.launch();
    }

    private void BtnTesKoneksiActionPerformed(final ActionEvent evt) {
        try {
            this.prop.loadFromXML(new FileInputStream("setting/database.xml"));
            DlgSetKoneksi.dataSource.setURL("jdbc:mysql://" + EnkripsiAES.decrypt(this.prop.getProperty("HOST")) + ":" + this.prop.getProperty("PORT") + "/" + EnkripsiAES.decrypt(this.prop.getProperty("DATABASE")) + "?zeroDateTimeBehavior=convertToNull");
            DlgSetKoneksi.dataSource.setUser(EnkripsiAES.decrypt(this.prop.getProperty("USER")));
            DlgSetKoneksi.dataSource.setPassword(EnkripsiAES.decrypt(this.prop.getProperty("PAS")));
            DlgSetKoneksi.connection = DlgSetKoneksi.dataSource.getConnection();
            JOptionPane.showMessageDialog(null, "Koneksi Sukses");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal ");
        }
    }

    public static void main(final String[] args) {
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(DlgSetKoneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(DlgSetKoneksi.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(DlgSetKoneksi.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(DlgSetKoneksi.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DlgSetKoneksi().setVisible(true);
            }
        });
    }

    private void launch() {
        final String[] run = { "java", "-jar", "SIMKESKhanza.jar" };
        try {
            Runtime.getRuntime().exec(run);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }

    static {
        DlgSetKoneksi.connection = null;
        dataSource = new MysqlDataSource();
    }
}
