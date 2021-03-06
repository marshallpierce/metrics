package io.dropwizard.metrics.graphite;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class GraphiteSanitizeTest {
    @Test
    public void sanitizeGraphiteValues() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(GraphiteSanitize.sanitize("Foo Bar", '-')).isEqualTo("Foo-Bar");
        softly.assertThat(GraphiteSanitize.sanitize(" Foo Bar ", '-')).isEqualTo("Foo-Bar");
        softly.assertThat(GraphiteSanitize.sanitize(" Foo Bar", '-')).isEqualTo("Foo-Bar");
        softly.assertThat(GraphiteSanitize.sanitize("Foo Bar ", '-')).isEqualTo("Foo-Bar");
        softly.assertThat(GraphiteSanitize.sanitize("  Foo Bar  ", '-')).isEqualTo("Foo-Bar");
        softly.assertThat(GraphiteSanitize.sanitize("Foo@Bar", '-')).isEqualTo("Foo-Bar");
        softly.assertThat(GraphiteSanitize.sanitize("Foó Bar", '-')).isEqualTo("Fo-Bar");
        softly.assertThat(GraphiteSanitize.sanitize("||ó/.", '-')).isEqualTo("");
        softly.assertThat(GraphiteSanitize.sanitize("${Foo:Bar:baz}", '-')).isEqualTo("Foo-Bar-baz");
        softly.assertThat(GraphiteSanitize.sanitize("St. Foo's of Bar", '-')).isEqualTo("St-Foo-s-of-Bar");
        softly.assertThat(GraphiteSanitize.sanitize("(Foo and (Bar and (Baz)))", '-')).isEqualTo("Foo-and-Bar-and-Baz");
        softly.assertThat(GraphiteSanitize.sanitize("Foo.bar.baz", '-')).isEqualTo("Foo-bar-baz");
        softly.assertThat(GraphiteSanitize.sanitize("FooBar", '-')).isEqualTo("FooBar");

        softly.assertAll();
    }
}
