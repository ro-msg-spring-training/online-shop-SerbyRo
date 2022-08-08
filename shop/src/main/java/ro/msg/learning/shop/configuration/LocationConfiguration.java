package ro.msg.learning.shop.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.ILocationInterfaceRepository;
import ro.msg.learning.shop.repository.IStockInterfaceRepository;
import ro.msg.learning.shop.repository.LocationStrategy;
import ro.msg.learning.shop.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

@Configuration
@RequiredArgsConstructor
public class LocationConfiguration {
    @Value("${strategy}")
    private LocationEnum strategyMode;

    private final IStockInterfaceRepository stockRepository;
    private final ILocationInterfaceRepository locationRepository;
    @Bean
    public LocationStrategy locationStrategy()
    {
        return switch (strategyMode){
            case SINGLE -> new SingleLocationStrategy(stockRepository, locationRepository);
            case MOSTABUNDANT -> new MostAbundantStrategy(stockRepository);
        };
    }
}


