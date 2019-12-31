package com.example.anaokuluotomasyonu;

import android.graphics.Picture;

public class Etkinlik
{
    private String etkinlik_Ad;
    private String etkinlik_Tarih;
    private String etkinlik_Aciklama;


    public Etkinlik() {
    }

    public Etkinlik(String etkinlik_Ad, String etkinlik_Tarih, String etkinlik_Aciklama) {
        this.etkinlik_Ad = etkinlik_Ad;
        this.etkinlik_Tarih = etkinlik_Tarih;
        this.etkinlik_Aciklama = etkinlik_Aciklama;
    }

    public String getEtkinlik_Ad() {
        return etkinlik_Ad;
    }

    public void setEtkinlik_Ad(String etkinlik_Ad) {
        this.etkinlik_Ad = etkinlik_Ad;
    }

    public String getEtkinlik_Tarih() {
        return etkinlik_Tarih;
    }

    public void setEtkinlik_Tarih(String etkinlik_Tarih) {
        this.etkinlik_Tarih = etkinlik_Tarih;
    }

    public String getEtkinlik_Aciklama() {
        return etkinlik_Aciklama;
    }

    public void setEtkinlik_Aciklama(String etkinlik_Aciklama) {
        this.etkinlik_Aciklama = etkinlik_Aciklama;
    }
}
