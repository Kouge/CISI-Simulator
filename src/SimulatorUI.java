import java.io.*;
import javax.swing.event.*;
import javax.swing.JFileChooser;

public class SimulatorUI extends javax.swing.JFrame{
    
    public static int count = 0;
    Functions p;
    public SimulatorUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        RSR = new javax.swing.JLabel();
        IR = new javax.swing.JLabel();
        MAR = new javax.swing.JLabel();
        MBR = new javax.swing.JLabel();
        X0 = new javax.swing.JLabel();
        PC = new javax.swing.JLabel();
        RSR_result = new javax.swing.JTextField();
        IR_result = new javax.swing.JTextField();
        MAR_result = new javax.swing.JTextField();
        MBR_result = new javax.swing.JTextField();
        X0_result = new javax.swing.JTextField();
        PC_result = new javax.swing.JTextField();
        Debug = new javax.swing.JButton();
        Run = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        IPL = new javax.swing.JButton();
        FilePath = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ConsolePanel = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        MemoryPanel = new javax.swing.JTextArea();
        Clear = new javax.swing.JButton();
        R0 = new javax.swing.JLabel();
        R1 = new javax.swing.JLabel();
        R2 = new javax.swing.JLabel();
        R3 = new javax.swing.JLabel();
        R1_result = new javax.swing.JTextField();
        R3_result = new javax.swing.JTextField();
        R2_result = new javax.swing.JTextField();
        R0_result = new javax.swing.JTextField();
        MBR1 = new javax.swing.JLabel();
        D2 = new javax.swing.JLabel();
        D1_result = new javax.swing.JTextField();
        D2_result = new javax.swing.JTextField();
        D1 = new javax.swing.JLabel();
        CC = new javax.swing.JLabel();
        CC_result = new javax.swing.JTextField();
        Halt = new javax.swing.JButton();
        MFR = new javax.swing.JLabel();
        MFR_result = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        Keyboard = new javax.swing.JTextArea();
        FR0 = new javax.swing.JLabel();
        FR0_result = new javax.swing.JTextField();
        FR1 = new javax.swing.JLabel();
        FR1_result = new javax.swing.JTextField();

        fileChooser.setDialogTitle("Open File");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simulator");

        RSR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RSR.setText("RSR");
        RSR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        IR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        IR.setText("IR");

        MAR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        MAR.setText("MAR");

        MBR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        MBR.setText("MBR");

        X0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        X0.setText("X0");

        PC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PC.setText("PC");

        RSR_result.setEditable(false);

        IR_result.setEditable(false);

        MAR_result.setEditable(false);

        MBR_result.setEditable(false);

        X0_result.setEditable(false);

