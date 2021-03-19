# HotelApp

Esta aplicacion hace un login de usuarios con firebase, para ello utiliza los scripts de AuthActivity y HomeActivity.

### La versión completa está en la rama develop!!!

# En qué fijarme...
Lo interesante está dentro de app/src/main. 

Fundamentalmente tienes 2 cosas:

1/ Los layout (directorio app/src/main/res/layout), en donde encuentras el diseño de las pantallas (archivos .xml).

2/ "la inteligencia" (directorio app\src\main\java\com\example\hotel), en este caso en lenguaje kotlin (archivos .kt). 

En el Auth hacemos el registro y el login, mientras que en el home solo mostramos la informacion de usuario y tenemos un boton para salir de ahi.

Merece la pena pararse a descubrir cómo en el código (archivos .kt) hay referencias a los elementos del layout (botones, cuadros de texto, etc).


