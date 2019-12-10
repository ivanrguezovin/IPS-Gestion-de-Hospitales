---
title: 'Gestión de hospitales'
author:
- Raúl López Calvo uo263918@uniovi.es
- Iván Rodríguez Ovín uo265368@uniovi.es
- Ricardo Soto Estévez uo265710@uniovi.es
- Alejandro Sáez Morollón uo198755@uniovi.es

---

## Descripción del sistema
El producto diseñado es una herramienta de gestión de hospitales. Una vez
implantado el sistema, los usuarios del mismo podrán gestionar diferentes
procedimientos hospitalarios dado el rol de su usuario.

## Roles de usuario

* Doctor: Como doctor, un usuario puede realizar las siguientes acciones:
  - Puede gestionar las vacunas de los pacientes, tanto creando nuevas citas
    para vacunarse como modifando las ya existentes.
  - Puede gestionar las citas de los pacientes, consultar información de estas,
    añadir diagnosticos (utilizando el sistema de clasificación CIE-10), editar
    las citas ya existentes y solicitar nuevas citas que seran gestionadas por
    algun administrativo del hospital.
* Enfermero: Como enfermero, un usuario puede ver el listado de citas que tiene
  asignadas.
* Administrativo: Como administrativo, un usuario puede dar te alta a otros
  usuarios, tanto miembros del hospital como pacientes, puede gestionar los
  horarios de enfermeros y doctores, gestionar la información de los pacientes
  y gestionar las citas que los doctores y enfermeros tienen.
* Gerente: Como gerente, un usuario puede ver las estadísticas de los
  diagnósticos creados y ver las actividades de los usuarios del hospital para
  garantizar la seguridad y cumplimiento de las normativas vigentes.

## Entorno tecnológico
El producto ha sido diseñado utilizando las siguientes tecnologías:

* `Spring`: Se trata de un _framework_ de desarrollo para la plataforma `Java`.
  Principalmente se utiliza para desarrollar aplicaciones web utilizando
  `Java EE`, pero en este caso ha sido adaptado para generar una aplicación de
  escritorio utilizando `Java Swing`.
* `Maven`: Dado que `Spring` esta formado por un conjunto grande de dependencias y
  que el proyecto a sido desarrollado por varias personas, la utilización de un
  gestor de proyectos.
* `H2`: En el entorno de desarrollo se ha utilizado `H2` como base de datos
  relacional. Dado que puede ser incorporada dentro de la aplicación, no hay
  necesidad de instalar nada en los equipos de desarrollo y esto ha
  simplificado notablemente el proceso.

El proyecto hace un uso intensivo de bibliotecas externas.
Algunas de las más relevantes son:

* `Spring Boot`: es una solución que auna una serie de convenciones del
  _framework_ `Spring`. Permite el desarrollo de aplicaciones utilizando
  configuraciones aceptadas como buenas practicas por la comunidad.
* `JCalendar`: Biblioteca de diseño de calendarios creados con `Java Swing`.
* `JavaMail`: Biblioteca de gestión de correos electrónicos.
* `LGoodDatePicker`: Biblioteca de generación de componentes visuales para la
  selección de fechas.
* `Jackson`: Biblioteca de gestión de `CSV`.
* `Apache Tomcat`: _Middleware_ para el despliegue de aplicaciones web.
* `JQuery`: Biblioteca para simplificar el desarrollo en `Javascript`.
* `Chart.js`: Biblioteca para el dibujo de gráficas.

Adicionalmente a las tecnologías citadas con anterioridad, otras, no menos
importantes, en forma de dependencias internas o productos externos han sido
también utilizadas:

* Entornos de desarrollo: Los desarrolladores del producto han utilizado
  `Eclipse` e `IntelliJ` para crear el producto
* Control de versiones: Se ha utilizado `git` utilizando el flujo de trabajo
  conocido comúnmente como `Git Feature Branch Workflow`. El código ha sido
  almacenado en `Bitbucket`.

## Story mapping

| Sprint | Historias |
|:------:|:----------|
| 1      | 7295,7297,7299,7300,7301,7302,7304,7305,7306 |
| 2      | 7828,7829,8208,8209,8213,8217,8218,8220,8226,8237 |
| 3      | 8214,8238,8337,8364,8370,8393,8394,8395,8470,8471,8475,8566,8570,8638 |

### Sprint 1
- 7295: Como administrativo quiero asignar jornadas laborales a los médicos y enfermeros
- 7297: Como administrativo quiero asignar un horario a la cita
- 7299: Como administrativo quiero establecer la ubicación de una cita
- 7300: Como administrativo quiero asignar información de contacto a las citas
- 7301: Como administrativo y como médico quiero visualizar el historial médico desde una cita
- 7302: Como médico quiero listar el calendario de citas
- 7304: Como médico quiero asignar la hora de entrada y salida del paciente en su cita
- 7305: Como médico quiero añadir las causas por las que el paciente acude a la cita
- 7306: Como médico quiero indicarle al paciente una prescripción

### Sprint 2
- 7828: Como administrativo quiero fijar citas entre pacientes y médicos
- 7829: Como administrativo quiero indicar que una cita es urgente
- 8208: Como administrativo quiero poder asignar varios médicos a una misma cita
- 8209: Como administrativo quiero poder crear descansos para los médicos y enfermeros
- 8213: Como auditor quiero que todos los usuarios tengan que autenticarse
- 8217: Como administrativo quiero poder visualizar el historial de un paciente
- 8218: Como medico quiero poder gestionar el calendario de vacunas de un paciente
- 8220: Como medico quiero poder gestionar los diagnósticos de una cita
- 8226: Como médico quiero poder gestionar las vacunas
- 8237: Como administrativo quiero poder editar citas futuras

### Sprint 3
- 8214: Como auditor quiero poder ver la actividad de cada uno de los usuarios
- 8238: Como administrativo quiero poder ver las citas
- 8337: Como médico quiero poder editar las prescripciones
- 8364: Como médico quiero poder visualizar el historial de un paciente
- 8370: Como administrativo quiero poder crear usuarios
- 8393: Como administrativo quiero crear citas entre pacientes y enfermeros
- 8394: Como enfermero quiero poder ver todas mis citas
- 8395: Como médico quiero solicitar citas que acepte el administrador
- 8470: Como administrativo quiero poder aprobar citas propuestas
- 8471: La aplicación debe de estar dotada de un diseño cohesivo
- 8475: Como médico y enfermero he de poder filtrar las citas
- 8566: Como auditor quiero que las contraseñas de los usuarios no estén almacenadas en plano
- 8570: Como médico quiero marcar como eliminado un diagnóstico en el historial y dejar ese diagnóstico como estaba en la cita
- 8638: Como gerente quiero poder ver las estadísticas de enfermedades que han sido registradas en el hospital

## Modelo de datos
![](./doc/database_schema.png)

## Mapa de navegación de usuario
![](./doc/map.jpg)

