package com.s2c.lambda;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

import java.util.function.Function;

@SpringBootApplication
public class FuncAppExample implements ApplicationContextInitializer<GenericApplicationContext> {

    @Bean
    public Function<String, String> myExampleFunction() {
        return String::toLowerCase;
    }

    @Override
    public void initialize(GenericApplicationContext context) {
        context.registerBean("myExampleFunction", FunctionRegistration.class,
                () -> new FunctionRegistration<Function<String, String>>(myExampleFunction())
                        .type(FunctionType.from(String.class).to(String.class).getType()));
    }

    public static void main(String[] args) {
        FunctionalSpringApplication.run(FuncAppExample.class, args);
    }
}
