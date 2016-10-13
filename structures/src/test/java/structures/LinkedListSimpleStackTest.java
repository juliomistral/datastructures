package structures;


import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class LinkedListSimpleStackTest {
    SimpleStack<Integer> stack;


    @Nested
    public class WhenStackIsEmpty {
        @Test
        public void callToEmptyShouldReturnTrue() {
            stack = new LinkedListSimpleStack<>();
            assertThat(stack.empty()).as("Is empty").isTrue();
        }

        @Test
        public void popingThrowsANoSuchElementException() {
            stack = new LinkedListSimpleStack<>();
            assertThatThrownBy(() -> {
                stack.pop();
            }).isInstanceOf(NoSuchElementException.class);
        }
    }

    @Nested
    public class WhenPushingElementsOntoTheStack {
        @Test
        public void stackIsNoLongerEmptyAfterAPush() throws Exception {
            stack = new LinkedListSimpleStack<>();
            stack.push(10);

            assertThat(stack.empty()).isFalse();
        }
    }

    @Nested
    public class WhenPoppingOrPeeking {
        @Test
        public void poppingElementsReturnsThemInTheReverseOrderThatTheyWerePushed() {
            stack = new LinkedListSimpleStack<>();
            stack.push(10);
            stack.push(20);
            stack.push(30);

            assertThat(stack.pop()).as("First popped value").isEqualTo(30);
            assertThat(stack.pop()).as("Second popped value").isEqualTo(20);
            assertThat(stack.pop()).as("Third popped value").isEqualTo(10);
        }

        @Test
        public void peekingKeepsTopElementAtTop() {
            stack = new LinkedListSimpleStack<>();
            stack.push(10);

            Integer result = stack.peek();

            assertThat(result).as("Peeked value").isEqualTo(10);
            assertThat(stack.empty()).as("Is stack empty").isFalse();
            assertThat(stack.pop()).as("Popped value").isEqualTo(10);
        }
    }
}
