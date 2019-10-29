# IPS: 1920-32

## Base de datos
La base de datos es H2 y esta en memoria.
Eso significa que no hay datos ni esquemas cuando la aplicacion esta parada.
Es la propia aplicacion la que en segundo plano inicia la base de datos.
Esto es muy comodo de cara al desarrollo ya que evita problemas con las actualizaciones
de esquemas y con comportamientos raros.

Por comodidad, hay una consola web para hacer consultas a la base de datos.
Una vez la aplicacion este funcionando, abre un nevegador y escribe `http://localhost:8080/db`
Dale a conectar con los valores que aparecen por defecto.

Los datos que veras en la base de datos se crean en `HospitalApplication.java`.


# Historias de usuario segundo sprint 2019-10-22

## Doctor
* Como doctor quiero poder listar mis citas
  - Quiero ver cuales son urgentes
	- Solo puedo ver mis citas
* ! Como doctor quiero poder actualizar una de mis citas.
	- Quiero poder poner la hora real de salida y de entrada del paciente
  * ! Quiero poder anadir las causas
    - Puede haber o no causas y pueden ser mas de una
    - campo opcional
  * ! Quiero poder asignar el diagnostico basado en el CIE-10
    - Podrian ser varios diagnosticos (0 a n diagnosticos)
  * ! Quiero poder editar las prescripciones
    - poder selecionar prescripciones existentes o poder crear una nueva
    - tiempos entre dosis
    - cantidades
* Quiero poder gestionar el calendario de vacunas de una persona
  - La fecha es cuando te toca la vacuna no cuando has de ir.
  - Realmente el calendario marca si te han puesto o no la vacuna
  - Es realmente una lista de vacunas pendientes

# Notas sobre lo que se puede hacer con una cita desde el punto de vista de un doctor
- Cita
    Indico las causas
    Indico el procedimiento
    Hago el diagnostico
    Dar una prescripcion
    Esto ha de quedar en el historial
- A parte de esto hay calendario de vacunas
- Y ver antecedentes medicos del paciente

## Administrador
* Como administrativo quiero poder gestionar todas las citas
! * Como administrativo quiero poder crear citas
  - Ha de permitir seleccionar el paciente al que se le asigna la cita
  - Ha de permitir seleccionar el doctor o doctores.
    - Puede haber mas de un doctor asignado (0 a n doctores)
    - La asignacion de doctores es opcional
  - Quiero poder indicar si son urgentes o no
    - Por defecto no son urgentes
    - Ha de enviar un email al medico cuando se marque como urgente
  - Asignar la informacion de contacto que por defecto ha de ser la de contacto del paciente
* Visualizar el historial medico de un paciente
  - Pero no puede ser editado
* Gestionar la creacion o borrado de habitaciones
* Gestionar el horario de los trabajadores
  - Indicar cuando no trabajan
  ### Para un futuro
  * Como administrativo quiero poder crear nuevos pacientes
  * Como administrativo quiero poder crear nuevos doctores
  * Como administrativo quiero poder crear nuevos enfermeros

## Enfermeros

## Pacientes
* Como paciente quiero poder ver mis citas

## Auditor
* Como auditor quiero poder ver los logs del sistema de cada una de las
  actividades
* Como auditor quiero que todos los usuarios hayan iniciado sesion para
  realizar sus respectivas tareas

