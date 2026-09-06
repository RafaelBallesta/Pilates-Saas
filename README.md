# Pilates SaaS

Sistema SaaS para la gestión de estudios de Pilates, desarrollado con **Java + Spring Boot + PostgreSQL + Thymeleaf**.

El proyecto está pensado como una aplicación multi-tenant: cada profesor/estudio tiene su propio subdominio y sus alumnos pertenecen exclusivamente a ese estudio.

---

## 1. Stack tecnológico

| Tecnología | Versión / elección |
|---|---|
| Java | 21 |
| Spring Boot | 3.5.x |
| Maven | Maven Wrapper incluido (`./mvnw` / `mvnw.cmd`) |
| PostgreSQL | 17 |
| Spring Data JPA | Sí |
| Spring Security | Sí |
| Thymeleaf | Sí |
| HTML / CSS / JavaScript | Vanilla |
| Docker | Sí |
| Docker Compose | Sí |
| Arquitectura | Por funcionalidad/módulo |

### Dependencias principales

- Spring Web MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- Thymeleaf Extras Spring Security 6
- Spring Validation
- PostgreSQL Driver
- Lombok

---

# 2. Requisitos previos

Antes de ejecutar el proyecto, el equipo debe tener instalados:

1. Git
2. Java JDK 21
3. Docker
4. Docker Compose
5. Un IDE/editor de código, recomendado:
   - IntelliJ IDEA
   - Visual Studio Code
   - Eclipse

> **No es necesario instalar Maven manualmente.**
>
> El proyecto utiliza Maven Wrapper (`mvnw` / `mvnw.cmd`).

> **No es necesario instalar PostgreSQL directamente en el equipo.**
>
> PostgreSQL se ejecuta mediante Docker.

---

# 3. Java 21

El proyecto utiliza **Java 21**.

Es importante instalar el **JDK**, no solamente el JRE, porque necesitamos herramientas como `javac`.

## Windows

Se puede instalar un JDK 21 mediante:

- Eclipse Temurin
- Microsoft Build of OpenJDK
- Oracle JDK

Después de instalarlo, abrir una nueva terminal y verificar:

```powershell
java -version
```

Y:

```powershell
javac -version
```

Debería aparecer Java 21.

También conviene verificar:

```powershell
echo $env:JAVA_HOME
```

Si `JAVA_HOME` no está configurado, configurarlo apuntando al directorio donde se instaló el JDK 21.

Ejemplo:

```text
C:\Program Files\Eclipse Adoptium\jdk-21.x.x-hotspot
```

Luego cerrar y volver a abrir la terminal.

---

## macOS

Se recomienda instalar JDK 21 mediante Homebrew:

```bash
brew install openjdk@21
```

Verificar:

```bash
java -version
```

```bash
javac -version
```

Si el sistema no encuentra Java automáticamente, configurar `JAVA_HOME`.

