package com.example.project.object;

public class User {
    private String sPhongBan, maNV, tenNV, gioiTinh, email, bangCap, soDT, sNoiO;

    public User() {
    }

    public User( String maNV, String tenNV, String phongBan, String gioiTinh, String email, String bangCap, String soDT, String sNoiO) {

        this.sPhongBan = phongBan;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.bangCap = bangCap;
        this.soDT = soDT;
        this.sNoiO = sNoiO;
    }

    public String getsPhongBan() {
        return sPhongBan;
    }

    public void setsPhongBan(String sPhongBan) {
        this.sPhongBan = sPhongBan;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBangCap() {
        return bangCap;
    }

    public void setBangCap(String bangCap) {
        this.bangCap = bangCap;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getsNoiO() {
        return sNoiO;
    }

    public void setsNoiO(String sNoiO) {
        this.sNoiO = sNoiO;
    }


    @Override
    public String toString() {
        return "User{" +
                "sPhongBan='" + sPhongBan + '\'' +
                ", maNV='" + maNV + '\'' +
                ", tenNV='" + tenNV + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", email='" + email + '\'' +
                ", bangCap='" + bangCap + '\'' +
                ", soDT='" + soDT + '\'' +
                ", sNoiO='" + sNoiO + '\'' +
                '}';
    }
}
