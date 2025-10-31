# SistemaPolizas

#  Sistema de Distribución de Información de Pólizas de Seguro  
**Universidad Autónoma de la Ciudad de México**  
**Licenciatura en Ingeniería de Software – Arquitectura de Software (2025-II)**  
**Profesor:** Manuel Ignacio Castillo López  

---

##  Introducción  
Este proyecto forma parte de la **Práctica 2: Modelado de arquitecturas de software**, cuyo objetivo es diseñar e implementar un sistema de software capaz de **gestionar y distribuir información de pólizas de seguro**.  

El sistema debe interactuar con un **servidor remoto** que administra las pólizas por medio de una **API REST**, garantizando la **consistencia de la información** entre ambos repositorios (remoto y local).  

Se desarrolla utilizando **Spring Boot**, **Java**, y **PostgreSQL**, siguiendo los principios de **Extreme Programming (XP)** y buenas prácticas de **arquitectura limpia y código limpio**.

---

## Objetivos del proyecto  
- Implementar modelos de datos para **Clientes**, **Pólizas** y **Beneficiarios** usando **Spring Data JPA (Hibernate)**.  
- Desarrollar un **prototipo de media fidelidad** que soporte operaciones REST (`GET`, `POST`, `PUT`, `DELETE`).  
- Implementar **funciones de verificación de consistencia** entre los datos locales y los del sistema remoto.  
- Aplicar una **estrategia colaborativa con Git**, usando ramas protegidas y control de versiones basado en *pull requests*.  
- Realizar **retrospectivas** para la mejora continua del proceso y del diseño.

---

## Tecnologías utilizadas  
- **Lenguaje:** Java 24  
- **Framework:** Spring Boot 3.5.5  
- **ORM:** Hibernate / Spring Data JPA  
- **Base de datos:** PostgreSQL  
- **Control de versiones:** Git + GitHub  
- **Gestor de dependencias:** Maven  
- **Linter:** Checkstyle  

---

## 📁 Estructura del repositorio  
