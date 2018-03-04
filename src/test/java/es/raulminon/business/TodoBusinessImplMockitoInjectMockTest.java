package es.raulminon.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import es.raulminon.data.api.TodoService;


public class TodoBusinessImplMockitoInjectMockTest {
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	TodoService todoServiceMock;
	
	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;
	
	// Declare an argument capture
	@Captor
	ArgumentCaptor<String> stringArgumentCapture;
	
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		List <String> todos = Arrays.asList(
				"Learn Spring MVC", "Learn Spring", "Learn to Dance"
				);
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
	
		List <String> filteredTodos = 
				todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2, filteredTodos.size());
		
	}
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD() {
		
		List <String> todos = Arrays.asList(
				"Learn Spring MVC", "Learn Spring", "Learn to Dance"
				);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
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
		
		List <String> todos = Arrays.asList(
				"Learn Spring MVC", "Learn Spring", "Learn to Dance"
				);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
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
		
		
		List <String> todos = Arrays.asList(
				"Learn Spring MVC", "Learn to Rock and roll", "Learn to Dance"
				);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//Then
		// To ensure that a method is call
		// Define argument capture on specific method call
		// Capture the argument
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCapture.capture());
		assertThat(stringArgumentCapture.getAllValues().size(), is(2));
	}
}
