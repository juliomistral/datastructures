package structures;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;


public class ResizableArraySimpleListTest {
    private ResizableArraySimpleList<Integer> resizableArraySimpleList;

    @Nested
    @DisplayName("When retrieving elements")
    public class WhenRetrievingElements {
        @Test
        public void removingElementByIndexShiftsRemainingElementsTogether() {
            resizableArraySimpleList = new ResizableArraySimpleList<Integer>();
            resizableArraySimpleList.add(10);
            resizableArraySimpleList.add(20);
            resizableArraySimpleList.add(30);
            resizableArraySimpleList.add(40);

            resizableArraySimpleList.remove(2);
            resizableArraySimpleList.remove(0);

            assertThat(resizableArraySimpleList.get(0)).isEqualTo(20);
            assertThat(resizableArraySimpleList.get(1)).isEqualTo(40);
        }

        @Test
        public void anAddedElementCanBeRetrievedByIndex() throws Exception {
            resizableArraySimpleList = new ResizableArraySimpleList<Integer>();
            resizableArraySimpleList.add(10);

            assertThat(resizableArraySimpleList.get(0)).isEqualTo(10);
        }

        @Test
        public void elementsAreRetrievedInThOrderTheyWereAdded() {
            resizableArraySimpleList = new ResizableArraySimpleList<>();
            resizableArraySimpleList.add(10);
            resizableArraySimpleList.add(20);
            resizableArraySimpleList.add(30);
            resizableArraySimpleList.add(40);

            assertThat(resizableArraySimpleList.get(0)).isEqualTo(10);
            assertThat(resizableArraySimpleList.get(1)).isEqualTo(20);
            assertThat(resizableArraySimpleList.get(2)).isEqualTo(30);
            assertThat(resizableArraySimpleList.get(3)).isEqualTo(40);
        }

        @Test
        public void removingAnElementByValueLeftShiftsRemainingElementsTogether() {
            resizableArraySimpleList = new ResizableArraySimpleList<Integer>();
            resizableArraySimpleList.add(10);
            resizableArraySimpleList.add(20);
            resizableArraySimpleList.add(30);
            resizableArraySimpleList.add(40);

            resizableArraySimpleList.remove((Integer)10);
            resizableArraySimpleList.remove((Integer)30);

            assertThat(resizableArraySimpleList.get(0)).isEqualTo(20);
            assertThat(resizableArraySimpleList.get(1)).isEqualTo(40);
        }

        @Test
        public void iteratorReturnsAllElementsInTheList() {
            resizableArraySimpleList = new ResizableArraySimpleList<Integer>();
            resizableArraySimpleList.add(10);
            resizableArraySimpleList.add(20);
            resizableArraySimpleList.add(30);
            resizableArraySimpleList.add(40);

            Iterator<Integer> iterator = resizableArraySimpleList.iterator();

            assertThat(iterator.next()).isEqualTo(10);
            assertThat(iterator.next()).isEqualTo(20);
            assertThat(iterator.next()).isEqualTo(30);
            assertThat(iterator.next()).isEqualTo(40);

            assertThatThrownBy(() -> {
                iterator.next();
            }).isInstanceOf(NoSuchElementException.class);
        }
    }
}
