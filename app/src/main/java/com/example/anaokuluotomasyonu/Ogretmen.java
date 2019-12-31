package com.example.anaokuluotomasyonu;

import java.math.BigInteger;

public class Ogretmen
{
    private String o_Ad;
    private String o_Soyad;
    private String o_Tel;
    private String o_Mail;
    private String o_Sifre;
    private String sinif;
    private Long o_Tc_No;

    public Ogretmen(){
    }

    public Ogretmen(String o_Ad, String o_Soyad, String o_Tel, String o_Mail, String o_Sifre, String sinif, Long o_Tc_No) {
        this.o_Ad = o_Ad;
        this.o_Soyad = o_Soyad;
        this.o_Tel = o_Tel;
        this.o_Mail = o_Mail;
        this.o_Sifre = o_Sifre;
        this.sinif = sinif;
        this.o_Tc_No = o_Tc_No;
    }

    public String getO_Ad() {
        return o_Ad;
    }

    public void setO_Ad(String o_Ad) {
        this.o_Ad = o_Ad;
    }

    public String getO_Soyad() { return o_Soyad; }

    public void setO_Soyad(String o_Soyad) { this.o_Soyad = o_Soyad; }

    public String getO_Tel() { return o_Tel; }

    public void setO_Tel(String o_Tel) { this.o_Tel = o_Tel; }

    public String getO_Mail() { return o_Mail; }

    public void setO_Mail(String o_Mail) { this.o_Mail = o_Mail; }

    public String getO_Sifre() { return o_Sifre; }

    public void setO_Sifre(String o_Sifre) { this.o_Sifre = o_Sifre; }

    public String getSinif() { return sinif; }

    public void setSinif(String sinif) { this.sinif = sinif; }

    public Long getO_Tc_No() { return o_Tc_No; }

    public void setO_Tc_No(Long o_Tc_No) { this.o_Tc_No = o_Tc_No; }
}
