package com.restapiexample.dummy.interactions;


import com.restapiexample.dummy.utils.EscribirEnJson;
import com.restapiexample.dummy.utils.Excel;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import org.json.JSONObject;

import java.io.IOException;


public class PostEmpleado implements Interaction {

    public static int id;
    Excel datos = new Excel();
    public static String respuesta;

    @Override
    public <T extends Actor> void performAs(T actor) {

        int codigoRespuesta = 0;
        JSONObject json;

        String body = null;
        try {
            body = "{\"name\":\""+datos.leerExcel("Datos","Data.xlsx",1,0)+"\",\"salary\":\""+datos.leerExcel("Datos","Data.xlsx",1,1)+"\",\"age\":\""+datos.leerExcel("Datos","Data.xlsx",1,2)+"\"}";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (codigoRespuesta != 200) {
            try {
                respuesta = SerenityRest.given().baseUri(datos.leerExcel("Apis", "Data.xlsx", 1, 1))
                        .header("Content-Type", "text/plain")
                        .header("Accept","*/*").body(body).post().getBody().asString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            codigoRespuesta= Integer.parseInt(String.valueOf(SerenityRest.lastResponse().getStatusCode()));

            JSONObject jsonObject = new JSONObject(respuesta);
            json = jsonObject.getJSONObject("data");
            id = json.getInt("id");
        }
        System.out.println("Dato creado es: " + respuesta);
        String respuestaJson = SerenityRest.lastResponse().getBody().asString();
        EscribirEnJson.Json(respuestaJson,"resultados/PostEmpleado.json");

    }

    public static Performable on(){
        return Instrumented.instanceOf(PostEmpleado.class).withProperties();
    }
}
