package org.wowyomad.questionaire.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wowyomad.questionaire.utils.enums.QuestionType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Label shouldn't be blank")
    @NotNull(message = "Label shouldn't be null")
    private String label;

    @Nullable
    private String text;

    @Enumerated(EnumType.STRING)
    @NotNull
    private QuestionType type;

    @Column(name = "is_required")
    private boolean isRequired;

    @Column(name = "is_active")
    private boolean isActive;

    @Nullable
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Option> options = new ArrayList<>();

    public void setOptions(List<Option> newOptions) {
        if(newOptions == null) {
            options = null;
            return;
        }

        for (Option option : newOptions) {
            option.setQuestion(this);
        }
        options = newOptions;
    }

    public void addOption(Option option) {
        if (options == null) {
            options = new ArrayList<>();
        }
        int newIndex = options.size();
        option.setIndex(newIndex);
        options.add(option);
    }
}
