package kr.co.yapp._22nd.coffice.domain.review;

import jakarta.annotation.Nullable;
import kr.co.yapp._22nd.coffice.domain.place.ElectricOutletLevel;
import kr.co.yapp._22nd.coffice.domain.place.NoiseLevel;
import kr.co.yapp._22nd.coffice.domain.place.WifiLevel;
import lombok.Value;

@Value(staticConstructor = "of")
public class ReviewCreateVo {
    ElectricOutletLevel electricOutletLevel;
    WifiLevel wifiLevel;
    NoiseLevel noiseLevel;
    @Nullable
    String content;
}
