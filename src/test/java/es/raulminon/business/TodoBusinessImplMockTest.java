package es.raulminon.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

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
		then(todoServiceMock).should().deleteTodo("Learn to Dance");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring");
		verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
		verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");
		verify(todoServiceMock, never()).deleteTodo("Learn Spring");
	}
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {
		// Declare an argument capture
		ArgumentCaptor<String> stringArgumentCapture = ArgumentCaptor.forClass(String.class);
		
		
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
		// Define argument capture on specific method call
		// Capture the argument
		then(todoServiceMock).should().deleteTodo(stringArgumentCapture.capture());
		assertThat(stringArgumentCapture.getValue(), is("Learn to Dance"));
	}
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCaptureMultipleTimes() {
		// Declare an argument capture
		ArgumentCaptor<String> stringArgumentCapture = ArgumentCaptor.forClass(String.class);
		
		
		// Given -- Setup
		TodoService todoServiceMock = mock(TodoService.class);
		List <String> todos = Arrays.asList(
				"Learn Spring MVC", "Learn to Rock and roll", "Learn to Dance"
				);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = 
				new TodoBusinessImpl(todoServiceMock);
		
		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//Then
		// To ensure that a method is call
		// Define argument capture on specific method call
		// Capture the argument
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCapture.capture());
		assertThat(stringArgumentCapture.getAllValues().size(), is(2));
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
