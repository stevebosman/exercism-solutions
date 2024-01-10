import java.util.Arrays;

class CircularBuffer<T> {
    private static final int NO_OLDEST = -1;
    private final T[] buffer;
    private final int size;
    private int next;
    private int oldest = NO_OLDEST;
    
    CircularBuffer(final int size) {
        this.buffer = (T[])new Object[size];
        this.size = size;
    }

    T read() throws BufferIOException {
        System.out.println("pre-read: " + this);
        if (oldest == NO_OLDEST) {
            throw new BufferIOException("Tried to read from empty buffer");
        }
        final T value = buffer[oldest];
        buffer[oldest] = null;
        incrementOldest();
        System.out.println("post-read: " + this);
        return value;
    }

    void write(final T data) throws BufferIOException {
        System.out.println("pre-write: " + this);
        if (this.buffer[this.next] != null) {
            throw new BufferIOException("Tried to write to full buffer");
        }
        overwrite(data);
        System.out.println("post-write: " + this);
    }

    void overwrite(final T data) {
        System.out.println("pre-overwrite: " + this);
        if (oldest == NO_OLDEST) {
            oldest = next;
        } else if (oldest == next) {
            incrementOldest();
        }
        this.buffer[next] = data;
        this.next = (this.next + 1) % this.size;
        System.out.println("post-overwrite: " + this);
    }

    private void incrementOldest() {
        this.oldest = (this.oldest + 1) % this.size;
        if (this.oldest == this.next) {
            this.oldest = NO_OLDEST;
        }
    }

    void clear() {
        for (int i = 0; i < buffer.length; i++) buffer[i] = null;
        this.next = 0;
        this.oldest = NO_OLDEST;
    }

    public String toString() {
        return "[buffer=" + Arrays.toString(buffer) + "; size=" + size + "; next=" + next + "; oldest=" + oldest + "]";
    }

}