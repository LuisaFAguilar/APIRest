package tasks;

import models.TokenAutorization;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class consultarToken implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/token")
                        .with(request -> request.body("{\"username\": \"jromo\",\"password\": \"jRomo12345*\"}"))
        );
        TokenAutorization token = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("data",TokenAutorization.class);
        System.out.println("token:"+token.getToken());
    }

    public static consultarToken consultarToken(){
        return Tasks.instrumented(consultarToken.class);
    }
}
