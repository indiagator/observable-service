package com.cbt.observableservice1;


import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
class MyConfiguration {
    // To have the @Observed support we need to register this aspect
    @Bean
    ObservedAspect observedAspect(ObservationRegistry observationRegistry) {


        // Create an ObservationRegistry
        ObservationRegistry registry = ObservationRegistry.create();
// Register an ObservationHandler
        registry.observationConfig().observationHandler(new MyHandler());

// Create an Observation and observe your code!
        Observation.createNotStarted("user.name", registry)
                .contextualName("getting-user-name")
                .lowCardinalityKeyValue("userType", "userType1") // let's assume that you can have 3 user types
                .highCardinalityKeyValue("userId", "1234") // let's assume that this is an arbitrary number
                .observe(() -> {
                    Logger log = LoggerFactory.getLogger(MyController.class);
                    log.info("Hello");
                }); // this is a shortcut for starting an observation, opening a scope, running user's code, closing the scope and stopping the observation

        return new ObservedAspect(observationRegistry);
    }
}