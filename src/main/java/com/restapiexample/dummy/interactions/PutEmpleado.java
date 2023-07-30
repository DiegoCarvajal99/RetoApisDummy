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


public class PutEmpleado implements Interaction {

    public static int id;
    Excel datos = new Excel();
    public static String respuesta;

    @Override
    public <T extends Actor> void performAs(T actor) {

        int codigoRespuesta = 0;

        String body = null;
        try {
            body = "{\"name\":\""+datos.leerExcel("Datos","Data.xlsx",1,3)+"\",\"salary\":\""+datos.leerExcel("Datos","Data.xlsx",1,4)+"\",\"age\":\""+datos.leerExcel("Datos","Data.xlsx",1,5)+"\"}";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (codigoRespuesta != 200) {
            try {
                respuesta = SerenityRest.given().baseUri(datos.leerExcel("Apis", "Data.xlsx", 3, 1)+PostEmpleado.id)
                        .header("Content-Type", "text/plain")
                        .header("Accept","*/*").body(body).put().getBody().asString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            codigoRespuesta= Integer.parseInt(String.valueOf(SerenityRest.lastResponse().getStatusCode()));

        }
        System.out.println("Dato actualizado del empleado es: " + respuesta);
        String respuestaJson = SerenityRest.lastResponse().getBody().asString();
        EscribirEnJson.Json(respuestaJson,"resultados/PutEmpleado.json");

    }

    public static Performable on(){
        return Instrumented.instanceOf(PutEmpleado.class).withProperties();
    }
}
