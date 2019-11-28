package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
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
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        Integer offset = (page - 1) * size;

        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        if (questions != null && questions.size() != 0) {
            for (Question question : questions) {
                UserExample userExample = new UserExample();
                userExample.createCriteria().andIdEqualTo(question.getCreator());
                List<User> users = userMapper.selectByExample(userExample);
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(users.get(0));
                questionDTOS.add(questionDTO);
            }

        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = questionMapper.countByUserId(userId);
        paginationDTO.setPagination(totalCount, page, size);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        Integer offset = (page - 1) * size;
        if (page == 0) {
            offset = 0;
        }

        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        if (questions != null && questions.size() != 0) {
            for (Question question : questions) {
                UserExample userExample = new UserExample();
                userExample.createCriteria().andIdEqualTo(question.getCreator());
                List<User> users = userMapper.selectByExample(userExample);
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(users.get(0));
                questionDTOS.add(questionDTO);
            }

        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.getById(id);
//        User user = userMapper.findById(question.getCreator());
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdEqualTo(question.getCreator());
        List<User> users = userMapper.selectByExample(userExample);
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(users.get(0));
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        } else {
            //¸üÐÂ
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }

    }
}
