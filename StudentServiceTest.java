package com.example.demo.student;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test; 

import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private AutoCloseable autoCloseable;
    private StudentService underTest;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(testClass:this);
        underTest = new StudentService(studentRepository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }
    @Test
    void canGetAllStudents(){
        //when 
        underTest.getAllStudents();
        //then
        verify(studentRepository).findAll();

    }
    @Test
    void canAddStudents(){
        //given
        String email = "jamila@gmail.com";
        Student student = new Student("Jamila", email, Gender.FEMALE);

        //when
        underTest.addStudent(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = argumentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(student);
    }
    @Test
    void willThrowWhenEmailsTaken(){
        //given
        String email = "jamila@gmail.com";
        Student student = new Student("Jamila", email, Gender.FEMALE);

        given(stduentRepository.selectExistsEmail(student.getEmail())).willReturn(true);


        //when
        //then
        assertThrownBy(()->underTest.addStudent(student)).isIntanceOf(BadRequestException.class).hasMessageContaining("Email " + student.getEmail() + " taken");

        verify(studentRepository, never()).save(any());
    }

    @Test
    @Disabled
    void deleteStudents(){
        
    }
}
