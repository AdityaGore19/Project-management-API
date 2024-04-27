package com.example.project_management_api.Controller;

import com.example.project_management_api.model.Project_Model;
import com.example.project_management_api.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Project_Controller {

    @Autowired
    private ProjectRepo projectRepo;

    @GetMapping("/getAllProjects")
    public ResponseEntity<List<Project_Model>> getAllProjects(){
        try {
            List<Project_Model> projects = new ArrayList<>();
            projectRepo.findAll().forEach(projects::add);

            if(projects.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(projects, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getProjectById/{Id}")
    public ResponseEntity<Project_Model> getProjectById(@PathVariable Long Id){
        Optional<Project_Model> project = projectRepo.findById(Id);

        if(project.isPresent()){
            return new ResponseEntity<>(project.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("addProject")
    public ResponseEntity<Project_Model> addProject(@RequestBody Project_Model projectModel){
        Project_Model projectModel1 = projectRepo.save(projectModel);

        return new ResponseEntity<>(projectModel1, HttpStatus.OK);
    }

    @PostMapping("/updateProjectById/{Id}")
    public ResponseEntity<Project_Model> updteProjectById(@PathVariable Long Id, @RequestBody Project_Model newProject){
        Optional<Project_Model> oldprojectdata = projectRepo.findById(Id);

        if(oldprojectdata.isPresent()){
            Project_Model updatedProject = oldprojectdata.get();

            updatedProject.setName(newProject.getName());
            updatedProject.setDescription(newProject.getDescription());
            updatedProject.setStartDate(newProject.getStartDate());
            updatedProject.setEndDate(newProject.getEndDate());

            Project_Model projectObject = projectRepo.save(updatedProject);
            return new ResponseEntity<>(projectObject,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteProjectById/{Id}")
    public ResponseEntity<HttpStatus> deleteProjectById(@PathVariable Long Id){
        projectRepo.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
