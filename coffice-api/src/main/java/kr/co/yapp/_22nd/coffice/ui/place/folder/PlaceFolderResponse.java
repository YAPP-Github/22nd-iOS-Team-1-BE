package kr.co.yapp._22nd.coffice.ui.place.folder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceFolderResponse {
    @NotNull
    private Long placeFolderId;
    @NotBlank
    private String name;
    @NotBlank
    private String color;
}
