package es.raulminon.bussines;

import java.util.ArrayList;
import java.util.List;

import es.raulminon.data.api.TodoService;

// TodoBussinessImpl SUT (System under test)
// TodoServie dependency

public class TodoBussinessImpl {
	private TodoService todoService;

	public TodoBussinessImpl(TodoService todoService) {
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
		return null;
	}
	
}
