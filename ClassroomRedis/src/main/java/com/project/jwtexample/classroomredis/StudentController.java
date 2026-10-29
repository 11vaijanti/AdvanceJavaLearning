package com.project.jwtexample.classroomredis;
import com.project.jwtexample.classroomredis.StudentRedisService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRedisService studentRedisService;

    public StudentController(StudentRedisService studentRedisService) {
        this.studentRedisService = studentRedisService;
    }

    @PostMapping("/{id}")
    public String saveStudent(
            @PathVariable String id,
            @RequestParam String name) {

        studentRedisService.saveStudent(id, name);
        return "Student saved in Redis";
    }

    @GetMapping("/{id}")
    public String getStudent(@PathVariable String id) {
        String name = studentRedisService.getStudent(id);

        if (name == null) {
            return "Student not found or key expired";
        }

        return name;
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentRedisService.deleteStudent(id);
        return "Delete request completed";
    }
}

