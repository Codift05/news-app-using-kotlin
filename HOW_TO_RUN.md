# 🚀 Quick Start - Cara Menjalankan Aplikasi

## Metode 1: Menggunakan Script Otomatis (TERMUDAH) ⭐

### Di VS Code Terminal:
```powershell
.\run-app.ps1
```

### Atau di PowerShell Eksternal:
1. **Tutup VS Code dulu**
2. **Klik kanan** pada file `run-app.ps1` → **Run with PowerShell**

### Atau di Command Prompt:
```batch
run-app.bat
```

---

## Metode 2: Manual Build & Install

### Langkah 1: Build APK
```powershell
# Stop daemon
.\gradlew.bat --stop

# Build
.\gradlew.bat assembleDebug
```

APK akan dibuat di: `app\build\outputs\apk\debug\app-debug.apk`

### Langkah 2: Install ke Device

#### Dengan HP Android:
1. **Aktifkan USB Debugging:**
   - Settings → About Phone → Tap "Build Number" 7x
   - Settings → Developer Options → USB Debugging (ON)
2. **Sambungkan HP ke PC**
3. **Install:**
   ```powershell
   adb install -r app\build\outputs\apk\debug\app-debug.apk
   ```

#### Dengan Emulator:
1. **Buka Android Studio → AVD Manager**
2. **Start Emulator**
3. **Install:**
   ```powershell
   adb install -r app\build\outputs\apk\debug\app-debug.apk
   ```

#### Manual (tanpa ADB):
1. **Copy file** `app\build\outputs\apk\debug\app-debug.apk` ke HP
2. **Install langsung** dari file manager

---

## Metode 3: Dari Android Studio

1. **Buka Android Studio**
2. **Open Project** → Pilih folder `final_project`
3. **Tunggu Gradle Sync selesai**
4. **Klik tombol Run ▶️** (atau Shift+F10)
5. **Pilih device/emulator**

---

## 🔧 Troubleshooting

### Build Gagal - File Terkunci?
1. **Tutup VS Code**
2. **Task Manager** → End semua process "Java" dan "OpenJDK"
3. **Jalankan script** `run-app.bat` dari Windows Explorer

### ADB Not Found?
```powershell
# Set ADB Path (sesuaikan dengan lokasi Android SDK)
$env:PATH += ";C:\Users\YourUsername\AppData\Local\Android\Sdk\platform-tools"
```

### Device Not Detected?
1. Cek USB Debugging enabled
2. Install USB drivers untuk HP Anda
3. Coba kabel USB lain
4. Jalankan: `adb devices`

---

## 📱 Setelah Terinstall

Aplikasi akan muncul dengan nama: **News App**

Untuk membuka via command line:
```powershell
adb shell am start -n dev.rakamin.newsapp/.ui.view.MainActivity
```

---

## ⚙️ Build Variants

### Debug Build (Development):
```powershell
.\gradlew.bat assembleDebug
```

### Release Build (Production):
```powershell
.\gradlew.bat assembleRelease
```

---

## 🎯 Requirements

- ✅ Java 21 (sudah terinstall di C:\Users\Miftah\.jdk\jdk-21.0.8)
- ✅ Android SDK (API 33)
- ✅ Device/Emulator dengan Android 7.0+ (API 24+)
- ⚠️ Koneksi internet (untuk fetch news)

---

## 📝 Catatan Penting

1. **News API Key:** Pastikan API key di `Constants.kt` valid
2. **Internet Permission:** Sudah ditambahkan di manifest
3. **API Level:** Min SDK 24, Target SDK 33

---

## 🆘 Butuh Bantuan?

Jika masih ada masalah, coba:
1. Clean build: `.\gradlew.bat clean`
2. Stop daemon: `.\gradlew.bat --stop`
3. Restart PC
4. Jalankan `run-app.ps1` lagi

Good luck! 🚀
