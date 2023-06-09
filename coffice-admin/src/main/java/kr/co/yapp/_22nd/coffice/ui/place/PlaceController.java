package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.application.PlaceApplicationService;
import kr.co.yapp._22nd.coffice.domain.GeoCodingService;
import kr.co.yapp._22nd.coffice.domain.place.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequestMapping("/place")
@Controller
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceApplicationService placeApplicationService;
    private final GeoCodingService geoCodingService;

    @GetMapping
    public String list(
            @PageableDefault Pageable pageable,
            Model model
    ) {
        Page<Place> placePage = placeApplicationService.findAll(pageable);
        model.addAttribute("placePage", placePage);
        return "place/list";
    }

    @GetMapping("/{placeId}")
    public String detail(
            @PathVariable Long placeId,
            Model model
    ) {
        model.addAttribute("place", placeApplicationService.getPlace(placeId));
        return "place/detail";
    }

    @GetMapping("/add")
    public String addForm(
            Model model
    ) {
        model.addAttribute("dayTimeTypes", DayTimeType.values());
        model.addAttribute("weekDayTypes", WeekDayType.values());
        List<CrowdednessLevel> crowdednessLevels = Arrays.stream(CrowdednessLevel.values())
                .filter(it -> it != CrowdednessLevel.UNKNOWN)
                .toList();
        model.addAttribute("crowdednessLevels", crowdednessLevels);
        return "place/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute PlaceAddRequest placeAddRequest,
            Model model
    ) {
        Place place = placeApplicationService.create(
                PlaceCreateVo.of(
                        placeAddRequest.getName(),
                        resolveCoordinates(placeAddRequest),
                        Address.builder()
                                .streetAddress(placeAddRequest.getStreetAddress())
                                .landAddress(placeAddRequest.getLandAddress())
                                .postalCode(placeAddRequest.getPostalCode())
                                .build(),
                        Collections.emptyList(),
                        PhoneNumber.from(placeAddRequest.getPhoneNumber()),
                        placeAddRequest.getHomepageUrl(),
                        ElectricOutletCount.from(placeAddRequest.getElectricOutletCount()),
                        SeatCount.from(placeAddRequest.getSeatCount()),
                        TableCount.from(placeAddRequest.getTableCount()),
                        CommunalTableCount.from(placeAddRequest.getCommunalTableCount()),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList()
                )
        );
        model.addAttribute("place", place);
        return "redirect:/place/" + place.getPlaceId();
    }

    private Coordinates resolveCoordinates(PlaceAddRequest placeAddRequest) {
        if (placeAddRequest.getLatitude() != null
                && placeAddRequest.getLongitude() != null) {
            return Coordinates.of(
                    placeAddRequest.getLatitude(),
                    placeAddRequest.getLongitude()
            );
        }
        String address = placeAddRequest.getStreetAddress() != null
                ? placeAddRequest.getStreetAddress()
                : placeAddRequest.getLandAddress();
        return geoCodingService.getCoordinates(address);
    }

    @GetMapping("/{placeId}/edit")
    public String editForm(
            @PathVariable Long placeId,
            Model model
    ) {
        model.addAttribute("place", placeApplicationService.getPlace(placeId));
        return "place/edit";
    }

    @PostMapping("/{placeId}/edit")
    public String edit(
            @PathVariable Long placeId,
            @ModelAttribute PlaceEditRequest placeEditRequest,
            Model model
    ) {
        Place place = placeApplicationService.update(
                placeId,
                PlaceUpdateVo.of(
                        placeEditRequest.getName(),
                        Coordinates.of(
                                placeEditRequest.getLatitude(),
                                placeEditRequest.getLongitude()
                        ),
                        Address.builder()
                                .streetAddress(placeEditRequest.getStreetAddress())
                                .landAddress(placeEditRequest.getLandAddress())
                                .postalCode(placeEditRequest.getPostalCode())
                                .build(),
                        Collections.emptyList(),
                        PhoneNumber.from(placeEditRequest.getPhoneNumber()),
                        placeEditRequest.getHomepageUrl(),
                        ElectricOutletCount.from(placeEditRequest.getElectricOutletCount()),
                        SeatCount.from(placeEditRequest.getSeatCount()),
                        TableCount.from(placeEditRequest.getTableCount()),
                        CommunalTableCount.from(placeEditRequest.getCommunalTableCount()),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList()
                )
        );
        model.addAttribute("place", place);
        return "redirect:/place/" + placeId;
    }

}
