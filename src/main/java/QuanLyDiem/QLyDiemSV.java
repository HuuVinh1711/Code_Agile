/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyDiem;

/**
 *
 * @author Huu Vinh
 */
public class QLyDiemSV {

    private String MaSV, HoTen;
    private double TiengAnh, TinHoc, GDTC;

    public QLyDiemSV() {
    }

    public QLyDiemSV(String MaSV, String HoTen, double TiengAnh, double TinHoc, double GDTC, double DiemTB) {
        this.MaSV = MaSV;
        this.HoTen = HoTen;
        this.TiengAnh = TiengAnh;
        this.TinHoc = TinHoc;
        this.GDTC = GDTC;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String MaSV) {
        this.MaSV = MaSV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public double getTiengAnh() {
        return TiengAnh;
    }

    public void setTiengAnh(double TiengAnh) {
        this.TiengAnh = TiengAnh;
    }

    public double getTinHoc() {
        return TinHoc;
    }

    public void setTinHoc(double TinHoc) {
        this.TinHoc = TinHoc;
    }

    public double getGDTC() {
        return GDTC;
    }

    public void setGDTC(double GDTC) {
        this.GDTC = GDTC;
    }

    public double getDiemTB() {
        double diem = (TiengAnh + TinHoc + GDTC) / 3;
        return diem;
    }

}
