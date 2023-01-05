package enums;

import java.math.BigDecimal;

public enum TaxiType {
    STANDART(BigDecimal.valueOf(15),BigDecimal.valueOf(70)),
    COMFORT(BigDecimal.valueOf(30),BigDecimal.valueOf(130)),
    BUSINESS(BigDecimal.valueOf(40),BigDecimal.valueOf(150));
    BigDecimal pricePerKm;
    BigDecimal priceForLanding;

    TaxiType(BigDecimal pricePerKm, BigDecimal priceForLanding) {
        this.pricePerKm = pricePerKm;
        this.priceForLanding = priceForLanding;
    }

    public BigDecimal getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(BigDecimal pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public BigDecimal getPriceForLanding() {
        return priceForLanding;
    }

    public void setPriceForLanding(BigDecimal priceForLanding) {
        this.priceForLanding = priceForLanding;
    }
}
