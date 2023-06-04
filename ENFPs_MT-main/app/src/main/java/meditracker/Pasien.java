package meditracker;

public class Pasien {
    private String nama;
    private int umur;
    private String diagnosis;
    private String obat;

    public Pasien(String nama, int umur, String diagnosis, String obat) {
        this.nama = nama;
        this.umur = umur;
        this.diagnosis = diagnosis;
        this.obat = obat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getObat() {
        return obat;
    }

    public void setObat(String obat) {
        this.obat = obat;
    }
}
