/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectominiphp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

/**
 *
 * @author DELL
 */
public class Analizer extends javax.swing.JFrame {

    File address;
    String addressFileOut;
    String addressErrorsFileOut;
    /**
     * Creates new form Analizer
     */
    public Analizer() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonSelectFile = new javax.swing.JButton();
        jTextField = new javax.swing.JTextField();
        jButtonUpload = new javax.swing.JButton();
        textArea = new java.awt.TextArea();
        jLabel3 = new javax.swing.JLabel();
        textArea1 = new java.awt.TextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ANALIZADOR LÉXICO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, 40));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 153));
        jLabel2.setText("MiniPHP");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 200, 53));

        jButtonSelectFile.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButtonSelectFile.setText("SELECCIONAR ARCHIVO");
        jButtonSelectFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSelectFileMouseClicked(evt);
            }
        });
        jButtonSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelectFileActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSelectFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 170, 40));

        jTextField.setEditable(false);
        getContentPane().add(jTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 340, 40));

        jButtonUpload.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButtonUpload.setText("ANALIZAR ARCHIVO");
        jButtonUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUploadActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonUpload, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 170, 40));

        textArea.setEditable(false);
        getContentPane().add(textArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 290, 210));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/590x300.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 260));

        textArea1.setEditable(false);
        getContentPane().add(textArea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 290, 210));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSelectFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSelectFileMouseClicked
        // TODO add your handling code here: 
        JFileChooser chooser = new JFileChooser(); 
        
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            address = chooser.getSelectedFile(); 
            jTextField.setText(address.getAbsolutePath()); 
        } 
    }//GEN-LAST:event_jButtonSelectFileMouseClicked

    private void jButtonSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelectFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSelectFileActionPerformed

    private void jButtonUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUploadActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            String root = new File(".").getCanonicalPath();
            String path = root + "\\src\\proyectominiphp\\Lexer.flex";
            generateLexer(path);
            this.analizeLexer();
       } catch (IOException ex) {
            infoBox("Seleccionar un archivo", "Missing File");    
        }
    }//GEN-LAST:event_jButtonUploadActionPerformed
 
    public void analizeLexer() throws FileNotFoundException, IOException{   
        Reader reader = new BufferedReader(new FileReader(jTextField.getText()));
        Lexer lexer = new Lexer(reader);
        String fileErrors = "";
        String showErrors1 = "";
        String showErrors = "";
        String outFile = "";
        int errors = 0;
        
        String name = address.getName();
        String extension = name.substring(name.indexOf("."));
        addressFileOut = address.getPath().replace(extension, ".out");
      
        File fileOut = new File(addressFileOut);
        BufferedWriter bw;
        if(fileOut.exists()) {
        //File exists
            bw = new BufferedWriter(new FileWriter(fileOut));
        } else {
        //File does not exist
            bw = new BufferedWriter(new FileWriter(fileOut));
        }

        addressErrorsFileOut = address.getPath().replace(name, "fileErrors.out");
      
        File errorsFileOut = new File(addressErrorsFileOut);
        BufferedWriter bw2;
        if(errorsFileOut.exists()) {
        //File exists
            bw2 = new BufferedWriter(new FileWriter(errorsFileOut));
        } else {
        //File does not exist
            bw2 = new BufferedWriter(new FileWriter(errorsFileOut));
        }
        while(true){
            Token token = lexer.yylex();
            if(token == null){
                break;
            }
            if(token == Token.ERROR){
                errors++;
                //ResultadoArchivoErrores += ":"+ lexer.chars + "\tNot valid token:'"+"'\n";
                showErrors1 +=  "Line: " + lexer.countLine + ". No valid token: " + lexer.myLexer + "\n";
                fileErrors +=  "Line: " + lexer.countLine + ". No valid token: " + lexer.myLexer+ "\n";
                lexer.myLexer = "ERROR";
            }
            //Verify syntaxis
            else{
                if(token == Token.CONTROLSTRUCT){
                       if(!lexer.myLexer.equals(lexer.myLexer.toLowerCase())){
                        showErrors += "Line: " + lexer.countLine + ". Control structure '" + lexer.myLexer+ "' must be in lower case." + "\n";                       
                        lexer.myLexer = lexer.myLexer.toLowerCase();
                }
                }else if(token == Token.DB){                   
                    String content = lexer.myLexer.substring(12, lexer.myLexer.length()-2);
                    if(!content.equals(content.toUpperCase())){
                        showErrors += "Line: " + lexer.countLine + ". Database '" + lexer.myLexer+ "' must be in upper case." + "\n";                      
                        content = content.toUpperCase();
                        lexer.myLexer = "$recordset['"+content+"']";
                    }
                }
                else if(token == Token.LOGOPERATOR){
                    if(!lexer.myLexer.equals(lexer.myLexer.toLowerCase())){
                        showErrors += "Line: " + lexer.countLine + ". Logical operator: '" + lexer.myLexer+ "' must be in lower case." + "\n";                       
                        lexer.myLexer = lexer.myLexer.toLowerCase();
                    }
                }
                else if(token == Token.PRECONSTANT){
                    if(!lexer.myLexer.equals(lexer.myLexer.toUpperCase())){
                        showErrors += "Line: " + lexer.countLine + ". Predetermined constant: '" + lexer.myLexer+ "' must be in upper case." + "\n";                       
                        lexer.myLexer = lexer.myLexer.toUpperCase();
                    }                  
                }
                else if(token == Token.PHP){
                    if(!lexer.myLexer.equals(lexer.myLexer.toLowerCase())){
                        showErrors += "Line: " + lexer.countLine + ". " + lexer.myLexer+ "' must be in lower case." + "\n";                       
                        lexer.myLexer = lexer.myLexer.toLowerCase();
                    }                  
                }
                else if(token == Token.RESERVEDWORDS){
                    if(!lexer.myLexer.equals(lexer.myLexer.toLowerCase())){
                        showErrors += "Line: " + lexer.countLine + ". Reserved words: '" + lexer.myLexer+ "' must be in lower case." + "\n";                       
                        lexer.myLexer = lexer.myLexer.toLowerCase();
                    }                  
                }
                else if(token == Token.BOOL){
                    if(!lexer.myLexer.equals(lexer.myLexer.toLowerCase())){
                        showErrors += "Line: " + lexer.countLine + ". Bool type: '" + lexer.myLexer+ "' must be in lower case." + "\n";                       
                        lexer.myLexer = lexer.myLexer.toLowerCase();
                    }                  
                }
                else if(token == Token.FUNCTION){
                    if(!lexer.myLexer.equals(lexer.myLexer.toLowerCase())){
                        showErrors += "Line: " + lexer.countLine + ". Function: '" + lexer.myLexer+ "' must be in lower case." + "\n";                       
                        lexer.myLexer = lexer.myLexer.toLowerCase();
                    }                  
                }
                else if(token == Token.PREVAR){
                    if(!lexer.myLexer.equals(lexer.myLexer.toUpperCase())){
                        showErrors += "Line: " + lexer.countLine + ". Predeterminated variable: '" + lexer.myLexer+ "' must be in upper case." + "\n";                       
                        lexer.myLexer = lexer.myLexer.toUpperCase();
                    }                  
                }
                else if(token == Token.PREVAR2){
                    if(!lexer.myLexer.equals(lexer.myLexer.toLowerCase())){
                        showErrors += "Line: " + lexer.countLine + ". Predeterminated variable: '" + lexer.myLexer+ "' must be in lower case." + "\n";                       
                        lexer.myLexer = lexer.myLexer.toLowerCase();
                    }                  
                }
                outFile += lexer.myLexer;
            }     
        }
        if(errors == 0)
        {          
            bw.write(outFile);
            infoBox("Successful. File .out in: " + addressFileOut, "File");
        }
        else
        {
            bw2.write(fileErrors);
            infoBox("File contains errors. A file was generated with information about the errors in: " + addressErrorsFileOut, "Error");
        }
        
        bw.close();
        bw2.close();
        textArea.setText(showErrors);
        textArea1.setText(showErrors1);
    }
    
    public static void generateLexer(String path){
        File file = new File(path);
        jflex.Main.generate(file);
}
    
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
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
            java.util.logging.Logger.getLogger(Analizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Analizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Analizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Analizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Analizer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSelectFile;
    private javax.swing.JButton jButtonUpload;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField;
    private java.awt.TextArea textArea;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}
