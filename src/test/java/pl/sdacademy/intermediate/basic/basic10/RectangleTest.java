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

    private static final int DEFAULT_WIDTH = 3;
    private static final int DEFAULT_HEIGHT = 4;
    private static final int DEFAULT_AREA = 12;
    private static final int DEFAULT_PERIMETER = 14;

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
        rectangle = new Rectangle(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @After
    public void tearDown() {
        System.out.println("After single test case");
    }

    @Test
    public void testCalculateArea() {
        Assert.assertEquals(DEFAULT_AREA, rectangle.calculateArea());
    }

    @Test
    public void testCalculatePerimeter() {
        Assert.assertEquals(DEFAULT_PERIMETER, rectangle.calculatePerimeter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowExceptionOnIncorrectValuesInConstructor() {
        new Rectangle(-1, DEFAULT_HEIGHT);
    }

    @Test
    public void testGetWidth() {
        Assert.assertEquals(DEFAULT_WIDTH, rectangle.getWidth());
    }

    @Test
    public void testGetHeight() {
        Assert.assertEquals(DEFAULT_HEIGHT, rectangle.getHeight());
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
        Assert.assertEquals(DEFAULT_WIDTH, rectangle.getWidth());
    }

    @Test
    public void testHeightNotUpdatedOnWrongValue() {
        rectangle.updateHeight(0);
        Assert.assertEquals(DEFAULT_HEIGHT, rectangle.getHeight());
    }
}