Por ejemplo:

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
```

Para hacerlo permanente, agregarlo al archivo correspondiente:

```bash
~/.zshrc
```

y luego:

```bash
source ~/.zshrc
```

Verificar:

```bash
echo $JAVA_HOME
```

---

## Ubuntu / Linux

En Ubuntu se puede instalar OpenJDK 21:

```bash
sudo apt update
sudo apt install openjdk-21-jdk
```

Verificar:

```bash
java -version
```

```bash
javac -version
```

Verificar `JAVA_HOME`:

```bash
echo $JAVA_HOME
```

Si no está definido, localizar Java:

```bash
readlink -f $(which java)
```

Normalmente será algo similar a:

```text
/usr/lib/jvm/java-21-openjdk-amd64/bin/java
```

Por lo tanto:

```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
```

Para hacerlo permanente, agregarlo al archivo:

```bash
~/.bashrc
```

y ejecutar:

```bash
source ~/.bashrc
```

---

# 4. Git

Git es necesario para obtener el proyecto y trabajar con el repositorio.

## Windows

Instalar Git desde:

https://git-scm.com/

Verificar:

```powershell
git --version
```

## macOS

Con Homebrew:

```bash
brew install git
```

Verificar:

```bash
git --version
```

## Ubuntu / Linux

```bash
sudo apt update
sudo apt install git
```

Verificar:

```bash
git --version
```

---

# 5. Docker

Docker se utiliza para ejecutar PostgreSQL.

## Windows

La opción recomendada es:

**Docker Desktop**

https://www.docker.com/products/docker-desktop/

Después de instalar Docker Desktop, abrirlo y comprobar:

```powershell
docker --version
```

Y:

```powershell
docker compose version
```

Ambos comandos deben funcionar.

---

## macOS

La opción recomendada es:

**Docker Desktop**

https://www.docker.com/products/docker-desktop/

Después de instalarlo y abrirlo:

```bash
docker --version
```

```bash
docker compose version
```

---

## Ubuntu / Linux

Se necesita Docker Engine y Docker Compose.

Verificar:

```bash
docker --version
```

Y:

```bash
docker compose version
```

En Ubuntu, el paquete de Compose puede aparecer como:

```text
docker-compose-v2
```

Por ejemplo:

```bash
sudo apt update
sudo apt install docker-compose-v2
```

Luego:

```bash
docker compose version
```

> En Linux no asumir que el paquete se llama `docker-compose-plugin`. El nombre disponible depende de la fuente/repositorio utilizado.

---

# 6. Permisos de Docker en Linux

En Linux puede ser necesario agregar el usuario al grupo `docker` para poder ejecutar Docker sin `sudo`.

```bash
sudo usermod -aG docker $USER
```

Después cerrar sesión y volver a iniciar sesión.

Comprobar:

```bash
docker ps
```

Si funciona sin `sudo`, la configuración está correcta.

---

# 7. Clonar el proyecto

Clonar el repositorio:

```bash
git clone <URL_DEL_REPOSITORIO>
```

Entrar al proyecto:

```bash
cd pilates-saas
```

---

# 8. Verificar Java

Desde la raíz del proyecto:

```bash
java -version
```

Debe utilizar Java 21.

También:

```bash
javac -version
```

Y:

```bash
echo $JAVA_HOME
```

En Windows PowerShell:

```powershell
echo $env:JAVA_HOME
```

---

# 9. Maven Wrapper

El proyecto incluye Maven Wrapper.

En Linux/macOS:

```bash
./mvnw -version
```

En Windows:

```powershell
.\mvnw.cmd -version
```

Debería aparecer una versión de Maven y Java 21.

No es necesario ejecutar:

```bash
mvn install
```

ni instalar Maven globalmente.

---

# 10. PostgreSQL mediante Docker

El proyecto utiliza PostgreSQL 17.

La configuración de Docker Compose es:

```yaml
services:

  postgres:
    image: postgres:17
    container_name: pilates-saas-postgres
    restart: unless-stopped

    environment:
      POSTGRES_DB: pilates_saas
      POSTGRES_USER: pilates
      POSTGRES_PASSWORD: pilates_dev_password

    ports:
      - "5432:5432"

    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

El archivo debe estar ubicado en la raíz:

```text
docker-compose.yml
```

---

# 11. Iniciar PostgreSQL

Desde la raíz del proyecto:

```bash
docker compose up -d
```

Verificar los contenedores:

```bash
docker compose ps
```

Debería aparecer:

```text
pilates-saas-postgres
```

También se puede comprobar:

```bash
docker ps
```

---

# 12. Detener PostgreSQL

Para detener el contenedor:

```bash
docker compose stop
```

Para detenerlo y eliminar el contenedor:

```bash
docker compose down
```

El volumen de PostgreSQL se conserva.

Por lo tanto, los datos no se pierden simplemente ejecutando:

```bash
docker compose down
```

---

# 13. Eliminar completamente la base de datos de desarrollo

⚠️ **CUIDADO**

Este comando elimina también el volumen con los datos:

```bash
docker compose down -v
```

Esto significa que PostgreSQL volverá a crearse desde cero la próxima vez que ejecutemos:

