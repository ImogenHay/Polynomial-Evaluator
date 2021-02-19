package com.idbs.devassessment.solution;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author imogenhay
 * Tests functionality of ReadNumeric class
 */

public class ReadJsonTest {

	@Test // tests assertTrue is working
	public void shouldAnswerWithTrue() { 
		assertTrue( true );
	}
	
		
	@Test // tests assertEquals is working
	public void shouldBeEqual() {
		assertEquals("true" ,"true");
	}
	
	
	@Test // tests ReadJson can be created and evaluated
	public void testReadJson() {
		ReadJson reader = new ReadJson("{\"xValue\" : 7,\"terms\" : [{ \"power\" : 0,  \"multiplier\" : 1, \"action\" :  \"add\"  },{ \"power\" : 2,  \"multiplier\" : 2, \"action\" :  \"subtract\"  },{ \"power\" : 3,  \"multiplier\" : 3, \"action\" :  \"add\"  },{ \"power\" : 1,  \"multiplier\" : 1, \"action\" :  \"subtract\"  }]}");
		assertEquals("925" ,reader.getResult());
	}
	
	
	@Test
	public void testGetXValue() {
		ReadJson reader = new ReadJson("{\"xValue\" : 7,\"terms\" : [{ \"power\" : 0,  \"multiplier\" : 1, \"action\" :  \"add\"  },{ \"power\" : 2,  \"multiplier\" : 2, \"action\" :  \"subtract\"  },{ \"power\" : 3,  \"multiplier\" : 3, \"action\" :  \"add\"  },{ \"power\" : 1,  \"multiplier\" : 1, \"action\" :  \"subtract\"  }]}");
		assertEquals(7 ,reader.getXValue());
	}
	
	
	@Test
	public void testGetData() {
		ReadJson reader = new ReadJson("{\"xValue\" : 7,\"terms\" : [{ \"power\" : 0,  \"multiplier\" : 1, \"action\" :  \"add\"  },{ \"power\" : 2,  \"multiplier\" : 2, \"action\" :  \"subtract\"  },{ \"power\" : 3,  \"multiplier\" : 3, \"action\" :  \"add\"  },{ \"power\" : 1,  \"multiplier\" : 1, \"action\" :  \"subtract\"  }]}");
		assertEquals("{\"xValue\" : 7,\"terms\" : [{ \"power\" : 0,  \"multiplier\" : 1, \"action\" :  \"add\"  },{ \"power\" : 2,  \"multiplier\" : 2, \"action\" :  \"subtract\"  },{ \"power\" : 3,  \"multiplier\" : 3, \"action\" :  \"add\"  },{ \"power\" : 1,  \"multiplier\" : 1, \"action\" :  \"subtract\"  }]}" ,reader.getData());
	}
	
	
	@Test
	public void testGetEquation() {
		ReadJson reader = new ReadJson("{\"xValue\" : 7,\"terms\" : [{ \"power\" : 0,  \"multiplier\" : 1, \"action\" :  \"add\"  },{ \"power\" : 2,  \"multiplier\" : 2, \"action\" :  \"subtract\"  },{ \"power\" : 3,  \"multiplier\" : 3, \"action\" :  \"add\"  },{ \"power\" : 1,  \"multiplier\" : 1, \"action\" :  \"subtract\"  }]}");
		reader.getResult();
		
		assertEquals("+ 1x^(0) - 2x^(2) + 3x^(3) - 1x^(1) \n" ,reader.getEquation().toString());
	}
	
	
	@Test
	public void testToString() {
		ReadJson reader = new ReadJson("{\"xValue\" : 7,\"terms\" : [{ \"power\" : 0,  \"multiplier\" : 1, \"action\" :  \"add\"  },{ \"power\" : 2,  \"multiplier\" : 2, \"action\" :  \"subtract\"  },{ \"power\" : 3,  \"multiplier\" : 3, \"action\" :  \"add\"  },{ \"power\" : 1,  \"multiplier\" : 1, \"action\" :  \"subtract\"  }]}");
		reader.getResult();
		assertEquals("+ 1x^(0) - 2x^(2) + 3x^(3) - 1x^(1) \n = 925 (x=7)\n" ,reader.toString());
	}
	
	
	// test if invalid json format with throw error
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidReadJson() {
		ReadJson reader = new ReadJson("invalid input");
	}
	

	

}
