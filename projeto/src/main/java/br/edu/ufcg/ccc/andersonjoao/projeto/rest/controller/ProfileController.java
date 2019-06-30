package br.edu.ufcg.ccc.andersonjoao.projeto.rest.controller;

import br.edu.ufcg.ccc.andersonjoao.projeto.exception.InvalidDataException;
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

        if (userService.findByEmail(userEmail) == null) {
            throw new InvalidDataException("O usuário que está sendo autenticado não existe");
        }

        Subject subject = subjectService.findById(subjectId);

        if (subject == null) {
            throw new InvalidDataException("Disciplina buscada não existe");
        }

        ArrayList<CommentsWithFlag> comments = getSubjectComments(userEmail, subjectId);

        Set<String> usersLiked = subject.getUsersLiked();
        SubjectResponse response = new SubjectResponse(subjectId, subject.getName(), usersLiked.size(), comments);
        if (usersLiked.contains(userEmail)) {
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


        if (comment.getAnswerTo() != null) {
            Comment answerTo = commentsService.findById(comment.getAnswerTo());
            if (answerTo == null || !answerTo.getSubjectId().equals(subjectId)) {
                throw new InvalidDataException("Comentário que está sendo respondido não existe na disciplina especificada");
            }
        }

        if (subjectId == null || subjectService.findById(subjectId) == null) {
            throw new InvalidDataException("Disciplina que está sendo comentada não existe");
        }

        if (comment.getContent() == null || comment.getContent().length() > 150) {
            throw new InvalidDataException("Comentário inválido ou maior que 150 caracteres");
        }

        String content = comment.getContent();

        Comment newComment = new Comment(content, userEmail, subjectId, comment.getAnswerTo());
        commentsService.save(newComment);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }

    @DeleteMapping("/{subjectId}/{commentId}")
    public void deleteComment(HttpServletRequest request,
                              @PathVariable("subjectId") Long subjectId,
                              @PathVariable("commentId") Long commentId) {
        String userEmail = UserData.getUserEmail(request);

        Comment comment = commentsService.findById(commentId);

        if (commentId == null || comment == null) {
            throw new InvalidDataException("O comentário que está sendo removido não existe");
        }

        if (subjectId == null || subjectService.findById(subjectId) == null || !comment.getSubjectId().equals(subjectId)) {
            throw new InvalidDataException("A disciplina não condiz com o comentário a ser deletado");
        }

        if (!comment.getAuthorEmail().equals(userEmail)) {
            throw new InvalidDataException("Não está autorizado a remover este comentário");
        }

        comment.setActive(false);

        commentsService.save(comment);
    }

    @ApiOperation(value = "Da like na disciplina")
    @PutMapping("/{subjectId}/like")
    public void like(HttpServletRequest request, @PathVariable Long subjectId) {
        String userEmail = UserData.getUserEmail(request);
        Subject subject = subjectService.findById(subjectId);

        if (subjectId == null || subjectService.findById(subjectId) == null) {
            throw new InvalidDataException("Disciplina que está recebendo like não existe");
        }

        Set<String> likes = subject.getUsersLiked();
        likes.add(userEmail);
        subject.setUsersLiked(likes);

        subjectService.update(subject);
    }

    @ApiOperation(value = "Tira like na disciplina")
    @PutMapping("/{subjectId}/removeLike")
    public void removeLike(HttpServletRequest request, @PathVariable Long subjectId) {
        String userEmail = UserData.getUserEmail(request);
        Subject subject = subjectService.findById(subjectId);

        if (subject == null) {
            throw new InvalidDataException("Disciplina cujo like deve ser removido não existe");
        }

        Set<String> likes = subject.getUsersLiked();

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

            resp.add(new CommentsWithFlag(comm.getId(), comm.getContent(), user.getFirstName() + " " + user.getLastName(), comm.getAnswerTo(), comm.getAuthorEmail().equals(email), comm.getActive()));
        }
        return resp;
    }

    @Data
    private static class CommentsWithFlag {
        private Long id;
        private String content;
        private String authorName;
        private Long answerTo;
        private boolean itsMine;
        private boolean active;

        public CommentsWithFlag(Long id, String content, String authorName, Long answerTo, boolean itsMine, boolean active) {
            this.id = id;
            this.content = content;
            this.authorName = authorName;
            this.answerTo = answerTo;
            this.itsMine = itsMine;
            this.active = active;
        }
    }

    @Data
    private static class SubjectResponse {
        private long id;

        private String name;
        private int  usersLiked;

        private ArrayList<CommentsWithFlag> comments;
        private boolean liked;

        public SubjectResponse(long id, String name, int usersLiked, ArrayList<CommentsWithFlag> comments) {
            this.id = id;
            this.name = name;
            this.usersLiked = usersLiked;
            this.comments = comments;
        }
    }

    @Data
    private static class CommentRequest implements Serializable {

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
