import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TextBuddyTest {
	
	TextBuddy myTextBuddy = new TextBuddy();

	@Test
	public void testSortInput() {
		ArrayList<String> myList = new ArrayList<>();
		
		assertEquals(myList, myTextBuddy.sortInput(myList));
	}

}
