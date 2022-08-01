package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.LocationStrategy;
import ro.msg.learning.shop.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

@Configuration
public class LocationConfiguration {
    @Value("${strategy}")
    private LocationEnum strategyMode;
    @Bean
    public LocationStrategy locationStrategy()
    {
        return switch (strategyMode){
            case SINGLE -> new SingleLocationStrategy();
            case MOSTABUNDANT -> new MostAbundantStrategy();
        };
    }
}


