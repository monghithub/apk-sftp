# Phase 5: Remote File Browser

## 📌 Descripción General

Navegar archivos en servidor SFTP remoto

### Problemas que soluciona
- Similares a local pero con protocolo SFTP
- Manejo de permisos del servidor
- Errores de red durante navegación

---

## 🎯 Objetivos de la Fase

Al completar Phase 5, tendrás:
✅ ['Create', 'SFTPFileManager']
✅ ['Implement', 'remote']
✅ ['Build', 'RemoteFileViewModel']
✅ ['Create', 'RemoteFileBrowserScreen']
... (12 tareas totales)

---

## 📋 Issues (12 tareas)

### Issue [5.1] - Create SFTPFileManager for remote file operations
**GitHub**: [#309](https://github.com/monghithub/apk-sftp/issues/309)

**¿Por qué se hace?** Operaciones SFTP

**¿Qué soluciona?** Listar, navegar, borrar, renombrar

---

### Issue [5.2] - Implement remote directory listing via SFTP
**GitHub**: [#310](https://github.com/monghithub/apk-sftp/issues/310)

**¿Por qué se hace?** Ver archivos remotos

**¿Qué soluciona?** Conecta a servidor

---

### Issue [5.3] - Build RemoteFileViewModel for state management
**GitHub**: [#311](https://github.com/monghithub/apk-sftp/issues/311)

**¿Por qué se hace?** Estado remoto

**¿Qué soluciona?** Sincronizar UI

---

### Issue [5.4] - Create RemoteFileBrowserScreen UI component
**GitHub**: [#312](https://github.com/monghithub/apk-sftp/issues/312)

**¿Por qué se hace?** Interfaz remota

**¿Qué soluciona?** Similar a local pero con archivos servidor

---

### Issue [5.5] - Implement directory navigation on remote server
**GitHub**: [#313](https://github.com/monghithub/apk-sftp/issues/313)

**¿Por qué se hace?** Navegar servidor

**¿Qué soluciona?** Entrar/salir carpetas

---

### Issue [5.6] - Add file list display with permissions and metadata
**GitHub**: [#314](https://github.com/monghithub/apk-sftp/issues/314)

**¿Por qué se hace?** Ver permisos

**¿Qué soluciona?** Mostrar rwx-rw-r-- etc

---

### Issue [5.7] - Implement file selection (single and multi-select)
**GitHub**: [#315](https://github.com/monghithub/apk-sftp/issues/315)

**¿Por qué se hace?** Seleccionar remotos

**¿Qué soluciona?** Para downloads

---

### Issue [5.8] - Add file sorting (name, size, date, permissions)
**GitHub**: [#316](https://github.com/monghithub/apk-sftp/issues/316)

**¿Por qué se hace?** Ordenar

**¿Qué soluciona?** Con permisos además

---

### Issue [5.9] - Implement file search/filtering
**GitHub**: [#317](https://github.com/monghithub/apk-sftp/issues/317)

**¿Por qué se hace?** Buscar remoto

**¿Qué soluciona?** En archivos servidor

---

### Issue [5.10] - Add remote path display with breadcrumb
**GitHub**: [#318](https://github.com/monghithub/apk-sftp/issues/318)

**¿Por qué se hace?** Ver ruta

**¿Qué soluciona?** Usuario sabe dónde está

---

### Issue [5.11] - Implement refresh button to re-query server
**GitHub**: [#319](https://github.com/monghithub/apk-sftp/issues/319)

**¿Por qué se hace?** Actualizar

**¿Qué soluciona?** Ver cambios recientes

---

### Issue [5.12] - Add error handling for permission denied
**GitHub**: [#320](https://github.com/monghithub/apk-sftp/issues/320)

**¿Por qué se hace?** Manejar errores

**¿Qué soluciona?** Mensaje claro si no tiene permisos

---



---

## ✅ Criterios de Aceptación

La Phase 5 se considera **COMPLETA** cuando:

- [ ] Todos los issues están cerrados
- [ ] Todos los tests pasan
- [ ] Code review aprobado
- [ ] No hay regressions en fases previas
- [ ] Documentation actualizada

---

## 🔗 Dependencias

**Prerequisitos**: Phase 4: Local File Browser

**Bloquea**: Phase 6: Connection Profiles

---

## 📚 Referencias

- [Propuesta](../PROPOSAL.md)
- [Diseño Técnico](../DESIGN.md)
- [Especificaciones](../spec/)

---

**Fase**: 5 de 12
**Issues**: 12
**Priority**: 🟡 HIGH
