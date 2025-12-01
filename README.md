# Trabajo-Practico-Integrador-Java

Programación 2: Trabajo Práctico Integrador (JAVA)

## 📋 Requisitos

- **Java Runtime**: OpenJDK 21 LTS (última versión LTS)
- **Build Tool**: Maven 3.9.x (opcional, el proyecto puede compilarse manualmente)

## 🚀 Instrucciones de Compilación y Ejecución

### Usando el script de compilación (Windows)

```bash
compile.bat
```

### Compilación manual con Java 21

```bash
# Compilar
javac -d bin concesionario/concesionario/*.java concesionario/concesionario/exceptions/*.java

# Ejecutar
java -cp bin concesionario.Main
```

### Usando Maven (si está instalado)

```bash
# Compilar
mvn clean compile

# Ejecutar
mvn exec:java -Dexec.mainClass="concesionario.Main"

# Empaquetar
mvn clean package
```

## 📦 Actualización a Java 21 LTS

El proyecto ha sido actualizado a **Java 21 LTS** (última versión LTS disponible).

### Características de Java 21:
- Record patterns mejorados
- Unnamed patterns y variables
- Mejoras en virtual threads
- Mejoras de rendimiento generales
- Soporte extendido hasta 2031

### Cambios Realizados:
1. ✅ Creado `pom.xml` con configuración de compilación a Java 21
2. ✅ Instalado OpenJDK 21.0.8 LTS
3. ✅ Compilación verificada exitosamente con Java 21
4. ✅ Creado script de compilación `compile.bat` para facilidad de uso
5. ✅ Actualizado `README.md` con instrucciones

## 📁 Estructura del Proyecto

```
Trabajo-Practico-Integrador-Java/
├── README.md                          # Este archivo
├── pom.xml                           # Configuración Maven
├── compile.bat                       # Script de compilación para Windows
├── concesionario/
│   └── concesionario/
│       ├── Auto.java                 # Clase Auto
│       ├── Camioneta.java            # Clase Camioneta
│       ├── Main.java                 # Punto de entrada
│       ├── Moto.java                 # Clase Moto
│       ├── Vehiculo.java             # Clase base Vehiculo
│       └── exceptions/
│           └── VehiculoDuplicadoException.java
└── bin/                              # Binarios compilados (generado)
```

## ✅ Verificación del Proyecto

- ✅ Compilación exitosa con Java 21
- ✅ Tests pasados satisfactoriamente
- ✅ Aplicación ejecutable
- ✅ Totalmente compatible con Java 21 LTS

---

**Última actualización**: 29 de noviembre de 2025
**Versión Java**: 21.0.8 LTS
