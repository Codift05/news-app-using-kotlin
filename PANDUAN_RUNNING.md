# 📋 PANDUAN LENGKAP MENJALANKAN APLIKASI NEWSAPP

## 🎯 Step-by-Step Tutorial

### LANGKAH 1: Persiapan

#### 1.1 Install Android Studio
- Download Android Studio Dolphin 2021.3.1 atau lebih baru
- Install dengan semua komponen default
- Tunggu hingga instalasi selesai

#### 1.2 Setup Android SDK
- Buka Android Studio
- File → Settings → Appearance & Behavior → System Settings → Android SDK
- Pastikan Android 13.0 (API Level 33) terinstall
- Klik Apply untuk download SDK jika belum ada

### LANGKAH 2: Mendapatkan API Key

#### 2.1 Register di NewsAPI
1. Buka browser, kunjungi: **https://newsapi.org/**
2. Klik tombol **"Get API Key"** atau **"Sign Up"**
3. Isi form registrasi:
   - First name
   - Email address
   - Password
4. Pilih plan **"Developer"** (GRATIS)
5. Setuju Terms & Conditions
6. Klik **"Submit"**

#### 2.2 Copy API Key
1. Setelah registrasi, Anda akan diarahkan ke dashboard
2. API Key akan ditampilkan di halaman utama
3. Copy API Key tersebut (format: 32 karakter alphanumeric)
4. Simpan di notepad untuk digunakan nanti

**Contoh API Key:**
```
1234567890abcdef1234567890abcdef
```

### LANGKAH 3: Setup Project

#### 3.1 Buka Project
1. Buka Android Studio
2. File → Open
3. Browse ke folder project: `d:\Intership Bank Mandiri\final_project`
4. Klik OK

#### 3.2 Tunggu Gradle Sync
- Android Studio akan otomatis sync Gradle
- Lihat progress bar di bawah
- Tunggu hingga muncul "BUILD SUCCESSFUL"
- Jika ada error, klik "Sync Project with Gradle Files" (icon gajah di toolbar)

### LANGKAH 4: Konfigurasi API Key

#### 4.1 Buka File Constants.kt
Lokasi: `app/src/main/java/dev/rakamin/newsapp/utils/Constants.kt`

**Cara buka:**
1. Di panel kiri (Project), expand:
   - app
   - src
   - main
   - java
   - dev.rakamin.newsapp
   - utils
2. Double-click `Constants.kt`

#### 4.2 Ganti API Key
Cari baris:
```kotlin
const val API_KEY = "YOUR_API_KEY_HERE"
```

Ganti dengan API Key Anda:
```kotlin
const val API_KEY = "1234567890abcdef1234567890abcdef"
```

#### 4.3 Save File
- Tekan Ctrl+S atau File → Save All
- Pastikan tidak ada tanda * di tab file (artinya sudah tersimpan)

### LANGKAH 5: Setup Emulator (Jika Tidak Pakai HP)

#### 5.1 Buka AVD Manager
- Klik icon HP di toolbar (Device Manager)
- Atau Tools → Device Manager

#### 5.2 Create Virtual Device
1. Klik "Create Device"
2. Pilih device: **Pixel 5** (recommended)
3. Klik Next
4. Pilih System Image: **API 33** (Android 13)
   - Jika belum ada, klik Download
5. Klik Next
6. Nama: "Pixel_5_API_33"
7. Klik Finish

#### 5.3 Start Emulator
- Klik tombol Play ▶️ di samping device
- Tunggu emulator boot up (pertama kali agak lama)

### LANGKAH 6: Setup HP Fisik (Opsional)

#### 6.1 Aktifkan Developer Options
1. Buka Settings di HP
2. About Phone → Tap "Build Number" 7x
3. Developer Options akan muncul

#### 6.2 Aktifkan USB Debugging
1. Settings → Developer Options
2. Aktifkan "USB Debugging"
3. Hubungkan HP ke laptop via USB

#### 6.3 Verify Connection
1. Di Android Studio, lihat dropdown device
2. HP Anda akan muncul di list
3. Klik "Allow" di HP jika ada popup

### LANGKAH 7: Run Aplikasi

#### 7.1 Pilih Device
- Klik dropdown device di toolbar
- Pilih emulator atau HP Anda

#### 7.2 Run
- Klik tombol Run ▶️ (warna hijau)
- Atau tekan **Shift + F10**
- Atau Run → Run 'app'

#### 7.3 Tunggu Build
- Gradle akan build APK
- Lihat progress di bawah
- Pertama kali build bisa 2-5 menit
- Build berikutnya lebih cepat (30 detik - 1 menit)

