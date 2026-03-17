# Phase 1: Project Setup & Dependencies

## 📌 Descripción General

Esta es la fase **fundamental** donde se establece la estructura base del proyecto Android. Sin esta fase, el proyecto no puede compilar ni ejecutarse.

### ¿Por qué es importante?
- Define el entorno de desarrollo
- Configura todas las dependencias necesarias
- Establece la arquitectura del proyecto (MVVM)
- Prepara el terreno para todas las fases siguientes

### Problema que soluciona
Evita que cada desarrollador tenga que configurar manualmente:
- Versiones correctas de librerías
- Estructura de carpetas
- Configuración de build
- Herramientas de proguard/R8

---

## 🎯 Objetivos de la Fase

Al completar Phase 1, tendrás:
✅ Proyecto Android compilable
✅ Todas las dependencias instaladas
✅ Arquitectura MVVM configurada
✅ Android Keystore listo
✅ Build variants configurados

---

## 📋 Issues (8 tareas)

### Issue [1.1] - Create Android Gradle project with MVVM architecture
**GitHub**: [#279](https://github.com/monghithub/apk-sftp/issues/279)

**¿Por qué se hace?**
- Establece la estructura base del proyecto
- Define carpetas y módulos necesarios
- Configura la arquitectura MVVM para toda la app

**¿Qué soluciona?**
- Sin esto, no tenemos base donde trabajar
- Todos los desarrolladores trabajan con la misma estructura
- Facilita la colaboración en el equipo

**Tareas específicas**:
- [ ] Crear nuevo proyecto en Android Studio
- [ ] Organizar estructura: `app/src/main/java/com/monghithub/apk_sftp`
- [ ] Crear capas: presentation, domain, data
- [ ] Configurar gradle build

---

### Issue [1.2] - Add JSch SSH/SFTP library dependency
**GitHub**: [#280](https://github.com/monghithub/apk-sftp/issues/280)

**¿Por qué se hace?**
- JSch es la librería de facto para SSH/SFTP en Java
- Sin esto, tendríamos que implementar SSH desde cero
- Es la base para todas las fases de SSH

**¿Qué soluciona?**
- Proporciona API para conexiones SSH
- Implementa protocolo SFTP
- Manejo de claves SSH

**Tareas específicas**:
- [ ] Agregar `com.jcraft:jsch:0.1.55` a build.gradle
- [ ] Validar que compila sin errores
- [ ] Crear test básico de importación

---

### Issue [1.3] - Add AndroidX Lifecycle and ViewModel dependencies
**GitHub**: [#281](https://github.com/monghithub/apk-sftp/issues/281)

**¿Por qué se hace?**
- AndroidX Lifecycle es estándar en desarrollo Android moderno
- ViewModels manejan el estado de forma lifecycle-aware
- Essencial para la arquitectura MVVM

**¿Qué soluciona?**
- Manejo correcto del ciclo de vida de la aplicación
- Prevención de memory leaks
- Estado persistente durante configuración changes

**Tareas específicas**:
- [ ] Agregar `androidx.lifecycle:lifecycle-viewmodel-ktx`
- [ ] Agregar `androidx.lifecycle:lifecycle-runtime-ktx`
- [ ] Crear ViewModel base para el proyecto

---

### Issue [1.4] - Add Jetpack Compose UI framework
**GitHub**: [#282](https://github.com/monghithub/apk-sftp/issues/282)

**¿Por qué se hace?**
- Compose es el futuro de UI en Android (Google lo recomienda)
- Código declarativo más limpio que XML
- Mejor integración con Kotlin

**¿Qué soluciona?**
- Desarrollo más rápido de UIs
- Menos boilerplate que XML tradicional
- Mejor reactividaddesde el estado

**Tareas específicas**:
- [ ] Agregar dependencias de Compose
- [ ] Configurar compileSdkVersion y targetSdkVersion
- [ ] Crear Activity base con Compose
- [ ] Validar que compila

---

### Issue [1.5] - Configure Android Keystore for secure key storage
**GitHub**: [#283](https://github.com/monghithub/apk-sftp/issues/283)

**¿Por qué se hace?**
- Las claves SSH no deben guardarse en texto plano
- Android Keystore es el almacenamiento seguro estándar
- Requerimiento de seguridad crítico

**¿Qué soluciona?**
- Claves SSH encriptadas en reposo
- Acceso solo por la aplicación
- Cumple estándares de seguridad

**Tareas específicas**:
- [ ] Configurar keyStoreSpec en build.gradle
- [ ] Crear utilidades para Keystore
- [ ] Implementar encriptación/desencriptación
- [ ] Escribir tests de seguridad

---

### Issue [1.6] - Add EncryptedSharedPreferences for storing metadata
**GitHub**: [#284](https://github.com/monghithub/apk-sftp/issues/284)

**¿Por qué se hace?**
- SharedPreferences regular es inseguro
- EncryptedSharedPreferences encripta automáticamente
- Necesario para guardar datos sensibles como hostnames

**¿Qué soluciona?**
- Protección de datos de configuración
- Fácil acceso a preferencias cifradas
- API compatible con SharedPreferences

**Tareas específicas**:
- [ ] Agregar `androidx.security:security-crypto`
- [ ] Crear manager para EncryptedSharedPreferences
- [ ] Implementar métodos get/set seguros

---

### Issue [1.7] - Add WorkManager dependency for background tasks
**GitHub**: [#285](https://github.com/monghithub/apk-sftp/issues/285)

**¿Por qué se hace?**
- Transferencias pueden interrumpirse
- WorkManager mantiene tareas entre sesiones
- Requerido para futuras características de background

**¿Qué soluciona?**
- Permite reanudar descargas interrumpidas
- Maneja transferencias en background
- Compatible con Doze mode del sistema

**Tareas específicas**:
- [ ] Agregar `androidx.work:work-runtime-ktx`
- [ ] Crear Worker base para la app
- [ ] Configurar PeriodicWorkRequest de prueba

---

### Issue [1.8] - Configure build variants and proguard rules
**GitHub**: [#286](https://github.com/monghithub/apk-sftp/issues/286)

**¿Por qué se hace?**
- Build variants separan debug y release
- ProGuard ofusca código y reduce tamaño
- Necesario para lanzamiento profesional

**¿Qué soluciona?**
- Compilaciones separadas para dev y producción
- Protección del código intelectual
- Reducción del tamaño del APK

**Tareas específicas**:
- [ ] Configurar debuggable y release buildTypes
- [ ] Crear proguard-rules.pro
- [ ] Agregar reglas para JSch y Compose
- [ ] Validar que ProGuard no rompe la app

---

## ✅ Criterios de Aceptación

La Phase 1 se considera **COMPLETA** cuando:

- [ ] El proyecto compila sin errores
- [ ] `./gradlew build` ejecuta exitosamente
- [ ] Android Keystore está funcionando
- [ ] EncryptedSharedPreferences está disponible
- [ ] ProGuard está configurado correctamente
- [ ] No hay advertencias en build.gradle
- [ ] La estructura de carpetas sigue MVVM
- [ ] Todos los tests pasan

---

## 🔗 Dependencias

**Prerequisitos**: Ninguno (es la fase inicial)

**Bloquea**: Todas las fases siguientes dependen de esta

---

## 📚 Referencias

- [Propuesta](../PROPOSAL.md)
- [Diseño Técnico](../DESIGN.md)
- [Android Keystore Docs](https://developer.android.com/training/articles/keystore)
- [Jetpack Compose](https://developer.android.com/compose)
- [ProGuard Manual](https://www.guardsquare.com/proguard/manual)

---

## 🔧 Comandos Útiles

```bash
# Compilar proyecto
./gradlew build

# Ejecutar tests
./gradlew test

# Crear APK release
./gradlew assembleRelease

# Limpiar build
./gradlew clean
```

---

**Fase**: 1 de 12
**Issues**: 8
**Estimated Duration**: 2-3 días
**Priority**: 🔴 CRITICAL
