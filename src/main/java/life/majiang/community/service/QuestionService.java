package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        Integer offset = (page - 1) * size;

        List<Question> questions = questionMapper.list(offset, size);
        PaginationDTO paginationDTO = new PaginationDTO();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        if (questions != null && questions.size() != 0) {
            for (Question question : questions) {
                User user = userMapper.findById(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOS.add(questionDTO);
            }

        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }
}
