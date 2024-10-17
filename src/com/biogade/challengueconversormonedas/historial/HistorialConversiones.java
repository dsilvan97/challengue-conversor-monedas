package com.biogade.challengueconversormonedas.historial;

import com.google.gson.*;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class HistorialConversiones {

    private static final String RUTA_ARCHIVO_JSON = "jsonscreated/HistorialConversiones.json";

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public String GuardarConversion(String monedaBase, String monedaDestino, double cantidad, double valorMonedaCambiada){

        LocalDateTime fechaActual = LocalDateTime.now();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("fechaConsulta", fechaActual.toString());
        jsonObject.addProperty("monedaBase", monedaBase);
        jsonObject.addProperty("cantidad", cantidad);
        jsonObject.addProperty("monedaDestino", monedaDestino);

        BigDecimal bd = new BigDecimal(valorMonedaCambiada);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        valorMonedaCambiada = bd.doubleValue();

        jsonObject.addProperty("valorEquivalente", valorMonedaCambiada);

        return jsonObject.toString();

    }

    public void GuardarHistorial(JsonObject historial){

        String carpeta = "jsonscreated";
        File directorio = new File(carpeta);

        if (!directorio.exists()) {
            directorio.mkdir();
        }

        String nombreArchivo = "HistorialConversiones.json";

        File archivo = new File(directorio, nombreArchivo);

        boolean archivoExistente = archivo.exists();

        JsonArray historialConversiones = new JsonArray();

        // Si el archivo existe, lee su contenido
        if (archivoExistente) {
            try (Reader reader = new FileReader(RUTA_ARCHIVO_JSON)) {
                historialConversiones = JsonParser.parseReader(reader).getAsJsonArray();

                JsonObject ultimaConversion = historialConversiones.get(historialConversiones.size() - 1).getAsJsonObject();
                Integer numeroSesion = ultimaConversion.get("sesion").getAsInt() + 1;
                historial.addProperty("sesion", numeroSesion);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Agrega la nueva conversión al historial
        historialConversiones.add(historial);

        // Guarda el historial actualizado en el archivo JSON
        try (Writer writer = new FileWriter(RUTA_ARCHIVO_JSON)) {
            gson.toJson(historialConversiones, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}