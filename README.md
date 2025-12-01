# NewsApp - Modern Android News Application

A modern Android application for reading latest news from NewsAPI, featuring breaking news headlines and comprehensive news listing with infinite scrolling capabilities.

## Features

- Breaking News Headlines with horizontal scrolling
- Latest News list with infinite scroll pagination
- Detailed article view with browser integration
- Pull-to-refresh functionality
- Modern Material Design 3 UI
- Responsive layout for various screen sizes

## Architecture

This application implements MVVM (Model-View-ViewModel) architecture pattern with Repository Pattern for clean separation of concerns:

```
dev.rakamin.newsapp/
├── network/          # Retrofit & API Services
│   ├── NewsInterface.kt
│   └── NewsService.kt
├── model/            # Data Classes
│   ├── Article.kt
│   ├── Source.kt
│   └── NewsResponse.kt
├── repository/       # Data Repository
│   └── NewsRepository.kt
├── viewmodel/        # ViewModels
│   └── NewsViewModel.kt
├── ui/
│   ├── adapter/      # RecyclerView Adapters
│   │   ├── HeadlineAdapter.kt
│   │   └── NewsAdapter.kt
│   └── view/         # Activities
│       ├── MainActivity.kt
│       └── DetailActivity.kt
└── utils/            # Utilities
    └── Constants.kt
```

## Technology Stack

**Core:**
- Language: Kotlin
- Build Tool: Gradle 8.4
- Java Runtime: JDK 21 LTS
- Minimum SDK: API 24 (Android 7.0)
- Target SDK: API 33 (Android 13)

**Libraries:**
- Retrofit 2.9.0 - REST API client
- OkHttp 4.10.0 - HTTP client with logging interceptor
- Glide 4.14.2 - Image loading and caching
- Lifecycle Components - ViewModel and LiveData
- Kotlin Coroutines - Asynchronous programming
- Material Components - Modern UI components
- RecyclerView & CardView - List implementation

## Prerequisites

- Android Studio Electric Eel or newer
- JDK 21 or higher
- Android SDK with API Level 33
- NewsAPI key (free registration at https://newsapi.org/)

## Installation

### 1. Clone Repository

```bash
git clone https://github.com/Codift05/news-app-using-kotlin.git
cd news-app-using-kotlin
```

### 2. Configure API Key

Navigate to `app/src/main/java/dev/rakamin/newsapp/utils/Constants.kt` and replace the placeholder:

```kotlin
const val API_KEY = "your_api_key_here"
```

To obtain an API key:
1. Visit https://newsapi.org/
2. Click "Get API Key" and register
3. Verify your email
4. Copy the provided API key

### 3. Build Project

#### Using Android Studio:
1. Open project in Android Studio
2. Wait for Gradle sync to complete
3. Build > Make Project

#### Using Command Line:
```bash
./gradlew build
```

### 4. Run Application

#### On Emulator:
1. Open AVD Manager
2. Create or select an existing virtual device
3. Run the application (Shift+F10)

#### On Physical Device:
1. Enable Developer Options and USB Debugging
2. Connect device via USB
3. Select device and run

## Build APK

**Debug Build:**
```bash
./gradlew assembleDebug
```
Output: `app/build/outputs/apk/debug/app-debug.apk`

**Release Build:**
```bash
./gradlew assembleRelease
```
Output: `app/build/outputs/apk/release/app-release.apk`

## Usage

1. **Home Screen**
   - View breaking news in horizontal scroll section
   - Browse latest news in vertical list
   - Automatic pagination on scroll
   - Pull down to refresh content

2. **Detail View**
   - Tap any news item to view details
   - Read full article content
   - Open original article in browser

## API Endpoints

### Top Headlines
```
GET https://newsapi.org/v2/top-headlines
Parameters:
- country: id
- apiKey: YOUR_API_KEY
- page: integer
- pageSize: integer
```

### Everything
```
GET https://newsapi.org/v2/everything
Parameters:
- q: search query
- apiKey: YOUR_API_KEY
- page: integer
- pageSize: integer
- sortBy: publishedAt
```

## Project Structure

### Network Layer
- `NewsInterface`: API endpoint definitions
- `NewsService`: Retrofit configuration with OkHttp logging

### Data Layer
- `Article`: News article data model
- `Source`: News source data model
- `NewsResponse`: API response wrapper

### Repository Layer
- `NewsRepository`: Data access abstraction

### ViewModel Layer
- `NewsViewModel`: Business logic and state management

### View Layer
- `MainActivity`: Primary interface with dual RecyclerViews
- `DetailActivity`: Article detail display
- `HeadlineAdapter`: Horizontal news carousel adapter
- `NewsAdapter`: Vertical news list adapter

## Permissions

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

## Troubleshooting

### 401 Unauthorized Error
Verify API key is correctly configured in `Constants.kt`

### No Internet Connection
- Ensure device/emulator has internet access
- Verify INTERNET permission in AndroidManifest.xml

### Images Not Loading
- Check internet connectivity
- Some URLs from API may be invalid

### Gradle Sync Failed
- Invalidate caches: File > Invalidate Caches > Invalidate and Restart
- Ensure stable internet connection for dependency downloads

## Development

Built as part of Rakamin Academy - Bank Mandiri Internship Program

## License

This project is developed for educational purposes.

---

For questions or issues, please open an issue on GitHub.
