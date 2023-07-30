package com.restapiexample.dummy.stepDefinitions;


import com.restapiexample.dummy.interactions.*;
import com.restapiexample.dummy.questions.ValidateCodigoRespuesta;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class EmpleadoRestStepDefinition {

    @Before
    public void before(){
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("^que el usuario cree un numero resgistro$")
    public void queElUsuarioCreeUnNumeroResgistro() {
        OnStage.theActor("actor").attemptsTo(PostEmpleado.on());
    }

    @Cuando("^el usuaria realiza la solicitud de los demas servicos$")
    public void elUsuariaRealizaLaSolicitudDeLosDemasServicos() {
        OnStage.theActor("actor").attemptsTo(
                GetEmpleado.on(),
                PutEmpleado.on(),
                GetTodosEmpleados.on(),
                DeleteEmpleado.on()
        );
    }

    @Entonces("^se vbalida que el codigo de respuesta sea (\\d+)$")
    public void seVbalidaQueElCodigoDeRespuestaSea(String codigoRespuesta) {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidateCodigoRespuesta.on(codigoRespuesta)));

    }
}
