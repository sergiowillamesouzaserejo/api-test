import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class APITest {

    @Test
    public void deveRetornarTarefas(){
        given()
                .log().all()
        .when()
                .get("http://localhost:8001/tasks-backend/todo")
        .then()
                .statusCode(200);


    }

    @Test
    public void deveAddTarefaComSucesso(){
        given()
                .body("{\"task\":\"teste123\",\"dueDate\":\"2024-11-05\"}")
                .contentType(ContentType.JSON)
        .when()
                .post("http://localhost:8001/tasks-backend/todo")
        .then()
                .statusCode(201);
    }

    @Test
    public void naoDeveAddTarefaInvalida(){
        given()
                .body("{\"task\":\"teste123\",\"dueDate\":\"2023-11-05\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("http://localhost:8001/tasks-backend/todo")
                .then()
                .statusCode(400)
                .body("message", CoreMatchers.is("Due date must not be in past"));
    }
}
