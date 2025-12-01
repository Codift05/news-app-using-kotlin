# Run News App with Android Emulator
Write-Host "========================================"
Write-Host "  NEWS APP - RUN WITH EMULATOR"
Write-Host "========================================"
Write-Host ""

# Set Android SDK paths
$androidSdk = "D:\Android\Sdk"
$emulatorPath = "$androidSdk\emulator\emulator.exe"
$adbPath = "$androidSdk\platform-tools\adb.exe"
$avdManagerPath = "$androidSdk\cmdline-tools\latest\bin\avdmanager.bat"

# Check if SDK exists
if (-not (Test-Path $androidSdk)) {
    Write-Host "ERROR: Android SDK not found at $androidSdk"
    Write-Host "Please update the path in this script"
    Read-Host "Press Enter to exit"
    exit
}

# Add to PATH temporarily
$env:ANDROID_HOME = $androidSdk
$env:Path = "$androidSdk\platform-tools;$androidSdk\emulator;$androidSdk\cmdline-tools\latest\bin;$env:Path"

Write-Host "[1/5] Checking available emulators..."
if (Test-Path $emulatorPath) {
    $avdList = & $emulatorPath -list-avds
    
    if ($avdList) {
        Write-Host "Found emulators:"
        $avdList | ForEach-Object { Write-Host "  - $_" }
        Write-Host ""
        
        # Use first available emulator
        $selectedAvd = $avdList[0]
        Write-Host "Using emulator: $selectedAvd"
        Write-Host ""
        
        Write-Host "[2/5] Starting emulator..."
        Write-Host "Please wait, this may take 1-2 minutes..."
        Write-Host ""
        
        # Start emulator in background
        Start-Process -FilePath $emulatorPath -ArgumentList "-avd", $selectedAvd, "-no-snapshot-load" -WindowStyle Normal
        
        Write-Host "[3/5] Waiting for emulator to boot..."
        $maxWait = 120
        $waited = 0
        
        while ($waited -lt $maxWait) {
            Start-Sleep -Seconds 5
            $waited += 5
            
            $bootComplete = & $adbPath shell getprop sys.boot_completed 2>$null
            if ($bootComplete -eq "1") {
                Write-Host "Emulator is ready!"
                break
            }
            
            Write-Host "  Waiting... ($waited seconds)"
        }
        
        if ($waited -ge $maxWait) {
            Write-Host "Emulator is taking too long. Continuing anyway..."
        }
        
        Write-Host ""
        Write-Host "[4/5] Building APK..."
        & .\gradlew.bat assembleDebug
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host ""
            Write-Host "[5/5] Installing APK to emulator..."
            $apkPath = "app\build\outputs\apk\debug\app-debug.apk"
            & $adbPath install -r $apkPath
            
            if ($LASTEXITCODE -eq 0) {
                Write-Host ""
                Write-Host "========================================"
                Write-Host "  SUCCESS!"
                Write-Host "========================================"
                Write-Host ""
                Write-Host "App installed! Starting app..."
                
                # Launch the app
                & $adbPath shell am start -n dev.rakamin.newsapp/.ui.view.MainActivity
                
                Write-Host ""
                Write-Host "The app should now be running on the emulator"
            } else {
                Write-Host ""
                Write-Host "Installation failed. Check the error above."
            }
        }
        
    } else {
        Write-Host "No emulators found!"
        Write-Host ""
        Write-Host "Please create an emulator first:"
        Write-Host "1. Open Android Studio"
        Write-Host "2. Go to Tools > Device Manager"
        Write-Host "3. Create a new Virtual Device"
        Write-Host "4. Run this script again"
    }
} else {
    Write-Host "Emulator not found at: $emulatorPath"
    Write-Host ""
    Write-Host "Alternative: Install using adb directly"
    Write-Host "1. Start your emulator manually from Android Studio"
    Write-Host "2. Run: adb install -r app\build\outputs\apk\debug\app-debug.apk"
}

Write-Host ""
Read-Host "Press Enter to exit"
