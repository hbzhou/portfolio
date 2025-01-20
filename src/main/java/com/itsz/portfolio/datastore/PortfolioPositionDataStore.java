package com.itsz.portfolio.datastore;

import com.itsz.portfolio.entity.Security;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

@Component
public final class PortfolioPositionDataStore implements InitializingBean {
    private static final String CSV_DELIMITER = ",";
    private Map<String, PortfolioPosition> portfolioPositions;

    @Value("classpath:portfolio_position.csv")
    private Resource resource;


    @Override
    public void afterPropertiesSet() throws Exception {
        portfolioPositions = load();
    }

    private Map<String, PortfolioPosition> load() {
        try (Stream<String> rawDataStream = Files.lines(Paths.get(resource.getURI()))) {
            return rawDataStream.skip(1).map(PortfolioPositionDataStore::construct)
                    .collect(Collectors.toMap(PortfolioPosition::getIdentifier, Function.identity()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Predicate<Security> subscribed() {
        return security -> portfolioPositions.containsKey(security.getIdentifier());
    }

    public PortfolioPosition getPosition(String identifier) {
        return portfolioPositions.get(identifier);
    }

    public static PortfolioPosition construct(String row) {
        String[] columns = row.split(CSV_DELIMITER);
        return new PortfolioPosition(columns[0], Long.parseLong(columns[1]));
    }
}
