package com.restapiexample.dummy.utils;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirEnJson {

    public static void Json(String texto,String Nombre) {

        String textoJson = texto;
        String nombre = Nombre;
        JSONObject jsonObject = new JSONObject(textoJson);
        String textoJsonFormateado = jsonObject.toString(4);

        try (FileWriter fileWriter = new FileWriter(nombre);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(textoJsonFormateado);

        } catch (IOException e) {
        }
    }

}
