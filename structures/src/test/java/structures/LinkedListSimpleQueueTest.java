package structures;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LinkedListSimpleQueueTest {
    private SimpleQueue<Integer> queue;


    @Nested
    public class WhenQueueIsEmpty {
        @Test
        public void callToEmptyShouldReturnTrue() {
            queue = new LinkedListSimpleQueue<>();
            assertThat(queue.empty()).as("Is empty").isTrue();
        }

        @Test
        public void callToDequeueProducesAnException() {
            queue = new LinkedListSimpleQueue<>();
            assertThatThrownBy(() -> {
                queue.dequeue();
            }).isInstanceOf(NoSuchElementException.class);
        }
    }

    @Nested
    public class WhenEnqueueingElements {
        @Test
        public void queueIsNoLongerEmptyAfterAnEnqueue() {
            queue = new LinkedListSimpleQueue<>();
            queue.enqueue(10);

            assertThat(queue.empty()).isFalse();
        }
    }

    @Nested
    public class WhenDequeuingElements {
        @Test
        public void canDequeueElementsInTheOrderTheyWereEnqueued() {
            queue = new LinkedListSimpleQueue<>();
            queue.enqueue(10);
            queue.enqueue(20);
            queue.enqueue(30);

            assertThat(queue.dequeue()).isEqualTo(10);
            assertThat(queue.dequeue()).isEqualTo(20);
            assertThat(queue.dequeue()).isEqualTo(30);
        }

        @Test
        public void queueIsEmptyIfAllElementsAreDequeued() {
            queue = new LinkedListSimpleQueue<>();
            queue.enqueue(10);
            queue.enqueue(20);
            queue.enqueue(30);

            queue.dequeue();
            queue.dequeue();
            queue.dequeue();

            assertThat(queue.empty()).isTrue();
        }
    }
}
