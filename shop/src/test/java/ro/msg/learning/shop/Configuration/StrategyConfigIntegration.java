package ro.msg.learning.shop.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import ro.msg.learning.shop.repository.ILocationInterfaceRepository;
import ro.msg.learning.shop.repository.IStockInterfaceRepository;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

@TestConfiguration
@RequiredArgsConstructor
@Import({LocationService.class, StockService.class})
public class StrategyConfigIntegration {
    private final ILocationInterfaceRepository locationInterfaceRepository;
    private final IStockInterfaceRepository stockInterfaceRepository;

    @Bean(name = "mostAbundantStrategy")
    @Primary
    public MostAbundantStrategy getMultipleLocationStrategy() {
        return new MostAbundantStrategy(stockInterfaceRepository);
    }

    @Bean(name = "singleLocationStrategy")
    public SingleLocationStrategy getSingleLocationStrategy() {
        return new SingleLocationStrategy(stockInterfaceRepository, locationInterfaceRepository);
    }
}
