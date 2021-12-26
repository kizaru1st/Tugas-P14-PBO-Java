public class Minuman extends Barang {
    final String kategori = "Minuman";

    Minuman(String a, int b, int c) {
        super(a, b, c);
    }

    @Override
    public void namaBarang() {
        System.out.println("Nama Barang\t\t: " + super.namaBarang);
        System.out.println("Kategori\t\t: " + this.kategori);
        this.namaBarang = this.namaBarang.toUpperCase();
        System.out.println("Kode Barang\t\t: " + this.namaBarang.charAt(0) + this.namaBarang.charAt(this.namaBarang.length() - 1) + this.namaBarang.length());
    }

    @Override
    public void display() {
        noFaktur();
        namaBarang();
        hargaBarang();
        jumlah();
        subTotal();
        discount();
        totalHarga();
    }

    @Override
    public void discount() {
        if (this.totalHarga >= 100000 && this.totalHarga < 500000) {
            this.diskon = this.totalHarga * 2 / 100;
            System.out.println("Diskon Minuman\t\t: " + this.diskon);
        } else if (this.totalHarga >= 500000 && this.totalHarga < 1000000) {
            this.diskon = this.totalHarga * 3 / 100;
            System.out.println("Diskon Minuman\t\t: " + this.diskon);
        } else if (this.totalHarga >= 1000000 && this.totalHarga < 1500000) {
            this.diskon = this.totalHarga * 4 / 100;
            System.out.println("Diskon Minuman\t\t: " + this.diskon);
        } else if (this.totalHarga >= 1500000 && this.totalHarga < 2000000) {
            this.diskon = this.totalHarga * 5 / 100;
            System.out.println("Diskon Minuman\t\t: " + this.diskon);
        } else if (this.totalHarga >= 2000000) {
            this.diskon = this.totalHarga * 6 / 100;
            System.out.println("Diskon Minuman\t\t: " + this.diskon);
        } else {
            System.out.println("Diskon Minuman\t\t: Tidak ada");
        }
    }

    @Override
    public void totalHarga() {
        this.totalAkhir = this.totalHarga - this.diskon;
        System.out.println("Total Bayar\t\t: " + this.totalAkhir);
    }

}
