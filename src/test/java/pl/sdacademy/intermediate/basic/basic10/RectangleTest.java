package pl.sdacademy.intermediate.basic.basic10;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Piotr Zietek
 */
public class RectangleTest {

    private Rectangle rectangle;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting RectangleTest");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Finishing RectangleTest");
    }

    @Before
    public void setUp() {
        rectangle = new Rectangle(3, 4);
    }

    @After
    public void tearDown() {
        System.out.println("After single test case");
    }

    @Test
    public void testCalculateArea() {
        Assert.assertEquals(12, rectangle.calculateArea());
    }

    @Test
    public void testCalculatePerimeter() {
        Assert.assertEquals(14, rectangle.calculatePerimeter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowExceptionOnIncorrectValuesInConstructor() {
        new Rectangle(-1, 4);
    }

    @Test
    public void testGetWidth() {
        Assert.assertEquals(3, rectangle.getWidth());
    }

    @Test
    public void testGetHeight() {
        Assert.assertEquals(4, rectangle.getHeight());
    }

    @Test
    public void testUpdateWidthWithCorrectValue() {
        rectangle.updateWidth(5);
        Assert.assertEquals(5, rectangle.getWidth());
    }

    @Test
    public void testUpdateHeightWithCorrectValue() {
        rectangle.updateHeight(9);
        Assert.assertEquals(9, rectangle.getHeight());
    }

    @Test
    public void testWidthNotUpdatedOnWrongValue() {
        rectangle.updateWidth(0);
        Assert.assertEquals(3, rectangle.getWidth());
    }

    @Test
    public void testHeightNotUpdatedOnWrongValue() {
        rectangle.updateHeight(0);
        Assert.assertEquals(4, rectangle.getHeight());
    }
}
