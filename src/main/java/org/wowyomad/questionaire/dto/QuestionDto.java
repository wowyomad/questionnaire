package org.wowyomad.questionaire.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class QuestionDto {
    @Nullable
    private Integer id;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    private String label;

    @Nullable
    private String text;


    @JsonProperty("required")
    @JsonAlias({"required", "isRequired"})
    private boolean isRequired;

    @JsonProperty("active")
    @JsonAlias({"active", "isActive"})
    private boolean isActive;

    @Valid
    @Nullable
    private List<OptionDTO> options;

}
