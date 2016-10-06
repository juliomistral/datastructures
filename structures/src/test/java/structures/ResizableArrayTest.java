package structures;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;


public class ResizableArrayTest {
    private ResizableArray<Integer> resizableArray;


    @Test
    public void anAddedElementCanBeRetrievedByIndex() throws Exception {
        resizableArray = new ResizableArray<Integer>();
        resizableArray.add(10);

        assertThat(resizableArray.get(0)).isEqualTo(10);
    }

    @Test
    public void elementsAreRetrievedInThOrderTheyWereAdded() {
        resizableArray = new ResizableArray<Integer>();
        resizableArray.add(10);
        resizableArray.add(20);
        resizableArray.add(30);
        resizableArray.add(40);

        assertThat(resizableArray.get(0)).isEqualTo(10);
        assertThat(resizableArray.get(1)).isEqualTo(20);
        assertThat(resizableArray.get(2)).isEqualTo(30);
        assertThat(resizableArray.get(3)).isEqualTo(40);
    }

    @Test
    public void removingAnElementShiftsRemainingElementsTogeter() {
        resizableArray = new ResizableArray<Integer>();
        resizableArray.add(10);
        resizableArray.add(20);
        resizableArray.add(30);
        resizableArray.add(40);

        resizableArray.remove(10);
        resizableArray.remove(30);

        assertThat(resizableArray.get(0)).isEqualTo(20);
        assertThat(resizableArray.get(1)).isEqualTo(40);
    }
}
