📱 Tugas Fingerprint Sensor

Aplikasi ini dirancang untuk Fakultas Ilmu Komputer, Universitas Kuningan. Aplikasi ini menyediakan fitur autentikasi sidik jari, peta kampus, daftar pejabat struktural, jadwal praktikum, dan informasi rapat kampus.

✨ Fitur Utama
🔒 1. Autentikasi Sidik Jari
Aplikasi ini menggunakan autentikasi sidik jari untuk mengamankan fitur tertentu. Pengguna harus melakukan verifikasi menggunakan sidik jari untuk mengakses informasi sensitif.

🗺️ 2. Lokasi Kampus
Pengguna dapat melihat peta kampus menggunakan Google Maps API. Terdapat penanda khusus untuk menunjukkan lokasi Fakultas Ilmu Komputer.

📋 3. Daftar Pejabat Struktural
Menampilkan daftar pejabat struktural di Fakultas Ilmu Komputer dalam bentuk RecyclerView. Setiap pejabat memiliki deskripsi singkat dan foto.

📅 4. Jadwal Praktikum
Pengguna dapat melihat jadwal praktikum yang tersedia.

🏫 5. Informasi Rapat Kampus
Menyediakan informasi terkait rapat yang akan diadakan di kampus.

🗂️ Struktur Proyek
perl
Salin kode
Tugas_Fingerprint_Sensor/
│
├── app/
│   ├── manifests/
│   │   └── AndroidManifest.xml
│   │
│   ├── java/com/wira_fkom/tugas_fingerprint_sensor/
│   │   ├── adapter/
│   │   │   └── Adapter.java
│   │   ├── data/
│   │   │   └── Data.java
│   │   ├── daftar_dosen/
│   │   │   └── RecyclerviewActivity.java
│   │   ├── map/
│   │   │   └── MapsActivity.java
│   │   ├── ui/
│   │   │   ├── AboutActivity.java
│   │   │   ├── DeskripsiActivity.java
│   │   │   ├── MainActivity.java
│   │   │   └── SecretActivity.java
│   │   └── TugasFingerprintSensorApp.java
│   │
│   └── res/
│       ├── drawable/
│       ├── layout/
│       │   ├── activity_main.xml
│       │   ├── activity_maps.xml
│       │   └── activity_recyclerview.xml
│       └── values/
│           ├── strings.xml
│           └── styles.xml
│
└── README.md
🚀 Instalasi
Clone repositori ini:

sh
Salin kode
git clone https://github.com/Wira07/Tugas_Fingerprint_Sensor.git
Buka di Android Studio:
Buka Android Studio dan pilih File -> Open, lalu arahkan ke folder proyek yang telah di-clone.

Tambahkan API Key Google Maps:
Tambahkan API Key Google Maps Anda di file AndroidManifest.xml:

xml
Salin kode
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_API_KEY_HERE" />
Jalankan Aplikasi:
Pilih emulator atau perangkat fisik, lalu klik tombol 'Run' di Android Studio.

📖 Penggunaan
Autentikasi Sidik Jari:
Pada halaman utama, klik tombol "ABSENSI" untuk membuka autentikasi sidik jari.

Lihat Lokasi Kampus:
Klik tombol "LOCATION" untuk membuka peta kampus.

Lihat Daftar Pejabat Struktural:
Klik tombol "PEJABAT STRUKTURAL FKOM" untuk melihat daftar pejabat.

Lihat Jadwal Praktikum:
Klik tombol "JADWAL PRAKTIKUM" untuk melihat jadwal praktikum.

🤝 Kontribusi
Jika Anda ingin berkontribusi pada proyek ini, silakan buat pull request dengan perubahan yang ingin Anda tambahkan. Semua kontribusi sangat dihargai!

📜 Lisensi
Proyek ini dilisensikan di bawah lisensi MIT. Lihat file LICENSE untuk informasi lebih lanjut.

📧 Kontak
Jika Anda memiliki pertanyaan atau saran, silakan hubungi saya melalui email di wiralodrasaputra07@gmail.com.
