#language:es
Característica: Servicio Rest con serenity
  Escenario: Servicio Rest de Empleados
    Dado que el usuario cree un numero resgistro
    Cuando el usuaria realiza la solicitud de los demas servicos
    Entonces se vbalida que el codigo de respuesta sea 200
