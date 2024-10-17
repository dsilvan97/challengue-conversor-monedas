package com.biogade.challengueconversormonedas.modelos;

import com.biogade.challengueconversormonedas.historial.HistorialConversiones;
import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Divisa {

    private String monedaBase;
    private String monedaDestino;
    private double valorMonedaBase;
    private double valorMonedaDestino;
    private double valorMonedaCambiada;
    private Integer cantidadConversiones = 0;
    private Integer sesionesRealizadas = 1;

    private List<String> historialConversiones = new ArrayList<>();

    HistorialConversiones historial = new HistorialConversiones();

    public void CambiarDivisa(int opcion, double cantidad) {

        //Se crea variable para uso del Gson.
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        //Se crea cliente Http.
        HttpClient client = HttpClient.newHttpClient();

        //Se crea la petición Http.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://openexchangerates.org/api/latest.json?app_id=444cc090bffb4e69bf4b53e050704357&symbols=COP%2CGBP%2CEUR%2CINR%2CBRL"))
                .build();

        //Se crea la variable que tomará la respuesta de la solicitud Http.
        HttpResponse<String> response = null;

        //Se crea try catch para verificar errores en la petición, a su vez, se agrega a la variable "response" el resultado obtenido de la petición Http.
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Se convierte a String la información obtenida por la variable "response".
        String json = response.body();

        //Se convierte el String anterior a un objeto Json.
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        //Se guarda en otro objeto Json lo obtenido de la llave "rates", la cual la encuentra en el objeto Json anterior.
        JsonObject rates = jsonObject.getAsJsonObject("rates");

        //Se crea un objeto que contenga la información de las Divisas seleccionadas en su Record.
        TomarDivisa tomarDatos = gson.fromJson(rates, TomarDivisa.class);

        //Dependiendo de la opción obtenida por el usuario se dará valor a cada una de las variables correspondientes a moneda base con su valor y moneda destino con su valor.
        switch (opcion){
            case 1:
                monedaBase = "USD";
                valorMonedaBase = 1;
                monedaDestino = "COP";
                valorMonedaDestino = tomarDatos.COP();
                break;

            case 2:
                monedaBase = "COP";
                valorMonedaBase = tomarDatos.COP();
                monedaDestino = "USD";
                valorMonedaDestino = 1;
                break;

            case 3:
                monedaBase = "USD";
                valorMonedaBase = 1;
                monedaDestino = "GBP";
                valorMonedaDestino = tomarDatos.GBP();
                break;

            case 4:
                monedaBase = "GBP";
                valorMonedaBase = tomarDatos.GBP();
                monedaDestino = "USD";
                valorMonedaDestino = 1;
                break;

            case 5:
                monedaBase = "EUR";
                valorMonedaBase = tomarDatos.EUR();
                monedaDestino = "BRL";
                valorMonedaDestino = tomarDatos.BRL();
                break;

            case 6:
                monedaBase = "BRL";
                valorMonedaBase = tomarDatos.BRL();
                monedaDestino = "EUR";
                valorMonedaDestino = tomarDatos.EUR();
                break;

            case 7:
                monedaBase = "INR";
                valorMonedaBase = tomarDatos.INR();
                monedaDestino = "COP";
                valorMonedaDestino = tomarDatos.COP();
                break;

            case 8:
                monedaBase = "COP";
                valorMonedaBase = tomarDatos.COP();
                monedaDestino = "INR";
                valorMonedaDestino = tomarDatos.INR();
                break;

            case 9:
                JsonObject jsonObject1 = new JsonObject();
                LocalDateTime  fechaActual = LocalDateTime.now();

                jsonObject1.addProperty("sesion", sesionesRealizadas);
                jsonObject1.addProperty("horaCierreSesion", fechaActual.toString());
                jsonObject1.addProperty("cantidadConversiones", cantidadConversiones);

                JsonArray conversionesArray = new JsonArray();

                for (String conversion : historialConversiones) {
                    conversionesArray.add(gson.fromJson(conversion, JsonObject.class));
                }

                jsonObject1.add("conversionesRealizadas", conversionesArray);

                historial.GuardarHistorial(jsonObject1);
                sesionesRealizadas++;

                System.out.println("Cerrando aplicativo...");
                break;

            default:
                System.out.println("Opción inválida...");
                break;
        }

        if (String.valueOf(opcion).matches("[1-8]")) {
            valorMonedaCambiada = (cantidad * valorMonedaDestino)/valorMonedaBase;

            System.out.println("======>" + cantidad + " " + monedaBase + " equivale a " + String.format("%.2f",valorMonedaCambiada) + " " + monedaDestino);

            historialConversiones.add(historial.GuardarConversion(monedaBase, monedaDestino, cantidad, valorMonedaCambiada));

            cantidadConversiones++;
        }



    }

}
