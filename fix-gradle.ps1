# PowerShell Script untuk Fix Gradle Sync Issues
# Jalankan script ini di PowerShell dengan: .\fix-gradle.ps1

Write-Host "================================================" -ForegroundColor Cyan
Write-Host "  FIXING ANDROID STUDIO GRADLE SYNC ISSUES" -ForegroundColor Cyan
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""

# Set location to project directory
$projectPath = "d:\Intership Bank Mandiri\final_project"
Set-Location $projectPath

Write-Host "[1/5] Checking project structure..." -ForegroundColor Yellow

if (Test-Path ".\app\build.gradle") {
    Write-Host "  ✓ Project structure OK" -ForegroundColor Green
} else {
    Write-Host "  ✗ build.gradle not found!" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "[2/5] Cleaning build cache..." -ForegroundColor Yellow

# Clean Gradle cache
if (Test-Path ".\.gradle") {
    Remove-Item -Path ".\.gradle" -Recurse -Force
    Write-Host "  ✓ .gradle folder cleaned" -ForegroundColor Green
}

if (Test-Path ".\build") {
    Remove-Item -Path ".\build" -Recurse -Force
    Write-Host "  ✓ build folder cleaned" -ForegroundColor Green
}

if (Test-Path ".\app\build") {
    Remove-Item -Path ".\app\build" -Recurse -Force
    Write-Host "  ✓ app/build folder cleaned" -ForegroundColor Green
}

Write-Host ""
Write-Host "[3/5] Checking Gradle Wrapper..." -ForegroundColor Yellow

if (Test-Path ".\gradlew.bat") {
    Write-Host "  ✓ Gradle wrapper found" -ForegroundColor Green
} else {
    Write-Host "  ⚠ Gradle wrapper not found, will be created on sync" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "[4/5] Creating local.properties..." -ForegroundColor Yellow

# Auto-detect Android SDK location
$possibleSdkPaths = @(
    "$env:LOCALAPPDATA\Android\Sdk",
    "$env:USERPROFILE\AppData\Local\Android\Sdk",
    "C:\Android\Sdk"
)

$sdkPath = $null
foreach ($path in $possibleSdkPaths) {
    if (Test-Path $path) {
        $sdkPath = $path
        break
    }
}

if ($sdkPath) {
    $sdkPathEscaped = $sdkPath -replace '\\', '\\'
    $localPropsContent = "sdk.dir=$sdkPathEscaped"
    Set-Content -Path ".\local.properties" -Value $localPropsContent
    Write-Host "  ✓ local.properties created" -ForegroundColor Green
    Write-Host "  SDK Path: $sdkPath" -ForegroundColor Gray
} else {
    Write-Host "  ⚠ Android SDK not found automatically" -ForegroundColor Yellow
    Write-Host "  Please set SDK location manually in Android Studio" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "[5/5] Instructions for Android Studio..." -ForegroundColor Yellow
Write-Host ""
Write-Host "================================================" -ForegroundColor Cyan
Write-Host "  NEXT STEPS - DO THIS IN ANDROID STUDIO:" -ForegroundColor Cyan
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "1. Open Android Studio" -ForegroundColor White
Write-Host "2. Open project: $projectPath" -ForegroundColor White
Write-Host ""
Write-Host "3. Wait for notification bar at top:" -ForegroundColor White
Write-Host "   'Gradle files have changed...'" -ForegroundColor Gray
Write-Host ""
Write-Host "4. Click 'Sync Now' button (blue button)" -ForegroundColor White
Write-Host "   OR" -ForegroundColor Gray
Write-Host "   Click elephant icon in toolbar" -ForegroundColor Gray
Write-Host "   OR" -ForegroundColor Gray
Write-Host "   File > Sync Project with Gradle Files" -ForegroundColor Gray
Write-Host ""
Write-Host "5. Wait for Gradle sync (3-10 minutes first time)" -ForegroundColor White
Write-Host "   - You'll see progress bar at bottom" -ForegroundColor Gray
Write-Host "   - Don't close Android Studio during sync" -ForegroundColor Gray
Write-Host ""
Write-Host "6. After sync completes:" -ForegroundColor White
Write-Host "   ✓ Red errors will disappear" -ForegroundColor Green
Write-Host "   ✓ Kotlin files will be normal" -ForegroundColor Green
Write-Host "   ✓ You can build and run" -ForegroundColor Green
Write-Host ""
Write-Host "================================================" -ForegroundColor Cyan
Write-Host "  IF STILL ERRORS AFTER SYNC:" -ForegroundColor Cyan
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "1. File > Invalidate Caches..." -ForegroundColor White
Write-Host "2. Check all options and click 'Invalidate and Restart'" -ForegroundColor White
Write-Host "3. Wait for Android Studio to restart and re-index" -ForegroundColor White
Write-Host ""
Write-Host "================================================" -ForegroundColor Cyan
Write-Host "  TROUBLESHOOTING" -ForegroundColor Cyan
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Problem: Gradle sync fails" -ForegroundColor Yellow
Write-Host "Solution:" -ForegroundColor White
Write-Host "  - Check internet connection (need to download ~500MB)" -ForegroundColor Gray
Write-Host "  - Disable VPN if active" -ForegroundColor Gray
Write-Host "  - Try sync again" -ForegroundColor Gray
Write-Host ""
Write-Host "Problem: 'SDK location not found'" -ForegroundColor Yellow
Write-Host "Solution:" -ForegroundColor White
Write-Host "  - File > Project Structure > SDK Location" -ForegroundColor Gray
Write-Host "  - Set Android SDK path manually" -ForegroundColor Gray
Write-Host ""
Write-Host "Problem: Kotlin plugin not found" -ForegroundColor Yellow
Write-Host "Solution:" -ForegroundColor White
Write-Host "  - File > Settings > Plugins" -ForegroundColor Gray
Write-Host "  - Check 'Kotlin' is installed and enabled" -ForegroundColor Gray
Write-Host "  - If not, install from Marketplace" -ForegroundColor Gray
Write-Host "  - Restart Android Studio" -ForegroundColor Gray
Write-Host ""
Write-Host "================================================" -ForegroundColor Cyan
Write-Host "Script completed!" -ForegroundColor Green
Write-Host "Now open Android Studio and follow the steps above." -ForegroundColor Green
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""

# Pause so user can read
Write-Host "Press any key to exit..." -ForegroundColor Gray
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
