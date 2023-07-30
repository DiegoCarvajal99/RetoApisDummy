package com.restapiexample.dummy.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ValidateCodigoRespuesta implements Question<Boolean> {

    private String codigoEsperado;

    public ValidateCodigoRespuesta(String codigoEsperado) {

        this.codigoEsperado = codigoEsperado;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return String.valueOf(SerenityRest.lastResponse().statusCode()).equals(codigoEsperado);
    }

    public static ValidateCodigoRespuesta on(String codigoEsperado){
        return new ValidateCodigoRespuesta(codigoEsperado);
    }

}
