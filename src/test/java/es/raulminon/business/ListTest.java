package es.raulminon.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void letsMockListSizeMethod() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
	}
	@Test
	public void letsMockListSize_ReturnMultipleValues() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3);
		assertEquals(2, listMock.size());
		assertEquals(3, listMock.size());
	}
	
	@Test
	public void letsMockListGet() {
		List listMock = mock(List.class);
		// Argument matcher
		when(listMock.get(anyInt())).thenReturn("in28Minutes");
		assertEquals("in28Minutes", listMock.get(0));
		assertEquals("in28Minutes", listMock.get(1));
	}
	@Test
	public void letsMockListGet_usingBDD() {
		//Given
		List <String>listMock = mock(List.class);
		// Argument matcher
		given(listMock.get(anyInt())).willReturn("in28Minutes");
		
		//Then
		String firstElement = listMock.get(0);
		//When
		assertThat(firstElement, is("in28Minutes"));
	}
	@Test(expected= RuntimeException.class)
	public void letsMockList_ThrownAnException() {
		List listMock = mock(List.class);
		// Argument matcher
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
		assertEquals("in28Minutes", listMock.get(1));
		listMock.get(0);
	}
	

}
