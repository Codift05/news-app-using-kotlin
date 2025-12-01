# 📰 NewsApp - Aplikasi Berita Android

Aplikasi Android modern untuk membaca berita terkini dari NewsAPI dengan fitur Headline dan List News lengkap dengan Infinite Scrolling.

## 🎯 Fitur Utama

- ✅ **Breaking News Headlines** - Menampilkan berita utama dalam format horizontal scroll
- ✅ **Latest News List** - Daftar berita terbaru dengan infinite scroll
- ✅ **Detail Berita** - Tampilan detail lengkap dengan opsi buka di browser
- ✅ **Infinite Scroll** - Otomatis load berita saat scroll ke bawah
- ✅ **Pull to Refresh** - Refresh data dengan swipe down
- ✅ **Modern UI** - Desain Material Design 3 yang clean dan responsive

## 🏗️ Arsitektur

Aplikasi ini menggunakan **MVVM (Model-View-ViewModel)** dengan **Repository Pattern**:

```
📁 dev.rakamin.newsapp/
├── 📁 network/          # Retrofit & API Services
│   ├── NewsInterface.kt
│   └── NewsService.kt
├── 📁 model/            # Data Classes
│   ├── Article.kt
│   ├── Source.kt
│   └── NewsResponse.kt
├── 📁 repository/       # Data Repository
│   └── NewsRepository.kt
├── 📁 viewmodel/        # ViewModels
│   └── NewsViewModel.kt
├── 📁 ui/
│   ├── 📁 adapter/      # RecyclerView Adapters
│   │   ├── HeadlineAdapter.kt
│   │   └── NewsAdapter.kt
│   └── 📁 view/         # Activities
│       ├── MainActivity.kt
│       └── DetailActivity.kt
└── 📁 utils/            # Utilities
    └── Constants.kt
```

## 🛠️ Tech Stack

- **Language**: Kotlin
- **IDE**: Android Studio Dolphin 2021.3.1
- **Gradle**: 7.2
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 33 (Android 13)

### Libraries:
- **Retrofit 2.9.0** - REST API client
- **OkHttp 4.10.0** - HTTP client dengan logging
- **Glide 4.14.2** - Image loading
- **Lifecycle Components** - ViewModel & LiveData
- **Coroutines** - Asynchronous programming
- **Material Components** - Modern UI
- **RecyclerView & CardView** - List UI

## 📋 Prerequisites

