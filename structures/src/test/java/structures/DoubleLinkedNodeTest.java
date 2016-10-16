package structures;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import structures.basic.DoubleLinkedNode;

public class DoubleLinkedNodeTest {
    private DoubleLinkedNode node;


    @Test
    public void canInterateThroughElementsInTheOrderTheyWereAddedByNext() {
        node = new DoubleLinkedNode(0);
        node.addNext(1);
        node.addNext(2);

        assertThat(node.data()).isEqualTo(0);
        assertThat(node.next().data()).isEqualTo(1);
        assertThat(node.next().next().data()).isEqualTo(2);
    }

    @Test
    public void canInterateThroughElementsInTheOppositeOrderTheyWereAddedByPrevious() {
        node = new DoubleLinkedNode(0);
        node = node.addPrevious(1);
        node = node.addPrevious(2);

        assertThat(node.data()).isEqualTo(2);
        assertThat(node.next().data()).isEqualTo(1);
        assertThat(node.next().next().data()).isEqualTo(0);
    }
}
