package pl.sdacademy.intermediate.basic.basic10;

/**
 * @author Piotr Zietek
 */
class Rectangle {

    private int width;
    private int height;

    Rectangle(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Width and height must be >= 0!");
        }
        this.width = width;
        this.height = height;
    }

    int calculateArea() {
        return width * height;
    }

    int calculatePerimeter() {
        return 2 * width + 2 * height;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    void updateWidth(int newWidth) {
        if (newWidth > 0) {
            this.width = newWidth;
        }
    }

    void updateHeight(int newHeight) {
        if (newHeight > 0) {
            this.height = newHeight;
        }
    }
}
