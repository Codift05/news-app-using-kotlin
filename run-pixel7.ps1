# Run News App on Pixel 7 Emulator
Write-Host "========================================"
Write-Host "  Starting Pixel 7 Emulator"
Write-Host "========================================"
Write-Host ""

$androidSdk = "D:\Android\Sdk"
$emulatorPath = "$androidSdk\emulator\emulator.exe"
$adbPath = "$androidSdk\platform-tools\adb.exe"

$env:ANDROID_HOME = $androidSdk
$env:Path = "$androidSdk\platform-tools;$androidSdk\emulator;$env:Path"

Write-Host "Starting Pixel7_WithFrame emulator..."
Start-Process -FilePath $emulatorPath -ArgumentList "-avd", "Pixel7_WithFrame" -WindowStyle Normal

Write-Host ""
Write-Host "Waiting for emulator to boot (this may take 2-3 minutes)..."
Write-Host "You'll see the emulator window open..."
Write-Host ""

$maxWait = 180
$waited = 0

while ($waited -lt $maxWait) {
    Start-Sleep -Seconds 5
    $waited += 5
    
    $devices = & $adbPath devices 2>$null
    $bootComplete = & $adbPath shell getprop sys.boot_completed 2>$null
    
    if ($devices -match "emulator.*device" -and $bootComplete -eq "1") {
        Write-Host "Emulator is ready!"
        break
    }
    
    $dots = "." * ($waited / 5 % 4)
    Write-Host "`rBooting$dots    " -NoNewline
}

if ($waited -ge $maxWait) {
    Write-Host "`nEmulator timeout. Please wait for it to fully boot, then run:"
    Write-Host "  adb install -r app\build\outputs\apk\debug\app-debug.apk"
    Read-Host "`nPress Enter to exit"
    exit
}

Write-Host "`n"
Write-Host "Installing News App..."
$apkPath = "app\build\outputs\apk\debug\app-debug.apk"

if (-not (Test-Path $apkPath)) {
    Write-Host "APK not found. Building..."
    & .\gradlew.bat assembleDebug
}

& $adbPath install -r $apkPath

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "========================================"
    Write-Host "  APP INSTALLED!"
    Write-Host "========================================"
    Write-Host ""
    Write-Host "Launching News App..."
    & $adbPath shell am start -n dev.rakamin.newsapp/.ui.view.MainActivity
    Write-Host ""
    Write-Host "Check your emulator - the app should be running!"
} else {
    Write-Host ""
    Write-Host "Installation failed. Make sure emulator is fully booted."
}

Write-Host ""
Read-Host "Press Enter to exit"
