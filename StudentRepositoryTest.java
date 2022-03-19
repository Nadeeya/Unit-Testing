package com.example.demo.student;

import org.checkerframework.checker.units.qual.s;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest{
    
    @Autowired
    private StudentRepositoryTest underTest;
    }

    @Test
    void itShouldCheckIfselectExistsEmail(){
        //given
        String email = "jamila@gmail.com";
        Student student = new Student("Jamila", email, Gender.FEMALE);

        underTest.save(student);

        //when
        boolean expected = underTest.selectExistsEmail(email);

        //then
        assertThat(expected).isTrue();


    }


}