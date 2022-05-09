# base16
(EN)
A java program to manage a grocery. Features:
- Can store data from a graphical table to database
- Can be customable with:
* Look and feel
* Customable window icon
* Customable frame image
* Switchable name
* Import/export database
- Employee, partners, providers, sold and stored items data display on a table or detailed window
- Employee, partners and providers data storage
- Employee, partners, providers image storage if is needed to make physical IDs
- in case of program's update, files and libraries download
- Exception handling
- JSON backup handler
- Bill and ticket print
- Barcode reader
- Threads

To start programming, everything is on "src" folder. Database, windows, classes, libraries and media

Notes:
- To avoid a message during an exception, you will create a new folder into "src/data/logs" called "exceptions". This don't be created outside of logs folder nor into static folder.
- Sometimes will throw exceptions caused by using windows' look and feel in other systems. To avoid this exception, delete file into "src/data/config" called "config.properties", replace by "preconfig.properties" and rename it as the previous deleted file (config.properties). I recommend you to copy "preconfig.properties" instead of using the same file.

Installation:

- To use without any troubles or errors, it'll need to use a database manager like MySQL or MariaDB (xampp). It's needed to the correct execution of the program. Database can be found on the following path: "src/data/database/MySQL", called as tienda.sql.
- After of this, you'll need to replace "config.properties" by "preconfig.properties", renaming it as the previous deleted file. I recommend you to copy "preconfig.properties" instead of using the same file
- In case if have a MySQL database server previously configurated and completed database import step, you'll need configurate on "Configurar BD" panel inside in the window called "Herramientas de administrador" ("Administrator Tools") the server connection.

(ES)
Programa hecho en Java para administrar una tienda de abarrotes. Sus características:
- Poder guardar, desde una tabla, datos a una base de datos
- Puede ser personalizado con: 
* Look and feel
* Icono de ventana personalizable
* Imagen de ventana personalizable 
* Cambiar el nombre del programa
* Importar/Exportar la base de datos
- Almacena datos de empleados, socios y proveedores
- Almacenamiento de las imágenes de los socios, proveedores y/o empleados en caso de crear una credencial física
- Visualización de los datos de los empleados, socios, proveedores, descuentos, productos vendidos y almacenados tablas o ventanas para ver los datos a más detalle
- En caso de actualización, descarga los archivos y librerías
- Gestión de errores
- Gestión de copias de seguridad con JSON
- Impresión de ticket y factura
- Lector de código de barras
- Hilos

Para poder programar, todo el código está en la carpeta "src". Ahí está la base de datos, ventanas, clases, librerias y medios visuales.

Notas:
- Para que no dé un mensaje en el momento de una excepción, se debe crear la carpeta en la siguiente dirección: "src/data/logs". No debe estar fuera de logs ni mucho menos dentro de la carpeta static.
- A veces lanzará errores por el uso de apariencia de windows en otros sistemas. Para prevenir este error, elimine el archivo que está en "src/data/config" llamado "config.properties" y reemplázalo por "preconfig.properties" renombrándolo como el archivo eliminado (config.properties). Recomiendo que se haga una copia del archivo preconfig en vez de usar el mismo archivo.

Instalación:

- Para poder usar sin problemas o errores, se debe utilizar el gestor de base de datos MySQL o MariaDB (xampp). Es necesario para el correcto funcionamiento del programa. La base de datos se encuentra en "src/data/database/MySQL", llamado como tienda.sql.
- Después, debes cambiar el archivo "config.properties" por "preconfig.properties", renombrándolo como el archivo eliminado.
- En caso de que tengas un servidor de base de datos de MySQL ya configurado y hayas completado el paso de la importación de la base de datos, debes configurar, en el panel de "Configurar BD" en Herramientas de administrador, la conexión al servidor.