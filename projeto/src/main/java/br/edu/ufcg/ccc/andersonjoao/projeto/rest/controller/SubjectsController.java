package br.edu.ufcg.ccc.andersonjoao.projeto.rest.controller;

import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.Subject;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectsController {

    @Autowired
    private SubjectService subjectService;

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

    @Data
    private class SubjectsFindResponse {
        private Long id;
        private String name;

        public SubjectsFindResponse(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
