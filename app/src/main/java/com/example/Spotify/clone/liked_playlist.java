/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.Spotify.clone;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.border.EmptyBorder;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import javazoom.jl.player.Player;
import java.util.Random;
/**
 *
 * @author Dhruvi Tanna
 */
public class liked_playlist extends javax.swing.JFrame {

    /**
     * Creates new form liked_playlist
     */
    private Player player;
    private Thread playerThread;
    private boolean isPlaying = false;
    private boolean isPaused = false;
    private long pauseLocation = 0;
    String Songpath;
    private FileInputStream fis;
    private BufferedInputStream bis;
    private int total_length;
    private int pause=0;
    long startTime=0;
    long elapsed=0;
    private long pausedTime = 0;
    int total_duration;
    String prev="";
    String songName;
    boolean c_visible;
    Thread progress_t;
    volatile boolean running_liked=true;
    private HashSet<String> likedSongs;
    private String[] likedSongArray;
    private int currentIndex = 0;
    private boolean isMuted = false;
    private static final String nircmdPath = "C:\\Users\\deeks\\Desktop\\Spotify_clone\\nircmd.exe";
    private DefaultListModel<String> listModel;
    public liked_playlist() {
        listModel = new DefaultListModel<>();
        initComponents();
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        songList.setCellRenderer(new LikedListCellRenderer());
        sqldb.connect();
        more.setComponentPopupMenu(menu);
        loadLikedSongs();
        controls.setVisible(false);
        
    }
    public liked_playlist(boolean isPlaying, String songName, String Songpath, long elapsed, boolean c_visible, int play_from, int total_length) {
        listModel = new DefaultListModel<>();
        initComponents();
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        songList.setCellRenderer(new LikedListCellRenderer());
        sqldb.connect();
        loadLikedSongs();
        more.setComponentPopupMenu(menu);
        playing_icon.setVisible(false);
        controls.setVisible(false);
        try{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace();
        }
        this.isPlaying=isPlaying;
        this.songName=songName;
        this.Songpath=Songpath;
        this.elapsed=elapsed;
        this.total_length=total_length;
        this.pause=play_from;
        if(c_visible){
            controls.setVisible(true);
            playing_icon.setVisible(true);
        }
        else{
            controls.setVisible(false);
            playing_icon.setVisible(false);
        }
        if(isPlaying){
            continue_playing(play_from);
        }
        else{
            ImageIcon play = new ImageIcon("src\\main\\Resources\\play.png");
            Image scaledImage = play.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            playpause.setIcon(new ImageIcon(scaledImage));
            pause();
        }
        try{
            ResultSet res=sqldb.fetchdata("select * from songs where lower(name)='"+songName+"'");
            if(res.next()){
                ImageIcon songImage = new ImageIcon(res.getString("image"));
                Image scaledImage = songImage.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                playing_icon.setIcon(new ImageIcon(scaledImage));
                playing_songname.setText(res.getString("name"));
                songName=res.getString("name");
                playing_songname.setForeground(new Color(204,204,204));
                playing_songname.setFont(new Font("Tahoma", Font.BOLD, 14));
                playing_artist.setText(res.getString("artist"));
                playing_artist.setForeground(new Color(204,204,204));
                playing_artist.setFont(new Font("Tahoma", Font.PLAIN, 12));
                Songpath=res.getString("location");
                curr_duration.setText(res.getString("duration"));
                String[] parts = res.getString("duration").split(":");
                int minutes = Integer.parseInt(parts[0]);
                int seconds = Integer.parseInt(parts[1]);
                total_duration=minutes*60+seconds;
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //JScrollPane scroll=new JScrollPane(jPanel4);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new BlackMenu();
        file = new javax.swing.JMenuItem();
        playback = new javax.swing.JMenuItem();
        view = new javax.swing.JMenuItem();
        help = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        songList = new GradientJList(listModel);
        jPanel3 = new javax.swing.JPanel();
        rehman = new javax.swing.JButton();
        shreya = new javax.swing.JButton();
        liked = new javax.swing.JButton();
        liked1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        more = new javax.swing.JButton();
        controls = new javax.swing.JPanel();
        playpause = new javax.swing.JButton();
        next = new javax.swing.JButton();
        previous = new javax.swing.JButton();
        progress = new CustomProgressBar();
        curr_duration = new javax.swing.JLabel();
        curr_start = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jSlider1 = new CustomSlider(0,100,50);
        playing_artist = new javax.swing.JLabel();
        playing_icon = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        home = new javax.swing.JButton();
        playing_songname = new javax.swing.JLabel();

        file.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        file.setForeground(new java.awt.Color(204, 204, 204));
        file.setText("File");

        playback.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        playback.setForeground(new java.awt.Color(204, 204, 204));
        playback.setText("Playback");

        view.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        view.setForeground(new java.awt.Color(204, 204, 204));
        view.setText("View");

        help.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        help.setForeground(new java.awt.Color(204, 204, 204));
        help.setText("Help");

        edit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edit.setForeground(new java.awt.Color(204, 204, 204));
        edit.setText("Edit");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(1343, 776));
        setPreferredSize(new java.awt.Dimension(1343, 776));
        setSize(new java.awt.Dimension(1343, 776));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(1343, 517));

        songList.setBackground(new java.awt.Color(0, 0, 0));
        songList.setBorder(new EmptyBorder(0, 0, 0, 0));
        songList.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        songList.setForeground(new java.awt.Color(255, 255, 255));
        songList.setModel(listModel);
        songList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        songList.setPreferredSize(new java.awt.Dimension(1335, 200));
        songList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                songListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(songList);

        jPanel3.setBackground(new java.awt.Color(17, 17, 17));
        jPanel3.setPreferredSize(new java.awt.Dimension(64, 565));

        rehman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rehman.png"))); // NOI18N
        rehman.setBorderPainted(false);
        rehman.setContentAreaFilled(false);

        shreya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shreya.png"))); // NOI18N
        shreya.setBorderPainted(false);
        shreya.setContentAreaFilled(false);

        liked.setIcon(new javax.swing.ImageIcon(getClass().getResource("/love.png"))); // NOI18N
        liked.setBorderPainted(false);
        liked.setContentAreaFilled(false);

        liked1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/expand.png"))); // NOI18N
        liked1.setBorderPainted(false);
        liked1.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rehman, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shreya, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(liked, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(liked1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(liked1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(liked)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rehman, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shreya, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(327, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/playlist.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        more.setIcon(new javax.swing.ImageIcon(getClass().getResource("/more.png"))); // NOI18N
        more.setBorderPainted(false);
        more.setContentAreaFilled(false);
        more.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                moreMousePressed(evt);
            }
        });
        more.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moreActionPerformed(evt);
            }
        });

        controls.setBackground(new java.awt.Color(0, 0, 0));
        controls.setForeground(new java.awt.Color(204, 204, 204));
        controls.setPreferredSize(new java.awt.Dimension(1229, 72));

        playpause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pause.png"))); // NOI18N
        playpause.setBorderPainted(false);
        playpause.setContentAreaFilled(false);
        playpause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playpauseActionPerformed(evt);
            }
        });

        next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/next.png"))); // NOI18N
        next.setBorderPainted(false);
        next.setContentAreaFilled(false);
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prev.png"))); // NOI18N
        previous.setBorderPainted(false);
        previous.setContentAreaFilled(false);
        previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionPerformed(evt);
            }
        });

        progress.setBackground(new java.awt.Color(51, 51, 51));
        progress.setForeground(new java.awt.Color(204, 204, 204));
        progress.setBorder(null);
        progress.setPreferredSize(new java.awt.Dimension(376, 4));

        curr_duration.setBackground(new java.awt.Color(0, 0, 0));
        curr_duration.setForeground(new java.awt.Color(204, 204, 204));

        curr_start.setForeground(new java.awt.Color(204, 204, 204));
        curr_start.setText("0:00");

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus.png"))); // NOI18N
        jButton9.setBorder(null);
        jButton9.setMaximumSize(new java.awt.Dimension(75, 75));
        jButton9.setMinimumSize(new java.awt.Dimension(75, 75));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vol.png"))); // NOI18N
        jButton10.setBorder(null);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jSlider1.setMajorTickSpacing(10);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        javax.swing.GroupLayout controlsLayout = new javax.swing.GroupLayout(controls);
        controls.setLayout(controlsLayout);
        controlsLayout.setHorizontalGroup(
            controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(curr_start)
                .addGroup(controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(curr_duration, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(controlsLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playpause, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
        );
        controlsLayout.setVerticalGroup(
            controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsLayout.createSequentialGroup()
                .addGroup(controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(curr_duration, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(controlsLayout.createSequentialGroup()
                            .addGroup(controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(previous)
                                .addGroup(controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(playpause, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(curr_start))
                    .addGroup(controlsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(controlsLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        Search.setBackground(new java.awt.Color(31, 31, 31));
        Search.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Search.setForeground(new java.awt.Color(204, 204, 204));
        Search.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Search.setText("What do you want to play?");
        Search.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchMouseClicked(evt);
            }
        });
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchKeyPressed(evt);
            }
        });

        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home.png"))); // NOI18N
        home.setBorderPainted(false);
        home.setContentAreaFilled(false);
        home.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        playing_songname.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(more)
                .addGap(427, 427, 427)
                .addComponent(home)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1499, 1499, 1499))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playing_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(playing_artist, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playing_songname, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addComponent(controls, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(more))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(home))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(playing_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(playing_songname)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(playing_artist, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(controls, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void songListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_songListValueChanged
        // TODO add your handling code here:
        String[] values=songList.getSelectedValue().split("\\+");
        String name=values[0];
        if(isPlaying)
        player.close();
        prev=songName;
        startTime=0;
        elapsed=0;
        if(!controls.isVisible()){
        controls.setVisible(true);
        playing_icon.setVisible(true);
        }
        try{
            ResultSet res=sqldb.fetchdata("select * from songs where lower(name)='"+name+"'");
            if(res.next()){
                ImageIcon songImage = new ImageIcon(res.getString("image"));
                Image scaledImage = songImage.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                playing_icon.setIcon(new ImageIcon(scaledImage));
                playing_songname.setText(res.getString("name"));
                songName=res.getString("name");
                playing_songname.setForeground(new Color(204,204,204));
                playing_songname.setFont(new Font("Tahoma", Font.BOLD, 14));
                playing_artist.setText(res.getString("artist"));
                playing_artist.setForeground(new Color(204,204,204));
                playing_artist.setFont(new Font("Tahoma", Font.PLAIN, 12));
                Songpath=res.getString("location");
                curr_duration.setText(res.getString("duration"));
                String[] parts = res.getString("duration").split(":");
                int minutes = Integer.parseInt(parts[0]);
                int seconds = Integer.parseInt(parts[1]);
                total_duration=minutes*60+seconds;
                controls.setVisible(true);
                play();
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }//GEN-LAST:event_songListValueChanged

    private void moreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moreMousePressed

    }//GEN-LAST:event_moreMousePressed

    private void moreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moreActionPerformed
        // TODO add your handling code here:
        menu.show(more, 10, more.getHeight());
    }//GEN-LAST:event_moreActionPerformed

    private void playpauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playpauseActionPerformed
        // TODO add your handling code here:
        if (isPlaying) {

            ImageIcon play = new ImageIcon("src\\main\\Resources\\play.png");
            Image scaledImage = play.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            playpause.setIcon(new ImageIcon(scaledImage));
            pause();
        } else {
            ImageIcon pause = new ImageIcon("src\\main\\Resources\\pause.png");
            Image scaledImage = pause.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            playpause.setIcon(new ImageIcon(scaledImage));
            resume();
        }
    }//GEN-LAST:event_playpauseActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        // TODO add your handling code here:
        if(isPlaying)
        player.close();
        prev=songName;
        startTime=0;
        elapsed=0;
        controls.setVisible(true);
        playing_icon.setVisible(true);
        try{
            ResultSet res=sqldb.fetchdata("select * from songs");
            res.last(); // Move cursor to the last row
            int rowCount = res.getRow(); // Get the number of rows
            res.beforeFirst(); // Move cursor back to the beginning

            // Generate a random index
            Random random = new Random();
            int randomIndex = random.nextInt(rowCount);
            if (res.absolute(randomIndex + 1)){
                ImageIcon songImage = new ImageIcon(res.getString("image"));
                Image scaledImage = songImage.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                playing_icon.setIcon(new ImageIcon(scaledImage));
                playing_songname.setText(res.getString("name"));
                songName=res.getString("name");
                playing_songname.setForeground(new Color(204,204,204));
                playing_songname.setFont(new Font("Tahoma", Font.BOLD, 14));
                playing_artist.setText(res.getString("artist"));
                playing_artist.setForeground(new Color(204,204,204));
                playing_artist.setFont(new Font("Tahoma", Font.PLAIN, 12));
                Songpath=res.getString("location");
                curr_duration.setText(res.getString("duration"));
                String[] parts = res.getString("duration").split(":");
                int minutes = Integer.parseInt(parts[0]);
                int seconds = Integer.parseInt(parts[1]);
                total_duration=minutes*60+seconds;
                controls.setVisible(true);
                play();
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_nextActionPerformed

    private void previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionPerformed
        // TODO add your handling code here:
        if(prev==null)
        return;

        if(isPlaying)
        player.close();

        String curr=prev;
        prev=songName;
        startTime=0;
        elapsed=0;
        controls.setVisible(true);
        playing_icon.setVisible(true);
        try{
            ResultSet res=sqldb.fetchdata("select * from songs where name='"+curr+"'");
            res.last(); // Move cursor to the last row
            int rowCount = res.getRow(); // Get the number of rows
            res.beforeFirst(); // Move cursor back to the beginning

            // Generate a random index
            Random random = new Random();
            int randomIndex = random.nextInt(rowCount);
            if (res.absolute(randomIndex + 1)){
                ImageIcon songImage = new ImageIcon(res.getString("image"));
                Image scaledImage = songImage.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                playing_icon.setIcon(new ImageIcon(scaledImage));
                playing_songname.setText(res.getString("name"));
                songName=res.getString("name");
                playing_songname.setForeground(new Color(204,204,204));
                playing_songname.setFont(new Font("Tahoma", Font.BOLD, 14));
                playing_artist.setText(res.getString("artist"));
                playing_artist.setForeground(new Color(204,204,204));
                playing_artist.setFont(new Font("Tahoma", Font.PLAIN, 12));
                Songpath=res.getString("location");
                curr_duration.setText(res.getString("duration"));
                String[] parts = res.getString("duration").split(":");
                int minutes = Integer.parseInt(parts[0]);
                int seconds = Integer.parseInt(parts[1]);
                total_duration=minutes*60+seconds;
                controls.setVisible(true);
                play();
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_previousActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        // Check if there is a song to like/unlike

        // Initialize the likedSongs HashSet if it's null
        if (likedSongs == null) {
            likedSongs = new HashSet<>();
        }

        // Load the icons for like and unlike states
        ImageIcon unlikeIcon = new ImageIcon("src\\main\\Resources\\plus.png");
        ImageIcon likedIcon = new ImageIcon("src\\main\\Resources\\liked.png");

        // Check if the song is already liked
        if (!likedSongs.contains(Songpath)) {
            // Like the song
            likedSongs.add(Songpath);  // Add song to liked list
            jButton9.setIcon(likedIcon);  // Change icon to liked
            saveLikedSongToDB(Songpath);  // Save the liked song in the database
        } else {
            // Unlike the song
            likedSongs.remove(Songpath);  // Remove song from liked list
            jButton9.setIcon(unlikeIcon);  // Change icon to unliked
            removeLikedSongFromDB(Songpath);  // Remove the song from the database
        }
    }//GEN-LAST:event_jButton9ActionPerformed
private void saveLikedSongToDB(String songPath) {
    String query = "INSERT INTO liked_songs (song_path, liked_at) VALUES ('" + songPath.replace("\\","\\\\") + "', CURRENT_TIMESTAMP) " +
                   "ON DUPLICATE KEY UPDATE liked_at = CURRENT_TIMESTAMP";
    sqldb.iud_data(query);
    System.out.println(songPath);// Assuming sqldb has an executeUpdate method
}

    private void removeLikedSongFromDB(String songPath) {
    String query = "DELETE FROM liked_songs WHERE song_path = '" + songPath + "'";
    sqldb.iud_data(query);  // Assuming sqldb has an executeUpdate method
}
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        // Load the mute and unmute icons
        // Load the mute and unmute icons
        ImageIcon muteIcon = new ImageIcon("src\\main\\Resources\\mute.png"); // Mute icon
        ImageIcon unmuteIcon = new ImageIcon("src\\main\\Resources\\vol.png"); // Unmute icon

        String nircmdPath = "C:\\Users\\deeks\\Desktop\\Spotify_clone\\nircmd.exe\\"; // Update this path to where your nircmd.exe is located

        if (!isMuted) {
            // Mute the system sound
            isMuted = true;
            jButton10.setIcon(muteIcon); // Change button icon to mute
            try {
                // Execute NirCmd to mute the system volume using ProcessBuilder
                ProcessBuilder processBuilder = new ProcessBuilder(nircmdPath, "mutesysvolume", "1");
                Process process = processBuilder.start();

                // Capture the output and errors
                process.waitFor();
                System.out.println("Process executed: " + process.exitValue());
            } catch (IOException | InterruptedException e) {
                System.err.println("Error executing NirCmd: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            // Unmute the system sound
            isMuted = false;
            jButton10.setIcon(unmuteIcon); // Change button icon to unmute
            try {
                // Execute NirCmd to unmute the system volume using ProcessBuilder
                ProcessBuilder processBuilder = new ProcessBuilder(nircmdPath, "mutesysvolume", "0");
                Process process = processBuilder.start();

                // Capture the output and errors
                process.waitFor();
                System.out.println("Process executed: " + process.exitValue());
            } catch (IOException | InterruptedException e) {
                System.err.println("Error executing NirCmd: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed
private void setSystemVolume(int volume) {
        try {
            // Convert slider value (0-100) to NirCmd volume scale (0-65535)
            int volumeLevel = (volume * 65535) / 100;
            ProcessBuilder processBuilder = new ProcessBuilder(nircmdPath, "setsysvolume", String.valueOf(volumeLevel));
            Process process = processBuilder.start();
            process.waitFor();  // Wait for NirCmd to finish
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }                        

    private void fileActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }  
    private void continue_playing(int play_from){
    try {
                fis = new FileInputStream(Songpath);
                bis=new BufferedInputStream(fis);
                player= new Player(bis);
                fis.skip(total_length-play_from);
                isPlaying=true;
                startTime=System.currentTimeMillis()/1000;
                new Thread()
                { 
                    public void run(){
                        try{
                            player.play();
                            startTime = System.currentTimeMillis();
                            
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                progress_t=new Thread()
                {
                    public void run(){
                        running_liked=true;
                        long session_elapsed=elapsed;
                        while (running_liked & isPlaying) {
                            // Calculate the current playback position
                            try{
                                
                                long currentLength = session_elapsed+(System.currentTimeMillis()/1000-startTime);
                                elapsed=currentLength;
                                if (total_duration==0)
                                    total_duration=1;
                                progress.setValue((int)currentLength*100/total_duration);
                                String st_min=String.valueOf(currentLength/60);
                                String st_sec=String.valueOf(currentLength%60);
                                if(st_min.length()==1){
                                    st_min="0"+st_min;
                                }
                                if(st_sec.length()==1){
                                    st_sec="0"+st_sec;
                                }
                                curr_start.setText(st_min+":"+st_sec);
                                System.out.println("Elapsed Time: " + (currentLength) + " ms");
                                Thread.sleep(1000);
                            }catch(Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                };
                progress_t.start();
            }catch(Exception e){
                        e.printStackTrace();
            }
    }
private void play() {
            try {
                fis = new FileInputStream(Songpath);
                bis=new BufferedInputStream(fis);
                player= new Player(bis);
                total_length=fis.available();
                isPlaying=true;
                startTime = System.currentTimeMillis()/1000;
                ImageIcon pause = new ImageIcon("src\\main\\Resources\\pause.png");
                Image scaledImage = pause.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                playpause.setIcon(new ImageIcon(scaledImage));
                new Thread()
                { 
                    public void run(){
                        try{
                            player.play();
                            
                            
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                progress_t=new Thread()
                {
                    public void run(){
                        long session_elapsed=elapsed;
                        running_liked=true;
                        while (running_liked & isPlaying) {
                            // Calculate the current playback position
                            try{
                                
                                long currentLength = session_elapsed+(System.currentTimeMillis()/1000-startTime);
                                elapsed=currentLength;
                                if (total_duration==0)
                                    total_duration=1;
                                progress.setValue((int)currentLength*100/total_duration);
                                String st_min=String.valueOf(currentLength/60);
                                String st_sec=String.valueOf(currentLength%60);
                                if(st_min.length()==1){
                                    st_min="0"+st_min;
                                }
                                if(st_sec.length()==1){
                                    st_sec="0"+st_sec;
                                }
                                
                                curr_start.setText(st_min+":"+st_sec);
                                System.out.println("Elapsed Time: " + (currentLength) + " ms");
                                Thread.sleep(1000);
                            }catch(Exception e){
                                e.printStackTrace();
                            }

                        }
                        }
                };
                progress_t.start();
            }catch(Exception e){
                        e.printStackTrace();
            }
    }
private void pause(){
    if(player!=null){
        try{
            pause=fis.available();
            player.close();
            isPlaying = false;
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
private void resume() {
            try {
                fis = new FileInputStream(Songpath);
                bis=new BufferedInputStream(fis);
                player= new Player(bis);
                fis.skip(total_length-pause);
                isPlaying=true;
                startTime=System.currentTimeMillis()/1000;
                new Thread()
                { 
                    public void run(){
                        try{
                            player.play();
                            startTime = System.currentTimeMillis();
                            
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                progress_t=new Thread()
                {
                    public void run(){
                        running_liked=true;
                        long session_elapsed=elapsed;
                        while (running_liked & isPlaying) {
                            // Calculate the current playback position
                            try{
                                
                                long currentLength = session_elapsed+(System.currentTimeMillis()/1000-startTime);
                                elapsed=currentLength;
                                if (total_duration==0)
                                    total_duration=1;
                                progress.setValue((int)currentLength*100/total_duration);
                                String st_min=String.valueOf(currentLength/60);
                                String st_sec=String.valueOf(currentLength%60);
                                if(st_min.length()==1){
                                    st_min="0"+st_min;
                                }
                                if(st_sec.length()==1){
                                    st_sec="0"+st_sec;
                                }
                                curr_start.setText(st_min+":"+st_sec);
                                System.out.println("Elapsed Time: " + (currentLength) + " ms");
                                Thread.sleep(1000);
                            }catch(Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                };
                progress_t.start();
            }catch(Exception e){
                        e.printStackTrace();
            }
    }
    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        // TODO add your handling code here:
        int volume = jSlider1.getValue();  // Get the slider's current value
        setSystemVolume(volume);  // Set the system volume dynamically
    }//GEN-LAST:event_jSlider1StateChanged

    private void SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchMouseClicked
        // TODO add your handling code here:
        try{
            if(fis==null){
            Search searchpage=new Search();
            searchpage.setVisible(true);
            setVisible(false);
        }
            else if(isPlaying){
        Search searchpage=new Search(isPlaying, songName, Songpath, elapsed, controls.isVisible(), fis.available(), total_length);
        running_liked=false;
        if(isPlaying)
            player.close();
        setVisible(false);
        searchpage.setVisible(true);}
            else{
                Search searchpage=new Search(isPlaying, songName, Songpath, elapsed, controls.isVisible(), pause, total_length);
                running_liked=false;
                setVisible(false);
        searchpage.setVisible(true);
            }
        }catch(Exception e){e.printStackTrace();}

    }//GEN-LAST:event_SearchMouseClicked

    private void SearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyPressed
        // TODO add your handling code here:
        try{
            if(fis==null){
            Search searchpage=new Search();
            searchpage.setVisible(true);
            setVisible(false);
        }
        else if(isPlaying){
        Search searchpage=new Search(isPlaying, songName, Songpath, elapsed, controls.isVisible(), fis.available(), total_length);
        running_liked=false;
        if(isPlaying)
            player.close();
        setVisible(false);
        searchpage.setVisible(true);}
        else{
        Search searchpage=new Search(isPlaying, songName, Songpath, elapsed, controls.isVisible(), pause, total_length);
        running_liked=false;
        setVisible(false);
        searchpage.setVisible(true);}
        }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_SearchKeyPressed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        // TODO add your handling code here:
        try{
            if(fis==null){
                new Home().setVisible(true);
                setVisible(false);
            }
            else if(isPlaying){
        Home homepage=new Home(isPlaying, songName, Songpath, elapsed, controls.isVisible(), fis.available(), total_length);
        running_liked=false;
        if(isPlaying)
            player.close();
        setVisible(false);
        homepage.setVisible(true);}
            else{
        Home homepage=new Home(isPlaying, songName, Songpath, elapsed, controls.isVisible(), pause, total_length);
        setVisible(false);
        running_liked=false;
        if(isPlaying)
            player.close();
        homepage.setVisible(true);}
        }catch(Exception e){}
    }//GEN-LAST:event_homeActionPerformed
 

    private void loadLikedSongs() {
    try {
        // SQL query to get liked songs
        String query = "SELECT s.id, s.name, s.image, s.artist, s.album, s.duration, l.liked_at, s.location " +
                       "FROM songs s, liked_songs l "+
                       "where s.location = l.song_path";  // Ensure `song_path` is the correct field
        ResultSet res = sqldb.fetchdata(query);
        listModel.clear();
        // Process each row of data from the ResultSet
        likedSongs=new HashSet<>();
        while (res.next()) {

            String song_name = res.getString("name");
            String song_imagePath = res.getString("image");
            String song_artists=res.getString("artist");
            String song_album=res.getString("album");
            SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
            String liked_at=formatter.format(res.getDate("liked_at"));
            String duration = res.getString("duration");
            System.out.println("ID: " + res.getInt("id") + ", Name: " + res.getString("name") + ", Image: " + res.getString("image"));
            String song = song_name + "+" + song_imagePath + "+" + song_artists + "+" + song_album + "+" + liked_at + "+" + duration;
            System.out.println(song);
            likedSongs.add(res.getString("location"));
            listModel.addElement(song);
        }
        songList.setModel(listModel);
    } catch (SQLException e) {
        e.printStackTrace();  // Handle SQL exceptions
    }
}


    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(liked_playlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(liked_playlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(liked_playlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(liked_playlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new liked_playlist().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Search;
    private javax.swing.JPanel controls;
    private javax.swing.JLabel curr_duration;
    private javax.swing.JLabel curr_start;
    private javax.swing.JMenuItem edit;
    private javax.swing.JMenuItem file;
    private javax.swing.JMenuItem help;
    private javax.swing.JButton home;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JButton liked;
    private javax.swing.JButton liked1;
    private javax.swing.JPopupMenu menu;
    private javax.swing.JButton more;
    private javax.swing.JButton next;
    private javax.swing.JMenuItem playback;
    private javax.swing.JLabel playing_artist;
    private javax.swing.JLabel playing_icon;
    private javax.swing.JLabel playing_songname;
    private javax.swing.JButton playpause;
    private javax.swing.JButton previous;
    private javax.swing.JProgressBar progress;
    private javax.swing.JButton rehman;
    private javax.swing.JButton shreya;
    private javax.swing.JList<String> songList;
    private javax.swing.JMenuItem view;
    // End of variables declaration//GEN-END:variables
}
class LikedListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

           
        String songData = (String) value;
        String[] arr = songData.split("\\+");
        String name =arr[0];
        String imagePath =arr[1];
        String artist=arr[2];
        String album=arr[3];
        String liked_at=arr[4];
        String duration=arr[5];

        // Create a JPanel to hold both the image and the text
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false); // Make the panel transparent
        panel.setBorder(new EmptyBorder(10, 10, 10, 20)); // Add padding

        // Load and resize the image
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBorder(new EmptyBorder(0, 20, 0, 0));

        // Create a label for the song name
        JLabel nameLabel = new JLabel(name);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        nameLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        
        JLabel artistLabel = new JLabel(artist);
        artistLabel.setForeground(new Color(194,197,204));
        artistLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        artistLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        
        JLabel durationLabel = new JLabel(duration);
        durationLabel.setForeground(new Color(194,197,204));
        durationLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        durationLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        
        JLabel albumLabel = new JLabel(album);
        albumLabel.setForeground(new Color(194,197,204));
        albumLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        albumLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        albumLabel.setMaximumSize(new Dimension(10, albumLabel.getPreferredSize().height));
        
        JLabel likedLabel = new JLabel(liked_at);
        likedLabel.setForeground(new Color(194,197,204));
        likedLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        likedLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        
        panel.add(imageLabel, BorderLayout.WEST);
        panel.add(durationLabel,BorderLayout.EAST);
        JPanel center=new JPanel(new GridLayout(1,3));
        JPanel text=new JPanel(new GridLayout(2,1));
        text.setOpaque(false);
        text.add(nameLabel);
        text.add(artistLabel);
        center.setOpaque(false);
        center.add(text);
        center.add(albumLabel);
        center.add(likedLabel);
        center.setMaximumSize(new Dimension(100, 50));
        panel.add(center, BorderLayout.CENTER);

        // Highlight background if selected
        if (isSelected) {
            panel.setBackground(list.getSelectionBackground());
        } else {
            panel.setBackground(list.getBackground());
        }

        return panel;
    }
    }