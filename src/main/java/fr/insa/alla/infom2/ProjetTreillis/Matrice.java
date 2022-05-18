/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

import java.util.Arrays;

/**
 *
 * @author Farouk
 */
public class Matrice {

    public int nbrLig;
    public int nbrCol;
    public double[][] coeffs;

    public Matrice() {
        
    }

    /**
     * @return the nbrLig
     */
    public int getNbrLig() {
        return nbrLig;
    }

    /**
     * @param nbrLig the nbrLig to set
     */
    public void setNbrLig(int nbrLig) {
        this.nbrLig = nbrLig;
    }

    /**
     * @return the nbrCol
     */
    public int getNbrCol() {
        return nbrCol;
    }

    /**
     * @param nbrCol the nbrCol to set
     */
    public void setNbrCol(int nbrCol) {
        this.nbrCol = nbrCol;
    }

    /**
     * @return the coeffs
     */
    public double[][] getCoeffs() {
        return coeffs;
    }

    public void setCoeffs(double[][] coeffs) {
        this.coeffs = coeffs;
    }

    @Override
    public String toString() {
        String output = new String();
        String[][] Matrice = new String[nbrLig][nbrCol];
        String.format("%+4.2E", getCoeffs());
        return output;
    }
    
    public double get() {
        for (int i = 0; i < nbrLig; i++) {
            for (int j = 0; j < nbrCol; j++) {
                double[][] Matrice = new double[nbrLig][nbrCol];
                this.coeffs = Matrice;
            }
    }
    public void set(int i,int j ,double k){
        this.coeffs[i][j]=k;
       
   }
    
    /**
     *
     * @return
     */
    public String Transposee() {
        double[][] Matrice = new double[nbrLig][nbrCol];
        double[][] Transposee = new double[nbrCol][nbrLig];
        for (var i = 0; i < nbrLig; i++) {
            for (var j = 0; nbrCol >= j; j++) {
                this.coeffs
            }
        }
        return Arrays.toString(Transposee);
    }
}
