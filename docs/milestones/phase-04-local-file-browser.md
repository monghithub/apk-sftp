# Phase 4: Local File Browser

## 📌 Descripción General

Navegar archivos del dispositivo Android

### Problemas que soluciona
- Android tiene permisos de almacenamiento complejos
- Scoped Storage añade restricciones en Android 11+
- Usuario necesita interfaz intuitiva para seleccionar archivos

---

## 🎯 Objetivos de la Fase

Al completar Phase 4, tendrás:
✅ ['Create', 'LocalFileManager']
✅ ['Implement', 'scoped']
✅ ['Add', 'storage']
✅ ['Build', 'LocalFileViewModel']
... (11 tareas totales)

---

## 📋 Issues (11 tareas)

### Issue [4.1] - Create LocalFileManager for device file system access
**GitHub**: [#298](https://github.com/monghithub/apk-sftp/issues/298)

**¿Por qué se hace?** Abstracción de files

**¿Qué soluciona?** Manejar permisos internamente

---

### Issue [4.2] - Implement scoped storage support for Android 11+
**GitHub**: [#299](https://github.com/monghithub/apk-sftp/issues/299)

**¿Por qué se hace?** Cumplir requisitos

**¿Qué soluciona?** Funciona en dispositivos modernos

---

### Issue [4.3] - Add storage permission request and handling
**GitHub**: [#300](https://github.com/monghithub/apk-sftp/issues/300)

**¿Por qué se hace?** Pedir permisos

**¿Qué soluciona?** App legal y bien comportada

---

### Issue [4.4] - Build LocalFileViewModel for state management
**GitHub**: [#301](https://github.com/monghithub/apk-sftp/issues/301)

**¿Por qué se hace?** Estado de browsing

**¿Qué soluciona?** UI sincronizada

---

### Issue [4.5] - Create LocalFileBrowserScreen UI component
**GitHub**: [#302](https://github.com/monghithub/apk-sftp/issues/302)

**¿Por qué se hace?** Interfaz principal

**¿Qué soluciona?** Usuario ve archivos locales

---

### Issue [4.6] - Implement directory navigation
**GitHub**: [#303](https://github.com/monghithub/apk-sftp/issues/303)

**¿Por qué se hace?** Entrar/salir carpetas

**¿Qué soluciona?** Explorar sistema de archivos

---

### Issue [4.7] - Add file list display with icons and metadata
**GitHub**: [#304](https://github.com/monghithub/apk-sftp/issues/304)

**¿Por qué se hace?** Mostrar detalles

**¿Qué soluciona?** Usuario sabe tamaño y fecha

---

### Issue [4.8] - Implement file selection (single and multi-select)
**GitHub**: [#305](https://github.com/monghithub/apk-sftp/issues/305)

**¿Por qué se hace?** Seleccionar archivos

**¿Qué soluciona?** Necesario para uploads

---

### Issue [4.9] - Add file sorting (name, size, date)
**GitHub**: [#306](https://github.com/monghithub/apk-sftp/issues/306)

**¿Por qué se hace?** Organizar archivos

**¿Qué soluciona?** Encuentra fácilmente lo que busca

---

### Issue [4.10] - Implement file search/filtering
**GitHub**: [#307](https://github.com/monghithub/apk-sftp/issues/307)

**¿Por qué se hace?** Buscar archivos

**¿Qué soluciona?** Rápido para encontrar en carpetas grandes

---

### Issue [4.11] - Add upload button to initiate file transfer
**GitHub**: [#308](https://github.com/monghithub/apk-sftp/issues/308)

**¿Por qué se hace?** Iniciar upload

**¿Qué soluciona?** Conecta con Phase 6

---



---

## ✅ Criterios de Aceptación

La Phase 4 se considera **COMPLETA** cuando:

- [ ] Todos los issues están cerrados
- [ ] Todos los tests pasan
- [ ] Code review aprobado
- [ ] No hay regressions en fases previas
- [ ] Documentation actualizada

---

## 🔗 Dependencias

**Prerequisitos**: Phase 3: SSH Key Management

**Bloquea**: Phase 5: File Transfer Operations

---

## 📚 Referencias

- [Propuesta](../PROPOSAL.md)
- [Diseño Técnico](../DESIGN.md)
- [Especificaciones](../spec/)

---

**Fase**: 4 de 12
**Issues**: 11
**Priority**: 🟡 HIGH
