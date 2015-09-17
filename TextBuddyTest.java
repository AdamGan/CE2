import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TextBuddyTest {
	
	TextBuddy myTextBuddy = new TextBuddy();

	@Test
	public void testSortInput() {
		ArrayList<String> myList = new ArrayList<>();
		
		assertEquals(myList, myTextBuddy.sortInput(myList));
		myList.add("1");
		myList.add("c");
		myList.add("3");
		myList.add("e");
		
		ArrayList<String> toBeSorted = new ArrayList<>();
		myList.add("1");
		myList.add("c");
		myList.add("3");
		myList.add("e");
		
		assertFalse(myList.equals(myTextBuddy.sortInput(toBeSorted)));
		
		
	}

}