```bash
docker compose up -d
```

Utilizarlo solamente cuando realmente queramos borrar la base de datos de desarrollo.

---

# 14. Configuración de Spring Boot

La configuración principal se encuentra en:

```text
src/main/resources/application.yaml
```

La aplicación debe conectarse al PostgreSQL ejecutado mediante Docker.

Configuración de desarrollo esperada:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/pilates_saas
    username: pilates
    password: pilates_dev_password

  jpa:
    hibernate:
      ddl-auto: update

    open-in-view: false

  thymeleaf:
    check-template-location: true

server:
  port: 8080

app:
  timezone: America/Montevideo
```

> Si el `application.yaml` actual del proyecto tiene una configuración equivalente, conservarla.

> Las credenciales mostradas son únicamente para desarrollo local. No deben utilizarse como credenciales de producción.

---

# 15. Ejecutar la aplicación

Primero iniciar PostgreSQL:

```bash
docker compose up -d
```

Después ejecutar Spring Boot.

## Linux / macOS

```bash
./mvnw spring-boot:run
```

## Windows

```powershell
.\mvnw.cmd spring-boot:run
```

Si todo está correcto, Spring Boot debería iniciar Tomcat en:

```text
http://localhost:8080
```

---

# 16. Ejecutar los tests

## Linux / macOS

```bash
./mvnw clean test
```

## Windows

```powershell
.\mvnw.cmd clean test
```

La compilación debe finalizar correctamente.

---

# 17. Ejecutar el proyecto desde un IDE

También se puede ejecutar directamente desde IntelliJ IDEA, Eclipse o VS Code.

La clase principal es:

```text
src/main/java/
└── uy/com/emptyloop/pilatesaas/
    └── PilatesSaasApplication.java
```

Ejecutar:

```java
PilatesSaasApplication
```

Antes de hacerlo, verificar que PostgreSQL esté funcionando:

```bash
docker compose ps
```

---

# 18. Estructura general del proyecto

El proyecto utiliza arquitectura organizada por funcionalidad.

Ejemplo:

```text
src/
├── main/
│   ├── java/
│   │   └── uy/com/emptyloop/pilatesaas/
│   │       ├── PilatesSaasApplication.java
│   │       │
│   │       ├── tenant/
│   │       │   ├── Tenant.java
│   │       │   ├── ITenantRepository.java
│   │       │   ├── TenantService.java
│   │       │   └── TenantController.java
│   │       │
│   │       ├── user/
│   │       ├── student/
│   │       ├── professor/
│   │       ├── payment/
│   │       ├── membership/
│   │       ├── classschedule/
│   │       ├── holiday/
│   │       ├── report/
│   │       └── security/
│   │
│   └── resources/
│       ├── application.yaml
│       ├── templates/
│       └── static/
│
└── test/
    └── java/
```

La estructura podrá crecer a medida que implementemos las funcionalidades.

---

# 19. Arquitectura multi-tenant

Cada estudio es un `Tenant`.

Ejemplo:

```text
PilatesFlow
    │
    ├── German
    │     ROLE_PROFESSOR
    │
    ├── Pedro
    │     ROLE_STUDENT
    │
    └── Elena
          ROLE_STUDENT
```

Otro estudio:

```text
Nuhara
    │
    ├── Agustina
    │     ROLE_PROFESSOR
    │
    ├── Juan
    │     ROLE_STUDENT
    │
    └── Romina
          ROLE_STUDENT
