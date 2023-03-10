package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).contains("Sp")
                .isNotBlank()
                .isNotEmpty()
                .isEqualTo("Sphere");
        assertThat(box.getArea())
                .isGreaterThan(1250)
                .isLessThan(1260)
                .isEqualTo(1256.63d, withPrecision(0.01d));
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 8);
        String name = box.whatsThis();
        assertThat(name)
                .startsWith("Tet")
                .endsWith("on")
                .isEqualTo("Tetrahedron");
        assertThat(box.getArea())
                .isGreaterThan(110)
                .isLessThan(111)
                .isEqualTo(110.85d, withPrecision(0.01d));
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 16);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
        assertThat(box.getArea())
                .isGreaterThan(1535.9d)
                .isLessThan(1537)
                .isEqualTo(1536.00d, withPrecision(0.1d));
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(9, 16);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
        assertThat(box.getArea())
                .isNotNegative()
                .isNotPositive()
                .isEqualTo(0.0d);
    }

    @Test
    void whenBoxIsNotExistThenFalse() {
        Box box = new Box(-10, 5);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void whenBoxIsExistThenTrue() {
        Box box = new Box(4, 5);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void whenVertexIsGreaterThen0ThenNumberOfVerticesIsLessThen0() {
        Box box = new Box(3, 5);
        assertThat(box.getNumberOfVertices())
                .isNotPositive()
                .isLessThan(0);
    }

    @Test
    void whenVertexIsLessThen0ThenNumberOfVerticesIsLessThen0() {
        Box box = new Box(-4, 5);
        assertThat(box.getNumberOfVertices())
                .isNotPositive()
                .isLessThan(0);
    }
}