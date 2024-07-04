package com.example.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    // service for test
    @InjectMocks
    private  StudentService studentService;
    //declare discrepancies
    @Mock
    private StudentRepository repository;
    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }
    @Test
    public void should_successfully_save_a_student(){
        //given
        StudentDto dto = new StudentDto(
                "john",
                "doe",
                "john@gmail.com",
                1
        );
        Student student = new Student(
                "john",
                "doe",
                "john@gmail.com",
                20
        );
        Student savedStudent = new Student(
                "john",
                "doe",
                "john@gmail.com",
                20
        );
        savedStudent.setId(3);

        //Mock the calls
        when(studentMapper.toStudent(dto))
                .thenReturn(student);
        when(repository.save(student))
                .thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto(
                        "john",
                        "doe",
                        "john@gmail.com"
                ));

        //when
        StudentResponseDto responseDto = studentService.saveStudent(dto);


        //then
        assertEquals(dto.firstName(), responseDto.firstName());
        assertEquals(dto.lastName(), responseDto.lastName());
        assertEquals(dto.email(), responseDto.email());

        verify(studentMapper,times(1)).
                toStudent(dto);
        verify(repository,times(1)).
                save(student);
        verify(studentMapper,times(1)).
                toStudentResponseDto(savedStudent);
    }
    @Test
    public void should_return_all_students(){
        //given
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "john",
                "doe",
                "john@gmail.com"
                ,20
                ));
        //mock
        when(repository.findAll()).
                thenReturn(students);

        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "john",
                        "doe",
                        "john@gmail.com"
                ));
        //when
        List<StudentResponseDto> responseDtos=studentService.findAllStudents();
        //then
        assertEquals(students.size(), responseDtos.size());
        verify(repository,times(1)).findAll();

    }

    @Test
    public void should_return_student_by_id(){
        //given
        Integer id = 1;
        Student student = new Student(
                "john",
                "doe",
                "john@gmail.com"
                ,20
        );


        //
        //mock
        when(repository.findById(id))
                .thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(student)).
                thenReturn(new StudentResponseDto(
                        "john",
                        "doe",
                        "john@gmail.com"
                ));

        //when
        StudentResponseDto responseDto=studentService.findStudentById(id);
        System.out.println(responseDto);
        //then
        assertEquals(student.getFirstName(), responseDto.firstName());
        assertEquals(student.getLastName(), responseDto.lastName());
        assertEquals(student.getEmail(), responseDto.email());

        verify(repository,times(1)).findById(id);

    }
    @Test
    public void should_return_student_by_first_name(){
        String firstName = "john";
        List<Student> students=new ArrayList<>();

        students.add(new Student(
                "john",
                "doe",
                "john@gmail.com",
                22
        ));
        //mock
        when(repository.findAllByFirstNameContaining(firstName))
                .thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
        .thenReturn(new StudentResponseDto(
                "john",
                "doe",
                "john@gmail.com"
        ));

        //when
        List<StudentResponseDto> responseDtos =studentService.findStudentByName(firstName);
        //then
        assertEquals(students.size(), responseDtos.size());

        verify(repository,times(1)).findAllByFirstNameContaining(firstName);



    }
}