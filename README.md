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