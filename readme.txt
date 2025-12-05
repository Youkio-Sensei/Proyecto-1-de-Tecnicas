GUÍA DE EJECUCIÓN DEL SISTEMA DE MONITOREO AMBIENTAL EN JAVA

¿Qué es este programa?

Es una aplicación hecha en Java con Swing que simula un sistema de
monitoreo ambiental.
Permite leer sensores (simulados), calcular promedios, guardar/cargar
datos, configurar umbrales y generar reportes.

PASO 1: INSTALAR JAVA Y UN EDITOR

1. Instalar Java JDK

Necesitas tener instalado Java 8 o superior.

1.  Abre tu navegador.
2.  Busca: “Descargar Java JDK”.
3.  Entra a la página oficial de Oracle u OpenJDK.
4.  Descarga e instala para Windows.

2. Instalar un editor o IDE

Puedes usar: - IntelliJ IDEA - Eclipse - NetBeans - VS Code

PASO 2: IMPORTAR O CREAR EL PROYECTO

1.  Abre tu IDE.
2.  Crea un proyecto Java.
3.  Crea un archivo llamado: EnvironmentalMonitoringSystem.java
4.  Copia y pega tu código allí.

PASO 3: EJECUTAR EL PROGRAMA

1.  Asegúrate de tener este main:

public static void main(String[] args) { SwingUtilities.invokeLater(()
-> { new EnvironmentalMonitoringSystem().setVisible(true); }); }

2.  Haz clic en ‘Run’.
3.  Se abrirá la ventana del sistema.

PASO 4: FUNCIONES PRINCIPALES

-   Leer Sensores: genera temperatura, humedad y CO₂ aleatorios.
-   Calcular Promedios: calcula promedios de los datos.
-   Guardar Datos: crea el archivo environmental_data.txt.
-   Cargar Datos: lee el archivo guardado previamente.
-   Configurar Umbrales: define límites de alerta.
-   Generar Reporte: muestra estadísticas generales.

PASO 5: PRUEBA RÁPIDA

1.  Presiona “Leer Sensores”.
2.  Luego “Calcular Promedios”.
3.  Usa “Guardar Datos”.
4.  Cierra y vuelve a abrir para probar “Cargar Datos”.
5.  Ajusta umbrales para ver alertas.

¿NECESITAS AYUDA CON ALGUN ERROR?

-   Verifica que Java esté instalado.
-   Asegúrate de que el archivo se llame igual que la clase.