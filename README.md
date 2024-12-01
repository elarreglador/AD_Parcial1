# Examen del primer parcial de Acceso a Datos (AD)

Consta de dos partes:


## Parte 1
Examen-1A-2024.parte1.pdf

Tenemos dos bases de datos iguales en dos SGBD diferentes: SQLite y HSQLDB. Contienen las siguientes tablas, que representan una lista de alumnos y otra de municipios. Los alumnos se relacionan con el municipio en el que viven a través de la clave ajena id_municipio.
Realiza un programa que en la función main ofrezca un menú en el que se preguntará con qué base de datos queremos conectar.

¿Con qué SGBD quiere conectar?
  1 SQLite
  2 HSQLDB

Se abrirá una conexión con el SGBD elegido y se mostrará el nombre del Driver empleado en la conexión (consulta los metadatos). (1.5 PTO)
A continuación se mostrará un segundo menú que permitirá realizar las siguientes opciones.

¿Qué operación desea realizar?
  1 Mostrar un listado de alumnos
  2 Mostrar un listado de municipios
  3 Introducir un nuevo alumno (PreparedStatement)
  4 Introducir un nuevo municipio (Procedimiento)
  5 Salir

Una vez finalizada la opción, se volverá a mostrar el segundo menú. Cada opción se codificará en una función diferente, que recibirá como argumento la conexión. Cada función debe cerrar todos los recursos que emplee (Statements, ResultSets, etc).

### OPCIÓN 1 (1 PTO)
Codifica la función mostrar_alumnos (Connection con). No devolverá ningún resultado. Mostrará por pantalla un listado con el nombre y apellido de los alumnos y el código del municipio en el que viven. Para ello se utilizará un Statement que obtenga solo la información requerida.

### OPCIÓN 2 (1 PTO)
Codifica la función mostrar_municipios (Connection con). No devolverá ningún resultado.
Mostrará por pantalla un listado con todas las columnas de la tabla municipios. Para ello se utilizará un Statement que obtenga solo la información requerida.

### OPCIÓN 3 (1.5 PTO)
Codifica la función introduce_alumno(Connection con). Se pedirá por consola los datos necesarios y se realizará una inserción en la base de datos, utilizando un PreparedStatement.

### OPCIÓN 4 (1 PTO)
Codifica la función introduce_municipio(Connection con). Primero se debe comprobar que el SGBD elegido sea HSQLDB. Para ello comprueba los metadatos, concretamente el nombre del driver. Se pedirá por consola los datos necesarios y se realizará una inserción en la base de datos usando un procedimiento que YA existe en la base de datos. El procedimiento tiene la siguiente cabecera:

nuevo_municipio (
   IN cod VARCHAR(5),
   IN nombre VARCHAR(10),
   IN n_habitantes INT
)

Se entregará un proyecto Maven de Eclipse que contenga todo lo necesario para ser compilado y probado, es decir, debe incluir todas las librerías y las bases de datos con rutas relativas.


## Parte 2
Examen-1A-2024.parte2.pdf

Partiendo de las clases proporcionadas: Alumno, Grupo y NewMain, se quiere implementar un programa que serialice en XML un objeto de la clase Grupo y posteriormente lo deserialice. Para ello:

   a) Crea un proyecto Maven con las librerías necesarias y realiza las modificaciones pertinentes para que funcione. (1 PTOS)

   b) Completa el código que falta en la función SerializeGroup. (1.5PTOS)

   c) Completa el código que falta en la función DeserializeGroup. (1.5PTOS)

El código ya existente de la función main NO debe ser modificado.
Ambas funciones deben hacer un tratamiento adecuado de las excepciones
Se entregará un proyecto de Maven de Eclipse que contenga todo lo necesario para ser compilado y probado.
