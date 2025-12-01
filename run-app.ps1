# News App - Build and Install Script
Write-Host "========================================"
Write-Host "  NEWS APP - BUILD & INSTALL"
Write-Host "========================================"
Write-Host ""

Write-Host "[1/4] Stopping Gradle daemons..."
& .\gradlew.bat --stop
Start-Sleep -Seconds 2

Write-Host "[2/4] Killing Java processes..."
Get-Process -Name "java" -ErrorAction SilentlyContinue | Stop-Process -Force -ErrorAction SilentlyContinue
Start-Sleep -Seconds 2

Write-Host "[3/4] Cleaning build folder..."
if (Test-Path "app\build") {
    Remove-Item -Path "app\build" -Recurse -Force -ErrorAction SilentlyContinue
}

Write-Host "[4/4] Building APK..."
Write-Host ""
& .\gradlew.bat assembleDebug

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "BUILD SUCCESS!"
    Write-Host ""
    $apkPath = "app\build\outputs\apk\debug\app-debug.apk"
    Write-Host "APK Location: $apkPath"
    
    $adb = Get-Command adb -ErrorAction SilentlyContinue
    if ($adb) {
        Write-Host ""
        Write-Host "Installing to device..."
        & adb install -r $apkPath
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host ""
            Write-Host "APP INSTALLED SUCCESSFULLY!"
            Write-Host "Look for 'News App' in your device"
        }
    } else {
        Write-Host ""
        Write-Host "ADB not found. Install manually from:"
        Write-Host $apkPath
    }
} else {
    Write-Host ""
    Write-Host "BUILD FAILED!"
    Write-Host "Try closing VS Code and run: run-app.bat"
}

Write-Host ""
Read-Host "Press Enter to exit"
