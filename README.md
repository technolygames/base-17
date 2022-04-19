# base16
Un programa hecho en Java para administrar una tienda de abarrotes
A java program to manage a grocery

Este programa se encarga de administrar una tienda de abarrotes. Las características son:
- Poder guardar, desde una tabla, todos los datos a mysql;
- Personalizar el programa a tu gusto, con: 
* Look and feel
* Icono de ventana personalizable
* Cambiar el nombre del programa
* Importar/Exportar la base de datos
- Almacena los datos de empleados, socios y proveedores
- Visualización de los datos de los empleados, socios, proveedores, descuentos, productos vendidos y almacenados tablas o ventanas para ver los datos a más detalle
- En caso de actualización del programa, descarga los archivos y librerías
- Correcta gestión de errores
- Almacenamiento de las imágenes de los socios, proveedores y/o empleados en caso de crear una credencial física
- Creación de ticket y factura
- Lector de código de barras
- Almacenamiento preventivo al eliminar datos de la base de datos o crearla por seguridad
- Hilos

Para poder programar, la carpeta con todo el código es "src". Ahí está la base de datos, ventanas, clases, librerias y medios visuales necesarios para poder programar.

Notas:
- Para que no dé el error al momento de una excepción, se debe crear la carpeta en la siguiente dirección: src/data/logs. No debe estar fuera de logs ni mucho menos dentro de la carpeta static
- A veces saldrán errores por el uso de look and feel de windows en otros sistemas. Para prevenir este error, elimine el archivo que está en src/data/config llamado "config.properties" y reemplazarlo por preconfig.properties cambiándole el nombre por el mismo del archivo eliminado. Es recomendable que se haga una copia del archivo preconfig en vez de hacerlo en el mismo archivo

Instalación
Para poder usar sin problemas y/o errores, se debe utilizar el gestor de base de datos MySQL o MariaDB (xampp). Es necesario para el correcto funcionamiento del programa. La base de datos se encuentra en src/data/database/MySQL, nombrado como tienda.sql. Después, se debe cambiar el archivo config.properties (src/data/config) por preconfig.properties, cambiándole a este último el nombre por el archivo eliminado. En caso de tener un servidor de base de datos de MySQL previamente configurado, se debe configurar en el panel de "Configurar BD" en Herramientas de administrador los datos necesarios del servidor.

This program can be use to manage a grocery store. Features:
- Can store from a table to mysql's database
- Can be customable with:
* Look and feel
* Customable window icon
* Switch name
* Import/export database
- Employee, partners and providers data storage
- Employee, partners, providers, sold and store items data display on a table or detailed window
- in case of program update, file and libraries download from internet
- Exception handling
- Employee, partners, providers image storage if is needed to make physical IDs
- Ticket and invoice print
- Barcode reader
- Threads

Everything is on src folder. Everything is on it. Database, windows, classes, libraries and media

Notes:
- To avoid a message during an exception, you will create a new folder into "src/data/logs" called "exceptions". This don't be created outside of logs folder nor into static folder
- Sometimes will throw exceptions caused by using windows' look and feel in other systems. To avoid this exception, delete file into src/data/config called "config.properties", replace by "preconfig.properties" and rename it as the previous deleted file (config.properties). I recommend you to copy preconfig.properties instead of using the same file

Installation
To use without troubles or errors, it'll need to use a database manager like MySQL or MariaDB (xampp; are the same thing) It's needed to the correct execution of the program. Database can be found on the following path: "src/data/database/MySQL", named as tienda.sql. After of this, it'll need to change config.properties by preconfig.properties, changing preconfig like previous deleted file. In case of have a MySQL database server previously configurated, it'll need to configurate on database config panel on admin tools ("Herramientas de administrador") window with the server ip.