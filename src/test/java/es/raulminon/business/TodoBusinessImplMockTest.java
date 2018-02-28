package es.raulminon.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

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
	public void testDeleteTodosNotRelatedToSpring_usingBDD() {
		
		// Given -- Setup
		TodoService todoServiceMock = mock(TodoService.class);
		List <String> todos = Arrays.asList(
				"Learn Spring MVC", "Learn Spring", "Learn to Dance"
				);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = 
				new TodoBusinessImpl(todoServiceMock);
		
		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

		//Then
		// To ensure that a method is call
		verify(todoServiceMock).deleteTodo("Learn to Dance");
		verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
		verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");
		verify(todoServiceMock, never()).deleteTodo("Learn Spring");
	}
	
	
//	@Test
//	public void testRetrieveTodosRelatedToSpring_withEmptyList() {
//		TodoService todoServiceMock = mock(TodoService.class);
//
//		List<String> todos = Arrays.asList();
//		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
//		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
//		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
//		assertEquals(0, filteredTodos.size());
//
//	}


}