```

Un profesor solamente puede acceder a los datos de su propio tenant.

El `ADMIN` de la plataforma puede administrar todos los tenants.

---

# 20. Roles

El sistema tendrá tres roles:

```text
ROLE_ADMIN
ROLE_PROFESSOR
ROLE_STUDENT
```

### ADMIN

Administrador global de la plataforma.

Puede:

- crear profesores;
- modificar profesores;
- desactivar profesores;
- gestionar tenants;
- crear/restablecer acceso de alumnos;
- consultar alumnos;
- consultar pagos;
- consultar mensualidades;
- gestionar feriados;
- administrar información global del sistema.

### PROFESSOR

Pertenece a un único tenant.

Puede gestionar:

- alumnos;
- clases;
- horarios;
- mensualidades;
- pagos manuales;
- reportes;
- datos de su estudio.

No puede acceder a información de otros tenants.

### STUDENT

Pertenece a un único tenant.

Puede consultar:

- sus horarios;
- su mensualidad;
- sus pagos;
- su información personal.

---

# 21. Usuarios y contraseñas

Para profesores y administradores:

```text
username = email
```

Para alumnos:

```text
username = cédula
```

La contraseña inicial del alumno será su cédula.

Sin embargo:

**La contraseña nunca se almacena en texto plano.**

El backend almacenará únicamente un hash seguro.

Ejemplo conceptual:

```text
username
passwordHash
mustChangePassword
```

Al primer inicio de sesión:

```text
Cédula
   +
Cédula como contraseña temporal
        ↓
mustChangePassword = true
        ↓
Obligar a establecer nueva contraseña
        ↓
mustChangePassword = false
```

Las contraseñas no pueden ser visualizadas por profesores ni administradores.

El administrador podrá restablecer el acceso, pero tampoco podrá conocer la contraseña actual.

---

# 22. Cédula de los alumnos

La cédula uruguaya identifica de forma única a una persona.

Por lo tanto, el sistema no debe permitir registrar dos usuarios con la misma cédula.

La unicidad deberá estar garantizada también a nivel de base de datos.

La implementación definitiva de esta regla se realizará cuando construyamos el modelo de usuarios/alumnos.

---

# 23. Zona horaria

La aplicación utiliza:

```text
America/Montevideo
```

Esto es especialmente importante para:

- vencimientos;
- días hábiles;
- feriados;
- recargos;
- generación de mensualidades;
- reportes automáticos;
- fechas de pago.

No debemos asumir la zona horaria del servidor.

---

# 24. Feriados uruguayos

El sistema tendrá una tabla propia de feriados.

Conceptualmente:

```text
holidays
---------
id
date
name
country
```

Inicialmente:

```text
country = UY
```

La aplicación utilizará esta información como fuente de verdad para determinar días hábiles.

No se dependerá de una API externa para calcular vencimientos.

El `ADMIN` podrá gestionar los feriados.

---

# 25. Subdominios

Cada tenant tendrá un subdominio.

Ejemplos:

```text
pilatesflow.tuapp.com
nuhara.tuapp.com
```

El subdominio pertenece al estudio.

Ejemplo:

```text
Profesor: German
Estudio: PilatesFlow

Subdominio:
pilatesflow
```

Los alumnos de German accederán mediante:

```text
pilatesflow.tuapp.com
```

Otro ejemplo:

```text
Profesora: Agustina
Estudio: Nuhara

Subdominio:
nuhara
```

Acceso:

```text
nuhara.tuapp.com
```

La implementación de los subdominios reales queda para una etapa posterior. Durante el desarrollo local se podrá trabajar inicialmente con:

```text
localhost:8080
```

---

# 26. Flujo recomendado para comenzar a trabajar

Cada vez que se clone el proyecto en un equipo nuevo:

### 1. Clonar

```bash
git clone <URL_DEL_REPOSITORIO>
cd pilates-saas
```

### 2. Verificar Java

```bash
java -version
javac -version
```

Debe ser Java 21.

### 3. Verificar Docker

```bash
docker --version
docker compose version
```

### 4. Levantar PostgreSQL

```bash
docker compose up -d
```

### 5. Verificar PostgreSQL

```bash
docker compose ps
```

### 6. Ejecutar tests

Linux/macOS:

```bash
./mvnw clean test
```

Windows:

```powershell
.\mvnw.cmd clean test
```

### 7. Ejecutar Spring Boot

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

### 8. Abrir la aplicación

```text
http://localhost:8080
```

---

# 27. Comandos útiles de Docker

Ver contenedores:

```bash
docker ps
```

Ver todos:

```bash
docker ps -a
```

Ver servicios del proyecto:

```bash
docker compose ps
```

Iniciar servicios:

```bash
docker compose up -d
```

Detener servicios:

```bash
docker compose stop
```

Ver logs:

```bash
docker compose logs
```

Ver logs de PostgreSQL:

```bash
docker compose logs postgres
```

Seguir logs en tiempo real:

```bash
docker compose logs -f postgres
```

Eliminar contenedores:

```bash
docker compose down
```

Eliminar contenedores y volumen:

```bash
docker compose down -v
```

---

# 28. Problemas frecuentes

## `java: command not found`

Java no está instalado o no está disponible en el `PATH`.

Verificar:

```bash
which java
```

Linux/macOS:

```bash
echo $JAVA_HOME
```

Windows PowerShell:

```powershell
echo $env:JAVA_HOME
```

---

## `JAVA_HOME environment variable is not defined correctly`

Configurar `JAVA_HOME` apuntando al JDK 21.

Linux ejemplo:

```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
```

macOS:

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
```

