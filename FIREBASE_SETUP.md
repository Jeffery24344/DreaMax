# Firebase setup for DreaMax

1. Create a Firebase project; add Android app `com.bigbigdreams.dreamax`.
2. Download `google-services.json` into `app/`.
3. In `app/build.gradle.kts` plugins, enable `id("com.google.gms.google-services")`.
4. Enable Email/Password Auth and Firestore.
5. Collection `store_games` for catalogue; `users/{uid}/library` for synced metadata.

Without Firebase the app still works offline (import, install, sample store).
