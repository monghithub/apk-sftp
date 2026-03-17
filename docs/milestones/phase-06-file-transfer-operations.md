# Phase 6: File Transfer Operations

## 📌 Descripción General

Upload, download, delete, rename files

### Problemas que soluciona
- Transferencias de archivos grandes pueden fallar
- Necesario mostrar progreso
- Manejar interrupciones y cancelaciones

---

## 🎯 Objetivos de la Fase

Al completar Phase 6, tendrás:
✅ ['Implement', 'SFTP']
✅ ['Implement', 'SFTP']
✅ ['Add', 'chunked/streaming']
✅ ['Create', 'progress']
... (12 tareas totales)

---

## 📋 Issues (12 tareas)

### Issue [6.1] - Implement SFTP upload using JSch
**GitHub**: [#321](https://github.com/monghithub/apk-sftp/issues/321)

**¿Por qué se hace?** Subir archivos

**¿Qué soluciona?** Del dispositivo al servidor

---

### Issue [6.2] - Implement SFTP download using JSch
**GitHub**: [#322](https://github.com/monghithub/apk-sftp/issues/322)

**¿Por qué se hace?** Descargar archivos

**¿Qué soluciona?** Del servidor al dispositivo

---

### Issue [6.3] - Add chunked/streaming transfer for large files
**GitHub**: [#323](https://github.com/monghithub/apk-sftp/issues/323)

**¿Por qué se hace?** Archivos grandes

**¿Qué soluciona?** No cargar todo en memoria

---

### Issue [6.4] - Create progress tracking
**GitHub**: [#324](https://github.com/monghithub/apk-sftp/issues/324)

**¿Por qué se hace?** Ver progreso

**¿Qué soluciona?** Bytes, %, tiempo restante

---

### Issue [6.5] - Build file transfer progress UI
**GitHub**: [#325](https://github.com/monghithub/apk-sftp/issues/325)

**¿Por qué se hace?** Interfaz de progreso

**¿Qué soluciona?** Barra visual para usuario

---

### Issue [6.6] - Implement transfer cancellation
**GitHub**: [#326](https://github.com/monghithub/apk-sftp/issues/326)

**¿Por qué se hace?** Cancelar

**¿Qué soluciona?** Usuario puede detener transferencia

---

### Issue [6.7] - Add error handling for transfer failures
**GitHub**: [#327](https://github.com/monghithub/apk-sftp/issues/327)

**¿Por qué se hace?** Errores

**¿Qué soluciona?** Reintentar o error claro

---

### Issue [6.8] - Implement partial file cleanup
**GitHub**: [#328](https://github.com/monghithub/apk-sftp/issues/328)

**¿Por qué se hace?** Limpiar

**¿Qué soluciona?** No dejar archivos incompletos

---

### Issue [6.9] - Add overwrite/rename/skip dialog
**GitHub**: [#329](https://github.com/monghithub/apk-sftp/issues/329)

**¿Por qué se hace?** Conflictos

**¿Qué soluciona?** ¿Qué hacer si archivo existe?

---

### Issue [6.10] - Implement remote file deletion
**GitHub**: [#330](https://github.com/monghithub/apk-sftp/issues/330)

**¿Por qué se hace?** Borrar remoto

**¿Qué soluciona?** Eliminar archivo en servidor

---

### Issue [6.11] - Implement remote file rename
**GitHub**: [#331](https://github.com/monghithub/apk-sftp/issues/331)

**¿Por qué se hace?** Renombrar remoto

**¿Qué soluciona?** Cambiar nombre en servidor

---

### Issue [6.12] - Add auto-refresh of file browsers
**GitHub**: [#332](https://github.com/monghithub/apk-sftp/issues/332)

**¿Por qué se hace?** Sincronizar

**¿Qué soluciona?** Ver cambios después de transfer

---



---

## ✅ Criterios de Aceptación

La Phase 6 se considera **COMPLETA** cuando:

- [ ] Todos los issues están cerrados
- [ ] Todos los tests pasan
- [ ] Code review aprobado
- [ ] No hay regressions en fases previas
- [ ] Documentation actualizada

---

## 🔗 Dependencias

**Prerequisitos**: Phase 5: Remote File Browser

**Bloquea**: Phase 7: Main Application UI & Navigation

---

## 📚 Referencias

- [Propuesta](../PROPOSAL.md)
- [Diseño Técnico](../DESIGN.md)
- [Especificaciones](../spec/)

---

**Fase**: 6 de 12
**Issues**: 12
**Priority**: 🟡 HIGH
