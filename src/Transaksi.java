import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.*;

public class Transaksi extends Program implements Penjualan {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //generate no faktur
    int totalHarga; // sebelum diskon
    int totalAkhir; // setelah diskon
    int diskon;

    // -- property constructure --
    String namaBarang;
    int noFaktur;
    int harga;
    int jumlah;

    // -- set date --
    LocalDateTime waktuTanggal = LocalDateTime.now(); 
    DateTimeFormatter dateOnly = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String generateTanggal = waktuTanggal.format(dateOnly);

    // -- set time --
    DateTimeFormatter timeOnly = DateTimeFormatter.ofPattern("HH:mm:ss");
    String generateWaktu = waktuTanggal.format(timeOnly);

    Transaksi(String a, int b, int d) {
        this.namaBarang = a;
        this.harga = b;
        this.jumlah = d;
    }

    public void display() {
        noFaktur();
        namaBarang();
        hargaBarang();
        jumlah();
        subTotal();
        discount();
        totalHarga();
    }

    public void lanjut(String jawab) {
        if ((jawab.equals("tidak")) || (jawab.equals("Tidak"))) {
            System.exit(0);
        } 
        else {}
    }

    // -- interface --
    public void noFaktur() {
        System.out.println("\n");
        System.out.println("Tanggal Transaksi\t: " + generateTanggal);
        System.out.println("Waktu Transaksi\t\t: " + generateWaktu);
        System.out.println("No Faktur Barang\t: " + timestamp.getTime());
    }

    public void namaBarang() {
        System.out.println("Nama Barang\t\t: " + this.namaBarang);
    }

    public void hargaBarang() {
        System.out.println("Harga Barang\t\t: " + this.harga);
    }

    public void jumlah() {
        System.out.println("Jumlah Barang\t\t: " + this.jumlah);
    }

    public void subTotal() {
        this.totalHarga = this.totalHarga + (this.jumlah * this.harga);
        System.out.println("Sub Total\t\t: " + this.totalHarga);
    }

    public void discount() {
        if (this.totalHarga >= 100000 && this.totalHarga < 500000) {
            this.diskon = this.totalHarga * 2 / 100;
            this.totalAkhir = this.totalHarga - this.diskon;
        } 
        else if (this.totalHarga >= 500000 && this.totalHarga < 1000000) {
            this.diskon = this.totalHarga * 3 / 100;
            this.totalAkhir = this.totalHarga - this.diskon;
        } 
        else if (this.totalHarga >= 1000000 && this.totalHarga < 1500000) {
            this.diskon = this.totalHarga * 4 / 100;
            this.totalAkhir = this.totalHarga - this.diskon;
        } 
        else if (this.totalHarga >= 1500000 && this.totalHarga < 2000000) {
            this.diskon = this.totalHarga * 5 / 100;
            this.totalAkhir = this.totalHarga - this.diskon;
        } 
        else {}
    }

    public void totalHarga() {
        System.out.println("Total Bayar\t\t: " + this.totalAkhir);
    }

    
}
