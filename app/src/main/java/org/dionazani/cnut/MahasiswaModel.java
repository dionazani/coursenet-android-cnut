package org.dionazani.cnut;

public class MahasiswaModel {

    private int id;
    private String kodeMahasiswa;
    private String namaMahasiswa;
    private String jurusan;

    public MahasiswaModel() {}

    public MahasiswaModel(int id, String kodeMahasiswa, String namaMahasiswa, String jurusan) {
        super();
        this.id = id;
        this.kodeMahasiswa = kodeMahasiswa;
        this.namaMahasiswa = namaMahasiswa;
        this.jurusan = jurusan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKodeMahasiswa() {
        return kodeMahasiswa;
    }

    public void setKodeMahasiswa(String kodeMahasiswa) {
        this.kodeMahasiswa = kodeMahasiswa;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}
