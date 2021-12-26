import java.io.*;
import java.sql.*;
import java.util.*;
import java.lang.*;

public class Program {
    // --MySQL setup
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/pbo_java";
    static final String DB_USER = "root";
    static final String DB_PASS = "";

    static Connection connectionSQL;
    static Statement statementSQL;
    static ResultSet resultSetSQL;

    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader inputBufferedReader = new BufferedReader(inputStreamReader);

    public static void main(String[] args) throws Exception {
        Scanner inputUser = new Scanner(System.in);
        // -- object for history --
        ArrayList<String> historyPenjualan = new ArrayList<String>();

        // -- continue --
        boolean tf = true;
        String tfAns;

        // -- tumbal --
        String tempNamaBarang = null;
        int tempJumlahBarang;
        int tempHargaBarang;
        int tempHargaBarang1;
        // --tumbal db --
        String tempJumlahBarangString = null;
        String tempHargaBarangString = null;

        // -- property setup --
        int pilihan, pilihan1;

        // -- Main --
        do {
            System.out.println("--- Program Transaksi Barang ---");
            System.out.println("1. Makanan");
            System.out.println("2. Minuman");
            System.out.println("3. History Penjualan");
            System.out.println("4. Hapus History");
            System.out.println("5. Cek Database");
            System.out.println("6. Exit");
            System.out.print("Masukkan Pilihan: ");
            pilihan = inputUser.nextInt();
            inputUser.nextLine();
            switch (pilihan) {
                case 1:
                    try {
                        Class.forName(JDBC_DRIVER);
                        connectionSQL = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                        statementSQL = connectionSQL.createStatement();
                        System.out.print("Nama Barang\t: ");
                        tempNamaBarang = inputUser.nextLine();
                        System.out.print("Harga Barang\t: ");
                        tempHargaBarang = inputUser.nextInt();
                        System.out.print("Jumlah Barang\t: ");
                        tempJumlahBarang = inputUser.nextInt();
                        String sql = "INSERT INTO pbo_transaksi (`nama_barang`, `harga_barang`, `qty`) VALUES('%s', '%d', '%d')";
                        sql = String.format(sql, tempNamaBarang, tempHargaBarang, tempJumlahBarang);
                        statementSQL.execute(sql);
                        Transaksi makanan1 = new Makanan(tempNamaBarang, tempHargaBarang, tempJumlahBarang);
                        historyPenjualan.add(tempNamaBarang);
                        makanan1.display();
                        System.out.print("\nLanjut? <Ya/Tidak>: ");
                        inputUser.nextLine();
                        tfAns = inputUser.nextLine();
                        makanan1.lanjut(tfAns);
                    } catch (InputMismatchException e) {
                        System.err.println("Terdapat Kesalahan Input!");
                        tf = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try {
                        Class.forName(JDBC_DRIVER);
                        connectionSQL = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                        statementSQL = connectionSQL.createStatement();
                        System.out.print("Nama Barang\t: ");
                        tempNamaBarang = inputUser.nextLine();
                        System.out.print("Harga Barang\t: ");
                        tempHargaBarang = inputUser.nextInt();
                        System.out.print("Jumlah Barang\t: ");
                        tempJumlahBarang = inputUser.nextInt();
                        String sql = "INSERT INTO pbo_transaksi (`nama_barang`, `harga_barang`, `qty`) VALUES('%s', '%d', '%d')";
                        sql = String.format(sql, tempNamaBarang, tempHargaBarang, tempJumlahBarang);
                        statementSQL.execute(sql);
                        Transaksi minuman1 = new Minuman(tempNamaBarang, tempHargaBarang, tempJumlahBarang);
                        historyPenjualan.add(tempNamaBarang);
                        minuman1.display();
                        System.out.print("\nLanjut? <Ya/Tidak>: ");
                        inputUser.nextLine();
                        tfAns = inputUser.nextLine();
                        minuman1.lanjut(tfAns);
                    } catch (InputMismatchException e) {
                        System.err.println("Terdapat Kesalahan Input!");
                        tf = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    if (historyPenjualan.isEmpty()) {
                        System.out.println("History penjualan tidak ada!");
                    } else {
                        System.out.println("History Penjualan: " + historyPenjualan);
                    }
                    break;

                case 4:
                    if (historyPenjualan.isEmpty()) {
                        System.out.println("History penjualan kosong!");
                    } else {
                        historyPenjualan.clear();
                        System.out.println("History Penjualan berhasil dihapus!" + historyPenjualan);
                    }
                    break;

                case 5:
                    System.out.println("1. Tampilkan data dari Database");
                    System.out.println("2. Hapus data dari Database");
                    System.out.println("3. Cari data dari Database");
                    System.out.println("4. Ubah data dari Database");
                    System.out.println("5. Exit");
                    pilihan1 = inputUser.nextInt();
                    inputUser.nextLine();
                    switch (pilihan1) {
                        case 1:
                            tampilData();
                            break;

                        case 2:
                            hapusData();
                            break;

                        case 3:
                            cariData();
                            break;

                        case 4:
                            ubahData();
                            break;
                    }
                    break;

                case 6:
                    System.exit(0);
                    break;

                default:
                    throw new Exception("Masukkan pilihan dengan benar! (1-2)");
            }
        } while (tf);
    }

    // === CRUD SESSION ===
    // -- SHOW DATA--
    private static void tampilData() {
        try {
            Class.forName(JDBC_DRIVER);
            connectionSQL = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statementSQL = connectionSQL.createStatement();
            String sql = "SELECT * FROM pbo_transaksi";
            resultSetSQL = statementSQL.executeQuery(sql);
            while (resultSetSQL.next()) {
                System.out.println("\nNo\t\t\t\t\t: " + resultSetSQL.getInt("no"));
                System.out.println("Tanggal Transaksi\t: " + resultSetSQL.getString("tgl_transaksi"));
                System.out.println("Nama Barang\t\t\t: " + resultSetSQL.getString("nama_barang"));
                System.out.println("Harga Barang\t\t: " + resultSetSQL.getString("harga_barang"));
                System.out.println("Jumlah Barang\t\t: " + resultSetSQL.getString("qty"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void hapusData() {
        Scanner inputUserDelete = new Scanner(System.in);
        try {
            connectionSQL = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statementSQL = connectionSQL.createStatement();
            System.out.print("Masukkan no data yang akan dihapus : ");
            int noData = inputUserDelete.nextInt();
            String sql = "DELETE FROM pbo_transaksi WHERE no = '" + noData + "' ";
            statementSQL.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void cariData() {
        Scanner inputUserFind = new Scanner(System.in);
        try {
            connectionSQL = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statementSQL = connectionSQL.createStatement();
            System.out.println("Masukkan nama barang yang ingin Anda cari: ");
            String namaBarang = inputUserFind.nextLine();
            String sql = "SELECT * FROM pbo_transaksi WHERE nama_barang = '" + namaBarang + "'";
            resultSetSQL = statementSQL.executeQuery(sql);
            while (resultSetSQL.next()) {
                System.out.println("\nNo\t\t\t\t\t: " + resultSetSQL.getInt("no"));
                System.out.println("Tanggal Transaksi\t: " + resultSetSQL.getString("tgl_transaksi"));
                System.out.println("Nama Barang\t\t\t: " + resultSetSQL.getString("nama_barang"));
                System.out.println("Harga Barang\t\t: " + resultSetSQL.getString("harga_barang"));
                System.out.println("Jumlah Barang\t\t: " + resultSetSQL.getString("qty"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void ubahData() {
        Scanner inputUserUpdate = new Scanner(System.in);
        System.out.println("Masukan no data yang akan anda ubah: ");
        int noUpdate = inputUserUpdate.nextInt();
        try {
            connectionSQL = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statementSQL = connectionSQL.createStatement();
            String sql = "SELECT * FROM pbo_transaksi WHERE no = '" + noUpdate + "'";
            resultSetSQL = statementSQL.executeQuery(sql);
            System.out.println("\nData yang akan  diubah ?");
            System.out.println(".................................");
            System.out.println("\t(1)\tNama Barang");
            System.out.println("\t(2)\tHarga Barang");
            System.out.println("\t(3)\tJumlah Barang");
            System.out.println("Silahkan Masukan angka di atas ! >");
            Scanner terminalInput = new Scanner(System.in);
            String inputUser = terminalInput.nextLine();
            inputUserUpdate.nextLine();
            if ("1".equals(inputUser)) {
                System.out.println("\n\nMasukan Nama Barang Baru, (Cth: Bakso Bakar Rebus / Milo Panas with Ice Cube) : ");
                String namaBarangTemp = inputUserUpdate.nextLine();
                String sql1 = "UPDATE pbo_transaksi SET nama_barang='" + namaBarangTemp + "' WHERE no='" + noUpdate + "'";
                statementSQL.execute(sql1);
                System.out.println("\nNama Barang Berhasil diubah");
            } else if ("2".equals(inputUser)) {
                System.out.println("Masukan Harga Barang Baru\t\t\t\t\t : ");
                int hargaBaru = inputUserUpdate.nextInt();
                String sql2 = "UPDATE pbo_transaksi SET harga_barang='" + hargaBaru + "' WHERE no='" + noUpdate + "'";
                statementSQL.execute(sql2);
                System.out.println("\nHarga Barang Berhasil diubah");
            } else if ("3".equals(inputUser)) {
                System.out.println("Masukan Jumlah Baru\t\t\t\t\t : ");
                int qtyBaru = inputUserUpdate.nextInt();
                String sql3 = "UPDATE pbo_transaksi SET qty='" + qtyBaru + "' WHERE no='" + noUpdate + "'";
                statementSQL.execute(sql3);
                System.out.println("\nJumlah Barang Berhasil diubah");
            } else {
                System.err.println("Input Yang anda masukan salah !!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



