package br.edu.ufcg.ccc.andersonjoao.projeto.rest.controller;

import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.Comment;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.User;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.service.CommentService;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.service.UserService;
import br.edu.ufcg.ccc.andersonjoao.projeto.utils.UserData;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentService commentsService;

    @Autowired
    private UserService userService;

    @ApiOperation(value="Pega comentários da disciplina com id subjectId")
    @GetMapping("/find/{subjectId}")
    public ArrayList<CommentsWithFlag> findBySubject(HttpServletRequest request, @PathVariable Long subjectId) {
        ArrayList resp = new ArrayList();
        String userEmail = UserData.getUserEmail(request);
        for (Comment comm : commentsService.findBySubject(subjectId)) {
            User user = userService.findByEmail(comm.getAuthorEmail());
            resp.add(new CommentsWithFlag(comm.getId(), comm.getContent(), user.getFirstName() + " " + user.getLastName(), comm.getAnswerTo(), comm.getAuthorEmail().equals(userEmail)));
        }
        return resp;
    }

    @ApiOperation(value="Pega comentário por id")
    @GetMapping("/byId/{id}")
    public Comment findByid(@PathVariable long id) {
        return commentsService.findById(id);
    }

    private class CommentsWithFlag {
        private Long id;
        private String content;
        private String authorName;
        private Long answerTo;
        private boolean itsMine;

        public CommentsWithFlag(Long id, String content, String authorName, Long answerTo, boolean itsMine) {
            this.id = id;
            this.content = content;
            this.authorName = authorName;
            this.answerTo = answerTo;
            this.itsMine = itsMine;
        }
    }
}
