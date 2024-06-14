package org.wowyomad.questionaire.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.dto.QuestionCreateDTO;
import org.wowyomad.questionaire.dto.QuestionResponseDTO;
import org.wowyomad.questionaire.dto.OptionCreateDTO;
import org.wowyomad.questionaire.dto.OptionResponseDTO;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.model.Option;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    @Autowired
    private OptionMapper optionMapper;

    public QuestionResponseDTO toResponseDTO(Question question) {
        if (question == null) {
            return null;
        }

        QuestionResponseDTO dto = new QuestionResponseDTO();
        dto.setId(question.getId());
        dto.setLabel(question.getLabel());
        dto.setType(question.getType());
        dto.setRequired(question.isRequired());
        dto.setActive(question.isActive());
        dto.setOptions(question.getOptions() != null ?
                question.getOptions().stream().map(optionMapper::toResponseDTO).collect(Collectors.toList()) : null);

        return dto;
    }

    public Question fromCreateDTO(QuestionCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        Question question = new Question();
        question.setLabel(dto.getLabel());
        question.setType(dto.getType());
        question.setRequired(dto.isRequired());
        question.setActive(dto.isActive());

        if (dto.getOptions() != null) {
            List<Option> options = dto.getOptions().stream().map(optionMapper::fromCreateDTO).collect(Collectors.toList());
            question.setOptions(options);
        }

        return question;
    }
}