1. Android Studio Dolphin 2021.3.1 atau lebih baru
2. JDK 8 atau lebih baru
3. Android SDK dengan API Level 33
4. API Key dari NewsAPI (gratis di https://newsapi.org/)

## 🚀 Cara Setup & Running

### 1. Clone Project

```bash
git clone <repository-url>
cd final_project
```

### 2. Setup API Key

Buka file `Constants.kt` di path:
```
app/src/main/java/dev/rakamin/newsapp/utils/Constants.kt
```

Ganti `YOUR_API_KEY_HERE` dengan API Key Anda:

```kotlin
const val API_KEY = "masukkan_api_key_anda_disini"
```

**Cara mendapatkan API Key:**
1. Kunjungi https://newsapi.org/
2. Klik "Get API Key" atau "Sign Up"
3. Daftar dengan email Anda (GRATIS)
4. Copy API Key yang diberikan
5. Paste ke file Constants.kt

### 3. Sync Gradle

- Buka project di Android Studio
- Tunggu hingga Gradle sync selesai
- Jika ada error, klik "Sync Project with Gradle Files"

### 4. Run Aplikasi

**Opsi 1: Menggunakan Emulator**
1. Buka AVD Manager (Android Virtual Device)
2. Buat emulator baru atau gunakan yang sudah ada
3. Klik tombol Run ▶️ atau Shift+F10
4. Pilih emulator yang sudah dibuat

**Opsi 2: Menggunakan Device Fisik**
1. Aktifkan Developer Options & USB Debugging di HP
2. Hubungkan HP ke komputer via USB
3. Klik Run ▶️ dan pilih device Anda

### 5. Build APK

**Debug APK:**
```bash
./gradlew assembleDebug
```
APK ada di: `app/build/outputs/apk/debug/app-debug.apk`

**Release APK:**
```bash
./gradlew assembleRelease
```
APK ada di: `app/build/outputs/apk/release/app-release.apk`

**Atau via Android Studio:**
1. Build → Build Bundle(s) / APK(s) → Build APK(s)
2. Tunggu sampai selesai
3. Klik "locate" untuk membuka folder APK

## 📱 Cara Menggunakan Aplikasi

1. **Home Screen**
   - Lihat Breaking News di bagian atas (horizontal scroll)
   - Scroll ke bawah untuk melihat Latest News
   - Scroll terus ke bawah, berita akan otomatis load (infinite scroll)
   - Swipe down untuk refresh

2. **Detail Screen**
   - Klik salah satu berita untuk melihat detail
   - Baca konten lengkap berita
   - Klik "Buka di Browser" untuk membaca artikel lengkap

## 🔧 Troubleshooting

### Problem: "401 Unauthorized"
**Solusi**: API Key salah atau belum diisi. Pastikan sudah mengisi API_KEY di Constants.kt

### Problem: "No Internet Connection"
**Solusi**: 
- Pastikan emulator/device terhubung internet
- Cek permission INTERNET di AndroidManifest.xml

### Problem: Gambar tidak muncul
**Solusi**: 
- Pastikan ada koneksi internet
- Beberapa URL gambar mungkin tidak valid dari API

### Problem: Gradle Sync Failed
**Solusi**:
- File → Invalidate Caches → Invalidate and Restart
- Pastikan internet stabil untuk download dependencies

## 📊 API Endpoints

### 1. Top Headlines (Breaking News)
```
GET https://newsapi.org/v2/top-headlines
Parameters:
- country: id (Indonesia)
- apiKey: YOUR_API_KEY
- page: 1
- pageSize: 10
```

### 2. Everything (Latest News)
```
GET https://newsapi.org/v2/everything
Parameters:
- q: Indonesia
- apiKey: YOUR_API_KEY
- page: 1
- pageSize: 20
- sortBy: publishedAt
```

## 🎨 UI/UX Features

- **Material Design 3** - Modern & clean interface
- **Responsive Layout** - Support untuk berbagai ukuran layar
- **Smooth Scrolling** - Infinite scroll yang smooth
- **Image Caching** - Glide untuk performa optimal
- **Pull to Refresh** - SwipeRefreshLayout
- **Loading Indicators** - Progress bar saat load data
- **Error Handling** - Toast message untuk error

## 📖 Penjelasan Komponen

### Network Layer
- **NewsInterface**: Mendefinisikan endpoint API
- **NewsService**: Setup Retrofit dengan OkHttp & logging

### Model Layer
- **Article**: Data class untuk artikel berita
- **Source**: Data class untuk sumber berita
- **NewsResponse**: Response wrapper dari API

### Repository Layer
- **NewsRepository**: Abstraksi layer untuk fetch data dari API

### ViewModel Layer
- **NewsViewModel**: Business logic, manage LiveData, pagination

### View Layer (UI)
- **MainActivity**: Tampilan utama dengan 2 RecyclerView
- **DetailActivity**: Detail artikel
- **HeadlineAdapter**: Adapter untuk headline horizontal
- **NewsAdapter**: Adapter untuk news list vertical

## 🔐 Permissions

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

## 📝 Notes untuk Presentasi (PPT)

### Slide 1: Overview
- Nama Aplikasi: NewsApp
- Tujuan: Aplikasi berita real-time dari NewsAPI
- Tech: Kotlin + MVVM + Retrofit

### Slide 2: Fitur Utama
- Breaking News (Horizontal)
- Latest News (Vertical + Infinite Scroll)
- Detail Berita
- Pull to Refresh

### Slide 3: Arsitektur
- MVVM Pattern
- Repository Pattern
- Clean Architecture
- Separation of Concerns

### Slide 4: Tech Stack
- Retrofit untuk API
- Glide untuk gambar
- Coroutines untuk async
- LiveData untuk reactive
- RecyclerView untuk list

### Slide 5: Demo
- Screenshot aplikasi
- Video demo (jika ada)

### Slide 6: Challenges & Solutions
- Infinite scroll implementation
- Image loading optimization
- API rate limiting handling

## 👨‍💻 Developer

Developed for **Rakamin Academy - Bank Mandiri Internship**

## 📄 License

This project is for educational purposes.

---

**Selamat Menggunakan NewsApp! 🚀**

Jika ada pertanyaan atau issue, jangan ragu untuk bertanya.
