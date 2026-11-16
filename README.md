# DreaMax

**Powered by Big Big Dreams**

Independent Android APK marketplace + personal game management system.

## Brand

- **Primary**: Red (`#E53935`)
- **Secondary**: White
- **Background**: Black (`#0A0A0A`)
- **Philosophy**: Modern, minimalist, professional, gaming-inspired

## Tech Stack

- Kotlin
- Jetpack Compose + Material 3
- Room
- Firebase Auth / Firestore / Storage (optional)
- Coil
- Navigation Compose

## Build without Android Studio

### Option A — GitHub Actions (recommended)

1. Push to `main` (or run the **Android CI** workflow manually).
2. Open the Actions tab → download the **dreamax-debug-apk** artifact.
3. Install the APK on your phone (enable install from unknown sources).

### Option B — Local JDK + Gradle

```bash
git clone https://github.com/Jeffery24344/DreaMax.git
cd DreaMax
gradle assembleDebug   # requires JDK 17 + Android SDK
```

See **RELEASE.md** for signed release builds and **FIREBASE_SETUP.md** for cloud features.

## Project structure

```
app/src/main/java/com/bigbigdreams/dreamax/
├── ui/          # Compose screens
├── data/        # Room, repositories, remote
├── domain/      # Models
├── download/    # APK downloader
├── install/     # PackageInstaller flow
├── auth/        # Firebase auth
└── util/        # APK metadata & icons
```

## Phases

| Phase | Status |
|-------|--------|
| 1 Foundation | Done |
| 2 UI | Done |
| 3 Game management | Done |
| 4 Store + downloads | Done |
| 5 Installation | Done |
| 6 Firebase cloud | Done (needs google-services.json) |
| 7 Finalization | Done |

## License / distribution

Only distribute APKs you are authorized to host. SHA-256 verifies integrity, not safety.
