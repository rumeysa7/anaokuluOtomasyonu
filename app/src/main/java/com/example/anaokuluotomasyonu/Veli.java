package com.example.anaokuluotomasyonu;

public class Veli
{
    private String v_Ad;
    private String v_Soyad;
    private String v_Tel;
    private String v_Mail;
    private String v_Sifre;
    private String v_Adres;
    private String ogrenci_Ad;
    private String ogrenci_Soyad;
    private Integer ogrenci_Numara;
    private Long v_Tc_No;

    public Veli(){
    }

    public Veli(String v_Ad, String v_Soyad, String v_Tel, String v_Mail, String v_Sifre, String v_Adres, String ogrenci_Ad, String ogrenci_Soyad
            , Integer ogrenci_Numara, Long v_Tc_No) {
        this.v_Ad = v_Ad;
        this.v_Soyad = v_Soyad;
        this.v_Tel = v_Tel;
        this.v_Mail = v_Mail;
        this.v_Sifre = v_Sifre;
        this.v_Adres = v_Adres;
        this.ogrenci_Ad = ogrenci_Ad;
        this.ogrenci_Soyad = ogrenci_Soyad;
        this.ogrenci_Numara = ogrenci_Numara;
        this.v_Tc_No = v_Tc_No;
    }

    public String getV_Ad() {
        return v_Ad;
    }

    public void setV_Ad(String v_Ad) {
        this.v_Ad = v_Ad;
    }

    public String getv_Soyad() { return v_Soyad; }

    public void setv_Soyad(String v_Soyad) { this.v_Soyad = v_Soyad; }

    public String getv_Tel() { return v_Tel; }

    public void setv_Tel(String v_Tel) { this.v_Tel = v_Tel; }

    public String getv_Mail() { return v_Mail; }

    public void setv_Mail(String v_Mail) { this.v_Mail = v_Mail; }

    public String getv_Sifre() { return v_Sifre; }

    public void setv_Sifre(String v_Sifre) { this.v_Sifre = v_Sifre; }

    public String getV_Adres() { return v_Adres; }

    public void setV_Adres(String v_Adres) { this.v_Adres = v_Adres; }

    public String getOgrenci_Ad() { return ogrenci_Ad; }

    public void setOgrenci_Ad(String ogrenci_Ad) { this.ogrenci_Ad = ogrenci_Ad; }

    public String getOgrenci_Soyad() { return ogrenci_Soyad; }

    public void setOgrenci_Soyad(String ogrenci_Soyad) { this.ogrenci_Soyad = ogrenci_Soyad; }

    public Integer getOgrenci_Numara() { return ogrenci_Numara; }

    public void setOgrenci_Numara(Integer ogrenci_Numara) { this.ogrenci_Numara = ogrenci_Numara; }

    public Long getv_Tc_No() { return v_Tc_No; }

    public void setv_Tc_No(Long v_Tc_No) { this.v_Tc_No = v_Tc_No; }
}