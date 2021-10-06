package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;
import tasks.consultarUsuario;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.core.IsEqual.equalTo;

public class consultarUsuarioStep {
    private String theRestApiBaseUrl;
    private EnvironmentVariables environmentVariables;
    private Actor Sam;


    @Before
    public void configuracionBaseUrl() {
        theRestApiBaseUrl = environmentVariables.optionalProperty("restapi.baseurl")
                .orElse("https://reqres.in/api");

    }

    @Dado("que un consumidor quiere consultar el listado de mascotas")
    public void queUnConsumidorQuiereConsultarElListadoDeMascotas() {
        Sam = Actor.named("Consumidor").whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @Cuando("se haga el consumo")
    public void seHagaElConsumo() {
        Sam.attemptsTo(
                        consultarUsuario.consultarUsuario(1)
                );
    }

    @Entonces("Se listan las mascotas consultadas")
    public void seListanLasMascotasConsultadas() {
        Sam.should(
                seeThatResponse( "User details should be correct",
                        response -> response.statusCode(200)
                                .body("data.name",equalTo("cerulean"))
                )
        );
    }

}
