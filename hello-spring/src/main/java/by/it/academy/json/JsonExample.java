package by.it.academy.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class JsonExample {

    private static final Logger log = LoggerFactory.getLogger(JsonExample.class);

    @SneakyThrows
    public static void main(String[] args) {
        ObjectMapper objectMapper =
                new ObjectMapper();
        StringWriter writer = new StringWriter();
        objectMapper.writeValue(writer, createA());
        log.info(writer.toString());

        String json = writer.toString();
        final A a = objectMapper.readValue(json, A.class);
        log.info(a.toString());
    }

    private static A createA() {
        C c1 = C.builder().name("name1").value("value1").build();
        C c2 = C.builder().name("name2").value("value2").build();
        C c3 = C.builder().name("name3").value("value3").build();

        B b1 = B.builder().cList(List.of(c1, c2)).build();

        return A.builder().b(b1).c(c3).build();
    }

}
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class A {
    private B b;
    private C c;

}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class B {
    private List<C> cList = new ArrayList<>();

}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class C {
    private String name;
    private String value;

}
