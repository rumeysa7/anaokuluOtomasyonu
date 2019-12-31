package com.example.anaokuluotomasyonu;

public class Yemek
{
    private Integer ogrenci_No;
    private String ogrenci_Ad;
    private String ogrenci_Soyad;
    private Boolean yemek_Kontrol;
    private String tarih;
    private String yemek_Aciklama;

    public Yemek() {
    }

    public Yemek(Integer ogrenci_No, String ogrenci_Ad, String ogrenci_Soyad, Boolean yemek_Kontrol, String tarih, String yemek_Aciklama) {
        this.ogrenci_No = ogrenci_No;
        this.ogrenci_Ad = ogrenci_Ad;
        this.ogrenci_Soyad = ogrenci_Soyad;
        this.yemek_Kontrol = yemek_Kontrol;
        this.tarih = tarih;
        this.yemek_Aciklama = yemek_Aciklama;
    }

    public Integer getOgrenci_No() {
        return ogrenci_No;
    }

    public void setOgrenci_No(Integer ogrenci_No) {
        this.ogrenci_No = ogrenci_No;
    }

    public String getOgrenci_Ad() {
        return ogrenci_Ad;
    }

    public void setOgrenci_Ad(String ogrenci_Ad) {
        this.ogrenci_Ad = ogrenci_Ad;
    }

    public String getOgrenci_Soyad() {
        return ogrenci_Soyad;
    }

    public void setOgrenci_Soyad(String ogrenci_Soyad) {
        this.ogrenci_Soyad = ogrenci_Soyad;
    }

    public Boolean getYemek_Kontrol() {
        return yemek_Kontrol;
    }

    public void setYemek_Kontrol(Boolean yemek_Kontrol) {
        this.yemek_Kontrol = yemek_Kontrol;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getYemek_Aciklama() {
        return yemek_Aciklama;
    }

    public void setYemek_Aciklama(String yemek_Aciklama) {
        this.yemek_Aciklama = yemek_Aciklama;
    }
}