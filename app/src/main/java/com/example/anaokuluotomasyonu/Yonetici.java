package com.example.anaokuluotomasyonu;

public class Yonetici
{
    private String y_Ad;
    private String y_Soyad;
    private String y_Tel;
    private String y_Mail;
    private String y_Sifre;
    private String gorev;
    private Long y_Tc_No;

    public Yonetici(){
    }

    public Yonetici(String y_Ad, String y_Soyad, String y_Tel, String y_Mail, String y_Sifre, String gorev, Long y_Tc_No) {
        this.y_Ad = y_Ad;
        this.y_Soyad = y_Soyad;
        this.y_Tel = y_Tel;
        this.y_Mail = y_Mail;
        this.y_Sifre = y_Sifre;
        this.gorev = gorev;
        this.y_Tc_No = y_Tc_No;
    }

    public String getY_Ad() {
        return y_Ad;
    }

    public void setY_Ad(String y_Ad) {
        this.y_Ad = y_Ad;
    }

    public String getY_Soyad() { return y_Soyad; }

    public void setY_Soyad(String y_Soyad) { this.y_Soyad = y_Soyad; }

    public String getY_Tel() { return y_Tel; }

    public void setY_Tel(String y_Tel) { this.y_Tel = y_Tel; }

    public String getY_Mail() { return y_Mail; }

    public void setY_Mail(String y_Mail) { this.y_Mail = y_Mail; }

    public String getY_Sifre() { return y_Sifre; }

    public void setY_Sifre(String y_Sifre) { this.y_Sifre = y_Sifre; }

    public String getGorev() { return gorev; }

    public void setGorev(String gorev) { this.gorev = gorev; }

    public Long getY_Tc_No() { return y_Tc_No; }

    public void setY_Tc_No(Long y_Tc_No) { this.y_Tc_No = y_Tc_No; }
}
