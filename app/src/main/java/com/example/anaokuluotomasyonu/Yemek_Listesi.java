package com.example.anaokuluotomasyonu;

public class Yemek_Listesi
{
    private String gün;
    private String yemek1;
    private String yemek2;
    private String meyve;
    private String icecek;

    public Yemek_Listesi() {
    }

    public Yemek_Listesi(String gün, String yemek1, String yemek2, String meyve, String icecek) {
        this.gün = gün;
        this.yemek1 = yemek1;
        this.yemek2 = yemek2;
        this.meyve = meyve;
        this.icecek = icecek;
    }

    public String getGün() {
        return gün;
    }

    public void setGün(String gün) {
        this.gün = gün;
    }

    public String getYemek1() {
        return yemek1;
    }

    public void setYemek1(String yemek1) {
        this.yemek1 = yemek1;
    }

    public String getYemek2() {
        return yemek2;
    }

    public void setYemek2(String yemek2) {
        this.yemek2 = yemek2;
    }

    public String getMeyve() {
        return meyve;
    }

    public void setMeyve(String meyve) {
        this.meyve = meyve;
    }

    public String getIcecek() {
        return icecek;
    }

    public void setIcecek(String icecek) {
        this.icecek = icecek;
    }
}
