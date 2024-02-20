package com.example.todo;

import com.example.todo.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoApplicationTests {
	@Autowired
	private WebTestClient webTestClient;
	private String endpointTodos = "/todos";
	@Test
	void testeCreateTodoSuccess() {
		var todo = new Todo("Daily", "Daily time infra", 1, "01/01/2024", "02/01/2024", false);

		webTestClient
				.post()
				.uri(endpointTodos)
				.bodyValue(todo)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].nome").isEqualTo(todo.getNome())
				.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade())
				.jsonPath("$[0].dataCriacao").isEqualTo(todo.getDataCriacao())
				.jsonPath("$[0].dataFinalizacao").isEqualTo(todo.getDataFinalizacao())
				.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado());
	}

	@Test
	void testeCreateTodoFailure() {
		webTestClient
				.post()
				.uri(endpointTodos)
				.bodyValue(new Todo("", "", 1, "", "", false))
				.exchange()
				.expectStatus().isBadRequest();
	}

}
