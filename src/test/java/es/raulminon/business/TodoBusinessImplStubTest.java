package es.raulminon.business;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.raulminon.business.TodoBusinessImpl;
import es.raulminon.data.api.TodoService;
import es.raulminon.data.api.TodoServiceStub;

public class TodoBusinessImplStubTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAStub() {
		TodoService todoServiceStub = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = 
				new TodoBusinessImpl(todoServiceStub);
		List <String> filteredTodos = 
				todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2, filteredTodos.size());
		
	}

}
