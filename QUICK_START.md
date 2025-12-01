# ⚡ QUICK START GUIDE - NEWSAPP

## 🚀 Get Started in 5 Minutes!

### Step 1: Get API Key (2 minutes)
1. Go to: https://newsapi.org
2. Click "Get API Key"
3. Sign up (FREE)
4. Copy your API Key

### Step 2: Setup Project (1 minute)
1. Open project in Android Studio
2. Navigate to: `app/src/main/java/dev/rakamin/newsapp/utils/Constants.kt`
3. Replace:
   ```kotlin
   const val API_KEY = "YOUR_API_KEY_HERE"
   ```
   with:
   ```kotlin
   const val API_KEY = "paste_your_api_key_here"
   ```
4. Save file (Ctrl+S)

### Step 3: Sync Gradle (1 minute)
- Wait for automatic Gradle sync
- If fails, click "Sync Project with Gradle Files" button

### Step 4: Run App (1 minute)
1. Click Run ▶️ button (or Shift+F10)
2. Select device (emulator or phone)
3. Wait for build & install
4. App opens automatically!

---

## 🎯 What You'll See

### Home Screen:
✅ Breaking News (horizontal scroll)
✅ Latest News (vertical list)
✅ Pull down to refresh

### Detail Screen:
✅ Full article view
✅ "Open in Browser" button
✅ Back to home

### Infinite Scroll:
✅ Scroll down → auto loads more news!

---

## 🔧 Troubleshooting

### Problem: "401 Unauthorized"
**Fix:** Check API Key in Constants.kt

### Problem: No Internet
**Fix:** Enable WiFi/Data on device

### Problem: Gradle Sync Failed
**Fix:** File → Invalidate Caches → Restart

---

## 📦 Build APK

### Debug APK:
```bash
./gradlew assembleDebug
```
Output: `app/build/outputs/apk/debug/app-debug.apk`

### Via Android Studio:
Build → Build Bundle(s) / APK(s) → Build APK(s)

---

## ✅ Feature Checklist

After running, test these features:
- [ ] Breaking News loads
- [ ] Latest News loads
- [ ] Horizontal scroll works
- [ ] Infinite scroll works
- [ ] Click news → shows detail
- [ ] "Open in Browser" works
- [ ] Pull to refresh works

---

## 📱 System Requirements

- Android Studio Dolphin 2021.3.1+
- JDK 8+
- Android SDK API 33
- Internet connection
- Device with Android 7.0+ (API 24+)

---

## 🎉 Success!

If you see news loading, congratulations! 
Your NewsApp is working perfectly! 🚀

---

## 📚 Need More Help?

- **Full Guide:** See `PANDUAN_RUNNING.md`
- **PPT Guide:** See `PANDUAN_PPT.md`
- **Documentation:** See `README.md`
- **Details:** See `DOKUMENTASI_LENGKAP.md`

---

**Happy Coding! 💻**
