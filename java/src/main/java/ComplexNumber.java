class ComplexNumber {
    private final double real;
    private final double imaginary;

    ComplexNumber(final double real, final double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    double getReal() {
        return real;
    }

    double getImaginary() {
        return imaginary;
    }

    double abs2() {
        return real * real + imaginary * imaginary;
    }

    double abs() {
        return Math.sqrt(abs2());
    }

    ComplexNumber add(final ComplexNumber other) {
        return new ComplexNumber(real + other.real, imaginary + other.imaginary);
    }

    ComplexNumber subtract(final ComplexNumber other) {
        return new ComplexNumber(real - other.real, imaginary - other.imaginary);
    }

    ComplexNumber multiply(final ComplexNumber other) {
        return new ComplexNumber(
                (real * other.real - imaginary * other.imaginary),
                (imaginary * other.real + real * other.imaginary)
        );
    }

    ComplexNumber multiply(final double factor) {
        return new ComplexNumber(real * factor, imaginary * factor);
    }

    ComplexNumber divide(final ComplexNumber other) {
        final double divisor = other.abs2();
        return new ComplexNumber(
                (real * other.real + imaginary * other.imaginary)/divisor,
                (imaginary * other.real - real * other.imaginary)/divisor
        );
    }

    ComplexNumber divide(final double divisor) {
        return new ComplexNumber(real / divisor, imaginary / divisor);
    }

    ComplexNumber conjugate() {
        return new ComplexNumber(real, -imaginary);
    }

    ComplexNumber exponentialOf() {
        final double expReal = Math.exp(real);
        return new ComplexNumber(
                expReal * Math.cos(imaginary),
                expReal * Math.sin(imaginary)
        );
    }

}