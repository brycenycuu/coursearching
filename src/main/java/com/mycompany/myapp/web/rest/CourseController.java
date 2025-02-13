package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Course;
import com.mycompany.myapp.domain.UserCourse;
import com.mycompany.myapp.domain.dto.CourseDto;
import com.mycompany.myapp.domain.dto.CourseWithTNDto;
import com.mycompany.myapp.service.CourseService;
import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping
@Api(value="Course Service Controller", description = "Controller for find couses information")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping(path = "/api/course/findAllCourses", produces = "application/json")
    public HttpEntity<List<CourseDto>> findAllCourses(){
        List<CourseDto> allCourses = courseService.findAllCourses();
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @GetMapping(path = "/api/course/findAllCoursesLengthLargerThan10", produces = "application/json")
    public HttpEntity<List<CourseDto>> findAllCoursesLengthLargerThan10() {

        List<CourseDto> allCourses = courseService.findAllCoursesLengthLargerThan10();

        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @GetMapping(path = "/api/course/findAllCoursesDto", produces = "application/json")
    public HttpEntity<List<CourseDto>> findAllCoursesDto(){
        List<CourseDto> allCourses = courseService.findAllCoursesDtoFromDB();

        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @GetMapping(path = "/api/course/findAllCoursesWithTNDto/{userId}", produces = "application/json")
    public HttpEntity<List<CourseWithTNDto>> findAllCoursesWithTNDto(@PathVariable Long userId){
        List<CourseWithTNDto> allCourses = courseService.findAllRegisteredCourses(userId);

        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @PostMapping(path = "/api/course/registerCourse/{courseName}", produces = "application/json")
    public HttpStatus registerCourse(@PathVariable String courseName) {
        try {
            courseService.registerCourse(courseName);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }
    }

    @PostMapping(path = "/api/course/addCourse", produces = "application/json")
    public HttpStatus addCourse(@RequestBody @NotNull CourseDto course) {
        try {
            courseService.addCourse(course);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PutMapping(path = "/api/course/updateCourse", produces = "application/json")
    public HttpStatus updateCourse(@RequestBody @NotNull CourseDto course) {
        try {
            courseService.updateCourse(course);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PostMapping(path = "/api/course/createCourse", produces = "application/json")
    public HttpStatus createCourse(@RequestBody @NotNull CourseDto course) {
        try {
            courseService.addCourse(course);
            return HttpStatus.OK;
        } catch (Exception e) {
            System.out.print(String.valueOf(e));
            return HttpStatus.BAD_REQUEST;
        }
    }

    @DeleteMapping(path = "/api/course/deleteCourse/{courseName}", produces = "application/json")
    public HttpStatus deleteCourse(@NotNull @PathVariable("courseName") String courseName) {
        System.out.print(courseName);
        try {
            courseService.deleteCourse(courseName);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

//    @PostMapping(path = "/api/course/addCourseToStudent/{courseName}", produces = "application/js")
//    public HttpStatus addCourseToStudent(@NotNull @PathVariable("courseName") UserCourse userCourse) {
//        try {
//            courseService.addCourseToStudent(userCourse);
//            return HttpStatus.OK;
//        } catch (Exception e) {
//            return HttpStatus.BAD_REQUEST;
//        }
//    }

    @PostMapping(path = "/api/course/addCourseToStudent", produces = "application/json")
    public HttpStatus addCourseToStudent(@RequestBody @NotNull String courseName) {
        try {
            courseService.registerCourse(courseName);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
