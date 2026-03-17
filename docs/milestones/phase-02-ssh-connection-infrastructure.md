# Phase 2: SSH Connection Infrastructure

## 📌 Descripción General

Establece la capacidad de conectar a servidores SSH

### Problemas que soluciona
- Sin conexión SSH, no podemos acceder a servidores remotos
- Manejo correcto de sesiones y ciclo de vida
- Manejo de errores y timeouts

---

## 🎯 Objetivos de la Fase

Al completar Phase 2, tendrás:
✅ ['Create', 'SSH']
✅ ['Implement', 'SSH']
✅ ['Add', 'connection']
✅ ['Implement', 'automatic']
... (8 tareas totales)

---

## 📋 Issues (8 tareas)

### Issue [2.1] - Create SSH connection manager class using JSch
**GitHub**: [#279](https://github.com/monghithub/apk-sftp/issues/279)

**¿Por qué se hace?** Base para todas las conexiones SSH

**¿Qué soluciona?** Abstraer JSch en una clase reutilizable

---

### Issue [2.2] - Implement SSH session creation and lifecycle management
**GitHub**: [#280](https://github.com/monghithub/apk-sftp/issues/280)

**¿Por qué se hace?** Crear/mantener/cerrar sesiones

**¿Qué soluciona?** ViewModels pueden usar sesiones sin preocuparse por ciclo de vida

---

### Issue [2.3] - Add connection timeout and error handling
**GitHub**: [#281](https://github.com/monghithub/apk-sftp/issues/281)

**¿Por qué se hace?** Evitar bloqueos indefinidos

**¿Qué soluciona?** Mejor UX con timeouts claros y errores informativos

---

### Issue [2.4] - Implement automatic connection cleanup on app exit
**GitHub**: [#282](https://github.com/monghithub/apk-sftp/issues/282)

**¿Por qué se hace?** Liberar recursos

**¿Qué soluciona?** Previene conexiones fantasma que usan batería

---

### Issue [2.5] - Create ConnectionViewModel for managing connection state
**GitHub**: [#283](https://github.com/monghithub/apk-sftp/issues/283)

**¿Por qué se hace?** Estado centralizado

**¿Qué soluciona?** UI siempre sincronizada con estado real

---

### Issue [2.6] - Implement connection status live data
**GitHub**: [#284](https://github.com/monghithub/apk-sftp/issues/284)

**¿Por qué se hace?** Mostrar estado en UI

**¿Qué soluciona?** Usuario sabe si está conectado/conectando/desconectado

---

### Issue [2.7] - Add SSH connection error handler
**GitHub**: [#285](https://github.com/monghithub/apk-sftp/issues/285)

**¿Por qué se hace?** Manejo de fallos

**¿Qué soluciona?** Información clara cuando algo falla

---

### Issue [2.8] - Implement reconnection logic for dropped connections
**GitHub**: [#286](https://github.com/monghithub/apk-sftp/issues/286)

**¿Por qué se hace?** Recuperación automática

**¿Qué soluciona?** No pierde conexión por lag temporal

---



---

## ✅ Criterios de Aceptación

La Phase 2 se considera **COMPLETA** cuando:

- [ ] Todos los issues están cerrados
- [ ] Todos los tests pasan
- [ ] Code review aprobado
- [ ] No hay regressions en fases previas
- [ ] Documentation actualizada

---

## 🔗 Dependencias

**Prerequisitos**: Phase 1

**Bloquea**: Phase 3: Local File Browser

---

## 📚 Referencias

- [Propuesta](../PROPOSAL.md)
- [Diseño Técnico](../DESIGN.md)
- [Especificaciones](../spec/)

---

**Fase**: 2 de 12
**Issues**: 8
**Priority**: 🟡 HIGH
