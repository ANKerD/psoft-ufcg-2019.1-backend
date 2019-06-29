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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    public ResponseEntity<SubjectResponse> findBySubject(HttpServletRequest request, @PathVariable Long subjectId) {
        String userEmail = UserData.getUserEmail(request);
        ArrayList<CommentsWithFlag> comments = getSubjectComments(userEmail, subjectId);

        Subject subject = subjectService.findById(subjectId);

        long id = subject.getId();
        String name = subject.getName();
        Set<String> usersLiked = subject.getUsersLiked();

        SubjectResponse response = new SubjectResponse(id, name, usersLiked, comments);
        if (response.getUsersLiked().contains(userEmail)) {
            response.setLiked(true);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Adiciona um comentario a disciplina")
    @PostMapping("/{subjectId}/comment")
    public ResponseEntity<Comment> addCommentToProfile(HttpServletRequest request,
                                                       @PathVariable Long subjectId,
                                                       @RequestBody CommentRequest comment) {
        String userEmail = UserData.getUserEmail(request);
        ArrayList<CommentsWithFlag> comments = getSubjectComments(userEmail, subjectId);

        String content = comment.getContent();
        Long answerTo = comment.getAnswerTo();

        Comment newComment = new Comment(content, userEmail, subjectId, answerTo);
        commentsService.save(newComment);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }

    @DeleteMapping("/{subjectId}/{commentId}")
    public void deleteComment(HttpServletRequest request,
                              @PathVariable("subjectId") Long subjectId,
                              @PathVariable("commentId") Long commentId) {
        String userEmail = UserData.getUserEmail(request);
        Comment comment = commentsService.findById(commentId);
        if (!comment.getAuthorEmail().equals(userEmail)) {
            // TODO: explode
            return;
        }

        commentsService.delete(comment);
    }

    @ApiOperation(value = "Da like na disciplina")
    @PostMapping("/{subjectId}/like")
    public void like(HttpServletRequest request, @PathVariable Long subjectId) {
        String userEmail = UserData.getUserEmail(request);
        Subject subject = subjectService.findById(subjectId);

        Set<String> likes = getNotNull(subject.getUsersLiked());
        likes.add(userEmail);
        subject.setUsersLiked(likes);

        subjectService.update(subject);
    }

    @ApiOperation(value = "Tira like na disciplina")
    @DeleteMapping("/{subjectId}/like")
    public void dislike(HttpServletRequest request, @PathVariable Long subjectId) {
        String userEmail = UserData.getUserEmail(request);
        Subject subject = subjectService.findById(subjectId);

        Set<String> likes = getNotNull(subject.getUsersLiked());

        if (likes.contains(userEmail)) {
            likes.remove(userEmail);
        }

        subject.setUsersLiked(likes);
        subjectService.update(subject);
    }

    private ArrayList<CommentsWithFlag> getSubjectComments(String email, Long subjectId) {
        ArrayList resp = new ArrayList();
        for (Comment comm : commentsService.findBySubject(subjectId)) {
            User user = userService.findByEmail(comm.getAuthorEmail());
            if (!comm.getActive()) {
                comm.setContent("");
            }
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
        private Set<String> usersLiked;

        private ArrayList<CommentsWithFlag> comments;
        private boolean liked;

        public SubjectResponse(long id, String name, Set<String> usersLiked, ArrayList<CommentsWithFlag> comments) {
            this.id = id;
            this.name = name;
            this.usersLiked = usersLiked == null ? new HashSet<>() : usersLiked;
            this.comments = comments == null ? new ArrayList<>() : comments;
        }
    }

    @Data
    private class CommentRequest implements Serializable {

        private String content;
        private Long answerTo;

        public CommentRequest() {
        }

        public CommentRequest(String content) {
            this.content = content;
        }

        public CommentRequest(String content, Long answerTo) {
            this.content = content;
            this.answerTo = answerTo;
        }
    }

    private Set<String> getNotNull(Set<String> set) {
        return set == null ? new HashSet<>() : set;
    }
}