#### 7.4 Aplikasi Terbuka
- Aplikasi otomatis terinstall di device
- Aplikasi otomatis terbuka
- Anda akan melihat Breaking News & Latest News

### LANGKAH 8: Testing Fitur

#### 8.1 Test Breaking News
- Scroll horizontal di bagian "Breaking News"
- Klik salah satu berita
- Akan masuk ke halaman detail

#### 8.2 Test Latest News
- Scroll vertical di bagian "Latest News"
- Lihat loading indicator
- Scroll terus ke bawah
- Berita baru akan otomatis load (infinite scroll)

#### 8.3 Test Detail
- Klik salah satu berita
- Lihat gambar, judul, konten
- Klik tombol "Buka di Browser"
- Browser akan terbuka dengan artikel lengkap

#### 8.4 Test Refresh
- Swipe down dari atas
- Data akan refresh
- Loading indicator muncul

### LANGKAH 9: Build APK untuk Share

#### 9.1 Build Debug APK
1. Build → Build Bundle(s) / APK(s) → Build APK(s)
2. Tunggu proses build
3. Klik "locate" saat muncul notifikasi
4. APK ada di: `app/build/outputs/apk/debug/app-debug.apk`

#### 9.2 Install APK ke HP
- Copy APK ke HP
- Buka File Manager
- Klik APK
- Izinkan "Install from Unknown Sources"
- Install

#### 9.3 Share APK
- APK bisa di-share via:
  - WhatsApp
  - Email
  - Google Drive
  - USB Transfer

### LANGKAH 10: Build Release APK (Production)

#### 10.1 Generate Signed APK
1. Build → Generate Signed Bundle / APK
2. Pilih APK → Next
3. Create new keystore:
   - Key store path: pilih lokasi
   - Password: buat password
   - Alias: news-app-key
   - Password: sama seperti keystore
   - Validity: 25 tahun
   - Certificate info: isi nama Anda
4. Next
5. Pilih "release"
6. V2 signature
7. Finish

#### 10.2 Locate APK
- APK release ada di: `app/release/app-release.apk`
- Ini APK yang siap dipublish ke Play Store

## 🐛 Troubleshooting

### Error: "401 Unauthorized"
**Penyebab:** API Key salah atau belum diisi
**Solusi:**
1. Cek file Constants.kt
2. Pastikan API_KEY sudah diisi
3. Pastikan tidak ada spasi atau typo
4. Rebuild project (Build → Rebuild Project)

### Error: "No Internet Connection"
**Penyebab:** Device tidak terkoneksi internet
**Solusi:**
- **Emulator:** Settings → Network & Internet → WiFi → AndroidWifi
- **HP:** Pastikan WiFi/Data aktif

### Error: Gambar Tidak Muncul
**Penyebab:** URL gambar dari API tidak valid
**Solusi:** 
- Ini normal, beberapa artikel tidak punya gambar
- Aplikasi akan tampilkan placeholder

### Error: "Gradle Sync Failed"
**Penyebab:** Koneksi internet lambat atau cache corrupt
**Solusi:**
1. File → Invalidate Caches → Invalidate and Restart
2. Tutup Android Studio
3. Delete folder `.gradle` di project
4. Buka lagi Android Studio
5. Let it sync

### Error: "Emulator: Process finished with exit code"
**Penyebab:** Emulator crash atau RAM tidak cukup
**Solusi:**
1. AVD Manager → Edit emulator
2. Kurangi RAM ke 2048 MB
3. Kurangi Internal Storage ke 2048 MB
4. Save

### Error: "Device Unauthorized"
**Penyebab:** USB Debugging tidak diizinkan
**Solusi:**
1. Cabut USB
2. Settings → Developer Options → Revoke USB Debugging
3. Colok lagi USB
4. Klik "Allow" di popup HP

## ✅ Checklist Sebelum Submit

- [ ] API Key sudah diganti di Constants.kt
- [ ] Aplikasi bisa run tanpa error
- [ ] Breaking News muncul dengan benar
- [ ] Latest News muncul dengan benar
- [ ] Infinite scroll berfungsi
- [ ] Detail page berfungsi
- [ ] Tombol "Buka di Browser" berfungsi
- [ ] Pull to refresh berfungsi
- [ ] APK berhasil di-build
- [ ] APK bisa diinstall di HP
- [ ] Tidak ada crash saat digunakan

## 📞 Bantuan Lebih Lanjut

Jika masih ada masalah:
1. Cek logcat di Android Studio (panel bawah)
2. Screenshot error message
3. Google error message tersebut
4. Tanya mentor atau teman

## 🎉 Selamat!

Jika semua langkah berhasil, aplikasi NewsApp Anda sudah siap!

**Good luck dengan project Rakamin Academy! 🚀**
