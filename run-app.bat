@echo off
REM Simple build script for News App
REM Run this if the PowerShell script has issues

echo ========================================
echo   NEWS APP - SIMPLE BUILD SCRIPT
echo ========================================
echo.

echo [1/4] Stopping Gradle daemons...
call gradlew.bat --stop
timeout /t 3 /nobreak >nul

echo [2/4] Cleaning project...
call gradlew.bat clean
echo.

echo [3/4] Building APK...
call gradlew.bat assembleDebug
echo.

if %ERRORLEVEL% EQU 0 (
    echo ========================================
    echo   BUILD SUCCESSFUL!
    echo ========================================
    echo.
    echo APK Location: app\build\outputs\apk\debug\app-debug.apk
    echo.
    
    echo [4/4] Checking for connected devices...
    adb devices
    echo.
    
    echo To install, run:
    echo   adb install -r app\build\outputs\apk\debug\app-debug.apk
    echo.
) else (
    echo ========================================
    echo   BUILD FAILED!
    echo ========================================
    echo.
    echo Try closing all Java/Gradle processes and run again.
    echo.
)

pause
