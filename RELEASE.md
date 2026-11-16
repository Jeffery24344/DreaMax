# DreaMax release guide

## Build debug APK via GitHub Actions

1. Go to the repository **Actions** tab.
2. Select **Android CI** → **Run workflow**.
3. Download **dreamax-debug-apk** from the finished run.

## Signed release (local or CI secrets)

See keystore creation and `signingConfigs` in the full RELEASE notes in-repo after clone.

Bump `versionCode` for every Play upload.
