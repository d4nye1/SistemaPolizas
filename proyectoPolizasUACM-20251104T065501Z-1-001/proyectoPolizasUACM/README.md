Proyecto de ejemplo: proyectoPolizasUACM
--------------------------------------

Este proyecto es un prototipo con H2 en memoria para la Práctica 2 (UACM).
Abre el proyecto en NetBeans como un proyecto Maven y ejecútalo.

Endpoints principales:
- GET  /polizas
- GET  /polizas/{clave}
- POST /polizas
- PUT  /polizas/{clave}
- DELETE /polizas/{clave}
- GET  /polizas/{clave}/verificar-consistencia

La consola H2 está habilitada en: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:polizasdb
Usuario: sa
Contraseña: (vacía)
