package kr.co.yapp._22nd.coffice.ui.place;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kr.co.yapp._22nd.coffice.domain.place.*;
import lombok.Data;

import java.util.Set;

@Data
public class PlaceSearchRequest {
    private String searchText;
    @Positive
    private Double latitude;
    @Positive
    private Double longitude;
    @PositiveOrZero
    @NotNull
    private Double distance;
    /**
     * 영업중
     */
    private Boolean open;
    /**
     * 24시간
     */
    private Boolean openAroundTheClock;
    /**
     * 단체 테이블 있는지
     */
    private Boolean hasCommunalTable;
    private Set<CapacityLevel> capacityLevels;
    private Set<ElectricOutletLevel> electricOutletLevels;
    private Set<DrinkType> drinkTypes;
    private Set<FoodType> foodTypes;
    private Set<RestroomType> restroomTypes;
    @Min(1)
    @NotNull
    private Integer pageSize;
    @Positive
    private Double lastSeenDistance;
}
