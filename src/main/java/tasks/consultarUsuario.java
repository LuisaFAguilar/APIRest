package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.questions.RestQueryFunction;

import java.util.List;


public class consultarUsuario implements Task {

    int id;
    public consultarUsuario(int id){
        this.id = id;

    }

    @Override
    public <T extends Actor> void performAs(T actor) {
            actor.attemptsTo(
                    Get.resource("/user/{id}").with(
                            requestList -> requestList.header("token",123).pathParam("id",id)
                    )
            );
    }

    public static consultarUsuario consultarUsuario(int id){
        return Tasks.instrumented(consultarUsuario.class, id);
    }
}
