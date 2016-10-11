package structures;


import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class OpenBucketSimpleMapTest {
    SimpleMap<String, String> tested;


    @Nested
    public class WhenRetrievingValues {
        @Test
        public void aValueMappedToAKeyCanBeRetrievedByTheKey() throws Exception {
            tested = new OpenBucketSimpleMap<>();
            tested.put("key", "value");

            String results = tested.get("key");

            assertThat(results).as("retrieved value").isEqualTo("value");
        }

        @Test
        public void aValueMappedToNullCanBeRetrievedByNull() {
            tested = new OpenBucketSimpleMap<>();
            tested.put(null, "value");

            assertThat(tested.get(null)).as("retrieved value").isEqualTo("value");
        }
    }

    @Nested
    public class WhenPuttingValues {
        @Test
        public void mappingAValueToANonExistentKeyReturnsNull() {
            tested = new OpenBucketSimpleMap<>();
            String previous = tested.put("key", "value");

            assertThat(previous).as("returned value from put").isNull();
        }

        @Test
        public void mappingAValueToAPreexistingKeyReturnsThePreviouslyMappedValue() {
            tested = new OpenBucketSimpleMap<>();
            tested.put("key", "value");

            String previous = tested.put("key", "other");

            assertThat(previous).as("returned value from put").isEqualTo("value");
        }

        @Test
        public void mappingAValueToANonExistentKeyIncreasesSizeByOne() {
            tested = new OpenBucketSimpleMap<>();
            tested.put("key", "value");
            tested.put("otherKey", "otherValue");

            assertThat(tested.size()).as("Map size").isEqualTo(2);
        }

        @Test
        public void mappingAValueToAPreexistingKeyDoesNotIncrementTheSize() {
            tested = new OpenBucketSimpleMap<>();
            tested.put("key", "value");
            tested.put("key", "otherValue");

            assertThat(tested.size()).as("Map size").isEqualTo(1);
        }
    }

    @Nested
    public class WhenRemovingValues {
        @Test
        public void removedValueIsNoLongerInTheMap() {
            tested = new OpenBucketSimpleMap<>();
            tested.put("key", "value");
            tested.put("foo", "bar");

            tested.remove("key");

            assertThat(tested.get("key")).as("Removed value").isNull();
            assertThat(tested.get("foo")).as("Remaining value").isEqualTo("bar");
        }

        @Test
        public void removingAValueDecreasesTheSizeOfTheMapByOne() {
            tested = new OpenBucketSimpleMap<>();
            tested.put("key", "value");
            tested.put("foo", "bar");

            tested.remove("key");

            assertThat(tested.size()).as("Map size").isEqualTo(1);
        }

        @Test
        public void returnsNullIfNoValueIsMappedToTheProvidedKey() {
            tested = new OpenBucketSimpleMap<>();

            String removed = tested.remove("key");

            assertThat(removed).as("Removed value").isNull();
        }

        @Test
        public void returnsRemovedValueIfItWasMappedToProvidedKey() {
            tested = new OpenBucketSimpleMap<>();
            tested.put("key", "value");

            String removed = tested.remove("key");

            assertThat(removed).as("Removed value").isEqualTo("value");
        }
    }
}
