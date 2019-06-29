package br.edu.ufcg.ccc.andersonjoao.projeto.rest.controller;

import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.Comment;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.Subject;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.User;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.service.CommentService;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.service.SubjectService;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.service.UserService;
import br.edu.ufcg.ccc.andersonjoao.projeto.utils.UserData;
import io.swagger.annotations.ApiOperation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;

@RestController
@RequestMapping("/subjectsProfile")
public class ProfileController {

    @Autowired
    private CommentService commentsService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;

    @ApiOperation(value="Pega o profile de uma disciplina com id dela")
    @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectResponse> findBySubject(HttpServletRequest request, @PathVariable Long subjectId, @RequestBody CommentRequest comment) {
        String userEmail = UserData.getUserEmail(request);
//        Comment newComment = new Comment();
        String content = , String authorId, long subjectId, long answerTo)

//        SubjectResponse response = new SubjectResponse(id, name, usersLiked, comments);
//        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Adiciona um comentario a disciplina")
    @PostMapping("/{subjectId}/comment")
    public ResponseEntity<SubjectResponse> addCommentToProfile(HttpServletRequest request, @PathVariable Long subjectId) {
        String userEmail = UserData.getUserEmail(request);
        ArrayList<CommentsWithFlag> comments = getSubjectComments(userEmail, subjectId);

        Subject subject = subjectService.findById(subjectId);
    }

    private ArrayList<CommentsWithFlag> getSubjectComments(String email, Long subjectId) {
        ArrayList resp = new ArrayList();
        for (Comment comm : commentsService.findBySubject(subjectId)) {
            User user = userService.findByEmail(comm.getAuthorEmail());
            resp.add(new CommentsWithFlag(comm.getId(), comm.getContent(), user.getFirstName() + " " + user.getLastName(), comm.getAnswerTo(), comm.getAuthorEmail().equals(email)));
        }
        return resp;
    }

    @Data
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

    @Data
    private class SubjectResponse {
        private long id;

        private String name;
        private HashSet<String> usersLiked = new HashSet<>();

        private ArrayList<CommentsWithFlag> comments;

        public SubjectResponse(long id, String name, HashSet<String> usersLiked, ArrayList<CommentsWithFlag> comments) {
            this.id = id;
            this.name = name;
            this.usersLiked = usersLiked == null ? new HashSet<>() : usersLiked;
            this.comments = comments == null ? new ArrayList<>() : comments;
        }
    }

    @Data
    private class CommentRequest {
        private String content;

        public CommentRequest() {
        }

        public CommentRequest(String content) {
            this.content = content;
        }
    }
}
