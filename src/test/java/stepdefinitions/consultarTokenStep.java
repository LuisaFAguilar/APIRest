package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;
import tasks.consultarToken;
import tasks.consultarUsuario;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.core.IsEqual.equalTo;

public class consultarTokenStep {
    private String theRestApiBaseUrl;
    private EnvironmentVariables environmentVariables;
    private Actor Sam;

    @Before
    public void configuracionBaseUrl() {
        theRestApiBaseUrl = environmentVariables.optionalProperty("restapi.baseurl")
                .orElse("https://reqres.in/api");

    }

    @Dado("que un consumidor quiere obtener el token")
    public void queUnConsumidorQuiereObtenerElToken() {
        Sam = Actor.named("Consumidor").whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @Cuando("se haga el consumo del API")
    public void seHagaElConsumoDelAPI() {
        Sam.attemptsTo(
                consultarToken.consultarToken()
        );
    }

    @Entonces("obtendra el token de autorización")
    public void obtendraElTokenDeAutorización() {
        Sam.should(
                seeThatResponse( "User details should be correct",
                        response -> response.statusCode(200)
                                .body("data",equalTo("cerulean"))
                )
        );
    }
}
