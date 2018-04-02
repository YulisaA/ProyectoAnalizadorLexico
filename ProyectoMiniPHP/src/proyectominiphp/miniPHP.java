/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectominiphp;

import java.io.File;

/**
 *
 * @author DELL
 */
public class miniPHP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String path = "C:/Users/DELL/Desktop/ProyectoAnalizadorLexico/ProyectoMiniPHP/src/proyectominiphp/Lexer.flex";
        generarLexer(path);
    }
    public static void generarLexer(String path){
        File file = new File(path);
        jflex.Main.generate(file);
}
        
    }