        PC_result.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PC_resultActionPerformed(evt);
            }
        });

        Debug.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Debug.setText("Debug");
        Debug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DebugActionPerformed(evt);
            }
        });

        Run.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Run.setText("Run");
        Run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunActionPerformed(evt);
            }
        });

        Exit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        IPL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        IPL.setText("IPL");
        IPL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IPLActionPerformed(evt);
            }
        });

        FilePath.setEditable(false);

        ConsolePanel.setEditable(false);
        ConsolePanel.setColumns(20);
        ConsolePanel.setRows(5);
        jScrollPane1.setViewportView(ConsolePanel);

        MemoryPanel.setEditable(false);
        MemoryPanel.setColumns(20);
        MemoryPanel.setRows(5);
        jScrollPane2.setViewportView(MemoryPanel);

        Clear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Clear.setText("Clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });

        R0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        R0.setText("R0");

        R1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        R1.setText("R1");

        R2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        R2.setText("R2");

        R3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        R3.setText("R3");

        R1_result.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1_resultActionPerformed(evt);
            }
        });

        R3_result.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3_resultActionPerformed(evt);
            }
        });

        R2_result.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2_resultActionPerformed(evt);
            }
        });

        R0_result.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R0_resultActionPerformed(evt);
            }
        });

        MBR1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        MBR1.setText("ALU:");

        D2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D2.setText("D2:");

        D1_result.setEditable(false);

        D2_result.setEditable(false);

        D1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        D1.setText("D1:");

        CC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CC.setText("CC");
        CC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        CC_result.setEditable(false);

        Halt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Halt.setText("Halt");
        Halt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HaltActionPerformed(evt);
            }
        });

        MFR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        MFR.setText("MFR");

        MFR_result.setEditable(false);

        Keyboard.setColumns(20);
        Keyboard.setRows(5);
        jScrollPane3.setViewportView(Keyboard);

        FR0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        FR0.setText("FR0:");

        FR0_result.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FR0_resultActionPerformed(evt);
            }
        });

        FR1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        FR1.setText("FR1:");

        FR1_result.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FR1_resultActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(RSR, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RSR_result, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CC_result, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(R0, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(R0_result, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(FR1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FR1_result))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(IPL, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FilePath))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MBR1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(IR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(IR_result, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(PC, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(PC_result, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(D1_result, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(MBR)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(MBR_result, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(MAR)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(MAR_result, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(X0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(MFR, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(X0_result, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(MFR_result, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(R3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(R3_result, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(R2)
                                                .addComponent(R1))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(R1_result)
                                                .addComponent(R2_result, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(D2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(D2_result))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(FR0, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FR0_result, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Debug)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Run)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Halt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Clear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Exit))
                    .addComponent(jScrollPane3))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IPL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FilePath))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IR, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IR_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PC_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RSR_result)
                            .addComponent(R0_result)
                            .addComponent(RSR)
                            .addComponent(R0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CC_result))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(R1_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(R1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(R2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(R2_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(R3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(R3_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(MAR_result, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(MAR, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(MBR, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(MBR_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(X0, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(X0_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MFR, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MFR_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(MBR1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D1_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D2_result, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FR0_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FR0, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FR1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FR1_result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Debug)
                    .addComponent(Run, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Halt)
                    .addComponent(Clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void IPLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IPLActionPerformed
        // TODO add your handling code here:   
        //p.interrupt();
        p = new Functions();       
        Functions.stopFlag = false;
        MemoryPanel.setText("");
        Keyboard.setText("");
        Data.reset();
        count = 0;
        
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        try {
          // What to do with the file, e.g. display it in a TextArea
          BufferedReader br = new BufferedReader(new FileReader(file));
          //ConsolePanel.read( fr , null );
          FilePath.setText(file.getAbsolutePath());
                   
          String str;
          while((str = br.readLine())!=null){             
              char[] chars = str.toCharArray();
              short tmp = 0;
              for(int j = 0; j<Short.SIZE; j++){                 
                  tmp += (short) (chars[j] - '0');
                  if(j<Short.SIZE-1)
                    tmp = (short) (tmp<<1);
              } 
              Data.mem[count+2] = tmp; // Address 0 and 1 cannot be used           
              count++;             
          }       
          br.close();
          for(int n = 0; n<Data.mem.length; n++){    //display in MemoryPanel
              MemoryPanel.append(Functions.toBinaryString(Data.mem[n])+"\n");
          }         
        } catch (IOException ex) {
          System.out.println("problem accessing file"+file.getAbsolutePath());
        } 
    } else {
        System.out.println("File access cancelled by user.");
    }
    }//GEN-LAST:event_IPLActionPerformed

    private void RunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunActionPerformed
        // TODO add your handling code here:
        Functions.debug = false;
        Functions.stopFlag = false;
        if(!p.isAlive()){
            p.start();           
        }
        else           
            p.myresume();
    }//GEN-LAST:event_RunActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        // TODO add your handling code here:
        ConsolePanel.setText("");
    }//GEN-LAST:event_ClearActionPerformed

    private void DebugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DebugActionPerformed
        // TODO add your handling code here:
        Functions.debug = true;
        if(!p.isAlive()){
            p.start();           
        }
        else           
            p.myresume();
            
    }//GEN-LAST:event_DebugActionPerformed

    private void R2_resultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2_resultActionPerformed
        // TODO add your handling code here:
        Data.R[2] = Functions.stringToBinary(R2_result.getText());
        ConsolePanel.append("R2 has been changed to "+Functions.toBinaryString(Data.R[2])+"\n\n");
    }//GEN-LAST:event_R2_resultActionPerformed

    private void R0_resultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R0_resultActionPerformed
        // TODO add your handling code here:   
        Data.R[0] = Functions.stringToBinary(R0_result.getText());
        ConsolePanel.append("R0 has been changed to "+Functions.toBinaryString(Data.R[0])+"\n\n");
    }//GEN-LAST:event_R0_resultActionPerformed

    private void PC_resultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PC_resultActionPerformed
        // TODO add your handling code here:
        Data.PC = Functions.stringToBinary(PC_result.getText());
        ConsolePanel.append("PC has been changed to "+Functions.toBinaryString(Data.PC)+"\n\n");
    }//GEN-LAST:event_PC_resultActionPerformed

    private void R1_resultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1_resultActionPerformed
        // TODO add your handling code here:
        Data.R[1] = Functions.stringToBinary(R1_result.getText());
        ConsolePanel.append("R1 has been changed to "+Functions.toBinaryString(Data.R[1])+"\n\n");
    }//GEN-LAST:event_R1_resultActionPerformed

    private void R3_resultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3_resultActionPerformed
        // TODO add your handling code here:
        Data.R[3] = Functions.stringToBinary(R3_result.getText());
        ConsolePanel.append("R3 has been changed to "+Functions.toBinaryString(Data.R[3])+"\n\n");
    }//GEN-LAST:event_R3_resultActionPerformed

    private void HaltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HaltActionPerformed
        // TODO add your handling code here:
        Functions.stopFlag = true;
        p.interrupt();
        ConsolePanel.append("-------------------HALT--------------------\n");
    }//GEN-LAST:event_HaltActionPerformed

    private void FR0_resultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FR0_resultActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FR0_resultActionPerformed

    private void FR1_resultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FR1_resultActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FR1_resultActionPerformed

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
            java.util.logging.Logger.getLogger(SimulatorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SimulatorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SimulatorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimulatorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SimulatorUI().setVisible(true);
                
                PC_result.setText("0000000000000000");
                R0_result.setText("0000000000000000");
                R1_result.setText("0000000000000000");
                R2_result.setText("0000000000000000");
                R3_result.setText("0000000000000000");
            }
        });      
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel CC;
    public static javax.swing.JTextField CC_result;
    public static javax.swing.JButton Clear;
    public static javax.swing.JTextArea ConsolePanel;
    public javax.swing.JLabel D1;
    public static javax.swing.JTextField D1_result;
    public javax.swing.JLabel D2;
    public static javax.swing.JTextField D2_result;
    public javax.swing.JButton Debug;
    public javax.swing.JButton Exit;
    public javax.swing.JLabel FR0;
    public static javax.swing.JTextField FR0_result;
    public javax.swing.JLabel FR1;
    public static javax.swing.JTextField FR1_result;
    public javax.swing.JTextField FilePath;
    private javax.swing.JButton Halt;
    public javax.swing.JButton IPL;
    public javax.swing.JLabel IR;
    public static javax.swing.JTextField IR_result;
    public static javax.swing.JTextArea Keyboard;
    public javax.swing.JLabel MAR;
    public static javax.swing.JTextField MAR_result;
    public javax.swing.JLabel MBR;
    public javax.swing.JLabel MBR1;
    public static javax.swing.JTextField MBR_result;
    public javax.swing.JLabel MFR;
    public static javax.swing.JTextField MFR_result;
    public static javax.swing.JTextArea MemoryPanel;
    public javax.swing.JLabel PC;
    public static javax.swing.JTextField PC_result;
    private javax.swing.JLabel R0;
    public static javax.swing.JTextField R0_result;
    private javax.swing.JLabel R1;
    public static javax.swing.JTextField R1_result;
    private javax.swing.JLabel R2;
    public static javax.swing.JTextField R2_result;
    private javax.swing.JLabel R3;
    public static javax.swing.JTextField R3_result;
    public javax.swing.JLabel RSR;
    public static javax.swing.JTextField RSR_result;
    public javax.swing.JButton Run;
    public javax.swing.JLabel X0;
    public static javax.swing.JTextField X0_result;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
