package es.raulminon.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import es.raulminon.business.TodoBusinessImpl;
import es.raulminon.data.api.TodoService;

public class TodoBusinessImplMockTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		TodoService todoServiceMock = mock(TodoService.class);
		
		List <String> todos = Arrays.asList(
				"Learn Spring MVC", "Learn Spring", "Learn to Dance"
				);
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		TodoBusinessImpl todoBusinessImpl = 
				new TodoBusinessImpl(todoServiceMock);
		List <String> filteredTodos = 
				todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2, filteredTodos.size());
		
	}
	
	
	@Test
	public void testRetrieveTodosRelatedToSpring_withEmptyList() {
		TodoService todoServiceMock = mock(TodoService.class);
		
		List <String> todos = Arrays.asList();
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		TodoBusinessImpl todoBusinessImpl = 
				new TodoBusinessImpl(todoServiceMock);
		List <String> filteredTodos = 
				todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(0, filteredTodos.size());
		
	}


}
