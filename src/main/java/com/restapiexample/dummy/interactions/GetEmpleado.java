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

public class GetEmpleado implements Interaction {

    Excel datos = new Excel();
    public static String respuesta;

    @Override
    public <T extends Actor> void performAs(T actor) {

        int codigoRespuesta = 0;

        while (codigoRespuesta != 200) {
            try {
                respuesta = SerenityRest.given().baseUri(datos.leerExcel("Apis", "Data.xlsx", 2, 1)+PostEmpleado.id)
                        .header("Content-Type", "text/plain")
                        .header("Accept","*/*").get().getBody().asString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            codigoRespuesta= Integer.parseInt(String.valueOf(SerenityRest.lastResponse().getStatusCode()));

          }
        System.out.println("Dato del empleado es: " + respuesta);
        String respuestaJson = SerenityRest.lastResponse().getBody().asString();
        EscribirEnJson.Json(respuestaJson,"resultados/GetEmpleado.json");

    }

    public static Performable on(){
        return Instrumented.instanceOf(GetEmpleado.class).withProperties();
    }
}
