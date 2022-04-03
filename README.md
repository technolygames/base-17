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
- Hilos

Para poder programar, la carpeta con todo el código es "src". Ahí está la base de datos, ventanas, clases, librerias y medios visuales necesarios para poder programar.

Notas:
- Para que no dé el error al momento de una excepción, se debe crear la carpeta en la siguiente dirección: src/data/logs. No debe estar fuera de logs ni mucho menos dentro de la carpeta static
- A veces saldrán errores por el uso de look and feel de windows en otros sistemas. Para prevenir este error, elimine el archivo que está en src/data/config llamado "config.properties" y reemplazarlo por preconfig.properties cambiándole el nombre por el mismo del archivo eliminado. Es recomendable que se haga una copia del archivo preconfig en vez de hacerlo en el mismo archivo

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