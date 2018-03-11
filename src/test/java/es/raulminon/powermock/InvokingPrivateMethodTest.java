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
import org.powermock.reflect.Whitebox;

// Specific runner
@RunWith(PowerMockRunner.class)
//Initialize static class
public class InvokingPrivateMethodTest {
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	Dependency dependecy;
	
	@InjectMocks
	SystemUnderTest systemUnderTest;
	
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() throws Exception {
		List <Integer> stats = Arrays.asList(1,2,3);
		when(dependecy.retrieveAllStats()).thenReturn(stats);
		//mock
		long result = Whitebox.invokeMethod(systemUnderTest,"privateMethodUnderTest");
		assertEquals(6, result);
	}
}
