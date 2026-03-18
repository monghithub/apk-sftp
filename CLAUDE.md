# Compilación

Para compilar este proyecto usa la imagen Docker `android-builder` (titan no tiene Android SDK local):

```bash
docker run --rm \
  -v "$(pwd)":/project \
  -v /tmp/apk-output:/output \
  -v gradle-cache:/root/.gradle \
  android-builder [assembleDebug|assembleRelease]
```

Desplegar APK en apk-server:
```bash
cp /tmp/apk-output/*.apk /home/monghit/titan/apkServer/apks/<app-name>-<version>.apk
```

Si la imagen no existe: `cd /home/monghit/titan/android-builder && docker compose build`
