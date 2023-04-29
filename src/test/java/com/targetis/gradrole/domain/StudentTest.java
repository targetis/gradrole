package com.targetis.gradrole.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.targetis.gradrole.web.rest.TestUtil;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Student.class);
        Student student1 = new Student();
        student1.setId(1L);
        Student student2 = new Student();
        student2.setId(student1.getId());
        assertThat(student1).isEqualTo(student2);
        student2.setId(2L);
        assertThat(student1).isNotEqualTo(student2);
        student1.setId(null);
        assertThat(student1).isNotEqualTo(student2);
    }

    @Test
    void equalsVerifier2() throws Exception {
        TestUtil.equalsVerifier(Student.class);
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John Smith");
        student1.setEmail("john-smith@gmail.com");
        student1.setDob(LocalDate.of(1994, 11, 12));
        student1.setCourse("Computer Science");

        Student student2 = new Student();
        student2.setId(student1.getId());
        student2.setName(student1.getName());
        student2.setEmail(student1.getEmail());
        student2.setDob(student1.getDob());
        student2.setCourse(student1.getCourse());

        assertThat(student1).isEqualTo(student2);

        student2.setId(2L);
        assertThat(student1).isNotEqualTo(student2);

        student1.setId(null);
        assertThat(student1).isNotEqualTo(student2);
    }
}
