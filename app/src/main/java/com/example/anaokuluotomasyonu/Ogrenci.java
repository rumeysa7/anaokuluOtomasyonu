package com.example.anaokuluotomasyonu;

import java.util.Date;

public class Ogrenci
{
    private Integer ogrenci_No;
    private String ogrenci_Ad;
    private String ogrenci_Soyad;
    private Long ogrenci_Tc_No;
    private String ogrenci_Veli1;
    private String ogrenci_Veli2;
    private String ogrenci_Boy;
    private String ogrenci_Kilo;
    private String ogrenci_Dogum_Tarih;
    private String ogrenci_Sinif;
    private String o_Ad;
    //Öğrenci Fotoğraf tanımlanacak
    private String ogrenci_Ilac;


    public Ogrenci(){
    }

    public Ogrenci(Integer ogrenci_No, String ogrenci_Ad, String ogrenci_Soyad, Long ogrenci_Tc_No, String ogrenci_Veli1, String ogrenci_Veli2, String ogrenci_Boy, String ogrenci_Kilo, String ogrenci_Dogum_Tarih, String ogrenci_Sinif, String o_Ad, String ogrenci_Ilac) {
        this.ogrenci_No = ogrenci_No;
        this.ogrenci_Ad = ogrenci_Ad;
        this.ogrenci_Soyad = ogrenci_Soyad;
        this.ogrenci_Tc_No = ogrenci_Tc_No;
        this.ogrenci_Veli1 = ogrenci_Veli1;
        this.ogrenci_Veli2 = ogrenci_Veli2;
        this.ogrenci_Boy = ogrenci_Boy;
        this.ogrenci_Kilo = ogrenci_Kilo;
        this.ogrenci_Dogum_Tarih = ogrenci_Dogum_Tarih;
        this.ogrenci_Sinif = ogrenci_Sinif;
        this.o_Ad = o_Ad;
        this.ogrenci_Ilac = ogrenci_Ilac;
    }

    public Ogrenci(Integer ogrenci_No, String ogrenci_Ad, String ogrenci_Soyad) {
        this.ogrenci_No = ogrenci_No;
        this.ogrenci_Ad = ogrenci_Ad;
        this.ogrenci_Soyad = ogrenci_Soyad;
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

    public Long getOgrenci_Tc_No() {
        return ogrenci_Tc_No;
    }

    public void setOgrenci_Tc_No(Long ogrenci_Tc_No) {
        this.ogrenci_Tc_No = ogrenci_Tc_No;
    }

    public String getOgrenci_Veli1() {
        return ogrenci_Veli1;
    }

    public void setOgrenci_Veli1(String ogrenci_Veli1) {
        this.ogrenci_Veli1 = ogrenci_Veli1;
    }

    public String getOgrenci_Veli2() {
        return ogrenci_Veli2;
    }

    public void setOgrenci_Veli2(String ogrenci_Veli2) {
        this.ogrenci_Veli2 = ogrenci_Veli2;
    }

    public String getOgrenci_Boy() {
        return ogrenci_Boy;
    }

    public void setOgrenci_Boy(String ogrenci_Boy) {
        this.ogrenci_Boy = ogrenci_Boy;
    }

    public String getOgrenci_Kilo() {
        return ogrenci_Kilo;
    }

    public void setOgrenci_Kilo(String ogrenci_Kilo) {
        this.ogrenci_Kilo = ogrenci_Kilo;
    }

    public String getOgrenci_Dogum_Tarih() {
        return ogrenci_Dogum_Tarih;
    }

    public void setOgrenci_Dogum_Tarih(String ogrenci_Dogum_Tarih) { this.ogrenci_Dogum_Tarih = ogrenci_Dogum_Tarih; }

    public String getOgrenci_Sinif() {
        return ogrenci_Sinif;
    }

    public void setOgrenci_Sinif(String ogrenci_Sinif) {
        this.ogrenci_Sinif = ogrenci_Sinif;
    }

    public String getO_Ad() {
        return o_Ad;
    }

    public void setO_Ad(String o_Ad) {
        this.o_Ad = o_Ad;
    }

    public String getOgrenci_Ilac() {
        return ogrenci_Ilac;
    }

    public void setOgrenci_Ilac(String ogrenci_Ilac) {
        this.ogrenci_Ilac = ogrenci_Ilac;
    }
}