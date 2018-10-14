package net.rim99.demo.account.startup.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.rim99.demo.account.startup.config.impl.YamlConfigReader;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ConfigReaderTest {

    @Test
    void should_create_bean_from_yaml_file() {
        String testFile = "config/ConfigReaderTestConfig.yml";
        ConfigReader reader = new YamlConfigReader();
        TestBean testBean = reader.readFrom(testFile, TestBean.class);

        assertThat(testBean.stringItem, is("hello"));
        assertThat(testBean.numbers, hasSize(3));
        assertThat(testBean.numbers.get(2), is(10));
        assertThat(testBean.map, hasKey("key"));
    }

    static class TestBean extends Config {
        @JsonProperty
        String stringItem;
        @JsonProperty
        List<Integer> numbers;
        @JsonProperty
        Map<String, Object> map;

        public TestBean() {
        }

        public String getStringItem() {
            return stringItem;
        }

        public List<Integer> getNumbers() {
            return numbers;
        }

        public Map<String, Object> getMap() {
            return map;
        }

        public void setStringItem(String stringItem) {
            this.stringItem = stringItem;
        }

        public void setNumbers(List<Integer> numbers) {
            this.numbers = numbers;
        }

        public void setMap(Map<String, Object> map) {
            this.map = map;
        }
    }
}
