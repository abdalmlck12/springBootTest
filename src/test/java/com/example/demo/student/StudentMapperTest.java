package com.example.demo.student;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }
    @Test
    public void shouldMapStudentDtoToStudent() {
     StudentDto dto = new StudentDto(
             "john",
             "doe",
             "john@gmail.com",
             1
     );
     Student student =mapper.toStudent(dto);
     assertEquals(dto.firstName(),student.getFirstName());
     assertEquals(dto.lastName(),student.getLastName());
     assertEquals(dto.email(),student.getEmail());
     assertNotNull(dto.schoolId());
     assertEquals(dto.schoolId(),student.getSchool().getId());


    }
    @Test
    public void shouldMapStudentToStudentResponseDto() {
        //give
        Student student =new Student("john",
                "doe",
                "john@gmail.com",
                20);
        //when
        StudentResponseDto response = mapper.toStudentResponseDto( student);
        //then
        assertEquals(response.firstName(),student.getFirstName());
        assertEquals(response.lastName(),student.getLastName());
        assertEquals(response.email(),student.getEmail());


    }
    @Test
    public  void should_throw_null_pointer_exception_when_studentDto_is_null(){
     var msg=    assertThrows(NullPointerException.class, ()->mapper.toStudent(null));
     assertEquals("the student dto is null",msg.getMessage());
    }
}