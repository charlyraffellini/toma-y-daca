[Master](http://toma-y-daca.appspot.com/): [![Build Status](https://travis-ci.org/charlyraffellini/toma-y-daca.svg?branch=master)](https://travis-ci.org/charlyraffellini/toma-y-daca)


[Staging](http://staging-toma-y-daca.appspot.com/): [![Build Status](https://travis-ci.org/charlyraffellini/toma-y-daca.svg?branch=staging)](https://travis-ci.org/charlyraffellini/toma-y-daca)

# Este es el TP de los pibes del grupo 3 de TACS del segundo cuatrimestre de 2014.

##### Voy a contar un poco lo que está en el pom.xml pero por ahí no es tan obvio.

### Entorno de Desarrollo

La aplicación se levanta con `mvn appengine:devserver -Pdevserver` en el entorno de desarrollo.
El **profile devserver** appendea al **GAE plugin** los **jvmFlags** para que se levante el servidor de debug escuchando el puerto 8000.
Como les escupirá la consola al aplicación escucha el puerto 8080.

##### Links de interes:
- [Configurar idea para debuggear nuestro código](http://stackoverflow.com/questions/18684618/how-can-i-debug-a-java-google-app-engine-app-in-idea-while-using-the-gae-maven-p) tener en cuenta que la parte de los jvmFlags ya están puestos en el **profile devserver**.
- [El issue que me salvó](http://code.google.com/p/maven-gae-plugin/issues/detail?id=42) explica lo del las properties **jvmFlag** de la configuración del plugin de appengine.
- [Documentación oficial del plugin para maven appengine](https://developers.google.com/appengine/docs/java/tools/maven?hl=es)

### Entorno de Producción

Para _deployar_ hay que correr `mvn appengine:update -Pupdate`. Pero esto lo debe hacer el servidor de CI.



##### Prueba:


Muy al margen dejo la [prueba de concepto](https://github.com/charlyraffellini/pruebadeconceptoNinja) que se hizo antes de empezar este proyecto.
