Master: [![Build Status](https://travis-ci.org/charlyraffellini/toma-y-daca.svg?branch=master)](https://travis-ci.org/charlyraffellini/toma-y-daca)

Staging: [![Build Status](https://travis-ci.org/charlyraffellini/toma-y-daca.svg?branch=staging)](https://travis-ci.org/charlyraffellini/toma-y-daca)

# Este es el TP de los pibes del grupo 3 de TACS del segundo cuatrimestre de 2014.

##### Voy a contar un poco lo que está en el pom.xml pero por ahí no es tan obvio.

### Entorno de Desarrollo

La aplicación se levanta con `mvn appengine:devserver -Pdevserver` en el entorno de desarrollo.
El **profile devserver** appendea al **GAE plugin** los **jvmFlags** para que se levante el servidor de debug escuchando el puerto 8000.
Como les escupirá la consola al aplicación escucha el puerto 8080.

### Entorno de Producción

Para _deployar_ hay que correr `mvn appengine:update -Pupdate`. Pero esto lo debe hacer el servidor de CI.