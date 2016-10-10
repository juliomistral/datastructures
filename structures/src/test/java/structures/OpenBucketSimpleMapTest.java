package structures;


import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OpenBucketSimpleMapTest {
    SimpleMap<String, String> tested;


    @Test
    public void aValueMappedToAKeyCanBeRetrievedByTheKey() throws Exception {
        tested = new OpenBucketSimpleMap<>();
        tested.put("key", "value");

        String results = tested.get("key");

        assertThat(results).isEqualTo("value");
    }

    @Test
    public void aValueMappedToNullCanBeRetriegvedByNull() {
        tested = new OpenBucketSimpleMap<>();
        tested.put(null, "value");

        assertThat(tested.get(null)).isEqualTo("value");
    }
}
