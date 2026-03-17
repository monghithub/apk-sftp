# Phase 3: SSH Key Management

## 📌 Descripción General

Importar, almacenar y gestionar claves SSH de forma segura

### Problemas que soluciona
- Claves SSH requieren seguridad extrema
- Usuario necesita forma fácil de importar claves
- Acceso rápido sin exponer la clave

---

## 🎯 Objetivos de la Fase

Al completar Phase 3, tendrás:
✅ ['Create', 'KeyManager']
✅ ['Implement', 'secure']
✅ ['Add', 'passphrase-protected']
✅ ['Implement', 'key']
... (11 tareas totales)

---

## 📋 Issues (11 tareas)

### Issue [3.1] - Create KeyManager class for Android Keystore operations
**GitHub**: [#287](https://github.com/monghithub/apk-sftp/issues/287)

**¿Por qué se hace?** Encapsular Keystore

**¿Qué soluciona?** Código limpio y reutilizable

---

### Issue [3.2] - Implement secure key import from device files
**GitHub**: [#288](https://github.com/monghithub/apk-sftp/issues/288)

**¿Por qué se hace?** Importar claves PEM

**¿Qué soluciona?** Usuario puede cargar sus claves existentes

---

### Issue [3.3] - Add passphrase-protected key decryption support
**GitHub**: [#289](https://github.com/monghithub/apk-sftp/issues/289)

**¿Por qué se hace?** Claves con contraseña

**¿Qué soluciona?** Soporte para claves encriptadas

---

### Issue [3.4] - Implement key validation and format checking
**GitHub**: [#290](https://github.com/monghithub/apk-sftp/issues/290)

**¿Por qué se hace?** Validar antes de guardar

**¿Qué soluciona?** Prevenir errores después de importar

---

### Issue [3.5] - Create KeyViewModel for managing key operations
**GitHub**: [#291](https://github.com/monghithub/apk-sftp/issues/291)

**¿Por qué se hace?** Estado de claves

**¿Qué soluciona?** UI refleja cambios de claves

---

### Issue [3.6] - Build key import UI screen
**GitHub**: [#292](https://github.com/monghithub/apk-sftp/issues/292)

**¿Por qué se hace?** Interfaz intuitiva

**¿Qué soluciona?** Usuario puede importar sin terminal

---

### Issue [3.7] - Build key list screen showing imported keys
**GitHub**: [#293](https://github.com/monghithub/apk-sftp/issues/293)

**¿Por qué se hace?** Ver claves disponibles

**¿Qué soluciona?** Usuario ve qué claves tiene importadas

---

### Issue [3.8] - Implement key deletion with confirmation
**GitHub**: [#294](https://github.com/monghithub/apk-sftp/issues/294)

**¿Por qué se hace?** Borrar claves

**¿Qué soluciona?** Prevenir eliminación accidental

---

### Issue [3.9] - Add key rename functionality
**GitHub**: [#295](https://github.com/monghithub/apk-sftp/issues/295)

**¿Por qué se hace?** Renombrar claves

**¿Qué soluciona?** Mejor organización

---

### Issue [3.10] - Implement key detail view
**GitHub**: [#296](https://github.com/monghithub/apk-sftp/issues/296)

**¿Por qué se hace?** Ver propiedades de clave

**¿Qué soluciona?** Usuario sabe tamaño, tipo, fecha de importación

---

### Issue [3.11] - Add streaming key access
**GitHub**: [#297](https://github.com/monghithub/apk-sftp/issues/297)

**¿Por qué se hace?** Nunca cargar clave completa en memoria

**¿Qué soluciona?** Seguridad: clave no está vulnerable en RAM

---



---

## ✅ Criterios de Aceptación

La Phase 3 se considera **COMPLETA** cuando:

- [ ] Todos los issues están cerrados
- [ ] Todos los tests pasan
- [ ] Code review aprobado
- [ ] No hay regressions en fases previas
- [ ] Documentation actualizada

---

## 🔗 Dependencias

**Prerequisitos**: Phase 2: SSH Connection Infrastructure

**Bloquea**: Phase 4: Remote File Browser

---

## 📚 Referencias

- [Propuesta](../PROPOSAL.md)
- [Diseño Técnico](../DESIGN.md)
- [Especificaciones](../spec/)

---

**Fase**: 3 de 12
**Issues**: 11
**Priority**: 🟡 HIGH
