package es.raulminon.powermock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

// Specific runner
@RunWith(PowerMockRunner.class)
//Initialize static class
@PrepareForTest(UtilityClass.class)
public class MockingStaticMethodTest {
	
	
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	Dependency dependecy;
	
	@InjectMocks
	SystemUnderTest systemUnderTest;
	
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		List <Integer> stats = Arrays.asList(1,2,3);
		when(dependecy.retrieveAllStats()).thenReturn(stats);
		//mock
		PowerMockito.mockStatic(UtilityClass.class);
		when(UtilityClass.staticMethod(6)).thenReturn(150);
		int result = systemUnderTest.methodCallingAStaticMethod();
		assertEquals(150, result);
		PowerMockito.verifyStatic();
		UtilityClass.staticMethod(5);
	}
}
