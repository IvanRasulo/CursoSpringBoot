# Exercises SpringBoot

## Ejercicio 1
Crea un proyecto SpringBoot web.
- Lleva los componentes proyecto MyShoppingCart al proyecto spring boot. 
	- O bien modifica el proyecto original MyShoppingCart para que sea un proyecto web.
- Modifica las propiedades para usar archivos yaml
- Genera dos perfiles: dev y prod
- El perfil dev debe usar el puerto 8080 y debe conectarse a una base de datos H2. 
	- Debe precargar el conjunto de datos de desarrollo.
	- Debe mostrar los logs en nivel debug y los sql
- El perfil prod deber usar el puerto 8443 y debe conectarse a una base da datos Mysql. 
	- No debe cargar datos
	- Debe mostrar los logs en nivel error
- Añade un endpoint "usuarios" que siempre devuelva el texto "usuarios" para todas las peticiones.
- Añade un endpoint "productos" que siempre devuelva el texto "productos" para todas las peticiones.


## Ejercicio 2
- Para el recurso de usuarios y productos de MyShoppingCartApp define los endpoints pertinentes e impleméntalos.
- Usa @JsonIgnore para los campos que no se quieran mapear.

## Ejercicio 3
- Para el proyecto, añade la dependencia de swagger y las anotaciones necesarias para generar la documentación apropiada.
- Añade comunicaciones seguras para perfil prod

## Ejercicio 4
- Para el proyecto Products Service: 
	- Genera una configuración de testing que de "barra libre" para los endpoints de tipo GET.
	- Verifica que los tests de GET pasen.
	- Deshabilita la configuración de seguridad de testing anterior y generar una configuración en testing con usuarios en memoria.
	- Verifica que puedes obtener el token para un usuario dado.