Windows debe configurarse mediante las variables de entorno del sistema.

---

## `docker compose: command not found`

Verificar:

```bash
docker compose version
```

En Ubuntu puede ser necesario instalar:

```bash
sudo apt install docker-compose-v2
```

---

## No se puede conectar a PostgreSQL

Primero comprobar:

```bash
docker compose ps
```

Luego:

```bash
docker compose logs postgres
```

Verificar que PostgreSQL esté escuchando en:

```text
localhost:5432
```

Y que `application.yaml` tenga las mismas credenciales que `docker-compose.yml`.

---

## Puerto 5432 ocupado

Comprobar qué proceso utiliza el puerto.

Linux:

```bash
sudo lsof -i :5432
```

También:

```bash
docker ps
```

Si existe otro PostgreSQL instalado localmente, puede estar utilizando el puerto.

---

## Puerto 8080 ocupado

Comprobar:

Linux:

```bash
sudo lsof -i :8080
```

En ese caso se puede detener el proceso que está utilizando el puerto o cambiar temporalmente el puerto de Spring Boot.

---

# 29. Recomendaciones de desarrollo

No subir al repositorio:

- contraseñas reales;
- credenciales de producción;
- tokens;
- claves privadas;
- secretos de Mercado Pago;
- credenciales de WhatsApp;
- credenciales de email.

Para desarrollo local se pueden utilizar valores de prueba.

Cuando lleguemos a producción utilizaremos variables de entorno o un mecanismo específico de gestión de secretos.

---

# 30. Estado actual del proyecto

Actualmente tenemos preparada la base del proyecto:

- [x] Spring Boot 3.5.x
- [x] Java 21
- [x] Maven Wrapper
- [x] Spring Web MVC
- [x] Spring Data JPA
- [x] Spring Security
- [x] Thymeleaf
- [x] PostgreSQL
- [x] Docker
- [x] Docker Compose
- [x] Arquitectura por funcionalidad
- [x] Entidad `Tenant`
- [x] Repositorio `ITenantRepository`
- [x] Conexión Spring Boot ↔ PostgreSQL
- [x] PostgreSQL ejecutándose mediante Docker

El siguiente paso será comenzar la implementación funcional de la aplicación.

---

# 31. Objetivo del proyecto

El objetivo del MVP es construir una plataforma SaaS para estudios de Pilates que permita:

- administrar estudios;
- administrar profesores;
- administrar alumnos;
- gestionar clases y horarios;
- gestionar mensualidades;
- calcular prorrateos;
- controlar vencimientos;
- aplicar recargos por mora;
- registrar pagos manuales;
- integrar Mercado Pago;
- generar comprobantes;
- enviar notificaciones;
- generar reportes;
- administrar feriados uruguayos;
- mantener aislamiento entre tenants.

La aplicación se desarrollará progresivamente, priorizando primero una base sólida de dominio, persistencia, seguridad y multi-tenancy.
