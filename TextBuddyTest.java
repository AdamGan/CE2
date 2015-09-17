import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

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
		toBeSorted.add("1");
		toBeSorted.add("c");
		toBeSorted.add("3");
		toBeSorted.add("e");
		
		assertFalse(myList.equals(myTextBuddy.sortInput(toBeSorted)));
		
		Collections.sort(myList);
		assertEquals(myList, myTextBuddy.sortInput(toBeSorted));
		
	}
	
	public void testSearch() {
		ArrayList<String> myList = new ArrayList<>();
		assertTrue(myTextBuddy.search(myList, "present") == 0);
		
	}
	

}
