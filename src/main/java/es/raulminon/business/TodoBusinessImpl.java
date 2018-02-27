package es.raulminon.business;

import java.util.ArrayList;
import java.util.List;

import es.raulminon.data.api.TodoService;

// TodoBussinessImpl SUT (System under test)
// TodoServie dependency

public class TodoBusinessImpl {
	private TodoService todoService;

	public TodoBusinessImpl(TodoService todoService) {
		this.todoService = todoService;
	}
	
	public List<String> retrieveTodosRelatedToSpring(String user) {
		List<String> filteredTodos = new ArrayList<String>();
		List<String> todos = todoService.retrieveTodos(user);
		for (String todo : todos) {
			if(todo.contains("Spring")) {
				filteredTodos.add(todo);
			}
		}
		return filteredTodos;
	}
	
}
