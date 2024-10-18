<h1 align="center"> CHALLENGUE CONVERSOR DE MONEDAS </h1>

<p align="center">
  <img src="https://github.com/user-attachments/assets/d695032d-c2f0-48f2-b5a9-618fea4b015d" alt="ImagenLogoConversorMonedas">
</p>

<h2>Bienvenidos al Conversor de Moneda...</h2>

Este proyecto es un programa para realizar conversiones entre diferentes divisas utilizando tasas de cambio actualizadas de una API en línea. Además, el programa guarda un historial detallado de las conversiones y sesiones realizadas, proporcionando una manera sencilla de rastrear el uso del programa a lo largo del tiempo.

<h3>Funcionalidades</h3>

<strong>Conversión de divisas</strong>: Permite seleccionar una opción de cambio de divisa y la cantidad para realizar conversiones en tiempo real utilizando datos de tasas de cambio actualizadas desde una API.

![Menu](https://github.com/user-attachments/assets/e72080d0-1b38-4fc0-b31d-2b8b2bd1df2b)

<strong>Historial de conversiones:</strong> Guarda un archivo JSON con los detalles de cada conversión, incluyendo:<br><br>
*Moneda base y cantidad.<br>
*Moneda de destino y el valor equivalente.<br>
*Hora y fecha de la conversión.<br><br>

![image](https://github.com/user-attachments/assets/ca44c252-1ffe-46ee-b08a-012c4aea42a5)

<strong>Gestión de sesiones:</strong> Almacena el historial de cada sesión, incluyendo:<br><br>
*La cantidad total de conversiones realizadas en la sesión.<br>
*La hora en que se inició y cerró la sesión.<br><br>

![image](https://github.com/user-attachments/assets/d8c156eb-3b55-4688-9166-148980604446)

<h3>Requisitos</h3>
Java (JDK 8 o superior).<br>
Gson para manejar archivos JSON.<br>
Una cuenta y acceso a la API de Open Exchange Rates para obtener las tasas de cambio actualizadas.
