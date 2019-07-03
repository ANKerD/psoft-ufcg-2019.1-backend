package br.edu.ufcg.ccc.andersonjoao.projeto.rest.controller;

import br.edu.ufcg.ccc.andersonjoao.projeto.exception.InvalidDataException;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.Subject;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.service.CommentService;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectsController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CommentService commentService;

    @ApiOperation(value="Pega todas as disciplinas")
    @GetMapping("/find")
    public List<SubjectsFindResponse> allSubjects() {
        return this.findSubject("");
    }

    @ApiOperation(value="Pega disciplinas que possuem substr como substring")
    @GetMapping("/find/{substr}")
    public ArrayList<SubjectsFindResponse> findSubject(@PathVariable String substr) {
        ArrayList resp = new ArrayList();
        for (Subject subj : subjectService.findBySubstring(substr))
            resp.add(new SubjectsFindResponse(subj.getId(), subj.getName()));

        return resp;
    }

    @ApiOperation(value="Pega disciplinas que possuem substr como substring")
    @GetMapping("/ranking")
    public ArrayList<SubjectsRankingResponse> findOrdered(@RequestParam(name="type", required=true) String type, @RequestParam(name="desc", required=true) boolean desc) {
        ArrayList resp = new ArrayList();
        for (Subject subj : subjectService.findBySubstring("")) {
            Integer comments = commentService.findBySubject(subj.getId()).size();
            Integer likes = subj.getUsersLiked().size();
            resp.add(new SubjectsRankingResponse(subj.getId(), subj.getName(), likes, comments));
        }

        Comparator comp;
        if (type.equals("likes")) {
            comp = new LikesComparator();
        } else if (type.equals("comments")) {
            comp = new CommentsComparator();
        } else if (type.equals("commentsPlusLikes")){
            comp = new CommentsPlusLikesComparator();
        } else {
            throw new InvalidDataException("Método de comparação inexistente");
        }
        if (desc) {
            comp = comp.reversed();
        }
        Collections.sort(resp, comp);

        return resp;
    }

    @Data
    private class SubjectsFindResponse {
        private Long id;
        private String name;

        public SubjectsFindResponse(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Data
    private class SubjectsRankingResponse {
        private Long id;
        private String name;
        private Integer likes;
        private Integer comments;

        public SubjectsRankingResponse(Long id, String name, Integer likes, Integer comments) {
            this.id = id;
            this.name = name;
            this.likes = likes;
            this.comments = comments;
        }
    }

    public class LikesComparator implements Comparator<SubjectsRankingResponse> {
        public int compare(SubjectsRankingResponse subjA, SubjectsRankingResponse subjB) {
            return subjA.getLikes()-subjB.getLikes();
        }
    }

    public class CommentsComparator implements Comparator<SubjectsRankingResponse> {
        public int compare(SubjectsRankingResponse subjA, SubjectsRankingResponse subjB) {
            return subjA.getComments()-subjB.getComments();
        }
    }

    public class CommentsPlusLikesComparator implements Comparator<SubjectsRankingResponse> {
        public int compare(SubjectsRankingResponse subjA, SubjectsRankingResponse subjB) {
            return (subjA.getLikes()+subjA.getComments())-(subjB.getLikes()+subjB.getComments());
        }
    }
}
