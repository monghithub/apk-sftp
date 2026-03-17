# Phase 11: Security & Optimization

## 📌 Descripción General

Hardening de seguridad y optimización de performance para producción.

### Problemas que soluciona
- App puede tener vulnerabilidades de seguridad
- Puede ser lenta o consumir mucha batería
- Tamaño del APK puede ser demasiado grande
- Código sin ofuscar es vulnerable a ingeniería inversa

---

## 📋 Issues (7 tareas)

### Issue [11.1] - Add ProGuard/R8 rules for code obfuscation
**GitHub**: [#196](https://github.com/monghithub/apk-sftp/issues/196)

**¿Por qué se hace?** Proteger código intelectual y reducir tamaño
**¿Qué soluciona?** Evita ingeniería inversa, reduce APK ~30%

---

### Issue [11.2] - Implement certificate pinning for known servers
**GitHub**: [#197](https://github.com/monghithub/apk-sftp/issues/197)

**¿Por qué se hace?** Proteger contra ataques MITM
**¿Qué soluciona?** Si certificado SSH es comprometido, seguimos seguros

---

### Issue [11.3] - Add session timeout functionality
**GitHub**: [#198](https://github.com/monghithub/apk-sftp/issues/198)

**¿Por qué se hace?** Sesiones largas sin actividad son riesgo de seguridad
**¿Qué soluciona?** Auto-cierra sesión después de 15 minutos inactividad

---

### Issue [11.4] - Implement automatic logout on app background
**GitHub**: [#199](https://github.com/monghithub/apk-sftp/issues/199)

**¿Por qué se hace?** App minimizada o pantalla bloqueada = riesgo
**¿Qué soluciona?** Cierra sesión cuando app va a background

---

### Issue [11.5] - Add app signing configuration for release builds
**GitHub**: [#200](https://github.com/monghithub/apk-sftp/issues/200)

**¿Por qué se hace?** APK debe estar firmado digitalmente para Play Store
**¿Qué soluciona?** Autentica que eres el desarrollador legítimo

---

### Issue [11.6] - Profile memory usage during large file transfers
**GitHub**: [#201](https://github.com/monghithub/apk-sftp/issues/201)

**¿Por qué se hace?** Prevenir memory leaks en transferencias
**¿Qué soluciona?** App no consume memoria innecesariamente

---

### Issue [11.7] - Optimize transfer buffer sizes for device capabilities
**GitHub**: [#202](https://github.com/monghithub/apk-sftp/issues/202)

**¿Por qué se hace?** Buffers pequeños = lento, grandes = crash en memoria baja
**¿Qué soluciona?** Balance entre velocidad y disponibilidad de memoria

---

## ✅ Criterios de Aceptación

- [ ] Todos los issues cerrados
- [ ] APK signado está disponible
- [ ] ProGuard/R8 está funcionando
- [ ] No hay memory leaks en stress test
- [ ] Session timeout funciona

---

**Fase**: 11 de 12
**Issues**: 7
**Priority**: 🟡 HIGH
