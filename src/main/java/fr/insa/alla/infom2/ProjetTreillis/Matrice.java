/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Farouk
 */
public class Matrice {
    
    private int  nbrLig;
    private int nbrCol;
    private double coeffs;

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
    public double getCoeffs() {
        return coeffs;
    }

    /**
     * @param coeffs the coeffs to set
     */
    public void setCoeffs(double coeffs) {
        this.coeffs = coeffs;
    }
    @Override
    public String toString() {
        String output = new String();
        String[][] Matrice = new String[nbrLig][nbrCol];
        String.format("%+4.2E", coeffs);
        return output;
    }
}
