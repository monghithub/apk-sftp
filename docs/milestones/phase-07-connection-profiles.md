# Phase 7: Connection Profiles

## 📌 Descripción General

Guardar y reutilizar configuraciones de conexión

### Problemas que soluciona
- Usuario no quiere escribir host/usuario cada vez
- Perfiles permiten acceso rápido a servidores frecuentes

---

## 🎯 Objetivos de la Fase

Al completar Phase 7, tendrás:
✅ ['Create', 'ConnectionProfile']
✅ ['Implement', 'profile']
✅ ['Build', 'ProfileViewModel']
✅ ['Create', 'ProfileListScreen']
... (13 tareas totales)

---

## 📋 Issues (13 tareas)

### Issue [7.1] - Create ConnectionProfile data model
**GitHub**: [#333](https://github.com/monghithub/apk-sftp/issues/333)

**¿Por qué se hace?** Modelo de datos

**¿Qué soluciona?** Estructura de perfil

---

### Issue [7.2] - Implement profile persistence using EncryptedSharedPreferences
**GitHub**: [#334](https://github.com/monghithub/apk-sftp/issues/334)

**¿Por qué se hace?** Guardar

**¿Qué soluciona?** Persistir perfiles

---

### Issue [7.3] - Build ProfileViewModel for managing profiles
**GitHub**: [#335](https://github.com/monghithub/apk-sftp/issues/335)

**¿Por qué se hace?** Estado de perfiles

**¿Qué soluciona?** Agregar/editar/borrar

---

### Issue [7.4] - Create ProfileListScreen UI
**GitHub**: [#336](https://github.com/monghithub/apk-sftp/issues/336)

**¿Por qué se hace?** Lista de perfiles

**¿Qué soluciona?** Ver todos los perfiles

---

### Issue [7.5] - Build create profile dialog/screen
**GitHub**: [#337](https://github.com/monghithub/apk-sftp/issues/337)

**¿Por qué se hace?** Crear nuevo

**¿Qué soluciona?** Interfaz para nuevo perfil

---

### Issue [7.6] - Implement profile editing
**GitHub**: [#338](https://github.com/monghithub/apk-sftp/issues/338)

**¿Por qué se hace?** Editar perfil

**¿Qué soluciona?** Cambiar host/usuario/clave

---

### Issue [7.7] - Implement profile deletion with confirmation
**GitHub**: [#339](https://github.com/monghithub/apk-sftp/issues/339)

**¿Por qué se hace?** Borrar perfil

**¿Qué soluciona?** Confirmar antes de borrar

---

### Issue [7.8] - Add profile quick connect functionality
**GitHub**: [#340](https://github.com/monghithub/apk-sftp/issues/340)

**¿Por qué se hace?** Conexión rápida

**¿Qué soluciona?** Tap y conectar

---

### Issue [7.9] - Implement profile reordering/favorite marking
**GitHub**: [#341](https://github.com/monghithub/apk-sftp/issues/341)

**¿Por qué se hace?** Organizar

**¿Qué soluciona?** Drag to reorder

---

### Issue [7.10] - Add profile rename functionality
**GitHub**: [#342](https://github.com/monghithub/apk-sftp/issues/342)

**¿Por qué se hace?** Renombrar perfil

**¿Qué soluciona?** Cambiar nombre de perfil

---

### Issue [7.11] - Implement profile export (JSON format, no keys)
**GitHub**: [#343](https://github.com/monghithub/apk-sftp/issues/343)

**¿Por qué se hace?** Exportar

**¿Qué soluciona?** Compartir config sin claves

---

### Issue [7.12] - Implement profile import from backup file
**GitHub**: [#344](https://github.com/monghithub/apk-sftp/issues/344)

**¿Por qué se hace?** Importar

**¿Qué soluciona?** Restaurar desde backup

---

### Issue [7.13] - Add default profile selection for auto-connect
**GitHub**: [#345](https://github.com/monghithub/apk-sftp/issues/345)

**¿Por qué se hace?** Auto-connect

**¿Qué soluciona?** Conectar automáticamente

---



---

## ✅ Criterios de Aceptación

La Phase 7 se considera **COMPLETA** cuando:

- [ ] Todos los issues están cerrados
- [ ] Todos los tests pasan
- [ ] Code review aprobado
- [ ] No hay regressions en fases previas
- [ ] Documentation actualizada

---

## 🔗 Dependencias

**Prerequisitos**: Phase 6: File Transfer Operations

**Bloquea**: Phase 8: Error Handling & User Feedback

---

## 📚 Referencias

- [Propuesta](../PROPOSAL.md)
- [Diseño Técnico](../DESIGN.md)
- [Especificaciones](../spec/)

---

**Fase**: 7 de 12
**Issues**: 13
**Priority**: 🟡 HIGH
